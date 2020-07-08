package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzjo extends AbstractList<String> implements zzhj, RandomAccess {
    /* access modifiers changed from: private */
    public final zzhj zzaau;

    public zzjo(zzhj zzhj) {
        this.zzaau = zzhj;
    }

    public final Object getRaw(int i) {
        return this.zzaau.getRaw(i);
    }

    public final int size() {
        return this.zzaau.size();
    }

    public final void zzc(zzfh zzfh) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzjn(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzjq(this);
    }

    public final List<?> zzgx() {
        return this.zzaau.zzgx();
    }

    public final zzhj zzgy() {
        return this;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzaau.get(i);
    }
}
