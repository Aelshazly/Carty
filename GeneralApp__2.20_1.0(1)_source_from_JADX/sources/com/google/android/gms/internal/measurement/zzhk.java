package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzhk implements Iterator<Entry<K, V>> {
    private int zza;
    private Iterator<Entry<K, V>> zzb;
    private final /* synthetic */ zzhi zzc;

    private zzhk(zzhi zzhi) {
        this.zzc = zzhi;
        this.zza = this.zzc.zzb.size();
    }

    public final boolean hasNext() {
        int i = this.zza;
        return (i > 0 && i <= this.zzc.zzb.size()) || zza().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Entry<K, V>> zza() {
        if (this.zzb == null) {
            this.zzb = this.zzc.zzf.entrySet().iterator();
        }
        return this.zzb;
    }

    public final /* synthetic */ Object next() {
        if (zza().hasNext()) {
            return (Entry) zza().next();
        }
        List zzb2 = this.zzc.zzb;
        int i = this.zza - 1;
        this.zza = i;
        return (Entry) zzb2.get(i);
    }

    /* synthetic */ zzhk(zzhi zzhi, zzhh zzhh) {
        this(zzhi);
    }
}
