package com.google.firebase.crashlytics.internal.settings.model;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class AppSettingsData {
    public static final String STATUS_ACTIVATED = "activated";
    public static final String STATUS_CONFIGURED = "configured";
    public static final String STATUS_NEW = "new";
    public final String bundleId;
    public final int nativeReportUploadVariant;
    public final String ndkReportsUrl;
    public final String organizationId;
    public final int reportUploadVariant;
    public final String reportsUrl;
    public final String status;
    public final boolean updateRequired;
    public final String url;

    public AppSettingsData(String status2, String url2, String reportsUrl2, String ndkReportsUrl2, String bundleId2, String organizationId2, boolean updateRequired2, int reportUploadVariant2, int nativeReportUploadVariant2) {
        this.status = status2;
        this.url = url2;
        this.reportsUrl = reportsUrl2;
        this.ndkReportsUrl = ndkReportsUrl2;
        this.bundleId = bundleId2;
        this.organizationId = organizationId2;
        this.updateRequired = updateRequired2;
        this.reportUploadVariant = reportUploadVariant2;
        this.nativeReportUploadVariant = nativeReportUploadVariant2;
    }

    public AppSettingsData(String status2, String url2, String reportsUrl2, String ndkReportsUrl2, boolean updateRequired2) {
        this(status2, url2, reportsUrl2, ndkReportsUrl2, null, null, updateRequired2, 0, 0);
    }
}
