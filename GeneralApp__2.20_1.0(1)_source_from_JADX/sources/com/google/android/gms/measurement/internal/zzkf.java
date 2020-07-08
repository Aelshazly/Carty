package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzkf {
    final /* synthetic */ zzke zza;
    private zzki zzb;

    zzkf(zzke zzke) {
        this.zza = zzke;
    }

    /* access modifiers changed from: 0000 */
    public final void zza() {
        this.zza.zzd();
        if (this.zza.zzt().zza(zzap.zzci) && this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb() {
        if (this.zza.zzt().zza(zzap.zzci)) {
            this.zzb = new zzki(this, this.zza.zzm().currentTimeMillis());
            this.zza.zzc.postDelayed(this.zzb, 2000);
        }
    }
}
