package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhr implements zzhz {
    private zzhz[] zzyr;

    zzhr(zzhz... zzhzArr) {
        this.zzyr = zzhzArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzhz zza : this.zzyr) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzia zzb(Class<?> cls) {
        zzhz[] zzhzArr;
        for (zzhz zzhz : this.zzyr) {
            if (zzhz.zza(cls)) {
                return zzhz.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
