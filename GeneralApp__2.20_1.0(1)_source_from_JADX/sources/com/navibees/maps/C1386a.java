package com.navibees.maps;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.C1164R;
import com.navibees.core.NaviBeesConstants.MapInteraction;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.metadata.json.VisioFloor;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1511a;
import com.navibees.visioglobe.p020g.C1515e;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.p020g.C1517f;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIEngineContext;
import com.visioglobe.libVisioMove.VgIEnginePostDrawCallback;
import com.visioglobe.libVisioMove.VgIEnginePostDrawCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIPlaceListenerRefPtr;
import com.visioglobe.libVisioMove.VgPOIDescriptor;
import com.visioglobe.libVisioMove.VgPosition;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.navibees.maps.a */
/* compiled from: NBBubbleView */
public class C1386a implements C1511a, C1517f {

    /* renamed from: a */
    protected LinearLayout f402a = null;

    /* renamed from: b */
    protected Activity f403b = null;

    /* renamed from: c */
    protected C1420e f404c = null;

    /* renamed from: d */
    protected VgIApplication f405d = null;

    /* renamed from: e */
    protected VgIMapModule f406e = null;

    /* renamed from: f */
    protected VgIEnginePostDrawCallbackRefPtr f407f = null;

    /* renamed from: g */
    protected VgMySurfaceView f408g = null;

    /* renamed from: h */
    protected VgPosition f409h = null;

    /* renamed from: i */
    protected String f410i = null;

    /* renamed from: j */
    protected ViewGroup f411j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public LocalBroadcastManager f412k;

    /* renamed from: l */
    protected VgIPlaceListenerRefPtr f413l;

    /* renamed from: m */
    protected C1515e f414m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public MapHoverInterface f415n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public IndoorLocation f416o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public String f417p;

    /* renamed from: q */
    private BroadcastReceiver f418q = new C1399c();

    /* renamed from: com.navibees.maps.a$a */
    /* compiled from: NBBubbleView */
    class C1387a implements Runnable {

        /* renamed from: com.navibees.maps.a$a$a */
        /* compiled from: NBBubbleView */
        class C1388a implements OnClickListener {

            /* renamed from: com.navibees.maps.a$a$a$a */
            /* compiled from: NBBubbleView */
            class C1389a implements Runnable {
                C1389a() {
                }

                public void run() {
                    C1386a.this.hide();
                }
            }

            C1388a() {
            }

            public void onClick(View view) {
                C1386a.this.f408g.queueEvent(new C1389a());
            }
        }

        /* renamed from: com.navibees.maps.a$a$b */
        /* compiled from: NBBubbleView */
        class C1390b implements OnClickListener {

            /* renamed from: com.navibees.maps.a$a$b$a */
            /* compiled from: NBBubbleView */
            class C1391a implements Runnable {
                C1391a() {
                }

                public void run() {
                    C1386a.this.hide();
                }
            }

            C1390b() {
            }

            public void onClick(View view) {
                Intent intent = new Intent(MapInteraction.ACTION_SHARE_LOCATION);
                intent.putExtra(MapInteraction.SHARED_LOCATION_KEY, C1386a.this.f416o);
                C1386a.this.f412k.sendBroadcast(intent);
                C1386a.this.f408g.queueEvent(new C1391a());
            }
        }

        /* renamed from: com.navibees.maps.a$a$c */
        /* compiled from: NBBubbleView */
        class C1392c implements OnClickListener {

            /* renamed from: com.navibees.maps.a$a$c$a */
            /* compiled from: NBBubbleView */
            class C1393a implements Runnable {
                C1393a() {
                }

                public void run() {
                    C1386a.this.hide();
                }
            }

            C1392c() {
            }

            public void onClick(View view) {
                C1386a.this.f412k.sendBroadcast(new Intent(MapInteraction.ACTION_SHARE_LOCATION));
                C1386a.this.f408g.queueEvent(new C1393a());
            }
        }

        /* renamed from: com.navibees.maps.a$a$d */
        /* compiled from: NBBubbleView */
        class C1394d implements OnClickListener {

            /* renamed from: com.navibees.maps.a$a$d$a */
            /* compiled from: NBBubbleView */
            class C1395a implements Runnable {
                C1395a() {
                }

                public void run() {
                    C1386a.this.hide();
                }
            }

            C1394d() {
            }

            public void onClick(View view) {
                C1386a aVar = C1386a.this;
                aVar.f404c.mo28365a(aVar.f416o);
                C1386a.this.f408g.queueEvent(new C1395a());
            }
        }

        /* renamed from: com.navibees.maps.a$a$e */
        /* compiled from: NBBubbleView */
        class C1396e implements OnClickListener {

            /* renamed from: com.navibees.maps.a$a$e$a */
            /* compiled from: NBBubbleView */
            class C1397a implements Runnable {
                C1397a() {
                }

                public void run() {
                    C1386a.this.hide();
                }
            }

            C1396e() {
            }

            public void onClick(View view) {
                if (C1386a.this.f415n != null) {
                    C1386a.this.f415n.onSaveLocationClicked(C1386a.this.f416o);
                }
                C1386a.this.f408g.queueEvent(new C1397a());
            }
        }

        C1387a() {
        }

        public void run() {
            if (MapControls.SHOW_POPUP_VIEW) {
                C1386a aVar = C1386a.this;
                if (aVar.f402a == null) {
                    aVar.f402a = (LinearLayout) aVar.f403b.getLayoutInflater().inflate(C1164R.layout.share_location_bubbleview, null);
                    C1386a.this.f402a.setBackgroundResource(C1164R.C1166drawable.dialog_frame);
                    Button button = (Button) C1386a.this.f402a.findViewById(C1164R.C1167id.buttonShareLocationCancel);
                    button.setOnClickListener(new C1388a());
                    if (!MapControls.SHOW_POPUP_CANCEL_BUTTON) {
                        button.setVisibility(8);
                    }
                    Button button2 = (Button) C1386a.this.f402a.findViewById(C1164R.C1167id.buttonShareThisLocation);
                    button2.setOnClickListener(new C1390b());
                    if (!MapControls.SHOW_POPUP_SHARE_LOCATION_BUTTON) {
                        button2.setVisibility(8);
                    }
                    Button button3 = (Button) C1386a.this.f402a.findViewById(C1164R.C1167id.buttonShareMyLocation);
                    button3.setOnClickListener(new C1392c());
                    if (!MapControls.SHOW_POPUP_SHARE_USER_LOCATION_BUTTON) {
                        button3.setVisibility(8);
                    }
                    Button button4 = (Button) C1386a.this.f402a.findViewById(C1164R.C1167id.buttonSetStartPosition);
                    button4.setVisibility(8);
                    button4.setOnClickListener(new C1394d());
                    Button button5 = (Button) C1386a.this.f402a.findViewById(C1164R.C1167id.buttonSaveLocation);
                    button5.setOnClickListener(new C1396e());
                    if (!MapControls.SHOW_POPUP_SAVE_LOCATION_BUTTON) {
                        button5.setVisibility(8);
                    }
                    C1386a.this.f402a.setVisibility(8);
                    LayoutParams layoutParams = new LayoutParams((int) TypedValue.applyDimension(1, 210.0f, C1386a.this.f403b.getResources().getDisplayMetrics()), -2, 51);
                    C1386a aVar2 = C1386a.this;
                    aVar2.f411j.addView(aVar2.f402a, layoutParams);
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.a$b */
    /* compiled from: NBBubbleView */
    class C1398b implements Runnable {
        C1398b() {
        }

        public void run() {
            C1386a.this.f402a.setVisibility(8);
        }
    }

    /* renamed from: com.navibees.maps.a$c */
    /* compiled from: NBBubbleView */
    class C1399c extends BroadcastReceiver {

        /* renamed from: com.navibees.maps.a$c$a */
        /* compiled from: NBBubbleView */
        class C1400a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ Intent f432a;

            C1400a(Intent intent) {
                this.f432a = intent;
            }

            public void run() {
                int i;
                this.f432a.getDoubleExtra("NaviBees_SHARE_LOCATION_Z", 0.0d);
                double doubleExtra = this.f432a.getDoubleExtra("NaviBees_SHARE_LOCATION_X_Longitude", 0.0d);
                double doubleExtra2 = this.f432a.getDoubleExtra("NaviBees_SHARE_LOCATION_Y_Latitude", 0.0d);
                VgPosition vgPosition = new VgPosition(doubleExtra, doubleExtra2, 0.0d);
                String charSequence = C1386a.this.f414m.mo28363a().toString();
                C1516a c = C1386a.this.f414m.mo28405c();
                Venue currentVenue = NaviBeesManager.getInstance(C1386a.this.f403b.getApplication()).getMetaDataManager().getCurrentVenue();
                if (currentVenue != null) {
                    Building currentBuilding = NaviBeesManager.getInstance(C1386a.this.f403b.getApplication()).getMetaDataManager().getCurrentBuilding();
                    if (currentBuilding != null) {
                        List<Floor> list = currentBuilding.floors;
                        if (c == C1516a.eVgViewModeFloor && list != null && charSequence != null) {
                            Iterator it = list.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                Floor floor = (Floor) it.next();
                                VisioFloor visioFloor = (VisioFloor) floor;
                                if (visioFloor.vgfloorId.equals(charSequence)) {
                                    int i2 = floor.floorIndex;
                                    C1386a.this.f417p = visioFloor.vgfloorId;
                                    i = i2;
                                    break;
                                }
                            }
                            C1386a aVar = C1386a.this;
                            IndoorLocation indoorLocation = new IndoorLocation(doubleExtra, doubleExtra2, i);
                            aVar.f416o = indoorLocation;
                            C1386a.this.f416o.venueId = currentVenue.f1342id;
                            C1386a.this.f416o.buildingId = currentBuilding.f1329id;
                            C1386a.this.mo28317a("", vgPosition);
                            C1386a.this.show();
                        }
                        i = -1;
                        C1386a aVar2 = C1386a.this;
                        IndoorLocation indoorLocation2 = new IndoorLocation(doubleExtra, doubleExtra2, i);
                        aVar2.f416o = indoorLocation2;
                        C1386a.this.f416o.venueId = currentVenue.f1342id;
                        C1386a.this.f416o.buildingId = currentBuilding.f1329id;
                        C1386a.this.mo28317a("", vgPosition);
                        C1386a.this.show();
                    }
                }
            }
        }

        C1399c() {
        }

        public void onReceive(Context context, Intent intent) {
            C1386a.this.f416o = null;
            C1386a.this.f417p = null;
            C1386a aVar = C1386a.this;
            if (aVar.f408g != null && aVar.f414m != null && aVar.f405d != null && intent.getExtras() != null) {
                C1386a.this.f408g.queueEvent(new C1400a(intent));
            }
        }
    }

    /* renamed from: com.navibees.maps.a$d */
    /* compiled from: NBBubbleView */
    protected class C1401d extends VgIEnginePostDrawCallback {

        /* renamed from: a */
        private VgPosition f434a;

        /* renamed from: com.navibees.maps.a$d$a */
        /* compiled from: NBBubbleView */
        class C1402a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ float f436a;

            /* renamed from: b */
            final /* synthetic */ float f437b;

            C1402a(float f, float f2) {
                this.f436a = f;
                this.f437b = f2;
            }

            public void run() {
                LinearLayout linearLayout = C1386a.this.f402a;
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                    LayoutParams layoutParams = (LayoutParams) C1386a.this.f402a.getLayoutParams();
                    layoutParams.leftMargin = (int) ((this.f436a * ((float) C1386a.this.f411j.getWidth())) - (((float) C1386a.this.f402a.getWidth()) / 2.0f));
                    layoutParams.topMargin = (int) (((1.0f - this.f437b) * ((float) C1386a.this.f411j.getHeight())) - ((float) C1386a.this.f402a.getHeight()));
                    if (C1386a.this.f402a.getWidth() == 0 || C1386a.this.f402a.getHeight() == 0) {
                        C1386a.this.f408g.requestRedraw();
                        return;
                    }
                    C1386a aVar = C1386a.this;
                    aVar.f411j.updateViewLayout(aVar.f402a, layoutParams);
                }
            }
        }

        public C1401d(VgPosition vgPosition) {
            this.f434a = new VgPosition(vgPosition);
        }

        public void postDraw(VgIEngineContext vgIEngineContext) {
            double[] dArr = {0.0d};
            double[] dArr2 = {0.0d};
            double[] dArr3 = {0.0d};
            C1386a.this.f405d.editEngine().editCamera().projectOnScreen(this.f434a, dArr, dArr2, dArr3);
            if (!Double.isNaN(dArr[0]) && !Double.isNaN(dArr2[0]) && !Double.isNaN(dArr3[0]) && !Double.isInfinite(dArr[0]) && !Double.isInfinite(dArr2[0]) && !Double.isInfinite(dArr3[0])) {
                if (dArr[0] < 0.0d) {
                    dArr[0] = 0.0d;
                }
                if (dArr[0] > 1.0d) {
                    dArr[0] = 1.0d;
                }
                if (dArr2[0] < 0.0d) {
                    dArr2[0] = 0.0d;
                }
                if (dArr2[0] > 1.0d) {
                    dArr2[0] = 1.0d;
                }
                C1386a.this.f403b.runOnUiThread(new C1402a((float) dArr[0], (float) dArr2[0]));
            }
        }
    }

    public C1386a(Activity activity, C1420e eVar, VgMySurfaceView vgMySurfaceView, C1515e eVar2, ViewGroup viewGroup, MapHoverInterface mapHoverInterface) {
        this.f404c = eVar;
        this.f408g = vgMySurfaceView;
        this.f403b = activity;
        this.f411j = viewGroup;
        this.f414m = eVar2;
        this.f415n = mapHoverInterface;
        this.f405d = this.f408g.getApplication();
        this.f406e = C1732libVisioMove.castToIMapModule(this.f405d.editModuleManager().queryModule("Map"));
        this.f412k = LocalBroadcastManager.getInstance(this.f403b);
        this.f412k.registerReceiver(this.f418q, new IntentFilter("NaviBeesShareLocation"));
    }

    public void hide() {
        if (this.f402a != null) {
            if (this.f407f != null) {
                this.f405d.editEngine().removePostDrawCallback(this.f407f);
            }
            this.f403b.runOnUiThread(new C1398b());
        }
    }

    public void layerWillChangeFrom(C1515e eVar, String str, String str2) {
        hide();
    }

    public void modeDidChange(C1515e eVar, C1516a aVar) {
        hide();
    }

    public void modeWillChange(C1515e eVar, C1516a aVar) {
        hide();
        C1516a aVar2 = C1516a.eVgViewModeBuilding;
    }

    public void release() {
        if (this.f407f != null) {
            this.f405d.editEngine().removePostDrawCallback(this.f407f);
        }
        this.f407f = null;
        BroadcastReceiver broadcastReceiver = this.f418q;
        if (broadcastReceiver != null) {
            this.f412k.unregisterReceiver(broadcastReceiver);
        }
        this.f412k = null;
        if (this.f406e != null) {
            VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr = this.f413l;
            if (vgIPlaceListenerRefPtr != null) {
                if (vgIPlaceListenerRefPtr.isValid()) {
                    this.f406e.removeListener(this.f413l);
                    this.f413l.set(null);
                }
                this.f413l = null;
            }
            this.f406e = null;
        }
        this.f403b = null;
        this.f404c = null;
        this.f405d = null;
        this.f409h = null;
        this.f416o = null;
        this.f411j = null;
        this.f408g = null;
        this.f402a = null;
    }

    public void show() {
        if (this.f407f != null) {
            this.f405d.editEngine().addPostDrawCallback(this.f407f);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28316a(String str) {
        this.f403b.runOnUiThread(new C1387a());
    }

    /* renamed from: a */
    public void mo28317a(String str, VgPosition vgPosition) {
        this.f410i = str;
        this.f409h = new VgPosition(vgPosition);
        VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
        this.f406e.queryPOIDescriptor(str, vgPOIDescriptor);
        vgPOIDescriptor.getMLayerName();
        mo28316a(this.f410i);
        VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr = this.f407f;
        if (vgIEnginePostDrawCallbackRefPtr != null) {
            if (vgIEnginePostDrawCallbackRefPtr.isValid()) {
                this.f405d.editEngine().removePostDrawCallback(this.f407f);
                this.f407f.set(null);
            }
            this.f407f = null;
        }
        this.f407f = new VgIEnginePostDrawCallbackRefPtr((VgIEnginePostDrawCallback) new C1401d(this.f409h));
    }
}
