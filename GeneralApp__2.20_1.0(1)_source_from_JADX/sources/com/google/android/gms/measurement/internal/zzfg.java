package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zznh;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzfg extends zze {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private long zzg;
    private List<String> zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;

    zzfg(zzgq zzgq, long j) {
        super(zzgq);
        this.zzg = j;
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0290 A[Catch:{ IllegalStateException -> 0x02be }] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x02a1 A[Catch:{ IllegalStateException -> 0x02be }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02df  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x033f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0245 A[Catch:{ IllegalStateException -> 0x02be }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0247 A[Catch:{ IllegalStateException -> 0x02be }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0252 A[SYNTHETIC, Splitter:B:92:0x0252] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzaa() {
        /*
            r11 = this;
            android.content.Context r0 = r11.zzn()
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r1 = r11.zzn()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.lang.String r2 = "Unknown"
            java.lang.String r3 = ""
            r4 = 0
            java.lang.String r5 = "unknown"
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != 0) goto L_0x0033
            com.google.android.gms.measurement.internal.zzfj r7 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzf()
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfj.zza(r0)
            java.lang.String r9 = "PackageManager is null, app identity information might be inaccurate. appId"
            r7.zza(r9, r8)
            r8 = r2
            goto L_0x0098
        L_0x0033:
            java.lang.String r5 = r1.getInstallerPackageName(r0)     // Catch:{ IllegalArgumentException -> 0x0038 }
            goto L_0x004a
        L_0x0038:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfj r7 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzf()
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfj.zza(r0)
            java.lang.String r9 = "Error retrieving app installer package name. appId"
            r7.zza(r9, r8)
        L_0x004a:
            if (r5 != 0) goto L_0x004f
            java.lang.String r5 = "manual_install"
            goto L_0x0058
        L_0x004f:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r5)
            if (r7 == 0) goto L_0x0058
            r5 = r3
        L_0x0058:
            android.content.Context r7 = r11.zzn()     // Catch:{ NameNotFoundException -> 0x0083 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0083 }
            android.content.pm.PackageInfo r7 = r1.getPackageInfo(r7, r4)     // Catch:{ NameNotFoundException -> 0x0083 }
            if (r7 == 0) goto L_0x0081
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x0083 }
            java.lang.CharSequence r8 = r1.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x0083 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ NameNotFoundException -> 0x0083 }
            if (r9 != 0) goto L_0x0077
            java.lang.String r8 = r8.toString()     // Catch:{ NameNotFoundException -> 0x0083 }
            goto L_0x0078
        L_0x0077:
            r8 = r2
        L_0x0078:
            java.lang.String r2 = r7.versionName     // Catch:{ NameNotFoundException -> 0x007d }
            int r6 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x007d }
            goto L_0x0082
        L_0x007d:
            r7 = move-exception
            r7 = r2
            r2 = r8
            goto L_0x0085
        L_0x0081:
            r8 = r2
        L_0x0082:
            goto L_0x0098
        L_0x0083:
            r7 = move-exception
            r7 = r2
        L_0x0085:
            com.google.android.gms.measurement.internal.zzfj r8 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzf()
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r0)
            java.lang.String r10 = "Error retrieving package info. appId, appName"
            r8.zza(r10, r9, r2)
            r8 = r2
            r2 = r7
        L_0x0098:
            r11.zza = r0
            r11.zzd = r5
            r11.zzb = r2
            r11.zzc = r6
            r11.zze = r8
            r5 = 0
            r11.zzf = r5
            r11.zzu()
            android.content.Context r2 = r11.zzn()
            com.google.android.gms.common.api.Status r2 = com.google.android.gms.common.api.internal.GoogleServices.initialize(r2)
            r5 = 1
            if (r2 == 0) goto L_0x00bd
            boolean r6 = r2.isSuccess()
            if (r6 == 0) goto L_0x00bd
            r6 = 1
            goto L_0x00be
        L_0x00bd:
            r6 = 0
        L_0x00be:
            com.google.android.gms.measurement.internal.zzgq r7 = r11.zzx
            java.lang.String r7 = r7.zzo()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x00da
            com.google.android.gms.measurement.internal.zzgq r7 = r11.zzx
            java.lang.String r7 = r7.zzp()
            java.lang.String r8 = "am"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x00da
            r7 = 1
            goto L_0x00db
        L_0x00da:
            r7 = 0
        L_0x00db:
            r6 = r6 | r7
            if (r6 != 0) goto L_0x0108
            if (r2 != 0) goto L_0x00ef
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzg()
            java.lang.String r8 = "GoogleService failed to initialize (no status)"
            r2.zza(r8)
            goto L_0x0108
        L_0x00ef:
            com.google.android.gms.measurement.internal.zzfj r8 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzg()
            int r9 = r2.getStatusCode()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = r2.getStatusMessage()
            java.lang.String r10 = "GoogleService failed to initialize, status"
            r8.zza(r10, r9, r2)
        L_0x0108:
            if (r6 == 0) goto L_0x0227
            boolean r2 = com.google.android.gms.internal.measurement.zzky.zzb()
            if (r2 == 0) goto L_0x01ba
            com.google.android.gms.measurement.internal.zzx r2 = r11.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzap.zzcx
            boolean r2 = r2.zza(r6)
            if (r2 == 0) goto L_0x01ba
            com.google.android.gms.measurement.internal.zzgq r2 = r11.zzx
            int r2 = r2.zzac()
            switch(r2) {
                case 0: goto L_0x01a5;
                case 1: goto L_0x0197;
                case 2: goto L_0x0189;
                case 3: goto L_0x017b;
                case 4: goto L_0x016d;
                case 5: goto L_0x015f;
                case 6: goto L_0x0151;
                case 7: goto L_0x0143;
                default: goto L_0x0127;
            }
        L_0x0127:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzv()
            java.lang.String r8 = "App measurement disabled"
            r6.zza(r8)
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzg()
            java.lang.String r8 = "Invalid scion state in identity"
            r6.zza(r8)
            goto L_0x01b3
        L_0x0143:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzv()
            java.lang.String r8 = "App measurement disabled via the global data collection setting"
            r6.zza(r8)
            goto L_0x01b3
        L_0x0151:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzk()
            java.lang.String r8 = "App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics"
            r6.zza(r8)
            goto L_0x01b3
        L_0x015f:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzx()
            java.lang.String r8 = "App measurement disabled via the init parameters"
            r6.zza(r8)
            goto L_0x01b3
        L_0x016d:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzv()
            java.lang.String r8 = "App measurement disabled via the manifest"
            r6.zza(r8)
            goto L_0x01b3
        L_0x017b:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzv()
            java.lang.String r8 = "App measurement disabled by setAnalyticsCollectionEnabled(false)"
            r6.zza(r8)
            goto L_0x01b3
        L_0x0189:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzx()
            java.lang.String r8 = "App measurement deactivated via the init parameters"
            r6.zza(r8)
            goto L_0x01b3
        L_0x0197:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzv()
            java.lang.String r8 = "App measurement deactivated via the manifest"
            r6.zza(r8)
            goto L_0x01b3
        L_0x01a5:
            com.google.android.gms.measurement.internal.zzfj r6 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzx()
            java.lang.String r8 = "App measurement collection enabled"
            r6.zza(r8)
        L_0x01b3:
            if (r2 != 0) goto L_0x01b7
            r2 = 1
            goto L_0x01b8
        L_0x01b7:
            r2 = 0
        L_0x01b8:
            goto L_0x0228
        L_0x01ba:
            com.google.android.gms.measurement.internal.zzx r2 = r11.zzt()
            java.lang.Boolean r2 = r2.zzi()
            com.google.android.gms.measurement.internal.zzx r6 = r11.zzt()
            boolean r6 = r6.zzh()
            if (r6 == 0) goto L_0x01e3
            com.google.android.gms.measurement.internal.zzgq r2 = r11.zzx
            boolean r2 = r2.zzl()
            if (r2 == 0) goto L_0x0216
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzv()
            java.lang.String r6 = "Collection disabled with firebase_analytics_collection_deactivated=1"
            r2.zza(r6)
            goto L_0x0216
        L_0x01e3:
            if (r2 == 0) goto L_0x0201
            boolean r6 = r2.booleanValue()
            if (r6 != 0) goto L_0x0201
            com.google.android.gms.measurement.internal.zzgq r2 = r11.zzx
            boolean r2 = r2.zzl()
            if (r2 == 0) goto L_0x0216
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzv()
            java.lang.String r6 = "Collection disabled with firebase_analytics_collection_enabled=0"
            r2.zza(r6)
            goto L_0x0216
        L_0x0201:
            if (r2 != 0) goto L_0x0218
            boolean r2 = com.google.android.gms.common.api.internal.GoogleServices.isMeasurementExplicitlyDisabled()
            if (r2 == 0) goto L_0x0218
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzv()
            java.lang.String r6 = "Collection disabled with google_app_measurement_enable=0"
            r2.zza(r6)
        L_0x0216:
            r2 = 0
            goto L_0x0226
        L_0x0218:
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzx()
            java.lang.String r6 = "Collection enabled"
            r2.zza(r6)
            r2 = 1
        L_0x0226:
            goto L_0x0228
        L_0x0227:
            r2 = 0
        L_0x0228:
            r11.zzj = r3
            r11.zzk = r3
            r11.zzl = r3
            r11.zzu()
            if (r7 == 0) goto L_0x023b
            com.google.android.gms.measurement.internal.zzgq r6 = r11.zzx
            java.lang.String r6 = r6.zzo()
            r11.zzk = r6
        L_0x023b:
            java.lang.String r6 = com.google.android.gms.common.api.internal.GoogleServices.getGoogleAppId()     // Catch:{ IllegalStateException -> 0x02be }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IllegalStateException -> 0x02be }
            if (r7 == 0) goto L_0x0247
            r7 = r3
            goto L_0x0248
        L_0x0247:
            r7 = r6
        L_0x0248:
            r11.zzj = r7     // Catch:{ IllegalStateException -> 0x02be }
            boolean r7 = com.google.android.gms.internal.measurement.zzll.zzb()     // Catch:{ IllegalStateException -> 0x02be }
            java.lang.String r8 = "admob_app_id"
            if (r7 == 0) goto L_0x028a
            com.google.android.gms.measurement.internal.zzx r7 = r11.zzt()     // Catch:{ IllegalStateException -> 0x02be }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzch     // Catch:{ IllegalStateException -> 0x02be }
            boolean r7 = r7.zza(r9)     // Catch:{ IllegalStateException -> 0x02be }
            if (r7 == 0) goto L_0x028a
            com.google.android.gms.common.internal.StringResourceValueReader r7 = new com.google.android.gms.common.internal.StringResourceValueReader     // Catch:{ IllegalStateException -> 0x02be }
            android.content.Context r9 = r11.zzn()     // Catch:{ IllegalStateException -> 0x02be }
            r7.<init>(r9)     // Catch:{ IllegalStateException -> 0x02be }
            java.lang.String r9 = "ga_app_id"
            java.lang.String r9 = r7.getString(r9)     // Catch:{ IllegalStateException -> 0x02be }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ IllegalStateException -> 0x02be }
            if (r10 == 0) goto L_0x0274
            goto L_0x0275
        L_0x0274:
            r3 = r9
        L_0x0275:
            r11.zzl = r3     // Catch:{ IllegalStateException -> 0x02be }
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IllegalStateException -> 0x02be }
            if (r3 == 0) goto L_0x0283
            boolean r3 = android.text.TextUtils.isEmpty(r9)     // Catch:{ IllegalStateException -> 0x02be }
            if (r3 != 0) goto L_0x0289
        L_0x0283:
            java.lang.String r3 = r7.getString(r8)     // Catch:{ IllegalStateException -> 0x02be }
            r11.zzk = r3     // Catch:{ IllegalStateException -> 0x02be }
        L_0x0289:
            goto L_0x029f
        L_0x028a:
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IllegalStateException -> 0x02be }
            if (r3 != 0) goto L_0x029f
            com.google.android.gms.common.internal.StringResourceValueReader r3 = new com.google.android.gms.common.internal.StringResourceValueReader     // Catch:{ IllegalStateException -> 0x02be }
            android.content.Context r6 = r11.zzn()     // Catch:{ IllegalStateException -> 0x02be }
            r3.<init>(r6)     // Catch:{ IllegalStateException -> 0x02be }
            java.lang.String r3 = r3.getString(r8)     // Catch:{ IllegalStateException -> 0x02be }
            r11.zzk = r3     // Catch:{ IllegalStateException -> 0x02be }
        L_0x029f:
            if (r2 == 0) goto L_0x02bd
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()     // Catch:{ IllegalStateException -> 0x02be }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzx()     // Catch:{ IllegalStateException -> 0x02be }
            java.lang.String r3 = "App measurement enabled for app package, google app id"
            java.lang.String r6 = r11.zza     // Catch:{ IllegalStateException -> 0x02be }
            java.lang.String r7 = r11.zzj     // Catch:{ IllegalStateException -> 0x02be }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IllegalStateException -> 0x02be }
            if (r7 == 0) goto L_0x02b8
            java.lang.String r7 = r11.zzk     // Catch:{ IllegalStateException -> 0x02be }
            goto L_0x02ba
        L_0x02b8:
            java.lang.String r7 = r11.zzj     // Catch:{ IllegalStateException -> 0x02be }
        L_0x02ba:
            r2.zza(r3, r6, r7)     // Catch:{ IllegalStateException -> 0x02be }
        L_0x02bd:
            goto L_0x02d0
        L_0x02be:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfj r3 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzfj.zza(r0)
            java.lang.String r6 = "Fetching Google App Id failed with exception. appId"
            r3.zza(r6, r0, r2)
        L_0x02d0:
            r0 = 0
            r11.zzh = r0
            com.google.android.gms.measurement.internal.zzx r0 = r11.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzap.zzbl
            boolean r0 = r0.zza(r2)
            if (r0 == 0) goto L_0x0329
            r11.zzu()
            com.google.android.gms.measurement.internal.zzx r0 = r11.zzt()
            java.lang.String r2 = "analytics.safelisted_events"
            java.util.List r0 = r0.zze(r2)
            if (r0 == 0) goto L_0x0324
            int r2 = r0.size()
            if (r2 != 0) goto L_0x0305
            com.google.android.gms.measurement.internal.zzfj r2 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzk()
            java.lang.String r3 = "Safelisted event list is empty. Ignoring"
            r2.zza(r3)
            r5 = 0
            goto L_0x0325
        L_0x0305:
            java.util.Iterator r2 = r0.iterator()
        L_0x0309:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0324
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.gms.measurement.internal.zzla r6 = r11.zzp()
            java.lang.String r7 = "safelisted event"
            boolean r3 = r6.zzb(r7, r3)
            if (r3 != 0) goto L_0x0323
            r5 = 0
            goto L_0x0325
        L_0x0323:
            goto L_0x0309
        L_0x0324:
        L_0x0325:
            if (r5 == 0) goto L_0x0329
            r11.zzh = r0
        L_0x0329:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 16
            if (r0 < r2) goto L_0x033f
            if (r1 == 0) goto L_0x033c
            android.content.Context r0 = r11.zzn()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r11.zzi = r0
            return
        L_0x033c:
            r11.zzi = r4
            return
        L_0x033f:
            r11.zzi = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfg.zzaa():void");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0109  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzm zza(java.lang.String r35) {
        /*
            r34 = this;
            r0 = r34
            r34.zzd()
            r34.zzb()
            com.google.android.gms.measurement.internal.zzm r30 = new com.google.android.gms.measurement.internal.zzm
            java.lang.String r2 = r34.zzab()
            java.lang.String r3 = r34.zzac()
            r34.zzw()
            java.lang.String r4 = r0.zzb
            int r1 = r34.zzaf()
            long r5 = (long) r1
            r34.zzw()
            java.lang.String r7 = r0.zzd
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            long r8 = r1.zzf()
            r34.zzw()
            r34.zzd()
            long r10 = r0.zzf
            r12 = 0
            int r1 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x0055
            com.google.android.gms.measurement.internal.zzgq r1 = r0.zzx
            com.google.android.gms.measurement.internal.zzla r1 = r1.zzi()
            android.content.Context r10 = r34.zzn()
            android.content.Context r11 = r34.zzn()
            java.lang.String r11 = r11.getPackageName()
            long r10 = r1.zza(r10, r11)
            r0.zzf = r10
        L_0x0055:
            long r10 = r0.zzf
            com.google.android.gms.measurement.internal.zzgq r1 = r0.zzx
            boolean r13 = r1.zzab()
            com.google.android.gms.measurement.internal.zzfv r1 = r34.zzs()
            boolean r1 = r1.zzs
            r12 = 1
            r14 = r1 ^ 1
            r34.zzd()
            r34.zzb()
            com.google.android.gms.measurement.internal.zzgq r1 = r0.zzx
            boolean r1 = r1.zzab()
            if (r1 != 0) goto L_0x0077
            r16 = 0
            goto L_0x007d
        L_0x0077:
            java.lang.String r1 = r34.zzai()
            r16 = r1
        L_0x007d:
            r17 = 0
            com.google.android.gms.measurement.internal.zzgq r1 = r0.zzx
            long r19 = r1.zzad()
            int r21 = r34.zzag()
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            java.lang.Boolean r1 = r1.zzj()
            boolean r22 = r1.booleanValue()
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            r1.zzb()
            java.lang.String r15 = "google_analytics_ssaid_collection_enabled"
            java.lang.Boolean r1 = r1.zzd(r15)
            r15 = 0
            if (r1 == 0) goto L_0x00ae
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00ac
            goto L_0x00ae
        L_0x00ac:
            r1 = 0
            goto L_0x00af
        L_0x00ae:
            r1 = 1
        L_0x00af:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            boolean r24 = r1.booleanValue()
            com.google.android.gms.measurement.internal.zzfv r1 = r34.zzs()
            r1.zzd()
            android.content.SharedPreferences r1 = r1.zzg()
            java.lang.String r12 = "deferred_analytics_collection"
            boolean r26 = r1.getBoolean(r12, r15)
            java.lang.String r27 = r34.zzad()
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzbd
            boolean r1 = r1.zza(r12)
            if (r1 == 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            java.lang.String r12 = "google_analytics_default_allow_ad_personalization_signals"
            java.lang.Boolean r1 = r1.zzd(r12)
            if (r1 == 0) goto L_0x00f2
            boolean r1 = r1.booleanValue()
            r12 = 1
            r1 = r1 ^ r12
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r25 = r1
            goto L_0x00f4
        L_0x00f2:
            r25 = 0
        L_0x00f4:
            r28 = r14
            long r14 = r0.zzg
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzbl
            boolean r1 = r1.zza(r12)
            if (r1 == 0) goto L_0x0109
            java.util.List<java.lang.String> r1 = r0.zzh
            r29 = r1
            goto L_0x010b
        L_0x0109:
            r29 = 0
        L_0x010b:
            boolean r1 = com.google.android.gms.internal.measurement.zzll.zzb()
            if (r1 == 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzx r1 = r34.zzt()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzch
            boolean r1 = r1.zza(r12)
            if (r1 == 0) goto L_0x0124
            java.lang.String r1 = r34.zzae()
            r31 = r1
            goto L_0x0126
        L_0x0124:
            r31 = 0
        L_0x0126:
            r1 = r30
            r12 = r35
            r32 = r14
            r14 = r28
            r15 = r16
            r16 = r17
            r18 = r19
            r20 = r21
            r21 = r22
            r22 = r24
            r23 = r26
            r24 = r27
            r26 = r32
            r28 = r29
            r29 = r31
            r1.<init>(r2, r3, r4, r5, r7, r8, r10, r12, r13, r14, r15, r16, r18, r20, r21, r22, r23, r24, r25, r26, r28, r29)
            return r30
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfg.zza(java.lang.String):com.google.android.gms.measurement.internal.zzm");
    }

    private final String zzai() {
        if (!zznh.zzb() || !zzt().zza(zzap.zzck)) {
            try {
                Class loadClass = zzn().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                if (loadClass == null) {
                    return null;
                }
                try {
                    Object invoke = loadClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{zzn()});
                    if (invoke == null) {
                        return null;
                    }
                    try {
                        return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                    } catch (Exception e) {
                        zzr().zzk().zza("Failed to retrieve Firebase Instance Id");
                        return null;
                    }
                } catch (Exception e2) {
                    zzr().zzj().zza("Failed to obtain Firebase Analytics instance");
                    return null;
                }
            } catch (ClassNotFoundException e3) {
                return null;
            }
        } else {
            zzr().zzx().zza("Disabled IID for tests.");
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zzab() {
        zzw();
        return this.zza;
    }

    /* access modifiers changed from: 0000 */
    public final String zzac() {
        zzw();
        return this.zzj;
    }

    /* access modifiers changed from: 0000 */
    public final String zzad() {
        zzw();
        return this.zzk;
    }

    /* access modifiers changed from: 0000 */
    public final String zzae() {
        zzw();
        return this.zzl;
    }

    /* access modifiers changed from: 0000 */
    public final int zzaf() {
        zzw();
        return this.zzc;
    }

    /* access modifiers changed from: 0000 */
    public final int zzag() {
        zzw();
        return this.zzi;
    }

    /* access modifiers changed from: 0000 */
    public final List<String> zzah() {
        return this.zzh;
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzhr zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzfg zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zziz zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zziy zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzff zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzke zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzfh zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzla zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzgj zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfv zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }
}
