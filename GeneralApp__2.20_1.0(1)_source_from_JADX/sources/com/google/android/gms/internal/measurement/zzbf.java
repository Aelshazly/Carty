package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzbf extends zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzd zzd;

    zzbf(zzd zzd2, Activity activity) {
        this.zzd = zzd2;
        this.zzc = activity;
        super(zzx.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        zzx.this.zzr.onActivityStopped(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
