package com.navibees.maps;

import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.visioglobe.C1481d;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1519h;
import com.visioglobe.libVisioMove.VgINavigationConstRefPtr;
import com.visioglobe.libVisioMove.VgINavigationListener;
import com.visioglobe.libVisioMove.VgINavigationListenerRefPtr;
import com.visioglobe.libVisioMove.VgINavigationRefPtr;
import com.visioglobe.libVisioMove.VgPosition;

/* renamed from: com.navibees.maps.c */
/* compiled from: NBMyNavigationLocationProvider */
public class C1415c extends VgINavigationListener implements C1519h {

    /* renamed from: a */
    private MapHoverInterface f481a;

    /* renamed from: b */
    protected boolean f482b = false;

    /* renamed from: c */
    protected VgINavigationRefPtr f483c = null;

    /* renamed from: d */
    protected VgINavigationListenerRefPtr f484d = null;

    /* renamed from: e */
    private VgMySurfaceView f485e;

    /* renamed from: com.navibees.maps.c$a */
    /* compiled from: NBMyNavigationLocationProvider */
    class C1416a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f486a;

        /* renamed from: b */
        final /* synthetic */ VgPosition f487b;

        C1416a(String str, VgPosition vgPosition) {
            this.f486a = str;
            this.f487b = vgPosition;
        }

        public void run() {
            C1481d.m455a().mo28380a(C1415c.this.mo28336b(), this.f486a);
            C1481d.m455a().mo28379a(C1415c.this.mo28336b(), this.f487b);
        }
    }

    /* renamed from: com.navibees.maps.c$b */
    /* compiled from: NBMyNavigationLocationProvider */
    class C1417b implements Runnable {
        C1417b() {
        }

        public void run() {
            C1481d.m455a().mo28400b(C1415c.this.mo28336b());
            VgPosition vgPosition = new VgPosition();
            C1415c cVar = C1415c.this;
            cVar.notifyPositionUpdated(new VgINavigationConstRefPtr(cVar.f483c.get()), vgPosition, 0.0d);
        }
    }

    /* renamed from: com.navibees.maps.c$c */
    /* compiled from: NBMyNavigationLocationProvider */
    class C1418c implements Runnable {
        C1418c() {
        }

        public void run() {
            C1481d.m455a().mo28377a(C1415c.this.mo28336b());
        }
    }

    public C1415c(VgINavigationRefPtr vgINavigationRefPtr, VgMySurfaceView vgMySurfaceView, MapHoverInterface mapHoverInterface) {
        this.f483c = vgINavigationRefPtr;
        this.f484d = new VgINavigationListenerRefPtr((VgINavigationListener) this);
        this.f485e = vgMySurfaceView;
        this.f481a = mapHoverInterface;
    }

    /* renamed from: a */
    public void mo28334a() {
        if (mo28337c()) {
            this.f482b = false;
            this.f483c.removeListener(this.f484d);
            new Handler(Looper.getMainLooper()).post(new C1418c());
        }
    }

    /* renamed from: b */
    public String mo28336b() {
        return NotificationCompat.CATEGORY_NAVIGATION;
    }

    /* renamed from: c */
    public boolean mo28337c() {
        return this.f482b;
    }

    /* renamed from: d */
    public void mo28338d() {
        if (!mo28337c()) {
            this.f482b = true;
            this.f483c.addListener(this.f484d);
            new Handler(Looper.getMainLooper()).post(new C1417b());
        }
    }

    public void notifyNewInstruction(VgINavigationConstRefPtr vgINavigationConstRefPtr, long j) {
    }

    public void notifyPositionUpdated(VgINavigationConstRefPtr vgINavigationConstRefPtr, VgPosition vgPosition, double d) {
        String layer = vgINavigationConstRefPtr.getInstruction(vgINavigationConstRefPtr.getCurrentInstructionIndex()).getLayer();
        VgPosition vgPosition2 = new VgPosition(vgINavigationConstRefPtr.getClosestPositionOnRoute());
        new Handler(Looper.getMainLooper()).post(new C1416a(layer, vgPosition2));
        double computeDistance = this.f485e.getApplication().editEngine().getPositionToolbox().computeDistance(vgPosition, vgPosition2);
        MapHoverInterface mapHoverInterface = this.f481a;
        if (mapHoverInterface != null) {
            mapHoverInterface.updateDistanceToRoute(computeDistance);
        }
    }
}
