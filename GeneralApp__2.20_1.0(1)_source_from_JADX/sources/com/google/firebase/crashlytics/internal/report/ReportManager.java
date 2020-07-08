package com.google.firebase.crashlytics.internal.report;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.report.ReportUploader.ReportFilesProvider;
import com.google.firebase.crashlytics.internal.report.model.NativeSessionReport;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.model.SessionReport;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class ReportManager {
    private final ReportFilesProvider reportFilesProvider;

    public ReportManager(ReportFilesProvider reportFilesProvider2) {
        this.reportFilesProvider = reportFilesProvider2;
    }

    public boolean areReportsAvailable() {
        File[] clsFiles = this.reportFilesProvider.getCompleteSessionFiles();
        File[] nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        if (clsFiles != null && clsFiles.length > 0) {
            return true;
        }
        if (nativeReportFiles == null || nativeReportFiles.length <= 0) {
            return false;
        }
        return true;
    }

    public List<Report> findReports() {
        Logger.getLogger().mo19679d("Checking for crash reports...");
        File[] clsFiles = this.reportFilesProvider.getCompleteSessionFiles();
        File[] nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        List<Report> reports = new LinkedList<>();
        if (clsFiles != null) {
            for (File file : clsFiles) {
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Found crash report ");
                sb.append(file.getPath());
                logger.mo19679d(sb.toString());
                reports.add(new SessionReport(file));
            }
        }
        if (nativeReportFiles != null) {
            for (File dir : nativeReportFiles) {
                reports.add(new NativeSessionReport(dir));
            }
        }
        if (reports.isEmpty()) {
            Logger.getLogger().mo19679d("No reports found.");
        }
        return reports;
    }

    public void deleteReport(Report report) {
        report.remove();
    }

    public void deleteReports(List<Report> reports) {
        for (Report report : reports) {
            deleteReport(report);
        }
    }
}
