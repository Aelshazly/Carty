package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzj.zza;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final class zzau {
    private static int zza = 0;
    private static PendingIntent zzb;
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzc = new SimpleArrayMap<>();
    private final Context zzd;
    private final zzao zze;
    private Messenger zzf;
    private Messenger zzg;
    private zzj zzh;

    public zzau(Context context, zzao zzao) {
        this.zzd = context;
        this.zze = zzao;
        this.zzf = new Messenger(new zzax(this, Looper.getMainLooper()));
    }

    /* access modifiers changed from: private */
    public final void zza(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("FirebaseInstanceId", "Dropping invalid message");
        } else {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new zza());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzj) {
                    this.zzh = (zzj) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzg = (Messenger) parcelableExtra;
                }
            }
            Intent intent2 = (Intent) message.obj;
            String action = intent2.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String str = "Unexpected response action: ";
                    String valueOf = String.valueOf(action);
                    Log.d("FirebaseInstanceId", valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
                return;
            }
            String stringExtra = intent2.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent2.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent2.getStringExtra("error");
                if (stringExtra2 == null) {
                    String valueOf2 = String.valueOf(intent2.getExtras());
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 49);
                    sb.append("Unexpected response, no error or registration id ");
                    sb.append(valueOf2);
                    Log.w("FirebaseInstanceId", sb.toString());
                } else {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        String str2 = "Received InstanceID error ";
                        String valueOf3 = String.valueOf(stringExtra2);
                        Log.d("FirebaseInstanceId", valueOf3.length() != 0 ? str2.concat(valueOf3) : new String(str2));
                    }
                    if (stringExtra2.startsWith("|")) {
                        String[] split = stringExtra2.split("\\|");
                        if (split.length > 2) {
                            if ("ID".equals(split[1])) {
                                String str3 = split[2];
                                String str4 = split[3];
                                if (str4.startsWith(":")) {
                                    str4 = str4.substring(1);
                                }
                                zza(str3, intent2.putExtra("error", str4).getExtras());
                            }
                        }
                        String str5 = "Unexpected structured response ";
                        String valueOf4 = String.valueOf(stringExtra2);
                        Log.w("FirebaseInstanceId", valueOf4.length() != 0 ? str5.concat(valueOf4) : new String(str5));
                    } else {
                        synchronized (this.zzc) {
                            for (int i = 0; i < this.zzc.size(); i++) {
                                zza((String) this.zzc.keyAt(i), intent2.getExtras());
                            }
                        }
                    }
                }
            } else {
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (!matcher.matches()) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        String str6 = "Unexpected response string: ";
                        String valueOf5 = String.valueOf(stringExtra);
                        Log.d("FirebaseInstanceId", valueOf5.length() != 0 ? str6.concat(valueOf5) : new String(str6));
                    }
                    return;
                }
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                Bundle extras = intent2.getExtras();
                extras.putString("registration_id", group2);
                zza(group, extras);
            }
        }
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzau.class) {
            if (zzb == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzb = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", zzb);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzc) {
            TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzc.remove(str);
            if (taskCompletionSource == null) {
                String str2 = "FirebaseInstanceId";
                String str3 = "Missing callback for ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return;
            }
            taskCompletionSource.setResult(bundle);
        }
    }

    /* access modifiers changed from: 0000 */
    public final Bundle zza(Bundle bundle) throws IOException {
        if (this.zze.zze() < 12000000) {
            return zzb(bundle);
        }
        try {
            return (Bundle) Tasks.await(zzab.zza(this.zzd).zzb(1, bundle));
        } catch (InterruptedException | ExecutionException e) {
            String str = "FirebaseInstanceId";
            if (Log.isLoggable(str, 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("Error making request: ");
                sb.append(valueOf);
                Log.d(str, sb.toString());
            }
            if (!(e.getCause() instanceof zzam) || ((zzam) e.getCause()).zza() != 4) {
                return null;
            }
            return zzb(bundle);
        }
    }

    private final Bundle zzb(Bundle bundle) throws IOException {
        Bundle zzc2 = zzc(bundle);
        if (zzc2 == null) {
            return zzc2;
        }
        String str = "google.messenger";
        if (!zzc2.containsKey(str)) {
            return zzc2;
        }
        Bundle zzc3 = zzc(bundle);
        if (zzc3 == null || !zzc3.containsKey(str)) {
            return zzc3;
        }
        return null;
    }

    private static synchronized String zza() {
        String num;
        synchronized (zzau.class) {
            int i = zza;
            zza = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.os.Bundle zzc(android.os.Bundle r8) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = zza()
            com.google.android.gms.tasks.TaskCompletionSource r1 = new com.google.android.gms.tasks.TaskCompletionSource
            r1.<init>()
            androidx.collection.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r2 = r7.zzc
            monitor-enter(r2)
            androidx.collection.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r3 = r7.zzc     // Catch:{ all -> 0x012d }
            r3.put(r0, r1)     // Catch:{ all -> 0x012d }
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            com.google.firebase.iid.zzao r2 = r7.zze
            int r2 = r2.zzb()
            if (r2 == 0) goto L_0x0125
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.lang.String r3 = "com.google.android.gms"
            r2.setPackage(r3)
            com.google.firebase.iid.zzao r3 = r7.zze
            int r3 = r3.zzb()
            r4 = 2
            if (r3 != r4) goto L_0x0034
            java.lang.String r3 = "com.google.iid.TOKEN_REQUEST"
            r2.setAction(r3)
            goto L_0x0039
        L_0x0034:
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTER"
            r2.setAction(r3)
        L_0x0039:
            r2.putExtras(r8)
            android.content.Context r8 = r7.zzd
            zza(r8, r2)
            java.lang.String r8 = java.lang.String.valueOf(r0)
            int r8 = r8.length()
            int r8 = r8 + 5
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r8)
            java.lang.String r8 = "|ID|"
            r3.append(r8)
            r3.append(r0)
            java.lang.String r8 = "|"
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            java.lang.String r3 = "kid"
            r2.putExtra(r3, r8)
            r8 = 3
            java.lang.String r3 = "FirebaseInstanceId"
            boolean r3 = android.util.Log.isLoggable(r3, r8)
            if (r3 == 0) goto L_0x009a
            android.os.Bundle r3 = r2.getExtras()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r5 = java.lang.String.valueOf(r3)
            int r5 = r5.length()
            int r5 = r5 + 8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Sending "
            r6.append(r5)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            java.lang.String r5 = "FirebaseInstanceId"
            android.util.Log.d(r5, r3)
        L_0x009a:
            android.os.Messenger r3 = r7.zzf
            java.lang.String r5 = "google.messenger"
            r2.putExtra(r5, r3)
            android.os.Messenger r3 = r7.zzg
            if (r3 != 0) goto L_0x00a9
            com.google.firebase.iid.zzj r3 = r7.zzh
            if (r3 == 0) goto L_0x00cf
        L_0x00a9:
            android.os.Message r3 = android.os.Message.obtain()
            r3.obj = r2
            android.os.Messenger r5 = r7.zzg     // Catch:{ RemoteException -> 0x00bf }
            if (r5 == 0) goto L_0x00b9
            android.os.Messenger r5 = r7.zzg     // Catch:{ RemoteException -> 0x00bf }
            r5.send(r3)     // Catch:{ RemoteException -> 0x00bf }
            goto L_0x00e2
        L_0x00b9:
            com.google.firebase.iid.zzj r5 = r7.zzh     // Catch:{ RemoteException -> 0x00bf }
            r5.zza(r3)     // Catch:{ RemoteException -> 0x00bf }
            goto L_0x00e2
        L_0x00bf:
            r3 = move-exception
            java.lang.String r3 = "FirebaseInstanceId"
            boolean r8 = android.util.Log.isLoggable(r3, r8)
            if (r8 == 0) goto L_0x00cf
            java.lang.String r8 = "FirebaseInstanceId"
            java.lang.String r3 = "Messenger failed, fallback to startService"
            android.util.Log.d(r8, r3)
        L_0x00cf:
            com.google.firebase.iid.zzao r8 = r7.zze
            int r8 = r8.zzb()
            if (r8 != r4) goto L_0x00dd
            android.content.Context r8 = r7.zzd
            r8.sendBroadcast(r2)
            goto L_0x00e2
        L_0x00dd:
            android.content.Context r8 = r7.zzd
            r8.startService(r2)
        L_0x00e2:
            com.google.android.gms.tasks.Task r8 = r1.getTask()     // Catch:{ InterruptedException -> 0x0108, TimeoutException -> 0x0106, ExecutionException -> 0x00ff }
            r1 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0108, TimeoutException -> 0x0106, ExecutionException -> 0x00ff }
            java.lang.Object r8 = com.google.android.gms.tasks.Tasks.await(r8, r1, r3)     // Catch:{ InterruptedException -> 0x0108, TimeoutException -> 0x0106, ExecutionException -> 0x00ff }
            android.os.Bundle r8 = (android.os.Bundle) r8     // Catch:{ InterruptedException -> 0x0108, TimeoutException -> 0x0106, ExecutionException -> 0x00ff }
            androidx.collection.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r1 = r7.zzc
            monitor-enter(r1)
            androidx.collection.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r2 = r7.zzc     // Catch:{ all -> 0x00fa }
            r2.remove(r0)     // Catch:{ all -> 0x00fa }
            monitor-exit(r1)     // Catch:{ all -> 0x00fa }
            return r8
        L_0x00fa:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00fa }
            throw r8
        L_0x00fd:
            r8 = move-exception
            goto L_0x0118
        L_0x00ff:
            r8 = move-exception
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x00fd }
            r1.<init>(r8)     // Catch:{ all -> 0x00fd }
            throw r1     // Catch:{ all -> 0x00fd }
        L_0x0106:
            r8 = move-exception
            goto L_0x0109
        L_0x0108:
            r8 = move-exception
        L_0x0109:
            java.lang.String r8 = "FirebaseInstanceId"
            java.lang.String r1 = "No response"
            android.util.Log.w(r8, r1)     // Catch:{ all -> 0x00fd }
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x00fd }
            java.lang.String r1 = "TIMEOUT"
            r8.<init>(r1)     // Catch:{ all -> 0x00fd }
            throw r8     // Catch:{ all -> 0x00fd }
        L_0x0118:
            androidx.collection.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r1 = r7.zzc
            monitor-enter(r1)
            androidx.collection.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r2 = r7.zzc     // Catch:{ all -> 0x0122 }
            r2.remove(r0)     // Catch:{ all -> 0x0122 }
            monitor-exit(r1)     // Catch:{ all -> 0x0122 }
            throw r8
        L_0x0122:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0122 }
            throw r8
        L_0x0125:
            java.io.IOException r8 = new java.io.IOException
            java.lang.String r0 = "MISSING_INSTANCEID_SERVICE"
            r8.<init>(r0)
            throw r8
        L_0x012d:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzau.zzc(android.os.Bundle):android.os.Bundle");
    }
}
