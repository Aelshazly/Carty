package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public interface zzgh {
    PendingResult doGoAsync();

    void doStartService(Context context, Intent intent);
}
