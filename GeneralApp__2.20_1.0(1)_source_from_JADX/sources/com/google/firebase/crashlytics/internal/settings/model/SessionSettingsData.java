package com.google.firebase.crashlytics.internal.settings.model;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SessionSettingsData {
    public final int maxCompleteSessionsCount;
    public final int maxCustomExceptionEvents;

    public SessionSettingsData(int maxCustomExceptionEvents2, int maxCompleteSessionsCount2) {
        this.maxCustomExceptionEvents = maxCustomExceptionEvents2;
        this.maxCompleteSessionsCount = maxCompleteSessionsCount2;
    }
}
