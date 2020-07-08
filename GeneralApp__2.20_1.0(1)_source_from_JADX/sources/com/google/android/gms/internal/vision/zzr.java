package com.google.android.gms.internal.vision;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule.VersionPolicy;
import com.google.android.gms.vision.C0548L;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzr {
    public static boolean zza(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str) > DynamiteModule.getRemoteVersion(context, "com.google.android.gms.vision.dynamite");
    }

    public static DynamiteModule zza(Context context, String str, boolean z) {
        VersionPolicy versionPolicy;
        String format = String.format("%s.%s", new Object[]{"com.google.android.gms.vision", str});
        if (!z) {
            format = "com.google.android.gms.vision.dynamite";
        }
        try {
            C0548L.m61d("Loading module %s", format);
            if (z) {
                versionPolicy = DynamiteModule.PREFER_REMOTE;
            } else {
                versionPolicy = DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;
            }
            return DynamiteModule.load(context, versionPolicy, format);
        } catch (LoadingException e) {
            C0548L.m64e(e, "Error loading module %s optional module %b", format, Boolean.valueOf(z));
            return null;
        }
    }
}
