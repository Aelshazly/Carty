package com.navibees.core.model.postioning;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.blesdk.C1620a;
import com.navibees.core.blesdk.p022d.C1626a;
import com.navibees.core.blesdk.p022d.C1628c;
import com.navibees.core.blesdk.p022d.C1630e;
import com.navibees.core.blesdk.p022d.C1631f;
import com.navibees.core.blesdk.p023e.C1632a;
import com.navibees.core.blesdk.p023e.C1634b;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.C1666a;
import com.navibees.core.model.metadata.MetaDataManager;
import com.navibees.core.model.metadata.json.BeaconNodeConfiguration;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.UByte;

public final class NaviBeesBeaconManager implements C1626a, C1631f, C1630e, C1628c {
    public static boolean SHOW_LOG = false;

    /* renamed from: i */
    static final long f1349i = 86400000;

    /* renamed from: j */
    private static final char[] f1350j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    private ArrayList<BeaconNodeListener> f1351a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1620a f1352b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public List<C1634b> f1353c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public List<C1634b> f1354d;

    /* renamed from: e */
    private Application f1355e;

    /* renamed from: f */
    private Set<BeaconNodeConfiguration> f1356f = new HashSet();
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f1357g;

    /* renamed from: h */
    private BroadcastReceiver f1358h;

    /* renamed from: com.navibees.core.model.postioning.NaviBeesBeaconManager$a */
    class C1688a extends BroadcastReceiver {
        C1688a() {
        }

        public void onReceive(Context context, Intent intent) {
            String str = NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE;
            if (intent.hasExtra(str)) {
                int intExtra = intent.getIntExtra(str, -1);
                String str2 = "BeaconManager";
                if (intExtra == 1) {
                    NaviBeesBeaconManager.this.f1357g = NaviBeesBeaconManager.this.f1357g + 1;
                    Log.m1171d(str2, "BeaconManager will try to stop scanning if any");
                    if (NaviBeesBeaconManager.this.f1352b.mo28943b().size() != 0) {
                        for (C1634b d : NaviBeesBeaconManager.this.f1353c) {
                            try {
                                NaviBeesBeaconManager.this.f1352b.mo28951d(d);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (NaviBeesBeaconManager.this.f1352b.mo28934a().size() != 0) {
                        for (C1634b c : NaviBeesBeaconManager.this.f1354d) {
                            try {
                                NaviBeesBeaconManager.this.f1352b.mo28950c(c);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                } else if (intExtra == 2 && NaviBeesBeaconManager.this.f1357g > 0 && NaviBeesBeaconManager.m1000b(NaviBeesBeaconManager.this) <= 0) {
                    Log.m1171d(str2, "BeaconManager will start scanning for sure");
                    if (NaviBeesBeaconManager.this.f1352b.mo28943b().size() == 0) {
                        for (C1634b b : NaviBeesBeaconManager.this.f1353c) {
                            try {
                                NaviBeesBeaconManager.this.f1352b.mo28945b(b);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (NaviBeesBeaconManager.this.f1352b.mo28934a().size() == 0) {
                        for (C1634b a : NaviBeesBeaconManager.this.f1354d) {
                            try {
                                NaviBeesBeaconManager.this.f1352b.mo28941a(a);
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public NaviBeesBeaconManager(Application application) {
        C1634b bVar;
        int i = 0;
        this.f1357g = 0;
        this.f1358h = new C1688a();
        this.f1355e = application;
        this.f1351a = new ArrayList<>();
        this.f1352b = C1620a.m840a(this.f1355e);
        this.f1352b.mo28938a((C1628c) this);
        this.f1353c = new ArrayList();
        String[] strArr = NaviBeesManager.getInstance(application).getMetaDataManager().getConfiguration().beaconsUUIDList;
        int i2 = 0;
        while (strArr != null && i2 < strArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("NaviBees#");
            sb.append(i2);
            this.f1353c.add(new C1634b(sb.toString(), strArr[i2]));
            i2++;
        }
        try {
            NaviBeesManager.getInstance(application).getLicenseManager().mo29048a(NaviBeesFeature.Location_Based_Notifications);
            HashMap<String, MonitoredRegion> hashMap = NaviBeesManager.getInstance(application).getMetaDataManager().navibeesApp.regions;
            this.f1354d = new ArrayList();
            for (MonitoredRegion monitoredRegion : hashMap.values()) {
                if (monitoredRegion.major != null && monitoredRegion.major.intValue() != -1 && monitoredRegion.minor != null && monitoredRegion.minor.intValue() != -1) {
                    bVar = new C1634b(monitoredRegion.identifier, monitoredRegion.UUID, monitoredRegion.major.intValue(), monitoredRegion.minor.intValue());
                } else if (monitoredRegion.major == null || monitoredRegion.major.intValue() == -1) {
                    bVar = new C1634b(monitoredRegion.identifier, monitoredRegion.UUID);
                } else {
                    bVar = new C1634b(monitoredRegion.identifier, monitoredRegion.UUID, monitoredRegion.major.intValue());
                }
                this.f1354d.add(bVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        List<C1634b> list = this.f1354d;
        if (list != null) {
            arrayList.addAll(list);
        }
        while (strArr != null && i < strArr.length) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("navibees-background-analytics-region#");
            sb2.append(i);
            arrayList.add(new C1634b(sb2.toString(), strArr[i]));
            i++;
        }
        this.f1352b.mo28942a((List<C1634b>) arrayList);
        Editor edit = PreferenceManager.getDefaultSharedPreferences(application.getApplicationContext()).edit();
        edit.putString(NaviBeesConstants.BACKGROUND_MONITORED_REGIONS_KEY, new Gson().toJson((Object) arrayList));
        edit.apply();
    }

    /* renamed from: b */
    static /* synthetic */ int m1000b(NaviBeesBeaconManager naviBeesBeaconManager) {
        int i = naviBeesBeaconManager.f1357g - 1;
        naviBeesBeaconManager.f1357g = i;
        return i;
    }

    public void addBeaconNodeListener(BeaconNodeListener beaconNodeListener) {
        try {
            NaviBeesManager.getInstance(this.f1355e).getLicenseManager().mo29048a(NaviBeesFeature.Positioning);
            this.f1351a.add(beaconNodeListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bindBeaconManager() {
        ((NaviBeesApplication) this.f1355e).disableRegionBootstrap(this.f1354d);
        if (!NaviBeesUtils.isMarshmallowOrAbove() || NaviBeesUtils.isLocationServicesGranted(this.f1355e.getApplicationContext())) {
            try {
                if (!this.f1352b.mo28947b((C1626a) this)) {
                    this.f1352b.mo28937a((C1626a) this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getApplicationContext().bindService(intent, serviceConnection, i);
    }

    public void didEnterRegion(C1634b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("Did Enter Region: ");
        sb.append(bVar.mo28996e());
        Log.m1171d("blesdk", sb.toString());
        Intent intent = new Intent(NaviBeesConstants.MONITORED_REGION_INTERNAL_ACTION);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY, bVar.mo28994d());
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_ENTER_VALUE);
        getApplicationContext().sendBroadcast(intent);
    }

    public void didExitRegion(C1634b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("Did Exit Region: ");
        sb.append(bVar.mo28996e());
        Log.m1171d("blesdk", sb.toString());
        Intent intent = new Intent(NaviBeesConstants.MONITORED_REGION_INTERNAL_ACTION);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY, bVar.mo28994d());
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_EXIT_VALUE);
        getApplicationContext().sendBroadcast(intent);
    }

    public void didRangeBeaconInRegion(C1632a aVar, C1634b bVar) {
    }

    public void didRangeBeaconsInRegion(List<C1632a> list, C1634b bVar) {
        ArrayList arrayList = new ArrayList();
        for (C1632a aVar : list) {
            if (aVar.mo28978e() != 0) {
                BeaconNode a = m997a(aVar);
                if (a.location != null) {
                    arrayList.add(a);
                }
            }
        }
        Iterator it = this.f1351a.iterator();
        while (it.hasNext()) {
            try {
                ((BeaconNodeListener) it.next()).beaconNodeCallback(arrayList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enableBackgroundMonitoring() {
        C1620a aVar = this.f1352b;
        if (aVar != null) {
            try {
                if (aVar.mo28947b((C1626a) this)) {
                    this.f1352b.mo28949c((C1626a) this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!NaviBeesUtils.isMarshmallowOrAbove() || NaviBeesUtils.isLocationServicesGranted(this.f1355e.getApplicationContext())) {
            ((NaviBeesApplication) this.f1355e).enableRegionBootstrap(this.f1354d);
        }
    }

    public Context getApplicationContext() {
        return this.f1355e.getApplicationContext();
    }

    public void onBeaconServiceConnect() {
        this.f1352b.mo28940a((C1631f) this);
        try {
            for (C1634b b : this.f1353c) {
                this.f1352b.mo28945b(b);
            }
            NaviBeesManager.getInstance(this.f1355e).getLicenseManager().mo29048a(NaviBeesFeature.Location_Based_Notifications);
            this.f1352b.mo28939a((C1630e) this);
            if (this.f1354d != null) {
                for (C1634b a : this.f1354d) {
                    this.f1352b.mo28941a(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBeaconServiceError(Throwable th) {
        th.printStackTrace();
    }

    public void onStateChanged(int i) {
        if (i == C1620a.f1186D) {
            NaviBeesManager.getInstance(this.f1355e).getPositionManager().stateTurnedOff();
        }
        if (i == C1620a.f1188F) {
            NaviBeesManager.getInstance(this.f1355e).getPositionManager().stateTurnedOn();
        }
    }

    public void registerReceivers() {
        LocalBroadcastManager.getInstance(this.f1355e.getApplicationContext()).registerReceiver(this.f1358h, new IntentFilter(NaviBeesConstants.NETWORK_ACTIVITY_ACTION));
    }

    public void removeBeaconNodeListener(BeaconNodeListener beaconNodeListener) {
        this.f1351a.remove(beaconNodeListener);
    }

    public void unbindService() {
        C1620a aVar = this.f1352b;
        if (aVar != null) {
            try {
                if (aVar.mo28947b((C1626a) this)) {
                    this.f1352b.mo28949c((C1626a) this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterReceivers() {
        LocalBroadcastManager.getInstance(this.f1355e.getApplicationContext()).unregisterReceiver(this.f1358h);
    }

    /* renamed from: a */
    private BeaconNode m997a(C1632a aVar) {
        BeaconNode beaconNode = new BeaconNode(aVar.mo28973c(), aVar.mo28975d());
        List<C1666a> list = beaconNode.states;
        C1666a aVar2 = new C1666a(aVar.mo28978e(), 0.0d, m994a(aVar.mo28978e(), aVar.mo28980f()));
        list.add(aVar2);
        MetaDataManager metaDataManager = NaviBeesManager.getInstance(this.f1355e).getMetaDataManager();
        if (metaDataManager == null) {
            return beaconNode;
        }
        BeaconNodeConfiguration lookupTagLocation = metaDataManager.lookupTagLocation(beaconNode.major, beaconNode.minor, aVar.mo28981g().toUpperCase());
        if (lookupTagLocation != null) {
            beaconNode.location = lookupTagLocation.location;
            beaconNode.buildingId = lookupTagLocation.buildingId;
            if (aVar.mo28967a() != 0) {
                beaconNode.batteryStatus = aVar.mo28967a();
            } else {
                beaconNode.batteryStatus = -1;
            }
            lookupTagLocation.batteryStatus = beaconNode.batteryStatus;
            if (System.currentTimeMillis() - lookupTagLocation.lastTimeBatteryReported > f1349i) {
                lookupTagLocation.lastTimeBatteryReported = System.currentTimeMillis();
                this.f1356f.add(lookupTagLocation);
            }
        } else {
            beaconNode.location = null;
        }
        return beaconNode;
    }

    public void unbindService(ServiceConnection serviceConnection) {
        getApplicationContext().unbindService(serviceConnection);
    }

    /* renamed from: a */
    private int m995a(byte b) {
        return (int) (((((((double) Integer.parseInt(m998a(new byte[]{b}), 16)) * 3.6d) / 255.0d) - 1.8d) / 1.4000000000000001d) * 100.0d);
    }

    /* renamed from: a */
    private double m994a(int i, int i2) {
        return Math.pow(10.0d, (((double) i2) - ((double) i)) / 20.0d);
    }

    /* renamed from: a */
    private static String m998a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & UByte.MAX_VALUE;
            int i2 = i * 2;
            char[] cArr2 = f1350j;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    /* renamed from: a */
    private void m999a() {
        BeaconNodeConfiguration beaconNodeConfiguration = new BeaconNodeConfiguration(10, 47);
        beaconNodeConfiguration.batteryStatus = 100;
        beaconNodeConfiguration.lastTimeBatteryReported = System.currentTimeMillis();
        beaconNodeConfiguration.f1328id = "5qnVLxOTwL";
        this.f1356f.add(beaconNodeConfiguration);
    }
}
