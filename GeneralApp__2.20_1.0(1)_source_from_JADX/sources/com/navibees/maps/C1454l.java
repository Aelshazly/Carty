package com.navibees.maps;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1515e;
import com.navibees.visioglobe.p020g.C1515e.C1516a;
import com.navibees.visioglobe.p020g.C1517f;
import com.navibees.visioglobe.utils.C1536d;
import com.navibees.visioglobe.utils.C1536d.C1538b.C1540b;
import com.navibees.visioglobe.utils.C1545e;
import com.navibees.visioglobe.utils.C1546f;
import com.navibees.visioglobe.utils.C1546f.C1548b;
import com.navibees.visioglobe.utils.C1546f.C1551c;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgAnimationChannels;
import com.visioglobe.libVisioMove.VgAnimationDescriptor;
import com.visioglobe.libVisioMove.VgAnimationDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgAnimationRefPtr;
import com.visioglobe.libVisioMove.VgAxialRotationQuaternionFunctorDescriptor;
import com.visioglobe.libVisioMove.VgAxialRotationQuaternionFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgGestureState;
import com.visioglobe.libVisioMove.VgIAnimationCallback;
import com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgICamera;
import com.visioglobe.libVisioMove.VgIDatabaseDatasetDescriptor;
import com.visioglobe.libVisioMove.VgIEngine;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIViewPoint;
import com.visioglobe.libVisioMove.VgLayerRefPtr;
import com.visioglobe.libVisioMove.VgLayerVector;
import com.visioglobe.libVisioMove.VgManipulator;
import com.visioglobe.libVisioMove.VgManipulatorListener;
import com.visioglobe.libVisioMove.VgManipulatorListenerRefPtr;
import com.visioglobe.libVisioMove.VgPOIDescriptor;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgPositionToolbox;
import com.visioglobe.libVisioMove.VgPositionVector;
import com.visioglobe.libVisioMove.VgQuaternionInterpolationFunctorDescriptor;
import com.visioglobe.libVisioMove.VgQuaternionInterpolationFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgQuery;
import com.visioglobe.libVisioMove.VgQuery.Operator;
import com.visioglobe.libVisioMove.VgSRSConstRefPtr;
import com.visioglobe.libVisioMove.VgSpatialList;
import com.visioglobe.libVisioMove.VgVectorInterpolationFunctorDescriptor;
import com.visioglobe.libVisioMove.VgVectorInterpolationFunctorDescriptorRefPtr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* renamed from: com.navibees.maps.l */
/* compiled from: VgMyStackedLayerAndCameraHandler */
public class C1454l implements C1515e {

    /* renamed from: D */
    static final /* synthetic */ boolean f616D = (!C1454l.class.desiredAssertionStatus());

    /* renamed from: A */
    protected boolean f617A;

    /* renamed from: B */
    private BroadcastReceiver f618B;

    /* renamed from: C */
    private BroadcastReceiver f619C;

    /* renamed from: a */
    private VgIViewPoint f620a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public VgPosition f621b;

    /* renamed from: c */
    double[] f622c = new double[C1463h.eMaxDoubleParams.ordinal()];

    /* renamed from: d */
    public long f623d;

    /* renamed from: e */
    private boolean f624e;

    /* renamed from: f */
    private C1516a f625f = C1516a.eVgViewModeBuilding;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C1536d f626g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C1546f f627h = null;

    /* renamed from: i */
    private C1548b f628i = null;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f629j = -1;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public VgIApplication f630k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public VgMySurfaceView f631l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public long f632m = 0;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public long f633n = 0;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public Application f634o;

    /* renamed from: p */
    private VgIAnimationCallbackRefPtr f635p;

    /* renamed from: q */
    private ArrayList<VgIAnimationCallbackRefPtr> f636q;

    /* renamed from: r */
    private C1517f f637r;

    /* renamed from: s */
    private C1459d f638s;

    /* renamed from: t */
    private VgManipulatorListenerRefPtr f639t;

    /* renamed from: u */
    private HashMap<String, Float> f640u = new HashMap<>();

    /* renamed from: v */
    private HashMap<String, Float> f641v = new HashMap<>();

    /* renamed from: w */
    private VgManipulator f642w;
    /* access modifiers changed from: private */

    /* renamed from: x */
    public LocalBroadcastManager f643x;

    /* renamed from: y */
    private HashMap<String, VgPositionVector> f644y;

    /* renamed from: z */
    private HashMap<String, VgSpatialList> f645z;

    /* renamed from: com.navibees.maps.l$a */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    class C1455a extends BroadcastReceiver {
        C1455a() {
        }

        public void onReceive(Context context, Intent intent) {
            long j;
            C1454l.this.f627h = (C1546f) intent.getParcelableExtra("venueLayout");
            C1454l.this.f626g = (C1536d) intent.getParcelableExtra("parameters");
            if (C1454l.this.f627h != null && C1454l.this.f630k != null) {
                VgLayerVector layers = C1454l.this.f630k.editEngine().editLayerManager().getLayers();
                int i = 0;
                while (true) {
                    j = (long) i;
                    if (j < layers.size() && !layers.get(i).getName().equals(C1454l.this.f627h.f927c)) {
                        i++;
                    }
                }
                if (j < layers.size()) {
                    C1454l.this.f629j = i;
                }
                boolean isRendering = C1454l.this.f631l.isRendering();
                if (isRendering) {
                    C1454l.this.f631l.pauseRendering();
                }
                C1454l lVar = C1454l.this;
                lVar.mo28504c(lVar.f632m, C1454l.this.f633n);
                C1454l.this.mo28495a(false, false);
                if (isRendering) {
                    C1454l.this.f631l.resumeRendering();
                }
            }
        }
    }

    /* renamed from: com.navibees.maps.l$b */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    class C1456b extends BroadcastReceiver {

        /* renamed from: com.navibees.maps.l$b$a */
        /* compiled from: VgMyStackedLayerAndCameraHandler */
        class C1457a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ Intent f648a;

            C1457a(Intent intent) {
                this.f648a = intent;
            }

            public void run() {
                String str;
                C1516a aVar = (C1516a) this.f648a.getSerializableExtra("view");
                boolean booleanExtra = this.f648a.getBooleanExtra("focusAnimation", true);
                String stringExtra = this.f648a.getStringExtra("focusedBuilding");
                if (aVar == C1516a.eVgViewModeGlobal) {
                    str = C1454l.this.f627h.f927c;
                } else {
                    str = this.f648a.getStringExtra("focusedFloor");
                }
                C1454l.this.mo28487a(aVar, stringExtra, str, booleanExtra);
                VgIViewPoint vgIViewPoint = new VgIViewPoint(C1454l.this.f630k.editEngine().editCamera().getViewpoint());
                String str2 = "focusedLatitude";
                if (this.f648a.hasExtra(str2)) {
                    String str3 = "focusedLongitude";
                    if (this.f648a.hasExtra(str3)) {
                        float floatExtra = this.f648a.getFloatExtra(str2, 0.0f);
                        float floatExtra2 = this.f648a.getFloatExtra(str3, 0.0f);
                        float floatExtra3 = this.f648a.getFloatExtra("focusedAltitude", 0.0f);
                        float floatExtra4 = this.f648a.getFloatExtra("focusedPitch", (float) C1454l.this.f631l.getApplication().editEngine().editCamera().getPitch());
                        float floatExtra5 = this.f648a.getFloatExtra("focusedHeading", (float) C1454l.this.f631l.getApplication().editEngine().editCamera().getHeading());
                        vgIViewPoint.getMPosition().setMSRS(VgSRSConstRefPtr.getNull());
                        vgIViewPoint.getMPosition().setMYOrLatitude((double) floatExtra);
                        vgIViewPoint.getMPosition().setMXOrLongitude((double) floatExtra2);
                        vgIViewPoint.getMPosition().setMZOrAltitude((double) floatExtra3);
                        vgIViewPoint.setMPitch((double) floatExtra4);
                        vgIViewPoint.setMHeading((double) floatExtra5);
                        C1454l.this.mo28491a(vgIViewPoint, str, booleanExtra);
                        return;
                    }
                }
                String str4 = "focusedPlace";
                if (this.f648a.hasExtra(str4)) {
                    VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(C1454l.this.f630k.editModuleManager().queryModule("Map"));
                    if (castToIMapModule != null) {
                        VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
                        if (castToIMapModule.queryPOIDescriptor(this.f648a.getStringExtra(str4), vgPOIDescriptor)) {
                            VgIViewPoint viewpointFromPositions = C1454l.this.f630k.editEngine().editCamera().getViewpointFromPositions(vgPOIDescriptor.getMBoundingPositions(), 0, 0, 0, 0, -75.0d, vgPOIDescriptor.getMHeading());
                            double d = 20.0d;
                            Venue currentVenue = NaviBeesManager.getInstance(C1454l.this.f634o).getMetaDataManager().getCurrentVenue();
                            if (currentVenue != null) {
                                d = (double) currentVenue.defaultZoom;
                            }
                            viewpointFromPositions.getMPosition().setMZOrAltitude(d);
                            C1454l.this.mo28491a(viewpointFromPositions, str, true);
                        }
                    }
                }
            }
        }

        C1456b() {
        }

        public void onReceive(Context context, Intent intent) {
            C1454l.this.f631l.queueEvent(new C1457a(intent));
        }
    }

    /* renamed from: com.navibees.maps.l$c */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    static /* synthetic */ class C1458c {

        /* renamed from: a */
        static final /* synthetic */ int[] f650a = new int[C1516a.values().length];

        static {
            try {
                f650a[C1516a.eVgViewModeGlobal.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f650a[C1516a.eVgViewModeBuilding.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f650a[C1516a.eVgViewModeFloor.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.navibees.maps.l$d */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    class C1459d extends VgManipulatorListener {

        /* renamed from: a */
        VgIApplication f651a;

        /* renamed from: b */
        int f652b = 0;

        /* renamed from: c */
        boolean f653c = false;

        /* renamed from: d */
        boolean f654d = false;

        /* renamed from: e */
        float f655e = 0.0f;

        /* renamed from: f */
        long f656f;

        /* renamed from: g */
        long f657g;

        C1459d(VgIApplication vgIApplication, long j, long j2) {
            this.f651a = vgIApplication;
            this.f656f = j;
            this.f657g = j2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo28514a() {
            if (this.f653c && this.f652b != 0) {
                C1454l lVar = C1454l.this;
                lVar.mo28494a((CharSequence) lVar.mo28363a(), true);
            }
            this.f652b = 0;
            this.f653c = false;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public void mo28516b() {
            if (this.f654d) {
                VgIViewPoint b = C1454l.this.mo28499b(C1516a.eVgViewModeBuilding);
                C1454l lVar = C1454l.this;
                lVar.mo28490a(b, (float) lVar.mo28479a(C1463h.eAnimateCancelPinchDuration), true, false);
            }
            this.f654d = false;
        }

        public void onClick(float f, float f2) {
            if (C1454l.this.mo28505d() != null) {
                C1516a aVar = C1516a.eVgViewModeBuilding;
                C1454l lVar = C1454l.this;
                int i = lVar.m367a(lVar.mo28507e()).f943f;
                if (f2 < 0.33f) {
                    i++;
                } else if (f2 > 0.66f) {
                    i--;
                } else {
                    aVar = C1516a.eVgViewModeFloor;
                }
                C1551c a = C1454l.this.mo28505d().mo28699a(i);
                if (a != null) {
                    Intent intent = new Intent("exploreRequest");
                    intent.putExtra("view", aVar);
                    intent.putExtra("focusedFloor", a.f938a);
                    C1454l.this.f643x.sendBroadcast(intent);
                }
            }
        }

        public void onDoubleClick(float f, float f2) {
        }

        public void onSimpleDrag(VgGestureState vgGestureState, long j, float f, float f2) {
            if (j == 1) {
                if (this.f654d) {
                    mo28516b();
                } else if (vgGestureState == VgGestureState.eVgGestureEnd) {
                    mo28514a();
                } else {
                    if (vgGestureState == VgGestureState.eVgGestureBegin) {
                        this.f653c = true;
                    }
                    if (this.f653c && C1454l.this.mo28505d() != null) {
                        this.f652b += (int) f2;
                        C1454l lVar = C1454l.this;
                        C1551c a = lVar.m367a(lVar.mo28507e());
                        double a2 = ((double) this.f657g) * C1454l.this.mo28479a(C1463h.eChangeLayerTriggerThreshold);
                        C1551c a3 = C1454l.this.mo28505d().mo28699a((((float) this.f652b) > 0.0f ? 1 : (((float) this.f652b) == 0.0f ? 0 : -1)) < 0 ? a.f943f + 1 : a.f943f - 1);
                        if (a3 == null || ((double) Math.abs(this.f652b)) <= a2) {
                            double a4 = (C1454l.this.mo28479a(C1463h.eLayerDistance) * ((double) f2)) / ((double) (((float) this.f657g) * 0.2f));
                            StringBuilder sb = new StringBuilder();
                            sb.append("dragging les than threshold. Offset=");
                            sb.append(a4);
                            Log.i("Vg", sb.toString());
                            List a5 = C1454l.this.mo28505d().mo28700a();
                            for (int size = a5.size() - 1; size >= 0; size--) {
                                VgLayerRefPtr editLayer = C1454l.this.f630k.editEngine().editLayerManager().editLayer(((C1551c) a5.get(size)).f939b);
                                VgPosition vgPosition = new VgPosition(editLayer.getPosition());
                                vgPosition.setMZOrAltitude(vgPosition.getMZOrAltitude() + a4);
                                editLayer.setPosition(vgPosition);
                            }
                            if (C1454l.this.f627h.f927c != null && !C1454l.this.f627h.f927c.isEmpty()) {
                                VgLayerRefPtr editLayer2 = C1454l.this.f630k.editEngine().editLayerManager().editLayer(C1454l.this.f627h.f927c);
                                VgPosition vgPosition2 = new VgPosition(editLayer2.getPosition());
                                vgPosition2.setMZOrAltitude(vgPosition2.getMZOrAltitude() + a4);
                                editLayer2.setPosition(vgPosition2);
                            }
                        } else {
                            Intent intent = new Intent("exploreRequest");
                            intent.putExtra("focusedFloor", a3.f938a);
                            C1454l.this.f643x.sendBroadcast(intent);
                            this.f652b = 0;
                        }
                    }
                }
            }
        }

        public void onSimplePinch(VgGestureState vgGestureState, float f, float f2) {
            if (this.f653c) {
                mo28514a();
            } else if (vgGestureState == VgGestureState.eVgGestureEnd) {
                mo28516b();
            } else {
                if (vgGestureState == VgGestureState.eVgGestureBegin) {
                    this.f654d = true;
                    this.f655e = f;
                }
                if (this.f654d && C1454l.this.mo28505d() != null) {
                    float max = (float) Math.max(this.f656f, this.f657g);
                    float f3 = this.f655e - f;
                    VgPositionToolbox positionToolbox = this.f651a.editEngine().getPositionToolbox();
                    VgICamera editCamera = this.f651a.editEngine().editCamera();
                    VgIViewPoint b = C1454l.this.mo28499b(C1516a.eVgViewModeBuilding);
                    double computeDistance = positionToolbox.computeDistance(C1454l.this.f621b, b.getMPosition());
                    editCamera.setPosition(positionToolbox.offsetPosition(b.getMPosition(), b.getMHeading(), b.getMPitch(), (((-computeDistance) * 0.75d) * ((double) f3)) / ((double) max)));
                }
            }
        }

        /* renamed from: a */
        public void mo28515a(long j, long j2) {
            this.f656f = j;
            this.f657g = j2;
        }
    }

    /* renamed from: com.navibees.maps.l$e */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    class C1460e extends VgIAnimationCallback {

        /* renamed from: a */
        protected VgLayerRefPtr f659a;

        C1460e(C1454l lVar, VgLayerRefPtr vgLayerRefPtr) {
            this.f659a = vgLayerRefPtr;
        }

        public void onFinish(VgAnimationRefPtr vgAnimationRefPtr) {
            vgAnimationRefPtr.stop();
            VgLayerRefPtr vgLayerRefPtr = this.f659a;
            if (vgLayerRefPtr != null && vgLayerRefPtr.isValid()) {
                if (this.f659a.isVisible()) {
                    this.f659a.setVisible(false);
                }
                this.f659a.setAnimation(VgAnimationRefPtr.getNull());
                this.f659a.setHostedVisible(false);
            }
        }
    }

    /* renamed from: com.navibees.maps.l$f */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    class C1461f extends VgIAnimationCallback {

        /* renamed from: a */
        private boolean f660a = false;

        C1461f(boolean z) {
            this.f660a = z;
        }

        public void onFinish(VgAnimationRefPtr vgAnimationRefPtr) {
            vgAnimationRefPtr.stop();
            if (this.f660a) {
                C1454l lVar = C1454l.this;
                lVar.mo28506d(lVar.mo28405c());
            }
        }
    }

    /* renamed from: com.navibees.maps.l$g */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    class C1462g extends VgIAnimationCallback {

        /* renamed from: a */
        private boolean f662a = false;

        C1462g(boolean z) {
            this.f662a = z;
        }

        public void onFinish(VgAnimationRefPtr vgAnimationRefPtr) {
            vgAnimationRefPtr.stop();
            C1454l lVar = C1454l.this;
            lVar.mo28508e(lVar.mo28405c());
            if (this.f662a) {
                C1454l lVar2 = C1454l.this;
                lVar2.mo28506d(lVar2.mo28405c());
            }
        }
    }

    /* renamed from: com.navibees.maps.l$h */
    /* compiled from: VgMyStackedLayerAndCameraHandler */
    public enum C1463h {
        eFarAwayHeight,
        eStackViewHeading,
        eStackViewPitch,
        eLayerMaxSize,
        eStackViewDistance,
        eLayerDistance,
        eLayerDistanceFromCurrent,
        eAnimateToDetailCameraDuration,
        eAnimateToGlobalCameraDuration,
        eAnimateToGlobalLayerDuration,
        eAnimateToDetailLayerDuration,
        eAnimateChangeOfLayerDuration,
        eAnimateCancelPinchDuration,
        eAnimateCancelDragDuration,
        eAnimateMaxViewPointDuration,
        eChangeLayerTriggerThreshold,
        eStackNonActiveLayerAngle,
        eStackActiveLayerAngle,
        eViewpointMinimumAltitude,
        eMaxDoubleParams
    }

    public C1454l(VgMySurfaceView vgMySurfaceView, C1517f fVar, long j, long j2, Application application) {
        new VgIViewPoint();
        this.f644y = new HashMap<>();
        this.f645z = new HashMap<>();
        this.f617A = false;
        this.f618B = new C1455a();
        this.f619C = new C1456b();
        this.f631l = vgMySurfaceView;
        this.f630k = this.f631l.getApplication();
        this.f634o = application;
        this.f637r = fVar;
        this.f643x = LocalBroadcastManager.getInstance(this.f631l.getContext());
        this.f643x.registerReceiver(this.f619C, new IntentFilter("explore"));
        this.f643x.registerReceiver(this.f618B, new IntentFilter("parametersLoaded"));
        this.f623d = 0;
        this.f624e = false;
        this.f642w = this.f630k.editManipulatorManager().editManipulatorObject("simpleGesture");
        C1459d dVar = new C1459d(this.f630k, j, j2);
        this.f638s = dVar;
        this.f639t = new VgManipulatorListenerRefPtr((VgManipulatorListener) this.f638s);
        this.f642w.setListener(this.f639t);
        mo28510g();
        this.f635p = null;
        this.f620a = new VgIViewPoint(this.f630k.editEngine().editCamera().getViewpoint());
        mo28504c(j, j2);
    }

    public void release() {
        HashMap<String, VgSpatialList> hashMap = this.f645z;
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                int size = (int) ((VgSpatialList) entry.getValue()).size();
                for (int i = 0; i < size; i++) {
                    ((VgSpatialList) entry.getValue()).get(0).set(null);
                }
                ((VgSpatialList) entry.getValue()).clear();
            }
            this.f645z.clear();
        }
        VgManipulator vgManipulator = this.f642w;
        if (vgManipulator != null) {
            vgManipulator.setListener(VgManipulatorListenerRefPtr.getNull());
            this.f642w = null;
        }
        VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr = this.f639t;
        if (vgManipulatorListenerRefPtr != null && vgManipulatorListenerRefPtr.isValid()) {
            this.f639t.set(null);
            this.f638s = null;
        }
        VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr = this.f635p;
        if (vgIAnimationCallbackRefPtr != null && vgIAnimationCallbackRefPtr.isValid()) {
            this.f635p.set(null);
        }
        ArrayList<VgIAnimationCallbackRefPtr> arrayList = this.f636q;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((VgIAnimationCallbackRefPtr) it.next()).set(null);
            }
            this.f636q.clear();
        }
        VgIApplication vgIApplication = this.f630k;
        if (vgIApplication != null) {
            VgLayerVector editLayers = vgIApplication.editEngine().editLayerManager().editLayers();
            for (int i2 = 0; ((long) i2) < editLayers.size(); i2++) {
                editLayers.get(i2).setAnimation("", VgAnimationRefPtr.getNull());
            }
            this.f630k.editEngine().editAnimationManager().setCameraAnimation(VgAnimationRefPtr.getNull());
            this.f630k = null;
        }
        LocalBroadcastManager localBroadcastManager = this.f643x;
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(this.f619C);
            this.f643x.unregisterReceiver(this.f618B);
            this.f643x = null;
        }
        this.f631l = null;
        this.f634o = null;
    }

    /* renamed from: b */
    private boolean m374b(int i) {
        return m371a(i) || i == this.f629j;
    }

    /* renamed from: h */
    private void m382h() {
        VgLayerVector editLayers = this.f630k.editEngine().editLayerManager().editLayers();
        this.f636q = new ArrayList<>((int) editLayers.size());
        for (int i = 0; ((long) i) < editLayers.size(); i++) {
            this.f636q.add(VgIAnimationCallbackRefPtr.getNull());
        }
        for (int i2 = 0; ((long) i2) < editLayers.size(); i2++) {
            VgLayerRefPtr vgLayerRefPtr = editLayers.get(i2);
            String name = vgLayerRefPtr.getName();
            this.f641v.put(name, Float.valueOf(vgLayerRefPtr.getLoadFactor()));
            this.f640u.put(name, Float.valueOf(vgLayerRefPtr.getLODFactor()));
        }
    }

    /* renamed from: c */
    public C1516a mo28405c() {
        return this.f625f;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public void mo28506d(C1516a aVar) {
        C1517f fVar = this.f637r;
        if (fVar != null) {
            fVar.modeDidChange(this, aVar);
        }
    }

    /* renamed from: e */
    public void mo28508e(C1516a aVar) {
        int i = C1458c.f650a[aVar.ordinal()];
        String str = "2D";
        if (i == 1) {
            this.f630k.editManipulatorManager().selectManipulator(str);
        } else if (i == 2) {
            this.f630k.editManipulatorManager().selectManipulator("simpleGesture");
        } else if (i == 3) {
            this.f630k.editManipulatorManager().editManipulatorObject(str);
            this.f630k.editManipulatorManager().selectManipulator(str);
        }
    }

    /* renamed from: f */
    public long mo28509f() {
        C1548b bVar = this.f628i;
        return bVar != null ? (long) bVar.f937h.size() : this.f630k.editEngine().editLayerManager().getLayers().size();
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public void mo28510g() {
        float f;
        VgManipulator editManipulatorObject;
        VgIEngine editEngine = this.f630k.editEngine();
        VgPosition vgPosition = new VgPosition(editEngine.getPositionToolbox().getSceneSRS());
        VgIDatabaseDatasetDescriptor currentDatasetDescriptor = editEngine.editDatabase().getCurrentDatasetDescriptor();
        if (currentDatasetDescriptor != null) {
            f = (float) editEngine.getPositionToolbox().computeDistance(currentDatasetDescriptor.getMMinimum(), currentDatasetDescriptor.getMMaximum());
            if (((double) f) > 0.001d) {
                vgPosition.setMSRS(currentDatasetDescriptor.getMMinimum().getMSRS());
                vgPosition.setMXOrLongitude((currentDatasetDescriptor.getMMinimum().getMXOrLongitude() + currentDatasetDescriptor.getMMaximum().getMXOrLongitude()) / 2.0d);
                vgPosition.setMYOrLatitude((currentDatasetDescriptor.getMMinimum().getMYOrLatitude() + currentDatasetDescriptor.getMMaximum().getMYOrLatitude()) / 2.0d);
                vgPosition.setMZOrAltitude((currentDatasetDescriptor.getMMinimum().getMZOrAltitude() + currentDatasetDescriptor.getMMaximum().getMZOrAltitude()) / 2.0d);
                mo28485a(C1463h.eLayerMaxSize, (double) f);
                mo28493a(vgPosition);
                mo28485a(C1463h.eFarAwayHeight, 200.0d);
                mo28485a(C1463h.eStackViewHeading, (double) ((float) editEngine.editCamera().getViewpoint().getMHeading()));
                mo28485a(C1463h.eStackViewPitch, -30.0d);
                mo28485a(C1463h.eChangeLayerTriggerThreshold, 0.1d);
                mo28485a(C1463h.eAnimateToDetailCameraDuration, 0.75d);
                mo28485a(C1463h.eAnimateToGlobalCameraDuration, 1.25d);
                mo28485a(C1463h.eAnimateToGlobalLayerDuration, 1.5d);
                mo28485a(C1463h.eAnimateToDetailLayerDuration, 1.0d);
                mo28485a(C1463h.eAnimateChangeOfLayerDuration, 0.25d);
                mo28485a(C1463h.eAnimateCancelPinchDuration, 0.3d);
                mo28485a(C1463h.eAnimateCancelDragDuration, 0.3d);
                mo28485a(C1463h.eAnimateMaxViewPointDuration, 3.0d);
                mo28485a(C1463h.eStackActiveLayerAngle, 0.0d);
                mo28485a(C1463h.eStackNonActiveLayerAngle, 0.0d);
                editManipulatorObject = this.f630k.editManipulatorManager().editManipulatorObject("2D");
                VgPosition vgPosition2 = new VgPosition();
                VgPosition vgPosition3 = new VgPosition();
                if (editManipulatorObject != null || !editManipulatorObject.getBoundaries(vgPosition3, vgPosition2)) {
                    mo28485a(C1463h.eViewpointMinimumAltitude, editEngine.editCamera().getViewpoint().getMPosition().getMZOrAltitude());
                } else {
                    mo28485a(C1463h.eViewpointMinimumAltitude, vgPosition3.getMZOrAltitude());
                    return;
                }
            }
        }
        f = 700.0f;
        mo28485a(C1463h.eLayerMaxSize, (double) f);
        mo28493a(vgPosition);
        mo28485a(C1463h.eFarAwayHeight, 200.0d);
        mo28485a(C1463h.eStackViewHeading, (double) ((float) editEngine.editCamera().getViewpoint().getMHeading()));
        mo28485a(C1463h.eStackViewPitch, -30.0d);
        mo28485a(C1463h.eChangeLayerTriggerThreshold, 0.1d);
        mo28485a(C1463h.eAnimateToDetailCameraDuration, 0.75d);
        mo28485a(C1463h.eAnimateToGlobalCameraDuration, 1.25d);
        mo28485a(C1463h.eAnimateToGlobalLayerDuration, 1.5d);
        mo28485a(C1463h.eAnimateToDetailLayerDuration, 1.0d);
        mo28485a(C1463h.eAnimateChangeOfLayerDuration, 0.25d);
        mo28485a(C1463h.eAnimateCancelPinchDuration, 0.3d);
        mo28485a(C1463h.eAnimateCancelDragDuration, 0.3d);
        mo28485a(C1463h.eAnimateMaxViewPointDuration, 3.0d);
        mo28485a(C1463h.eStackActiveLayerAngle, 0.0d);
        mo28485a(C1463h.eStackNonActiveLayerAngle, 0.0d);
        editManipulatorObject = this.f630k.editManipulatorManager().editManipulatorObject("2D");
        VgPosition vgPosition22 = new VgPosition();
        VgPosition vgPosition32 = new VgPosition();
        if (editManipulatorObject != null) {
        }
        mo28485a(C1463h.eViewpointMinimumAltitude, editEngine.editCamera().getViewpoint().getMPosition().getMZOrAltitude());
    }

    /* renamed from: c */
    private VgPositionVector m376c(String str) {
        VgPositionVector vgPositionVector = (VgPositionVector) this.f644y.get(str);
        if (vgPositionVector != null) {
            return vgPositionVector;
        }
        VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(this.f630k.editModuleManager().queryModule("Map"));
        VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
        if (str == null || !castToIMapModule.queryPOIDescriptor(str, vgPOIDescriptor)) {
            return null;
        }
        VgPositionVector vgPositionVector2 = new VgPositionVector();
        for (int i = 0; ((long) i) < vgPOIDescriptor.getMBoundingPositions().size(); i++) {
            vgPositionVector2.add(new VgPosition(vgPOIDescriptor.getMBoundingPositions().get(i)));
        }
        this.f644y.put(str, vgPositionVector2);
        return vgPositionVector2;
    }

    /* renamed from: b */
    public VgPosition mo28500b(long j, long j2) {
        VgPosition vgPosition = new VgPosition(this.f630k.editEngine().getPositionToolbox().getSceneSRS());
        C1551c a = m367a(j);
        C1551c a2 = m367a(j2);
        if (j != ((long) this.f629j)) {
            if (!(a == null || a2 == null)) {
                int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                if (i != 0) {
                    if (this.f626g.f912b.f916c != C1540b.eLego) {
                        vgPosition.setMZOrAltitude(i < 0 ? -mo28479a(C1463h.eFarAwayHeight) : mo28479a(C1463h.eFarAwayHeight));
                    } else if (a.f943f > a2.f943f) {
                        vgPosition.setMZOrAltitude(mo28479a(C1463h.eFarAwayHeight));
                    } else {
                        vgPosition = mo28481a(j, j2);
                        vgPosition.setMZOrAltitude(vgPosition.getMZOrAltitude() - 0.10000000149011612d);
                    }
                }
            }
            vgPosition.setMZOrAltitude(0.0d);
        } else if (this.f626g.f912b.f916c == C1540b.eLego) {
            C1548b bVar = this.f628i;
            if (bVar != null) {
                return mo28500b(mo28480a(bVar.f935f), j2);
            }
        }
        return vgPosition;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public C1548b mo28505d() {
        return this.f628i;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo28498a(String str, boolean z) {
        C1548b bVar = this.f628i;
        if (bVar != null && bVar.f930a.equals(str)) {
            return true;
        }
        C1548b bVar2 = (C1548b) this.f627h.f929e.get(str);
        if (bVar2 != null) {
            return mo28497a(bVar2, bVar2.f935f, z);
        }
        return mo28497a(bVar2, this.f627h.f927c, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public VgPosition mo28482a(C1548b bVar) {
        if (bVar != null && bVar.f930a.equals("<Building>")) {
            return null;
        }
        VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(this.f630k.editModuleManager().queryModule("Map"));
        if (castToIMapModule != null) {
            VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
            if (castToIMapModule.queryPOIDescriptor(bVar != null ? bVar.f930a : this.f627h.f927c, vgPOIDescriptor)) {
                return new VgPosition(vgPOIDescriptor.getMCenter());
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public long mo28507e() {
        return this.f623d;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        if (mo28496a(r6, r0) != false) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double mo28503c(com.navibees.visioglobe.p020g.C1515e.C1516a r6) {
        /*
            r5 = this;
            com.navibees.visioglobe.utils.e r0 = new com.navibees.visioglobe.utils.e
            r0.<init>()
            com.visioglobe.libVisioMove.VgIApplication r1 = r5.f630k
            com.visioglobe.libVisioMove.VgIModuleManager r1 = r1.editModuleManager()
            java.lang.String r2 = "Map"
            com.visioglobe.libVisioMove.VgIModule r1 = r1.queryModule(r2)
            com.visioglobe.libVisioMove.VgIMapModule r1 = com.visioglobe.libVisioMove.C1732libVisioMove.castToIMapModule(r1)
            if (r1 != 0) goto L_0x001a
            r0 = 0
            return r0
        L_0x001a:
            int[] r2 = com.navibees.maps.C1454l.C1458c.f650a
            int r6 = r6.ordinal()
            r6 = r2[r6]
            r2 = 1
            if (r6 == r2) goto L_0x0037
            r1 = 2
            if (r6 == r1) goto L_0x002c
            r1 = 3
            if (r6 == r1) goto L_0x002c
            goto L_0x004e
        L_0x002c:
            com.navibees.visioglobe.utils.f$b r6 = r5.f628i
            if (r6 == 0) goto L_0x004e
            boolean r6 = r5.mo28496a(r6, r0)
            if (r6 == 0) goto L_0x004e
            goto L_0x004f
        L_0x0037:
            com.visioglobe.libVisioMove.VgPOIDescriptor r6 = new com.visioglobe.libVisioMove.VgPOIDescriptor
            r6.<init>()
            com.navibees.visioglobe.utils.f r3 = r5.f627h
            java.lang.String r3 = r3.f927c
            boolean r1 = r1.queryPOIDescriptor(r3, r6)
            if (r1 == 0) goto L_0x004e
            double r3 = r6.getMHeading()
            r0.mo28693a(r3)
            goto L_0x004f
        L_0x004e:
            r2 = 0
        L_0x004f:
            if (r2 != 0) goto L_0x005a
            com.navibees.maps.l$h r6 = com.navibees.maps.C1454l.C1463h.eStackViewHeading
            double r1 = r5.mo28479a(r6)
            r0.mo28693a(r1)
        L_0x005a:
            double r0 = r0.mo28692a()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.C1454l.mo28503c(com.navibees.visioglobe.g.e$a):double");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo28496a(C1548b bVar, C1545e eVar) {
        boolean z = false;
        if (bVar != null && bVar.f930a.equals("<Building>")) {
            return false;
        }
        VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(this.f630k.editModuleManager().queryModule("Map"));
        if (castToIMapModule == null) {
            return false;
        }
        VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
        if (castToIMapModule.queryPOIDescriptor(bVar.f930a, vgPOIDescriptor)) {
            eVar.mo28693a(vgPOIDescriptor.getMHeading());
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo28497a(C1548b bVar, String str, boolean z) {
        C1548b bVar2 = this.f628i;
        if (bVar2 != null) {
            mo28489a(bVar2, true);
        }
        this.f628i = bVar;
        mo28489a(this.f628i, false);
        VgPosition vgPosition = null;
        C1548b bVar3 = this.f628i;
        if (bVar3 != null) {
            if (bVar3.f930a.equalsIgnoreCase("RSMPARK")) {
                vgPosition = new VgPosition(39.109745d, 21.629369d, 0.0d);
            } else if (this.f628i.f930a.equalsIgnoreCase("RSM")) {
                vgPosition = new VgPosition(39.11106d, 21.627687d, 0.0d);
            } else {
                vgPosition = mo28482a(this.f628i);
            }
        }
        if (vgPosition != null) {
            vgPosition.getMXOrLongitude();
            vgPosition.getMYOrLatitude();
            mo28493a(vgPosition);
        }
        VgLayerVector editLayers = this.f630k.editEngine().editLayerManager().editLayers();
        int i = 0;
        while (((long) i) < editLayers.size()) {
            editLayers.get(i).setVisible(i == this.f629j || m371a(i));
            i++;
        }
        C1548b bVar4 = this.f628i;
        if (bVar4 == null || bVar4.f937h.containsKey(str)) {
            mo28494a((CharSequence) str, z);
        } else {
            mo28494a((CharSequence) this.f628i.f935f, z);
        }
        mo28495a(false, z);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ff  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.visioglobe.libVisioMove.VgIViewPoint mo28499b(com.navibees.visioglobe.p020g.C1515e.C1516a r26) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            com.visioglobe.libVisioMove.VgIApplication r2 = r0.f630k
            com.visioglobe.libVisioMove.VgIEngine r2 = r2.editEngine()
            com.visioglobe.libVisioMove.VgICamera r2 = r2.editCamera()
            com.visioglobe.libVisioMove.VgIApplication r3 = r0.f630k
            com.visioglobe.libVisioMove.VgIEngine r3 = r3.editEngine()
            com.visioglobe.libVisioMove.VgPositionToolbox r17 = r3.getPositionToolbox()
            com.visioglobe.libVisioMove.VgIViewPoint r3 = new com.visioglobe.libVisioMove.VgIViewPoint
            r3.<init>()
            com.visioglobe.libVisioMove.VgPOIDescriptor r4 = new com.visioglobe.libVisioMove.VgPOIDescriptor
            r4.<init>()
            com.visioglobe.libVisioMove.VgIApplication r5 = r0.f630k
            com.visioglobe.libVisioMove.VgIModuleManager r5 = r5.editModuleManager()
            java.lang.String r6 = "Map"
            com.visioglobe.libVisioMove.VgIModule r5 = r5.queryModule(r6)
            com.visioglobe.libVisioMove.VgIMapModule r5 = com.visioglobe.libVisioMove.C1732libVisioMove.castToIMapModule(r5)
            double r13 = r25.mo28503c(r26)
            com.navibees.visioglobe.g.e$a r6 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeBuilding
            if (r1 != r6) goto L_0x004c
            com.navibees.visioglobe.utils.d r6 = r0.f626g
            if (r6 == 0) goto L_0x0044
            com.navibees.visioglobe.utils.d$b r6 = r6.f912b
            double r6 = r6.f915b
            r11 = r6
            goto L_0x0053
        L_0x0044:
            com.navibees.maps.l$h r6 = com.navibees.maps.C1454l.C1463h.eStackViewPitch
            double r6 = r0.mo28479a(r6)
            r11 = r6
            goto L_0x0053
        L_0x004c:
            com.visioglobe.libVisioMove.VgIViewPoint r6 = r0.f620a
            double r6 = r6.getMPitch()
            r11 = r6
        L_0x0053:
            com.navibees.visioglobe.g.e$a r6 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeFloor
            r18 = 1
            r19 = 2
            if (r1 != r6) goto L_0x0090
            if (r5 == 0) goto L_0x0090
            java.lang.String r6 = r25.mo28363a()
            boolean r5 = r5.queryPOIDescriptor(r6, r4)
            if (r5 == 0) goto L_0x0090
            com.visioglobe.libVisioMove.VgPositionVector r4 = r4.getMBoundingPositions()
            long r5 = r4.size()
            int r7 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r7 <= 0) goto L_0x008b
            r5 = 20
            r7 = 20
            r9 = 20
            r15 = 20
            r3 = r2
            r21 = r11
            r11 = r15
            r23 = r13
            r13 = r21
            r15 = r23
            com.visioglobe.libVisioMove.VgIViewPoint r3 = r3.getViewpointFromPositions(r4, r5, r7, r9, r11, r13, r15)
            r4 = 1
            goto L_0x0095
        L_0x008b:
            r21 = r11
            r23 = r13
            goto L_0x0094
        L_0x0090:
            r21 = r11
            r23 = r13
        L_0x0094:
            r4 = 0
        L_0x0095:
            if (r4 != 0) goto L_0x00ce
            com.navibees.visioglobe.utils.f$b r5 = r0.f628i
            if (r5 == 0) goto L_0x00ce
            java.lang.String r5 = r5.f930a
            java.lang.String r6 = "<Building>"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x00ce
            com.navibees.visioglobe.utils.f$b r5 = r0.f628i
            java.lang.String r5 = r5.f930a
            com.visioglobe.libVisioMove.VgPositionVector r5 = r0.m376c(r5)
            if (r5 == 0) goto L_0x00ce
            long r6 = r5.size()
            int r8 = (r6 > r19 ? 1 : (r6 == r19 ? 0 : -1))
            if (r8 <= 0) goto L_0x00ce
            r6 = 20
            r8 = 20
            r10 = 20
            r12 = 20
            r3 = r2
            r4 = r5
            r5 = r6
            r7 = r8
            r9 = r10
            r11 = r12
            r13 = r21
            r15 = r23
            com.visioglobe.libVisioMove.VgIViewPoint r3 = r3.getViewpointFromPositions(r4, r5, r7, r9, r11, r13, r15)
            r4 = 1
        L_0x00ce:
            if (r4 != 0) goto L_0x00fb
            com.navibees.visioglobe.utils.f r5 = r0.f627h
            if (r5 == 0) goto L_0x00fb
            java.lang.String r5 = r5.f927c
            com.visioglobe.libVisioMove.VgPositionVector r5 = r0.m376c(r5)
            if (r5 == 0) goto L_0x00fb
            long r6 = r5.size()
            int r8 = (r6 > r19 ? 1 : (r6 == r19 ? 0 : -1))
            if (r8 <= 0) goto L_0x00fb
            r6 = 20
            r8 = 20
            r10 = 20
            r12 = 20
            r3 = r2
            r4 = r5
            r5 = r6
            r7 = r8
            r9 = r10
            r11 = r12
            r13 = r21
            r15 = r23
            com.visioglobe.libVisioMove.VgIViewPoint r3 = r3.getViewpointFromPositions(r4, r5, r7, r9, r11, r13, r15)
            goto L_0x00fd
        L_0x00fb:
            r18 = r4
        L_0x00fd:
            if (r18 != 0) goto L_0x012b
            com.navibees.visioglobe.g.e$a r2 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeBuilding
            if (r1 != r2) goto L_0x0106
            r1 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0108
        L_0x0106:
            r1 = 1061158912(0x3f400000, float:0.75)
        L_0x0108:
            com.visioglobe.libVisioMove.VgPosition r5 = r0.f621b
            com.navibees.maps.l$h r2 = com.navibees.maps.C1454l.C1463h.eStackViewDistance
            double r6 = r0.mo28479a(r2)
            double r6 = -r6
            double r1 = (double) r1
            double r10 = r6 * r1
            r4 = r17
            r6 = r23
            r8 = r21
            com.visioglobe.libVisioMove.VgPosition r1 = r4.offsetPosition(r5, r6, r8, r10)
            r3.setMPosition(r1)
            r1 = r23
            r3.setMHeading(r1)
            r6 = r21
            r3.setMPitch(r6)
        L_0x012b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.C1454l.mo28499b(com.navibees.visioglobe.g.e$a):com.visioglobe.libVisioMove.VgIViewPoint");
    }

    /* renamed from: c */
    public void mo28504c(long j, long j2) {
        this.f631l.pauseRendering();
        if (!this.f617A) {
            VgICamera editCamera = this.f630k.editEngine().editCamera();
            if (j2 > j) {
                editCamera.setFovX(86.199999d);
            } else {
                editCamera.setFovX(Math.atan((double) ((float) ((Math.tan(0.7522368988829098d) / ((double) j2)) * ((double) j)))) * 2.0d * 57.29577951308232d);
            }
            this.f617A = true;
        }
        this.f632m = j;
        this.f633n = j2;
        this.f638s.mo28515a(j, j2);
        mo28501b();
        mo28495a(false, false);
        this.f631l.resumeRendering();
    }

    /* renamed from: a */
    public void mo28487a(C1516a aVar, String str, String str2, boolean z) {
        int i = C1458c.f650a[aVar.ordinal()];
        if (i == 1) {
            mo28488a(C1516a.eVgViewModeGlobal, z);
            mo28497a((C1548b) null, this.f627h.f927c, z);
        } else if (i == 2) {
            mo28488a(C1516a.eVgViewModeBuilding, z);
            mo28498a(str, z);
            if (str2 != null && !str2.equals(mo28363a())) {
                mo28494a((CharSequence) str2, z);
            }
        } else if (i == 3) {
            mo28488a(C1516a.eVgViewModeFloor, false);
            if (this.f628i == null || !str2.equals(mo28363a())) {
                C1548b bVar = (C1548b) this.f627h.f929e.get(str);
                if (bVar == this.f628i) {
                    mo28494a((CharSequence) str2, z);
                } else {
                    mo28497a(bVar, str2, z);
                }
            }
        }
    }

    /* renamed from: b */
    private VgSpatialList m373b(C1548b bVar) {
        VgSpatialList vgSpatialList = (VgSpatialList) this.f645z.get(bVar.f930a);
        if (vgSpatialList != null) {
            return vgSpatialList;
        }
        VgQuery vgQuery = new VgQuery();
        vgQuery.where("ID", Operator.eEquals, bVar.f930a);
        VgSpatialList execute = this.f630k.editEngine().execute(vgQuery);
        this.f645z.put(bVar.f930a, execute);
        return execute;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28501b() {
        float a = (float) mo28479a(C1463h.eLayerMaxSize);
        double tan = Math.tan(((this.f630k.editEngine().editCamera().getFovX() * 3.141592653589793d) / 180.0d) / 2.0d);
        double tan2 = Math.tan(((this.f630k.editEngine().editCamera().getFovY() * 3.141592653589793d) / 180.0d) / 2.0d);
        mo28485a(C1463h.eStackViewDistance, (((double) (a / 2.0f)) / tan) * 0.800000011920929d);
        mo28485a(C1463h.eLayerDistanceFromCurrent, ((tan2 * mo28479a(C1463h.eStackViewDistance)) * 2.0d) / ((double) (mo28509f() + 1)));
        C1546f fVar = this.f627h;
        if (fVar == null || this.f626g.f912b.f916c != C1540b.eLego) {
            mo28485a(C1463h.eLayerDistance, mo28479a(C1463h.eLayerDistanceFromCurrent) * 0.33000001311302185d);
            return;
        }
        double d = 10.0d;
        Iterator it = fVar.f929e.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (((C1548b) entry.getValue()).f937h.size() > 1) {
                List a2 = ((C1548b) entry.getValue()).mo28700a();
                VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(this.f630k.editModuleManager().queryModule("Map"));
                if (castToIMapModule != null) {
                    float[] fArr = new float[1];
                    float[] fArr2 = new float[1];
                    Iterator it2 = a2.iterator();
                    castToIMapModule.getHeightRangeForLayer(((C1551c) it2.next()).f938a, fArr, fArr2);
                    float f = (fArr2[0] + fArr[0]) * 0.5f;
                    castToIMapModule.getHeightRangeForLayer(((C1551c) it2.next()).f938a, fArr, fArr2);
                    d = (double) (((fArr2[0] + fArr[0]) * 0.5f) - f);
                    break;
                }
            }
        }
        mo28485a(C1463h.eLayerDistance, d);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28486a(C1516a aVar) {
        if (this.f641v.size() == 0) {
            m382h();
        }
        VgLayerVector editLayers = this.f630k.editEngine().editLayerManager().editLayers();
        int i = 0;
        while (true) {
            long j = (long) i;
            if (j < editLayers.size()) {
                if (m374b(i)) {
                    VgLayerRefPtr vgLayerRefPtr = editLayers.get(i);
                    vgLayerRefPtr.setHostedVisible(j == this.f623d || i == this.f629j);
                    int i2 = C1458c.f650a[aVar.ordinal()];
                    if (i2 == 1) {
                        vgLayerRefPtr.setLODFactor(0.001f);
                        vgLayerRefPtr.setLoadFactor(0.001f);
                    } else if (i2 == 2 || i2 == 3) {
                        if (j == this.f623d) {
                            vgLayerRefPtr.setLODFactor(((Float) this.f640u.get(vgLayerRefPtr.getName())).floatValue());
                            vgLayerRefPtr.setLoadFactor(((Float) this.f641v.get(vgLayerRefPtr.getName())).floatValue());
                        } else {
                            vgLayerRefPtr.setLODFactor(0.001f);
                            vgLayerRefPtr.setLoadFactor(0.001f);
                            if (i == this.f629j) {
                                vgLayerRefPtr.setLODFactor(10.0f);
                                vgLayerRefPtr.setLoadFactor(10.0f);
                            }
                        }
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private boolean m371a(int i) {
        VgLayerVector layers = this.f630k.editEngine().editLayerManager().getLayers();
        if (i < 0 || ((long) i) >= layers.size()) {
            return false;
        }
        C1548b bVar = this.f628i;
        if (bVar == null || !bVar.f937h.containsKey(layers.get(i).getName())) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo28488a(C1516a aVar, boolean z) {
        if (this.f625f != aVar || !this.f624e) {
            C1517f fVar = this.f637r;
            if (fVar != null) {
                fVar.modeWillChange(this, aVar);
            }
            this.f625f = aVar;
            if (!this.f624e) {
                this.f624e = true;
            }
            mo28495a(true, z);
            if (!z) {
                mo28506d(this.f625f);
            }
        }
    }

    /* renamed from: b */
    public boolean mo28502b(String str) {
        return this.f627h.f929e.containsKey(str);
    }

    /* renamed from: a */
    public String m388a() {
        return this.f630k.editEngine().editLayerManager().editLayers().get((int) this.f623d).getName();
    }

    /* renamed from: a */
    public void mo28494a(CharSequence charSequence, boolean z) {
        VgLayerVector editLayers = this.f630k.editEngine().editLayerManager().editLayers();
        boolean z2 = false;
        int i = 0;
        while (!z2 && ((long) i) < editLayers.size()) {
            if (editLayers.get(i).getName().contentEquals(charSequence)) {
                z2 = true;
            } else {
                i++;
            }
        }
        if (z2) {
            mo28483a((long) i, z);
        }
    }

    /* renamed from: a */
    public void mo28483a(long j, boolean z) {
        VgLayerVector editLayers = this.f630k.editEngine().editLayerManager().editLayers();
        if (j >= 0 && j < editLayers.size()) {
            if (f616D || m371a((int) j)) {
                C1517f fVar = this.f637r;
                if (fVar != null) {
                    long j2 = this.f623d;
                    if (j != j2) {
                        fVar.layerWillChangeFrom(this, editLayers.get((int) j2).getName(), editLayers.get((int) j).getName());
                    }
                }
                mo28484a(j, z, this.f625f);
                this.f623d = j;
                if (this.f624e) {
                    mo28486a(this.f625f);
                    return;
                }
                return;
            }
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public C1551c m367a(long j) {
        VgLayerRefPtr vgLayerRefPtr = this.f630k.editEngine().editLayerManager().editLayers().get((int) j);
        for (Entry value : this.f627h.f929e.entrySet()) {
            Iterator it = ((C1548b) value.getValue()).f937h.entrySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (((C1551c) entry.getValue()).f939b.equals(vgLayerRefPtr.getName())) {
                        return (C1551c) entry.getValue();
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00db A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x013e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo28484a(long r21, boolean r23, com.navibees.visioglobe.p020g.C1515e.C1516a r24) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            r11 = r24
            com.visioglobe.libVisioMove.VgIApplication r0 = r8.f630k
            com.visioglobe.libVisioMove.VgIEngine r0 = r0.editEngine()
            com.visioglobe.libVisioMove.VgLayerManager r0 = r0.editLayerManager()
            com.visioglobe.libVisioMove.VgLayerVector r12 = r0.editLayers()
            com.navibees.visioglobe.utils.f$c r13 = r20.m367a(r21)
            r15 = 0
        L_0x0019:
            long r0 = (long) r15
            long r2 = r12.size()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0147
            com.visioglobe.libVisioMove.VgLayerRefPtr r2 = r12.get(r15)
            com.navibees.visioglobe.utils.f$c r3 = r8.m367a(r0)
            boolean r4 = r8.m371a(r15)
            r5 = 1
            r4 = r4 ^ r5
            com.navibees.visioglobe.utils.d r6 = r8.f626g
            com.navibees.visioglobe.utils.d$b r6 = r6.f912b
            com.navibees.visioglobe.utils.d$b$b r6 = r6.f916c
            com.navibees.visioglobe.utils.d$b$b r7 = com.navibees.visioglobe.utils.C1536d.C1538b.C1540b.eLego
            if (r6 != r7) goto L_0x003c
            r6 = 1
            goto L_0x003d
        L_0x003c:
            r6 = 0
        L_0x003d:
            int r7 = r8.f629j
            if (r15 != r7) goto L_0x0043
            r7 = 1
            goto L_0x0044
        L_0x0043:
            r7 = 0
        L_0x0044:
            boolean r16 = r2.isVisible()
            com.navibees.visioglobe.g.e$a r14 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeGlobal
            if (r11 != r14) goto L_0x004e
            r14 = 1
            goto L_0x004f
        L_0x004e:
            r14 = 0
        L_0x004f:
            com.navibees.visioglobe.g.e$a r5 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeBuilding
            if (r11 != r5) goto L_0x0055
            r5 = 1
            goto L_0x0056
        L_0x0055:
            r5 = 0
        L_0x0056:
            if (r3 == 0) goto L_0x0066
            if (r13 == 0) goto L_0x0066
            r17 = r12
            int r12 = r3.f943f
            r18 = r15
            int r15 = r13.f943f
            if (r12 >= r15) goto L_0x006a
            r12 = 1
            goto L_0x006b
        L_0x0066:
            r17 = r12
            r18 = r15
        L_0x006a:
            r12 = 0
        L_0x006b:
            if (r3 == 0) goto L_0x0077
            if (r13 == 0) goto L_0x0077
            int r3 = r3.f943f
            int r15 = r13.f943f
            if (r3 <= r15) goto L_0x0077
            r3 = 1
            goto L_0x0078
        L_0x0077:
            r3 = 0
        L_0x0078:
            int r15 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r15 != 0) goto L_0x007e
            r15 = 1
            goto L_0x007f
        L_0x007e:
            r15 = 0
        L_0x007f:
            if (r13 == 0) goto L_0x0089
            r19 = r3
            int r3 = r13.f943f
            if (r3 >= 0) goto L_0x008b
            r3 = 1
            goto L_0x008c
        L_0x0089:
            r19 = r3
        L_0x008b:
            r3 = 0
        L_0x008c:
            if (r16 != 0) goto L_0x00c2
            if (r6 == 0) goto L_0x00b6
            if (r3 == 0) goto L_0x00a0
            if (r4 != 0) goto L_0x00c2
            if (r12 != 0) goto L_0x009b
            if (r15 == 0) goto L_0x0099
            goto L_0x009b
        L_0x0099:
            r5 = 1
            goto L_0x00c3
        L_0x009b:
            r5 = 1
            r2.setVisible(r5)
            goto L_0x00c3
        L_0x00a0:
            r5 = 1
            if (r7 == 0) goto L_0x00a7
            r2.setVisible(r5)
            goto L_0x00c3
        L_0x00a7:
            if (r14 != 0) goto L_0x00b4
            if (r4 != 0) goto L_0x00b4
            if (r12 != 0) goto L_0x00af
            if (r15 == 0) goto L_0x00b4
        L_0x00af:
            r5 = 1
            r2.setVisible(r5)
            goto L_0x00c3
        L_0x00b4:
            r5 = 1
            goto L_0x00c3
        L_0x00b6:
            if (r5 != 0) goto L_0x00bd
            if (r15 == 0) goto L_0x00bb
            goto L_0x00bd
        L_0x00bb:
            r5 = 1
            goto L_0x00c3
        L_0x00bd:
            r5 = 1
            r2.setVisible(r5)
            goto L_0x00c3
        L_0x00c2:
            r5 = 1
        L_0x00c3:
            com.visioglobe.libVisioMove.VgPosition r12 = r2.getPosition()
            com.navibees.visioglobe.g.e$a r14 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeBuilding
            if (r11 != r14) goto L_0x00d0
            com.visioglobe.libVisioMove.VgPosition r0 = r8.mo28481a(r0, r9)
            goto L_0x00d4
        L_0x00d0:
            com.visioglobe.libVisioMove.VgPosition r0 = r8.mo28500b(r0, r9)
        L_0x00d4:
            r14 = r0
            boolean r0 = r2.isVisible()
            if (r0 == 0) goto L_0x013e
            if (r6 == 0) goto L_0x00ed
            if (r3 == 0) goto L_0x00e6
            if (r7 != 0) goto L_0x00e5
            if (r19 != 0) goto L_0x00e5
            if (r4 == 0) goto L_0x00f4
        L_0x00e5:
            goto L_0x00f3
        L_0x00e6:
            if (r7 != 0) goto L_0x00ea
            if (r4 != 0) goto L_0x00e5
        L_0x00ea:
            if (r19 == 0) goto L_0x00f4
            goto L_0x00e5
        L_0x00ed:
            com.navibees.visioglobe.g.e$a r0 = com.navibees.visioglobe.p020g.C1515e.C1516a.eVgViewModeFloor
            if (r11 != r0) goto L_0x00f4
            if (r15 != 0) goto L_0x00f4
        L_0x00f3:
            goto L_0x00f5
        L_0x00f4:
            r5 = 0
        L_0x00f5:
            if (r23 == 0) goto L_0x0130
            if (r5 == 0) goto L_0x010b
            com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr r0 = new com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr
            com.navibees.maps.l$e r1 = new com.navibees.maps.l$e
            r1.<init>(r8, r2)
            r0.<init>(r1)
            r15 = r18
            com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr r0 = r8.m370a(r0, r15)
            r4 = r0
            goto L_0x011d
        L_0x010b:
            r15 = r18
            com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr r0 = new com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr
            com.navibees.maps.l$f r1 = new com.navibees.maps.l$f
            r3 = 0
            r1.<init>(r3)
            r0.<init>(r1)
            com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr r0 = r8.m370a(r0, r15)
            r4 = r0
        L_0x011d:
            com.navibees.maps.l$h r0 = com.navibees.maps.C1454l.C1463h.eAnimateChangeOfLayerDuration
            double r0 = r8.mo28479a(r0)
            float r5 = (float) r0
            r6 = 0
            r7 = 0
            r0 = r20
            r1 = r2
            r2 = r12
            r3 = r14
            r0.mo28492a(r1, r2, r3, r4, r5, r6, r7)
            r0 = 0
            goto L_0x0141
        L_0x0130:
            r15 = r18
            if (r5 == 0) goto L_0x0139
            r0 = 0
            r2.setVisible(r0)
            goto L_0x013a
        L_0x0139:
            r0 = 0
        L_0x013a:
            r2.setPosition(r14)
            goto L_0x0141
        L_0x013e:
            r15 = r18
            r0 = 0
        L_0x0141:
            int r15 = r15 + 1
            r12 = r17
            goto L_0x0019
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.C1454l.mo28484a(long, boolean, com.navibees.visioglobe.g.e$a):void");
    }

    /* renamed from: a */
    public VgPosition mo28481a(long j, long j2) {
        double d;
        long j3 = j;
        long j4 = j2;
        VgPosition vgPosition = new VgPosition(0.0d, 0.0d, 0.0d, this.f630k.editEngine().getPositionToolbox().getSceneSRS());
        C1551c a = m367a(j);
        C1551c a2 = m367a(j4);
        if (j3 != ((long) this.f629j)) {
            if (j3 != j4) {
                if (!m371a((int) j3) || a.f943f > a2.f943f) {
                    double c = 180.0d + mo28503c(this.f625f);
                    double d2 = -(mo28479a(C1463h.eStackViewPitch) + 90.0d);
                    if (this.f626g.f912b.f916c == C1540b.eLego) {
                        d = mo28479a(C1463h.eFarAwayHeight);
                    } else {
                        d = ((double) (((float) mo28479a(C1463h.eLayerDistance)) * ((float) ((a.f943f - a2.f943f) - 1)))) + mo28479a(C1463h.eLayerDistanceFromCurrent);
                    }
                    vgPosition = this.f630k.editEngine().getPositionToolbox().offsetPosition(vgPosition, c, d2, -d);
                } else if (this.f626g.f912b.f916c == C1540b.eLego) {
                    vgPosition.setMZOrAltitude((-(mo28479a(C1463h.eLayerDistance) * ((double) (a2.f943f - a.f943f)))) - 0.10000000149011612d);
                } else {
                    vgPosition.setMZOrAltitude(-(mo28479a(C1463h.eLayerDistanceFromCurrent) + (mo28479a(C1463h.eLayerDistance) * ((double) (a2.f943f - a.f943f)))));
                }
            }
            return vgPosition;
        } else if (a2.f943f < 0) {
            VgPosition vgPosition2 = new VgPosition(0.0d, 0.0d, mo28479a(C1463h.eFarAwayHeight), this.f630k.editEngine().getPositionToolbox().getSceneSRS());
            return vgPosition2;
        } else {
            VgPosition vgPosition3 = new VgPosition(0.0d, 0.0d, (-mo28479a(C1463h.eLayerDistance)) * ((double) a2.f943f), this.f630k.editEngine().getPositionToolbox().getSceneSRS());
            return vgPosition3;
        }
    }

    /* renamed from: a */
    public long mo28480a(String str) {
        VgLayerVector editLayers = this.f630k.editEngine().editLayerManager().editLayers();
        int i = 0;
        while (true) {
            long j = (long) i;
            if (j >= editLayers.size()) {
                return -1;
            }
            if (editLayers.get(i).getName().equals(str)) {
                return j;
            }
            i++;
        }
    }

    /* renamed from: a */
    public void mo28491a(VgIViewPoint vgIViewPoint, String str, boolean z) {
        VgIViewPoint vgIViewPoint2;
        double d;
        C1454l lVar;
        String str2 = str;
        boolean z2 = z;
        if (this.f625f == C1516a.eVgViewModeBuilding) {
            mo28494a((CharSequence) str2, z2);
            return;
        }
        String name = this.f630k.editEngine().editLayerManager().editLayers().get((int) mo28507e()).getName();
        VgPositionToolbox positionToolbox = this.f630k.editEngine().getPositionToolbox();
        VgICamera editCamera = this.f630k.editEngine().editCamera();
        double mHeading = vgIViewPoint.getMHeading();
        double mPitch = vgIViewPoint.getMPitch();
        VgIViewPoint vgIViewPoint3 = new VgIViewPoint(vgIViewPoint);
        double a = mo28479a(C1463h.eViewpointMinimumAltitude);
        if (vgIViewPoint3.getMPosition().getMZOrAltitude() < a) {
            double mZOrAltitude = a - vgIViewPoint3.getMPosition().getMZOrAltitude();
            vgIViewPoint2 = vgIViewPoint3;
            d = mPitch;
            vgIViewPoint2.setMPosition(positionToolbox.offsetPosition(vgIViewPoint.getMPosition(), mHeading, mPitch, mZOrAltitude / Math.sin((3.141592653589793d * mPitch) / 180.0d)));
        } else {
            vgIViewPoint2 = vgIViewPoint3;
            d = mPitch;
        }
        double computeDistance = positionToolbox.computeDistance(vgIViewPoint2.getMPosition(), editCamera.getPosition());
        double heading = editCamera.getHeading();
        double pitch = editCamera.getPitch();
        double a2 = m363a(mHeading, heading);
        double a3 = m363a(d, pitch);
        if (computeDistance >= 0.001d || a2 >= 0.001d || a3 >= 0.001d) {
            boolean z3 = z;
            VgIViewPoint vgIViewPoint4 = vgIViewPoint2;
            String str3 = str;
            VgPositionVector vgPositionVector = new VgPositionVector();
            vgPositionVector.add(vgIViewPoint4.getMPosition());
            vgPositionVector.add(this.f630k.editEngine().editCamera().getPosition());
            positionToolbox.computeDistance(vgPositionVector);
            Math.max(a3, a2);
            if (!name.equals(str3) || !this.f624e) {
                mo28490a(vgIViewPoint4, (float) 1.0d, false, false);
                mo28494a((CharSequence) str3, z3);
                return;
            }
            mo28490a(vgIViewPoint4, (float) 1.0d, z3, false);
            return;
        }
        String str4 = str;
        if (name.equals(str4)) {
            lVar = this;
            if (lVar.f624e) {
                return;
            }
        } else {
            lVar = this;
        }
        lVar.mo28494a((CharSequence) str4, z);
    }

    /* renamed from: a */
    public void mo28376a(VgPosition vgPosition, String str, boolean z) {
        if (this.f630k.editEngine().editLayerManager().editLayer(str) != null) {
            VgICamera editCamera = this.f630k.editEngine().editCamera();
            VgPositionToolbox positionToolbox = this.f630k.editEngine().getPositionToolbox();
            VgPosition vgPosition2 = new VgPosition(vgPosition);
            vgPosition2.setMZOrAltitude(0.0d);
            positionToolbox.geoConvert(vgPosition2, positionToolbox.getSceneSRS());
            VgPositionVector vgPositionVector = new VgPositionVector();
            vgPositionVector.add(vgPosition2);
            VgIViewPoint viewpointFromPositions = editCamera.getViewpointFromPositions(vgPositionVector, 0, 0, 0, 0);
            positionToolbox.convert(viewpointFromPositions.getMPosition(), VgSRSConstRefPtr.getNull());
            Intent intent = new Intent("exploreRequest");
            intent.putExtra("focusedLatitude", (float) viewpointFromPositions.getMPosition().getMYOrLatitude());
            intent.putExtra("focusedLongitude", (float) viewpointFromPositions.getMPosition().getMXOrLongitude());
            intent.putExtra("focusedAltitude", (float) viewpointFromPositions.getMPosition().getMZOrAltitude());
            intent.putExtra("focusedPitch", (float) viewpointFromPositions.getMPitch());
            intent.putExtra("focusedHeading", (float) viewpointFromPositions.getMHeading());
            String str2 = "view";
            if (str.equals(this.f627h.f927c)) {
                intent.putExtra(str2, C1516a.eVgViewModeGlobal);
            } else {
                intent.putExtra(str2, C1516a.eVgViewModeFloor);
                intent.putExtra("focusedFloor", str);
            }
            this.f643x.sendBroadcast(intent);
        }
    }

    /* renamed from: a */
    public void mo28492a(VgLayerRefPtr vgLayerRefPtr, VgPosition vgPosition, VgPosition vgPosition2, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr, float f, float f2, float f3) {
        VgAnimationDescriptorRefPtr create = VgAnimationDescriptor.create();
        create.setMDuration(f);
        VgVectorInterpolationFunctorDescriptorRefPtr create2 = VgVectorInterpolationFunctorDescriptor.create();
        create.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create2));
        create.setMCallback(vgIAnimationCallbackRefPtr);
        create2.setMStartPosition(vgPosition);
        create2.setMEndPosition(vgPosition2);
        if (!(mo28479a(C1463h.eStackActiveLayerAngle) == 0.0d && mo28479a(C1463h.eStackNonActiveLayerAngle) == 0.0d)) {
            VgAxialRotationQuaternionFunctorDescriptorRefPtr create3 = VgAxialRotationQuaternionFunctorDescriptor.create();
            create.getMFunctorDescriptors().set(VgAnimationChannels.getMscOrientationChannel(), new VgFunctorDescriptorRefPtr(create3));
            double c = ((mo28503c(this.f625f) + 90.0d) * 3.141592653589793d) / 180.0d;
            create3.setMAxis(new float[]{(float) Math.sin(c), (float) Math.cos(c), 0.0f});
            create3.setMStartAngle(-f2);
            create3.setMEndAngle(-f3);
            create3.setMCubic(false);
        }
        VgAnimationRefPtr instantiate = this.f630k.editEngine().editInstanceFactory().instantiate(create);
        vgLayerRefPtr.setAnimation("", instantiate);
        instantiate.start();
    }

    /* renamed from: a */
    public void mo28490a(VgIViewPoint vgIViewPoint, float f, boolean z, boolean z2) {
        if (z) {
            VgIViewPoint viewpoint = this.f630k.editEngine().editCamera().getViewpoint();
            VgVectorInterpolationFunctorDescriptorRefPtr create = VgVectorInterpolationFunctorDescriptor.create();
            create.setMStartPosition(viewpoint.getMPosition());
            create.setMEndPosition(vgIViewPoint.getMPosition());
            create.setMCubic(true);
            VgAnimationDescriptorRefPtr create2 = VgAnimationDescriptor.create();
            create2.setMDuration(1.0f);
            create2.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create));
            this.f635p = new VgIAnimationCallbackRefPtr((VgIAnimationCallback) new C1462g(z2));
            create2.setMCallback(this.f635p);
            VgQuaternionInterpolationFunctorDescriptorRefPtr create3 = VgQuaternionInterpolationFunctorDescriptor.create();
            create3.setMCubic(true);
            create3.getMStartOrientation().setMAzimuth((float) viewpoint.getMHeading());
            create3.getMStartOrientation().setMPitch((float) viewpoint.getMPitch());
            create3.getMStartOrientation().setMRoll(0.0f);
            create3.getMEndOrientation().setMAzimuth((float) vgIViewPoint.getMHeading());
            create3.getMEndOrientation().setMPitch((float) vgIViewPoint.getMPitch());
            create3.getMEndOrientation().setMRoll(0.0f);
            create2.getMFunctorDescriptors().set(VgAnimationChannels.getMscOrientationChannel(), new VgFunctorDescriptorRefPtr(create3));
            VgAnimationRefPtr instantiate = this.f630k.editEngine().editInstanceFactory().instantiate(create2);
            this.f630k.editEngine().editAnimationManager().setCameraAnimation(instantiate);
            this.f630k.editManipulatorManager().selectManipulator("animation");
            instantiate.start();
            return;
        }
        int i = C1458c.f650a[this.f625f.ordinal()];
        String str = "2D";
        if (i == 1) {
            this.f630k.editManipulatorManager().selectManipulator(str);
        } else if (i == 2) {
            this.f630k.editManipulatorManager().selectManipulator("simpleGesture");
        } else if (i == 3) {
            this.f630k.editManipulatorManager().selectManipulator(str);
        }
        this.f630k.editEngine().editCamera().setViewpoint(vgIViewPoint);
    }

    /* renamed from: a */
    private VgIAnimationCallbackRefPtr m370a(VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr, int i) {
        this.f636q.add(i, vgIAnimationCallbackRefPtr);
        return vgIAnimationCallbackRefPtr;
    }

    /* renamed from: a */
    public void mo28495a(boolean z, boolean z2) {
        if (this.f624e) {
            double d = 0.0d;
            int i = C1458c.f650a[this.f625f.ordinal()];
            if (i == 1) {
                d = mo28479a(C1463h.eAnimateToDetailCameraDuration);
            } else if (i == 2) {
                d = mo28479a(C1463h.eAnimateToGlobalCameraDuration);
            } else if (i == 3) {
                d = mo28479a(C1463h.eAnimateToDetailCameraDuration);
            }
            mo28490a(mo28499b(this.f625f), (float) d, z2, z);
            if (!(this.f627h == null || this.f626g.f912b.f916c == C1540b.eLego)) {
                mo28484a(this.f623d, z2, this.f625f);
            }
            mo28486a(this.f625f);
            if (!z2) {
                mo28508e(this.f625f);
            }
        }
    }

    /* renamed from: a */
    public void mo28493a(VgPosition vgPosition) {
        VgPositionToolbox positionToolbox = this.f630k.editEngine().getPositionToolbox();
        this.f621b = vgPosition;
        positionToolbox.convert(this.f621b, positionToolbox.getSceneSRS());
    }

    /* renamed from: a */
    public void mo28485a(C1463h hVar, double d) {
        if (hVar.ordinal() < C1463h.eMaxDoubleParams.ordinal()) {
            this.f622c[hVar.ordinal()] = d;
        }
        if (hVar == C1463h.eLayerMaxSize) {
            mo28501b();
        }
    }

    /* renamed from: a */
    public double mo28479a(C1463h hVar) {
        if (hVar.ordinal() < C1463h.eMaxDoubleParams.ordinal()) {
            return this.f622c[hVar.ordinal()];
        }
        return 0.0d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28489a(C1548b bVar, boolean z) {
        if (bVar != null) {
            VgSpatialList b = m373b(bVar);
            for (int i = 0; ((long) i) < b.size(); i++) {
                b.get(i).setVisible(z);
            }
        }
    }

    /* renamed from: a */
    public static double m363a(double d, double d2) {
        while (d >= 360.0d) {
            d -= 360.0d;
        }
        while (d < 0.0d) {
            d += 360.0d;
        }
        while (d2 >= 360.0d) {
            d2 -= 360.0d;
        }
        while (d2 < 0.0d) {
            d2 += 360.0d;
        }
        double abs = Math.abs(d - d2);
        return abs > 180.0d ? 360.0d - abs : abs;
    }
}
