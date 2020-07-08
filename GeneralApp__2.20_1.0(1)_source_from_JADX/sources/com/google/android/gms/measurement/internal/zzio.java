package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzle;
import com.google.android.gms.internal.measurement.zzlr;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzio implements ActivityLifecycleCallbacks {
    private final /* synthetic */ zzhr zza;

    private zzio(zzhr zzhr) {
        this.zza = zzhr;
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String str;
        try {
            this.zza.zzr().zzx().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null) {
                    if (data.isHierarchical()) {
                        this.zza.zzp();
                        if (zzla.zza(intent)) {
                            str = "gs";
                        } else {
                            str = "auto";
                        }
                        String queryParameter = data.getQueryParameter("referrer");
                        boolean z = bundle == null;
                        if (!zzlr.zzb() || !((Boolean) zzap.zzcd.zza(null)).booleanValue()) {
                            zza(z, data, str, queryParameter);
                        } else {
                            zzgj zzq = this.zza.zzq();
                            zzin zzin = new zzin(this, z, data, str, queryParameter);
                            zzq.zza((Runnable) zzin);
                        }
                        this.zza.zzi().zza(activity, bundle);
                        return;
                    }
                }
                this.zza.zzi().zza(activity, bundle);
            }
        } catch (Exception e) {
            this.zza.zzr().zzf().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzi().zza(activity, bundle);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a2 A[SYNTHETIC, Splitter:B:33:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea A[Catch:{ Exception -> 0x01b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00fb A[SYNTHETIC, Splitter:B:49:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012a A[Catch:{ Exception -> 0x01b4 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x012b A[Catch:{ Exception -> 0x01b4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r18, android.net.Uri r19, java.lang.String r20, java.lang.String r21) {
        /*
            r17 = this;
            r1 = r17
            r0 = r20
            r2 = r21
            com.google.android.gms.measurement.internal.zzhr r3 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r3 = r3.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzap.zzca     // Catch:{ Exception -> 0x01b4 }
            boolean r3 = r3.zza(r4)     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r4 = "Activity created with data 'referrer' without required params"
            java.lang.String r5 = "utm_medium"
            java.lang.String r6 = "_cis"
            java.lang.String r7 = "utm_source"
            java.lang.String r8 = "utm_campaign"
            java.lang.String r10 = "gclid"
            if (r3 != 0) goto L_0x0040
            com.google.android.gms.measurement.internal.zzhr r3 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r3 = r3.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzap.zzcc     // Catch:{ Exception -> 0x01b4 }
            boolean r3 = r3.zza(r11)     // Catch:{ Exception -> 0x01b4 }
            if (r3 != 0) goto L_0x0040
            com.google.android.gms.measurement.internal.zzhr r3 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r3 = r3.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzap.zzcb     // Catch:{ Exception -> 0x01b4 }
            boolean r3 = r3.zza(r11)     // Catch:{ Exception -> 0x01b4 }
            if (r3 == 0) goto L_0x003e
            goto L_0x0040
        L_0x003e:
            r3 = 0
            goto L_0x009b
        L_0x0040:
            com.google.android.gms.measurement.internal.zzhr r3 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzla r3 = r3.zzp()     // Catch:{ Exception -> 0x01b4 }
            boolean r11 = android.text.TextUtils.isEmpty(r21)     // Catch:{ Exception -> 0x01b4 }
            if (r11 == 0) goto L_0x004e
            r3 = 0
            goto L_0x009a
        L_0x004e:
            boolean r11 = r2.contains(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r11 != 0) goto L_0x0073
            boolean r11 = r2.contains(r8)     // Catch:{ Exception -> 0x01b4 }
            if (r11 != 0) goto L_0x0073
            boolean r11 = r2.contains(r7)     // Catch:{ Exception -> 0x01b4 }
            if (r11 != 0) goto L_0x0073
            boolean r11 = r2.contains(r5)     // Catch:{ Exception -> 0x01b4 }
            if (r11 != 0) goto L_0x0073
            com.google.android.gms.measurement.internal.zzfj r3 = r3.zzr()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzw()     // Catch:{ Exception -> 0x01b4 }
            r3.zza(r4)     // Catch:{ Exception -> 0x01b4 }
            r3 = 0
            goto L_0x009a
        L_0x0073:
            java.lang.String r11 = "https://google.com/search?"
            java.lang.String r12 = java.lang.String.valueOf(r21)     // Catch:{ Exception -> 0x01b4 }
            int r13 = r12.length()     // Catch:{ Exception -> 0x01b4 }
            if (r13 == 0) goto L_0x0084
            java.lang.String r11 = r11.concat(r12)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x008a
        L_0x0084:
            java.lang.String r12 = new java.lang.String     // Catch:{ Exception -> 0x01b4 }
            r12.<init>(r11)     // Catch:{ Exception -> 0x01b4 }
            r11 = r12
        L_0x008a:
            android.net.Uri r11 = android.net.Uri.parse(r11)     // Catch:{ Exception -> 0x01b4 }
            android.os.Bundle r3 = r3.zza(r11)     // Catch:{ Exception -> 0x01b4 }
            if (r3 == 0) goto L_0x0099
            java.lang.String r11 = "referrer"
            r3.putString(r6, r11)     // Catch:{ Exception -> 0x01b4 }
        L_0x0099:
        L_0x009a:
        L_0x009b:
            r11 = 0
            java.lang.String r12 = "_cmp"
            r13 = 1
            if (r18 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzhr r14 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzla r14 = r14.zzp()     // Catch:{ Exception -> 0x01b4 }
            r15 = r19
            android.os.Bundle r14 = r14.zza(r15)     // Catch:{ Exception -> 0x01b4 }
            if (r14 == 0) goto L_0x00eb
            java.lang.String r15 = "intent"
            r14.putString(r6, r15)     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzap.zzca     // Catch:{ Exception -> 0x01b4 }
            boolean r6 = r6.zza(r15)     // Catch:{ Exception -> 0x01b4 }
            if (r6 == 0) goto L_0x00e4
            boolean r6 = r14.containsKey(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r6 != 0) goto L_0x00e4
            if (r3 == 0) goto L_0x00e4
            boolean r6 = r3.containsKey(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r6 == 0) goto L_0x00e4
            java.lang.String r6 = "_cer"
            java.lang.String r15 = "gclid=%s"
            java.lang.Object[] r9 = new java.lang.Object[r13]     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r16 = r3.getString(r10)     // Catch:{ Exception -> 0x01b4 }
            r9[r11] = r16     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r9 = java.lang.String.format(r15, r9)     // Catch:{ Exception -> 0x01b4 }
            r14.putString(r6, r9)     // Catch:{ Exception -> 0x01b4 }
        L_0x00e4:
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            r6.zza(r0, r12, r14)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x00eb
        L_0x00ea:
            r14 = 0
        L_0x00eb:
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzcc     // Catch:{ Exception -> 0x01b4 }
            boolean r6 = r6.zza(r9)     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r9 = "auto"
            if (r6 == 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzap.zzcb     // Catch:{ Exception -> 0x01b4 }
            boolean r6 = r6.zza(r15)     // Catch:{ Exception -> 0x01b4 }
            if (r6 != 0) goto L_0x0124
            if (r3 == 0) goto L_0x0124
            boolean r6 = r3.containsKey(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r6 == 0) goto L_0x0124
            if (r14 == 0) goto L_0x0119
            boolean r6 = r14.containsKey(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r6 != 0) goto L_0x0124
        L_0x0119:
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r14 = "_lgclid"
            java.lang.String r15 = r3.getString(r10)     // Catch:{ Exception -> 0x01b4 }
            r6.zza(r9, r14, r15, r13)     // Catch:{ Exception -> 0x01b4 }
        L_0x0124:
            boolean r6 = android.text.TextUtils.isEmpty(r21)     // Catch:{ Exception -> 0x01b4 }
            if (r6 == 0) goto L_0x012b
            return
        L_0x012b:
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfj r6 = r6.zzr()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzw()     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r14 = "Activity created with referrer"
            r6.zza(r14, r2)     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzhr r6 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzt()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzap.zzcb     // Catch:{ Exception -> 0x01b4 }
            boolean r6 = r6.zza(r14)     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r14 = "_ldl"
            if (r6 == 0) goto L_0x0168
            if (r3 == 0) goto L_0x0152
            com.google.android.gms.measurement.internal.zzhr r2 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            r2.zza(r0, r12, r3)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x0161
        L_0x0152:
            com.google.android.gms.measurement.internal.zzhr r0 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfj r0 = r0.zzr()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzw()     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r3 = "Referrer does not contain valid parameters"
            r0.zza(r3, r2)     // Catch:{ Exception -> 0x01b4 }
        L_0x0161:
            com.google.android.gms.measurement.internal.zzhr r0 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            r2 = 0
            r0.zza(r9, r14, r2, r13)     // Catch:{ Exception -> 0x01b4 }
            return
        L_0x0168:
            boolean r0 = r2.contains(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r0 == 0) goto L_0x0193
            boolean r0 = r2.contains(r8)     // Catch:{ Exception -> 0x01b4 }
            if (r0 != 0) goto L_0x0191
            boolean r0 = r2.contains(r7)     // Catch:{ Exception -> 0x01b4 }
            if (r0 != 0) goto L_0x0191
            boolean r0 = r2.contains(r5)     // Catch:{ Exception -> 0x01b4 }
            if (r0 != 0) goto L_0x0191
            java.lang.String r0 = "utm_term"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b4 }
            if (r0 != 0) goto L_0x0191
            java.lang.String r0 = "utm_content"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b4 }
            if (r0 == 0) goto L_0x0193
        L_0x0191:
            r11 = 1
            goto L_0x0194
        L_0x0193:
        L_0x0194:
            if (r11 != 0) goto L_0x01a4
            com.google.android.gms.measurement.internal.zzhr r0 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfj r0 = r0.zzr()     // Catch:{ Exception -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzw()     // Catch:{ Exception -> 0x01b4 }
            r0.zza(r4)     // Catch:{ Exception -> 0x01b4 }
            return
        L_0x01a4:
            boolean r0 = android.text.TextUtils.isEmpty(r21)     // Catch:{ Exception -> 0x01b4 }
            if (r0 != 0) goto L_0x01b1
            com.google.android.gms.measurement.internal.zzhr r0 = r1.zza     // Catch:{ Exception -> 0x01b4 }
            r0.zza(r9, r14, r2, r13)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x01b2
        L_0x01b1:
        L_0x01b2:
            return
        L_0x01b4:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzhr r2 = r1.zza
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()
            java.lang.String r3 = "Throwable caught in handleReferrerForOnActivityCreated"
            r2.zza(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzio.zza(boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzi().zzc(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zza.zzi().zzb(activity);
        zzke zzk = this.zza.zzk();
        zzk.zzq().zza((Runnable) new zzkg(zzk, zzk.zzm().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        if (!zzle.zzb() || !((Boolean) zzap.zzay.zza(null)).booleanValue()) {
            this.zza.zzi().zza(activity);
            this.zza.zzk().zzab();
            return;
        }
        this.zza.zzk().zzab();
        this.zza.zzi().zza(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzi().zzb(activity, bundle);
    }

    /* synthetic */ zzio(zzhr zzhr, zzht zzht) {
        this(zzhr);
    }
}
