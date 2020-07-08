package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.measurement.internal.zzhp;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.3.0 */
final class zzaw extends zza {
    private final /* synthetic */ zzhp zzc;
    private final /* synthetic */ zzx zzd;

    zzaw(zzx zzx, zzhp zzhp) {
        this.zzd = zzx;
        this.zzc = zzhp;
        super(zzx);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        for (int i = 0; i < this.zzd.zzf.size(); i++) {
            if (this.zzc.equals(((Pair) this.zzd.zzf.get(i)).first)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            }
        }
        zzb zzb = new zzb(this.zzc);
        this.zzd.zzf.add(new Pair(this.zzc, zzb));
        this.zzd.zzr.registerOnMeasurementEventListener(zzb);
    }
}
