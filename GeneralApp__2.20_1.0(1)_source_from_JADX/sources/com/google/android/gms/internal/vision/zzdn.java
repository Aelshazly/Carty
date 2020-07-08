package com.google.android.gms.internal.vision;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdn extends zzdf<Entry<K, V>> {
    private final /* synthetic */ zzdk zzmf;

    zzdn(zzdk zzdk) {
        this.zzmf = zzdk;
    }

    public final int size() {
        return this.zzmf.size;
    }

    public final /* synthetic */ Object get(int i) {
        zzct.zzc(i, this.zzmf.size);
        Object[] zzb = this.zzmf.zzmb;
        int i2 = i * 2;
        zzdk zzdk = this.zzmf;
        Object obj = zzb[i2];
        Object[] zzb2 = zzdk.zzmb;
        zzdk zzdk2 = this.zzmf;
        return new SimpleImmutableEntry(obj, zzb2[i2 + 1]);
    }
}
