package com.navibees.navigatorapp.p010ui.activities;

import androidx.appcompat.app.AppCompatActivity;

/* renamed from: com.navibees.navigatorapp.ui.activities.SplashActivity */
public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            super.onCreate(r8)
            android.view.Window r0 = r7.getWindow()
            r1 = 512(0x200, float:7.175E-43)
            r0.setFlags(r1, r1)
            r1 = 2131558439(0x7f0d0027, float:1.8742194E38)
            r7.setContentView(r1)
            android.content.Context r1 = r7.getApplicationContext()
            com.navibees.navigatorapp.data.Prefs r1 = com.navibees.navigatorapp.data.Prefs.getInstance(r1)
            java.lang.String r1 = r1.getOperationVenueName()
            int r2 = r1.hashCode()
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r2) {
                case -442449034: goto L_0x0047;
                case -414964852: goto L_0x003d;
                case 72607: goto L_0x0033;
                case 2304049: goto L_0x0029;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x0051
        L_0x0029:
            java.lang.String r2 = "KFMC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0028
            r1 = 3
            goto L_0x0052
        L_0x0033:
            java.lang.String r2 = "IMC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0028
            r1 = 2
            goto L_0x0052
        L_0x003d:
            java.lang.String r2 = "Riyadh Airport > KKIA"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0028
            r1 = 0
            goto L_0x0052
        L_0x0047:
            java.lang.String r2 = "Masjid_ul_Haram"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0028
            r1 = 1
            goto L_0x0052
        L_0x0051:
            r1 = -1
        L_0x0052:
            if (r1 == 0) goto L_0x006a
            if (r1 == r5) goto L_0x0066
            if (r1 == r4) goto L_0x0062
            if (r1 == r3) goto L_0x005e
            r1 = 2131231050(0x7f08014a, float:1.807817E38)
            goto L_0x006e
        L_0x005e:
            r1 = 2131231054(0x7f08014e, float:1.8078178E38)
            goto L_0x006e
        L_0x0062:
            r1 = 2131231053(0x7f08014d, float:1.8078176E38)
            goto L_0x006e
        L_0x0066:
            r1 = 2131231052(0x7f08014c, float:1.8078174E38)
            goto L_0x006e
        L_0x006a:
            r1 = 2131231051(0x7f08014b, float:1.8078172E38)
        L_0x006e:
            r2 = 2131362061(0x7f0a010d, float:1.8343892E38)
            android.view.View r2 = r7.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            r2.setImageResource(r1)
            boolean r3 = com.navibees.core.NaviBeesApplication.isLicenseExpired
            if (r3 != 0) goto L_0x008e
            android.os.Handler r3 = new android.os.Handler
            r3.<init>()
            com.navibees.navigatorapp.ui.activities.SplashActivity$1 r4 = new com.navibees.navigatorapp.ui.activities.SplashActivity$1
            r4.<init>()
            r5 = 2000(0x7d0, double:9.88E-321)
            r3.postDelayed(r4, r5)
            goto L_0x00b0
        L_0x008e:
            android.app.AlertDialog$Builder r3 = new android.app.AlertDialog$Builder
            r3.<init>(r7)
            java.lang.String r4 = "License Expired"
            android.app.AlertDialog$Builder r4 = r3.setTitle(r4)
            java.lang.String r5 = "your license is expired, please contact your administrator to renew"
            android.app.AlertDialog$Builder r4 = r4.setMessage(r5)
            com.navibees.navigatorapp.ui.activities.SplashActivity$2 r5 = new com.navibees.navigatorapp.ui.activities.SplashActivity$2
            r5.<init>()
            java.lang.String r6 = "OK"
            r4.setPositiveButton(r6, r5)
            android.app.AlertDialog r4 = r3.create()
            r4.show()
        L_0x00b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.SplashActivity.onCreate(android.os.Bundle):void");
    }
}
