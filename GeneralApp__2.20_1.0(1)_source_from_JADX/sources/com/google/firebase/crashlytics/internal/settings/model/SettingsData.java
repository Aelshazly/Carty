package com.google.firebase.crashlytics.internal.settings.model;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SettingsData implements Settings {
    public final AppSettingsData appData;
    public final int cacheDuration;
    public final long expiresAtMillis;
    public final FeaturesSettingsData featuresData;
    public final SessionSettingsData sessionData;
    public final int settingsVersion;

    public SettingsData(long expiresAtMillis2, AppSettingsData appData2, SessionSettingsData sessionData2, FeaturesSettingsData featuresData2, int settingsVersion2, int cacheDuration2) {
        this.expiresAtMillis = expiresAtMillis2;
        this.appData = appData2;
        this.sessionData = sessionData2;
        this.featuresData = featuresData2;
        this.settingsVersion = settingsVersion2;
        this.cacheDuration = cacheDuration2;
    }

    public AppSettingsData getAppSettingsData() {
        return this.appData;
    }

    public boolean isExpired(long currentTimeMillis) {
        return this.expiresAtMillis < currentTimeMillis;
    }

    public SessionSettingsData getSessionData() {
        return this.sessionData;
    }

    public FeaturesSettingsData getFeaturesData() {
        return this.featuresData;
    }

    public long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }

    public int getSettingsVersion() {
        return this.settingsVersion;
    }

    public int getCacheDuration() {
        return this.cacheDuration;
    }
}
