package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
final class zzkn extends zzaf {
    private final /* synthetic */ zzks zza;
    private final /* synthetic */ zzko zzb;

    zzkn(zzko zzko, zzhj zzhj, zzks zzks) {
        this.zzb = zzko;
        this.zza = zzks;
        super(zzhj);
    }

    public final void zza() {
        this.zzb.zzf();
        this.zzb.zzr().zzx().zza("Starting upload from DelayedRunnable");
        this.zza.zzl();
    }
}
