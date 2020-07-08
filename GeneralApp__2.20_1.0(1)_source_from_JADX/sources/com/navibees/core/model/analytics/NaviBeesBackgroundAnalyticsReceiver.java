package com.navibees.core.model.analytics;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesConstants;

public class NaviBeesBackgroundAnalyticsReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static final long f1243a = 60000;

    public static void startBackgroundAnalytics(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = NaviBeesConstants.BACKGROUND_ANALYTICS_ENABLED_KEY;
        if (!defaultSharedPreferences.getBoolean(str, false)) {
            long j = 0;
            long currentTimeMillis = System.currentTimeMillis() - defaultSharedPreferences.getLong(NaviBeesConstants.BACKGROUND_ANALYTICS_LAST_TIMESTAMP_KEY, 0);
            if (currentTimeMillis < f1243a) {
                j = f1243a - currentTimeMillis;
            }
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(2, SystemClock.elapsedRealtime() + j, f1243a, PendingIntent.getBroadcast(context, 7, new Intent(context, NaviBeesBackgroundAnalyticsReceiver.class), 134217728));
            Editor edit = defaultSharedPreferences.edit();
            edit.putBoolean(str, true);
            edit.apply();
        }
    }

    public static void stopBackgroundAnalytics(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = NaviBeesConstants.BACKGROUND_ANALYTICS_ENABLED_KEY;
        if (defaultSharedPreferences.getBoolean(str, false)) {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 7, new Intent(context, NaviBeesBackgroundAnalyticsReceiver.class), 134217728));
            Editor edit = defaultSharedPreferences.edit();
            edit.putBoolean(str, false);
            edit.apply();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (VERSION.SDK_INT < 26) {
            context.startService(new Intent(context, NaviBeesBackgroundAnalyticsService.class));
        }
    }
}
