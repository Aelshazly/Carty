package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzal extends zza {
    private final /* synthetic */ zzk zzc;
    private final /* synthetic */ zzx zzd;

    zzal(zzx zzx, zzk zzk) {
        this.zzd = zzx;
        this.zzc = zzk;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.getCachedAppInstanceId(this.zzc);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
