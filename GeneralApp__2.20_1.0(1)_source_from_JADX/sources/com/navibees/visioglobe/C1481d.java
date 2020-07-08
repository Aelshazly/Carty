package com.navibees.visioglobe;

import android.os.Looper;
import com.navibees.visioglobe.p020g.C1518g;
import com.navibees.visioglobe.p020g.C1519h;
import com.visioglobe.libVisioMove.VgPosition;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/* renamed from: com.navibees.visioglobe.d */
/* compiled from: VgMyLocationManager */
public class C1481d implements C1518g {

    /* renamed from: c */
    private static C1481d f749c;

    /* renamed from: a */
    private HashMap<String, Vector<C1518g>> f750a = new HashMap<>();

    /* renamed from: b */
    Vector<C1519h> f751b = new Vector<>();

    protected C1481d() {
    }

    /* renamed from: a */
    public static C1481d m455a() {
        if (f749c == null) {
            f749c = new C1481d();
        }
        return f749c;
    }

    /* renamed from: b */
    public void mo28568b(C1519h hVar) {
        if (this.f751b.contains(hVar)) {
            if (hVar.mo28337c()) {
                hVar.mo28334a();
            }
            this.f751b.remove(hVar);
        }
    }

    /* renamed from: c */
    public C1519h mo28570c(String str) {
        Iterator it = this.f751b.iterator();
        C1519h hVar = null;
        while (it.hasNext()) {
            C1519h hVar2 = (C1519h) it.next();
            if (hVar2.mo28336b().contentEquals(str)) {
                hVar = hVar2;
            }
        }
        return hVar;
    }

    /* renamed from: d */
    public void mo28572d(String str, C1518g gVar) {
        if (this.f750a.containsKey(str) && ((Vector) this.f750a.get(str)).contains(gVar)) {
            C1519h c = mo28570c(str);
            if (c != null && c.mo28337c()) {
                mo28569b(str, gVar);
            }
            ((Vector) this.f750a.get(str)).remove(gVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo28571c(String str, C1518g gVar) {
        if (gVar != null) {
            gVar.mo28400b(str);
        }
    }

    /* renamed from: a */
    public Vector<String> mo28565a(boolean z) {
        Vector<String> vector = new Vector<>();
        Iterator it = this.f751b.iterator();
        while (it.hasNext()) {
            C1519h hVar = (C1519h) it.next();
            boolean z2 = true;
            if (z && !hVar.mo28337c()) {
                z2 = false;
            }
            if (z2) {
                vector.add(hVar.mo28336b());
            }
        }
        return vector;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28569b(String str, C1518g gVar) {
        if (gVar != null) {
            gVar.mo28377a(str);
        }
    }

    /* renamed from: b */
    public void mo28400b(String str) {
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName())) {
            throw new IllegalThreadStateException("Method must be called from within main thread");
        } else if (this.f750a.containsKey(str)) {
            Iterator it = ((Vector) this.f750a.get(str)).iterator();
            while (it.hasNext()) {
                ((C1518g) it.next()).mo28400b(str);
            }
        }
    }

    /* renamed from: a */
    public void mo28566a(C1519h hVar) {
        if (!this.f751b.contains(hVar)) {
            this.f751b.add(hVar);
            if (hVar.mo28337c()) {
                mo28400b(hVar.mo28336b());
            }
        }
    }

    /* renamed from: a */
    public void mo28567a(String str, C1518g gVar) {
        if (!this.f750a.containsKey(str)) {
            this.f750a.put(str, new Vector());
        }
        if (!((Vector) this.f750a.get(str)).contains(gVar)) {
            ((Vector) this.f750a.get(str)).add(gVar);
            C1519h c = mo28570c(str);
            if (c == null || !c.mo28337c()) {
                mo28569b(str, gVar);
            } else {
                mo28571c(str, gVar);
            }
        }
    }

    /* renamed from: a */
    public void mo28379a(String str, VgPosition vgPosition) {
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName())) {
            throw new IllegalThreadStateException("Method must be called from within main thread");
        } else if (this.f750a.containsKey(str)) {
            Iterator it = ((Vector) this.f750a.get(str)).iterator();
            while (it.hasNext()) {
                ((C1518g) it.next()).mo28379a(str, vgPosition);
            }
        }
    }

    /* renamed from: a */
    public void mo28380a(String str, String str2) {
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName())) {
            throw new IllegalThreadStateException("Method must be called from within main thread");
        } else if (this.f750a.containsKey(str)) {
            Iterator it = ((Vector) this.f750a.get(str)).iterator();
            while (it.hasNext()) {
                ((C1518g) it.next()).mo28380a(str, str2);
            }
        }
    }

    /* renamed from: a */
    public void mo28378a(String str, double d) {
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName())) {
            throw new IllegalThreadStateException("Method must be called from within main thread");
        } else if (this.f750a.containsKey(str)) {
            Iterator it = ((Vector) this.f750a.get(str)).iterator();
            while (it.hasNext()) {
                ((C1518g) it.next()).mo28378a(str, d);
            }
        }
    }

    /* renamed from: a */
    public void mo28377a(String str) {
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName())) {
            throw new IllegalThreadStateException("Method must be called from within main thread");
        } else if (this.f750a.containsKey(str)) {
            Iterator it = ((Vector) this.f750a.get(str)).iterator();
            while (it.hasNext()) {
                ((C1518g) it.next()).mo28377a(str);
            }
        }
    }
}
