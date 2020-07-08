package com.google.firebase.messaging;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.google.firebase.iid.zzao;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
final class zzx implements Runnable {
    private static final Object zzf = new Object();
    private static Boolean zzg = null;
    private static Boolean zzh = null;
    private final Context zza;
    private final zzao zzb;
    private final WakeLock zzc;
    /* access modifiers changed from: private */
    public final zzu zzd;
    private final long zze;

    zzx(zzu zzu, Context context, zzao zzao, long j) {
        this.zzd = zzu;
        this.zza = context;
        this.zze = j;
        this.zzb = zzao;
        this.zzc = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "wake:com.google.firebase.messaging");
    }

    public final void run() {
        String str = "TopicsSyncTask's wakelock was already released due to timeout.";
        String str2 = "FirebaseMessaging";
        if (zza(this.zza)) {
            this.zzc.acquire(zzd.zza);
        }
        try {
            boolean z = true;
            this.zzd.zza(true);
            if (!this.zzb.zza()) {
                this.zzd.zza(false);
                if (zza(this.zza)) {
                    try {
                        this.zzc.release();
                    } catch (RuntimeException e) {
                        Log.i(str2, str);
                    }
                }
            } else if (!zzb(this.zza) || zzb()) {
                if (this.zzd.zzb()) {
                    this.zzd.zza(false);
                } else {
                    this.zzd.zza(this.zze);
                }
                if (zza(this.zza)) {
                    try {
                        this.zzc.release();
                    } catch (RuntimeException e2) {
                        Log.i(str2, str);
                    }
                }
            } else {
                zzw zzw = new zzw(this, this);
                if (!Log.isLoggable(str2, 3)) {
                    if (VERSION.SDK_INT != 23 || !Log.isLoggable(str2, 3)) {
                        z = false;
                    }
                }
                if (z) {
                    Log.d(str2, "Connectivity change received registered");
                }
                zzw.zza.zza.registerReceiver(zzw, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                if (zza(this.zza)) {
                    try {
                        this.zzc.release();
                    } catch (RuntimeException e3) {
                        Log.i(str2, str);
                    }
                }
            }
        } catch (IOException e4) {
            String str3 = "Failed to sync topics. Won't retry sync. ";
            String valueOf = String.valueOf(e4.getMessage());
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            this.zzd.zza(false);
            if (zza(this.zza)) {
                try {
                    this.zzc.release();
                } catch (RuntimeException e5) {
                    Log.i(str2, str);
                }
            }
        } catch (Throwable th) {
            if (zza(this.zza)) {
                try {
                    this.zzc.release();
                } catch (RuntimeException e6) {
                    Log.i(str2, str);
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final synchronized boolean zzb() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.zza.getSystemService("connectivity");
        activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* access modifiers changed from: private */
    public static boolean zzc() {
        String str = "FirebaseMessaging";
        return Log.isLoggable(str, 3) || (VERSION.SDK_INT == 23 && Log.isLoggable(str, 3));
    }

    private static boolean zza(Context context) {
        boolean z;
        boolean booleanValue;
        synchronized (zzf) {
            if (zzg == null) {
                z = zza(context, "android.permission.WAKE_LOCK", zzg);
            } else {
                z = zzg.booleanValue();
            }
            Boolean valueOf = Boolean.valueOf(z);
            zzg = valueOf;
            booleanValue = valueOf.booleanValue();
        }
        return booleanValue;
    }

    private static boolean zzb(Context context) {
        boolean z;
        boolean booleanValue;
        synchronized (zzf) {
            if (zzh == null) {
                z = zza(context, "android.permission.ACCESS_NETWORK_STATE", zzh);
            } else {
                z = zzh.booleanValue();
            }
            Boolean valueOf = Boolean.valueOf(z);
            zzh = valueOf;
            booleanValue = valueOf.booleanValue();
        }
        return booleanValue;
    }

    private static boolean zza(Context context, String str, Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = context.checkCallingOrSelfPermission(str) == 0;
        if (!z) {
            String str2 = "FirebaseMessaging";
            if (Log.isLoggable(str2, 3)) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 142);
                sb.append("Missing Permission: ");
                sb.append(str);
                sb.append(". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest");
                Log.d(str2, sb.toString());
            }
        }
        return z;
    }
}
