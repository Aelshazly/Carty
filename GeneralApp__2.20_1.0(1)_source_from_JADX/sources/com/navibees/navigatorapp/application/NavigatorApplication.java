package com.navibees.navigatorapp.application;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat.BigTextStyle;
import androidx.core.app.NotificationCompat.Builder;
import androidx.multidex.MultiDex;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import com.navibees.NaviBeesFramework;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.postioning.NBLocationListener;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.models.Point;
import com.navibees.navigatorapp.utils.LocaleUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class NavigatorApplication extends NaviBeesApplication {
    private String CHANNEL_ID = "Navibees";
    private BroadcastReceiver LocaleChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String locale = Locale.getDefault().getCountry().toLowerCase();
            if ("ae".equals(locale)) {
                locale = "ar";
            }
            if (!NavigatorApplication.this.selectedLang.equals(locale)) {
                NavigatorApplication navigatorApplication = NavigatorApplication.this;
                Locale locale2 = navigatorApplication.getResources().getConfiguration().locale;
                navigatorApplication.selectedLang = Locale.getDefault().getLanguage();
                NavigatorApplication.this.f203sp.edit().putString("com.navibees.source.multivenuessample.language", NavigatorApplication.this.selectedLang).commit();
                NaviBeesManager.changeLanguage(NavigatorApplication.this.getApplicationContext(), NavigatorApplication.this.selectedLang);
            }
        }
    };
    private HashMap<String, String> buildingProperties;
    private List<LatLng> innerRegionLatLngs = new ArrayList();
    private boolean isInside = false;
    NBLocationListener nbLocationListener = new NBLocationListener() {
        public void locationCallback(IndoorLocation indoorLocation, IndoorLocation indoorLocation1, int i, boolean b, boolean b1) {
            if (indoorLocation1 != null) {
                NavigatorApplication.this.showNotification(new Point(indoorLocation1.f1332x, indoorLocation1.f1333y).getLatLng(NavigatorApplication.this));
            }
        }

        public boolean isBackground() {
            return false;
        }
    };
    private List<LatLng> outerRegionLatLngs = new ArrayList();
    long previousTimeStamp = 0;
    private List<LatLng> regionLatLngs = new ArrayList();
    /* access modifiers changed from: private */
    public String selectedLang;
    /* access modifiers changed from: private */

    /* renamed from: sp */
    public SharedPreferences f203sp;
    private HashMap<String, String> venueProperties;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void onCreate() {
        super.onCreate();
        registerReceiver(this.LocaleChangedReceiver, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
        this.f203sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str = "com.navibees.source.multivenuessample.language";
        this.selectedLang = this.f203sp.getString(str, null);
        if (TextUtils.isEmpty(this.selectedLang)) {
            Locale locale = getResources().getConfiguration().locale;
            this.selectedLang = Locale.getDefault().getLanguage();
            this.f203sp.edit().putString(str, this.selectedLang).commit();
            NaviBeesManager.changeLanguage(getApplicationContext(), this.selectedLang);
        }
        Locale locale2 = new Locale(this.selectedLang);
        Locale.setDefault(locale2);
        Resources resources = getApplicationContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale2;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        NaviBeesFramework.setFontForLanguage(getApplicationContext(), "andlso.ttf", "ar");
    }

    /* access modifiers changed from: private */
    public synchronized void showNotification(LatLng latLng) {
        long currentTimeStamp = new Date().getTime();
        if ((currentTimeStamp - this.previousTimeStamp) / 1000 >= 30 && PolyUtil.containsLocation(latLng, this.regionLatLngs, false)) {
            this.isInside = true;
            this.previousTimeStamp = currentTimeStamp;
            notify("", getString(C1170R.string.hadith));
        }
    }

    private synchronized void notify(String title, String text) {
        ((NotificationManager) getSystemService("notification")).notify(0, new Builder(this, this.CHANNEL_ID).setSmallIcon(C1170R.C1172drawable.ic_stat_notification_icon).setContentTitle(title).setContentText(text).setStyle(new BigTextStyle().bigText(text)).setPriority(2).setAutoCancel(true).build());
    }

    private void createNotificationChannel() {
        if (VERSION.SDK_INT >= 26) {
            CharSequence name = this.CHANNEL_ID;
            String description = this.CHANNEL_ID;
            NotificationChannel channel = new NotificationChannel(this.CHANNEL_ID, name, 4);
            channel.setDescription(description);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(channel);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleUtils.updateConfig(this, newConfig);
    }
}
