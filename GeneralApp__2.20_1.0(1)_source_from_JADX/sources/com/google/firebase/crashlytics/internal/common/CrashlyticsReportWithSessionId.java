package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public abstract class CrashlyticsReportWithSessionId {
    public abstract CrashlyticsReport getReport();

    public abstract String getSessionId();

    public static CrashlyticsReportWithSessionId create(CrashlyticsReport report, String sessionId) {
        return new AutoValue_CrashlyticsReportWithSessionId(report, sessionId);
    }
}
