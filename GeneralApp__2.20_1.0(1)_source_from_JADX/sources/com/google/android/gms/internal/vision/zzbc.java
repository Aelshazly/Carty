package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzbc {
    private static volatile zzcn<Boolean> zzfw = zzcn.zzbx();
    private static final Object zzfx = new Object();

    private static boolean zzh(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean zza(Context context, Uri uri) {
        boolean z;
        String authority = uri.getAuthority();
        boolean z2 = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 91);
            sb.append(authority);
            sb.append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
            Log.e("PhenotypeClientHelper", sb.toString());
            return false;
        } else if (zzfw.isPresent()) {
            return ((Boolean) zzfw.get()).booleanValue();
        } else {
            synchronized (zzfx) {
                if (zzfw.isPresent()) {
                    boolean booleanValue = ((Boolean) zzfw.get()).booleanValue();
                    return booleanValue;
                }
                if ("com.google.android.gms".equals(context.getPackageName())) {
                    z = true;
                } else {
                    ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0);
                    z = resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName);
                }
                if (z && zzh(context)) {
                    z2 = true;
                }
                zzfw = zzcn.zzc(Boolean.valueOf(z2));
                return ((Boolean) zzfw.get()).booleanValue();
            }
        }
    }
}
