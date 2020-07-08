package com.navibees.navigatorapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.navibees.navigatorapp.models.AgendaData;

public class Prefs {
    public static String AGENDA = "agenda";
    public static String IS_ADMIN = "isAdmin";
    private static final String OPERATION_DATA = "OPERATION_DATA";
    private static final String OPERATION_IS_USER_ADMIN = "OPERATION_IS_USER_ADMIN";
    private static final String OPERATION_USERNAME_DATA = "OPERATION_USERNAME_DATA";
    private static final String OPERATION_VENUE_ID = "OPERATION_VENUE_ID";
    private static final String OPERATION_VENUE_NAME_DATA = "OPERATION_VENUE_NAME_DATA";
    private static Prefs ourInstance;
    private Editor editor = this.prefs.edit();
    private SharedPreferences prefs;

    public static Prefs getInstance(Context applicationContext) {
        if (ourInstance == null) {
            ourInstance = new Prefs(applicationContext);
        }
        return ourInstance;
    }

    private Prefs(Context mContext) {
        this.prefs = mContext.getSharedPreferences("operationsPrefs", 0);
    }

    public void saveOperationData(String operationData) {
        this.editor.putString(OPERATION_DATA, operationData).commit();
    }

    public void saveOperationUsername(String username) {
        this.editor.putString(OPERATION_USERNAME_DATA, username).commit();
    }

    public void saveOperationVenue(String venueName, String venueId) {
        this.editor.putString(OPERATION_VENUE_NAME_DATA, venueName);
        this.editor.putString(OPERATION_VENUE_ID, venueId);
        this.editor.commit();
    }

    public void saveOperationUserType(boolean userType) {
        this.editor.putBoolean(OPERATION_IS_USER_ADMIN, userType).commit();
    }

    public void saveKey(String key, String val) {
        this.editor = this.prefs.edit();
        this.editor.putString(key, val);
        this.editor.apply();
    }

    public void saveKey(String key, int val) {
        this.editor = this.prefs.edit();
        this.editor.putInt(key, val);
        this.editor.apply();
    }

    public void saveKey(String key, boolean val) {
        this.editor = this.prefs.edit();
        this.editor.putBoolean(key, val);
        this.editor.apply();
    }

    public String getStringValue(String key) {
        return this.prefs.getString(key, null);
    }

    public int getIntValue(String key) {
        return this.prefs.getInt(key, -1);
    }

    public boolean getBooleanValue(String key) {
        return this.prefs.getBoolean(key, false);
    }

    public void saveAgendaData(AgendaData val) {
        this.editor = this.prefs.edit();
        this.editor.putString(AGENDA, val.toString());
        this.editor.apply();
    }

    public AgendaData getAgendaData() {
        return AgendaData.fromString(this.prefs.getString(AGENDA, null));
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, -1);
    }

    public String getString(String key) {
        return this.prefs.getString(key, null);
    }

    public void save(String key, int val) {
        this.editor.putInt(key, val).commit();
    }

    public String getOperationData() {
        return this.prefs.getString(OPERATION_DATA, null);
    }

    public String getOperationUsername() {
        return this.prefs.getString(OPERATION_USERNAME_DATA, "");
    }

    public String getOperationVenueName() {
        return this.prefs.getString(OPERATION_VENUE_NAME_DATA, "");
    }

    public String getOperationVenueId() {
        return this.prefs.getString(OPERATION_VENUE_ID, "");
    }

    public boolean getOperationUserIsAdmin() {
        return this.prefs.getBoolean(OPERATION_IS_USER_ADMIN, false);
    }

    public void clearPref() {
        saveOperationUsername("");
    }
}
