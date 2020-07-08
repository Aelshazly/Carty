package com.navibees.maps;

import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.visioglobe.C1481d;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1519h;
import com.navibees.visioglobe.p020g.C1521j;
import com.visioglobe.libVisioMove.VgINavigationRefPtr;

/* renamed from: com.navibees.maps.d */
/* compiled from: NBMyNavigationProviderManager */
public class C1419d implements C1521j {

    /* renamed from: a */
    private VgMySurfaceView f491a;

    /* renamed from: b */
    private MapHoverInterface f492b;

    /* renamed from: c */
    protected C1415c f493c = null;

    public C1419d(VgMySurfaceView vgMySurfaceView, MapHoverInterface mapHoverInterface) {
        this.f491a = vgMySurfaceView;
        this.f492b = mapHoverInterface;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28360a() {
        if (this.f493c != null) {
            C1481d.m455a().mo28568b((C1519h) this.f493c);
            this.f493c = null;
        }
    }

    /* renamed from: b */
    public void mo28362b(VgINavigationRefPtr vgINavigationRefPtr) {
        mo28360a();
    }

    /* renamed from: a */
    public void mo28361a(VgINavigationRefPtr vgINavigationRefPtr) {
        mo28360a();
        this.f493c = new C1415c(vgINavigationRefPtr, this.f491a, this.f492b);
        C1481d a = C1481d.m455a();
        a.mo28566a((C1519h) this.f493c);
        if (a.mo28565a(true).size() > 0) {
            this.f493c.mo28338d();
        }
    }
}
