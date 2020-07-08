package com.navibees.core.model.metadata;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.navibees.core.model.metadata.json.BeaconNodeConfiguration;
import java.util.Set;

public final class BatteryStatusIntentService extends IntentService {

    /* renamed from: a */
    private static final String f1311a = "com.navibees.sdk.model.metadata.action.SEND.BATTERY.STATUS";

    /* renamed from: b */
    private static final String f1312b = "com.navibees.sdk.model.metadata.extra.BEACONS";

    public BatteryStatusIntentService() {
        super("BatteryStatusIntentService");
    }

    /* renamed from: a */
    public static void m963a(Context context, Set<BeaconNodeConfiguration> set) {
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
    }
}
