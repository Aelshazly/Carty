package com.navibees.core;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.widget.Toast;
import androidx.preference.PreferenceManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.navibees.core.model.analytics.C1646a;
import com.navibees.core.model.analytics.NaviBeesBackgroundAnalyticsReceiver;
import com.navibees.core.model.license.C1660a;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.MetaDataManager;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.postioning.NaviBeesBeaconManager;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.model.routing.TurnByTurnNavigation;
import com.navibees.core.model.server.C1718a;
import com.navibees.core.model.server.OnSyncListener;
import com.navibees.core.model.server.ServerManager;
import com.navibees.core.model.tts.TTSManager;
import com.navibees.core.util.AssetsManager;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import java.util.Locale;

public final class NaviBeesManager {
    public static final String LANGUAGE_KEY = "com.navibees.preferred_language";
    public static boolean STEP_BY_STEP = false;
    private C1646a analyticsManager;
    private AssetsManager assetsManager;
    private Activity currentContext;
    private boolean isInitialized = false;
    private C1660a licenseManager;
    public BroadcastReceiver localeChangedReceiver = new C1555a();
    private MetaDataManager metaDataManager;
    private NaviBeesBeaconManager naviBeesBeaconManager;
    private PositionManager positionManager;
    private ServerManager serverManager;
    private TurnByTurnNavigation turnByTurnNavigation;

    /* renamed from: com.navibees.core.NaviBeesManager$a */
    class C1555a extends BroadcastReceiver {
        C1555a() {
        }

        public void onReceive(Context context, Intent intent) {
            Locale locale = context.getResources().getConfiguration().locale;
            String language = Locale.getDefault().getLanguage();
            if (NaviBeesBeaconManager.SHOW_LOG) {
                StringBuilder sb = new StringBuilder();
                sb.append("LOCALE CHANGED to ");
                sb.append(language);
                Toast.makeText(context, sb.toString(), 1).show();
            }
            NaviBeesManager.changeLanguage(context, language);
        }
    }

    NaviBeesManager() {
    }

    public static Context adjustLanguage(Context context) {
        String str;
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(LANGUAGE_KEY, null);
        if (string != null) {
            return adjustLanguage(context, string);
        }
        if (VERSION.SDK_INT >= 24) {
            str = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        } else {
            str = context.getResources().getConfiguration().locale.getLanguage();
        }
        return changeLanguage(context, str);
    }

    public static Context changeLanguage(Context context, String str) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(LANGUAGE_KEY, str);
        edit.apply();
        return adjustLanguage(context, str);
    }

    public static String getAppLanguage() {
        return NaviBeesUtils.getAppLang();
    }

    private AssetsManager getAssetsManager() {
        return this.assetsManager;
    }

    public static NaviBeesManager getInstance(Application application) {
        if (application instanceof NaviBeesApplication) {
            return ((NaviBeesApplication) application).mo28714a();
        }
        return null;
    }

    private void initManagers(Application application) {
        if (this.positionManager == null) {
            try {
                this.positionManager = new PositionManager(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.naviBeesBeaconManager == null) {
            try {
                this.naviBeesBeaconManager = new NaviBeesBeaconManager(application);
                if (this.positionManager != null) {
                    this.positionManager.startTracking();
                    this.naviBeesBeaconManager.addBeaconNodeListener(this.positionManager);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.serverManager == null) {
            try {
                this.serverManager = new C1718a();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (this.analyticsManager == null) {
            this.analyticsManager = new C1646a();
        }
    }

    private void initPrimaryManagers(Application application) {
        if (this.metaDataManager == null) {
            this.metaDataManager = new MetaDataManager();
        }
        if (this.assetsManager == null) {
            this.assetsManager = new AssetsManager(application.getApplicationContext());
        }
        if (this.licenseManager == null) {
            this.licenseManager = new C1660a(application);
        }
    }

    private void setAppInForeground(Context context, Boolean bool) {
        StringBuilder sb = new StringBuilder();
        sb.append("NaviBeesApplication:setAppInForeground:isAppInForeground:");
        sb.append(bool);
        Log.m1172e("AppManager", sb.toString());
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(NaviBeesConstants.IS_APP_IN_FOREGROUND_KEY, bool.booleanValue());
        edit.apply();
    }

    public void activityOnStart(Activity activity) {
        if (this.currentContext == null) {
            setAppInForeground(activity, Boolean.valueOf(true));
            if (this.isInitialized) {
                NaviBeesBeaconManager naviBeesBeaconManager2 = this.naviBeesBeaconManager;
                if (naviBeesBeaconManager2 != null) {
                    naviBeesBeaconManager2.bindBeaconManager();
                    this.naviBeesBeaconManager.registerReceivers();
                }
            }
        }
        this.currentContext = activity;
        NaviBeesBackgroundAnalyticsReceiver.stopBackgroundAnalytics(activity);
        if (this.isInitialized) {
            getNavibeesAnalytics().mo29032a(activity);
        }
    }

    public void activityOnStop(Activity activity) {
        if (this.isInitialized) {
            getNavibeesAnalytics().mo29038b(activity);
        }
        if (this.currentContext == activity) {
            if (this.isInitialized) {
                getMetaDataManager().saveLastVenue(activity);
                NaviBeesBeaconManager naviBeesBeaconManager2 = this.naviBeesBeaconManager;
                if (naviBeesBeaconManager2 != null) {
                    naviBeesBeaconManager2.enableBackgroundMonitoring();
                    this.naviBeesBeaconManager.unregisterReceivers();
                }
            }
            setAppInForeground(activity, Boolean.valueOf(false));
            this.currentContext = null;
        }
    }

    public void deInitialize(Context context) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(NaviBeesConstants.STATUS_LAST_UPDATED_DATE, null);
        edit.apply();
        getNaviBeesBeaconManager().unbindService();
        getAssetsManager().deleteMapResources();
    }

    public void deleteSavedLocation(Context context) {
        if (context != null) {
            getMetaDataManager().deleteSavedLocation(context);
        }
    }

    public C1660a getLicenseManager() {
        return this.licenseManager;
    }

    public MetaDataManager getMetaDataManager() {
        return this.metaDataManager;
    }

    public NaviBeesBeaconManager getNaviBeesBeaconManager() {
        return this.naviBeesBeaconManager;
    }

    public C1646a getNavibeesAnalytics() {
        return this.analyticsManager;
    }

    public PositionManager getPositionManager() {
        return this.positionManager;
    }

    public IndoorLocation getSavedLocation(Context context) {
        return getMetaDataManager().getSavedLocation(context);
    }

    public ServerManager getServerManager() {
        return this.serverManager;
    }

    public TTSManager getTTSManager(Activity activity) {
        try {
            return new TTSManager(activity);
        } catch (Exception e) {
            NaviBeesUtils.disableTTS(activity.getApplicationContext());
            e.printStackTrace();
            return null;
        }
    }

    public TurnByTurnNavigation getTurnByTurnNavigation(Context context) {
        if (this.turnByTurnNavigation == null) {
            try {
                this.turnByTurnNavigation = TurnByTurnNavigation.getInstance(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.turnByTurnNavigation;
    }

    /* access modifiers changed from: 0000 */
    public boolean initializeSync(Application application) throws NaviBeesLicenseExpireException, NaviBeesLicenseNotAuthorizedException {
        adjustLanguage(application.getBaseContext());
        initPrimaryManagers(application);
        this.isInitialized = getAssetsManager().copyAssetsFolder() && getMetaDataManager().loadMapMetaData(application);
        if (!this.isInitialized) {
            return false;
        }
        initManagers(application);
        try {
            getLicenseManager().mo29048a((NaviBeesFeature) null);
            getNavibeesAnalytics().mo29033a(application);
            if (NaviBeesUtils.isInternetConnectionOnline(application.getApplicationContext())) {
                getServerManager().startSync(application, getMetaDataManager().navibeesApp.f1338id, getInstance(application).getMetaDataManager().navibeesApp.authenticationToken);
                String token = FirebaseInstanceId.getInstance().getToken();
                if (token != null) {
                    getServerManager().registerPNSDeviceIdentifier(application, token);
                }
                getNavibeesAnalytics().mo29036a(application.getApplicationContext());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.isInitialized = false;
            return false;
        }
    }

    public boolean isInitialized() {
        return this.isInitialized;
    }

    public void saveLocation(Context context, IndoorLocation indoorLocation) {
        if (indoorLocation != null) {
            getMetaDataManager().saveLocation(context, indoorLocation);
        }
    }

    public void syncData(Application application, OnSyncListener onSyncListener) {
        if (getMetaDataManager().navibeesApp != null) {
            new C1718a().mo29422a(application, getMetaDataManager().navibeesApp.f1338id, getInstance(application).getMetaDataManager().navibeesApp.authenticationToken, onSyncListener);
        }
    }

    public void verifyFeatureLicense(NaviBeesFeature naviBeesFeature) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        getLicenseManager().mo29048a(naviBeesFeature);
    }

    public void syncData(Application application, String str, String str2, OnSyncListener onSyncListener) {
        if (str != null && str2 != null) {
            new C1718a().mo29422a(application, str, str2, onSyncListener);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.Context adjustLanguage(android.content.Context r9, java.lang.String r10) {
        /*
            int r0 = r10.hashCode()
            java.lang.String r1 = "ur"
            java.lang.String r2 = "fr"
            java.lang.String r3 = "de"
            r4 = 3
            r5 = 2
            r6 = 1
            java.lang.String r7 = "ar"
            r8 = 3121(0xc31, float:4.373E-42)
            if (r0 == r8) goto L_0x0038
            r8 = 3201(0xc81, float:4.486E-42)
            if (r0 == r8) goto L_0x0030
            r8 = 3276(0xccc, float:4.59E-42)
            if (r0 == r8) goto L_0x0028
            r8 = 3741(0xe9d, float:5.242E-42)
            if (r0 == r8) goto L_0x0020
            goto L_0x0040
        L_0x0020:
            boolean r0 = r10.equals(r1)
            if (r0 == 0) goto L_0x0040
            r0 = 3
            goto L_0x0041
        L_0x0028:
            boolean r0 = r10.equals(r2)
            if (r0 == 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x0030:
            boolean r0 = r10.equals(r3)
            if (r0 == 0) goto L_0x0040
            r0 = 2
            goto L_0x0041
        L_0x0038:
            boolean r0 = r10.equals(r7)
            if (r0 == 0) goto L_0x0040
            r0 = 0
            goto L_0x0041
        L_0x0040:
            r0 = -1
        L_0x0041:
            if (r0 == 0) goto L_0x0063
            if (r0 == r6) goto L_0x005d
            if (r0 == r5) goto L_0x0057
            if (r0 == r4) goto L_0x0051
            java.util.Locale r0 = new java.util.Locale
            java.lang.String r1 = "en"
            r0.<init>(r1)
            goto L_0x0068
        L_0x0051:
            java.util.Locale r0 = new java.util.Locale
            r0.<init>(r1)
            goto L_0x0068
        L_0x0057:
            java.util.Locale r0 = new java.util.Locale
            r0.<init>(r3)
            goto L_0x0068
        L_0x005d:
            java.util.Locale r0 = new java.util.Locale
            r0.<init>(r2)
            goto L_0x0068
        L_0x0063:
            java.util.Locale r0 = new java.util.Locale
            r0.<init>(r7)
        L_0x0068:
            java.util.Locale.setDefault(r0)
            com.navibees.core.util.NaviBeesUtils.setAppLang(r10)
            android.content.res.Resources r10 = r9.getResources()
            android.content.res.Configuration r10 = r10.getConfiguration()
            r10.setLocale(r0)
            android.content.Context r9 = r9.createConfigurationContext(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.NaviBeesManager.adjustLanguage(android.content.Context, java.lang.String):android.content.Context");
    }
}
