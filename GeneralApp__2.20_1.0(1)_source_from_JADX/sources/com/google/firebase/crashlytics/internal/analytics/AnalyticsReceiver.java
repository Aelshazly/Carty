package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface AnalyticsReceiver {

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public interface CrashlyticsOriginEventListener {
        void onCrashlyticsOriginEvent(int i, Bundle bundle);
    }

    CrashlyticsOriginEventListener getCrashlyticsOriginEventListener();

    boolean register();

    void setCrashlyticsOriginEventListener(CrashlyticsOriginEventListener crashlyticsOriginEventListener);

    void unregister();
}
