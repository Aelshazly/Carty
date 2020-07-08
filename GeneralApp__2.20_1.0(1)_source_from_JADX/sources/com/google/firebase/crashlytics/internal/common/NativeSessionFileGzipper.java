package com.google.firebase.crashlytics.internal.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class NativeSessionFileGzipper {
    NativeSessionFileGzipper() {
    }

    static void processNativeSessions(File nativeSessionDirectory, List<NativeSessionFile> streams) {
        for (NativeSessionFile stream : streams) {
            InputStream inputStream = null;
            try {
                inputStream = stream.getStream();
                if (inputStream == null) {
                    CommonUtils.closeQuietly(inputStream);
                } else {
                    gzipInputStream(inputStream, new File(nativeSessionDirectory, stream.getReportsEndpointFilename()));
                    CommonUtils.closeQuietly(inputStream);
                }
            } catch (IOException e) {
            } catch (Throwable th) {
                CommonUtils.closeQuietly(inputStream);
                throw th;
            }
        }
    }

    private static void gzipInputStream(InputStream input, File output) throws IOException {
        if (input != null) {
            byte[] buffer = new byte[8192];
            GZIPOutputStream gos = null;
            try {
                gos = new GZIPOutputStream(new FileOutputStream(output));
                while (true) {
                    int read = input.read(buffer);
                    int read2 = read;
                    if (read > 0) {
                        gos.write(buffer, 0, read2);
                    } else {
                        gos.finish();
                        return;
                    }
                }
            } finally {
                CommonUtils.closeQuietly(gos);
            }
        }
    }
}
