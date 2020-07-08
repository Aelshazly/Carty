package com.google.firebase.crashlytics.internal.settings;

import androidx.core.app.NotificationCompat;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.FeaturesSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SessionSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class SettingsV3JsonTransform implements SettingsJsonTransform {
    private static final String CRASHLYTICS_APP_URL = "https://update.crashlytics.com/spi/v1/platforms/android/apps";
    private static final String CRASHLYTICS_APP_URL_FORMAT = "https://update.crashlytics.com/spi/v1/platforms/android/apps/%s";
    private static final String NDK_REPORTS_URL_FORMAT = "https://reports.crashlytics.com/sdk-api/v1/platforms/android/apps/%s/minidumps";
    private static final String REPORTS_URL_FORMAT = "https://reports.crashlytics.com/spi/v1/platforms/android/apps/%s/reports";

    SettingsV3JsonTransform() {
    }

    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject json) throws JSONException {
        JSONObject jSONObject = json;
        int settingsVersion = jSONObject.optInt("settings_version", 0);
        int cacheDuration = jSONObject.optInt("cache_duration", 3600);
        SettingsData settingsData = new SettingsData(getExpiresAtFrom(currentTimeProvider, (long) cacheDuration, jSONObject), buildAppDataFrom(jSONObject.getJSONObject("fabric"), jSONObject.getJSONObject("app")), defaultSessionData(), buildFeaturesSessionDataFrom(jSONObject.getJSONObject("features")), settingsVersion, cacheDuration);
        return settingsData;
    }

    public JSONObject toJson(SettingsData settingsData) throws JSONException {
        return new JSONObject().put("expires_at", settingsData.expiresAtMillis).put("cache_duration", settingsData.cacheDuration).put("settings_version", settingsData.settingsVersion).put("features", toFeaturesJson(settingsData.featuresData)).put("app", toAppJson(settingsData.appData)).put("fabric", toFabricJson(settingsData.appData));
    }

    private static AppSettingsData buildAppDataFrom(JSONObject fabricJson, JSONObject appJson) throws JSONException {
        String url;
        JSONObject jSONObject = fabricJson;
        JSONObject jSONObject2 = appJson;
        String status = jSONObject2.getString(NotificationCompat.CATEGORY_STATUS);
        boolean isNewApp = AppSettingsData.STATUS_NEW.equals(status);
        String bundleId = jSONObject.getString("bundle_id");
        String organizationId = jSONObject.getString(AbstractAppSpiCall.ORGANIZATION_ID_PARAM);
        if (isNewApp) {
            url = CRASHLYTICS_APP_URL;
        } else {
            url = String.format(Locale.US, CRASHLYTICS_APP_URL_FORMAT, new Object[]{bundleId});
        }
        AppSettingsData appSettingsData = new AppSettingsData(status, url, String.format(Locale.US, REPORTS_URL_FORMAT, new Object[]{bundleId}), String.format(Locale.US, NDK_REPORTS_URL_FORMAT, new Object[]{bundleId}), bundleId, organizationId, jSONObject2.optBoolean("update_required", false), jSONObject2.optInt("report_upload_variant", 0), jSONObject2.optInt("native_report_upload_variant", 0));
        return appSettingsData;
    }

    private static FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject json) {
        return new FeaturesSettingsData(json.optBoolean("collect_reports", true));
    }

    private static SessionSettingsData defaultSessionData() {
        return new SessionSettingsData(8, 4);
    }

    private JSONObject toFabricJson(AppSettingsData appData) throws JSONException {
        return new JSONObject().put("bundle_id", appData.bundleId).put(AbstractAppSpiCall.ORGANIZATION_ID_PARAM, appData.organizationId);
    }

    private JSONObject toAppJson(AppSettingsData appData) throws JSONException {
        return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, appData.status).put("update_required", appData.updateRequired).put("report_upload_variant", appData.reportUploadVariant).put("native_report_upload_variant", appData.nativeReportUploadVariant);
    }

    private JSONObject toFeaturesJson(FeaturesSettingsData features) throws JSONException {
        return new JSONObject().put("collect_reports", features.collectReports);
    }

    private static long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long cacheDurationSeconds, JSONObject json) {
        String str = "expires_at";
        if (json.has(str)) {
            return json.optLong(str);
        }
        return currentTimeProvider.getCurrentTimeMillis() + (1000 * cacheDurationSeconds);
    }
}
