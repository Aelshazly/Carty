package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReportWithSessionId extends CrashlyticsReportWithSessionId {
    private final CrashlyticsReport report;
    private final String sessionId;

    AutoValue_CrashlyticsReportWithSessionId(CrashlyticsReport report2, String sessionId2) {
        if (report2 != null) {
            this.report = report2;
            if (sessionId2 != null) {
                this.sessionId = sessionId2;
                return;
            }
            throw new NullPointerException("Null sessionId");
        }
        throw new NullPointerException("Null report");
    }

    public CrashlyticsReport getReport() {
        return this.report;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CrashlyticsReportWithSessionId{report=");
        sb.append(this.report);
        sb.append(", sessionId=");
        sb.append(this.sessionId);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReportWithSessionId)) {
            return false;
        }
        CrashlyticsReportWithSessionId that = (CrashlyticsReportWithSessionId) o;
        if (!this.report.equals(that.getReport()) || !this.sessionId.equals(that.getSessionId())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.report.hashCode()) * 1000003) ^ this.sessionId.hashCode();
    }
}
