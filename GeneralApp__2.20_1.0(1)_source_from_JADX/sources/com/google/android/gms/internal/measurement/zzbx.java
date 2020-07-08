package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzbx implements zzcb {
    private static final Map<Uri, zzbx> zza = new ArrayMap();
    private static final String[] zzh = {"key", "value"};
    private final ContentResolver zzb;
    private final Uri zzc;
    private final ContentObserver zzd = new zzbz(this, null);
    private final Object zze = new Object();
    private volatile Map<String, String> zzf;
    private final List<zzcc> zzg = new ArrayList();

    private zzbx(ContentResolver contentResolver, Uri uri) {
        this.zzb = contentResolver;
        this.zzc = uri;
        contentResolver.registerContentObserver(uri, false, this.zzd);
    }

    public static zzbx zza(ContentResolver contentResolver, Uri uri) {
        zzbx zzbx;
        synchronized (zzbx.class) {
            zzbx = (zzbx) zza.get(uri);
            if (zzbx == null) {
                try {
                    zzbx zzbx2 = new zzbx(contentResolver, uri);
                    try {
                        zza.put(uri, zzbx2);
                        zzbx = zzbx2;
                    } catch (SecurityException e) {
                        zzbx = zzbx2;
                    }
                } catch (SecurityException e2) {
                }
            }
        }
        return zzbx;
    }

    public final Map<String, String> zza() {
        Map<String, String> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                map = this.zzf;
                if (map == null) {
                    map = zze();
                    this.zzf = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    public final void zzb() {
        synchronized (this.zze) {
            this.zzf = null;
            zzcl.zza();
        }
        synchronized (this) {
            for (zzcc zza2 : this.zzg) {
                zza2.zza();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private final Map<String, String> zze() {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Map<String, String> map = (Map) zzce.zza(new zzca(this));
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return map;
        } catch (SQLiteException | IllegalStateException | SecurityException e) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return null;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    static synchronized void zzc() {
        synchronized (zzbx.class) {
            for (zzbx zzbx : zza.values()) {
                zzbx.zzb.unregisterContentObserver(zzbx.zzd);
            }
            zza.clear();
        }
    }

    public final /* synthetic */ Object zza(String str) {
        return (String) zza().get(str);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Map zzd() {
        Map map;
        Cursor query = this.zzb.query(this.zzc, zzh, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }
}
