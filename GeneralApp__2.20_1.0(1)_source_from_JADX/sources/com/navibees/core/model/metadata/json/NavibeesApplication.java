package com.navibees.core.model.metadata.json;

import android.content.Context;
import androidx.collection.SimpleArrayMap;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NavibeesApplication {
    private static String JSON_KEY_AUTHENTICATION_TOKEN = "authentication_token";
    private static final String JSON_KEY_BRANDS = "brands";
    private static final String JSON_KEY_CATEGORIES = "poi_categories";
    private static String JSON_KEY_CLIENT_ID = "client_id";
    private static String JSON_KEY_CONFIG = "config";
    private static String JSON_KEY_CREDENTIALS = "credentials";
    private static String JSON_KEY_ID = "id";
    private static String JSON_KEY_IDENTIFIER = "identifier";
    private static String JSON_KEY_NAME = "name";
    private static String JSON_KEY_NAVIBEES_APPLICATION = "navibees_application";
    private static String JSON_KEY_PROJECT_ID = "project_id";
    private static String JSON_KEY_PROPERTIES = "properties";
    private static final String JSON_KEY_REGIONS = "regions";
    private static String JSON_KEY_UPDATED_AT = "updated_at";
    private static String JSON_KEY_VENUES = "venues";
    public ApplicationConfiguration appConfiguration;
    public String authenticationToken;
    public HashMap<String, Brand> brands;
    public HashMap<String, POICategory> categories;
    public String clientId;

    /* renamed from: id */
    public String f1338id;
    public String identifier;
    public Text name;
    public String projectId;
    public JSONObject properties;
    public HashMap<String, MonitoredRegion> regions;
    public String updatedAt;
    public SimpleArrayMap<String, Venue> venues;

    public NavibeesApplication(JSONObject jSONObject, Context context) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(JSON_KEY_NAVIBEES_APPLICATION);
        this.f1338id = jSONObject2.getString(JSON_KEY_ID);
        this.identifier = jSONObject2.getString(JSON_KEY_IDENTIFIER);
        this.clientId = jSONObject2.getJSONObject(JSON_KEY_CREDENTIALS).getString(JSON_KEY_CLIENT_ID);
        this.authenticationToken = jSONObject2.getJSONObject(JSON_KEY_CREDENTIALS).getString(JSON_KEY_AUTHENTICATION_TOKEN);
        this.appConfiguration = new ApplicationConfiguration(jSONObject2.getJSONObject(JSON_KEY_CONFIG));
        if (!jSONObject2.isNull(JSON_KEY_NAME)) {
            try {
                this.name = new Text(jSONObject2.optJSONObject(JSON_KEY_NAME));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!jSONObject2.isNull(JSON_KEY_VENUES)) {
            JSONArray jSONArray = jSONObject2.getJSONArray(JSON_KEY_VENUES);
            this.venues = new SimpleArrayMap<>();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    Venue venue = new Venue(jSONArray.getJSONObject(i));
                    this.venues.put(venue.f1342id, venue);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (!jSONObject2.isNull(JSON_KEY_UPDATED_AT)) {
            this.updatedAt = jSONObject2.optString(JSON_KEY_UPDATED_AT);
        }
        String str = JSON_KEY_REGIONS;
        if (!jSONObject2.isNull(str)) {
            JSONArray optJSONArray = jSONObject2.optJSONArray(str);
            this.regions = new HashMap<>();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    MonitoredRegion monitoredRegion = new MonitoredRegion(optJSONArray.getJSONObject(i2), context);
                    this.regions.put(monitoredRegion.identifier, monitoredRegion);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        String str2 = JSON_KEY_BRANDS;
        if (!jSONObject2.isNull(str2)) {
            JSONArray optJSONArray2 = jSONObject2.optJSONArray(str2);
            this.brands = new HashMap<>();
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                try {
                    Brand brand = new Brand(optJSONArray2.getJSONObject(i3));
                    this.brands.put(brand.brandId, brand);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
        String str3 = JSON_KEY_CATEGORIES;
        if (!jSONObject2.isNull(str3)) {
            JSONArray optJSONArray3 = jSONObject2.optJSONArray(str3);
            this.categories = new HashMap<>();
            for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                try {
                    POICategory pOICategory = new POICategory(optJSONArray3.getJSONObject(i4));
                    this.categories.put(pOICategory.f1340id, pOICategory);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
        if (!jSONObject2.isNull(JSON_KEY_PROJECT_ID)) {
            this.projectId = jSONObject2.optString(JSON_KEY_PROJECT_ID);
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put(JSON_KEY_NAVIBEES_APPLICATION, jSONObject2);
            jSONObject2.put(JSON_KEY_ID, this.f1338id);
            jSONObject2.put(JSON_KEY_IDENTIFIER, this.identifier);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(JSON_KEY_CLIENT_ID, this.clientId);
            jSONObject3.put(JSON_KEY_AUTHENTICATION_TOKEN, this.authenticationToken);
            jSONObject2.put(JSON_KEY_CREDENTIALS, jSONObject3);
            jSONObject2.put(JSON_KEY_CONFIG, this.appConfiguration.toJson());
            if (this.name != null) {
                jSONObject2.put(JSON_KEY_NAME, this.name.toJson());
            }
            if (this.venues != null && this.venues.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < this.venues.size(); i++) {
                    JSONObject json = ((Venue) this.venues.valueAt(i)).toJson();
                    if (json != null) {
                        jSONArray.put(json);
                    }
                }
                jSONObject2.put(JSON_KEY_VENUES, jSONArray);
            }
            if (this.updatedAt != null) {
                jSONObject2.put(JSON_KEY_UPDATED_AT, this.updatedAt);
            }
            if (this.regions != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (MonitoredRegion json2 : this.regions.values()) {
                    JSONObject json3 = json2.toJson();
                    if (json3 != null) {
                        jSONArray2.put(json3);
                    }
                }
                jSONObject2.put(JSON_KEY_REGIONS, jSONArray2);
            }
            if (this.brands != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (Brand json4 : this.brands.values()) {
                    JSONObject json5 = json4.toJson();
                    if (json5 != null) {
                        jSONArray3.put(json5);
                    }
                }
                jSONObject2.put(JSON_KEY_BRANDS, jSONArray3);
            }
            if (this.categories != null) {
                JSONArray jSONArray4 = new JSONArray();
                for (POICategory json6 : this.categories.values()) {
                    JSONObject json7 = json6.toJson();
                    if (json7 != null) {
                        jSONArray4.put(json7);
                    }
                }
                jSONObject2.put(JSON_KEY_CATEGORIES, jSONArray4);
            }
            if (this.projectId != null) {
                jSONObject2.put(JSON_KEY_PROJECT_ID, this.projectId);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
