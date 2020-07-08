package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver.CrashlyticsOriginEventListener;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class AnalyticsConnectorReceiver implements AnalyticsConnectorListener, AnalyticsReceiver {
    public static final String APP_EXCEPTION_EVENT_NAME = "_ae";
    private static final String BREADCRUMB_PARAMS_KEY = "parameters";
    private static final String BREADCRUMB_PREFIX = "$A$:";
    static final String CRASHLYTICS_ORIGIN = "clx";
    public static final String EVENT_NAME_KEY = "name";
    private static final String EVENT_ORIGIN_KEY = "_o";
    private static final String EVENT_PARAMS_KEY = "params";
    static final String LEGACY_CRASH_ORIGIN = "crash";
    private final AnalyticsConnector analyticsConnector;
    private AnalyticsConnectorHandle analyticsConnectorHandle;
    private final BreadcrumbHandler breadcrumbHandler;
    private CrashlyticsOriginEventListener crashOriginEventListener;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public interface BreadcrumbHandler {
        void dropBreadcrumb(String str);
    }

    public AnalyticsConnectorReceiver(AnalyticsConnector analyticsConnector2, BreadcrumbHandler breadcrumbHandler2) {
        this.analyticsConnector = analyticsConnector2;
        this.breadcrumbHandler = breadcrumbHandler2;
    }

    public boolean register() {
        AnalyticsConnector analyticsConnector2 = this.analyticsConnector;
        boolean z = false;
        if (analyticsConnector2 == null) {
            Logger.getLogger().mo19679d("Firebase Analytics is not present; you will not see automatic logging of events before a crash occurs.");
            return false;
        }
        this.analyticsConnectorHandle = analyticsConnector2.registerAnalyticsConnectorListener(CRASHLYTICS_ORIGIN, this);
        if (this.analyticsConnectorHandle == null) {
            Logger.getLogger().mo19679d("Could not register AnalyticsConnectorListener with Crashlytics origin.");
            this.analyticsConnectorHandle = this.analyticsConnector.registerAnalyticsConnectorListener("crash", this);
            if (this.analyticsConnectorHandle != null) {
                Logger.getLogger().mo19689w("A new version of the Google Analytics for Firebase SDK is now available. For improved performance and compatibility with Crashlytics, please update to the latest version.");
            }
        }
        if (this.analyticsConnectorHandle != null) {
            z = true;
        }
        return z;
    }

    public void unregister() {
        AnalyticsConnectorHandle analyticsConnectorHandle2 = this.analyticsConnectorHandle;
        if (analyticsConnectorHandle2 != null) {
            analyticsConnectorHandle2.unregister();
        }
    }

    public void setCrashlyticsOriginEventListener(CrashlyticsOriginEventListener listener) {
        this.crashOriginEventListener = listener;
    }

    public CrashlyticsOriginEventListener getCrashlyticsOriginEventListener() {
        return this.crashOriginEventListener;
    }

    public void onMessageTriggered(int id, Bundle extras) {
        Logger logger = Logger.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append("AnalyticsConnectorReceiver received message: ");
        sb.append(id);
        sb.append(" ");
        sb.append(extras);
        logger.mo19679d(sb.toString());
        if (extras != null) {
            Bundle params = extras.getBundle(EVENT_PARAMS_KEY);
            if (params == null) {
                params = new Bundle();
            }
            if (CRASHLYTICS_ORIGIN.equals(params.getString(EVENT_ORIGIN_KEY))) {
                dispatchCrashlyticsOriginEvent(id, extras);
            } else {
                String name = extras.getString("name");
                if (name != null) {
                    dispatchBreadcrumbEvent(name, params);
                }
            }
        }
    }

    private void dispatchCrashlyticsOriginEvent(int id, Bundle extras) {
        CrashlyticsOriginEventListener crashlyticsOriginEventListener = this.crashOriginEventListener;
        if (crashlyticsOriginEventListener != null) {
            crashlyticsOriginEventListener.onCrashlyticsOriginEvent(id, extras);
        }
    }

    private void dispatchBreadcrumbEvent(String name, Bundle params) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(BREADCRUMB_PREFIX);
            sb.append(serializeEvent(name, params));
            this.breadcrumbHandler.dropBreadcrumb(sb.toString());
        } catch (JSONException e) {
            Logger.getLogger().mo19689w("Unable to serialize Firebase Analytics event.");
        }
    }

    private static String serializeEvent(String name, Bundle params) throws JSONException {
        JSONObject enclosingObject = new JSONObject();
        JSONObject paramsObject = new JSONObject();
        for (String key : params.keySet()) {
            paramsObject.put(key, params.get(key));
        }
        enclosingObject.put("name", name);
        enclosingObject.put(BREADCRUMB_PARAMS_KEY, paramsObject);
        return enclosingObject.toString();
    }
}
