package com.google.firebase.crashlytics.internal.settings.model;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface Settings {
    int getCacheDuration();

    long getExpiresAtMillis();

    FeaturesSettingsData getFeaturesData();

    SessionSettingsData getSessionData();

    int getSettingsVersion();

    boolean isExpired(long j);
}
