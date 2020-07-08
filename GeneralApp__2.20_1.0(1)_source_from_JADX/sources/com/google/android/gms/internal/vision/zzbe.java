package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzbe<T> {
    private static Context zze = null;
    private static final Object zzfz = new Object();
    private static boolean zzga = false;
    private static zzcu<zzcn<zzba>> zzgb;
    private static final AtomicInteger zzge = new AtomicInteger();
    private final String name;
    private final zzbk zzgc;
    private final T zzgd;
    private volatile int zzgf;
    private volatile T zzgg;

    public static void init(Context context) {
        synchronized (zzfz) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zze != context) {
                zzaq.zzx();
                zzbj.zzx();
                zzav.zzaa();
                zzge.incrementAndGet();
                zze = context;
                zzgb = zzcx.zza(zzbd.zzfy);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract T zza(Object obj);

    public static void maybeInit(Context context) {
        synchronized (zzfz) {
            if (zze == null) {
                init(context);
            }
        }
    }

    static void zzab() {
        zzge.incrementAndGet();
    }

    private zzbe(zzbk zzbk, String str, T t) {
        this.zzgf = -1;
        if (zzbk.zzgl == null && zzbk.zzgm == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (zzbk.zzgl == null || zzbk.zzgm == null) {
            this.zzgc = zzbk;
            this.name = str;
            this.zzgd = t;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    private final String zze(String str) {
        if (str != null && str.isEmpty()) {
            return this.name;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.name);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzac() {
        return zze(this.zzgc.zzgo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get() {
        /*
            r6 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = zzge
            int r0 = r0.get()
            int r1 = r6.zzgf
            if (r1 >= r0) goto L_0x007e
            monitor-enter(r6)
            int r1 = r6.zzgf     // Catch:{ all -> 0x007b }
            if (r1 >= r0) goto L_0x0079
            android.content.Context r1 = zze     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0071
            com.google.android.gms.internal.vision.zzbk r1 = r6.zzgc     // Catch:{ all -> 0x007b }
            boolean r1 = r1.zzgq     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0029
            java.lang.Object r1 = r6.zzae()     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0022
            goto L_0x0039
        L_0x0022:
            java.lang.Object r1 = r6.zzad()     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0037
            goto L_0x0039
        L_0x0029:
            java.lang.Object r1 = r6.zzad()     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0030
            goto L_0x0039
        L_0x0030:
            java.lang.Object r1 = r6.zzae()     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0037
            goto L_0x0039
        L_0x0037:
            T r1 = r6.zzgd     // Catch:{ all -> 0x007b }
        L_0x0039:
            com.google.android.gms.internal.vision.zzcu<com.google.android.gms.internal.vision.zzcn<com.google.android.gms.internal.vision.zzba>> r2 = zzgb     // Catch:{ all -> 0x007b }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.vision.zzcn r2 = (com.google.android.gms.internal.vision.zzcn) r2     // Catch:{ all -> 0x007b }
            boolean r3 = r2.isPresent()     // Catch:{ all -> 0x007b }
            if (r3 == 0) goto L_0x006b
            java.lang.Object r1 = r2.get()     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.vision.zzba r1 = (com.google.android.gms.internal.vision.zzba) r1     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.vision.zzbk r2 = r6.zzgc     // Catch:{ all -> 0x007b }
            android.net.Uri r2 = r2.zzgm     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.vision.zzbk r3 = r6.zzgc     // Catch:{ all -> 0x007b }
            java.lang.String r3 = r3.zzgl     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.vision.zzbk r4 = r6.zzgc     // Catch:{ all -> 0x007b }
            java.lang.String r4 = r4.zzgo     // Catch:{ all -> 0x007b }
            java.lang.String r5 = r6.name     // Catch:{ all -> 0x007b }
            java.lang.String r1 = r1.zza(r2, r3, r4, r5)     // Catch:{ all -> 0x007b }
            if (r1 != 0) goto L_0x0066
            T r1 = r6.zzgd     // Catch:{ all -> 0x007b }
            goto L_0x006c
        L_0x0066:
            java.lang.Object r1 = r6.zza(r1)     // Catch:{ all -> 0x007b }
            goto L_0x006c
        L_0x006b:
        L_0x006c:
            r6.zzgg = r1     // Catch:{ all -> 0x007b }
            r6.zzgf = r0     // Catch:{ all -> 0x007b }
            goto L_0x0079
        L_0x0071:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x007b }
            java.lang.String r1 = "Must call PhenotypeFlag.init() first"
            r0.<init>(r1)     // Catch:{ all -> 0x007b }
            throw r0     // Catch:{ all -> 0x007b }
        L_0x0079:
            monitor-exit(r6)     // Catch:{ all -> 0x007b }
            goto L_0x007e
        L_0x007b:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x007b }
            throw r0
        L_0x007e:
            T r0 = r6.zzgg
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzbe.get():java.lang.Object");
    }

    @Nullable
    private final T zzad() {
        zzau zzau;
        boolean z = false;
        if (!this.zzgc.zzgr) {
            String str = (String) zzav.zze(zze).zzb("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
            if (str != null && zzal.zzev.matcher(str).matches()) {
                z = true;
            }
        }
        if (!z) {
            if (this.zzgc.zzgm == null) {
                zzau = zzbj.zzb(zze, this.zzgc.zzgl);
            } else if (!zzbc.zza(zze, this.zzgc.zzgm)) {
                zzau = null;
            } else if (this.zzgc.zzgs) {
                ContentResolver contentResolver = zze.getContentResolver();
                String lastPathSegment = this.zzgc.zzgm.getLastPathSegment();
                String packageName = zze.getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(lastPathSegment).length() + 1 + String.valueOf(packageName).length());
                sb.append(lastPathSegment);
                sb.append("#");
                sb.append(packageName);
                zzau = zzaq.zza(contentResolver, zzbb.getContentProviderUri(sb.toString()));
            } else {
                zzau = zzaq.zza(zze.getContentResolver(), this.zzgc.zzgm);
            }
            if (zzau != null) {
                Object zzb = zzau.zzb(zzac());
                if (zzb != null) {
                    return zza(zzb);
                }
            }
        } else {
            String str2 = "PhenotypeFlag";
            if (Log.isLoggable(str2, 3)) {
                String str3 = "Bypass reading Phenotype values for flag: ";
                String valueOf = String.valueOf(zzac());
                Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            }
        }
        return null;
    }

    @Nullable
    private final T zzae() {
        if (!this.zzgc.zzgp && (this.zzgc.zzgt == null || ((Boolean) this.zzgc.zzgt.apply(zze)).booleanValue())) {
            Object zzb = zzav.zze(zze).zzb(this.zzgc.zzgp ? null : zze(this.zzgc.zzgn));
            if (zzb != null) {
                return zza(zzb);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static zzbe<Long> zza(zzbk zzbk, String str, long j) {
        return new zzbg(zzbk, str, Long.valueOf(j));
    }

    /* access modifiers changed from: private */
    public static zzbe<Boolean> zza(zzbk zzbk, String str, boolean z) {
        return new zzbf(zzbk, str, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public static <T> zzbe<T> zza(zzbk zzbk, String str, T t, zzbh<T> zzbh) {
        return new zzbi(zzbk, str, t, zzbh);
    }

    static final /* synthetic */ zzcn zzaf() {
        new zzaz();
        return zzaz.zzf(zze);
    }

    /* synthetic */ zzbe(zzbk zzbk, String str, Object obj, zzbg zzbg) {
        this(zzbk, str, obj);
    }
}
