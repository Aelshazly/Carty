package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.HttpStatus;

public class ApplicationConfiguration {
    private static String JSON_KEY_ACCESS_TOKEN = "access_token";
    private static String JSON_KEY_BEACON_UUID = "beacon_UUID";
    private static String JSON_KEY_LICENSE_DURATION = "license_duration";
    private static String JSON_KEY_LICENSE_FEATURE_STR = "license_feature_str";
    private static String JSON_KEY_LICENSE_START_DATE = "license_start_date";
    public String accessToken;
    public String[] beaconsUUIDList;

    /* renamed from: id */
    public String f1327id;
    public int licenseDuration;
    public int licenseFeatureStr;
    public String licenseStartDate;
    public int minPOIVisibleZoomLevel;
    public boolean outdoorEnabled = false;
    public boolean trackingEnabledByDefault;

    public ApplicationConfiguration(JSONObject jSONObject) throws JSONException {
        if (jSONObject.isNull(JSON_KEY_BEACON_UUID) || jSONObject.isNull(JSON_KEY_ACCESS_TOKEN) || jSONObject.isNull(JSON_KEY_LICENSE_DURATION) || jSONObject.isNull(JSON_KEY_LICENSE_START_DATE) || jSONObject.isNull(JSON_KEY_LICENSE_FEATURE_STR)) {
            StringBuilder sb = new StringBuilder();
            sb.append("ApplicationConfiguration must contains ");
            sb.append(JSON_KEY_BEACON_UUID);
            String str = ", ";
            sb.append(str);
            sb.append(JSON_KEY_ACCESS_TOKEN);
            sb.append(str);
            sb.append(JSON_KEY_LICENSE_DURATION);
            sb.append(JSON_KEY_LICENSE_START_DATE);
            sb.append(str);
            sb.append(JSON_KEY_LICENSE_FEATURE_STR);
            sb.append(",  keys with NON NULL values");
            throw new JSONException(sb.toString());
        }
        String string = jSONObject.getString(JSON_KEY_BEACON_UUID);
        if (string != null) {
            this.beaconsUUIDList = string.toUpperCase().replace(" ", "").split(",");
        }
        this.accessToken = jSONObject.getString(JSON_KEY_ACCESS_TOKEN);
        this.licenseDuration = jSONObject.getInt(JSON_KEY_LICENSE_DURATION);
        this.licenseStartDate = jSONObject.getString(JSON_KEY_LICENSE_START_DATE);
        this.licenseFeatureStr = jSONObject.getInt(JSON_KEY_LICENSE_FEATURE_STR);
        String str2 = "minimum_poi_visible_zoom_level";
        if (!jSONObject.isNull(str2)) {
            this.minPOIVisibleZoomLevel = jSONObject.optInt(str2, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        String str3 = "tracking_enable_by_default";
        if (!jSONObject.isNull(str3)) {
            this.trackingEnabledByDefault = jSONObject.optBoolean(str3, true);
        }
        String str4 = "outdoor_enabled";
        if (!jSONObject.isNull(str4)) {
            this.outdoorEnabled = jSONObject.optBoolean(str4);
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.beaconsUUIDList != null) {
                String str = "";
                for (int i = 0; i < this.beaconsUUIDList.length - 1; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(this.beaconsUUIDList[i]);
                    sb.append(",");
                    str = sb.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(this.beaconsUUIDList[this.beaconsUUIDList.length - 1]);
                jSONObject.put(JSON_KEY_BEACON_UUID, sb2.toString());
            }
            jSONObject.put(JSON_KEY_ACCESS_TOKEN, this.accessToken);
            jSONObject.put(JSON_KEY_LICENSE_DURATION, this.licenseDuration);
            jSONObject.put(JSON_KEY_LICENSE_START_DATE, this.licenseStartDate);
            jSONObject.put(JSON_KEY_LICENSE_FEATURE_STR, this.licenseFeatureStr);
            jSONObject.put("minimum_poi_visible_zoom_level", this.minPOIVisibleZoomLevel);
            jSONObject.put("tracking_enable_by_default", this.trackingEnabledByDefault);
            jSONObject.put("outdoor_enabled", this.outdoorEnabled);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
