package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File;
import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_FilesPayload_File extends File {
    private final byte[] contents;
    private final String filename;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder {
        private byte[] contents;
        private String filename;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder setFilename(String filename2) {
            if (filename2 != null) {
                this.filename = filename2;
                return this;
            }
            throw new NullPointerException("Null filename");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder setContents(byte[] contents2) {
            if (contents2 != null) {
                this.contents = contents2;
                return this;
            }
            throw new NullPointerException("Null contents");
        }

        public File build() {
            String missing = "";
            if (this.filename == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" filename");
                missing = sb.toString();
            }
            if (this.contents == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" contents");
                missing = sb2.toString();
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_FilesPayload_File(this.filename, this.contents);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Missing required properties:");
            sb3.append(missing);
            throw new IllegalStateException(sb3.toString());
        }
    }

    private AutoValue_CrashlyticsReport_FilesPayload_File(String filename2, byte[] contents2) {
        this.filename = filename2;
        this.contents = contents2;
    }

    public String getFilename() {
        return this.filename;
    }

    public byte[] getContents() {
        return this.contents;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("File{filename=");
        sb.append(this.filename);
        sb.append(", contents=");
        sb.append(Arrays.toString(this.contents));
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (java.util.Arrays.equals(r5.contents, r1 instanceof com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File ? ((com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File) r1).contents : r1.getContents()) != false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File
            r2 = 0
            if (r1 == 0) goto L_0x0031
            r1 = r6
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$FilesPayload$File r1 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File) r1
            java.lang.String r3 = r5.filename
            java.lang.String r4 = r1.getFilename()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x002f
            byte[] r3 = r5.contents
            boolean r4 = r1 instanceof com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File
            if (r4 == 0) goto L_0x0024
            r4 = r1
            com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File r4 = (com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File) r4
            byte[] r4 = r4.contents
            goto L_0x0028
        L_0x0024:
            byte[] r4 = r1.getContents()
        L_0x0028:
            boolean r3 = java.util.Arrays.equals(r3, r4)
            if (r3 == 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r0 = 0
        L_0x0030:
            return r0
        L_0x0031:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.filename.hashCode()) * 1000003) ^ Arrays.hashCode(this.contents);
    }
}
