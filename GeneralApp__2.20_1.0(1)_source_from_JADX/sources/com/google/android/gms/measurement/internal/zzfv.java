package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzfv extends zzhk {
    static final Pair<String, Long> zza = new Pair<>("", Long.valueOf(0));
    private boolean zzaa;
    private long zzab;
    public zzfz zzb;
    public final zzga zzc = new zzga(this, "last_upload", 0);
    public final zzga zzd = new zzga(this, "last_upload_attempt", 0);
    public final zzga zze = new zzga(this, "backoff", 0);
    public final zzga zzf = new zzga(this, "last_delete_stale", 0);
    public final zzga zzg = new zzga(this, "midnight_offset", 0);
    public final zzga zzh = new zzga(this, "first_open_time", 0);
    public final zzga zzi = new zzga(this, "app_install_time", 0);
    public final zzgc zzj = new zzgc(this, "app_instance_id", null);
    public final zzga zzk = new zzga(this, "time_before_start", 10000);
    public final zzga zzl = new zzga(this, "session_timeout", 1800000);
    public final zzfx zzm = new zzfx(this, "start_new_session", true);
    public final zzgc zzn = new zzgc(this, "non_personalized_ads", null);
    public final zzfx zzo = new zzfx(this, "use_dynamite_api", false);
    public final zzfx zzp = new zzfx(this, "allow_remote_dynamite", false);
    public final zzga zzq = new zzga(this, "last_pause_time", 0);
    public final zzga zzr = new zzga(this, "time_active", 0);
    public boolean zzs;
    public zzfx zzt = new zzfx(this, "app_backgrounded", false);
    public zzfx zzu = new zzfx(this, "deep_link_retrieval_complete", false);
    public zzga zzv = new zzga(this, "deep_link_retrieval_attempts", 0);
    public final zzgc zzw = new zzgc(this, "firebase_feature_rollouts", null);
    private SharedPreferences zzy;
    private String zzz;

    /* access modifiers changed from: 0000 */
    public final Pair<String, Boolean> zza(String str) {
        String str2 = "";
        zzd();
        long elapsedRealtime = zzm().elapsedRealtime();
        String str3 = this.zzz;
        if (str3 != null && elapsedRealtime < this.zzab) {
            return new Pair<>(str3, Boolean.valueOf(this.zzaa));
        }
        this.zzab = elapsedRealtime + zzt().zza(str, zzap.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzn());
            if (advertisingIdInfo != null) {
                this.zzz = advertisingIdInfo.getId();
                this.zzaa = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzz == null) {
                this.zzz = str2;
            }
        } catch (Exception e) {
            zzr().zzw().zza("Unable to get advertising id", e);
            this.zzz = str2;
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzz, Boolean.valueOf(this.zzaa));
    }

    /* access modifiers changed from: 0000 */
    public final String zzb(String str) {
        zzd();
        String str2 = (String) zza(str).first;
        MessageDigest zzi2 = zzla.zzi();
        if (zzi2 == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzi2.digest(str2.getBytes()))});
    }

    zzfv(zzgq zzgq) {
        super(zzgq);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f_ */
    public final void mo16404f_() {
        this.zzy = zzn().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        String str = "has_been_opened";
        this.zzs = this.zzy.getBoolean(str, false);
        if (!this.zzs) {
            Editor edit = this.zzy.edit();
            edit.putBoolean(str, true);
            edit.apply();
        }
        zzfz zzfz = new zzfz(this, "health_monitor", Math.max(0, ((Long) zzap.zzb.zza(null)).longValue()));
        this.zzb = zzfz;
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zzg() {
        zzd();
        zzaa();
        return this.zzy;
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(String str) {
        zzd();
        Editor edit = zzg().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    public final String zzh() {
        zzd();
        return zzg().getString("gmp_app_id", null);
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(String str) {
        zzd();
        Editor edit = zzg().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    public final String zzi() {
        zzd();
        return zzg().getString("admob_app_id", null);
    }

    /* access modifiers changed from: 0000 */
    public final Boolean zzj() {
        zzd();
        String str = "use_service";
        if (!zzg().contains(str)) {
            return null;
        }
        return Boolean.valueOf(zzg().getBoolean(str, false));
    }

    /* access modifiers changed from: 0000 */
    public final void zza(boolean z) {
        zzd();
        Editor edit = zzg().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    public final void zzk() {
        zzd();
        Boolean zzv2 = zzv();
        Editor edit = zzg().edit();
        edit.clear();
        edit.apply();
        if (zzv2 != null) {
            zzb(zzv2.booleanValue());
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(boolean z) {
        zzd();
        Editor edit = zzg().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    public final Boolean zzv() {
        zzd();
        String str = "measurement_enabled";
        if (zzg().contains(str)) {
            return Boolean.valueOf(zzg().getBoolean(str, true));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final String zzw() {
        zzd();
        String str = "previous_os_version";
        String string = zzg().getString(str, null);
        zzl().zzaa();
        String str2 = VERSION.RELEASE;
        if (!TextUtils.isEmpty(str2) && !str2.equals(string)) {
            Editor edit = zzg().edit();
            edit.putString(str, str2);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(boolean z) {
        zzd();
        zzr().zzx().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        Editor edit = zzg().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzx() {
        return this.zzy.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(long j) {
        return j - this.zzl.zza() > this.zzq.zza();
    }
}
