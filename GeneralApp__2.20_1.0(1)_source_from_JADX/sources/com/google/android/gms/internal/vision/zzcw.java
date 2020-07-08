package com.google.android.gms.internal.vision;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzcw<T> implements zzcu<T>, Serializable {
    @NullableDecl
    private transient T value;
    private final zzcu<T> zzlm;
    private volatile transient boolean zzln;

    zzcw(zzcu<T> zzcu) {
        this.zzlm = (zzcu) zzct.checkNotNull(zzcu);
    }

    public final T get() {
        if (!this.zzln) {
            synchronized (this) {
                if (!this.zzln) {
                    T t = this.zzlm.get();
                    this.value = t;
                    this.zzln = true;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj;
        if (this.zzln) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        } else {
            obj = this.zzlm;
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
