package com.google.firebase.iid;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Parcelable;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import p008cz.msebera.android.httpclient.HttpStatus;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    private final ExecutorService zza = zzh.zzb();

    public final void onReceive(Context context, Intent intent) {
        zzb zzb;
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
            if (intent2 != null) {
                intent = intent2;
            }
            intent.setComponent(null);
            intent.setPackage(context.getPackageName());
            if (VERSION.SDK_INT <= 18) {
                intent.removeCategory(context.getPackageName());
            }
            if ("google.com/iid".equals(intent.getStringExtra("from"))) {
                zzb = new zzz(this.zza);
            } else {
                zzb = new zza(context, this.zza);
            }
            zzb.zza(intent).addOnCompleteListener((Executor) this.zza, (OnCompleteListener<TResult>) new zzr<TResult>(isOrderedBroadcast(), goAsync()));
        }
    }

    static final /* synthetic */ void zza(boolean z, PendingResult pendingResult, Task task) {
        int i;
        if (z) {
            if (task.isSuccessful()) {
                i = ((Integer) task.getResult()).intValue();
            } else {
                i = HttpStatus.SC_INTERNAL_SERVER_ERROR;
            }
            pendingResult.setResultCode(i);
        }
        pendingResult.finish();
    }
}
