package com.google.android.gms.internal.vision;

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

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzaq implements zzau {
    private static final Map<Uri, zzaq> zzfk = new ArrayMap();
    private static final String[] zzfq = {"key", "value"};
    private final Uri uri;
    private final ContentResolver zzfl;
    private final ContentObserver zzfm = new zzas(this, null);
    private final Object zzfn = new Object();
    private volatile Map<String, String> zzfo;
    private final List<zzar> zzfp = new ArrayList();

    private zzaq(ContentResolver contentResolver, Uri uri2) {
        this.zzfl = contentResolver;
        this.uri = uri2;
        contentResolver.registerContentObserver(uri2, false, this.zzfm);
    }

    public static zzaq zza(ContentResolver contentResolver, Uri uri2) {
        zzaq zzaq;
        synchronized (zzaq.class) {
            zzaq = (zzaq) zzfk.get(uri2);
            if (zzaq == null) {
                try {
                    zzaq zzaq2 = new zzaq(contentResolver, uri2);
                    try {
                        zzfk.put(uri2, zzaq2);
                        zzaq = zzaq2;
                    } catch (SecurityException e) {
                        zzaq = zzaq2;
                    }
                } catch (SecurityException e2) {
                }
            }
        }
        return zzaq;
    }

    private final Map<String, String> zzu() {
        Map<String, String> map = this.zzfo;
        if (map == null) {
            synchronized (this.zzfn) {
                map = this.zzfo;
                if (map == null) {
                    map = zzw();
                    this.zzfo = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    public final void zzv() {
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

    /* JADX INFO: finally extract failed */
    private final Map<String, String> zzw() {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Map<String, String> map = (Map) zzat.zza(new zzap(this));
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

    static synchronized void zzx() {
        synchronized (zzaq.class) {
            for (zzaq zzaq : zzfk.values()) {
                zzaq.zzfl.unregisterContentObserver(zzaq.zzfm);
            }
            zzfk.clear();
        }
    }

    public final /* synthetic */ Object zzb(String str) {
        return (String) zzu().get(str);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Map zzy() {
        Map map;
        Cursor query = this.zzfl.query(this.uri, zzfq, null, null, null);
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
