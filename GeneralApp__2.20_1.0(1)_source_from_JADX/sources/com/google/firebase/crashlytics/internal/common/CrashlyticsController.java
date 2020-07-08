package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver.CrashlyticsOriginEventListener;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.log.LogFileManager.DirectoryProvider;
import com.google.firebase.crashlytics.internal.ndk.NativeFileUtils;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream;
import com.google.firebase.crashlytics.internal.proto.CodedOutputStream;
import com.google.firebase.crashlytics.internal.proto.SessionProtobufHelper;
import com.google.firebase.crashlytics.internal.report.ReportManager;
import com.google.firebase.crashlytics.internal.report.ReportUploader;
import com.google.firebase.crashlytics.internal.report.ReportUploader.HandlingExceptionCheck;
import com.google.firebase.crashlytics.internal.report.ReportUploader.Provider;
import com.google.firebase.crashlytics.internal.report.ReportUploader.ReportFilesProvider;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.model.Report.Type;
import com.google.firebase.crashlytics.internal.report.model.SessionReport;
import com.google.firebase.crashlytics.internal.report.network.CompositeCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.CreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.DefaultCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.NativeCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.stacktrace.MiddleOutFallbackStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.RemoveRepeatsStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import com.google.firebase.crashlytics.internal.unity.UnityVersionProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.cache.DiskLruCache;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    static final String FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS = "clx";
    static final String FIREBASE_APPLICATION_EXCEPTION = "_ae";
    static final String FIREBASE_CRASH_TYPE = "fatal";
    static final int FIREBASE_CRASH_TYPE_FATAL = 1;
    static final String FIREBASE_TIMESTAMP = "timestamp";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final String[] INITIAL_SESSION_PART_TAGS = {SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file1, File file2) {
            return file2.getName().compareTo(file1.getName());
        }
    };
    private static final int MAX_CHAINED_EXCEPTION_DEPTH = 8;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NATIVE_SESSION_DIR = "native-sessions";
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", DiskLruCache.VERSION_1);
    static final String SESSION_APP_TAG = "SessionApp";
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter(SESSION_BEGIN_TAG) {
        public boolean accept(File dir, String filename) {
            return super.accept(dir, filename) && filename.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File dir, String filename) {
            int length = filename.length();
            String str = ClsFileOutputStream.SESSION_FILE_EXTENSION;
            return length == str.length() + 35 && filename.endsWith(str);
        }
    };
    /* access modifiers changed from: private */
    public static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_USER_TAG = "SessionUser";
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file1, File file2) {
            return file1.getName().compareTo(file2.getName());
        }
    };
    /* access modifiers changed from: private */
    public final AnalyticsConnector analyticsConnector;
    /* access modifiers changed from: private */
    public final AnalyticsReceiver analyticsReceiver;
    /* access modifiers changed from: private */
    public final AppData appData;
    /* access modifiers changed from: private */
    public final CrashlyticsBackgroundWorker backgroundWorker;
    AtomicBoolean checkForUnsentReportsCalled = new AtomicBoolean(false);
    private final Context context;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    /* access modifiers changed from: private */
    public final CrashlyticsFileMarker crashMarker;
    /* access modifiers changed from: private */
    public final DataCollectionArbiter dataCollectionArbiter;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    /* access modifiers changed from: private */
    public final HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    /* access modifiers changed from: private */
    public final LogFileManager logFileManager;
    private final CrashlyticsNativeComponent nativeComponent;
    TaskCompletionSource<Boolean> reportActionProvided = new TaskCompletionSource<>();
    /* access modifiers changed from: private */
    public final ReportManager reportManager;
    /* access modifiers changed from: private */
    public final Provider reportUploaderProvider;
    /* access modifiers changed from: private */
    public final SessionReportingCoordinator reportingCoordinator;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    /* access modifiers changed from: private */
    public final String unityVersion;
    TaskCompletionSource<Boolean> unsentReportsAvailable = new TaskCompletionSource<>();
    TaskCompletionSource<Void> unsentReportsHandled = new TaskCompletionSource<>();
    private final UserMetadata userMetadata;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String fileName) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, fileName) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(fileName).matches();
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static class BlockingCrashEventListener implements CrashlyticsOriginEventListener {
        private static final int APP_EXCEPTION_CALLBACK_TIMEOUT_MS = 2000;
        private final CountDownLatch eventLatch;

        private BlockingCrashEventListener() {
            this.eventLatch = new CountDownLatch(1);
        }

        public void awaitEvent() throws InterruptedException {
            Logger.getLogger().mo19679d("Background thread awaiting app exception callback from FA...");
            if (this.eventLatch.await(2000, TimeUnit.MILLISECONDS)) {
                Logger.getLogger().mo19679d("App exception callback received from FA listener.");
            } else {
                Logger.getLogger().mo19679d("Timeout exceeded while awaiting app exception callback from FA listener.");
            }
        }

        public void onCrashlyticsOriginEvent(int id, Bundle extras) {
            if ("_ae".equals(extras.getString("name"))) {
                this.eventLatch.countDown();
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream) throws Exception;
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String s) {
            this.string = s;
        }

        public boolean accept(File dir, String filename) {
            return filename.contains(this.string) && !filename.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String fileName) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, fileName) || fileName.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG);
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class LogFileDirectoryProvider implements DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore rootFileStore2) {
            this.rootFileStore = rootFileStore2;
        }

        public File getLogFileDir() {
            File logFileDir = new File(this.rootFileStore.getFilesDir(), LOG_FILES_DIR);
            if (!logFileDir.exists()) {
                logFileDir.mkdirs();
            }
            return logFileDir;
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private final class ReportUploaderFilesProvider implements ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        public File[] getNativeReportFiles() {
            return CrashlyticsController.this.listNativeSessionFileDirectories();
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private final class ReportUploaderHandlingExceptionCheck implements HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final boolean dataCollectionToken;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context2, Report report2, ReportUploader reportUploader2, boolean dataCollectionToken2) {
            this.context = context2;
            this.report = report2;
            this.reportUploader = reportUploader2;
            this.dataCollectionToken = dataCollectionToken2;
        }

        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Logger.getLogger().mo19679d("Attempting to send crash report at time of crash...");
                this.reportUploader.uploadReport(this.report, this.dataCollectionToken);
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String sessionId2) {
            this.sessionId = sessionId2;
        }

        public boolean accept(File file, String fileName) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.sessionId);
            sb.append(ClsFileOutputStream.SESSION_FILE_EXTENSION);
            boolean z = false;
            if (fileName.equals(sb.toString())) {
                return false;
            }
            if (fileName.contains(this.sessionId) && !fileName.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION)) {
                z = true;
            }
            return z;
        }
    }

    CrashlyticsController(Context context2, CrashlyticsBackgroundWorker backgroundWorker2, HttpRequestFactory httpRequestFactory2, IdManager idManager2, DataCollectionArbiter dataCollectionArbiter2, FileStore fileStore2, CrashlyticsFileMarker crashMarker2, AppData appData2, ReportManager reportManager2, Provider reportUploaderProvider2, CrashlyticsNativeComponent nativeComponent2, UnityVersionProvider unityVersionProvider, AnalyticsReceiver analyticsReceiver2, AnalyticsConnector analyticsConnector2, SettingsDataProvider settingsDataProvider) {
        ReportManager reportManager3;
        Context context3 = context2;
        FileStore fileStore3 = fileStore2;
        Provider provider = reportUploaderProvider2;
        this.context = context3;
        this.backgroundWorker = backgroundWorker2;
        this.httpRequestFactory = httpRequestFactory2;
        this.idManager = idManager2;
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.fileStore = fileStore3;
        this.crashMarker = crashMarker2;
        this.appData = appData2;
        if (provider != null) {
            this.reportUploaderProvider = provider;
        } else {
            this.reportUploaderProvider = defaultReportUploader();
        }
        this.nativeComponent = nativeComponent2;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.analyticsReceiver = analyticsReceiver2;
        this.analyticsConnector = analyticsConnector2;
        this.userMetadata = new UserMetadata();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore3);
        this.logFileManager = new LogFileManager(context3, this.logFileDirectoryProvider);
        if (reportManager2 == null) {
            reportManager3 = new ReportManager(new ReportUploaderFilesProvider());
            ReportManager reportManager4 = reportManager3;
        } else {
            reportManager3 = reportManager2;
        }
        this.reportManager = reportManager3;
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        ReportManager reportManager5 = reportManager3;
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
        LogFileManager logFileManager2 = this.logFileManager;
        UserMetadata userMetadata2 = this.userMetadata;
        UserMetadata userMetadata3 = userMetadata2;
        LogFileManager logFileManager3 = logFileManager2;
        this.reportingCoordinator = SessionReportingCoordinator.create(context2, idManager2, fileStore2, appData2, logFileManager3, userMetadata3, this.stackTraceTrimmingStrategy, settingsDataProvider);
    }

    private Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: 0000 */
    public void enableExceptionHandling(UncaughtExceptionHandler defaultHandler, SettingsDataProvider settingsProvider) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new CrashListener() {
            public void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable ex) {
                CrashlyticsController.this.handleUncaughtException(settingsDataProvider, thread, ex);
            }
        }, settingsProvider, defaultHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void handleUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable ex) {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Crashlytics is handling uncaught exception \"");
        sb.append(ex);
        sb.append("\" from thread ");
        sb.append(thread.getName());
        logger.mo19679d(sb.toString());
        final Date time = new Date();
        final Task<Void> recordFatalFirebaseEventTask = recordFatalFirebaseEvent(time.getTime());
        CrashlyticsBackgroundWorker crashlyticsBackgroundWorker = this.backgroundWorker;
        final Throwable th = ex;
        final Thread thread2 = thread;
        final SettingsDataProvider settingsDataProvider2 = settingsDataProvider;
        C07536 r2 = new Callable<Task<Void>>() {
            public Task<Void> call() throws Exception {
                CrashlyticsController.this.crashMarker.create();
                long timestampSeconds = CrashlyticsController.getTimestampSeconds(time);
                CrashlyticsController.this.reportingCoordinator.persistFatalEvent(th, thread2, timestampSeconds);
                CrashlyticsController.this.writeFatal(thread2, th, timestampSeconds);
                Settings settings = settingsDataProvider2.getSettings();
                int maxCustomExceptionEvents = settings.getSessionData().maxCustomExceptionEvents;
                int maxCompleteSessionsCount = settings.getSessionData().maxCompleteSessionsCount;
                CrashlyticsController.this.doCloseSessions(maxCustomExceptionEvents);
                CrashlyticsController.this.doOpenSession();
                CrashlyticsController.this.trimSessionFiles(maxCompleteSessionsCount);
                if (!CrashlyticsController.this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
                    return Tasks.forResult(null);
                }
                final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                return settingsDataProvider2.getAppSettings().onSuccessTask(executor, new SuccessContinuation<AppSettingsData, Void>() {
                    public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                        if (appSettingsData == null) {
                            Logger.getLogger().mo19689w("Received null app settings, cannot send reports at crash time.");
                            return Tasks.forResult(null);
                        }
                        CrashlyticsController.this.sendSessionReports(appSettingsData, true);
                        CrashlyticsController.this.reportingCoordinator.sendReports(executor, DataTransportState.getState(appSettingsData));
                        return recordFatalFirebaseEventTask;
                    }
                });
            }
        };
        try {
            Utils.awaitEvenIfOnMainThread(crashlyticsBackgroundWorker.submitTask(r2));
        } catch (Exception e) {
        }
    }

    private Task<Boolean> waitForReportAction() {
        boolean isAutomaticDataCollectionEnabled = this.dataCollectionArbiter.isAutomaticDataCollectionEnabled();
        Boolean valueOf = Boolean.valueOf(true);
        if (isAutomaticDataCollectionEnabled) {
            Logger.getLogger().mo19679d("Automatic data collection is enabled. Allowing upload.");
            this.unsentReportsAvailable.trySetResult(Boolean.valueOf(false));
            return Tasks.forResult(valueOf);
        }
        Logger.getLogger().mo19679d("Automatic data collection is disabled.");
        Logger.getLogger().mo19679d("Notifying that unsent reports are available.");
        this.unsentReportsAvailable.trySetResult(valueOf);
        Task<Boolean> collectionEnabled = this.dataCollectionArbiter.waitForAutomaticDataCollectionEnabled().onSuccessTask(new SuccessContinuation<Void, Boolean>() {
            public Task<Boolean> then(Void aVoid) throws Exception {
                return Tasks.forResult(Boolean.valueOf(true));
            }
        });
        Logger.getLogger().mo19679d("Waiting for send/deleteUnsentReports to be called.");
        return Utils.race(collectionEnabled, this.reportActionProvided.getTask());
    }

    /* access modifiers changed from: 0000 */
    public boolean didCrashOnPreviousExecution() {
        if (!this.crashMarker.isPresent()) {
            String sessionId = getCurrentSessionId();
            return sessionId != null && this.nativeComponent.hasCrashDataForSession(sessionId);
        }
        Logger.getLogger().mo19679d("Found previous crash marker.");
        this.crashMarker.remove();
        return Boolean.TRUE.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    public Task<Boolean> checkForUnsentReports() {
        if (this.checkForUnsentReportsCalled.compareAndSet(false, true)) {
            return this.unsentReportsAvailable.getTask();
        }
        Logger.getLogger().mo19679d("checkForUnsentReports should only be called once per execution.");
        return Tasks.forResult(Boolean.valueOf(false));
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> sendUnsentReports() {
        this.reportActionProvided.trySetResult(Boolean.valueOf(true));
        return this.unsentReportsHandled.getTask();
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> deleteUnsentReports() {
        this.reportActionProvided.trySetResult(Boolean.valueOf(false));
        return this.unsentReportsHandled.getTask();
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> submitAllReports(final float delay, final Task<AppSettingsData> appSettingsDataTask) {
        if (!this.reportManager.areReportsAvailable()) {
            Logger.getLogger().mo19679d("No reports are available.");
            this.unsentReportsAvailable.trySetResult(Boolean.valueOf(false));
            return Tasks.forResult(null);
        }
        Logger.getLogger().mo19679d("Unsent reports are available.");
        return waitForReportAction().onSuccessTask(new SuccessContinuation<Boolean, Void>() {
            public Task<Void> then(final Boolean send) throws Exception {
                return CrashlyticsController.this.backgroundWorker.submitTask(new Callable<Task<Void>>() {
                    public Task<Void> call() throws Exception {
                        final List<Report> reports = CrashlyticsController.this.reportManager.findReports();
                        if (!send.booleanValue()) {
                            Logger.getLogger().mo19679d("Reports are being deleted.");
                            CrashlyticsController.this.reportManager.deleteReports(reports);
                            CrashlyticsController.this.reportingCoordinator.removeAllReports();
                            CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                            return Tasks.forResult(null);
                        }
                        Logger.getLogger().mo19679d("Reports are being sent.");
                        final boolean dataCollectionToken = send.booleanValue();
                        CrashlyticsController.this.dataCollectionArbiter.grantDataCollectionPermission(dataCollectionToken);
                        final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                        return appSettingsDataTask.onSuccessTask(executor, new SuccessContinuation<AppSettingsData, Void>() {
                            public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                                if (appSettingsData == null) {
                                    Logger.getLogger().mo19689w("Received null app settings, cannot send reports during app startup.");
                                    return Tasks.forResult(null);
                                }
                                for (Report report : reports) {
                                    if (report.getType() == Type.JAVA) {
                                        CrashlyticsController.appendOrganizationIdToSessionFile(appSettingsData.organizationId, report.getFile());
                                    }
                                }
                                CrashlyticsController.this.reportUploaderProvider.createReportUploader(appSettingsData).uploadReportsAsync(reports, dataCollectionToken, delay);
                                CrashlyticsController.this.reportingCoordinator.sendReports(executor, DataTransportState.getState(appSettingsData));
                                CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                                return Tasks.forResult(null);
                            }
                        });
                    }
                });
            }
        });
    }

    private Provider defaultReportUploader() {
        return new Provider() {
            public ReportUploader createReportUploader(AppSettingsData appSettingsData) {
                String reportsUrl = appSettingsData.reportsUrl;
                String ndkReportsUrl = appSettingsData.ndkReportsUrl;
                String organizationId = appSettingsData.organizationId;
                ReportUploader reportUploader = new ReportUploader(organizationId, CrashlyticsController.this.appData.googleAppId, DataTransportState.getState(appSettingsData), CrashlyticsController.this.reportManager, CrashlyticsController.this.getCreateReportSpiCall(reportsUrl, ndkReportsUrl), CrashlyticsController.this.handlingExceptionCheck);
                return reportUploader;
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void writeToLog(final long timestamp, final String msg) {
        this.backgroundWorker.submit((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.logFileManager.writeToLog(timestamp, msg);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void writeNonFatalException(final Thread thread, final Throwable ex) {
        final Date time = new Date();
        this.backgroundWorker.submit((Runnable) new Runnable() {
            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    long timestampSeconds = CrashlyticsController.getTimestampSeconds(time);
                    CrashlyticsController.this.reportingCoordinator.persistNonFatalEvent(ex, thread, timestampSeconds);
                    CrashlyticsController.this.doWriteNonFatal(thread, ex, timestampSeconds);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void setUserId(String identifier) {
        this.userMetadata.setUserId(identifier);
        cacheUserData(this.userMetadata);
    }

    /* access modifiers changed from: 0000 */
    public void setCustomKey(String key, String value) {
        try {
            this.userMetadata.setCustomKey(key, value);
            cacheKeyData(this.userMetadata.getCustomKeys());
        } catch (IllegalArgumentException ex) {
            Context context2 = this.context;
            if (context2 == null || !CommonUtils.isAppDebuggable(context2)) {
                Logger.getLogger().mo19681e("Attempting to set custom attribute with null key, ignoring.");
                return;
            }
            throw ex;
        }
    }

    private void cacheUserData(final UserMetadata userMetaData) {
        this.backgroundWorker.submit((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsController.this.reportingCoordinator.persistUserId();
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(CrashlyticsController.this.getCurrentSessionId(), userMetaData);
                return null;
            }
        });
    }

    private void cacheKeyData(final Map<String, String> keyData) {
        this.backgroundWorker.submit((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), keyData);
                return null;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void openSession() {
        this.backgroundWorker.submit((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public String getCurrentSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(sessionBeginFiles[0]);
        }
        return null;
    }

    private String getPreviousSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length > 1) {
            return getSessionIdFromSessionFile(sessionBeginFiles[1]);
        }
        return null;
    }

    static String getSessionIdFromSessionFile(File sessionFile) {
        return sessionFile.getName().substring(0, 35);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean finalizeSessions(int maxCustomExceptionEvents) {
        this.backgroundWorker.checkRunningOnThread();
        if (isHandlingException()) {
            Logger.getLogger().mo19679d("Skipping session finalization because a crash has already occurred.");
            return Boolean.FALSE.booleanValue();
        }
        Logger.getLogger().mo19679d("Finalizing previously open sessions.");
        try {
            doCloseSessions(maxCustomExceptionEvents, false);
            Logger.getLogger().mo19679d("Closed all previously open sessions");
            return true;
        } catch (Exception e) {
            Logger.getLogger().mo19682e("Unable to finalize previously open sessions.", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void doOpenSession() throws Exception {
        long startedAtSeconds = getCurrentTimestampSeconds();
        String sessionIdentifier = new CLSUUID(this.idManager).toString();
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Opening a new session with ID ");
        sb.append(sessionIdentifier);
        logger.mo19679d(sb.toString());
        this.nativeComponent.openSession(sessionIdentifier);
        writeBeginSession(sessionIdentifier, startedAtSeconds);
        writeSessionApp(sessionIdentifier);
        writeSessionOS(sessionIdentifier);
        writeSessionDevice(sessionIdentifier);
        this.logFileManager.setCurrentSession(sessionIdentifier);
        this.reportingCoordinator.onBeginSession(makeFirebaseSessionIdentifier(sessionIdentifier), startedAtSeconds);
    }

    /* access modifiers changed from: 0000 */
    public void doCloseSessions(int maxCustomExceptionEvents) throws Exception {
        doCloseSessions(maxCustomExceptionEvents, true);
    }

    private void doCloseSessions(int maxCustomExceptionEvents, boolean includeCurrent) throws Exception {
        int offset = !includeCurrent;
        trimOpenSessions(offset + 8);
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length <= offset) {
            Logger.getLogger().mo19679d("No open sessions to be closed.");
            return;
        }
        String mostRecentSessionIdToClose = getSessionIdFromSessionFile(sessionBeginFiles[offset]);
        writeSessionUser(mostRecentSessionIdToClose);
        if (includeCurrent) {
            this.reportingCoordinator.onEndSession();
        } else if (this.nativeComponent.hasCrashDataForSession(mostRecentSessionIdToClose)) {
            finalizePreviousNativeSession(mostRecentSessionIdToClose);
            if (!this.nativeComponent.finalizeSession(mostRecentSessionIdToClose)) {
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Could not finalize native session: ");
                sb.append(mostRecentSessionIdToClose);
                logger.mo19679d(sb.toString());
            }
        }
        closeOpenSessions(sessionBeginFiles, offset, maxCustomExceptionEvents);
        this.reportingCoordinator.finalizeSessions(getCurrentTimestampSeconds());
    }

    private void closeOpenSessions(File[] sessionBeginFiles, int beginIndex, int maxLoggedExceptionsCount) {
        Logger.getLogger().mo19679d("Closing open sessions.");
        for (int i = beginIndex; i < sessionBeginFiles.length; i++) {
            File sessionBeginFile = sessionBeginFiles[i];
            String sessionIdentifier = getSessionIdFromSessionFile(sessionBeginFile);
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Closing session: ");
            sb.append(sessionIdentifier);
            logger.mo19679d(sb.toString());
            writeSessionPartsToSessionFile(sessionBeginFile, sessionIdentifier, maxLoggedExceptionsCount);
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream fos) {
        if (fos != null) {
            try {
                fos.closeInProgressStream();
            } catch (IOException ex) {
                Logger.getLogger().mo19682e("Error closing session file stream in the presence of an exception", ex);
            }
        }
    }

    private void deleteSessionPartFilesFor(String sessionId) {
        for (File file : listSessionPartFilesFor(sessionId)) {
            file.delete();
        }
    }

    private File[] listSessionPartFilesFor(String sessionId) {
        return listFilesMatching(new SessionPartFileFilter(sessionId));
    }

    /* access modifiers changed from: 0000 */
    public File[] listCompleteSessionFiles() {
        List<File> completeSessionFiles = new LinkedList<>();
        Collections.addAll(completeSessionFiles, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) completeSessionFiles.toArray(new File[completeSessionFiles.size()]);
    }

    /* access modifiers changed from: 0000 */
    public File[] listNativeSessionFileDirectories() {
        return ensureFileArrayNotNull(getNativeSessionFilesDir().listFiles());
    }

    /* access modifiers changed from: 0000 */
    public File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] sessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(sessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return sessionBeginFiles;
    }

    /* access modifiers changed from: private */
    public File[] listFilesMatching(FilenameFilter filter) {
        return listFilesMatching(getFilesDir(), filter);
    }

    private File[] listFilesMatching(File directory, FilenameFilter filter) {
        return ensureFileArrayNotNull(directory.listFiles(filter));
    }

    private File[] listFiles(File directory) {
        return ensureFileArrayNotNull(directory.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] files) {
        return files == null ? new File[0] : files;
    }

    private void trimSessionEventFiles(String sessionId, int limit) {
        File filesDir = getFilesDir();
        StringBuilder sb = new StringBuilder();
        sb.append(sessionId);
        sb.append(SESSION_NON_FATAL_TAG);
        Utils.capFileCount(filesDir, new FileNameContainsFilter(sb.toString()), limit, SMALLEST_FILE_NAME_FIRST);
    }

    /* access modifiers changed from: 0000 */
    public void trimSessionFiles(int maxCompleteSessionsCount) {
        int remaining = maxCompleteSessionsCount;
        int remaining2 = remaining - Utils.capSessionCount(getNativeSessionFilesDir(), getFatalSessionFilesDir(), remaining, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, remaining2 - Utils.capFileCount(getNonFatalSessionFilesDir(), remaining2, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int maxOpenSessionCount) {
        Set<String> sessionIdsToKeep = new HashSet<>();
        File[] beginSessionFiles = listSortedSessionBeginFiles();
        int count = Math.min(maxOpenSessionCount, beginSessionFiles.length);
        for (int i = 0; i < count; i++) {
            sessionIdsToKeep.add(getSessionIdFromSessionFile(beginSessionFiles[i]));
        }
        this.logFileManager.discardOldLogFiles(sessionIdsToKeep);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), sessionIdsToKeep);
    }

    private void retainSessions(File[] files, Set<String> sessionIdsToKeep) {
        for (File sessionPartFile : files) {
            String fileName = sessionPartFile.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(fileName);
            if (!matcher.matches()) {
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Deleting unknown file: ");
                sb.append(fileName);
                logger.mo19679d(sb.toString());
                sessionPartFile.delete();
            } else if (!sessionIdsToKeep.contains(matcher.group(1))) {
                Logger logger2 = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Trimming session file: ");
                sb2.append(fileName);
                logger2.mo19679d(sb2.toString());
                sessionPartFile.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String sessionId, File[] nonFatalFiles, int maxLoggedExceptionsCount) {
        if (nonFatalFiles.length <= maxLoggedExceptionsCount) {
            return nonFatalFiles;
        }
        Logger.getLogger().mo19679d(String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(maxLoggedExceptionsCount)}));
        trimSessionEventFiles(sessionId, maxLoggedExceptionsCount);
        StringBuilder sb = new StringBuilder();
        sb.append(sessionId);
        sb.append(SESSION_NON_FATAL_TAG);
        return listFilesMatching(new FileNameContainsFilter(sb.toString()));
    }

    /* access modifiers changed from: 0000 */
    public void cleanInvalidTempFiles() {
        this.backgroundWorker.submit((Runnable) new Runnable() {
            public void run() {
                CrashlyticsController crashlyticsController = CrashlyticsController.this;
                crashlyticsController.doCleanInvalidTempFiles(crashlyticsController.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void doCleanInvalidTempFiles(File[] invalidFiles) {
        File[] listFilesMatching;
        final Set<String> invalidSessionIds = new HashSet<>();
        for (File invalidFile : invalidFiles) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Found invalid session part file: ");
            sb.append(invalidFile);
            logger.mo19679d(sb.toString());
            invalidSessionIds.add(getSessionIdFromSessionFile(invalidFile));
        }
        if (!invalidSessionIds.isEmpty()) {
            for (File sessionFile : listFilesMatching(new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    if (filename.length() < 35) {
                        return false;
                    }
                    return invalidSessionIds.contains(filename.substring(0, 35));
                }
            })) {
                Logger logger2 = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Deleting invalid session file: ");
                sb2.append(sessionFile);
                logger2.mo19679d(sb2.toString());
                sessionFile.delete();
            }
        }
    }

    private void finalizePreviousNativeSession(String previousSessionId) {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Finalizing native report for session ");
        sb.append(previousSessionId);
        logger.mo19679d(sb.toString());
        NativeSessionFileProvider nativeSessionFileProvider = this.nativeComponent.getSessionFileProvider(previousSessionId);
        File minidumpFile = nativeSessionFileProvider.getMinidumpFile();
        if (minidumpFile == null || !minidumpFile.exists()) {
            Logger logger2 = Logger.getLogger();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No minidump data found for session ");
            sb2.append(previousSessionId);
            logger2.mo19689w(sb2.toString());
            return;
        }
        LogFileManager previousSessionLogManager = new LogFileManager(this.context, this.logFileDirectoryProvider, previousSessionId);
        File nativeSessionDirectory = new File(getNativeSessionFilesDir(), previousSessionId);
        if (!nativeSessionDirectory.mkdirs()) {
            Logger.getLogger().mo19679d("Couldn't create native sessions directory");
            return;
        }
        List<NativeSessionFile> nativeSessionFiles = getNativeSessionFiles(nativeSessionFileProvider, previousSessionId, getContext(), getFilesDir(), previousSessionLogManager.getBytesForLog());
        NativeSessionFileGzipper.processNativeSessions(nativeSessionDirectory, nativeSessionFiles);
        this.reportingCoordinator.finalizeSessionWithNativeEvent(makeFirebaseSessionIdentifier(previousSessionId), nativeSessionFiles);
        previousSessionLogManager.clearLog();
    }

    private static long getCurrentTimestampSeconds() {
        return getTimestampSeconds(new Date());
    }

    /* access modifiers changed from: private */
    public static long getTimestampSeconds(Date date) {
        return date.getTime() / 1000;
    }

    private static String makeFirebaseSessionIdentifier(String sessionIdentifier) {
        return sessionIdentifier.replaceAll("-", "");
    }

    /* access modifiers changed from: private */
    public void writeFatal(Thread thread, Throwable ex, long eventTime) {
        String str = "Failed to close fatal exception file output stream.";
        String str2 = "Failed to flush to session begin file.";
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                Logger.getLogger().mo19681e("Tried to write a fatal exception while no session was open.");
                CommonUtils.flushOrLog(null, str2);
                CommonUtils.closeOrLog(null, str);
                return;
            }
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(currentSessionId);
            sb.append(SESSION_FATAL_TAG);
            fos = new ClsFileOutputStream(filesDir, sb.toString());
            cos = CodedOutputStream.newInstance((OutputStream) fos);
            writeSessionEvent(cos, thread, ex, eventTime, "crash", true);
            CommonUtils.flushOrLog(cos, str2);
            CommonUtils.closeOrLog(fos, str);
        } catch (Exception e) {
            Logger.getLogger().mo19682e("An error occurred in the fatal exception logger", e);
        } catch (Throwable th) {
            CommonUtils.flushOrLog(cos, str2);
            CommonUtils.closeOrLog(fos, str);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void doWriteNonFatal(Thread thread, Throwable ex, long eventTime) {
        CodedOutputStream cos;
        CodedOutputStream cos2;
        String str = "Failed to close non-fatal file output stream.";
        String str2 = "Failed to flush to non-fatal file.";
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId == null) {
            Logger.getLogger().mo19679d("Tried to write a non-fatal exception while no session was open.");
            return;
        }
        ClsFileOutputStream fos = null;
        CodedOutputStream cos3 = null;
        try {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Crashlytics is logging non-fatal exception \"");
            sb.append(ex);
            sb.append("\" from thread ");
            sb.append(thread.getName());
            logger.mo19679d(sb.toString());
            String counterString = CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(currentSessionId);
            sb2.append(SESSION_NON_FATAL_TAG);
            sb2.append(counterString);
            ClsFileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), sb2.toString());
            try {
                cos = CodedOutputStream.newInstance((OutputStream) fos2);
                cos2 = cos;
            } catch (Exception e) {
                e = e;
                fos = fos2;
                try {
                    Logger.getLogger().mo19682e("An error occurred in the non-fatal exception logger", e);
                    CommonUtils.flushOrLog(cos3, str2);
                    CommonUtils.closeOrLog(fos, str);
                    ClsFileOutputStream clsFileOutputStream = fos;
                    CodedOutputStream codedOutputStream = cos3;
                    trimSessionEventFiles(currentSessionId, 64);
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.flushOrLog(cos3, str2);
                    CommonUtils.closeOrLog(fos, str);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fos = fos2;
                CommonUtils.flushOrLog(cos3, str2);
                CommonUtils.closeOrLog(fos, str);
                throw th;
            }
            try {
                writeSessionEvent(cos, thread, ex, eventTime, EVENT_TYPE_LOGGED, false);
                CommonUtils.flushOrLog(cos2, str2);
                CommonUtils.closeOrLog(fos2, str);
            } catch (Exception e2) {
                e = e2;
                cos3 = cos2;
                fos = fos2;
                Logger.getLogger().mo19682e("An error occurred in the non-fatal exception logger", e);
                CommonUtils.flushOrLog(cos3, str2);
                CommonUtils.closeOrLog(fos, str);
                ClsFileOutputStream clsFileOutputStream2 = fos;
                CodedOutputStream codedOutputStream2 = cos3;
                trimSessionEventFiles(currentSessionId, 64);
            } catch (Throwable th3) {
                th = th3;
                cos3 = cos2;
                fos = fos2;
                CommonUtils.flushOrLog(cos3, str2);
                CommonUtils.closeOrLog(fos, str);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Logger.getLogger().mo19682e("An error occurred in the non-fatal exception logger", e);
            CommonUtils.flushOrLog(cos3, str2);
            CommonUtils.closeOrLog(fos, str);
            ClsFileOutputStream clsFileOutputStream22 = fos;
            CodedOutputStream codedOutputStream22 = cos3;
            trimSessionEventFiles(currentSessionId, 64);
        }
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Exception e4) {
            Logger.getLogger().mo19682e("An error occurred when trimming non-fatal files.", e4);
        }
    }

    private void writeSessionPartFile(String sessionId, String tag, CodedOutputStreamWriteAction writeAction) throws Exception {
        String str = "Failed to close session ";
        String str2 = "Failed to flush to session ";
        String str3 = " file.";
        FileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(sessionId);
            sb.append(tag);
            fos = new ClsFileOutputStream(filesDir, sb.toString());
            cos = CodedOutputStream.newInstance((OutputStream) fos);
            writeAction.writeTo(cos);
        } finally {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(tag);
            sb2.append(str3);
            CommonUtils.flushOrLog(cos, sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(tag);
            sb3.append(str3);
            CommonUtils.closeOrLog(fos, sb3.toString());
        }
    }

    private static void appendToProtoFile(File file, CodedOutputStreamWriteAction writeAction) throws Exception {
        String str = "Failed to close ";
        String str2 = "Failed to flush to append to ";
        FileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            fos = new FileOutputStream(file, true);
            cos = CodedOutputStream.newInstance((OutputStream) fos);
            writeAction.writeTo(cos);
        } finally {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(file.getPath());
            CommonUtils.flushOrLog(cos, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(file.getPath());
            CommonUtils.closeOrLog(fos, sb2.toString());
        }
    }

    private void writeBeginSession(String sessionId, long startedAtSeconds) throws Exception {
        String generator = String.format(Locale.US, GENERATOR_FORMAT, new Object[]{CrashlyticsCore.getVersion()});
        final String str = sessionId;
        final String str2 = generator;
        final long j = startedAtSeconds;
        C074217 r3 = new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeBeginSession(arg, str, str2, j);
            }
        };
        writeSessionPartFile(sessionId, SESSION_BEGIN_TAG, r3);
        this.nativeComponent.writeBeginSession(sessionId, generator, startedAtSeconds);
    }

    private void writeSessionApp(String sessionId) throws Exception {
        String appIdentifier = this.idManager.getAppIdentifier();
        String versionCode = this.appData.versionCode;
        String versionName = this.appData.versionName;
        final String str = appIdentifier;
        final String str2 = versionCode;
        final String str3 = versionName;
        final String crashlyticsInstallId = this.idManager.getCrashlyticsInstallId();
        final int id = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        C074318 r1 = new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionApp(arg, str, str2, str3, crashlyticsInstallId, id, CrashlyticsController.this.unityVersion);
            }
        };
        writeSessionPartFile(sessionId, SESSION_APP_TAG, r1);
        this.nativeComponent.writeSessionApp(sessionId, str, str2, str3, crashlyticsInstallId, id, this.unityVersion);
    }

    private void writeSessionOS(String sessionId) throws Exception {
        final String osRelease = VERSION.RELEASE;
        final String osCodeName = VERSION.CODENAME;
        final boolean isRooted = CommonUtils.isRooted(getContext());
        writeSessionPartFile(sessionId, SESSION_OS_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionOS(arg, osRelease, osCodeName, isRooted);
            }
        });
        this.nativeComponent.writeSessionOs(sessionId, osRelease, osCodeName, isRooted);
    }

    private void writeSessionDevice(String sessionId) throws Exception {
        Context context2 = getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int arch = CommonUtils.getCpuArchitectureInt();
        String model = Build.MODEL;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long totalRam = CommonUtils.getTotalRamInBytes();
        long diskSpace = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        boolean isEmulator = CommonUtils.isEmulator(context2);
        int state = CommonUtils.getDeviceState(context2);
        String manufacturer = Build.MANUFACTURER;
        String modelClass = Build.PRODUCT;
        final int i = arch;
        final String str = model;
        final int i2 = availableProcessors;
        final long j = totalRam;
        final long j2 = diskSpace;
        final boolean z = isEmulator;
        final int i3 = state;
        final String str2 = manufacturer;
        Context context3 = context2;
        C074620 r14 = r0;
        final String str3 = modelClass;
        C074620 r0 = new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionDevice(arg, i, str, i2, j, j2, z, i3, str2, str3);
            }
        };
        writeSessionPartFile(sessionId, SESSION_DEVICE_TAG, r14);
        this.nativeComponent.writeSessionDevice(sessionId, i, str, i2, j, j2, z, i3, str2, modelClass);
    }

    private void writeSessionUser(String sessionId) throws Exception {
        final UserMetadata metadata = getUserMetadata(sessionId);
        writeSessionPartFile(sessionId, SESSION_USER_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionUser(arg, metadata.getUserId(), null, null);
            }
        });
    }

    private void writeSessionEvent(CodedOutputStream cos, Thread thread, Throwable ex, long eventTime, String eventType, boolean includeAllThreads) throws Exception {
        Thread[] threads;
        Map<String, String> attributes;
        TrimmedThrowableData trimmedEx = new TrimmedThrowableData(ex, this.stackTraceTrimmingStrategy);
        Context context2 = getContext();
        BatteryState battery = BatteryState.get(context2);
        Float batteryLevel = battery.getBatteryLevel();
        int batteryVelocity = battery.getBatteryVelocity();
        boolean proximityEnabled = CommonUtils.getProximitySensorEnabled(context2);
        int orientation = context2.getResources().getConfiguration().orientation;
        long usedRamBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context2);
        long diskUsedBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo runningAppProcessInfo = CommonUtils.getAppProcessInfo(context2.getPackageName(), context2);
        LinkedList linkedList = new LinkedList();
        StackTraceElement[] exceptionStack = trimmedEx.stacktrace;
        String buildId = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        if (includeAllThreads) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            Thread[] threads2 = new Thread[allStackTraces.size()];
            int i = 0;
            for (Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                threads2[i] = (Thread) entry.getKey();
                linkedList.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) entry.getValue()));
                i++;
            }
            threads = threads2;
        } else {
            threads = new Thread[0];
        }
        if (!CommonUtils.getBooleanResourceValue(context2, COLLECT_CUSTOM_KEYS, true)) {
            attributes = new TreeMap<>();
        } else {
            Map<String, String> attributes2 = this.userMetadata.getCustomKeys();
            if (attributes2 == null || attributes2.size() <= 1) {
                attributes = attributes2;
            } else {
                attributes = new TreeMap<>(attributes2);
            }
        }
        StackTraceElement[] stackTraceElementArr = exceptionStack;
        LinkedList linkedList2 = linkedList;
        int orientation2 = orientation;
        SessionProtobufHelper.writeSessionEvent(cos, eventTime, eventType, trimmedEx, thread, exceptionStack, threads, linkedList, 8, attributes, this.logFileManager.getBytesForLog(), runningAppProcessInfo, orientation2, appIdentifier, buildId, batteryLevel, batteryVelocity, proximityEnabled, usedRamBytes, diskUsedBytes);
        this.logFileManager.clearLog();
    }

    private void writeSessionPartsToSessionFile(File sessionBeginFile, String sessionId, int maxLoggedExceptionsCount) {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Collecting session parts for ID ");
        sb.append(sessionId);
        logger.mo19679d(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sessionId);
        sb2.append(SESSION_FATAL_TAG);
        File[] fatalFiles = listFilesMatching(new FileNameContainsFilter(sb2.toString()));
        boolean hasFatal = fatalFiles != null && fatalFiles.length > 0;
        Logger.getLogger().mo19679d(String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{sessionId, Boolean.valueOf(hasFatal)}));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sessionId);
        sb3.append(SESSION_NON_FATAL_TAG);
        File[] nonFatalFiles = listFilesMatching(new FileNameContainsFilter(sb3.toString()));
        boolean hasNonFatal = nonFatalFiles != null && nonFatalFiles.length > 0;
        Logger.getLogger().mo19679d(String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{sessionId, Boolean.valueOf(hasNonFatal)}));
        if (hasFatal || hasNonFatal) {
            synthesizeSessionFile(sessionBeginFile, sessionId, getTrimmedNonFatalFiles(sessionId, nonFatalFiles, maxLoggedExceptionsCount), hasFatal ? fatalFiles[0] : null);
        } else {
            Logger logger2 = Logger.getLogger();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("No events present for session ID ");
            sb4.append(sessionId);
            logger2.mo19679d(sb4.toString());
        }
        Logger logger3 = Logger.getLogger();
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Removing session part files for ID ");
        sb5.append(sessionId);
        logger3.mo19679d(sb5.toString());
        deleteSessionPartFilesFor(sessionId);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006c, code lost:
        if (0 != 0) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006e, code lost:
        closeWithoutRenamingOrLog(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0072, code lost:
        com.google.firebase.crashlytics.internal.common.CommonUtils.closeOrLog(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0095, code lost:
        if (1 == 0) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void synthesizeSessionFile(java.io.File r12, java.lang.String r13, java.io.File[] r14, java.io.File r15) {
        /*
            r11 = this;
            java.lang.String r0 = "Failed to close CLS file"
            java.lang.String r1 = "Error flushing session file stream"
            r2 = 1
            if (r15 == 0) goto L_0x0009
            r3 = 1
            goto L_0x000a
        L_0x0009:
            r3 = 0
        L_0x000a:
            r4 = 0
            if (r3 == 0) goto L_0x0012
            java.io.File r5 = r11.getFatalSessionFilesDir()
            goto L_0x0016
        L_0x0012:
            java.io.File r5 = r11.getNonFatalSessionFilesDir()
        L_0x0016:
            boolean r6 = r5.exists()
            if (r6 != 0) goto L_0x001f
            r5.mkdirs()
        L_0x001f:
            r6 = 0
            r7 = 0
            com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream r8 = new com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream     // Catch:{ Exception -> 0x0078 }
            r8.<init>(r5, r13)     // Catch:{ Exception -> 0x0078 }
            r6 = r8
            com.google.firebase.crashlytics.internal.proto.CodedOutputStream r8 = com.google.firebase.crashlytics.internal.proto.CodedOutputStream.newInstance(r6)     // Catch:{ Exception -> 0x0078 }
            r7 = r8
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ Exception -> 0x0078 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0078 }
            r9.<init>()     // Catch:{ Exception -> 0x0078 }
            java.lang.String r10 = "Collecting SessionStart data for session ID "
            r9.append(r10)     // Catch:{ Exception -> 0x0078 }
            r9.append(r13)     // Catch:{ Exception -> 0x0078 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0078 }
            r8.mo19679d(r9)     // Catch:{ Exception -> 0x0078 }
            writeToCosFromFile(r7, r12)     // Catch:{ Exception -> 0x0078 }
            r8 = 4
            long r9 = getCurrentTimestampSeconds()     // Catch:{ Exception -> 0x0078 }
            r7.writeUInt64(r8, r9)     // Catch:{ Exception -> 0x0078 }
            r8 = 5
            r7.writeBool(r8, r3)     // Catch:{ Exception -> 0x0078 }
            r8 = 11
            r7.writeUInt32(r8, r2)     // Catch:{ Exception -> 0x0078 }
            r2 = 12
            r8 = 3
            r7.writeEnum(r2, r8)     // Catch:{ Exception -> 0x0078 }
            r11.writeInitialPartsTo(r7, r13)     // Catch:{ Exception -> 0x0078 }
            writeNonFatalEventsTo(r7, r14, r13)     // Catch:{ Exception -> 0x0078 }
            if (r3 == 0) goto L_0x0069
            writeToCosFromFile(r7, r15)     // Catch:{ Exception -> 0x0078 }
        L_0x0069:
            com.google.firebase.crashlytics.internal.common.CommonUtils.flushOrLog(r7, r1)
            if (r4 == 0) goto L_0x0072
        L_0x006e:
            r11.closeWithoutRenamingOrLog(r6)
            goto L_0x0098
        L_0x0072:
            com.google.firebase.crashlytics.internal.common.CommonUtils.closeOrLog(r6, r0)
            goto L_0x0098
        L_0x0076:
            r2 = move-exception
            goto L_0x0099
        L_0x0078:
            r2 = move-exception
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0076 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            r9.<init>()     // Catch:{ all -> 0x0076 }
            java.lang.String r10 = "Failed to write session file for session ID: "
            r9.append(r10)     // Catch:{ all -> 0x0076 }
            r9.append(r13)     // Catch:{ all -> 0x0076 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0076 }
            r8.mo19682e(r9, r2)     // Catch:{ all -> 0x0076 }
            r4 = 1
            com.google.firebase.crashlytics.internal.common.CommonUtils.flushOrLog(r7, r1)
            if (r4 == 0) goto L_0x0072
            goto L_0x006e
        L_0x0098:
            return
        L_0x0099:
            com.google.firebase.crashlytics.internal.common.CommonUtils.flushOrLog(r7, r1)
            if (r4 == 0) goto L_0x00a2
            r11.closeWithoutRenamingOrLog(r6)
            goto L_0x00a5
        L_0x00a2:
            com.google.firebase.crashlytics.internal.common.CommonUtils.closeOrLog(r6, r0)
        L_0x00a5:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CrashlyticsController.synthesizeSessionFile(java.io.File, java.lang.String, java.io.File[], java.io.File):void");
    }

    private static void writeNonFatalEventsTo(CodedOutputStream cos, File[] nonFatalFiles, String sessionId) {
        Arrays.sort(nonFatalFiles, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File nonFatalFile : nonFatalFiles) {
            try {
                Logger.getLogger().mo19679d(String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{sessionId, nonFatalFile.getName()}));
                writeToCosFromFile(cos, nonFatalFile);
            } catch (Exception e) {
                Logger.getLogger().mo19682e("Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream cos, String sessionId) throws IOException {
        String[] strArr;
        for (String tag : INITIAL_SESSION_PART_TAGS) {
            StringBuilder sb = new StringBuilder();
            sb.append(sessionId);
            sb.append(tag);
            sb.append(ClsFileOutputStream.SESSION_FILE_EXTENSION);
            File[] sessionPartFiles = listFilesMatching(new FileNameContainsFilter(sb.toString()));
            String str = " data for session ID ";
            if (sessionPartFiles.length == 0) {
                Logger logger = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Can't find ");
                sb2.append(tag);
                sb2.append(str);
                sb2.append(sessionId);
                logger.mo19679d(sb2.toString());
            } else {
                Logger logger2 = Logger.getLogger();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Collecting ");
                sb3.append(tag);
                sb3.append(str);
                sb3.append(sessionId);
                logger2.mo19679d(sb3.toString());
                writeToCosFromFile(cos, sessionPartFiles[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void appendOrganizationIdToSessionFile(final String organizationId, File file) throws Exception {
        if (organizationId != null) {
            appendToProtoFile(file, new CodedOutputStreamWriteAction() {
                public void writeTo(CodedOutputStream cos) throws Exception {
                    SessionProtobufHelper.writeSessionAppClsId(cos, organizationId);
                }
            });
        }
    }

    private static void writeToCosFromFile(CodedOutputStream cos, File file) throws IOException {
        String str = "Failed to close file input stream.";
        if (!file.exists()) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Tried to include a file that doesn't exist: ");
            sb.append(file.getName());
            logger.mo19681e(sb.toString());
            return;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            copyToCodedOutputStream(fis, cos, (int) file.length());
        } finally {
            CommonUtils.closeOrLog(fis, str);
        }
    }

    private static void copyToCodedOutputStream(InputStream inStream, CodedOutputStream cos, int bufferLength) throws IOException {
        byte[] buffer = new byte[bufferLength];
        int offset = 0;
        while (offset < buffer.length) {
            int read = inStream.read(buffer, offset, buffer.length - offset);
            int numRead = read;
            if (read < 0) {
                break;
            }
            offset += numRead;
        }
        cos.writeRawBytes(buffer);
    }

    /* access modifiers changed from: 0000 */
    public UserMetadata getUserMetadata() {
        return this.userMetadata;
    }

    private UserMetadata getUserMetadata(String sessionId) {
        if (isHandlingException()) {
            return this.userMetadata;
        }
        return new MetaDataStore(getFilesDir()).readUserData(sessionId);
    }

    /* access modifiers changed from: 0000 */
    public boolean isHandlingException() {
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = this.crashHandler;
        return crashlyticsUncaughtExceptionHandler != null && crashlyticsUncaughtExceptionHandler.isHandlingException();
    }

    /* access modifiers changed from: 0000 */
    public File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    /* access modifiers changed from: 0000 */
    public File getNativeSessionFilesDir() {
        return new File(getFilesDir(), NATIVE_SESSION_DIR);
    }

    /* access modifiers changed from: 0000 */
    public File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    /* access modifiers changed from: 0000 */
    public File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    /* access modifiers changed from: 0000 */
    public void registerAnalyticsListener() {
        boolean analyticsRegistered = this.analyticsReceiver.register();
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("Registered Firebase Analytics event listener for breadcrumbs: ");
        sb.append(analyticsRegistered);
        logger.mo19679d(sb.toString());
    }

    /* access modifiers changed from: private */
    public CreateReportSpiCall getCreateReportSpiCall(String reportsUrl, String ndkReportsUrl) {
        String overriddenHost = CommonUtils.getStringsFileValue(getContext(), CRASHLYTICS_API_ENDPOINT);
        return new CompositeCreateReportSpiCall(new DefaultCreateReportSpiCall(overriddenHost, reportsUrl, this.httpRequestFactory, CrashlyticsCore.getVersion()), new NativeCreateReportSpiCall(overriddenHost, ndkReportsUrl, this.httpRequestFactory, CrashlyticsCore.getVersion()));
    }

    /* access modifiers changed from: private */
    public void sendSessionReports(AppSettingsData appSettings, boolean dataCollectionToken) throws Exception {
        File[] listCompleteSessionFiles;
        Context context2 = getContext();
        ReportUploader reportUploader = this.reportUploaderProvider.createReportUploader(appSettings);
        for (File finishedSessionFile : listCompleteSessionFiles()) {
            appendOrganizationIdToSessionFile(appSettings.organizationId, finishedSessionFile);
            this.backgroundWorker.submit((Runnable) new SendReportRunnable(context2, new SessionReport(finishedSessionFile, SEND_AT_CRASHTIME_HEADER), reportUploader, dataCollectionToken));
        }
    }

    private Task<Void> recordFatalFirebaseEvent(final long timestamp) {
        return Tasks.call(new ScheduledThreadPoolExecutor(1), new Callable<Void>() {
            public Void call() throws Exception {
                if (CrashlyticsController.this.firebaseCrashExists()) {
                    Logger.getLogger().mo19679d("Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
                    return null;
                } else if (CrashlyticsController.this.analyticsConnector == null) {
                    Logger.getLogger().mo19679d("Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
                    return null;
                } else {
                    BlockingCrashEventListener blockingListener = new BlockingCrashEventListener();
                    CrashlyticsController.this.analyticsReceiver.setCrashlyticsOriginEventListener(blockingListener);
                    Logger.getLogger().mo19679d("Logging Crashlytics event to Firebase");
                    Bundle params = new Bundle();
                    params.putInt("fatal", 1);
                    params.putLong("timestamp", timestamp);
                    CrashlyticsController.this.analyticsConnector.logEvent(CrashlyticsController.FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS, "_ae", params);
                    blockingListener.awaitEvent();
                    CrashlyticsController.this.analyticsReceiver.setCrashlyticsOriginEventListener(null);
                    return null;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean firebaseCrashExists() {
        try {
            Class cls = Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    static List<NativeSessionFile> getNativeSessionFiles(NativeSessionFileProvider fileProvider, String previousSessionId, Context context2, File filesDir, byte[] logBytes) {
        MetaDataStore metaDataStore = new MetaDataStore(filesDir);
        File userFile = metaDataStore.getUserDataFileForSession(previousSessionId);
        File keysFile = metaDataStore.getKeysFileForSession(previousSessionId);
        byte[] binaryImageBytes = null;
        try {
            binaryImageBytes = NativeFileUtils.binaryImagesJsonFromMapsFile(fileProvider.getBinaryImagesFile(), context2);
        } catch (Exception e) {
        }
        List<NativeSessionFile> nativeSessionFiles = new ArrayList<>();
        nativeSessionFiles.add(new BytesBackedNativeSessionFile("logs_file", "logs", logBytes));
        nativeSessionFiles.add(new BytesBackedNativeSessionFile("binary_images_file", "binaryImages", binaryImageBytes));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("crash_meta_file", "metadata", fileProvider.getMetadataFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("session_meta_file", "session", fileProvider.getSessionFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("app_meta_file", "app", fileProvider.getAppFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("device_meta_file", "device", fileProvider.getDeviceFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("os_meta_file", "os", fileProvider.getOsFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("minidump_file", "minidump", fileProvider.getMinidumpFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("user_meta_file", "user", userFile));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("keys_file", "keys", keysFile));
        return nativeSessionFiles;
    }
}
