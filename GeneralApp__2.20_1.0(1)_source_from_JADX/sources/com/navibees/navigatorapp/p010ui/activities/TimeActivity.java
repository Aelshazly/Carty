package com.navibees.navigatorapp.p010ui.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.navibees.navigatorapp.C1170R;

/* renamed from: com.navibees.navigatorapp.ui.activities.TimeActivity */
public class TimeActivity extends AppCompatActivity {
    private TextView txtMonday;
    private TextView txtSunday;
    private TextView txtThursday;
    private TextView txtTuesday;
    private TextView txtWednesday;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C1170R.layout.activity_time);
        Toolbar toolbar = (Toolbar) findViewById(C1170R.C1173id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(C1170R.string.opening_hours));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    TimeActivity.this.onBackPressed();
                }
            });
        }
        this.txtSunday = (TextView) findViewById(C1170R.C1173id.txtSunday);
        this.txtMonday = (TextView) findViewById(C1170R.C1173id.txtMonday);
        this.txtTuesday = (TextView) findViewById(C1170R.C1173id.txtTuesday);
        this.txtWednesday = (TextView) findViewById(C1170R.C1173id.txtWednesday);
        this.txtThursday = (TextView) findViewById(C1170R.C1173id.txtThursday);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(C1170R.string.six));
        String str = " ";
        sb.append(str);
        sb.append(getString(C1170R.string.oct));
        String str2 = ", ";
        sb.append(str2);
        sb.append(getString(C1170R.string.thirteen));
        String str3 = " - ";
        sb.append(str3);
        sb.append(getString(C1170R.string.nineteen));
        String sunday = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(C1170R.string.seven));
        sb2.append(str);
        sb2.append(getString(C1170R.string.oct));
        sb2.append(str2);
        sb2.append(getString(C1170R.string.eleven));
        sb2.append(str3);
        sb2.append(getString(C1170R.string.nineteen));
        String monday = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(C1170R.string.eight));
        sb3.append(str);
        sb3.append(getString(C1170R.string.oct));
        sb3.append(str2);
        sb3.append(getString(C1170R.string.eleven));
        sb3.append(str3);
        sb3.append(getString(C1170R.string.nineteen));
        String tuesday = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getString(C1170R.string.nine));
        sb4.append(str);
        sb4.append(getString(C1170R.string.oct));
        sb4.append(str2);
        sb4.append(getString(C1170R.string.eleven));
        sb4.append(str3);
        sb4.append(getString(C1170R.string.nineteen));
        String wednesday = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(getString(C1170R.string.ten));
        sb5.append(str);
        sb5.append(getString(C1170R.string.oct));
        sb5.append(str2);
        sb5.append(getString(C1170R.string.eleven));
        sb5.append(str3);
        sb5.append(getString(C1170R.string.seventeen));
        String thursday = sb5.toString();
        this.txtSunday.setText(sunday);
        this.txtMonday.setText(monday);
        this.txtTuesday.setText(tuesday);
        this.txtWednesday.setText(wednesday);
        this.txtThursday.setText(thursday);
    }
}
