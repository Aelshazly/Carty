package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.firebase_messaging.zze;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final class zzax extends zze {
    private final /* synthetic */ zzau zza;

    zzax(zzau zzau, Looper looper) {
        this.zza = zzau;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zza.zza(message);
    }
}
