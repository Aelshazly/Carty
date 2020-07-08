package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzcu implements zzcb {
    private static final Map<String, zzcu> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final OnSharedPreferenceChangeListener zzc = new zzct(this);
    private final Object zzd = new Object();
    private volatile Map<String, ?> zze;
    private final List<zzcc> zzf = new ArrayList();

    static zzcu zza(Context context, String str) {
        boolean z;
        zzcu zzcu;
        if (!zzby.zza() || str.startsWith("direct_boot:")) {
            z = true;
        } else {
            z = zzby.zza(context);
        }
        if (!z) {
            return null;
        }
        synchronized (zzcu.class) {
            zzcu = (zzcu) zza.get(str);
            if (zzcu == null) {
                zzcu = new zzcu(zzb(context, str));
                zza.put(str, zzcu);
            }
        }
        return zzcu;
    }

    private static SharedPreferences zzb(Context context, String str) {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzby.zza()) {
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

    private zzcu(SharedPreferences sharedPreferences) {
        this.zzb = sharedPreferences;
        this.zzb.registerOnSharedPreferenceChangeListener(this.zzc);
    }

    /* JADX INFO: finally extract failed */
    public final Object zza(String str) {
        Map<String, ?> map = this.zze;
        if (map == null) {
            synchronized (this.zzd) {
                map = this.zze;
                if (map == null) {
                    ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzb.getAll();
                        this.zze = all;
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

    static synchronized void zza() {
        synchronized (zzcu.class) {
            for (zzcu zzcu : zza.values()) {
                zzcu.zzb.unregisterOnSharedPreferenceChangeListener(zzcu.zzc);
            }
            zza.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzd) {
            this.zze = null;
            zzcl.zza();
        }
        synchronized (this) {
            for (zzcc zza2 : this.zzf) {
                zza2.zza();
            }
        }
    }
}
