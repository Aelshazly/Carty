package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final class zzbb implements Runnable {
    private final long zza;
    private final WakeLock zzb = ((PowerManager) zza().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId zzc;

    zzbb(FirebaseInstanceId firebaseInstanceId, long j) {
        this.zzc = firebaseInstanceId;
        this.zza = j;
        this.zzb.setReferenceCounted(false);
    }

    public final void run() {
        if (zzaw.zza().zza(zza())) {
            this.zzb.acquire();
        }
        try {
            this.zzc.zza(true);
            if (!this.zzc.zzf()) {
                this.zzc.zza(false);
                if (zzaw.zza().zza(zza())) {
                    this.zzb.release();
                }
            } else if (!zzaw.zza().zzb(zza()) || zzb()) {
                if (zzc()) {
                    this.zzc.zza(false);
                } else {
                    this.zzc.zza(this.zza);
                }
                if (zzaw.zza().zza(zza())) {
                    this.zzb.release();
                }
            } else {
                new zzba(this).zza();
                if (zzaw.zza().zza(zza())) {
                    this.zzb.release();
                }
            }
        } catch (IOException e) {
            String str = "FirebaseInstanceId";
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 93);
            sb.append("Topic sync or token retrieval failed on hard failure exceptions: ");
            sb.append(message);
            sb.append(". Won't retry the operation.");
            Log.e(str, sb.toString());
            this.zzc.zza(false);
            if (zzaw.zza().zza(zza())) {
                this.zzb.release();
            }
        } catch (Throwable th) {
            if (zzaw.zza().zza(zza())) {
                this.zzb.release();
            }
            throw th;
        }
    }

    private final boolean zzc() throws IOException {
        String str = "FirebaseInstanceId";
        zzay zzb2 = this.zzc.zzb();
        boolean z = true;
        if (!this.zzc.zza(zzb2)) {
            return true;
        }
        try {
            String zzc2 = this.zzc.zzc();
            if (zzc2 == null) {
                Log.e(str, "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Token successfully retrieved");
            }
            if ((zzb2 == null || (zzb2 != null && !zzc2.equals(zzb2.zza))) && FirebaseApp.DEFAULT_APP_NAME.equals(this.zzc.zza().getName())) {
                if (Log.isLoggable(str, 3)) {
                    String str2 = "Invoking onNewToken for app: ";
                    String valueOf = String.valueOf(this.zzc.zza().getName());
                    Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
                Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                intent.putExtra("token", zzc2);
                Context zza2 = zza();
                Intent intent2 = new Intent(zza2, FirebaseInstanceIdReceiver.class);
                intent2.setAction("com.google.firebase.MESSAGING_EVENT");
                intent2.putExtra("wrapped_intent", intent);
                zza2.sendBroadcast(intent2);
            }
            return true;
        } catch (IOException e) {
            String message = e.getMessage();
            if (!"SERVICE_NOT_AVAILABLE".equals(message) && !"INTERNAL_SERVER_ERROR".equals(message) && !"InternalServerError".equals(message)) {
                z = false;
            }
            if (z) {
                String message2 = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message2).length() + 52);
                sb.append("Token retrieval failed: ");
                sb.append(message2);
                sb.append(". Will retry token retrieval");
                Log.w(str, sb.toString());
                return false;
            } else if (e.getMessage() == null) {
                Log.w(str, "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            } else {
                throw e;
            }
        } catch (SecurityException e2) {
            Log.w(str, "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Context zza() {
        return this.zzc.zza().getApplicationContext();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzb() {
        ConnectivityManager connectivityManager = (ConnectivityManager) zza().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
