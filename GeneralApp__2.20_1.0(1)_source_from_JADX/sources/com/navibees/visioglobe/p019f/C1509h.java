package com.navibees.visioglobe.p019f;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.C1164R;
import com.navibees.visioglobe.C1477c;
import com.navibees.visioglobe.C1477c.C1480c;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p019f.C1505g.C1507b;
import com.navibees.visioglobe.p020g.C1515e;
import com.navibees.visioglobe.p020g.C1522k;
import com.navibees.visioglobe.p020g.C1527m;
import com.navibees.visioglobe.p020g.C1528n;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgAnchorMode;
import com.visioglobe.libVisioMove.VgAnimationChannels;
import com.visioglobe.libVisioMove.VgAnimationDescriptor;
import com.visioglobe.libVisioMove.VgAnimationDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgAnimationRefPtr;
import com.visioglobe.libVisioMove.VgAxialRotationQuaternionFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIGeometryCallback;
import com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIGeometryEvent;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIModule;
import com.visioglobe.libVisioMove.VgIModuleManager;
import com.visioglobe.libVisioMove.VgIRouteCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIRouteConstRefPtr;
import com.visioglobe.libVisioMove.VgIRouteConverter;
import com.visioglobe.libVisioMove.VgIRouteConverterType;
import com.visioglobe.libVisioMove.VgIRouteDestinationsOrder;
import com.visioglobe.libVisioMove.VgIRouteGeometryDescriptorVector;
import com.visioglobe.libVisioMove.VgIRouteRefPtr;
import com.visioglobe.libVisioMove.VgIRouteRequestParameters;
import com.visioglobe.libVisioMove.VgIRouteRequestType;
import com.visioglobe.libVisioMove.VgIRoutingModule;
import com.visioglobe.libVisioMove.VgIRoutingNodeRefPtr;
import com.visioglobe.libVisioMove.VgIRoutingNodeRefPtrVector;
import com.visioglobe.libVisioMove.VgLoopModes;
import com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgMarkerDescriptorVector;
import com.visioglobe.libVisioMove.VgPOIDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgPositionToolbox;
import com.visioglobe.libVisioMove.VgSRSConstRefPtr;
import com.visioglobe.libVisioMove.VgSinusoidalVectorOffsetFunctorDescriptor;
import com.visioglobe.libVisioMove.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr;
import java.util.List;

/* renamed from: com.navibees.visioglobe.f.h */
/* compiled from: VgMyRoutingHelper */
public class C1509h implements C1527m, C1528n, C1522k {

    /* renamed from: a */
    C1482e f843a = null;

    /* renamed from: b */
    VgMySurfaceView f844b = null;

    /* renamed from: c */
    VgIRoutingModule f845c = null;

    /* renamed from: d */
    VgIMapModule f846d = null;

    /* renamed from: e */
    C1477c f847e = null;

    /* renamed from: f */
    C1505g f848f = null;

    /* renamed from: g */
    C1507b f849g = null;

    /* renamed from: h */
    float f850h;

    /* renamed from: i */
    float f851i;

    /* renamed from: j */
    boolean f852j = false;

    /* renamed from: k */
    private VgIRouteCallbackRefPtr f853k = null;

    /* renamed from: l */
    private C1515e f854l = null;

    /* renamed from: m */
    protected VgIRoutingNodeRefPtr[] f855m = new VgIRoutingNodeRefPtr[8];

    /* renamed from: n */
    VgIRouteRequestParameters f856n;

    /* renamed from: o */
    VgSinusoidalVectorOffsetFunctorDescriptorRefPtr f857o = null;

    /* renamed from: p */
    VgAxialRotationQuaternionFunctorDescriptorRefPtr f858p = null;

    /* renamed from: com.navibees.visioglobe.f.h$a */
    /* compiled from: VgMyRoutingHelper */
    public class C1510a extends VgIGeometryCallback {

        /* renamed from: a */
        private String f859a;

        /* renamed from: b */
        private VgPosition f860b;

        /* renamed from: c */
        private C1515e f861c = null;

        public C1510a(C1509h hVar, C1515e eVar, String str, VgPosition vgPosition) {
            this.f859a = str;
            this.f861c = eVar;
            VgPositionToolbox positionToolbox = hVar.f844b.getApplication().editEngine().getPositionToolbox();
            this.f860b = new VgPosition(vgPosition);
            positionToolbox.convert(this.f860b, VgSRSConstRefPtr.getNull());
        }

        public void handleGeometryEvent(VgIGeometryEvent vgIGeometryEvent) {
            C1515e eVar = this.f861c;
            if (eVar != null) {
                eVar.mo28376a(this.f860b, this.f859a, true);
            }
        }
    }

    public C1509h(VgMySurfaceView vgMySurfaceView, C1482e eVar, C1477c cVar, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr, C1515e eVar2) {
        this.f844b = vgMySurfaceView;
        this.f843a = eVar;
        this.f847e = cVar;
        this.f853k = vgIRouteCallbackRefPtr;
        this.f854l = eVar2;
        this.f848f = new C1505g(this.f844b.getApplication());
        this.f850h = 3.0f;
        this.f851i = 3.5f;
        this.f849g = C1507b.eSimplifiedMultiLinePlusAgent;
        LocalBroadcastManager.getInstance(this.f844b.getContext());
        mo28628d();
        VgMySurfaceView vgMySurfaceView2 = this.f844b;
        if (vgMySurfaceView2 != null) {
            VgIApplication application = vgMySurfaceView2.getApplication();
            if (application != null) {
                VgIModuleManager editModuleManager = application.editModuleManager();
                if (editModuleManager != null) {
                    VgIModule queryModule = editModuleManager.queryModule("Routing");
                    if (queryModule != null) {
                        this.f845c = C1732libVisioMove.castToIRoutingModule(queryModule);
                        if (this.f845c != null) {
                            VgIModule queryModule2 = editModuleManager.queryModule("Map");
                            if (queryModule2 != null) {
                                this.f846d = C1732libVisioMove.castToIMapModule(queryModule2);
                                if (this.f846d != null) {
                                    m535e();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: e */
    private void m535e() {
        String str = "start";
        this.f843a.mo28576a(str, C1164R.C1166drawable.track_start);
        String str2 = "end";
        this.f843a.mo28576a(str2, C1164R.C1166drawable.track_end_red);
        String str3 = "down";
        this.f843a.mo28576a(str3, C1164R.C1166drawable.track_down);
        String str4 = "up";
        this.f843a.mo28576a(str4, C1164R.C1166drawable.track_up);
        this.f843a.mo28576a("track", C1164R.C1166drawable.track);
        this.f843a.mo28576a("trackBase", C1164R.C1166drawable.track_base);
        String str5 = "layerChange";
        this.f843a.mo28576a(str5, C1164R.C1166drawable.track_layer_change);
        String str6 = "modalityChange";
        this.f843a.mo28576a(str6, C1164R.C1166drawable.track_modality_change);
        String str7 = "waypoint";
        this.f843a.mo28576a(str7, C1164R.C1166drawable.track_intermediate_destination);
        String str8 = "green";
        this.f843a.mo28576a(str8, C1164R.C1166drawable.green);
        String str9 = "gray";
        this.f843a.mo28576a(str9, C1164R.C1166drawable.gray);
        String str10 = "red";
        this.f843a.mo28576a(str10, C1164R.C1166drawable.red);
        this.f843a.mo28577a(str, str);
        this.f843a.mo28577a(str2, str2);
        this.f843a.mo28577a(str3, str3);
        this.f843a.mo28577a(str4, str4);
        this.f843a.mo28577a(str5, str5);
        this.f843a.mo28577a(str6, str6);
        this.f843a.mo28577a(str7, str7);
        this.f843a.mo28577a("marker", str2);
        this.f843a.mo28577a(str8, str8);
        this.f843a.mo28577a(str9, str9);
        this.f843a.mo28577a(str10, str10);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo28619a(VgPosition vgPosition) {
        String str;
        VgIMapModule vgIMapModule = this.f846d;
        if (vgIMapModule == null) {
            return null;
        }
        String[] strArr = new String[1];
        if (vgIMapModule.getLayerForPosition(vgPosition, strArr)) {
            str = strArr[0];
        } else {
            str = "";
        }
        return str;
    }

    /* renamed from: a */
    public void mo28381a(boolean z) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public double mo28623b(String str) {
        VgIMapModule vgIMapModule = this.f846d;
        double d = 0.0d;
        if (vgIMapModule == null) {
            return 0.0d;
        }
        float[] fArr = new float[1];
        float[] fArr2 = new float[1];
        if (vgIMapModule.getHeightRangeForLayer(str, fArr, fArr2)) {
            d = (double) ((fArr[0] + fArr2[0]) / 2.0f);
        }
        return d;
    }

    /* renamed from: b */
    public void mo28401b(boolean z) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public String mo28626c(String str) {
        if (this.f846d == null || str == null) {
            return null;
        }
        VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
        this.f846d.queryPOIDescriptor(str, vgPOIDescriptor);
        return vgPOIDescriptor.getMLayerName();
    }

    public void clear() {
        hide();
        C1477c cVar = this.f847e;
        if (cVar != null) {
            cVar.mo28562b("routingObjects");
            this.f847e.mo28562b(Integer.toString(0));
            this.f847e.mo28562b(Integer.toString(7));
            for (int i = 1; i < 7; i++) {
                mo28399b(i);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:112:0x03db  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0401  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean createRouteObjects(com.visioglobe.libVisioMove.VgIRouteConstRefPtr r39) {
        /*
            r38 = this;
            r0 = r38
            if (r39 == 0) goto L_0x0428
            boolean r2 = r39.isValid()
            if (r2 == 0) goto L_0x0428
            com.navibees.visioglobe.c r2 = r0.f847e
            if (r2 == 0) goto L_0x0428
            java.lang.String r3 = "routingObjects"
            r2.mo28563c(r3)
            com.navibees.visioglobe.c r2 = r0.f847e
            r2.mo28562b(r3)
            com.visioglobe.libVisioMove.VgIRouteGeometryDescriptorVector r2 = r38.mo28618a(r39)
            com.visioglobe.libVisioMove.VgPosition r4 = new com.visioglobe.libVisioMove.VgPosition
            r4.<init>()
            r2.size()
            r11 = r4
            r4 = 0
            r8 = 1
            r9 = 0
        L_0x0029:
            long r12 = (long) r4
            long r14 = r2.size()
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x0428
            com.visioglobe.libVisioMove.VgIRouteGeometryDescriptor r14 = r2.get(r4)
            com.visioglobe.libVisioMove.VgLineDescriptorVector r15 = r14.getMLineDescriptors()
            java.lang.String r1 = r14.getMLayerName()
            double r17 = r0.mo28623b(r1)
            r1 = 0
        L_0x0043:
            long r5 = (long) r1
            long r19 = r15.size()
            int r21 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r21 >= 0) goto L_0x0153
            com.visioglobe.libVisioMove.VgLineDescriptorRefPtr r5 = r15.get(r1)
            com.navibees.visioglobe.f.g$c r6 = new com.navibees.visioglobe.f.g$c
            com.navibees.visioglobe.f.g r7 = r0.f848f
            r7.getClass()
            r6.<init>(r7)
            com.visioglobe.libVisioMove.VgColor r7 = new com.visioglobe.libVisioMove.VgColor
            r19 = r15
            r15 = 1065353216(0x3f800000, float:1.0)
            r7.<init>(r15, r15, r15, r15)
            r6.f841c = r7
            com.navibees.visioglobe.e r7 = r0.f843a
            java.lang.String r15 = "trackBase"
            com.visioglobe.libVisioMove.VgITextureRefPtr r7 = r7.mo28578b(r15)
            r6.f839a = r7
            com.navibees.visioglobe.e r7 = r0.f843a
            java.lang.String r15 = "track"
            com.visioglobe.libVisioMove.VgITextureRefPtr r7 = r7.mo28578b(r15)
            r6.f840b = r7
            float r7 = r0.f850h
            r6.f842d = r7
            com.navibees.visioglobe.f.g r7 = r0.f848f
            r28 = r12
            com.navibees.visioglobe.f.g$b r12 = r0.f849g
            com.navibees.visioglobe.c r13 = r0.f847e
            java.lang.String r27 = r14.getMLayerName()
            r22 = r7
            r23 = r12
            r24 = r6
            r25 = r5
            r26 = r13
            r22.mo28616a(r23, r24, r25, r26, r27)
            r6 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r6)
            if (r8 != 0) goto L_0x0115
            boolean r6 = r7.booleanValue()
            if (r6 == 0) goto L_0x0112
            int r6 = (r17 > r9 ? 1 : (r17 == r9 ? 0 : -1))
            if (r6 == 0) goto L_0x0112
            com.navibees.visioglobe.e r6 = r0.f843a
            com.visioglobe.libVisioMove.VgITextureRefPtr r6 = r6.mo28578b(r15)
            com.visioglobe.libVisioMove.VgLinkDescriptorRefPtr r7 = com.visioglobe.libVisioMove.VgLinkDescriptor.create()
            if (r6 == 0) goto L_0x00bc
            boolean r8 = r6.isValid()
            if (r8 == 0) goto L_0x00bc
            r7.setMTexture(r6)
        L_0x00bc:
            r7.setMSourcePosition(r11)
            com.visioglobe.libVisioMove.VgPosition r6 = r7.getMSourcePosition()
            r8 = 0
            r6.setMZOrAltitude(r8)
            com.visioglobe.libVisioMove.VgPosition r6 = new com.visioglobe.libVisioMove.VgPosition
            com.visioglobe.libVisioMove.VgPositionVector r8 = r5.getMPositions()
            r9 = 0
            com.visioglobe.libVisioMove.VgPosition r8 = r8.get(r9)
            r6.<init>(r8)
            com.navibees.visioglobe.VgMySurfaceView r8 = r0.f844b
            com.visioglobe.libVisioMove.VgIApplication r8 = r8.getApplication()
            com.visioglobe.libVisioMove.VgIEngine r8 = r8.editEngine()
            com.visioglobe.libVisioMove.VgLayerManager r8 = r8.editLayerManager()
            java.lang.String r9 = r14.getMLayerName()
            com.visioglobe.libVisioMove.VgLayerRefPtr r8 = r8.editLayer(r9)
            com.visioglobe.libVisioMove.VgSRSConstRefPtr r8 = r8.getSRS()
            r6.setMSRS(r8)
            r7.setMTargetPosition(r6)
            com.visioglobe.libVisioMove.VgPosition r6 = r7.getMTargetPosition()
            r12 = 0
            r6.setMZOrAltitude(r12)
            r6 = 1092616192(0x41200000, float:10.0)
            r7.setMWidth(r6)
            r8 = 1065353216(0x3f800000, float:1.0)
            r7.setMTextureRatio(r8)
            r7.setMAnimationSpeed(r6)
            com.navibees.visioglobe.c r6 = r0.f847e
            r6.mo28559a(r3, r7)
            goto L_0x0117
        L_0x0112:
            r12 = 0
            goto L_0x0117
        L_0x0115:
            r12 = 0
        L_0x0117:
            com.visioglobe.libVisioMove.VgPositionVector r6 = r5.getMPositions()
            com.visioglobe.libVisioMove.VgPositionVector r5 = r5.getMPositions()
            long r7 = r5.size()
            int r5 = (int) r7
            r7 = 1
            int r5 = r5 - r7
            com.visioglobe.libVisioMove.VgPosition r11 = r6.get(r5)
            com.navibees.visioglobe.VgMySurfaceView r5 = r0.f844b
            com.visioglobe.libVisioMove.VgIApplication r5 = r5.getApplication()
            com.visioglobe.libVisioMove.VgIEngine r5 = r5.editEngine()
            com.visioglobe.libVisioMove.VgLayerManager r5 = r5.editLayerManager()
            java.lang.String r6 = r14.getMLayerName()
            com.visioglobe.libVisioMove.VgLayerRefPtr r5 = r5.editLayer(r6)
            com.visioglobe.libVisioMove.VgSRSConstRefPtr r5 = r5.getSRS()
            r11.setMSRS(r5)
            int r1 = r1 + 1
            r9 = r17
            r15 = r19
            r12 = r28
            r8 = 0
            goto L_0x0043
        L_0x0153:
            r28 = r12
            r12 = 0
            com.navibees.visioglobe.e r1 = r0.f843a
            java.lang.String r5 = "up"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r1 = r1.mo28574a(r5)
            com.navibees.visioglobe.e r5 = r0.f843a
            java.lang.String r6 = "down"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r5 = r5.mo28574a(r6)
            com.navibees.visioglobe.e r6 = r0.f843a
            java.lang.String r7 = "modalityChange"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r6 = r6.mo28574a(r7)
            com.navibees.visioglobe.e r7 = r0.f843a
            java.lang.String r15 = "waypoint"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r7 = r7.mo28574a(r15)
            com.navibees.visioglobe.e r15 = r0.f843a
            java.lang.String r12 = "layerChange"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r12 = r15.mo28574a(r12)
            com.visioglobe.libVisioMove.VgPointDescriptorVector r13 = r14.getMPointDescriptors()
            r15 = 0
        L_0x0184:
            r17 = r8
            r19 = r9
            long r8 = (long) r15
            long r21 = r13.size()
            int r10 = (r8 > r21 ? 1 : (r8 == r21 ? 0 : -1))
            if (r10 >= 0) goto L_0x0419
            com.visioglobe.libVisioMove.VgPointDescriptorRefPtr r10 = r13.get(r15)
            r18 = r6
            r21 = r7
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r10.setMVisibilityRampStartVisible(r6)
            r6 = 4617315517961601024(0x4014000000000000, double:5.0)
            r10.setMVisibilityRampFullyVisible(r6)
            r6 = 4651127699538968576(0x408c200000000000, double:900.0)
            r10.setMVisibilityRampStartInvisible(r6)
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            r10.setMVisibilityRampFullyInvisible(r6)
            r6 = 1120403456(0x42c80000, float:100.0)
            r10.setMGeometryConstantSizeDistance(r6)
            com.visioglobe.libVisioMove.VgAnchorMode r6 = com.visioglobe.libVisioMove.VgAnchorMode.eVgBottomCenter
            r10.setMAnchorPosition(r6)
            r6 = 1
            r10.setMDrawOnTop(r6)
            com.visioglobe.libVisioMove.VgPosition r6 = r10.getMPosition()
            float r7 = r0.f851i
            r22 = r8
            double r7 = (double) r7
            r6.setMZOrAltitude(r7)
            com.visioglobe.libVisioMove.VgMarkerDescriptorVector r6 = new com.visioglobe.libVisioMove.VgMarkerDescriptorVector
            r6.<init>()
            java.lang.String r7 = r14.getMLayerName()
            java.lang.String r8 = r14.getMModality()
            double r24 = r0.mo28623b(r7)
            com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr r26 = com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr.getNull()
            com.visioglobe.libVisioMove.VgIRouteGeometryDescriptor r27 = r2.get(r4)
            r30 = 1
            if (r15 != 0) goto L_0x02d6
            if (r4 != 0) goto L_0x0239
            com.navibees.visioglobe.e r7 = r0.f843a
            java.lang.String r8 = "start"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r7 = r7.mo28574a(r8)
            if (r7 == 0) goto L_0x0227
            boolean r8 = r7.isValid()
            if (r8 == 0) goto L_0x0215
            r6.add(r7)
            com.visioglobe.libVisioMove.VgAnimationRefPtr r9 = r38.mo28617a()
            r30 = r2
            r34 = r3
            r35 = r4
            r32 = r11
            r36 = r14
            r33 = r15
            r2 = r18
            r3 = r21
            goto L_0x03d3
        L_0x0215:
            r30 = r2
            r34 = r3
            r35 = r4
            r32 = r11
            r36 = r14
            r33 = r15
            r2 = r18
            r3 = r21
            goto L_0x03d2
        L_0x0227:
            r30 = r2
            r34 = r3
            r35 = r4
            r32 = r11
            r36 = r14
            r33 = r15
            r2 = r18
            r3 = r21
            goto L_0x03d2
        L_0x0239:
            boolean r9 = r0.f852j
            if (r9 == 0) goto L_0x02c4
            int r9 = r4 + -1
            com.visioglobe.libVisioMove.VgIRouteGeometryDescriptor r9 = r2.get(r9)
            r32 = r11
            java.lang.String r11 = r9.getMLayerName()
            r33 = r15
            com.visioglobe.libVisioMove.VgPointDescriptorVector r15 = r9.getMPointDescriptors()
            com.visioglobe.libVisioMove.VgPointDescriptorVector r22 = r9.getMPointDescriptors()
            long r22 = r22.size()
            r34 = r3
            r35 = r4
            long r3 = r22 - r30
            int r4 = (int) r3
            com.visioglobe.libVisioMove.VgPointDescriptorRefPtr r3 = r15.get(r4)
            com.visioglobe.libVisioMove.VgPosition r3 = r3.getMPosition()
            java.lang.String r4 = r9.getMModality()
            double r22 = r0.mo28623b(r11)
            com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr r9 = new com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr
            com.navibees.visioglobe.f.h$a r15 = new com.navibees.visioglobe.f.h$a
            r36 = r14
            com.navibees.visioglobe.g.e r14 = r0.f854l
            r15.<init>(r0, r14, r11, r3)
            r9.<init>(r15)
            boolean r3 = r11.contentEquals(r7)
            if (r3 != 0) goto L_0x028a
            int r3 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r3 != 0) goto L_0x028a
            r6.add(r12)
            goto L_0x02ba
        L_0x028a:
            boolean r3 = r4.contentEquals(r8)
            if (r3 != 0) goto L_0x0291
        L_0x0290:
            goto L_0x02ba
        L_0x0291:
            int r3 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r3 <= 0) goto L_0x02a6
            if (r1 == 0) goto L_0x0290
            boolean r3 = r1.isValid()
            if (r3 == 0) goto L_0x02ba
            r6.add(r1)
            com.visioglobe.libVisioMove.VgAnchorMode r3 = com.visioglobe.libVisioMove.VgAnchorMode.eVgBottomRight
            r10.setMAnchorPosition(r3)
            goto L_0x02ba
        L_0x02a6:
            int r3 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r3 >= 0) goto L_0x02ba
            if (r5 == 0) goto L_0x02ba
            boolean r3 = r5.isValid()
            if (r3 == 0) goto L_0x02ba
            r6.add(r5)
            com.visioglobe.libVisioMove.VgAnchorMode r3 = com.visioglobe.libVisioMove.VgAnchorMode.eVgBottomLeft
            r10.setMAnchorPosition(r3)
        L_0x02ba:
            r30 = r2
            r15 = r9
            r2 = r18
            r3 = r21
            r9 = 0
            goto L_0x03d5
        L_0x02c4:
            r34 = r3
            r35 = r4
            r32 = r11
            r36 = r14
            r33 = r15
            r30 = r2
            r2 = r18
            r3 = r21
            goto L_0x03d2
        L_0x02d6:
            r34 = r3
            r35 = r4
            r32 = r11
            r36 = r14
            r33 = r15
            long r3 = r13.size()
            long r3 = r3 - r30
            int r9 = (r22 > r3 ? 1 : (r22 == r3 ? 0 : -1))
            if (r9 != 0) goto L_0x03cc
            long r3 = r2.size()
            long r3 = r3 - r30
            int r9 = (r28 > r3 ? 1 : (r28 == r3 ? 0 : -1))
            if (r9 != 0) goto L_0x0323
            com.navibees.visioglobe.e r3 = r0.f843a
            java.lang.String r4 = "end"
            com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr r3 = r3.mo28574a(r4)
            if (r3 == 0) goto L_0x031b
            boolean r4 = r3.isValid()
            if (r4 == 0) goto L_0x0313
            r6.add(r3)
            com.visioglobe.libVisioMove.VgAnimationRefPtr r9 = r38.mo28617a()
            r30 = r2
            r2 = r18
            r3 = r21
            goto L_0x03d3
        L_0x0313:
            r30 = r2
            r2 = r18
            r3 = r21
            goto L_0x03d2
        L_0x031b:
            r30 = r2
            r2 = r18
            r3 = r21
            goto L_0x03d2
        L_0x0323:
            int r4 = r35 + 1
            com.visioglobe.libVisioMove.VgIRouteGeometryDescriptor r3 = r2.get(r4)
            java.lang.String r4 = r3.getMLayerName()
            com.visioglobe.libVisioMove.VgPointDescriptorVector r9 = r3.getMPointDescriptors()
            r11 = 0
            com.visioglobe.libVisioMove.VgPointDescriptorRefPtr r9 = r9.get(r11)
            com.visioglobe.libVisioMove.VgPosition r9 = r9.getMPosition()
            java.lang.String r14 = r3.getMModality()
            double r22 = r0.mo28623b(r4)
            com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr r15 = new com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr
            com.navibees.visioglobe.f.h$a r11 = new com.navibees.visioglobe.f.h$a
            r30 = r2
            com.navibees.visioglobe.g.e r2 = r0.f854l
            r11.<init>(r0, r2, r4, r9)
            r15.<init>(r11)
            boolean r2 = r4.contentEquals(r7)
            if (r2 != 0) goto L_0x0363
            int r2 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r2 != 0) goto L_0x0363
            r6.add(r12)
            r2 = r18
            r3 = r21
            goto L_0x03ca
        L_0x0363:
            boolean r2 = r14.contentEquals(r8)
            if (r2 != 0) goto L_0x0371
            r2 = r18
            r6.add(r2)
        L_0x036e:
            r3 = r21
            goto L_0x03ca
        L_0x0371:
            r2 = r18
            int r4 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r4 <= 0) goto L_0x038b
            if (r1 == 0) goto L_0x036e
            boolean r3 = r1.isValid()
            if (r3 == 0) goto L_0x0388
            r6.add(r1)
            com.visioglobe.libVisioMove.VgAnchorMode r3 = com.visioglobe.libVisioMove.VgAnchorMode.eVgBottomRight
            r10.setMAnchorPosition(r3)
            goto L_0x03a5
        L_0x0388:
            r3 = r21
            goto L_0x03ca
        L_0x038b:
            int r4 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r4 >= 0) goto L_0x03a8
            if (r5 == 0) goto L_0x03a5
            boolean r3 = r5.isValid()
            if (r3 == 0) goto L_0x03a2
            r6.add(r5)
            com.visioglobe.libVisioMove.VgAnchorMode r3 = com.visioglobe.libVisioMove.VgAnchorMode.eVgBottomLeft
            r10.setMAnchorPosition(r3)
            r3 = r21
            goto L_0x03ca
        L_0x03a2:
            r3 = r21
            goto L_0x03ca
        L_0x03a5:
            r3 = r21
            goto L_0x03ca
        L_0x03a8:
            int r3 = r3.getMDestinationIndex()
            int r4 = r27.getMDestinationIndex()
            if (r3 == r4) goto L_0x03c8
            if (r21 == 0) goto L_0x03c8
            boolean r3 = r21.isValid()
            if (r3 == 0) goto L_0x03c5
            r3 = r21
            r6.add(r3)
            com.visioglobe.libVisioMove.VgAnchorMode r4 = com.visioglobe.libVisioMove.VgAnchorMode.eVgBottomCenter
            r10.setMAnchorPosition(r4)
            goto L_0x03ca
        L_0x03c5:
            r3 = r21
            goto L_0x03ca
        L_0x03c8:
            r3 = r21
        L_0x03ca:
            r9 = 0
            goto L_0x03d5
        L_0x03cc:
            r30 = r2
            r2 = r18
            r3 = r21
        L_0x03d2:
            r9 = 0
        L_0x03d3:
            r15 = r26
        L_0x03d5:
            boolean r4 = r6.isEmpty()
            if (r4 != 0) goto L_0x0401
            r10.setMMarkerDescriptors(r6)
            com.navibees.visioglobe.c r4 = r0.f847e
            java.lang.String r6 = r36.getMLayerName()
            r7 = r34
            com.navibees.visioglobe.c$c r4 = r4.mo28557a(r7, r6, r10, r15)
            com.visioglobe.libVisioMove.VgPointRefPtr r6 = r4.f745a
            if (r6 == 0) goto L_0x0403
            if (r9 == 0) goto L_0x0403
            boolean r6 = r9.isValid()
            if (r6 == 0) goto L_0x0403
            com.visioglobe.libVisioMove.VgPointRefPtr r4 = r4.f745a
            java.lang.String r6 = ""
            r4.setAnimation(r6, r9)
            r9.start()
            goto L_0x0403
        L_0x0401:
            r7 = r34
        L_0x0403:
            int r15 = r33 + 1
            r6 = r2
            r8 = r17
            r9 = r19
            r2 = r30
            r11 = r32
            r4 = r35
            r14 = r36
            r37 = r7
            r7 = r3
            r3 = r37
            goto L_0x0184
        L_0x0419:
            r30 = r2
            r7 = r3
            r35 = r4
            r32 = r11
            int r4 = r35 + 1
            r8 = r17
            r9 = r19
            goto L_0x0029
        L_0x0428:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.visioglobe.p019f.C1509h.createRouteObjects(com.visioglobe.libVisioMove.VgIRouteConstRefPtr):boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public VgPosition mo28627d(String str) {
        if (!(this.f846d == null || str == null)) {
            VgPOIDescriptor vgPOIDescriptor = new VgPOIDescriptor();
            if (this.f846d.queryPOIDescriptor(str, vgPOIDescriptor)) {
                double b = mo28623b(vgPOIDescriptor.getMLayerName());
                VgPosition mCenter = vgPOIDescriptor.getMCenter();
                mCenter.setMZOrAltitude(b);
                return mCenter;
            }
        }
        return null;
    }

    public void hide() {
        C1477c cVar = this.f847e;
        if (cVar != null) {
            cVar.mo28563c("routingObjects");
            this.f847e.mo28563c(Integer.toString(0));
            this.f847e.mo28563c(Integer.toString(7));
            for (int i = 1; i < 7; i++) {
                this.f847e.mo28563c(Integer.toString(i));
            }
        }
    }

    public void release() {
        clear();
        VgIRouteRequestParameters vgIRouteRequestParameters = this.f856n;
        if (vgIRouteRequestParameters != null) {
            vgIRouteRequestParameters.setMCallback(VgIRouteCallbackRefPtr.getNull());
            this.f856n = null;
        }
        this.f853k = null;
    }

    public void show() {
        C1477c cVar = this.f847e;
        if (cVar != null) {
            cVar.mo28561a("routingObjects");
            this.f847e.mo28561a(Integer.toString(0));
            this.f847e.mo28561a(Integer.toString(7));
            for (int i = 1; i < 7; i++) {
                this.f847e.mo28561a(Integer.toString(i));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public VgFunctorDescriptorRefPtr mo28625c() {
        if (this.f857o == null) {
            VgSinusoidalVectorOffsetFunctorDescriptorRefPtr create = VgSinusoidalVectorOffsetFunctorDescriptor.create();
            create.setMStartPhase(-3.141592653589793d);
            create.setMEndPhase(3.141592653589793d);
            create.setMBaseVector(new float[]{0.0f, 5.0f, 0.0f});
            create.setMVector(new float[]{0.0f, 2.5f, 0.0f});
            this.f857o = create;
        }
        return new VgFunctorDescriptorRefPtr(this.f857o);
    }

    /* renamed from: d */
    public void mo28628d() {
        this.f856n = new VgIRouteRequestParameters();
    }

    /* renamed from: a */
    public void mo28622a(String str) {
        String c = mo28626c(str);
        VgPointDescriptorRefPtr create = VgPointDescriptor.create();
        create.setMVisibilityRampStartVisible(1.0d);
        create.setMVisibilityRampFullyVisible(5.0d);
        create.setMVisibilityRampStartInvisible(900.0d);
        create.setMVisibilityRampFullyInvisible(1000.0d);
        create.setMGeometryConstantSizeDistance(100.0f);
        create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
        create.setMID(str);
        create.setMPosition(mo28627d(str));
        create.setMDrawOnTop(true);
        VgMarkerDescriptorRefPtr.getNull();
        VgMarkerDescriptorRefPtr a = this.f843a.mo28574a("marker");
        if (a != null) {
            VgMarkerDescriptorVector vgMarkerDescriptorVector = new VgMarkerDescriptorVector();
            vgMarkerDescriptorVector.add(a);
            create.setMMarkerDescriptors(vgMarkerDescriptorVector);
            create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
        }
        String num = Integer.toString(7);
        C1480c a2 = this.f847e.mo28557a(num, c, create, VgIGeometryCallbackRefPtr.getNull());
        this.f847e.mo28561a(num);
        if (a2.f745a != null) {
            VgAnimationRefPtr a3 = mo28617a();
            a2.f745a.setAnimation("", a3);
            a3.start();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo28624b(int i, VgPosition vgPosition, String str) {
        VgIRoutingNodeRefPtr routingNode = this.f845c.getRoutingSolver().getRoutingNode(vgPosition);
        if (!routingNode.isValid()) {
            return false;
        }
        mo28399b(i);
        this.f855m[i] = routingNode;
        mo28621a(i, vgPosition, str);
        return true;
    }

    /* renamed from: b */
    public void mo28399b(int i) {
        this.f855m[i] = VgIRoutingNodeRefPtr.getNull();
        String num = Integer.toString(i);
        this.f847e.mo28563c(num);
        this.f847e.mo28562b(num);
    }

    /* renamed from: b */
    public void mo28398b() {
        if (mo28382a(0) && mo28382a(7)) {
            VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr = this.f853k;
            if (vgIRouteCallbackRefPtr != null && vgIRouteCallbackRefPtr.isValid()) {
                VgIRouteRequestParameters vgIRouteRequestParameters = this.f856n;
                vgIRouteRequestParameters.setMDestinationsOrder(VgIRouteDestinationsOrder.eOptimalFinishOnLast);
                vgIRouteRequestParameters.setMOrigin(this.f855m[0]);
                VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector = new VgIRoutingNodeRefPtrVector();
                for (int i = 1; i < 7; i++) {
                    if (mo28382a(i)) {
                        vgIRoutingNodeRefPtrVector.add(this.f855m[i]);
                    }
                }
                vgIRoutingNodeRefPtrVector.add(this.f855m[7]);
                vgIRouteRequestParameters.setMDestinations(vgIRoutingNodeRefPtrVector);
                vgIRouteRequestParameters.setMCallback(this.f853k);
                this.f845c.getRoutingSolver().computeRoute(vgIRouteRequestParameters);
                mo28399b(0);
                mo28399b(7);
                for (int i2 = 1; i2 < 7; i2++) {
                    mo28399b(i2);
                }
            }
        }
    }

    /* renamed from: c */
    public void mo28406c(boolean z) {
        String[] strArr;
        for (String str : new String[]{"stairs", "stairway", "escalator"}) {
            if (z) {
                this.f856n.getMExcludedAttributes().insert(str);
            } else if (this.f856n.getMExcludedAttributes().has_key(str)) {
                this.f856n.getMExcludedAttributes().del(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public boolean mo28629e(String str) {
        if (str != null) {
            VgIRoutingModule vgIRoutingModule = this.f845c;
            if (vgIRoutingModule != null) {
                return vgIRoutingModule.getRoutingSolver().getRoutingNode(str).isValid();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28621a(int i, VgPosition vgPosition, String str) {
        VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr;
        if (str == null || str.isEmpty()) {
            str = mo28619a(vgPosition);
        }
        VgPointDescriptorRefPtr create = VgPointDescriptor.create();
        create.setMVisibilityRampStartVisible(1.0d);
        create.setMVisibilityRampFullyVisible(5.0d);
        create.setMVisibilityRampStartInvisible(900.0d);
        create.setMVisibilityRampFullyInvisible(1000.0d);
        create.setMGeometryConstantSizeDistance(100.0f);
        create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
        create.setMPosition(vgPosition);
        create.setMDrawOnTop(true);
        VgMarkerDescriptorRefPtr.getNull();
        if (i == 0) {
            vgMarkerDescriptorRefPtr = this.f843a.mo28574a("start");
        } else if (i == 7) {
            vgMarkerDescriptorRefPtr = this.f843a.mo28574a("end");
        } else {
            vgMarkerDescriptorRefPtr = this.f843a.mo28574a("waypoint");
        }
        if (vgMarkerDescriptorRefPtr != null) {
            VgMarkerDescriptorVector vgMarkerDescriptorVector = new VgMarkerDescriptorVector();
            vgMarkerDescriptorVector.add(vgMarkerDescriptorRefPtr);
            create.setMMarkerDescriptors(vgMarkerDescriptorVector);
            create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
        }
        String num = Integer.toString(i);
        C1480c a = this.f847e.mo28557a(num, str, create, VgIGeometryCallbackRefPtr.getNull());
        this.f847e.mo28561a(num);
        if (a.f745a != null) {
            VgAnimationRefPtr a2 = mo28617a();
            a.f745a.setAnimation("", a2);
            a2.start();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public VgAnimationRefPtr mo28617a() {
        VgAnimationDescriptorRefPtr create = VgAnimationDescriptor.create();
        create.setMDuration(2.0f);
        create.setMCallback(VgIAnimationCallbackRefPtr.getNull());
        create.setMLoopMode(VgLoopModes.getMscLoop());
        create.getMFunctorDescriptors().set(VgAnimationChannels.getMscLocalPositionChannel(), mo28625c());
        return this.f844b.getApplication().editEngine().editInstanceFactory().instantiate(create);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public VgIRouteGeometryDescriptorVector mo28618a(VgIRouteConstRefPtr vgIRouteConstRefPtr) {
        VgIRoutingModule vgIRoutingModule = this.f845c;
        if (vgIRoutingModule == null) {
            return null;
        }
        VgIRouteConverter createConverter = vgIRoutingModule.getRouteConverterFactory().createConverter(VgIRouteConverterType.e2D);
        VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector = new VgIRouteGeometryDescriptorVector();
        createConverter.convertRoute(vgIRouteConstRefPtr, vgIRouteGeometryDescriptorVector);
        return vgIRouteGeometryDescriptorVector;
    }

    /* renamed from: a */
    public boolean mo28384a(int i, String str) {
        if (mo28629e(str)) {
            VgIRoutingNodeRefPtr routingNode = this.f845c.getRoutingSolver().getRoutingNode(str);
            if (routingNode.isValid()) {
                mo28399b(i);
                this.f855m[i] = routingNode;
                mo28621a(i, mo28627d(str), mo28626c(str));
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo28383a(int i, VgPosition vgPosition) {
        return mo28624b(i, vgPosition, "");
    }

    /* renamed from: a */
    public boolean mo28382a(int i) {
        VgIRoutingNodeRefPtr[] vgIRoutingNodeRefPtrArr = this.f855m;
        return vgIRoutingNodeRefPtrArr[i] != null && vgIRoutingNodeRefPtrArr[i].isValid();
    }

    /* renamed from: a */
    public String mo28620a(List<String> list) {
        if (list != null && list.size() > 0 && mo28382a(7)) {
            VgIRouteRequestParameters vgIRouteRequestParameters = new VgIRouteRequestParameters();
            vgIRouteRequestParameters.setMDestinationsOrder(VgIRouteDestinationsOrder.eClosest);
            VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector = new VgIRoutingNodeRefPtrVector();
            vgIRouteRequestParameters.setMOrigin(this.f855m[7]);
            vgIRouteRequestParameters.setMRequestType(VgIRouteRequestType.eShortest);
            for (String routingNode : list) {
                VgIRoutingNodeRefPtr routingNode2 = this.f845c.getRoutingSolver().getRoutingNode(routingNode);
                if (routingNode2.isValid()) {
                    mo28399b(0);
                    this.f855m[0] = routingNode2;
                    if (mo28382a(0)) {
                        vgIRoutingNodeRefPtrVector.add(this.f855m[0]);
                    }
                }
            }
            vgIRouteRequestParameters.setMDestinations(vgIRoutingNodeRefPtrVector);
            VgIRouteRefPtr computeRouteDirect = this.f845c.getRoutingSolver().computeRouteDirect(vgIRouteRequestParameters);
            if (computeRouteDirect.isValid()) {
                VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr = computeRouteDirect.getRequestParameters().getMDestinations().get(computeRouteDirect.getDestinationIndices().get(0));
                if (vgIRoutingNodeRefPtr.isValid() && vgIRoutingNodeRefPtr.hasPoiID()) {
                    return vgIRoutingNodeRefPtr.getPoiID();
                }
            }
        }
        return null;
    }
}
