package com.google.android.gms.internal.vision;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzjn implements ListIterator<String> {
    private ListIterator<String> zzaar = this.zzaat.zzaau.listIterator(this.zzaas);
    private final /* synthetic */ int zzaas;
    private final /* synthetic */ zzjo zzaat;

    zzjn(zzjo zzjo, int i) {
        this.zzaat = zzjo;
        this.zzaas = i;
    }

    public final boolean hasNext() {
        return this.zzaar.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzaar.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzaar.nextIndex();
    }

    public final int previousIndex() {
        return this.zzaar.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.zzaar.previous();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzaar.next();
    }
}
