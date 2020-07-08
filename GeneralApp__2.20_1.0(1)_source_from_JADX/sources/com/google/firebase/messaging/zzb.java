package com.google.firebase.messaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat.BigTextStyle;
import androidx.core.app.NotificationCompat.Builder;
import androidx.core.content.ContextCompat;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
public final class zzb {
    private static final AtomicInteger zza = new AtomicInteger((int) SystemClock.elapsedRealtime());

    static zza zza(Context context, zzr zzr) {
        Uri uri;
        Intent intent;
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        Bundle zza2 = zza(context.getPackageManager(), context.getPackageName());
        String packageName = context.getPackageName();
        String zzb = zzb(context, zzr.zza("gcm.n.android_channel_id"), zza2);
        Resources resources = context.getResources();
        PackageManager packageManager = context.getPackageManager();
        Builder builder = new Builder(context, zzb);
        String zza3 = zzr.zza(resources, packageName, "gcm.n.title");
        if (!TextUtils.isEmpty(zza3)) {
            builder.setContentTitle(zza3);
        }
        String zza4 = zzr.zza(resources, packageName, "gcm.n.body");
        if (!TextUtils.isEmpty(zza4)) {
            builder.setContentText(zza4);
            builder.setStyle(new BigTextStyle().bigText(zza4));
        }
        builder.setSmallIcon(zza(packageManager, resources, packageName, zzr.zza("gcm.n.icon"), zza2));
        String zzb2 = zzr.zzb();
        Integer num = null;
        if (TextUtils.isEmpty(zzb2)) {
            uri = null;
        } else if ("default".equals(zzb2) || resources.getIdentifier(zzb2, "raw", packageName) == 0) {
            uri = RingtoneManager.getDefaultUri(2);
        } else {
            StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(zzb2).length());
            sb.append("android.resource://");
            sb.append(packageName);
            sb.append("/raw/");
            sb.append(zzb2);
            uri = Uri.parse(sb.toString());
        }
        if (uri != null) {
            builder.setSound(uri);
        }
        String zza5 = zzr.zza("gcm.n.click_action");
        String str = "FirebaseMessaging";
        if (!TextUtils.isEmpty(zza5)) {
            intent = new Intent(zza5);
            intent.setPackage(packageName);
            intent.setFlags(268435456);
        } else {
            Uri zza6 = zzr.zza();
            if (zza6 != null) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setPackage(packageName);
                intent.setData(zza6);
            } else {
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
                if (launchIntentForPackage == null) {
                    Log.w(str, "No activity found to launch app");
                }
                intent = launchIntentForPackage;
            }
        }
        String str2 = "google.c.a.e";
        if (intent == null) {
            pendingIntent = null;
        } else {
            intent.addFlags(67108864);
            intent.putExtras(zzr.zze());
            pendingIntent = PendingIntent.getActivity(context, zza.incrementAndGet(), intent, 1073741824);
            if (zzr.zzb(str2)) {
                pendingIntent = zza(context, new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN").putExtras(zzr.zzf()).putExtra("pending_intent", pendingIntent));
            }
        }
        builder.setContentIntent(pendingIntent);
        if (!zzr.zzb(str2)) {
            pendingIntent2 = null;
        } else {
            pendingIntent2 = zza(context, new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS").putExtras(zzr.zzf()));
        }
        if (pendingIntent2 != null) {
            builder.setDeleteIntent(pendingIntent2);
        }
        Integer zza7 = zza(context, zzr.zza("gcm.n.color"), zza2);
        if (zza7 != null) {
            builder.setColor(zza7.intValue());
        }
        int i = 1;
        builder.setAutoCancel(!zzr.zzb("gcm.n.sticky"));
        builder.setLocalOnly(zzr.zzb("gcm.n.local_only"));
        String zza8 = zzr.zza("gcm.n.ticker");
        if (zza8 != null) {
            builder.setTicker(zza8);
        }
        Integer zzc = zzr.zzc("gcm.n.notification_priority");
        if (zzc == null) {
            zzc = null;
        } else if (zzc.intValue() < -2 || zzc.intValue() > 2) {
            String valueOf = String.valueOf(zzc);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 72);
            sb2.append("notificationPriority is invalid ");
            sb2.append(valueOf);
            sb2.append(". Skipping setting notificationPriority.");
            Log.w(str, sb2.toString());
            zzc = null;
        }
        if (zzc != null) {
            builder.setPriority(zzc.intValue());
        }
        Integer zzc2 = zzr.zzc("gcm.n.visibility");
        if (zzc2 == null) {
            zzc2 = null;
        } else if (zzc2.intValue() < -1 || zzc2.intValue() > 1) {
            String valueOf2 = String.valueOf(zzc2);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 53);
            sb3.append("visibility is invalid: ");
            sb3.append(valueOf2);
            sb3.append(". Skipping setting visibility.");
            Log.w("NotificationParams", sb3.toString());
            zzc2 = null;
        }
        if (zzc2 != null) {
            builder.setVisibility(zzc2.intValue());
        }
        Integer zzc3 = zzr.zzc("gcm.n.notification_count");
        if (zzc3 != null) {
            if (zzc3.intValue() < 0) {
                String valueOf3 = String.valueOf(zzc3);
                StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf3).length() + 67);
                sb4.append("notificationCount is invalid: ");
                sb4.append(valueOf3);
                sb4.append(". Skipping setting notificationCount.");
                Log.w(str, sb4.toString());
            } else {
                num = zzc3;
            }
        }
        if (num != null) {
            builder.setNumber(num.intValue());
        }
        Long zzd = zzr.zzd("gcm.n.event_time");
        if (zzd != null) {
            builder.setShowWhen(true);
            builder.setWhen(zzd.longValue());
        }
        long[] zzc4 = zzr.zzc();
        if (zzc4 != null) {
            builder.setVibrate(zzc4);
        }
        int[] zzd2 = zzr.zzd();
        if (zzd2 != null) {
            builder.setLights(zzd2[0], zzd2[1], zzd2[2]);
        }
        if (!zzr.zzb("gcm.n.default_sound")) {
            i = 0;
        }
        if (zzr.zzb("gcm.n.default_vibrate_timings")) {
            i |= 2;
        }
        if (zzr.zzb("gcm.n.default_light_settings")) {
            i |= 4;
        }
        builder.setDefaults(i);
        String zza9 = zzr.zza("gcm.n.tag");
        if (TextUtils.isEmpty(zza9)) {
            long uptimeMillis = SystemClock.uptimeMillis();
            StringBuilder sb5 = new StringBuilder(37);
            sb5.append("FCM-Notification:");
            sb5.append(uptimeMillis);
            zza9 = sb5.toString();
        }
        return new zza(builder, zza9, 0);
    }

    private static boolean zza(Resources resources, int i) {
        String str = "FirebaseMessaging";
        if (VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(resources.getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            StringBuilder sb = new StringBuilder(77);
            sb.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            sb.append(i);
            Log.e(str, sb.toString());
            return false;
        } catch (NotFoundException e) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Couldn't find resource ");
            sb2.append(i);
            sb2.append(", treating it as an invalid icon");
            Log.e(str, sb2.toString());
            return false;
        }
    }

    private static int zza(PackageManager packageManager, Resources resources, String str, String str2, Bundle bundle) {
        String str3 = "FirebaseMessaging";
        if (!TextUtils.isEmpty(str2)) {
            int identifier = resources.getIdentifier(str2, "drawable", str);
            if (identifier != 0 && zza(resources, identifier)) {
                return identifier;
            }
            int identifier2 = resources.getIdentifier(str2, "mipmap", str);
            if (identifier2 != 0 && zza(resources, identifier2)) {
                return identifier2;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 61);
            sb.append("Icon resource ");
            sb.append(str2);
            sb.append(" not found. Notification will use default icon.");
            Log.w(str3, sb.toString());
        }
        int i = bundle.getInt("com.google.firebase.messaging.default_notification_icon", 0);
        if (i == 0 || !zza(resources, i)) {
            try {
                i = packageManager.getApplicationInfo(str, 0).icon;
            } catch (NameNotFoundException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 35);
                sb2.append("Couldn't get own application info: ");
                sb2.append(valueOf);
                Log.w(str3, sb2.toString());
            }
        }
        if (i == 0 || !zza(resources, i)) {
            i = 17301651;
        }
        return i;
    }

    private static Integer zza(Context context, String str, Bundle bundle) {
        if (VERSION.SDK_INT < 21) {
            return null;
        }
        String str2 = "FirebaseMessaging";
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56);
                sb.append("Color is invalid: ");
                sb.append(str);
                sb.append(". Notification will use default color.");
                Log.w(str2, sb.toString());
            }
        }
        int i = bundle.getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(context, i));
            } catch (NotFoundException e2) {
                Log.w(str2, "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    private static Bundle zza(PackageManager packageManager, String str) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                return applicationInfo.metaData;
            }
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 35);
            sb.append("Couldn't get own application info: ");
            sb.append(valueOf);
            Log.w("FirebaseMessaging", sb.toString());
        }
        return Bundle.EMPTY;
    }

    private static String zzb(Context context, String str, Bundle bundle) {
        if (VERSION.SDK_INT < 26) {
            return null;
        }
        try {
            if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion < 26) {
                return null;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            String str2 = "FirebaseMessaging";
            if (!TextUtils.isEmpty(str)) {
                if (notificationManager.getNotificationChannel(str) != null) {
                    return str;
                }
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 122);
                sb.append("Notification Channel requested (");
                sb.append(str);
                sb.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                Log.w(str2, sb.toString());
            }
            String string = bundle.getString("com.google.firebase.messaging.default_notification_channel_id");
            if (TextUtils.isEmpty(string)) {
                Log.w(str2, "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            } else if (notificationManager.getNotificationChannel(string) != null) {
                return string;
            } else {
                Log.w(str2, "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
            }
            String str3 = "fcm_fallback_notification_channel";
            if (notificationManager.getNotificationChannel(str3) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(str3, context.getString(context.getResources().getIdentifier("fcm_fallback_notification_channel_label", "string", context.getPackageName())), 3));
            }
            return str3;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private static PendingIntent zza(Context context, Intent intent) {
        return PendingIntent.getBroadcast(context, zza.incrementAndGet(), new Intent("com.google.firebase.MESSAGING_EVENT").setComponent(new ComponentName(context, "com.google.firebase.iid.FirebaseInstanceIdReceiver")).putExtra("wrapped_intent", intent), 1073741824);
    }
}
