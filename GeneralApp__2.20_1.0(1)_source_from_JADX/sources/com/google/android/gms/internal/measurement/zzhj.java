package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzhj extends zzhp {
    private final /* synthetic */ zzhi zza;

    private zzhj(zzhi zzhi) {
        this.zza = zzhi;
        super(zzhi, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzhk(this.zza, null);
    }

    /* synthetic */ zzhj(zzhi zzhi, zzhh zzhh) {
        this(zzhi);
    }
}
