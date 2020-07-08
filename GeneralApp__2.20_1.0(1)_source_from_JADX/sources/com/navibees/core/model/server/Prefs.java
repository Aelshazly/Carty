package com.navibees.core.model.server;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Prefs {
    private static final String LAST_SYNC_DATE = "LAST_SYNC_DATE";
    private static final String LICENSE_DURATION = "LICENSE_DURATION";
    private static final String LICENSE_FEATURE_STR = "LICENSE_FEATURE_STR";
    private static final String LICENSE_START_DATE = "LICENSE_START_DATE";
    private static Prefs ourInstance;
    private SharedPreferences metaDataPref;
    private Editor metaDataPrefEditor = this.metaDataPref.edit();

    private Prefs(Context context) {
        this.metaDataPref = context.getSharedPreferences("navibeesPrefs", 0);
    }

    public static Prefs getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new Prefs(context);
        }
        return ourInstance;
    }

    public int getInt(String str) {
        return this.metaDataPref.getInt(str, -1);
    }

    public String getLastSyncDate() {
        return this.metaDataPref.getString(LAST_SYNC_DATE, null);
    }

    public int getLicenseDuration() {
        return this.metaDataPref.getInt(LICENSE_DURATION, -1);
    }

    public int getLicenseFeatureStr() {
        return this.metaDataPref.getInt(LICENSE_FEATURE_STR, -1);
    }

    public String getLicenseStartDate() {
        return this.metaDataPref.getString(LICENSE_START_DATE, null);
    }

    public String getString(String str) {
        return this.metaDataPref.getString(str, null);
    }

    public void save(String str, int i) {
        this.metaDataPrefEditor.putInt(str, i).commit();
    }

    public void saveLastSyncDate(String str) {
        this.metaDataPrefEditor.putString(LAST_SYNC_DATE, str).commit();
    }

    public void saveLicenseDuration(int i) {
        this.metaDataPrefEditor.putInt(LICENSE_DURATION, i);
    }

    public void saveLicenseFeatureStr(int i) {
        this.metaDataPrefEditor.putInt(LICENSE_FEATURE_STR, i).commit();
    }

    public void saveLicenseStartDate(String str) {
        this.metaDataPrefEditor.putString(LICENSE_START_DATE, str).commit();
    }

    public void save(String str, String str2) {
        this.metaDataPrefEditor.putString(str, str2).commit();
    }
}
