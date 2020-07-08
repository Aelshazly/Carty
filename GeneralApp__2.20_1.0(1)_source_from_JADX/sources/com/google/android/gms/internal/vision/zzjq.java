package com.google.android.gms.internal.vision;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzjq implements Iterator<String> {
    private final /* synthetic */ zzjo zzaat;
    private Iterator<String> zzabp = this.zzaat.zzaau.iterator();

    zzjq(zzjo zzjo) {
        this.zzaat = zzjo;
    }

    public final boolean hasNext() {
        return this.zzabp.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzabp.next();
    }
}
