package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzv;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzgg {
    private final zzgh zza;

    public zzgg(zzgh zzgh) {
        Preconditions.checkNotNull(zzgh);
        this.zza = zzgh;
    }

    public static boolean zza(Context context) {
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);
            if (receiverInfo != null && receiverInfo.enabled) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
        }
    }

    public final void zza(Context context, Intent intent) {
        zzgq zza2 = zzgq.zza(context, (zzv) null);
        zzfj zzr = zza2.zzr();
        if (intent == null) {
            zzr.zzi().zza("Receiver called with null intent");
            return;
        }
        zza2.zzu();
        String action = intent.getAction();
        zzr.zzx().zza("Local receiver got", action);
        String str = "com.google.android.gms.measurement.UPLOAD";
        if (str.equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction(str);
            zzr.zzx().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                zza2.zzq().zza((Runnable) new zzgf(this, zza2, zzr));
            } catch (Exception e) {
                zzr.zzi().zza("Install Referrer Reporter encountered a problem", e);
            }
            PendingResult doGoAsync = this.zza.doGoAsync();
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                zzr.zzx().zza("Install referrer extras are null");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                }
                return;
            }
            zzr.zzv().zza("Install referrer extras are", stringExtra);
            String str2 = "?";
            if (!stringExtra.contains(str2)) {
                String valueOf = String.valueOf(stringExtra);
                stringExtra = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            Bundle zza3 = zza2.zzi().zza(Uri.parse(stringExtra));
            if (zza3 == null) {
                zzr.zzx().zza("No campaign defined in install referrer broadcast");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                }
            } else {
                long longExtra = intent.getLongExtra("referrer_timestamp_seconds", 0) * 1000;
                if (longExtra == 0) {
                    zzr.zzi().zza("Install referrer is missing timestamp");
                }
                zzgj zzq = zza2.zzq();
                zzgi zzgi = new zzgi(this, zza2, longExtra, zza3, context, zzr, doGoAsync);
                zzq.zza((Runnable) zzgi);
            }
        }
    }
}
