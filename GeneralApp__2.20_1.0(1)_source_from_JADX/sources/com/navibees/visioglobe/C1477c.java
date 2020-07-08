package com.navibees.visioglobe;

import com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr;
import com.visioglobe.libVisioMove.VgLayerRefPtr;
import com.visioglobe.libVisioMove.VgLineDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgLineRefPtr;
import com.visioglobe.libVisioMove.VgLinkDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgLinkRefPtr;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPointRefPtr;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

/* renamed from: com.navibees.visioglobe.c */
/* compiled from: VgMyGeometryManager */
public class C1477c {

    /* renamed from: a */
    private HashMap<String, LinkedList<C1478a>> f738a = new HashMap<>();

    /* renamed from: b */
    private HashMap<String, LinkedList<C1480c>> f739b = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, LinkedList<C1479b>> f740c = new HashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: d */
    public VgMySurfaceView f741d;

    /* renamed from: com.navibees.visioglobe.c$a */
    /* compiled from: VgMyGeometryManager */
    public class C1478a {

        /* renamed from: a */
        VgLineRefPtr f742a;

        /* renamed from: b */
        String f743b;

        public C1478a(C1477c cVar) {
        }
    }

    /* renamed from: com.navibees.visioglobe.c$b */
    /* compiled from: VgMyGeometryManager */
    public class C1479b {

        /* renamed from: a */
        VgLinkRefPtr f744a;

        public C1479b(C1477c cVar) {
        }
    }

    /* renamed from: com.navibees.visioglobe.c$c */
    /* compiled from: VgMyGeometryManager */
    public class C1480c {

        /* renamed from: a */
        public VgPointRefPtr f745a;

        /* renamed from: b */
        public VgIGeometryCallbackRefPtr f746b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f747c;

        public C1480c() {
        }

        /* renamed from: a */
        public void mo28564a(String str, boolean z) {
            if (this.f745a != null) {
                String str2 = this.f747c;
                if (str2 == null || !str2.equals(str)) {
                    VgLayerRefPtr editLayer = C1477c.this.f741d.getApplication().editEngine().editLayerManager().editLayer(str);
                    if (editLayer != null) {
                        this.f747c = str;
                        if (z) {
                            this.f745a.setLayer(editLayer);
                        }
                    }
                }
            }
        }
    }

    public C1477c(VgMySurfaceView vgMySurfaceView) {
        this.f741d = vgMySurfaceView;
    }

    /* renamed from: b */
    public void mo28562b(String str) {
        this.f738a.remove(str);
        this.f740c.remove(str);
        this.f739b.remove(str);
    }

    /* renamed from: c */
    public void mo28563c(String str) {
        LinkedList linkedList = (LinkedList) this.f738a.get(str);
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                ((C1478a) it.next()).f742a.setLayer(VgLayerRefPtr.getNull());
            }
        }
        LinkedList linkedList2 = (LinkedList) this.f740c.get(str);
        if (linkedList2 != null) {
            Iterator it2 = linkedList2.iterator();
            while (it2.hasNext()) {
                ((C1479b) it2.next()).f744a.setVisible(false);
            }
        }
        LinkedList linkedList3 = (LinkedList) this.f739b.get(str);
        if (linkedList3 != null) {
            Iterator it3 = linkedList3.iterator();
            while (it3.hasNext()) {
                C1480c cVar = (C1480c) it3.next();
                cVar.f745a.setLayer(VgLayerRefPtr.getNull());
                VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr = cVar.f746b;
                if (vgIGeometryCallbackRefPtr != null) {
                    cVar.f745a.removeListener(vgIGeometryCallbackRefPtr);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo28561a(String str) {
        for (Entry entry : this.f738a.entrySet()) {
            String str2 = (String) entry.getKey();
            if (str == null || str2.contentEquals(str)) {
                Iterator it = ((LinkedList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    C1478a aVar = (C1478a) it.next();
                    aVar.f742a.setLayer(this.f741d.getApplication().editEngine().editLayerManager().editLayer(aVar.f743b));
                }
            }
        }
        for (Entry entry2 : this.f740c.entrySet()) {
            String str3 = (String) entry2.getKey();
            if (str == null || str3.contentEquals(str)) {
                Iterator it2 = ((LinkedList) entry2.getValue()).iterator();
                while (it2.hasNext()) {
                    ((C1479b) it2.next()).f744a.setVisible(true);
                }
            }
        }
        for (Entry entry3 : this.f739b.entrySet()) {
            String str4 = (String) entry3.getKey();
            if (str == null || str4.contentEquals(str)) {
                Iterator it3 = ((LinkedList) entry3.getValue()).iterator();
                while (it3.hasNext()) {
                    C1480c cVar = (C1480c) it3.next();
                    if (cVar.f747c != null) {
                        VgLayerRefPtr editLayer = this.f741d.getApplication().editEngine().editLayerManager().editLayer(cVar.f747c);
                        if (editLayer.isValid()) {
                            cVar.f745a.setLayer(editLayer);
                            VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr = cVar.f746b;
                            if (vgIGeometryCallbackRefPtr != null) {
                                cVar.f745a.addListener(vgIGeometryCallbackRefPtr);
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public VgLineRefPtr mo28558a(String str, String str2, VgLineDescriptorRefPtr vgLineDescriptorRefPtr) {
        C1478a aVar = new C1478a(this);
        aVar.f742a = this.f741d.getApplication().editEngine().editInstanceFactory().instantiate(vgLineDescriptorRefPtr);
        if (aVar.f742a.isValid()) {
            aVar.f742a.setLayer(this.f741d.getApplication().editEngine().editLayerManager().editLayer(str2));
            aVar.f743b = str2;
            LinkedList linkedList = (LinkedList) this.f738a.get(str);
            if (linkedList == null) {
                linkedList = new LinkedList();
            }
            linkedList.add(aVar);
            this.f738a.put(str, linkedList);
        }
        return aVar.f742a;
    }

    /* renamed from: a */
    public C1480c mo28557a(String str, String str2, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr) {
        C1480c cVar = new C1480c();
        cVar.f745a = this.f741d.getApplication().editEngine().editInstanceFactory().instantiate(vgPointDescriptorRefPtr);
        VgPointRefPtr vgPointRefPtr = cVar.f745a;
        if (vgPointRefPtr == null || !vgPointRefPtr.isValid()) {
            return null;
        }
        cVar.f745a.setLayer(VgLayerRefPtr.getNull());
        cVar.f747c = str2;
        cVar.f746b = vgIGeometryCallbackRefPtr;
        if (vgIGeometryCallbackRefPtr != null) {
            cVar.f745a.addListener(vgIGeometryCallbackRefPtr);
        }
        LinkedList linkedList = (LinkedList) this.f739b.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList();
        }
        linkedList.add(cVar);
        this.f739b.put(str, linkedList);
        return cVar;
    }

    /* renamed from: a */
    public VgLinkRefPtr mo28559a(String str, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr) {
        C1479b bVar = new C1479b(this);
        bVar.f744a = this.f741d.getApplication().editEngine().editInstanceFactory().instantiate(vgLinkDescriptorRefPtr);
        bVar.f744a.setVisible(false);
        LinkedList linkedList = (LinkedList) this.f740c.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList();
        }
        linkedList.add(bVar);
        this.f740c.put(str, linkedList);
        return bVar.f744a;
    }

    /* renamed from: a */
    public void mo28560a() {
        this.f741d = null;
    }
}
