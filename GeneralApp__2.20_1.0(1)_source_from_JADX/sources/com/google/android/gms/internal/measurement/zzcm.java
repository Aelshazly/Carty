package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzcm {
    private static final ArrayMap<String, Uri> zza = new ArrayMap<>();

    public static synchronized Uri zza(String str) {
        Uri uri;
        synchronized (zzcm.class) {
            uri = (Uri) zza.get(str);
            if (uri == null) {
                String str2 = "content://com.google.android.gms.phenotype/";
                String valueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                zza.put(str, uri);
            }
        }
        return uri;
    }
}
