package com.navibees.core.model.metadata;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.collection.SimpleArrayMap;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.json.ApplicationConfiguration;
import com.navibees.core.model.metadata.json.BeaconNodeConfiguration;
import com.navibees.core.model.metadata.json.Brand;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.NavibeesApplication;
import com.navibees.core.model.metadata.json.OutdoorRegion;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.postioning.NaviBeesBeaconManager;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.model.server.Prefs;
import com.navibees.core.util.AssetsManager;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class MetaDataManager {

    /* renamed from: d */
    private static int f1313d = 15;

    /* renamed from: a */
    private String f1314a;

    /* renamed from: b */
    private String f1315b;

    /* renamed from: c */
    private Application f1316c;
    public NavibeesApplication navibeesApp;

    /* renamed from: com.navibees.core.model.metadata.MetaDataManager$a */
    class C1662a extends TypeToken<Map<String, Long>> {
        C1662a() {
        }
    }

    /* renamed from: com.navibees.core.model.metadata.MetaDataManager$b */
    class C1663b extends TypeToken<Map<String, Long>> {
        C1663b() {
        }
    }

    /* renamed from: com.navibees.core.model.metadata.MetaDataManager$c */
    class C1664c extends TypeToken<Map<String, Long>> {
        C1664c() {
        }
    }

    /* renamed from: com.navibees.core.model.metadata.MetaDataManager$d */
    class C1665d extends TypeToken<Map<String, Long>> {
        C1665d() {
        }
    }

    /* renamed from: a */
    private void m965a(Application application, String str) {
        Venue venue = (Venue) getVenues().get(str);
        if (venue != null) {
            for (int i = 0; i < venue.buildings.size(); i++) {
                Building building = (Building) venue.buildings.valueAt(i);
                StringBuilder sb = new StringBuilder();
                sb.append(AssetsManager.getMapResourcesMetadataPath(application.getApplicationContext()));
                sb.append("/");
                sb.append(String.format(NaviBeesConstants.BUILDING_FILE, new Object[]{building.f1329id}));
                try {
                    building.loadBuildingData(new JSONObject(readFromFile(sb.toString())).getJSONObject("all_building"), application);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: b */
    private boolean m966b(Context context) {
        return loadNavibeesApp(readFromFile(AssetsManager.getMapResourcesAppPath(context)), context);
    }

    public void deleteSavedLocation(Context context) {
        if (context != null) {
            String str = this.f1315b;
            if (str == null) {
                str = "";
            }
            Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            StringBuilder sb = new StringBuilder();
            sb.append(NaviBeesConstants.SAVED_LOCATION_KEY);
            sb.append(str);
            edit.remove(sb.toString());
            edit.apply();
        }
    }

    public List<OutdoorRegion> getAllOutdoorRegions() {
        ArrayList arrayList = new ArrayList();
        SimpleArrayMap buildings = getBuildings();
        if (buildings == null) {
            return arrayList;
        }
        for (int i = 0; i < buildings.size(); i++) {
            List<OutdoorRegion> list = ((Building) buildings.valueAt(i)).outdoorRegions;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public String getBuildingIdByName(String str) {
        for (int i = 0; i < getCurrentVenue().buildings.size(); i++) {
            Building building = (Building) getCurrentVenue().buildings.valueAt(i);
            if (building.name.getText().equalsIgnoreCase(str)) {
                return building.f1329id;
            }
        }
        return null;
    }

    public HashMap<String, String> getBuildingProperties() {
        try {
            return getCurrentBuilding().properties;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SimpleArrayMap<String, Building> getBuildings() {
        Venue currentVenue = getCurrentVenue();
        if (currentVenue == null) {
            return null;
        }
        return currentVenue.buildings;
    }

    public ApplicationConfiguration getConfiguration() {
        return this.navibeesApp.appConfiguration;
    }

    public Building getCurrentBuilding() {
        if (this.f1314a == null || getBuildings() == null) {
            return null;
        }
        return (Building) getBuildings().get(this.f1314a);
    }

    public Venue getCurrentVenue() {
        if (this.f1315b == null) {
            return null;
        }
        return (Venue) getVenues().get(this.f1315b);
    }

    public int getEndAlertDistance() {
        int i = 3;
        try {
            String str = (String) getCurrentBuilding().properties.get("End_Alert_Distance");
            if (str != null) {
                i = Integer.valueOf(str).intValue();
            }
            return i;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 3;
        }
    }

    public String getFloorAltitude(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Flooralt_");
        sb.append(str);
        try {
            str2 = (String) getCurrentBuilding().properties.get(sb.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            str2 = null;
        }
        return str2;
    }

    public String getLastSyncDate(String str, SimpleDateFormat simpleDateFormat) {
        Prefs instance = Prefs.getInstance(this.f1316c.getApplicationContext());
        return simpleDateFormat.format(new Date(((Long) ((Map) new Gson().fromJson(instance.getLastSyncDate(), new C1662a().getType())).get(str)).longValue()));
    }

    public int getMinimumBeacons() {
        int i = 2;
        try {
            String str = (String) getCurrentBuilding().properties.get("Minimum_Beacons");
            if (str != null) {
                i = Integer.valueOf(str).intValue();
            }
            return i;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public String getOutsideBuildingId() {
        try {
            return (String) getCurrentVenue().properties.get("outside_building_id");
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getOutsideFloorIndex() {
        int i = Integer.MIN_VALUE;
        try {
            String str = (String) getCurrentVenue().properties.get("outside_floor_id");
            if (str != null) {
                i = Integer.valueOf(str).intValue();
            }
            return i;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    public int getRouteRecalculationDistance() {
        try {
            String str = (String) getCurrentVenue().properties.get("Route_Recalculation_Distance");
            if (str != null) {
                return Integer.valueOf(str).intValue();
            }
            return f1313d;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return f1313d;
        }
    }

    public IndoorLocation getSavedLocation(Context context) {
        String str = this.f1315b;
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        StringBuilder sb = new StringBuilder();
        sb.append(NaviBeesConstants.SAVED_LOCATION_KEY);
        sb.append(str);
        try {
            JSONObject jSONObject = new JSONObject(defaultSharedPreferences.getString(sb.toString(), str2));
            Log.m1173i("saveLoc:load", jSONObject.toString());
            return new IndoorLocation(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getVenueProperties() {
        try {
            return getCurrentVenue().properties;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SimpleArrayMap<String, Venue> getVenues() {
        return this.navibeesApp.venues;
    }

    public boolean loadMapMetaData(Application application) throws NaviBeesLicenseExpireException, NaviBeesLicenseNotAuthorizedException {
        boolean z;
        this.navibeesApp = null;
        this.f1316c = application;
        if (!m966b(application.getApplicationContext())) {
            return false;
        }
        try {
            NaviBeesManager.getInstance(application).getLicenseManager().mo29048a((NaviBeesFeature) null);
            NavibeesApplication navibeesApplication = this.navibeesApp;
            if (navibeesApplication != null) {
                SimpleArrayMap<String, Venue> simpleArrayMap = navibeesApplication.venues;
                if (simpleArrayMap != null) {
                    if (simpleArrayMap.size() == 1) {
                        setCurrentVenue(application, ((Venue) this.navibeesApp.venues.valueAt(0)).f1342id);
                        z = true;
                    } else {
                        String a = m964a(application.getApplicationContext());
                        if (a != null) {
                            setCurrentVenue(application, a);
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                    if (z && getCurrentVenue() != null && getCurrentVenue().buildings.size() == 1) {
                        this.f1314a = ((Building) getCurrentVenue().buildings.valueAt(0)).f1329id;
                    }
                }
            }
            return true;
        } catch (NaviBeesLicenseExpireException e) {
            String str = "Navibees License Expired";
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(application, str, 0).show();
            }
            throw new NaviBeesLicenseExpireException(str);
        } catch (NaviBeesLicenseNotAuthorizedException e2) {
            String str2 = "Unauthorized Navibees License";
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(application, str2, 0).show();
            }
            throw new NaviBeesLicenseNotAuthorizedException(str2);
        }
    }

    public boolean loadNavibeesApp(String str, Context context) {
        try {
            if (str.isEmpty()) {
                return false;
            }
            this.navibeesApp = new NavibeesApplication(new JSONObject(str), context);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BeaconNodeConfiguration lookupTagLocation(int i, int i2, String str) {
        SimpleArrayMap buildings = getBuildings();
        BeaconNodeConfiguration beaconNodeConfiguration = new BeaconNodeConfiguration(i, i2);
        beaconNodeConfiguration.uuid = str;
        String generateKey = beaconNodeConfiguration.generateKey();
        int i3 = 0;
        int i4 = 0;
        while (buildings != null && i4 < buildings.size()) {
            HashMap<String, BeaconNodeConfiguration> hashMap = ((Building) buildings.valueAt(i4)).beacons;
            if (hashMap != null) {
                BeaconNodeConfiguration beaconNodeConfiguration2 = (BeaconNodeConfiguration) hashMap.get(generateKey);
                if (beaconNodeConfiguration2 != null && beaconNodeConfiguration2.active) {
                    return beaconNodeConfiguration2;
                }
            }
            i4++;
        }
        BeaconNodeConfiguration beaconNodeConfiguration3 = new BeaconNodeConfiguration(i, i2);
        if (Arrays.asList(NaviBeesManager.getInstance(this.f1316c).getMetaDataManager().getConfiguration().beaconsUUIDList).contains(str)) {
            beaconNodeConfiguration3.uuid = null;
            String generateKey2 = beaconNodeConfiguration3.generateKey();
            while (buildings != null && i3 < buildings.size()) {
                HashMap<String, BeaconNodeConfiguration> hashMap2 = ((Building) buildings.valueAt(i3)).beacons;
                if (hashMap2 != null) {
                    BeaconNodeConfiguration beaconNodeConfiguration4 = (BeaconNodeConfiguration) hashMap2.get(generateKey2);
                    if (beaconNodeConfiguration4 != null && beaconNodeConfiguration4.active) {
                        return beaconNodeConfiguration4;
                    }
                }
                i3++;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A[SYNTHETIC, Splitter:B:34:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005c A[Catch:{ Exception -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0061 A[Catch:{ Exception -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0070 A[SYNTHETIC, Splitter:B:47:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0078 A[Catch:{ Exception -> 0x0074 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x007d A[Catch:{ Exception -> 0x0074 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String readFromFile(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004c, all -> 0x0047 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x004c, all -> 0x0047 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0044, all -> 0x0040 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x0044, all -> 0x0040 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x003a, all -> 0x0036 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x003a, all -> 0x0036 }
        L_0x0015:
            java.lang.String r1 = r3.readLine()     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            if (r1 == 0) goto L_0x001f
            r0.append(r1)     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            goto L_0x0015
        L_0x001f:
            r6.close()     // Catch:{ Exception -> 0x0029 }
            r2.close()     // Catch:{ Exception -> 0x0029 }
            r3.close()     // Catch:{ Exception -> 0x0029 }
            goto L_0x0068
        L_0x0029:
            r6 = move-exception
            r6.getMessage()
            goto L_0x0068
        L_0x002e:
            r0 = move-exception
            r1 = r6
            goto L_0x006e
        L_0x0031:
            r1 = move-exception
            r4 = r1
            r1 = r6
            r6 = r4
            goto L_0x004f
        L_0x0036:
            r0 = move-exception
            r3 = r1
            r1 = r6
            goto L_0x006e
        L_0x003a:
            r3 = move-exception
            r4 = r1
            r1 = r6
            r6 = r3
            r3 = r4
            goto L_0x004f
        L_0x0040:
            r6 = move-exception
            r0 = r6
            r3 = r1
            goto L_0x006e
        L_0x0044:
            r6 = move-exception
            r3 = r1
            goto L_0x004f
        L_0x0047:
            r6 = move-exception
            r0 = r6
            r2 = r1
            r3 = r2
            goto L_0x006e
        L_0x004c:
            r6 = move-exception
            r2 = r1
            r3 = r2
        L_0x004f:
            r6.getMessage()     // Catch:{ all -> 0x006d }
            if (r1 == 0) goto L_0x005a
            r1.close()     // Catch:{ Exception -> 0x0058 }
            goto L_0x005a
        L_0x0058:
            r6 = move-exception
            goto L_0x0065
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ Exception -> 0x0058 }
        L_0x005f:
            if (r3 == 0) goto L_0x0068
            r3.close()     // Catch:{ Exception -> 0x0058 }
            goto L_0x0068
        L_0x0065:
            r6.getMessage()
        L_0x0068:
            java.lang.String r6 = r0.toString()
            return r6
        L_0x006d:
            r0 = move-exception
        L_0x006e:
            if (r1 == 0) goto L_0x0076
            r1.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0076
        L_0x0074:
            r6 = move-exception
            goto L_0x0081
        L_0x0076:
            if (r2 == 0) goto L_0x007b
            r2.close()     // Catch:{ Exception -> 0x0074 }
        L_0x007b:
            if (r3 == 0) goto L_0x0084
            r3.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0084
        L_0x0081:
            r6.getMessage()
        L_0x0084:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.model.metadata.MetaDataManager.readFromFile(java.lang.String):java.lang.String");
    }

    public void saveLastVenue(Context context) {
        if (this.f1315b != null) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putString(NaviBeesConstants.LAST_VENUE_ID_KEY, this.f1315b);
            edit.apply();
        }
    }

    public void saveLocation(Context context, IndoorLocation indoorLocation) {
        if (indoorLocation != null) {
            JSONObject json = indoorLocation.toJson();
            if (json != null) {
                String str = this.f1315b;
                if (str == null) {
                    str = "";
                }
                Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                StringBuilder sb = new StringBuilder();
                sb.append(NaviBeesConstants.SAVED_LOCATION_KEY);
                sb.append(str);
                edit.putString(sb.toString(), json.toString());
                edit.apply();
            }
        }
    }

    public List<Facility> searchInFacilities(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.toUpperCase().split(" ");
        HashMap<String, Brand> hashMap = this.navibeesApp.brands;
        ArrayList arrayList = new ArrayList();
        SimpleArrayMap buildings = getBuildings();
        int i = 0;
        while (buildings != null && i < buildings.size()) {
            List<Facility> list = ((Building) buildings.get((String) buildings.keyAt(i))).facilities;
            if (list != null) {
                for (Facility facility : list) {
                    if (!NaviBeesUtils.searchInString(facility.name.toString(), split)) {
                        List pois = facility.getPois();
                        if (pois != null) {
                            Iterator it = pois.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (NaviBeesUtils.isPOIMatch(split, (POI) it.next(), hashMap)) {
                                        arrayList.add(facility);
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    } else {
                        arrayList.add(facility);
                    }
                }
            }
            i++;
        }
        return arrayList;
    }

    public List<POI> searchInPOIs(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.toUpperCase().split(" ");
        HashMap<String, Brand> hashMap = this.navibeesApp.brands;
        ArrayList arrayList = new ArrayList();
        SimpleArrayMap buildings = getBuildings();
        int i = 0;
        while (buildings != null && i < buildings.size()) {
            List<POI> list = ((Building) buildings.get((String) buildings.keyAt(i))).pois;
            if (list != null) {
                for (POI poi : list) {
                    if (NaviBeesUtils.isPOIMatch(split, poi, hashMap)) {
                        arrayList.add(poi);
                    }
                }
            }
            i++;
        }
        return arrayList;
    }

    public boolean serializeNavibeesApp(Context context) {
        return writeToFile(AssetsManager.getMapResourcesAppPath(context), this.navibeesApp.toJson().toString());
    }

    public void setCurrentBuilding(String str) {
        this.f1314a = str;
    }

    public void setCurrentVenue(Application application, String str) {
        this.f1315b = str;
        m965a(application, this.f1315b);
        if (getCurrentVenue() != null) {
            NaviBeesUtils.createProjection(getCurrentVenue().projParams);
        }
        PositionManager positionManager = NaviBeesManager.getInstance(application).getPositionManager();
        if (positionManager != null) {
            positionManager.reset();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0044 A[SYNTHETIC, Splitter:B:26:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0051 A[SYNTHETIC, Splitter:B:34:0x0051] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeToFile(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            r1.<init>(r4)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            java.io.File r2 = r1.getParentFile()     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            boolean r2 = r2.exists()     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            if (r2 != 0) goto L_0x0017
            java.io.File r2 = r1.getParentFile()     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            r2.mkdirs()     // Catch:{ Exception -> 0x003e, all -> 0x003c }
        L_0x0017:
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            if (r2 != 0) goto L_0x0020
            r1.createNewFile()     // Catch:{ Exception -> 0x003e, all -> 0x003c }
        L_0x0020:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            r1.<init>(r4)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            byte[] r4 = r5.getBytes()     // Catch:{ Exception -> 0x0039, all -> 0x0036 }
            r1.write(r4)     // Catch:{ Exception -> 0x0039, all -> 0x0036 }
            r4 = 1
            r1.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0035:
            return r4
        L_0x0036:
            r4 = move-exception
            r0 = r1
            goto L_0x004f
        L_0x0039:
            r4 = move-exception
            r0 = r1
            goto L_0x003f
        L_0x003c:
            r4 = move-exception
            goto L_0x004f
        L_0x003e:
            r4 = move-exception
        L_0x003f:
            r4.printStackTrace()     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ Exception -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004c:
            r4 = 0
            return r4
        L_0x004e:
            r4 = move-exception
        L_0x004f:
            if (r0 == 0) goto L_0x0059
            r0.close()     // Catch:{ Exception -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0059:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.model.metadata.MetaDataManager.writeToFile(java.lang.String, java.lang.String):boolean");
    }

    public HashMap<String, String> getBuildingProperties(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        while (i < getVenues().size()) {
            try {
                Building building = (Building) ((Venue) getVenues().get(getVenues().keyAt(i))).buildings.get(str);
                if (building != null) {
                    return building.properties;
                }
                i++;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public HashMap<String, String> getVenueProperties(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return ((Venue) getVenues().get(str)).properties;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLastSyncDate(String str) {
        Prefs instance = Prefs.getInstance(this.f1316c.getApplicationContext());
        return new SimpleDateFormat("dd MMM YYYY hh:mm:ss a ZZZZ", Locale.getDefault()).format(((Map) new Gson().fromJson(instance.getLastSyncDate(), new C1663b().getType())).get(str));
    }

    /* renamed from: a */
    private String m964a(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = NaviBeesConstants.LAST_VENUE_ID_KEY;
        if (defaultSharedPreferences.contains(str)) {
            return defaultSharedPreferences.getString(str, null);
        }
        return null;
    }

    public HashMap<String, String> getLastSyncDate(List<String> list, SimpleDateFormat simpleDateFormat) {
        Prefs instance = Prefs.getInstance(this.f1316c.getApplicationContext());
        HashMap<String, String> hashMap = new HashMap<>();
        Map map = (Map) new Gson().fromJson(instance.getLastSyncDate(), new C1664c().getType());
        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                hashMap.put(list.get(i), simpleDateFormat.format(new Date(((Long) map.get(list.get(i))).longValue())));
            }
        }
        return hashMap;
    }

    public HashMap<String, String> getLastSyncDate(List<String> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM YYYY hh:mm:ss a ZZZZ", Locale.getDefault());
        Prefs instance = Prefs.getInstance(this.f1316c.getApplicationContext());
        HashMap<String, String> hashMap = new HashMap<>();
        Map map = (Map) new Gson().fromJson(instance.getLastSyncDate(), new C1665d().getType());
        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                hashMap.put(list.get(i), simpleDateFormat.format(new Date(((Long) map.get(list.get(i))).longValue())));
            }
        }
        return hashMap;
    }
}
