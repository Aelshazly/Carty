package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zziy implements Iterator<Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zziw zzaah;
    private Iterator<Entry<K, V>> zzaai;

    private zziy(zziw zziw) {
        this.zzaah = zziw;
        this.pos = this.zzaah.zzaac.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzaah.zzaac.size()) || zzic().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Entry<K, V>> zzic() {
        if (this.zzaai == null) {
            this.zzaai = this.zzaah.zzaaf.entrySet().iterator();
        }
        return this.zzaai;
    }

    public final /* synthetic */ Object next() {
        if (zzic().hasNext()) {
            return (Entry) zzic().next();
        }
        List zzb = this.zzaah.zzaac;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    /* synthetic */ zziy(zziw zziw, zziv zziv) {
        this(zziw);
    }
}
