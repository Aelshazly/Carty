package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzn<T> {
    private static String PREFIX = "com.google.android.gms.vision.dynamite";
    private final Object lock = new Object();
    private final String tag;
    private final String zzdf;
    private final String zzdg;
    private final boolean zzdh;
    private boolean zzdi;
    private boolean zzdj;
    private T zzdk;
    private final Context zze;

    public zzn(Context context, String str, String str2) {
        boolean z = false;
        this.zzdi = false;
        this.zzdj = false;
        this.zze = context;
        this.tag = str;
        String str3 = PREFIX;
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 1 + String.valueOf(str2).length());
        sb.append(str3);
        sb.append(".");
        sb.append(str2);
        this.zzdf = sb.toString();
        this.zzdg = str2;
        if (context != null) {
            zzbe.maybeInit(context);
            zzdg zza = zzdg.zza("barcode", Boolean.valueOf(zzkv.zzjp()), "face", Boolean.TRUE, "ica", Boolean.valueOf(zzkv.zzjq()), "ocr", Boolean.TRUE);
            if (zza.containsKey(str2) && ((Boolean) zza.get(str2)).booleanValue()) {
                z = true;
            }
        }
        this.zzdh = z;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException;

    /* access modifiers changed from: protected */
    public abstract void zzn() throws RemoteException;

    public final boolean isOperational() {
        return zzp() != null;
    }

    public final void zzo() {
        synchronized (this.lock) {
            if (this.zzdk != null) {
                try {
                    zzn();
                } catch (RemoteException e) {
                    Log.e(this.tag, "Could not finalize native handle", e);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final T zzp() {
        DynamiteModule dynamiteModule;
        synchronized (this.lock) {
            if (this.zzdk != null) {
                T t = this.zzdk;
                return t;
            }
            try {
                dynamiteModule = DynamiteModule.load(this.zze, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzdf);
            } catch (LoadingException e) {
                Log.d(this.tag, "Cannot load feature, fall back to load dynamite module.");
                dynamiteModule = zzr.zza(this.zze, this.zzdg, this.zzdh);
                if (dynamiteModule == null && this.zzdh && !this.zzdi) {
                    String str = this.tag;
                    String str2 = "Broadcasting download intent for dependency ";
                    String valueOf = String.valueOf(this.zzdg);
                    Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    String str3 = this.zzdg;
                    Intent intent = new Intent();
                    intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
                    intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", str3);
                    intent.setAction("com.google.android.gms.vision.DEPENDENCY");
                    this.zze.sendBroadcast(intent);
                    this.zzdi = true;
                }
            }
            if (dynamiteModule != null) {
                try {
                    this.zzdk = zza(dynamiteModule, this.zze);
                } catch (RemoteException | LoadingException e2) {
                    Log.e(this.tag, "Error creating remote native handle", e2);
                }
            }
            if (!this.zzdj && this.zzdk == null) {
                Log.w(this.tag, "Native handle not yet available. Reverting to no-op handle.");
                this.zzdj = true;
            } else if (this.zzdj && this.zzdk != null) {
                Log.w(this.tag, "Native handle is now available.");
            }
            T t2 = this.zzdk;
            return t2;
        }
    }
}
