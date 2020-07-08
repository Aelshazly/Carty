package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzgz {
    private static final zzgz zza = new zzgz();
    private final zzhg zzb = new zzgb();
    private final ConcurrentMap<Class<?>, zzhd<?>> zzc = new ConcurrentHashMap();

    public static zzgz zza() {
        return zza;
    }

    public final <T> zzhd<T> zza(Class<T> cls) {
        String str = "messageType";
        zzff.zza(cls, str);
        zzhd<T> zzhd = (zzhd) this.zzc.get(cls);
        if (zzhd != null) {
            return zzhd;
        }
        zzhd<T> zza2 = this.zzb.zza(cls);
        zzff.zza(cls, str);
        zzff.zza(zza2, "schema");
        zzhd zzhd2 = (zzhd) this.zzc.putIfAbsent(cls, zza2);
        if (zzhd2 != null) {
            return zzhd2;
        }
        return zza2;
    }

    public final <T> zzhd<T> zza(T t) {
        return zza(t.getClass());
    }

    private zzgz() {
    }
}
