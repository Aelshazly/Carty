package com.navibees.visioglobe.p019f;

import com.navibees.visioglobe.C1477c;
import com.navibees.visioglobe.C1477c.C1480c;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1512b;
import com.navibees.visioglobe.p020g.C1518g;
import com.visioglobe.libVisioMove.VgAnchorMode;
import com.visioglobe.libVisioMove.VgAnimationChannels;
import com.visioglobe.libVisioMove.VgAnimationConstRefPtr;
import com.visioglobe.libVisioMove.VgAnimationDescriptor;
import com.visioglobe.libVisioMove.VgAnimationDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgAnimationRefPtr;
import com.visioglobe.libVisioMove.VgFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgIAnimationCallbackRefPtr;
import com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr;
import com.visioglobe.libVisioMove.VgLayerRefPtr;
import com.visioglobe.libVisioMove.VgLoopModes;
import com.visioglobe.libVisioMove.VgPointDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgSinusoidalVectorOffsetFunctorDescriptor;
import com.visioglobe.libVisioMove.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgVectorInterpolationFunctorDescriptor;
import com.visioglobe.libVisioMove.VgVectorInterpolationFunctorDescriptorRefPtr;

/* renamed from: com.navibees.visioglobe.f.b */
/* compiled from: VgMyAvatarDisplay */
public class C1491b implements C1518g, C1512b {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public VgMySurfaceView f779a;

    /* renamed from: b */
    private C1482e f780b;

    /* renamed from: c */
    private C1477c f781c;

    /* renamed from: d */
    private int f782d;

    /* renamed from: e */
    private String f783e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public String f784f = "";
    /* access modifiers changed from: private */

    /* renamed from: g */
    public VgPosition f785g = new VgPosition();
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f786h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1480c f787i;

    /* renamed from: j */
    private VgSinusoidalVectorOffsetFunctorDescriptorRefPtr f788j = null;

    /* renamed from: com.navibees.visioglobe.f.b$a */
    /* compiled from: VgMyAvatarDisplay */
    class C1492a implements Runnable {
        C1492a() {
        }

        public void run() {
            if (C1491b.this.f787i.f745a.isValid()) {
                VgPosition position = C1491b.this.f787i.f745a.getPosition();
                VgPosition b = C1491b.this.f785g;
                double computeDistance = C1491b.this.f779a.getApplication().editEngine().getPositionToolbox().computeDistance(position, b);
                if (computeDistance < 0.1d) {
                    C1491b.this.f787i.f745a.setPosition(C1491b.this.f785g);
                    return;
                }
                String str = "positionAnim";
                VgAnimationConstRefPtr animation = C1491b.this.f787i.f745a.getAnimation(str);
                if (!animation.isValid() || !animation.isPlaying()) {
                    VgAnimationDescriptorRefPtr create = VgAnimationDescriptor.create();
                    VgVectorInterpolationFunctorDescriptorRefPtr create2 = VgVectorInterpolationFunctorDescriptor.create();
                    create2.setMStartTime(0.0f);
                    create2.setMStartPosition(position);
                    create2.setMEndPosition(b);
                    float log = (float) (Math.log(computeDistance + 1.0d) * 0.05000000074505806d);
                    create2.setMEndTime(log);
                    create2.setMCubic(false);
                    create.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create2));
                    create.setMDuration(log);
                    VgAnimationRefPtr instantiate = C1491b.this.f779a.getApplication().editEngine().editInstanceFactory().instantiate(create);
                    C1491b.this.f787i.f745a.setAnimation(str, instantiate);
                    instantiate.start();
                }
            }
        }
    }

    /* renamed from: com.navibees.visioglobe.f.b$b */
    /* compiled from: VgMyAvatarDisplay */
    class C1493b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f790a;

        C1493b(String str) {
            this.f790a = str;
        }

        public void run() {
            VgLayerRefPtr editLayer = C1491b.this.f779a.getApplication().editEngine().editLayerManager().editLayer(this.f790a);
            if (editLayer.isValid()) {
                C1491b.this.f784f = editLayer.getName();
                C1491b.this.f787i.mo28564a(C1491b.this.f784f, C1491b.this.f786h);
            }
        }
    }

    /* renamed from: com.navibees.visioglobe.f.b$c */
    /* compiled from: VgMyAvatarDisplay */
    class C1494c implements Runnable {
        C1494c() {
        }

        public void run() {
            if (C1491b.this.f787i == null) {
                C1491b.this.mo28589a();
            }
            C1491b.this.show();
        }
    }

    /* renamed from: com.navibees.visioglobe.f.b$d */
    /* compiled from: VgMyAvatarDisplay */
    class C1495d implements Runnable {
        C1495d() {
        }

        public void run() {
            C1491b.this.hide();
        }
    }

    public C1491b(VgMySurfaceView vgMySurfaceView, C1482e eVar, C1477c cVar, String str, int i) {
        this.f779a = vgMySurfaceView;
        this.f780b = eVar;
        this.f781c = cVar;
        this.f783e = str;
        this.f782d = i;
        mo28592d();
    }

    /* renamed from: a */
    public void mo28378a(String str, double d) {
    }

    public void hide() {
        this.f786h = false;
        this.f781c.mo28563c(this.f783e);
    }

    public boolean isVisible() {
        return this.f786h;
    }

    public void release() {
        this.f779a = null;
        this.f780b = null;
        this.f781c = null;
        this.f787i = null;
        this.f785g = null;
        this.f788j = null;
    }

    public void show() {
        this.f786h = true;
        if (this.f787i != null) {
            this.f781c.mo28561a(this.f783e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public VgAnimationRefPtr mo28590b() {
        VgAnimationDescriptorRefPtr create = VgAnimationDescriptor.create();
        create.setMDuration(1.0f);
        create.setMCallback(VgIAnimationCallbackRefPtr.getNull());
        create.setMLoopMode(VgLoopModes.getMscLoop());
        create.getMFunctorDescriptors().set(VgAnimationChannels.getMscLocalScaleChannel(), mo28591c());
        return this.f779a.getApplication().editEngine().editInstanceFactory().instantiate(create);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public VgFunctorDescriptorRefPtr mo28591c() {
        if (this.f788j == null) {
            VgSinusoidalVectorOffsetFunctorDescriptorRefPtr create = VgSinusoidalVectorOffsetFunctorDescriptor.create();
            create.setMStartPhase(-3.141592653589793d);
            create.setMEndPhase(3.141592653589793d);
            create.setMBaseVector(new float[]{1.0f, 1.0f, 1.0f});
            create.setMVector(new float[]{0.1f, 0.1f, 0.1f});
            this.f788j = create;
        }
        return new VgFunctorDescriptorRefPtr(this.f788j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28592d() {
        C1482e eVar = this.f780b;
        if (eVar != null) {
            if (!eVar.mo28579c(this.f783e)) {
                this.f780b.mo28576a(this.f783e, this.f782d);
            }
            if (!this.f780b.mo28580d(this.f783e)) {
                C1482e eVar2 = this.f780b;
                String str = this.f783e;
                eVar2.mo28577a(str, str);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28589a() {
        VgPointDescriptorRefPtr create = VgPointDescriptor.create();
        create.setMVisibilityRampStartVisible(1.0d);
        create.setMVisibilityRampFullyVisible(5.0d);
        create.setMVisibilityRampStartInvisible(900.0d);
        create.setMVisibilityRampFullyInvisible(1000.0d);
        create.setMGeometryConstantSizeDistance(100.0f);
        create.setMAnchorPosition(VgAnchorMode.eVgBottomCenter);
        create.setMPosition(this.f785g);
        create.setMDrawOnTop(true);
        create.setMZIndex(-1);
        create.getMMarkerDescriptors().add(this.f780b.mo28574a(this.f783e));
        this.f787i = this.f781c.mo28557a(this.f783e, this.f784f, create, VgIGeometryCallbackRefPtr.getNull());
        if (this.f787i.f745a.isValid()) {
            VgAnimationRefPtr b = mo28590b();
            this.f787i.f745a.setAnimation("pulseAnim", b);
            b.start();
        }
    }

    /* renamed from: b */
    public void mo28400b(String str) {
        this.f779a.queueEvent(new C1494c());
    }

    /* renamed from: a */
    public void mo28379a(String str, VgPosition vgPosition) {
        this.f785g = new VgPosition(vgPosition);
        this.f785g.setMZOrAltitude(2.0d);
        this.f779a.queueEvent(new C1492a());
    }

    /* renamed from: a */
    public void mo28380a(String str, String str2) {
        this.f779a.queueEvent(new C1493b(str2));
    }

    /* renamed from: a */
    public void mo28377a(String str) {
        this.f779a.queueEvent(new C1495d());
    }
}
