package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzec {
    private final zzen zza;
    private final byte[] zzb;

    private zzec(int i) {
        this.zzb = new byte[i];
        this.zza = zzen.zza(this.zzb);
    }

    public final zzdu zza() {
        this.zza.zzb();
        return new zzee(this.zzb);
    }

    public final zzen zzb() {
        return this.zza;
    }

    /* synthetic */ zzec(int i, zzdx zzdx) {
        this(i);
    }
}
