package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final /* synthetic */ class zzq implements EventHandler {
    private final zza zza;

    zzq(zza zza2) {
        this.zza = zza2;
    }

    public final void handle(Event event) {
        zza zza2 = this.zza;
        synchronized (zza2) {
            if (zza2.zza()) {
                FirebaseInstanceId.this.zzj();
            }
        }
    }
}
