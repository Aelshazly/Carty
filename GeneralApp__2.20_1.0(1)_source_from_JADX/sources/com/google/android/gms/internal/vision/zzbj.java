package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzbj implements zzau {
    private static final Map<String, zzbj> zzgi = new ArrayMap();
    private final Object zzfn = new Object();
    private volatile Map<String, ?> zzfo;
    private final List<zzar> zzfp = new ArrayList();
    private final SharedPreferences zzgj;
    private final OnSharedPreferenceChangeListener zzgk = new zzbm(this);

    static zzbj zzb(Context context, String str) {
        boolean z;
        zzbj zzbj;
        if (!zzan.zzs() || str.startsWith("direct_boot:")) {
            z = true;
        } else {
            z = zzan.isUserUnlocked(context);
        }
        if (!z) {
            return null;
        }
        synchronized (zzbj.class) {
            zzbj = (zzbj) zzgi.get(str);
            if (zzbj == null) {
                zzbj = new zzbj(zzc(context, str));
                zzgi.put(str, zzbj);
            }
        }
        return zzbj;
    }

    private static SharedPreferences zzc(Context context, String str) {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzan.zzs()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return context.getSharedPreferences(str.substring(12), 0);
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return sharedPreferences;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private zzbj(SharedPreferences sharedPreferences) {
        this.zzgj = sharedPreferences;
        this.zzgj.registerOnSharedPreferenceChangeListener(this.zzgk);
    }

    /* JADX INFO: finally extract failed */
    public final Object zzb(String str) {
        Map<String, ?> map = this.zzfo;
        if (map == null) {
            synchronized (this.zzfn) {
                map = this.zzfo;
                if (map == null) {
                    ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzgj.getAll();
                        this.zzfo = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    static synchronized void zzx() {
        synchronized (zzbj.class) {
            for (zzbj zzbj : zzgi.values()) {
                zzbj.zzgj.unregisterOnSharedPreferenceChangeListener(zzbj.zzgk);
            }
            zzgi.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzfn) {
            this.zzfo = null;
            zzbe.zzab();
        }
        synchronized (this) {
            for (zzar zzz : this.zzfp) {
                zzz.zzz();
            }
        }
    }
}
