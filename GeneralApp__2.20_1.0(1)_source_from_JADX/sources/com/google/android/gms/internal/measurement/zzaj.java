package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.measurement.internal.zzhq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzaj extends zza {
    private final /* synthetic */ zzhq zzc;
    private final /* synthetic */ zzx zzd;

    zzaj(zzx zzx, zzhq zzhq) {
        this.zzd = zzx;
        this.zzc = zzhq;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setEventInterceptor(new zzc(this.zzc));
    }
}
