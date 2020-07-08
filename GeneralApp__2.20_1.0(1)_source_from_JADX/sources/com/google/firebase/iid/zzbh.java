package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
public final class zzbh implements ServiceConnection {
    private final Context zza;
    private final Intent zzb;
    private final ScheduledExecutorService zzc;
    private final Queue<zzbg> zzd;
    private zzbc zze;
    private boolean zzf;

    public zzbh(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }

    private zzbh(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zzd = new ArrayDeque();
        this.zzf = false;
        this.zza = context.getApplicationContext();
        this.zzb = new Intent(str).setPackage(this.zza.getPackageName());
        this.zzc = scheduledExecutorService;
    }

    public final synchronized Task<Void> zza(Intent intent) {
        zzbg zzbg;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "new intent queued in the bind-strategy delivery");
        }
        zzbg = new zzbg(intent);
        ScheduledExecutorService scheduledExecutorService = this.zzc;
        zzbg.zza().addOnCompleteListener((Executor) scheduledExecutorService, (OnCompleteListener<TResult>) new zzbi<TResult>(scheduledExecutorService.schedule(new zzbj(zzbg), 9000, TimeUnit.MILLISECONDS)));
        this.zzd.add(zzbg);
        zza();
        return zzbg.zza();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a9, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zza() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = "FirebaseInstanceId"
            r1 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r2 = "flush queue called"
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00ac }
        L_0x0011:
            java.util.Queue<com.google.firebase.iid.zzbg> r0 = r6.zzd     // Catch:{ all -> 0x00ac }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00ac }
            if (r0 != 0) goto L_0x00aa
            java.lang.String r0 = "FirebaseInstanceId"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x0028
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r2 = "found intent to be delivered"
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00ac }
        L_0x0028:
            com.google.firebase.iid.zzbc r0 = r6.zze     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x0051
            com.google.firebase.iid.zzbc r0 = r6.zze     // Catch:{ all -> 0x00ac }
            boolean r0 = r0.isBinderAlive()     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x0051
            java.lang.String r0 = "FirebaseInstanceId"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ac }
            if (r0 == 0) goto L_0x0043
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r2 = "binder is alive, sending the intent."
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00ac }
        L_0x0043:
            java.util.Queue<com.google.firebase.iid.zzbg> r0 = r6.zzd     // Catch:{ all -> 0x00ac }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x00ac }
            com.google.firebase.iid.zzbg r0 = (com.google.firebase.iid.zzbg) r0     // Catch:{ all -> 0x00ac }
            com.google.firebase.iid.zzbc r2 = r6.zze     // Catch:{ all -> 0x00ac }
            r2.zza(r0)     // Catch:{ all -> 0x00ac }
            goto L_0x0011
        L_0x0051:
            java.lang.String r0 = "FirebaseInstanceId"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ac }
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x007b
            java.lang.String r0 = "FirebaseInstanceId"
            boolean r3 = r6.zzf     // Catch:{ all -> 0x00ac }
            if (r3 != 0) goto L_0x0064
            r3 = 1
            goto L_0x0065
        L_0x0064:
            r3 = 0
        L_0x0065:
            r4 = 39
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ac }
            r5.<init>(r4)     // Catch:{ all -> 0x00ac }
            java.lang.String r4 = "binder is dead. start connection? "
            r5.append(r4)     // Catch:{ all -> 0x00ac }
            r5.append(r3)     // Catch:{ all -> 0x00ac }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x00ac }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00ac }
        L_0x007b:
            boolean r0 = r6.zzf     // Catch:{ all -> 0x00ac }
            if (r0 != 0) goto L_0x00a8
            r6.zzf = r2     // Catch:{ all -> 0x00ac }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ SecurityException -> 0x009b }
            android.content.Context r2 = r6.zza     // Catch:{ SecurityException -> 0x009b }
            android.content.Intent r3 = r6.zzb     // Catch:{ SecurityException -> 0x009b }
            r4 = 65
            boolean r0 = r0.bindService(r2, r3, r6, r4)     // Catch:{ SecurityException -> 0x009b }
            if (r0 == 0) goto L_0x0093
            monitor-exit(r6)
            return
        L_0x0093:
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r2 = "binding to the service failed"
            android.util.Log.e(r0, r2)     // Catch:{ SecurityException -> 0x009b }
            goto L_0x00a3
        L_0x009b:
            r0 = move-exception
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "Exception while binding the service"
            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x00ac }
        L_0x00a3:
            r6.zzf = r1     // Catch:{ all -> 0x00ac }
            r6.zzb()     // Catch:{ all -> 0x00ac }
        L_0x00a8:
            monitor-exit(r6)
            return
        L_0x00aa:
            monitor-exit(r6)
            return
        L_0x00ac:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzbh.zza():void");
    }

    private final void zzb() {
        while (!this.zzd.isEmpty()) {
            ((zzbg) this.zzd.poll()).zzb();
        }
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("onServiceConnected: ");
            sb.append(valueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        this.zzf = false;
        if (!(iBinder instanceof zzbc)) {
            String valueOf2 = String.valueOf(iBinder);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 28);
            sb2.append("Invalid service connection: ");
            sb2.append(valueOf2);
            Log.e("FirebaseInstanceId", sb2.toString());
            zzb();
            return;
        }
        this.zze = (zzbc) iBinder;
        zza();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        String str = "FirebaseInstanceId";
        if (Log.isLoggable(str, 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
            sb.append("onServiceDisconnected: ");
            sb.append(valueOf);
            Log.d(str, sb.toString());
        }
        zza();
    }
}
