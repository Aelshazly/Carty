package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfa {
    private static final Class<?> zzrs = zzv("libcore.io.Memory");
    private static final boolean zzrt = (zzv("org.robolectric.Robolectric") != null);

    static boolean zzdr() {
        return zzrs != null && !zzrt;
    }

    static Class<?> zzds() {
        return zzrs;
    }

    private static <T> Class<T> zzv(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
