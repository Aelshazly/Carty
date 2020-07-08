package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
abstract class zzhm {
    private static final zzhm zzyk = new zzho();
    private static final zzhm zzyl = new zzhn();

    private zzhm() {
    }

    /* access modifiers changed from: 0000 */
    public abstract <L> List<L> zza(Object obj, long j);

    /* access modifiers changed from: 0000 */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(Object obj, long j);

    static zzhm zzgz() {
        return zzyk;
    }

    static zzhm zzha() {
        return zzyl;
    }
}
