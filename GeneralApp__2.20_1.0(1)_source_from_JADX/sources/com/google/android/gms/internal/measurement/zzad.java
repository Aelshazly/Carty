package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzad extends zza {
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzx zzd;

    zzad(zzx zzx, boolean z) {
        this.zzd = zzx;
        this.zzc = z;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setMeasurementEnabled(this.zzc, this.zza);
    }
}
