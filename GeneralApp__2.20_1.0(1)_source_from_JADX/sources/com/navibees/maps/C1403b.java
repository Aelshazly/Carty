package com.navibees.maps;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationConfidence;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.metadata.json.VisioFloor;
import com.navibees.core.model.postioning.NBLocationListener;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.model.postioning.compass.BearingProvider;
import com.navibees.core.model.postioning.compass.BearingProvider.ChangeEventListener;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.p018a.C1343a;
import com.navibees.visioglobe.C1481d;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1515e;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.p020g.C1519h;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgICamera;
import com.visioglobe.libVisioMove.VgIViewPoint;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgSRSConstRefPtr;
import java.util.List;

/* renamed from: com.navibees.maps.b */
/* compiled from: NBMyLocationProvider */
public class C1403b implements C1519h, NBLocationListener, ChangeEventListener {

    /* renamed from: a */
    protected int f439a;

    /* renamed from: b */
    protected VgMySurfaceView f440b;

    /* renamed from: c */
    protected Activity f441c;

    /* renamed from: d */
    protected C1515e f442d;

    /* renamed from: e */
    protected int f443e;

    /* renamed from: f */
    private boolean f444f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public boolean f445g = false;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f446h = true;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f447i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public boolean f448j;

    /* renamed from: k */
    boolean f449k = true;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public boolean f450l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public LocalBroadcastManager f451m;

    /* renamed from: n */
    IndoorLocation f452n;

    /* renamed from: o */
    private BearingProvider f453o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public double f454p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public long f455q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public boolean f456r = true;

    /* renamed from: s */
    private boolean f457s = false;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public MapHoverInterface f458t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public C1343a f459u;

    /* renamed from: v */
    private BroadcastReceiver f460v = new C1414f();

    /* renamed from: com.navibees.maps.b$a */
    /* compiled from: NBMyLocationProvider */
    class C1404a implements Runnable {
        C1404a() {
        }

        public void run() {
            C1481d.m455a().mo28400b(C1403b.this.mo28336b());
        }
    }

    /* renamed from: com.navibees.maps.b$b */
    /* compiled from: NBMyLocationProvider */
    class C1405b implements Runnable {
        C1405b() {
        }

        public void run() {
            PositionManager positionManager = NaviBeesManager.getInstance(C1403b.this.f441c.getApplication()).getPositionManager();
            if ((positionManager != null) && (!C1403b.this.f445g)) {
                positionManager.connectOutdoorPositionManager(true, C1403b.this.f441c);
                positionManager.addLocationListener(C1403b.this);
            }
            if (C1403b.this.f445g) {
                C1403b.this.f445g = false;
            }
            C1403b.this.mo28341g();
        }
    }

    /* renamed from: com.navibees.maps.b$c */
    /* compiled from: NBMyLocationProvider */
    class C1406c implements Runnable {
        C1406c() {
        }

        public void run() {
            C1481d.m455a().mo28377a(C1403b.this.mo28336b());
        }
    }

    /* renamed from: com.navibees.maps.b$d */
    /* compiled from: NBMyLocationProvider */
    class C1407d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ boolean f464a;

        /* renamed from: b */
        final /* synthetic */ IndoorLocation f465b;

        /* renamed from: c */
        final /* synthetic */ boolean f466c;

        /* renamed from: d */
        final /* synthetic */ double f467d;

        /* renamed from: com.navibees.maps.b$d$a */
        /* compiled from: NBMyLocationProvider */
        class C1408a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ String f469a;

            C1408a(String str) {
                this.f469a = str;
            }

            public void run() {
                C1481d.m455a().mo28380a(C1403b.this.mo28336b(), this.f469a);
            }
        }

        /* renamed from: com.navibees.maps.b$d$b */
        /* compiled from: NBMyLocationProvider */
        class C1409b implements Runnable {

            /* renamed from: a */
            final /* synthetic */ VgPosition f471a;

            C1409b(VgPosition vgPosition) {
                this.f471a = vgPosition;
            }

            public void run() {
                C1481d.m455a().mo28379a(C1403b.this.mo28336b(), this.f471a);
            }
        }

        /* renamed from: com.navibees.maps.b$d$c */
        /* compiled from: NBMyLocationProvider */
        class C1410c implements Runnable {
            C1410c() {
            }

            public void run() {
                C1481d.m455a().mo28378a(C1403b.this.mo28336b(), (double) C1403b.this.f439a);
            }
        }

        C1407d(boolean z, IndoorLocation indoorLocation, boolean z2, double d) {
            this.f464a = z;
            this.f465b = indoorLocation;
            this.f466c = z2;
            this.f467d = d;
        }

        public void run() {
            VgPosition vgPosition;
            double d;
            C1403b.this.f456r = this.f464a;
            if (this.f465b != null && (C1403b.this.f452n == null || this.f466c)) {
                boolean a = C1403b.this.m200a(this.f465b);
                C1403b.this.f455q = System.currentTimeMillis();
                C1403b bVar = C1403b.this;
                bVar.f443e = this.f465b.floor;
                String str = ((VisioFloor) ((Building) NaviBeesManager.getInstance(bVar.f441c.getApplication()).getMetaDataManager().getBuildings().get(this.f465b.buildingId)).floors.get(C1403b.this.f443e)).vgfloorId;
                new Handler(Looper.getMainLooper()).post(new C1408a(str));
                VgICamera editCamera = C1403b.this.f440b.getApplication().editEngine().editCamera();
                VgPosition position = editCamera.getPosition();
                double mZOrAltitude = position.getMZOrAltitude() / Math.abs(Math.sin((editCamera.getPitch() * 3.141592653589793d) / 180.0d));
                if (this.f464a) {
                    IndoorLocation convertUTMToLatLong = NaviBeesUtils.convertUTMToLatLong(this.f465b);
                    VgPosition vgPosition2 = new VgPosition(convertUTMToLatLong.f1332x, convertUTMToLatLong.f1333y, mZOrAltitude);
                    vgPosition = vgPosition2;
                } else {
                    IndoorLocation indoorLocation = this.f465b;
                    vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, mZOrAltitude);
                }
                new Handler(Looper.getMainLooper()).post(new C1409b(vgPosition));
                if ((C1403b.this.f447i && !C1403b.this.f448j) || (a && this.f464a)) {
                    double d2 = this.f467d;
                    C1403b bVar2 = C1403b.this;
                    if (bVar2.f452n == null) {
                        NaviBeesManager instance = NaviBeesManager.getInstance(bVar2.f441c.getApplication());
                        if (instance != null) {
                            Venue currentVenue = instance.getMetaDataManager().getCurrentVenue();
                            if (currentVenue != null) {
                                d2 = (double) currentVenue.defaultZoom;
                            }
                        }
                    }
                    if (d2 == -1.0d) {
                        d = position.getMZOrAltitude();
                    } else {
                        d = d2;
                    }
                    VgPosition vgPosition3 = new VgPosition(vgPosition.getMXOrLongitude(), vgPosition.getMYOrLatitude(), d);
                    VgIViewPoint vgIViewPoint = new VgIViewPoint();
                    vgIViewPoint.setMPosition(vgPosition3);
                    double heading = editCamera.getHeading();
                    if (C1403b.this.f454p != -1.0d) {
                        heading = C1403b.this.f454p;
                    }
                    vgIViewPoint.setMHeading(heading);
                    vgIViewPoint.setMPitch(-75.0d);
                    Intent intent = new Intent("exploreRequest");
                    intent.putExtra("view", C1516a.eVgViewModeFloor);
                    intent.putExtra("focusedFloor", str);
                    intent.putExtra("focusedLongitude", new Float(vgIViewPoint.getMPosition().getMXOrLongitude()));
                    intent.putExtra("focusedLatitude", new Float(vgIViewPoint.getMPosition().getMYOrLatitude()));
                    intent.putExtra("focusedAltitude", new Float(vgIViewPoint.getMPosition().getMZOrAltitude()));
                    intent.putExtra("focusedHeading", new Float(vgIViewPoint.getMHeading()));
                    intent.putExtra("focusedPitch", new Float(vgIViewPoint.getMPitch()));
                    C1403b.this.f451m.sendBroadcast(intent);
                }
                C1403b.this.f452n = this.f465b;
            }
            IndoorLocation indoorLocation2 = this.f465b;
            if (indoorLocation2 == null) {
                C1403b.this.f439a = 20;
            } else {
                IndoorLocationConfidence indoorLocationConfidence = indoorLocation2.confidence;
                if (indoorLocationConfidence == IndoorLocationConfidence.High) {
                    C1403b.this.f439a = 1;
                } else if (indoorLocationConfidence == IndoorLocationConfidence.Average) {
                    C1403b.this.f439a = 10;
                } else if (indoorLocationConfidence == IndoorLocationConfidence.Low) {
                    C1403b.this.f439a = 20;
                }
            }
            new Handler(Looper.getMainLooper()).post(new C1410c());
        }
    }

    /* renamed from: com.navibees.maps.b$e */
    /* compiled from: NBMyLocationProvider */
    class C1411e implements Runnable {

        /* renamed from: a */
        final /* synthetic */ boolean f474a;

        /* renamed from: b */
        final /* synthetic */ IndoorLocation f475b;

        /* renamed from: c */
        final /* synthetic */ int f476c;

        /* renamed from: com.navibees.maps.b$e$a */
        /* compiled from: NBMyLocationProvider */
        class C1412a implements Runnable {
            C1412a() {
            }

            public void run() {
                C1403b.this.mo28334a();
                C1481d.m455a().mo28377a(C1403b.this.mo28336b());
                C1403b.this.f452n = null;
            }
        }

        /* renamed from: com.navibees.maps.b$e$b */
        /* compiled from: NBMyLocationProvider */
        class C1413b implements Runnable {
            C1413b() {
            }

            public void run() {
                if (!C1403b.this.f446h) {
                    C1403b.this.f446h = true;
                    C1403b.this.f458t.onEnterOrExitCoverageArea(false);
                }
                if (C1403b.this.f459u != null && !C1403b.this.f445g) {
                    System.out.println("NBMyLocationProvider: user out of venue2");
                    C1403b.this.f459u.mo28171b();
                    C1403b.this.f445g = true;
                    C1403b.this.mo28334a();
                    C1481d.m455a().mo28377a(C1403b.this.mo28336b());
                }
            }
        }

        C1411e(boolean z, IndoorLocation indoorLocation, int i) {
            this.f474a = z;
            this.f475b = indoorLocation;
            this.f476c = i;
        }

        public void run() {
            if (this.f474a) {
                new Handler(Looper.getMainLooper()).post(new C1412a());
                return;
            }
            if (this.f475b == null && this.f476c == 0) {
                new Handler(Looper.getMainLooper()).post(new C1413b());
            }
        }
    }

    /* renamed from: com.navibees.maps.b$f */
    /* compiled from: NBMyLocationProvider */
    class C1414f extends BroadcastReceiver {
        C1414f() {
        }

        public void onReceive(Context context, Intent intent) {
            String string = intent.getExtras().getString("com.navibees.map.gesture.action");
            if (string != null) {
                try {
                    if (string.equals("com.navibees.map.gesture.pause_automode")) {
                        if (C1403b.this.f447i) {
                            C1403b.this.f448j = true;
                        }
                        if (C1403b.this.f449k) {
                            C1403b.this.f450l = true;
                        }
                    } else if (string.equals("com.navibees.map.gesture.resume_automode")) {
                        C1403b.this.f448j = false;
                        C1403b.this.f450l = false;
                    } else {
                        if (string.equals("com.navibees.map.gesture.try_start_automode")) {
                            if (C1403b.this.f448j) {
                                C1403b.this.f447i = true;
                                C1403b.this.f458t.locationTrackingChanged(true);
                            }
                            if (C1403b.this.f450l) {
                                C1403b.this.f449k = true;
                                C1403b.this.f458t.compassTrackingChanged(true);
                            }
                        } else if (string.equals("com.navibees.map.gesture.stop_automode")) {
                            C1403b.this.f447i = false;
                            C1403b.this.f449k = false;
                            try {
                                C1403b.this.f458t.trackingLocationAndCompassChanged(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public C1403b(Activity activity, VgMySurfaceView vgMySurfaceView, C1515e eVar, MapHoverInterface mapHoverInterface) {
        this.f441c = activity;
        this.f440b = vgMySurfaceView;
        this.f442d = eVar;
        this.f443e = -1;
        this.f444f = false;
        this.f447i = false;
        this.f448j = true;
        this.f450l = true;
        this.f458t = mapHoverInterface;
        this.f451m = LocalBroadcastManager.getInstance(activity);
        this.f453o = new BearingProvider(activity);
        this.f453o.setChangeEventListener(this);
        this.f454p = -1.0d;
        this.f455q = System.currentTimeMillis();
    }

    /* renamed from: j */
    private void m218j() {
        PositionManager positionManager = NaviBeesManager.getInstance(this.f441c.getApplication()).getPositionManager();
        if (positionManager != null) {
            positionManager.registerSensor();
        }
        this.f453o.start();
    }

    /* renamed from: k */
    private void m219k() {
        PositionManager positionManager = NaviBeesManager.getInstance(this.f441c.getApplication()).getPositionManager();
        if (positionManager != null) {
            positionManager.startOutdoorLocationUpdates(this.f441c);
        }
    }

    /* renamed from: l */
    private void m220l() {
        PositionManager positionManager = NaviBeesManager.getInstance(this.f441c.getApplication()).getPositionManager();
        if (positionManager != null) {
            positionManager.stopOutdoorLocationUpdates();
        }
    }

    /* renamed from: m */
    private void m221m() {
        this.f451m.unregisterReceiver(this.f460v);
    }

    /* renamed from: n */
    private void m222n() {
        PositionManager positionManager = NaviBeesManager.getInstance(this.f441c.getApplication()).getPositionManager();
        if (positionManager != null) {
            positionManager.unregisterSensor();
        }
        this.f453o.stop();
    }

    /* renamed from: b */
    public String mo28336b() {
        return "navibeesLocationProvider";
    }

    public boolean isBackground() {
        return false;
    }

    public void locationCallback(IndoorLocation indoorLocation, IndoorLocation indoorLocation2, int i, boolean z, boolean z2) {
        if (i > 0) {
            if (this.f445g) {
                this.f445g = false;
                System.out.println("NBMyLocationProvider: user in venue");
                this.f459u.mo28170a();
            }
            if (this.f446h) {
                this.f446h = false;
                this.f458t.onEnterOrExitCoverageArea(true);
            }
            if (!NaviBeesConstants.CAMERA_MOVING) {
                m198a(indoorLocation2, z, z2, -1.0d);
            }
        } else if (z && !PositionManager.useOutdoor) {
            m199a(indoorLocation2, true, z2, -1.0d, 0);
        } else if (z || PositionManager.useOutdoor) {
            if (indoorLocation2 != null && !z && z2 && !NaviBeesConstants.CAMERA_MOVING) {
                m198a(indoorLocation2, false, true, -1.0d);
            }
        } else {
            System.out.println("NBMyLocationProvider: user out of venue1");
            m199a(indoorLocation2, false, false, -1.0d, 0);
        }
    }

    public void onBearingChanged(double d) {
        if (this.f449k && !this.f448j) {
            this.f454p = d;
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f455q > 1500 && this.f442d.mo28405c() == C1516a.eVgViewModeFloor) {
                this.f455q = currentTimeMillis;
                String charSequence = this.f442d.mo28363a().toString();
                VgIApplication application = this.f440b.getApplication();
                VgIViewPoint vgIViewPoint = new VgIViewPoint(application.editEngine().editCamera().getViewpoint());
                application.editEngine().getPositionToolbox().convert(vgIViewPoint.getMPosition(), VgSRSConstRefPtr.getNull());
                Intent intent = new Intent("exploreRequest");
                intent.putExtra("view", C1516a.eVgViewModeFloor);
                intent.putExtra("focusedFloor", charSequence);
                intent.putExtra("focusedLongitude", (float) vgIViewPoint.getMPosition().getMXOrLongitude());
                intent.putExtra("focusedLatitude", (float) vgIViewPoint.getMPosition().getMYOrLatitude());
                intent.putExtra("focusedAltitude", (float) vgIViewPoint.getMPosition().getMZOrAltitude());
                intent.putExtra("focusedHeading", (float) d);
                intent.putExtra("focusedPitch", -75.0f);
                this.f451m.sendBroadcast(intent);
            }
        }
    }

    /* renamed from: i */
    private void m217i() {
        this.f451m.registerReceiver(this.f460v, new IntentFilter("com.navibees.map.gesture"));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public void mo28341g() {
        if (!this.f457s) {
            this.f457s = true;
            m217i();
            m218j();
            m219k();
        }
    }

    /* renamed from: h */
    public void mo28342h() {
        this.f444f = false;
        this.f440b = null;
        this.f441c = null;
        this.f442d = null;
        this.f451m = null;
    }

    /* renamed from: c */
    public boolean mo28337c() {
        return this.f444f;
    }

    /* renamed from: d */
    public void mo28338d() {
        if (!this.f445g) {
            this.f447i = true;
            this.f448j = false;
            this.f450l = false;
            this.f449k = true;
            if (!this.f444f) {
                this.f444f = true;
                new Handler(Looper.getMainLooper()).post(new C1404a());
                this.f441c.runOnUiThread(new C1405b());
            }
            if (this.f452n != null) {
                int i = 20;
                Venue currentVenue = NaviBeesManager.getInstance(this.f441c.getApplication()).getMetaDataManager().getCurrentVenue();
                if (currentVenue != null) {
                    i = currentVenue.defaultZoom;
                }
                m198a(this.f452n, this.f456r, true, (double) i);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public IndoorLocation mo28339e() {
        if (this.f456r) {
            return NaviBeesUtils.convertUTMToLatLong(this.f452n);
        }
        IndoorLocation indoorLocation = this.f452n;
        IndoorLocation indoorLocation2 = new IndoorLocation(indoorLocation.f1332x, indoorLocation.f1333y, indoorLocation.floor);
        return indoorLocation2;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public void mo28340f() {
        if (this.f457s) {
            this.f457s = false;
            m221m();
            m222n();
            m220l();
        }
    }

    /* renamed from: a */
    public void mo28334a() {
        this.f447i = false;
        this.f448j = true;
        this.f450l = true;
        this.f449k = false;
        if (this.f444f) {
            this.f444f = false;
            new Handler(Looper.getMainLooper()).post(new C1406c());
            mo28340f();
            PositionManager positionManager = NaviBeesManager.getInstance(this.f441c.getApplication()).getPositionManager();
            if ((true ^ this.f445g) && (positionManager != null)) {
                positionManager.connectOutdoorPositionManager(false, this.f441c);
                positionManager.removeLocationListener(this);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m200a(IndoorLocation indoorLocation) {
        if (this.f452n == null) {
            return true;
        }
        String charSequence = this.f442d.mo28363a().toString();
        C1516a c = this.f442d.mo28405c();
        Building building = (Building) NaviBeesManager.getInstance(this.f441c.getApplication()).getMetaDataManager().getBuildings().get(indoorLocation.buildingId);
        if (building == null) {
            return false;
        }
        int i = indoorLocation.floor;
        int i2 = this.f452n.floor;
        List<Floor> list = building.floors;
        if (i2 > -1 && i2 < list.size() && i > -1 && i < list.size()) {
            String str = ((VisioFloor) list.get(i2)).vgfloorId;
            String str2 = ((VisioFloor) list.get(i)).vgfloorId;
            if (c != C1516a.eVgViewModeFloor || !str.equals(charSequence) || str2.equals(charSequence)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m198a(IndoorLocation indoorLocation, boolean z, boolean z2, double d) {
        if (!mo28337c()) {
            mo28338d();
        }
        if (!this.f457s) {
            mo28341g();
        }
        VgMySurfaceView vgMySurfaceView = this.f440b;
        if (vgMySurfaceView != null) {
            C1407d dVar = new C1407d(z, indoorLocation, z2, d);
            vgMySurfaceView.queueEvent(dVar);
        }
    }

    /* renamed from: a */
    public void mo28335a(C1343a aVar) {
        this.f459u = aVar;
    }

    /* renamed from: a */
    private void m199a(IndoorLocation indoorLocation, boolean z, boolean z2, double d, int i) {
        VgMySurfaceView vgMySurfaceView = this.f440b;
        if (vgMySurfaceView != null) {
            vgMySurfaceView.queueEvent(new C1411e(z, indoorLocation, i));
        }
    }
}
