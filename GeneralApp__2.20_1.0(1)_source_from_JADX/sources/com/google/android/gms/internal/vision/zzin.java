package com.google.android.gms.internal.vision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzin {
    private static final zzin zzzt = new zzin();
    private final zziu zzzu = new zzhp();
    private final ConcurrentMap<Class<?>, zzir<?>> zzzv = new ConcurrentHashMap();

    public static zzin zzho() {
        return zzzt;
    }

    public final <T> zzir<T> zzf(Class<T> cls) {
        String str = "messageType";
        zzgt.zza(cls, str);
        zzir<T> zzir = (zzir) this.zzzv.get(cls);
        if (zzir != null) {
            return zzir;
        }
        zzir<T> zze = this.zzzu.zze(cls);
        zzgt.zza(cls, str);
        zzgt.zza(zze, "schema");
        zzir zzir2 = (zzir) this.zzzv.putIfAbsent(cls, zze);
        if (zzir2 != null) {
            return zzir2;
        }
        return zze;
    }

    public final <T> zzir<T> zzv(T t) {
        return zzf(t.getClass());
    }

    private zzin() {
    }
}
