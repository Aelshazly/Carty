package com.google.firebase.crashlytics.internal.common;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
interface CrashlyticsLifecycleEvents {
    void onBeginSession(String str, long j);

    void onCustomKey(String str, String str2);

    void onEndSession();

    void onLog(long j, String str);

    void onUserId(String str);
}
