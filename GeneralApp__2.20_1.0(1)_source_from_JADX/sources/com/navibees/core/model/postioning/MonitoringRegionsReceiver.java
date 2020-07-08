package com.navibees.core.model.postioning;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import com.navibees.core.model.metadata.json.NavibeesApplication;
import com.navibees.core.model.metadata.json.RegionType;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import java.util.HashMap;

public class MonitoringRegionsReceiver extends BroadcastReceiver {

    /* renamed from: a */
    static final String f1348a = "MontoringRegionsReceiver";

    /* access modifiers changed from: protected */
    public void didEnterRegionAll(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didEnterRegionAll:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        Log.m1173i(f1348a, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void didEnterRegionBackground(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didEnterRegionBackground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        Log.m1173i(f1348a, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void didEnterRegionForeground(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didEnterRegionForeground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        Log.m1173i(f1348a, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void didExitRegionAll(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didExitRegionAll:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        Log.m1173i(f1348a, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void didExitRegionBackground(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didExitRegionBackground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        Log.m1173i(f1348a, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void didExitRegionForeground(MonitoredRegion monitoredRegion) {
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:didExitRegionForeground:");
        sb.append(monitoredRegion.identifier);
        sb.append(", time since last notification:");
        sb.append(monitoredRegion.getLastSeen());
        Log.m1173i(f1348a, sb.toString());
    }

    public void onReceive(Context context, Intent intent) {
        String str = f1348a;
        Log.m1173i(str, "-------------------------------------");
        Log.m1173i(str, "MontoringRegionsReceiver:onReceive");
        String stringExtra = intent.getStringExtra(NaviBeesConstants.MONITORED_REGION_UNIQUE_IDENTIFIER_KEY);
        String str2 = NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_KEY;
        String stringExtra2 = intent.getStringExtra(str2);
        StringBuilder sb = new StringBuilder();
        sb.append("MontoringRegionsReceiver:regionIdentifier:");
        sb.append(stringExtra);
        Log.m1173i(str, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("MontoringRegionsReceiver:regionActionType:");
        sb2.append(stringExtra2);
        Log.m1173i(str, sb2.toString());
        NaviBeesApplication naviBeesApplication = (NaviBeesApplication) context.getApplicationContext();
        NavibeesApplication navibeesApplication = NaviBeesManager.getInstance(naviBeesApplication).getMetaDataManager().navibeesApp;
        if (!(stringExtra == null || navibeesApplication == null)) {
            HashMap<String, MonitoredRegion> hashMap = navibeesApplication.regions;
            if (hashMap != null) {
                MonitoredRegion monitoredRegion = (MonitoredRegion) hashMap.get(stringExtra);
                if (monitoredRegion != null) {
                    if (monitoredRegion.tracking) {
                        NaviBeesManager.getInstance(naviBeesApplication).getNavibeesAnalytics().mo29037a(context, monitoredRegion, stringExtra2);
                    }
                    if (monitoredRegion.notification) {
                        boolean isAppInForeground = NaviBeesUtils.isAppInForeground(context);
                        RegionType regionType = monitoredRegion.type;
                        if (regionType == RegionType.ALL || ((regionType == RegionType.FOREGROUND && isAppInForeground) || (monitoredRegion.type == RegionType.BACKGROUND && !isAppInForeground))) {
                            String str3 = NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_ENTER_VALUE;
                            boolean equalsIgnoreCase = stringExtra2.equalsIgnoreCase(str3);
                            String str4 = NaviBeesConstants.MONITORED_REGION_OBJECT_KEY;
                            String str5 = NaviBeesConstants.MONITORED_REGION_ACTION;
                            if (equalsIgnoreCase) {
                                long currentTimeMillis = System.currentTimeMillis() / 1000;
                                if (currentTimeMillis - monitoredRegion.getLastSeen() > monitoredRegion.interval) {
                                    monitoredRegion.setLastSeen(context, currentTimeMillis);
                                    monitoredRegion.setEnteredState(context, true);
                                    Intent intent2 = new Intent(str5);
                                    intent2.putExtra(str4, monitoredRegion);
                                    intent2.putExtra(str2, str3);
                                    context.sendBroadcast(intent2);
                                }
                            } else {
                                String str6 = NaviBeesConstants.MONITORED_REGION_ACTION_TYPE_EXIT_VALUE;
                                if (stringExtra2.equalsIgnoreCase(str6) && monitoredRegion.getEnteredState()) {
                                    monitoredRegion.setEnteredState(context, false);
                                    Intent intent3 = new Intent(str5);
                                    intent3.putExtra(str4, monitoredRegion);
                                    intent3.putExtra(str2, str6);
                                    context.sendBroadcast(intent3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
