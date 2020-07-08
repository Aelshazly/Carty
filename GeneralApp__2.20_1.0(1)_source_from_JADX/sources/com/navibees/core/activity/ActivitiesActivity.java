package com.navibees.core.activity;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.LayoutParams;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.metadata.json.Activity;
import com.navibees.core.p024c.C1640b;
import com.navibees.core.p024c.C1641c;
import com.navibees.sdk.C1266R;

public class ActivitiesActivity extends NaviBeesActivity {

    /* renamed from: a */
    private SearchView f958a = null;

    /* renamed from: b */
    private TextView f959b = null;

    /* renamed from: e */
    private void m639e() {
        try {
            if (getActionBarLayoutRes() != -1) {
                View inflate = LayoutInflater.from(this).inflate(getActionBarLayoutRes(), null);
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setDisplayOptions(16);
                supportActionBar.setDisplayHomeAsUpEnabled(false);
                supportActionBar.setDisplayShowCustomEnabled(true);
                supportActionBar.setDisplayShowTitleEnabled(false);
                supportActionBar.setDisplayUseLogoEnabled(false);
                supportActionBar.setDisplayShowHomeEnabled(false);
                supportActionBar.setCustomView(inflate, new LayoutParams(-1, -1, 17));
                ((Toolbar) inflate.getParent()).setContentInsetsAbsolute(0, 0);
                inflate.setBackgroundResource(getActionBarLayoutBackgroundRes());
                this.f959b = (TextView) inflate.findViewById(C1266R.C1269id.action_bar_title);
                if (this.f959b != null) {
                    this.f959b.setText(getTitle());
                    customiseActionBarTitle(this.f959b);
                }
                inflate.findViewById(C1266R.C1269id.up_navigation).setBackgroundResource(getActionBarLayoutBackgroundRes());
                inflate.findViewById(C1266R.C1269id.up_navigation).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ActivitiesActivity.this.onBackPressed();
                    }
                });
                this.f958a = (SearchView) inflate.findViewById(C1266R.C1269id.searchView);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    private void m640f() {
        Toolbar toolbar = (Toolbar) findViewById(C1266R.C1269id.agenda_toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ActivitiesActivity.this.onBackPressed();
                }
            });
        }
    }

    /* renamed from: a */
    public void mo28755a(Activity activity) {
        mo28754a(C1641c.m917a(activity), true);
    }

    /* renamed from: b */
    public TextView mo28756b() {
        return this.f959b;
    }

    /* renamed from: c */
    public SearchView mo28757c() {
        return this.f958a;
    }

    /* access modifiers changed from: protected */
    public void customiseActionBarTitle(TextView textView) {
    }

    public void customiseActivityInfoIcon(ImageView imageView) {
    }

    /* renamed from: d */
    public void mo28760d() {
        mo28754a(new C1640b(), false);
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutBackgroundRes() {
        return C1266R.C1268drawable.f213x5d4690c8;
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutRes() {
        return C1266R.layout.com_navibees_sdk_activities_activity_action_bar;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setContentView(C1266R.layout.com_navibees_sdk_activities_activity);
        m640f();
        try {
            NaviBeesManager.getInstance(getApplication()).getLicenseManager().mo29048a(NaviBeesFeature.Temporal_Based_Event_Activities_Notification);
            mo28760d();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(bundle);
    }

    /* renamed from: a */
    public void mo28754a(Fragment fragment, boolean z) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(C1266R.C1269id.com_uqu_navibees_activities_activity_fragment_container, fragment);
        if (z) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commitAllowingStateLoss();
    }
}
