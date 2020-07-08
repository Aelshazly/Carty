package com.google.firebase.crashlytics.internal.common;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SystemCurrentTimeProvider implements CurrentTimeProvider {
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
