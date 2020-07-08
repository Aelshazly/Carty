package com.google.firebase.crashlytics.internal.report.model;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CreateReportRequest {
    public final String googleAppId;
    public final String organizationId;
    public final Report report;

    public CreateReportRequest(String organizationId2, String googleAppId2, Report report2) {
        this.organizationId = organizationId2;
        this.googleAppId = googleAppId2;
        this.report = report2;
    }
}
