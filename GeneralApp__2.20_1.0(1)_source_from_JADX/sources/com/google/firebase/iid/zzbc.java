package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
public final class zzbc extends Binder {
    private final zzbe zza;

    public zzbc(zzbe zzbe) {
        this.zza = zzbe;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbg zzbg) {
        if (Binder.getCallingUid() == Process.myUid()) {
            String str = "FirebaseInstanceId";
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "service received new intent via bind strategy");
            }
            this.zza.zza(zzbg.zza).addOnCompleteListener(zzh.zza(), (OnCompleteListener<TResult>) new zzbf<TResult>(zzbg));
            return;
        }
        throw new SecurityException("Binding only allowed within app");
    }
}
