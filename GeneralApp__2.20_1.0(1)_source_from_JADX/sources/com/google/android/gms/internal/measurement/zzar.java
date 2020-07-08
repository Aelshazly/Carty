package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzar extends zza {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzk zzd;
    private final /* synthetic */ zzx zze;

    zzar(zzx zzx, Bundle bundle, zzk zzk) {
        this.zze = zzx;
        this.zzc = bundle;
        this.zzd = zzk;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zze.zzr.performAction(this.zzc, this.zzd, this.zza);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
