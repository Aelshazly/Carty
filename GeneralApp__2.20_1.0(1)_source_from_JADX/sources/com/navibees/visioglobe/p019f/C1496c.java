package com.navibees.visioglobe.p019f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.C1164R;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.utils.C1536d;
import com.navibees.visioglobe.utils.C1546f;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgAltitudeMode;
import com.visioglobe.libVisioMove.VgAnchorMode;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgLayerRefPtr;
import com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPOIDescriptor;
import com.visioglobe.libVisioMove.VgPoint;
import com.visioglobe.libVisioMove.VgPointDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPointRefPtr;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgQuery;
import com.visioglobe.libVisioMove.VgQuery.Operator;
import com.visioglobe.libVisioMove.VgSpatialList;
import com.visioglobe.libVisioMove.VgSurfaceView;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.navibees.visioglobe.f.c */
/* compiled from: VgMyBuildingMarkerView */
public class C1496c {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final VgSurfaceView f794a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1546f f795b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1482e f796c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public VgIApplication f797d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public LocalBroadcastManager f798e;

    /* renamed from: f */
    Set<String> f799f = new HashSet();

    /* renamed from: g */
    private BroadcastReceiver f800g = new C1498b();
    /* access modifiers changed from: private */

    /* renamed from: h */
    public BroadcastReceiver f801h = new C1499c();

    /* renamed from: com.navibees.visioglobe.f.c$a */
    /* compiled from: VgMyBuildingMarkerView */
    class C1497a implements Runnable {
        C1497a() {
        }

        public void run() {
            for (String str : C1496c.this.f799f) {
                VgQuery vgQuery = new VgQuery();
                vgQuery.where("ID", Operator.eEquals, str);
                VgSpatialList execute = C1496c.this.f797d.editEngine().execute(vgQuery);
                long size = execute.size();
                for (long j = 0; j < size; j++) {
                    VgPoint asPoint = execute.get((int) j).asPoint();
                    if (asPoint != null) {
                        asPoint.setLayer(VgLayerRefPtr.getNull());
                    }
                }
                execute.clear();
            }
            C1496c.this.f799f.clear();
        }
    }

    /* renamed from: com.navibees.visioglobe.f.c$b */
    /* compiled from: VgMyBuildingMarkerView */
    class C1498b extends BroadcastReceiver {
        C1498b() {
        }

        public void onReceive(Context context, Intent intent) {
            if (((C1536d) intent.getParcelableExtra("parameters")).f911a.f922a && C1496c.this.f798e != null && C1496c.this.f796c != null) {
                C1496c.this.f798e.registerReceiver(C1496c.this.f801h, new IntentFilter("explore"));
                C1496c.this.f795b = (C1546f) intent.getParcelableExtra("venueLayout");
                String str = "building";
                C1496c.this.f796c.mo28576a(str, C1164R.C1166drawable.building);
                C1496c.this.f796c.mo28577a(str, str);
                String str2 = "focusedBuilding";
                C1496c.this.f796c.mo28576a(str2, C1164R.C1166drawable.building_focused);
                C1496c.this.f796c.mo28577a(str2, str2);
            }
        }
    }

    /* renamed from: com.navibees.visioglobe.f.c$c */
    /* compiled from: VgMyBuildingMarkerView */
    class C1499c extends BroadcastReceiver {

        /* renamed from: com.navibees.visioglobe.f.c$c$a */
        /* compiled from: VgMyBuildingMarkerView */
        class C1500a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ String f805a;

            C1500a(String str) {
                this.f805a = str;
            }

            public void run() {
                if (C1496c.this.f795b != null) {
                    for (String str : C1496c.this.f795b.f929e.keySet()) {
                        VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
                        VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(C1496c.this.f797d.editModuleManager().queryModule("Map"));
                        if (castToIMapModule != null && castToIMapModule.queryPOIDescriptor(str, vgPOIDescriptor)) {
                            VgPosition mCenter = vgPOIDescriptor.getMCenter();
                            mCenter.setMZOrAltitude(40.0d);
                            C1496c.this.m509a(str, mCenter, str.equals(this.f805a));
                        }
                    }
                }
            }
        }

        C1499c() {
        }

        public void onReceive(Context context, Intent intent) {
            C1516a aVar = (C1516a) intent.getSerializableExtra("view");
            String stringExtra = intent.getStringExtra("focusedBuilding");
            if (aVar == C1516a.eVgViewModeGlobal) {
                C1496c.this.f794a.queueEvent(new C1500a(stringExtra));
            } else {
                C1496c.this.m511b();
            }
        }
    }

    public C1496c(Context context, C1482e eVar, VgIApplication vgIApplication, VgSurfaceView vgSurfaceView) {
        this.f796c = eVar;
        this.f797d = vgIApplication;
        this.f794a = vgSurfaceView;
        this.f798e = LocalBroadcastManager.getInstance(context);
        this.f798e.registerReceiver(this.f800g, new IntentFilter("parametersLoaded"));
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m511b() {
        this.f794a.queueEvent(new C1497a());
    }

    /* renamed from: a */
    public void mo28598a() {
        LocalBroadcastManager localBroadcastManager = this.f798e;
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(this.f800g);
            this.f798e.unregisterReceiver(this.f801h);
            this.f798e = null;
        }
        this.f795b = null;
        this.f796c = null;
        this.f797d = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m509a(String str, VgPosition vgPosition, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("buildingMarker-");
        sb.append(str);
        String sb2 = sb.toString();
        VgMarkerDescriptorRefPtr a = this.f796c.mo28574a(z ? "focusedBuilding" : "building");
        if (a != null && a.isValid()) {
            VgPointDescriptorRefPtr create = VgPointDescriptor.create();
            create.setMPosition(vgPosition);
            create.setMAltitudeMode(VgAltitudeMode.eAbsolute);
            create.setMGeometryConstantSizeDistance(100.0f);
            create.setMVisibilityRampStartVisible(0.0d);
            create.setMVisibilityRampFullyVisible(0.0d);
            create.setMVisibilityRampStartInvisible(900.0d);
            create.setMVisibilityRampFullyInvisible(1000.0d);
            create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
            create.getMMarkerDescriptors().add(a);
            create.setMID(sb2);
            create.setMZIndex(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
            create.setMDrawOnTop(true);
            VgPointRefPtr instantiate = this.f797d.editEngine().editInstanceFactory().instantiate(create);
            create.set(null);
            if (instantiate.isValid()) {
                instantiate.setScale(2.0f);
                instantiate.setNotifyPOISelectedOnClick(false);
                VgLayerRefPtr editLayer = this.f797d.editEngine().editLayerManager().editLayer(this.f795b.f927c);
                if (editLayer.isValid()) {
                    instantiate.setLayer(editLayer);
                }
                this.f799f.add(sb2);
            }
        }
    }
}
