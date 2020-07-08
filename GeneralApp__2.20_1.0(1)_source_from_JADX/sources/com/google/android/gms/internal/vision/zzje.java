package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzje implements Iterator<Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zziw zzaah;
    private Iterator<Entry<K, V>> zzaai;
    private boolean zzaam;

    private zzje(zziw zziw) {
        this.zzaah = zziw;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzaah.zzaac.size() || (!this.zzaah.zzaad.isEmpty() && zzic().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzaam) {
            this.zzaam = false;
            this.zzaah.zzia();
            if (this.pos < this.zzaah.zzaac.size()) {
                zziw zziw = this.zzaah;
                int i = this.pos;
                this.pos = i - 1;
                zziw.zzbw(i);
                return;
            }
            zzic().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Entry<K, V>> zzic() {
        if (this.zzaai == null) {
            this.zzaai = this.zzaah.zzaad.entrySet().iterator();
        }
        return this.zzaai;
    }

    public final /* synthetic */ Object next() {
        this.zzaam = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzaah.zzaac.size()) {
            return (Entry) this.zzaah.zzaac.get(this.pos);
        }
        return (Entry) zzic().next();
    }

    /* synthetic */ zzje(zziw zziw, zziv zziv) {
        this(zziw);
    }
}
