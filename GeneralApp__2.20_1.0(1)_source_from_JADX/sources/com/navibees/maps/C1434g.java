package com.navibees.maps;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.navibees.C1164R;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.visioglobe.VgMySurfaceView;
import com.navibees.visioglobe.p020g.C1513c;
import com.navibees.visioglobe.p020g.C1514d;
import com.visioglobe.libVisioMove.VgINavigationConstRefPtr;
import com.visioglobe.libVisioMove.VgManeuverType;

/* renamed from: com.navibees.maps.g */
/* compiled from: VgMyInstructionViewStaticRouting */
public class C1434g implements C1513c {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int[] f560a = new int[VgManeuverType.eVgManeuverTypeMax.swigValue()];

    /* renamed from: b */
    Activity f561b = null;

    /* renamed from: c */
    C1514d f562c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public FrameLayout f563d = null;

    /* renamed from: e */
    VgMySurfaceView f564e = null;

    /* renamed from: f */
    FrameLayout f565f;

    /* renamed from: g */
    private long f566g = 0;

    /* renamed from: h */
    private int f567h = 0;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public MapHoverInterface f568i;

    /* renamed from: com.navibees.maps.g$a */
    /* compiled from: VgMyInstructionViewStaticRouting */
    class C1435a implements Runnable {

        /* renamed from: com.navibees.maps.g$a$a */
        /* compiled from: VgMyInstructionViewStaticRouting */
        class C1436a implements OnClickListener {

            /* renamed from: com.navibees.maps.g$a$a$a */
            /* compiled from: VgMyInstructionViewStaticRouting */
            class C1437a implements Runnable {
                C1437a() {
                }

                public void run() {
                    C1434g.this.m333e();
                }
            }

            C1436a() {
            }

            public void onClick(View view) {
                C1434g.this.f564e.queueEvent(new C1437a());
            }
        }

        /* renamed from: com.navibees.maps.g$a$b */
        /* compiled from: VgMyInstructionViewStaticRouting */
        class C1438b implements OnClickListener {

            /* renamed from: com.navibees.maps.g$a$b$a */
            /* compiled from: VgMyInstructionViewStaticRouting */
            class C1439a implements Runnable {
                C1439a() {
                }

                public void run() {
                    C1434g.this.m332d();
                }
            }

            C1438b() {
            }

            public void onClick(View view) {
                C1434g.this.f564e.queueEvent(new C1439a());
            }
        }

        C1435a() {
        }

        public void run() {
            if (C1434g.this.f563d == null) {
                C1434g.this.f563d = (FrameLayout) C1434g.this.f561b.getLayoutInflater().inflate(C1164R.layout.navigation_layout, null);
                C1434g.this.f563d.setVisibility(8);
                MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-1, -2);
                marginLayoutParams.setMargins(5, 5, 5, 5);
                C1434g gVar = C1434g.this;
                gVar.f565f.addView(gVar.f563d, marginLayoutParams);
                ((ImageButton) C1434g.this.f563d.findViewById(C1164R.C1167id.imageButtonLeftArrow)).setOnClickListener(new C1436a());
                ((ImageButton) C1434g.this.f563d.findViewById(C1164R.C1167id.imageButtonRightArrow)).setOnClickListener(new C1438b());
            }
        }
    }

    /* renamed from: com.navibees.maps.g$b */
    /* compiled from: VgMyInstructionViewStaticRouting */
    class C1440b implements Runnable {
        C1440b() {
        }

        public void run() {
            if (C1434g.this.f563d != null) {
                C1434g.this.f563d.setVisibility(0);
                C1434g.this.f563d.startAnimation(AnimationUtils.loadAnimation(C1434g.this.f561b, C1164R.anim.nav_instruction_on));
            }
        }
    }

    /* renamed from: com.navibees.maps.g$c */
    /* compiled from: VgMyInstructionViewStaticRouting */
    class C1441c implements Runnable {

        /* renamed from: com.navibees.maps.g$c$a */
        /* compiled from: VgMyInstructionViewStaticRouting */
        class C1442a implements AnimationListener {
            C1442a() {
            }

            public void onAnimationEnd(Animation animation) {
                C1434g.this.f563d.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        C1441c() {
        }

        public void run() {
            if (C1434g.this.f563d != null && 8 != C1434g.this.f563d.getVisibility()) {
                Animation loadAnimation = AnimationUtils.loadAnimation(C1434g.this.f561b, C1164R.anim.nav_instruction_off);
                loadAnimation.setAnimationListener(new C1442a());
                C1434g.this.f563d.startAnimation(loadAnimation);
            }
        }
    }

    /* renamed from: com.navibees.maps.g$d */
    /* compiled from: VgMyInstructionViewStaticRouting */
    class C1443d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f577a;

        /* renamed from: b */
        final /* synthetic */ String f578b;

        /* renamed from: c */
        final /* synthetic */ VgManeuverType f579c;

        C1443d(String str, String str2, VgManeuverType vgManeuverType) {
            this.f577a = str;
            this.f578b = str2;
            this.f579c = vgManeuverType;
        }

        public void run() {
            if (C1434g.this.f568i != null) {
                C1434g.this.f568i.updateNavigationMessage(this.f577a, this.f578b, this.f579c.toString());
            }
        }
    }

    /* renamed from: com.navibees.maps.g$e */
    /* compiled from: VgMyInstructionViewStaticRouting */
    class C1444e implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f581a;

        /* renamed from: b */
        final /* synthetic */ String f582b;

        /* renamed from: c */
        final /* synthetic */ String f583c;

        C1444e(String str, String str2, String str3) {
            this.f581a = str;
            this.f582b = str2;
            this.f583c = str3;
        }

        public void run() {
            if (C1434g.this.f563d != null) {
                ((TextView) C1434g.this.f563d.findViewById(C1164R.C1167id.textViewNavigationTitle)).setText(this.f581a);
                ((TextView) C1434g.this.f563d.findViewById(C1164R.C1167id.textViewNavigationInstruction)).setText(this.f582b);
                ((TextView) C1434g.this.f563d.findViewById(C1164R.C1167id.textViewInstructionNumber)).setText(this.f583c);
            }
        }
    }

    /* renamed from: com.navibees.maps.g$f */
    /* compiled from: VgMyInstructionViewStaticRouting */
    class C1445f implements Runnable {

        /* renamed from: a */
        final /* synthetic */ VgManeuverType f585a;

        C1445f(VgManeuverType vgManeuverType) {
            this.f585a = vgManeuverType;
        }

        public void run() {
            if (C1434g.this.f563d != null) {
                ((ImageView) C1434g.this.f563d.findViewById(C1164R.C1167id.imageViewNavigation)).setImageResource(C1434g.this.f560a[this.f585a.swigValue()]);
            }
        }
    }

    public C1434g(Activity activity, C1514d dVar, VgMySurfaceView vgMySurfaceView, FrameLayout frameLayout, MapHoverInterface mapHoverInterface) {
        this.f568i = mapHoverInterface;
        this.f561b = activity;
        this.f562c = dVar;
        this.f564e = vgMySurfaceView;
        this.f565f = frameLayout;
        this.f560a[VgManeuverType.eVgManeuverTypeEnd.swigValue()] = C1164R.C1166drawable.transit_instruction_end;
        this.f560a[VgManeuverType.eVgManeuverTypeWaypoint.swigValue()] = C1164R.C1166drawable.transit_instruction_intermediate_destination;
        this.f560a[VgManeuverType.eVgManeuverTypeGoDown.swigValue()] = C1164R.C1166drawable.transit_instruction_down;
        this.f560a[VgManeuverType.eVgManeuverTypeGoUp.swigValue()] = C1164R.C1166drawable.transit_instruction_up;
        this.f560a[VgManeuverType.eVgManeuverTypeStart.swigValue()] = C1164R.C1166drawable.transit_instruction_start;
        this.f560a[VgManeuverType.eVgManeuverTypeGoStraight.swigValue()] = C1164R.C1166drawable.transit_instruction_straight;
        this.f560a[VgManeuverType.eVgManeuverTypeTurnGentleLeft.swigValue()] = C1164R.C1166drawable.transit_instruction_turn_gentle_left;
        this.f560a[VgManeuverType.eVgManeuverTypeTurnGentleRight.swigValue()] = C1164R.C1166drawable.transit_instruction_turn_gentle_right;
        this.f560a[VgManeuverType.eVgManeuverTypeTurnLeft.swigValue()] = C1164R.C1166drawable.transit_instruction_turn_left;
        this.f560a[VgManeuverType.eVgManeuverTypeTurnRight.swigValue()] = C1164R.C1166drawable.transit_instruction_turn_right;
        this.f560a[VgManeuverType.eVgManeuverTypeTurnSharpLeft.swigValue()] = C1164R.C1166drawable.transit_instruction_turn_sharp_left;
        this.f560a[VgManeuverType.eVgManeuverTypeTurnSharpRight.swigValue()] = C1164R.C1166drawable.transit_instruction_turn_sharp_right;
        this.f560a[VgManeuverType.eVgManeuverTypeUTurnLeft.swigValue()] = C1164R.C1166drawable.transit_instruction_uturn_left;
        this.f560a[VgManeuverType.eVgManeuverTypeUTurnRight.swigValue()] = C1164R.C1166drawable.transit_instruction_uturn_right;
        this.f560a[VgManeuverType.eVgManeuverTypeChangeModality.swigValue()] = C1164R.C1166drawable.transit_instruction_modality_change;
        this.f560a[VgManeuverType.eVgManeuverTypeChangeLayer.swigValue()] = C1164R.C1166drawable.transit_instruction_layer_change;
        this.f567h = C1446h.m342a(NaviBeesUtils.getAppLang());
        mo28446a();
    }

    public void clear() {
        hide();
    }

    public void hide() {
        mo28449b();
    }

    public boolean isVisible() {
        FrameLayout frameLayout = this.f563d;
        if (frameLayout == null || frameLayout.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public void release() {
        this.f561b = null;
        this.f562c = null;
        FrameLayout frameLayout = this.f563d;
        if (!(frameLayout == null || frameLayout.getParent() == null)) {
            ((ViewGroup) this.f563d.getParent()).removeView(this.f563d);
        }
        this.f564e = null;
        FrameLayout frameLayout2 = this.f565f;
        if (frameLayout2 != null && frameLayout2.getParent() != null) {
            ((ViewGroup) this.f565f.getParent()).removeView(this.f565f);
        }
    }

    public void show() {
        mo28451c();
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m332d() {
        this.f562c.mo28202c((int) (this.f566g + 1));
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m333e() {
        this.f562c.mo28202c((int) (this.f566g - 1));
    }

    /* renamed from: b */
    public void mo28449b() {
        this.f561b.runOnUiThread(new C1441c());
    }

    /* renamed from: c */
    public void mo28451c() {
        this.f561b.runOnUiThread(new C1440b());
    }

    /* renamed from: a */
    public void mo28446a() {
        this.f561b.runOnUiThread(new C1435a());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0080  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo28450b(com.visioglobe.libVisioMove.VgINavigationConstRefPtr r17, long r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            com.visioglobe.libVisioMove.VgManeuverType r3 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeUnknown
            com.navibees.visioglobe.VgMySurfaceView r4 = r0.f564e
            com.visioglobe.libVisioMove.VgIApplication r4 = r4.getApplication()
            com.visioglobe.libVisioMove.VgIModuleManager r4 = r4.editModuleManager()
            if (r4 == 0) goto L_0x001f
            java.lang.String r5 = "Map"
            com.visioglobe.libVisioMove.VgIModule r4 = r4.queryModule(r5)
            if (r4 == 0) goto L_0x001f
            com.visioglobe.libVisioMove.VgIMapModule r4 = com.visioglobe.libVisioMove.C1732libVisioMove.castToIMapModule(r4)
            goto L_0x0020
        L_0x001f:
            r4 = 0
        L_0x0020:
            long r11 = r17.getNumInstructions()
            java.lang.String r5 = ""
            int r6 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x0080
            com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr r3 = r17.getInstruction(r18)
            com.navibees.maps.h$b r13 = new com.navibees.maps.h$b
            r13.<init>()
            com.navibees.maps.h$b r14 = new com.navibees.maps.h$b
            r14.<init>()
            int r9 = r0.f567h
            android.app.Activity r5 = r0.f561b
            android.app.Application r10 = r5.getApplication()
            r5 = r3
            r6 = r17
            r7 = r13
            r8 = r4
            com.navibees.maps.C1446h.m349a(r5, r6, r7, r8, r9, r10)
            android.app.Activity r5 = r0.f561b
            android.app.Application r10 = r5.getApplication()
            r9 = 0
            r5 = r3
            r7 = r14
            com.navibees.maps.C1446h.m349a(r5, r6, r7, r8, r9, r10)
            com.visioglobe.libVisioMove.VgManeuverType r3 = r3.getManeuverType()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 1
            long r5 = r5 + r1
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r4.append(r5)
            java.lang.String r5 = "/"
            r4.append(r5)
            r4.append(r11)
            java.lang.String r5 = r4.toString()
            java.lang.String r4 = r13.f605b
            java.lang.String r6 = r14.f605b
            java.lang.String r7 = r13.f604a
            java.lang.String r8 = r13.f606c
            r15 = r6
            r6 = r5
            r5 = r7
            r7 = r15
            goto L_0x0084
        L_0x0080:
            r4 = r5
            r6 = r4
            r7 = r6
            r8 = r7
        L_0x0084:
            r0.mo28448a(r5, r4, r6, r8)
            r0.mo28447a(r3)
            android.app.Activity r5 = r0.f561b
            com.navibees.maps.g$d r6 = new com.navibees.maps.g$d
            r6.<init>(r4, r7, r3)
            r5.runOnUiThread(r6)
            r0.f566g = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.C1434g.mo28450b(com.visioglobe.libVisioMove.VgINavigationConstRefPtr, long):void");
    }

    /* renamed from: a */
    public void mo28448a(String str, String str2, String str3, String str4) {
        this.f561b.runOnUiThread(new C1444e(str, str2, str3));
    }

    /* renamed from: a */
    public void mo28447a(VgManeuverType vgManeuverType) {
        this.f561b.runOnUiThread(new C1445f(vgManeuverType));
    }

    /* renamed from: a */
    public void mo28374a(VgINavigationConstRefPtr vgINavigationConstRefPtr, long j) {
        vgINavigationConstRefPtr.set(vgINavigationConstRefPtr.get());
        mo28450b(vgINavigationConstRefPtr, j);
    }
}
