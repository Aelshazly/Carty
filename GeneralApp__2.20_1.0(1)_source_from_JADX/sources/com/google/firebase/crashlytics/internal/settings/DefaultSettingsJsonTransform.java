package com.google.firebase.crashlytics.internal.settings;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.FeaturesSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SessionSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject json) throws JSONException {
        JSONObject jSONObject = json;
        int settingsVersion = jSONObject.optInt("settings_version", 0);
        int cacheDuration = jSONObject.optInt("cache_duration", 3600);
        SettingsData settingsData = new SettingsData(getExpiresAtFrom(currentTimeProvider, (long) cacheDuration, jSONObject), buildAppDataFrom(jSONObject.getJSONObject("app")), buildSessionDataFrom(jSONObject.getJSONObject("session")), buildFeaturesSessionDataFrom(jSONObject.getJSONObject("features")), settingsVersion, cacheDuration);
        return settingsData;
    }

    static Settings defaultSettings(CurrentTimeProvider currentTimeProvider) {
        JSONObject empty = new JSONObject();
        SettingsData settingsData = new SettingsData(getExpiresAtFrom(currentTimeProvider, 3600, empty), null, buildSessionDataFrom(empty), buildFeaturesSessionDataFrom(empty), 0, 3600);
        return settingsData;
    }

    public JSONObject toJson(SettingsData settingsData) throws JSONException {
        return new JSONObject().put("expires_at", settingsData.expiresAtMillis).put("cache_duration", settingsData.cacheDuration).put("settings_version", settingsData.settingsVersion).put("features", toFeaturesJson(settingsData.featuresData)).put("app", toAppJson(settingsData.appData)).put("session", toSessionJson(settingsData.sessionData));
    }

    private static AppSettingsData buildAppDataFrom(JSONObject json) throws JSONException {
        AppSettingsData appSettingsData = new AppSettingsData(json.getString(NotificationCompat.CATEGORY_STATUS), json.getString(ImagesContract.URL), json.getString("reports_url"), json.getString("ndk_reports_url"), json.optBoolean("update_required", false));
        return appSettingsData;
    }

    private static FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject json) {
        return new FeaturesSettingsData(json.optBoolean("collect_reports", true));
    }

    private static SessionSettingsData buildSessionDataFrom(JSONObject json) {
        return new SessionSettingsData(json.optInt("max_custom_exception_events", 8), 4);
    }

    private static long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long cacheDurationSeconds, JSONObject json) {
        String str = "expires_at";
        if (json.has(str)) {
            return json.optLong(str);
        }
        return currentTimeProvider.getCurrentTimeMillis() + (1000 * cacheDurationSeconds);
    }

    private JSONObject toAppJson(AppSettingsData appData) throws JSONException {
        return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, appData.status).put(ImagesContract.URL, appData.url).put("reports_url", appData.reportsUrl).put("ndk_reports_url", appData.ndkReportsUrl).put("update_required", appData.updateRequired);
    }

    private JSONObject toFeaturesJson(FeaturesSettingsData features) throws JSONException {
        return new JSONObject().put("collect_reports", features.collectReports);
    }

    private JSONObject toSessionJson(SessionSettingsData data) throws JSONException {
        return new JSONObject().put("max_custom_exception_events", data.maxCustomExceptionEvents).put("max_complete_sessions_count", data.maxCompleteSessionsCount);
    }
}
