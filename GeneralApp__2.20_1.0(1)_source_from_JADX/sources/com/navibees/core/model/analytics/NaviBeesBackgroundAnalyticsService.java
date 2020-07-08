package com.navibees.core.model.analytics;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import androidx.preference.PreferenceManager;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.postioning.NBLocationListener;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.util.NaviBeesUtils;
import java.util.Timer;
import java.util.TimerTask;
import p008cz.msebera.android.httpclient.Header;

public class NaviBeesBackgroundAnalyticsService extends IntentService implements NBLocationListener {

    /* renamed from: a */
    private boolean f1244a;

    /* renamed from: b */
    private Timer f1245b;

    /* renamed from: c */
    private TimerTask f1246c = new C1645b();

    /* renamed from: com.navibees.core.model.analytics.NaviBeesBackgroundAnalyticsService$a */
    class C1644a extends AsyncHttpResponseHandler {
        C1644a() {
        }

        public boolean getUseSynchronousMode() {
            return true;
        }

        public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            NaviBeesBackgroundAnalyticsService.this.m922b();
            NaviBeesBackgroundAnalyticsService.this.m920a();
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            NaviBeesBackgroundAnalyticsService.this.m922b();
            NaviBeesBackgroundAnalyticsService.this.m920a();
        }
    }

    /* renamed from: com.navibees.core.model.analytics.NaviBeesBackgroundAnalyticsService$b */
    class C1645b extends TimerTask {
        C1645b() {
        }

        public void run() {
            NaviBeesBackgroundAnalyticsService.this.m920a();
            NaviBeesBackgroundAnalyticsService.this.m922b();
        }
    }

    public NaviBeesBackgroundAnalyticsService() {
        super(NaviBeesBackgroundAnalyticsService.class.getName());
    }

    public boolean isBackground() {
        return true;
    }

    public void locationCallback(IndoorLocation indoorLocation, IndoorLocation indoorLocation2, int i, boolean z, boolean z2) {
        if (z) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
            edit.putLong(NaviBeesConstants.BACKGROUND_ANALYTICS_LAST_TIMESTAMP_KEY, System.currentTimeMillis());
            edit.apply();
            NaviBeesManager.getInstance(getApplication()).getNavibeesAnalytics().mo29035a(getApplication(), indoorLocation2, (AsyncHttpResponseHandler) new C1644a());
            return;
        }
        m922b();
        m920a();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        try {
            if (NaviBeesManager.getInstance(getApplication()).isInitialized()) {
                PositionManager positionManager = NaviBeesManager.getInstance(getApplication()).getPositionManager();
                if (positionManager != null) {
                    positionManager.addLocationListener(this);
                    NaviBeesManager.getInstance(getApplication()).getNaviBeesBeaconManager().bindBeaconManager();
                    this.f1244a = true;
                    this.f1245b = new Timer();
                    this.f1245b.schedule(this.f1246c, 20000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m920a() {
        if (this.f1244a) {
            this.f1244a = false;
            NaviBeesManager.getInstance(getApplication()).getPositionManager().removeLocationListener(this);
            if (!NaviBeesUtils.isAppInForeground(getApplicationContext())) {
                NaviBeesManager.getInstance(getApplication()).getNaviBeesBeaconManager().enableBackgroundMonitoring();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m922b() {
        Timer timer = this.f1245b;
        if (timer != null) {
            timer.cancel();
            this.f1245b.purge();
            this.f1245b = null;
        }
    }
}
