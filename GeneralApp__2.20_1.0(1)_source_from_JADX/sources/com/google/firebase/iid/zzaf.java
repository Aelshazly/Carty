package com.google.firebase.iid;

import android.os.Handler.Callback;
import android.os.Message;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final /* synthetic */ class zzaf implements Callback {
    private final zzac zza;

    zzaf(zzac zzac) {
        this.zza = zzac;
    }

    public final boolean handleMessage(Message message) {
        return this.zza.zza(message);
    }
}
