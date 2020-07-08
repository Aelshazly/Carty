package com.navibees.core.model.server;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.collection.SimpleArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.navibees.core.C1556a;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.UserProfilerLoginListener;
import com.navibees.core.interfaces.UserProfilerRegistrationListener;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.NavibeesApplication;
import com.navibees.core.model.metadata.json.Text;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.postioning.NaviBeesBeaconManager;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.sdk.C1266R;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.entity.StringEntity;
import p008cz.msebera.android.httpclient.message.BasicHeader;

/* renamed from: com.navibees.core.model.server.a */
/* compiled from: NaviBeesServerManager */
public class C1718a implements ServerManager {

    /* renamed from: j */
    private static boolean f1563j = true;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public static String f1564k = "NaviBeesServerManager";

    /* renamed from: a */
    private final String f1565a;

    /* renamed from: b */
    private final String f1566b;

    /* renamed from: c */
    private final String f1567c;

    /* renamed from: d */
    private final String f1568d;

    /* renamed from: e */
    private final String f1569e;

    /* renamed from: f */
    private final String f1570f;

    /* renamed from: g */
    private final String f1571g;

    /* renamed from: h */
    private final String f1572h;

    /* renamed from: i */
    private AsyncHttpClient f1573i;

    /* renamed from: com.navibees.core.model.server.a$a */
    /* compiled from: NaviBeesServerManager */
    class C1719a extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ UserProfilerRegistrationListener f1574a;

        /* renamed from: b */
        final /* synthetic */ Application f1575b;

        C1719a(UserProfilerRegistrationListener userProfilerRegistrationListener, Application application) {
            this.f1574a = userProfilerRegistrationListener;
            this.f1575b = application;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.m1171d("NavibeesServerManager", "User Register request failed");
            this.f1574a.onFailure(i, th);
            C1718a.this.m1160b(this.f1575b.getApplicationContext());
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            this.f1574a.onSuccess(i);
            C1718a.this.m1160b(this.f1575b.getApplicationContext());
        }
    }

    /* renamed from: com.navibees.core.model.server.a$b */
    /* compiled from: NaviBeesServerManager */
    class C1720b extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ UserProfilerLoginListener f1577a;

        /* renamed from: b */
        final /* synthetic */ Application f1578b;

        C1720b(UserProfilerLoginListener userProfilerLoginListener, Application application) {
            this.f1577a = userProfilerLoginListener;
            this.f1578b = application;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.m1171d("NavibeesServerManager", "User Login request failed");
            this.f1577a.onFailure(i, th);
            C1718a.this.m1160b(this.f1578b.getApplicationContext());
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            this.f1577a.onSuccess(i);
            C1718a.this.m1160b(this.f1578b.getApplicationContext());
        }
    }

    /* renamed from: com.navibees.core.model.server.a$c */
    /* compiled from: NaviBeesServerManager */
    class C1721c extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ Application f1580a;

        /* renamed from: b */
        final /* synthetic */ OnSyncListener f1581b;

        /* renamed from: c */
        final /* synthetic */ Prefs f1582c;

        C1721c(Application application, OnSyncListener onSyncListener, Prefs prefs) {
            this.f1580a = application;
            this.f1581b = onSyncListener;
            this.f1582c = prefs;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.m1171d("NavibeesServerManager", "Application request failed");
            C1718a.this.m1160b(this.f1580a.getApplicationContext());
            if (!NaviBeesUtils.isInternetConnectionOnline(this.f1580a.getApplicationContext())) {
                this.f1581b.appMetadataSyncFailed(this.f1580a.getString(C1266R.string.messageErrorMapDownloadInternet));
            } else {
                this.f1581b.appMetadataSyncFailed(th.getMessage());
            }
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(this.f1580a.getApplicationContext(), "Data Sync Failed", 0).show();
            }
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            String str = "License-> Valid";
            String str2 = "License-> Expired";
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(this.f1580a.getApplicationContext(), "Data Sync Success", 0).show();
            }
            Log.m1171d("NavibeesServerManager", "Application request succeeded");
            SimpleArrayMap buildings = NaviBeesManager.getInstance(this.f1580a).getMetaDataManager().getBuildings();
            String str3 = new String(bArr);
            NaviBeesManager.getInstance(this.f1580a).getMetaDataManager().loadNavibeesApp(str3, this.f1580a.getApplicationContext());
            NaviBeesManager.getInstance(this.f1580a).getMetaDataManager().serializeNavibeesApp(this.f1580a.getApplicationContext());
            this.f1581b.appMetadataSyncCompleted();
            try {
                NaviBeesManager.getInstance(this.f1580a).getLicenseManager().mo29048a((NaviBeesFeature) null);
                NaviBeesApplication.isLicenseExpired = false;
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    Toast.makeText(this.f1580a.getApplicationContext(), str, 0).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                NaviBeesApplication.isLicenseExpired = true;
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    Toast.makeText(this.f1580a.getApplicationContext(), str2, 0).show();
                }
            }
            try {
                NavibeesApplication navibeesApplication = new NavibeesApplication(new JSONObject(str3), this.f1580a.getApplicationContext());
                this.f1582c.saveLicenseDuration(navibeesApplication.appConfiguration.licenseDuration);
                this.f1582c.saveLicenseStartDate(navibeesApplication.appConfiguration.licenseStartDate);
                this.f1582c.saveLicenseFeatureStr(navibeesApplication.appConfiguration.licenseFeatureStr);
                if (navibeesApplication.appConfiguration.licenseDuration > -1 && navibeesApplication.appConfiguration.licenseStartDate != null) {
                    try {
                        NaviBeesApplication.isLicenseExpired = C1718a.this.m1159a(new Date(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(navibeesApplication.appConfiguration.licenseStartDate).getTime() + (((long) navibeesApplication.appConfiguration.licenseDuration) * 24 * 60 * 60 * 1000)), new Date());
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                        NaviBeesApplication.isLicenseExpired = true;
                    }
                    if (NaviBeesApplication.isLicenseExpired) {
                        if (NaviBeesBeaconManager.SHOW_LOG) {
                            Toast.makeText(this.f1580a.getApplicationContext(), str2, 0).show();
                        }
                    } else if (NaviBeesBeaconManager.SHOW_LOG) {
                        Toast.makeText(this.f1580a.getApplicationContext(), str, 0).show();
                    }
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            C1718a.this.m1151a(this.f1580a, this.f1581b);
            SimpleArrayMap buildings2 = NaviBeesManager.getInstance(this.f1580a).getMetaDataManager().getBuildings();
            HashMap hashMap = new HashMap();
            if (!(buildings == null || buildings2 == null)) {
                int size = buildings2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str4 = (String) buildings2.keyAt(i2);
                    Building building = (Building) buildings2.get(str4);
                    hashMap.put(str4, Long.valueOf(System.currentTimeMillis()));
                    Building building2 = (Building) buildings.get(str4);
                    if (!(building == null || building2 == null)) {
                        building.copyBuildingData(building2, this.f1580a);
                    }
                }
            }
            this.f1582c.saveLastSyncDate(new Gson().toJson((Object) hashMap));
        }
    }

    /* renamed from: com.navibees.core.model.server.a$d */
    /* compiled from: NaviBeesServerManager */
    class C1722d extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ Application f1584a;

        C1722d(Application application) {
            this.f1584a = application;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.m1171d("NavibeesServerManager", "Application request failed");
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(this.f1584a.getApplicationContext(), "Data Sync Failed", 0).show();
            }
            C1718a.this.m1160b(this.f1584a.getApplicationContext());
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            String str = "License-> Valid";
            String str2 = "License-> Expired";
            Log.m1171d("NavibeesServerManager", "Application request succeeded");
            if (NaviBeesBeaconManager.SHOW_LOG) {
                Toast.makeText(this.f1584a.getApplicationContext(), "Data Sync Success", 0).show();
            }
            SimpleArrayMap buildings = NaviBeesManager.getInstance(this.f1584a).getMetaDataManager().getBuildings();
            String str3 = new String(bArr);
            NaviBeesManager.getInstance(this.f1584a).getMetaDataManager().loadNavibeesApp(str3, this.f1584a.getApplicationContext());
            NaviBeesManager.getInstance(this.f1584a).getMetaDataManager().serializeNavibeesApp(this.f1584a.getApplicationContext());
            try {
                NaviBeesManager.getInstance(this.f1584a).getLicenseManager().mo29048a((NaviBeesFeature) null);
                NaviBeesApplication.isLicenseExpired = false;
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    Toast.makeText(this.f1584a.getApplicationContext(), str, 0).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                NaviBeesApplication.isLicenseExpired = true;
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    Toast.makeText(this.f1584a.getApplicationContext(), str2, 0).show();
                }
            }
            try {
                NavibeesApplication navibeesApplication = new NavibeesApplication(new JSONObject(str3), this.f1584a.getApplicationContext());
                Prefs instance = Prefs.getInstance(this.f1584a.getApplicationContext());
                instance.saveLicenseDuration(navibeesApplication.appConfiguration.licenseDuration);
                instance.saveLicenseStartDate(navibeesApplication.appConfiguration.licenseStartDate);
                instance.saveLicenseFeatureStr(navibeesApplication.appConfiguration.licenseFeatureStr);
                if (navibeesApplication.appConfiguration.licenseDuration > -1 && navibeesApplication.appConfiguration.licenseStartDate != null) {
                    try {
                        NaviBeesApplication.isLicenseExpired = C1718a.this.m1159a(new Date(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(navibeesApplication.appConfiguration.licenseStartDate).getTime() + (((long) navibeesApplication.appConfiguration.licenseDuration) * 24 * 60 * 60 * 1000)), new Date());
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                        NaviBeesApplication.isLicenseExpired = true;
                    }
                    if (NaviBeesApplication.isLicenseExpired) {
                        if (NaviBeesBeaconManager.SHOW_LOG) {
                            Toast.makeText(this.f1584a.getApplicationContext(), str2, 0).show();
                        }
                    } else if (NaviBeesBeaconManager.SHOW_LOG) {
                        Toast.makeText(this.f1584a.getApplicationContext(), str, 0).show();
                    }
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            SimpleArrayMap buildings2 = NaviBeesManager.getInstance(this.f1584a).getMetaDataManager().getBuildings();
            HashMap hashMap = new HashMap();
            if (!(buildings == null || buildings2 == null)) {
                int size = buildings2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str4 = (String) buildings2.keyAt(i2);
                    Building building = (Building) buildings2.get(str4);
                    hashMap.put(str4, Long.valueOf(System.currentTimeMillis()));
                    Building building2 = (Building) buildings.get(str4);
                    if (!(building == null || building2 == null)) {
                        building.copyBuildingData(building2, this.f1584a);
                    }
                }
            }
            C1718a.this.m1151a(this.f1584a, (OnSyncListener) null);
            Prefs.getInstance(this.f1584a.getApplicationContext()).saveLastSyncDate(new Gson().toJson((Object) hashMap));
        }
    }

    /* renamed from: com.navibees.core.model.server.a$e */
    /* compiled from: NaviBeesServerManager */
    class C1723e extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ Application f1586a;

        /* renamed from: b */
        final /* synthetic */ OnSyncListener f1587b;

        /* renamed from: c */
        final /* synthetic */ Queue f1588c;

        C1723e(Application application, OnSyncListener onSyncListener, Queue queue) {
            this.f1586a = application;
            this.f1587b = onSyncListener;
            this.f1588c = queue;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.m1171d("NaviBeesServer", ">>>>>>> Status call failed");
            C1718a.this.m1153a(this.f1586a, this.f1588c, null, true, this.f1587b);
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            String str = "NaviBeesServer";
            Log.m1171d(str, ">>>>>>> Status call success");
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr)).getJSONObject("buildings_status");
                LinkedList linkedList = new LinkedList();
                SimpleArrayMap venues = NaviBeesManager.getInstance(this.f1586a).getMetaDataManager().getVenues();
                for (int i2 = 0; i2 < venues.size(); i2++) {
                    Venue venue = (Venue) venues.valueAt(i2);
                    if (venue.buildings != null) {
                        for (int i3 = 0; i3 < venue.buildings.size(); i3++) {
                            Building building = (Building) venue.buildings.valueAt(i3);
                            if (jSONObject.getBoolean(building.f1329id)) {
                                linkedList.add(building);
                            }
                        }
                    }
                }
                C1718a.this.m1153a(this.f1586a, linkedList, null, true, this.f1587b);
            } catch (Exception e) {
                Log.m1171d(str, ">>>>>>> Status call parse invalid");
                e.printStackTrace();
                C1718a.this.m1153a(this.f1586a, this.f1588c, null, true, this.f1587b);
            }
        }
    }

    /* renamed from: com.navibees.core.model.server.a$f */
    /* compiled from: NaviBeesServerManager */
    class C1724f extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ String f1590a;

        /* renamed from: b */
        final /* synthetic */ Building f1591b;

        /* renamed from: c */
        final /* synthetic */ Application f1592c;

        /* renamed from: d */
        final /* synthetic */ OnSyncListener f1593d;

        /* renamed from: e */
        final /* synthetic */ Queue f1594e;

        /* renamed from: f */
        final /* synthetic */ boolean f1595f;

        C1724f(String str, Building building, Application application, OnSyncListener onSyncListener, Queue queue, boolean z) {
            this.f1590a = str;
            this.f1591b = building;
            this.f1592c = application;
            this.f1593d = onSyncListener;
            this.f1594e = queue;
            this.f1595f = z;
        }

        public boolean getUseSynchronousMode() {
            return false;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            String a = C1718a.f1564k;
            StringBuilder sb = new StringBuilder();
            sb.append(">>>>>>>Building failed with status code ");
            sb.append(i);
            sb.append(", error ");
            sb.append(th.getMessage());
            sb.append(" building #");
            sb.append(this.f1594e.size());
            Log.m1171d(a, sb.toString());
            if (this.f1593d != null) {
                if (!NaviBeesUtils.isInternetConnectionOnline(this.f1592c.getApplicationContext())) {
                    this.f1593d.buildingSyncFailed(this.f1591b.f1329id, this.f1592c.getString(C1266R.string.messageErrorMapDownloadInternet));
                } else {
                    this.f1593d.buildingSyncFailed(this.f1591b.f1329id, th.getMessage());
                }
            }
            C1718a.this.m1153a(this.f1592c, this.f1594e, this.f1590a, false, this.f1593d);
        }

        /* JADX WARNING: Removed duplicated region for block: B:23:0x0088  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00b8  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x0115  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0156 A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onSuccess(int r7, p008cz.msebera.android.httpclient.Header[] r8, byte[] r9) {
            /*
                r6 = this;
                java.lang.String r7 = r6.f1590a
                r8 = 1
                java.lang.String r0 = new java.lang.String     // Catch:{ Exception -> 0x007e }
                r0.<init>(r9)     // Catch:{ Exception -> 0x007e }
                org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Exception -> 0x007e }
                r9.<init>(r0)     // Catch:{ Exception -> 0x007e }
                com.navibees.core.model.metadata.json.Building r0 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                android.app.Application r1 = r6.f1592c     // Catch:{ Exception -> 0x007c }
                android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Exception -> 0x007c }
                boolean r0 = r0.getDidSyncAll(r1)     // Catch:{ Exception -> 0x007c }
                if (r0 != 0) goto L_0x002e
                java.lang.String r0 = "all_building"
                org.json.JSONObject r9 = r9.getJSONObject(r0)     // Catch:{ Exception -> 0x007c }
                if (r9 == 0) goto L_0x002e
                com.navibees.core.model.metadata.json.Building r0 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                android.app.Application r1 = r6.f1592c     // Catch:{ Exception -> 0x007c }
                android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Exception -> 0x007c }
                r0.setDidSyncAll(r8, r1)     // Catch:{ Exception -> 0x007c }
            L_0x002e:
                if (r9 == 0) goto L_0x007a
                com.navibees.core.model.metadata.json.Building r0 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                android.app.Application r1 = r6.f1592c     // Catch:{ Exception -> 0x007c }
                r0.loadBuildingData(r9, r1)     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.metadata.json.Building r0 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                android.app.Application r1 = r6.f1592c     // Catch:{ Exception -> 0x007c }
                r0.serializeBuilding(r1)     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.server.a r0 = com.navibees.core.model.server.C1718a.this     // Catch:{ Exception -> 0x007c }
                java.lang.String r1 = r6.f1590a     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.metadata.json.Building r2 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                java.lang.String r2 = r2.updatedAt     // Catch:{ Exception -> 0x007c }
                java.lang.String r7 = r0.m1148a(r1, r2)     // Catch:{ Exception -> 0x007c }
                android.app.Application r0 = r6.f1592c     // Catch:{ Exception -> 0x007c }
                com.navibees.core.NaviBeesManager r0 = com.navibees.core.NaviBeesManager.getInstance(r0)     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.metadata.MetaDataManager r0 = r0.getMetaDataManager()     // Catch:{ Exception -> 0x007c }
                androidx.collection.SimpleArrayMap r0 = r0.getBuildings()     // Catch:{ Exception -> 0x007c }
                if (r0 == 0) goto L_0x007a
                com.navibees.core.model.metadata.json.Building r1 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                java.lang.String r1 = r1.f1329id     // Catch:{ Exception -> 0x007c }
                java.lang.Object r1 = r0.get(r1)     // Catch:{ Exception -> 0x007c }
                if (r1 == 0) goto L_0x007a
                com.navibees.core.model.metadata.json.Building r1 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                java.lang.String r1 = r1.f1329id     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.metadata.json.Building r2 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                r0.put(r1, r2)     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.server.OnSyncListener r0 = r6.f1593d     // Catch:{ Exception -> 0x007c }
                if (r0 == 0) goto L_0x007a
                com.navibees.core.model.server.OnSyncListener r0 = r6.f1593d     // Catch:{ Exception -> 0x007c }
                com.navibees.core.model.metadata.json.Building r1 = r6.f1591b     // Catch:{ Exception -> 0x007c }
                java.lang.String r1 = r1.f1329id     // Catch:{ Exception -> 0x007c }
                r0.buildingSyncCompleted(r1)     // Catch:{ Exception -> 0x007c }
            L_0x007a:
                r3 = r7
                goto L_0x00b4
            L_0x007c:
                r0 = move-exception
                goto L_0x0081
            L_0x007e:
                r9 = move-exception
                r0 = r9
                r9 = 0
            L_0x0081:
                r0.printStackTrace()
                com.navibees.core.model.server.OnSyncListener r1 = r6.f1593d
                if (r1 == 0) goto L_0x00b3
                android.app.Application r1 = r6.f1592c
                android.content.Context r1 = r1.getApplicationContext()
                boolean r1 = com.navibees.core.util.NaviBeesUtils.isInternetConnectionOnline(r1)
                if (r1 != 0) goto L_0x00a6
                com.navibees.core.model.server.OnSyncListener r0 = r6.f1593d
                com.navibees.core.model.metadata.json.Building r1 = r6.f1591b
                java.lang.String r1 = r1.f1329id
                android.app.Application r2 = r6.f1592c
                int r3 = com.navibees.sdk.C1266R.string.messageErrorMapDownloadInternet
                java.lang.String r2 = r2.getString(r3)
                r0.buildingSyncFailed(r1, r2)
                goto L_0x00b3
            L_0x00a6:
                com.navibees.core.model.server.OnSyncListener r1 = r6.f1593d
                com.navibees.core.model.metadata.json.Building r2 = r6.f1591b
                java.lang.String r2 = r2.f1329id
                java.lang.String r0 = r0.getMessage()
                r1.buildingSyncFailed(r2, r0)
            L_0x00b3:
                r3 = r7
            L_0x00b4:
                java.lang.String r7 = " with name "
                if (r9 != 0) goto L_0x0115
                java.lang.String r0 = com.navibees.core.model.server.C1718a.f1564k
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ">>>>>>>>>Failed to get building #"
                r1.append(r2)
                java.util.Queue r2 = r6.f1594e
                int r2 = r2.size()
                r1.append(r2)
                r1.append(r7)
                com.navibees.core.model.metadata.json.Building r2 = r6.f1591b
                com.navibees.core.model.metadata.json.Text r2 = r2.name
                java.lang.String r2 = r2.getText()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                com.navibees.core.util.Log.m1171d(r0, r1)
                com.navibees.core.model.server.OnSyncListener r0 = r6.f1593d
                if (r0 == 0) goto L_0x014c
                com.navibees.core.model.metadata.json.Building r1 = r6.f1591b
                java.lang.String r1 = r1.f1329id
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r4 = "Failed to get building #"
                r2.append(r4)
                java.util.Queue r4 = r6.f1594e
                int r4 = r4.size()
                r2.append(r4)
                r2.append(r7)
                com.navibees.core.model.metadata.json.Building r7 = r6.f1591b
                com.navibees.core.model.metadata.json.Text r7 = r7.name
                java.lang.String r7 = r7.getText()
                r2.append(r7)
                java.lang.String r7 = r2.toString()
                r0.buildingSyncFailed(r1, r7)
                goto L_0x014c
            L_0x0115:
                java.lang.String r0 = com.navibees.core.model.server.C1718a.f1564k
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ">>>>>>>>>Succeeded to get building #"
                r1.append(r2)
                java.util.Queue r2 = r6.f1594e
                int r2 = r2.size()
                r1.append(r2)
                r1.append(r7)
                com.navibees.core.model.metadata.json.Building r7 = r6.f1591b
                com.navibees.core.model.metadata.json.Text r7 = r7.name
                java.lang.String r7 = r7.getText()
                r1.append(r7)
                java.lang.String r7 = r1.toString()
                com.navibees.core.util.Log.m1171d(r0, r7)
                com.navibees.core.model.server.OnSyncListener r7 = r6.f1593d
                if (r7 == 0) goto L_0x014c
                com.navibees.core.model.metadata.json.Building r0 = r6.f1591b
                java.lang.String r0 = r0.f1329id
                r7.buildingSyncCompleted(r0)
            L_0x014c:
                com.navibees.core.model.server.a r0 = com.navibees.core.model.server.C1718a.this
                android.app.Application r1 = r6.f1592c
                java.util.Queue r2 = r6.f1594e
                boolean r7 = r6.f1595f
                if (r7 == 0) goto L_0x015a
                if (r9 == 0) goto L_0x015a
                r4 = 1
                goto L_0x015c
            L_0x015a:
                r8 = 0
                r4 = 0
            L_0x015c:
                com.navibees.core.model.server.OnSyncListener r5 = r6.f1593d
                r0.m1153a(r1, r2, r3, r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.model.server.C1718a.C1724f.onSuccess(int, cz.msebera.android.httpclient.Header[], byte[]):void");
        }
    }

    /* renamed from: com.navibees.core.model.server.a$g */
    /* compiled from: NaviBeesServerManager */
    class C1725g extends AsyncHttpResponseHandler {
        C1725g() {
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            android.util.Log.d("NavibeesServerManager", "pns token registered failure");
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            android.util.Log.d("NavibeesServerManager", "pns token registered success");
        }
    }

    public C1718a() {
        this.f1565a = f1563j ? "http://api.navibees.com/api/v2/" : "http://staging-api.navibees.com/api/v2/";
        this.f1566b = "navibees_applications/%s";
        this.f1567c = "buildings/%s/get_all_building";
        this.f1568d = "buildings/%s/sync?last_updated_date=%s";
        this.f1569e = "profilers/register";
        this.f1570f = "profilers/login_with_external_provider";
        this.f1571g = "profiler_devices/set_pns_device_identifier";
        this.f1572h = "projects/%s/get_buildings_status?last_updated_date=%s";
        this.f1573i = new AsyncHttpClient();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1160b(Context context) {
        Intent intent = new Intent(NaviBeesConstants.NETWORK_ACTIVITY_ACTION);
        intent.putExtra(NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE, 2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /* renamed from: c */
    private void m1161c(Context context) {
        Intent intent = new Intent(NaviBeesConstants.NETWORK_ACTIVITY_ACTION);
        intent.putExtra(NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE, 1);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void loginUser(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, UserProfilerLoginListener userProfilerLoginListener) throws InvalidParameterException, JSONException {
        StringEntity stringEntity;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1565a);
        sb.append("profilers/login_with_external_provider");
        String sb2 = sb.toString();
        String str10 = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.authenticationToken;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Token token=");
        sb3.append(str10);
        Header[] headerArr = {new BasicHeader("Authorization", sb3.toString()), new BasicHeader("device_id", C1556a.m635d(application.getApplicationContext()))};
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("profiler", m1150a(str, str2, str3, str4, str5, str6, str7, str8, str9));
        jSONObject.put("profiler_device", m1149a(application));
        try {
            stringEntity = new StringEntity(jSONObject.toString());
            try {
                stringEntity.setContentType((Header) new BasicHeader("Content-Type", "application/json"));
            } catch (UnsupportedEncodingException e) {
                e = e;
            }
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            stringEntity = null;
            e.printStackTrace();
            m1161c(application.getApplicationContext());
            Application application2 = application;
            this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1720b(userProfilerLoginListener, application));
        }
        m1161c(application.getApplicationContext());
        Application application22 = application;
        this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1720b(userProfilerLoginListener, application));
    }

    public void registerPNSDeviceIdentifier(Application application, String str) {
        StringEntity stringEntity;
        StringEntity stringEntity2;
        UnsupportedEncodingException e;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1565a);
        sb.append("profiler_devices/set_pns_device_identifier");
        String sb2 = sb.toString();
        String str2 = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.authenticationToken;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Token token=");
        sb3.append(str2);
        Header[] headerArr = {new BasicHeader("Authorization", sb3.toString())};
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_identifier", C1556a.m630a(application));
            jSONObject.put("device_id", C1556a.m635d(application.getApplicationContext()));
            jSONObject.put("os_type", C1556a.m629a());
            jSONObject.put("pns_device_identifier", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            stringEntity2 = new StringEntity(jSONObject.toString());
            try {
                stringEntity2.setContentType((Header) new BasicHeader("Content-Type", "application/json"));
                stringEntity = stringEntity2;
            } catch (UnsupportedEncodingException e3) {
                e = e3;
                e.printStackTrace();
                stringEntity = stringEntity2;
                this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1725g());
            }
        } catch (UnsupportedEncodingException e4) {
            UnsupportedEncodingException unsupportedEncodingException = e4;
            stringEntity2 = null;
            e = unsupportedEncodingException;
            e.printStackTrace();
            stringEntity = stringEntity2;
            this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1725g());
        }
        this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1725g());
    }

    public void registerUser(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, UserProfilerRegistrationListener userProfilerRegistrationListener) throws InvalidParameterException, JSONException {
        StringEntity stringEntity;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1565a);
        sb.append("profilers/register");
        String sb2 = sb.toString();
        String str10 = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.authenticationToken;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Token token=");
        sb3.append(str10);
        Header[] headerArr = {new BasicHeader("Authorization", sb3.toString()), new BasicHeader("device_id", C1556a.m635d(application))};
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("profiler", m1150a(str, str2, str3, str4, str5, str6, str7, str8, str9));
        jSONObject.put("profiler_device", m1149a(application));
        try {
            stringEntity = new StringEntity(jSONObject.toString());
            try {
                stringEntity.setContentType((Header) new BasicHeader("Content-Type", "application/json"));
            } catch (UnsupportedEncodingException e) {
                e = e;
            }
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            stringEntity = null;
            e.printStackTrace();
            m1161c(application.getApplicationContext());
            Application application2 = application;
            this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1719a(userProfilerRegistrationListener, application));
        }
        m1161c(application.getApplicationContext());
        Application application22 = application;
        this.f1573i.post((Context) null, sb2, headerArr, (HttpEntity) stringEntity, "application/json", (ResponseHandlerInterface) new C1719a(userProfilerRegistrationListener, application));
    }

    public void startSync(Application application, String str, String str2) {
        m1152a(application, str, str2);
    }

    /* renamed from: a */
    public void mo29422a(Application application, String str, String str2, OnSyncListener onSyncListener) {
        Prefs instance = Prefs.getInstance(application.getApplicationContext());
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1565a);
        sb.append(String.format("navibees_applications/%s", new Object[]{str}));
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Token token=");
        sb3.append(str2);
        Header[] headerArr = {new BasicHeader("Authorization", sb3.toString()), new BasicHeader("device_id", C1556a.m635d(application.getApplicationContext()))};
        if (NaviBeesBeaconManager.SHOW_LOG) {
            Toast.makeText(application.getApplicationContext(), "Syncing Data", 0).show();
        }
        m1161c(application.getApplicationContext());
        this.f1573i.get((Context) null, sb2, headerArr, (RequestParams) null, (ResponseHandlerInterface) new C1721c(application, onSyncListener, instance));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m1159a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(date);
        instance2.setTime(date2);
        return instance2.after(instance);
    }

    /* renamed from: a */
    private void m1152a(Application application, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1565a);
        sb.append(String.format("navibees_applications/%s", new Object[]{str}));
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Token token=");
        sb3.append(str2);
        Header[] headerArr = {new BasicHeader("Authorization", sb3.toString()), new BasicHeader("device_id", C1556a.m635d(application.getApplicationContext()))};
        m1161c(application.getApplicationContext());
        Log.m1171d("NavibeesServerManager", "Sending Application request");
        this.f1573i.get((Context) null, sb2, headerArr, (RequestParams) null, (ResponseHandlerInterface) new C1722d(application));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1151a(Application application, OnSyncListener onSyncListener) {
        LinkedList linkedList = new LinkedList();
        SimpleArrayMap venues = NaviBeesManager.getInstance(application).getMetaDataManager().getVenues();
        for (int i = 0; i < venues.size(); i++) {
            Venue venue = (Venue) venues.valueAt(i);
            if (venue.buildings != null) {
                for (int i2 = 0; i2 < venue.buildings.size(); i2++) {
                    linkedList.add((Building) venue.buildings.valueAt(i2));
                }
            }
        }
        String a = m1146a(application.getApplicationContext());
        if (TextUtils.isEmpty(a)) {
            m1153a(application, linkedList, null, true, onSyncListener);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(">>>>>>> Status call last_updated_date= ");
        sb.append(a);
        Log.m1171d("NaviBeesServer", sb.toString());
        String str = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.projectId;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f1565a);
        sb2.append(String.format("projects/%s/get_buildings_status?last_updated_date=%s", new Object[]{str, a}));
        String sb3 = sb2.toString();
        String str2 = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.authenticationToken;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Token token=");
        sb4.append(str2);
        this.f1573i.get((Context) null, sb3, new Header[]{new BasicHeader("Authorization", sb4.toString()), new BasicHeader("device_id", C1556a.m635d(application.getApplicationContext()))}, (RequestParams) null, (ResponseHandlerInterface) new C1723e(application, onSyncListener, linkedList));
    }

    /* renamed from: a */
    private String m1146a(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(NaviBeesConstants.STATUS_LAST_UPDATED_DATE, null);
    }

    /* renamed from: a */
    private void m1154a(Context context, String str) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(NaviBeesConstants.STATUS_LAST_UPDATED_DATE, str);
        edit.apply();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m1148a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        try {
            if (simpleDateFormat.parse(str).after(simpleDateFormat.parse(str2))) {
                return str;
            }
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1153a(Application application, Queue<Building> queue, String str, boolean z, OnSyncListener onSyncListener) {
        String str2;
        String str3 = str;
        if (queue.isEmpty()) {
            Log.m1171d(f1564k, ">>>>>>>>All buildings update completed");
            if (z && str3 != null) {
                String str4 = f1564k;
                StringBuilder sb = new StringBuilder();
                sb.append(">>>>>>>> Set new status update date = ");
                sb.append(str3);
                Log.m1171d(str4, sb.toString());
                if (onSyncListener != null) {
                    onSyncListener.buildingSyncCompleted();
                }
                m1154a(application.getApplicationContext(), str3);
            } else if (queue.size() == 0 && onSyncListener != null) {
                onSyncListener.buildingSyncCompleted();
            }
            m1160b(application.getApplicationContext());
            return;
        }
        Building building = new Building((Building) queue.poll());
        boolean loadBuildingDataFromFile = building.loadBuildingDataFromFile(application);
        Text text = building.name;
        String text2 = text != null ? text.getText() : "empty";
        String str5 = f1564k;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(">>>>>>>>>Start get Building #");
        sb2.append(queue.size());
        sb2.append(" with name ");
        sb2.append(text2);
        Log.m1171d(str5, sb2.toString());
        if (!building.getDidSyncAll(application.getApplicationContext())) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.f1565a);
            sb3.append(String.format("buildings/%s/get_all_building", new Object[]{building.f1329id}));
            String sb4 = sb3.toString();
            Log.m1171d(f1564k, ">>>>>>>>>Trying to get building with sync all");
            str2 = sb4;
        } else {
            String str6 = building.updatedAt;
            if (!loadBuildingDataFromFile || TextUtils.isEmpty(str6)) {
                str6 = "1970-01-01T00:00:00.000Z";
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.f1565a);
            sb5.append(String.format("buildings/%s/sync?last_updated_date=%s", new Object[]{building.f1329id, str6}));
            String sb6 = sb5.toString();
            String str7 = f1564k;
            StringBuilder sb7 = new StringBuilder();
            sb7.append(">>>>>>>>>Trying get building with updated at= ");
            sb7.append(str6);
            Log.m1171d(str7, sb7.toString());
            str2 = sb6;
        }
        String str8 = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.authenticationToken;
        StringBuilder sb8 = new StringBuilder();
        sb8.append("Token token=");
        sb8.append(str8);
        Header[] headerArr = {new BasicHeader("Authorization", sb8.toString()), new BasicHeader("device_id", C1556a.m635d(application.getApplicationContext()))};
        AsyncHttpClient asyncHttpClient = this.f1573i;
        C1724f fVar = new C1724f(str, building, application, onSyncListener, queue, z);
        asyncHttpClient.get((Context) null, str2, headerArr, (RequestParams) null, (ResponseHandlerInterface) fVar);
    }

    /* renamed from: a */
    private JSONObject m1149a(Application application) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_id", C1556a.m635d(application.getApplicationContext()));
        jSONObject.put("app_identifier", C1556a.m630a(application));
        jSONObject.put("app_id", NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.f1338id);
        jSONObject.put("app_version", C1556a.m631a(application.getApplicationContext()));
        jSONObject.put("device_type", C1556a.m633b(application.getApplicationContext()));
        jSONObject.put("os_type", C1556a.m629a());
        jSONObject.put("os_version", C1556a.m634c(application.getApplicationContext()));
        return jSONObject;
    }

    /* renamed from: a */
    private JSONObject m1150a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) throws InvalidParameterException, JSONException {
        JSONObject jSONObject = new JSONObject();
        if ((TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) && (TextUtils.isEmpty(str5) || TextUtils.isEmpty(str4))) {
            throw new InvalidParameterException("email/password or providerId/provider is missing");
        }
        jSONObject.put("name", str);
        jSONObject.put("email", str2);
        jSONObject.put("password", str3);
        jSONObject.put("provider_type", str4);
        jSONObject.put("provider_identifier", str5);
        jSONObject.put("gender", str6);
        jSONObject.put("phone_number", str7);
        jSONObject.put("birthday", str8);
        jSONObject.put("photo_url", str9);
        return jSONObject;
    }
}
