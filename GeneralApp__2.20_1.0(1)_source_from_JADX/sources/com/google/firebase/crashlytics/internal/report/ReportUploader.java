package com.google.firebase.crashlytics.internal.report;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable;
import com.google.firebase.crashlytics.internal.common.DataTransportState;
import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.model.Report.Type;
import com.google.firebase.crashlytics.internal.report.network.CreateReportSpiCall;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class ReportUploader {
    /* access modifiers changed from: private */
    public static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private final CreateReportSpiCall createReportCall;
    private final DataTransportState dataTransportState;
    private final String googleAppId;
    /* access modifiers changed from: private */
    public final HandlingExceptionCheck handlingExceptionCheck;
    private final String organizationId;
    private final ReportManager reportManager;
    /* access modifiers changed from: private */
    public Thread uploadThread;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public interface Provider {
        ReportUploader createReportUploader(AppSettingsData appSettingsData);
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getNativeReportFiles();
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private class Worker extends BackgroundPriorityRunnable {
        private final boolean dataCollectionToken;
        private final float delay;
        private final List<Report> reports;

        Worker(List<Report> reports2, boolean dataCollectionToken2, float delay2) {
            this.reports = reports2;
            this.dataCollectionToken = dataCollectionToken2;
            this.delay = delay2;
        }

        public void onRun() {
            try {
                attemptUploadWithRetry(this.reports, this.dataCollectionToken);
            } catch (Exception e) {
                Logger.getLogger().mo19682e("An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry(List<Report> list, boolean dataCollectionToken2) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Starting report processing in ");
            sb.append(this.delay);
            sb.append(" second(s)...");
            logger.mo19679d(sb.toString());
            float f = this.delay;
            if (f > 0.0f) {
                try {
                    Thread.sleep((long) (f * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (!ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                int retryCount = 0;
                r9 = list;
                while (r9.size() > 0 && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                    Logger logger2 = Logger.getLogger();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Attempting to send ");
                    sb2.append(r9.size());
                    sb2.append(" report(s)");
                    logger2.mo19679d(sb2.toString());
                    ArrayList<Report> remaining = new ArrayList<>();
                    for (Report report : r9) {
                        if (!ReportUploader.this.uploadReport(report, dataCollectionToken2)) {
                            remaining.add(report);
                        }
                    }
                    r9 = remaining;
                    if (r9.size() > 0) {
                        int retryCount2 = retryCount + 1;
                        long interval = (long) ReportUploader.RETRY_INTERVALS[Math.min(retryCount, ReportUploader.RETRY_INTERVALS.length - 1)];
                        Logger logger3 = Logger.getLogger();
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Report submission: scheduling delayed retry in ");
                        sb3.append(interval);
                        sb3.append(" seconds");
                        logger3.mo19679d(sb3.toString());
                        try {
                            Thread.sleep(1000 * interval);
                            retryCount = retryCount2;
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    r9 = r9;
                }
            }
        }
    }

    public ReportUploader(String organizationId2, String googleAppId2, DataTransportState dataTransportState2, ReportManager reportManager2, CreateReportSpiCall createReportCall2, HandlingExceptionCheck handlingExceptionCheck2) {
        if (createReportCall2 != null) {
            this.createReportCall = createReportCall2;
            this.organizationId = organizationId2;
            this.googleAppId = googleAppId2;
            this.dataTransportState = dataTransportState2;
            this.reportManager = reportManager2;
            this.handlingExceptionCheck = handlingExceptionCheck2;
            return;
        }
        throw new IllegalArgumentException("createReportCall must not be null.");
    }

    public synchronized void uploadReportsAsync(List<Report> reports, boolean dataCollectionToken, float delay) {
        if (this.uploadThread != null) {
            Logger.getLogger().mo19679d("Report upload has already been started.");
            return;
        }
        this.uploadThread = new Thread(new Worker(reports, dataCollectionToken, delay), "Crashlytics Report Uploader");
        this.uploadThread.start();
    }

    /* access modifiers changed from: 0000 */
    public boolean isUploading() {
        return this.uploadThread != null;
    }

    public boolean uploadReport(Report report, boolean dataCollectionToken) {
        try {
            CreateReportRequest requestData = new CreateReportRequest(this.organizationId, this.googleAppId, report);
            boolean shouldDeleteReport = true;
            if (this.dataTransportState == DataTransportState.ALL) {
                Logger.getLogger().mo19679d("Send to Reports Endpoint disabled. Removing Reports Endpoint report.");
            } else if (this.dataTransportState == DataTransportState.JAVA_ONLY && report.getType() == Type.JAVA) {
                Logger.getLogger().mo19679d("Send to Reports Endpoint for non-native reports disabled. Removing Reports Uploader report.");
            } else {
                boolean sent = this.createReportCall.invoke(requestData, dataCollectionToken);
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Crashlytics Reports Endpoint upload ");
                sb.append(sent ? "complete: " : "FAILED: ");
                sb.append(report.getIdentifier());
                logger.mo19683i(sb.toString());
                shouldDeleteReport = sent;
            }
            if (!shouldDeleteReport) {
                return false;
            }
            this.reportManager.deleteReport(report);
            return true;
        } catch (Exception e) {
            Logger logger2 = Logger.getLogger();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Error occurred sending report ");
            sb2.append(report);
            logger2.mo19682e(sb2.toString(), e);
            return false;
        }
    }
}
