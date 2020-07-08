package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import okhttp3.internal.cache.DiskLruCache;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
public final class zzp {
    private static final DataEncoder zza = new JsonDataEncoderBuilder().registerEncoder(zza.class, (ObjectEncoder) new zzc()).registerEncoder(FirelogAnalyticsEvent.class, (ObjectEncoder) new zzb()).build();

    public static void zza(Intent intent, Transport<String> transport) {
        zza("_nr", intent);
        if (transport != null) {
            try {
                transport.send(Event.ofTelemetry(zza.encode(new zza(new FirelogAnalyticsEvent("MESSAGE_DELIVERED", intent)))));
            } catch (EncodingException e) {
                Log.d("FirebaseMessaging", "Failed to encode big query analytics payload. Skip sending");
            }
        }
    }

    public static void zza(Intent intent) {
        if (intent != null) {
            String str = "FirebaseMessaging";
            if (DiskLruCache.VERSION_1.equals(intent.getStringExtra("google.c.a.tc"))) {
                AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
                if (Log.isLoggable(str, 3)) {
                    Log.d(str, "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if (analyticsConnector != null) {
                    String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    String str2 = "fcm";
                    analyticsConnector.setUserProperty(str2, UserProperty.FIREBASE_LAST_NOTIFICATION, stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString(Param.SOURCE, "Firebase");
                    bundle.putString(Param.MEDIUM, "notification");
                    bundle.putString(Param.CAMPAIGN, stringExtra);
                    analyticsConnector.logEvent(str2, "_cmp", bundle);
                } else {
                    Log.w(str, "Unable to set user property for conversion tracking:  analytics library is missing");
                }
            } else if (Log.isLoggable(str, 3)) {
                Log.d(str, "Received event with track-conversion=false. Do not set user property");
            }
        }
        zza("_no", intent);
    }

    public static void zzb(Intent intent) {
        zza("_nd", intent);
    }

    public static void zzc(Intent intent) {
        zza("_nf", intent);
    }

    public static boolean zzd(Intent intent) {
        if (intent == null || zzn(intent)) {
            return false;
        }
        return DiskLruCache.VERSION_1.equals(intent.getStringExtra("google.c.a.e"));
    }

    public static boolean zze(Intent intent) {
        if (intent == null || zzn(intent)) {
            return false;
        }
        return zza();
    }

    private static boolean zzn(Intent intent) {
        return "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(intent.getAction());
    }

    static boolean zza() {
        String str = "delivery_metrics_exported_to_big_query_enabled";
        try {
            FirebaseApp.getInstance();
            Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            String str2 = "export_to_big_query";
            if (sharedPreferences.contains(str2)) {
                return sharedPreferences.getBoolean(str2, false);
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
                    if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(str))) {
                        return applicationInfo.metaData.getBoolean(str, false);
                    }
                }
            } catch (NameNotFoundException e) {
            }
            return false;
        } catch (IllegalStateException e2) {
            Log.i("FirebaseMessaging", "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
            return false;
        }
    }

    private static void zza(String str, Intent intent) {
        String str2;
        String str3;
        Bundle bundle = new Bundle();
        String stringExtra = intent.getStringExtra("google.c.a.c_id");
        if (stringExtra != null) {
            bundle.putString("_nmid", stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("google.c.a.c_l");
        if (stringExtra2 != null) {
            bundle.putString("_nmn", stringExtra2);
        }
        String stringExtra3 = intent.getStringExtra("google.c.a.m_l");
        if (!TextUtils.isEmpty(stringExtra3)) {
            bundle.putString("label", stringExtra3);
        }
        String stringExtra4 = intent.getStringExtra("google.c.a.m_c");
        if (!TextUtils.isEmpty(stringExtra4)) {
            bundle.putString("message_channel", stringExtra4);
        }
        String zzl = zzl(intent);
        if (zzl != null) {
            bundle.putString("_nt", zzl);
        }
        String stringExtra5 = intent.getStringExtra("google.c.a.ts");
        String str4 = "FirebaseMessaging";
        if (stringExtra5 != null) {
            try {
                bundle.putInt("_nmt", Integer.parseInt(stringExtra5));
            } catch (NumberFormatException e) {
                Log.w(str4, "Error while parsing timestamp in GCM event", e);
            }
        }
        String str5 = "google.c.a.udt";
        if (intent.hasExtra(str5)) {
            str2 = intent.getStringExtra(str5);
        } else {
            str2 = null;
        }
        if (str2 != null) {
            try {
                bundle.putInt("_ndt", Integer.parseInt(str2));
            } catch (NumberFormatException e2) {
                Log.w(str4, "Error while parsing use_device_time in GCM event", e2);
            }
        }
        if (intent.getExtras() == null || !zzr.zza(intent.getExtras())) {
            str3 = "data";
        } else {
            str3 = "display";
        }
        if ("_nr".equals(str) || "_nf".equals(str)) {
            bundle.putString("_nmc", str3);
        }
        if (Log.isLoggable(str4, 3)) {
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(valueOf).length());
            sb.append("Logging to scion event=");
            sb.append(str);
            sb.append(" scionPayload=");
            sb.append(valueOf);
            Log.d(str4, sb.toString());
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (analyticsConnector != null) {
            analyticsConnector.logEvent("fcm", str, bundle);
        } else {
            Log.w(str4, "Unable to log event: analytics library is missing");
        }
    }

    static void zza(boolean z) {
        FirebaseApp.getInstance().getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit().putBoolean("export_to_big_query", z).apply();
    }

    static int zzf(Intent intent) {
        Object obj = intent.getExtras().get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
                sb.append("Invalid TTL: ");
                sb.append(valueOf);
                Log.w("FirebaseMessaging", sb.toString());
            }
        }
        return 0;
    }

    static String zzg(Intent intent) {
        return intent.getStringExtra("collapse_key");
    }

    static String zzh(Intent intent) {
        return intent.getStringExtra("google.c.a.c_l");
    }

    static String zzi(Intent intent) {
        return intent.getStringExtra("google.c.a.m_l");
    }

    static String zzj(Intent intent) {
        String stringExtra = intent.getStringExtra("google.message_id");
        if (stringExtra == null) {
            return intent.getStringExtra("message_id");
        }
        return stringExtra;
    }

    static String zzb() {
        return FirebaseApp.getInstance().getApplicationContext().getPackageName();
    }

    static String zzc() {
        return FirebaseInstanceId.getInstance(FirebaseApp.getInstance()).getId();
    }

    static String zzk(Intent intent) {
        if (intent.getExtras() == null || !zzr.zza(intent.getExtras())) {
            return "DATA_MESSAGE";
        }
        return "DISPLAY_NOTIFICATION";
    }

    static String zzl(Intent intent) {
        String stringExtra = intent.getStringExtra("from");
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            return null;
        }
        return stringExtra;
    }

    static int zzm(Intent intent) {
        String stringExtra = intent.getStringExtra("google.delivered_priority");
        if (stringExtra == null) {
            if (DiskLruCache.VERSION_1.equals(intent.getStringExtra("google.priority_reduced"))) {
                return 2;
            }
            stringExtra = intent.getStringExtra("google.priority");
        }
        if ("high".equals(stringExtra)) {
            return 1;
        }
        if ("normal".equals(stringExtra)) {
            return 2;
        }
        return 0;
    }

    static String zzd() {
        FirebaseApp instance = FirebaseApp.getInstance();
        String gcmSenderId = instance.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = instance.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }
}
