package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport extends CrashlyticsReport {
    private final String buildVersion;
    private final String displayVersion;
    private final String gmpAppId;
    private final String installationUuid;
    private final FilesPayload ndkPayload;
    private final int platform;
    private final String sdkVersion;
    private final Session session;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder {
        private String buildVersion;
        private String displayVersion;
        private String gmpAppId;
        private String installationUuid;
        private FilesPayload ndkPayload;
        private Integer platform;
        private String sdkVersion;
        private Session session;

        Builder() {
        }

        private Builder(CrashlyticsReport source) {
            this.sdkVersion = source.getSdkVersion();
            this.gmpAppId = source.getGmpAppId();
            this.platform = Integer.valueOf(source.getPlatform());
            this.installationUuid = source.getInstallationUuid();
            this.buildVersion = source.getBuildVersion();
            this.displayVersion = source.getDisplayVersion();
            this.session = source.getSession();
            this.ndkPayload = source.getNdkPayload();
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setSdkVersion(String sdkVersion2) {
            if (sdkVersion2 != null) {
                this.sdkVersion = sdkVersion2;
                return this;
            }
            throw new NullPointerException("Null sdkVersion");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setGmpAppId(String gmpAppId2) {
            if (gmpAppId2 != null) {
                this.gmpAppId = gmpAppId2;
                return this;
            }
            throw new NullPointerException("Null gmpAppId");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setPlatform(int platform2) {
            this.platform = Integer.valueOf(platform2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setInstallationUuid(String installationUuid2) {
            if (installationUuid2 != null) {
                this.installationUuid = installationUuid2;
                return this;
            }
            throw new NullPointerException("Null installationUuid");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setBuildVersion(String buildVersion2) {
            if (buildVersion2 != null) {
                this.buildVersion = buildVersion2;
                return this;
            }
            throw new NullPointerException("Null buildVersion");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setDisplayVersion(String displayVersion2) {
            if (displayVersion2 != null) {
                this.displayVersion = displayVersion2;
                return this;
            }
            throw new NullPointerException("Null displayVersion");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setSession(Session session2) {
            this.session = session2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder setNdkPayload(FilesPayload ndkPayload2) {
            this.ndkPayload = ndkPayload2;
            return this;
        }

        public CrashlyticsReport build() {
            String missing = "";
            if (this.sdkVersion == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" sdkVersion");
                missing = sb.toString();
            }
            if (this.gmpAppId == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" gmpAppId");
                missing = sb2.toString();
            }
            if (this.platform == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" platform");
                missing = sb3.toString();
            }
            if (this.installationUuid == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" installationUuid");
                missing = sb4.toString();
            }
            if (this.buildVersion == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(missing);
                sb5.append(" buildVersion");
                missing = sb5.toString();
            }
            if (this.displayVersion == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(missing);
                sb6.append(" displayVersion");
                missing = sb6.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport autoValue_CrashlyticsReport = new AutoValue_CrashlyticsReport(this.sdkVersion, this.gmpAppId, this.platform.intValue(), this.installationUuid, this.buildVersion, this.displayVersion, this.session, this.ndkPayload);
                return autoValue_CrashlyticsReport;
            }
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Missing required properties:");
            sb7.append(missing);
            throw new IllegalStateException(sb7.toString());
        }
    }

    private AutoValue_CrashlyticsReport(String sdkVersion2, String gmpAppId2, int platform2, String installationUuid2, String buildVersion2, String displayVersion2, Session session2, FilesPayload ndkPayload2) {
        this.sdkVersion = sdkVersion2;
        this.gmpAppId = gmpAppId2;
        this.platform = platform2;
        this.installationUuid = installationUuid2;
        this.buildVersion = buildVersion2;
        this.displayVersion = displayVersion2;
        this.session = session2;
        this.ndkPayload = ndkPayload2;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getGmpAppId() {
        return this.gmpAppId;
    }

    public int getPlatform() {
        return this.platform;
    }

    public String getInstallationUuid() {
        return this.installationUuid;
    }

    public String getBuildVersion() {
        return this.buildVersion;
    }

    public String getDisplayVersion() {
        return this.displayVersion;
    }

    public Session getSession() {
        return this.session;
    }

    public FilesPayload getNdkPayload() {
        return this.ndkPayload;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CrashlyticsReport{sdkVersion=");
        sb.append(this.sdkVersion);
        sb.append(", gmpAppId=");
        sb.append(this.gmpAppId);
        sb.append(", platform=");
        sb.append(this.platform);
        sb.append(", installationUuid=");
        sb.append(this.installationUuid);
        sb.append(", buildVersion=");
        sb.append(this.buildVersion);
        sb.append(", displayVersion=");
        sb.append(this.displayVersion);
        sb.append(", session=");
        sb.append(this.session);
        sb.append(", ndkPayload=");
        sb.append(this.ndkPayload);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReport)) {
            return false;
        }
        CrashlyticsReport that = (CrashlyticsReport) o;
        if (this.sdkVersion.equals(that.getSdkVersion()) && this.gmpAppId.equals(that.getGmpAppId()) && this.platform == that.getPlatform() && this.installationUuid.equals(that.getInstallationUuid()) && this.buildVersion.equals(that.getBuildVersion()) && this.displayVersion.equals(that.getDisplayVersion())) {
            Session session2 = this.session;
            if (session2 != null ? session2.equals(that.getSession()) : that.getSession() == null) {
                FilesPayload filesPayload = this.ndkPayload;
                if (filesPayload != null) {
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = ((((((((((((1 * 1000003) ^ this.sdkVersion.hashCode()) * 1000003) ^ this.gmpAppId.hashCode()) * 1000003) ^ this.platform) * 1000003) ^ this.installationUuid.hashCode()) * 1000003) ^ this.buildVersion.hashCode()) * 1000003) ^ this.displayVersion.hashCode()) * 1000003;
        Session session2 = this.session;
        int i = 0;
        int h$2 = (h$ ^ (session2 == null ? 0 : session2.hashCode())) * 1000003;
        FilesPayload filesPayload = this.ndkPayload;
        if (filesPayload != null) {
            i = filesPayload.hashCode();
        }
        return h$2 ^ i;
    }

    /* access modifiers changed from: protected */
    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder toBuilder() {
        return new Builder(this);
    }
}
