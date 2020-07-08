package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Application extends Application {
    private final String displayVersion;
    private final String identifier;
    private final String installationUuid;
    private final Organization organization;
    private final String version;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder {
        private String displayVersion;
        private String identifier;
        private String installationUuid;
        private Organization organization;
        private String version;

        Builder() {
        }

        private Builder(Application source) {
            this.identifier = source.getIdentifier();
            this.version = source.getVersion();
            this.displayVersion = source.getDisplayVersion();
            this.organization = source.getOrganization();
            this.installationUuid = source.getInstallationUuid();
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder setIdentifier(String identifier2) {
            if (identifier2 != null) {
                this.identifier = identifier2;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder setVersion(String version2) {
            if (version2 != null) {
                this.version = version2;
                return this;
            }
            throw new NullPointerException("Null version");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder setDisplayVersion(String displayVersion2) {
            this.displayVersion = displayVersion2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder setOrganization(Organization organization2) {
            this.organization = organization2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder setInstallationUuid(String installationUuid2) {
            this.installationUuid = installationUuid2;
            return this;
        }

        public Application build() {
            String missing = "";
            if (this.identifier == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" identifier");
                missing = sb.toString();
            }
            if (this.version == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" version");
                missing = sb2.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_Application autoValue_CrashlyticsReport_Session_Application = new AutoValue_CrashlyticsReport_Session_Application(this.identifier, this.version, this.displayVersion, this.organization, this.installationUuid);
                return autoValue_CrashlyticsReport_Session_Application;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Missing required properties:");
            sb3.append(missing);
            throw new IllegalStateException(sb3.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Application(String identifier2, String version2, String displayVersion2, Organization organization2, String installationUuid2) {
        this.identifier = identifier2;
        this.version = version2;
        this.displayVersion = displayVersion2;
        this.organization = organization2;
        this.installationUuid = installationUuid2;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDisplayVersion() {
        return this.displayVersion;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public String getInstallationUuid() {
        return this.installationUuid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Application{identifier=");
        sb.append(this.identifier);
        sb.append(", version=");
        sb.append(this.version);
        sb.append(", displayVersion=");
        sb.append(this.displayVersion);
        sb.append(", organization=");
        sb.append(this.organization);
        sb.append(", installationUuid=");
        sb.append(this.installationUuid);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }
        Application that = (Application) o;
        if (this.identifier.equals(that.getIdentifier()) && this.version.equals(that.getVersion())) {
            String str = this.displayVersion;
            if (str != null ? str.equals(that.getDisplayVersion()) : that.getDisplayVersion() == null) {
                Organization organization2 = this.organization;
                if (organization2 != null ? organization2.equals(that.getOrganization()) : that.getOrganization() == null) {
                    String str2 = this.installationUuid;
                    if (str2 != null) {
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = ((((1 * 1000003) ^ this.identifier.hashCode()) * 1000003) ^ this.version.hashCode()) * 1000003;
        String str = this.displayVersion;
        int i = 0;
        int h$2 = (h$ ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Organization organization2 = this.organization;
        int h$3 = (h$2 ^ (organization2 == null ? 0 : organization2.hashCode())) * 1000003;
        String str2 = this.installationUuid;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return h$3 ^ i;
    }

    /* access modifiers changed from: protected */
    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder toBuilder() {
        return new Builder(this);
    }
}
