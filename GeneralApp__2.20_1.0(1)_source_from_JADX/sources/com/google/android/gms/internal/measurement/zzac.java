package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzac extends zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzk zze;
    private final /* synthetic */ zzx zzf;

    zzac(zzx zzx, String str, String str2, zzk zzk) {
        this.zzf = zzx;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzk;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzf.zzr.getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zze.zza((Bundle) null);
    }
}
