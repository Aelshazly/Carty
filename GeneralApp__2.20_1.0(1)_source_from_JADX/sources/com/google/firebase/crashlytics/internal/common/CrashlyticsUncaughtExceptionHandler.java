package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class CrashlyticsUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final SettingsDataProvider settingsDataProvider;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    interface CrashListener {
        void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener2, SettingsDataProvider settingsProvider, UncaughtExceptionHandler defaultHandler2) {
        this.crashListener = crashListener2;
        this.settingsDataProvider = settingsProvider;
        this.defaultHandler = defaultHandler2;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        this.isHandlingException.set(true);
        String str = "Crashlytics completed exception processing. Invoking default exception handler.";
        if (thread == null) {
            try {
                Logger.getLogger().mo19681e("Could not handle uncaught exception; null thread");
            } catch (Exception e) {
                Logger.getLogger().mo19682e("An error occurred in the uncaught exception handler", e);
            } catch (Throwable th) {
                Logger.getLogger().mo19679d(str);
                this.defaultHandler.uncaughtException(thread, ex);
                this.isHandlingException.set(false);
                throw th;
            }
        } else if (ex == null) {
            Logger.getLogger().mo19681e("Could not handle uncaught exception; null throwable");
        } else {
            this.crashListener.onUncaughtException(this.settingsDataProvider, thread, ex);
        }
        Logger.getLogger().mo19679d(str);
        this.defaultHandler.uncaughtException(thread, ex);
        this.isHandlingException.set(false);
    }

    /* access modifiers changed from: 0000 */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
