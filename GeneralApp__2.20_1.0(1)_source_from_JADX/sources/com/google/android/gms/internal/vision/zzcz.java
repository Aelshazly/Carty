package com.google.android.gms.internal.vision;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzcz<T> implements zzcu<T> {
    @NullableDecl
    private T value;
    private volatile zzcu<T> zzlm;
    private volatile boolean zzln;

    zzcz(zzcu<T> zzcu) {
        this.zzlm = (zzcu) zzct.checkNotNull(zzcu);
    }

    public final T get() {
        if (!this.zzln) {
            synchronized (this) {
                if (!this.zzln) {
                    T t = this.zzlm.get();
                    this.value = t;
                    this.zzln = true;
                    this.zzlm = null;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj = this.zzlm;
        if (obj == null) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
