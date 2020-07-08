package com.navibees.core.model.metadata.json;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import androidx.collection.SimpleArrayMap;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.util.AssetsManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Building {
    private static String JSON_KEY_ACTIVITY_GROUPS = "activity_groups";
    private static String JSON_KEY_BEACONS = "beacons";
    private static String JSON_KEY_CREATED_AT = "created_at";
    private static String JSON_KEY_FACILITIES = "facilities";
    private static String JSON_KEY_FLOORS = "floors";
    private static String JSON_KEY_ID = "id";
    private static String JSON_KEY_NAME = "name";
    private static String JSON_KEY_OUTDOOR = "outdoor";
    private static String JSON_KEY_OUTDOOR_REGIONS = "outdoor_regions";
    private static String JSON_KEY_POIS = "pois";
    private static String JSON_KEY_PORTALS = "portals";
    private static String JSON_KEY_PROJECT_ID = "project_id";
    private static String JSON_KEY_PROPERTIES = "properties";
    private static String JSON_KEY_PROVIDER_ID = "provider_id";
    private static String JSON_KEY_RESTRICTIONS = "restrictions";
    private static String JSON_KEY_UPDATED_AT = "updated_at";
    private static String JSON_KEY_VENUE_ID = "venue_id";
    public List<ActivityGroup> activityGroups;
    public HashMap<String, BeaconNodeConfiguration> beacons;
    public List<POICategory> categories;
    private HashMap<String, List<Facility>> categoryFacilityMap;
    private HashMap<String, List<POI>> categoryPOIMap;
    public String createdAt;
    public List<Facility> facilities;
    public SimpleArrayMap<String, Facility> facilitiesDictionary;
    public List<Floor> floors;
    public SimpleArrayMap<String, Floor> floorsDictionary;

    /* renamed from: id */
    public String f1329id;
    private int mapProviderType;
    public Text name;
    public boolean outdoor;
    public List<OutdoorRegion> outdoorRegions;
    public List<POI> pois;
    public SimpleArrayMap<String, POI> poisDictionary;
    public List<Portal> portals;
    public SimpleArrayMap<String, Portal> portalsDictionary;
    public String projectId;
    public HashMap<String, String> properties;
    public String providerId;
    public SimpleArrayMap<Integer, List<IndoorLocationRestriction>> restrictions;
    public String updatedAt;
    public String venueId;

    public Building() {
    }

    private void generateCategoriesList(Application application) {
        this.categories = new ArrayList();
        this.categoryPOIMap = new HashMap<>();
        this.categoryFacilityMap = new HashMap<>();
        HashSet hashSet = new HashSet();
        HashMap<String, POICategory> hashMap = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.categories;
        if (hashMap != null) {
            List<POI> list = this.pois;
            if (list != null) {
                for (POI poi : list) {
                    String str = poi.categoryId;
                    if (str != null) {
                        POICategory pOICategory = (POICategory) hashMap.get(str);
                        if (pOICategory != null) {
                            if (!hashSet.contains(pOICategory.f1340id)) {
                                hashSet.add(pOICategory.f1340id);
                                this.categories.add(pOICategory);
                            }
                            if (this.categoryPOIMap.get(pOICategory.f1340id) == null) {
                                this.categoryPOIMap.put(pOICategory.f1340id, new ArrayList());
                            }
                            ((List) this.categoryPOIMap.get(pOICategory.f1340id)).add(poi);
                        }
                    }
                }
            }
            List<Facility> list2 = this.facilities;
            if (list2 != null) {
                for (Facility facility : list2) {
                    String str2 = facility.categoryId;
                    if (str2 != null) {
                        POICategory pOICategory2 = (POICategory) hashMap.get(str2);
                        if (pOICategory2 != null) {
                            if (!hashSet.contains(pOICategory2.f1340id)) {
                                hashSet.add(pOICategory2.f1340id);
                                this.categories.add(pOICategory2);
                            }
                            if (this.categoryFacilityMap.get(pOICategory2.f1340id) == null) {
                                this.categoryFacilityMap.put(pOICategory2.f1340id, new ArrayList());
                            }
                            ((List) this.categoryFacilityMap.get(pOICategory2.f1340id)).add(facility);
                        }
                    }
                }
            }
        }
    }

    private void loadActivities(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.activityGroups.add(new ActivityGroup(jSONArray.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadEsriFloors(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                EsriFloor esriFloor = new EsriFloor(jSONArray.getJSONObject(i));
                esriFloor.buildingId = this.f1329id;
                this.floors.add(esriFloor);
                this.floorsDictionary.put(esriFloor.f1331id, esriFloor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadEsriPois(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                EsriPOI esriPOI = new EsriPOI(jSONArray.getJSONObject(i));
                esriPOI.buildingId = this.f1329id;
                this.pois.add(esriPOI);
                this.poisDictionary.put(esriPOI.f1339id, esriPOI);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFacilities(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            Facility facility = null;
            try {
                if (this.mapProviderType == 0) {
                    facility = new EsriFacility(jSONArray.getJSONObject(i));
                } else if (this.mapProviderType == 1) {
                    facility = new VisioFacility(jSONArray.getJSONObject(i));
                }
                if (facility != null) {
                    facility.buildingId = this.f1329id;
                    for (POI poi : facility.getPois()) {
                        poi.buildingId = this.f1329id;
                    }
                    this.facilities.add(facility);
                    this.facilitiesDictionary.put(facility.f1330id, facility);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFloors(JSONArray jSONArray) {
        int i = this.mapProviderType;
        if (i == 0) {
            loadEsriFloors(jSONArray);
        } else if (i == 1) {
            loadVisioFloors(jSONArray);
        }
    }

    private void loadPois(JSONArray jSONArray) {
        int i = this.mapProviderType;
        if (i == 0) {
            loadEsriPois(jSONArray);
        } else if (i == 1) {
            loadVisioPois(jSONArray);
        }
    }

    private void loadRestrictions(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String string = jSONArray.getJSONObject(i).getString("type");
                IndoorLocationRestriction indoorLocationRestriction = null;
                if (string.equalsIgnoreCase("point")) {
                    indoorLocationRestriction = new PointIndoorLocationRestriction(jSONArray.getJSONObject(i));
                } else if (string.equalsIgnoreCase("line")) {
                    indoorLocationRestriction = new LineIndoorLocationRestriction(jSONArray.getJSONObject(i));
                } else if (string.equalsIgnoreCase("polygon")) {
                    indoorLocationRestriction = new PolygonIndoorLocationRestriction(jSONArray.getJSONObject(i));
                } else if (string.equalsIgnoreCase("circle")) {
                    indoorLocationRestriction = new CircleIndoorLocationRestriction(jSONArray.getJSONObject(i));
                }
                if (indoorLocationRestriction != null) {
                    if (this.restrictions.get(Integer.valueOf(indoorLocationRestriction.floor)) == null) {
                        this.restrictions.put(Integer.valueOf(indoorLocationRestriction.floor), new ArrayList());
                    }
                    ((List) this.restrictions.get(Integer.valueOf(indoorLocationRestriction.floor))).add(indoorLocationRestriction);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadTags(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                BeaconNodeConfiguration beaconNodeConfiguration = new BeaconNodeConfiguration(jSONArray.getJSONObject(i));
                beaconNodeConfiguration.buildingId = this.f1329id;
                this.beacons.put(beaconNodeConfiguration.generateKey(), beaconNodeConfiguration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadVisioFloors(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                VisioFloor visioFloor = new VisioFloor(jSONArray.getJSONObject(i));
                visioFloor.buildingId = this.f1329id;
                this.floors.add(visioFloor);
                this.floorsDictionary.put(visioFloor.f1331id, visioFloor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadVisioPois(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                VisioPOI visioPOI = new VisioPOI(jSONArray.getJSONObject(i));
                visioPOI.buildingId = this.f1329id;
                this.pois.add(visioPOI);
                this.poisDictionary.put(visioPOI.f1339id, visioPOI);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void copyBuildingData(Building building, Application application) {
        this.portalsDictionary = new SimpleArrayMap<>();
        this.portals = new ArrayList();
        this.outdoorRegions = new ArrayList();
        this.updatedAt = building.updatedAt;
        Venue venue = (Venue) NaviBeesManager.getInstance(application).getMetaDataManager().getVenues().get(this.venueId);
        if (venue != null) {
            this.mapProviderType = venue.mapProvider.type;
            this.floors = building.floors;
            this.floorsDictionary = building.floorsDictionary;
            this.poisDictionary = building.poisDictionary;
            this.pois = building.pois;
            this.facilities = building.facilities;
            this.facilitiesDictionary = building.facilitiesDictionary;
            this.beacons = building.beacons;
            this.restrictions = building.restrictions;
            this.activityGroups = building.activityGroups;
            this.categories = building.categories;
            this.categoryPOIMap = building.categoryPOIMap;
            this.categoryFacilityMap = building.categoryFacilityMap;
        }
    }

    public boolean getDidSyncAll(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        StringBuilder sb = new StringBuilder();
        sb.append(NaviBeesConstants.BUILDING_SYNC_ALL_PREFIX);
        sb.append(this.f1329id);
        return defaultSharedPreferences.getBoolean(sb.toString(), false);
    }

    public List<Facility> getFacilitiesOfCategory(String str) {
        HashMap<String, List<Facility>> hashMap = this.categoryFacilityMap;
        if (hashMap == null) {
            return null;
        }
        return (List) hashMap.get(str);
    }

    public List<POI> getPOIsOfCategory(String str) {
        HashMap<String, List<POI>> hashMap = this.categoryPOIMap;
        if (hashMap == null) {
            return null;
        }
        return (List) hashMap.get(str);
    }

    public void loadBuildingData(JSONObject jSONObject, Application application) {
        Venue venue = (Venue) NaviBeesManager.getInstance(application).getMetaDataManager().getVenues().get(this.venueId);
        if (venue != null) {
            this.mapProviderType = venue.mapProvider.type;
            this.portalsDictionary = new SimpleArrayMap<>();
            this.portals = new ArrayList();
            if (!jSONObject.isNull(JSON_KEY_UPDATED_AT)) {
                this.updatedAt = jSONObject.optString(JSON_KEY_UPDATED_AT);
            }
            this.outdoorRegions = new ArrayList();
            if (!jSONObject.isNull(JSON_KEY_FLOORS)) {
                this.floors = new ArrayList();
                this.floorsDictionary = new SimpleArrayMap<>();
                loadFloors(jSONObject.optJSONArray(JSON_KEY_FLOORS));
            }
            if (!jSONObject.isNull(JSON_KEY_POIS)) {
                this.poisDictionary = new SimpleArrayMap<>();
                this.pois = new ArrayList();
                loadPois(jSONObject.optJSONArray(JSON_KEY_POIS));
            }
            if (!jSONObject.isNull(JSON_KEY_FACILITIES)) {
                this.facilities = new ArrayList();
                this.facilitiesDictionary = new SimpleArrayMap<>();
                loadFacilities(jSONObject.optJSONArray(JSON_KEY_FACILITIES));
            }
            if (!jSONObject.isNull(JSON_KEY_BEACONS)) {
                this.beacons = new HashMap<>();
                loadTags(jSONObject.optJSONArray(JSON_KEY_BEACONS));
            }
            if (!jSONObject.isNull(JSON_KEY_RESTRICTIONS)) {
                this.restrictions = new SimpleArrayMap<>();
                loadRestrictions(jSONObject.optJSONArray(JSON_KEY_RESTRICTIONS));
            }
            if (!jSONObject.isNull(JSON_KEY_ACTIVITY_GROUPS)) {
                this.activityGroups = new ArrayList();
                loadActivities(jSONObject.optJSONArray(JSON_KEY_ACTIVITY_GROUPS));
            }
            generateCategoriesList(application);
        }
    }

    public boolean loadBuildingDataFromFile(Application application) {
        StringBuilder sb = new StringBuilder();
        sb.append(AssetsManager.getMapResourcesMetadataPath(application.getApplicationContext()));
        sb.append("/");
        sb.append(String.format(NaviBeesConstants.BUILDING_FILE, new Object[]{this.f1329id}));
        try {
            loadBuildingData(new JSONObject(NaviBeesManager.getInstance(application).getMetaDataManager().readFromFile(sb.toString())).getJSONObject("all_building"), application);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public JSONObject metadataToJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1329id);
            if (this.name != null) {
                jSONObject.put(JSON_KEY_NAME, this.name.toJson());
            }
            if (this.createdAt != null) {
                jSONObject.put(JSON_KEY_CREATED_AT, this.createdAt);
            }
            if (this.projectId != null) {
                jSONObject.put(JSON_KEY_PROJECT_ID, this.projectId);
            }
            if (this.venueId != null) {
                jSONObject.put(JSON_KEY_VENUE_ID, this.venueId);
            }
            if (this.providerId != null) {
                jSONObject.put(JSON_KEY_PROVIDER_ID, this.providerId);
            }
            jSONObject.put(JSON_KEY_OUTDOOR, this.outdoor);
            if (this.properties != null) {
                jSONObject.put(JSON_KEY_PROPERTIES, new JSONObject(this.properties));
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void serializeBuilding(Application application) {
        JSONObject jSONObject = new JSONObject();
        String str = this.updatedAt;
        if (str != null) {
            try {
                jSONObject.put(JSON_KEY_UPDATED_AT, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Floor> list = this.floors;
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (Floor json : this.floors) {
                JSONObject json2 = json.toJson();
                if (json2 != null) {
                    jSONArray.put(json2);
                }
            }
            try {
                jSONObject.put(JSON_KEY_FLOORS, jSONArray);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        List<POI> list2 = this.pois;
        if (list2 != null && list2.size() > 0) {
            JSONArray jSONArray2 = new JSONArray();
            for (POI json3 : this.pois) {
                JSONObject json4 = json3.toJson();
                if (json4 != null) {
                    jSONArray2.put(json4);
                }
            }
            try {
                jSONObject.put(JSON_KEY_POIS, jSONArray2);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        List<Facility> list3 = this.facilities;
        if (list3 != null && list3.size() > 0) {
            JSONArray jSONArray3 = new JSONArray();
            for (Facility json5 : this.facilities) {
                JSONObject json6 = json5.toJson();
                if (json6 != null) {
                    jSONArray3.put(json6);
                }
            }
            try {
                jSONObject.put(JSON_KEY_FACILITIES, jSONArray3);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        HashMap<String, BeaconNodeConfiguration> hashMap = this.beacons;
        if (hashMap != null && hashMap.size() > 0) {
            JSONArray jSONArray4 = new JSONArray();
            for (BeaconNodeConfiguration json7 : this.beacons.values()) {
                jSONArray4.put(json7.toJson());
            }
            try {
                jSONObject.put(JSON_KEY_BEACONS, jSONArray4);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        SimpleArrayMap<Integer, List<IndoorLocationRestriction>> simpleArrayMap = this.restrictions;
        if (simpleArrayMap != null && simpleArrayMap.size() > 0) {
            JSONArray jSONArray5 = new JSONArray();
            for (int i = 0; i < this.restrictions.size(); i++) {
                for (IndoorLocationRestriction json8 : (List) this.restrictions.get(Integer.valueOf(((Integer) this.restrictions.keyAt(i)).intValue()))) {
                    JSONObject json9 = json8.toJson();
                    if (json9 != null) {
                        jSONArray5.put(json9);
                    }
                }
            }
            try {
                jSONObject.put(JSON_KEY_RESTRICTIONS, jSONArray5);
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        }
        List<ActivityGroup> list4 = this.activityGroups;
        if (list4 != null && list4.size() > 0) {
            JSONArray jSONArray6 = new JSONArray();
            for (ActivityGroup json10 : this.activityGroups) {
                JSONObject json11 = json10.toJson();
                if (json11 != null) {
                    jSONArray6.put(json11);
                }
            }
            try {
                jSONObject.put(JSON_KEY_ACTIVITY_GROUPS, jSONArray6);
            } catch (Exception e7) {
                e7.printStackTrace();
            }
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("all_building", jSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append(AssetsManager.getMapResourcesMetadataPath(application.getApplicationContext()));
            sb.append("/");
            sb.append(String.format(NaviBeesConstants.BUILDING_FILE, new Object[]{this.f1329id}));
            NaviBeesManager.getInstance(application).getMetaDataManager().writeToFile(sb.toString(), jSONObject2.toString());
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    public void setDidSyncAll(boolean z, Context context) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        StringBuilder sb = new StringBuilder();
        sb.append(NaviBeesConstants.BUILDING_SYNC_ALL_PREFIX);
        sb.append(this.f1329id);
        edit.putBoolean(sb.toString(), z);
        edit.apply();
    }

    public Building(Building building) {
        this.f1329id = building.f1329id;
        this.createdAt = building.createdAt;
        this.name = building.name;
        this.projectId = building.projectId;
        this.updatedAt = building.updatedAt;
        this.venueId = building.venueId;
        this.providerId = building.providerId;
        this.outdoor = building.outdoor;
        this.properties = building.properties;
    }

    public Building(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull(JSON_KEY_ID)) {
            this.f1329id = jSONObject.getString(JSON_KEY_ID);
            try {
                this.name = new Text(jSONObject.optJSONObject(JSON_KEY_NAME));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!jSONObject.isNull(JSON_KEY_CREATED_AT)) {
                this.createdAt = jSONObject.optString(JSON_KEY_CREATED_AT);
            }
            if (!jSONObject.isNull(JSON_KEY_PROJECT_ID)) {
                this.projectId = jSONObject.optString(JSON_KEY_PROJECT_ID);
            }
            if (!jSONObject.isNull(JSON_KEY_VENUE_ID)) {
                this.venueId = jSONObject.optString(JSON_KEY_VENUE_ID);
            }
            if (!jSONObject.isNull(JSON_KEY_PROVIDER_ID)) {
                this.providerId = jSONObject.getString(JSON_KEY_PROVIDER_ID);
            }
            if (!jSONObject.isNull(JSON_KEY_OUTDOOR)) {
                this.outdoor = jSONObject.getBoolean(JSON_KEY_OUTDOOR);
            }
            if (!jSONObject.isNull(JSON_KEY_PROPERTIES)) {
                this.properties = new HashMap<>();
                JSONObject jSONObject2 = jSONObject.getJSONObject(JSON_KEY_PROPERTIES);
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    this.properties.put(str, jSONObject2.getString(str));
                }
                return;
            }
            return;
        }
        throw new JSONException("Building must contains \"id\" key with NON NULL value");
    }
}
