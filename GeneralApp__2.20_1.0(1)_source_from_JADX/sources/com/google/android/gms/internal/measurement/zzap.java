package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzap extends zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzk zzf;
    private final /* synthetic */ zzx zzg;

    zzap(zzx zzx, String str, String str2, boolean z, zzk zzk) {
        this.zzg = zzx;
        this.zzc = str;
        this.zzd = str2;
        this.zze = z;
        this.zzf = zzk;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzg.zzr.getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzf.zza((Bundle) null);
    }
}
