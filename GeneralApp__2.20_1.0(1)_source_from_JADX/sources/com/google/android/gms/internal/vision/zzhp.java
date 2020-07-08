package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zzf;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhp implements zziu {
    private static final zzhz zzyo = new zzhs();
    private final zzhz zzyn;

    public zzhp() {
        this(new zzhr(zzgq.zzfw(), zzhb()));
    }

    private zzhp(zzhz zzhz) {
        this.zzyn = (zzhz) zzgt.zza(zzhz, "messageInfoFactory");
    }

    public final <T> zzir<T> zze(Class<T> cls) {
        zzit.zzg(cls);
        zzia zzb = this.zzyn.zzb(cls);
        if (zzb.zzhj()) {
            if (zzgs.class.isAssignableFrom(cls)) {
                return zzii.zza(zzit.zzhu(), zzgj.zzfq(), zzb.zzhk());
            }
            return zzii.zza(zzit.zzhs(), zzgj.zzfr(), zzb.zzhk());
        } else if (zzgs.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzig.zza(cls, zzb, zzim.zzhm(), zzhm.zzha(), zzit.zzhu(), zzgj.zzfq(), zzhx.zzhg());
            }
            return zzig.zza(cls, zzb, zzim.zzhm(), zzhm.zzha(), zzit.zzhu(), null, zzhx.zzhg());
        } else if (zza(zzb)) {
            return zzig.zza(cls, zzb, zzim.zzhl(), zzhm.zzgz(), zzit.zzhs(), zzgj.zzfr(), zzhx.zzhf());
        } else {
            return zzig.zza(cls, zzb, zzim.zzhl(), zzhm.zzgz(), zzit.zzht(), null, zzhx.zzhf());
        }
    }

    private static boolean zza(zzia zzia) {
        return zzia.zzhi() == zzf.zzwz;
    }

    private static zzhz zzhb() {
        try {
            return (zzhz) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zzyo;
        }
    }
}
