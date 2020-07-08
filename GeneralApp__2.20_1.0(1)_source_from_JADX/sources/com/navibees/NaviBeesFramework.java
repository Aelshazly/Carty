package com.navibees;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.routing.C1464a;
import com.navibees.routing.GetNearestPOIsCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NaviBeesFramework {
    public static final double DEFAULT_DISTANCE = 200.0d;
    public static final int DEFAULT_MAX_NUMBER = 20;
    public static final double INFINITE_DISTANCE = -1.0d;
    public static final int INFINITE_NUMBER = -1;

    /* renamed from: a */
    private static List<POI> m140a(Application application) {
        Venue currentVenue = NaviBeesManager.getInstance(application).getMetaDataManager().getCurrentVenue();
        if (currentVenue == null) {
            return null;
        }
        return currentVenue.getPOIs();
    }

    public static void getAllPOIs(Activity activity, IndoorLocation indoorLocation, double d, int i, String str, List<POICategory> list, GetNearestPOIsCallback getNearestPOIsCallback) {
        List a = m141a(activity.getApplication(), str, list);
        if (a == null) {
            getNearestPOIsCallback.onNearestPOIsFailure("No venue data is available");
        } else {
            getNearestPOIsCallback.onNearestPOIsFound(a);
        }
    }

    public static void getNearestPOIWithDuration(Activity activity, IndoorLocation indoorLocation, double d, int i, String str, List<POICategory> list, GetNearestPOIsCallback getNearestPOIsCallback) {
        List a = m140a(activity.getApplication());
        if (a == null) {
            getNearestPOIsCallback.onNearestPOIsFailure("No venue data is available");
            return;
        }
        new C1464a().mo28525a(activity, null, indoorLocation, a, d, i, getNearestPOIsCallback);
    }

    public static void getNearestPOIs(Activity activity, POI poi, double d, int i, String str, List<POICategory> list, GetNearestPOIsCallback getNearestPOIsCallback) {
        String str2 = str;
        List<POICategory> list2 = list;
        List a = m141a(activity.getApplication(), str, list);
        if (a == null) {
            getNearestPOIsCallback.onNearestPOIsFailure("No venue data is available");
            return;
        }
        new C1464a().mo28526a(activity, poi, a, d, i, getNearestPOIsCallback);
    }

    public static void setFontForLanguage(Context context, String str, String str2) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(String.format(NaviBeesConstants.FONT_FOR_LANGUAGE_KEY, new Object[]{str2}), str);
        edit.apply();
    }

    /* renamed from: a */
    private static List<POI> m141a(Application application, String str, List<POICategory> list) {
        List<POI> list2;
        Venue currentVenue = NaviBeesManager.getInstance(application).getMetaDataManager().getCurrentVenue();
        if (currentVenue == null) {
            return null;
        }
        if (list == null && str == null) {
            list2 = currentVenue.getPOIs();
        } else {
            HashMap hashMap = new HashMap();
            if (list != null) {
                for (POICategory pOICategory : list) {
                    for (POI poi : currentVenue.getPOIsOfCategory(pOICategory.f1340id)) {
                        hashMap.put(poi.f1339id, poi);
                    }
                }
            }
            if (str != null) {
                for (POI poi2 : NaviBeesManager.getInstance(application).getMetaDataManager().searchInPOIs(str)) {
                    if (!hashMap.containsKey(poi2.f1339id)) {
                        hashMap.put(poi2.f1339id, poi2);
                    }
                }
            }
            list2 = new ArrayList<>(hashMap.values());
        }
        return list2;
    }

    public static void getNearestPOIs(Activity activity, IndoorLocation indoorLocation, double d, int i, String str, List<POICategory> list, GetNearestPOIsCallback getNearestPOIsCallback) {
        String str2 = str;
        List<POICategory> list2 = list;
        List a = m141a(activity.getApplication(), str, list);
        if (a == null) {
            getNearestPOIsCallback.onNearestPOIsFailure("No venue data is available");
            return;
        }
        new C1464a().mo28524a(activity, indoorLocation, a, d, i, getNearestPOIsCallback);
    }
}
