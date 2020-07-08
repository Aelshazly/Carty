package com.navibees.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import androidx.preference.PreferenceManager;
import java.util.Set;

/* renamed from: com.navibees.core.a */
/* compiled from: NaviBeesSettings */
public class C1556a {

    /* renamed from: a */
    public static boolean f953a = true;

    /* renamed from: b */
    private static String f954b = "<unknown>";

    /* renamed from: a */
    public static String m629a() {
        return "Android";
    }

    /* renamed from: a */
    public static String m630a(Application application) {
        return NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.identifier;
    }

    /* renamed from: b */
    public static String m633b(Context context) {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        try {
            if (str2.startsWith(str)) {
                return m632a(str2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(m632a(str));
            sb.append(" ");
            sb.append(str2);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return f954b;
        }
    }

    /* renamed from: c */
    public static String m634c(Context context) {
        return VERSION.RELEASE;
    }

    /* renamed from: d */
    public static String m635d(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    /* renamed from: e */
    public static boolean m636e(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = NaviBeesConstants.MAP_RESOURCES_APP_VERSION_KEY;
        int i = defaultSharedPreferences.getInt(str, -1);
        boolean z = true;
        try {
            int i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            if (i2 > i) {
                Editor edit = defaultSharedPreferences.edit();
                edit.putInt(str, i2);
                edit.apply();
            } else {
                z = false;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (z) {
            Set<String> keySet = defaultSharedPreferences.getAll().keySet();
            Editor edit2 = defaultSharedPreferences.edit();
            for (String str2 : keySet) {
                if (str2.startsWith(NaviBeesConstants.BUILDING_SYNC_ALL_PREFIX)) {
                    edit2.remove(str2);
                }
            }
            edit2.remove(NaviBeesConstants.STATUS_LAST_UPDATED_DATE);
            edit2.apply();
        }
        return z;
    }

    /* renamed from: a */
    public static String m631a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return f954b;
        }
    }

    /* renamed from: a */
    private static String m632a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (char c : charArray) {
            if (!z || !Character.isLetter(c)) {
                if (Character.isWhitespace(c)) {
                    z = true;
                }
                sb.append(c);
            } else {
                sb.append(Character.toUpperCase(c));
                z = false;
            }
        }
        return sb.toString();
    }
}
