package com.loopj.android.http;

import android.os.Build.VERSION;
import android.util.Log;

public class LogHandler implements LogInterface {
    boolean mLoggingEnabled = true;
    int mLoggingLevel = 2;

    public boolean isLoggingEnabled() {
        return this.mLoggingEnabled;
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        this.mLoggingEnabled = loggingEnabled;
    }

    public int getLoggingLevel() {
        return this.mLoggingLevel;
    }

    public void setLoggingLevel(int loggingLevel) {
        this.mLoggingLevel = loggingLevel;
    }

    public boolean shouldLog(int logLevel) {
        return logLevel >= this.mLoggingLevel;
    }

    public void log(int logLevel, String tag, String msg) {
        logWithThrowable(logLevel, tag, msg, null);
    }

    public void logWithThrowable(int logLevel, String tag, String msg, Throwable t) {
        if (isLoggingEnabled() && shouldLog(logLevel)) {
            if (logLevel == 2) {
                Log.v(tag, msg, t);
            } else if (logLevel == 3) {
                Log.d(tag, msg, t);
            } else if (logLevel == 4) {
                Log.i(tag, msg, t);
            } else if (logLevel == 5) {
                Log.w(tag, msg, t);
            } else if (logLevel == 6) {
                Log.e(tag, msg, t);
            } else if (logLevel == 8) {
                if (Integer.valueOf(VERSION.SDK).intValue() > 8) {
                    checkedWtf(tag, msg, t);
                } else {
                    Log.e(tag, msg, t);
                }
            }
        }
    }

    private void checkedWtf(String tag, String msg, Throwable t) {
        Log.wtf(tag, msg, t);
    }

    /* renamed from: v */
    public void mo22460v(String tag, String msg) {
        log(2, tag, msg);
    }

    /* renamed from: v */
    public void mo22461v(String tag, String msg, Throwable t) {
        logWithThrowable(2, tag, msg, t);
    }

    /* renamed from: d */
    public void mo22447d(String tag, String msg) {
        log(2, tag, msg);
    }

    /* renamed from: d */
    public void mo22448d(String tag, String msg, Throwable t) {
        logWithThrowable(3, tag, msg, t);
    }

    /* renamed from: i */
    public void mo22452i(String tag, String msg) {
        log(4, tag, msg);
    }

    /* renamed from: i */
    public void mo22453i(String tag, String msg, Throwable t) {
        logWithThrowable(4, tag, msg, t);
    }

    /* renamed from: w */
    public void mo22462w(String tag, String msg) {
        log(5, tag, msg);
    }

    /* renamed from: w */
    public void mo22463w(String tag, String msg, Throwable t) {
        logWithThrowable(5, tag, msg, t);
    }

    /* renamed from: e */
    public void mo22449e(String tag, String msg) {
        log(6, tag, msg);
    }

    /* renamed from: e */
    public void mo22450e(String tag, String msg, Throwable t) {
        logWithThrowable(6, tag, msg, t);
    }

    public void wtf(String tag, String msg) {
        log(8, tag, msg);
    }

    public void wtf(String tag, String msg, Throwable t) {
        logWithThrowable(8, tag, msg, t);
    }
}
