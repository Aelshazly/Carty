package com.navibees.core;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.navibees.core.blesdk.C1620a;
import com.navibees.core.blesdk.C1625c;
import com.navibees.core.blesdk.p022d.C1629d;
import com.navibees.core.blesdk.p023e.C1634b;
import com.navibees.core.model.analytics.NaviBeesBackgroundAnalyticsReceiver;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.postioning.MonitoringRegionsJobService;
import com.navibees.core.model.postioning.NaviBeesBeaconManager;
import com.navibees.core.model.server.OnSyncListener;
import com.navibees.core.model.server.Prefs;
import com.navibees.core.util.ActivityLifecycleHandler;
import com.navibees.core.util.ActivityLifecycleHandler.LifecycleListener;
import com.navibees.core.util.Log;
import java.util.ArrayList;
import java.util.List;

public class NaviBeesApplication extends Application implements C1629d, LifecycleListener {

    /* renamed from: f */
    private static final String f944f = "NaviBeesApplication";
    public static boolean isLicenseExpired = false;

    /* renamed from: a */
    private C1625c f945a;

    /* renamed from: b */
    private C1620a f946b;

    /* renamed from: c */
    private NaviBeesManager f947c;

    /* renamed from: d */
    private Prefs f948d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public OnSyncListener f949e;
    public boolean syncInProgress = false;

    /* renamed from: com.navibees.core.NaviBeesApplication$a */
    class C1553a extends TypeToken<List<C1634b>> {
        C1553a() {
        }
    }

    /* renamed from: com.navibees.core.NaviBeesApplication$b */
    class C1554b implements OnSyncListener {
        C1554b() {
        }

        public void appMetadataSyncCompleted() {
            NaviBeesApplication.this.syncInProgress = false;
            Log.m1173i(NaviBeesApplication.f944f, "appMetadataSyncCompleted: ");
            if (NaviBeesApplication.this.f949e != null) {
                NaviBeesApplication.this.f949e.appMetadataSyncCompleted();
            }
        }

        public void appMetadataSyncFailed(String str) {
            NaviBeesApplication.this.syncInProgress = false;
            StringBuilder sb = new StringBuilder();
            sb.append("appMetadataSyncFailed: ");
            sb.append(str);
            Log.m1173i(NaviBeesApplication.f944f, sb.toString());
            if (NaviBeesApplication.this.f949e != null) {
                NaviBeesApplication.this.f949e.appMetadataSyncFailed(str);
            }
        }

        public void buildingSyncCompleted() {
            NaviBeesApplication.this.syncInProgress = false;
            Log.m1173i(NaviBeesApplication.f944f, "buildingSyncCompleted: ");
            if (NaviBeesApplication.this.f949e != null) {
                NaviBeesApplication.this.f949e.buildingSyncCompleted();
            }
        }

        public void buildingSyncFailed(String str) {
            NaviBeesApplication.this.syncInProgress = false;
            StringBuilder sb = new StringBuilder();
            sb.append("buildingSyncFailed: ");
            sb.append(str);
            Log.m1173i(NaviBeesApplication.f944f, sb.toString());
            if (NaviBeesApplication.this.f949e != null) {
                NaviBeesApplication.this.f949e.buildingSyncFailed(str);
            }
        }

        public void buildingSyncCompleted(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("buildingSyncCompleted: ");
            sb.append(str);
            Log.m1173i(NaviBeesApplication.f944f, sb.toString());
            if (NaviBeesApplication.this.f949e != null) {
                NaviBeesApplication.this.f949e.buildingSyncCompleted(str);
            }
        }

        public void buildingSyncFailed(String str, String str2) {
            StringBuilder sb = new StringBuilder();
            sb.append("buildingSyncFailed: ");
            sb.append(str);
            sb.append(" Error: ");
            sb.append(str2);
            Log.m1173i(NaviBeesApplication.f944f, sb.toString());
            if (NaviBeesApplication.this.f949e != null) {
                NaviBeesApplication.this.f949e.buildingSyncFailed(str, str2);
            }
        }
    }

    public void didEnterRegion(C1634b bVar) {
        Log.m1171d(f944f, "Got a didEnterRegion call");
        StringBuilder sb = new StringBuilder();
        sb.append("Entered Region: ");
        sb.append(bVar.mo28994d());
        Log.m1171d("regioneer", sb.toString());
        if (bVar.mo28994d().contains(NaviBeesConstants.BACKGRORUND_ANALYTICS_REGION_ID)) {
            NaviBeesBackgroundAnalyticsReceiver.startBackgroundAnalytics(getApplicationContext());
            return;
        }
        Intent intent = new Intent(NaviBeesConstants.MONITORED_REGION_INTERNAL_ACTION);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY, bVar.mo28994d());
        String str = NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_ENTER_VALUE;
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, str);
        if (VERSION.SDK_INT >= 21) {
            m627a(bVar.mo28994d(), str);
        } else {
            sendBroadcast(intent);
        }
    }

    public void didExitRegion(C1634b bVar) {
        Log.m1171d(f944f, "Got a didExitRegion call");
        StringBuilder sb = new StringBuilder();
        sb.append("Exited Region: ");
        sb.append(bVar.mo28994d());
        Log.m1171d("regioneer", sb.toString());
        if (bVar.mo28994d().contains(NaviBeesConstants.BACKGRORUND_ANALYTICS_REGION_ID)) {
            NaviBeesBackgroundAnalyticsReceiver.stopBackgroundAnalytics(getApplicationContext());
            return;
        }
        Intent intent = new Intent(NaviBeesConstants.MONITORED_REGION_INTERNAL_ACTION);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY, bVar.mo28994d());
        String str = NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_EXIT_VALUE;
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, str);
        if (VERSION.SDK_INT >= 21) {
            m627a(bVar.mo28994d(), str);
        } else {
            sendBroadcast(intent);
        }
    }

    public final void disableRegionBootstrap(List<C1634b> list) {
        try {
            if (this.f945a != null) {
                this.f945a.mo28958a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void enableRegionBootstrap(List<C1634b> list) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = NaviBeesManager.getInstance(this).getMetaDataManager().getConfiguration().beaconsUUIDList;
        int i = 0;
        while (strArr != null && i < strArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("navibees-background-analytics-region#");
            sb.append(i);
            arrayList.add(new C1634b(sb.toString(), strArr[i]));
            i++;
        }
        try {
            NaviBeesManager.getInstance(this).getLicenseManager().mo29048a(NaviBeesFeature.Location_Based_Notifications);
            if (list != null) {
                arrayList.addAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("enableBackgroundMonitoring:regions.size():");
        sb2.append(arrayList.size());
        Log.m1172e(f944f, sb2.toString());
        this.f945a = new C1625c(this, arrayList);
        this.f946b.mo28936a(this.f945a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize() {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            com.navibees.core.NaviBeesManager r2 = r4.f947c     // Catch:{ NaviBeesLicenseExpireException -> 0x0017, NaviBeesLicenseNotAuthorizedException -> 0x000f }
            boolean r2 = r2.initializeSync(r4)     // Catch:{ NaviBeesLicenseExpireException -> 0x0017, NaviBeesLicenseNotAuthorizedException -> 0x000f }
            isLicenseExpired = r1     // Catch:{ NaviBeesLicenseExpireException -> 0x000d, NaviBeesLicenseNotAuthorizedException -> 0x000b }
            goto L_0x001e
        L_0x000b:
            r3 = move-exception
            goto L_0x0011
        L_0x000d:
            r3 = move-exception
            goto L_0x0019
        L_0x000f:
            r3 = move-exception
            r2 = 0
        L_0x0011:
            isLicenseExpired = r0
            r3.printStackTrace()
            goto L_0x001e
        L_0x0017:
            r3 = move-exception
            r2 = 0
        L_0x0019:
            isLicenseExpired = r0
            r3.printStackTrace()
        L_0x001e:
            if (r2 != 0) goto L_0x002e
            boolean r0 = com.navibees.core.model.postioning.NaviBeesBeaconManager.SHOW_LOG
            if (r0 == 0) goto L_0x002d
            java.lang.String r0 = "NaviBees Initialization Failed"
            android.widget.Toast r0 = android.widget.Toast.makeText(r4, r0, r1)
            r0.show()
        L_0x002d:
            return
        L_0x002e:
            com.navibees.core.blesdk.a r0 = com.navibees.core.blesdk.C1620a.m840a(r4)
            r4.f946b = r0
            com.navibees.core.blesdk.a r0 = r4.f946b
            r1 = 1100(0x44c, double:5.435E-321)
            r0.mo28944b(r1)
            com.navibees.core.blesdk.a r0 = r4.f946b
            r1 = 5000(0x1388, double:2.4703E-320)
            r0.mo28935a(r1)
            android.content.SharedPreferences r0 = androidx.preference.PreferenceManager.getDefaultSharedPreferences(r4)
            java.lang.String r1 = "-"
            java.lang.String r2 = "com.navibees.background.monitored.regions"
            java.lang.String r0 = r0.getString(r2, r1)
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 != 0) goto L_0x00a3
            boolean r1 = com.navibees.core.util.NaviBeesUtils.isMarshmallowOrAbove()
            if (r1 == 0) goto L_0x0060
            boolean r1 = com.navibees.core.util.NaviBeesUtils.isLocationServicesGranted(r4)
            if (r1 == 0) goto L_0x00a3
        L_0x0060:
            com.google.gson.Gson r1 = new com.google.gson.Gson
            r1.<init>()
            com.navibees.core.NaviBeesApplication$a r2 = new com.navibees.core.NaviBeesApplication$a
            r2.<init>()
            java.lang.reflect.Type r2 = r2.getType()
            java.lang.Object r0 = r1.fromJson(r0, r2)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x0090
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "In App Started backgroundRegions.size():"
            r1.append(r2)
            int r2 = r0.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "NaviBeesApplication"
            com.navibees.core.util.Log.m1173i(r2, r1)
        L_0x0090:
            com.navibees.core.blesdk.c r1 = new com.navibees.core.blesdk.c
            r1.<init>(r4, r0)
            r4.f945a = r1
            com.navibees.core.blesdk.a r1 = r4.f946b
            com.navibees.core.blesdk.c r2 = r4.f945a
            r1.mo28936a(r2)
            com.navibees.core.blesdk.a r1 = r4.f946b
            r1.mo28942a(r0)
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.NaviBeesApplication.initialize():void");
    }

    public void onApplicationPaused() {
    }

    public void onApplicationResumed() {
        try {
            this.f947c.getMetaDataManager().setCurrentVenue(this, this.f947c.getMetaDataManager().getCurrentVenue().f1342id);
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Context applicationContext = getApplicationContext();
                StringBuilder sb = new StringBuilder();
                sb.append("Set Current Venue to: ");
                sb.append(this.f947c.getMetaDataManager().getCurrentVenue().name.getText());
                Toast.makeText(applicationContext, sb.toString(), 0).show();
            }
        } catch (Exception e) {
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(getApplicationContext(), "Set Current Venue Failed", 0).show();
            }
        }
        if (this.f948d.getLicenseStartDate() != null) {
            isLicenseExpired = this.f947c.getLicenseManager().mo29049a(this.f948d.getLicenseStartDate(), this.f948d.getLicenseDuration());
            if (isLicenseExpired) {
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    Toast.makeText(getApplicationContext(), "License -> Expired", 0).show();
                }
            } else if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(getApplicationContext(), "License -> Valid", 0).show();
            }
        }
        syncData(new C1554b());
    }

    public void onApplicationStarted() {
    }

    public void onApplicationStopped() {
    }

    public void onCreate() {
        super.onCreate();
        System.gc();
        registerActivityLifecycleCallbacks(new ActivityLifecycleHandler(this));
        this.f948d = Prefs.getInstance(getApplicationContext());
        Log.m1171d("NavibeesServerManager", "App started up");
        if (NaviBeesBeaconManager.SHOW_LOG) {
            Toast.makeText(getApplicationContext(), "App->OnCreate()", 0).show();
        }
        isLicenseExpired = false;
        this.f947c = new NaviBeesManager();
        registerReceiver(this.f947c.localeChangedReceiver, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
        initialize();
    }

    public void onTerminate() {
        super.onTerminate();
        try {
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            StringBuilder sb = new StringBuilder();
            sb.append("pm clear ");
            sb.append(packageName);
            runtime.exec(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSyncListener(OnSyncListener onSyncListener) {
        this.f949e = onSyncListener;
    }

    public void syncData(OnSyncListener onSyncListener) {
        if (this.f947c.getMetaDataManager().navibeesApp == null) {
            return;
        }
        if (!this.syncInProgress) {
            this.syncInProgress = true;
            this.f947c.syncData(this, onSyncListener);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("sync data is in progress: ");
        sb.append(this.syncInProgress);
        Log.m1173i(f944f, sb.toString());
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public NaviBeesManager mo28714a() {
        return this.f947c;
    }

    @TargetApi(21)
    /* renamed from: a */
    private void m627a(String str, String str2) {
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService("jobscheduler");
        ComponentName componentName = new ComponentName(this, MonitoringRegionsJobService.class);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY, str);
        persistableBundle.putString(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, str2);
        jobScheduler.schedule(new Builder(1, componentName).setOverrideDeadline(0).setExtras(persistableBundle).build());
    }

    public void syncData(String str, String str2, OnSyncListener onSyncListener) {
        if (!this.syncInProgress) {
            this.syncInProgress = true;
            this.f947c.syncData(this, str, str2, onSyncListener);
            return;
        }
        onSyncListener.appMetadataSyncFailed("");
        StringBuilder sb = new StringBuilder();
        sb.append("sync data is in progress: ");
        sb.append(this.syncInProgress);
        Log.m1173i(f944f, sb.toString());
    }
}
