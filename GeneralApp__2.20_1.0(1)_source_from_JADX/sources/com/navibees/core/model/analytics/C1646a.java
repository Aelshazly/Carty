package com.navibees.core.model.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.preference.PreferenceManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;
import com.navibees.core.C1556a;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.entity.ByteArrayEntity;
import p008cz.msebera.android.httpclient.message.BasicHeader;

/* renamed from: com.navibees.core.model.analytics.a */
/* compiled from: NaviBeesAnalytics */
public class C1646a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final String f1249a;

    /* renamed from: b */
    private final String f1250b;

    /* renamed from: c */
    private final String f1251c;

    /* renamed from: d */
    private final String f1252d;

    /* renamed from: e */
    private final String f1253e;

    /* renamed from: f */
    private ArrayList<IndoorLocation> f1254f;

    /* renamed from: g */
    private final long f1255g;

    /* renamed from: h */
    private final int f1256h;

    /* renamed from: i */
    private final long f1257i;

    /* renamed from: j */
    private long f1258j;

    /* renamed from: k */
    private long f1259k;

    /* renamed from: l */
    private final long f1260l;

    /* renamed from: m */
    private final String f1261m;

    /* renamed from: n */
    private final String f1262n;

    /* renamed from: o */
    private final String f1263o;

    /* renamed from: p */
    private Timer f1264p;

    /* renamed from: q */
    private TimerTask f1265q;

    /* renamed from: r */
    private final String f1266r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public Activity f1267s;

    /* renamed from: t */
    private final String f1268t;

    /* renamed from: u */
    private final String f1269u;

    /* renamed from: v */
    private final String f1270v;

    /* renamed from: w */
    private final String f1271w;

    /* renamed from: x */
    private final int f1272x;

    /* renamed from: y */
    private final int f1273y;
    /* access modifiers changed from: private */

    /* renamed from: z */
    public AsyncHttpClient f1274z;

    /* renamed from: com.navibees.core.model.analytics.a$a */
    /* compiled from: NaviBeesAnalytics */
    class C1647a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Application f1275a;

        /* renamed from: b */
        final /* synthetic */ ByteArrayEntity f1276b;

        /* renamed from: com.navibees.core.model.analytics.a$a$a */
        /* compiled from: NaviBeesAnalytics */
        class C1648a extends AsyncHttpResponseHandler {
            C1648a() {
            }

            public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                Log.e("Navibees Analytics", "locations requests failed");
            }

            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                Log.d("Navibees Analytics", "locations requests succeed");
            }
        }

        C1647a(Application application, ByteArrayEntity byteArrayEntity) {
            this.f1275a = application;
            this.f1276b = byteArrayEntity;
        }

        public void run() {
            StringBuilder sb = new StringBuilder();
            sb.append(C1646a.this.f1249a);
            sb.append("agent_locations");
            C1646a.this.f1274z.post((Context) null, sb.toString(), C1646a.this.m939b((Context) this.f1275a), (HttpEntity) this.f1276b, "application/json; charset=utf-8", (ResponseHandlerInterface) new C1648a());
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$b */
    /* compiled from: NaviBeesAnalytics */
    class C1649b extends AsyncHttpResponseHandler {
        C1649b() {
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.e("Navibees Analytics", "locations requests failed");
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Log.d("Navibees Analytics", "locations requests succeed");
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$c */
    /* compiled from: NaviBeesAnalytics */
    class C1650c extends AsyncHttpResponseHandler {
        C1650c() {
        }

        public boolean getUseSynchronousMode() {
            return false;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.d("Navibees Analytics", "Session requests failed");
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Log.d("Navibees Analytics", "Session requests succeed");
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$d */
    /* compiled from: NaviBeesAnalytics */
    class C1651d extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ SharedPreferences f1281a;

        C1651d(SharedPreferences sharedPreferences) {
            this.f1281a = sharedPreferences;
        }

        public boolean getUseSynchronousMode() {
            return false;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Log.d("Navibees Analytics", "First Session requests failed");
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Log.d("Navibees Analytics", "First Session request succeed");
            Editor edit = this.f1281a.edit();
            edit.putBoolean("navibees_first_session_success", true);
            edit.apply();
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$e */
    /* compiled from: NaviBeesAnalytics */
    class C1652e extends AsyncHttpResponseHandler {
        C1652e() {
        }

        public boolean getUseSynchronousMode() {
            return false;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Entered region failed with status code = ");
            sb.append(i);
            Log.e("NaviBees Analytics", sb.toString());
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Log.d("NaviBees Analytics", "Entered region succeeded");
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$f */
    /* compiled from: NaviBeesAnalytics */
    class C1653f extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ Context f1284a;

        /* renamed from: b */
        final /* synthetic */ C1656b f1285b;

        C1653f(Context context, C1656b bVar) {
            this.f1284a = context;
            this.f1285b = bVar;
        }

        public boolean getUseSynchronousMode() {
            return false;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Visited region failed with status code = ");
            sb.append(i);
            Log.d("NaviBees Analytics", sb.toString());
            C1646a.this.m927a(this.f1284a, this.f1285b);
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Log.d("NaviBees Analytics", "Visited region succeeded");
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$g */
    /* compiled from: NaviBeesAnalytics */
    class C1654g extends AsyncHttpResponseHandler {

        /* renamed from: a */
        final /* synthetic */ Context f1287a;

        /* renamed from: b */
        final /* synthetic */ List f1288b;

        /* renamed from: c */
        final /* synthetic */ C1656b f1289c;

        C1654g(Context context, List list, C1656b bVar) {
            this.f1287a = context;
            this.f1288b = list;
            this.f1289c = bVar;
        }

        public boolean getUseSynchronousMode() {
            return false;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Retry failed region event failed with status code = ");
            sb.append(i);
            Log.d("NaviBees Analytics", sb.toString());
            C1646a.this.m927a(this.f1287a, this.f1289c);
            C1646a.this.m930a(this.f1287a, this.f1288b);
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            Log.d("NaviBees Analytics", "Retry failed region event succeeded");
            C1646a.this.m930a(this.f1287a, this.f1288b);
        }
    }

    /* renamed from: com.navibees.core.model.analytics.a$h */
    /* compiled from: NaviBeesAnalytics */
    private class C1655h extends TimerTask {
        private C1655h() {
        }

        public void run() {
            C1646a aVar = C1646a.this;
            aVar.m926a(aVar.f1267s.getApplication(), "ack");
        }

        /* synthetic */ C1655h(C1646a aVar, C1647a aVar2) {
            this();
        }
    }

    public C1646a() {
        this.f1249a = C1556a.f953a ? "http://analytics.navibees.com/api/v1/" : "http://52.40.190.56/api/v1/";
        this.f1250b = "agent_locations";
        this.f1251c = "sessions";
        this.f1252d = "sessions/first";
        this.f1253e = "region_visits";
        this.f1255g = 240000;
        this.f1256h = 48;
        this.f1257i = 5000;
        this.f1258j = 0;
        this.f1259k = 0;
        this.f1260l = 180000;
        this.f1261m = "start";
        this.f1262n = "ack";
        this.f1263o = "end";
        this.f1266r = "navibees_last_ack_timestamp";
        this.f1268t = "navibees_first_session_success";
        this.f1269u = "navibees_first_session_timestamp";
        this.f1270v = "navibees_pending_events";
        this.f1271w = "navibees_failed_events";
        this.f1272x = 86400;
        this.f1273y = HttpStatus.SC_MULTIPLE_CHOICES;
        this.f1254f = new ArrayList<>();
        this.f1274z = new AsyncHttpClient();
    }

    /* renamed from: b */
    private void m937b(Application application) {
        if (this.f1254f.size() > 0) {
            this.f1258j = System.currentTimeMillis();
            ByteArrayEntity a = m924a(application, (List<IndoorLocation>) this.f1254f);
            if (a != null) {
                this.f1254f.clear();
                StringBuilder sb = new StringBuilder();
                sb.append(this.f1249a);
                sb.append("agent_locations");
                this.f1274z.post((Context) null, sb.toString(), m939b((Context) application), (HttpEntity) a, "application/json; charset=utf-8", (ResponseHandlerInterface) new C1649b());
            }
        }
    }

    /* renamed from: c */
    private HashMap<String, C1656b> m941c(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        HashMap<String, C1656b> hashMap = new HashMap<>();
        try {
            JSONArray jSONArray = new JSONArray(defaultSharedPreferences.getString("navibees_pending_events", "-"));
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    C1656b bVar = new C1656b(jSONArray.getJSONObject(i));
                    if (currentTimeMillis - bVar.f1303e < 86400) {
                        hashMap.put(bVar.f1302d.identifier, bVar);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    /* renamed from: a */
    public void mo29034a(Application application, IndoorLocation indoorLocation) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f1259k >= 5000) {
            this.f1259k = currentTimeMillis;
            indoorLocation.timestamp = currentTimeMillis;
            this.f1254f.add(indoorLocation);
            if (((double) (currentTimeMillis - this.f1258j)) >= 240000.0d || this.f1254f.size() >= 48) {
                this.f1258j = currentTimeMillis;
                ByteArrayEntity a = m924a(application, (List<IndoorLocation>) this.f1254f);
                if (a != null) {
                    this.f1254f.clear();
                    new Handler(Looper.getMainLooper()).post(new C1647a(application, a));
                }
            }
        }
    }

    /* renamed from: b */
    public void mo29038b(Activity activity) {
        if (this.f1267s == activity) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext()).edit();
            edit.putLong("navibees_last_ack_timestamp", System.currentTimeMillis());
            edit.apply();
            TimerTask timerTask = this.f1265q;
            if (timerTask != null) {
                timerTask.cancel();
                this.f1265q = null;
            }
            Timer timer = this.f1264p;
            if (timer != null) {
                timer.cancel();
                this.f1264p.purge();
                this.f1264p = null;
            }
            this.f1267s = null;
            m937b(activity.getApplication());
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29035a(Application application, IndoorLocation indoorLocation, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        indoorLocation.timestamp = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        arrayList.add(indoorLocation);
        ByteArrayEntity a = m924a(application, (List<IndoorLocation>) arrayList);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1249a);
        sb.append("agent_locations");
        String sb2 = sb.toString();
        if (a == null) {
            asyncHttpResponseHandler.onFailure(-1, null, null, null);
        } else {
            new SyncHttpClient().post((Context) null, sb2, m939b((Context) application), (HttpEntity) a, "application/json; charset=utf-8", (ResponseHandlerInterface) asyncHttpResponseHandler);
        }
    }

    /* renamed from: b */
    private void m938b(Context context, MonitoredRegion monitoredRegion) {
        C1656b bVar;
        synchronized ("navibees_pending_events") {
            HashMap c = m941c(context);
            bVar = (C1656b) c.remove(monitoredRegion.identifier);
            m929a(context, c);
        }
        if (bVar != null) {
            bVar.f1305g = Long.valueOf((System.currentTimeMillis() / 1000) - bVar.f1303e);
            bVar.f1301c = C1658c.VISITED;
            bVar.f1304f = Long.valueOf(System.currentTimeMillis() / 1000);
            m934a(bVar, context, (AsyncHttpResponseHandler) new C1653f(context, bVar));
        }
    }

    /* renamed from: a */
    private ByteArrayEntity m924a(Application application, List<IndoorLocation> list) {
        String str;
        String str2 = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
        JSONArray jSONArray = new JSONArray();
        for (IndoorLocation indoorLocation : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                String str3 = "x";
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(indoorLocation.f1332x);
                jSONObject.put(str3, sb.toString());
                String str4 = "y";
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(indoorLocation.f1333y);
                jSONObject.put(str4, sb2.toString());
                SimpleArrayMap buildings = NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings();
                if (!(buildings == null || buildings.get(indoorLocation.buildingId) == null)) {
                    Building building = (Building) buildings.get(indoorLocation.buildingId);
                    if (indoorLocation.floor < building.floors.size()) {
                        str = ((Floor) building.floors.get(indoorLocation.floor)).f1331id;
                        jSONObject.put("floor_id", str);
                        jSONObject.put("building_id", indoorLocation.buildingId);
                        jSONObject.put("venue_id", indoorLocation.venueId);
                        jSONObject.put("ts", simpleDateFormat.format(new Date(indoorLocation.timestamp)));
                        jSONArray.put(jSONObject);
                    }
                }
                str = str2;
                jSONObject.put("floor_id", str);
                jSONObject.put("building_id", indoorLocation.buildingId);
                jSONObject.put("venue_id", indoorLocation.venueId);
                jSONObject.put("ts", simpleDateFormat.format(new Date(indoorLocation.timestamp)));
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("bundle_id", C1556a.m630a(application));
            jSONObject2.put("app_version", C1556a.m631a((Context) application));
            jSONObject2.put("device_type", C1556a.m633b(application));
            jSONObject2.put("os_version", C1556a.m634c(application));
            jSONObject2.put("os_type", C1556a.m629a());
            jSONObject2.put("agent_id", C1556a.m635d(application));
            jSONObject2.put("date", simpleDateFormat2.format(new Date(System.currentTimeMillis())));
            jSONObject2.put("data", jSONArray);
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(jSONObject2.toString().getBytes("UTF-8"));
            byteArrayEntity.setContentType((Header) new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            return byteArrayEntity;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public Header[] m939b(Context context) {
        if (!((context.getApplicationInfo().flags & 2) != 0)) {
            return null;
        }
        return new Header[]{new BasicHeader("DEBUG", "true")};
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m926a(Application application, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1249a);
        sb.append("sessions");
        String sb2 = sb.toString();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", str);
            jSONObject.put("bundle_id", C1556a.m630a(application));
            jSONObject.put("agent_id", C1556a.m635d(application));
            jSONObject.put("event_ts", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US).format(new Date(System.currentTimeMillis())));
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(jSONObject.toString().getBytes("UTF-8"));
            byteArrayEntity.setContentType((Header) new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            this.f1274z.post((Context) null, sb2, m939b((Context) application), (HttpEntity) byteArrayEntity, "application/json; charset=utf-8", (ResponseHandlerInterface) new C1650c());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo29032a(Activity activity) {
        this.f1267s = activity;
        if (this.f1264p == null) {
            long currentTimeMillis = System.currentTimeMillis() - PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext()).getLong("navibees_last_ack_timestamp", 0);
            this.f1264p = new Timer();
            this.f1265q = new C1655h(this, null);
            if (currentTimeMillis > 180000) {
                m926a(activity.getApplication(), "start");
                this.f1264p.schedule(this.f1265q, 180000, 180000);
                return;
            }
            this.f1264p.schedule(this.f1265q, 180000 - currentTimeMillis, 180000);
        }
    }

    /* renamed from: a */
    public void mo29033a(Application application) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        if (!defaultSharedPreferences.getBoolean("navibees_first_session_success", false)) {
            String str = "navibees_first_session_timestamp";
            if (!defaultSharedPreferences.contains(str)) {
                Editor edit = defaultSharedPreferences.edit();
                edit.putLong(str, System.currentTimeMillis());
                edit.apply();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.f1249a);
            sb.append("sessions/first");
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bundle_id", C1556a.m630a(application));
                jSONObject.put("agent_id", C1556a.m635d(application));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
                long j = defaultSharedPreferences.getLong(str, System.currentTimeMillis());
                jSONObject.put("ts", simpleDateFormat.format(new Date(j)));
                ByteArrayEntity byteArrayEntity = new ByteArrayEntity(jSONObject.toString().getBytes("UTF-8"));
                byteArrayEntity.setContentType((Header) new BasicHeader("Content-Type", "application/json; charset=utf-8"));
                this.f1274z.post((Context) null, sb2, m939b((Context) application), (HttpEntity) byteArrayEntity, "application/json; charset=utf-8", (ResponseHandlerInterface) new C1651d(defaultSharedPreferences));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo29037a(Context context, MonitoredRegion monitoredRegion, String str) {
        if (str.equalsIgnoreCase(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_ENTER_VALUE)) {
            m928a(context, monitoredRegion);
        } else if (str.equalsIgnoreCase(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_EXIT_VALUE)) {
            m938b(context, monitoredRegion);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("State of region ");
            sb.append(monitoredRegion.identifier);
            sb.append(" not determined");
            Log.d("NaviBees Analytics", sb.toString());
        }
    }

    /* renamed from: a */
    private void m928a(Context context, MonitoredRegion monitoredRegion) {
        synchronized ("navibees_pending_events") {
            HashMap c = m941c(context);
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            C1656b bVar = (C1656b) c.get(monitoredRegion.identifier);
            if (bVar == null || currentTimeMillis - bVar.f1303e >= 300) {
                c.remove(monitoredRegion.identifier);
                C1656b bVar2 = new C1656b(context, monitoredRegion);
                c.put(bVar2.f1302d.identifier, bVar2);
                m929a(context, c);
                m934a(bVar2, context, (AsyncHttpResponseHandler) new C1652e());
            }
        }
    }

    /* renamed from: a */
    private void m934a(C1656b bVar, Context context, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1249a);
        sb.append("region_visits");
        String sb2 = sb.toString();
        try {
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bVar.mo29041a().toString().getBytes("UTF-8"));
            byteArrayEntity.setContentType((Header) new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            this.f1274z.post((Context) null, sb2, m939b(context), (HttpEntity) byteArrayEntity, "application/json; charset=utf-8", (ResponseHandlerInterface) asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m929a(Context context, HashMap<String, C1656b> hashMap) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        JSONArray jSONArray = new JSONArray();
        for (C1656b a : hashMap.values()) {
            JSONObject a2 = a.mo29041a();
            if (a2 != null) {
                jSONArray.put(a2);
            }
        }
        edit.putString("navibees_pending_events", jSONArray.toString());
        edit.apply();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m927a(Context context, C1656b bVar) {
        JSONArray jSONArray;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        synchronized ("navibees_failed_events") {
            try {
                jSONArray = new JSONArray(defaultSharedPreferences.getString("navibees_failed_events", "-"));
            } catch (Exception e) {
                e.printStackTrace();
                jSONArray = new JSONArray();
            }
            jSONArray.put(bVar.mo29041a());
            Editor edit = defaultSharedPreferences.edit();
            edit.putString("navibees_failed_events", jSONArray.toString());
            edit.apply();
        }
    }

    /* renamed from: a */
    public void mo29036a(Context context) {
        ArrayList arrayList = new ArrayList();
        synchronized ("navibees_failed_events") {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            JSONArray jSONArray = null;
            try {
                jSONArray = new JSONArray(defaultSharedPreferences.getString("navibees_failed_events", null));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i = 0;
            while (jSONArray != null && i < jSONArray.length()) {
                try {
                    arrayList.add(new C1656b(jSONArray.getJSONObject(i)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                i++;
            }
            Editor edit = defaultSharedPreferences.edit();
            edit.remove("navibees_failed_events");
            edit.apply();
        }
        if (arrayList.size() != 0) {
            m930a(context, (List<C1656b>) arrayList);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m930a(Context context, List<C1656b> list) {
        if (list.size() != 0) {
            C1656b bVar = (C1656b) list.remove(0);
            bVar.f1304f = Long.valueOf(System.currentTimeMillis() / 1000);
            m934a(bVar, context, (AsyncHttpResponseHandler) new C1654g(context, list, bVar));
        }
    }
}
