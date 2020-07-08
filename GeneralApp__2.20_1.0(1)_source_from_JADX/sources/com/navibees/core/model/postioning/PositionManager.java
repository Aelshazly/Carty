package com.navibees.core.model.postioning;

import android.app.Activity;
import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.C1642a;
import com.navibees.core.interfaces.OutdoorLocationListener;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.json.ApplicationConfiguration;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationConfidence;
import com.navibees.core.model.metadata.json.IndoorLocationRestriction;
import com.navibees.core.model.metadata.json.OutdoorRegion;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.postioning.PositionLocator.CalculatedLocation;
import com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM;
import com.navibees.core.model.postioning.p025h.C1713b;
import com.navibees.core.util.NaviBeesUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class PositionManager implements BeaconNodeListener, SensorEventListener, OutdoorLocationListener, C1642a {

    /* renamed from: A */
    static final String f1368A = "PositionManager";
    public static LOCALIZER_ALGORITHM ALGO_TO_USE = LOCALIZER_ALGORITHM.WEIGHTED_CENTROID;

    /* renamed from: B */
    private static final int f1369B = 4;

    /* renamed from: C */
    private static int f1370C = -1;
    /* access modifiers changed from: private */

    /* renamed from: D */
    public static IndoorLocation f1371D = null;

    /* renamed from: E */
    private static IndoorLocation f1372E = null;

    /* renamed from: F */
    private static int f1373F = -1;
    public static int GPS_ACCURACY_THRESHOLD = 20;
    public static double LOCATION_DIFF_RANGE = 4.0d;
    public static double MAX_LOCATION_DIFF = 10.0d;
    public static boolean PATH_CONFIDENCE_ON = false;
    public static int PATH_INC_CONFIDENCE = 20;
    public static int PATH_MAX_CONFIDENCE = 100;
    public static int changeFloorCounter = 0;
    public static int changeFloorCounterTri = 0;
    public static int currentFloor = -1;
    public static int currentFloorTri = -1;
    public static int initialPathConfidence = 0;
    public static int newFloor = -1;
    public static int newFloorTri = -1;
    public static int pathConfidence = 0;
    public static int pathConfidence_Tri = 0;
    public static int pathSwitchingDecisionPercentage = 50;
    public static boolean showMarkers = false;
    public static boolean useOutdoor = false;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public ArrayList<NBLocationListener> f1374a;

    /* renamed from: b */
    private ArrayList<AdvancedLocationListener> f1375b;

    /* renamed from: c */
    private C1713b f1376c;

    /* renamed from: d */
    private C1713b f1377d;

    /* renamed from: e */
    private SensorManager f1378e;

    /* renamed from: f */
    private double f1379f = 2.5d;

    /* renamed from: g */
    private boolean f1380g;
    public int gpsAccuracy;

    /* renamed from: h */
    private boolean f1381h;

    /* renamed from: i */
    private C1701d f1382i;
    public boolean isInsideCircle;
    public boolean isOutdoor;

    /* renamed from: j */
    private int f1383j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public Handler f1384k;

    /* renamed from: l */
    private ArrayList<Integer> f1385l;
    public double locationDiff;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public IndoorLocation f1386m;

    /* renamed from: n */
    private final int f1387n;

    /* renamed from: o */
    private IndoorLocationRestriction f1388o;

    /* renamed from: p */
    private IndoorLocationRestriction f1389p;

    /* renamed from: q */
    private final boolean f1390q;

    /* renamed from: r */
    private C1713b f1391r;

    /* renamed from: s */
    private C1713b f1392s;

    /* renamed from: t */
    private Application f1393t;

    /* renamed from: u */
    private int f1394u;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public volatile int f1395v;
    /* access modifiers changed from: private */

    /* renamed from: w */
    public int f1396w;
    /* access modifiers changed from: private */

    /* renamed from: x */
    public int f1397x;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public boolean f1398y;
    /* access modifiers changed from: private */

    /* renamed from: z */
    public Runnable f1399z;

    /* renamed from: com.navibees.core.model.postioning.PositionManager$a */
    class C1691a implements Runnable {
        C1691a() {
        }

        public void run() {
            if (PositionManager.this.f1395v != -1) {
                if (PositionManager.this.f1395v >= PositionManager.this.f1396w) {
                    PositionManager.f1371D = null;
                    PositionManager.this.f1386m = null;
                    System.out.println("Position Manager: user out of venue");
                    Iterator it = PositionManager.this.f1374a.iterator();
                    while (it.hasNext()) {
                        ((NBLocationListener) it.next()).locationCallback(null, null, 0, false, false);
                    }
                    PositionManager.this.f1398y = false;
                    return;
                }
                PositionManager.this.f1395v = PositionManager.this.f1395v + 1;
                PositionManager.this.f1384k.postDelayed(PositionManager.this.f1399z, (long) PositionManager.this.f1397x);
            }
        }
    }

    /* renamed from: com.navibees.core.model.postioning.PositionManager$b */
    static /* synthetic */ class C1692b {

        /* renamed from: a */
        static final /* synthetic */ int[] f1401a = new int[LOCALIZER_ALGORITHM.values().length];

        static {
            try {
                f1401a[LOCALIZER_ALGORITHM.TRILLATERATION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public PositionManager(Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        boolean z = false;
        this.f1380g = false;
        this.f1381h = false;
        this.f1383j = 0;
        this.isOutdoor = false;
        this.isInsideCircle = false;
        this.gpsAccuracy = 0;
        this.f1384k = new Handler();
        this.f1385l = new ArrayList<>();
        this.f1386m = null;
        this.f1387n = 90;
        this.f1390q = true;
        this.f1394u = 0;
        this.f1395v = -1;
        this.f1396w = 2;
        this.f1397x = 3000;
        this.f1398y = false;
        this.f1399z = new C1691a();
        this.f1393t = application;
        NaviBeesManager.getInstance(application).getLicenseManager().mo29048a(NaviBeesFeature.Positioning);
        this.f1376c = new C1713b(application);
        this.f1391r = new C1713b(application);
        this.f1377d = new C1713b(application);
        this.f1392s = new C1713b(application);
        this.f1378e = (SensorManager) application.getSystemService("sensor");
        this.f1374a = new ArrayList<>();
        this.f1375b = new ArrayList<>();
        ApplicationConfiguration configuration = NaviBeesManager.getInstance(application).getMetaDataManager().getConfiguration();
        if (configuration != null && configuration.outdoorEnabled) {
            this.f1382i = new C1701d(application.getApplicationContext(), this);
            useOutdoor = true;
        }
        HashMap venueProperties = NaviBeesManager.getInstance(application).getMetaDataManager().getVenueProperties();
        String str = "Path_Confidence_Enable";
        int intValue = venueProperties.get(str) != null ? Integer.valueOf((String) venueProperties.get(str)).intValue() : 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Path_Confidence_Enable : ");
        sb.append(intValue);
        String sb2 = sb.toString();
        String str2 = f1368A;
        Log.d(str2, sb2);
        String str3 = "Location_Difference_Range";
        LOCATION_DIFF_RANGE = venueProperties.get(str3) != null ? Double.valueOf((String) venueProperties.get(str3)).doubleValue() : 4.0d;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("LOCATION_DIFF_RANGE : ");
        sb3.append(LOCATION_DIFF_RANGE);
        Log.d(str2, sb3.toString());
        String str4 = "Max_Path_Confidence";
        PATH_MAX_CONFIDENCE = venueProperties.get(str4) != null ? Integer.valueOf((String) venueProperties.get(str4)).intValue() : 100;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("PATH_MAX_CONFIDENCE : ");
        sb4.append(PATH_MAX_CONFIDENCE);
        Log.d(str2, sb4.toString());
        String str5 = "Initial_Path_Confidence";
        initialPathConfidence = venueProperties.get(str5) != null ? Integer.valueOf((String) venueProperties.get(str5)).intValue() : 100;
        initialPathConfidence = (initialPathConfidence * PATH_MAX_CONFIDENCE) / 100;
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Initial_Path_Confidence (in percentage): ");
        sb5.append(initialPathConfidence);
        Log.d(str2, sb5.toString());
        String str6 = "Confidence_Inc_Dec_Factor";
        PATH_INC_CONFIDENCE = venueProperties.get(str6) != null ? Integer.valueOf((String) venueProperties.get(str6)).intValue() : 10;
        PATH_INC_CONFIDENCE = (PATH_INC_CONFIDENCE * PATH_MAX_CONFIDENCE) / 100;
        StringBuilder sb6 = new StringBuilder();
        sb6.append("Confidence_Inc_Dec_Factor (in percentage): ");
        sb6.append(PATH_INC_CONFIDENCE);
        Log.d(str2, sb6.toString());
        String str7 = "Path_Switching_Decision_Percentage";
        pathSwitchingDecisionPercentage = venueProperties.get(str7) != null ? Integer.valueOf((String) venueProperties.get(str7)).intValue() : 50;
        pathSwitchingDecisionPercentage = (pathSwitchingDecisionPercentage * PATH_MAX_CONFIDENCE) / 100;
        StringBuilder sb7 = new StringBuilder();
        sb7.append("Path_Switching_Decision_Percentage (in percentage): ");
        sb7.append(pathSwitchingDecisionPercentage);
        Log.d(str2, sb7.toString());
        pathConfidence = initialPathConfidence;
        if (intValue != 0) {
            z = true;
        }
        PATH_CONFIDENCE_ON = z;
        pathConfidence_Tri = (initialPathConfidence * PATH_MAX_CONFIDENCE) / 100;
    }

    public void addAdvancedLocationListener(AdvancedLocationListener advancedLocationListener) {
        this.f1375b.add(advancedLocationListener);
    }

    public void addLocationListener(NBLocationListener nBLocationListener) {
        this.f1374a.add(nBLocationListener);
    }

    public void beaconNodeCallback(List<BeaconNode> list) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        CalculatedLocation calculatedLocation;
        if (!this.f1398y) {
            System.out.println("Position Manager: starting reset thread");
            this.f1384k.postDelayed(this.f1399z, (long) this.f1397x);
            this.f1398y = true;
            f1370C = -1;
        }
        this.f1395v = 0;
        this.f1383j = list.size();
        CalculatedLocation calculatedLocation2 = null;
        LOCALIZER_ALGORITHM localizer_algorithm = ALGO_TO_USE;
        LOCALIZER_ALGORITHM localizer_algorithm2 = LOCALIZER_ALGORITHM.WEIGHTED_CENTROID;
        if (localizer_algorithm == localizer_algorithm2) {
            calculatedLocation = m1018b(localizer_algorithm2, list);
        } else {
            LOCALIZER_ALGORITHM localizer_algorithm3 = ALGO_TO_USE;
            LOCALIZER_ALGORITHM localizer_algorithm4 = LOCALIZER_ALGORITHM.TRILLATERATION;
            if (localizer_algorithm3 == localizer_algorithm4) {
                calculatedLocation = m1018b(localizer_algorithm4, list);
            } else {
                calculatedLocation = m1018b(LOCALIZER_ALGORITHM.WEIGHTED_CENTROID, list);
                calculatedLocation2 = m1018b(LOCALIZER_ALGORITHM.TRILLATERATION, list);
            }
        }
        if (NaviBeesManager.STEP_BY_STEP) {
            Iterator it = this.f1375b.iterator();
            while (it.hasNext()) {
                ((AdvancedLocationListener) it.next()).locationCallback(calculatedLocation2, calculatedLocation, this.f1380g);
            }
            return;
        }
        if (this.f1374a.size() != 1 || !((NBLocationListener) this.f1374a.get(0)).isBackground()) {
            NaviBeesManager.getInstance(this.f1393t).getNavibeesAnalytics().mo29034a(this.f1393t, f1371D);
        }
        Iterator it2 = this.f1374a.iterator();
        while (it2.hasNext()) {
            ((NBLocationListener) it2.next()).locationCallback(null, f1371D, list.size(), true, this.f1380g);
        }
    }

    public void connectOutdoorPositionManager(boolean z, Activity activity) {
        C1701d dVar = this.f1382i;
        if (dVar == null) {
            return;
        }
        if (z) {
            dVar.mo29361a(activity);
        } else {
            dVar.mo29362b(activity);
        }
    }

    public IndoorLocation getLastLocationCoordinateLatLong() {
        IndoorLocation indoorLocation = f1371D;
        if (indoorLocation == null || indoorLocation.floor <= -1) {
            return null;
        }
        return NaviBeesUtils.convertUTMToLatLong(indoorLocation);
    }

    public IndoorLocation getLastLocationCoordinateUTM() {
        return f1371D;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onLocationUpdate(IndoorLocation indoorLocation, int i) {
        this.gpsAccuracy = i;
        if ((indoorLocation != null) && (i <= 90)) {
            this.f1385l.add(Integer.valueOf(i));
            if (this.f1385l.size() > 2) {
                this.f1385l.remove(0);
            } else if (this.f1385l.size() < 2) {
                this.f1385l.add(Integer.valueOf(i));
            }
            this.locationDiff = MAX_LOCATION_DIFF;
            IndoorLocation convertLatLongToUTM = NaviBeesUtils.convertLatLongToUTM(indoorLocation);
            IndoorLocation indoorLocation2 = f1371D;
            if (!(indoorLocation2 == null || convertLatLongToUTM == null)) {
                this.locationDiff = C1697c.m1064d(convertLatLongToUTM, indoorLocation2);
            }
            double intValue = (double) (((Integer) this.f1385l.get(1)).intValue() - ((Integer) this.f1385l.get(0)).intValue());
            if (m1023d(indoorLocation) && ((i <= GPS_ACCURACY_THRESHOLD) || (((intValue > -1.0E-4d ? 1 : (intValue == -1.0E-4d ? 0 : -1)) < 0 && (this.locationDiff > MAX_LOCATION_DIFF ? 1 : (this.locationDiff == MAX_LOCATION_DIFF ? 0 : -1)) < 0) || (this.isOutdoor && (Math.abs(intValue) > 1.0E-4d ? 1 : (Math.abs(intValue) == 1.0E-4d ? 0 : -1)) < 0)))) {
                this.isOutdoor = true;
                IndoorLocation indoorLocation3 = f1371D;
                indoorLocation.floor = indoorLocation3 == null ? 0 : indoorLocation3.floor;
                indoorLocation.confidence = IndoorLocationConfidence.Average;
                Building currentBuilding = NaviBeesManager.getInstance(this.f1393t).getMetaDataManager().getCurrentBuilding();
                if (currentBuilding != null) {
                    indoorLocation.buildingId = currentBuilding.f1329id;
                } else {
                    IndoorLocation indoorLocation4 = f1371D;
                    indoorLocation.buildingId = indoorLocation4 == null ? ((Building) NaviBeesManager.getInstance(this.f1393t).getMetaDataManager().getBuildings().valueAt(0)).f1329id : indoorLocation4.buildingId;
                }
                Venue currentVenue = NaviBeesManager.getInstance(this.f1393t).getMetaDataManager().getCurrentVenue();
                if (currentVenue != null) {
                    indoorLocation.venueId = currentVenue.f1342id;
                } else {
                    IndoorLocation indoorLocation5 = f1371D;
                    indoorLocation.venueId = indoorLocation5 == null ? ((Venue) NaviBeesManager.getInstance(this.f1393t).getMetaDataManager().getVenues().valueAt(0)).f1342id : indoorLocation5.venueId;
                }
                Iterator it = this.f1374a.iterator();
                while (it.hasNext()) {
                    ((NBLocationListener) it.next()).locationCallback(null, indoorLocation, 0, false, this.f1380g);
                }
                f1371D = NaviBeesUtils.convertLatLongToUTM(indoorLocation);
                if (this.f1374a.size() != 1 || !((NBLocationListener) this.f1374a.get(0)).isBackground()) {
                    NaviBeesManager.getInstance(this.f1393t).getNavibeesAnalytics().mo29034a(this.f1393t, f1371D);
                }
            } else {
                this.isOutdoor = false;
            }
        } else {
            this.isOutdoor = false;
        }
        this.isInsideCircle = m1023d(indoorLocation);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        boolean z = false;
        double d = (double) fArr[0];
        double d2 = (double) fArr[1];
        double d3 = (double) fArr[2];
        this.f1379f = (this.f1379f * 0.30000000000000004d) + (Math.sqrt((d * d) + (d2 * d2) + (d3 * d3)) * 0.7d);
        if (this.f1379f > 0.5d) {
            z = true;
        }
        this.f1380g = z;
    }

    public void registerSensor() {
        if (!this.f1381h) {
            this.f1381h = true;
            this.f1378e.registerListener(this, this.f1378e.getDefaultSensor(10), 3);
            this.f1379f = 2.5d;
        }
    }

    public void removeAdvancedLocationListener(AdvancedLocationListener advancedLocationListener) {
        this.f1375b.remove(advancedLocationListener);
    }

    public void removeAdvancedLocationListeners() {
        this.f1375b.clear();
    }

    public void removeLocationListener(NBLocationListener nBLocationListener) {
        this.f1374a.remove(nBLocationListener);
    }

    public void reset() {
        this.f1376c.mo29383b();
        this.f1377d.mo29383b();
        this.f1379f = 2.5d;
        this.f1380g = false;
        f1370C = -1;
        changeFloorCounter = 0;
        f1371D = null;
        this.f1385l = new ArrayList<>();
        this.f1386m = null;
        pathConfidence = 0;
        this.f1388o = null;
        this.f1391r.mo29383b();
        this.f1392s.mo29383b();
    }

    public void startOutdoorLocationUpdates(Activity activity) {
        C1701d dVar = this.f1382i;
        if (dVar != null) {
            dVar.mo29363c(activity);
        }
    }

    public void startTracking() {
        this.f1376c.mo29383b();
        this.f1377d.mo29383b();
        this.f1380g = false;
    }

    public void stateTurnedOff() {
        f1371D = null;
        this.f1386m = null;
        Iterator it = this.f1374a.iterator();
        while (it.hasNext()) {
            ((NBLocationListener) it.next()).locationCallback(null, null, 0, true, this.f1380g);
        }
        this.f1384k.removeCallbacks(this.f1399z);
        this.f1398y = false;
    }

    public void stateTurnedOn() {
        reset();
        this.f1384k.postDelayed(this.f1399z, (long) this.f1397x);
        this.f1398y = true;
    }

    public void stopOutdoorLocationUpdates() {
        C1701d dVar = this.f1382i;
        if (dVar != null) {
            this.isOutdoor = false;
            dVar.mo29360a();
        }
    }

    public void unregisterSensor() {
        this.f1380g = false;
        if (this.f1381h) {
            this.f1381h = false;
            this.f1378e.unregisterListener(this);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0108  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.navibees.core.model.postioning.PositionLocator.CalculatedLocation m1018b(com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM r14, java.util.List<com.navibees.core.model.metadata.BeaconNode> r15) throws com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException, com.navibees.core.model.license.NaviBeesLicenseExpireException {
        /*
            r13 = this;
            com.navibees.core.model.postioning.PositionLocator$CalculatedLocation r15 = r13.m1015a(r14, r15)
            com.navibees.core.model.metadata.json.IndoorLocation r0 = r15.indoorLocation
            r1 = 2
            double[] r1 = new double[r1]
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r2 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.WEIGHTED_CENTROID
            if (r14 != r2) goto L_0x001d
            com.navibees.core.model.postioning.h.b r1 = r13.f1376c
            double r2 = r0.f1332x
            double r4 = r0.f1333y
            r1.mo29381a(r2, r4)
            com.navibees.core.model.postioning.h.b r1 = r13.f1376c
            double[] r1 = r1.mo29382a()
            goto L_0x0030
        L_0x001d:
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r2 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.TRILLATERATION
            if (r14 != r2) goto L_0x0030
            com.navibees.core.model.postioning.h.b r1 = r13.f1377d
            double r2 = r0.f1332x
            double r4 = r0.f1333y
            r1.mo29381a(r2, r4)
            com.navibees.core.model.postioning.h.b r1 = r13.f1377d
            double[] r1 = r1.mo29382a()
        L_0x0030:
            com.navibees.core.model.metadata.json.IndoorLocation r2 = new com.navibees.core.model.metadata.json.IndoorLocation
            r3 = 0
            r4 = r1[r3]
            r6 = 1
            r7 = r1[r6]
            r2.<init>(r4, r7)
            int r1 = r0.floor
            r2.floor = r1
            com.navibees.core.model.metadata.json.IndoorLocationConfidence r1 = r0.confidence
            r2.confidence = r1
            java.lang.String r1 = r0.buildingId
            r2.buildingId = r1
            java.lang.String r0 = r0.venueId
            r2.venueId = r0
            android.app.Application r0 = r13.f1393t
            com.navibees.core.NaviBeesManager r0 = com.navibees.core.NaviBeesManager.getInstance(r0)
            com.navibees.core.model.metadata.MetaDataManager r0 = r0.getMetaDataManager()
            androidx.collection.SimpleArrayMap r0 = r0.getBuildings()
            java.lang.String r1 = r2.buildingId
            java.lang.Object r0 = r0.get(r1)
            com.navibees.core.model.metadata.json.Building r0 = (com.navibees.core.model.metadata.json.Building) r0
            com.navibees.core.model.metadata.json.IndoorLocation r1 = new com.navibees.core.model.metadata.json.IndoorLocation
            double r4 = r2.f1332x
            double r7 = r2.f1333y
            r1.<init>(r4, r7)
            r4 = 0
            if (r0 == 0) goto L_0x0096
            androidx.collection.SimpleArrayMap<java.lang.Integer, java.util.List<com.navibees.core.model.metadata.json.IndoorLocationRestriction>> r0 = r0.restrictions
            if (r0 == 0) goto L_0x0096
            int r5 = r2.floor
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r0.get(r5)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x0096
            int r5 = r0.size()
            if (r5 <= 0) goto L_0x0096
            com.navibees.core.model.postioning.a$a r0 = com.navibees.core.model.postioning.C1694a.m1036b(r2, r0)
            com.navibees.core.model.metadata.json.IndoorLocation r4 = r0.f1407b
            com.navibees.core.model.metadata.json.LineIndoorLocationRestriction r0 = r0.f1406a
            double r7 = r4.f1332x
            r2.f1332x = r7
            double r4 = r4.f1333y
            r2.f1333y = r4
            goto L_0x0097
        L_0x0096:
            r0 = r4
        L_0x0097:
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r4 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.WEIGHTED_CENTROID
            if (r14 != r4) goto L_0x009e
            int r4 = pathConfidence
            goto L_0x00a0
        L_0x009e:
            int r4 = pathConfidence_Tri
        L_0x00a0:
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r5 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.WEIGHTED_CENTROID
            if (r14 != r5) goto L_0x00a7
            com.navibees.core.model.metadata.json.IndoorLocation r5 = f1371D
            goto L_0x00a9
        L_0x00a7:
            com.navibees.core.model.metadata.json.IndoorLocation r5 = f1372E
        L_0x00a9:
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r7 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.WEIGHTED_CENTROID
            if (r14 != r7) goto L_0x00b0
            com.navibees.core.model.metadata.json.IndoorLocationRestriction r7 = r13.f1388o
            goto L_0x00b2
        L_0x00b0:
            com.navibees.core.model.metadata.json.IndoorLocationRestriction r7 = r13.f1389p
        L_0x00b2:
            boolean r8 = PATH_CONFIDENCE_ON
            if (r8 == 0) goto L_0x00f3
            if (r5 == 0) goto L_0x00f3
            int r8 = r5.floor
            int r9 = r2.floor
            if (r8 != r9) goto L_0x00f3
            double r8 = com.navibees.core.model.postioning.C1697c.m1064d(r5, r2)
            r15.locDifference = r8
            double r10 = LOCATION_DIFF_RANGE
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x00e7
            int r8 = PATH_INC_CONFIDENCE
            int r4 = r4 - r8
            int r8 = pathSwitchingDecisionPercentage
            if (r4 >= r8) goto L_0x00d6
            int r4 = initialPathConfidence
            r13.f1394u = r3
            goto L_0x00f4
        L_0x00d6:
            com.navibees.core.model.metadata.json.IndoorLocationRestriction r3 = r13.f1388o
            if (r3 == 0) goto L_0x00f3
            com.navibees.core.model.metadata.json.IndoorLocation r0 = r7.calculateNewCoordinates(r1)
            r15.modifiedLocation = r0
            int r0 = r13.f1394u
            int r0 = r0 + r6
            r13.f1394u = r0
            r0 = r7
            goto L_0x00f4
        L_0x00e7:
            int r1 = PATH_INC_CONFIDENCE
            int r4 = r4 + r1
            int r1 = PATH_MAX_CONFIDENCE
            int r4 = java.lang.Math.min(r4, r1)
            r13.f1394u = r3
            goto L_0x00f4
        L_0x00f3:
        L_0x00f4:
            r15.indoorLocation = r5
            r15.f1367pc = r4
            int r1 = r13.f1394u
            r15.pcCounter = r1
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r1 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.WEIGHTED_CENTROID
            if (r14 != r1) goto L_0x0108
            r13.m1019b(r2)
            r13.f1388o = r0
            pathConfidence = r4
            goto L_0x0113
        L_0x0108:
            com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM r1 = com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM.TRILLATERATION
            if (r14 != r1) goto L_0x0113
            r13.m1021c(r2)
            r13.f1389p = r0
            pathConfidence_Tri = r4
        L_0x0113:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.model.postioning.PositionManager.m1018b(com.navibees.core.model.postioning.PositionLocator$LOCALIZER_ALGORITHM, java.util.List):com.navibees.core.model.postioning.PositionLocator$CalculatedLocation");
    }

    /* renamed from: c */
    private void m1021c(IndoorLocation indoorLocation) {
        int i = f1373F;
        if (i == -1) {
            f1372E = indoorLocation;
            f1373F = indoorLocation.floor;
            changeFloorCounterTri = 0;
        } else {
            int i2 = indoorLocation.floor;
            if (i2 != f1372E.floor) {
                if (i2 == i) {
                    int i3 = changeFloorCounterTri + 1;
                    changeFloorCounterTri = i3;
                    changeFloorCounterTri = Math.min(i3, 4);
                    if (changeFloorCounterTri >= 4) {
                        f1372E = indoorLocation;
                        changeFloorCounterTri = 0;
                    }
                } else {
                    changeFloorCounterTri = 0;
                }
                f1373F = indoorLocation.floor;
            } else {
                changeFloorCounterTri = 0;
                f1372E = indoorLocation;
                f1373F = f1372E.floor;
            }
        }
        currentFloorTri = f1372E.floor;
        newFloorTri = f1373F;
    }

    /* renamed from: d */
    private boolean m1023d(IndoorLocation indoorLocation) {
        IndoorLocation convertLatLongToUTM = NaviBeesUtils.convertLatLongToUTM(indoorLocation);
        Venue currentVenue = NaviBeesManager.getInstance(this.f1393t).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            OutdoorRegion outdoorRegion = currentVenue.outdoorRegion;
            if (outdoorRegion != null) {
                return outdoorRegion.contains(convertLatLongToUTM);
            }
        }
        return false;
    }

    /* renamed from: a */
    private CalculatedLocation m1015a(LOCALIZER_ALGORITHM localizer_algorithm, List<BeaconNode> list) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        PositionLocator positionLocator;
        if (list == null || list.size() <= 0) {
            return null;
        }
        if (C1692b.f1401a[localizer_algorithm.ordinal()] != 1) {
            positionLocator = new C1710g(this.f1393t);
        } else {
            positionLocator = new C1706e(this.f1393t);
        }
        return positionLocator.mo29283a(list);
    }

    /* renamed from: a */
    private IndoorLocationRestriction m1014a(IndoorLocation indoorLocation, List<IndoorLocationRestriction> list) {
        double d;
        IndoorLocation indoorLocation2 = indoorLocation;
        List<IndoorLocationRestriction> list2 = list;
        double d2 = indoorLocation2.f1332x;
        double d3 = indoorLocation2.f1333y;
        char c = 0;
        IndoorLocationRestriction indoorLocationRestriction = null;
        double d4 = 2.147483647E9d;
        double d5 = d2;
        int i = 0;
        while (i < list.size()) {
            double[] isInsideWithinRange = ((IndoorLocationRestriction) list2.get(i)).isInsideWithinRange(indoorLocation2);
            boolean z = isInsideWithinRange[c] != 0.0d;
            double d6 = isInsideWithinRange[1];
            if (!z || d6 >= d4) {
                d = d5;
            } else {
                IndoorLocation calculateNewCoordinates = ((IndoorLocationRestriction) list2.get(i)).calculateNewCoordinates(indoorLocation2);
                d = d5;
                double d7 = d6;
                if (calculateNewCoordinates.f1332x != indoorLocation2.f1332x || calculateNewCoordinates.f1333y != indoorLocation2.f1333y) {
                    indoorLocationRestriction = (IndoorLocationRestriction) list2.get(i);
                    d = calculateNewCoordinates.f1332x;
                    d3 = calculateNewCoordinates.f1333y;
                    d4 = d7;
                }
            }
            i++;
            d5 = d;
            c = 0;
        }
        indoorLocation2.f1332x = d5;
        indoorLocation2.f1333y = d3;
        return indoorLocationRestriction;
    }

    /* renamed from: b */
    private void m1019b(IndoorLocation indoorLocation) {
        int i = f1370C;
        if (i == -1) {
            f1371D = indoorLocation;
            f1370C = indoorLocation.floor;
            changeFloorCounter = 0;
        } else {
            int i2 = indoorLocation.floor;
            if (i2 != f1371D.floor) {
                if (i2 == i) {
                    int i3 = changeFloorCounter + 1;
                    changeFloorCounter = i3;
                    changeFloorCounter = Math.min(i3, 4);
                    if (changeFloorCounter >= 4) {
                        f1371D = indoorLocation;
                        changeFloorCounter = 0;
                    }
                } else {
                    changeFloorCounter = 0;
                }
                f1370C = indoorLocation.floor;
            } else {
                changeFloorCounter = 0;
                f1371D = indoorLocation;
                f1370C = f1371D.floor;
            }
        }
        currentFloor = f1371D.floor;
        newFloor = f1370C;
    }
}
