package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
abstract class zzhk extends zzhh {
    private boolean zza;

    zzhk(zzgq zzgq) {
        super(zzgq);
        this.zzx.zza(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zze();

    /* access modifiers changed from: 0000 */
    public final boolean zzz() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzaa() {
        if (!zzz()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzab() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zze()) {
            this.zzx.zzag();
            this.zza = true;
        }
    }

    public final void zzac() {
        if (!this.zza) {
            mo16404f_();
            this.zzx.zzag();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: protected */
    /* renamed from: f_ */
    public void mo16404f_() {
    }
}
