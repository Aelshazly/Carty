package com.navibees.core.model.postioning.p025h;

import android.app.Application;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.util.Log;

/* renamed from: com.navibees.core.model.postioning.h.b */
/* compiled from: KalmanFilterHandler */
public final class C1713b {

    /* renamed from: a */
    private C1712a f1548a;

    /* renamed from: b */
    private double f1549b = 0.1d;

    /* renamed from: c */
    private double f1550c = 1.0d;

    /* renamed from: d */
    private Long f1551d = null;

    public C1713b(Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        NaviBeesManager.getInstance(application).getLicenseManager().mo29048a(NaviBeesFeature.Positioning);
        this.f1548a = new C1712a(4, 2, application);
        m1114c();
    }

    /* renamed from: c */
    private void m1114c() {
        this.f1548a.f1532d.mo29390b();
        mo29380a(this.f1549b);
        this.f1548a.f1533e.mo29389a(1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        this.f1548a.f1543o.mo29389a(0.0d, 0.0d, 0.0d, 0.0d);
        this.f1548a.f1544p.mo29390b();
        this.f1548a.f1544p.mo29385a(1.0E12d);
        this.f1548a.f1535g.mo29389a(0.3d, 0.0d, 0.0d, 0.3d);
        this.f1548a.f1534f.mo29389a(0.001d, 0.0d, 0.0d, 0.0d, 0.0d, 0.001d, 0.0d, 0.0d, 0.0d, 0.0d, 0.001d, 0.0d, 0.0d, 0.0d, 0.0d, 0.001d);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29380a(double d) {
        double[][] dArr = this.f1548a.f1532d.f1555c;
        dArr[0][2] = d;
        dArr[1][3] = d;
    }

    /* renamed from: b */
    public void mo29383b() {
        m1114c();
    }

    /* renamed from: a */
    public void mo29381a(double d, double d2) {
        long currentTimeMillis = System.currentTimeMillis();
        String str = "PositionManager";
        if (this.f1551d == null) {
            Log.m1173i(str, "Current updateVelocity2d 1");
            mo29380a(0.0d);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Current updateVelocity2d 2 , (currentTime - this.previousTime)/1000 = ");
            sb.append((currentTimeMillis - this.f1551d.longValue()) / 1000);
            Log.m1173i(str, sb.toString());
            mo29380a((double) ((currentTimeMillis - this.f1551d.longValue()) / 1000));
        }
        this.f1551d = Long.valueOf(currentTimeMillis);
        this.f1548a.f1536h.mo29389a(d, d2);
        this.f1548a.mo29379c();
    }

    /* renamed from: a */
    public double[] mo29382a() {
        double[][] dArr = this.f1548a.f1543o.f1555c;
        return new double[]{dArr[0][0], dArr[1][0]};
    }
}
