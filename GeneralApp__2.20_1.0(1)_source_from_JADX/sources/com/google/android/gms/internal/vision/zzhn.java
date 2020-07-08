package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhn extends zzhm {
    private zzhn() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> zza(Object obj, long j) {
        zzgz zzc = zzc(obj, j);
        if (zzc.zzdo()) {
            return zzc;
        }
        int size = zzc.size();
        zzgz zzah = zzc.zzah(size == 0 ? 10 : size << 1);
        zzjp.zza(obj, j, (Object) zzah);
        return zzah;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzdp();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzgz zzc = zzc(obj, j);
        zzgz zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzdo()) {
                zzc = zzc.zzah(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzjp.zza(obj, j, (Object) zzc2);
    }

    private static <E> zzgz<E> zzc(Object obj, long j) {
        return (zzgz) zzjp.zzp(obj, j);
    }
}
