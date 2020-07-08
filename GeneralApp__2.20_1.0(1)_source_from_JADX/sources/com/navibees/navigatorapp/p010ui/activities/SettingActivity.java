package com.navibees.navigatorapp.p010ui.activities;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.navibees.navigatorapp.C1170R;

/* renamed from: com.navibees.navigatorapp.ui.activities.SettingActivity */
public class SettingActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r5) {
        /*
            r4 = this;
            super.onCreate(r5)
            android.content.Context r0 = r4.getApplicationContext()
            com.navibees.navigatorapp.data.Prefs r0 = com.navibees.navigatorapp.data.Prefs.getInstance(r0)
            java.lang.String r0 = r0.getOperationVenueName()
            int r1 = r0.hashCode()
            r2 = -442449034(0xffffffffe5a0c376, float:-9.489803E22)
            r3 = 1
            if (r1 == r2) goto L_0x0029
            r2 = 2304049(0x232831, float:3.22866E-39)
            if (r1 == r2) goto L_0x001f
        L_0x001e:
            goto L_0x0033
        L_0x001f:
            java.lang.String r1 = "KFMC"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001e
            r0 = 1
            goto L_0x0034
        L_0x0029:
            java.lang.String r1 = "Masjid_ul_Haram"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001e
            r0 = 0
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            if (r0 == 0) goto L_0x0046
            if (r0 == r3) goto L_0x003f
            r0 = 2131951625(0x7f130009, float:1.953967E38)
            r4.setTheme(r0)
            goto L_0x004d
        L_0x003f:
            r0 = 2131951626(0x7f13000a, float:1.9539672E38)
            r4.setTheme(r0)
            goto L_0x004d
        L_0x0046:
            r0 = 2131951627(0x7f13000b, float:1.9539674E38)
            r4.setTheme(r0)
        L_0x004d:
            r0 = 2131558438(0x7f0d0026, float:1.8742192E38)
            r4.setContentView(r0)
            r4.setupToolBar()
            android.app.FragmentManager r0 = r4.getFragmentManager()
            android.app.FragmentTransaction r1 = r0.beginTransaction()
            com.navibees.navigatorapp.ui.fragments.SettingFragment r2 = new com.navibees.navigatorapp.ui.fragments.SettingFragment
            r2.<init>()
            r3 = 2131362223(0x7f0a01af, float:1.834422E38)
            r1.replace(r3, r2)
            r1.commit()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.SettingActivity.onCreate(android.os.Bundle):void");
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(C1170R.C1173id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    SettingActivity.this.onBackPressed();
                }
            });
        }
    }

    private void setupActionBar() {
        try {
            if (getActionBarLayoutRes() != -1) {
                View customView = LayoutInflater.from(this).inflate(getActionBarLayoutRes(), null);
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayOptions(16);
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setCustomView(customView, new LayoutParams(-1, -1, 17));
                ((Toolbar) customView.getParent()).setContentInsetsAbsolute(0, 0);
                customView.setBackgroundResource(getActionBarLayoutBackgroundRes());
                TextView title = (TextView) customView.findViewById(C1170R.C1173id.action_bar_title);
                if (title != null) {
                    title.setText(getTitle());
                    customiseActionBarTitle(title);
                }
                customView.findViewById(C1170R.C1173id.up_navigation).setBackgroundResource(getActionBarLayoutBackgroundRes());
                customView.findViewById(C1170R.C1173id.up_navigation).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        SettingActivity.this.onBackPressed();
                    }
                });
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutRes() {
        return C1170R.layout.com_navibees_sdk_about_activity_action_bar;
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutBackgroundRes() {
        return C1170R.C1172drawable.com_navibees_sdk_about_activity_action_bar_layout_bg;
    }

    /* access modifiers changed from: protected */
    public void customiseActionBarTitle(TextView title) {
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(67108864);
        startActivity(intent);
    }
}
