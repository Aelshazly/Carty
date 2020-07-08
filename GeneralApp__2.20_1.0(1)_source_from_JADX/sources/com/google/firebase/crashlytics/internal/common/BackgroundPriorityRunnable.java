package com.google.firebase.crashlytics.internal.common;

import android.os.Process;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public abstract class BackgroundPriorityRunnable implements Runnable {
    /* access modifiers changed from: protected */
    public abstract void onRun();

    public final void run() {
        Process.setThreadPriority(10);
        onRun();
    }
}
