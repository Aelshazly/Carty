package com.google.android.datatransport.runtime.logging;

import android.util.Log;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public final class Logging {
    private Logging() {
    }

    private static String getTag(String tag) {
        StringBuilder sb = new StringBuilder();
        sb.append("TransportRuntime.");
        sb.append(tag);
        return sb.toString();
    }

    /* renamed from: d */
    public static void m13d(String tag, String message) {
        Log.d(getTag(tag), message);
    }

    /* renamed from: d */
    public static void m14d(String tag, String message, Object arg1) {
        Log.d(getTag(tag), String.format(message, new Object[]{arg1}));
    }

    /* renamed from: d */
    public static void m15d(String tag, String message, Object arg1, Object arg2) {
        Log.d(getTag(tag), String.format(message, new Object[]{arg1, arg2}));
    }

    /* renamed from: d */
    public static void m16d(String tag, String message, Object... args) {
        Log.d(getTag(tag), String.format(message, args));
    }

    /* renamed from: i */
    public static void m18i(String tag, String message) {
        Log.i(getTag(tag), message);
    }

    /* renamed from: e */
    public static void m17e(String tag, String message, Throwable e) {
        Log.e(getTag(tag), message, e);
    }

    /* renamed from: w */
    public static void m19w(String tag, String message, Object arg1) {
        Log.w(getTag(tag), String.format(message, new Object[]{arg1}));
    }
}
