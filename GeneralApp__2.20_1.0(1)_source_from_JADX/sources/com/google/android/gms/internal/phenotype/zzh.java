package com.google.android.gms.internal.phenotype;

import android.content.Context;
import android.os.Build.VERSION;

public final class zzh<T> {
    private static final Object zzak = new Object();
    private static Context zzal = null;
    private static boolean zzam = false;
    private static volatile Boolean zzan = null;
    private static volatile Boolean zzbq = null;

    public static void init(Context context) {
        synchronized (zzak) {
            if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (zzal != context) {
                zzan = null;
            }
            zzal = context;
        }
        zzam = false;
    }

    public static void maybeInit(Context context) {
        if (zzal == null) {
            init(context);
        }
    }
}