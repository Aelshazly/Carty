package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final /* synthetic */ class zzy implements Callable {
    private final Intent zza;

    zzy(Intent intent) {
        this.zza = intent;
    }

    public final Object call() {
        Intent intent = this.zza;
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra != null) {
            String str = "FirebaseInstanceId";
            if (Log.isLoggable(str, 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                StringBuilder sb = new StringBuilder(String.valueOf(stringExtra).length() + 21 + String.valueOf(valueOf).length());
                sb.append("Received command: ");
                sb.append(stringExtra);
                sb.append(" - ");
                sb.append(valueOf);
                Log.d(str, sb.toString());
            }
            if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                FirebaseInstanceId.getInstance().zze();
            } else if ("SYNC".equals(stringExtra)) {
                FirebaseInstanceId.getInstance().zzg();
            }
        }
        return Integer.valueOf(-1);
    }
}
