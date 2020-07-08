package com.google.android.gms.vision;

import android.util.Log;

/* renamed from: com.google.android.gms.vision.L */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class C0548L {
    public static final String TAG = "Vision";

    /* renamed from: v */
    public static int m66v(String str, Object... objArr) {
        String str2 = TAG;
        if (Log.isLoggable(str2, 2)) {
            return Log.v(str2, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: d */
    public static int m61d(String str, Object... objArr) {
        String str2 = TAG;
        if (Log.isLoggable(str2, 3)) {
            return Log.d(str2, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: d */
    public static int m62d(Throwable th, String str, Object... objArr) {
        String str2 = TAG;
        if (Log.isLoggable(str2, 3)) {
            return Log.d(str2, String.format(str, objArr), th);
        }
        return 0;
    }

    /* renamed from: i */
    public static int m65i(String str, Object... objArr) {
        String str2 = TAG;
        if (Log.isLoggable(str2, 4)) {
            return Log.i(str2, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: e */
    public static int m63e(String str, Object... objArr) {
        String str2 = TAG;
        if (Log.isLoggable(str2, 6)) {
            return Log.e(str2, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: e */
    public static int m64e(Throwable th, String str, Object... objArr) {
        String str2 = TAG;
        if (!Log.isLoggable(str2, 6)) {
            return 0;
        }
        if (Log.isLoggable(str2, 3)) {
            return Log.e(str2, String.format(str, objArr), th);
        }
        String format = String.format(str, objArr);
        String valueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length());
        sb.append(format);
        sb.append(": ");
        sb.append(valueOf);
        return Log.e(str2, sb.toString());
    }

    /* renamed from: w */
    public static int m67w(String str, Object... objArr) {
        String str2 = TAG;
        if (Log.isLoggable(str2, 5)) {
            return Log.w(str2, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: w */
    public static int m68w(Throwable th, String str, Object... objArr) {
        String str2 = TAG;
        if (!Log.isLoggable(str2, 5)) {
            return 0;
        }
        if (Log.isLoggable(str2, 3)) {
            return Log.w(str2, String.format(str, objArr), th);
        }
        String format = String.format(str, objArr);
        String valueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length());
        sb.append(format);
        sb.append(": ");
        sb.append(valueOf);
        return Log.w(str2, sb.toString());
    }
}
