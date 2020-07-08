package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzjj;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.cache.DiskLruCache;
import p008cz.msebera.android.httpclient.HttpStatus;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzx extends zzhh {
    private Boolean zza;
    private zzz zzb = zzaa.zza;
    private Boolean zzc;

    zzx(zzgq zzgq) {
        super(zzgq);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzz zzz) {
        this.zzb = zzz;
    }

    public final int zze() {
        if (!zzjj.zzb() || !zzt().zzd(null, zzap.zzdg) || zzp().zzj() < 2147483) {
            return 25;
        }
        return 100;
    }

    /* access modifiers changed from: 0000 */
    public final int zza(String str) {
        if (!zzjj.zzb() || !zzd(null, zzap.zzdf)) {
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        return zza(str, zzap.zzag, HttpStatus.SC_INTERNAL_SERVER_ERROR, 2000);
    }

    public final int zzb(String str) {
        return zzb(str, zzap.zzn);
    }

    /* access modifiers changed from: 0000 */
    public final int zzc(String str) {
        if (!zzjj.zzb() || !zzd(null, zzap.zzdf)) {
            return 25;
        }
        return zza(str, zzap.zzaf, 25, 100);
    }

    public final long zzf() {
        zzu();
        return 25001;
    }

    public final boolean zzg() {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    ApplicationInfo applicationInfo = zzn().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzc = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        zzr().zzf().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzc.booleanValue();
    }

    public final long zza(String str, zzfc<Long> zzfc) {
        if (str == null) {
            return ((Long) zzfc.zza(null)).longValue();
        }
        String zza2 = this.zzb.zza(str, zzfc.zza());
        if (TextUtils.isEmpty(zza2)) {
            return ((Long) zzfc.zza(null)).longValue();
        }
        try {
            return ((Long) zzfc.zza(Long.valueOf(Long.parseLong(zza2)))).longValue();
        } catch (NumberFormatException e) {
            return ((Long) zzfc.zza(null)).longValue();
        }
    }

    public final int zzb(String str, zzfc<Integer> zzfc) {
        if (str == null) {
            return ((Integer) zzfc.zza(null)).intValue();
        }
        String zza2 = this.zzb.zza(str, zzfc.zza());
        if (TextUtils.isEmpty(zza2)) {
            return ((Integer) zzfc.zza(null)).intValue();
        }
        try {
            return ((Integer) zzfc.zza(Integer.valueOf(Integer.parseInt(zza2)))).intValue();
        } catch (NumberFormatException e) {
            return ((Integer) zzfc.zza(null)).intValue();
        }
    }

    public final int zza(String str, zzfc<Integer> zzfc, int i, int i2) {
        return Math.max(Math.min(zzb(str, zzfc), i2), i);
    }

    public final double zzc(String str, zzfc<Double> zzfc) {
        if (str == null) {
            return ((Double) zzfc.zza(null)).doubleValue();
        }
        String zza2 = this.zzb.zza(str, zzfc.zza());
        if (TextUtils.isEmpty(zza2)) {
            return ((Double) zzfc.zza(null)).doubleValue();
        }
        try {
            return ((Double) zzfc.zza(Double.valueOf(Double.parseDouble(zza2)))).doubleValue();
        } catch (NumberFormatException e) {
            return ((Double) zzfc.zza(null)).doubleValue();
        }
    }

    public final boolean zzd(String str, zzfc<Boolean> zzfc) {
        if (str == null) {
            return ((Boolean) zzfc.zza(null)).booleanValue();
        }
        String zza2 = this.zzb.zza(str, zzfc.zza());
        if (TextUtils.isEmpty(zza2)) {
            return ((Boolean) zzfc.zza(null)).booleanValue();
        }
        return ((Boolean) zzfc.zza(Boolean.valueOf(Boolean.parseBoolean(zza2)))).booleanValue();
    }

    public final boolean zze(String str, zzfc<Boolean> zzfc) {
        return zzd(str, zzfc);
    }

    public final boolean zza(zzfc<Boolean> zzfc) {
        return zzd(null, zzfc);
    }

    private final Bundle zzz() {
        try {
            if (zzn().getPackageManager() == null) {
                zzr().zzf().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zzn()).getApplicationInfo(zzn().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzr().zzf().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (NameNotFoundException e) {
            zzr().zzf().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Boolean zzd(String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzr().zzf().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzz.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzz.getBoolean(str));
        }
    }

    /* access modifiers changed from: 0000 */
    public final List<String> zze(String str) {
        Integer num;
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzr().zzf().zza("Failed to load metadata: Metadata bundle is null");
            num = null;
        } else if (!zzz.containsKey(str)) {
            num = null;
        } else {
            num = Integer.valueOf(zzz.getInt(str));
        }
        if (num == null) {
            return null;
        }
        try {
            String[] stringArray = zzn().getResources().getStringArray(num.intValue());
            if (stringArray == null) {
                return null;
            }
            return Arrays.asList(stringArray);
        } catch (NotFoundException e) {
            zzr().zzf().zza("Failed to load string array from metadata: resource not found", e);
            return null;
        }
    }

    public final boolean zzh() {
        zzu();
        Boolean zzd = zzd("firebase_analytics_collection_deactivated");
        return zzd != null && zzd.booleanValue();
    }

    public final Boolean zzi() {
        zzu();
        return zzd("firebase_analytics_collection_enabled");
    }

    public final Boolean zzj() {
        zzb();
        Boolean zzd = zzd("google_analytics_adid_collection_enabled");
        return Boolean.valueOf(zzd == null || zzd.booleanValue());
    }

    public static long zzk() {
        return ((Long) zzap.zzac.zza(null)).longValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002d, code lost:
        if (android.text.TextUtils.isEmpty(r1) != false) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(com.google.android.gms.measurement.internal.zzg r6) {
        /*
            r5 = this;
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            java.lang.String r1 = r6.zze()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0033
            boolean r1 = com.google.android.gms.internal.measurement.zzll.zzb()
            if (r1 == 0) goto L_0x002f
            com.google.android.gms.measurement.internal.zzx r1 = r5.zzt()
            java.lang.String r2 = r6.zzc()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzap.zzch
            boolean r1 = r1.zzd(r2, r3)
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = r6.zzg()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0033
        L_0x002f:
            java.lang.String r1 = r6.zzf()
        L_0x0033:
            com.google.android.gms.measurement.internal.zzfc<java.lang.String> r2 = com.google.android.gms.measurement.internal.zzap.zzd
            r3 = 0
            java.lang.Object r2 = r2.zza(r3)
            java.lang.String r2 = (java.lang.String) r2
            android.net.Uri$Builder r2 = r0.scheme(r2)
            com.google.android.gms.measurement.internal.zzfc<java.lang.String> r4 = com.google.android.gms.measurement.internal.zzap.zze
            java.lang.Object r3 = r4.zza(r3)
            java.lang.String r3 = (java.lang.String) r3
            android.net.Uri$Builder r2 = r2.encodedAuthority(r3)
            java.lang.String r3 = "config/app/"
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r4 = r1.length()
            if (r4 == 0) goto L_0x005d
            java.lang.String r1 = r3.concat(r1)
            goto L_0x0062
        L_0x005d:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
        L_0x0062:
            android.net.Uri$Builder r1 = r2.path(r1)
            java.lang.String r6 = r6.zzd()
            java.lang.String r2 = "app_instance_id"
            android.net.Uri$Builder r6 = r1.appendQueryParameter(r2, r6)
            java.lang.String r1 = "platform"
            java.lang.String r2 = "android"
            android.net.Uri$Builder r6 = r6.appendQueryParameter(r1, r2)
            long r1 = r5.zzf()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "gmp_version"
            r6.appendQueryParameter(r2, r1)
            android.net.Uri r6 = r0.build()
            java.lang.String r6 = r6.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zza(com.google.android.gms.measurement.internal.zzg):java.lang.String");
    }

    public static long zzv() {
        return ((Long) zzap.zzc.zza(null)).longValue();
    }

    public final String zzw() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzx() {
        return zza("debug.deferred.deeplink", "");
    }

    private final String zza(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (ClassNotFoundException e) {
            zzr().zzf().zza("Could not find SystemProperties class", e);
            return str2;
        } catch (NoSuchMethodException e2) {
            zzr().zzf().zza("Could not find SystemProperties.get() method", e2);
            return str2;
        } catch (IllegalAccessException e3) {
            zzr().zzf().zza("Could not access SystemProperties.get()", e3);
            return str2;
        } catch (InvocationTargetException e4) {
            zzr().zzf().zza("SystemProperties.get() threw an exception", e4);
            return str2;
        }
    }

    public final boolean zzf(String str) {
        return DiskLruCache.VERSION_1.equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzg(String str) {
        return DiskLruCache.VERSION_1.equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzh(String str) {
        return zzd(str, zzap.zzaj);
    }

    /* access modifiers changed from: 0000 */
    public final String zzi(String str) {
        zzfc<String> zzfc = zzap.zzak;
        if (str == null) {
            return (String) zzfc.zza(null);
        }
        return (String) zzfc.zza(this.zzb.zza(str, zzfc.zza()));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzy() {
        if (this.zza == null) {
            this.zza = zzd("app_measurement_lite");
            if (this.zza == null) {
                this.zza = Boolean.valueOf(false);
            }
        }
        if (this.zza.booleanValue() || !this.zzx.zzt()) {
            return true;
        }
        return false;
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
