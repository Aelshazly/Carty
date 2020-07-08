package com.navibees.core.model.metadata.json;

import android.app.Application;
import androidx.collection.SimpleArrayMap;
import com.navibees.core.NaviBeesManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Venue {
    private static String JSON_KEY_BUILDINGS = "buildings";
    private static String JSON_KEY_CONFIGURATION = "config";
    private static String JSON_KEY_CREATED_AT = "created_at";
    private static String JSON_KEY_DEFAULT_ZOOM = "default_zoom";
    private static String JSON_KEY_GLOBAL_MODE = "global_mode";
    private static String JSON_KEY_ID = "id";
    private static String JSON_KEY_INITIAL_LOCATION = "initial_location";
    private static String JSON_KEY_MAP_PROVIDER = "map_provider";
    private static String JSON_KEY_MULTI_BUILDING_MODE = "multiple_building_mode";
    private static String JSON_KEY_NAME = "name";
    private static String JSON_KEY_OUTDOOR_REGION = "outdoor_region";
    private static String JSON_KEY_PROJECT_ID = "project_id";
    private static String JSON_KEY_PROJ_PARAMS = "proj4string";
    private static String JSON_KEY_PROPERTIES = "properties";
    private static String JSON_KEY_UPDATED_AT = "updated_at";
    private static String JSON_KEY_VENUE_CATEGORY_ID = "venue_category_id";
    public SimpleArrayMap<String, Building> buildings;
    public String createdAt;
    public int defaultZoom = 20;
    public boolean globalMode;

    /* renamed from: id */
    public String f1342id;
    public IndoorLocation initialLocation;
    public MapProvider mapProvider;
    public boolean multiBuildingMode;
    public Text name;
    public OutdoorRegion outdoorRegion;
    public String projParams;
    public String projectId;
    public HashMap<String, String> properties;
    public String updatedAt;
    public String venueCategoryId;

    public Venue(JSONObject jSONObject) throws JSONException {
        this.globalMode = false;
        this.multiBuildingMode = false;
        this.projParams = "";
        String str = " key with NON NULL value";
        String str2 = "Venue must contains ";
        if (!jSONObject.isNull(JSON_KEY_ID)) {
            this.f1342id = jSONObject.getString(JSON_KEY_ID);
            if (!jSONObject.isNull(JSON_KEY_NAME)) {
                this.name = new Text(jSONObject.getJSONObject(JSON_KEY_NAME));
                JSONArray jSONArray = jSONObject.getJSONArray(JSON_KEY_BUILDINGS);
                this.buildings = new SimpleArrayMap<>();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        Building building = new Building(jSONArray.getJSONObject(i));
                        this.buildings.put(building.f1329id, building);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!jSONObject.isNull(JSON_KEY_CREATED_AT)) {
                    this.createdAt = jSONObject.optString(JSON_KEY_CREATED_AT);
                }
                if (!jSONObject.isNull(JSON_KEY_PROJECT_ID)) {
                    this.projectId = jSONObject.optString(JSON_KEY_PROJECT_ID);
                }
                if (!jSONObject.isNull(JSON_KEY_UPDATED_AT)) {
                    this.updatedAt = jSONObject.optString(JSON_KEY_UPDATED_AT);
                }
                if (!jSONObject.isNull(JSON_KEY_VENUE_CATEGORY_ID)) {
                    this.venueCategoryId = jSONObject.optString(JSON_KEY_VENUE_CATEGORY_ID);
                }
                if (!jSONObject.isNull(JSON_KEY_PROPERTIES)) {
                    this.properties = new HashMap<>();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(JSON_KEY_PROPERTIES);
                    Iterator keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String str3 = (String) keys.next();
                        this.properties.put(str3, jSONObject2.getString(str3));
                    }
                }
                JSONObject jSONObject3 = jSONObject.getJSONObject(JSON_KEY_CONFIGURATION);
                this.mapProvider = new MapProvider(jSONObject3.getJSONObject(JSON_KEY_MAP_PROVIDER));
                if (!jSONObject3.isNull(JSON_KEY_INITIAL_LOCATION)) {
                    try {
                        this.initialLocation = new IndoorLocation(jSONObject3.getJSONObject(JSON_KEY_INITIAL_LOCATION));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (!jSONObject3.isNull(JSON_KEY_OUTDOOR_REGION)) {
                    try {
                        this.outdoorRegion = new OutdoorRegion(jSONObject3.getJSONObject(JSON_KEY_OUTDOOR_REGION));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (!jSONObject3.isNull(JSON_KEY_DEFAULT_ZOOM)) {
                    try {
                        this.defaultZoom = jSONObject3.getInt(JSON_KEY_DEFAULT_ZOOM);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                if (!jSONObject3.isNull(JSON_KEY_GLOBAL_MODE)) {
                    try {
                        this.globalMode = jSONObject3.getBoolean(JSON_KEY_GLOBAL_MODE);
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                if (!jSONObject3.isNull(JSON_KEY_MULTI_BUILDING_MODE)) {
                    try {
                        this.multiBuildingMode = jSONObject3.getBoolean(JSON_KEY_MULTI_BUILDING_MODE);
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                if (!jSONObject3.isNull(JSON_KEY_PROJ_PARAMS)) {
                    this.projParams = jSONObject3.getString(JSON_KEY_PROJ_PARAMS);
                    return;
                }
                throw new JSONException("Venue must contain proj4string");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(JSON_KEY_NAME);
            sb.append(str);
            throw new JSONException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(JSON_KEY_ID);
        sb2.append(str);
        throw new JSONException(sb2.toString());
    }

    public List<POICategory> getCategories(Application application) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.buildings.size(); i++) {
            List<POICategory> list = ((Building) this.buildings.valueAt(i)).categories;
            if (list != null) {
                for (POICategory pOICategory : list) {
                    hashSet.add(pOICategory.f1340id);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        HashMap<String, POICategory> hashMap = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.categories;
        if (hashMap == null) {
            return arrayList;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (hashMap.get(str) != null) {
                arrayList.add(hashMap.get(str));
            }
        }
        return arrayList;
    }

    public List<Facility> getFacilities() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.buildings.size(); i++) {
            List<Facility> list = ((Building) this.buildings.valueAt(i)).facilities;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public List<Facility> getFacilitiesOfCategory(String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.buildings.size(); i++) {
            List facilitiesOfCategory = ((Building) this.buildings.valueAt(i)).getFacilitiesOfCategory(str);
            if (facilitiesOfCategory != null) {
                arrayList.addAll(facilitiesOfCategory);
            }
        }
        return arrayList;
    }

    public List<POI> getPOIs() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.buildings.size(); i++) {
            List<POI> list = ((Building) this.buildings.valueAt(i)).pois;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public List<POI> getPOIsOfCategory(String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.buildings.size(); i++) {
            List pOIsOfCategory = ((Building) this.buildings.valueAt(i)).getPOIsOfCategory(str);
            if (pOIsOfCategory != null) {
                arrayList.addAll(pOIsOfCategory);
            }
        }
        return arrayList;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1342id);
            jSONObject.put(JSON_KEY_NAME, this.name.toJson());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.buildings.size(); i++) {
                JSONObject metadataToJson = ((Building) this.buildings.valueAt(i)).metadataToJson();
                if (metadataToJson != null) {
                    jSONArray.put(metadataToJson);
                }
            }
            jSONObject.put(JSON_KEY_BUILDINGS, jSONArray);
            if (this.createdAt != null) {
                jSONObject.put(JSON_KEY_CREATED_AT, this.createdAt);
            }
            if (this.projectId != null) {
                jSONObject.put(JSON_KEY_PROJECT_ID, this.projectId);
            }
            if (this.updatedAt != null) {
                jSONObject.put(JSON_KEY_UPDATED_AT, this.updatedAt);
            }
            if (this.venueCategoryId != null) {
                jSONObject.put(JSON_KEY_VENUE_CATEGORY_ID, this.venueCategoryId);
            }
            if (this.properties != null) {
                jSONObject.put(JSON_KEY_PROPERTIES, new JSONObject(this.properties));
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(JSON_KEY_MAP_PROVIDER, this.mapProvider.toJson());
            jSONObject2.put(JSON_KEY_DEFAULT_ZOOM, this.defaultZoom);
            if (this.initialLocation != null) {
                jSONObject2.put(JSON_KEY_INITIAL_LOCATION, this.initialLocation.toJson());
            }
            if (this.outdoorRegion != null) {
                jSONObject2.put(JSON_KEY_OUTDOOR_REGION, this.outdoorRegion.toJson());
            }
            jSONObject2.put(JSON_KEY_GLOBAL_MODE, this.globalMode);
            jSONObject2.put(JSON_KEY_MULTI_BUILDING_MODE, this.multiBuildingMode);
            jSONObject2.put(JSON_KEY_PROJ_PARAMS, this.projParams);
            jSONObject.put(JSON_KEY_CONFIGURATION, jSONObject2);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
