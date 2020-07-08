package com.navibees.core.model.postioning.p025h;

import android.app.Application;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;

/* renamed from: com.navibees.core.model.postioning.h.a */
/* compiled from: KalmanFilter */
public final class C1712a {

    /* renamed from: a */
    int f1529a = 0;

    /* renamed from: b */
    int f1530b;

    /* renamed from: c */
    int f1531c;

    /* renamed from: d */
    C1714c f1532d;

    /* renamed from: e */
    C1714c f1533e;

    /* renamed from: f */
    C1714c f1534f;

    /* renamed from: g */
    C1714c f1535g;

    /* renamed from: h */
    C1714c f1536h;

    /* renamed from: i */
    C1714c f1537i;

    /* renamed from: j */
    C1714c f1538j;

    /* renamed from: k */
    C1714c f1539k;

    /* renamed from: l */
    C1714c f1540l;

    /* renamed from: m */
    C1714c f1541m;

    /* renamed from: n */
    C1714c f1542n;

    /* renamed from: o */
    C1714c f1543o;

    /* renamed from: p */
    C1714c f1544p;

    /* renamed from: q */
    C1714c f1545q;

    /* renamed from: r */
    C1714c f1546r;

    /* renamed from: s */
    C1714c f1547s;

    public C1712a(int i, int i2, Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        NaviBeesManager.getInstance(application).getLicenseManager().mo29048a(NaviBeesFeature.Positioning);
        this.f1530b = i;
        this.f1531c = i2;
        this.f1532d = new C1714c(i, i);
        this.f1533e = new C1714c(i2, i);
        this.f1534f = new C1714c(i, i);
        this.f1535g = new C1714c(i2, i2);
        this.f1536h = new C1714c(i2, 1);
        this.f1537i = new C1714c(i, 1);
        this.f1538j = new C1714c(i, i);
        this.f1539k = new C1714c(i2, 1);
        this.f1540l = new C1714c(i2, i2);
        this.f1541m = new C1714c(i2, i2);
        this.f1542n = new C1714c(i, i2);
        this.f1543o = new C1714c(i, 1);
        this.f1544p = new C1714c(i, i);
        this.f1545q = new C1714c(i, i2);
        this.f1546r = new C1714c(i2, i2);
        this.f1547s = new C1714c(i, i);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29377a() {
        C1714c.m1126c(this.f1533e, this.f1537i, this.f1539k);
        C1714c cVar = this.f1536h;
        C1714c cVar2 = this.f1539k;
        C1714c.m1127d(cVar, cVar2, cVar2);
        C1714c.m1123b(this.f1538j, this.f1533e, this.f1545q);
        C1714c.m1126c(this.f1533e, this.f1545q, this.f1540l);
        C1714c cVar3 = this.f1540l;
        C1714c.m1121a(cVar3, this.f1535g, cVar3);
        C1714c.m1124b(this.f1540l, this.f1541m);
        C1714c.m1126c(this.f1545q, this.f1541m, this.f1542n);
        C1714c.m1126c(this.f1542n, this.f1539k, this.f1543o);
        C1714c cVar4 = this.f1543o;
        C1714c.m1121a(cVar4, this.f1537i, cVar4);
        C1714c.m1126c(this.f1542n, this.f1533e, this.f1547s);
        C1714c.m1119a(this.f1547s);
        C1714c.m1126c(this.f1547s, this.f1538j, this.f1544p);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo29378b() {
        this.f1529a++;
        C1714c.m1126c(this.f1532d, this.f1543o, this.f1537i);
        C1714c.m1126c(this.f1532d, this.f1544p, this.f1547s);
        C1714c.m1123b(this.f1547s, this.f1532d, this.f1538j);
        C1714c cVar = this.f1538j;
        C1714c.m1121a(cVar, this.f1534f, cVar);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public void mo29379c() {
        mo29378b();
        mo29377a();
    }
}
