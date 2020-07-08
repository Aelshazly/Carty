package com.navibees.core.blesdk;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.blesdk.BeaconService.C1619e;
import com.navibees.core.blesdk.p022d.C1626a;
import com.navibees.core.blesdk.p022d.C1627b;
import com.navibees.core.blesdk.p022d.C1628c;
import com.navibees.core.blesdk.p022d.C1630e;
import com.navibees.core.blesdk.p022d.C1631f;
import com.navibees.core.blesdk.p023e.C1632a;
import com.navibees.core.blesdk.p023e.C1634b;
import com.navibees.core.model.postioning.NaviBeesBeaconManager;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.navibees.core.blesdk.a */
/* compiled from: BeaconManager */
public class C1620a {

    /* renamed from: A */
    private static C1620a f1183A = null;
    /* access modifiers changed from: private */

    /* renamed from: B */
    public static Application f1184B = null;

    /* renamed from: C */
    public static int f1185C = 13;

    /* renamed from: D */
    public static int f1186D = 10;

    /* renamed from: E */
    public static int f1187E = 11;

    /* renamed from: F */
    public static int f1188F = 12;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public BeaconService f1189a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f1190b;

    /* renamed from: c */
    Intent f1191c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1626a f1192d;

    /* renamed from: e */
    private C1630e f1193e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1631f f1194f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C1628c f1195g;

    /* renamed from: h */
    private boolean f1196h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f1197i;

    /* renamed from: j */
    private C1625c f1198j;

    /* renamed from: k */
    public long f1199k = 2000;

    /* renamed from: l */
    public long f1200l = 7000;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public long f1201m = 2000;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public long f1202n = 0;

    /* renamed from: o */
    private int f1203o = 3;

    /* renamed from: p */
    private List<C1634b> f1204p;

    /* renamed from: q */
    private List<C1634b> f1205q;

    /* renamed from: r */
    private List<C1634b> f1206r = new ArrayList();

    /* renamed from: s */
    private List<C1634b> f1207s = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: t */
    public Map<String, C1632a> f1208t = new HashMap();

    /* renamed from: u */
    private Map<String, Integer> f1209u = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: v */
    public Map<String, C1634b> f1210v = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: w */
    public Handler f1211w = new Handler();
    /* access modifiers changed from: private */

    /* renamed from: x */
    public Runnable f1212x = new C1621a();
    /* access modifiers changed from: private */

    /* renamed from: y */
    public C1627b f1213y = new C1622b();

    /* renamed from: z */
    private ServiceConnection f1214z = new C1623c();

    /* renamed from: com.navibees.core.blesdk.a$a */
    /* compiled from: BeaconManager */
    class C1621a implements Runnable {
        C1621a() {
        }

        public void run() {
            if (System.currentTimeMillis() - C1620a.this.f1202n >= C1620a.this.f1201m) {
                C1620a.this.f1202n = System.currentTimeMillis();
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Posting Timestamp: ");
                    sb.append(System.currentTimeMillis());
                    printStream.println(sb.toString());
                }
                if (C1620a.this.f1197i) {
                    for (Entry entry : C1620a.this.f1210v.entrySet()) {
                        String str = (String) entry.getKey();
                        C1634b bVar = (C1634b) entry.getValue();
                        List a = C1620a.this.m842a(str);
                        int minimumBeacons = NaviBeesManager.getInstance(C1620a.f1184B).getMetaDataManager().getMinimumBeacons();
                        if (C1620a.this.f1197i && C1620a.this.f1194f != null && a.size() >= minimumBeacons) {
                            if (NaviBeesBeaconManager.SHOW_LOG) {
                                PrintStream printStream2 = System.out;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Posting Beacons, Region: ");
                                sb2.append(bVar);
                                sb2.append(", Length:");
                                sb2.append(a.size());
                                printStream2.println(sb2.toString());
                            }
                            C1620a.this.f1194f.didRangeBeaconsInRegion(a, bVar);
                        }
                    }
                    C1620a.this.f1210v.clear();
                    C1620a.this.f1208t.clear();
                    C1620a.this.f1211w.postDelayed(C1620a.this.f1212x, C1620a.this.f1201m);
                }
            }
        }
    }

    /* renamed from: com.navibees.core.blesdk.a$b */
    /* compiled from: BeaconManager */
    class C1622b implements C1627b {
        C1622b() {
        }

        /* renamed from: a */
        public void mo28953a(C1634b bVar, C1632a aVar) {
            if (C1620a.this.f1190b) {
                StringBuilder sb = new StringBuilder();
                sb.append(bVar.mo28996e());
                String str = ":";
                sb.append(str);
                sb.append(aVar.mo28973c());
                sb.append(str);
                sb.append(aVar.mo28975d());
                C1620a.this.f1208t.put(sb.toString(), aVar);
                C1620a.this.f1210v.put(bVar.mo28996e(), bVar);
            }
        }
    }

    /* renamed from: com.navibees.core.blesdk.a$c */
    /* compiled from: BeaconManager */
    class C1623c implements ServiceConnection {
        C1623c() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            C1620a.this.f1189a = ((C1619e) iBinder).mo28933a();
            C1620a.this.f1189a.mo28917a();
            C1620a.this.f1189a.mo28919a(C1620a.this.f1213y);
            C1620a.this.f1189a.mo28920a(C1620a.this.f1195g);
            C1620a.this.f1192d.onBeaconServiceConnect();
            C1620a.this.f1190b = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            C1620a.this.f1190b = false;
        }
    }

    private C1620a(Application application) {
        f1184B = application;
        Log.d("blesdk", "starting beacon service");
        this.f1191c = new Intent(application.getApplicationContext(), BeaconService.class);
        if (VERSION.SDK_INT >= 26) {
            application.startForegroundService(this.f1191c);
        } else {
            application.startService(this.f1191c);
        }
    }

    /* renamed from: e */
    private void m849e() {
        for (String str : this.f1209u.keySet()) {
            int intValue = ((Integer) this.f1209u.get(str)).intValue();
            if (intValue >= this.f1203o) {
                this.f1208t.remove(str);
                this.f1209u.remove(str);
            } else {
                this.f1209u.put(str, Integer.valueOf(intValue + 1));
            }
        }
    }

    /* renamed from: b */
    public void mo28944b(long j) {
        this.f1199k = j;
    }

    /* renamed from: c */
    public C1625c mo28948c() {
        return this.f1198j;
    }

    /* renamed from: b */
    public boolean mo28947b(C1626a aVar) {
        return this.f1192d == aVar;
    }

    /* renamed from: c */
    public void mo28949c(C1626a aVar) {
        Log.d("blesdk", "unbinding beacon service");
        if (this.f1192d == aVar) {
            this.f1189a.mo28921b();
            this.f1189a.mo28924e();
            f1184B.unbindService(this.f1214z);
            this.f1190b = false;
            this.f1192d = null;
        }
    }

    /* renamed from: d */
    public void mo28951d(C1634b bVar) {
        this.f1205q.remove(bVar);
    }

    /* renamed from: b */
    public void mo28946b(List<C1634b> list) {
        this.f1205q = list;
        this.f1197i = true;
    }

    /* renamed from: a */
    public static synchronized C1620a m840a(Application application) {
        C1620a aVar;
        synchronized (C1620a.class) {
            if (f1183A == null) {
                f1183A = new C1620a(application);
            }
            aVar = f1183A;
        }
        return aVar;
    }

    /* renamed from: b */
    public void mo28945b(C1634b bVar) {
        if (this.f1205q == null) {
            this.f1205q = new ArrayList();
        }
        this.f1205q.add(bVar);
        this.f1197i = true;
        this.f1211w.postDelayed(this.f1212x, this.f1201m);
    }

    /* renamed from: a */
    public void mo28935a(long j) {
        this.f1200l = j;
    }

    /* renamed from: c */
    public void mo28950c(C1634b bVar) {
        this.f1204p.remove(bVar);
    }

    /* renamed from: a */
    public void mo28939a(C1630e eVar) {
        this.f1193e = eVar;
    }

    /* renamed from: a */
    public void mo28940a(C1631f fVar) {
        this.f1194f = fVar;
    }

    /* renamed from: b */
    public List<C1634b> mo28943b() {
        if (this.f1205q == null) {
            this.f1205q = new ArrayList();
        }
        return this.f1205q;
    }

    /* renamed from: a */
    public void mo28938a(C1628c cVar) {
        this.f1195g = cVar;
    }

    /* renamed from: a */
    public void mo28937a(C1626a aVar) throws Exception {
        this.f1192d = aVar;
        f1184B.bindService(this.f1191c, this.f1214z, 1);
        Log.d("blesdk", "binding beacon service");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public List<C1632a> m842a(String str) {
        ArrayList arrayList = new ArrayList();
        for (Entry entry : this.f1208t.entrySet()) {
            if (((String) entry.getKey()).startsWith(str)) {
                arrayList.add((C1632a) entry.getValue());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo28936a(C1625c cVar) {
        this.f1198j = cVar;
    }

    /* renamed from: a */
    public void mo28942a(List<C1634b> list) {
        this.f1204p = list;
        this.f1196h = true;
    }

    /* renamed from: a */
    public void mo28941a(C1634b bVar) {
        if (this.f1204p == null) {
            this.f1204p = new ArrayList();
        }
        this.f1204p.add(bVar);
        this.f1196h = true;
    }

    /* renamed from: a */
    public List<C1634b> mo28934a() {
        if (this.f1204p == null) {
            this.f1204p = new ArrayList();
        }
        return this.f1204p;
    }
}
