package com.google.firebase.crashlytics.internal.ndk;

import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class Sha1FileIdStrategy implements FileIdStrategy {
    Sha1FileIdStrategy() {
    }

    public String createId(File file) throws IOException {
        return getFileSHA(file.getPath());
    }

    private static String getFileSHA(String path) throws IOException {
        InputStream data = null;
        try {
            data = new BufferedInputStream(new FileInputStream(path));
            return CommonUtils.sha1(data);
        } finally {
            CommonUtils.closeQuietly(data);
        }
    }
}
