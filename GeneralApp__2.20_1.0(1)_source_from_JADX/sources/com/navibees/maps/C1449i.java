package com.navibees.maps;

import android.app.Activity;
import androidx.collection.SimpleArrayMap;
import com.navibees.C1164R;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.VisioFacility;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.visioglobe.C1482e;
import com.navibees.visioglobe.VgMySurfaceView;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIModule;
import com.visioglobe.libVisioMove.VgIModuleManager;
import com.visioglobe.libVisioMove.VgITextureRefPtr;
import com.visioglobe.libVisioMove.VgPlaceIconDescriptor;
import com.visioglobe.libVisioMove.VgStringVector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.navibees.maps.i */
/* compiled from: VgMyPlaceConfigurationSetter */
public class C1449i {

    /* renamed from: a */
    private VgMySurfaceView f608a;

    /* renamed from: b */
    private C1482e f609b;

    /* renamed from: c */
    private Activity f610c;

    public C1449i(VgMySurfaceView vgMySurfaceView, C1482e eVar, Activity activity) {
        this.f608a = vgMySurfaceView;
        this.f609b = eVar;
        this.f610c = activity;
        m352d();
    }

    /* renamed from: c */
    private HashMap<String, String> m351c() {
        SimpleArrayMap buildings = NaviBeesManager.getInstance(this.f610c.getApplication()).getMetaDataManager().getBuildings();
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < buildings.size(); i++) {
            Building building = (Building) buildings.get((String) buildings.keyAt(i));
            List<POI> list = building.pois;
            if (list != null && list.size() > 0) {
                for (POI poi : building.pois) {
                    hashMap.put(((VisioPOI) poi).vgId, poi.getAdjustedName());
                }
            }
            List<Facility> list2 = building.facilities;
            if (list2 != null && list2.size() > 0) {
                Iterator it = building.facilities.iterator();
                while (it.hasNext()) {
                    List<VisioPOI> pois = ((VisioFacility) it.next()).getPois();
                    if (!(pois == null || pois.size() == 0)) {
                        for (VisioPOI visioPOI : pois) {
                            hashMap.put(visioPOI.vgId, visioPOI.getAdjustedName());
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    /* renamed from: d */
    private void m352d() {
        this.f609b.mo28576a("place", C1164R.C1166drawable.place_icon);
    }

    /* renamed from: a */
    public void mo28467a() {
        this.f608a.pauseRendering();
        VgIModuleManager editModuleManager = this.f608a.getApplication().editModuleManager();
        if (editModuleManager != null) {
            VgIModule queryModule = editModuleManager.queryModule("Map");
            if (queryModule != null) {
                VgIMapModule castToIMapModule = C1732libVisioMove.castToIMapModule(queryModule);
                if (castToIMapModule != null) {
                    VgStringVector vgStringVector = new VgStringVector();
                    castToIMapModule.queryAllPlaceIDs(vgStringVector);
                    VgPlaceIconDescriptor vgPlaceIconDescriptor = new VgPlaceIconDescriptor();
                    HashMap c = m351c();
                    for (int i = 0; ((long) i) < vgStringVector.size(); i++) {
                        String str = vgStringVector.get(i);
                        String str2 = "UL0-ID0067";
                        if (str2.contentEquals(str)) {
                            String str3 = "place";
                            if (this.f609b.mo28579c(str3)) {
                                VgITextureRefPtr b = this.f609b.mo28578b(str3);
                                if (b.isValid()) {
                                    vgPlaceIconDescriptor.setMIcon(b);
                                    vgPlaceIconDescriptor.setMScale(5.0d);
                                    castToIMapModule.setPlaceIcon(str2, vgPlaceIconDescriptor);
                                    castToIMapModule.setPlaceName(str2, "");
                                }
                            }
                        } else if (c == null || !c.containsKey(str)) {
                            String[] strArr = new String[2];
                            castToIMapModule.getPlaceName(str, strArr);
                            if (strArr[0].length() <= 0) {
                                castToIMapModule.setPlaceName(str, str);
                            } else if (c != null && c.containsKey(strArr[0])) {
                                castToIMapModule.setPlaceName(str, (String) c.get(strArr[0]));
                            }
                        } else {
                            castToIMapModule.setPlaceName(str, (String) c.get(str));
                        }
                    }
                }
            }
        }
        this.f608a.resumeRendering();
    }

    /* renamed from: b */
    public void mo28468b() {
        this.f610c = null;
        this.f608a = null;
        this.f609b = null;
    }
}
