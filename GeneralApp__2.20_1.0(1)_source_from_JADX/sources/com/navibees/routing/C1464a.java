package com.navibees.routing;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.metadata.json.VisioFloor;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.core.util.Log;
import com.navibees.maps.C1420e;
import com.navibees.visioglobe.C1472a;
import com.navibees.visioglobe.C1477c;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p019f.C1509h;
import com.navibees.visioglobe.p020g.C1527m;
import com.navibees.visioglobe.p020g.C1528n;
import com.visioglobe.libVisioMove.VgPosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.navibees.routing.a */
/* compiled from: NBVgRouting */
public class C1464a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public GetNearestPOIsCallback f685a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public VgMySurfaceView f686b;

    /* renamed from: com.navibees.routing.a$a */
    /* compiled from: NBVgRouting */
    class C1465a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Activity f687a;

        /* renamed from: b */
        final /* synthetic */ C1472a f688b;

        /* renamed from: c */
        final /* synthetic */ IndoorLocation f689c;

        /* renamed from: d */
        final /* synthetic */ POI f690d;

        /* renamed from: e */
        final /* synthetic */ List f691e;

        /* renamed from: f */
        final /* synthetic */ double f692f;

        /* renamed from: g */
        final /* synthetic */ int f693g;

        /* renamed from: com.navibees.routing.a$a$a */
        /* compiled from: NBVgRouting */
        class C1466a implements Comparator<POI> {
            C1466a(C1465a aVar) {
            }

            /* renamed from: a */
            public int compare(POI poi, POI poi2) {
                double d = poi.routingDistance;
                double d2 = poi2.routingDistance;
                int i = (d > d2 ? 1 : (d == d2 ? 0 : -1));
                if (i == 0) {
                    return 0;
                }
                if (d == -1.0d) {
                    return 1;
                }
                int i2 = -1;
                if (d2 == -1.0d) {
                    return -1;
                }
                if (i > 0) {
                    i2 = 1;
                }
                return i2;
            }
        }

        C1465a(Activity activity, C1472a aVar, IndoorLocation indoorLocation, POI poi, List list, double d, int i) {
            this.f687a = activity;
            this.f688b = aVar;
            this.f689c = indoorLocation;
            this.f690d = poi;
            this.f691e = list;
            this.f692f = d;
            this.f693g = i;
        }

        public void run() {
            VgPosition vgPosition;
            String str;
            C1477c cVar = new C1477c(C1464a.this.f686b);
            C1482e eVar = new C1482e(this.f687a, C1464a.this.f686b);
            C1420e eVar2 = new C1420e(this.f687a, eVar, C1464a.this.f686b);
            eVar2.mo28411d(this.f688b.f721a);
            eVar2.mo28416f(this.f688b.f723c);
            eVar2.mo28364a(this.f688b.f722b.longValue());
            if (!eVar2.mo28431r()) {
                C1464a.this.m426a("VgMyBasicApplicationController setup failed");
                return;
            }
            C1509h hVar = new C1509h(C1464a.this.f686b, eVar, cVar, eVar2.f515f, eVar2);
            eVar2.mo28372a((C1527m) hVar);
            eVar2.mo28373a((C1528n) hVar);
            if (this.f689c != null) {
                Building building = (Building) NaviBeesManager.getInstance(this.f687a.getApplication()).getMetaDataManager().getBuildings().get(this.f689c.buildingId);
                if (building != null) {
                    List<Floor> list = building.floors;
                    if (list != null && this.f689c.floor < list.size()) {
                        int i = this.f689c.floor;
                        if (i >= 0) {
                            double c = eVar2.mo28404c(((VisioFloor) building.floors.get(i)).vgfloorId);
                            IndoorLocation indoorLocation = this.f689c;
                            vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, c);
                        }
                    }
                }
                C1464a.this.m426a("Start location is invalid");
                return;
            }
            vgPosition = null;
            POI poi = this.f690d;
            if (poi != null && (poi instanceof VisioPOI)) {
                str = ((VisioPOI) poi).vgId;
            } else if (vgPosition == null) {
                C1464a.this.m426a("Start POI is invalid");
                return;
            } else {
                str = null;
            }
            List arrayList = new ArrayList();
            for (POI poi2 : this.f691e) {
                if (poi2 instanceof VisioPOI) {
                    double[] dArr = {-1.0d, -1.0d};
                    String str2 = ((VisioPOI) poi2).vgId;
                    if (vgPosition != null) {
                        dArr = eVar2.mo28397a(vgPosition, str2);
                    } else if (str != null) {
                        dArr = eVar2.mo28407c(str, str2);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("poi: ");
                    sb.append(poi2.name.getText());
                    sb.append(", distance= ");
                    sb.append(dArr[0]);
                    Log.m1171d("NBVgRouting", sb.toString());
                    double d = this.f692f;
                    if (d == -1.0d || (dArr[0] != -1.0d && dArr[0] <= d)) {
                        poi2.routingDistance = dArr[0];
                        poi2.routingDuration = dArr[1];
                        arrayList.add(poi2);
                    }
                }
            }
            Collections.sort(arrayList, new C1466a(this));
            if (this.f693g != -1) {
                int size = arrayList.size();
                int i2 = this.f693g;
                if (size > i2) {
                    arrayList = arrayList.subList(0, i2);
                }
            }
            eVar2.mo28413e();
            eVar2.release();
            eVar.mo28575a();
            cVar.mo28560a();
            C1464a.this.m427a(arrayList);
            C1464a.this.f686b = null;
        }
    }

    /* renamed from: com.navibees.routing.a$b */
    /* compiled from: NBVgRouting */
    class C1467b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Activity f695a;

        /* renamed from: b */
        final /* synthetic */ C1472a f696b;

        /* renamed from: c */
        final /* synthetic */ IndoorLocation f697c;

        /* renamed from: d */
        final /* synthetic */ POI f698d;

        /* renamed from: e */
        final /* synthetic */ List f699e;

        /* renamed from: f */
        final /* synthetic */ double f700f;

        /* renamed from: g */
        final /* synthetic */ int f701g;

        /* renamed from: com.navibees.routing.a$b$a */
        /* compiled from: NBVgRouting */
        class C1468a implements Comparator<POI> {
            C1468a(C1467b bVar) {
            }

            /* renamed from: a */
            public int compare(POI poi, POI poi2) {
                double d = poi.routingDistance;
                double d2 = poi2.routingDistance;
                int i = (d > d2 ? 1 : (d == d2 ? 0 : -1));
                if (i == 0) {
                    return 0;
                }
                if (d == -1.0d) {
                    return 1;
                }
                int i2 = -1;
                if (d2 == -1.0d) {
                    return -1;
                }
                if (i > 0) {
                    i2 = 1;
                }
                return i2;
            }
        }

        C1467b(Activity activity, C1472a aVar, IndoorLocation indoorLocation, POI poi, List list, double d, int i) {
            this.f695a = activity;
            this.f696b = aVar;
            this.f697c = indoorLocation;
            this.f698d = poi;
            this.f699e = list;
            this.f700f = d;
            this.f701g = i;
        }

        public void run() {
            VgPosition vgPosition;
            String str;
            C1477c cVar = new C1477c(C1464a.this.f686b);
            C1482e eVar = new C1482e(this.f695a, C1464a.this.f686b);
            C1420e eVar2 = new C1420e(this.f695a, eVar, C1464a.this.f686b);
            eVar2.mo28411d(this.f696b.f721a);
            eVar2.mo28416f(this.f696b.f723c);
            eVar2.mo28364a(this.f696b.f722b.longValue());
            if (!eVar2.mo28431r()) {
                C1464a.this.m426a("VgMyBasicApplicationController setup failed");
                return;
            }
            C1509h hVar = new C1509h(C1464a.this.f686b, eVar, cVar, eVar2.f515f, eVar2);
            eVar2.mo28372a((C1527m) hVar);
            eVar2.mo28373a((C1528n) hVar);
            if (this.f697c != null) {
                Building building = (Building) NaviBeesManager.getInstance(this.f695a.getApplication()).getMetaDataManager().getBuildings().get(this.f697c.buildingId);
                if (building != null) {
                    List<Floor> list = building.floors;
                    if (list != null && this.f697c.floor < list.size()) {
                        int i = this.f697c.floor;
                        if (i >= 0) {
                            double c = eVar2.mo28404c(((VisioFloor) building.floors.get(i)).vgfloorId);
                            IndoorLocation indoorLocation = this.f697c;
                            vgPosition = new VgPosition(indoorLocation.f1332x, indoorLocation.f1333y, c);
                        }
                    }
                }
                C1464a.this.m426a("Start location is invalid");
                return;
            }
            vgPosition = null;
            POI poi = this.f698d;
            if (poi != null && (poi instanceof VisioPOI)) {
                str = ((VisioPOI) poi).vgId;
            } else if (vgPosition == null) {
                C1464a.this.m426a("Start POI is invalid");
                return;
            } else {
                str = null;
            }
            List arrayList = new ArrayList();
            for (POI poi2 : this.f699e) {
                if (poi2 instanceof VisioPOI) {
                    double[] dArr = {-1.0d, -1.0d};
                    String str2 = ((VisioPOI) poi2).vgId;
                    if (vgPosition != null) {
                        dArr = eVar2.mo28397a(vgPosition, str2);
                    } else if (str != null) {
                        dArr = eVar2.mo28403b(str, str2);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("poi: ");
                    sb.append(poi2.name.getText());
                    sb.append(", distance= ");
                    sb.append(dArr[0]);
                    Log.m1171d("NBVgRouting", sb.toString());
                    double d = this.f700f;
                    if (d == -1.0d || (dArr[0] != -1.0d && dArr[0] <= d)) {
                        poi2.routingDistance = dArr[0];
                        poi2.routingDuration = dArr[1];
                        arrayList.add(poi2);
                    }
                }
            }
            Collections.sort(arrayList, new C1468a(this));
            if (this.f701g != -1) {
                int size = arrayList.size();
                int i2 = this.f701g;
                if (size > i2) {
                    arrayList = arrayList.subList(0, i2);
                }
            }
            eVar2.mo28413e();
            eVar2.release();
            eVar.mo28575a();
            cVar.mo28560a();
            C1464a.this.m427a(arrayList);
            C1464a.this.f686b = null;
        }
    }

    /* renamed from: com.navibees.routing.a$c */
    /* compiled from: NBVgRouting */
    class C1469c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f703a;

        C1469c(String str) {
            this.f703a = str;
        }

        public void run() {
            C1464a.this.f685a.onNearestPOIsFailure(this.f703a);
        }
    }

    /* renamed from: com.navibees.routing.a$d */
    /* compiled from: NBVgRouting */
    class C1470d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ List f705a;

        C1470d(List list) {
            this.f705a = list;
        }

        public void run() {
            C1464a.this.f685a.onNearestPOIsFound(this.f705a);
        }
    }

    /* renamed from: b */
    private void m429b(Activity activity, POI poi, IndoorLocation indoorLocation, List<POI> list, double d, int i, GetNearestPOIsCallback getNearestPOIsCallback) {
        Activity activity2 = activity;
        GetNearestPOIsCallback getNearestPOIsCallback2 = getNearestPOIsCallback;
        Venue currentVenue = NaviBeesManager.getInstance(activity.getApplication()).getMetaDataManager().getCurrentVenue();
        C1472a b = C1472a.m438b(activity, currentVenue != null ? currentVenue.f1342id : "");
        if (b == null) {
            getNearestPOIsCallback2.onNearestPOIsFailure("Map data is not available");
            return;
        }
        this.f685a = getNearestPOIsCallback2;
        this.f686b = new VgMySurfaceView(activity);
        VgMySurfaceView vgMySurfaceView = this.f686b;
        C1465a aVar = new C1465a(activity, b, indoorLocation, poi, list, d, i);
        vgMySurfaceView.queueEvent(aVar);
    }

    /* renamed from: a */
    public void mo28526a(Activity activity, POI poi, List<POI> list, double d, int i, GetNearestPOIsCallback getNearestPOIsCallback) {
        m429b(activity, poi, null, list, d, i, getNearestPOIsCallback);
    }

    /* renamed from: a */
    public void mo28524a(Activity activity, IndoorLocation indoorLocation, List<POI> list, double d, int i, GetNearestPOIsCallback getNearestPOIsCallback) {
        m429b(activity, null, indoorLocation, list, d, i, getNearestPOIsCallback);
    }

    /* renamed from: a */
    public void mo28525a(Activity activity, POI poi, IndoorLocation indoorLocation, List<POI> list, double d, int i, GetNearestPOIsCallback getNearestPOIsCallback) {
        GetNearestPOIsCallback getNearestPOIsCallback2 = getNearestPOIsCallback;
        Venue currentVenue = NaviBeesManager.getInstance(activity.getApplication()).getMetaDataManager().getCurrentVenue();
        Activity activity2 = activity;
        C1472a b = C1472a.m438b(activity, currentVenue != null ? currentVenue.f1342id : "");
        if (b == null) {
            getNearestPOIsCallback2.onNearestPOIsFailure("Map data is not available");
            return;
        }
        this.f685a = getNearestPOIsCallback2;
        this.f686b = new VgMySurfaceView(activity.getApplication());
        VgMySurfaceView vgMySurfaceView = this.f686b;
        C1467b bVar = new C1467b(activity, b, indoorLocation, poi, list, d, i);
        vgMySurfaceView.queueEvent(bVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m426a(String str) {
        new Handler(Looper.getMainLooper()).post(new C1469c(str));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m427a(List<POI> list) {
        new Handler(Looper.getMainLooper()).post(new C1470d(list));
    }
}
