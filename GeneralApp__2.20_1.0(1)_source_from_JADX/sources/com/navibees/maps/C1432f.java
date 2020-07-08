package com.navibees.maps;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesConstants.InstructionsIconsNames;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1513c;
import com.navibees.visioglobe.p020g.C1514d;
import com.visioglobe.libVisioMove.VgINavigationConstRefPtr;
import com.visioglobe.libVisioMove.VgManeuverType;
import java.util.Locale;

/* renamed from: com.navibees.maps.f */
/* compiled from: VgMyInstructionViewN */
public class C1432f implements C1513c {

    /* renamed from: a */
    private String[] f549a = new String[VgManeuverType.eVgManeuverTypeMax.swigValue()];

    /* renamed from: b */
    Activity f550b = null;

    /* renamed from: c */
    private FrameLayout f551c = null;

    /* renamed from: d */
    VgMySurfaceView f552d = null;

    /* renamed from: e */
    FrameLayout f553e;

    /* renamed from: f */
    private int f554f = 0;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public MapHoverInterface f555g;

    /* renamed from: com.navibees.maps.f$a */
    /* compiled from: VgMyInstructionViewN */
    class C1433a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f556a;

        /* renamed from: b */
        final /* synthetic */ String f557b;

        /* renamed from: c */
        final /* synthetic */ VgManeuverType f558c;

        C1433a(String str, String str2, VgManeuverType vgManeuverType) {
            this.f556a = str;
            this.f557b = str2;
            this.f558c = vgManeuverType;
        }

        public void run() {
            if (C1432f.this.f555g != null) {
                C1432f.this.f555g.updateNavigationMessage(this.f556a, this.f557b, C1432f.this.m324a(this.f558c));
            }
        }
    }

    public C1432f(Activity activity, C1514d dVar, VgMySurfaceView vgMySurfaceView, FrameLayout frameLayout, MapHoverInterface mapHoverInterface) {
        this.f555g = mapHoverInterface;
        this.f550b = activity;
        this.f552d = vgMySurfaceView;
        this.f553e = frameLayout;
        this.f549a[VgManeuverType.eVgManeuverTypeEnd.swigValue()] = InstructionsIconsNames.maneuverTypeEnd;
        this.f549a[VgManeuverType.eVgManeuverTypeWaypoint.swigValue()] = InstructionsIconsNames.maneuverTypeWaypoint;
        this.f549a[VgManeuverType.eVgManeuverTypeGoDown.swigValue()] = InstructionsIconsNames.maneuverTypeGoDown;
        this.f549a[VgManeuverType.eVgManeuverTypeGoUp.swigValue()] = InstructionsIconsNames.maneuverTypeGoUp;
        this.f549a[VgManeuverType.eVgManeuverTypeStart.swigValue()] = InstructionsIconsNames.maneuverTypeStart;
        this.f549a[VgManeuverType.eVgManeuverTypeGoStraight.swigValue()] = InstructionsIconsNames.maneuverTypeGoStraight;
        this.f549a[VgManeuverType.eVgManeuverTypeTurnGentleLeft.swigValue()] = InstructionsIconsNames.maneuverTypeTurnGentleLeft;
        this.f549a[VgManeuverType.eVgManeuverTypeTurnGentleRight.swigValue()] = InstructionsIconsNames.maneuverTypeTurnGentleRight;
        this.f549a[VgManeuverType.eVgManeuverTypeTurnLeft.swigValue()] = InstructionsIconsNames.maneuverTypeTurnLeft;
        this.f549a[VgManeuverType.eVgManeuverTypeTurnRight.swigValue()] = InstructionsIconsNames.maneuverTypeTurnRight;
        this.f549a[VgManeuverType.eVgManeuverTypeTurnSharpLeft.swigValue()] = InstructionsIconsNames.maneuverTypeTurnSharpLeft;
        this.f549a[VgManeuverType.eVgManeuverTypeTurnSharpRight.swigValue()] = InstructionsIconsNames.maneuverTypeTurnSharpRight;
        this.f549a[VgManeuverType.eVgManeuverTypeUTurnLeft.swigValue()] = InstructionsIconsNames.maneuverTypeUTurnLeft;
        this.f549a[VgManeuverType.eVgManeuverTypeUTurnRight.swigValue()] = InstructionsIconsNames.maneuverTypeUTurnRight;
        this.f549a[VgManeuverType.eVgManeuverTypeChangeModality.swigValue()] = InstructionsIconsNames.maneuverTypeChangeModality;
        this.f549a[VgManeuverType.eVgManeuverTypeChangeLayer.swigValue()] = InstructionsIconsNames.maneuverTypeChangeLayer;
        this.f554f = C1446h.m342a(PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext()).getString(NaviBeesManager.LANGUAGE_KEY, Locale.getDefault().getDisplayLanguage()));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0109 A[SYNTHETIC, Splitter:B:24:0x0109] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f8 A[EDGE_INSN: B:40:0x00f8->B:19:0x00f8 ?: BREAK  
    EDGE_INSN: B:40:0x00f8->B:19:0x00f8 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0036  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo28444b(com.visioglobe.libVisioMove.VgINavigationConstRefPtr r33, long r34) {
        /*
            r32 = this;
            r1 = r32
            r8 = r33
            com.visioglobe.libVisioMove.VgManeuverType r9 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeUnknown
            com.navibees.visioglobe.VgMySurfaceView r0 = r1.f552d
            com.visioglobe.libVisioMove.VgIApplication r0 = r0.getApplication()
            com.visioglobe.libVisioMove.VgIModuleManager r0 = r0.editModuleManager()
            if (r0 == 0) goto L_0x0020
            java.lang.String r2 = "Map"
            com.visioglobe.libVisioMove.VgIModule r0 = r0.queryModule(r2)
            if (r0 == 0) goto L_0x0020
            com.visioglobe.libVisioMove.VgIMapModule r0 = com.visioglobe.libVisioMove.C1732libVisioMove.castToIMapModule(r0)
            r10 = r0
            goto L_0x0022
        L_0x0020:
            r0 = 0
            r10 = r0
        L_0x0022:
            long r11 = r33.getNumInstructions()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r0 = 0
            r2 = 0
            r5 = r2
            r3 = r5
            r2 = 0
        L_0x0031:
            long r13 = (long) r2
            int r0 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f8
            com.navibees.core.model.metadata.json.RouteSegment r0 = new com.navibees.core.model.metadata.json.RouteSegment
            r0.<init>()
            com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr r17 = r8.getInstruction(r13)
            float r15 = r17.getDuration()
            r16 = r9
            r20 = r10
            double r9 = (double) r15
            double r3 = r3 + r9
            double r9 = r17.getLength()
            double r5 = r5 + r9
            com.visioglobe.libVisioMove.VgPosition r9 = r17.getPosition()     // Catch:{ Exception -> 0x00ec }
            com.navibees.visioglobe.VgMySurfaceView r10 = r1.f552d     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgIApplication r10 = r10.getApplication()     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgIEngine r10 = r10.editEngine()     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgPositionToolbox r10 = r10.getPositionToolbox()     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgSRSConstRefPtr r15 = com.visioglobe.libVisioMove.VgSRSConstRefPtr.getNull()     // Catch:{ Exception -> 0x00ec }
            r10.geoConvert(r9, r15)     // Catch:{ Exception -> 0x00ec }
            r18 = 1
            long r18 = r11 - r18
            int r10 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r10 >= 0) goto L_0x007b
            int r10 = r2 + 1
            long r13 = (long) r10     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr r10 = r8.getInstruction(r13)     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgPosition r10 = r10.getPosition()     // Catch:{ Exception -> 0x00ec }
            goto L_0x007d
        L_0x007b:
            com.visioglobe.libVisioMove.VgPosition r10 = com.navibees.maps.C1420e.f494P     // Catch:{ Exception -> 0x00ec }
        L_0x007d:
            com.navibees.visioglobe.VgMySurfaceView r13 = r1.f552d     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgIApplication r13 = r13.getApplication()     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgIEngine r13 = r13.editEngine()     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgPositionToolbox r13 = r13.getPositionToolbox()     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgSRSConstRefPtr r14 = com.visioglobe.libVisioMove.VgSRSConstRefPtr.getNull()     // Catch:{ Exception -> 0x00ec }
            r13.geoConvert(r10, r14)     // Catch:{ Exception -> 0x00ec }
            com.navibees.core.model.metadata.json.Location r13 = new com.navibees.core.model.metadata.json.Location     // Catch:{ Exception -> 0x00ec }
            double r22 = r9.getMXOrLongitude()     // Catch:{ Exception -> 0x00ec }
            double r24 = r9.getMYOrLatitude()     // Catch:{ Exception -> 0x00ec }
            double r14 = r9.getMZOrAltitude()     // Catch:{ Exception -> 0x00ec }
            int r9 = (int) r14     // Catch:{ Exception -> 0x00ec }
            r21 = r13
            r26 = r9
            r21.<init>(r22, r24, r26)     // Catch:{ Exception -> 0x00ec }
            r0.startLoc = r13     // Catch:{ Exception -> 0x00ec }
            com.navibees.core.model.metadata.json.Location r9 = new com.navibees.core.model.metadata.json.Location     // Catch:{ Exception -> 0x00ec }
            double r27 = r10.getMXOrLongitude()     // Catch:{ Exception -> 0x00ec }
            double r29 = r10.getMYOrLatitude()     // Catch:{ Exception -> 0x00ec }
            double r13 = r10.getMZOrAltitude()     // Catch:{ Exception -> 0x00ec }
            int r10 = (int) r13     // Catch:{ Exception -> 0x00ec }
            r26 = r9
            r31 = r10
            r26.<init>(r27, r29, r31)     // Catch:{ Exception -> 0x00ec }
            r0.endLoc = r9     // Catch:{ Exception -> 0x00ec }
            float r9 = r17.getDuration()     // Catch:{ Exception -> 0x00ec }
            double r9 = (double) r9     // Catch:{ Exception -> 0x00ec }
            r0.duration = r9     // Catch:{ Exception -> 0x00ec }
            double r9 = r17.getLength()     // Catch:{ Exception -> 0x00ec }
            r0.distance = r9     // Catch:{ Exception -> 0x00ec }
            float r9 = r17.getTime()     // Catch:{ Exception -> 0x00ec }
            double r9 = (double) r9     // Catch:{ Exception -> 0x00ec }
            r0.time = r9     // Catch:{ Exception -> 0x00ec }
            float r9 = r17.getTotalTime()     // Catch:{ Exception -> 0x00ec }
            double r9 = (double) r9     // Catch:{ Exception -> 0x00ec }
            r0.totalTime = r9     // Catch:{ Exception -> 0x00ec }
            com.visioglobe.libVisioMove.VgManeuverType r9 = r17.getManeuverType()     // Catch:{ Exception -> 0x00ec }
            java.lang.String r9 = r1.m324a(r9)     // Catch:{ Exception -> 0x00ec }
            r0.maneuverType = r9     // Catch:{ Exception -> 0x00ec }
            r7.add(r0)     // Catch:{ Exception -> 0x00ec }
            goto L_0x00f0
        L_0x00ec:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00f0:
            int r2 = r2 + 1
            r9 = r16
            r10 = r20
            goto L_0x0031
        L_0x00f8:
            r16 = r9
            r20 = r10
            com.navibees.core.interfaces.MapHoverInterface r2 = r1.f555g
            if (r2 == 0) goto L_0x0103
            r2.onNavigationInstructionComputed(r3, r5, r7)
        L_0x0103:
            java.lang.String r9 = ""
            int r0 = (r34 > r11 ? 1 : (r34 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x016c
            com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr r0 = r33.getInstruction(r34)     // Catch:{ Exception -> 0x0164 }
            com.navibees.maps.h$b r10 = new com.navibees.maps.h$b     // Catch:{ Exception -> 0x0164 }
            r10.<init>()     // Catch:{ Exception -> 0x0164 }
            com.navibees.maps.h$b r13 = new com.navibees.maps.h$b     // Catch:{ Exception -> 0x0164 }
            r13.<init>()     // Catch:{ Exception -> 0x0164 }
            int r6 = r1.f554f     // Catch:{ Exception -> 0x0164 }
            android.app.Activity r2 = r1.f550b     // Catch:{ Exception -> 0x0164 }
            android.app.Application r7 = r2.getApplication()     // Catch:{ Exception -> 0x0164 }
            r2 = r0
            r3 = r33
            r4 = r10
            r5 = r20
            com.navibees.maps.C1446h.m349a(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0164 }
            r6 = 0
            android.app.Activity r2 = r1.f550b     // Catch:{ Exception -> 0x0164 }
            android.app.Application r7 = r2.getApplication()     // Catch:{ Exception -> 0x0164 }
            r2 = r0
            r3 = r33
            r4 = r13
            r5 = r20
            com.navibees.maps.C1446h.m349a(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0164 }
            com.visioglobe.libVisioMove.VgManeuverType r2 = r0.getManeuverType()     // Catch:{ Exception -> 0x0164 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0160 }
            r0.<init>()     // Catch:{ Exception -> 0x0160 }
            r3 = 1
            long r3 = r34 + r3
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x0160 }
            r0.append(r3)     // Catch:{ Exception -> 0x0160 }
            java.lang.String r3 = "/"
            r0.append(r3)     // Catch:{ Exception -> 0x0160 }
            r0.append(r11)     // Catch:{ Exception -> 0x0160 }
            r0.toString()     // Catch:{ Exception -> 0x0160 }
            java.lang.String r3 = r10.f605b     // Catch:{ Exception -> 0x0160 }
            java.lang.String r9 = r13.f605b     // Catch:{ Exception -> 0x015c }
            goto L_0x016f
        L_0x015c:
            r0 = move-exception
            r16 = r2
            goto L_0x0166
        L_0x0160:
            r0 = move-exception
            r16 = r2
            goto L_0x0165
        L_0x0164:
            r0 = move-exception
        L_0x0165:
            r3 = r9
        L_0x0166:
            r0.printStackTrace()
            r2 = r16
            goto L_0x016f
        L_0x016c:
            r3 = r9
            r2 = r16
        L_0x016f:
            android.app.Activity r0 = r1.f550b
            com.navibees.maps.f$a r4 = new com.navibees.maps.f$a
            r4.<init>(r3, r9, r2)
            r0.runOnUiThread(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.C1432f.mo28444b(com.visioglobe.libVisioMove.VgINavigationConstRefPtr, long):void");
    }

    public void clear() {
        hide();
    }

    public void hide() {
    }

    public boolean isVisible() {
        FrameLayout frameLayout = this.f551c;
        if (frameLayout == null || frameLayout.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public void release() {
        this.f550b = null;
        FrameLayout frameLayout = this.f551c;
        if (!(frameLayout == null || frameLayout.getParent() == null)) {
            ((ViewGroup) this.f551c.getParent()).removeView(this.f551c);
        }
        this.f552d = null;
        FrameLayout frameLayout2 = this.f553e;
        if (frameLayout2 != null && frameLayout2.getParent() != null) {
            ((ViewGroup) this.f553e.getParent()).removeView(this.f553e);
        }
    }

    public void show() {
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m324a(VgManeuverType vgManeuverType) {
        return this.f549a[vgManeuverType.swigValue()];
    }

    /* renamed from: a */
    public void mo28374a(VgINavigationConstRefPtr vgINavigationConstRefPtr, long j) {
        vgINavigationConstRefPtr.set(vgINavigationConstRefPtr.get());
        mo28444b(vgINavigationConstRefPtr, j);
    }
}
