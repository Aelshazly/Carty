package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class zzan {
    private static UserManager zzfg;
    private static volatile boolean zzfh = (!zzs());
    private static boolean zzfi = false;

    private zzan() {
    }

    public static boolean zzs() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzs() || zzd(context);
    }

    private static boolean zzc(Context context) {
        boolean z;
        int i = 1;
        while (true) {
            z = false;
            if (i > 2) {
                break;
            }
            if (zzfg == null) {
                zzfg = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zzfg;
            if (userManager == null) {
                return true;
            }
            try {
                if (userManager.isUserUnlocked() || !userManager.isUserRunning(Process.myUserHandle())) {
                    z = true;
                }
            } catch (NullPointerException e) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                zzfg = null;
                i++;
            }
        }
        if (z) {
            zzfg = null;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzd(android.content.Context r3) {
        /*
            boolean r0 = zzfh
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Class<com.google.android.gms.internal.vision.zzan> r0 = com.google.android.gms.internal.vision.zzan.class
            monitor-enter(r0)
            boolean r2 = zzfh     // Catch:{ all -> 0x001a }
            if (r2 == 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r1
        L_0x000f:
            boolean r3 = zzc(r3)     // Catch:{ all -> 0x001a }
            if (r3 == 0) goto L_0x0018
            zzfh = r3     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r3
        L_0x001a:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzan.zzd(android.content.Context):boolean");
    }
}
