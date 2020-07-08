package com.google.firebase.crashlytics.internal;

import android.util.Log;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class Logger {
    static final Logger DEFAULT_LOGGER = new Logger(TAG);
    public static final String TAG = "FirebaseCrashlytics";
    private int logLevel = 4;
    private final String tag;

    public Logger(String tag2) {
        this.tag = tag2;
    }

    public static Logger getLogger() {
        return DEFAULT_LOGGER;
    }

    private boolean canLog(int level) {
        return this.logLevel <= level || Log.isLoggable(this.tag, level);
    }

    /* renamed from: d */
    public void mo19680d(String text, Throwable throwable) {
        if (canLog(3)) {
            Log.d(this.tag, text, throwable);
        }
    }

    /* renamed from: v */
    public void mo19688v(String text, Throwable throwable) {
        if (canLog(2)) {
            Log.v(this.tag, text, throwable);
        }
    }

    /* renamed from: i */
    public void mo19684i(String text, Throwable throwable) {
        if (canLog(4)) {
            Log.i(this.tag, text, throwable);
        }
    }

    /* renamed from: w */
    public void mo19690w(String text, Throwable throwable) {
        if (canLog(5)) {
            Log.w(this.tag, text, throwable);
        }
    }

    /* renamed from: e */
    public void mo19682e(String text, Throwable throwable) {
        if (canLog(6)) {
            Log.e(this.tag, text, throwable);
        }
    }

    /* renamed from: d */
    public void mo19679d(String text) {
        mo19680d(text, null);
    }

    /* renamed from: v */
    public void mo19687v(String text) {
        mo19688v(text, null);
    }

    /* renamed from: i */
    public void mo19683i(String text) {
        mo19684i(text, null);
    }

    /* renamed from: w */
    public void mo19689w(String text) {
        mo19690w(text, null);
    }

    /* renamed from: e */
    public void mo19681e(String text) {
        mo19682e(text, null);
    }

    public void log(int priority, String msg) {
        log(priority, msg, false);
    }

    public void log(int priority, String msg, boolean forceLog) {
        if (forceLog || canLog(priority)) {
            Log.println(priority, this.tag, msg);
        }
    }
}
