package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzcy<T> implements zzcu<T>, Serializable {
    @NullableDecl
    private final T zzlo;

    zzcy(@NullableDecl T t) {
        this.zzlo = t;
    }

    public final T get() {
        return this.zzlo;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof zzcy)) {
            return false;
        }
        return zzco.equal(this.zzlo, ((zzcy) obj).zzlo);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzlo});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlo);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
