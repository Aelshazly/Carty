package com.navibees.visioglobe.p019f;

import com.navibees.C1164R;
import com.navibees.visioglobe.C1477c;
import com.navibees.visioglobe.C1477c.C1480c;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1512b;
import com.navibees.visioglobe.p020g.C1518g;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgAnchorMode;
import com.visioglobe.libVisioMove.VgAnimationChannels;
import com.visioglobe.libVisioMove.VgAnimationConstRefPtr;
import com.visioglobe.libVisioMove.VgAnimationDescriptor;
import com.visioglobe.libVisioMove.VgAnimationDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgAnimationRefPtr;
import com.visioglobe.libVisioMove.VgFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptor;
import com.visioglobe.libVisioMove.VgLayerRefPtr;
import com.visioglobe.libVisioMove.VgLoopModes;
import com.visioglobe.libVisioMove.VgMarkerDescriptor;
import com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgOrientationType;
import com.visioglobe.libVisioMove.VgPointDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgSRSConstRefPtr;
import com.visioglobe.libVisioMove.VgVectorInterpolationFunctorDescriptor;
import com.visioglobe.libVisioMove.VgVectorInterpolationFunctorDescriptorRefPtr;

/* renamed from: com.navibees.visioglobe.f.a */
/* compiled from: VgMyAvatarDiscDisplay */
public class C1485a implements C1518g, C1512b {

    /* renamed from: j */
    static float f762j = 1.0f;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public VgMySurfaceView f763a;

    /* renamed from: b */
    private C1482e f764b;

    /* renamed from: c */
    private C1477c f765c;

    /* renamed from: d */
    private String f766d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public String f767e = "";
    /* access modifiers changed from: private */

    /* renamed from: f */
    public VgPosition f768f = new VgPosition();
    /* access modifiers changed from: private */

    /* renamed from: g */
    public float f769g = f762j;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f770h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1480c f771i;

    /* renamed from: com.navibees.visioglobe.f.a$a */
    /* compiled from: VgMyAvatarDiscDisplay */
    class C1486a implements Runnable {
        C1486a() {
        }

        public void run() {
            C1485a.this.f768f.setMZOrAltitude(1.0d);
            if (C1485a.this.f771i.f745a.isValid()) {
                VgPosition position = C1485a.this.f771i.f745a.getPosition();
                VgPosition a = C1485a.this.f768f;
                double computeDistance = C1485a.this.f763a.getApplication().editEngine().getPositionToolbox().computeDistance(position, a);
                if (computeDistance < 0.1d) {
                    C1485a.this.f771i.f745a.setPosition(C1485a.this.f768f);
                    return;
                }
                String str = "positionAnim";
                VgAnimationConstRefPtr animation = C1485a.this.f771i.f745a.getAnimation(str);
                if (!animation.isValid() || !animation.isPlaying()) {
                    VgAnimationDescriptorRefPtr create = VgAnimationDescriptor.create();
                    VgVectorInterpolationFunctorDescriptorRefPtr create2 = VgVectorInterpolationFunctorDescriptor.create();
                    create2.setMStartTime(0.0f);
                    create2.setMStartPosition(position);
                    create2.setMEndPosition(a);
                    float log = (float) (Math.log(computeDistance + 1.0d) * 0.05000000074505806d);
                    create2.setMEndTime(log);
                    create2.setMCubic(false);
                    create.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create2));
                    create.setMDuration(log);
                    VgAnimationRefPtr instantiate = C1485a.this.f763a.getApplication().editEngine().editInstanceFactory().instantiate(create);
                    C1485a.this.f771i.f745a.setAnimation(str, instantiate);
                    instantiate.start();
                }
            }
        }
    }

    /* renamed from: com.navibees.visioglobe.f.a$b */
    /* compiled from: VgMyAvatarDiscDisplay */
    class C1487b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f773a;

        C1487b(String str) {
            this.f773a = str;
        }

        public void run() {
            VgLayerRefPtr editLayer = C1485a.this.f763a.getApplication().editEngine().editLayerManager().editLayer(this.f773a);
            if (editLayer.isValid()) {
                C1485a.this.f767e = editLayer.getName();
                C1485a.this.f771i.mo28564a(C1485a.this.f767e, C1485a.this.f770h);
            }
        }
    }

    /* renamed from: com.navibees.visioglobe.f.a$c */
    /* compiled from: VgMyAvatarDiscDisplay */
    class C1488c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ float f775a;

        C1488c(float f) {
            this.f775a = f;
        }

        public void run() {
            VgAnimationDescriptorRefPtr create = VgAnimationDescriptor.create();
            create.setMDuration(0.5f);
            create.setMCallback(VgIAnimationCallbackRefPtr.getNull());
            create.setMLoopMode(VgLoopModes.getMscNoLoop());
            VgVectorInterpolationFunctorDescriptorRefPtr create2 = VgVectorInterpolationFunctorDescriptor.create();
            double d = (double) this.f775a;
            VgSRSConstRefPtr sceneSRS = C1485a.this.f763a.getApplication().editEngine().getPositionToolbox().getSceneSRS();
            VgPosition vgPosition = new VgPosition(d, d, d, sceneSRS);
            create2.setMStartPosition(vgPosition);
            VgPosition vgPosition2 = new VgPosition((double) C1485a.this.f769g, (double) C1485a.this.f769g, (double) C1485a.this.f769g, sceneSRS);
            create2.setMEndPosition(vgPosition2);
            create.getMFunctorDescriptors().set(VgAnimationChannels.getMscLocalScaleChannel(), new VgFunctorDescriptorRefPtr(create2));
            VgAnimationRefPtr instantiate = C1485a.this.f763a.getApplication().editEngine().editInstanceFactory().instantiate(create);
            C1485a.this.f771i.f745a.setAnimation("scaleAnim", instantiate);
            instantiate.start();
        }
    }

    /* renamed from: com.navibees.visioglobe.f.a$d */
    /* compiled from: VgMyAvatarDiscDisplay */
    class C1489d implements Runnable {
        C1489d() {
        }

        public void run() {
            if (C1485a.this.f771i == null) {
                C1485a.this.mo28581a();
            }
            C1485a.this.show();
        }
    }

    /* renamed from: com.navibees.visioglobe.f.a$e */
    /* compiled from: VgMyAvatarDiscDisplay */
    class C1490e implements Runnable {
        C1490e() {
        }

        public void run() {
            C1485a.this.hide();
        }
    }

    public C1485a(VgMySurfaceView vgMySurfaceView, C1482e eVar, C1477c cVar, String str) {
        this.f763a = vgMySurfaceView;
        this.f764b = eVar;
        this.f765c = cVar;
        this.f766d = str;
        mo28582b();
    }

    public void hide() {
        this.f770h = false;
        this.f765c.mo28563c(this.f766d);
    }

    public boolean isVisible() {
        return this.f770h;
    }

    public void release() {
        this.f763a = null;
        this.f764b = null;
        this.f765c = null;
        this.f771i = null;
        this.f768f = null;
    }

    public void show() {
        this.f770h = true;
        if (this.f771i != null) {
            this.f765c.mo28561a(this.f766d);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28582b() {
        C1482e eVar = this.f764b;
        if (eVar != null) {
            String str = "avatar_base";
            if (!eVar.mo28579c(str)) {
                this.f764b.mo28576a(str, C1164R.C1166drawable.avatar_base);
            }
            if (!this.f764b.mo28580d(str)) {
                this.f764b.mo28577a(str, str);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28581a() {
        VgPointDescriptorRefPtr create = VgPointDescriptor.create();
        create.setMVisibilityRampStartVisible(1.0d);
        create.setMVisibilityRampFullyVisible(5.0d);
        create.setMVisibilityRampStartInvisible(900.0d);
        create.setMVisibilityRampFullyInvisible(1000.0d);
        create.setMGeometryConstantSizeDistance(0.0f);
        create.setMHeadingOrientationType(VgOrientationType.eVgOrientationFixed);
        create.setMPitchOrientationType(VgOrientationType.eVgOrientationFixed);
        create.setMRollOrientationType(VgOrientationType.eVgOrientationFixed);
        create.setMAnchorPosition(VgAnchorMode.eVgCenter);
        VgIconMarkerDescriptor castToIconMarkerDescriptor = C1732libVisioMove.castToIconMarkerDescriptor(this.f764b.mo28574a("avatar_base").get());
        castToIconMarkerDescriptor.setMScale(1.0f);
        create.getMMarkerDescriptors().add(new VgMarkerDescriptorRefPtr((VgMarkerDescriptor) castToIconMarkerDescriptor));
        create.setMPosition(this.f768f);
        create.setMDrawOnTop(true);
        create.setMZIndex(-1);
        this.f771i = this.f765c.mo28557a(this.f766d, this.f767e, create, VgIGeometryCallbackRefPtr.getNull());
    }

    /* renamed from: b */
    public void mo28400b(String str) {
        this.f763a.queueEvent(new C1489d());
    }

    /* renamed from: a */
    public void mo28379a(String str, VgPosition vgPosition) {
        this.f768f = new VgPosition(vgPosition);
        this.f763a.queueEvent(new C1486a());
    }

    /* renamed from: a */
    public void mo28380a(String str, String str2) {
        this.f763a.queueEvent(new C1487b(str2));
    }

    /* renamed from: a */
    public void mo28378a(String str, double d) {
        float f = this.f769g;
        this.f769g = Math.max((float) d, f762j);
        this.f763a.queueEvent(new C1488c(f));
    }

    /* renamed from: a */
    public void mo28377a(String str) {
        this.f763a.queueEvent(new C1490e());
    }
}
