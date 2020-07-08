package com.google.android.gms.internal.vision;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzav implements zzau {
    private static zzav zzfs;
    @Nullable
    private final Context zze;
    @Nullable
    private final ContentObserver zzfm;

    static zzav zze(Context context) {
        zzav zzav;
        synchronized (zzav.class) {
            if (zzfs == null) {
                zzfs = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzav(context) : new zzav();
            }
            zzav = zzfs;
        }
        return zzav;
    }

    private zzav(Context context) {
        this.zze = context;
        this.zzfm = new zzax(this, null);
        context.getContentResolver().registerContentObserver(zzal.CONTENT_URI, true, this.zzfm);
    }

    private zzav() {
        this.zze = null;
        this.zzfm = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zzb(String str) {
        if (this.zze == null) {
            return null;
        }
        try {
            return (String) zzat.zza(new zzay(this, str));
        } catch (IllegalStateException | SecurityException e) {
            String str2 = "Unable to read GServices for: ";
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
            return null;
        }
    }

    static synchronized void zzaa() {
        synchronized (zzav.class) {
            if (!(zzfs == null || zzfs.zze == null || zzfs.zzfm == null)) {
                zzfs.zze.getContentResolver().unregisterContentObserver(zzfs.zzfm);
            }
            zzfs = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zzd(String str) {
        return zzal.zza(this.zze.getContentResolver(), str, (String) null);
    }
}
