package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class BytesBackedNativeSessionFile implements NativeSessionFile {
    private final byte[] bytes;
    private final String dataTransportFilename;
    private final String reportsEndpointFilename;

    BytesBackedNativeSessionFile(String dataTransportFilename2, String reportsEndpointFilename2, byte[] bytes2) {
        this.dataTransportFilename = dataTransportFilename2;
        this.reportsEndpointFilename = reportsEndpointFilename2;
        this.bytes = bytes2;
    }

    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    public InputStream getStream() {
        if (isEmpty()) {
            return null;
        }
        return new ByteArrayInputStream(this.bytes);
    }

    public File asFilePayload() {
        byte[] gzippedBytes = asGzippedBytes();
        if (gzippedBytes == null) {
            return null;
        }
        return File.builder().setContents(gzippedBytes).setFilename(this.dataTransportFilename).build();
    }

    private boolean isEmpty() {
        byte[] bArr = this.bytes;
        return bArr == null || bArr.length == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        $closeResource(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        $closeResource(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0033, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] asGzippedBytes() {
        /*
            r5 = this;
            boolean r0 = r5.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0034 }
            r0.<init>()     // Catch:{ IOException -> 0x0034 }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x002d }
            r2.<init>(r0)     // Catch:{ all -> 0x002d }
            byte[] r3 = r5.bytes     // Catch:{ all -> 0x0026 }
            r2.write(r3)     // Catch:{ all -> 0x0026 }
            r2.finish()     // Catch:{ all -> 0x0026 }
            byte[] r3 = r0.toByteArray()     // Catch:{ all -> 0x0026 }
            $closeResource(r1, r2)     // Catch:{ all -> 0x002d }
            $closeResource(r1, r0)     // Catch:{ IOException -> 0x0034 }
            return r3
        L_0x0026:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r4 = move-exception
            $closeResource(r3, r2)     // Catch:{ all -> 0x002d }
            throw r4     // Catch:{ all -> 0x002d }
        L_0x002d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002f }
        L_0x002f:
            r3 = move-exception
            $closeResource(r2, r0)     // Catch:{ IOException -> 0x0034 }
            throw r3     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            r0 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.BytesBackedNativeSessionFile.asGzippedBytes():byte[]");
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
