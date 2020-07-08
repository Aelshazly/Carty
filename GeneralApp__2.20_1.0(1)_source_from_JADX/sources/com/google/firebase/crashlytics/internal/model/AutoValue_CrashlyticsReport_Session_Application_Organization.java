package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Application_Organization extends Organization {
    private final String clsId;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization.Builder {
        private String clsId;

        Builder() {
        }

        private Builder(Organization source) {
            this.clsId = source.getClsId();
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization.Builder setClsId(String clsId2) {
            if (clsId2 != null) {
                this.clsId = clsId2;
                return this;
            }
            throw new NullPointerException("Null clsId");
        }

        public Organization build() {
            String missing = "";
            if (this.clsId == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" clsId");
                missing = sb.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Application_Organization(this.clsId);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(missing);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Application_Organization(String clsId2) {
        this.clsId = clsId2;
    }

    public String getClsId() {
        return this.clsId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Organization{clsId=");
        sb.append(this.clsId);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return this.clsId.equals(((Organization) o).getClsId());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.clsId.hashCode();
    }

    /* access modifiers changed from: protected */
    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization.Builder toBuilder() {
        return new Builder(this);
    }
}
