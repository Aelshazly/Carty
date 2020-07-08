package com.navibees.core.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.Button;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.jhlabs.map.Point2D.Double;
import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Brand;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.core.model.metadata.json.Portal;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.sdk.C1266R;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class NaviBeesUtils {

    /* renamed from: a */
    static final String f1610a = "CommonUtils";

    /* renamed from: b */
    private static final Pattern f1611b = Pattern.compile("^(ar|dv|he|iw|fa|nqo|ps|sd|ug|ur|yi|.*[-_](Arab|Hebr|Thaa|Nkoo|Tfng))(?!.*[-_](Latn|Cyrl)($|-|_))($|-|_)");

    /* renamed from: c */
    private static Projection f1612c;

    /* renamed from: d */
    private static String f1613d = "en";

    /* renamed from: b */
    private static int m1182b(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = "navibees_unique_notification_id";
        int i = defaultSharedPreferences.getInt(str, 1);
        Editor edit = defaultSharedPreferences.edit();
        edit.putInt(str, i + 1);
        edit.apply();
        return i;
    }

    @TargetApi(29)
    /* renamed from: c */
    private static boolean m1183c(Context context) {
        return context.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0;
    }

    public static HashMap<String, String> convertBundleToMap(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public static IndoorLocation convertLatLongToUTM(IndoorLocation indoorLocation) {
        if (f1612c == null) {
            return null;
        }
        Double transform = f1612c.transform(new Double(indoorLocation.f1332x, indoorLocation.f1333y), new Double());
        IndoorLocation indoorLocation2 = new IndoorLocation(transform.f1626x, transform.f1627y, indoorLocation.floor);
        indoorLocation2.buildingId = indoorLocation.buildingId;
        indoorLocation2.venueId = indoorLocation.venueId;
        indoorLocation2.confidence = indoorLocation.confidence;
        return indoorLocation2;
    }

    public static Bundle convertMapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (Entry entry : map.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        return bundle;
    }

    public static IndoorLocation convertUTMToLatLong(IndoorLocation indoorLocation) {
        if (f1612c == null || indoorLocation == null) {
            return null;
        }
        Double inverseTransform = f1612c.inverseTransform(new Double(indoorLocation.f1332x, indoorLocation.f1333y), new Double());
        IndoorLocation indoorLocation2 = new IndoorLocation(inverseTransform.f1626x, inverseTransform.f1627y, indoorLocation.floor);
        indoorLocation2.buildingId = indoorLocation.buildingId;
        indoorLocation2.venueId = indoorLocation.venueId;
        indoorLocation2.confidence = indoorLocation.confidence;
        return indoorLocation2;
    }

    public static void createProjection(String str) {
        f1612c = ProjectionFactory.fromPROJ4Specification(m1181a(str));
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("convertUTMToLatLong:PROJ4Description:");
        sb.append(f1612c.getPROJ4Description());
        printStream.println(sb.toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static void m1184d(Context context) {
        if (m1180a() && !m1183c(context)) {
            requestLocationPermission((Activity) context);
        } else if (isMarshmallowOrAbove() && !isLocationServicesGranted(context)) {
            requestLocationPermission((Activity) context);
        } else if (isMarshmallowOrAbove() && isLocationServicesGranted(context) && !isLocationServicesEnabled(context)) {
            m1186f(context);
        }
    }

    public static void disableTTS(Context context) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putBoolean(context.getResources().getString(C1266R.string.com_navibees_sdk_preference_user_enabled_tts_key), false);
        edit.commit();
    }

    public static void dumpIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String str = f1610a;
            Log.m1173i(str, "Dumping Intent start");
            for (String str2 : extras.keySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                sb.append(str2);
                sb.append("=");
                sb.append(extras.get(str2));
                sb.append("]");
                Log.m1173i(str, sb.toString());
            }
            Log.m1173i(str, "Dumping Intent end");
        }
    }

    /* renamed from: e */
    private static void m1185e(final Context context) {
        Builder builder = new Builder(context);
        builder.setTitle(C1266R.string.enable_bluetooth_dialog_title);
        builder.setMessage(C1266R.string.enable_bluetooth_dialog_message);
        builder.setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BluetoothAdapter.getDefaultAdapter().enable();
                NaviBeesUtils.m1184d(context);
            }
        });
        builder.setNegativeButton(17039369, null);
        builder.show();
    }

    public static void enableTTS(Context context) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putBoolean(context.getResources().getString(C1266R.string.com_navibees_sdk_preference_user_enabled_tts_key), true);
        edit.commit();
    }

    /* renamed from: f */
    private static void m1186f(final Context context) {
        GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(LocationServices.API).build();
        build.connect();
        LocationRequest create = LocationRequest.create();
        create.setPriority(100);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(create);
        builder.setNeedBle(true);
        builder.setAlwaysShow(true);
        LocationServices.SettingsApi.checkLocationSettings(build, builder.build()).setResultCallback(new ResultCallback<LocationSettingsResult>() {
            public void onResult(LocationSettingsResult locationSettingsResult) {
                Status status = locationSettingsResult.getStatus();
                if (status.getStatusCode() == 6) {
                    try {
                        status.startResolutionForResult((Activity) context, 6);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static String getAppLang() {
        return f1613d;
    }

    public static Bitmap getBitmapFromURL(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static float getImageFactor(Context context, float f) {
        return context.getResources().getDisplayMetrics().density / f;
    }

    public static int[] getNotificationLargeIconSize(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i >= 640) {
            return new int[]{256, 256};
        }
        if (i >= 480) {
            return new int[]{192, 192};
        }
        if (i >= 320) {
            return new int[]{128, 128};
        }
        if (i >= 240) {
            return new int[]{96, 96};
        }
        if (i >= 160) {
            return new int[]{64, 64};
        }
        return new int[]{48, 48};
    }

    public static VisioPOI getPOIFromPlaceID(Application application, String str) {
        SimpleArrayMap buildings = NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings();
        int i = 0;
        while (buildings != null && i < buildings.size()) {
            if (((Building) buildings.valueAt(i)).pois != null) {
                Iterator it = ((Building) buildings.valueAt(i)).pois.iterator();
                while (it.hasNext()) {
                    VisioPOI visioPOI = (VisioPOI) it.next();
                    if (visioPOI.vgId.equals(str)) {
                        return visioPOI;
                    }
                }
                continue;
            }
            i++;
        }
        return null;
    }

    public static VisioPOI getPOIFromPlaceIDUsingFacilities(Application application, String str) {
        SimpleArrayMap buildings = NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings();
        int i = 0;
        while (buildings != null && i < buildings.size()) {
            if (((Building) buildings.valueAt(i)).facilities != null) {
                for (Facility pois : ((Building) buildings.valueAt(i)).facilities) {
                    List<VisioPOI> pois2 = pois.getPois();
                    if (pois2 != null && pois2.size() > 0) {
                        for (VisioPOI visioPOI : pois2) {
                            if (visioPOI.vgId.equals(str)) {
                                return visioPOI;
                            }
                        }
                        continue;
                    }
                }
                continue;
            }
            i++;
        }
        return null;
    }

    public static HashMap<String, Boolean> getPortalTypeStatusForUser(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        HashMap<String, Boolean> hashMap = new HashMap<>();
        Boolean valueOf = Boolean.valueOf(defaultSharedPreferences.getBoolean(context.getString(C1266R.string.com_navibees_sdk_preference_portal_elevator_key), true));
        Boolean valueOf2 = Boolean.valueOf(defaultSharedPreferences.getBoolean(context.getString(C1266R.string.com_navibees_sdk_preference_portal_stair_key), true));
        Boolean valueOf3 = Boolean.valueOf(defaultSharedPreferences.getBoolean(context.getString(C1266R.string.com_navibees_sdk_preference_portal_escalator_key), true));
        hashMap.put(Portal.PORTAL_TYPE[0], valueOf);
        hashMap.put(Portal.PORTAL_TYPE[1], valueOf2);
        hashMap.put(Portal.PORTAL_TYPE[2], valueOf3);
        return hashMap;
    }

    public static VisioPOI getVisioPOI(Application application, String str) {
        VisioPOI pOIFromPlaceID = getPOIFromPlaceID(application, str);
        return pOIFromPlaceID == null ? getPOIFromPlaceIDUsingFacilities(application, str) : pOIFromPlaceID;
    }

    public static boolean isAppInForeground(Context context) {
        boolean z = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getBoolean(NaviBeesConstants.IS_APP_IN_FOREGROUND_KEY, false);
        StringBuilder sb = new StringBuilder();
        sb.append("isAppInForeground:");
        sb.append(z);
        Log.m1173i(f1610a, sb.toString());
        return z;
    }

    public static Boolean isArabicLang() {
        return Boolean.valueOf(Locale.getDefault().getLanguage().equals("ar"));
    }

    public static boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static boolean isCategoryMatch(String[] strArr, POICategory pOICategory) {
        return (strArr == null || pOICategory == null || !searchInString(pOICategory.name.getText(), strArr)) ? false : true;
    }

    public static boolean isInternetConnectionOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            int i = VERSION.SDK_INT;
            String str = "Connected Network Name: ";
            String str2 = f1610a;
            if (i >= 21) {
                for (Network networkInfo : connectivityManager.getAllNetworks()) {
                    NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(networkInfo);
                    StringBuilder sb = new StringBuilder();
                    sb.append(" Network Name : ");
                    sb.append(networkInfo2.getTypeName());
                    Log.m1173i(str2, sb.toString());
                    if (networkInfo2.getState().equals(State.CONNECTED)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(networkInfo2.getTypeName());
                        Log.m1173i(str2, sb2.toString());
                        return true;
                    }
                }
            } else {
                NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                if (allNetworkInfo != null) {
                    for (NetworkInfo networkInfo3 : allNetworkInfo) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Network Name: ");
                        sb3.append(networkInfo3.getTypeName());
                        Log.m1173i(str2, sb3.toString());
                        if (networkInfo3.getState() == State.CONNECTED) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(str);
                            sb4.append(networkInfo3.getTypeName());
                            Log.m1173i(str2, sb4.toString());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isLangRTL() {
        String str = f1613d;
        return str != null && f1611b.matcher(str).find();
    }

    public static boolean isLocationServicesEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Param.LOCATION);
        return locationManager != null && (locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps"));
    }

    @TargetApi(23)
    public static boolean isLocationServicesGranted(Context context) {
        return context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public static boolean isMarshmallowOrAbove() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean isPOIMatch(String[] strArr, POI poi, HashMap<String, Brand> hashMap) {
        if (!(strArr == null || poi == null)) {
            if (searchInString(poi.name.getText(), strArr)) {
                return true;
            }
            List<String> list = poi.tags;
            if (list != null && list.size() > 0) {
                for (String searchInString : poi.tags) {
                    if (searchInString(searchInString, strArr)) {
                        return true;
                    }
                }
            }
            if (hashMap == null) {
                return false;
            }
            String str = poi.mainBrandId;
            if (str != null && hashMap.get(str) != null && searchInString(((Brand) hashMap.get(poi.mainBrandId)).name.getText(), strArr)) {
                return true;
            }
            List<String> list2 = poi.relatedBrandIds;
            if (list2 != null) {
                for (String str2 : list2) {
                    if (hashMap.get(str2) != null && searchInString(((Brand) hashMap.get(str2)).name.getText(), strArr)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isRTL() {
        byte directionality = Character.getDirectionality(Locale.getDefault().getDisplayName().charAt(0));
        return directionality == 1 || directionality == 2;
    }

    public static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr, Activity activity) {
        String str = "location access granted";
        String str2 = "AppManager";
        if (i == 3) {
            if (iArr != null && iArr.length > 0 && iArr[0] == 0) {
                Log.m1171d(str2, str);
                m1184d(activity);
                if (NaviBeesManager.getInstance(activity.getApplication()).getNaviBeesBeaconManager() != null) {
                    NaviBeesManager.getInstance(activity.getApplication()).getNaviBeesBeaconManager().bindBeaconManager();
                }
            } else if (iArr != null && iArr.length > 0 && iArr[0] == -1) {
                Builder builder = new Builder(activity);
                builder.setTitle(C1266R.string.request_location_permission_title);
                builder.setMessage(C1266R.string.location_permission_denied_message);
                builder.setPositiveButton(17039370, null);
                builder.show();
            }
        }
        if (i != 8) {
            return;
        }
        if (iArr != null && iArr.length > 0 && iArr[0] == 0) {
            Log.m1171d(str2, str);
            m1184d(activity);
            if (NaviBeesManager.getInstance(activity.getApplication()).getNaviBeesBeaconManager() != null) {
                NaviBeesManager.getInstance(activity.getApplication()).getNaviBeesBeaconManager().bindBeaconManager();
            }
        } else if (iArr != null && iArr.length > 0 && iArr[0] == -1) {
            Builder builder2 = new Builder(activity);
            builder2.setTitle(C1266R.string.request_location_permission_title);
            builder2.setMessage(C1266R.string.location_permission_denied_message);
            builder2.setPositiveButton(17039370, null);
            builder2.show();
        }
    }

    public static void recycle() {
        f1612c = null;
        f1613d = null;
    }

    @TargetApi(23)
    public static void requestLocationPermission(Activity activity) {
        activity.requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"}, 3);
    }

    public static boolean searchInString(String str, String[] strArr) {
        boolean z;
        String[] split = str.toUpperCase().split(" ");
        int i = 0;
        boolean z2 = false;
        while (i < strArr.length) {
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                } else if (split[i2].startsWith(strArr[i])) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            z2 = i == 0 ? z : z2 && z;
            i++;
        }
        return z2;
    }

    public static void setAppLang(String str) {
        f1613d = str;
    }

    public static float setTextSize(Button button, int i) {
        Paint paint = new Paint();
        paint.setTypeface(button.getTypeface());
        float textSize = button.getTextSize();
        paint.setTextSize(textSize);
        String charSequence = button.getText().toString();
        for (float measureText = paint.measureText(charSequence); measureText > ((float) i); measureText = paint.measureText(charSequence)) {
            textSize -= 1.0f;
            paint.setTextSize(textSize);
        }
        return textSize;
    }

    public static void showNeededPermissionsToReadBeacons(Context context) {
        if (!isBluetoothEnabled()) {
            m1185e(context);
        } else {
            m1184d(context);
        }
    }

    public static void showNotification(Context context, String str, String str2) {
        ((NotificationManager) context.getSystemService("notification")).notify(m1182b(context), new NotificationCompat.Builder(context).setSmallIcon(C1266R.C1268drawable.com_navibees_sdk_notification_small).setContentTitle(str).setContentText(str2).build());
    }

    /* renamed from: a */
    private static boolean m1180a() {
        return VERSION.SDK_INT >= 29;
    }

    /* renamed from: a */
    private static String[] m1181a(String str) {
        return str.split(" ");
    }
}
