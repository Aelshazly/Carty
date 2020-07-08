package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zzf;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgq implements zzhz {
    private static final zzgq zzwe = new zzgq();

    private zzgq() {
    }

    public static zzgq zzfw() {
        return zzwe;
    }

    public final boolean zza(Class<?> cls) {
        return zzgs.class.isAssignableFrom(cls);
    }

    public final zzia zzb(Class<?> cls) {
        if (!zzgs.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzia) zzgs.zzd(cls.asSubclass(zzgs.class)).zza(zzf.zzwt, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
