package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzix extends zzjd {
    private final /* synthetic */ zziw zzaah;

    private zzix(zziw zziw) {
        this.zzaah = zziw;
        super(zziw, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zziy(this.zzaah, null);
    }

    /* synthetic */ zzix(zziw zziw, zziv zziv) {
        this(zziw);
    }
}
