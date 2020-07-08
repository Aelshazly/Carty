package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcl;
import com.google.android.gms.internal.measurement.zzkb;
import com.google.android.gms.internal.measurement.zzky;
import com.google.android.gms.internal.measurement.zzv;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public class zzgq implements zzhj {
    private static volatile zzgq zza;
    private long zzaa;
    private volatile Boolean zzab;
    private Boolean zzac;
    private Boolean zzad;
    private int zzae;
    private AtomicInteger zzaf = new AtomicInteger(0);
    private final long zzag;
    private final Context zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final boolean zzf;
    private final zzw zzg;
    private final zzx zzh;
    private final zzfv zzi;
    private final zzfj zzj;
    private final zzgj zzk;
    private final zzke zzl;
    private final zzla zzm;
    private final zzfh zzn;
    private final Clock zzo;
    private final zziy zzp;
    private final zzhr zzq;
    private final zzb zzr;
    private final zzip zzs;
    private zzff zzt;
    private zziz zzu;
    private zzah zzv;
    private zzfg zzw;
    private zzgb zzx;
    private boolean zzy = false;
    private Boolean zzz;

    private zzgq(zzhs zzhs) {
        boolean z = false;
        Preconditions.checkNotNull(zzhs);
        this.zzg = new zzw(zzhs.zza);
        zzez.zza = this.zzg;
        this.zzb = zzhs.zza;
        this.zzc = zzhs.zzb;
        this.zzd = zzhs.zzc;
        this.zze = zzhs.zzd;
        this.zzf = zzhs.zzh;
        this.zzab = zzhs.zze;
        zzv zzv2 = zzhs.zzg;
        if (!(zzv2 == null || zzv2.zzg == null)) {
            Object obj = zzv2.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzac = (Boolean) obj;
            }
            Object obj2 = zzv2.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzad = (Boolean) obj2;
            }
        }
        zzcl.zza(this.zzb);
        this.zzo = DefaultClock.getInstance();
        this.zzag = this.zzo.currentTimeMillis();
        this.zzh = new zzx(this);
        zzfv zzfv = new zzfv(this);
        zzfv.zzab();
        this.zzi = zzfv;
        zzfj zzfj = new zzfj(this);
        zzfj.zzab();
        this.zzj = zzfj;
        zzla zzla = new zzla(this);
        zzla.zzab();
        this.zzm = zzla;
        zzfh zzfh = new zzfh(this);
        zzfh.zzab();
        this.zzn = zzfh;
        this.zzr = new zzb(this);
        zziy zziy = new zziy(this);
        zziy.zzx();
        this.zzp = zziy;
        zzhr zzhr = new zzhr(this);
        zzhr.zzx();
        this.zzq = zzhr;
        zzke zzke = new zzke(this);
        zzke.zzx();
        this.zzl = zzke;
        zzip zzip = new zzip(this);
        zzip.zzab();
        this.zzs = zzip;
        zzgj zzgj = new zzgj(this);
        zzgj.zzab();
        this.zzk = zzgj;
        if (!(zzhs.zzg == null || zzhs.zzg.zzb == 0)) {
            z = true;
        }
        boolean z2 = !z;
        zzw zzw2 = this.zzg;
        if (this.zzb.getApplicationContext() instanceof Application) {
            zzhr zzh2 = zzh();
            if (zzh2.zzn().getApplicationContext() instanceof Application) {
                Application application = (Application) zzh2.zzn().getApplicationContext();
                if (zzh2.zza == null) {
                    zzh2.zza = new zzio(zzh2, null);
                }
                if (z2) {
                    application.unregisterActivityLifecycleCallbacks(zzh2.zza);
                    application.registerActivityLifecycleCallbacks(zzh2.zza);
                    zzh2.zzr().zzx().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzr().zzi().zza("Application context is not an Application");
        }
        this.zzk.zza((Runnable) new zzgs(this, zzhs));
    }

    /* access modifiers changed from: private */
    public final void zza(zzhs zzhs) {
        String str;
        zzfl zzfl;
        zzq().zzd();
        zzah zzah = new zzah(this);
        zzah.zzab();
        this.zzv = zzah;
        zzfg zzfg = new zzfg(this, zzhs.zzf);
        zzfg.zzx();
        this.zzw = zzfg;
        zzff zzff = new zzff(this);
        zzff.zzx();
        this.zzt = zzff;
        zziz zziz = new zziz(this);
        zziz.zzx();
        this.zzu = zziz;
        this.zzm.zzac();
        this.zzi.zzac();
        this.zzx = new zzgb(this);
        this.zzw.zzy();
        zzr().zzv().zza("App measurement initialized, version", Long.valueOf(this.zzh.zzf()));
        zzw zzw2 = this.zzg;
        zzr().zzv().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzw zzw3 = this.zzg;
        String zzab2 = zzfg.zzab();
        if (TextUtils.isEmpty(this.zzc)) {
            if (zzi().zzf(zzab2)) {
                zzfl = zzr().zzv();
                str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzfl = zzr().zzv();
                String str2 = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
                String valueOf = String.valueOf(zzab2);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            zzfl.zza(str);
        }
        zzr().zzw().zza("Debug-level message logging enabled");
        if (this.zzae != this.zzaf.get()) {
            zzr().zzf().zza("Not all components initialized", Integer.valueOf(this.zzae), Integer.valueOf(this.zzaf.get()));
        }
        this.zzy = true;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        zzq().zzd();
        if (zzc().zzc.zza() == 0) {
            zzc().zzc.zza(this.zzo.currentTimeMillis());
        }
        if (Long.valueOf(zzc().zzh.zza()).longValue() == 0) {
            zzr().zzx().zza("Persisting first open", Long.valueOf(this.zzag));
            zzc().zzh.zza(this.zzag);
        }
        if (zzah()) {
            zzw zzw2 = this.zzg;
            if (!TextUtils.isEmpty(zzy().zzac()) || !TextUtils.isEmpty(zzy().zzad())) {
                zzi();
                if (zzla.zza(zzy().zzac(), zzc().zzh(), zzy().zzad(), zzc().zzi())) {
                    zzr().zzv().zza("Rechecking which service to use due to a GMP App Id change");
                    zzc().zzk();
                    zzk().zzab();
                    this.zzu.zzah();
                    this.zzu.zzaf();
                    zzc().zzh.zza(this.zzag);
                    zzc().zzj.zza(null);
                }
                zzc().zzc(zzy().zzac());
                zzc().zzd(zzy().zzad());
            }
            zzh().zza(zzc().zzj.zza());
            zzw zzw3 = this.zzg;
            if (zzkb.zzb() && this.zzh.zza(zzap.zzcq) && !zzi().zzv() && !TextUtils.isEmpty(zzc().zzw.zza())) {
                zzr().zzi().zza("Remote config removed with active feature rollouts");
                zzc().zzw.zza(null);
            }
            if (!TextUtils.isEmpty(zzy().zzac()) || !TextUtils.isEmpty(zzy().zzad())) {
                boolean zzab2 = zzab();
                if (!zzc().zzx() && !this.zzh.zzh()) {
                    zzc().zzc(!zzab2);
                }
                if (zzab2) {
                    zzh().zzai();
                }
                zze().zza.zza();
                zzw().zza(new AtomicReference<>());
            }
        } else if (zzab()) {
            if (!zzi().zzd("android.permission.INTERNET")) {
                zzr().zzf().zza("App is missing INTERNET permission");
            }
            if (!zzi().zzd("android.permission.ACCESS_NETWORK_STATE")) {
                zzr().zzf().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzw zzw4 = this.zzg;
            if (!Wrappers.packageManager(this.zzb).isCallerInstantApp() && !this.zzh.zzy()) {
                if (!zzgg.zza(this.zzb)) {
                    zzr().zzf().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzla.zza(this.zzb, false)) {
                    zzr().zzf().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzr().zzf().zza("Uploading is not possible. App measurement disabled");
        }
        zzc().zzo.zza(this.zzh.zza(zzap.zzbi));
        zzc().zzp.zza(this.zzh.zza(zzap.zzbj));
    }

    public final zzw zzu() {
        return this.zzg;
    }

    public final zzx zzb() {
        return this.zzh;
    }

    public final zzfv zzc() {
        zza((zzhh) this.zzi);
        return this.zzi;
    }

    public final zzfj zzr() {
        zzb((zzhk) this.zzj);
        return this.zzj;
    }

    public final zzfj zzd() {
        zzfj zzfj = this.zzj;
        if (zzfj == null || !zzfj.zzz()) {
            return null;
        }
        return this.zzj;
    }

    public final zzgj zzq() {
        zzb((zzhk) this.zzk);
        return this.zzk;
    }

    public final zzke zze() {
        zzb((zze) this.zzl);
        return this.zzl;
    }

    public final zzgb zzf() {
        return this.zzx;
    }

    /* access modifiers changed from: 0000 */
    public final zzgj zzg() {
        return this.zzk;
    }

    public final zzhr zzh() {
        zzb((zze) this.zzq);
        return this.zzq;
    }

    public final zzla zzi() {
        zza((zzhh) this.zzm);
        return this.zzm;
    }

    public final zzfh zzj() {
        zza((zzhh) this.zzn);
        return this.zzn;
    }

    public final zzff zzk() {
        zzb((zze) this.zzt);
        return this.zzt;
    }

    private final zzip zzaj() {
        zzb((zzhk) this.zzs);
        return this.zzs;
    }

    public final Context zzn() {
        return this.zzb;
    }

    public final boolean zzl() {
        return TextUtils.isEmpty(this.zzc);
    }

    public final String zzo() {
        return this.zzc;
    }

    public final String zzp() {
        return this.zzd;
    }

    public final String zzs() {
        return this.zze;
    }

    public final boolean zzt() {
        return this.zzf;
    }

    public final Clock zzm() {
        return this.zzo;
    }

    public final zziy zzv() {
        zzb((zze) this.zzp);
        return this.zzp;
    }

    public final zziz zzw() {
        zzb((zze) this.zzu);
        return this.zzu;
    }

    public final zzah zzx() {
        zzb((zzhk) this.zzv);
        return this.zzv;
    }

    public final zzfg zzy() {
        zzb((zze) this.zzw);
        return this.zzw;
    }

    public final zzb zzz() {
        zzb zzb2 = this.zzr;
        if (zzb2 != null) {
            return zzb2;
        }
        throw new IllegalStateException("Component not created");
    }

    public static zzgq zza(Context context, String str, String str2, Bundle bundle) {
        zzv zzv2 = new zzv(0, 0, true, null, null, null, bundle);
        return zza(context, zzv2);
    }

    public static zzgq zza(Context context, zzv zzv2) {
        if (zzv2 != null && (zzv2.zze == null || zzv2.zzf == null)) {
            zzv zzv3 = new zzv(zzv2.zza, zzv2.zzb, zzv2.zzc, zzv2.zzd, null, null, zzv2.zzg);
            zzv2 = zzv3;
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzgq.class) {
                if (zza == null) {
                    zza = new zzgq(new zzhs(context, zzv2));
                }
            }
        } else if (!(zzv2 == null || zzv2.zzg == null || !zzv2.zzg.containsKey("dataCollectionDefaultEnabled"))) {
            zza.zza(zzv2.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zza;
    }

    private final void zzak() {
        if (!this.zzy) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zzb(zzhk zzhk) {
        if (zzhk == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzhk.zzz()) {
            String valueOf = String.valueOf(zzhk.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zzb(zze zze2) {
        if (zze2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zze2.zzv()) {
            String valueOf = String.valueOf(zze2.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzhh zzhh) {
        if (zzhh == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(boolean z) {
        this.zzab = Boolean.valueOf(z);
    }

    public final boolean zzaa() {
        return this.zzab != null && this.zzab.booleanValue();
    }

    public final boolean zzab() {
        if (zzky.zzb() && this.zzh.zza(zzap.zzcx)) {
            return zzac() == 0;
        }
        zzq().zzd();
        zzak();
        if (this.zzh.zzh()) {
            return false;
        }
        Boolean bool = this.zzad;
        if (bool != null && bool.booleanValue()) {
            return false;
        }
        Boolean zzv2 = zzc().zzv();
        if (zzv2 != null) {
            return zzv2.booleanValue();
        }
        Boolean zzi2 = this.zzh.zzi();
        if (zzi2 != null) {
            return zzi2.booleanValue();
        }
        Boolean bool2 = this.zzac;
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        if (GoogleServices.isMeasurementExplicitlyDisabled()) {
            return false;
        }
        if (!this.zzh.zza(zzap.zzba) || this.zzab == null) {
            return true;
        }
        return this.zzab.booleanValue();
    }

    public final int zzac() {
        zzq().zzd();
        if (this.zzh.zzh()) {
            return 1;
        }
        Boolean bool = this.zzad;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        Boolean zzv2 = zzc().zzv();
        if (zzv2 == null) {
            Boolean zzi2 = this.zzh.zzi();
            if (zzi2 == null) {
                Boolean bool2 = this.zzac;
                if (bool2 != null) {
                    if (bool2.booleanValue()) {
                        return 0;
                    }
                    return 5;
                } else if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                    return 6;
                } else {
                    if (!this.zzh.zza(zzap.zzba) || this.zzab == null || this.zzab.booleanValue()) {
                        return 0;
                    }
                    return 7;
                }
            } else if (zzi2.booleanValue()) {
                return 0;
            } else {
                return 4;
            }
        } else if (zzv2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    /* access modifiers changed from: 0000 */
    public final long zzad() {
        Long valueOf = Long.valueOf(zzc().zzh.zza());
        if (valueOf.longValue() == 0) {
            return this.zzag;
        }
        return Math.min(this.zzag, valueOf.longValue());
    }

    /* access modifiers changed from: 0000 */
    public final void zzae() {
        zzw zzw2 = this.zzg;
    }

    /* access modifiers changed from: 0000 */
    public final void zzaf() {
        zzw zzw2 = this.zzg;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzhk zzhk) {
        this.zzae++;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zze zze2) {
        this.zzae++;
    }

    /* access modifiers changed from: 0000 */
    public final void zzag() {
        this.zzaf.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    public final boolean zzah() {
        zzak();
        zzq().zzd();
        Boolean bool = this.zzz;
        if (bool == null || this.zzaa == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzo.elapsedRealtime() - this.zzaa) > 1000)) {
            this.zzaa = this.zzo.elapsedRealtime();
            zzw zzw2 = this.zzg;
            boolean z = true;
            this.zzz = Boolean.valueOf(zzi().zzd("android.permission.INTERNET") && zzi().zzd("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzb).isCallerInstantApp() || this.zzh.zzy() || (zzgg.zza(this.zzb) && zzla.zza(this.zzb, false))));
            if (this.zzz.booleanValue()) {
                if (!zzi().zza(zzy().zzac(), zzy().zzad(), zzy().zzae()) && TextUtils.isEmpty(zzy().zzad())) {
                    z = false;
                }
                this.zzz = Boolean.valueOf(z);
            }
        }
        return this.zzz.booleanValue();
    }

    public final void zzai() {
        zzq().zzd();
        zzb((zzhk) zzaj());
        String zzab2 = zzy().zzab();
        Pair zza2 = zzc().zza(zzab2);
        if (!this.zzh.zzj().booleanValue() || ((Boolean) zza2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zza2.first)) {
            zzr().zzw().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
        } else if (!zzaj().zzg()) {
            zzr().zzi().zza("Network is not available for Deferred Deep Link request. Skipping");
        } else {
            zzla zzi2 = zzi();
            URL zza3 = zzi2.zza(zzy().zzt().zzf(), zzab2, (String) zza2.first, zzc().zzv.zza() - 1);
            zzip zzaj = zzaj();
            zzgp zzgp = new zzgp(this);
            zzaj.zzd();
            zzaj.zzaa();
            Preconditions.checkNotNull(zza3);
            Preconditions.checkNotNull(zzgp);
            zzgj zzq2 = zzaj.zzq();
            zzir zzir = new zzir(zzaj, zzab2, zza3, null, null, zzgp);
            zzq2.zzb((Runnable) zzir);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a6 A[Catch:{ JSONException -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b4 A[Catch:{ JSONException -> 0x00eb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map r11) {
        /*
            r6 = this;
            java.lang.String r7 = "gclid"
            java.lang.String r11 = ""
            r0 = 1
            r1 = 0
            r2 = 200(0xc8, float:2.8E-43)
            if (r8 == r2) goto L_0x0012
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0012
            r2 = 304(0x130, float:4.26E-43)
            if (r8 != r2) goto L_0x0016
        L_0x0012:
            if (r9 != 0) goto L_0x0016
            r2 = 1
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            if (r2 != 0) goto L_0x002b
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzr()
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzi()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.String r10 = "Network Request for Deferred Deep Link failed. response, exception"
            r7.zza(r10, r8, r9)
            return
        L_0x002b:
            com.google.android.gms.measurement.internal.zzfv r8 = r6.zzc()
            com.google.android.gms.measurement.internal.zzfx r8 = r8.zzu
            r8.zza(r0)
            int r8 = r10.length
            if (r8 != 0) goto L_0x0045
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzr()
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzw()
            java.lang.String r8 = "Deferred Deep Link response empty."
            r7.zza(r8)
            return
        L_0x0045:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r10)
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00eb }
            r9.<init>(r8)     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r8 = "deeplink"
            java.lang.String r8 = r9.optString(r8, r11)     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r10 = r9.optString(r7, r11)     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r11 = "timestamp"
            r2 = 0
            double r2 = r9.optDouble(r11, r2)     // Catch:{ JSONException -> 0x00eb }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00eb }
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzr()     // Catch:{ JSONException -> 0x00eb }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzw()     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r8 = "Deferred Deep Link is empty."
            r7.zza(r8)     // Catch:{ JSONException -> 0x00eb }
            return
        L_0x0075:
            com.google.android.gms.measurement.internal.zzla r9 = r6.zzi()     // Catch:{ JSONException -> 0x00eb }
            r9.zzb()     // Catch:{ JSONException -> 0x00eb }
            boolean r11 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00eb }
            if (r11 != 0) goto L_0x00a3
            android.content.Context r9 = r9.zzn()     // Catch:{ JSONException -> 0x00eb }
            android.content.pm.PackageManager r9 = r9.getPackageManager()     // Catch:{ JSONException -> 0x00eb }
            android.content.Intent r11 = new android.content.Intent     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r4 = "android.intent.action.VIEW"
            android.net.Uri r5 = android.net.Uri.parse(r8)     // Catch:{ JSONException -> 0x00eb }
            r11.<init>(r4, r5)     // Catch:{ JSONException -> 0x00eb }
            java.util.List r9 = r9.queryIntentActivities(r11, r1)     // Catch:{ JSONException -> 0x00eb }
            if (r9 == 0) goto L_0x00a3
            boolean r9 = r9.isEmpty()     // Catch:{ JSONException -> 0x00eb }
            if (r9 != 0) goto L_0x00a3
            goto L_0x00a4
        L_0x00a3:
            r0 = 0
        L_0x00a4:
            if (r0 != 0) goto L_0x00b4
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzr()     // Catch:{ JSONException -> 0x00eb }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzi()     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r9 = "Deferred Deep Link validation failed. gclid, deep link"
            r7.zza(r9, r10, r8)     // Catch:{ JSONException -> 0x00eb }
            return
        L_0x00b4:
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ JSONException -> 0x00eb }
            r9.<init>()     // Catch:{ JSONException -> 0x00eb }
            r9.putString(r7, r10)     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r7 = "_cis"
            java.lang.String r10 = "ddp"
            r9.putString(r7, r10)     // Catch:{ JSONException -> 0x00eb }
            com.google.android.gms.measurement.internal.zzhr r7 = r6.zzq     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r10 = "auto"
            java.lang.String r11 = "_cmp"
            r7.zza(r10, r11, r9)     // Catch:{ JSONException -> 0x00eb }
            com.google.android.gms.measurement.internal.zzla r7 = r6.zzi()     // Catch:{ JSONException -> 0x00eb }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00eb }
            if (r9 != 0) goto L_0x00ea
            boolean r8 = r7.zza(r8, r2)     // Catch:{ JSONException -> 0x00eb }
            if (r8 == 0) goto L_0x00ea
            android.content.Intent r8 = new android.content.Intent     // Catch:{ JSONException -> 0x00eb }
            java.lang.String r9 = "android.google.analytics.action.DEEPLINK_ACTION"
            r8.<init>(r9)     // Catch:{ JSONException -> 0x00eb }
            android.content.Context r7 = r7.zzn()     // Catch:{ JSONException -> 0x00eb }
            r7.sendBroadcast(r8)     // Catch:{ JSONException -> 0x00eb }
        L_0x00ea:
            return
        L_0x00eb:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfj r8 = r6.zzr()
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzf()
            java.lang.String r9 = "Failed to parse the Deferred Deep Link response. exception"
            r8.zza(r9, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgq.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }
}
