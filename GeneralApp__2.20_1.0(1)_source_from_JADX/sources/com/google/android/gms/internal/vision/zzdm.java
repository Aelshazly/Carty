package com.google.android.gms.internal.vision;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdm<K> extends zzdj<K> {
    private final transient zzdf<K> zzlq;
    private final transient zzdg<K, ?> zzma;

    zzdm(zzdg<K, ?> zzdg, zzdf<K> zzdf) {
        this.zzma = zzdg;
        this.zzlq = zzdf;
    }

    public final zzdr<K> zzby() {
        return (zzdr) zzcc().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Object[] objArr, int i) {
        return zzcc().zza(objArr, i);
    }

    public final zzdf<K> zzcc() {
        return this.zzlq;
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.zzma.get(obj) != null;
    }

    public final int size() {
        return this.zzma.size();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
