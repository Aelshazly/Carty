package com.navibees.core.model.postioning;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import com.navibees.core.model.metadata.json.NavibeesApplication;
import com.navibees.core.model.metadata.json.Text;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(21)
public class MonitoringRegionsJobService extends JobService {

    /* renamed from: b */
    private static final String f1345b = "MonitoringRegionsJobService";

    /* renamed from: c */
    private static HashMap<String, Long> f1346c = new HashMap<>();

    /* renamed from: a */
    private Context f1347a;

    /* renamed from: g */
    private void m986g(MonitoredRegion monitoredRegion) {
        if (f1346c.containsKey(monitoredRegion.identifier)) {
            if (System.currentTimeMillis() - ((Long) f1346c.get(monitoredRegion.identifier)).longValue() < monitoredRegion.interval * 60000) {
                return;
            }
        }
        f1346c.put(monitoredRegion.identifier, Long.valueOf(System.currentTimeMillis()));
        StringBuilder sb = new StringBuilder();
        sb.append("Sending notificationv for: ");
        sb.append(monitoredRegion.identifier);
        Log.d("voyager", sb.toString());
        if (monitoredRegion.message == null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("_id", "5be3f9b821234f09bf696326172edc");
                jSONObject.put("ar", null);
                jSONObject.put("de", null);
                String str = "en";
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Welcome to ");
                sb2.append(monitoredRegion.name);
                jSONObject.put(str, sb2.toString());
                jSONObject.put("fr", null);
                jSONObject.put("ur", null);
                monitoredRegion.message = new Text(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(NaviBeesConstants.MONITORED_REGION_ACTION);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_OBJECT_KEY, monitoredRegion);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_ENTER_VALUE);
        this.f1347a.sendBroadcast(intent);
    }

    /* renamed from: h */
    private void m987h(MonitoredRegion monitoredRegion) {
        Text text = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("_id", "5be3f9b821234f09bf69632e522ededa");
            jSONObject.put("ar", null);
            jSONObject.put("de", null);
            String str = "en";
            StringBuilder sb = new StringBuilder();
            sb.append("Exit From ");
            sb.append(monitoredRegion.name);
            jSONObject.put(str, sb.toString());
            jSONObject.put("fr", null);
            jSONObject.put("ur", null);
            Text text2 = new Text(jSONObject);
            text = monitoredRegion.message;
            monitoredRegion.message = text2;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(NaviBeesConstants.MONITORED_REGION_ACTION);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_OBJECT_KEY, monitoredRegion);
        intent.putExtra(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY, NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_EXIT_VALUE);
        this.f1347a.sendBroadcast(intent);
        monitoredRegion.message = text;
        com.navibees.core.util.Log.m1171d(f1345b, monitoredRegion.message.getText());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo29257a(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didEnterRegionAll:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        com.navibees.core.util.Log.m1173i(f1345b, sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo29258b(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didEnterRegionBackground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        com.navibees.core.util.Log.m1173i(f1345b, sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo29259c(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didEnterRegionForeground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        com.navibees.core.util.Log.m1173i(f1345b, sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo29260d(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didExitRegionAll:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        com.navibees.core.util.Log.m1173i(f1345b, sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo29261e(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didExitRegionBackground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        com.navibees.core.util.Log.m1173i(f1345b, sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo29262f(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didExitRegionForeground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        com.navibees.core.util.Log.m1173i(f1345b, sb.toString());
    }

    public boolean onStartJob(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        this.f1347a = getApplicationContext();
        String str = f1345b;
        com.navibees.core.util.Log.m1173i(str, "-------------------------------------");
        com.navibees.core.util.Log.m1173i(str, "MontoringRegionsReceiver:onReceive");
        String string = extras.getString(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY);
        String string2 = extras.getString(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY);
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:regionIdentifier:");
        sb.append(string);
        com.navibees.core.util.Log.m1173i(str, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("MontoringRegionsReceiver:regionActionType:");
        sb2.append(string2);
        com.navibees.core.util.Log.m1173i(str, sb2.toString());
        NaviBeesApplication naviBeesApplication = (NaviBeesApplication) this.f1347a;
        NavibeesApplication navibeesApplication = NaviBeesManager.getInstance(naviBeesApplication).getMetaDataManager().navibeesApp;
        if (!(string == null || navibeesApplication == null)) {
            HashMap<String, MonitoredRegion> hashMap = navibeesApplication.regions;
            if (hashMap != null) {
                MonitoredRegion monitoredRegion = (MonitoredRegion) hashMap.get(string);
                if (monitoredRegion == null) {
                    return false;
                }
                if (monitoredRegion.tracking) {
                    NaviBeesManager.getInstance(naviBeesApplication).getNavibeesAnalytics().mo29037a(this.f1347a, monitoredRegion, string2);
                }
                if (monitoredRegion.notification) {
                    if (string2.equalsIgnoreCase(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_ENTER_VALUE)) {
                        long currentTimeMillis = System.currentTimeMillis() / 60000;
                        if (currentTimeMillis - (monitoredRegion.getLastSeen() / 60000) >= monitoredRegion.interval) {
                            monitoredRegion.setLastSeen(this.f1347a, currentTimeMillis);
                            monitoredRegion.setEnteredState(this.f1347a, true);
                            m986g(monitoredRegion);
                        }
                    } else if (string2.equalsIgnoreCase(NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_EXIT_VALUE) && monitoredRegion.getEnteredState()) {
                        monitoredRegion.setEnteredState(this.f1347a, false);
                        m987h(monitoredRegion);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
