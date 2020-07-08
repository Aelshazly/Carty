package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzhu<K, V> {
    static <K, V> void zza(zzga zzga, zzht<K, V> zzht, K k, V v) throws IOException {
        zzgi.zza(zzga, zzht.zzys, 1, k);
        zzgi.zza(zzga, zzht.zzyu, 2, v);
    }

    static <K, V> int zza(zzht<K, V> zzht, K k, V v) {
        return zzgi.zza(zzht.zzys, 1, k) + zzgi.zza(zzht.zzyu, 2, v);
    }
}
