package com.google.android.gms.vision.clearcut;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.vision.zzea.zza;
import com.google.android.gms.internal.vision.zzea.zza.C1744zza;
import com.google.android.gms.internal.vision.zzgs;
import com.google.android.gms.vision.C0548L;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class LogUtils {
    public static zza zza(Context context) {
        C1744zza zzl = zza.zzcj().zzl(context.getPackageName());
        String zzb = zzb(context);
        if (zzb != null) {
            zzl.zzm(zzb);
        }
        return (zza) ((zzgs) zzl.zzgc());
    }

    private static String zzb(Context context) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            C0548L.m64e(e, "Unable to find calling package info for %s", context.getPackageName());
            return null;
        }
    }
}
