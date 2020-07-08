package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SettingsJsonParser {
    private final CurrentTimeProvider currentTimeProvider;

    SettingsJsonParser(CurrentTimeProvider currentTimeProvider2) {
        this.currentTimeProvider = currentTimeProvider2;
    }

    public SettingsData parseSettingsJson(JSONObject settingsJson) throws JSONException {
        return getJsonTransformForVersion(settingsJson.getInt("settings_version")).buildFromJson(this.currentTimeProvider, settingsJson);
    }

    private static SettingsJsonTransform getJsonTransformForVersion(int settingsVersion) {
        if (settingsVersion != 3) {
            return new DefaultSettingsJsonTransform();
        }
        return new SettingsV3JsonTransform();
    }
}
