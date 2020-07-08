package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class FileBackedNativeSessionFile implements NativeSessionFile {
    private final String dataTransportFilename;
    private final File file;
    private final String reportsEndpointFilename;

    FileBackedNativeSessionFile(String dataTransportFilename2, String reportsEndpointFilename2, File file2) {
        this.dataTransportFilename = dataTransportFilename2;
        this.reportsEndpointFilename = reportsEndpointFilename2;
        this.file = file2;
    }

    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    public InputStream getStream() {
        if (!this.file.exists() || !this.file.isFile()) {
            return null;
        }
        try {
            return new FileInputStream(this.file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public FilesPayload.File asFilePayload() {
        byte[] bytes = asGzippedBytes();
        if (bytes != null) {
            return FilesPayload.File.builder().setContents(bytes).setFilename(this.dataTransportFilename).build();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0030, code lost:
        r4.finish();
        r5 = r3.toByteArray();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        $closeResource(null, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        $closeResource(null, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        if (r2 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        $closeResource(null, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0042, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0045, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        $closeResource(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0049, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        $closeResource(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0050, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0053, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0054, code lost:
        if (r2 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        $closeResource(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0059, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] asGzippedBytes() {
        /*
            r7 = this;
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.InputStream r2 = r7.getStream()     // Catch:{ IOException -> 0x005a }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0051 }
            r3.<init>()     // Catch:{ all -> 0x0051 }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x004a }
            r4.<init>(r3)     // Catch:{ all -> 0x004a }
            if (r2 != 0) goto L_0x0024
            $closeResource(r1, r4)     // Catch:{ all -> 0x004a }
            $closeResource(r1, r3)     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0023
            $closeResource(r1, r2)     // Catch:{ IOException -> 0x005a }
        L_0x0023:
            return r1
        L_0x0024:
            int r5 = r2.read(r0)     // Catch:{ all -> 0x0043 }
            r6 = r5
            if (r5 <= 0) goto L_0x0030
            r5 = 0
            r4.write(r0, r5, r6)     // Catch:{ all -> 0x0043 }
            goto L_0x0024
        L_0x0030:
            r4.finish()     // Catch:{ all -> 0x0043 }
            byte[] r5 = r3.toByteArray()     // Catch:{ all -> 0x0043 }
            $closeResource(r1, r4)     // Catch:{ all -> 0x004a }
            $closeResource(r1, r3)     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0042
            $closeResource(r1, r2)     // Catch:{ IOException -> 0x005a }
        L_0x0042:
            return r5
        L_0x0043:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r6 = move-exception
            $closeResource(r5, r4)     // Catch:{ all -> 0x004a }
            throw r6     // Catch:{ all -> 0x004a }
        L_0x004a:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004c }
        L_0x004c:
            r5 = move-exception
            $closeResource(r4, r3)     // Catch:{ all -> 0x0051 }
            throw r5     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r4 = move-exception
            if (r2 == 0) goto L_0x0059
            $closeResource(r3, r2)     // Catch:{ IOException -> 0x005a }
        L_0x0059:
            throw r4     // Catch:{ IOException -> 0x005a }
        L_0x005a:
            r2 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.FileBackedNativeSessionFile.asGzippedBytes():byte[]");
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
            }
        } else {
            x1.close();
        }
    }
}
