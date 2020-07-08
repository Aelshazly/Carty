package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class BatteryState {
    static final int VELOCITY_CHARGING = 2;
    static final int VELOCITY_FULL = 3;
    static final int VELOCITY_UNPLUGGED = 1;
    private final Float level;
    private final boolean powerConnected;

    private BatteryState(Float level2, boolean powerConnected2) {
        this.powerConnected = powerConnected2;
        this.level = level2;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPowerConnected() {
        return this.powerConnected;
    }

    public Float getBatteryLevel() {
        return this.level;
    }

    public int getBatteryVelocity() {
        if (this.powerConnected) {
            Float f = this.level;
            if (f != null) {
                if (((double) f.floatValue()) < 0.99d) {
                    return 2;
                }
                return 3;
            }
        }
        return 1;
    }

    public static BatteryState get(Context context) {
        boolean powerConnected2 = false;
        Float level2 = null;
        Intent batteryStatusIntent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (batteryStatusIntent != null) {
            powerConnected2 = isPowerConnected(batteryStatusIntent);
            level2 = getLevel(batteryStatusIntent);
        }
        return new BatteryState(level2, powerConnected2);
    }

    private static boolean isPowerConnected(Intent batteryStatusIntent) {
        int status = batteryStatusIntent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        boolean z = false;
        if (status == -1) {
            return false;
        }
        if (status == 2 || status == 5) {
            z = true;
        }
        return z;
    }

    private static Float getLevel(Intent batteryStatusIntent) {
        int level2 = batteryStatusIntent.getIntExtra(Param.LEVEL, -1);
        int scale = batteryStatusIntent.getIntExtra("scale", -1);
        if (level2 == -1 || scale == -1) {
            return null;
        }
        return Float.valueOf(((float) level2) / ((float) scale));
    }
}
