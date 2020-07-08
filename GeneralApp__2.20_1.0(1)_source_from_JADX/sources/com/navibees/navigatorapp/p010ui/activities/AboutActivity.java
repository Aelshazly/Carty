package com.navibees.navigatorapp.p010ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.utils.LocaleUtils;

/* renamed from: com.navibees.navigatorapp.ui.activities.AboutActivity */
public class AboutActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C1170R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(C1170R.C1173id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(LocaleUtils.getLocalizedResources(this).getStringArray(C1170R.array.items)[getIntent().getIntExtra(Param.INDEX, 0)]);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AboutActivity.this.onBackPressed();
                }
            });
        }
    }

    public void aboutWadiMakkah(View view) {
        Intent intent = new Intent(getApplicationContext(), AboutDetailActivity.class);
        intent.putExtra("about", getString(C1170R.string.about_wm));
        intent.putExtra("title", getString(C1170R.string.about_wadimakkah));
        startActivity(intent);
    }

    public void aboutAveros(View view) {
        Intent intent = new Intent(getApplicationContext(), AboutDetailActivity.class);
        intent.putExtra("about", getString(C1170R.string.about_av));
        intent.putExtra("title", getString(C1170R.string.about_averos));
        startActivity(intent);
    }

    public void openingHours(View view) {
        startActivity(new Intent(getApplicationContext(), TimeActivity.class));
    }
}
