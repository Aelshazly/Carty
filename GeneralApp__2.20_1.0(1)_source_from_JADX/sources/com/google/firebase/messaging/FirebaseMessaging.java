package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzao;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    static TransportFactory zza;
    private final Context zzb;
    private final FirebaseInstanceId zzc;
    private final zzu zzd;

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging instance;
        synchronized (FirebaseMessaging.class) {
            instance = getInstance(FirebaseApp.getInstance());
        }
        return instance;
    }

    static synchronized FirebaseMessaging getInstance(FirebaseApp firebaseApp) {
        FirebaseMessaging firebaseMessaging;
        Class<FirebaseMessaging> cls = FirebaseMessaging.class;
        synchronized (cls) {
            firebaseMessaging = (FirebaseMessaging) firebaseApp.get(cls);
        }
        return firebaseMessaging;
    }

    FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, TransportFactory transportFactory) {
        zza = transportFactory;
        FirebaseInstanceId firebaseInstanceId2 = firebaseInstanceId;
        this.zzc = firebaseInstanceId2;
        this.zzb = firebaseApp.getApplicationContext();
        zzu zzu = new zzu(firebaseApp, firebaseInstanceId2, new zzao(this.zzb), userAgentPublisher, heartBeatInfo, firebaseInstallationsApi, this.zzb, zzi.zza(), new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("Firebase-Messaging-Topics-Io")));
        this.zzd = zzu;
        zzi.zzb().execute(new zzk(this));
    }

    public boolean isAutoInitEnabled() {
        return this.zzc.zzh();
    }

    public void setAutoInitEnabled(boolean z) {
        this.zzc.zzb(z);
    }

    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return zzp.zza();
    }

    public void setDeliveryMetricsExportToBigQuery(boolean z) {
        zzp.zza(z);
    }

    public Task<Void> subscribeToTopic(String str) {
        return this.zzd.zza(str);
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        return this.zzd.zzb(str);
    }

    public void send(RemoteMessage remoteMessage) {
        if (!TextUtils.isEmpty(remoteMessage.getTo())) {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            intent.putExtra("app", PendingIntent.getBroadcast(this.zzb, 0, intent2, 0));
            intent.setPackage("com.google.android.gms");
            intent.putExtras(remoteMessage.zza);
            this.zzb.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
        throw new IllegalArgumentException("Missing 'to'");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza() {
        if (isAutoInitEnabled()) {
            this.zzd.zza();
        }
    }
}
