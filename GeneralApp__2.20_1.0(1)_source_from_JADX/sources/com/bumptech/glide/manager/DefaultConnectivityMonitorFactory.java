package com.bumptech.glide.manager;

import android.content.Context;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;

public class DefaultConnectivityMonitorFactory implements ConnectivityMonitorFactory {
    private static final String NETWORK_PERMISSION = "android.permission.ACCESS_NETWORK_STATE";
    private static final String TAG = "ConnectivityMonitor";

    public ConnectivityMonitor build(Context context, ConnectivityListener listener) {
        String str;
        boolean hasPermission = ContextCompat.checkSelfPermission(context, NETWORK_PERMISSION) == 0;
        String str2 = TAG;
        if (Log.isLoggable(str2, 3)) {
            if (hasPermission) {
                str = "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor";
            } else {
                str = "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor";
            }
            Log.d(str2, str);
        }
        return hasPermission ? new DefaultConnectivityMonitor(context, listener) : new NullConnectivityMonitor();
    }
}
