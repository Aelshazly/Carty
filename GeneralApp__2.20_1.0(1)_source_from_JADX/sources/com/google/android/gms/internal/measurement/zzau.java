package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzau extends zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzk zzd;
    private final /* synthetic */ zzx zze;

    zzau(zzx zzx, String str, zzk zzk) {
        this.zze = zzx;
        this.zzc = str;
        this.zzd = zzk;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zze.zzr.getMaxUserProperties(this.zzc, this.zzd);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
