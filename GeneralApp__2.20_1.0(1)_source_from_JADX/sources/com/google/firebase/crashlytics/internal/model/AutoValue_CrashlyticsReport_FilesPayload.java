package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_FilesPayload extends FilesPayload {
    private final ImmutableList<File> files;
    private final String orgId;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder {
        private ImmutableList<File> files;
        private String orgId;

        Builder() {
        }

        private Builder(FilesPayload source) {
            this.files = source.getFiles();
            this.orgId = source.getOrgId();
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder setFiles(ImmutableList<File> files2) {
            if (files2 != null) {
                this.files = files2;
                return this;
            }
            throw new NullPointerException("Null files");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder setOrgId(String orgId2) {
            this.orgId = orgId2;
            return this;
        }

        public FilesPayload build() {
            String missing = "";
            if (this.files == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" files");
                missing = sb.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_FilesPayload(this.files, this.orgId);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(missing);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_CrashlyticsReport_FilesPayload(ImmutableList<File> files2, String orgId2) {
        this.files = files2;
        this.orgId = orgId2;
    }

    public ImmutableList<File> getFiles() {
        return this.files;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FilesPayload{files=");
        sb.append(this.files);
        sb.append(", orgId=");
        sb.append(this.orgId);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof FilesPayload)) {
            return false;
        }
        FilesPayload that = (FilesPayload) o;
        if (this.files.equals(that.getFiles())) {
            String str = this.orgId;
            if (str != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.files.hashCode()) * 1000003;
        String str = this.orgId;
        return h$ ^ (str == null ? 0 : str.hashCode());
    }

    /* access modifiers changed from: 0000 */
    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder toBuilder() {
        return new Builder(this);
    }
}
