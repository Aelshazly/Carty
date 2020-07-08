package com.google.android.gms.internal.vision;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzbb {
    private static final ArrayMap<String, Uri> zzfv = new ArrayMap<>();

    public static synchronized Uri getContentProviderUri(String str) {
        Uri uri;
        synchronized (zzbb.class) {
            uri = (Uri) zzfv.get(str);
            if (uri == null) {
                String str2 = "content://com.google.android.gms.phenotype/";
                String valueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                zzfv.put(str, uri);
            }
        }
        return uri;
    }
}
