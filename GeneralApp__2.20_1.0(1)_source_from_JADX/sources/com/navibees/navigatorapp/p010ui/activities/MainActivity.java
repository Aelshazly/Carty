package com.navibees.navigatorapp.p010ui.activities;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.adapters.GridAdapter;
import com.navibees.navigatorapp.data.Prefs;
import com.navibees.navigatorapp.utils.MenuStatic;
import java.util.TimerTask;

/* renamed from: com.navibees.navigatorapp.ui.activities.MainActivity */
public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public int clickCount = 0;
    /* access modifiers changed from: private */
    public Integer[] images;
    private ImageView imgLogo;
    /* access modifiers changed from: private */
    public ImageView imgSettings;
    private TabLayout indicator;
    /* access modifiers changed from: private */
    public ViewPager viewPager;

    /* renamed from: com.navibees.navigatorapp.ui.activities.MainActivity$SliderTimer */
    private class SliderTimer extends TimerTask {
        private SliderTimer() {
        }

        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (MainActivity.this.viewPager.getCurrentItem() < MainActivity.this.images.length - 1) {
                        MainActivity.this.viewPager.setCurrentItem(MainActivity.this.viewPager.getCurrentItem() + 1);
                    } else {
                        MainActivity.this.viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r17) {
        /*
            r16 = this;
            r0 = r16
            super.onCreate(r17)
            android.view.Window r1 = r16.getWindow()
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1.addFlags(r2)
            r2 = 67108864(0x4000000, float:1.5046328E-36)
            r1.clearFlags(r2)
            android.content.Context r2 = r16.getApplicationContext()
            com.navibees.navigatorapp.data.Prefs r2 = com.navibees.navigatorapp.data.Prefs.getInstance(r2)
            java.lang.String r2 = r2.getOperationVenueName()
            int r3 = r2.hashCode()
            r4 = -442449034(0xffffffffe5a0c376, float:-9.489803E22)
            java.lang.String r5 = "KFMC"
            java.lang.String r6 = "Masjid_ul_Haram"
            r7 = -1
            r8 = 0
            r9 = 1
            if (r3 == r4) goto L_0x003d
            r4 = 2304049(0x232831, float:3.22866E-39)
            if (r3 == r4) goto L_0x0035
        L_0x0034:
            goto L_0x0045
        L_0x0035:
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0034
            r2 = 1
            goto L_0x0046
        L_0x003d:
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0034
            r2 = 0
            goto L_0x0046
        L_0x0045:
            r2 = -1
        L_0x0046:
            if (r2 == 0) goto L_0x007b
            if (r2 == r9) goto L_0x0062
            android.content.res.Resources r2 = r16.getResources()
            r3 = 2131099648(0x7f060000, float:1.7811655E38)
            int r2 = r2.getColor(r3)
            r1.setStatusBarColor(r2)
            android.content.Context r2 = r16.getApplicationContext()
            r3 = 2131951625(0x7f130009, float:1.953967E38)
            r2.setTheme(r3)
            goto L_0x0094
        L_0x0062:
            android.content.res.Resources r2 = r16.getResources()
            r3 = 2131099650(0x7f060002, float:1.781166E38)
            int r2 = r2.getColor(r3)
            r1.setStatusBarColor(r2)
            android.content.Context r2 = r16.getApplicationContext()
            r3 = 2131951626(0x7f13000a, float:1.9539672E38)
            r2.setTheme(r3)
            goto L_0x0094
        L_0x007b:
            android.content.res.Resources r2 = r16.getResources()
            r3 = 2131099652(0x7f060004, float:1.7811663E38)
            int r2 = r2.getColor(r3)
            r1.setStatusBarColor(r2)
            android.content.Context r2 = r16.getApplicationContext()
            r3 = 2131951627(0x7f13000b, float:1.9539674E38)
            r2.setTheme(r3)
        L_0x0094:
            r2 = 2131558433(0x7f0d0021, float:1.8742182E38)
            r0.setContentView(r2)
            r2 = 2131362328(0x7f0a0218, float:1.8344433E38)
            android.view.View r2 = r0.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            r0.imgLogo = r2
            r2 = 2131362342(0x7f0a0226, float:1.8344462E38)
            android.view.View r2 = r0.findViewById(r2)
            androidx.viewpager.widget.ViewPager r2 = (androidx.viewpager.widget.ViewPager) r2
            r0.viewPager = r2
            r2 = 2131362062(0x7f0a010e, float:1.8343894E38)
            android.view.View r2 = r0.findViewById(r2)
            com.google.android.material.tabs.TabLayout r2 = (com.google.android.material.tabs.TabLayout) r2
            r0.indicator = r2
            r2 = 2131362060(0x7f0a010c, float:1.834389E38)
            android.view.View r2 = r0.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            r0.imgSettings = r2
            android.content.Context r2 = r16.getApplicationContext()
            com.navibees.navigatorapp.data.Prefs r2 = com.navibees.navigatorapp.data.Prefs.getInstance(r2)
            java.lang.String r2 = r2.getOperationVenueName()
            int r3 = r2.hashCode()
            r4 = 3
            r10 = 2
            switch(r3) {
                case -442449034: goto L_0x00f8;
                case -414964852: goto L_0x00ee;
                case 72607: goto L_0x00e4;
                case 2304049: goto L_0x00dc;
                default: goto L_0x00db;
            }
        L_0x00db:
            goto L_0x00ff
        L_0x00dc:
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x00db
            r7 = 3
            goto L_0x00ff
        L_0x00e4:
            java.lang.String r3 = "IMC"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00db
            r7 = 2
            goto L_0x00ff
        L_0x00ee:
            java.lang.String r3 = "Riyadh Airport > KKIA"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00db
            r7 = 0
            goto L_0x00ff
        L_0x00f8:
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x00db
            r7 = 1
        L_0x00ff:
            if (r7 == 0) goto L_0x01a8
            if (r7 == r9) goto L_0x0180
            if (r7 == r10) goto L_0x0158
            if (r7 == r4) goto L_0x0130
            android.widget.ImageView r2 = r0.imgLogo
            r3 = 2131230823(0x7f080067, float:1.807771E38)
            r2.setImageResource(r3)
            java.lang.Integer[] r2 = new java.lang.Integer[r4]
            r3 = 2131230815(0x7f08005f, float:1.8077693E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            r3 = 2131230816(0x7f080060, float:1.8077695E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r9] = r3
            r3 = 2131230817(0x7f080061, float:1.8077697E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            r0.images = r2
            goto L_0x01d0
        L_0x0130:
            android.widget.ImageView r2 = r0.imgLogo
            r3 = 2131230971(0x7f0800fb, float:1.807801E38)
            r2.setImageResource(r3)
            java.lang.Integer[] r2 = new java.lang.Integer[r4]
            r3 = 2131230972(0x7f0800fc, float:1.8078012E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            r3 = 2131230973(0x7f0800fd, float:1.8078014E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r9] = r3
            r3 = 2131230974(0x7f0800fe, float:1.8078016E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            r0.images = r2
            goto L_0x01d0
        L_0x0158:
            android.widget.ImageView r2 = r0.imgLogo
            r3 = 2131230966(0x7f0800f6, float:1.8078E38)
            r2.setImageResource(r3)
            java.lang.Integer[] r2 = new java.lang.Integer[r4]
            r3 = 2131230967(0x7f0800f7, float:1.8078002E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            r3 = 2131230968(0x7f0800f8, float:1.8078004E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r9] = r3
            r3 = 2131230969(0x7f0800f9, float:1.8078006E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            r0.images = r2
            goto L_0x01d0
        L_0x0180:
            android.widget.ImageView r2 = r0.imgLogo
            r3 = 2131230940(0x7f0800dc, float:1.8077947E38)
            r2.setImageResource(r3)
            java.lang.Integer[] r2 = new java.lang.Integer[r4]
            r3 = 2131230941(0x7f0800dd, float:1.8077949E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            r3 = 2131230942(0x7f0800de, float:1.807795E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r9] = r3
            r3 = 2131230943(0x7f0800df, float:1.8077953E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            r0.images = r2
            goto L_0x01d0
        L_0x01a8:
            android.widget.ImageView r2 = r0.imgLogo
            r3 = 2131230978(0x7f080102, float:1.8078024E38)
            r2.setImageResource(r3)
            java.lang.Integer[] r2 = new java.lang.Integer[r4]
            r3 = 2131230975(0x7f0800ff, float:1.8078018E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            r3 = 2131230976(0x7f080100, float:1.807802E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r9] = r3
            r3 = 2131230977(0x7f080101, float:1.8078022E38)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            r0.images = r2
        L_0x01d0:
            androidx.viewpager.widget.ViewPager r2 = r0.viewPager
            com.navibees.navigatorapp.adapters.SliderAdapter r3 = new com.navibees.navigatorapp.adapters.SliderAdapter
            java.lang.Integer[] r4 = r0.images
            r3.<init>(r0, r4)
            r2.setAdapter(r3)
            com.google.android.material.tabs.TabLayout r2 = r0.indicator
            androidx.viewpager.widget.ViewPager r3 = r0.viewPager
            r2.setupWithViewPager(r3, r9)
            java.util.Timer r10 = new java.util.Timer
            r10.<init>()
            com.navibees.navigatorapp.ui.activities.MainActivity$SliderTimer r11 = new com.navibees.navigatorapp.ui.activities.MainActivity$SliderTimer
            r2 = 0
            r11.<init>()
            r12 = 4000(0xfa0, double:1.9763E-320)
            r14 = 6000(0x1770, double:2.9644E-320)
            r10.scheduleAtFixedRate(r11, r12, r14)
            android.widget.ImageView r2 = r0.imgSettings
            com.navibees.navigatorapp.ui.activities.MainActivity$1 r3 = new com.navibees.navigatorapp.ui.activities.MainActivity$1
            r3.<init>()
            r2.setOnClickListener(r3)
            r16.setupGridView()
            android.widget.ImageView r2 = r0.imgLogo
            com.navibees.navigatorapp.ui.activities.MainActivity$2 r3 = new com.navibees.navigatorapp.ui.activities.MainActivity$2
            r3.<init>()
            r2.setOnClickListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.MainActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public void resetClickCount() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MainActivity.this.clickCount = 0;
            }
        }, 2500);
    }

    /* access modifiers changed from: private */
    public void showDebugDialog() {
        Builder builder = new Builder(this);
        String str = "Advanced Debug";
        builder.setTitle("Advance debug View").setMessage("Please choose any debug view").setPositiveButton("Indoor Debug", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(new Intent(mainActivity, DebugIndoorLocationActivity.class));
            }
        }).setNegativeButton(str, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(new Intent(mainActivity, AdvancedDebugActivity.class));
            }
        });
        builder.create().show();
    }

    private void showDialog() {
        View promptsView = LayoutInflater.from(this).inflate(C1170R.layout.prompts, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(C1170R.C1173id.editTextDialogUserInput);
        String str = "Cancel";
        alertDialogBuilder.setCancelable(false).setPositiveButton((CharSequence) "OK", (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (userInput.getText().toString().equalsIgnoreCase("wadimakkah6754")) {
                    Prefs.getInstance(MainActivity.this.getApplicationContext()).saveKey(Prefs.IS_ADMIN, true);
                    MainActivity.this.imgSettings.setVisibility(8);
                    MainActivity.this.setupGridView();
                }
            }
        }).setNegativeButton((CharSequence) str, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    /* access modifiers changed from: private */
    public void showSettings() {
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
    }

    /* access modifiers changed from: private */
    public void setupGridView() {
        GridView gv = (GridView) findViewById(C1170R.C1173id.gridView);
        gv.setAdapter(new GridAdapter(getApplicationContext(), new MenuStatic(getApplicationContext())));
        gv.setOnItemClickListener(new OnItemClickListener() {
            Intent intent;

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String str = NaviBeesConstants.ENABLE_ROUTING_WHEN_OUT_OF_VENUE;
                String str2 = Param.INDEX;
                if (position == 0) {
                    this.intent = new Intent(MainActivity.this.getApplicationContext(), MapViewActivity.class);
                    this.intent.putExtra(str2, position);
                    this.intent.putExtra(str, false);
                    MainActivity.this.startActivity(this.intent);
                } else if (position == 1) {
                    this.intent = new Intent(MainActivity.this.getApplicationContext(), POIActivity.class);
                    this.intent.putExtra("isPOI", true);
                    this.intent.putExtra(str2, position);
                    MainActivity.this.startActivity(this.intent);
                } else if (position == 2) {
                    this.intent = new Intent(MainActivity.this.getApplicationContext(), POIActivity.class);
                    this.intent.putExtra("isFacilities", true);
                    this.intent.putExtra(str2, position);
                    MainActivity.this.startActivity(this.intent);
                } else if (position == 3) {
                    this.intent = new Intent(MainActivity.this.getApplicationContext(), MapViewActivity.class);
                    this.intent.putExtra(str2, position);
                    this.intent.putExtra(str, true);
                    MainActivity.this.startActivity(this.intent);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        NaviBeesManager.getInstance(getApplication()).activityOnStart(this);
        NaviBeesUtils.showNeededPermissionsToReadBeacons(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        NaviBeesManager.getInstance(getApplication()).activityOnStop(this);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        NaviBeesUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
