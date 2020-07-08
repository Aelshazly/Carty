package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzah extends zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzx zzd;

    zzah(zzx zzx, String str) {
        this.zzd = zzx;
        this.zzc = str;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.beginAdUnitExposure(this.zzc, this.zzb);
    }
}
