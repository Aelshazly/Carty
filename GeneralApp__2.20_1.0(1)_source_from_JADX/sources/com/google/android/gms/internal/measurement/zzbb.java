package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzbb extends zza {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzx zzd;

    zzbb(zzx zzx, Bundle bundle) {
        this.zzd = zzx;
        this.zzc = bundle;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setConditionalUserProperty(this.zzc, this.zza);
    }
}
