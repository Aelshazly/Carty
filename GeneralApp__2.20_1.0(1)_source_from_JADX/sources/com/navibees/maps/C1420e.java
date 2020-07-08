package com.navibees.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.collection.SimpleArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import com.navibees.C1164R;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.MapHoverActivity;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.metadata.json.VisioFloor;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.visioglobe.C1481d;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.VgMySurfaceView.C1471a;
import com.navibees.visioglobe.p019f.C1496c;
import com.navibees.visioglobe.p020g.C1511a;
import com.navibees.visioglobe.p020g.C1513c;
import com.navibees.visioglobe.p020g.C1514d;
import com.navibees.visioglobe.p020g.C1515e;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.p020g.C1517f;
import com.navibees.visioglobe.p020g.C1518g;
import com.navibees.visioglobe.p020g.C1520i;
import com.navibees.visioglobe.p020g.C1521j;
import com.navibees.visioglobe.p020g.C1522k;
import com.navibees.visioglobe.p020g.C1527m;
import com.navibees.visioglobe.p020g.C1528n;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIDatabase;
import com.visioglobe.libVisioMove.VgIEngine;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIModule;
import com.visioglobe.libVisioMove.VgIModuleManager;
import com.visioglobe.libVisioMove.VgINavigationCallback;
import com.visioglobe.libVisioMove.VgINavigationCallbackRefPtr;
import com.visioglobe.libVisioMove.VgINavigationConstRefPtr;
import com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr;
import com.visioglobe.libVisioMove.VgINavigationListener;
import com.visioglobe.libVisioMove.VgINavigationListenerRefPtr;
import com.visioglobe.libVisioMove.VgINavigationModule;
import com.visioglobe.libVisioMove.VgINavigationRefPtr;
import com.visioglobe.libVisioMove.VgINavigationRequestParameters;
import com.visioglobe.libVisioMove.VgIRouteCallback;
import com.visioglobe.libVisioMove.VgIRouteCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIRouteConstRefPtr;
import com.visioglobe.libVisioMove.VgIRouteRefPtr;
import com.visioglobe.libVisioMove.VgIRoutingModule;
import com.visioglobe.libVisioMove.VgModalityParameterType;
import com.visioglobe.libVisioMove.VgNavigationModalityParametersMap;
import com.visioglobe.libVisioMove.VgNavigationRequestStatus;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgRouteRequestStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: com.navibees.maps.e */
/* compiled from: VgMyBasicApplicationController */
public class C1420e implements C1517f, C1515e, C1527m, C1522k, C1513c, C1514d, C1511a, C1518g, C1471a {

    /* renamed from: P */
    public static VgPosition f494P;

    /* renamed from: A */
    protected LinkedList<C1517f> f495A = new LinkedList<>();

    /* renamed from: B */
    protected LinkedList<C1513c> f496B = new LinkedList<>();

    /* renamed from: C */
    protected LinkedList<C1521j> f497C = new LinkedList<>();

    /* renamed from: D */
    protected C1482e f498D;

    /* renamed from: E */
    protected C1496c f499E;

    /* renamed from: F */
    protected final LocalBroadcastManager f500F;

    /* renamed from: G */
    String f501G;

    /* renamed from: H */
    VgPosition f502H;

    /* renamed from: I */
    VgPosition f503I;

    /* renamed from: J */
    public VgPosition f504J;

    /* renamed from: K */
    boolean f505K;
    /* access modifiers changed from: private */

    /* renamed from: L */
    public MapHoverInterface f506L;
    /* access modifiers changed from: private */

    /* renamed from: M */
    public boolean f507M;
    /* access modifiers changed from: private */

    /* renamed from: N */
    public boolean f508N;
    /* access modifiers changed from: private */

    /* renamed from: O */
    public boolean f509O;

    /* renamed from: a */
    private String f510a;

    /* renamed from: b */
    private String f511b;

    /* renamed from: c */
    private long f512c = -1;

    /* renamed from: d */
    protected Activity f513d;

    /* renamed from: e */
    protected VgMySurfaceView f514e;

    /* renamed from: f */
    public VgIRouteCallbackRefPtr f515f;

    /* renamed from: g */
    public VgINavigationCallbackRefPtr f516g;

    /* renamed from: h */
    public VgINavigationListenerRefPtr f517h;

    /* renamed from: i */
    protected VgIApplication f518i;

    /* renamed from: j */
    protected VgIEngine f519j;

    /* renamed from: k */
    protected VgIDatabase f520k;

    /* renamed from: l */
    protected VgIMapModule f521l;

    /* renamed from: m */
    protected VgIRoutingModule f522m;

    /* renamed from: n */
    protected VgINavigationModule f523n;

    /* renamed from: o */
    protected VgINavigationRefPtr f524o;

    /* renamed from: p */
    protected VgIRouteRefPtr f525p;

    /* renamed from: q */
    VgRouteRequestStatus f526q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public String f527r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public LinkedList<String> f528s;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public VgIRouteRefPtr f529t = null;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public String f530u;

    /* renamed from: v */
    protected C1515e f531v;

    /* renamed from: w */
    protected C1527m f532w;

    /* renamed from: x */
    protected LinkedList<C1528n> f533x = new LinkedList<>();

    /* renamed from: y */
    protected C1511a f534y;

    /* renamed from: z */
    protected C1520i f535z;

    /* renamed from: com.navibees.maps.e$a */
    /* compiled from: VgMyBasicApplicationController */
    class C1421a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ VgPosition f536a;

        C1421a(VgPosition vgPosition) {
            this.f536a = vgPosition;
        }

        public void run() {
            C1420e.this.mo28383a(0, this.f536a);
        }
    }

    /* renamed from: com.navibees.maps.e$b */
    /* compiled from: VgMyBasicApplicationController */
    class C1422b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f538a;

        /* renamed from: com.navibees.maps.e$b$a */
        /* compiled from: VgMyBasicApplicationController */
        class C1423a implements OnClickListener {
            C1423a(C1422b bVar) {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        C1422b(String str) {
            this.f538a = str;
        }

        public void run() {
            Builder builder = new Builder(C1420e.this.f513d);
            builder.setMessage(this.f538a);
            builder.setNeutralButton(HTTP.CONN_CLOSE, new C1423a(this));
            builder.show();
        }
    }

    /* renamed from: com.navibees.maps.e$c */
    /* compiled from: VgMyBasicApplicationController */
    class C1424c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ VgPosition f540a;

        C1424c(VgPosition vgPosition) {
            this.f540a = vgPosition;
        }

        public void run() {
            if (!C1420e.this.f501G.contentEquals("")) {
                C1420e eVar = C1420e.this;
                double c = eVar.mo28404c(eVar.f501G);
                VgPosition vgPosition = new VgPosition(this.f540a);
                vgPosition.setMZOrAltitude(c);
                C1420e.this.f502H = new VgPosition(vgPosition);
                VgINavigationRefPtr vgINavigationRefPtr = C1420e.this.f524o;
                if (vgINavigationRefPtr != null) {
                    vgINavigationRefPtr.updateCurrentPosition(vgPosition, 0.0d);
                }
                if (C1420e.this.f509O) {
                    C1420e.this.f532w.mo28399b(0);
                    if (C1420e.this.f527r != null) {
                        C1420e eVar2 = C1420e.this;
                        eVar2.mo28414e(eVar2.f527r);
                        return;
                    }
                    VgPosition vgPosition2 = C1420e.f494P;
                    if (vgPosition2 != null) {
                        C1420e.this.mo28385a(vgPosition2);
                    }
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.e$d */
    /* compiled from: VgMyBasicApplicationController */
    class C1425d extends VgINavigationListener {
        public C1425d() {
        }

        public void notifyNewInstruction(VgINavigationConstRefPtr vgINavigationConstRefPtr, long j) {
            C1420e.this.mo28374a(vgINavigationConstRefPtr, j);
            C1420e.this.mo28432s();
        }

        public void notifyPositionUpdated(VgINavigationConstRefPtr vgINavigationConstRefPtr, VgPosition vgPosition, double d) {
        }
    }

    /* renamed from: com.navibees.maps.e$e */
    /* compiled from: VgMyBasicApplicationController */
    class C1426e extends VgINavigationCallback {

        /* renamed from: com.navibees.maps.e$e$a */
        /* compiled from: VgMyBasicApplicationController */
        class C1427a implements Runnable {
            C1427a() {
            }

            public void run() {
                Toast.makeText(C1420e.this.f513d.getBaseContext(), C1420e.this.f513d.getResources().getString(C1164R.string.messageErrorNavigation), 0).show();
            }
        }

        public C1426e() {
        }

        public boolean notifyNavigationComputed(VgNavigationRequestStatus vgNavigationRequestStatus, VgINavigationRefPtr vgINavigationRefPtr) {
            Log.m1171d("poipoi", "Navigation Computed");
            if (vgNavigationRequestStatus.swigValue() == VgNavigationRequestStatus.eSuccess.swigValue()) {
                C1420e.this.f524o = new VgINavigationRefPtr(vgINavigationRefPtr);
                C1420e eVar = C1420e.this;
                eVar.f524o.addListener(eVar.f517h);
                Iterator it = C1420e.this.f497C.iterator();
                while (it.hasNext()) {
                    ((C1521j) it.next()).mo28361a(C1420e.this.f524o);
                }
            } else if (vgNavigationRequestStatus.swigValue() == VgNavigationRequestStatus.eError.swigValue()) {
                C1420e.this.f513d.runOnUiThread(new C1427a());
            }
            return true;
        }
    }

    /* renamed from: com.navibees.maps.e$f */
    /* compiled from: VgMyBasicApplicationController */
    class C1428f extends VgIRouteCallback {

        /* renamed from: com.navibees.maps.e$f$a */
        /* compiled from: VgMyBasicApplicationController */
        class C1429a implements Runnable {
            C1429a() {
            }

            public void run() {
                C1420e eVar = C1420e.this;
                eVar.mo28384a(7, eVar.f530u);
            }
        }

        /* renamed from: com.navibees.maps.e$f$b */
        /* compiled from: VgMyBasicApplicationController */
        class C1430b implements Runnable {
            C1430b() {
            }

            public void run() {
                Activity activity = C1420e.this.f513d;
                if (activity != null) {
                    activity.invalidateOptionsMenu();
                }
            }
        }

        /* renamed from: com.navibees.maps.e$f$c */
        /* compiled from: VgMyBasicApplicationController */
        class C1431c implements Runnable {
            C1431c() {
            }

            public void run() {
                Toast.makeText(C1420e.this.f513d.getBaseContext(), C1420e.this.f513d.getResources().getString(C1164R.string.messageErrorRouting), 0).show();
            }
        }

        public C1428f() {
        }

        @SuppressLint({"NewApi"})
        public void notifyRouteComputed(VgRouteRequestStatus vgRouteRequestStatus, VgIRouteRefPtr vgIRouteRefPtr) {
            if (VgMySurfaceView.f707h) {
                VgMySurfaceView.f707h = false;
                return;
            }
            if (vgRouteRequestStatus.swigValue() == VgRouteRequestStatus.eSuccess.swigValue()) {
                C1420e eVar = C1420e.this;
                if (eVar.f505K) {
                    eVar.f525p = new VgIRouteRefPtr(vgIRouteRefPtr);
                }
                String poiID = vgIRouteRefPtr.getRequestParameters().getMDestinations().get(0).getPoiID();
                VgPosition position = vgIRouteRefPtr.getRequestParameters().getMOrigin().getPosition();
                Intent intent = new Intent();
                intent.putExtra("dest", poiID);
                intent.putExtra("length", vgIRouteRefPtr.getLength());
                intent.putExtra("duration", vgIRouteRefPtr.getDuration());
                intent.putExtra("x", position.getMXOrLongitude());
                intent.putExtra("y", position.getMYOrLatitude());
                intent.putExtra("z", position.getMZOrAltitude());
                intent.setAction("details_route");
                Context context = MapHoverActivity.mContext;
                if (context != null) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            }
            if (C1420e.this.f528s != null) {
                if (vgRouteRequestStatus.swigValue() == VgRouteRequestStatus.eSuccess.swigValue()) {
                    if (C1420e.this.f529t == null) {
                        C1420e.this.f529t = new VgIRouteRefPtr(vgIRouteRefPtr.get());
                        C1420e eVar2 = C1420e.this;
                        eVar2.f526q = vgRouteRequestStatus;
                        eVar2.f527r = eVar2.f530u;
                    } else if (C1420e.this.f529t.getLength() > vgIRouteRefPtr.getLength()) {
                        C1420e.this.f529t = new VgIRouteRefPtr(vgIRouteRefPtr.get());
                        C1420e eVar3 = C1420e.this;
                        eVar3.f526q = vgRouteRequestStatus;
                        eVar3.f527r = eVar3.f530u;
                    }
                }
                if (!C1420e.this.f528s.isEmpty()) {
                    C1420e eVar4 = C1420e.this;
                    eVar4.f530u = (String) eVar4.f528s.poll();
                    C1420e.this.f514e.queueEvent(new C1429a());
                    return;
                }
                if (C1420e.this.f529t != null && C1420e.this.f529t.isValid()) {
                    C1420e eVar5 = C1420e.this;
                    eVar5.f525p = new VgIRouteRefPtr(eVar5.f529t);
                }
                C1420e.this.f529t = null;
                C1420e.this.f528s = null;
            } else if (C1420e.this.f504J != null) {
                if (vgRouteRequestStatus.swigValue() == VgRouteRequestStatus.eSuccess.swigValue()) {
                    if (C1420e.this.f529t == null) {
                        C1420e.this.f529t = new VgIRouteRefPtr(vgIRouteRefPtr.get());
                        C1420e.this.f526q = vgRouteRequestStatus;
                    } else if (C1420e.this.f529t.getLength() > vgIRouteRefPtr.getLength()) {
                        C1420e.this.f529t = new VgIRouteRefPtr(vgIRouteRefPtr.get());
                        C1420e.this.f526q = vgRouteRequestStatus;
                    }
                }
                if (C1420e.this.f529t != null && C1420e.this.f529t.isValid()) {
                    C1420e eVar6 = C1420e.this;
                    eVar6.f525p = eVar6.f529t;
                }
                C1420e.this.f529t = null;
            } else {
                if (vgIRouteRefPtr == null || !vgIRouteRefPtr.isValid()) {
                    C1420e.this.f525p = null;
                } else {
                    C1420e.this.f525p = new VgIRouteRefPtr(vgIRouteRefPtr);
                }
                C1420e.this.f526q = vgRouteRequestStatus;
            }
            if (C1420e.this.f508N) {
                VgIRouteRefPtr vgIRouteRefPtr2 = C1420e.this.f525p;
                if (vgIRouteRefPtr2 != null && vgIRouteRefPtr2.isValid()) {
                    C1420e.this.f508N = false;
                    if (C1420e.this.f527r != null) {
                        VisioPOI visioPOI = NaviBeesUtils.getVisioPOI(C1420e.this.f513d.getApplication(), C1420e.this.f527r);
                        if (!(visioPOI == null || C1420e.this.f506L == null)) {
                            C1420e.this.f506L.onRouteFindCallback(visioPOI, C1420e.this.f525p.getLength(), C1420e.this.f525p.getDuration(), visioPOI.getPOIFloor(C1420e.this.f513d.getApplication()), C1420e.this.f507M);
                        }
                    } else if (!(C1420e.f494P == null || C1420e.this.f506L == null)) {
                        C1420e.this.f506L.onRouteFindCallbackForPosition(C1420e.this.f525p.getLength(), C1420e.this.f525p.getDuration(), C1420e.this.f507M);
                    }
                } else if (!(C1420e.this.f527r == null || C1420e.this.f506L == null)) {
                    VisioPOI visioPOI2 = NaviBeesUtils.getVisioPOI(C1420e.this.f513d.getApplication(), C1420e.this.f527r);
                    C1420e.this.f506L.onRouteFindCallback(visioPOI2, -1.0d, -1.0d, visioPOI2.getPOIFloor(C1420e.this.f513d.getApplication()), C1420e.this.f507M);
                }
                return;
            }
            if (C1420e.this.f509O) {
                VgIRouteRefPtr vgIRouteRefPtr3 = C1420e.this.f525p;
                if (!(vgIRouteRefPtr3 == null || !vgIRouteRefPtr3.isValid() || C1420e.this.f506L == null)) {
                    C1420e.this.f506L.updateNavigationProgress(C1420e.this.f525p.getLength(), C1420e.this.f525p.getDuration());
                    return;
                }
            }
            if (MapHoverActivity.enableRoutingWhenOutOfVenue) {
                VgIRouteRefPtr vgIRouteRefPtr4 = C1420e.this.f525p;
                if (!(vgIRouteRefPtr4 == null || !vgIRouteRefPtr4.isValid() || C1420e.this.f506L == null)) {
                    C1420e.this.f506L.updateNavigationProgress(C1420e.this.f525p.getLength(), C1420e.this.f525p.getDuration());
                }
            }
            if (C1420e.this.f526q.swigValue() == VgRouteRequestStatus.eSuccess.swigValue()) {
                if (VERSION.SDK_INT >= 11) {
                    Activity activity = C1420e.this.f513d;
                    if (activity != null) {
                        activity.runOnUiThread(new C1430b());
                    }
                }
                Iterator it = C1420e.this.f533x.iterator();
                while (it.hasNext()) {
                    C1528n nVar = (C1528n) it.next();
                    if (nVar.createRouteObjects(new VgIRouteConstRefPtr(C1420e.this.f525p.get()))) {
                        nVar.show();
                    }
                }
                C1420e eVar7 = C1420e.this;
                VgINavigationRequestParameters vgINavigationRequestParameters = new VgINavigationRequestParameters(0, eVar7.f516g, eVar7.f525p);
                VgNavigationModalityParametersMap vgNavigationModalityParametersMap = new VgNavigationModalityParametersMap();
                vgNavigationModalityParametersMap.set(VgModalityParameterType.eStraightAngleThreshold, 180.0d);
                vgNavigationModalityParametersMap.set(VgModalityParameterType.eDistanceFromCouloirThreshold, 1000.0d);
                vgINavigationRequestParameters.getMModalityParameters().set("shuttle", vgNavigationModalityParametersMap);
                C1420e.this.mo28375a(vgINavigationRequestParameters);
            } else if (C1420e.this.f526q.swigValue() == VgRouteRequestStatus.eError.swigValue()) {
                C1420e.this.f513d.runOnUiThread(new C1431c());
            }
        }
    }

    public C1420e(Activity activity, C1482e eVar, VgMySurfaceView vgMySurfaceView) {
        String str = "";
        this.f510a = str;
        this.f511b = str;
        this.f501G = str;
        new VgPosition();
        this.f507M = false;
        this.f508N = false;
        this.f509O = false;
        this.f513d = activity;
        this.f500F = LocalBroadcastManager.getInstance(this.f513d);
        this.f514e = vgMySurfaceView;
        this.f498D = eVar;
        this.f514e.mo28536a(this);
        this.f515f = new VgIRouteCallbackRefPtr((VgIRouteCallback) new C1428f());
        this.f516g = new VgINavigationCallbackRefPtr((VgINavigationCallback) new C1426e());
        this.f517h = new VgINavigationListenerRefPtr((VgINavigationListener) new C1425d());
    }

    /* renamed from: a */
    public void mo28378a(String str, double d) {
    }

    public void clear() {
    }

    /* renamed from: d */
    public int mo28409d(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i != 4) {
            return i != 5 ? 7 : 6;
        }
        return 5;
    }

    public void hide() {
    }

    /* renamed from: i */
    public boolean mo28421i() {
        return this.f522m != null;
    }

    public boolean isVisible() {
        return false;
    }

    /* renamed from: j */
    public void mo28423j() {
        C1511a aVar = this.f534y;
        if (aVar != null) {
            aVar.hide();
        }
    }

    /* renamed from: k */
    public void mo28424k() {
        Iterator it = this.f496B.iterator();
        while (it.hasNext()) {
            C1513c cVar = (C1513c) it.next();
            if (cVar.isVisible()) {
                cVar.hide();
            }
        }
    }

    /* renamed from: l */
    public boolean mo28425l() {
        return (this.f514e == null || this.f518i == null || this.f519j == null || this.f520k == null) ? false : true;
    }

    public void layerWillChangeFrom(C1515e eVar, String str, String str2) {
        Iterator it = this.f495A.iterator();
        while (it.hasNext()) {
            ((C1517f) it.next()).layerWillChangeFrom(eVar, str, str2);
        }
    }

    /* renamed from: m */
    public void mo28426m() {
        this.f520k.unloadConfiguration();
        this.f518i = null;
        this.f519j = null;
        this.f520k = null;
        this.f521l = null;
        this.f522m = null;
        this.f523n = null;
        release();
        this.f524o = null;
        this.f531v = null;
        this.f532w = null;
        this.f533x.clear();
        this.f534y = null;
        this.f535z = null;
        this.f495A.clear();
        this.f496B.clear();
        this.f497C.clear();
    }

    public void modeDidChange(C1515e eVar, C1516a aVar) {
        Iterator it = this.f495A.iterator();
        while (it.hasNext()) {
            ((C1517f) it.next()).modeDidChange(eVar, aVar);
        }
    }

    public void modeWillChange(C1515e eVar, C1516a aVar) {
        Iterator it = this.f495A.iterator();
        while (it.hasNext()) {
            ((C1517f) it.next()).modeWillChange(eVar, aVar);
        }
    }

    /* renamed from: n */
    public boolean mo28427n() {
        VgPosition vgPosition;
        if (mo28382a(7) && !mo28382a(0)) {
            new VgPosition();
            IndoorLocation lastLocationCoordinateLatLong = NaviBeesManager.getInstance(this.f513d.getApplication()).getPositionManager().getLastLocationCoordinateLatLong();
            if (lastLocationCoordinateLatLong != null) {
                Building building = (Building) NaviBeesManager.getInstance(this.f513d.getApplication()).getMetaDataManager().getBuildings().get(lastLocationCoordinateLatLong.buildingId);
                if (building != null) {
                    List<Floor> list = building.floors;
                    if (list != null && lastLocationCoordinateLatLong.floor < list.size()) {
                        int i = lastLocationCoordinateLatLong.floor;
                        if (i >= 0) {
                            vgPosition = new VgPosition(lastLocationCoordinateLatLong.f1332x, lastLocationCoordinateLatLong.f1333y, mo28404c(((VisioFloor) building.floors.get(i)).vgfloorId));
                        }
                    }
                }
                return false;
            }
            VgPosition vgPosition2 = this.f503I;
            if (vgPosition2 != null) {
                vgPosition = new VgPosition(vgPosition2.getMXOrLongitude(), this.f503I.getMYOrLatitude(), this.f503I.getMZOrAltitude());
            } else {
                Venue currentVenue = NaviBeesManager.getInstance(this.f513d.getApplication()).getMetaDataManager().getCurrentVenue();
                if (currentVenue != null) {
                    IndoorLocation indoorLocation = currentVenue.initialLocation;
                    if (indoorLocation != null) {
                        Building building2 = (Building) NaviBeesManager.getInstance(this.f513d.getApplication()).getMetaDataManager().getBuildings().get(indoorLocation.buildingId);
                        if (building2 != null) {
                            List<Floor> list2 = building2.floors;
                            if (list2 != null && indoorLocation.floor < list2.size()) {
                                int i2 = indoorLocation.floor;
                                if (i2 >= 0) {
                                    double c = mo28404c(((VisioFloor) building2.floors.get(i2)).vgfloorId);
                                    IndoorLocation convertUTMToLatLong = NaviBeesUtils.convertUTMToLatLong(indoorLocation);
                                    VgPosition vgPosition3 = new VgPosition(convertUTMToLatLong.f1332x, convertUTMToLatLong.f1333y, c);
                                    vgPosition = vgPosition3;
                                }
                            }
                        }
                    }
                }
                return false;
            }
            this.f514e.queueEvent(new C1421a(vgPosition));
        }
        return true;
    }

    /* renamed from: o */
    public boolean mo28428o() {
        if (!mo28382a(0) || !mo28382a(7)) {
            return false;
        }
        mo28398b();
        return true;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
    }

    public void onSurfaceDestroyed() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: p */
    public void mo28429p() {
        VgMySurfaceView vgMySurfaceView = this.f514e;
        if (vgMySurfaceView != null) {
            this.f518i = vgMySurfaceView.getApplication();
        }
        VgIApplication vgIApplication = this.f518i;
        if (vgIApplication != null) {
            this.f519j = vgIApplication.editEngine();
        }
        VgIEngine vgIEngine = this.f519j;
        if (vgIEngine != null) {
            this.f520k = vgIEngine.editDatabase();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: q */
    public void mo28430q() {
        if (mo28425l()) {
            VgIModuleManager editModuleManager = this.f518i.editModuleManager();
            if (editModuleManager != null) {
                VgIModule queryModule = editModuleManager.queryModule("Map");
                if (queryModule != null) {
                    this.f521l = C1732libVisioMove.castToIMapModule(queryModule);
                }
                VgIModule queryModule2 = editModuleManager.queryModule("3D");
                if (queryModule2 != null) {
                    C1732libVisioMove.castToI3DModule(queryModule2);
                }
                VgIModule queryModule3 = editModuleManager.queryModule("Routing");
                if (queryModule3 != null) {
                    this.f522m = C1732libVisioMove.castToIRoutingModule(queryModule3);
                }
                VgIModule queryModule4 = editModuleManager.queryModule("Navigation");
                if (queryModule4 != null) {
                    this.f523n = C1732libVisioMove.castToINavigationModule(queryModule4);
                }
            }
        }
    }

    /* renamed from: r */
    public boolean mo28431r() {
        mo28429p();
        if (!mo28425l()) {
            return true;
        }
        if (!mo28390a(this.f511b, this.f512c, this.f510a, 0)) {
            String errorString = this.f519j.getErrorString(this.f519j.getLastError());
            StringBuilder sb = new StringBuilder();
            sb.append("loadConfiguration failed: ");
            sb.append(errorString);
            this.f513d.runOnUiThread(new C1422b(sb.toString()));
            return false;
        }
        mo28430q();
        return true;
    }

    public void release() {
        mo28423j();
        mo28424k();
        mo28413e();
        mo28410d();
        LinkedList<C1521j> linkedList = this.f497C;
        if (linkedList != null) {
            linkedList.clear();
            this.f497C = null;
        }
        C1496c cVar = this.f499E;
        if (cVar != null) {
            cVar.mo28598a();
            this.f499E = null;
        }
        C1527m mVar = this.f532w;
        if (mVar != null) {
            mVar.release();
            this.f532w = null;
        }
        LinkedList<C1528n> linkedList2 = this.f533x;
        if (linkedList2 != null) {
            Iterator it = linkedList2.iterator();
            while (it.hasNext()) {
                ((C1528n) it.next()).release();
            }
            this.f533x.clear();
            this.f533x = null;
        }
        C1511a aVar = this.f534y;
        if (aVar != null) {
            aVar.release();
            this.f534y = null;
        }
        C1520i iVar = this.f535z;
        if (iVar != null) {
            iVar.release();
            this.f535z = null;
        }
        C1515e eVar = this.f531v;
        if (eVar != null) {
            eVar.release();
            this.f531v = null;
        }
        C1496c cVar2 = this.f499E;
        if (cVar2 != null) {
            cVar2.mo28598a();
            this.f499E = null;
        }
        C1482e eVar2 = this.f498D;
        if (eVar2 != null) {
            eVar2.mo28575a();
            this.f498D = null;
        }
        LinkedList<C1513c> linkedList3 = this.f496B;
        if (linkedList3 != null) {
            Iterator it2 = linkedList3.iterator();
            while (it2.hasNext()) {
                ((C1513c) it2.next()).release();
            }
            this.f496B.clear();
        }
        LinkedList<C1517f> linkedList4 = this.f495A;
        if (linkedList4 != null) {
            linkedList4.clear();
        }
        this.f517h = null;
        this.f516g = null;
        this.f515f = null;
        VgIDatabase vgIDatabase = this.f520k;
        if (vgIDatabase != null) {
            vgIDatabase.unloadConfiguration();
        }
        this.f518i = null;
        this.f519j = null;
        this.f520k = null;
        this.f521l = null;
        this.f522m = null;
        this.f523n = null;
        VgMySurfaceView vgMySurfaceView = this.f514e;
        if (vgMySurfaceView != null) {
            vgMySurfaceView.mo28537b(this);
            this.f514e = null;
        }
        this.f513d = null;
        this.f509O = false;
        this.f508N = false;
        this.f506L = null;
    }

    /* renamed from: s */
    public void mo28432s() {
        Iterator it = this.f496B.iterator();
        while (it.hasNext()) {
            C1513c cVar = (C1513c) it.next();
            if (!cVar.isVisible()) {
                cVar.show();
            }
        }
    }

    public void show() {
    }

    /* renamed from: t */
    public void mo28433t() {
        VgINavigationRefPtr vgINavigationRefPtr = this.f524o;
        if (vgINavigationRefPtr != null) {
            mo28374a(new VgINavigationConstRefPtr(vgINavigationRefPtr.get()), this.f524o.getCurrentInstructionIndex());
        }
    }

    /* renamed from: c */
    public void mo28202c(int i) {
        VgINavigationRefPtr vgINavigationRefPtr = this.f524o;
        if (vgINavigationRefPtr != null && vgINavigationRefPtr.isValid()) {
            long j = (long) i;
            if (j < this.f524o.getNumInstructions() && i >= 0) {
                this.f517h.notifyNewInstruction(new VgINavigationConstRefPtr(this.f524o.get()), j);
                VgINavigationInstructionConstRefPtr instruction = this.f524o.getInstruction(j);
                mo28376a(instruction.getPosition(), instruction.getLayer(), true);
            }
        }
    }

    /* renamed from: d */
    public void mo28411d(String str) {
        this.f511b = str;
    }

    /* renamed from: e */
    public void mo28413e() {
        Iterator it = this.f533x.iterator();
        while (it.hasNext()) {
            ((C1528n) it.next()).clear();
        }
        Iterator it2 = this.f497C.iterator();
        while (it2.hasNext()) {
            ((C1521j) it2.next()).mo28362b(this.f524o);
        }
        VgINavigationRefPtr vgINavigationRefPtr = this.f524o;
        if (vgINavigationRefPtr != null) {
            vgINavigationRefPtr.removeListener(this.f517h);
            this.f524o = null;
        }
        this.f525p = null;
    }

    /* renamed from: f */
    public C1515e mo28415f() {
        return this.f531v;
    }

    /* renamed from: g */
    public boolean mo28417g() {
        return this.f521l != null;
    }

    /* renamed from: h */
    public boolean mo28419h() {
        return this.f523n != null;
    }

    /* renamed from: b */
    public boolean mo28402b(VgPosition vgPosition) {
        C1527m mVar = this.f532w;
        if (mVar == null || vgPosition == null) {
            return false;
        }
        return mVar.mo28383a(7, vgPosition);
    }

    /* renamed from: d */
    public void mo28410d() {
        Iterator it = this.f496B.iterator();
        while (it.hasNext()) {
            ((C1513c) it.next()).clear();
        }
    }

    /* renamed from: f */
    public void mo28416f(String str) {
        this.f510a = str;
    }

    /* renamed from: g */
    public boolean mo28418g(String str) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            return mVar.mo28384a(7, str);
        }
        return false;
    }

    /* renamed from: h */
    public boolean mo28420h(String str) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            return mVar.mo28384a(0, str);
        }
        return false;
    }

    /* renamed from: b */
    public double[] mo28403b(String str, String str2) {
        this.f505K = true;
        mo28384a(0, str);
        mo28384a(7, str2);
        mo28398b();
        double[] dArr = {-1.0d, -1.0d};
        VgIRouteRefPtr vgIRouteRefPtr = this.f525p;
        if (vgIRouteRefPtr != null && vgIRouteRefPtr.isValid()) {
            dArr[0] = this.f525p.getLength();
            dArr[1] = this.f525p.getDuration();
        }
        this.f505K = false;
        return dArr;
    }

    /* renamed from: d */
    public void mo28412d(boolean z) {
        this.f509O = z;
    }

    /* renamed from: a */
    public boolean mo28390a(String str, long j, String str2, long j2) {
        if (!mo28425l() || !this.f520k.loadConfiguration(str, j, str2)) {
            return false;
        }
        String string = PreferenceManager.getDefaultSharedPreferences(this.f513d.getApplicationContext()).getString(String.format(NaviBeesConstants.FONT_FOR_LANGUAGE_KEY, new Object[]{NaviBeesUtils.getAppLang()}), null);
        if (string != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f513d.getFilesDir().getAbsolutePath());
            String str3 = "/";
            sb.append(str3);
            sb.append(NaviBeesConstants.MAP_RESOURCES_FOLDER);
            sb.append(str3);
            sb.append(string);
            boolean font = this.f514e.getApplication().editEngine().editFontManager().setFont(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("font changed ");
            sb2.append(font);
            Log.m1171d("PlaceConfiguration", sb2.toString());
        }
        if (!this.f520k.selectDataset((int) j2)) {
            return false;
        }
        this.f499E = new C1496c(this.f513d, this.f498D, this.f518i, this.f514e);
        return true;
    }

    /* renamed from: c */
    public C1516a mo28405c() {
        C1515e eVar = this.f531v;
        if (eVar != null) {
            return eVar.mo28405c();
        }
        return null;
    }

    /* renamed from: c */
    public double[] mo28407c(String str, String str2) {
        this.f528s = null;
        this.f530u = null;
        this.f529t = null;
        f494P = null;
        this.f527r = str2;
        this.f508N = true;
        this.f507M = false;
        this.f509O = false;
        double[] dArr = {-1.0d, -1.0d};
        C1527m mVar = this.f532w;
        if (mVar == null || !mVar.mo28384a(0, str) || !this.f532w.mo28384a(7, str2)) {
            return dArr;
        }
        mo28398b();
        VgIRouteRefPtr vgIRouteRefPtr = this.f525p;
        if (vgIRouteRefPtr != null && vgIRouteRefPtr.isValid()) {
            dArr[0] = this.f525p.getLength();
            dArr[1] = this.f525p.getDuration();
        }
        return dArr;
    }

    /* renamed from: b */
    public void mo28399b(int i) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            mVar.mo28399b(i);
        }
    }

    /* renamed from: e */
    public boolean mo28414e(String str) {
        this.f528s = null;
        this.f530u = null;
        this.f529t = null;
        f494P = null;
        this.f527r = str;
        return mo28384a(7, str);
    }

    /* renamed from: b */
    public void mo28398b() {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            mVar.mo28398b();
        }
    }

    /* renamed from: b */
    public void mo28401b(boolean z) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            mVar.mo28401b(z);
        }
    }

    /* renamed from: b */
    public void mo28400b(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Enabled Provider: ");
        sb.append(str);
        Log.m1171d("--provider:", sb.toString());
        C1481d.m455a().mo28570c(str).mo28338d();
    }

    /* renamed from: a */
    public void mo28368a(C1515e eVar) {
        this.f531v = eVar;
    }

    /* renamed from: a */
    public void mo28372a(C1527m mVar) {
        this.f532w = mVar;
    }

    /* renamed from: a */
    public void mo28373a(C1528n nVar) {
        this.f533x.add(nVar);
    }

    /* renamed from: a */
    public void mo28370a(C1520i iVar) {
        this.f535z = iVar;
    }

    /* renamed from: a */
    public void mo28369a(C1517f fVar) {
        this.f495A.add(fVar);
    }

    /* renamed from: a */
    public void mo28367a(C1513c cVar) {
        this.f496B.add(cVar);
    }

    /* renamed from: a */
    public void mo28366a(C1511a aVar) {
        this.f534y = aVar;
    }

    /* renamed from: c */
    public void mo28406c(boolean z) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            mVar.mo28406c(z);
        }
    }

    /* renamed from: a */
    public void mo28371a(C1521j jVar) {
        this.f497C.add(jVar);
    }

    /* renamed from: a */
    public void mo28364a(long j) {
        this.f512c = j;
    }

    /* renamed from: c */
    public double mo28404c(String str) {
        double d;
        float[] fArr = new float[1];
        float[] fArr2 = new float[1];
        String floorAltitude = NaviBeesManager.getInstance(this.f513d.getApplication()).getMetaDataManager().getFloorAltitude(str);
        if (!TextUtils.isEmpty(floorAltitude)) {
            d = Double.valueOf(floorAltitude).doubleValue();
        } else {
            d = 0.0d;
        }
        if (d != 0.0d) {
            return d;
        }
        Log.m1171d("VgMyBasicApplicationController", "couldn't get floor height");
        return this.f521l.getHeightRangeForLayer(str, fArr, fArr2) ? (double) ((fArr[0] + fArr2[0]) / 2.0f) : d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28365a(IndoorLocation indoorLocation) {
        this.f503I = null;
        SimpleArrayMap buildings = NaviBeesManager.getInstance(this.f513d.getApplication()).getMetaDataManager().getBuildings();
        if (indoorLocation != null && buildings != null) {
            Building building = (Building) buildings.get(indoorLocation.buildingId);
            if (building != null) {
                List<Floor> list = building.floors;
                if (list != null && indoorLocation.floor < list.size()) {
                    int i = indoorLocation.floor;
                    if (i >= 0) {
                        VgPosition vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, mo28404c(((VisioFloor) building.floors.get(i)).vgfloorId));
                        this.f503I = vgPosition;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public CharSequence mo28363a() {
        C1515e eVar = this.f531v;
        if (eVar != null) {
            return eVar.mo28363a();
        }
        return null;
    }

    /* renamed from: a */
    public void mo28376a(VgPosition vgPosition, String str, boolean z) {
        C1515e eVar = this.f531v;
        if (eVar != null) {
            eVar.mo28376a(vgPosition, str, z);
        }
    }

    /* renamed from: a */
    public boolean mo28384a(int i, String str) {
        C1527m mVar = this.f532w;
        if (mVar == null || !mVar.mo28384a(i, str)) {
            return false;
        }
        boolean o = mo28428o();
        return !o ? mo28427n() : o;
    }

    /* renamed from: a */
    public boolean mo28383a(int i, VgPosition vgPosition) {
        C1527m mVar = this.f532w;
        if (mVar == null || !mVar.mo28383a(i, vgPosition)) {
            return false;
        }
        boolean o = mo28428o();
        if (o) {
            return o;
        }
        boolean n = mo28427n();
        if (!n || mo28382a(7)) {
            return n;
        }
        mo28414e(this.f527r);
        return n;
    }

    /* renamed from: a */
    public boolean mo28392a(String str, List<POI> list) {
        if (this.f532w == null) {
            return false;
        }
        int size = list.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            mo28384a(mo28409d(i), ((VisioPOI) list.get(i)).vgId);
        }
        return this.f532w.mo28384a(7, str);
    }

    /* renamed from: a */
    public boolean mo28389a(VgPosition vgPosition, List<POI> list) {
        if (this.f532w == null || vgPosition == null) {
            return false;
        }
        int size = list.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            mo28384a(mo28409d(i), ((VisioPOI) list.get(i)).vgId);
        }
        return this.f532w.mo28383a(7, vgPosition);
    }

    /* renamed from: a */
    public boolean mo28391a(String str, ArrayList<POI> arrayList) {
        this.f528s = null;
        this.f530u = null;
        this.f529t = null;
        f494P = null;
        this.f527r = str;
        int size = arrayList.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            mo28384a(mo28409d(i), ((VisioPOI) arrayList.get(i)).vgId);
        }
        return mo28384a(7, str);
    }

    /* renamed from: a */
    public boolean mo28393a(List<String> list) {
        this.f527r = null;
        this.f529t = null;
        f494P = null;
        this.f528s = new LinkedList<>(list);
        this.f530u = (String) this.f528s.poll();
        return mo28384a(7, this.f530u);
    }

    /* renamed from: a */
    public boolean mo28395a(List<String> list, ArrayList<POI> arrayList) {
        this.f527r = null;
        this.f529t = null;
        f494P = null;
        this.f528s = new LinkedList<>(list);
        int size = arrayList.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            mo28384a(mo28409d(i), ((VisioPOI) arrayList.get(i)).vgId);
        }
        this.f530u = (String) this.f528s.poll();
        return mo28384a(7, this.f530u);
    }

    /* renamed from: a */
    public boolean mo28385a(VgPosition vgPosition) {
        this.f527r = null;
        this.f528s = null;
        this.f530u = null;
        this.f529t = null;
        f494P = vgPosition;
        return mo28383a(7, vgPosition);
    }

    /* renamed from: a */
    public boolean mo28387a(VgPosition vgPosition, ArrayList<POI> arrayList) {
        this.f527r = null;
        this.f528s = null;
        this.f530u = null;
        this.f529t = null;
        f494P = vgPosition;
        int size = arrayList.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            mo28384a(mo28409d(i), ((VisioPOI) arrayList.get(i)).vgId);
        }
        return mo28383a(7, vgPosition);
    }

    /* renamed from: a */
    public double[] mo28397a(VgPosition vgPosition, String str) {
        this.f505K = true;
        mo28383a(0, vgPosition);
        mo28384a(7, str);
        mo28398b();
        double[] dArr = {-1.0d, -1.0d};
        VgIRouteRefPtr vgIRouteRefPtr = this.f525p;
        if (vgIRouteRefPtr != null && vgIRouteRefPtr.isValid()) {
            dArr[0] = this.f525p.getLength();
            dArr[1] = this.f525p.getDuration();
        }
        this.f505K = false;
        return dArr;
    }

    /* renamed from: a */
    public boolean mo28382a(int i) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            return mVar.mo28382a(i);
        }
        return false;
    }

    /* renamed from: a */
    public void mo28381a(boolean z) {
        C1527m mVar = this.f532w;
        if (mVar != null) {
            mVar.mo28381a(z);
        }
    }

    /* renamed from: a */
    public void mo28375a(VgINavigationRequestParameters vgINavigationRequestParameters) {
        C1520i iVar = this.f535z;
        if (iVar != null) {
            iVar.mo28465a(vgINavigationRequestParameters);
        }
    }

    /* renamed from: a */
    public void mo28374a(VgINavigationConstRefPtr vgINavigationConstRefPtr, long j) {
        Iterator it = this.f496B.iterator();
        while (it.hasNext()) {
            ((C1513c) it.next()).mo28374a(vgINavigationConstRefPtr, j);
        }
    }

    /* renamed from: a */
    public void mo28379a(String str, VgPosition vgPosition) {
        new VgPosition(vgPosition);
        this.f514e.queueEvent(new C1424c(vgPosition));
    }

    /* renamed from: a */
    public void mo28380a(String str, String str2) {
        this.f501G = new String(str2);
    }

    /* renamed from: a */
    public void mo28377a(String str) {
        C1481d.m455a().mo28570c(str).mo28334a();
        StringBuilder sb = new StringBuilder();
        sb.append("Disabled Provider: ");
        sb.append(str);
        Log.m1171d("--provider:", sb.toString());
    }

    /* renamed from: a */
    public boolean mo28394a(List<String> list, MapHoverInterface mapHoverInterface, boolean z) {
        if (list == null || list.size() <= 0 || mapHoverInterface == null) {
            return false;
        }
        this.f507M = z;
        this.f508N = true;
        this.f509O = false;
        this.f506L = mapHoverInterface;
        if (list.size() == 1) {
            return mo28414e((String) list.get(0));
        }
        return mo28393a(list);
    }

    /* renamed from: a */
    public boolean mo28396a(List<String> list, ArrayList<POI> arrayList, MapHoverInterface mapHoverInterface, boolean z) {
        if (list == null || list.size() <= 0 || mapHoverInterface == null) {
            return false;
        }
        this.f507M = z;
        this.f508N = true;
        this.f509O = false;
        this.f506L = mapHoverInterface;
        if (list.size() == 1) {
            return mo28391a((String) list.get(0), arrayList);
        }
        return mo28395a(list, arrayList);
    }

    /* renamed from: a */
    public boolean mo28386a(VgPosition vgPosition, MapHoverInterface mapHoverInterface, boolean z) {
        if (vgPosition == null || mapHoverInterface == null) {
            return false;
        }
        this.f507M = z;
        this.f508N = true;
        this.f509O = false;
        this.f506L = mapHoverInterface;
        return mo28385a(vgPosition);
    }

    /* renamed from: a */
    public boolean mo28388a(VgPosition vgPosition, ArrayList<POI> arrayList, MapHoverInterface mapHoverInterface, boolean z) {
        if (vgPosition == null || mapHoverInterface == null) {
            return false;
        }
        this.f507M = z;
        this.f508N = true;
        this.f509O = false;
        this.f506L = mapHoverInterface;
        return mo28387a(vgPosition, arrayList);
    }
}
