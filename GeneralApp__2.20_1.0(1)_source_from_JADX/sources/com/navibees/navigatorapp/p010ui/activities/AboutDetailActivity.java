package com.navibees.navigatorapp.p010ui.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.navibees.navigatorapp.C1170R;

/* renamed from: com.navibees.navigatorapp.ui.activities.AboutDetailActivity */
public class AboutDetailActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C1170R.layout.activity_about_detail);
        String text = getIntent().getStringExtra("about");
        String title = getIntent().getStringExtra("title");
        Toolbar toolbar = (Toolbar) findViewById(C1170R.C1173id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AboutDetailActivity.this.onBackPressed();
                }
            });
        }
        ((TextView) findViewById(C1170R.C1173id.txtAbout)).setText(text);
    }
}
