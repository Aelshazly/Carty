package com.google.android.gms.internal.vision;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdx extends WeakReference<Throwable> {
    private final int zzmm;

    public zzdx(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.zzmm = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final int hashCode() {
        return this.zzmm;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzdx zzdx = (zzdx) obj;
        if (this.zzmm == zzdx.zzmm && get() == zzdx.get()) {
            return true;
        }
        return false;
    }
}
