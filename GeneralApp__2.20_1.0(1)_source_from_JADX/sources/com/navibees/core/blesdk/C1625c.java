package com.navibees.core.blesdk;

import com.navibees.core.blesdk.p022d.C1629d;
import com.navibees.core.blesdk.p023e.C1634b;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.navibees.core.blesdk.c */
/* compiled from: RegionBootstrap */
public class C1625c {

    /* renamed from: c */
    private static boolean f1221c;

    /* renamed from: a */
    private C1629d f1222a;

    /* renamed from: b */
    private List<C1634b> f1223b;

    public C1625c(C1629d dVar, List<C1634b> list) {
        this.f1222a = dVar;
        this.f1223b = list;
        f1221c = true;
    }

    /* renamed from: a */
    public void mo28958a() {
        this.f1223b = null;
    }

    /* renamed from: b */
    public C1629d mo28959b() {
        return this.f1222a;
    }

    /* renamed from: c */
    public List<C1634b> mo28960c() {
        if (this.f1223b == null) {
            this.f1223b = new ArrayList();
        }
        return this.f1223b;
    }
}
