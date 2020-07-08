package com.google.android.gms.internal.vision;

import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzdo {
    static int zza(Set<?> set) {
        int i = 0;
        for (Object next : set) {
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    static boolean zza(Set<?> set, @NullableDecl Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() != set2.size() || !set.containsAll(set2)) {
                return false;
            }
            return true;
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }
}
