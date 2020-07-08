package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzz extends zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Bundle zze;
    private final /* synthetic */ zzx zzf;

    zzz(zzx zzx, String str, String str2, Bundle bundle) {
        this.zzf = zzx;
        this.zzc = str;
        this.zzd = str2;
        this.zze = bundle;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzf.zzr.clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}
