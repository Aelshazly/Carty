package com.navibees.core.blesdk;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Builder;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.blesdk.p022d.C1627b;
import com.navibees.core.blesdk.p022d.C1628c;
import com.navibees.core.blesdk.p023e.C1632a;
import com.navibees.core.blesdk.p023e.C1634b;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import com.navibees.core.model.postioning.NaviBeesBeaconManager;
import com.navibees.sdk.C1266R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class BeaconService extends Service {

    /* renamed from: s */
    static final /* synthetic */ boolean f1159s = (!BeaconService.class.desiredAssertionStatus());

    /* renamed from: a */
    private C1624b f1160a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1627b f1161b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1620a f1162c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1628c f1163d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C1625c f1164e;

    /* renamed from: f */
    private Handler f1165f = new Handler();
    /* access modifiers changed from: private */

    /* renamed from: g */
    public boolean f1166g;

    /* renamed from: h */
    private List<C1634b> f1167h = new ArrayList();

    /* renamed from: i */
    private List<C1634b> f1168i = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: j */
    public NaviBeesApplication f1169j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public HashMap<String, Long> f1170k = new HashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: l */
    public HashMap<String, MonitoredRegion> f1171l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public boolean f1172m;

    /* renamed from: n */
    private final IBinder f1173n = new C1619e();

    /* renamed from: o */
    private final BroadcastReceiver f1174o = new C1615a();

    /* renamed from: p */
    LeScanCallback f1175p = new C1616b();
    /* access modifiers changed from: private */

    /* renamed from: q */
    public Runnable f1176q = new C1617c();
    /* access modifiers changed from: private */

    /* renamed from: r */
    public Runnable f1177r = new C1618d();

    /* renamed from: com.navibees.core.blesdk.BeaconService$a */
    class C1615a extends BroadcastReceiver {
        C1615a() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                if (BeaconService.this.f1163d != null) {
                    BeaconService.this.f1163d.onStateChanged(intExtra);
                }
                if (intExtra == 10) {
                    new Handler().postDelayed(BeaconService.this.f1177r, 0);
                }
                if (intExtra == 12) {
                    new Handler().postDelayed(BeaconService.this.f1176q, 0);
                }
            }
        }
    }

    /* renamed from: com.navibees.core.blesdk.BeaconService$b */
    class C1616b implements LeScanCallback {
        C1616b() {
        }

        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            String address = bluetoothDevice.getAddress();
            C1632a aVar = new C1632a(address, i, bArr);
            C1634b bVar = new C1634b(address, bArr);
            if (aVar.mo28981g() != null) {
                if (NaviBeesBeaconManager.SHOW_LOG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Detected Beacon: ");
                    sb.append(aVar.mo28981g());
                    String str = ",";
                    sb.append(str);
                    sb.append(aVar.mo28973c());
                    sb.append(str);
                    sb.append(aVar.mo28975d());
                    Log.d("rfkamd", sb.toString());
                }
                if (BeaconService.this.f1172m && BeaconService.this.f1161b != null) {
                    BeaconService.this.f1161b.mo28953a(bVar, aVar);
                }
                if (i >= -75) {
                    String a = BeaconService.this.m809a(bVar);
                    if (a != null) {
                        bVar.mo28991b(a);
                        if (BeaconService.this.f1171l.containsKey(a)) {
                            MonitoredRegion monitoredRegion = (MonitoredRegion) BeaconService.this.f1171l.get(a);
                            String str2 = "voyager";
                            if (BeaconService.this.f1170k.containsKey(a)) {
                                long j = monitoredRegion.interval * 60000;
                                if (System.currentTimeMillis() - ((Long) BeaconService.this.f1170k.get(a)).longValue() >= j) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("entering region with difference: ");
                                    sb2.append(j / 60000);
                                    Log.d(str2, sb2.toString());
                                    BeaconService.this.f1169j.didEnterRegion(bVar);
                                    BeaconService.this.f1170k.put(a, Long.valueOf(System.currentTimeMillis()));
                                }
                            } else {
                                Log.d(str2, "entering region for first time");
                                BeaconService.this.f1170k.put(a, Long.valueOf(System.currentTimeMillis()));
                                BeaconService.this.f1169j.didEnterRegion(bVar);
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.navibees.core.blesdk.BeaconService$c */
    class C1617c implements Runnable {
        C1617c() {
        }

        public void run() {
            if (BeaconService.this.f1172m || BeaconService.this.f1162c.mo28948c() != null) {
                BeaconService beaconService = BeaconService.this;
                beaconService.f1164e = beaconService.f1162c.mo28948c();
                BeaconService.this.mo28922c();
                new Handler().postDelayed(BeaconService.this.f1177r, BeaconService.this.f1162c.f1199k);
                BeaconService.this.f1166g = true;
                return;
            }
            new Handler().postDelayed(BeaconService.this.f1176q, BeaconService.this.f1162c.f1200l);
        }
    }

    /* renamed from: com.navibees.core.blesdk.BeaconService$d */
    class C1618d implements Runnable {
        C1618d() {
        }

        public void run() {
            if (!BeaconService.this.f1172m) {
                BeaconService.this.mo28923d();
                new Handler().postDelayed(BeaconService.this.f1176q, BeaconService.this.f1162c.f1200l);
                BeaconService.this.f1166g = false;
            }
        }
    }

    /* renamed from: com.navibees.core.blesdk.BeaconService$e */
    public class C1619e extends Binder {
        public C1619e() {
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public BeaconService mo28933a() {
            return BeaconService.this;
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        if (!this.f1166g) {
            mo28918a(this.f1162c.mo28948c());
        }
        return this.f1173n;
    }

    public void onCreate() {
        super.onCreate();
        this.f1169j = (NaviBeesApplication) getApplicationContext();
        this.f1171l = NaviBeesManager.getInstance(this.f1169j).getMetaDataManager().navibeesApp.regions;
        m825h();
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f1174o);
        mo28923d();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    /* renamed from: b */
    private void m814b(C1634b bVar) {
        this.f1168i.add(bVar);
        C1625c cVar = this.f1164e;
        boolean z = true;
        boolean z2 = (cVar == null || cVar.mo28960c() == null) ? false : true;
        if (this.f1162c.mo28934a().size() <= 0) {
            z = false;
        }
        if (!z2 && !z) {
            return;
        }
        if (m812a(this.f1162c.mo28934a(), bVar) && m812a(this.f1168i, bVar) && !m812a(this.f1167h, bVar)) {
            this.f1164e.mo28959b().didEnterRegion(bVar);
        } else if (m812a(this.f1164e.mo28960c(), bVar) && m812a(this.f1168i, bVar) && !m812a(this.f1167h, bVar)) {
            this.f1164e.mo28959b().didEnterRegion(bVar);
        }
    }

    /* renamed from: f */
    private void m821f() {
        C1625c cVar = this.f1164e;
        boolean z = true;
        boolean z2 = (cVar == null || cVar.mo28960c() == null) ? false : true;
        if (this.f1162c.mo28934a().size() <= 0) {
            z = false;
        }
        if (z2 || z) {
            List a = this.f1162c.mo28934a();
            for (C1634b bVar : this.f1168i) {
                if (m812a(a, bVar) && !m812a(this.f1167h, bVar)) {
                    this.f1164e.mo28959b().didEnterRegion(bVar);
                } else if (m812a(this.f1164e.mo28960c(), bVar) && !m812a(this.f1167h, bVar)) {
                    this.f1164e.mo28959b().didEnterRegion(bVar);
                }
            }
        }
    }

    /* renamed from: g */
    private void m823g() {
        if (this.f1164e != null) {
            for (C1634b bVar : this.f1167h) {
                if (!m816c(bVar)) {
                    this.f1164e.mo28959b().didExitRegion(bVar);
                }
            }
            this.f1167h.clear();
            this.f1167h.addAll(this.f1168i);
            this.f1168i.clear();
        }
    }

    /* renamed from: h */
    private void m825h() {
        this.f1162c = C1620a.m840a(getApplication());
        this.f1160a = C1624b.m877a(getApplicationContext());
        mo28918a(this.f1162c.mo28948c());
        if (VERSION.SDK_INT >= 26) {
            m827i();
        } else {
            startForeground(1, new Notification());
        }
        registerReceiver(this.f1174o, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        Log.d("blesdk", "started beacon service");
    }

    @TargetApi(26)
    /* renamed from: i */
    private void m827i() {
        String str = "com.navibeees.core.blesdk";
        NotificationChannel notificationChannel = new NotificationChannel(str, "My Background Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (f1159s || notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
            startForeground(2, new Builder(this, str).setOngoing(true).setSmallIcon(C1266R.C1268drawable.com_navibees_sdk_navigate).setContentTitle("App is running in background").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    public void mo28922c() {
        this.f1160a.mo28956a(this.f1175p);
    }

    /* renamed from: d */
    public void mo28923d() {
        this.f1160a.mo28957b(this.f1175p);
    }

    /* renamed from: e */
    public void mo28924e() {
        this.f1172m = false;
        new Handler().postDelayed(this.f1177r, 0);
    }

    /* renamed from: c */
    private boolean m816c(C1634b bVar) {
        for (C1634b bVar2 : this.f1168i) {
            if (bVar2.mo28996e().toLowerCase().equalsIgnoreCase(bVar.mo28996e()) && bVar2.mo28989b() == bVar.mo28989b() && bVar2.mo28992c() == bVar.mo28992c()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    private boolean m818d(C1634b bVar) {
        for (Entry entry : NaviBeesManager.getInstance(getApplication()).getMetaDataManager().navibeesApp.regions.entrySet()) {
            String str = (String) entry.getKey();
            if (m811a((MonitoredRegion) entry.getValue(), bVar)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo28919a(C1627b bVar) {
        this.f1161b = bVar;
    }

    /* renamed from: a */
    public void mo28917a() {
        this.f1172m = true;
    }

    /* renamed from: a */
    public void mo28918a(C1625c cVar) {
        this.f1164e = cVar;
        new Handler().postDelayed(this.f1176q, this.f1162c.f1200l);
    }

    /* renamed from: a */
    public void mo28920a(C1628c cVar) {
        this.f1163d = cVar;
    }

    /* renamed from: a */
    private boolean m812a(List<C1634b> list, C1634b bVar) {
        for (C1634b e : list) {
            if (e.mo28996e().toLowerCase().equalsIgnoreCase(bVar.mo28996e())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m809a(C1634b bVar) {
        for (Entry entry : NaviBeesManager.getInstance(getApplication()).getMetaDataManager().navibeesApp.regions.entrySet()) {
            String str = (String) entry.getKey();
            if (m811a((MonitoredRegion) entry.getValue(), bVar)) {
                return str;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void mo28921b() {
        this.f1163d = null;
    }

    /* renamed from: a */
    private boolean m811a(MonitoredRegion monitoredRegion, C1634b bVar) {
        return monitoredRegion.UUID.toLowerCase().equalsIgnoreCase(bVar.mo28996e()) && monitoredRegion.major.intValue() == bVar.mo28989b() && monitoredRegion.minor.intValue() == bVar.mo28992c();
    }
}
