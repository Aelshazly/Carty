package com.navibees.maps;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.C1164R;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.MetaDataManager;
import com.navibees.core.model.metadata.json.BeaconNodeConfiguration;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.Portal;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.metadata.json.VisioFacility;
import com.navibees.core.model.metadata.json.VisioFloor;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.p018a.C1343a;
import com.navibees.visioglobe.C1472a;
import com.navibees.visioglobe.C1473b;
import com.navibees.visioglobe.C1477c;
import com.navibees.visioglobe.C1481d;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.VgMySurfaceView.C1471a;
import com.navibees.visioglobe.p019f.C1485a;
import com.navibees.visioglobe.p019f.C1491b;
import com.navibees.visioglobe.p019f.C1501d;
import com.navibees.visioglobe.p019f.C1501d.C1502a;
import com.navibees.visioglobe.p019f.C1503e;
import com.navibees.visioglobe.p019f.C1504f;
import com.navibees.visioglobe.p019f.C1509h;
import com.navibees.visioglobe.p020g.C1511a;
import com.navibees.visioglobe.p020g.C1513c;
import com.navibees.visioglobe.p020g.C1515e;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.p020g.C1517f;
import com.navibees.visioglobe.p020g.C1518g;
import com.navibees.visioglobe.p020g.C1519h;
import com.navibees.visioglobe.p020g.C1520i;
import com.navibees.visioglobe.p020g.C1521j;
import com.navibees.visioglobe.p020g.C1523l;
import com.navibees.visioglobe.p020g.C1523l.C1524a;
import com.navibees.visioglobe.p020g.C1523l.C1524a.C1525a;
import com.navibees.visioglobe.p020g.C1523l.C1526b;
import com.navibees.visioglobe.p020g.C1527m;
import com.navibees.visioglobe.p020g.C1528n;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIEngineContext;
import com.visioglobe.libVisioMove.VgIEnginePostDrawCallback;
import com.visioglobe.libVisioMove.VgIEnginePostDrawCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIPlaceListener;
import com.visioglobe.libVisioMove.VgIPlaceListenerRefPtr;
import com.visioglobe.libVisioMove.VgIRouteConstRefPtr;
import com.visioglobe.libVisioMove.VgIViewPoint;
import com.visioglobe.libVisioMove.VgLayerVector;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgQuery;
import com.visioglobe.libVisioMove.VgQuery.Operator;
import com.visioglobe.libVisioMove.VgSpatialList;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.FutureTask;
import javax.microedition.khronos.opengles.GL10;

public class MapFragment extends Fragment implements C1471a, C1528n, C1517f {
    public static final int INSTALL_MAP_BUNDLE_REQUEST = 5;
    public static final String SHOW_SEARCH_BAR = "com.navibees.sdk.show.search.bar";
    public static final String USE_BACK_ICON = "com.navibees.sdk.use.back.icon";

    /* renamed from: A */
    protected C1403b f288A = null;

    /* renamed from: B */
    protected boolean f289B = true;

    /* renamed from: C */
    protected boolean f290C = false;

    /* renamed from: D */
    protected C1472a f291D;

    /* renamed from: E */
    protected boolean f292E = true;

    /* renamed from: F */
    protected C1526b f293F;
    /* access modifiers changed from: private */

    /* renamed from: G */
    public MapHoverInterface f294G;

    /* renamed from: H */
    private BroadcastReceiver f295H = new C1375p();

    /* renamed from: a */
    private VgIPlaceListenerRefPtr f296a;

    /* renamed from: b */
    protected LocalBroadcastManager f297b;

    /* renamed from: c */
    private C1473b f298c;

    /* renamed from: d */
    private C1503e f299d;

    /* renamed from: e */
    private boolean f300e = false;

    /* renamed from: f */
    private Runnable f301f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C1509h f302g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public VgIEnginePostDrawCallbackRefPtr f303h;

    /* renamed from: i */
    private boolean f304i = false;
    public C1434g instructionView;
    public boolean isOutOfCoverageArea = false;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public AlertDialog f305j;

    /* renamed from: k */
    private List<BeaconNode> f306k = new ArrayList();

    /* renamed from: l */
    private List<BeaconNode> f307l = new ArrayList();

    /* renamed from: m */
    private Building f308m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public Floor f309n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public HashMap<String, C1453k> f310o = new HashMap<>();

    /* renamed from: p */
    private C1343a f311p = new C1354b0();

    /* renamed from: q */
    protected VgMySurfaceView f312q = null;

    /* renamed from: r */
    protected C1420e f313r = null;

    /* renamed from: s */
    protected C1482e f314s = null;

    /* renamed from: t */
    protected C1477c f315t = null;

    /* renamed from: u */
    protected C1454l f316u = null;

    /* renamed from: v */
    protected C1504f f317v = null;

    /* renamed from: w */
    protected C1491b f318w;

    /* renamed from: x */
    protected C1485a f319x;

    /* renamed from: y */
    protected C1491b f320y;

    /* renamed from: z */
    protected C1419d f321z;

    /* renamed from: com.navibees.maps.MapFragment$a */
    class C1351a implements Runnable {
        C1351a() {
        }

        public void run() {
            HashMap portalTypeStatusForUser = NaviBeesUtils.getPortalTypeStatusForUser(MapFragment.this.getContext());
            if (MapFragment.this.f313r != null && portalTypeStatusForUser != null) {
                if (((Boolean) portalTypeStatusForUser.get(Portal.PORTAL_TYPE[0])).booleanValue() || ((Boolean) portalTypeStatusForUser.get(Portal.PORTAL_TYPE[1])).booleanValue() || ((Boolean) portalTypeStatusForUser.get(Portal.PORTAL_TYPE[2])).booleanValue()) {
                    if (portalTypeStatusForUser.get(Portal.PORTAL_TYPE[0]) != null) {
                        MapFragment.this.f313r.mo28381a(!((Boolean) portalTypeStatusForUser.get(Portal.PORTAL_TYPE[0])).booleanValue());
                    }
                    if (portalTypeStatusForUser.get(Portal.PORTAL_TYPE[1]) != null) {
                        MapFragment.this.f313r.mo28406c(!((Boolean) portalTypeStatusForUser.get(Portal.PORTAL_TYPE[1])).booleanValue());
                    }
                    if (portalTypeStatusForUser.get(Portal.PORTAL_TYPE[2]) != null) {
                        MapFragment.this.f313r.mo28401b(!((Boolean) portalTypeStatusForUser.get(Portal.PORTAL_TYPE[2])).booleanValue());
                        return;
                    }
                    return;
                }
                MapFragment.this.f313r.mo28381a(false);
                MapFragment.this.f313r.mo28406c(false);
                MapFragment.this.f313r.mo28401b(false);
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$a0 */
    class C1352a0 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ IndoorLocation f323a;

        C1352a0(IndoorLocation indoorLocation) {
            this.f323a = indoorLocation;
        }

        public void run() {
            Building building = (Building) NaviBeesManager.getInstance(MapFragment.this.getActivity().getApplication()).getMetaDataManager().getBuildings().get(this.f323a.buildingId);
            if (building != null) {
                String str = ((VisioFloor) building.floors.get(this.f323a.floor)).vgfloorId;
                if (!TextUtils.isEmpty(str)) {
                    float[] fArr = new float[1];
                    float[] fArr2 = new float[1];
                    if (MapFragment.this.f313r.f521l.getHeightRangeForLayer(str, fArr, fArr2)) {
                        double d = (double) ((fArr[0] + fArr2[0]) / 2.0f);
                        IndoorLocation indoorLocation = this.f323a;
                        VgPosition vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, d);
                        MapFragment.this.f313r.mo28402b(vgPosition);
                    }
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$b */
    class C1353b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ VisioPOI f325a;

        C1353b(VisioPOI visioPOI) {
            this.f325a = visioPOI;
        }

        /* JADX WARNING: Removed duplicated region for block: B:28:0x008e  */
        /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r5 = this;
                com.navibees.core.model.metadata.json.VisioPOI r0 = r5.f325a
                if (r0 == 0) goto L_0x0091
                java.lang.String r0 = r0.vgId
                if (r0 == 0) goto L_0x0091
                com.navibees.maps.MapFragment r1 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r1 = r1.f313r
                r1.mo28418g(r0)
                boolean r0 = com.navibees.core.activity.MapHoverActivity.enableRoutingWhenOutOfVenue
                r1 = 0
                if (r0 == 0) goto L_0x002f
                java.lang.Object r0 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                boolean r2 = r0 instanceof com.navibees.core.model.metadata.json.POI
                if (r2 == 0) goto L_0x002f
                com.navibees.maps.MapFragment r2 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r2 = r2.f313r
                com.navibees.core.model.metadata.json.VisioPOI r0 = (com.navibees.core.model.metadata.json.VisioPOI) r0
                java.lang.String r0 = r0.vgId
                r2.mo28384a(r1, r0)
                com.navibees.maps.MapFragment r0 = com.navibees.maps.MapFragment.this
                java.lang.Object r1 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                com.navibees.core.model.metadata.json.VisioPOI r1 = (com.navibees.core.model.metadata.json.VisioPOI) r1
                r0.zoomToPOI(r1)
                goto L_0x0088
            L_0x002f:
                boolean r0 = com.navibees.core.activity.MapHoverActivity.enableRoutingWhenOutOfVenue
                if (r0 == 0) goto L_0x0088
                java.lang.Object r0 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                boolean r0 = r0 instanceof com.navibees.core.model.metadata.json.Facility
                if (r0 == 0) goto L_0x0088
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.lang.Object r2 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                com.navibees.core.model.metadata.json.VisioFacility r2 = (com.navibees.core.model.metadata.json.VisioFacility) r2
                java.util.List r2 = r2.getPois()
                if (r2 == 0) goto L_0x0088
                int r3 = r2.size()
                if (r3 <= 0) goto L_0x0088
                java.util.Iterator r3 = r2.iterator()
            L_0x0052:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x0064
                java.lang.Object r4 = r3.next()
                com.navibees.core.model.metadata.json.VisioPOI r4 = (com.navibees.core.model.metadata.json.VisioPOI) r4
                java.lang.String r4 = r4.vgId
                r0.add(r4)
                goto L_0x0052
            L_0x0064:
                com.navibees.maps.MapFragment r3 = com.navibees.maps.MapFragment.this
                com.navibees.visioglobe.f.h r3 = r3.f302g
                java.lang.String r3 = r3.mo28620a(r0)
                if (r3 == 0) goto L_0x0088
                com.navibees.maps.MapFragment r4 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r4 = r4.f313r
                if (r4 == 0) goto L_0x0088
                r4.mo28384a(r1, r3)
                com.navibees.maps.MapFragment r1 = com.navibees.maps.MapFragment.this
                int r0 = r0.indexOf(r3)
                java.lang.Object r0 = r2.get(r0)
                com.navibees.core.model.metadata.json.VisioPOI r0 = (com.navibees.core.model.metadata.json.VisioPOI) r0
                r1.zoomToPOI(r0)
            L_0x0088:
                com.navibees.maps.MapFragment r0 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r0 = r0.f313r
                if (r0 == 0) goto L_0x0091
                r0.mo28427n()
            L_0x0091:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.MapFragment.C1353b.run():void");
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$b0 */
    class C1354b0 implements C1343a {

        /* renamed from: com.navibees.maps.MapFragment$b0$a */
        class C1355a implements OnClickListener {
            C1355a(C1354b0 b0Var) {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }

        C1354b0() {
        }

        /* renamed from: a */
        public void mo28170a() {
            MapFragment mapFragment = MapFragment.this;
            if (mapFragment.isOutOfCoverageArea) {
                mapFragment.f305j.dismiss();
                if (MapFragment.this.f294G != null) {
                    try {
                        MapFragment.this.f294G.trackingLocationAndCompassChanged(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            MapFragment.this.isOutOfCoverageArea = false;
        }

        /* renamed from: b */
        public void mo28171b() {
            if (MapControls.SHOW_OUT_OF_COVERAGE_ALERT) {
                System.out.println("MapFragment: user out of venue");
                MapFragment mapFragment = MapFragment.this;
                mapFragment.isOutOfCoverageArea = true;
                mapFragment.stopAutomaticMode();
                if (MapFragment.this.f294G != null) {
                    try {
                        MapFragment.this.f294G.trackingLocationAndCompassChanged(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Builder builder = new Builder(MapFragment.this.getActivity());
                builder.setMessage(MapFragment.this.getString(C1164R.string.err_out_of_coverage_area));
                builder.setPositiveButton(MapFragment.this.getString(C1164R.string.f163ok), new C1355a(this));
                MapFragment.this.f305j = builder.create();
                MapFragment.this.f305j.show();
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$c */
    class C1356c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ VisioPOI f328a;

        /* renamed from: b */
        final /* synthetic */ List f329b;

        C1356c(VisioPOI visioPOI, List list) {
            this.f328a = visioPOI;
            this.f329b = list;
        }

        public void run() {
            VisioPOI visioPOI = this.f328a;
            if (visioPOI != null) {
                String str = visioPOI.vgId;
                if (str != null) {
                    MapFragment.this.f313r.mo28392a(str, this.f329b);
                    MapFragment.this.f313r.mo28427n();
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$c0 */
    private class C1357c0 extends VgIEnginePostDrawCallback {
        C1357c0() {
        }

        public void postDraw(VgIEngineContext vgIEngineContext) {
            if (MapFragment.this.f312q.getApplication().editEngine().isLoaded(new int[1], new int[1], new int[1], new int[1])) {
                MapFragment.this.m170i();
                MapFragment.this.f312q.getApplication().editEngine().removePostDrawCallback(new VgIEnginePostDrawCallbackRefPtr((VgIEnginePostDrawCallback) this));
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$d */
    class C1358d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ IndoorLocation f332a;

        C1358d(IndoorLocation indoorLocation) {
            this.f332a = indoorLocation;
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x00e2  */
        /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r10 = this;
                com.navibees.maps.MapFragment r0 = com.navibees.maps.MapFragment.this
                androidx.fragment.app.FragmentActivity r0 = r0.getActivity()
                android.app.Application r0 = r0.getApplication()
                com.navibees.core.NaviBeesManager r0 = com.navibees.core.NaviBeesManager.getInstance(r0)
                com.navibees.core.model.metadata.MetaDataManager r0 = r0.getMetaDataManager()
                androidx.collection.SimpleArrayMap r0 = r0.getBuildings()
                com.navibees.core.model.metadata.json.IndoorLocation r1 = r10.f332a
                java.lang.String r1 = r1.buildingId
                java.lang.Object r0 = r0.get(r1)
                com.navibees.core.model.metadata.json.Building r0 = (com.navibees.core.model.metadata.json.Building) r0
                if (r0 == 0) goto L_0x00e5
                java.util.List<com.navibees.core.model.metadata.json.Floor> r0 = r0.floors
                com.navibees.core.model.metadata.json.IndoorLocation r1 = r10.f332a
                int r1 = r1.floor
                java.lang.Object r0 = r0.get(r1)
                com.navibees.core.model.metadata.json.VisioFloor r0 = (com.navibees.core.model.metadata.json.VisioFloor) r0
                java.lang.String r0 = r0.vgfloorId
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x00e5
                r1 = 1
                float[] r2 = new float[r1]
                float[] r1 = new float[r1]
                com.navibees.maps.MapFragment r3 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r3 = r3.f313r
                com.visioglobe.libVisioMove.VgIMapModule r3 = r3.f521l
                boolean r0 = r3.getHeightRangeForLayer(r0, r2, r1)
                if (r0 == 0) goto L_0x00e5
                r0 = 0
                r2 = r2[r0]
                r1 = r1[r0]
                float r2 = r2 + r1
                r1 = 1073741824(0x40000000, float:2.0)
                float r2 = r2 / r1
                double r8 = (double) r2
                com.visioglobe.libVisioMove.VgPosition r1 = new com.visioglobe.libVisioMove.VgPosition
                com.navibees.core.model.metadata.json.IndoorLocation r2 = r10.f332a
                double r4 = r2.f1332x
                double r6 = r2.f1333y
                r3 = r1
                r3.<init>(r4, r6, r8)
                com.navibees.maps.MapFragment r2 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r2 = r2.f313r
                r2.mo28402b(r1)
                boolean r1 = com.navibees.core.activity.MapHoverActivity.enableRoutingWhenOutOfVenue
                if (r1 == 0) goto L_0x0083
                java.lang.Object r1 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                boolean r2 = r1 instanceof com.navibees.core.model.metadata.json.POI
                if (r2 == 0) goto L_0x0083
                com.navibees.maps.MapFragment r2 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r2 = r2.f313r
                com.navibees.core.model.metadata.json.VisioPOI r1 = (com.navibees.core.model.metadata.json.VisioPOI) r1
                java.lang.String r1 = r1.vgId
                r2.mo28384a(r0, r1)
                com.navibees.maps.MapFragment r0 = com.navibees.maps.MapFragment.this
                java.lang.Object r1 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                com.navibees.core.model.metadata.json.VisioPOI r1 = (com.navibees.core.model.metadata.json.VisioPOI) r1
                r0.zoomToPOI(r1)
                goto L_0x00dc
            L_0x0083:
                boolean r1 = com.navibees.core.activity.MapHoverActivity.enableRoutingWhenOutOfVenue
                if (r1 == 0) goto L_0x00dc
                java.lang.Object r1 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                boolean r1 = r1 instanceof com.navibees.core.model.metadata.json.Facility
                if (r1 == 0) goto L_0x00dc
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.lang.Object r2 = com.navibees.core.activity.MapHoverActivity.selectedOrigin
                com.navibees.core.model.metadata.json.VisioFacility r2 = (com.navibees.core.model.metadata.json.VisioFacility) r2
                java.util.List r2 = r2.getPois()
                if (r2 == 0) goto L_0x00dc
                int r3 = r2.size()
                if (r3 <= 0) goto L_0x00dc
                java.util.Iterator r3 = r2.iterator()
            L_0x00a6:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x00b8
                java.lang.Object r4 = r3.next()
                com.navibees.core.model.metadata.json.VisioPOI r4 = (com.navibees.core.model.metadata.json.VisioPOI) r4
                java.lang.String r4 = r4.vgId
                r1.add(r4)
                goto L_0x00a6
            L_0x00b8:
                com.navibees.maps.MapFragment r3 = com.navibees.maps.MapFragment.this
                com.navibees.visioglobe.f.h r3 = r3.f302g
                java.lang.String r3 = r3.mo28620a(r1)
                if (r3 == 0) goto L_0x00dc
                com.navibees.maps.MapFragment r4 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r4 = r4.f313r
                if (r4 == 0) goto L_0x00dc
                r4.mo28384a(r0, r3)
                com.navibees.maps.MapFragment r0 = com.navibees.maps.MapFragment.this
                int r1 = r1.indexOf(r3)
                java.lang.Object r1 = r2.get(r1)
                com.navibees.core.model.metadata.json.VisioPOI r1 = (com.navibees.core.model.metadata.json.VisioPOI) r1
                r0.zoomToPOI(r1)
            L_0x00dc:
                com.navibees.maps.MapFragment r0 = com.navibees.maps.MapFragment.this
                com.navibees.maps.e r0 = r0.f313r
                if (r0 == 0) goto L_0x00e5
                r0.mo28427n()
            L_0x00e5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.MapFragment.C1358d.run():void");
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$d0 */
    class C1359d0 extends VgIPlaceListener {

        /* renamed from: com.navibees.maps.MapFragment$d0$a */
        class C1360a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ VisioPOI f335a;

            C1360a(VisioPOI visioPOI) {
                this.f335a = visioPOI;
            }

            public void run() {
                if (MapFragment.this.f294G != null) {
                    MapFragment.this.f294G.onPOISelected(this.f335a);
                }
            }
        }

        public C1359d0() {
        }

        public void notifyMapDatabaseLoaded(VgIApplication vgIApplication) {
        }

        public void notifyPlaceSelected(VgIApplication vgIApplication, String str, VgPosition vgPosition) {
            StringBuilder sb = new StringBuilder();
            sb.append("PlaceSelected: ");
            sb.append(str);
            Log.m1173i("poipoi", sb.toString());
            C1454l lVar = MapFragment.this.f316u;
            if (lVar == null || !lVar.mo28502b(str) || MapFragment.this.f316u.mo28405c() == C1516a.eVgViewModeBuilding) {
                C1454l lVar2 = MapFragment.this.f316u;
                if (lVar2 != null && str != null && !lVar2.mo28502b(str) && MapFragment.this.f313r.mo28405c().equals(C1516a.eVgViewModeFloor)) {
                    MapFragment.this.getActivity().runOnUiThread(new C1360a(NaviBeesUtils.getVisioPOI(MapFragment.this.getActivity().getApplication(), str)));
                    return;
                }
                return;
            }
            Intent intent = new Intent("exploreRequest");
            intent.putExtra("view", C1516a.eVgViewModeBuilding);
            intent.putExtra("focusedBuilding", str);
            MapFragment.this.f297b.sendBroadcast(intent);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$e */
    class C1361e implements Runnable {

        /* renamed from: a */
        final /* synthetic */ IndoorLocation f337a;

        /* renamed from: b */
        final /* synthetic */ List f338b;

        C1361e(IndoorLocation indoorLocation, List list) {
            this.f337a = indoorLocation;
            this.f338b = list;
        }

        public void run() {
            Building building = (Building) NaviBeesManager.getInstance(MapFragment.this.getActivity().getApplication()).getMetaDataManager().getBuildings().get(this.f337a.buildingId);
            if (building != null) {
                String str = ((VisioFloor) building.floors.get(this.f337a.floor)).vgfloorId;
                if (!TextUtils.isEmpty(str)) {
                    float[] fArr = new float[1];
                    float[] fArr2 = new float[1];
                    if (MapFragment.this.f313r.f521l.getHeightRangeForLayer(str, fArr, fArr2)) {
                        double d = (double) ((fArr[0] + fArr2[0]) / 2.0f);
                        IndoorLocation indoorLocation = this.f337a;
                        VgPosition vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, d);
                        MapFragment.this.f313r.mo28389a(vgPosition, this.f338b);
                        MapFragment.this.f313r.mo28427n();
                    }
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$e0 */
    protected class C1362e0 implements C1524a {

        /* renamed from: a */
        String f340a;

        /* renamed from: b */
        C1502a f341b;

        /* renamed from: com.navibees.maps.MapFragment$e0$a */
        class C1363a implements OnClickListener {

            /* renamed from: a */
            final /* synthetic */ C1523l f343a;

            C1363a(C1523l lVar) {
                this.f343a = lVar;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.f343a.mo28612a(C1362e0.this.f341b)) {
                    Intent intent = new Intent(NaviBeesConstants.NETWORK_ACTIVITY_ACTION);
                    intent.putExtra(NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE, 1);
                    LocalBroadcastManager.getInstance(MapFragment.this.getContext()).sendBroadcast(intent);
                }
            }
        }

        /* renamed from: com.navibees.maps.MapFragment$e0$b */
        class C1364b implements OnClickListener {
            C1364b() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                MapFragment mapFragment = MapFragment.this;
                mapFragment.loadLocalMap(mapFragment.f291D);
            }
        }

        public C1362e0(String str) {
            this.f340a = str;
        }

        /* renamed from: a */
        public void mo28291a(C1523l lVar, C1525a aVar, C1501d dVar) {
            Intent intent = new Intent(NaviBeesConstants.NETWORK_ACTIVITY_ACTION);
            intent.putExtra(NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE, 2);
            LocalBroadcastManager.getInstance(MapFragment.this.getContext()).sendBroadcast(intent);
            this.f341b = null;
            if (C1525a.eSuccess != aVar || !dVar.mo28606c()) {
                MapFragment mapFragment = MapFragment.this;
                mapFragment.loadLocalMap(mapFragment.f291D);
                return;
            }
            while (!dVar.mo28603a()) {
                C1502a b = dVar.mo28605b();
                if (b == null) {
                    FragmentActivity activity = MapFragment.this.getActivity();
                    StringBuilder sb = new StringBuilder();
                    sb.append(MapFragment.this.getActivity().getResources().getString(C1164R.string.messageMapNotFoundInList));
                    sb.append(this.f340a);
                    Toast.makeText(activity, sb.toString(), 1).show();
                    MapFragment mapFragment2 = MapFragment.this;
                    mapFragment2.loadLocalMap(mapFragment2.f291D);
                } else if (b.f814a) {
                    String str = this.f340a;
                    if (str == null || str.isEmpty() || b.f816c.contentEquals(this.f340a)) {
                        if (MapFragment.this.f291D.f724d.length() == 0 || MapFragment.m162a(MapFragment.this.f291D.f724d, b.f817d)) {
                            this.f341b = b;
                            Builder builder = new Builder(MapFragment.this.getActivity());
                            builder.setMessage(C1164R.string.requestDownloadMapConfirmation);
                            builder.setPositiveButton(MapFragment.this.getResources().getString(17039370), new C1363a(lVar));
                            builder.setNegativeButton(MapFragment.this.getActivity().getResources().getString(17039360), new C1364b());
                            AlertDialog create = builder.create();
                            create.setCancelable(false);
                            create.show();
                        } else {
                            MapFragment mapFragment3 = MapFragment.this;
                            mapFragment3.loadLocalMap(mapFragment3.f291D);
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        public void mo28292a(C1523l lVar, C1525a aVar, String str) {
            String str2;
            Intent intent = new Intent(NaviBeesConstants.NETWORK_ACTIVITY_ACTION);
            intent.putExtra(NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE, 2);
            LocalBroadcastManager.getInstance(MapFragment.this.getContext()).sendBroadcast(intent);
            if (C1525a.eSuccess == aVar) {
                C1472a aVar2 = new C1472a();
                aVar2.f721a = lVar.mo28609a(str);
                StringBuilder sb = new StringBuilder();
                sb.append("http://license.visioglobe.com/renew/");
                sb.append(this.f341b.f819f);
                aVar2.f723c = sb.toString();
                aVar2.f722b = Long.valueOf(this.f341b.f818e);
                aVar2.f724d = this.f341b.f817d;
                if (MapFragment.this.verifyMyMap(aVar2)) {
                    Venue currentVenue = NaviBeesManager.getInstance(MapFragment.this.getActivity().getApplication()).getMetaDataManager().getCurrentVenue();
                    if (currentVenue != null) {
                        str2 = currentVenue.f1342id;
                    } else {
                        str2 = "";
                    }
                    aVar2.mo28551a(MapFragment.this.getActivity(), str2);
                    MapFragment mapFragment = MapFragment.this;
                    mapFragment.f291D = aVar2;
                    mapFragment.loadMap(mapFragment.f291D);
                }
            } else if (!NaviBeesUtils.isInternetConnectionOnline(MapFragment.this.getContext())) {
                Toast.makeText(MapFragment.this.getActivity().getApplicationContext(), MapFragment.this.getActivity().getResources().getString(C1164R.string.messageErrorMapDownloadInternet), 1).show();
            } else {
                Toast.makeText(MapFragment.this.getActivity().getApplicationContext(), MapFragment.this.getActivity().getResources().getString(C1164R.string.messageErrorMapDownload), 1).show();
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$f */
    class C1365f implements Runnable {
        C1365f() {
        }

        public void run() {
            MapFragment.this.f313r.mo28412d(false);
            MapFragment.this.f313r.mo28410d();
            MapFragment.this.f313r.mo28413e();
            MapFragment.this.f318w.show();
            MapFragment.this.f319x.show();
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$g */
    class C1366g implements Runnable {

        /* renamed from: a */
        final /* synthetic */ boolean[] f347a;

        C1366g(boolean[] zArr) {
            this.f347a = zArr;
        }

        public void run() {
            if (MapFragment.this.f313r != null) {
                C1481d a = C1481d.m455a();
                if (MapFragment.this.f313r.mo28419h()) {
                    a.mo28572d(NotificationCompat.CATEGORY_NAVIGATION, MapFragment.this.f320y);
                    MapFragment.this.f321z = null;
                }
                a.mo28572d(MapFragment.this.f288A.mo28336b(), MapFragment.this.f318w);
                a.mo28572d(MapFragment.this.f288A.mo28336b(), MapFragment.this.f319x);
                a.mo28572d(MapFragment.this.f288A.mo28336b(), MapFragment.this.f313r);
                a.mo28568b((C1519h) MapFragment.this.f288A);
                MapFragment mapFragment = MapFragment.this;
                mapFragment.f288A = null;
                mapFragment.f313r.mo28423j();
                MapFragment.this.f313r.mo28413e();
                MapFragment.this.f313r.mo28410d();
                MapFragment.this.f313r.mo28426m();
                MapFragment mapFragment2 = MapFragment.this;
                mapFragment2.f313r = null;
                mapFragment2.f314s = null;
                mapFragment2.f315t = null;
                mapFragment2.f316u = null;
            }
            this.f347a[0] = true;
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$h */
    class C1367h implements Runnable {
        C1367h() {
        }

        public void run() {
            Toast.makeText(MapFragment.this.getActivity().getApplicationContext(), C1164R.string.messageMapInstallationFailed, 1).show();
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$i */
    class C1368i implements Runnable {

        /* renamed from: a */
        final /* synthetic */ List f350a;

        /* renamed from: b */
        final /* synthetic */ MapHoverInterface f351b;

        C1368i(List list, MapHoverInterface mapHoverInterface) {
            this.f350a = list;
            this.f351b = mapHoverInterface;
        }

        public void run() {
            MapFragment.this.handleAvoidedPortals();
            MapFragment.this.f313r.mo28394a(this.f350a, this.f351b, false);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$j */
    class C1369j implements Runnable {

        /* renamed from: a */
        final /* synthetic */ List f353a;

        /* renamed from: b */
        final /* synthetic */ ArrayList f354b;

        /* renamed from: c */
        final /* synthetic */ MapHoverInterface f355c;

        C1369j(List list, ArrayList arrayList, MapHoverInterface mapHoverInterface) {
            this.f353a = list;
            this.f354b = arrayList;
            this.f355c = mapHoverInterface;
        }

        public void run() {
            MapFragment.this.handleAvoidedPortals();
            MapFragment.this.f313r.mo28396a(this.f353a, this.f354b, this.f355c, false);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$k */
    class C1370k implements Runnable {

        /* renamed from: a */
        final /* synthetic */ VisioPOI f357a;

        C1370k(VisioPOI visioPOI) {
            this.f357a = visioPOI;
        }

        public void run() {
            MapFragment.this.f313r.mo28418g(this.f357a.vgId);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$l */
    class C1371l implements Runnable {

        /* renamed from: a */
        final /* synthetic */ List f359a;

        /* renamed from: b */
        final /* synthetic */ MapHoverInterface f360b;

        C1371l(List list, MapHoverInterface mapHoverInterface) {
            this.f359a = list;
            this.f360b = mapHoverInterface;
        }

        public void run() {
            MapFragment.this.handleAvoidedPortals();
            MapFragment.this.f313r.mo28394a(this.f359a, this.f360b, true);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$m */
    class C1372m implements Runnable {

        /* renamed from: a */
        final /* synthetic */ List f362a;

        /* renamed from: b */
        final /* synthetic */ ArrayList f363b;

        /* renamed from: c */
        final /* synthetic */ MapHoverInterface f364c;

        C1372m(List list, ArrayList arrayList, MapHoverInterface mapHoverInterface) {
            this.f362a = list;
            this.f363b = arrayList;
            this.f364c = mapHoverInterface;
        }

        public void run() {
            MapFragment.this.handleAvoidedPortals();
            MapFragment.this.f313r.mo28396a(this.f362a, this.f363b, this.f364c, true);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$n */
    class C1373n implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f366a;

        /* renamed from: b */
        final /* synthetic */ IndoorLocation f367b;

        /* renamed from: c */
        final /* synthetic */ MapHoverInterface f368c;

        C1373n(String str, IndoorLocation indoorLocation, MapHoverInterface mapHoverInterface) {
            this.f366a = str;
            this.f367b = indoorLocation;
            this.f368c = mapHoverInterface;
        }

        public void run() {
            if (!TextUtils.isEmpty(this.f366a)) {
                float[] fArr = new float[1];
                float[] fArr2 = new float[1];
                if (MapFragment.this.f313r.f521l.getHeightRangeForLayer(this.f366a, fArr, fArr2)) {
                    double d = (double) ((fArr[0] + fArr2[0]) / 2.0f);
                    IndoorLocation indoorLocation = this.f367b;
                    VgPosition vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, d);
                    MapFragment.this.handleAvoidedPortals();
                    MapFragment.this.f313r.mo28386a(vgPosition, this.f368c, false);
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$o */
    class C1374o implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f370a;

        /* renamed from: b */
        final /* synthetic */ IndoorLocation f371b;

        /* renamed from: c */
        final /* synthetic */ ArrayList f372c;

        /* renamed from: d */
        final /* synthetic */ MapHoverInterface f373d;

        C1374o(String str, IndoorLocation indoorLocation, ArrayList arrayList, MapHoverInterface mapHoverInterface) {
            this.f370a = str;
            this.f371b = indoorLocation;
            this.f372c = arrayList;
            this.f373d = mapHoverInterface;
        }

        public void run() {
            if (!TextUtils.isEmpty(this.f370a)) {
                float[] fArr = new float[1];
                float[] fArr2 = new float[1];
                if (MapFragment.this.f313r.f521l.getHeightRangeForLayer(this.f370a, fArr, fArr2)) {
                    double d = (double) ((fArr[0] + fArr2[0]) / 2.0f);
                    IndoorLocation indoorLocation = this.f371b;
                    VgPosition vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, d);
                    MapFragment.this.handleAvoidedPortals();
                    MapFragment.this.f313r.mo28388a(vgPosition, this.f372c, this.f373d, false);
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$p */
    class C1375p extends BroadcastReceiver {
        C1375p() {
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (MapFragment.this.f294G != null) {
                    C1516a aVar = (C1516a) intent.getSerializableExtra("view");
                    String stringExtra = intent.getStringExtra("focusedFloor");
                    String stringExtra2 = intent.getStringExtra("focusedBuilding");
                    SimpleArrayMap buildings = NaviBeesManager.getInstance(MapFragment.this.getActivity().getApplication()).getMetaDataManager().getBuildings();
                    int i = 0;
                    while (true) {
                        if (i >= buildings.size()) {
                            break;
                        }
                        Building building = (Building) buildings.get((String) buildings.keyAt(i));
                        if (building.providerId != null && building.providerId.equals(stringExtra2)) {
                            MapFragment.this.f294G.presentedBuildingChanged(building);
                            break;
                        }
                        i++;
                    }
                    Building currentBuilding = NaviBeesManager.getInstance(MapFragment.this.getActivity().getApplication()).getMetaDataManager().getCurrentBuilding();
                    if (currentBuilding != null && stringExtra != null && aVar != null && currentBuilding.floors != null) {
                        Iterator it = currentBuilding.floors.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            VisioFloor visioFloor = (VisioFloor) ((Floor) it.next());
                            if (visioFloor.vgfloorId.equals(stringExtra)) {
                                MapFragment.this.f294G.presentedFloorChanged(visioFloor);
                                break;
                            }
                        }
                    }
                    if (aVar == C1516a.eVgViewModeBuilding) {
                        MapFragment.this.f294G.updateBuildingPickerState(false);
                    } else if (aVar == C1516a.eVgViewModeFloor) {
                        MapFragment.this.f294G.updateBuildingPickerState(false);
                        MapFragment.this.f294G.updateFloorPickerState(false);
                    }
                    float floatExtra = intent.getFloatExtra("focusedHeading", -1.0f);
                    if (((int) floatExtra) != -1) {
                        MapFragment.this.f294G.updateCompass(floatExtra);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$q */
    class C1376q implements Runnable {
        C1376q() {
        }

        public void run() {
            Toast.makeText(MapFragment.this.getActivity().getApplicationContext(), "There is no embedded or downloaded map for this venue", 1).show();
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$r */
    class C1377r implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C1472a f377a;

        C1377r(C1472a aVar) {
            this.f377a = aVar;
        }

        public void run() {
            MapFragment.this.createLocalObjects();
            MapFragment.this.f313r.mo28411d(this.f377a.f721a);
            MapFragment.this.f313r.mo28416f(this.f377a.f723c);
            MapFragment.this.f313r.mo28364a(this.f377a.f722b.longValue());
            if (MapFragment.this.f313r.mo28431r()) {
                String str = "NBVMapFragment";
                android.util.Log.i(str, "set layer lod factors");
                android.util.Log.i(str, "Override the default view point");
                android.util.Log.i(str, ">>>> setupAppBlocks");
                MapFragment.this.setupAppBlocks(this.f377a);
                android.util.Log.i(str, "<<<< setupAppBlocks");
                if (MapFragment.this.f289B) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("VisioMove version:");
                    sb.append(MapFragment.this.f312q.getApplication().editEngine().editLicenseManager().getVersion());
                    android.util.Log.i(str, sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("VisioMove revision:");
                    sb2.append(MapFragment.this.f312q.getApplication().editEngine().editLicenseManager().getRevision());
                    android.util.Log.i(str, sb2.toString());
                    MapFragment.this.f289B = false;
                }
                MapFragment mapFragment = MapFragment.this;
                mapFragment.f303h = new VgIEnginePostDrawCallbackRefPtr((VgIEnginePostDrawCallback) new C1357c0());
                MapFragment.this.f312q.getApplication().editEngine().addPostDrawCallback(MapFragment.this.f303h);
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$s */
    class C1378s implements Runnable {
        C1378s() {
        }

        public void run() {
            C1491b bVar = MapFragment.this.f318w;
            if (bVar != null && !bVar.isVisible()) {
                MapFragment.this.f318w.show();
            }
            C1485a aVar = MapFragment.this.f319x;
            if (aVar != null && !aVar.isVisible()) {
                MapFragment.this.f319x.show();
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$t */
    class C1379t implements Runnable {
        C1379t() {
        }

        public void run() {
            C1491b bVar = MapFragment.this.f318w;
            if (bVar != null && bVar.isVisible()) {
                MapFragment.this.f318w.hide();
            }
            C1485a aVar = MapFragment.this.f319x;
            if (aVar != null && aVar.isVisible()) {
                MapFragment.this.f319x.hide();
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$u */
    class C1380u implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Collection f381a;

        /* renamed from: b */
        final /* synthetic */ int f382b;

        C1380u(Collection collection, int i) {
            this.f381a = collection;
            this.f382b = i;
        }

        public void run() {
            for (BeaconNodeConfiguration beaconNodeConfiguration : this.f381a) {
                BeaconNode beaconNode = new BeaconNode(beaconNodeConfiguration.major, beaconNodeConfiguration.minor);
                beaconNode.location = beaconNodeConfiguration.location;
                beaconNode.UUID = beaconNodeConfiguration.uuid;
                StringBuilder sb = new StringBuilder();
                sb.append("Major: ");
                sb.append(beaconNode.major);
                sb.append("\nMinor: ");
                sb.append(beaconNode.minor);
                sb.append("\nUUID: ");
                sb.append(beaconNode.UUID);
                MapFragment.this.m160a(beaconNode, this.f382b, beaconNode.minor, sb.toString());
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$v */
    class C1381v implements Runnable {
        C1381v() {
        }

        public void run() {
            for (String str : MapFragment.this.f310o.keySet()) {
                ((C1453k) MapFragment.this.f310o.get(str)).mo28474a();
            }
            MapFragment.this.f310o.clear();
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$w */
    class C1382w implements Runnable {

        /* renamed from: a */
        final /* synthetic */ int f385a;

        C1382w(int i) {
            this.f385a = i;
        }

        public void run() {
            HashMap b = MapFragment.this.f310o;
            StringBuilder sb = new StringBuilder();
            String str = "";
            sb.append(str);
            sb.append(this.f385a);
            ((C1453k) b.get(sb.toString())).mo28474a();
            HashMap b2 = MapFragment.this.f310o;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(this.f385a);
            b2.remove(sb2.toString());
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$x */
    class C1383x implements Runnable {

        /* renamed from: a */
        final /* synthetic */ IndoorLocation f387a;

        /* renamed from: b */
        final /* synthetic */ Floor f388b;

        /* renamed from: c */
        final /* synthetic */ int f389c;

        /* renamed from: d */
        final /* synthetic */ String f390d;

        C1383x(IndoorLocation indoorLocation, Floor floor, int i, String str) {
            this.f387a = indoorLocation;
            this.f388b = floor;
            this.f389c = i;
            this.f390d = str;
        }

        public void run() {
            IndoorLocation convertUTMToLatLong = NaviBeesUtils.convertUTMToLatLong(this.f387a);
            String str = ((VisioFloor) this.f388b).vgfloorId;
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("rfkamd: ");
            sb.append(str);
            printStream.println(sb.toString());
            VgPosition vgPosition = new VgPosition();
            vgPosition.setMXOrLongitude(convertUTMToLatLong.f1332x);
            vgPosition.setMYOrLatitude(convertUTMToLatLong.f1333y);
            HashMap b = MapFragment.this.f310o;
            StringBuilder sb2 = new StringBuilder();
            String str2 = "";
            sb2.append(str2);
            sb2.append(this.f389c);
            if (b.get(sb2.toString()) == null) {
                C1453k kVar = new C1453k(MapFragment.this.getActivity(), MapFragment.this.f312q);
                if (MapFragment.this.f302g != null) {
                    VgMySurfaceView vgMySurfaceView = MapFragment.this.f312q;
                    kVar.mo28478a(str, vgMySurfaceView, kVar.mo28473a(vgPosition, kVar.mo28472a(vgMySurfaceView, this.f389c)));
                    String str3 = this.f390d;
                    if (str3 != null) {
                        kVar.mo28477a(str3);
                    }
                    HashMap b2 = MapFragment.this.f310o;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str2);
                    sb3.append(this.f389c);
                    b2.put(sb3.toString(), kVar);
                }
                return;
            }
            HashMap b3 = MapFragment.this.f310o;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(this.f389c);
            C1453k kVar2 = (C1453k) b3.get(sb4.toString());
            if (kVar2 != null) {
                kVar2.mo28475a(vgPosition);
            }
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$y */
    class C1384y implements Runnable {

        /* renamed from: a */
        final /* synthetic */ BeaconNode f392a;

        /* renamed from: b */
        final /* synthetic */ Floor f393b;

        /* renamed from: c */
        final /* synthetic */ int f394c;

        /* renamed from: d */
        final /* synthetic */ String f395d;

        C1384y(BeaconNode beaconNode, Floor floor, int i, String str) {
            this.f392a = beaconNode;
            this.f393b = floor;
            this.f394c = i;
            this.f395d = str;
        }

        public void run() {
            IndoorLocation convertUTMToLatLong = NaviBeesUtils.convertUTMToLatLong(this.f392a.location);
            String str = ((VisioFloor) this.f393b).vgfloorId;
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("rfkamd: ");
            sb.append(str);
            printStream.println(sb.toString());
            VgPosition vgPosition = new VgPosition();
            vgPosition.setMXOrLongitude(convertUTMToLatLong.f1332x);
            vgPosition.setMYOrLatitude(convertUTMToLatLong.f1333y);
            HashMap b = MapFragment.this.f310o;
            StringBuilder sb2 = new StringBuilder();
            String str2 = "";
            sb2.append(str2);
            sb2.append(this.f394c);
            if (b.get(sb2.toString()) == null) {
                C1453k kVar = new C1453k(MapFragment.this.getActivity(), MapFragment.this.f312q);
                if (MapFragment.this.f302g != null) {
                    VgMySurfaceView vgMySurfaceView = MapFragment.this.f312q;
                    kVar.mo28478a(str, vgMySurfaceView, kVar.mo28473a(vgPosition, kVar.mo28472a(vgMySurfaceView, this.f394c)));
                    String str3 = this.f395d;
                    if (str3 != null) {
                        kVar.mo28477a(str3);
                    }
                    HashMap b2 = MapFragment.this.f310o;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str2);
                    sb3.append(this.f394c);
                    b2.put(sb3.toString(), kVar);
                }
                return;
            }
            HashMap b3 = MapFragment.this.f310o;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(this.f394c);
            ((C1453k) b3.get(sb4.toString())).mo28475a(vgPosition);
        }
    }

    /* renamed from: com.navibees.maps.MapFragment$z */
    class C1385z implements Runnable {

        /* renamed from: a */
        final /* synthetic */ BeaconNode f397a;

        /* renamed from: b */
        final /* synthetic */ int f398b;

        /* renamed from: c */
        final /* synthetic */ int f399c;

        /* renamed from: d */
        final /* synthetic */ String f400d;

        C1385z(BeaconNode beaconNode, int i, int i2, String str) {
            this.f397a = beaconNode;
            this.f398b = i;
            this.f399c = i2;
            this.f400d = str;
        }

        public void run() {
            IndoorLocation convertUTMToLatLong = NaviBeesUtils.convertUTMToLatLong(this.f397a.location);
            String str = ((VisioFloor) MapFragment.this.f309n).vgfloorId;
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("smuh: ");
            sb.append(str);
            printStream.println(sb.toString());
            VgPosition vgPosition = new VgPosition();
            vgPosition.setMXOrLongitude(convertUTMToLatLong.f1332x);
            vgPosition.setMYOrLatitude(convertUTMToLatLong.f1333y);
            HashMap b = MapFragment.this.f310o;
            StringBuilder sb2 = new StringBuilder();
            String str2 = "";
            sb2.append(str2);
            sb2.append(this.f398b);
            if (b.get(sb2.toString()) == null) {
                C1453k kVar = new C1453k(MapFragment.this.getActivity(), MapFragment.this.f312q);
                if (MapFragment.this.f302g != null) {
                    VgMySurfaceView vgMySurfaceView = MapFragment.this.f312q;
                    kVar.mo28478a(str, vgMySurfaceView, kVar.mo28473a(vgPosition, kVar.mo28472a(vgMySurfaceView, this.f399c)));
                    String str3 = this.f400d;
                    if (str3 != null) {
                        kVar.mo28477a(str3);
                    }
                    HashMap b2 = MapFragment.this.f310o;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str2);
                    sb3.append(this.f398b);
                    b2.put(sb3.toString(), kVar);
                }
                return;
            }
            HashMap b3 = MapFragment.this.f310o;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(this.f398b);
            ((C1453k) b3.get(sb4.toString())).mo28475a(vgPosition);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m170i() {
        this.f300e = true;
        Runnable runnable = this.f301f;
        if (runnable != null) {
            runnable.run();
            this.f301f = null;
        }
        MapHoverInterface mapHoverInterface = this.f294G;
        if (mapHoverInterface != null) {
            mapHoverInterface.notifyMapIsLoaded();
        }
        MetaDataManager metaDataManager = NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager();
        String outsideBuildingId = metaDataManager.getOutsideBuildingId();
        if (!(metaDataManager.getOutsideFloorIndex() == Integer.MIN_VALUE || outsideBuildingId == null)) {
            mo28206a((Building) metaDataManager.getBuildings().get(outsideBuildingId));
        }
    }

    public void addActiveBeaconPoints(List<BeaconNode> list, int i) {
        this.f306k.addAll(list);
        PositionManager.showMarkers = true;
        for (BeaconNode beaconNode : list) {
            StringBuilder sb = new StringBuilder();
            sb.append("Major: ");
            sb.append(beaconNode.major);
            sb.append("\nMinor: ");
            sb.append(beaconNode.minor);
            sb.append("\nUUID: ");
            sb.append(beaconNode.UUID);
            m160a(beaconNode, i, beaconNode.minor, sb.toString());
        }
    }

    public void addBeaconPoints(Collection<BeaconNodeConfiguration> collection, int i) {
        PositionManager.showMarkers = true;
        this.f312q.queueEvent(new C1380u(collection, i));
    }

    public void addInActiveBeaconPoints(List<BeaconNode> list, int i) {
        this.f307l.addAll(list);
        PositionManager.showMarkers = true;
        for (BeaconNode beaconNode : list) {
            StringBuilder sb = new StringBuilder();
            sb.append("Major: ");
            sb.append(beaconNode.major);
            sb.append("\nMinor: ");
            sb.append(beaconNode.minor);
            sb.append("\nUUID: ");
            sb.append(beaconNode.UUID);
            m160a(beaconNode, i, beaconNode.minor, sb.toString());
        }
    }

    public void addMarkerAtLocation(IndoorLocation indoorLocation, int i, Floor floor, String str) {
        if (getIsMapLoaded() && PositionManager.showMarkers) {
            VgMySurfaceView vgMySurfaceView = this.f312q;
            C1383x xVar = new C1383x(indoorLocation, floor, i, str);
            vgMySurfaceView.queueEvent(xVar);
        }
    }

    public void calculateRouteTo(String str) {
        IndoorLocation lastLocationCoordinateLatLong = NaviBeesManager.getInstance(getActivity().getApplication()).getPositionManager().getLastLocationCoordinateLatLong();
        VgPosition vgPosition = new VgPosition(lastLocationCoordinateLatLong.f1332x, lastLocationCoordinateLatLong.f1333y, 10.0d);
        this.f313r.mo28383a(0, vgPosition);
        this.f313r.mo28384a(7, str);
        this.f313r.mo28398b();
    }

    public void clear() {
        showLocation();
        this.f313r.mo28412d(false);
    }

    public void clearCustomMarkers() {
        if (this.f302g != null) {
            this.f312q.queueEvent(new C1381v());
        }
    }

    public void clearMarkers() {
        C1420e eVar = this.f313r;
        if (eVar != null) {
            eVar.mo28413e();
            this.f313r.mo28410d();
        }
    }

    public void configureStackedLayerAndCameraHandler() {
        C1454l lVar = this.f316u;
        if (lVar != null) {
            lVar.mo28495a(false, false);
        }
    }

    public void createLocalObjects() {
        this.f314s = new C1482e(getActivity(), this.f312q);
        this.f315t = new C1477c(this.f312q);
        this.f313r = new C1420e(getActivity(), this.f314s, this.f312q);
    }

    public boolean createRouteObjects(VgIRouteConstRefPtr vgIRouteConstRefPtr) {
        hideLocation();
        this.f313r.mo28412d(true);
        return false;
    }

    public boolean getIsMapLoaded() {
        return this.f300e;
    }

    public IndoorLocation getLastLocation() {
        PositionManager positionManager = NaviBeesManager.getInstance(getActivity().getApplication()).getPositionManager();
        if (positionManager != null && positionManager.getLastLocationCoordinateLatLong() != null) {
            return positionManager.getLastLocationCoordinateLatLong();
        }
        C1403b bVar = this.f288A;
        if (bVar.f452n != null) {
            return bVar.mo28339e();
        }
        return null;
    }

    public String getPathStorage() {
        File externalFilesDir = getActivity().getExternalFilesDir(null);
        if (externalFilesDir != null) {
            return externalFilesDir.toString();
        }
        return "";
    }

    public String getPathTemp() {
        File externalCacheDir = getActivity().getExternalCacheDir();
        if (externalCacheDir != null) {
            return externalCacheDir.toString();
        }
        return "";
    }

    public void gotoPlaceId(String str) {
        Intent intent = new Intent("exploreRequest");
        intent.putExtra("view", C1516a.eVgViewModeFloor);
        intent.putExtra("focusedPlace", str);
        this.f297b.sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo28237h() {
        String str;
        String str2;
        String str3;
        C1472a aVar = new C1472a();
        aVar.f722b = Long.valueOf(291268198);
        String str4 = "";
        aVar.f721a = str4;
        Venue currentVenue = NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            str = currentVenue.mapProvider.version;
        } else {
            str = str4;
        }
        aVar.f724d = str;
        if (currentVenue != null) {
            str2 = currentVenue.f1342id;
        } else {
            str2 = str4;
        }
        this.f291D = C1472a.m438b(getActivity(), str2);
        C1472a aVar2 = this.f291D;
        if (aVar2 == null || m162a(aVar2.f724d, aVar.f724d)) {
            this.f291D = aVar;
        }
        this.f293F = new C1526b();
        this.f293F.f869a = "https://mapserver.visioglobe.com/";
        if (currentVenue != null) {
            str3 = currentVenue.mapProvider.hashString;
        } else {
            str3 = str4;
        }
        C1526b bVar = this.f293F;
        bVar.f872d = str3;
        bVar.f873e = "descriptor.json";
        bVar.f874f = new C1362e0(str4);
    }

    public void handleAvoidedPortals() {
        this.f312q.queueEvent(new C1351a());
    }

    public void hide() {
    }

    public void hideLocation() {
        this.f312q.queueEvent(new C1379t());
    }

    public void initiateRouting(VisioPOI visioPOI, MapHoverInterface mapHoverInterface) {
        if (visioPOI != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(visioPOI.vgId);
            this.f312q.queueEvent(new C1368i(arrayList, mapHoverInterface));
        }
    }

    public void layerChangedTo(C1515e eVar, String str, String str2) {
    }

    public void layerWillChangeFrom(C1515e eVar, String str, String str2) {
    }

    public void loadFromNetwork() {
        if (this.f317v == null) {
            C1504f fVar = new C1504f(this, this.f312q.getApplication(), this.f293F, getPathStorage(), getPathTemp());
            this.f317v = fVar;
        }
        if (!this.f317v.mo28611a()) {
            Toast.makeText(getActivity().getApplicationContext(), getActivity().getResources().getString(C1164R.string.messageErrorListDownload), 1).show();
            return;
        }
        Intent intent = new Intent(NaviBeesConstants.NETWORK_ACTIVITY_ACTION);
        intent.putExtra(NaviBeesConstants.NETWORK_ACTIVITY_ACTION_TYPE, 1);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }

    public void loadLocalMap(C1472a aVar) {
        if (aVar.f721a.length() > 0) {
            loadMap(aVar);
            return;
        }
        Log.m1171d("NBVMapFragment", "No Map Available");
        getActivity().runOnUiThread(new C1376q());
    }

    public void loadMap(C1472a aVar) {
        this.f312q.mo28536a(this);
        this.f312q.setVisibility(0);
        this.f312q.queueEvent(new C1377r(aVar));
    }

    public void modeDidChange(C1515e eVar, C1516a aVar) {
    }

    public void modeWillChange(C1515e eVar, C1516a aVar) {
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.f304i = bundle.getBoolean("com.navibees.location.provider.state", false);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        if (i != 5) {
            C1504f fVar = this.f317v;
            if (fVar != null) {
                fVar.mo28610a(i, i2, intent);
            }
        } else if (i2 == -1) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferenceFile", 0);
            this.f291D.f721a = sharedPreferences.getString("vg_config_xml", "vg_config.xml");
            if (verifyMyMap(this.f291D)) {
                Venue currentVenue = NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getCurrentVenue();
                if (currentVenue != null) {
                    str = currentVenue.f1342id;
                } else {
                    str = "";
                }
                this.f291D.mo28551a(getActivity(), str);
                loadMap(this.f291D);
            }
        } else {
            Log.m1171d("NBVMapFragment", "Map installation failed");
            getActivity().runOnUiThread(new C1367h());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f297b = LocalBroadcastManager.getInstance(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getArguments() != null) {
            getArguments().getBoolean(SHOW_SEARCH_BAR, true);
            getArguments().getBoolean(USE_BACK_ICON, false);
        }
        return layoutInflater.inflate(C1164R.layout.fragment_nbvmap, viewGroup, false);
    }

    public void onDestroy() {
        if (this.f312q != null) {
            VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr = this.f303h;
            if (vgIEnginePostDrawCallbackRefPtr != null && vgIEnginePostDrawCallbackRefPtr.isValid()) {
                this.f312q.getApplication().editEngine().removePostDrawCallback(this.f303h);
                this.f303h.set(null);
                this.f303h = null;
            }
        }
        this.f294G = null;
        C1420e eVar = this.f313r;
        if (eVar != null) {
            eVar.mo28423j();
            this.f313r.mo28413e();
            this.f313r.mo28410d();
        }
        C1481d a = C1481d.m455a();
        C1403b bVar = this.f288A;
        if (bVar != null) {
            bVar.mo28334a();
            a.mo28572d(this.f288A.mo28336b(), this.f318w);
            a.mo28572d(this.f288A.mo28336b(), this.f319x);
            a.mo28572d(this.f288A.mo28336b(), this.f313r);
            a.mo28568b((C1519h) this.f288A);
            C1420e eVar2 = this.f313r;
            if (eVar2 != null) {
                if (eVar2.mo28417g()) {
                    C1732libVisioMove.castToIMapModule(this.f312q.getApplication().editModuleManager().queryModule("Map")).removeListener(this.f296a);
                    this.f296a = null;
                }
                if (this.f313r.mo28419h()) {
                    C1491b bVar2 = this.f320y;
                    String str = NotificationCompat.CATEGORY_NAVIGATION;
                    a.mo28572d(str, bVar2);
                    this.f321z = null;
                    a.mo28572d(str, this.f320y);
                    this.f321z = null;
                }
            }
            this.f288A.mo28334a();
            this.f288A.mo28342h();
            this.f288A = null;
        }
        C1491b bVar3 = this.f318w;
        if (bVar3 != null) {
            bVar3.release();
            this.f318w = null;
        }
        C1491b bVar4 = this.f320y;
        if (bVar4 != null) {
            bVar4.release();
            this.f320y = null;
        }
        C1485a aVar = this.f319x;
        if (aVar != null) {
            aVar.release();
            this.f319x = null;
        }
        C1454l lVar = this.f316u;
        if (lVar != null) {
            lVar.release();
            this.f316u = null;
        }
        C1420e eVar3 = this.f313r;
        if (eVar3 != null) {
            eVar3.release();
            this.f313r = null;
        }
        C1482e eVar4 = this.f314s;
        if (eVar4 != null) {
            eVar4.mo28575a();
            this.f314s = null;
        }
        C1477c cVar = this.f315t;
        if (cVar != null) {
            cVar.mo28560a();
            this.f315t = null;
        }
        C1504f fVar = this.f317v;
        if (fVar != null) {
            fVar.mo28614b();
            this.f317v = null;
        }
        C1473b bVar5 = this.f298c;
        if (bVar5 != null) {
            bVar5.mo28552a();
            this.f298c = null;
        }
        C1526b bVar6 = this.f293F;
        if (bVar6 != null) {
            bVar6.f874f = null;
            this.f293F = null;
        }
        this.f312q.getApplication().editEngine().editDatabase().unloadConfiguration();
        ((ViewGroup) this.f312q.getParent()).removeView(this.f312q);
        this.f312q = null;
        this.f291D = null;
        super.onDestroy();
    }

    public void onMapLoaded(Runnable runnable) {
        toggleMapPoints();
        toggleMapPoints();
        if (this.f300e) {
            runnable.run();
        } else {
            this.f301f = runnable;
        }
    }

    public void onPause() {
        super.onPause();
        VgMySurfaceView vgMySurfaceView = this.f312q;
        if (vgMySurfaceView != null) {
            vgMySurfaceView.pauseRendering();
            this.f312q.onPause();
        }
        this.f297b.unregisterReceiver(this.f295H);
        C1403b bVar = this.f288A;
        if (bVar != null) {
            bVar.mo28340f();
        }
    }

    public void onResume() {
        super.onResume();
        VgMySurfaceView vgMySurfaceView = this.f312q;
        if (vgMySurfaceView != null) {
            vgMySurfaceView.resumeRendering();
            this.f312q.onResume();
        }
        this.f297b.registerReceiver(this.f295H, new IntentFilter("explore"));
        C1403b bVar = this.f288A;
        if (bVar != null) {
            bVar.mo28341g();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.navibees.location.provider.state", this.f304i);
    }

    public void onStart() {
        super.onStart();
        C1403b bVar = this.f288A;
        if (bVar != null && this.f304i) {
            bVar.mo28338d();
        }
    }

    public void onStop() {
        super.onStop();
        C1403b bVar = this.f288A;
        if (bVar != null) {
            bVar.mo28334a();
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        C1454l lVar = this.f316u;
        if (lVar != null) {
            lVar.mo28504c((long) i, (long) i2);
        }
        configureStackedLayerAndCameraHandler();
    }

    public void onSurfaceDestroyed() {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f312q = (VgMySurfaceView) view.findViewById(C1164R.C1167id.VgSurfaceView);
        m164b(true);
    }

    public void release() {
    }

    public void selectBuildingAndFloor(Building building, Floor floor) {
        if (getIsMapLoaded()) {
            mo28207a(floor);
        }
    }

    public void setBuildingAndFloor(String str, int i) {
        this.f308m = m157a(str);
        this.f309n = m158a(this.f308m, i);
        selectBuildingAndFloor(this.f308m, this.f309n);
    }

    public void setIsCompassActive(boolean z) {
        C1403b bVar = this.f288A;
        if (bVar != null) {
            bVar.f449k = z;
        }
    }

    public void setLocationProvider(boolean z) {
        if (z) {
            C1481d a = C1481d.m455a();
            Iterator it = a.mo28565a(false).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                a.mo28570c(str).mo28338d();
                if (a.mo28570c(str) instanceof C1403b) {
                    ((C1403b) a.mo28570c(str)).mo28335a(this.f311p);
                }
            }
        } else {
            C1481d a2 = C1481d.m455a();
            Iterator it2 = a2.mo28565a(false).iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                a2.mo28570c(str2).mo28334a();
                if (a2.mo28570c(str2) instanceof C1403b) {
                    ((C1403b) a2.mo28570c(str2)).mo28335a((C1343a) null);
                }
            }
        }
        this.f304i = z;
    }

    public void setMapHoverInterface(MapHoverInterface mapHoverInterface) {
        this.f294G = mapHoverInterface;
    }

    public void setupAppBlocks(C1472a aVar) {
        if (this.f313r.mo28425l()) {
            VgLayerVector editLayers = this.f312q.getApplication().editEngine().editLayerManager().editLayers();
            if (editLayers.size() > 0) {
                if (editLayers.size() > 1) {
                    this.f313r.mo28369a((C1517f) this);
                }
                this.f298c = new C1473b(getActivity());
                VgMySurfaceView vgMySurfaceView = this.f312q;
                C1454l lVar = new C1454l(vgMySurfaceView, this.f313r, (long) vgMySurfaceView.getWidth(), (long) this.f312q.getHeight(), getActivity().getApplication());
                this.f316u = lVar;
                this.f313r.mo28368a((C1515e) this.f316u);
                this.f299d = new C1503e(getActivity(), this.f312q.getApplication(), new File(aVar.f721a).getParentFile().getParent());
                this.f299d.mo28607a();
                configureStackedLayerAndCameraHandler();
                try {
                    NaviBeesManager.getInstance(getActivity().getApplication()).verifyFeatureLicense(NaviBeesFeature._3D_Maps);
                    if (this.f313r.mo28417g()) {
                        C1386a aVar2 = new C1386a(getActivity(), this.f313r, this.f312q, this.f313r.mo28415f(), (ViewGroup) getActivity().findViewById(C1164R.C1167id.SurfaceViewFrame), this.f294G);
                        this.f313r.mo28366a((C1511a) aVar2);
                        this.f313r.mo28369a((C1517f) aVar2);
                        C1449i iVar = new C1449i(this.f312q, this.f314s, getActivity());
                        iVar.mo28467a();
                        iVar.mo28468b();
                        this.f296a = new VgIPlaceListenerRefPtr((VgIPlaceListener) new C1359d0());
                        VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(this.f312q.getApplication().editModuleManager().queryModule("Map"));
                        castToIMapModule.addListener(this.f296a);
                        this.f298c.mo28554a(castToIMapModule);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    NaviBeesManager.getInstance(getActivity().getApplication()).verifyFeatureLicense(NaviBeesFeature.Multi_Floor_Navigation);
                    if (this.f313r.mo28421i()) {
                        C1509h hVar = new C1509h(this.f312q, this.f314s, this.f315t, this.f313r.f515f, this.f313r);
                        this.f302g = hVar;
                        this.f313r.mo28372a((C1527m) this.f302g);
                        this.f313r.mo28373a((C1528n) this.f302g);
                        this.f313r.mo28373a((C1528n) this);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    NaviBeesManager.getInstance(getActivity().getApplication()).verifyFeatureLicense(NaviBeesFeature.TurnByTurn_Navigation);
                    this.f313r.mo28419h();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                try {
                    NaviBeesManager.getInstance(getActivity().getApplication()).verifyFeatureLicense(NaviBeesFeature.Positioning);
                    this.f313r.mo28370a((C1520i) new C1446h(this.f312q));
                    C1491b bVar = new C1491b(this.f312q, this.f314s, this.f315t, "AvatarOnRoute", C1164R.C1166drawable.avatar_ontrack);
                    this.f320y = bVar;
                    C1481d.m455a().mo28567a(NotificationCompat.CATEGORY_NAVIGATION, (C1518g) this.f320y);
                    this.f321z = new C1419d(this.f312q, this.f294G);
                    this.f313r.mo28371a((C1521j) this.f321z);
                    this.f288A = new C1403b(getActivity(), this.f312q, this.f313r, this.f294G);
                    C1491b bVar2 = new C1491b(this.f312q, this.f314s, this.f315t, "Avatar", C1164R.C1166drawable.avatar);
                    this.f318w = bVar2;
                    this.f319x = new C1485a(this.f312q, this.f314s, this.f315t, "AvatarDisc");
                    C1481d.m455a().mo28566a((C1519h) this.f288A);
                    C1481d.m455a().mo28567a(this.f288A.mo28336b(), (C1518g) this.f318w);
                    C1481d.m455a().mo28567a(this.f288A.mo28336b(), (C1518g) this.f319x);
                    C1481d.m455a().mo28567a(this.f288A.mo28336b(), (C1518g) this.f313r);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                VgQuery vgQuery = new VgQuery();
                vgQuery.where("class", Operator.eEquals, "Vg3DModule::VgPoint");
                VgSpatialList execute = this.f312q.getApplication().editEngine().execute(vgQuery);
                long size = execute.size();
                for (int i = 0; ((long) i) < size; i++) {
                    execute.get(i).asPoint().setDrawOnTop(false);
                }
            }
        }
    }

    public void show() {
    }

    public void showDestinationMarker(VisioPOI visioPOI) {
        if (visioPOI != null && visioPOI.vgId != null) {
            this.f312q.queueEvent(new C1370k(visioPOI));
        }
    }

    public void showDestinationMarkers(List<POI> list, Building building, Floor floor) {
        if (getIsMapLoaded()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.f302g.mo28622a(((VisioPOI) it.next()).vgId);
            }
            if (building != null) {
                mo28206a(building);
            }
            mo28207a(floor);
        }
    }

    public void showLocation() {
        this.f312q.queueEvent(new C1378s());
    }

    public void showRoute(POI poi, POI poi2, Integer num) {
        if (num != null && this.f313r.mo28419h()) {
            C1434g gVar = new C1434g(getActivity(), this.f313r, this.f312q, (FrameLayout) getActivity().findViewById(C1164R.C1167id.SurfaceViewFrame), this.f294G);
            LinkedList<C1513c> linkedList = this.f313r.f496B;
            linkedList.removeAll(linkedList);
            this.f313r.mo28367a((C1513c) gVar);
        }
        String str = ((VisioPOI) poi).vgId;
        String str2 = ((VisioPOI) poi2).vgId;
        this.f313r.mo28413e();
        this.f313r.mo28384a(0, str);
        this.f313r.mo28384a(7, str2);
        this.f313r.mo28398b();
        gotoPlaceId(str);
    }

    public void stopAutomaticMode() {
        if (this.f297b != null) {
            Intent intent = new Intent("com.navibees.map.gesture");
            intent.putExtra("com.navibees.map.gesture.action", "com.navibees.map.gesture.stop_automode");
            this.f297b.sendBroadcast(intent);
        }
    }

    public void toggleMapPoints() {
        VgMySurfaceView vgMySurfaceView = this.f312q;
        if (!(vgMySurfaceView == null || vgMySurfaceView.getApplication() == null)) {
            VgQuery vgQuery = new VgQuery();
            vgQuery.where("class", Operator.eEquals, "Vg3DModule::VgPoint");
            new VgSpatialList();
            VgSpatialList execute = this.f312q.getApplication().editEngine().execute(vgQuery);
            for (int i = 0; ((long) i) < execute.size(); i++) {
                execute.get(i).asPoint().setVisible(this.f290C);
            }
            this.f290C = !this.f290C;
        }
    }

    public void unloadMap() {
        this.f300e = false;
        this.f301f = null;
        VgMySurfaceView vgMySurfaceView = this.f312q;
        if (vgMySurfaceView != null) {
            vgMySurfaceView.pauseRendering();
            this.f312q.onPause();
            this.f312q.setVisibility(8);
            boolean[] zArr = new boolean[1];
            FutureTask futureTask = new FutureTask(new C1366g(zArr), zArr);
            this.f312q.queueEvent(futureTask);
            try {
                boolean[] zArr2 = (boolean[]) futureTask.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean verifyMyMap(C1472a aVar) {
        return true;
    }

    public void zoomToLongLatLocation(IndoorLocation indoorLocation) {
        Building building = (Building) NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getBuildings().get(indoorLocation.buildingId);
        String str = (building == null || indoorLocation.floor >= building.floors.size()) ? "" : ((VisioFloor) building.floors.get(indoorLocation.floor)).vgfloorId;
        int i = 20;
        Venue currentVenue = NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            i = currentVenue.defaultZoom;
        }
        this.f313r.mo28404c(str);
        VgPosition vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, (double) i);
        VgIViewPoint vgIViewPoint = new VgIViewPoint();
        vgIViewPoint.setMPosition(vgPosition);
        vgIViewPoint.setMHeading(this.f312q.getApplication().editEngine().editCamera().getHeading());
        vgIViewPoint.setMPitch(-75.0d);
        Intent intent = new Intent("exploreRequest");
        intent.putExtra("view", C1516a.eVgViewModeFloor);
        intent.putExtra("focusedFloor", str);
        intent.putExtra("focusedLongitude", new Float(vgIViewPoint.getMPosition().getMXOrLongitude()));
        intent.putExtra("focusedLatitude", new Float(vgIViewPoint.getMPosition().getMYOrLatitude()));
        intent.putExtra("focusedAltitude", new Float(vgIViewPoint.getMPosition().getMZOrAltitude()));
        intent.putExtra("focusedHeading", new Float(vgIViewPoint.getMHeading()));
        intent.putExtra("focusedPitch", new Float(vgIViewPoint.getMPitch()));
        this.f297b.sendBroadcast(intent);
    }

    public void zoomToPOI(VisioPOI visioPOI) {
        if (visioPOI != null) {
            gotoPlaceId(visioPOI.vgId);
        }
    }

    public void zoomToUTMLocation(IndoorLocation indoorLocation) {
        IndoorLocation convertUTMToLatLong = NaviBeesUtils.convertUTMToLatLong(indoorLocation);
        if (convertUTMToLatLong != null) {
            zoomToLongLatLocation(convertUTMToLatLong);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo28218b() {
        this.f313r.mo28410d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo28219c() {
        loadLocalMap(this.f291D);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public void mo28228d() {
        stopAutomaticMode();
        Intent intent = new Intent("exploreRequest");
        intent.putExtra("view", C1516a.eVgViewModeGlobal);
        this.f297b.sendBroadcast(intent);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public void mo28229e() {
        if (this.f313r.f524o != null) {
            VgMySurfaceView vgMySurfaceView = this.f312q;
            if (vgMySurfaceView != null) {
                vgMySurfaceView.queueEvent(new C1365f());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public void mo28230f() {
        clearMarkers();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public void mo28231g() {
        stopAutomaticMode();
    }

    /* renamed from: b */
    private void m164b(boolean z) {
        if (z) {
            mo28237h();
            if (this.f292E) {
                loadFromNetwork();
            } else {
                mo28219c();
            }
        }
    }

    public void addMarkerAtLocation(BeaconNode beaconNode, int i, Floor floor, String str) {
        if (getIsMapLoaded() && PositionManager.showMarkers) {
            VgMySurfaceView vgMySurfaceView = this.f312q;
            C1384y yVar = new C1384y(beaconNode, floor, i, str);
            vgMySurfaceView.queueEvent(yVar);
        }
    }

    public void clearCustomMarkers(int i) {
        if (this.f302g != null) {
            HashMap<String, C1453k> hashMap = this.f310o;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(i);
            if (hashMap.containsKey(sb.toString())) {
                this.f312q.queueEvent(new C1382w(i));
            }
        }
    }

    public void showDestinationMarker(IndoorLocation indoorLocation) {
        if (indoorLocation != null) {
            this.f312q.queueEvent(new C1352a0(indoorLocation));
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28205a() {
        this.f313r.mo28413e();
    }

    public void initiateRouting(VisioPOI visioPOI, ArrayList<POI> arrayList, MapHoverInterface mapHoverInterface) {
        if (visioPOI != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(visioPOI.vgId);
            this.f312q.queueEvent(new C1369j(arrayList2, arrayList, mapHoverInterface));
        }
    }

    /* renamed from: a */
    private Floor m158a(Building building, int i) {
        for (Floor floor : building.floors) {
            if (floor.floorIndex == i) {
                return floor;
            }
        }
        return (Floor) building.floors.get(i);
    }

    public void initiateRouting(Object obj, MapHoverInterface mapHoverInterface) {
        if (obj != null) {
            ArrayList arrayList = new ArrayList();
            if (obj instanceof VisioPOI) {
                arrayList.add(((VisioPOI) obj).vgId);
            } else if (obj instanceof VisioFacility) {
                List<VisioPOI> pois = ((VisioFacility) obj).getPois();
                if (pois != null && pois.size() > 0) {
                    for (VisioPOI visioPOI : pois) {
                        arrayList.add(visioPOI.vgId);
                    }
                }
            }
            if (arrayList.size() > 0 && this.f313r != null) {
                this.f312q.queueEvent(new C1371l(arrayList, mapHoverInterface));
            }
        }
    }

    /* renamed from: a */
    private Building m157a(String str) {
        for (int i = 0; i < NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getCurrentVenue().buildings.size(); i++) {
            Building building = (Building) NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getCurrentVenue().buildings.valueAt(i);
            if (building.name.getText().equalsIgnoreCase(str)) {
                return building;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m160a(BeaconNode beaconNode, int i, int i2, String str) {
        if (getIsMapLoaded() && PositionManager.showMarkers) {
            VgMySurfaceView vgMySurfaceView = this.f312q;
            C1385z zVar = new C1385z(beaconNode, i2, i, str);
            vgMySurfaceView.queueEvent(zVar);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28212a(boolean z) {
        setLocationProvider(true);
        if (z) {
            C1420e eVar = this.f313r;
            if (eVar != null) {
                eVar.mo28433t();
            }
        }
        MapHoverInterface mapHoverInterface = this.f294G;
        if (mapHoverInterface != null) {
            try {
                mapHoverInterface.trackingLocationAndCompassChanged(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showRoute(POI poi, POI poi2, List<POI> list, Integer num) {
        if (num != null && this.f313r.mo28419h()) {
            C1434g gVar = new C1434g(getActivity(), this.f313r, this.f312q, (FrameLayout) getActivity().findViewById(C1164R.C1167id.SurfaceViewFrame), this.f294G);
            this.f313r.mo28367a((C1513c) gVar);
        }
        String str = ((VisioPOI) poi).vgId;
        String str2 = ((VisioPOI) poi2).vgId;
        this.f313r.mo28413e();
        this.f313r.mo28384a(0, str);
        int size = list.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            String str3 = ((VisioPOI) list.get(i)).vgId;
            C1420e eVar = this.f313r;
            eVar.mo28384a(eVar.mo28409d(i), str3);
        }
        this.f313r.mo28384a(7, str2);
        this.f313r.mo28398b();
        gotoPlaceId(str);
    }

    public void initiateRouting(Object obj, ArrayList<POI> arrayList, MapHoverInterface mapHoverInterface) {
        if (obj != null) {
            ArrayList arrayList2 = new ArrayList();
            if (obj instanceof VisioPOI) {
                arrayList2.add(((VisioPOI) obj).vgId);
            } else if (obj instanceof VisioFacility) {
                List<VisioPOI> pois = ((VisioFacility) obj).getPois();
                if (pois != null && pois.size() > 0) {
                    for (VisioPOI visioPOI : pois) {
                        arrayList2.add(visioPOI.vgId);
                    }
                }
            }
            if (arrayList2.size() > 0 && this.f313r != null) {
                this.f312q.queueEvent(new C1372m(arrayList2, arrayList, mapHoverInterface));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28206a(Building building) {
        Intent intent = new Intent("exploreRequest");
        intent.putExtra("view", C1516a.eVgViewModeFloor);
        intent.putExtra("focusedBuilding", building.providerId);
        this.f297b.sendBroadcast(intent);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28207a(Floor floor) {
        VisioFloor visioFloor = (VisioFloor) floor;
        Intent intent = new Intent("exploreRequest");
        intent.putExtra("view", C1516a.eVgViewModeFloor);
        intent.putExtra("focusedFloor", visioFloor.vgfloorId);
        this.f297b.sendBroadcast(intent);
        stopAutomaticMode();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28210a(VisioPOI visioPOI) {
        this.f312q.queueEvent(new C1353b(visioPOI));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28211a(VisioPOI visioPOI, List<POI> list) {
        this.f312q.queueEvent(new C1356c(visioPOI, list));
    }

    public void initiateRouting(IndoorLocation indoorLocation, MapHoverInterface mapHoverInterface) {
        if (!((indoorLocation.floor == -1 && indoorLocation.confidence == null) || indoorLocation == null)) {
            Building building = (Building) NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getBuildings().get(indoorLocation.buildingId);
            if (building != null) {
                this.f312q.queueEvent(new C1373n(((VisioFloor) building.floors.get(indoorLocation.floor)).vgfloorId, indoorLocation, mapHoverInterface));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28208a(IndoorLocation indoorLocation) {
        if (indoorLocation != null) {
            this.f312q.queueEvent(new C1358d(indoorLocation));
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28209a(IndoorLocation indoorLocation, List<POI> list) {
        if (indoorLocation != null) {
            this.f312q.queueEvent(new C1361e(indoorLocation, list));
        }
    }

    /* renamed from: a */
    static boolean m162a(String str, String str2) {
        return Integer.valueOf(str.compareTo(str2)).intValue() < 0;
    }

    public void showRoute(IndoorLocation indoorLocation, POI poi, Integer num) {
        if (num != null && this.f313r.mo28419h()) {
            C1434g gVar = new C1434g(getActivity(), this.f313r, this.f312q, (FrameLayout) getActivity().findViewById(C1164R.C1167id.SurfaceViewFrame), this.f294G);
            this.f313r.mo28367a((C1513c) gVar);
            this.instructionView = gVar;
        }
        String str = ((VisioPOI) poi).vgId;
        this.f313r.mo28413e();
        this.f313r.mo28365a(indoorLocation);
        this.f313r.mo28384a(7, str);
        this.f313r.mo28398b();
        zoomToUTMLocation(indoorLocation);
    }

    public void initiateRouting(IndoorLocation indoorLocation, ArrayList<POI> arrayList, MapHoverInterface mapHoverInterface) {
        if (!((indoorLocation.floor == -1 && indoorLocation.confidence == null) || indoorLocation == null)) {
            Building building = (Building) NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getBuildings().get(indoorLocation.buildingId);
            if (building != null) {
                String str = ((VisioFloor) building.floors.get(indoorLocation.floor)).vgfloorId;
                VgMySurfaceView vgMySurfaceView = this.f312q;
                C1374o oVar = new C1374o(str, indoorLocation, arrayList, mapHoverInterface);
                vgMySurfaceView.queueEvent(oVar);
            }
        }
    }

    public void showRoute(IndoorLocation indoorLocation, POI poi, List<POI> list, Integer num) {
        if (num != null && this.f313r.mo28419h()) {
            C1434g gVar = new C1434g(getActivity(), this.f313r, this.f312q, (FrameLayout) getActivity().findViewById(C1164R.C1167id.SurfaceViewFrame), this.f294G);
            this.f313r.mo28367a((C1513c) gVar);
            this.instructionView = gVar;
        }
        String str = ((VisioPOI) poi).vgId;
        this.f313r.mo28413e();
        this.f313r.mo28365a(indoorLocation);
        int size = list.size();
        if (size > 6) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            String str2 = ((VisioPOI) list.get(i)).vgId;
            C1420e eVar = this.f313r;
            eVar.mo28384a(eVar.mo28409d(i), str2);
        }
        this.f313r.mo28384a(7, str);
        this.f313r.mo28398b();
        zoomToUTMLocation(indoorLocation);
    }
}
