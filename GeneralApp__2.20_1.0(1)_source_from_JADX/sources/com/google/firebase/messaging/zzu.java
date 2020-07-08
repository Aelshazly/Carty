package com.google.firebase.messaging;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzao;
import com.google.firebase.iid.zzt;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
final class zzu {
    private static final long zza = TimeUnit.HOURS.toSeconds(8);
    private static final Pattern zzb = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private final FirebaseInstanceId zzc;
    private final Context zzd;
    private final zzao zze;
    private final zzt zzf;
    private final ScheduledExecutorService zzg;
    private boolean zzh;
    private int zzi;
    private final Map<Integer, TaskCompletionSource<Void>> zzj;
    private final zzv zzk;

    zzu(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, zzao zzao, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, Context context, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        Context context2 = context;
        zzv zzv = new zzv(context2);
        zzt zzt = new zzt(firebaseApp, zzao, executor, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi);
        this(firebaseInstanceId, zzao, zzv, zzt, context2, scheduledExecutorService);
    }

    private zzu(FirebaseInstanceId firebaseInstanceId, zzao zzao, zzv zzv, zzt zzt, Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzh = false;
        this.zzi = 0;
        this.zzj = new ArrayMap();
        this.zzc = firebaseInstanceId;
        this.zze = zzao;
        this.zzk = zzv;
        this.zzf = zzt;
        this.zzd = context;
        this.zzg = scheduledExecutorService;
    }

    /* access modifiers changed from: 0000 */
    public final Task<Void> zza(String str) {
        String valueOf = String.valueOf("S!");
        String valueOf2 = String.valueOf(zza(str, "subscribeToTopic"));
        Task<Void> zzc2 = zzc(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zza();
        return zzc2;
    }

    /* access modifiers changed from: 0000 */
    public final Task<Void> zzb(String str) {
        String valueOf = String.valueOf("U!");
        String valueOf2 = String.valueOf(zza(str, "unsubscribeFromTopic"));
        Task<Void> zzc2 = zzc(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zza();
        return zzc2;
    }

    private final synchronized Task<Void> zzc(String str) {
        String zza2;
        TaskCompletionSource taskCompletionSource;
        int i;
        synchronized (this.zzk) {
            zza2 = this.zzk.zza();
            zzv zzv = this.zzk;
            StringBuilder sb = new StringBuilder(String.valueOf(zza2).length() + 1 + String.valueOf(str).length());
            sb.append(zza2);
            sb.append(",");
            sb.append(str);
            zzv.zza(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        Map<Integer, TaskCompletionSource<Void>> map = this.zzj;
        if (TextUtils.isEmpty(zza2)) {
            i = 0;
        } else {
            i = zza2.split(",").length - 1;
        }
        map.put(Integer.valueOf(this.zzi + i), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    private final synchronized boolean zzd() {
        return zzf() != null;
    }

    private static String zza(String str, String str2) {
        if (str != null && str.startsWith("/topics/")) {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 78);
            sb.append("Format /topics/topic-name is deprecated. Only 'topic-name' should be used in ");
            sb.append(str2);
            sb.append(".");
            Log.w("FirebaseMessaging", sb.toString());
            str = str.substring(8);
        }
        if (str != null && zzb.matcher(str).matches()) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 79);
        sb2.append("Invalid topic name: ");
        sb2.append(str);
        sb2.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}.");
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zza() {
        if (zzd()) {
            zze();
        }
    }

    private final synchronized void zze() {
        if (!this.zzh) {
            zza(0);
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zza(long j) {
        zzx zzx = new zzx(this, this.zzd, this.zze, Math.min(Math.max(30, j << 1), zza));
        zza((Runnable) zzx, j);
        this.zzh = true;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zza(Runnable runnable, long j) {
        this.zzg.schedule(runnable, j, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (zzd(r0) != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r2 = (com.google.android.gms.tasks.TaskCompletionSource) r5.zzj.remove(java.lang.Integer.valueOf(r5.zzi));
        r3 = r5.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r5.zzk.zzb(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r5.zzi++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003d, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        if (r2 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0040, code lost:
        r2.setResult(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzb() throws java.io.IOException {
        /*
            r5 = this;
        L_0x0000:
            monitor-enter(r5)
            java.lang.String r0 = r5.zzf()     // Catch:{ all -> 0x004b }
            r1 = 1
            if (r0 != 0) goto L_0x0017
            boolean r0 = zzc()     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x0015
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r2 = "topic sync succeeded"
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x004b }
        L_0x0015:
            monitor-exit(r5)     // Catch:{ all -> 0x004b }
            return r1
        L_0x0017:
            monitor-exit(r5)     // Catch:{ all -> 0x004b }
            boolean r2 = r5.zzd(r0)
            if (r2 != 0) goto L_0x0020
            r0 = 0
            return r0
        L_0x0020:
            monitor-enter(r5)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r2 = r5.zzj     // Catch:{ all -> 0x0048 }
            int r3 = r5.zzi     // Catch:{ all -> 0x0048 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Object r2 = r2.remove(r3)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2     // Catch:{ all -> 0x0048 }
            com.google.firebase.messaging.zzv r3 = r5.zzk     // Catch:{ all -> 0x0048 }
            monitor-enter(r3)     // Catch:{ all -> 0x0048 }
            com.google.firebase.messaging.zzv r4 = r5.zzk     // Catch:{ all -> 0x0045 }
            r4.zzb(r0)     // Catch:{ all -> 0x0045 }
            monitor-exit(r3)     // Catch:{ all -> 0x0045 }
            int r0 = r5.zzi     // Catch:{ all -> 0x0048 }
            int r0 = r0 + r1
            r5.zzi = r0     // Catch:{ all -> 0x0048 }
            monitor-exit(r5)     // Catch:{ all -> 0x0048 }
            if (r2 == 0) goto L_0x0044
            r0 = 0
            r2.setResult(r0)
        L_0x0044:
            goto L_0x0000
        L_0x0045:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0048 }
            throw r0
        L_0x004b:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x004b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zzu.zzb():boolean");
    }

    private final String zzf() {
        String zza2;
        synchronized (this.zzk) {
            zza2 = this.zzk.zza();
        }
        if (!TextUtils.isEmpty(zza2)) {
            String[] split = zza2.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ab A[Catch:{ IOException -> 0x00f2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "!"
            java.lang.String[] r1 = r9.split(r1)
            int r2 = r1.length
            r3 = 1
            r4 = 2
            if (r2 != r4) goto L_0x0141
            r2 = 0
            r4 = r1[r2]
            r1 = r1[r3]
            r5 = -1
            int r6 = r4.hashCode()     // Catch:{ IOException -> 0x00f2 }
            r7 = 83
            if (r6 == r7) goto L_0x002a
            r7 = 85
            if (r6 == r7) goto L_0x0020
        L_0x001f:
            goto L_0x0033
        L_0x0020:
            java.lang.String r6 = "U"
            boolean r4 = r4.equals(r6)     // Catch:{ IOException -> 0x00f2 }
            if (r4 == 0) goto L_0x001f
            r5 = 1
            goto L_0x0033
        L_0x002a:
            java.lang.String r6 = "S"
            boolean r4 = r4.equals(r6)     // Catch:{ IOException -> 0x00f2 }
            if (r4 == 0) goto L_0x001f
            r5 = 0
        L_0x0033:
            java.lang.String r4 = " succeeded."
            if (r5 == 0) goto L_0x00ab
            if (r5 == r3) goto L_0x0064
            boolean r1 = zzc()     // Catch:{ IOException -> 0x00f2 }
            if (r1 == 0) goto L_0x00f1
            java.lang.String r1 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x00f2 }
            int r1 = r1.length()     // Catch:{ IOException -> 0x00f2 }
            int r1 = r1 + 24
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f2 }
            r4.<init>(r1)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r1 = "Unknown topic operation"
            r4.append(r1)     // Catch:{ IOException -> 0x00f2 }
            r4.append(r9)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = "."
            r4.append(r9)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = r4.toString()     // Catch:{ IOException -> 0x00f2 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x00f2 }
            goto L_0x00f1
        L_0x0064:
            com.google.firebase.iid.FirebaseInstanceId r9 = r8.zzc     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.tasks.Task r9 = r9.getInstanceId()     // Catch:{ IOException -> 0x00f2 }
            java.lang.Object r9 = zza(r9)     // Catch:{ IOException -> 0x00f2 }
            com.google.firebase.iid.InstanceIdResult r9 = (com.google.firebase.iid.InstanceIdResult) r9     // Catch:{ IOException -> 0x00f2 }
            com.google.firebase.iid.zzt r5 = r8.zzf     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r6 = r9.getId()     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = r9.getToken()     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.tasks.Task r9 = r5.zzd(r6, r9, r1)     // Catch:{ IOException -> 0x00f2 }
            zza(r9)     // Catch:{ IOException -> 0x00f2 }
            boolean r9 = zzc()     // Catch:{ IOException -> 0x00f2 }
            if (r9 == 0) goto L_0x00f1
            java.lang.String r9 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x00f2 }
            int r9 = r9.length()     // Catch:{ IOException -> 0x00f2 }
            int r9 = r9 + 35
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f2 }
            r5.<init>(r9)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = "Unsubscribe from topic: "
            r5.append(r9)     // Catch:{ IOException -> 0x00f2 }
            r5.append(r1)     // Catch:{ IOException -> 0x00f2 }
            r5.append(r4)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = r5.toString()     // Catch:{ IOException -> 0x00f2 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x00f2 }
            goto L_0x0141
        L_0x00ab:
            com.google.firebase.iid.FirebaseInstanceId r9 = r8.zzc     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.tasks.Task r9 = r9.getInstanceId()     // Catch:{ IOException -> 0x00f2 }
            java.lang.Object r9 = zza(r9)     // Catch:{ IOException -> 0x00f2 }
            com.google.firebase.iid.InstanceIdResult r9 = (com.google.firebase.iid.InstanceIdResult) r9     // Catch:{ IOException -> 0x00f2 }
            com.google.firebase.iid.zzt r5 = r8.zzf     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r6 = r9.getId()     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = r9.getToken()     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.tasks.Task r9 = r5.zzc(r6, r9, r1)     // Catch:{ IOException -> 0x00f2 }
            zza(r9)     // Catch:{ IOException -> 0x00f2 }
            boolean r9 = zzc()     // Catch:{ IOException -> 0x00f2 }
            if (r9 == 0) goto L_0x00f1
            java.lang.String r9 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x00f2 }
            int r9 = r9.length()     // Catch:{ IOException -> 0x00f2 }
            int r9 = r9 + 31
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f2 }
            r5.<init>(r9)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = "Subscribe to topic: "
            r5.append(r9)     // Catch:{ IOException -> 0x00f2 }
            r5.append(r1)     // Catch:{ IOException -> 0x00f2 }
            r5.append(r4)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r9 = r5.toString()     // Catch:{ IOException -> 0x00f2 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x00f2 }
            goto L_0x0141
        L_0x00f1:
            goto L_0x0141
        L_0x00f2:
            r9 = move-exception
            java.lang.String r1 = r9.getMessage()
            java.lang.String r3 = "SERVICE_NOT_AVAILABLE"
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L_0x0119
            java.lang.String r1 = r9.getMessage()
            java.lang.String r3 = "INTERNAL_SERVER_ERROR"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x010c
            goto L_0x0119
        L_0x010c:
            java.lang.String r1 = r9.getMessage()
            if (r1 != 0) goto L_0x0118
            java.lang.String r9 = "Topic operation failed without exception message. Will retry Topic operation."
            android.util.Log.e(r0, r9)
            return r2
        L_0x0118:
            throw r9
        L_0x0119:
            java.lang.String r9 = r9.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r9)
            int r1 = r1.length()
            int r1 = r1 + 53
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.String r1 = "Topic operation failed: "
            r3.append(r1)
            r3.append(r9)
            java.lang.String r9 = ". Will retry Topic operation."
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            android.util.Log.e(r0, r9)
            return r2
        L_0x0141:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zzu.zzd(java.lang.String):boolean");
    }

    private static <T> T zza(Task<T> task) throws IOException {
        try {
            return Tasks.await(task, 30, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException e2) {
            throw new IOException("SERVICE_NOT_AVAILABLE", e2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zza(boolean z) {
        this.zzh = z;
    }

    static boolean zzc() {
        String str = "FirebaseMessaging";
        return Log.isLoggable(str, 3) || (VERSION.SDK_INT == 23 && Log.isLoggable(str, 3));
    }
}
