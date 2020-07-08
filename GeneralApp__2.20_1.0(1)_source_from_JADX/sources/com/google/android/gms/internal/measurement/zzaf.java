package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzaf extends zza {
    private final /* synthetic */ long zzc;
    private final /* synthetic */ zzx zzd;

    zzaf(zzx zzx, long j) {
        this.zzd = zzx;
        this.zzc = j;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setMinimumSessionDuration(this.zzc);
    }
}
