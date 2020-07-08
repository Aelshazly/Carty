package com.navibees.visioglobe.p020g;

import com.visioglobe.libVisioMove.VgPosition;

/* renamed from: com.navibees.visioglobe.g.e */
/* compiled from: VgMyLayerAndCameraHandler */
public interface C1515e {

    /* renamed from: com.navibees.visioglobe.g.e$a */
    /* compiled from: VgMyLayerAndCameraHandler */
    public enum C1516a {
        eVgViewModeGlobal,
        eVgViewModeBuilding,
        eVgViewModeFloor
    }

    /* renamed from: a */
    CharSequence mo28363a();

    /* renamed from: a */
    void mo28376a(VgPosition vgPosition, String str, boolean z);

    /* renamed from: c */
    C1516a mo28405c();

    void release();
}
