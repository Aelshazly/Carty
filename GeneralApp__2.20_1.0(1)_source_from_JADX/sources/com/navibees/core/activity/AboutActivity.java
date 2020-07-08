package com.navibees.core.activity;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.LayoutParams;
import androidx.appcompat.widget.Toolbar;
import com.navibees.sdk.C1266R;

public class AboutActivity extends NaviBeesActivity {

    /* renamed from: a */
    private int f955a = C1266R.layout.com_navibees_sdk_about_activity;

    /* renamed from: b */
    private void m637b() {
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
                TextView textView = (TextView) inflate.findViewById(C1266R.C1269id.action_bar_title);
                if (textView != null) {
                    textView.setText(getTitle());
                    customiseActionBarTitle(textView);
                }
                inflate.findViewById(C1266R.C1269id.up_navigation).setBackgroundResource(getActionBarLayoutBackgroundRes());
                inflate.findViewById(C1266R.C1269id.up_navigation).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        AboutActivity.this.onBackPressed();
                    }
                });
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m638c() {
        Toolbar toolbar = (Toolbar) findViewById(C1266R.C1269id.about_toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AboutActivity.this.onBackPressed();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void customiseActionBarTitle(TextView textView) {
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutBackgroundRes() {
        return C1266R.C1268drawable.com_navibees_sdk_about_activity_action_bar_layout_bg;
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutRes() {
        return C1266R.layout.com_navibees_sdk_about_activity_action_bar;
    }

    /* access modifiers changed from: protected */
    public int getContentViewRes() {
        return this.f955a;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentViewRes());
        m638c();
    }
}
