package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Type;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.send.DataTransportCrashlyticsReportSender;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class SessionReportingCoordinator implements CrashlyticsLifecycleEvents {
    private static final int EVENT_THREAD_IMPORTANCE = 4;
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    private static final int MAX_CHAINED_EXCEPTION_DEPTH = 8;
    private String currentSessionId;
    private final CrashlyticsReportDataCapture dataCapture;
    private final LogFileManager logFileManager;
    private final UserMetadata reportMetadata;
    private final CrashlyticsReportPersistence reportPersistence;
    private final DataTransportCrashlyticsReportSender reportsSender;

    public static SessionReportingCoordinator create(Context context, IdManager idManager, FileStore fileStore, AppData appData, LogFileManager logFileManager2, UserMetadata userMetadata, StackTraceTrimmingStrategy stackTraceTrimmingStrategy, SettingsDataProvider settingsProvider) {
        Context context2 = context;
        IdManager idManager2 = idManager;
        SessionReportingCoordinator sessionReportingCoordinator = new SessionReportingCoordinator(new CrashlyticsReportDataCapture(context, idManager, appData, stackTraceTrimmingStrategy), new CrashlyticsReportPersistence(new File(fileStore.getFilesDirPath()), settingsProvider), DataTransportCrashlyticsReportSender.create(context), logFileManager2, userMetadata);
        return sessionReportingCoordinator;
    }

    SessionReportingCoordinator(CrashlyticsReportDataCapture dataCapture2, CrashlyticsReportPersistence reportPersistence2, DataTransportCrashlyticsReportSender reportsSender2, LogFileManager logFileManager2, UserMetadata reportMetadata2) {
        this.dataCapture = dataCapture2;
        this.reportPersistence = reportPersistence2;
        this.reportsSender = reportsSender2;
        this.logFileManager = logFileManager2;
        this.reportMetadata = reportMetadata2;
    }

    public void onBeginSession(String sessionId, long timestamp) {
        this.currentSessionId = sessionId;
        this.reportPersistence.persistReport(this.dataCapture.captureReportData(sessionId, timestamp));
    }

    public void onLog(long timestamp, String log) {
        this.logFileManager.writeToLog(timestamp, log);
    }

    public void onCustomKey(String key, String value) {
        this.reportMetadata.setCustomKey(key, value);
    }

    public void onUserId(String userId) {
        this.reportMetadata.setUserId(userId);
    }

    public void onEndSession() {
        this.currentSessionId = null;
    }

    public void persistFatalEvent(Throwable event, Thread thread, long timestamp) {
        persistEvent(event, thread, "crash", timestamp, true);
    }

    public void persistNonFatalEvent(Throwable event, Thread thread, long timestamp) {
        persistEvent(event, thread, EVENT_TYPE_LOGGED, timestamp, false);
    }

    public void finalizeSessionWithNativeEvent(String sessionId, List<NativeSessionFile> nativeSessionFiles) {
        ArrayList<FilesPayload.File> nativeFiles = new ArrayList<>();
        for (NativeSessionFile nativeSessionFile : nativeSessionFiles) {
            FilesPayload.File filePayload = nativeSessionFile.asFilePayload();
            if (filePayload != null) {
                nativeFiles.add(filePayload);
            }
        }
        this.reportPersistence.finalizeSessionWithNativeEvent(sessionId, FilesPayload.builder().setFiles(ImmutableList.from((List<E>) nativeFiles)).build());
    }

    public void persistUserId() {
        String sessionId = this.currentSessionId;
        if (sessionId == null) {
            Logger.getLogger().mo19679d("Could not persist user ID; no current session");
            return;
        }
        String userId = this.reportMetadata.getUserId();
        if (userId == null) {
            Logger.getLogger().mo19679d("Could not persist user ID; no user ID available");
        } else {
            this.reportPersistence.persistUserIdForSession(userId, sessionId);
        }
    }

    public void finalizeSessions(long timestamp) {
        this.reportPersistence.finalizeReports(this.currentSessionId, timestamp);
    }

    public void removeAllReports() {
        this.reportPersistence.deleteAllReports();
    }

    public void sendReports(Executor reportSendCompleteExecutor, DataTransportState dataTransportState) {
        if (dataTransportState == DataTransportState.NONE) {
            Logger.getLogger().mo19679d("Send via DataTransport disabled. Removing DataTransport reports.");
            this.reportPersistence.deleteAllReports();
            return;
        }
        for (CrashlyticsReportWithSessionId reportToSend : this.reportPersistence.loadFinalizedReports()) {
            if (reportToSend.getReport().getType() != Type.NATIVE || dataTransportState == DataTransportState.ALL) {
                this.reportsSender.sendReport(reportToSend).continueWith(reportSendCompleteExecutor, SessionReportingCoordinator$$Lambda$1.lambdaFactory$(this));
            } else {
                Logger.getLogger().mo19679d("Send native reports via DataTransport disabled. Removing DataTransport reports.");
                this.reportPersistence.deleteFinalizedReport(reportToSend.getSessionId());
            }
        }
    }

    private void persistEvent(Throwable event, Thread thread, String eventType, long timestamp, boolean includeAllThreads) {
        String sessionId = this.currentSessionId;
        if (sessionId == null) {
            Logger.getLogger().mo19679d("Cannot persist event, no currently open session");
            return;
        }
        boolean isHighPriority = eventType.equals("crash");
        Event capturedEvent = this.dataCapture.captureEventData(event, thread, eventType, timestamp, 4, 8, includeAllThreads);
        Builder eventBuilder = capturedEvent.toBuilder();
        String content = this.logFileManager.getLogString();
        if (content != null) {
            eventBuilder.setLog(Log.builder().setContent(content).build());
        } else {
            Logger.getLogger().mo19679d("No log data to include with this event.");
        }
        List<CustomAttribute> sortedCustomAttributes = getSortedCustomAttributes(this.reportMetadata.getCustomKeys());
        if (!sortedCustomAttributes.isEmpty()) {
            eventBuilder.setApp(capturedEvent.getApp().toBuilder().setCustomAttributes(ImmutableList.from(sortedCustomAttributes)).build());
        }
        this.reportPersistence.persistEvent(eventBuilder.build(), sessionId, isHighPriority);
    }

    /* access modifiers changed from: private */
    public boolean onReportSendComplete(Task<CrashlyticsReportWithSessionId> task) {
        if (task.isSuccessful()) {
            CrashlyticsReportWithSessionId report = (CrashlyticsReportWithSessionId) task.getResult();
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Crashlytics report successfully enqueued to DataTransport: ");
            sb.append(report.getSessionId());
            logger.mo19679d(sb.toString());
            this.reportPersistence.deleteFinalizedReport(report.getSessionId());
            return true;
        }
        Logger.getLogger().mo19680d("Crashlytics report could not be enqueued to DataTransport", task.getException());
        return false;
    }

    private static List<CustomAttribute> getSortedCustomAttributes(Map<String, String> attributes) {
        ArrayList<CustomAttribute> attributesList = new ArrayList<>();
        attributesList.ensureCapacity(attributes.size());
        for (Entry<String, String> entry : attributes.entrySet()) {
            attributesList.add(CustomAttribute.builder().setKey((String) entry.getKey()).setValue((String) entry.getValue()).build());
        }
        Collections.sort(attributesList, SessionReportingCoordinator$$Lambda$2.lambdaFactory$());
        return attributesList;
    }
}
