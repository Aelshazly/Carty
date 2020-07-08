package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzdj<E> extends zzdc<E> implements Set<E> {
    @NullableDecl
    private transient zzdf<E> zzlz;

    zzdj() {
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzdo.zza(this, obj);
    }

    public int hashCode() {
        return zzdo.zza(this);
    }

    public zzdf<E> zzcc() {
        zzdf<E> zzdf = this.zzlz;
        if (zzdf != null) {
            return zzdf;
        }
        zzdf<E> zzch = zzch();
        this.zzlz = zzch;
        return zzch;
    }

    /* access modifiers changed from: 0000 */
    public zzdf<E> zzch() {
        return zzdf.zza(toArray());
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
