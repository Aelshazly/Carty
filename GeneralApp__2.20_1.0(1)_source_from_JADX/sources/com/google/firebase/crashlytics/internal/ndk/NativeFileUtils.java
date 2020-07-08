package com.google.firebase.crashlytics.internal.ndk;

import android.content.Context;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public final class NativeFileUtils {
    private NativeFileUtils() {
    }

    public static byte[] binaryImagesJsonFromMapsFile(File file, Context context) throws IOException {
        if (file == null || !file.exists()) {
            return new byte[0];
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            return new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(reader);
        } finally {
            CommonUtils.closeQuietly(reader);
        }
    }
}
