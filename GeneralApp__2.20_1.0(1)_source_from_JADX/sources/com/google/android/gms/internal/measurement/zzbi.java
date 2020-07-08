package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzbi extends zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzk zzd;
    private final /* synthetic */ zzd zze;

    zzbi(zzd zzd2, Activity activity, zzk zzk) {
        this.zze = zzd2;
        this.zzc = activity;
        this.zzd = zzk;
        super(zzx.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        zzx.this.zzr.onActivitySaveInstanceState(ObjectWrapper.wrap(this.zzc), this.zzd, this.zzb);
    }
}
