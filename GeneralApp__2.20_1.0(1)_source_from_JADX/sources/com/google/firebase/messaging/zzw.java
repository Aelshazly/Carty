package com.google.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
final class zzw extends BroadcastReceiver {
    final /* synthetic */ zzx zza;
    private zzx zzb;

    public zzw(zzx zzx, zzx zzx2) {
        this.zza = zzx;
        this.zzb = zzx2;
    }

    public final synchronized void onReceive(Context context, Intent intent) {
        if (this.zzb != null) {
            if (!this.zza.zzb()) {
                if (zzx.zzc()) {
                    Log.d("FirebaseMessaging", "Connectivity changed. Starting background sync.");
                }
                this.zzb.zzd.zza((Runnable) this.zzb, 0);
                context.unregisterReceiver(this);
                this.zzb = null;
            }
        }
    }
}
