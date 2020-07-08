package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_OperatingSystem extends OperatingSystem {
    private final String buildVersion;
    private final boolean jailbroken;
    private final int platform;
    private final String version;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder {
        private String buildVersion;
        private Boolean jailbroken;
        private Integer platform;
        private String version;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder setPlatform(int platform2) {
            this.platform = Integer.valueOf(platform2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder setVersion(String version2) {
            if (version2 != null) {
                this.version = version2;
                return this;
            }
            throw new NullPointerException("Null version");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder setBuildVersion(String buildVersion2) {
            if (buildVersion2 != null) {
                this.buildVersion = buildVersion2;
                return this;
            }
            throw new NullPointerException("Null buildVersion");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder setJailbroken(boolean jailbroken2) {
            this.jailbroken = Boolean.valueOf(jailbroken2);
            return this;
        }

        public OperatingSystem build() {
            String missing = "";
            if (this.platform == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" platform");
                missing = sb.toString();
            }
            if (this.version == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" version");
                missing = sb2.toString();
            }
            if (this.buildVersion == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" buildVersion");
                missing = sb3.toString();
            }
            if (this.jailbroken == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" jailbroken");
                missing = sb4.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_OperatingSystem autoValue_CrashlyticsReport_Session_OperatingSystem = new AutoValue_CrashlyticsReport_Session_OperatingSystem(this.platform.intValue(), this.version, this.buildVersion, this.jailbroken.booleanValue());
                return autoValue_CrashlyticsReport_Session_OperatingSystem;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(missing);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_OperatingSystem(int platform2, String version2, String buildVersion2, boolean jailbroken2) {
        this.platform = platform2;
        this.version = version2;
        this.buildVersion = buildVersion2;
        this.jailbroken = jailbroken2;
    }

    public int getPlatform() {
        return this.platform;
    }

    public String getVersion() {
        return this.version;
    }

    public String getBuildVersion() {
        return this.buildVersion;
    }

    public boolean isJailbroken() {
        return this.jailbroken;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OperatingSystem{platform=");
        sb.append(this.platform);
        sb.append(", version=");
        sb.append(this.version);
        sb.append(", buildVersion=");
        sb.append(this.buildVersion);
        sb.append(", jailbroken=");
        sb.append(this.jailbroken);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof OperatingSystem)) {
            return false;
        }
        OperatingSystem that = (OperatingSystem) o;
        if (this.platform != that.getPlatform() || !this.version.equals(that.getVersion()) || !this.buildVersion.equals(that.getBuildVersion()) || this.jailbroken != that.isJailbroken()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.platform) * 1000003) ^ this.version.hashCode()) * 1000003) ^ this.buildVersion.hashCode()) * 1000003) ^ (this.jailbroken ? 1231 : 1237);
    }
}
