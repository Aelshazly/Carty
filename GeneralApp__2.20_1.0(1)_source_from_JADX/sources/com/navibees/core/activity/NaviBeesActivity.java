package com.navibees.core.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.util.NaviBeesUtils;
import java.util.Locale;

public class NaviBeesActivity extends AppCompatActivity {
    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo28850a() {
        NaviBeesUtils.showNeededPermissionsToReadBeacons(this);
    }

    public void applyOverrideConfiguration(Configuration configuration) {
        int i = VERSION.SDK_INT;
        if (i >= 21 && i <= 25) {
            configuration.setLocale(new Locale(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(NaviBeesManager.LANGUAGE_KEY, Locale.getDefault().getDisplayLanguage())));
        }
        super.applyOverrideConfiguration(configuration);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(NaviBeesManager.adjustLanguage(context));
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        NaviBeesUtils.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        NaviBeesManager.getInstance(getApplication()).activityOnStart(this);
        mo28850a();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        NaviBeesManager.getInstance(getApplication()).activityOnStop(this);
    }
}
