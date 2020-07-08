package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzag extends zza {
    private final /* synthetic */ zzx zzc;

    zzag(zzx zzx) {
        this.zzc = zzx;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzc.zzr.resetAnalyticsData(this.zza);
    }
}
