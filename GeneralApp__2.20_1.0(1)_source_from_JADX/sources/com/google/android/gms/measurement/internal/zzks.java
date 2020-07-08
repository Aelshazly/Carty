package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbo.zzb;
import com.google.android.gms.internal.measurement.zzbr.zzc;
import com.google.android.gms.internal.measurement.zzbr.zze;
import com.google.android.gms.internal.measurement.zzbr.zzf;
import com.google.android.gms.internal.measurement.zzbr.zzg;
import com.google.android.gms.internal.measurement.zzbr.zzk;
import com.google.android.gms.internal.measurement.zzfd;
import com.google.android.gms.internal.measurement.zzkt;
import com.google.android.gms.internal.measurement.zzkz;
import com.google.android.gms.internal.measurement.zzll;
import com.google.android.gms.internal.measurement.zzlx;
import com.google.android.gms.internal.measurement.zzv;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.jvm.internal.LongCompanionObject;
import p008cz.msebera.android.httpclient.HttpStatus;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
public class zzks implements zzhj {
    private static volatile zzks zza;
    private zzgk zzb;
    private zzfq zzc;
    private zzac zzd;
    private zzft zze;
    private zzko zzf;
    private zzn zzg;
    private final zzkw zzh;
    private zzit zzi;
    private final zzgq zzj;
    private boolean zzk;
    private boolean zzl;
    private long zzm;
    private List<Runnable> zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private FileLock zzt;
    private FileChannel zzu;
    private List<Long> zzv;
    private List<Long> zzw;
    private long zzx;

    /* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
    class zza implements zzae {
        zzg zza;
        List<Long> zzb;
        List<zzc> zzc;
        private long zzd;

        private zza() {
        }

        public final void zza(zzg zzg) {
            Preconditions.checkNotNull(zzg);
            this.zza = zzg;
        }

        public final boolean zza(long j, zzc zzc2) {
            Preconditions.checkNotNull(zzc2);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (this.zzc.size() > 0 && zza((zzc) this.zzc.get(0)) != zza(zzc2)) {
                return false;
            }
            long zzbn = this.zzd + ((long) zzc2.zzbn());
            if (zzbn >= ((long) Math.max(0, ((Integer) zzap.zzh.zza(null)).intValue()))) {
                return false;
            }
            this.zzd = zzbn;
            this.zzc.add(zzc2);
            this.zzb.add(Long.valueOf(j));
            if (this.zzc.size() >= Math.max(1, ((Integer) zzap.zzi.zza(null)).intValue())) {
                return false;
            }
            return true;
        }

        private static long zza(zzc zzc2) {
            return ((zzc2.zze() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzks zzks, zzkr zzkr) {
            this();
        }
    }

    public static zzks zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzks.class) {
                if (zza == null) {
                    zza = new zzks(new zzkx(context));
                }
            }
        }
        return zza;
    }

    private zzks(zzkx zzkx) {
        this(zzkx, null);
    }

    private zzks(zzkx zzkx, zzgq zzgq) {
        this.zzk = false;
        Preconditions.checkNotNull(zzkx);
        this.zzj = zzgq.zza(zzkx.zza, (zzv) null);
        this.zzx = -1;
        zzkw zzkw = new zzkw(this);
        zzkw.zzal();
        this.zzh = zzkw;
        zzfq zzfq = new zzfq(this);
        zzfq.zzal();
        this.zzc = zzfq;
        zzgk zzgk = new zzgk(this);
        zzgk.zzal();
        this.zzb = zzgk;
        this.zzj.zzq().zza((Runnable) new zzkr(this, zzkx));
    }

    /* access modifiers changed from: private */
    public final void zza(zzkx zzkx) {
        this.zzj.zzq().zzd();
        zzac zzac = new zzac(this);
        zzac.zzal();
        this.zzd = zzac;
        this.zzj.zzb().zza((zzz) this.zzb);
        zzn zzn2 = new zzn(this);
        zzn2.zzal();
        this.zzg = zzn2;
        zzit zzit = new zzit(this);
        zzit.zzal();
        this.zzi = zzit;
        zzko zzko = new zzko(this);
        zzko.zzal();
        this.zzf = zzko;
        this.zze = new zzft(this);
        if (this.zzo != this.zzp) {
            this.zzj.zzr().zzf().zza("Not all upload components initialized", Integer.valueOf(this.zzo), Integer.valueOf(this.zzp));
        }
        this.zzk = true;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        this.zzj.zzq().zzd();
        zze().zzv();
        if (this.zzj.zzc().zzc.zza() == 0) {
            this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
        }
        zzz();
    }

    public final zzw zzu() {
        return this.zzj.zzu();
    }

    public final zzx zzb() {
        return this.zzj.zzb();
    }

    public final zzfj zzr() {
        return this.zzj.zzr();
    }

    public final zzgj zzq() {
        return this.zzj.zzq();
    }

    public final zzgk zzc() {
        zzb((zzkp) this.zzb);
        return this.zzb;
    }

    public final zzfq zzd() {
        zzb((zzkp) this.zzc);
        return this.zzc;
    }

    public final zzac zze() {
        zzb((zzkp) this.zzd);
        return this.zzd;
    }

    private final zzft zzt() {
        zzft zzft = this.zze;
        if (zzft != null) {
            return zzft;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzko zzv() {
        zzb((zzkp) this.zzf);
        return this.zzf;
    }

    public final zzn zzf() {
        zzb((zzkp) this.zzg);
        return this.zzg;
    }

    public final zzit zzg() {
        zzb((zzkp) this.zzi);
        return this.zzi;
    }

    public final zzkw zzh() {
        zzb((zzkp) this.zzh);
        return this.zzh;
    }

    public final zzfh zzi() {
        return this.zzj.zzj();
    }

    public final Context zzn() {
        return this.zzj.zzn();
    }

    public final Clock zzm() {
        return this.zzj.zzm();
    }

    public final zzla zzj() {
        return this.zzj.zzi();
    }

    private final void zzw() {
        this.zzj.zzq().zzd();
    }

    /* access modifiers changed from: 0000 */
    public final void zzk() {
        if (!this.zzk) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zzb(zzkp zzkp) {
        if (zzkp == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzkp.zzaj()) {
            String valueOf = String.valueOf(zzkp.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private final long zzx() {
        long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
        zzfv zzc2 = this.zzj.zzc();
        zzc2.zzaa();
        zzc2.zzd();
        long zza2 = zzc2.zzg.zza();
        if (zza2 == 0) {
            zza2 = 1 + ((long) zzc2.zzp().zzh().nextInt(86400000));
            zzc2.zzg.zza(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzan zzan, String str) {
        String str2;
        zzan zzan2 = zzan;
        zzg zzb2 = zze().zzb(str);
        if (zzb2 == null || TextUtils.isEmpty(zzb2.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping event", str);
            return;
        }
        Boolean zzb3 = zzb(zzb2);
        if (zzb3 == null) {
            if (!"_ui".equals(zzan2.zza)) {
                this.zzj.zzr().zzi().zza("Could not find package. appId", zzfj.zza(str));
            }
        } else if (!zzb3.booleanValue()) {
            this.zzj.zzr().zzf().zza("App version does not match; dropping event. appId", zzfj.zza(str));
            return;
        }
        String zze2 = zzb2.zze();
        String zzl2 = zzb2.zzl();
        long zzm2 = zzb2.zzm();
        String zzn2 = zzb2.zzn();
        long zzo2 = zzb2.zzo();
        long zzp2 = zzb2.zzp();
        boolean zzr2 = zzb2.zzr();
        String zzi2 = zzb2.zzi();
        long zzae = zzb2.zzae();
        boolean zzaf = zzb2.zzaf();
        boolean zzag = zzb2.zzag();
        String zzf2 = zzb2.zzf();
        Boolean zzah = zzb2.zzah();
        long zzq2 = zzb2.zzq();
        List zzai = zzb2.zzai();
        if (!zzll.zzb() || !this.zzj.zzb().zze(zzb2.zzc(), zzap.zzch)) {
            str2 = null;
        } else {
            str2 = zzb2.zzg();
        }
        zzm zzm3 = r2;
        zzm zzm4 = new zzm(str, zze2, zzl2, zzm2, zzn2, zzo2, zzp2, (String) null, zzr2, false, zzi2, zzae, 0, 0, zzaf, zzag, false, zzf2, zzah, zzq2, zzai, str2);
        zza(zzan2, zzm3);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0140 A[Catch:{ all -> 0x03a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0222 A[Catch:{ all -> 0x03a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzan r20, com.google.android.gms.measurement.internal.zzm r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r20
            r2 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            java.lang.String r3 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            r19.zzw()
            r19.zzk()
            java.lang.String r3 = r2.zza
            long r11 = r0.zzd
            com.google.android.gms.measurement.internal.zzkw r4 = r19.zzh()
            boolean r4 = r4.zza(r0, r2)
            if (r4 != 0) goto L_0x0023
            return
        L_0x0023:
            boolean r4 = r2.zzh
            if (r4 != 0) goto L_0x002b
            r1.zzc(r2)
            return
        L_0x002b:
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj
            com.google.android.gms.measurement.internal.zzx r4 = r4.zzb()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzap.zzbl
            boolean r4 = r4.zze(r3, r5)
            if (r4 == 0) goto L_0x0081
            java.util.List<java.lang.String> r4 = r2.zzu
            if (r4 == 0) goto L_0x0081
            java.util.List<java.lang.String> r4 = r2.zzu
            java.lang.String r5 = r0.zza
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x006c
            com.google.android.gms.measurement.internal.zzam r4 = r0.zzb
            android.os.Bundle r4 = r4.zzb()
            r5 = 1
            java.lang.String r7 = "ga_safelisted"
            r4.putLong(r7, r5)
            com.google.android.gms.measurement.internal.zzan r5 = new com.google.android.gms.measurement.internal.zzan
            java.lang.String r14 = r0.zza
            com.google.android.gms.measurement.internal.zzam r15 = new com.google.android.gms.measurement.internal.zzam
            r15.<init>(r4)
            java.lang.String r4 = r0.zzc
            long r6 = r0.zzd
            r13 = r5
            r16 = r4
            r17 = r6
            r13.<init>(r14, r15, r16, r17)
            r0 = r5
            goto L_0x0081
        L_0x006c:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzw()
            java.lang.String r4 = r0.zza
            java.lang.String r0 = r0.zzc
            java.lang.String r5 = "Dropping non-safelisted event. appId, event name, origin"
            r2.zza(r5, r3, r4, r0)
            return
        L_0x0081:
            com.google.android.gms.measurement.internal.zzac r4 = r19.zze()
            r4.zzf()
            com.google.android.gms.measurement.internal.zzac r4 = r19.zze()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x03a2 }
            r4.zzd()     // Catch:{ all -> 0x03a2 }
            r4.zzak()     // Catch:{ all -> 0x03a2 }
            r5 = 0
            r7 = 2
            r13 = 0
            r14 = 1
            int r8 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x00b9
            com.google.android.gms.measurement.internal.zzfj r4 = r4.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzi()     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = "Invalid time querying timed out conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r3)     // Catch:{ all -> 0x03a2 }
            java.lang.Long r9 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x03a2 }
            r4.zza(r5, r6, r9)     // Catch:{ all -> 0x03a2 }
            java.util.List r4 = java.util.Collections.emptyList()     // Catch:{ all -> 0x03a2 }
            goto L_0x00c9
        L_0x00b9:
            java.lang.String r5 = "active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout"
            java.lang.String[] r6 = new java.lang.String[r7]     // Catch:{ all -> 0x03a2 }
            r6[r13] = r3     // Catch:{ all -> 0x03a2 }
            java.lang.String r9 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x03a2 }
            r6[r14] = r9     // Catch:{ all -> 0x03a2 }
            java.util.List r4 = r4.zza(r5, r6)     // Catch:{ all -> 0x03a2 }
        L_0x00c9:
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x03a2 }
        L_0x00cd:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x03a2 }
            if (r5 == 0) goto L_0x015b
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzv r5 = (com.google.android.gms.measurement.internal.zzv) r5     // Catch:{ all -> 0x03a2 }
            if (r5 == 0) goto L_0x0158
            boolean r6 = com.google.android.gms.internal.measurement.zzkz.zzb()     // Catch:{ all -> 0x03a2 }
            java.lang.String r9 = "User property timed out"
            if (r6 == 0) goto L_0x0118
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzb()     // Catch:{ all -> 0x03a2 }
            java.lang.String r10 = r2.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzap.zzcy     // Catch:{ all -> 0x03a2 }
            boolean r6 = r6.zze(r10, r15)     // Catch:{ all -> 0x03a2 }
            if (r6 == 0) goto L_0x0118
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r6 = r6.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzx()     // Catch:{ all -> 0x03a2 }
            java.lang.String r10 = r5.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r15 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r15 = r15.zzj()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r14 = r5.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r14 = r14.zza     // Catch:{ all -> 0x03a2 }
            java.lang.String r14 = r15.zzc(r14)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r15 = r5.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x03a2 }
            r6.zza(r9, r10, r14, r15)     // Catch:{ all -> 0x03a2 }
            goto L_0x013c
        L_0x0118:
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r6 = r6.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzw()     // Catch:{ all -> 0x03a2 }
            java.lang.String r10 = r5.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r14 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r14 = r14.zzj()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r15 = r5.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r15 = r15.zza     // Catch:{ all -> 0x03a2 }
            java.lang.String r14 = r14.zzc(r15)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r15 = r5.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x03a2 }
            r6.zza(r9, r10, r14, r15)     // Catch:{ all -> 0x03a2 }
        L_0x013c:
            com.google.android.gms.measurement.internal.zzan r6 = r5.zzg     // Catch:{ all -> 0x03a2 }
            if (r6 == 0) goto L_0x014a
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzan r9 = r5.zzg     // Catch:{ all -> 0x03a2 }
            r6.<init>(r9, r11)     // Catch:{ all -> 0x03a2 }
            r1.zzb(r6, r2)     // Catch:{ all -> 0x03a2 }
        L_0x014a:
            com.google.android.gms.measurement.internal.zzac r6 = r19.zze()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r5 = r5.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x03a2 }
            r6.zze(r3, r5)     // Catch:{ all -> 0x03a2 }
            r14 = 1
            goto L_0x00cd
        L_0x0158:
            r14 = 1
            goto L_0x00cd
        L_0x015b:
            com.google.android.gms.measurement.internal.zzac r4 = r19.zze()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x03a2 }
            r4.zzd()     // Catch:{ all -> 0x03a2 }
            r4.zzak()     // Catch:{ all -> 0x03a2 }
            if (r8 >= 0) goto L_0x0185
            com.google.android.gms.measurement.internal.zzfj r4 = r4.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzi()     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = "Invalid time querying expired conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r3)     // Catch:{ all -> 0x03a2 }
            java.lang.Long r9 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x03a2 }
            r4.zza(r5, r6, r9)     // Catch:{ all -> 0x03a2 }
            java.util.List r4 = java.util.Collections.emptyList()     // Catch:{ all -> 0x03a2 }
            goto L_0x0196
        L_0x0185:
            java.lang.String r5 = "active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live"
            java.lang.String[] r6 = new java.lang.String[r7]     // Catch:{ all -> 0x03a2 }
            r6[r13] = r3     // Catch:{ all -> 0x03a2 }
            java.lang.String r9 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x03a2 }
            r10 = 1
            r6[r10] = r9     // Catch:{ all -> 0x03a2 }
            java.util.List r4 = r4.zza(r5, r6)     // Catch:{ all -> 0x03a2 }
        L_0x0196:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x03a2 }
            int r6 = r4.size()     // Catch:{ all -> 0x03a2 }
            r5.<init>(r6)     // Catch:{ all -> 0x03a2 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x03a2 }
        L_0x01a4:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x03a2 }
            if (r6 == 0) goto L_0x0238
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzv r6 = (com.google.android.gms.measurement.internal.zzv) r6     // Catch:{ all -> 0x03a2 }
            if (r6 == 0) goto L_0x0235
            boolean r9 = com.google.android.gms.internal.measurement.zzkz.zzb()     // Catch:{ all -> 0x03a2 }
            java.lang.String r10 = "User property expired"
            if (r9 == 0) goto L_0x01ef
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzx r9 = r9.zzb()     // Catch:{ all -> 0x03a2 }
            java.lang.String r14 = r2.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzap.zzcy     // Catch:{ all -> 0x03a2 }
            boolean r9 = r9.zze(r14, r15)     // Catch:{ all -> 0x03a2 }
            if (r9 == 0) goto L_0x01ef
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r9 = r9.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r9 = r9.zzx()     // Catch:{ all -> 0x03a2 }
            java.lang.String r14 = r6.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r15 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r15 = r15.zzj()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r7 = r6.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = r7.zza     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = r15.zzc(r7)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r15 = r6.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x03a2 }
            r9.zza(r10, r14, r7, r15)     // Catch:{ all -> 0x03a2 }
            goto L_0x0213
        L_0x01ef:
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzw()     // Catch:{ all -> 0x03a2 }
            java.lang.String r9 = r6.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r14 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r14 = r14.zzj()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r15 = r6.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r15 = r15.zza     // Catch:{ all -> 0x03a2 }
            java.lang.String r14 = r14.zzc(r15)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r15 = r6.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x03a2 }
            r7.zza(r10, r9, r14, r15)     // Catch:{ all -> 0x03a2 }
        L_0x0213:
            com.google.android.gms.measurement.internal.zzac r7 = r19.zze()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r9 = r6.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03a2 }
            r7.zzb(r3, r9)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzan r7 = r6.zzk     // Catch:{ all -> 0x03a2 }
            if (r7 == 0) goto L_0x0227
            com.google.android.gms.measurement.internal.zzan r7 = r6.zzk     // Catch:{ all -> 0x03a2 }
            r5.add(r7)     // Catch:{ all -> 0x03a2 }
        L_0x0227:
            com.google.android.gms.measurement.internal.zzac r7 = r19.zze()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzkz r6 = r6.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r6 = r6.zza     // Catch:{ all -> 0x03a2 }
            r7.zze(r3, r6)     // Catch:{ all -> 0x03a2 }
            r7 = 2
            goto L_0x01a4
        L_0x0235:
            r7 = 2
            goto L_0x01a4
        L_0x0238:
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ all -> 0x03a2 }
            int r4 = r5.size()     // Catch:{ all -> 0x03a2 }
            r6 = 0
        L_0x023f:
            if (r6 >= r4) goto L_0x0252
            java.lang.Object r7 = r5.get(r6)     // Catch:{ all -> 0x03a2 }
            int r6 = r6 + 1
            com.google.android.gms.measurement.internal.zzan r7 = (com.google.android.gms.measurement.internal.zzan) r7     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzan r9 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x03a2 }
            r9.<init>(r7, r11)     // Catch:{ all -> 0x03a2 }
            r1.zzb(r9, r2)     // Catch:{ all -> 0x03a2 }
            goto L_0x023f
        L_0x0252:
            com.google.android.gms.measurement.internal.zzac r4 = r19.zze()     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = r0.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x03a2 }
            r4.zzd()     // Catch:{ all -> 0x03a2 }
            r4.zzak()     // Catch:{ all -> 0x03a2 }
            if (r8 >= 0) goto L_0x0289
            com.google.android.gms.measurement.internal.zzfj r6 = r4.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzi()     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = "Invalid time querying triggered conditional properties"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfj.zza(r3)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r4 = r4.zzo()     // Catch:{ all -> 0x03a2 }
            java.lang.String r4 = r4.zza(r5)     // Catch:{ all -> 0x03a2 }
            java.lang.Long r5 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x03a2 }
            r6.zza(r7, r3, r4, r5)     // Catch:{ all -> 0x03a2 }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x03a2 }
            goto L_0x029e
        L_0x0289:
            java.lang.String r6 = "active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout"
            r7 = 3
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ all -> 0x03a2 }
            r7[r13] = r3     // Catch:{ all -> 0x03a2 }
            r3 = 1
            r7[r3] = r5     // Catch:{ all -> 0x03a2 }
            java.lang.String r3 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x03a2 }
            r5 = 2
            r7[r5] = r3     // Catch:{ all -> 0x03a2 }
            java.util.List r3 = r4.zza(r6, r7)     // Catch:{ all -> 0x03a2 }
        L_0x029e:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x03a2 }
            int r4 = r3.size()     // Catch:{ all -> 0x03a2 }
            r14.<init>(r4)     // Catch:{ all -> 0x03a2 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x03a2 }
        L_0x02ac:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x03a2 }
            if (r4 == 0) goto L_0x0376
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x03a2 }
            r15 = r4
            com.google.android.gms.measurement.internal.zzv r15 = (com.google.android.gms.measurement.internal.zzv) r15     // Catch:{ all -> 0x03a2 }
            if (r15 == 0) goto L_0x0372
            com.google.android.gms.measurement.internal.zzkz r4 = r15.zzc     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzlb r10 = new com.google.android.gms.measurement.internal.zzlb     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x03a2 }
            java.lang.String r6 = r15.zzb     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = r4.zza     // Catch:{ all -> 0x03a2 }
            java.lang.Object r16 = r4.zza()     // Catch:{ all -> 0x03a2 }
            r4 = r10
            r8 = r11
            r13 = r10
            r10 = r16
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzac r4 = r19.zze()     // Catch:{ all -> 0x03a2 }
            boolean r4 = r4.zza(r13)     // Catch:{ all -> 0x03a2 }
            if (r4 == 0) goto L_0x0331
            boolean r4 = com.google.android.gms.internal.measurement.zzkz.zzb()     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = "User property triggered"
            if (r4 == 0) goto L_0x0312
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzx r4 = r4.zzb()     // Catch:{ all -> 0x03a2 }
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzcy     // Catch:{ all -> 0x03a2 }
            boolean r4 = r4.zze(r6, r7)     // Catch:{ all -> 0x03a2 }
            if (r4 == 0) goto L_0x0312
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r4 = r4.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzx()     // Catch:{ all -> 0x03a2 }
            java.lang.String r6 = r15.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r7 = r7.zzj()     // Catch:{ all -> 0x03a2 }
            java.lang.String r8 = r13.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = r7.zzc(r8)     // Catch:{ all -> 0x03a2 }
            java.lang.Object r8 = r13.zze     // Catch:{ all -> 0x03a2 }
            r4.zza(r5, r6, r7, r8)     // Catch:{ all -> 0x03a2 }
            goto L_0x0355
        L_0x0312:
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r4 = r4.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzw()     // Catch:{ all -> 0x03a2 }
            java.lang.String r6 = r15.zza     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r7 = r7.zzj()     // Catch:{ all -> 0x03a2 }
            java.lang.String r8 = r13.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = r7.zzc(r8)     // Catch:{ all -> 0x03a2 }
            java.lang.Object r8 = r13.zze     // Catch:{ all -> 0x03a2 }
            r4.zza(r5, r6, r7, r8)     // Catch:{ all -> 0x03a2 }
            goto L_0x0355
        L_0x0331:
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfj r4 = r4.zzr()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzf()     // Catch:{ all -> 0x03a2 }
            java.lang.String r5 = "Too many active user properties, ignoring"
            java.lang.String r6 = r15.zza     // Catch:{ all -> 0x03a2 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r6)     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzfh r7 = r7.zzj()     // Catch:{ all -> 0x03a2 }
            java.lang.String r8 = r13.zzc     // Catch:{ all -> 0x03a2 }
            java.lang.String r7 = r7.zzc(r8)     // Catch:{ all -> 0x03a2 }
            java.lang.Object r8 = r13.zze     // Catch:{ all -> 0x03a2 }
            r4.zza(r5, r6, r7, r8)     // Catch:{ all -> 0x03a2 }
        L_0x0355:
            com.google.android.gms.measurement.internal.zzan r4 = r15.zzi     // Catch:{ all -> 0x03a2 }
            if (r4 == 0) goto L_0x035e
            com.google.android.gms.measurement.internal.zzan r4 = r15.zzi     // Catch:{ all -> 0x03a2 }
            r14.add(r4)     // Catch:{ all -> 0x03a2 }
        L_0x035e:
            com.google.android.gms.measurement.internal.zzkz r4 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x03a2 }
            r4.<init>(r13)     // Catch:{ all -> 0x03a2 }
            r15.zzc = r4     // Catch:{ all -> 0x03a2 }
            r4 = 1
            r15.zze = r4     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzac r5 = r19.zze()     // Catch:{ all -> 0x03a2 }
            r5.zza(r15)     // Catch:{ all -> 0x03a2 }
            r13 = 0
            goto L_0x02ac
        L_0x0372:
            r4 = 1
            r13 = 0
            goto L_0x02ac
        L_0x0376:
            r1.zzb(r0, r2)     // Catch:{ all -> 0x03a2 }
            java.util.ArrayList r14 = (java.util.ArrayList) r14     // Catch:{ all -> 0x03a2 }
            int r0 = r14.size()     // Catch:{ all -> 0x03a2 }
            r3 = 0
        L_0x0380:
            if (r3 >= r0) goto L_0x0393
            java.lang.Object r4 = r14.get(r3)     // Catch:{ all -> 0x03a2 }
            int r3 = r3 + 1
            com.google.android.gms.measurement.internal.zzan r4 = (com.google.android.gms.measurement.internal.zzan) r4     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzan r5 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x03a2 }
            r5.<init>(r4, r11)     // Catch:{ all -> 0x03a2 }
            r1.zzb(r5, r2)     // Catch:{ all -> 0x03a2 }
            goto L_0x0380
        L_0x0393:
            com.google.android.gms.measurement.internal.zzac r0 = r19.zze()     // Catch:{ all -> 0x03a2 }
            r0.mo16206b_()     // Catch:{ all -> 0x03a2 }
            com.google.android.gms.measurement.internal.zzac r0 = r19.zze()
            r0.zzh()
            return
        L_0x03a2:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzac r2 = r19.zze()
            r2.zzh()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.zza(com.google.android.gms.measurement.internal.zzan, com.google.android.gms.measurement.internal.zzm):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:124:0x03ba A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x03f7 A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x041e A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0473 A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x04a1  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0a3d A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0247 A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x024f A[Catch:{ IOException -> 0x0a40, all -> 0x0acd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(com.google.android.gms.measurement.internal.zzan r31, com.google.android.gms.measurement.internal.zzm r32) {
        /*
            r30 = this;
            r1 = r30
            r2 = r31
            r3 = r32
            java.lang.String r4 = "_s"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r32)
            java.lang.String r5 = r3.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            long r5 = java.lang.System.nanoTime()
            r30.zzw()
            r30.zzk()
            java.lang.String r15 = r3.zza
            com.google.android.gms.measurement.internal.zzkw r7 = r30.zzh()
            boolean r7 = r7.zza(r2, r3)
            if (r7 != 0) goto L_0x0027
            return
        L_0x0027:
            boolean r7 = r3.zzh
            if (r7 != 0) goto L_0x002f
            r1.zzc(r3)
            return
        L_0x002f:
            com.google.android.gms.measurement.internal.zzgk r7 = r30.zzc()
            java.lang.String r8 = r2.zza
            boolean r7 = r7.zzb(r15, r8)
            java.lang.String r14 = "_err"
            r13 = 0
            if (r7 == 0) goto L_0x00e0
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r3 = r3.zzr()
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzi()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r15)
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj
            com.google.android.gms.measurement.internal.zzfh r5 = r5.zzj()
            java.lang.String r6 = r2.zza
            java.lang.String r5 = r5.zza(r6)
            java.lang.String r6 = "Dropping blacklisted event. appId"
            r3.zza(r6, r4, r5)
            com.google.android.gms.measurement.internal.zzgk r3 = r30.zzc()
            boolean r3 = r3.zzg(r15)
            if (r3 != 0) goto L_0x0076
            com.google.android.gms.measurement.internal.zzgk r3 = r30.zzc()
            boolean r3 = r3.zzh(r15)
            if (r3 == 0) goto L_0x0074
            goto L_0x0076
        L_0x0074:
            r3 = 0
            goto L_0x0077
        L_0x0076:
            r3 = 1
        L_0x0077:
            if (r3 != 0) goto L_0x0093
            java.lang.String r4 = r2.zza
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0093
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj
            com.google.android.gms.measurement.internal.zzla r7 = r4.zzi()
            r9 = 11
            java.lang.String r11 = r2.zza
            r12 = 0
            java.lang.String r10 = "_ev"
            r8 = r15
            r7.zza(r8, r9, r10, r11, r12)
        L_0x0093:
            if (r3 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            com.google.android.gms.measurement.internal.zzg r2 = r2.zzb(r15)
            if (r2 == 0) goto L_0x00df
            long r3 = r2.zzu()
            long r5 = r2.zzt()
            long r3 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj
            com.google.android.gms.common.util.Clock r5 = r5.zzm()
            long r5 = r5.currentTimeMillis()
            long r5 = r5 - r3
            long r3 = java.lang.Math.abs(r5)
            com.google.android.gms.measurement.internal.zzfc<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzap.zzy
            java.lang.Object r5 = r5.zza(r13)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r3 = r3.zzr()
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzw()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r3.zza(r4)
            r1.zza(r2)
        L_0x00df:
            return
        L_0x00e0:
            boolean r7 = com.google.android.gms.internal.measurement.zzjj.zzb()
            if (r7 == 0) goto L_0x01d0
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj
            com.google.android.gms.measurement.internal.zzx r7 = r7.zzb()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzap.zzdf
            boolean r7 = r7.zza(r8)
            if (r7 == 0) goto L_0x01d0
            com.google.android.gms.measurement.internal.zzfn r7 = new com.google.android.gms.measurement.internal.zzfn
            java.lang.String r8 = r2.zza
            java.lang.String r9 = r2.zzc
            com.google.android.gms.measurement.internal.zzam r10 = r2.zzb
            android.os.Bundle r19 = r10.zzb()
            long r11 = r2.zzd
            r16 = r7
            r17 = r8
            r18 = r9
            r20 = r11
            r16.<init>(r17, r18, r19, r20)
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj
            com.google.android.gms.measurement.internal.zzla r2 = r2.zzi()
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()
            com.google.android.gms.measurement.internal.zzfc<java.lang.Integer> r9 = com.google.android.gms.measurement.internal.zzap.zzah
            r10 = 25
            r11 = 100
            int r8 = r8.zza(r15, r9, r10, r11)
            java.util.TreeSet r9 = new java.util.TreeSet
            android.os.Bundle r10 = r7.zzd
            java.util.Set r10 = r10.keySet()
            r9.<init>(r10)
            java.util.Iterator r9 = r9.iterator()
            r10 = 0
        L_0x013a:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x01ae
            java.lang.Object r11 = r9.next()
            java.lang.String r11 = (java.lang.String) r11
            boolean r12 = com.google.android.gms.measurement.internal.zzla.zza(r11)
            if (r12 == 0) goto L_0x01a0
            int r10 = r10 + 1
            if (r10 <= r8) goto L_0x0199
            r12 = 48
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r12)
            java.lang.String r12 = "Event can't contain more than "
            r13.append(r12)
            r13.append(r8)
            java.lang.String r12 = " params"
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            com.google.android.gms.measurement.internal.zzfj r13 = r2.zzr()
            com.google.android.gms.measurement.internal.zzfl r13 = r13.zzh()
            r31 = r8
            com.google.android.gms.measurement.internal.zzfh r8 = r2.zzo()
            r17 = r9
            java.lang.String r9 = r7.zza
            java.lang.String r8 = r8.zza(r9)
            com.google.android.gms.measurement.internal.zzfh r9 = r2.zzo()
            r18 = r2
            android.os.Bundle r2 = r7.zzd
            java.lang.String r2 = r9.zza(r2)
            r13.zza(r12, r8, r2)
            android.os.Bundle r2 = r7.zzd
            r8 = 5
            com.google.android.gms.measurement.internal.zzla.zza(r2, r8)
            android.os.Bundle r2 = r7.zzd
            r2.remove(r11)
            goto L_0x01a6
        L_0x0199:
            r18 = r2
            r31 = r8
            r17 = r9
            goto L_0x01a6
        L_0x01a0:
            r18 = r2
            r31 = r8
            r17 = r9
        L_0x01a6:
            r8 = r31
            r9 = r17
            r2 = r18
            r13 = 0
            goto L_0x013a
        L_0x01ae:
            com.google.android.gms.measurement.internal.zzan r2 = new com.google.android.gms.measurement.internal.zzan
            java.lang.String r8 = r7.zza
            com.google.android.gms.measurement.internal.zzam r9 = new com.google.android.gms.measurement.internal.zzam
            android.os.Bundle r10 = new android.os.Bundle
            android.os.Bundle r11 = r7.zzd
            r10.<init>(r11)
            r9.<init>(r10)
            java.lang.String r10 = r7.zzb
            long r11 = r7.zzc
            r23 = r2
            r24 = r8
            r25 = r9
            r26 = r10
            r27 = r11
            r23.<init>(r24, r25, r26, r27)
        L_0x01d0:
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()
            r13 = 2
            boolean r7 = r7.zza(r13)
            if (r7 == 0) goto L_0x01f8
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzx()
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj
            com.google.android.gms.measurement.internal.zzfh r8 = r8.zzj()
            java.lang.String r8 = r8.zza(r2)
            java.lang.String r9 = "Logging event"
            r7.zza(r9, r8)
        L_0x01f8:
            com.google.android.gms.measurement.internal.zzac r7 = r30.zze()
            r7.zzf()
            r1.zzc(r3)     // Catch:{ all -> 0x0acd }
            boolean r7 = com.google.android.gms.internal.measurement.zzju.zzb()     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x0218
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r7 = r7.zzb()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzap.zzde     // Catch:{ all -> 0x0acd }
            boolean r7 = r7.zza(r8)     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x0218
            r7 = 1
            goto L_0x0219
        L_0x0218:
            r7 = 0
        L_0x0219:
            java.lang.String r8 = "ecommerce_purchase"
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = "refund"
            if (r8 != 0) goto L_0x023c
            if (r7 == 0) goto L_0x023a
            java.lang.String r7 = "purchase"
            java.lang.String r8 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0acd }
            if (r7 != 0) goto L_0x023c
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r7 = r9.equals(r7)     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x023a
            goto L_0x023c
        L_0x023a:
            r7 = 0
            goto L_0x023d
        L_0x023c:
            r7 = 1
        L_0x023d:
            java.lang.String r8 = "_iap"
            java.lang.String r10 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.equals(r10)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x024c
            if (r7 == 0) goto L_0x024a
            goto L_0x024c
        L_0x024a:
            r8 = 0
            goto L_0x024d
        L_0x024c:
            r8 = 1
        L_0x024d:
            if (r8 == 0) goto L_0x0406
            com.google.android.gms.measurement.internal.zzam r8 = r2.zzb     // Catch:{ all -> 0x0acd }
            java.lang.String r10 = "currency"
            java.lang.String r8 = r8.zzd(r10)     // Catch:{ all -> 0x0acd }
            java.lang.String r10 = "value"
            if (r7 == 0) goto L_0x02cd
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x0acd }
            java.lang.Double r7 = r7.zzc(r10)     // Catch:{ all -> 0x0acd }
            double r11 = r7.doubleValue()     // Catch:{ all -> 0x0acd }
            r17 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r11 = r11 * r17
            r19 = 0
            int r7 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r7 != 0) goto L_0x0280
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x0acd }
            java.lang.Long r7 = r7.zzb(r10)     // Catch:{ all -> 0x0acd }
            long r10 = r7.longValue()     // Catch:{ all -> 0x0acd }
            double r10 = (double) r10     // Catch:{ all -> 0x0acd }
            double r11 = r10 * r17
        L_0x0280:
            r17 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r7 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r7 > 0) goto L_0x02af
            r17 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r7 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r7 < 0) goto L_0x02af
            long r10 = java.lang.Math.round(r11)     // Catch:{ all -> 0x0acd }
            boolean r7 = com.google.android.gms.internal.measurement.zzju.zzb()     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x02ae
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r7 = r7.zzb()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzde     // Catch:{ all -> 0x0acd }
            boolean r7 = r7.zza(r12)     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x02ae
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r7 = r9.equals(r7)     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x02ae
            long r10 = -r10
            goto L_0x02d7
        L_0x02ae:
            goto L_0x02d7
        L_0x02af:
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "Data lost. Currency value is too big. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r15)     // Catch:{ all -> 0x0acd }
            java.lang.Double r10 = java.lang.Double.valueOf(r11)     // Catch:{ all -> 0x0acd }
            r7.zza(r8, r9, r10)     // Catch:{ all -> 0x0acd }
            r23 = r5
            r5 = 2
            r6 = 0
            goto L_0x03f5
        L_0x02cd:
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x0acd }
            java.lang.Long r7 = r7.zzb(r10)     // Catch:{ all -> 0x0acd }
            long r10 = r7.longValue()     // Catch:{ all -> 0x0acd }
        L_0x02d7:
            boolean r7 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r7 != 0) goto L_0x03f1
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = r8.toUpperCase(r7)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "[A-Z]{3}"
            boolean r8 = r7.matches(r8)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x03ed
            java.lang.String r8 = "_ltv_"
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0acd }
            int r9 = r7.length()     // Catch:{ all -> 0x0acd }
            if (r9 == 0) goto L_0x0300
            java.lang.String r7 = r8.concat(r7)     // Catch:{ all -> 0x0acd }
            goto L_0x0305
        L_0x0300:
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x0acd }
            r7.<init>(r8)     // Catch:{ all -> 0x0acd }
        L_0x0305:
            r12 = r7
            com.google.android.gms.measurement.internal.zzac r7 = r30.zze()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzlb r7 = r7.zzc(r15, r12)     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x0347
            java.lang.Object r8 = r7.zze     // Catch:{ all -> 0x0acd }
            boolean r8 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x031b
            r23 = r5
            r5 = 0
            r6 = 1
            goto L_0x034b
        L_0x031b:
            java.lang.Object r7 = r7.zze     // Catch:{ all -> 0x0acd }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0acd }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzlb r17 = new com.google.android.gms.measurement.internal.zzlb     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r13 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.common.util.Clock r13 = r13.zzm()     // Catch:{ all -> 0x0acd }
            long r18 = r13.currentTimeMillis()     // Catch:{ all -> 0x0acd }
            long r7 = r7 + r10
            java.lang.Long r13 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0acd }
            r7 = r17
            r8 = r15
            r10 = r12
            r23 = r5
            r5 = 0
            r6 = 1
            r11 = r18
            r7.<init>(r8, r9, r10, r11, r13)     // Catch:{ all -> 0x0acd }
            r6 = r17
            r5 = 2
            goto L_0x03b0
        L_0x0347:
            r23 = r5
            r5 = 0
            r6 = 1
        L_0x034b:
            com.google.android.gms.measurement.internal.zzac r7 = r30.zze()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Integer> r9 = com.google.android.gms.measurement.internal.zzap.zzad     // Catch:{ all -> 0x0acd }
            int r8 = r8.zzb(r15, r9)     // Catch:{ all -> 0x0acd }
            int r8 = r8 - r6
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x0acd }
            r7.zzd()     // Catch:{ all -> 0x0acd }
            r7.zzak()     // Catch:{ all -> 0x0acd }
            android.database.sqlite.SQLiteDatabase r9 = r7.mo16207c_()     // Catch:{ SQLiteException -> 0x0382 }
            java.lang.String r13 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            r6 = 3
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0382 }
            r6[r5] = r15     // Catch:{ SQLiteException -> 0x0382 }
            r16 = 1
            r6[r16] = r15     // Catch:{ SQLiteException -> 0x0382 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x0382 }
            r5 = 2
            r6[r5] = r8     // Catch:{ SQLiteException -> 0x0380 }
            r9.execSQL(r13, r6)     // Catch:{ SQLiteException -> 0x0380 }
            goto L_0x0396
        L_0x0380:
            r0 = move-exception
            goto L_0x0384
        L_0x0382:
            r0 = move-exception
            r5 = 2
        L_0x0384:
            r6 = r0
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "Error pruning currencies. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r15)     // Catch:{ all -> 0x0acd }
            r7.zza(r8, r9, r6)     // Catch:{ all -> 0x0acd }
        L_0x0396:
            com.google.android.gms.measurement.internal.zzlb r6 = new com.google.android.gms.measurement.internal.zzlb     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.common.util.Clock r7 = r7.zzm()     // Catch:{ all -> 0x0acd }
            long r16 = r7.currentTimeMillis()     // Catch:{ all -> 0x0acd }
            java.lang.Long r13 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0acd }
            r7 = r6
            r8 = r15
            r10 = r12
            r11 = r16
            r7.<init>(r8, r9, r10, r11, r13)     // Catch:{ all -> 0x0acd }
        L_0x03b0:
            com.google.android.gms.measurement.internal.zzac r7 = r30.zze()     // Catch:{ all -> 0x0acd }
            boolean r7 = r7.zza(r6)     // Catch:{ all -> 0x0acd }
            if (r7 != 0) goto L_0x03f4
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r15)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r10 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfh r10 = r10.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.String r11 = r6.zzc     // Catch:{ all -> 0x0acd }
            java.lang.String r10 = r10.zzc(r11)     // Catch:{ all -> 0x0acd }
            java.lang.Object r6 = r6.zze     // Catch:{ all -> 0x0acd }
            r7.zza(r8, r9, r10, r6)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r7 = r6.zzi()     // Catch:{ all -> 0x0acd }
            r9 = 9
            r10 = 0
            r11 = 0
            r12 = 0
            r8 = r15
            r7.zza(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0acd }
            goto L_0x03f4
        L_0x03ed:
            r23 = r5
            r5 = 2
            goto L_0x03f4
        L_0x03f1:
            r23 = r5
            r5 = 2
        L_0x03f4:
            r6 = 1
        L_0x03f5:
            if (r6 != 0) goto L_0x0409
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()     // Catch:{ all -> 0x0acd }
            r2.mo16206b_()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            r2.zzh()
            return
        L_0x0406:
            r23 = r5
            r5 = 2
        L_0x0409:
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r6 = com.google.android.gms.measurement.internal.zzla.zza(r6)     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r18 = r14.equals(r7)     // Catch:{ all -> 0x0acd }
            boolean r7 = com.google.android.gms.internal.measurement.zzju.zzb()     // Catch:{ all -> 0x0acd }
            r19 = 1
            if (r7 == 0) goto L_0x043e
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r7 = r7.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzda     // Catch:{ all -> 0x0acd }
            boolean r7 = r7.zze(r8, r9)     // Catch:{ all -> 0x0acd }
            if (r7 == 0) goto L_0x043e
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            r7.zzi()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x0acd }
            long r7 = com.google.android.gms.measurement.internal.zzla.zza(r7)     // Catch:{ all -> 0x0acd }
            long r7 = r7 + r19
            r11 = r7
            goto L_0x0440
        L_0x043e:
            r11 = r19
        L_0x0440:
            com.google.android.gms.measurement.internal.zzac r7 = r30.zze()     // Catch:{ all -> 0x0acd }
            long r8 = r30.zzx()     // Catch:{ all -> 0x0acd }
            r13 = 1
            r16 = 0
            r17 = 0
            r10 = r15
            r14 = r6
            r31 = r15
            r15 = r16
            r16 = r18
            com.google.android.gms.measurement.internal.zzab r7 = r7.zza(r8, r10, r11, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x0acd }
            long r8 = r7.zzb     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzap.zzj     // Catch:{ all -> 0x0acd }
            r14 = 0
            java.lang.Object r10 = r10.zza(r14)     // Catch:{ all -> 0x0acd }
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch:{ all -> 0x0acd }
            int r10 = r10.intValue()     // Catch:{ all -> 0x0acd }
            long r10 = (long) r10     // Catch:{ all -> 0x0acd }
            long r8 = r8 - r10
            r10 = 1000(0x3e8, double:4.94E-321)
            r12 = 0
            int r15 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r15 <= 0) goto L_0x04a1
            long r8 = r8 % r10
            int r2 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x0492
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r31)     // Catch:{ all -> 0x0acd }
            long r5 = r7.zzb     // Catch:{ all -> 0x0acd }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0acd }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0acd }
        L_0x0492:
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()     // Catch:{ all -> 0x0acd }
            r2.mo16206b_()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            r2.zzh()
            return
        L_0x04a1:
            if (r6 == 0) goto L_0x04fa
            long r8 = r7.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Integer> r15 = com.google.android.gms.measurement.internal.zzap.zzl     // Catch:{ all -> 0x0acd }
            java.lang.Object r15 = r15.zza(r14)     // Catch:{ all -> 0x0acd }
            java.lang.Integer r15 = (java.lang.Integer) r15     // Catch:{ all -> 0x0acd }
            int r15 = r15.intValue()     // Catch:{ all -> 0x0acd }
            r17 = r6
            long r5 = (long) r15     // Catch:{ all -> 0x0acd }
            long r8 = r8 - r5
            int r5 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r5 <= 0) goto L_0x04fc
            long r8 = r8 % r10
            int r3 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r3 != 0) goto L_0x04d8
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r3 = r3.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r31)     // Catch:{ all -> 0x0acd }
            long r6 = r7.zza     // Catch:{ all -> 0x0acd }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0acd }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x0acd }
        L_0x04d8:
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r7 = r3.zzi()     // Catch:{ all -> 0x0acd }
            r9 = 16
            java.lang.String r10 = "_ev"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0acd }
            r12 = 0
            r8 = r31
            r7.zza(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()     // Catch:{ all -> 0x0acd }
            r2.mo16206b_()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            r2.zzh()
            return
        L_0x04fa:
            r17 = r6
        L_0x04fc:
            if (r18 == 0) goto L_0x054d
            long r5 = r7.zzd     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzap.zzk     // Catch:{ all -> 0x0acd }
            int r8 = r8.zzb(r9, r10)     // Catch:{ all -> 0x0acd }
            r9 = 1000000(0xf4240, float:1.401298E-39)
            int r8 = java.lang.Math.min(r9, r8)     // Catch:{ all -> 0x0acd }
            r15 = 0
            int r8 = java.lang.Math.max(r15, r8)     // Catch:{ all -> 0x0acd }
            long r8 = (long) r8     // Catch:{ all -> 0x0acd }
            long r5 = r5 - r8
            int r8 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x054e
            int r2 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x053e
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r31)     // Catch:{ all -> 0x0acd }
            long r5 = r7.zzd     // Catch:{ all -> 0x0acd }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0acd }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0acd }
        L_0x053e:
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()     // Catch:{ all -> 0x0acd }
            r2.mo16206b_()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            r2.zzh()
            return
        L_0x054d:
            r15 = 0
        L_0x054e:
            com.google.android.gms.measurement.internal.zzam r5 = r2.zzb     // Catch:{ all -> 0x0acd }
            android.os.Bundle r5 = r5.zzb()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r6 = r6.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = "_o"
            java.lang.String r8 = r2.zzc     // Catch:{ all -> 0x0acd }
            r6.zza(r5, r7, r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r6 = r6.zzi()     // Catch:{ all -> 0x0acd }
            r11 = r31
            boolean r6 = r6.zzf(r11)     // Catch:{ all -> 0x0acd }
            java.lang.String r10 = "_r"
            if (r6 == 0) goto L_0x0591
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r6 = r6.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = "_dbg"
            java.lang.Long r8 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x0acd }
            r6.zza(r5, r7, r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r6 = r6.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.Long r7 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x0acd }
            r6.zza(r5, r10, r7)     // Catch:{ all -> 0x0acd }
        L_0x0591:
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r6 = r4.equals(r6)     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = "_sno"
            if (r6 == 0) goto L_0x05ca
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzat     // Catch:{ all -> 0x0acd }
            boolean r6 = r6.zze(r8, r9)     // Catch:{ all -> 0x0acd }
            if (r6 == 0) goto L_0x05ca
            com.google.android.gms.measurement.internal.zzac r6 = r30.zze()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzlb r6 = r6.zzc(r8, r7)     // Catch:{ all -> 0x0acd }
            if (r6 == 0) goto L_0x05ca
            java.lang.Object r8 = r6.zze     // Catch:{ all -> 0x0acd }
            boolean r8 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x05ca
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r8 = r8.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.Object r6 = r6.zze     // Catch:{ all -> 0x0acd }
            r8.zza(r5, r7, r6)     // Catch:{ all -> 0x0acd }
        L_0x05ca:
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x0acd }
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x0acd }
            if (r4 == 0) goto L_0x05fc
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r4 = r4.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzap.zzax     // Catch:{ all -> 0x0acd }
            boolean r4 = r4.zze(r6, r8)     // Catch:{ all -> 0x0acd }
            if (r4 == 0) goto L_0x05fc
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r4 = r4.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzap.zzat     // Catch:{ all -> 0x0acd }
            boolean r4 = r4.zze(r6, r8)     // Catch:{ all -> 0x0acd }
            if (r4 != 0) goto L_0x05fc
            com.google.android.gms.measurement.internal.zzkz r4 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x0acd }
            r4.<init>(r7, r12, r14)     // Catch:{ all -> 0x0acd }
            r1.zzb(r4, r3)     // Catch:{ all -> 0x0acd }
        L_0x05fc:
            com.google.android.gms.measurement.internal.zzac r4 = r30.zze()     // Catch:{ all -> 0x0acd }
            long r6 = r4.zzc(r11)     // Catch:{ all -> 0x0acd }
            int r4 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x0620
            com.google.android.gms.measurement.internal.zzgq r4 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r4 = r4.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r4 = r4.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r11)     // Catch:{ all -> 0x0acd }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0acd }
            r4.zza(r8, r9, r6)     // Catch:{ all -> 0x0acd }
        L_0x0620:
            com.google.android.gms.measurement.internal.zzak r4 = new com.google.android.gms.measurement.internal.zzak     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x0acd }
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x0acd }
            long r12 = r2.zzd     // Catch:{ all -> 0x0acd }
            r20 = 0
            r7 = r4
            r2 = r10
            r10 = r11
            r29 = r11
            r11 = r6
            r6 = r14
            r22 = 0
            r14 = r20
            r16 = r5
            r7.<init>(r8, r9, r10, r11, r12, r14, r16)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r5 = r30.zze()     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = r4.zzb     // Catch:{ all -> 0x0acd }
            r8 = r29
            com.google.android.gms.measurement.internal.zzaj r5 = r5.zza(r8, r7)     // Catch:{ all -> 0x0acd }
            if (r5 != 0) goto L_0x06c2
            com.google.android.gms.measurement.internal.zzac r5 = r30.zze()     // Catch:{ all -> 0x0acd }
            long r9 = r5.zzh(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r5 = r5.zzb()     // Catch:{ all -> 0x0acd }
            int r5 = r5.zza(r8)     // Catch:{ all -> 0x0acd }
            long r11 = (long) r5     // Catch:{ all -> 0x0acd }
            int r5 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r5 < 0) goto L_0x06a9
            if (r17 == 0) goto L_0x06a9
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfh r6 = r6.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.String r4 = r4.zzb     // Catch:{ all -> 0x0acd }
            java.lang.String r4 = r6.zza(r4)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzb()     // Catch:{ all -> 0x0acd }
            int r6 = r6.zza(r8)     // Catch:{ all -> 0x0acd }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0acd }
            r2.zza(r3, r5, r4, r6)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r7 = r2.zzi()     // Catch:{ all -> 0x0acd }
            r9 = 8
            r10 = 0
            r11 = 0
            r12 = 0
            r7.zza(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            r2.zzh()
            return
        L_0x06a9:
            com.google.android.gms.measurement.internal.zzaj r5 = new com.google.android.gms.measurement.internal.zzaj     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r4.zzb     // Catch:{ all -> 0x0acd }
            r10 = 0
            r12 = 0
            long r14 = r4.zzc     // Catch:{ all -> 0x0acd }
            r16 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r7 = r5
            r7.<init>(r8, r9, r10, r12, r14, r16, r18, r19, r20, r21)     // Catch:{ all -> 0x0acd }
            goto L_0x06d0
        L_0x06c2:
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0acd }
            long r8 = r5.zzf     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzak r4 = r4.zza(r7, r8)     // Catch:{ all -> 0x0acd }
            long r7 = r4.zzc     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzaj r5 = r5.zza(r7)     // Catch:{ all -> 0x0acd }
        L_0x06d0:
            com.google.android.gms.measurement.internal.zzac r7 = r30.zze()     // Catch:{ all -> 0x0acd }
            r7.zza(r5)     // Catch:{ all -> 0x0acd }
            r30.zzw()     // Catch:{ all -> 0x0acd }
            r30.zzk()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r32)     // Catch:{ all -> 0x0acd }
            java.lang.String r5 = r4.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x0acd }
            java.lang.String r5 = r4.zza     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x0acd }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r5)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r5 = com.google.android.gms.internal.measurement.zzbr.zzg.zzbf()     // Catch:{ all -> 0x0acd }
            r7 = 1
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r5 = r5.zza(r7)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "android"
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r5 = r5.zza(r8)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x0710
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            r5.zzf(r8)     // Catch:{ all -> 0x0acd }
        L_0x0710:
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x071d
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0acd }
            r5.zze(r8)     // Catch:{ all -> 0x0acd }
        L_0x071d:
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x072a
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0acd }
            r5.zzg(r8)     // Catch:{ all -> 0x0acd }
        L_0x072a:
            long r8 = r3.zzj     // Catch:{ all -> 0x0acd }
            r10 = -2147483648(0xffffffff80000000, double:NaN)
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x0739
            long r8 = r3.zzj     // Catch:{ all -> 0x0acd }
            int r9 = (int) r8     // Catch:{ all -> 0x0acd }
            r5.zzh(r9)     // Catch:{ all -> 0x0acd }
        L_0x0739:
            long r8 = r3.zze     // Catch:{ all -> 0x0acd }
            r5.zzf(r8)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x074b
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0acd }
            r5.zzk(r8)     // Catch:{ all -> 0x0acd }
        L_0x074b:
            boolean r8 = com.google.android.gms.internal.measurement.zzll.zzb()     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x079a
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzap.zzch     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.zze(r9, r10)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x079a
            java.lang.String r8 = r5.zzl()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x0778
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x0778
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x0acd }
            r5.zzp(r8)     // Catch:{ all -> 0x0acd }
        L_0x0778:
            java.lang.String r8 = r5.zzl()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x07b1
            java.lang.String r8 = r5.zzo()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x07b1
            java.lang.String r8 = r3.zzr     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x07b1
            java.lang.String r8 = r3.zzr     // Catch:{ all -> 0x0acd }
            r5.zzo(r8)     // Catch:{ all -> 0x0acd }
            goto L_0x07b1
        L_0x079a:
            java.lang.String r8 = r5.zzl()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x07b1
            java.lang.String r8 = r3.zzr     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x07b1
            java.lang.String r8 = r3.zzr     // Catch:{ all -> 0x0acd }
            r5.zzo(r8)     // Catch:{ all -> 0x0acd }
        L_0x07b1:
            long r8 = r3.zzf     // Catch:{ all -> 0x0acd }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x07be
            long r8 = r3.zzf     // Catch:{ all -> 0x0acd }
            r5.zzh(r8)     // Catch:{ all -> 0x0acd }
        L_0x07be:
            long r8 = r3.zzt     // Catch:{ all -> 0x0acd }
            r5.zzk(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzbf     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.zze(r9, r12)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x07e1
            com.google.android.gms.measurement.internal.zzkw r8 = r30.zzh()     // Catch:{ all -> 0x0acd }
            java.util.List r8 = r8.zzf()     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x07e1
            r5.zzd(r8)     // Catch:{ all -> 0x0acd }
        L_0x07e1:
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfv r8 = r8.zzc()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0acd }
            android.util.Pair r8 = r8.zza(r9)     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x0815
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x0acd }
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ all -> 0x0acd }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0acd }
            if (r9 != 0) goto L_0x0815
            boolean r9 = r3.zzo     // Catch:{ all -> 0x0acd }
            if (r9 == 0) goto L_0x087c
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0acd }
            r5.zzh(r9)     // Catch:{ all -> 0x0acd }
            java.lang.Object r9 = r8.second     // Catch:{ all -> 0x0acd }
            if (r9 == 0) goto L_0x087c
            java.lang.Object r8 = r8.second     // Catch:{ all -> 0x0acd }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0acd }
            r5.zza(r8)     // Catch:{ all -> 0x0acd }
            goto L_0x087c
        L_0x0815:
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzah r8 = r8.zzx()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x0acd }
            android.content.Context r9 = r9.zzn()     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.zza(r9)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x087c
            boolean r8 = r3.zzp     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x087c
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            android.content.Context r8 = r8.zzn()     // Catch:{ all -> 0x0acd }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = "android_id"
            java.lang.String r8 = android.provider.Settings.Secure.getString(r8, r9)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x085b
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r8 = r8.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = "null secure ID. appId"
            java.lang.String r12 = r5.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfj.zza(r12)     // Catch:{ all -> 0x0acd }
            r8.zza(r9, r12)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = "null"
            goto L_0x0879
        L_0x085b:
            boolean r9 = r8.isEmpty()     // Catch:{ all -> 0x0acd }
            if (r9 == 0) goto L_0x0879
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r9 = r9.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r9 = r9.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r12 = "empty secure ID. appId"
            java.lang.String r13 = r5.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ all -> 0x0acd }
            r9.zza(r12, r13)     // Catch:{ all -> 0x0acd }
        L_0x0879:
            r5.zzm(r8)     // Catch:{ all -> 0x0acd }
        L_0x087c:
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzah r8 = r8.zzx()     // Catch:{ all -> 0x0acd }
            r8.zzaa()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = android.os.Build.MODEL     // Catch:{ all -> 0x0acd }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r8 = r5.zzc(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzah r9 = r9.zzx()     // Catch:{ all -> 0x0acd }
            r9.zzaa()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0acd }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r8 = r8.zzb(r9)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzah r9 = r9.zzx()     // Catch:{ all -> 0x0acd }
            long r12 = r9.zzf()     // Catch:{ all -> 0x0acd }
            int r9 = (int) r12     // Catch:{ all -> 0x0acd }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r8 = r8.zzf(r9)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzah r9 = r9.zzx()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r9.zzg()     // Catch:{ all -> 0x0acd }
            r8.zzd(r9)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzdh     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.zza(r9)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x08cb
            long r8 = r3.zzl     // Catch:{ all -> 0x0acd }
            r5.zzj(r8)     // Catch:{ all -> 0x0acd }
        L_0x08cb:
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.zzab()     // Catch:{ all -> 0x0acd }
            if (r8 == 0) goto L_0x08df
            r5.zzj()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x08df
            r5.zzn(r6)     // Catch:{ all -> 0x0acd }
        L_0x08df:
            com.google.android.gms.measurement.internal.zzac r6 = r30.zze()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzg r6 = r6.zzb(r8)     // Catch:{ all -> 0x0acd }
            if (r6 != 0) goto L_0x0963
            com.google.android.gms.measurement.internal.zzg r6 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0acd }
            r6.<init>(r8, r9)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzla r8 = r8.zzi()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r8.zzk()     // Catch:{ all -> 0x0acd }
            r6.zza(r8)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zzk     // Catch:{ all -> 0x0acd }
            r6.zzf(r8)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0acd }
            r6.zzb(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfv r8 = r8.zzc()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r8.zzb(r9)     // Catch:{ all -> 0x0acd }
            r6.zze(r8)     // Catch:{ all -> 0x0acd }
            r6.zzg(r10)     // Catch:{ all -> 0x0acd }
            r6.zza(r10)     // Catch:{ all -> 0x0acd }
            r6.zzb(r10)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0acd }
            r6.zzg(r8)     // Catch:{ all -> 0x0acd }
            long r8 = r3.zzj     // Catch:{ all -> 0x0acd }
            r6.zzc(r8)     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0acd }
            r6.zzh(r8)     // Catch:{ all -> 0x0acd }
            long r8 = r3.zze     // Catch:{ all -> 0x0acd }
            r6.zzd(r8)     // Catch:{ all -> 0x0acd }
            long r8 = r3.zzf     // Catch:{ all -> 0x0acd }
            r6.zze(r8)     // Catch:{ all -> 0x0acd }
            boolean r8 = r3.zzh     // Catch:{ all -> 0x0acd }
            r6.zza(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r8 = r8.zzb()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzdh     // Catch:{ all -> 0x0acd }
            boolean r8 = r8.zza(r9)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x0957
            long r8 = r3.zzl     // Catch:{ all -> 0x0acd }
            r6.zzp(r8)     // Catch:{ all -> 0x0acd }
        L_0x0957:
            long r8 = r3.zzt     // Catch:{ all -> 0x0acd }
            r6.zzf(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r8 = r30.zze()     // Catch:{ all -> 0x0acd }
            r8.zza(r6)     // Catch:{ all -> 0x0acd }
        L_0x0963:
            java.lang.String r8 = r6.zzd()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x0974
            java.lang.String r8 = r6.zzd()     // Catch:{ all -> 0x0acd }
            r5.zzi(r8)     // Catch:{ all -> 0x0acd }
        L_0x0974:
            java.lang.String r8 = r6.zzi()     // Catch:{ all -> 0x0acd }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0acd }
            if (r8 != 0) goto L_0x0985
            java.lang.String r6 = r6.zzi()     // Catch:{ all -> 0x0acd }
            r5.zzl(r6)     // Catch:{ all -> 0x0acd }
        L_0x0985:
            com.google.android.gms.measurement.internal.zzac r6 = r30.zze()     // Catch:{ all -> 0x0acd }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0acd }
            java.util.List r6 = r6.zza(r8)     // Catch:{ all -> 0x0acd }
            r8 = 0
        L_0x0990:
            int r9 = r6.size()     // Catch:{ all -> 0x0acd }
            if (r8 >= r9) goto L_0x09c7
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r9 = com.google.android.gms.internal.measurement.zzbr.zzk.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.Object r12 = r6.get(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzlb r12 = (com.google.android.gms.measurement.internal.zzlb) r12     // Catch:{ all -> 0x0acd }
            java.lang.String r12 = r12.zzc     // Catch:{ all -> 0x0acd }
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r9 = r9.zza(r12)     // Catch:{ all -> 0x0acd }
            java.lang.Object r12 = r6.get(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzlb r12 = (com.google.android.gms.measurement.internal.zzlb) r12     // Catch:{ all -> 0x0acd }
            long r12 = r12.zzd     // Catch:{ all -> 0x0acd }
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r9 = r9.zza(r12)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzkw r12 = r30.zzh()     // Catch:{ all -> 0x0acd }
            java.lang.Object r13 = r6.get(r8)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzlb r13 = (com.google.android.gms.measurement.internal.zzlb) r13     // Catch:{ all -> 0x0acd }
            java.lang.Object r13 = r13.zze     // Catch:{ all -> 0x0acd }
            r12.zza(r9, r13)     // Catch:{ all -> 0x0acd }
            r5.zza(r9)     // Catch:{ all -> 0x0acd }
            int r8 = r8 + 1
            goto L_0x0990
        L_0x09c7:
            com.google.android.gms.measurement.internal.zzac r6 = r30.zze()     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.internal.measurement.zzgo r8 = r5.zzu()     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.internal.measurement.zzfd r8 = (com.google.android.gms.internal.measurement.zzfd) r8     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.internal.measurement.zzbr$zzg r8 = (com.google.android.gms.internal.measurement.zzbr.zzg) r8     // Catch:{ IOException -> 0x0a40 }
            long r5 = r6.zza(r8)     // Catch:{ IOException -> 0x0a40 }
            com.google.android.gms.measurement.internal.zzac r8 = r30.zze()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzam r9 = r4.zze     // Catch:{ all -> 0x0acd }
            if (r9 == 0) goto L_0x0a36
            com.google.android.gms.measurement.internal.zzam r9 = r4.zze     // Catch:{ all -> 0x0acd }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0acd }
        L_0x09e7:
            boolean r12 = r9.hasNext()     // Catch:{ all -> 0x0acd }
            if (r12 == 0) goto L_0x09fb
            java.lang.Object r12 = r9.next()     // Catch:{ all -> 0x0acd }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0acd }
            boolean r12 = r2.equals(r12)     // Catch:{ all -> 0x0acd }
            if (r12 == 0) goto L_0x09fa
            goto L_0x0a37
        L_0x09fa:
            goto L_0x09e7
        L_0x09fb:
            com.google.android.gms.measurement.internal.zzgk r2 = r30.zzc()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r4.zza     // Catch:{ all -> 0x0acd }
            java.lang.String r12 = r4.zzb     // Catch:{ all -> 0x0acd }
            boolean r2 = r2.zzc(r9, r12)     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzac r12 = r30.zze()     // Catch:{ all -> 0x0acd }
            long r13 = r30.zzx()     // Catch:{ all -> 0x0acd }
            java.lang.String r15 = r4.zza     // Catch:{ all -> 0x0acd }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            com.google.android.gms.measurement.internal.zzab r9 = r12.zza(r13, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x0acd }
            if (r2 == 0) goto L_0x0a36
            long r12 = r9.zze     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r9 = r4.zza     // Catch:{ all -> 0x0acd }
            int r2 = r2.zzb(r9)     // Catch:{ all -> 0x0acd }
            long r14 = (long) r2     // Catch:{ all -> 0x0acd }
            int r2 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r2 >= 0) goto L_0x0a36
            goto L_0x0a37
        L_0x0a36:
            r7 = 0
        L_0x0a37:
            boolean r2 = r8.zza(r4, r5, r7)     // Catch:{ all -> 0x0acd }
            if (r2 == 0) goto L_0x0a5b
            r1.zzm = r10     // Catch:{ all -> 0x0acd }
            goto L_0x0a5b
        L_0x0a40:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r6 = r6.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzf()     // Catch:{ all -> 0x0acd }
            java.lang.String r7 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r5 = r5.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r5)     // Catch:{ all -> 0x0acd }
            r6.zza(r7, r5, r2)     // Catch:{ all -> 0x0acd }
        L_0x0a5b:
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()     // Catch:{ all -> 0x0acd }
            r2.mo16206b_()     // Catch:{ all -> 0x0acd }
            boolean r2 = com.google.android.gms.internal.measurement.zzkz.zzb()     // Catch:{ all -> 0x0acd }
            if (r2 == 0) goto L_0x0a78
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x0acd }
            java.lang.String r3 = r3.zza     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzap.zzcy     // Catch:{ all -> 0x0acd }
            boolean r2 = r2.zze(r3, r5)     // Catch:{ all -> 0x0acd }
            if (r2 != 0) goto L_0x0a9f
        L_0x0a78:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x0acd }
            r3 = 2
            boolean r2 = r2.zza(r3)     // Catch:{ all -> 0x0acd }
            if (r2 == 0) goto L_0x0a9f
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzx()     // Catch:{ all -> 0x0acd }
            java.lang.String r3 = "Event recorded"
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x0acd }
            com.google.android.gms.measurement.internal.zzfh r5 = r5.zzj()     // Catch:{ all -> 0x0acd }
            java.lang.String r4 = r5.zza(r4)     // Catch:{ all -> 0x0acd }
            r2.zza(r3, r4)     // Catch:{ all -> 0x0acd }
        L_0x0a9f:
            com.google.android.gms.measurement.internal.zzac r2 = r30.zze()
            r2.zzh()
            r30.zzz()
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzx()
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r23
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "Background event processing time, ms"
            r2.zza(r4, r3)
            return
        L_0x0acd:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzac r3 = r30.zze()
            r3.zzh()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.zzb(com.google.android.gms.measurement.internal.zzan, com.google.android.gms.measurement.internal.zzm):void");
    }

    /* access modifiers changed from: 0000 */
    public final void zzl() {
        int i;
        String d_;
        String str;
        Object obj;
        String str2;
        zzw();
        zzk();
        this.zzs = true;
        try {
            this.zzj.zzu();
            Boolean zzag = this.zzj.zzw().zzag();
            if (zzag == null) {
                this.zzj.zzr().zzi().zza("Upload data called on the client side before use of service was decided");
                this.zzs = false;
                zzaa();
            } else if (zzag.booleanValue()) {
                this.zzj.zzr().zzf().zza("Upload called in the client side when service should be used");
                this.zzs = false;
                zzaa();
            } else if (this.zzm > 0) {
                zzz();
                this.zzs = false;
                zzaa();
            } else {
                zzw();
                if (this.zzv != null) {
                    this.zzj.zzr().zzx().zza("Uploading requested multiple times");
                    this.zzs = false;
                    zzaa();
                } else if (!zzd().zzf()) {
                    this.zzj.zzr().zzx().zza("Network not connected, ignoring upload request");
                    zzz();
                    this.zzs = false;
                    zzaa();
                } else {
                    long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
                    if (zzlx.zzb()) {
                        i = this.zzj.zzb().zzb(null, zzap.zzap);
                    } else {
                        i = 1;
                    }
                    if (i > 1) {
                        long zzv2 = currentTimeMillis - zzx.zzv();
                        for (int i2 = 0; i2 < i && zza((String) null, zzv2); i2++) {
                        }
                    } else {
                        zza((String) null, currentTimeMillis - zzx.zzv());
                    }
                    long zza2 = this.zzj.zzc().zzc.zza();
                    if (zza2 != 0) {
                        this.zzj.zzr().zzw().zza("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - zza2)));
                    }
                    d_ = zze().mo16208d_();
                    if (!TextUtils.isEmpty(d_)) {
                        if (this.zzx == -1) {
                            this.zzx = zze().zzaa();
                        }
                        List zza3 = zze().zza(d_, this.zzj.zzb().zzb(d_, zzap.zzf), Math.max(0, this.zzj.zzb().zzb(d_, zzap.zzg)));
                        if (!zza3.isEmpty()) {
                            Iterator it = zza3.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    str = null;
                                    break;
                                }
                                zzg zzg2 = (zzg) ((Pair) it.next()).first;
                                if (!TextUtils.isEmpty(zzg2.zzad())) {
                                    str = zzg2.zzad();
                                    break;
                                }
                            }
                            if (str != null) {
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= zza3.size()) {
                                        break;
                                    }
                                    zzg zzg3 = (zzg) ((Pair) zza3.get(i3)).first;
                                    if (!TextUtils.isEmpty(zzg3.zzad()) && !zzg3.zzad().equals(str)) {
                                        zza3 = zza3.subList(0, i3);
                                        break;
                                    }
                                    i3++;
                                }
                            }
                            com.google.android.gms.internal.measurement.zzbr.zzf.zza zzb2 = zzf.zzb();
                            int size = zza3.size();
                            ArrayList arrayList = new ArrayList(zza3.size());
                            boolean zzf2 = this.zzj.zzb().zzf(d_);
                            for (int i4 = 0; i4 < size; i4++) {
                                com.google.android.gms.internal.measurement.zzbr.zzg.zza zza4 = (com.google.android.gms.internal.measurement.zzbr.zzg.zza) ((zzg) ((Pair) zza3.get(i4)).first).zzbm();
                                arrayList.add((Long) ((Pair) zza3.get(i4)).second);
                                com.google.android.gms.internal.measurement.zzbr.zzg.zza zza5 = zza4.zzg(this.zzj.zzb().zzf()).zza(currentTimeMillis);
                                this.zzj.zzu();
                                zza5.zzb(false);
                                if (!zzf2) {
                                    zza4.zzn();
                                }
                                if (this.zzj.zzb().zze(d_, zzap.zzbh)) {
                                    zza4.zzl(zzh().zza(((zzg) ((zzfd) zza4.zzu())).zzbi()));
                                }
                                zzb2.zza(zza4);
                            }
                            if (this.zzj.zzr().zza(2)) {
                                obj = zzh().zza((zzf) ((zzfd) zzb2.zzu()));
                            } else {
                                obj = null;
                            }
                            zzh();
                            byte[] zzbi = ((zzf) ((zzfd) zzb2.zzu())).zzbi();
                            str2 = (String) zzap.zzp.zza(null);
                            URL url = new URL(str2);
                            Preconditions.checkArgument(!arrayList.isEmpty());
                            if (this.zzv != null) {
                                this.zzj.zzr().zzf().zza("Set uploading progress before finishing the previous upload");
                            } else {
                                this.zzv = new ArrayList(arrayList);
                            }
                            this.zzj.zzc().zzd.zza(currentTimeMillis);
                            String str3 = "?";
                            if (size > 0) {
                                str3 = zzb2.zza(0).zzx();
                            }
                            this.zzj.zzr().zzx().zza("Uploading data. app, uncompressed size, data", str3, Integer.valueOf(zzbi.length), obj);
                            this.zzr = true;
                            zzfq zzd2 = zzd();
                            zzku zzku = new zzku(this, d_);
                            zzd2.zzd();
                            zzd2.zzak();
                            Preconditions.checkNotNull(url);
                            Preconditions.checkNotNull(zzbi);
                            Preconditions.checkNotNull(zzku);
                            zzgj zzq2 = zzd2.zzq();
                            zzfu zzfu = new zzfu(zzd2, d_, url, zzbi, null, zzku);
                            zzq2.zzb((Runnable) zzfu);
                        }
                    } else {
                        this.zzx = -1;
                        String zza6 = zze().zza(currentTimeMillis - zzx.zzv());
                        if (!TextUtils.isEmpty(zza6)) {
                            zzg zzb3 = zze().zzb(zza6);
                            if (zzb3 != null) {
                                zza(zzb3);
                            }
                        }
                    }
                    this.zzs = false;
                    zzaa();
                }
            }
        } catch (MalformedURLException e) {
            this.zzj.zzr().zzf().zza("Failed to parse upload URL. Not uploading. appId", zzfj.zza(d_), str2);
        } catch (Throwable th) {
            this.zzs = false;
            zzaa();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0254, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0255, code lost:
        r6 = r4;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        r2 = r0;
        r8 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        r6 = null;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:587:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0044 A[ExcHandler: all (r0v13 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r4 
      PHI: (r4v51 android.database.Cursor) = (r4v46 android.database.Cursor), (r4v46 android.database.Cursor), (r4v46 android.database.Cursor), (r4v57 android.database.Cursor), (r4v57 android.database.Cursor), (r4v57 android.database.Cursor), (r4v57 android.database.Cursor), (r4v0 android.database.Cursor), (r4v0 android.database.Cursor) binds: [B:46:0x00e4, B:52:0x00f1, B:53:?, B:23:0x0082, B:29:0x008f, B:31:0x0093, B:32:?, B:9:0x0035, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0275 A[SYNTHETIC, Splitter:B:125:0x0275] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x027d A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x028b A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03cd A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03d8 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x03d9 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05d7 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x06aa A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x06c1 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0860 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:326:0x0874 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:327:0x088f A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:463:0x0c7a A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:464:0x0c8e A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:466:0x0c91 A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:467:0x0cbe A[Catch:{ IOException -> 0x0232, all -> 0x1090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:579:0x1077 A[SYNTHETIC, Splitter:B:579:0x1077] */
    /* JADX WARNING: Removed duplicated region for block: B:586:0x108c A[SYNTHETIC, Splitter:B:586:0x108c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r59, long r60) {
        /*
            r58 = this;
            r1 = r58
            java.lang.String r2 = ""
            com.google.android.gms.measurement.internal.zzac r3 = r58.zze()
            r3.zzf()
            com.google.android.gms.measurement.internal.zzks$zza r3 = new com.google.android.gms.measurement.internal.zzks$zza     // Catch:{ all -> 0x1090 }
            r4 = 0
            r3.<init>(r1, r4)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzac r5 = r58.zze()     // Catch:{ all -> 0x1090 }
            long r6 = r1.zzx     // Catch:{ all -> 0x1090 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x1090 }
            r5.zzd()     // Catch:{ all -> 0x1090 }
            r5.zzak()     // Catch:{ all -> 0x1090 }
            r9 = -1
            r11 = 2
            r12 = 0
            r13 = 1
            android.database.sqlite.SQLiteDatabase r15 = r5.mo16207c_()     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            boolean r14 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            if (r14 == 0) goto L_0x00a3
            int r14 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r14 == 0) goto L_0x004f
            java.lang.String[] r8 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0049, all -> 0x0044 }
            java.lang.String r16 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x0049, all -> 0x0044 }
            r8[r12] = r16     // Catch:{ SQLiteException -> 0x0049, all -> 0x0044 }
            java.lang.String r16 = java.lang.String.valueOf(r60)     // Catch:{ SQLiteException -> 0x0049, all -> 0x0044 }
            r8[r13] = r16     // Catch:{ SQLiteException -> 0x0049, all -> 0x0044 }
            goto L_0x0057
        L_0x0044:
            r0 = move-exception
            r2 = r0
            r8 = r4
            goto L_0x108a
        L_0x0049:
            r0 = move-exception
            r6 = r4
            r8 = r6
        L_0x004c:
            r4 = r0
            goto L_0x0262
        L_0x004f:
            java.lang.String[] r8 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r16 = java.lang.String.valueOf(r60)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r8[r12] = r16     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
        L_0x0057:
            if (r14 == 0) goto L_0x005d
            java.lang.String r14 = "rowid <= ? and "
            goto L_0x005e
        L_0x005d:
            r14 = r2
        L_0x005e:
            java.lang.String r16 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            int r4 = r16.length()     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            int r4 = r4 + 148
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r11.<init>(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r4 = "select app_id, metadata_fingerprint from raw_events where "
            r11.append(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r11.append(r14)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r4 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r11.append(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r4 = r11.toString()     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            android.database.Cursor r4 = r15.rawQuery(r4, r8)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            boolean r8 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0044 }
            if (r8 != 0) goto L_0x008f
            if (r4 == 0) goto L_0x008d
            r4.close()     // Catch:{ all -> 0x1090 }
        L_0x008d:
            goto L_0x0278
        L_0x008f:
            java.lang.String r8 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x0254, all -> 0x0044 }
            java.lang.String r11 = r4.getString(r13)     // Catch:{ SQLiteException -> 0x00a0, all -> 0x0044 }
            r4.close()     // Catch:{ SQLiteException -> 0x00a0, all -> 0x0044 }
            r57 = r8
            r8 = r4
            r4 = r57
            goto L_0x00fa
        L_0x00a0:
            r0 = move-exception
            r6 = r4
            goto L_0x004c
        L_0x00a3:
            int r4 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r4 == 0) goto L_0x00b4
            r8 = 2
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r8 = 0
            r11[r12] = r8     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r8 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r11[r13] = r8     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            goto L_0x00b9
        L_0x00b4:
            r8 = 0
            java.lang.String[] r11 = new java.lang.String[]{r8}     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
        L_0x00b9:
            if (r4 == 0) goto L_0x00bf
            java.lang.String r4 = " and rowid <= ?"
            goto L_0x00c0
        L_0x00bf:
            r4 = r2
        L_0x00c0:
            java.lang.String r8 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            int r8 = r8.length()     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            int r8 = r8 + 84
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r14.<init>(r8)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r8 = "select metadata_fingerprint from raw_events where app_id = ?"
            r14.append(r8)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r14.append(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r4 = " order by rowid limit 1;"
            r14.append(r4)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r4 = r14.toString()     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            android.database.Cursor r4 = r15.rawQuery(r4, r11)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            boolean r8 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0044 }
            if (r8 != 0) goto L_0x00f1
            if (r4 == 0) goto L_0x00ef
            r4.close()     // Catch:{ all -> 0x1090 }
        L_0x00ef:
            goto L_0x0278
        L_0x00f1:
            java.lang.String r11 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x0254, all -> 0x0044 }
            r4.close()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0044 }
            r8 = r4
            r4 = 0
        L_0x00fa:
            java.lang.String r16 = "raw_events_metadata"
            java.lang.String r14 = "metadata"
            java.lang.String[] r17 = new java.lang.String[]{r14}     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            java.lang.String r18 = "app_id = ? and metadata_fingerprint = ?"
            r14 = 2
            java.lang.String[] r9 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r9[r12] = r4     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r9[r13] = r11     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r19 = 0
            r20 = 0
            java.lang.String r21 = "rowid"
            java.lang.String r22 = "2"
            r14 = r15
            r10 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r18 = r9
            android.database.Cursor r8 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            boolean r9 = r8.moveToFirst()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            if (r9 != 0) goto L_0x013f
            com.google.android.gms.measurement.internal.zzfj r6 = r5.zzr()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            com.google.android.gms.measurement.internal.zzfl r6 = r6.zzf()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            java.lang.String r7 = "Raw event metadata record is missing. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r4)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r6.zza(r7, r9)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            if (r8 == 0) goto L_0x013d
            r8.close()     // Catch:{ all -> 0x1090 }
        L_0x013d:
            goto L_0x0278
        L_0x013f:
            byte[] r9 = r8.getBlob(r12)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r14 = com.google.android.gms.internal.measurement.zzbr.zzg.zzbf()     // Catch:{ IOException -> 0x0232 }
            com.google.android.gms.internal.measurement.zzgn r9 = com.google.android.gms.measurement.internal.zzkw.zza(r14, r9)     // Catch:{ IOException -> 0x0232 }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r9 = (com.google.android.gms.internal.measurement.zzbr.zzg.zza) r9     // Catch:{ IOException -> 0x0232 }
            com.google.android.gms.internal.measurement.zzgo r9 = r9.zzu()     // Catch:{ IOException -> 0x0232 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ IOException -> 0x0232 }
            com.google.android.gms.internal.measurement.zzbr$zzg r9 = (com.google.android.gms.internal.measurement.zzbr.zzg) r9     // Catch:{ IOException -> 0x0232 }
            boolean r14 = r8.moveToNext()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            if (r14 == 0) goto L_0x016d
            com.google.android.gms.measurement.internal.zzfj r14 = r5.zzr()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            com.google.android.gms.measurement.internal.zzfl r14 = r14.zzi()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            java.lang.String r15 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfj.zza(r4)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r14.zza(r15, r13)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
        L_0x016d:
            r8.close()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r3.zza(r9)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r13 = -1
            int r9 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r9 == 0) goto L_0x018f
            java.lang.String r9 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r13 = 3
            java.lang.String[] r14 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r14[r12] = r4     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r13 = 1
            r14[r13] = r11     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r7 = 2
            r14[r7] = r6     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r17 = r9
            r18 = r14
            goto L_0x019d
        L_0x018f:
            java.lang.String r6 = "app_id = ? and metadata_fingerprint = ?"
            r7 = 2
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r9[r12] = r4     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r7 = 1
            r9[r7] = r11     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r17 = r6
            r18 = r9
        L_0x019d:
            java.lang.String r15 = "raw_events"
            java.lang.String r6 = "rowid"
            java.lang.String r7 = "name"
            java.lang.String r9 = "timestamp"
            java.lang.String r11 = "data"
            java.lang.String[] r16 = new java.lang.String[]{r6, r7, r9, r11}     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r19 = 0
            r20 = 0
            java.lang.String r21 = "rowid"
            r22 = 0
            r14 = r10
            android.database.Cursor r6 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            boolean r7 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x0230 }
            if (r7 != 0) goto L_0x01d6
            com.google.android.gms.measurement.internal.zzfj r7 = r5.zzr()     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzi()     // Catch:{ SQLiteException -> 0x0230 }
            java.lang.String r8 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r4)     // Catch:{ SQLiteException -> 0x0230 }
            r7.zza(r8, r9)     // Catch:{ SQLiteException -> 0x0230 }
            if (r6 == 0) goto L_0x01d4
            r6.close()     // Catch:{ all -> 0x1090 }
        L_0x01d4:
            goto L_0x0278
        L_0x01d6:
            long r7 = r6.getLong(r12)     // Catch:{ SQLiteException -> 0x0230 }
            r9 = 3
            byte[] r10 = r6.getBlob(r9)     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r9 = com.google.android.gms.internal.measurement.zzbr.zzc.zzj()     // Catch:{ IOException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzgn r9 = com.google.android.gms.measurement.internal.zzkw.zza(r9, r10)     // Catch:{ IOException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r9 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r9     // Catch:{ IOException -> 0x0210 }
            r10 = 1
            java.lang.String r11 = r6.getString(r10)     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r10 = r9.zza(r11)     // Catch:{ SQLiteException -> 0x0230 }
            r11 = 2
            long r13 = r6.getLong(r11)     // Catch:{ SQLiteException -> 0x0230 }
            r10.zza(r13)     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.internal.measurement.zzgo r9 = r9.zzu()     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.internal.measurement.zzbr$zzc r9 = (com.google.android.gms.internal.measurement.zzbr.zzc) r9     // Catch:{ SQLiteException -> 0x0230 }
            boolean r7 = r3.zza(r7, r9)     // Catch:{ SQLiteException -> 0x0230 }
            if (r7 != 0) goto L_0x0224
            if (r6 == 0) goto L_0x020e
            r6.close()     // Catch:{ all -> 0x1090 }
        L_0x020e:
            goto L_0x0278
        L_0x0210:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.measurement.internal.zzfj r8 = r5.zzr()     // Catch:{ SQLiteException -> 0x0230 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzf()     // Catch:{ SQLiteException -> 0x0230 }
            java.lang.String r9 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfj.zza(r4)     // Catch:{ SQLiteException -> 0x0230 }
            r8.zza(r9, r10, r7)     // Catch:{ SQLiteException -> 0x0230 }
        L_0x0224:
            boolean r7 = r6.moveToNext()     // Catch:{ SQLiteException -> 0x0230 }
            if (r7 != 0) goto L_0x01d6
            if (r6 == 0) goto L_0x0278
            r6.close()     // Catch:{ all -> 0x1090 }
            goto L_0x0278
        L_0x0230:
            r0 = move-exception
            goto L_0x0251
        L_0x0232:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzfj r7 = r5.zzr()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzf()     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            java.lang.String r9 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfj.zza(r4)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            r7.zza(r9, r10, r6)     // Catch:{ SQLiteException -> 0x024f, all -> 0x024b }
            if (r8 == 0) goto L_0x024a
            r8.close()     // Catch:{ all -> 0x1090 }
        L_0x024a:
            goto L_0x0278
        L_0x024b:
            r0 = move-exception
            r2 = r0
            goto L_0x108a
        L_0x024f:
            r0 = move-exception
            r6 = r8
        L_0x0251:
            r8 = r4
            goto L_0x004c
        L_0x0254:
            r0 = move-exception
            r6 = r4
            r8 = 0
            goto L_0x004c
        L_0x0259:
            r0 = move-exception
            r2 = r0
            r8 = 0
            goto L_0x108a
        L_0x025e:
            r0 = move-exception
            r4 = r0
            r6 = 0
            r8 = 0
        L_0x0262:
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ all -> 0x1087 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzf()     // Catch:{ all -> 0x1087 }
            java.lang.String r7 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfj.zza(r8)     // Catch:{ all -> 0x1087 }
            r5.zza(r7, r8, r4)     // Catch:{ all -> 0x1087 }
            if (r6 == 0) goto L_0x0278
            r6.close()     // Catch:{ all -> 0x1090 }
        L_0x0278:
            java.util.List<com.google.android.gms.internal.measurement.zzbr$zzc> r4 = r3.zzc     // Catch:{ all -> 0x1090 }
            if (r4 == 0) goto L_0x0288
            java.util.List<com.google.android.gms.internal.measurement.zzbr$zzc> r4 = r3.zzc     // Catch:{ all -> 0x1090 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x1090 }
            if (r4 == 0) goto L_0x0286
            goto L_0x0288
        L_0x0286:
            r4 = 0
            goto L_0x0289
        L_0x0288:
            r4 = 1
        L_0x0289:
            if (r4 != 0) goto L_0x1077
            com.google.android.gms.internal.measurement.zzbr$zzg r4 = r3.zza     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r4 = r4.zzbm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r4 = (com.google.android.gms.internal.measurement.zzfd.zzb) r4     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r4 = (com.google.android.gms.internal.measurement.zzbr.zzg.zza) r4     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r4 = r4.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r5 = r5.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r6 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzbc     // Catch:{ all -> 0x1090 }
            boolean r5 = r5.zze(r6, r7)     // Catch:{ all -> 0x1090 }
            r8 = 0
            r9 = 0
            r12 = -1
            r13 = 0
            r14 = 0
            r15 = -1
            r17 = 0
            r18 = 0
        L_0x02be:
            java.util.List<com.google.android.gms.internal.measurement.zzbr$zzc> r6 = r3.zzc     // Catch:{ all -> 0x1090 }
            int r6 = r6.size()     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = "_et"
            java.lang.String r11 = "_e"
            r20 = r13
            r21 = r14
            if (r8 >= r6) goto L_0x08e9
            java.util.List<com.google.android.gms.internal.measurement.zzbr$zzc> r6 = r3.zzc     // Catch:{ all -> 0x1090 }
            java.lang.Object r6 = r6.get(r8)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r6 = (com.google.android.gms.internal.measurement.zzbr.zzc) r6     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r6 = r6.zzbm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r6 = (com.google.android.gms.internal.measurement.zzfd.zzb) r6     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r6 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r6     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgk r13 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r14 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzx()     // Catch:{ all -> 0x1090 }
            r22 = r2
            java.lang.String r2 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r13.zzb(r14, r2)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0373
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r11 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r11 = r11.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfj.zza(r11)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r13 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfh r13 = r13.zzj()     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r6.zzd()     // Catch:{ all -> 0x1090 }
            java.lang.String r13 = r13.zza(r14)     // Catch:{ all -> 0x1090 }
            r2.zza(r7, r11, r13)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgk r2 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r7 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zzg(r7)     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x0341
            com.google.android.gms.measurement.internal.zzgk r2 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r7 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zzh(r7)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x033f
            goto L_0x0341
        L_0x033f:
            r2 = 0
            goto L_0x0342
        L_0x0341:
            r2 = 1
        L_0x0342:
            if (r2 != 0) goto L_0x036a
            java.lang.String r2 = "_err"
            java.lang.String r7 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.equals(r7)     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x036a
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzla r23 = r2.zzi()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r2 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r24 = r2.zzx()     // Catch:{ all -> 0x1090 }
            r25 = 11
            java.lang.String r26 = "_ev"
            java.lang.String r27 = r6.zzd()     // Catch:{ all -> 0x1090 }
            r28 = 0
            r23.zza(r24, r25, r26, r27, r28)     // Catch:{ all -> 0x1090 }
        L_0x036a:
            r28 = r5
            r7 = r8
            r13 = r9
            r2 = r21
            r10 = 3
            goto L_0x08dd
        L_0x0373:
            com.google.android.gms.measurement.internal.zzgk r2 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r13 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r13 = r13.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zzc(r13, r14)     // Catch:{ all -> 0x1090 }
            java.lang.String r13 = "_c"
            if (r2 != 0) goto L_0x03df
            r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r6.zzd()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)     // Catch:{ all -> 0x1090 }
            r25 = r8
            int r8 = r14.hashCode()     // Catch:{ all -> 0x1090 }
            r26 = r9
            r9 = 94660(0x171c4, float:1.32647E-40)
            if (r8 == r9) goto L_0x03c0
            r9 = 95025(0x17331, float:1.33158E-40)
            if (r8 == r9) goto L_0x03b6
            r9 = 95027(0x17333, float:1.33161E-40)
            if (r8 == r9) goto L_0x03ac
        L_0x03ab:
            goto L_0x03ca
        L_0x03ac:
            java.lang.String r8 = "_ui"
            boolean r8 = r14.equals(r8)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x03ab
            r8 = 1
            goto L_0x03cb
        L_0x03b6:
            java.lang.String r8 = "_ug"
            boolean r8 = r14.equals(r8)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x03ab
            r8 = 2
            goto L_0x03cb
        L_0x03c0:
            java.lang.String r8 = "_in"
            boolean r8 = r14.equals(r8)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x03ab
            r8 = 0
            goto L_0x03cb
        L_0x03ca:
            r8 = -1
        L_0x03cb:
            if (r8 == 0) goto L_0x03d5
            r9 = 1
            if (r8 == r9) goto L_0x03d5
            r9 = 2
            if (r8 == r9) goto L_0x03d5
            r8 = 0
            goto L_0x03d6
        L_0x03d5:
            r8 = 1
        L_0x03d6:
            if (r8 == 0) goto L_0x03d9
            goto L_0x03e3
        L_0x03d9:
            r28 = r5
            r29 = r15
            goto L_0x05d5
        L_0x03df:
            r25 = r8
            r26 = r9
        L_0x03e3:
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x03e8:
            int r14 = r6.zzb()     // Catch:{ all -> 0x1090 }
            r28 = r5
            java.lang.String r5 = "_r"
            if (r8 >= r14) goto L_0x045a
            com.google.android.gms.internal.measurement.zzbr$zze r14 = r6.zza(r8)     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzb()     // Catch:{ all -> 0x1090 }
            boolean r14 = r13.equals(r14)     // Catch:{ all -> 0x1090 }
            if (r14 == 0) goto L_0x0423
            com.google.android.gms.internal.measurement.zzbr$zze r5 = r6.zza(r8)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r5 = r5.zzbm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r5 = (com.google.android.gms.internal.measurement.zzfd.zzb) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = (com.google.android.gms.internal.measurement.zzbr.zze.zza) r5     // Catch:{ all -> 0x1090 }
            r29 = r15
            r14 = 1
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = r5.zza(r14)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r5 = r5.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r5 = (com.google.android.gms.internal.measurement.zzfd) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r5 = (com.google.android.gms.internal.measurement.zzbr.zze) r5     // Catch:{ all -> 0x1090 }
            r6.zza(r8, r5)     // Catch:{ all -> 0x1090 }
            r9 = 1
            goto L_0x0453
        L_0x0423:
            r29 = r15
            com.google.android.gms.internal.measurement.zzbr$zze r14 = r6.zza(r8)     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzb()     // Catch:{ all -> 0x1090 }
            boolean r5 = r5.equals(r14)     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0453
            com.google.android.gms.internal.measurement.zzbr$zze r5 = r6.zza(r8)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r5 = r5.zzbm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r5 = (com.google.android.gms.internal.measurement.zzfd.zzb) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = (com.google.android.gms.internal.measurement.zzbr.zze.zza) r5     // Catch:{ all -> 0x1090 }
            r14 = 1
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = r5.zza(r14)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r5 = r5.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r5 = (com.google.android.gms.internal.measurement.zzfd) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r5 = (com.google.android.gms.internal.measurement.zzbr.zze) r5     // Catch:{ all -> 0x1090 }
            r6.zza(r8, r5)     // Catch:{ all -> 0x1090 }
            r10 = 1
        L_0x0453:
            int r8 = r8 + 1
            r5 = r28
            r15 = r29
            goto L_0x03e8
        L_0x045a:
            r29 = r15
            if (r9 != 0) goto L_0x0490
            if (r2 == 0) goto L_0x0490
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r8 = r8.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzgq r14 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfh r14 = r14.zzj()     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = r6.zzd()     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zza(r15)     // Catch:{ all -> 0x1090 }
            r8.zza(r9, r14)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r8 = com.google.android.gms.internal.measurement.zzbr.zze.zzk()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r8 = r8.zza(r13)     // Catch:{ all -> 0x1090 }
            r14 = 1
            com.google.android.gms.internal.measurement.zzbr$zze$zza r8 = r8.zza(r14)     // Catch:{ all -> 0x1090 }
            r6.zza(r8)     // Catch:{ all -> 0x1090 }
        L_0x0490:
            if (r10 != 0) goto L_0x04c2
            com.google.android.gms.measurement.internal.zzgq r8 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r8 = r8.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzgq r10 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfh r10 = r10.zzj()     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r6.zzd()     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = r10.zza(r14)     // Catch:{ all -> 0x1090 }
            r8.zza(r9, r10)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r8 = com.google.android.gms.internal.measurement.zzbr.zze.zzk()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r8 = r8.zza(r5)     // Catch:{ all -> 0x1090 }
            r9 = 1
            com.google.android.gms.internal.measurement.zzbr$zze$zza r8 = r8.zza(r9)     // Catch:{ all -> 0x1090 }
            r6.zza(r8)     // Catch:{ all -> 0x1090 }
        L_0x04c2:
            com.google.android.gms.measurement.internal.zzac r30 = r58.zze()     // Catch:{ all -> 0x1090 }
            long r31 = r58.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r8 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r33 = r8.zzx()     // Catch:{ all -> 0x1090 }
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 1
            com.google.android.gms.measurement.internal.zzab r8 = r30.zza(r31, r33, r34, r35, r36, r37, r38)     // Catch:{ all -> 0x1090 }
            long r8 = r8.zze     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r10 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r10 = r10.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r14 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzx()     // Catch:{ all -> 0x1090 }
            int r10 = r10.zzb(r14)     // Catch:{ all -> 0x1090 }
            long r14 = (long) r10     // Catch:{ all -> 0x1090 }
            int r10 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x04fb
            zza(r6, r5)     // Catch:{ all -> 0x1090 }
            goto L_0x04fd
        L_0x04fb:
            r20 = 1
        L_0x04fd:
            java.lang.String r5 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r5 = com.google.android.gms.measurement.internal.zzla.zza(r5)     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x05d5
            if (r2 == 0) goto L_0x05d5
            com.google.android.gms.measurement.internal.zzac r30 = r58.zze()     // Catch:{ all -> 0x1090 }
            long r31 = r58.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r5 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r33 = r5.zzx()     // Catch:{ all -> 0x1090 }
            r34 = 0
            r35 = 0
            r36 = 1
            r37 = 0
            r38 = 0
            com.google.android.gms.measurement.internal.zzab r5 = r30.zza(r31, r33, r34, r35, r36, r37, r38)     // Catch:{ all -> 0x1090 }
            long r8 = r5.zzc     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r5 = r5.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r10 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = r10.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Integer> r14 = com.google.android.gms.measurement.internal.zzap.zzm     // Catch:{ all -> 0x1090 }
            int r5 = r5.zzb(r10, r14)     // Catch:{ all -> 0x1090 }
            long r14 = (long) r5     // Catch:{ all -> 0x1090 }
            int r5 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r5 <= 0) goto L_0x05d5
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r9 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = r9.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r9)     // Catch:{ all -> 0x1090 }
            r5.zza(r8, r9)     // Catch:{ all -> 0x1090 }
            r5 = 0
            r8 = 0
            r9 = 0
            r10 = -1
        L_0x0560:
            int r14 = r6.zzb()     // Catch:{ all -> 0x1090 }
            if (r5 >= r14) goto L_0x058f
            com.google.android.gms.internal.measurement.zzbr$zze r14 = r6.zza(r5)     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = r14.zzb()     // Catch:{ all -> 0x1090 }
            boolean r15 = r13.equals(r15)     // Catch:{ all -> 0x1090 }
            if (r15 == 0) goto L_0x057f
            com.google.android.gms.internal.measurement.zzfd$zzb r9 = r14.zzbm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r9 = (com.google.android.gms.internal.measurement.zzfd.zzb) r9     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r9 = (com.google.android.gms.internal.measurement.zzbr.zze.zza) r9     // Catch:{ all -> 0x1090 }
            r10 = r5
            goto L_0x058c
        L_0x057f:
            java.lang.String r15 = "_err"
            java.lang.String r14 = r14.zzb()     // Catch:{ all -> 0x1090 }
            boolean r14 = r15.equals(r14)     // Catch:{ all -> 0x1090 }
            if (r14 == 0) goto L_0x058c
            r8 = 1
        L_0x058c:
            int r5 = r5 + 1
            goto L_0x0560
        L_0x058f:
            if (r8 == 0) goto L_0x0598
            if (r9 == 0) goto L_0x0598
            r6.zzb(r10)     // Catch:{ all -> 0x1090 }
            goto L_0x05d5
        L_0x0598:
            if (r9 == 0) goto L_0x05bb
            java.lang.Object r5 = r9.clone()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r5 = (com.google.android.gms.internal.measurement.zzfd.zzb) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = (com.google.android.gms.internal.measurement.zzbr.zze.zza) r5     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = "_err"
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = r5.zza(r8)     // Catch:{ all -> 0x1090 }
            r8 = 10
            com.google.android.gms.internal.measurement.zzbr$zze$zza r5 = r5.zza(r8)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r5 = r5.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r5 = (com.google.android.gms.internal.measurement.zzfd) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r5 = (com.google.android.gms.internal.measurement.zzbr.zze) r5     // Catch:{ all -> 0x1090 }
            r6.zza(r10, r5)     // Catch:{ all -> 0x1090 }
            goto L_0x05d5
        L_0x05bb:
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r9 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = r9.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfj.zza(r9)     // Catch:{ all -> 0x1090 }
            r5.zza(r8, r9)     // Catch:{ all -> 0x1090 }
        L_0x05d5:
            if (r2 == 0) goto L_0x06aa
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x1090 }
            java.util.List r5 = r6.zza()     // Catch:{ all -> 0x1090 }
            r2.<init>(r5)     // Catch:{ all -> 0x1090 }
            r5 = 0
            r8 = -1
            r9 = -1
        L_0x05e6:
            int r10 = r2.size()     // Catch:{ all -> 0x1090 }
            if (r5 >= r10) goto L_0x0616
            java.lang.String r10 = "value"
            java.lang.Object r14 = r2.get(r5)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r14 = (com.google.android.gms.internal.measurement.zzbr.zze) r14     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzb()     // Catch:{ all -> 0x1090 }
            boolean r10 = r10.equals(r14)     // Catch:{ all -> 0x1090 }
            if (r10 == 0) goto L_0x0600
            r8 = r5
            goto L_0x0613
        L_0x0600:
            java.lang.String r10 = "currency"
            java.lang.Object r14 = r2.get(r5)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r14 = (com.google.android.gms.internal.measurement.zzbr.zze) r14     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzb()     // Catch:{ all -> 0x1090 }
            boolean r10 = r10.equals(r14)     // Catch:{ all -> 0x1090 }
            if (r10 == 0) goto L_0x0613
            r9 = r5
        L_0x0613:
            int r5 = r5 + 1
            goto L_0x05e6
        L_0x0616:
            r5 = -1
            if (r8 == r5) goto L_0x06a8
            java.lang.Object r5 = r2.get(r8)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r5 = (com.google.android.gms.internal.measurement.zzbr.zze) r5     // Catch:{ all -> 0x1090 }
            boolean r5 = r5.zze()     // Catch:{ all -> 0x1090 }
            if (r5 != 0) goto L_0x0652
            java.lang.Object r5 = r2.get(r8)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r5 = (com.google.android.gms.internal.measurement.zzbr.zze) r5     // Catch:{ all -> 0x1090 }
            boolean r5 = r5.zzg()     // Catch:{ all -> 0x1090 }
            if (r5 != 0) goto L_0x0652
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzk()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "Value must be specified with a numeric type."
            r2.zza(r5)     // Catch:{ all -> 0x1090 }
            r6.zzb(r8)     // Catch:{ all -> 0x1090 }
            zza(r6, r13)     // Catch:{ all -> 0x1090 }
            r2 = 18
            java.lang.String r5 = "value"
            zza(r6, r2, r5)     // Catch:{ all -> 0x1090 }
            r5 = -1
            r10 = 3
            goto L_0x06ac
        L_0x0652:
            r5 = -1
            if (r9 != r5) goto L_0x0659
            r2 = 1
            r10 = 3
            goto L_0x0687
        L_0x0659:
            java.lang.Object r2 = r2.get(r9)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r2 = (com.google.android.gms.internal.measurement.zzbr.zze) r2     // Catch:{ all -> 0x1090 }
            java.lang.String r2 = r2.zzd()     // Catch:{ all -> 0x1090 }
            int r9 = r2.length()     // Catch:{ all -> 0x1090 }
            r10 = 3
            if (r9 == r10) goto L_0x066c
            r2 = 1
            goto L_0x0687
        L_0x066c:
            r9 = 0
        L_0x066d:
            int r14 = r2.length()     // Catch:{ all -> 0x1090 }
            if (r9 >= r14) goto L_0x0686
            int r14 = r2.codePointAt(r9)     // Catch:{ all -> 0x1090 }
            boolean r15 = java.lang.Character.isLetter(r14)     // Catch:{ all -> 0x1090 }
            if (r15 != 0) goto L_0x0680
            r2 = 1
            goto L_0x0687
        L_0x0680:
            int r14 = java.lang.Character.charCount(r14)     // Catch:{ all -> 0x1090 }
            int r9 = r9 + r14
            goto L_0x066d
        L_0x0686:
            r2 = 0
        L_0x0687:
            if (r2 == 0) goto L_0x06ac
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzk()     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r9)     // Catch:{ all -> 0x1090 }
            r6.zzb(r8)     // Catch:{ all -> 0x1090 }
            zza(r6, r13)     // Catch:{ all -> 0x1090 }
            r2 = 19
            java.lang.String r8 = "currency"
            zza(r6, r2, r8)     // Catch:{ all -> 0x1090 }
            goto L_0x06ac
        L_0x06a8:
            r10 = 3
            goto L_0x06ac
        L_0x06aa:
            r5 = -1
            r10 = 3
        L_0x06ac:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r8 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = r8.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzap.zzbb     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r8, r9)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0860
            java.lang.String r2 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r11.equals(r2)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x071d
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r2 = r6.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r2 = (com.google.android.gms.internal.measurement.zzfd) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r2 = (com.google.android.gms.internal.measurement.zzbr.zzc) r2     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = "_fr"
            com.google.android.gms.internal.measurement.zzbr$zze r2 = com.google.android.gms.measurement.internal.zzkw.zza(r2, r8)     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x0719
            if (r18 == 0) goto L_0x0711
            long r8 = r18.zzf()     // Catch:{ all -> 0x1090 }
            long r13 = r6.zzf()     // Catch:{ all -> 0x1090 }
            long r8 = r8 - r13
            long r8 = java.lang.Math.abs(r8)     // Catch:{ all -> 0x1090 }
            r13 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r2 > 0) goto L_0x0711
            java.lang.Object r2 = r18.clone()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r2 = (com.google.android.gms.internal.measurement.zzfd.zzb) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r2     // Catch:{ all -> 0x1090 }
            boolean r8 = r1.zza(r6, r2)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x070e
            r4.zza(r12, r2)     // Catch:{ all -> 0x1090 }
            r8 = r29
            r17 = 0
            r18 = 0
            goto L_0x0862
        L_0x070e:
            goto L_0x0713
        L_0x0711:
        L_0x0713:
            r17 = r6
            r8 = r21
            goto L_0x0862
        L_0x0719:
            r8 = r29
            goto L_0x0862
        L_0x071d:
            java.lang.String r2 = "_vs"
            java.lang.String r8 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.equals(r8)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x077f
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r2 = r6.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r2 = (com.google.android.gms.internal.measurement.zzfd) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r2 = (com.google.android.gms.internal.measurement.zzbr.zzc) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r2 = com.google.android.gms.measurement.internal.zzkw.zza(r2, r7)     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x077b
            if (r17 == 0) goto L_0x0771
            long r8 = r17.zzf()     // Catch:{ all -> 0x1090 }
            long r13 = r6.zzf()     // Catch:{ all -> 0x1090 }
            long r8 = r8 - r13
            long r8 = java.lang.Math.abs(r8)     // Catch:{ all -> 0x1090 }
            r13 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r2 > 0) goto L_0x076e
            java.lang.Object r2 = r17.clone()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r2 = (com.google.android.gms.internal.measurement.zzfd.zzb) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r2     // Catch:{ all -> 0x1090 }
            boolean r8 = r1.zza(r2, r6)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x076a
            r8 = r29
            r4.zza(r8, r2)     // Catch:{ all -> 0x1090 }
            r17 = 0
            r18 = 0
            goto L_0x0862
        L_0x076a:
            r8 = r29
            goto L_0x0775
        L_0x076e:
            r8 = r29
            goto L_0x0773
        L_0x0771:
            r8 = r29
        L_0x0773:
        L_0x0775:
            r18 = r6
            r12 = r21
            goto L_0x0862
        L_0x077b:
            r8 = r29
            goto L_0x0862
        L_0x077f:
            r8 = r29
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r9 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = r9.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r13 = com.google.android.gms.measurement.internal.zzap.zzcl     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r9, r13)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0862
            java.lang.String r2 = "_ab"
            java.lang.String r9 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.equals(r9)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0862
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r2 = r6.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r2 = (com.google.android.gms.internal.measurement.zzfd) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r2 = (com.google.android.gms.internal.measurement.zzbr.zzc) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r2 = com.google.android.gms.measurement.internal.zzkw.zza(r2, r7)     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x0862
            if (r17 == 0) goto L_0x0862
            long r13 = r17.zzf()     // Catch:{ all -> 0x1090 }
            long r23 = r6.zzf()     // Catch:{ all -> 0x1090 }
            long r13 = r13 - r23
            long r13 = java.lang.Math.abs(r13)     // Catch:{ all -> 0x1090 }
            r23 = 4000(0xfa0, double:1.9763E-320)
            int r2 = (r13 > r23 ? 1 : (r13 == r23 ? 0 : -1))
            if (r2 > 0) goto L_0x0862
            java.lang.Object r2 = r17.clone()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r2 = (com.google.android.gms.internal.measurement.zzfd.zzb) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r2     // Catch:{ all -> 0x1090 }
            r1.zzb(r2, r6)     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = r2.zzd()     // Catch:{ all -> 0x1090 }
            boolean r9 = r11.equals(r9)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r9)     // Catch:{ all -> 0x1090 }
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r9 = r2.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r9 = (com.google.android.gms.internal.measurement.zzbr.zzc) r9     // Catch:{ all -> 0x1090 }
            java.lang.String r13 = "_sn"
            com.google.android.gms.internal.measurement.zzbr$zze r9 = com.google.android.gms.measurement.internal.zzkw.zza(r9, r13)     // Catch:{ all -> 0x1090 }
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r13 = r2.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r13 = (com.google.android.gms.internal.measurement.zzfd) r13     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r13 = (com.google.android.gms.internal.measurement.zzbr.zzc) r13     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = "_sc"
            com.google.android.gms.internal.measurement.zzbr$zze r13 = com.google.android.gms.measurement.internal.zzkw.zza(r13, r14)     // Catch:{ all -> 0x1090 }
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r14 = r2.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r14 = (com.google.android.gms.internal.measurement.zzfd) r14     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r14 = (com.google.android.gms.internal.measurement.zzbr.zzc) r14     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = "_si"
            com.google.android.gms.internal.measurement.zzbr$zze r14 = com.google.android.gms.measurement.internal.zzkw.zza(r14, r15)     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x081d
            java.lang.String r9 = r9.zzd()     // Catch:{ all -> 0x1090 }
            goto L_0x081f
        L_0x081d:
            r9 = r22
        L_0x081f:
            boolean r15 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x1090 }
            if (r15 != 0) goto L_0x082e
            com.google.android.gms.measurement.internal.zzkw r15 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "_sn"
            r15.zza(r6, r5, r9)     // Catch:{ all -> 0x1090 }
        L_0x082e:
            if (r13 == 0) goto L_0x0835
            java.lang.String r5 = r13.zzd()     // Catch:{ all -> 0x1090 }
            goto L_0x0837
        L_0x0835:
            r5 = r22
        L_0x0837:
            boolean r9 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x1090 }
            if (r9 != 0) goto L_0x0846
            com.google.android.gms.measurement.internal.zzkw r9 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r13 = "_sc"
            r9.zza(r6, r13, r5)     // Catch:{ all -> 0x1090 }
        L_0x0846:
            if (r14 == 0) goto L_0x0859
            com.google.android.gms.measurement.internal.zzkw r5 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = "_si"
            long r13 = r14.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x1090 }
            r5.zza(r6, r9, r13)     // Catch:{ all -> 0x1090 }
        L_0x0859:
            r4.zza(r8, r2)     // Catch:{ all -> 0x1090 }
            r17 = 0
            goto L_0x0862
        L_0x0860:
            r8 = r29
        L_0x0862:
            if (r28 != 0) goto L_0x08c6
            java.lang.String r2 = r6.zzd()     // Catch:{ all -> 0x1090 }
            boolean r2 = r11.equals(r2)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x08c6
            int r2 = r6.zzb()     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x088f
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r7 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r7)     // Catch:{ all -> 0x1090 }
            r2.zza(r5, r7)     // Catch:{ all -> 0x1090 }
            goto L_0x08c6
        L_0x088f:
            com.google.android.gms.measurement.internal.zzkw r2 = r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r5 = r6.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r5 = (com.google.android.gms.internal.measurement.zzfd) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r5 = (com.google.android.gms.internal.measurement.zzbr.zzc) r5     // Catch:{ all -> 0x1090 }
            java.lang.Object r2 = r2.zzb(r5, r7)     // Catch:{ all -> 0x1090 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x08bf
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r7 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r7)     // Catch:{ all -> 0x1090 }
            r2.zza(r5, r7)     // Catch:{ all -> 0x1090 }
            goto L_0x08c6
        L_0x08bf:
            long r13 = r2.longValue()     // Catch:{ all -> 0x1090 }
            long r13 = r26 + r13
            goto L_0x08c8
        L_0x08c6:
            r13 = r26
        L_0x08c8:
            java.util.List<com.google.android.gms.internal.measurement.zzbr$zzc> r2 = r3.zzc     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r5 = r6.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r5 = (com.google.android.gms.internal.measurement.zzfd) r5     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r5 = (com.google.android.gms.internal.measurement.zzbr.zzc) r5     // Catch:{ all -> 0x1090 }
            r7 = r25
            r2.set(r7, r5)     // Catch:{ all -> 0x1090 }
            int r2 = r21 + 1
            r4.zza(r6)     // Catch:{ all -> 0x1090 }
            r15 = r8
        L_0x08dd:
            int r8 = r7 + 1
            r9 = r13
            r13 = r20
            r5 = r28
            r14 = r2
            r2 = r22
            goto L_0x02be
        L_0x08e9:
            r28 = r5
            r26 = r9
            if (r28 == 0) goto L_0x0945
            r5 = r21
            r9 = r26
            r2 = 0
        L_0x08f4:
            if (r2 >= r5) goto L_0x0947
            com.google.android.gms.internal.measurement.zzbr$zzc r6 = r4.zzb(r2)     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = r6.zzc()     // Catch:{ all -> 0x1090 }
            boolean r8 = r11.equals(r8)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x0917
            r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r8 = "_fr"
            com.google.android.gms.internal.measurement.zzbr$zze r8 = com.google.android.gms.measurement.internal.zzkw.zza(r6, r8)     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x0917
            r4.zzc(r2)     // Catch:{ all -> 0x1090 }
            int r5 = r5 + -1
            int r2 = r2 + -1
            goto L_0x0942
        L_0x0917:
            r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r6 = com.google.android.gms.measurement.internal.zzkw.zza(r6, r7)     // Catch:{ all -> 0x1090 }
            if (r6 == 0) goto L_0x0942
            boolean r8 = r6.zze()     // Catch:{ all -> 0x1090 }
            if (r8 == 0) goto L_0x0930
            long r12 = r6.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.Long r6 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1090 }
            goto L_0x0931
        L_0x0930:
            r6 = 0
        L_0x0931:
            if (r6 == 0) goto L_0x0942
            long r12 = r6.longValue()     // Catch:{ all -> 0x1090 }
            r14 = 0
            int r8 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r8 <= 0) goto L_0x0942
            long r12 = r6.longValue()     // Catch:{ all -> 0x1090 }
            long r9 = r9 + r12
        L_0x0942:
            r6 = 1
            int r2 = r2 + r6
            goto L_0x08f4
        L_0x0945:
            r9 = r26
        L_0x0947:
            r2 = 0
            r1.zza(r4, r9, r2)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = r4.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzap.zzbo     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r5, r6)     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "_se"
            if (r2 == 0) goto L_0x0a18
            java.util.List r2 = r4.zza()     // Catch:{ all -> 0x1090 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x1090 }
        L_0x0969:
            boolean r6 = r2.hasNext()     // Catch:{ all -> 0x1090 }
            if (r6 == 0) goto L_0x0985
            java.lang.Object r6 = r2.next()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r6 = (com.google.android.gms.internal.measurement.zzbr.zzc) r6     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = "_s"
            java.lang.String r6 = r6.zzc()     // Catch:{ all -> 0x1090 }
            boolean r6 = r7.equals(r6)     // Catch:{ all -> 0x1090 }
            if (r6 == 0) goto L_0x0984
            r2 = 1
            goto L_0x0986
        L_0x0984:
            goto L_0x0969
        L_0x0985:
            r2 = 0
        L_0x0986:
            if (r2 == 0) goto L_0x0993
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            r2.zzb(r6, r5)     // Catch:{ all -> 0x1090 }
        L_0x0993:
            boolean r2 = com.google.android.gms.internal.measurement.zzmv.zzb()     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0a13
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzbp     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r6, r7)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0a13
            java.lang.String r2 = "_sid"
            int r2 = com.google.android.gms.measurement.internal.zzkw.zza(r4, r2)     // Catch:{ all -> 0x1090 }
            if (r2 < 0) goto L_0x09b6
            r2 = 1
            goto L_0x09b7
        L_0x09b6:
            r2 = 0
        L_0x09b7:
            if (r2 != 0) goto L_0x0a13
            int r2 = com.google.android.gms.measurement.internal.zzkw.zza(r4, r5)     // Catch:{ all -> 0x1090 }
            if (r2 < 0) goto L_0x0a12
            r4.zze(r2)     // Catch:{ all -> 0x1090 }
            boolean r2 = com.google.android.gms.internal.measurement.zzkz.zzb()     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x09f8
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r5 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = r5.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzap.zzcy     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r5, r6)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x09f8
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r6 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r6)     // Catch:{ all -> 0x1090 }
            r2.zza(r5, r6)     // Catch:{ all -> 0x1090 }
            goto L_0x0a37
        L_0x09f8:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r6 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfj.zza(r6)     // Catch:{ all -> 0x1090 }
            r2.zza(r5, r6)     // Catch:{ all -> 0x1090 }
        L_0x0a12:
            goto L_0x0a37
        L_0x0a13:
            r2 = 1
            r1.zza(r4, r9, r2)     // Catch:{ all -> 0x1090 }
            goto L_0x0a37
        L_0x0a18:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzbr     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r6, r7)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0a37
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            r2.zzb(r6, r5)     // Catch:{ all -> 0x1090 }
        L_0x0a37:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = r4.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzap.zzbd     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r5, r6)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0adf
            com.google.android.gms.measurement.internal.zzkw r2 = r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r5 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "Checking account type status for ad personalization signals"
            r5.zza(r6)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgk r5 = r2.zzj()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            boolean r5 = r5.zze(r6)     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0adf
            com.google.android.gms.measurement.internal.zzac r5 = r2.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzg r5 = r5.zzb(r6)     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0adf
            boolean r5 = r5.zzaf()     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0adf
            com.google.android.gms.measurement.internal.zzah r5 = r2.zzl()     // Catch:{ all -> 0x1090 }
            boolean r5 = r5.zzj()     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0adf
            com.google.android.gms.measurement.internal.zzfj r5 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzw()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "Turning off ad personalization due to account type"
            r5.zza(r6)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r5 = com.google.android.gms.internal.measurement.zzbr.zzk.zzj()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "_npa"
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r5 = r5.zza(r6)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzah r2 = r2.zzl()     // Catch:{ all -> 0x1090 }
            long r6 = r2.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r2 = r5.zza(r6)     // Catch:{ all -> 0x1090 }
            r5 = 1
            com.google.android.gms.internal.measurement.zzbr$zzk$zza r2 = r2.zzb(r5)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r2 = r2.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r2 = (com.google.android.gms.internal.measurement.zzfd) r2     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzk r2 = (com.google.android.gms.internal.measurement.zzbr.zzk) r2     // Catch:{ all -> 0x1090 }
            r5 = 0
        L_0x0aba:
            int r6 = r4.zze()     // Catch:{ all -> 0x1090 }
            if (r5 >= r6) goto L_0x0ad9
            java.lang.String r6 = "_npa"
            com.google.android.gms.internal.measurement.zzbr$zzk r7 = r4.zzd(r5)     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzc()     // Catch:{ all -> 0x1090 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x1090 }
            if (r6 == 0) goto L_0x0ad6
            r4.zza(r5, r2)     // Catch:{ all -> 0x1090 }
            r5 = 1
            goto L_0x0ada
        L_0x0ad6:
            int r5 = r5 + 1
            goto L_0x0aba
        L_0x0ad9:
            r5 = 0
        L_0x0ada:
            if (r5 != 0) goto L_0x0adf
            r4.zza(r2)     // Catch:{ all -> 0x1090 }
        L_0x0adf:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = r4.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzap.zzcg     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r5, r6)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0af6
            zza(r4)     // Catch:{ all -> 0x1090 }
        L_0x0af6:
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r2 = r4.zzm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzn r5 = r58.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1090 }
            java.util.List r7 = r4.zza()     // Catch:{ all -> 0x1090 }
            java.util.List r8 = r4.zzd()     // Catch:{ all -> 0x1090 }
            long r9 = r4.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x1090 }
            long r10 = r4.zzg()     // Catch:{ all -> 0x1090 }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x1090 }
            java.util.List r5 = r5.zza(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x1090 }
            r2.zzc(r5)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r5 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = r5.zzx()     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zzg(r5)     // Catch:{ all -> 0x1090 }
            if (r2 == 0) goto L_0x0ec9
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x1090 }
            r2.<init>()     // Catch:{ all -> 0x1090 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x1090 }
            r5.<init>()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzla r6 = r6.zzi()     // Catch:{ all -> 0x1090 }
            java.security.SecureRandom r6 = r6.zzh()     // Catch:{ all -> 0x1090 }
            r7 = 0
        L_0x0b4c:
            int r8 = r4.zzb()     // Catch:{ all -> 0x1090 }
            if (r7 >= r8) goto L_0x0e93
            com.google.android.gms.internal.measurement.zzbr$zzc r8 = r4.zzb(r7)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r8 = r8.zzbm()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd$zzb r8 = (com.google.android.gms.internal.measurement.zzfd.zzb) r8     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc$zza r8 = (com.google.android.gms.internal.measurement.zzbr.zzc.zza) r8     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = r8.zzd()     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = "_ep"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0be4
            com.google.android.gms.measurement.internal.zzkw r9 = r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r10 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r10 = (com.google.android.gms.internal.measurement.zzfd) r10     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r10 = (com.google.android.gms.internal.measurement.zzbr.zzc) r10     // Catch:{ all -> 0x1090 }
            java.lang.String r11 = "_en"
            java.lang.Object r9 = r9.zzb(r10, r11)     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x1090 }
            java.lang.Object r10 = r2.get(r9)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r10 = (com.google.android.gms.measurement.internal.zzaj) r10     // Catch:{ all -> 0x1090 }
            if (r10 != 0) goto L_0x0b99
            com.google.android.gms.measurement.internal.zzac r10 = r58.zze()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r11 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r11 = r11.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r10 = r10.zza(r11, r9)     // Catch:{ all -> 0x1090 }
            r2.put(r9, r10)     // Catch:{ all -> 0x1090 }
        L_0x0b99:
            java.lang.Long r9 = r10.zzi     // Catch:{ all -> 0x1090 }
            if (r9 != 0) goto L_0x0bda
            java.lang.Long r9 = r10.zzj     // Catch:{ all -> 0x1090 }
            long r11 = r9.longValue()     // Catch:{ all -> 0x1090 }
            r13 = 1
            int r9 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r9 <= 0) goto L_0x0bb4
            com.google.android.gms.measurement.internal.zzkw r9 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r11 = "_sr"
            java.lang.Long r12 = r10.zzj     // Catch:{ all -> 0x1090 }
            r9.zza(r8, r11, r12)     // Catch:{ all -> 0x1090 }
        L_0x0bb4:
            java.lang.Boolean r9 = r10.zzk     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0bcf
            java.lang.Boolean r9 = r10.zzk     // Catch:{ all -> 0x1090 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0bcf
            com.google.android.gms.measurement.internal.zzkw r9 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = "_efs"
            r11 = 1
            java.lang.Long r13 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x1090 }
            r9.zza(r8, r10, r13)     // Catch:{ all -> 0x1090 }
        L_0x0bcf:
            com.google.android.gms.internal.measurement.zzgo r9 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r9 = (com.google.android.gms.internal.measurement.zzbr.zzc) r9     // Catch:{ all -> 0x1090 }
            r5.add(r9)     // Catch:{ all -> 0x1090 }
        L_0x0bda:
            r4.zza(r7, r8)     // Catch:{ all -> 0x1090 }
            r59 = r3
            r3 = r4
            r15 = r6
            r4 = r7
            goto L_0x0e8b
        L_0x0be4:
            com.google.android.gms.measurement.internal.zzgk r9 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r10 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = r10.zzx()     // Catch:{ all -> 0x1090 }
            long r9 = r9.zzf(r10)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r11 = r1.zzj     // Catch:{ all -> 0x1090 }
            r11.zzi()     // Catch:{ all -> 0x1090 }
            long r11 = r8.zzf()     // Catch:{ all -> 0x1090 }
            long r11 = com.google.android.gms.measurement.internal.zzla.zza(r11, r9)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r13 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r13 = (com.google.android.gms.internal.measurement.zzfd) r13     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r13 = (com.google.android.gms.internal.measurement.zzbr.zzc) r13     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = "_dbg"
            r17 = 1
            java.lang.Long r15 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x1090 }
            boolean r17 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x1090 }
            if (r17 != 0) goto L_0x0c77
            if (r15 != 0) goto L_0x0c1b
            goto L_0x0c77
        L_0x0c1b:
            java.util.List r13 = r13.zza()     // Catch:{ all -> 0x1090 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x1090 }
        L_0x0c23:
            boolean r17 = r13.hasNext()     // Catch:{ all -> 0x1090 }
            if (r17 == 0) goto L_0x0c75
            java.lang.Object r17 = r13.next()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zze r17 = (com.google.android.gms.internal.measurement.zzbr.zze) r17     // Catch:{ all -> 0x1090 }
            r59 = r13
            java.lang.String r13 = r17.zzb()     // Catch:{ all -> 0x1090 }
            boolean r13 = r14.equals(r13)     // Catch:{ all -> 0x1090 }
            if (r13 == 0) goto L_0x0c72
            boolean r13 = r15 instanceof java.lang.Long     // Catch:{ all -> 0x1090 }
            if (r13 == 0) goto L_0x0c4d
            long r13 = r17.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x1090 }
            boolean r13 = r15.equals(r13)     // Catch:{ all -> 0x1090 }
            if (r13 != 0) goto L_0x0c6d
        L_0x0c4d:
            boolean r13 = r15 instanceof java.lang.String     // Catch:{ all -> 0x1090 }
            if (r13 == 0) goto L_0x0c5b
            java.lang.String r13 = r17.zzd()     // Catch:{ all -> 0x1090 }
            boolean r13 = r15.equals(r13)     // Catch:{ all -> 0x1090 }
            if (r13 != 0) goto L_0x0c6d
        L_0x0c5b:
            boolean r13 = r15 instanceof java.lang.Double     // Catch:{ all -> 0x1090 }
            if (r13 == 0) goto L_0x0c6f
            double r13 = r17.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.Double r13 = java.lang.Double.valueOf(r13)     // Catch:{ all -> 0x1090 }
            boolean r13 = r15.equals(r13)     // Catch:{ all -> 0x1090 }
            if (r13 == 0) goto L_0x0c6f
        L_0x0c6d:
            r13 = 1
            goto L_0x0c78
        L_0x0c6f:
            r13 = 0
            goto L_0x0c78
        L_0x0c72:
            r13 = r59
            goto L_0x0c23
        L_0x0c75:
            r13 = 0
            goto L_0x0c78
        L_0x0c77:
            r13 = 0
        L_0x0c78:
            if (r13 != 0) goto L_0x0c8e
            com.google.android.gms.measurement.internal.zzgk r13 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r14 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = r8.zzd()     // Catch:{ all -> 0x1090 }
            int r13 = r13.zzd(r14, r15)     // Catch:{ all -> 0x1090 }
            goto L_0x0c8f
        L_0x0c8e:
            r13 = 1
        L_0x0c8f:
            if (r13 > 0) goto L_0x0cbe
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r9 = r9.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r9 = r9.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = "Sample rate must be positive. event, rate"
            java.lang.String r11 = r8.zzd()     // Catch:{ all -> 0x1090 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x1090 }
            r9.zza(r10, r11, r12)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r9 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r9 = (com.google.android.gms.internal.measurement.zzbr.zzc) r9     // Catch:{ all -> 0x1090 }
            r5.add(r9)     // Catch:{ all -> 0x1090 }
            r4.zza(r7, r8)     // Catch:{ all -> 0x1090 }
            r59 = r3
            r3 = r4
            r15 = r6
            r4 = r7
            goto L_0x0e8b
        L_0x0cbe:
            java.lang.String r14 = r8.zzd()     // Catch:{ all -> 0x1090 }
            java.lang.Object r14 = r2.get(r14)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r14 = (com.google.android.gms.measurement.internal.zzaj) r14     // Catch:{ all -> 0x1090 }
            if (r14 != 0) goto L_0x0d5c
            com.google.android.gms.measurement.internal.zzac r14 = r58.zze()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r15 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = r15.zzx()     // Catch:{ all -> 0x1090 }
            r17 = r9
            java.lang.String r9 = r8.zzd()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r14 = r14.zza(r15, r9)     // Catch:{ all -> 0x1090 }
            if (r14 != 0) goto L_0x0d5e
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r9 = r9.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r9 = r9.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzbr$zzg r14 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r14 = r14.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = r8.zzd()     // Catch:{ all -> 0x1090 }
            r9.zza(r10, r14, r15)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgq r9 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r9 = r9.zzb()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r10 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r10 = r10.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzap.zzbn     // Catch:{ all -> 0x1090 }
            boolean r9 = r9.zze(r10, r14)     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0d37
            com.google.android.gms.measurement.internal.zzaj r9 = new com.google.android.gms.measurement.internal.zzaj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r10 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r26 = r10.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r27 = r8.zzd()     // Catch:{ all -> 0x1090 }
            r28 = 1
            r30 = 1
            r32 = 1
            long r34 = r8.zzf()     // Catch:{ all -> 0x1090 }
            r36 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r25 = r9
            r25.<init>(r26, r27, r28, r30, r32, r34, r36, r38, r39, r40, r41)     // Catch:{ all -> 0x1090 }
            r14 = r9
            goto L_0x0d5e
        L_0x0d37:
            com.google.android.gms.measurement.internal.zzaj r9 = new com.google.android.gms.measurement.internal.zzaj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r10 = r3.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r43 = r10.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.String r44 = r8.zzd()     // Catch:{ all -> 0x1090 }
            r45 = 1
            r47 = 1
            long r49 = r8.zzf()     // Catch:{ all -> 0x1090 }
            r51 = 0
            r53 = 0
            r54 = 0
            r55 = 0
            r56 = 0
            r42 = r9
            r42.<init>(r43, r44, r45, r47, r49, r51, r53, r54, r55, r56)     // Catch:{ all -> 0x1090 }
            r14 = r9
            goto L_0x0d5e
        L_0x0d5c:
            r17 = r9
        L_0x0d5e:
            com.google.android.gms.measurement.internal.zzkw r9 = r58.zzh()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r10 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r10 = (com.google.android.gms.internal.measurement.zzfd) r10     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r10 = (com.google.android.gms.internal.measurement.zzbr.zzc) r10     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = "_eid"
            java.lang.Object r9 = r9.zzb(r10, r15)     // Catch:{ all -> 0x1090 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0d77
            r10 = 1
            goto L_0x0d78
        L_0x0d77:
            r10 = 0
        L_0x0d78:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x1090 }
            r15 = 1
            if (r13 != r15) goto L_0x0db2
            com.google.android.gms.internal.measurement.zzgo r9 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r9 = (com.google.android.gms.internal.measurement.zzbr.zzc) r9     // Catch:{ all -> 0x1090 }
            r5.add(r9)     // Catch:{ all -> 0x1090 }
            boolean r9 = r10.booleanValue()     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0da8
            java.lang.Long r9 = r14.zzi     // Catch:{ all -> 0x1090 }
            if (r9 != 0) goto L_0x0d9c
            java.lang.Long r9 = r14.zzj     // Catch:{ all -> 0x1090 }
            if (r9 != 0) goto L_0x0d9c
            java.lang.Boolean r9 = r14.zzk     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0da8
        L_0x0d9c:
            r9 = 0
            com.google.android.gms.measurement.internal.zzaj r10 = r14.zza(r9, r9, r9)     // Catch:{ all -> 0x1090 }
            java.lang.String r9 = r8.zzd()     // Catch:{ all -> 0x1090 }
            r2.put(r9, r10)     // Catch:{ all -> 0x1090 }
        L_0x0da8:
            r4.zza(r7, r8)     // Catch:{ all -> 0x1090 }
            r59 = r3
            r3 = r4
            r15 = r6
            r4 = r7
            goto L_0x0e8b
        L_0x0db2:
            int r15 = r6.nextInt(r13)     // Catch:{ all -> 0x1090 }
            if (r15 != 0) goto L_0x0dfa
            com.google.android.gms.measurement.internal.zzkw r9 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r15 = "_sr"
            r59 = r3
            r19 = r4
            long r3 = (long) r13     // Catch:{ all -> 0x1090 }
            java.lang.Long r13 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x1090 }
            r9.zza(r8, r15, r13)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r9 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r9 = (com.google.android.gms.internal.measurement.zzfd) r9     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r9 = (com.google.android.gms.internal.measurement.zzbr.zzc) r9     // Catch:{ all -> 0x1090 }
            r5.add(r9)     // Catch:{ all -> 0x1090 }
            boolean r9 = r10.booleanValue()     // Catch:{ all -> 0x1090 }
            if (r9 == 0) goto L_0x0de5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x1090 }
            r4 = 0
            com.google.android.gms.measurement.internal.zzaj r14 = r14.zza(r4, r3, r4)     // Catch:{ all -> 0x1090 }
        L_0x0de5:
            java.lang.String r3 = r8.zzd()     // Catch:{ all -> 0x1090 }
            long r9 = r8.zzf()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r4 = r14.zza(r9, r11)     // Catch:{ all -> 0x1090 }
            r2.put(r3, r4)     // Catch:{ all -> 0x1090 }
            r15 = r6
            r21 = r7
            goto L_0x0e84
        L_0x0dfa:
            r59 = r3
            r19 = r4
            java.lang.Long r3 = r14.zzh     // Catch:{ all -> 0x1090 }
            if (r3 == 0) goto L_0x0e0c
            java.lang.Long r3 = r14.zzh     // Catch:{ all -> 0x1090 }
            long r3 = r3.longValue()     // Catch:{ all -> 0x1090 }
            r15 = r6
            r21 = r7
            goto L_0x0e1f
        L_0x0e0c:
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj     // Catch:{ all -> 0x1090 }
            r3.zzi()     // Catch:{ all -> 0x1090 }
            long r3 = r8.zzg()     // Catch:{ all -> 0x1090 }
            r15 = r6
            r21 = r7
            r6 = r17
            long r3 = com.google.android.gms.measurement.internal.zzla.zza(r3, r6)     // Catch:{ all -> 0x1090 }
        L_0x0e1f:
            int r6 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x0e71
            com.google.android.gms.measurement.internal.zzkw r3 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r4 = "_efs"
            r6 = 1
            java.lang.Long r9 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x1090 }
            r3.zza(r8, r4, r9)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzkw r3 = r58.zzh()     // Catch:{ all -> 0x1090 }
            java.lang.String r4 = "_sr"
            long r6 = (long) r13     // Catch:{ all -> 0x1090 }
            java.lang.Long r9 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x1090 }
            r3.zza(r8, r4, r9)     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r3 = r8.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r3 = (com.google.android.gms.internal.measurement.zzfd) r3     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzc r3 = (com.google.android.gms.internal.measurement.zzbr.zzc) r3     // Catch:{ all -> 0x1090 }
            r5.add(r3)     // Catch:{ all -> 0x1090 }
            boolean r3 = r10.booleanValue()     // Catch:{ all -> 0x1090 }
            if (r3 == 0) goto L_0x0e60
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x1090 }
            r4 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x1090 }
            r4 = 0
            com.google.android.gms.measurement.internal.zzaj r14 = r14.zza(r4, r3, r6)     // Catch:{ all -> 0x1090 }
        L_0x0e60:
            java.lang.String r3 = r8.zzd()     // Catch:{ all -> 0x1090 }
            long r6 = r8.zzf()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r4 = r14.zza(r6, r11)     // Catch:{ all -> 0x1090 }
            r2.put(r3, r4)     // Catch:{ all -> 0x1090 }
            goto L_0x0e84
        L_0x0e71:
            boolean r3 = r10.booleanValue()     // Catch:{ all -> 0x1090 }
            if (r3 == 0) goto L_0x0e84
            java.lang.String r3 = r8.zzd()     // Catch:{ all -> 0x1090 }
            r4 = 0
            com.google.android.gms.measurement.internal.zzaj r6 = r14.zza(r9, r4, r4)     // Catch:{ all -> 0x1090 }
            r2.put(r3, r6)     // Catch:{ all -> 0x1090 }
        L_0x0e84:
            r3 = r19
            r4 = r21
            r3.zza(r4, r8)     // Catch:{ all -> 0x1090 }
        L_0x0e8b:
            int r7 = r4 + 1
            r4 = r3
            r6 = r15
            r3 = r59
            goto L_0x0b4c
        L_0x0e93:
            r59 = r3
            r3 = r4
            int r4 = r5.size()     // Catch:{ all -> 0x1090 }
            int r6 = r3.zzb()     // Catch:{ all -> 0x1090 }
            if (r4 >= r6) goto L_0x0ea7
            com.google.android.gms.internal.measurement.zzbr$zzg$zza r4 = r3.zzc()     // Catch:{ all -> 0x1090 }
            r4.zza(r5)     // Catch:{ all -> 0x1090 }
        L_0x0ea7:
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x1090 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x1090 }
        L_0x0eaf:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x1090 }
            if (r4 == 0) goto L_0x0ecc
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x1090 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzac r5 = r58.zze()     // Catch:{ all -> 0x1090 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzaj r4 = (com.google.android.gms.measurement.internal.zzaj) r4     // Catch:{ all -> 0x1090 }
            r5.zza(r4)     // Catch:{ all -> 0x1090 }
            goto L_0x0eaf
        L_0x0ec9:
            r59 = r3
            r3 = r4
        L_0x0ecc:
            com.google.android.gms.measurement.internal.zzgq r2 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzx r2 = r2.zzb()     // Catch:{ all -> 0x1090 }
            java.lang.String r4 = r3.zzj()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzap.zzcg     // Catch:{ all -> 0x1090 }
            boolean r2 = r2.zze(r4, r5)     // Catch:{ all -> 0x1090 }
            if (r2 != 0) goto L_0x0ee3
            zza(r3)     // Catch:{ all -> 0x1090 }
        L_0x0ee3:
            r2 = r59
            com.google.android.gms.internal.measurement.zzbr$zzg r4 = r2.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzac r5 = r58.zze()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzg r5 = r5.zzb(r4)     // Catch:{ all -> 0x1090 }
            if (r5 != 0) goto L_0x0f10
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r7 = r2.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r7)     // Catch:{ all -> 0x1090 }
            r5.zza(r6, r7)     // Catch:{ all -> 0x1090 }
            goto L_0x0f6b
        L_0x0f10:
            int r6 = r3.zzb()     // Catch:{ all -> 0x1090 }
            if (r6 <= 0) goto L_0x0f6b
            long r6 = r5.zzk()     // Catch:{ all -> 0x1090 }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0f24
            r3.zze(r6)     // Catch:{ all -> 0x1090 }
            goto L_0x0f27
        L_0x0f24:
            r3.zzi()     // Catch:{ all -> 0x1090 }
        L_0x0f27:
            long r8 = r5.zzj()     // Catch:{ all -> 0x1090 }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0f32
            goto L_0x0f33
        L_0x0f32:
            r6 = r8
        L_0x0f33:
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0f3b
            r3.zzd(r6)     // Catch:{ all -> 0x1090 }
            goto L_0x0f3e
        L_0x0f3b:
            r3.zzh()     // Catch:{ all -> 0x1090 }
        L_0x0f3e:
            r5.zzv()     // Catch:{ all -> 0x1090 }
            long r6 = r5.zzs()     // Catch:{ all -> 0x1090 }
            int r7 = (int) r6     // Catch:{ all -> 0x1090 }
            r3.zzg(r7)     // Catch:{ all -> 0x1090 }
            long r6 = r3.zzf()     // Catch:{ all -> 0x1090 }
            r5.zza(r6)     // Catch:{ all -> 0x1090 }
            long r6 = r3.zzg()     // Catch:{ all -> 0x1090 }
            r5.zzb(r6)     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r5.zzad()     // Catch:{ all -> 0x1090 }
            if (r6 == 0) goto L_0x0f61
            r3.zzj(r6)     // Catch:{ all -> 0x1090 }
            goto L_0x0f64
        L_0x0f61:
            r3.zzk()     // Catch:{ all -> 0x1090 }
        L_0x0f64:
            com.google.android.gms.measurement.internal.zzac r6 = r58.zze()     // Catch:{ all -> 0x1090 }
            r6.zza(r5)     // Catch:{ all -> 0x1090 }
        L_0x0f6b:
            int r5 = r3.zzb()     // Catch:{ all -> 0x1090 }
            if (r5 <= 0) goto L_0x0fd6
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            r5.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzgk r5 = r58.zzc()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r6 = r2.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbo$zzb r5 = r5.zza(r6)     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0f99
            boolean r6 = r5.zza()     // Catch:{ all -> 0x1090 }
            if (r6 != 0) goto L_0x0f91
            goto L_0x0f99
        L_0x0f91:
            long r5 = r5.zzb()     // Catch:{ all -> 0x1090 }
            r3.zzi(r5)     // Catch:{ all -> 0x1090 }
            goto L_0x0fc5
        L_0x0f99:
            com.google.android.gms.internal.measurement.zzbr$zzg r5 = r2.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = r5.zzam()     // Catch:{ all -> 0x1090 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x1090 }
            if (r5 == 0) goto L_0x0fab
            r5 = -1
            r3.zzi(r5)     // Catch:{ all -> 0x1090 }
            goto L_0x0fc5
        L_0x0fab:
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzi()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzbr$zzg r7 = r2.zza     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1090 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r7)     // Catch:{ all -> 0x1090 }
            r5.zza(r6, r7)     // Catch:{ all -> 0x1090 }
        L_0x0fc5:
            com.google.android.gms.measurement.internal.zzac r5 = r58.zze()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzgo r3 = r3.zzu()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzfd r3 = (com.google.android.gms.internal.measurement.zzfd) r3     // Catch:{ all -> 0x1090 }
            com.google.android.gms.internal.measurement.zzbr$zzg r3 = (com.google.android.gms.internal.measurement.zzbr.zzg) r3     // Catch:{ all -> 0x1090 }
            r12 = r20
            r5.zza(r3, r12)     // Catch:{ all -> 0x1090 }
        L_0x0fd6:
            com.google.android.gms.measurement.internal.zzac r3 = r58.zze()     // Catch:{ all -> 0x1090 }
            java.util.List<java.lang.Long> r2 = r2.zzb     // Catch:{ all -> 0x1090 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x1090 }
            r3.zzd()     // Catch:{ all -> 0x1090 }
            r3.zzak()     // Catch:{ all -> 0x1090 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "rowid in ("
            r5.<init>(r6)     // Catch:{ all -> 0x1090 }
            r6 = 0
        L_0x0fed:
            int r7 = r2.size()     // Catch:{ all -> 0x1090 }
            if (r6 >= r7) goto L_0x100a
            if (r6 == 0) goto L_0x0ffa
            java.lang.String r7 = ","
            r5.append(r7)     // Catch:{ all -> 0x1090 }
        L_0x0ffa:
            java.lang.Object r7 = r2.get(r6)     // Catch:{ all -> 0x1090 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x1090 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x1090 }
            r5.append(r7)     // Catch:{ all -> 0x1090 }
            int r6 = r6 + 1
            goto L_0x0fed
        L_0x100a:
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch:{ all -> 0x1090 }
            android.database.sqlite.SQLiteDatabase r6 = r3.mo16207c_()     // Catch:{ all -> 0x1090 }
            java.lang.String r7 = "raw_events"
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x1090 }
            r8 = 0
            int r5 = r6.delete(r7, r5, r8)     // Catch:{ all -> 0x1090 }
            int r6 = r2.size()     // Catch:{ all -> 0x1090 }
            if (r5 == r6) goto L_0x103d
            com.google.android.gms.measurement.internal.zzfj r3 = r3.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r3 = r3.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.String r6 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x1090 }
            int r2 = r2.size()     // Catch:{ all -> 0x1090 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x1090 }
            r3.zza(r6, r5, r2)     // Catch:{ all -> 0x1090 }
        L_0x103d:
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()     // Catch:{ all -> 0x1090 }
            android.database.sqlite.SQLiteDatabase r3 = r2.mo16207c_()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x1054 }
            r7 = 0
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x1054 }
            r7 = 1
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x1054 }
            r3.execSQL(r5, r6)     // Catch:{ SQLiteException -> 0x1054 }
            goto L_0x1067
        L_0x1054:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzfj r2 = r2.zzr()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzfl r2 = r2.zzf()     // Catch:{ all -> 0x1090 }
            java.lang.String r5 = "Failed to remove unused event metadata. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza(r4)     // Catch:{ all -> 0x1090 }
            r2.zza(r5, r4, r3)     // Catch:{ all -> 0x1090 }
        L_0x1067:
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()     // Catch:{ all -> 0x1090 }
            r2.mo16206b_()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()
            r2.zzh()
            r2 = 1
            return r2
        L_0x1077:
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()     // Catch:{ all -> 0x1090 }
            r2.mo16206b_()     // Catch:{ all -> 0x1090 }
            com.google.android.gms.measurement.internal.zzac r2 = r58.zze()
            r2.zzh()
            r2 = 0
            return r2
        L_0x1087:
            r0 = move-exception
            r2 = r0
            r8 = r6
        L_0x108a:
            if (r8 == 0) goto L_0x108f
            r8.close()     // Catch:{ all -> 0x1090 }
        L_0x108f:
            throw r2     // Catch:{ all -> 0x1090 }
        L_0x1090:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzac r3 = r58.zze()
            r3.zzh()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.zza(java.lang.String, long):boolean");
    }

    private static void zza(com.google.android.gms.internal.measurement.zzbr.zzg.zza zza2) {
        zza2.zzb((long) LongCompanionObject.MAX_VALUE).zzc(Long.MIN_VALUE);
        for (int i = 0; i < zza2.zzb(); i++) {
            zzc zzb2 = zza2.zzb(i);
            if (zzb2.zze() < zza2.zzf()) {
                zza2.zzb(zzb2.zze());
            }
            if (zzb2.zze() > zza2.zzg()) {
                zza2.zzc(zzb2.zze());
            }
        }
    }

    private final void zza(com.google.android.gms.internal.measurement.zzbr.zzg.zza zza2, long j, boolean z) {
        String str;
        zzlb zzlb;
        String str2;
        if (z) {
            str = "_se";
        } else {
            str = "_lte";
        }
        zzlb zzc2 = zze().zzc(zza2.zzj(), str);
        if (zzc2 == null || zzc2.zze == null) {
            zzlb = new zzlb(zza2.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzlb = new zzlb(zza2.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(((Long) zzc2.zze).longValue() + j));
        }
        zzk zzk2 = (zzk) ((zzfd) zzk.zzj().zza(str).zza(this.zzj.zzm().currentTimeMillis()).zzb(((Long) zzlb.zze).longValue()).zzu());
        boolean z2 = false;
        int zza3 = zzkw.zza(zza2, str);
        if (zza3 >= 0) {
            zza2.zza(zza3, zzk2);
            z2 = true;
        }
        if (!z2) {
            zza2.zza(zzk2);
        }
        if (j > 0) {
            zze().zza(zzlb);
            if (z) {
                str2 = "session-scoped";
            } else {
                str2 = "lifetime";
            }
            String str3 = "Updated engagement user property. scope, value";
            if (!zzkz.zzb() || !this.zzj.zzb().zze(zza2.zzj(), zzap.zzcy)) {
                this.zzj.zzr().zzw().zza(str3, str2, zzlb.zze);
            } else {
                this.zzj.zzr().zzx().zza(str3, str2, zzlb.zze);
            }
        }
    }

    private final boolean zza(com.google.android.gms.internal.measurement.zzbr.zzc.zza zza2, com.google.android.gms.internal.measurement.zzbr.zzc.zza zza3) {
        Preconditions.checkArgument("_e".equals(zza2.zzd()));
        zzh();
        zze zza4 = zzkw.zza((zzc) ((zzfd) zza2.zzu()), "_sc");
        String str = null;
        Object zzd2 = zza4 == null ? null : zza4.zzd();
        zzh();
        zze zza5 = zzkw.zza((zzc) ((zzfd) zza3.zzu()), "_pc");
        if (zza5 != null) {
            str = zza5.zzd();
        }
        if (str == null || !str.equals(zzd2)) {
            return false;
        }
        zzb(zza2, zza3);
        return true;
    }

    private final void zzb(com.google.android.gms.internal.measurement.zzbr.zzc.zza zza2, com.google.android.gms.internal.measurement.zzbr.zzc.zza zza3) {
        Preconditions.checkArgument("_e".equals(zza2.zzd()));
        zzh();
        String str = "_et";
        zze zza4 = zzkw.zza((zzc) ((zzfd) zza2.zzu()), str);
        if (zza4.zze() && zza4.zzf() > 0) {
            long zzf2 = zza4.zzf();
            zzh();
            zze zza5 = zzkw.zza((zzc) ((zzfd) zza3.zzu()), str);
            if (zza5 != null && zza5.zzf() > 0) {
                zzf2 += zza5.zzf();
            }
            zzh().zza(zza3, str, (Object) Long.valueOf(zzf2));
            zzh().zza(zza2, "_fr", (Object) Long.valueOf(1));
        }
    }

    private static void zza(com.google.android.gms.internal.measurement.zzbr.zzc.zza zza2, String str) {
        List zza3 = zza2.zza();
        for (int i = 0; i < zza3.size(); i++) {
            if (str.equals(((zze) zza3.get(i)).zzb())) {
                zza2.zzb(i);
                return;
            }
        }
    }

    private static void zza(com.google.android.gms.internal.measurement.zzbr.zzc.zza zza2, int i, String str) {
        List zza3 = zza2.zza();
        int i2 = 0;
        while (true) {
            String str2 = "_err";
            if (i2 >= zza3.size()) {
                zze zze2 = (zze) ((zzfd) zze.zzk().zza("_ev").zzb(str).zzu());
                zza2.zza((zze) ((zzfd) zze.zzk().zza(str2).zza(Long.valueOf((long) i).longValue()).zzu())).zza(zze2);
                return;
            } else if (!str2.equals(((zze) zza3.get(i2)).zzb())) {
                i2++;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzac zze2;
        zzw();
        zzk();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzr = false;
                zzaa();
                throw th2;
            }
        }
        List<Long> list = this.zzv;
        this.zzv = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
                this.zzj.zzc().zzd.zza(0);
                zzz();
                this.zzj.zzr().zzx().zza("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zze().zzf();
                try {
                    for (Long l : list) {
                        try {
                            zze2 = zze();
                            long longValue = l.longValue();
                            zze2.zzd();
                            zze2.zzak();
                            if (zze2.mo16207c_().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zze2.zzr().zzf().zza("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzw == null || !this.zzw.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zze().mo16206b_();
                    zze().zzh();
                    this.zzw = null;
                    if (!zzd().zzf() || !zzy()) {
                        this.zzx = -1;
                        zzz();
                    } else {
                        zzl();
                    }
                    this.zzm = 0;
                } catch (Throwable th3) {
                    zze().zzh();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzj.zzr().zzf().zza("Database error while trying to delete uploaded bundles", e3);
                this.zzm = this.zzj.zzm().elapsedRealtime();
                this.zzj.zzr().zzx().zza("Disable upload, time", Long.valueOf(this.zzm));
            }
        } else {
            this.zzj.zzr().zzx().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzj.zzc().zzd.zza(this.zzj.zzm().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                this.zzj.zzc().zze.zza(this.zzj.zzm().currentTimeMillis());
            }
            zze().zza(list);
            zzz();
        }
        this.zzr = false;
        zzaa();
    }

    private final boolean zzy() {
        zzw();
        zzk();
        return zze().zzy() || !TextUtils.isEmpty(zze().mo16208d_());
    }

    private final void zza(zzg zzg2) {
        Map map;
        zzw();
        if (!zzll.zzb() || !this.zzj.zzb().zze(zzg2.zzc(), zzap.zzch)) {
            if (TextUtils.isEmpty(zzg2.zze()) && TextUtils.isEmpty(zzg2.zzf())) {
                zza(zzg2.zzc(), HttpStatus.SC_NO_CONTENT, null, null, null);
                return;
            }
        } else if (TextUtils.isEmpty(zzg2.zze()) && TextUtils.isEmpty(zzg2.zzg()) && TextUtils.isEmpty(zzg2.zzf())) {
            zza(zzg2.zzc(), HttpStatus.SC_NO_CONTENT, null, null, null);
            return;
        }
        String zza2 = this.zzj.zzb().zza(zzg2);
        try {
            URL url = new URL(zza2);
            this.zzj.zzr().zzx().zza("Fetching remote configuration", zzg2.zzc());
            zzb zza3 = zzc().zza(zzg2.zzc());
            String zzb2 = zzc().zzb(zzg2.zzc());
            if (zza3 == null || TextUtils.isEmpty(zzb2)) {
                map = null;
            } else {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", zzb2);
                map = arrayMap;
            }
            this.zzq = true;
            zzfq zzd2 = zzd();
            String zzc2 = zzg2.zzc();
            zzkt zzkt = new zzkt(this);
            zzd2.zzd();
            zzd2.zzak();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkt);
            zzgj zzq2 = zzd2.zzq();
            zzfu zzfu = new zzfu(zzd2, zzc2, url, null, map, zzkt);
            zzq2.zzb((Runnable) zzfu);
        } catch (MalformedURLException e) {
            this.zzj.zzr().zzf().zza("Failed to parse config URL. Not fetching. appId", zzfj.zza(zzg2.zzc()), zza2);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0141 A[Catch:{ all -> 0x0198, all -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0152 A[Catch:{ all -> 0x0198, all -> 0x01a1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzw()
            r6.zzk()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x000e
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x01a1 }
        L_0x000e:
            com.google.android.gms.measurement.internal.zzgq r1 = r6.zzj     // Catch:{ all -> 0x01a1 }
            com.google.android.gms.measurement.internal.zzfj r1 = r1.zzr()     // Catch:{ all -> 0x01a1 }
            com.google.android.gms.measurement.internal.zzfl r1 = r1.zzx()     // Catch:{ all -> 0x01a1 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x01a1 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01a1 }
            r1.zza(r2, r3)     // Catch:{ all -> 0x01a1 }
            com.google.android.gms.measurement.internal.zzac r1 = r6.zze()     // Catch:{ all -> 0x01a1 }
            r1.zzf()     // Catch:{ all -> 0x01a1 }
            com.google.android.gms.measurement.internal.zzac r1 = r6.zze()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzg r1 = r1.zzb(r7)     // Catch:{ all -> 0x0198 }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            r4 = 1
            if (r8 == r2) goto L_0x003f
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x003f
            if (r8 != r3) goto L_0x0043
        L_0x003f:
            if (r9 != 0) goto L_0x0043
            r2 = 1
            goto L_0x0044
        L_0x0043:
            r2 = 0
        L_0x0044:
            if (r1 != 0) goto L_0x005c
            com.google.android.gms.measurement.internal.zzgq r8 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfj r8 = r8.zzr()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzi()     // Catch:{ all -> 0x0198 }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r7)     // Catch:{ all -> 0x0198 }
            r8.zza(r9, r7)     // Catch:{ all -> 0x0198 }
            goto L_0x0183
        L_0x005c:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00d0
            if (r8 != r5) goto L_0x0063
            goto L_0x00d0
        L_0x0063:
            com.google.android.gms.measurement.internal.zzgq r10 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.common.util.Clock r10 = r10.zzm()     // Catch:{ all -> 0x0198 }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x0198 }
            r1.zzi(r10)     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzac r10 = r6.zze()     // Catch:{ all -> 0x0198 }
            r10.zza(r1)     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzgq r10 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfj r10 = r10.zzr()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfl r10 = r10.zzx()     // Catch:{ all -> 0x0198 }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0198 }
            r10.zza(r11, r1, r9)     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzgk r9 = r6.zzc()     // Catch:{ all -> 0x0198 }
            r9.zzc(r7)     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzgq r7 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzc()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzga r7 = r7.zzd     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzgq r9 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.common.util.Clock r9 = r9.zzm()     // Catch:{ all -> 0x0198 }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x0198 }
            r7.zza(r9)     // Catch:{ all -> 0x0198 }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00b3
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00b2
            goto L_0x00b3
        L_0x00b2:
            r4 = 0
        L_0x00b3:
            if (r4 == 0) goto L_0x00cb
            com.google.android.gms.measurement.internal.zzgq r7 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzc()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzga r7 = r7.zze     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzgq r8 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.common.util.Clock r8 = r8.zzm()     // Catch:{ all -> 0x0198 }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x0198 }
            r7.zza(r8)     // Catch:{ all -> 0x0198 }
        L_0x00cb:
            r6.zzz()     // Catch:{ all -> 0x0198 }
            goto L_0x0183
        L_0x00d0:
            r9 = 0
            if (r11 == 0) goto L_0x00dc
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x0198 }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x0198 }
            goto L_0x00dd
        L_0x00dc:
            r11 = r9
        L_0x00dd:
            if (r11 == 0) goto L_0x00ec
            int r2 = r11.size()     // Catch:{ all -> 0x0198 }
            if (r2 <= 0) goto L_0x00ec
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x0198 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0198 }
            goto L_0x00ed
        L_0x00ec:
            r11 = r9
        L_0x00ed:
            if (r8 == r5) goto L_0x0109
            if (r8 != r3) goto L_0x00f2
            goto L_0x0109
        L_0x00f2:
            com.google.android.gms.measurement.internal.zzgk r9 = r6.zzc()     // Catch:{ all -> 0x0198 }
            boolean r9 = r9.zza(r7, r10, r11)     // Catch:{ all -> 0x0198 }
            if (r9 != 0) goto L_0x012a
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x01a1 }
            r7.zzh()     // Catch:{ all -> 0x01a1 }
            r6.zzq = r0
            r6.zzaa()
            return
        L_0x0109:
            com.google.android.gms.measurement.internal.zzgk r11 = r6.zzc()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.internal.measurement.zzbo$zzb r11 = r11.zza(r7)     // Catch:{ all -> 0x0198 }
            if (r11 != 0) goto L_0x012a
            com.google.android.gms.measurement.internal.zzgk r11 = r6.zzc()     // Catch:{ all -> 0x0198 }
            boolean r9 = r11.zza(r7, r9, r9)     // Catch:{ all -> 0x0198 }
            if (r9 != 0) goto L_0x012a
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x01a1 }
            r7.zzh()     // Catch:{ all -> 0x01a1 }
            r6.zzq = r0
            r6.zzaa()
            return
        L_0x012a:
            com.google.android.gms.measurement.internal.zzgq r9 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.common.util.Clock r9 = r9.zzm()     // Catch:{ all -> 0x0198 }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x0198 }
            r1.zzh(r2)     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzac r9 = r6.zze()     // Catch:{ all -> 0x0198 }
            r9.zza(r1)     // Catch:{ all -> 0x0198 }
            if (r8 != r5) goto L_0x0152
            com.google.android.gms.measurement.internal.zzgq r8 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfj r8 = r8.zzr()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzk()     // Catch:{ all -> 0x0198 }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zza(r9, r7)     // Catch:{ all -> 0x0198 }
            goto L_0x016b
        L_0x0152:
            com.google.android.gms.measurement.internal.zzgq r7 = r6.zzj     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzx()     // Catch:{ all -> 0x0198 }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0198 }
            int r10 = r10.length     // Catch:{ all -> 0x0198 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x0198 }
            r7.zza(r9, r8, r10)     // Catch:{ all -> 0x0198 }
        L_0x016b:
            com.google.android.gms.measurement.internal.zzfq r7 = r6.zzd()     // Catch:{ all -> 0x0198 }
            boolean r7 = r7.zzf()     // Catch:{ all -> 0x0198 }
            if (r7 == 0) goto L_0x017f
            boolean r7 = r6.zzy()     // Catch:{ all -> 0x0198 }
            if (r7 == 0) goto L_0x017f
            r6.zzl()     // Catch:{ all -> 0x0198 }
            goto L_0x0183
        L_0x017f:
            r6.zzz()     // Catch:{ all -> 0x0198 }
        L_0x0183:
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x0198 }
            r7.mo16206b_()     // Catch:{ all -> 0x0198 }
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x01a1 }
            r7.zzh()     // Catch:{ all -> 0x01a1 }
            r6.zzq = r0
            r6.zzaa()
            return
        L_0x0198:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzac r8 = r6.zze()     // Catch:{ all -> 0x01a1 }
            r8.zzh()     // Catch:{ all -> 0x01a1 }
            throw r7     // Catch:{ all -> 0x01a1 }
        L_0x01a1:
            r7 = move-exception
            r6.zzq = r0
            r6.zzaa()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    private final void zzz() {
        long j;
        long j2;
        zzw();
        zzk();
        if (this.zzm > 0) {
            long abs = 3600000 - Math.abs(this.zzj.zzm().elapsedRealtime() - this.zzm);
            if (abs > 0) {
                this.zzj.zzr().zzx().zza("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                zzt().zzb();
                zzv().zzf();
                return;
            }
            this.zzm = 0;
        }
        if (!this.zzj.zzah() || !zzy()) {
            this.zzj.zzr().zzx().zza("Nothing to upload or uploading impossible");
            zzt().zzb();
            zzv().zzf();
            return;
        }
        long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
        long max = Math.max(0, ((Long) zzap.zzz.zza(null)).longValue());
        boolean z = zze().zzz() || zze().zzk();
        if (z) {
            String zzw2 = this.zzj.zzb().zzw();
            if (TextUtils.isEmpty(zzw2) || ".none.".equals(zzw2)) {
                j = Math.max(0, ((Long) zzap.zzt.zza(null)).longValue());
            } else {
                j = Math.max(0, ((Long) zzap.zzu.zza(null)).longValue());
            }
        } else {
            j = Math.max(0, ((Long) zzap.zzs.zza(null)).longValue());
        }
        long zza2 = this.zzj.zzc().zzc.zza();
        long zza3 = this.zzj.zzc().zzd.zza();
        long j3 = j;
        long j4 = max;
        long max2 = Math.max(zze().zzw(), zze().zzx());
        if (max2 == 0) {
            j2 = 0;
        } else {
            long abs2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
            long abs3 = currentTimeMillis - Math.abs(zza2 - currentTimeMillis);
            long abs4 = currentTimeMillis - Math.abs(zza3 - currentTimeMillis);
            long max3 = Math.max(abs3, abs4);
            long j5 = abs2 + j4;
            if (z && max3 > 0) {
                j5 = Math.min(abs2, max3) + j3;
            }
            long j6 = j3;
            if (!zzh().zza(max3, j6)) {
                j2 = max3 + j6;
            } else {
                j2 = j5;
            }
            if (abs4 != 0 && abs4 >= abs2) {
                int i = 0;
                while (true) {
                    if (i >= Math.min(20, Math.max(0, ((Integer) zzap.zzab.zza(null)).intValue()))) {
                        j2 = 0;
                        break;
                    }
                    j2 += Math.max(0, ((Long) zzap.zzaa.zza(null)).longValue()) * (1 << i);
                    if (j2 > abs4) {
                        break;
                    }
                    i++;
                }
            }
        }
        if (j2 == 0) {
            this.zzj.zzr().zzx().zza("Next upload time is 0");
            zzt().zzb();
            zzv().zzf();
        } else if (!zzd().zzf()) {
            this.zzj.zzr().zzx().zza("No network");
            zzt().zza();
            zzv().zzf();
        } else {
            long zza4 = this.zzj.zzc().zze.zza();
            long max4 = Math.max(0, ((Long) zzap.zzq.zza(null)).longValue());
            if (!zzh().zza(zza4, max4)) {
                j2 = Math.max(j2, zza4 + max4);
            }
            zzt().zzb();
            long currentTimeMillis2 = j2 - this.zzj.zzm().currentTimeMillis();
            if (currentTimeMillis2 <= 0) {
                currentTimeMillis2 = Math.max(0, ((Long) zzap.zzv.zza(null)).longValue());
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
            }
            this.zzj.zzr().zzx().zza("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
            zzv().zza(currentTimeMillis2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Runnable runnable) {
        zzw();
        if (this.zzn == null) {
            this.zzn = new ArrayList();
        }
        this.zzn.add(runnable);
    }

    private final void zzaa() {
        zzw();
        if (this.zzq || this.zzr || this.zzs) {
            this.zzj.zzr().zzx().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzq), Boolean.valueOf(this.zzr), Boolean.valueOf(this.zzs));
            return;
        }
        this.zzj.zzr().zzx().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzn;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            this.zzn.clear();
        }
    }

    private final Boolean zzb(zzg zzg2) {
        try {
            if (zzg2.zzm() != -2147483648L) {
                if (zzg2.zzm() == ((long) Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzg2.zzc(), 0).versionCode)) {
                    return Boolean.valueOf(true);
                }
            } else {
                String str = Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzg2.zzc(), 0).versionName;
                if (zzg2.zzl() != null && zzg2.zzl().equals(str)) {
                    return Boolean.valueOf(true);
                }
            }
            return Boolean.valueOf(false);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzo() {
        zzw();
        zzk();
        if (!this.zzl) {
            this.zzl = true;
            if (zzab()) {
                int zza2 = zza(this.zzu);
                int zzaf = this.zzj.zzy().zzaf();
                zzw();
                if (zza2 > zzaf) {
                    this.zzj.zzr().zzf().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                } else if (zza2 >= zzaf) {
                } else {
                    if (zza(zzaf, this.zzu)) {
                        this.zzj.zzr().zzx().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                        return;
                    }
                    this.zzj.zzr().zzf().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                }
            }
        }
    }

    private final boolean zzab() {
        zzw();
        String str = "Storage concurrent access okay";
        if (this.zzj.zzb().zza(zzap.zzcf)) {
            FileLock fileLock = this.zzt;
            if (fileLock != null && fileLock.isValid()) {
                this.zzj.zzr().zzx().zza(str);
                return true;
            }
        }
        try {
            this.zzu = new RandomAccessFile(new File(this.zzj.zzn().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzt = this.zzu.tryLock();
            if (this.zzt != null) {
                this.zzj.zzr().zzx().zza(str);
                return true;
            }
            this.zzj.zzr().zzf().zza("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            this.zzj.zzr().zzf().zza("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            this.zzj.zzr().zzf().zza("Failed to access storage lock file", e2);
        } catch (OverlappingFileLockException e3) {
            this.zzj.zzr().zzi().zza("Storage lock already acquired", e3);
        }
    }

    private final int zza(FileChannel fileChannel) {
        zzw();
        int i = 0;
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzj.zzr().zzi().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            i = allocate.getInt();
            return i;
        } catch (IOException e) {
            this.zzj.zzr().zzf().zza("Failed to read from channel", e);
        }
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            if (this.zzj.zzb().zza(zzap.zzcu) && VERSION.SDK_INT <= 19) {
                fileChannel.position(0);
            }
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzj.zzr().zzf().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzj.zzr().zzf().zza("Failed to write to channel", e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzm zzm2) {
        String str = "app_id=?";
        if (this.zzv != null) {
            this.zzw = new ArrayList();
            this.zzw.addAll(this.zzv);
        }
        zzac zze2 = zze();
        String str2 = zzm2.zza;
        Preconditions.checkNotEmpty(str2);
        zze2.zzd();
        zze2.zzak();
        try {
            SQLiteDatabase c_ = zze2.mo16207c_();
            String[] strArr = {str2};
            int delete = c_.delete("apps", str, strArr) + 0 + c_.delete("events", str, strArr) + c_.delete("user_attributes", str, strArr) + c_.delete("conditional_properties", str, strArr) + c_.delete("raw_events", str, strArr) + c_.delete("raw_events_metadata", str, strArr) + c_.delete("queue", str, strArr) + c_.delete("audience_filter_values", str, strArr) + c_.delete("main_event_params", str, strArr);
            if (delete > 0) {
                zze2.zzr().zzx().zza("Reset analytics data. app, records", str2, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zze2.zzr().zzf().zza("Error resetting analytics data. appId, error", zzfj.zza(str2), e);
        }
        if (!com.google.android.gms.internal.measurement.zzks.zzb() || !this.zzj.zzb().zza(zzap.zzcm)) {
            zzm zza2 = zza(this.zzj.zzn(), zzm2.zza, zzm2.zzb, zzm2.zzh, zzm2.zzo, zzm2.zzp, zzm2.zzm, zzm2.zzr, zzm2.zzv);
            if (zzm2.zzh) {
                zzb(zza2);
            }
        } else if (zzm2.zzh) {
            zzb(zzm2);
        }
    }

    private final zzm zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j, String str3, String str4) {
        String str5;
        String str6;
        String str7;
        int i;
        String str8 = str;
        String str9 = "Unknown";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzj.zzr().zzf().zza("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str5 = packageManager.getInstallerPackageName(str8);
        } catch (IllegalArgumentException e) {
            this.zzj.zzr().zzf().zza("Error retrieving installer package name. appId", zzfj.zza(str));
            str5 = str9;
        }
        if (str5 == null) {
            str6 = "manual_install";
        } else if ("com.android.vending".equals(str5)) {
            str6 = "";
        } else {
            str6 = str5;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str8, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str8);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    String charSequence = applicationLabel.toString();
                }
                str7 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                i = Integer.MIN_VALUE;
                str7 = str9;
            }
            zzm zzm2 = new zzm(str, str2, str7, (long) i, str6, this.zzj.zzb().zzf(), this.zzj.zzi().zza(context, str8), (String) null, z, false, "", 0, j, 0, z2, z3, false, str3, (Boolean) null, 0, null, (!zzll.zzb() || !this.zzj.zzb().zze(str8, zzap.zzch)) ? null : str4);
            return zzm2;
        } catch (NameNotFoundException e2) {
            this.zzj.zzr().zzf().zza("Error retrieving newly installed package info. appId, appName", zzfj.zza(str), str9);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzkz zzkz, zzm zzm2) {
        int i;
        zzw();
        zzk();
        if (zze(zzm2)) {
            if (!zzm2.zzh) {
                zzc(zzm2);
                return;
            }
            int zzc2 = this.zzj.zzi().zzc(zzkz.zza);
            if (zzc2 != 0) {
                this.zzj.zzi();
                this.zzj.zzi().zza(zzm2.zza, zzc2, "_ev", zzla.zza(zzkz.zza, 24, true), zzkz.zza != null ? zzkz.zza.length() : 0);
                return;
            }
            int zzb2 = this.zzj.zzi().zzb(zzkz.zza, zzkz.zza());
            if (zzb2 != 0) {
                this.zzj.zzi();
                String zza2 = zzla.zza(zzkz.zza, 24, true);
                Object zza3 = zzkz.zza();
                if (zza3 == null || (!(zza3 instanceof String) && !(zza3 instanceof CharSequence))) {
                    i = 0;
                } else {
                    i = String.valueOf(zza3).length();
                }
                this.zzj.zzi().zza(zzm2.zza, zzb2, "_ev", zza2, i);
                return;
            }
            Object zzc3 = this.zzj.zzi().zzc(zzkz.zza, zzkz.zza());
            if (zzc3 != null) {
                if ("_sid".equals(zzkz.zza) && this.zzj.zzb().zze(zzm2.zza, zzap.zzat)) {
                    long j = zzkz.zzb;
                    String str = zzkz.zze;
                    long j2 = 0;
                    zzlb zzc4 = zze().zzc(zzm2.zza, "_sno");
                    if (zzc4 == null || !(zzc4.zze instanceof Long)) {
                        if (zzc4 != null) {
                            this.zzj.zzr().zzi().zza("Retrieved last session number from database does not contain a valid (long) value", zzc4.zze);
                        }
                        if (this.zzj.zzb().zze(zzm2.zza, zzap.zzaw)) {
                            zzaj zza4 = zze().zza(zzm2.zza, "_s");
                            if (zza4 != null) {
                                j2 = zza4.zzc;
                                this.zzj.zzr().zzx().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                            }
                        }
                    } else {
                        j2 = ((Long) zzc4.zze).longValue();
                    }
                    zzkz zzkz2 = new zzkz("_sno", j, Long.valueOf(j2 + 1), str);
                    zza(zzkz2, zzm2);
                }
                zzlb zzlb = new zzlb(zzm2.zza, zzkz.zze, zzkz.zza, zzkz.zzb, zzc3);
                String str2 = "Setting user property";
                if (!zzkz.zzb() || !this.zzj.zzb().zze(zzm2.zza, zzap.zzcy)) {
                    this.zzj.zzr().zzw().zza(str2, this.zzj.zzj().zzc(zzlb.zzc), zzc3);
                } else {
                    this.zzj.zzr().zzx().zza(str2, this.zzj.zzj().zzc(zzlb.zzc), zzc3);
                }
                zze().zzf();
                try {
                    zzc(zzm2);
                    boolean zza5 = zze().zza(zzlb);
                    zze().mo16206b_();
                    if (!zza5) {
                        this.zzj.zzr().zzf().zza("Too many unique user properties are set. Ignoring user property", this.zzj.zzj().zzc(zzlb.zzc), zzlb.zze);
                        this.zzj.zzi().zza(zzm2.zza, 9, (String) null, (String) null, 0);
                    } else if (!zzkz.zzb() || !this.zzj.zzb().zze(zzm2.zza, zzap.zzcy)) {
                        this.zzj.zzr().zzw().zza("User property set", this.zzj.zzj().zzc(zzlb.zzc), zzlb.zze);
                    }
                } finally {
                    zze().zzh();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzkz zzkz, zzm zzm2) {
        zzw();
        zzk();
        if (zze(zzm2)) {
            if (!zzm2.zzh) {
                zzc(zzm2);
                return;
            }
            String str = "User property removed";
            String str2 = "Removing user property";
            if (this.zzj.zzb().zze(zzm2.zza, zzap.zzbd)) {
                if (!"_npa".equals(zzkz.zza) || zzm2.zzs == null) {
                    this.zzj.zzr().zzw().zza(str2, this.zzj.zzj().zzc(zzkz.zza));
                    zze().zzf();
                    try {
                        zzc(zzm2);
                        zze().zzb(zzm2.zza, zzkz.zza);
                        zze().mo16206b_();
                        this.zzj.zzr().zzw().zza(str, this.zzj.zzj().zzc(zzkz.zza));
                    } finally {
                        zze().zzh();
                    }
                } else {
                    this.zzj.zzr().zzw().zza("Falling back to manifest metadata value for ad personalization");
                    zzkz zzkz2 = new zzkz("_npa", this.zzj.zzm().currentTimeMillis(), Long.valueOf(zzm2.zzs.booleanValue() ? 1 : 0), "auto");
                    zza(zzkz2, zzm2);
                }
            } else {
                this.zzj.zzr().zzw().zza(str2, this.zzj.zzj().zzc(zzkz.zza));
                zze().zzf();
                try {
                    zzc(zzm2);
                    zze().zzb(zzm2.zza, zzkz.zza);
                    zze().mo16206b_();
                    this.zzj.zzr().zzw().zza(str, this.zzj.zzj().zzc(zzkz.zza));
                } finally {
                    zze().zzh();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzkp zzkp) {
        this.zzo++;
    }

    /* access modifiers changed from: 0000 */
    public final void zzp() {
        this.zzp++;
    }

    /* access modifiers changed from: 0000 */
    public final zzgq zzs() {
        return this.zzj;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04e8 A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01fb A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0237 A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x025a A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x025c A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0264 A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0272 A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0286 A[Catch:{ NameNotFoundException -> 0x0368, all -> 0x0516 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.measurement.internal.zzm r22) {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            java.lang.String r3 = "_sys"
            java.lang.String r4 = "_pfo"
            java.lang.String r5 = "_uwa"
            java.lang.String r0 = "app_id=?"
            r21.zzw()
            r21.zzk()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r22)
            java.lang.String r6 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)
            boolean r6 = r21.zze(r22)
            if (r6 != 0) goto L_0x0021
            return
        L_0x0021:
            com.google.android.gms.measurement.internal.zzac r6 = r21.zze()
            java.lang.String r7 = r2.zza
            com.google.android.gms.measurement.internal.zzg r6 = r6.zzb(r7)
            r7 = 0
            if (r6 == 0) goto L_0x0054
            java.lang.String r9 = r6.zze()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 == 0) goto L_0x0054
            java.lang.String r9 = r2.zzb
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x0054
            r6.zzh(r7)
            com.google.android.gms.measurement.internal.zzac r9 = r21.zze()
            r9.zza(r6)
            com.google.android.gms.measurement.internal.zzgk r6 = r21.zzc()
            java.lang.String r9 = r2.zza
            r6.zzd(r9)
        L_0x0054:
            boolean r6 = r2.zzh
            if (r6 != 0) goto L_0x005c
            r21.zzc(r22)
            return
        L_0x005c:
            long r9 = r2.zzm
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x006d
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj
            com.google.android.gms.common.util.Clock r6 = r6.zzm()
            long r9 = r6.currentTimeMillis()
        L_0x006d:
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzb()
            java.lang.String r11 = r2.zza
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzbd
            boolean r6 = r6.zze(r11, r12)
            if (r6 == 0) goto L_0x0088
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj
            com.google.android.gms.measurement.internal.zzah r6 = r6.zzx()
            r6.zzi()
        L_0x0088:
            int r6 = r2.zzn
            r15 = 0
            r13 = 1
            if (r6 == 0) goto L_0x00ab
            if (r6 == r13) goto L_0x00ab
            com.google.android.gms.measurement.internal.zzgq r11 = r1.zzj
            com.google.android.gms.measurement.internal.zzfj r11 = r11.zzr()
            com.google.android.gms.measurement.internal.zzfl r11 = r11.zzi()
            java.lang.String r12 = r2.zza
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfj.zza(r12)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r14 = "Incorrect app type, assuming installed app. appId, appType"
            r11.zza(r14, r12, r6)
            r6 = 0
        L_0x00ab:
            com.google.android.gms.measurement.internal.zzac r11 = r21.zze()
            r11.zzf()
            com.google.android.gms.measurement.internal.zzgq r11 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r11 = r11.zzb()     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzap.zzbd     // Catch:{ all -> 0x0516 }
            boolean r11 = r11.zze(r12, r14)     // Catch:{ all -> 0x0516 }
            if (r11 == 0) goto L_0x0133
            com.google.android.gms.measurement.internal.zzac r11 = r21.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = "_npa"
            com.google.android.gms.measurement.internal.zzlb r14 = r11.zzc(r12, r14)     // Catch:{ all -> 0x0516 }
            if (r14 == 0) goto L_0x00e1
            java.lang.String r11 = "auto"
            java.lang.String r12 = r14.zzb     // Catch:{ all -> 0x0516 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0516 }
            if (r11 == 0) goto L_0x00dd
            goto L_0x00e1
        L_0x00dd:
            r19 = r3
            r3 = 1
            goto L_0x0136
        L_0x00e1:
            java.lang.Boolean r11 = r2.zzs     // Catch:{ all -> 0x0516 }
            if (r11 == 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzkz r12 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x0516 }
            java.lang.String r16 = "_npa"
            java.lang.Boolean r11 = r2.zzs     // Catch:{ all -> 0x0516 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0516 }
            if (r11 == 0) goto L_0x00f4
            r17 = 1
            goto L_0x00f6
        L_0x00f4:
            r17 = 0
        L_0x00f6:
            java.lang.Long r17 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x0516 }
            java.lang.String r18 = "auto"
            r11 = r12
            r7 = r12
            r12 = r16
            r19 = r3
            r8 = r14
            r3 = 1
            r13 = r9
            r15 = r17
            r16 = r18
            r11.<init>(r12, r13, r15, r16)     // Catch:{ all -> 0x0516 }
            if (r8 == 0) goto L_0x0118
            java.lang.Object r8 = r8.zze     // Catch:{ all -> 0x0516 }
            java.lang.Long r11 = r7.zzc     // Catch:{ all -> 0x0516 }
            boolean r8 = r8.equals(r11)     // Catch:{ all -> 0x0516 }
            if (r8 != 0) goto L_0x0132
        L_0x0118:
            r1.zza(r7, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x0132
        L_0x011c:
            r19 = r3
            r8 = r14
            r3 = 1
            if (r8 == 0) goto L_0x0132
            com.google.android.gms.measurement.internal.zzkz r7 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_npa"
            r15 = 0
            java.lang.String r16 = "auto"
            r11 = r7
            r13 = r9
            r11.<init>(r12, r13, r15, r16)     // Catch:{ all -> 0x0516 }
            r1.zzb(r7, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x0136
        L_0x0132:
            goto L_0x0136
        L_0x0133:
            r19 = r3
            r3 = 1
        L_0x0136:
            com.google.android.gms.measurement.internal.zzac r7 = r21.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r8 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzg r7 = r7.zzb(r8)     // Catch:{ all -> 0x0516 }
            if (r7 == 0) goto L_0x01f8
            com.google.android.gms.measurement.internal.zzgq r11 = r1.zzj     // Catch:{ all -> 0x0516 }
            r11.zzi()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = r2.zzb     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = r7.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r13 = r2.zzr     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = r7.zzf()     // Catch:{ all -> 0x0516 }
            boolean r11 = com.google.android.gms.measurement.internal.zzla.zza(r11, r12, r13, r14)     // Catch:{ all -> 0x0516 }
            if (r11 == 0) goto L_0x01f6
            com.google.android.gms.measurement.internal.zzgq r11 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfj r11 = r11.zzr()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfl r11 = r11.zzi()     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r13 = r7.zzc()     // Catch:{ all -> 0x0516 }
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfj.zza(r13)     // Catch:{ all -> 0x0516 }
            r11.zza(r12, r13)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzac r11 = r21.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r7 = r7.zzc()     // Catch:{ all -> 0x0516 }
            r11.zzak()     // Catch:{ all -> 0x0516 }
            r11.zzd()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)     // Catch:{ all -> 0x0516 }
            android.database.sqlite.SQLiteDatabase r12 = r11.mo16207c_()     // Catch:{ SQLiteException -> 0x01e1 }
            java.lang.String[] r13 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x01e1 }
            r15 = 0
            r13[r15] = r7     // Catch:{ SQLiteException -> 0x01df }
            java.lang.String r14 = "events"
            int r14 = r12.delete(r14, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r15
            java.lang.String r8 = "user_attributes"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "conditional_properties"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "apps"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "raw_events"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "raw_events_metadata"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "event_filters"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "property_filters"
            int r8 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r8
            java.lang.String r8 = "audience_filter_values"
            int r0 = r12.delete(r8, r0, r13)     // Catch:{ SQLiteException -> 0x01df }
            int r14 = r14 + r0
            if (r14 <= 0) goto L_0x01de
            com.google.android.gms.measurement.internal.zzfj r0 = r11.zzr()     // Catch:{ SQLiteException -> 0x01df }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzx()     // Catch:{ SQLiteException -> 0x01df }
            java.lang.String r8 = "Deleted application data. app, records"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r14)     // Catch:{ SQLiteException -> 0x01df }
            r0.zza(r8, r7, r12)     // Catch:{ SQLiteException -> 0x01df }
        L_0x01de:
            goto L_0x01f4
        L_0x01df:
            r0 = move-exception
            goto L_0x01e3
        L_0x01e1:
            r0 = move-exception
            r15 = 0
        L_0x01e3:
            com.google.android.gms.measurement.internal.zzfj r8 = r11.zzr()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfl r8 = r8.zzf()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = "Error deleting application data. appId, error"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfj.zza(r7)     // Catch:{ all -> 0x0516 }
            r8.zza(r11, r7, r0)     // Catch:{ all -> 0x0516 }
        L_0x01f4:
            r7 = 0
            goto L_0x01f9
        L_0x01f6:
            r15 = 0
            goto L_0x01f9
        L_0x01f8:
            r15 = 0
        L_0x01f9:
            if (r7 == 0) goto L_0x025c
            long r11 = r7.zzm()     // Catch:{ all -> 0x0516 }
            r13 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0214
            long r11 = r7.zzm()     // Catch:{ all -> 0x0516 }
            r8 = r4
            long r3 = r2.zzj     // Catch:{ all -> 0x0516 }
            int r0 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x0215
            r0 = 1
            goto L_0x0216
        L_0x0214:
            r8 = r4
        L_0x0215:
            r0 = 0
        L_0x0216:
            long r3 = r7.zzm()     // Catch:{ all -> 0x0516 }
            int r11 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r11 != 0) goto L_0x0233
            java.lang.String r3 = r7.zzl()     // Catch:{ all -> 0x0516 }
            if (r3 == 0) goto L_0x0233
            java.lang.String r3 = r7.zzl()     // Catch:{ all -> 0x0516 }
            java.lang.String r4 = r2.zzc     // Catch:{ all -> 0x0516 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0516 }
            if (r3 != 0) goto L_0x0233
            r3 = 1
            goto L_0x0234
        L_0x0233:
            r3 = 0
        L_0x0234:
            r0 = r0 | r3
            if (r0 == 0) goto L_0x025a
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0516 }
            r0.<init>()     // Catch:{ all -> 0x0516 }
            java.lang.String r3 = "_pv"
            java.lang.String r4 = r7.zzl()     // Catch:{ all -> 0x0516 }
            r0.putString(r3, r4)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzan r3 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_au"
            com.google.android.gms.measurement.internal.zzam r13 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x0516 }
            r13.<init>(r0)     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = "auto"
            r11 = r3
            r4 = 0
            r15 = r9
            r11.<init>(r12, r13, r14, r15)     // Catch:{ all -> 0x0516 }
            r1.zza(r3, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x025e
        L_0x025a:
            r4 = 0
            goto L_0x025e
        L_0x025c:
            r8 = r4
            r4 = 0
        L_0x025e:
            r21.zzc(r22)     // Catch:{ all -> 0x0516 }
            if (r6 != 0) goto L_0x0272
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x0516 }
            java.lang.String r7 = "_f"
            com.google.android.gms.measurement.internal.zzaj r0 = r0.zza(r3, r7)     // Catch:{ all -> 0x0516 }
            goto L_0x0284
        L_0x0272:
            r3 = 1
            if (r6 != r3) goto L_0x0283
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x0516 }
            java.lang.String r7 = "_v"
            com.google.android.gms.measurement.internal.zzaj r0 = r0.zza(r3, r7)     // Catch:{ all -> 0x0516 }
            goto L_0x0284
        L_0x0283:
            r0 = 0
        L_0x0284:
            if (r0 != 0) goto L_0x04e8
            r11 = 3600000(0x36ee80, double:1.7786363E-317)
            long r13 = r9 / r11
            r15 = 1
            long r13 = r13 + r15
            long r13 = r13 * r11
            java.lang.String r0 = "_dac"
            java.lang.String r3 = "_r"
            java.lang.String r7 = "_c"
            java.lang.String r15 = "_et"
            if (r6 != 0) goto L_0x0442
            com.google.android.gms.measurement.internal.zzkz r6 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_fot"
            java.lang.Long r16 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x0516 }
            java.lang.String r20 = "auto"
            r11 = r6
            r13 = r9
            r4 = r15
            r15 = r16
            r16 = r20
            r11.<init>(r12, r13, r15, r16)     // Catch:{ all -> 0x0516 }
            r1.zza(r6, r2)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r6 = r6.zzb()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = r2.zzb     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzar     // Catch:{ all -> 0x0516 }
            boolean r6 = r6.zze(r11, r12)     // Catch:{ all -> 0x0516 }
            if (r6 == 0) goto L_0x02d1
            r21.zzw()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgq r6 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgb r6 = r6.zzf()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0516 }
            r6.zza(r11)     // Catch:{ all -> 0x0516 }
        L_0x02d1:
            r21.zzw()     // Catch:{ all -> 0x0516 }
            r21.zzk()     // Catch:{ all -> 0x0516 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ all -> 0x0516 }
            r6.<init>()     // Catch:{ all -> 0x0516 }
            r11 = 1
            r6.putLong(r7, r11)     // Catch:{ all -> 0x0516 }
            r6.putLong(r3, r11)     // Catch:{ all -> 0x0516 }
            r11 = 0
            r6.putLong(r5, r11)     // Catch:{ all -> 0x0516 }
            r6.putLong(r8, r11)     // Catch:{ all -> 0x0516 }
            r3 = r19
            r6.putLong(r3, r11)     // Catch:{ all -> 0x0516 }
            java.lang.String r7 = "_sysu"
            r6.putLong(r7, r11)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r7 = r7.zzb()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzap.zzbb     // Catch:{ all -> 0x0516 }
            boolean r7 = r7.zze(r11, r12)     // Catch:{ all -> 0x0516 }
            if (r7 == 0) goto L_0x030d
            r11 = 1
            r6.putLong(r4, r11)     // Catch:{ all -> 0x0516 }
        L_0x030d:
            boolean r7 = r2.zzq     // Catch:{ all -> 0x0516 }
            if (r7 == 0) goto L_0x0316
            r11 = 1
            r6.putLong(r0, r11)     // Catch:{ all -> 0x0516 }
        L_0x0316:
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x0516 }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)     // Catch:{ all -> 0x0516 }
            r0.zzd()     // Catch:{ all -> 0x0516 }
            r0.zzak()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = "first_open_count"
            long r13 = r0.zzh(r7, r11)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzj     // Catch:{ all -> 0x0516 }
            android.content.Context r0 = r0.zzn()     // Catch:{ all -> 0x0516 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x0516 }
            if (r0 != 0) goto L_0x0354
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfj r0 = r0.zzr()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfl r0 = r0.zzf()     // Catch:{ all -> 0x0516 }
            java.lang.String r3 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r5 = r2.zza     // Catch:{ all -> 0x0516 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfj.zza(r5)     // Catch:{ all -> 0x0516 }
            r0.zza(r3, r5)     // Catch:{ all -> 0x0516 }
            r19 = r8
            r7 = r13
            goto L_0x0423
        L_0x0354:
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzj     // Catch:{ NameNotFoundException -> 0x0368 }
            android.content.Context r0 = r0.zzn()     // Catch:{ NameNotFoundException -> 0x0368 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0368 }
            java.lang.String r7 = r2.zza     // Catch:{ NameNotFoundException -> 0x0368 }
            r11 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r7, r11)     // Catch:{ NameNotFoundException -> 0x0368 }
            goto L_0x0380
        L_0x0368:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgq r7 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfj r7 = r7.zzr()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfl r7 = r7.zzf()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0516 }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfj.zza(r12)     // Catch:{ all -> 0x0516 }
            r7.zza(r11, r12, r0)     // Catch:{ all -> 0x0516 }
            r0 = 0
        L_0x0380:
            if (r0 == 0) goto L_0x03da
            long r11 = r0.firstInstallTime     // Catch:{ all -> 0x0516 }
            r15 = 0
            int r7 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r7 == 0) goto L_0x03d6
            long r11 = r0.firstInstallTime     // Catch:{ all -> 0x0516 }
            r19 = r8
            long r7 = r0.lastUpdateTime     // Catch:{ all -> 0x0516 }
            int r0 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x03ba
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r0 = r0.zzb()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzcn     // Catch:{ all -> 0x0516 }
            boolean r0 = r0.zza(r7)     // Catch:{ all -> 0x0516 }
            if (r0 == 0) goto L_0x03b3
            r7 = 0
            int r0 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x03b0
            r7 = 1
            r6.putLong(r5, r7)     // Catch:{ all -> 0x0516 }
            goto L_0x03b8
        L_0x03b0:
            r7 = 1
            goto L_0x03b8
        L_0x03b3:
            r7 = 1
            r6.putLong(r5, r7)     // Catch:{ all -> 0x0516 }
        L_0x03b8:
            r0 = 0
            goto L_0x03bb
        L_0x03ba:
            r0 = 1
        L_0x03bb:
            com.google.android.gms.measurement.internal.zzkz r5 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_fi"
            if (r0 == 0) goto L_0x03c4
            r7 = 1
            goto L_0x03c6
        L_0x03c4:
            r7 = 0
        L_0x03c6:
            java.lang.Long r15 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0516 }
            java.lang.String r16 = "auto"
            r11 = r5
            r7 = r13
            r13 = r9
            r11.<init>(r12, r13, r15, r16)     // Catch:{ all -> 0x0516 }
            r1.zza(r5, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x03dd
        L_0x03d6:
            r19 = r8
            r7 = r13
            goto L_0x03dd
        L_0x03da:
            r19 = r8
            r7 = r13
        L_0x03dd:
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzj     // Catch:{ NameNotFoundException -> 0x03f1 }
            android.content.Context r0 = r0.zzn()     // Catch:{ NameNotFoundException -> 0x03f1 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x03f1 }
            java.lang.String r5 = r2.zza     // Catch:{ NameNotFoundException -> 0x03f1 }
            r11 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r5, r11)     // Catch:{ NameNotFoundException -> 0x03f1 }
            goto L_0x0409
        L_0x03f1:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgq r5 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfj r5 = r5.zzr()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfl r5 = r5.zzf()     // Catch:{ all -> 0x0516 }
            java.lang.String r11 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0516 }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfj.zza(r12)     // Catch:{ all -> 0x0516 }
            r5.zza(r11, r12, r0)     // Catch:{ all -> 0x0516 }
            r0 = 0
        L_0x0409:
            if (r0 == 0) goto L_0x0423
            int r5 = r0.flags     // Catch:{ all -> 0x0516 }
            r11 = 1
            r5 = r5 & r11
            if (r5 == 0) goto L_0x0416
            r11 = 1
            r6.putLong(r3, r11)     // Catch:{ all -> 0x0516 }
        L_0x0416:
            int r0 = r0.flags     // Catch:{ all -> 0x0516 }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0423
            java.lang.String r0 = "_sysu"
            r11 = 1
            r6.putLong(r0, r11)     // Catch:{ all -> 0x0516 }
        L_0x0423:
            r11 = 0
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 < 0) goto L_0x042e
            r3 = r19
            r6.putLong(r3, r7)     // Catch:{ all -> 0x0516 }
        L_0x042e:
            com.google.android.gms.measurement.internal.zzan r0 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_f"
            com.google.android.gms.measurement.internal.zzam r13 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x0516 }
            r13.<init>(r6)     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = "auto"
            r11 = r0
            r15 = r9
            r11.<init>(r12, r13, r14, r15)     // Catch:{ all -> 0x0516 }
            r1.zza(r0, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x049f
        L_0x0442:
            r4 = r15
            r5 = 1
            if (r6 != r5) goto L_0x049f
            com.google.android.gms.measurement.internal.zzkz r5 = new com.google.android.gms.measurement.internal.zzkz     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_fvt"
            java.lang.Long r15 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x0516 }
            java.lang.String r16 = "auto"
            r11 = r5
            r13 = r9
            r11.<init>(r12, r13, r15, r16)     // Catch:{ all -> 0x0516 }
            r1.zza(r5, r2)     // Catch:{ all -> 0x0516 }
            r21.zzw()     // Catch:{ all -> 0x0516 }
            r21.zzk()     // Catch:{ all -> 0x0516 }
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x0516 }
            r5.<init>()     // Catch:{ all -> 0x0516 }
            r11 = 1
            r5.putLong(r7, r11)     // Catch:{ all -> 0x0516 }
            r5.putLong(r3, r11)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r3 = r3.zzb()     // Catch:{ all -> 0x0516 }
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzap.zzbb     // Catch:{ all -> 0x0516 }
            boolean r3 = r3.zze(r6, r7)     // Catch:{ all -> 0x0516 }
            if (r3 == 0) goto L_0x0482
            r6 = 1
            r5.putLong(r4, r6)     // Catch:{ all -> 0x0516 }
        L_0x0482:
            boolean r3 = r2.zzq     // Catch:{ all -> 0x0516 }
            if (r3 == 0) goto L_0x048b
            r6 = 1
            r5.putLong(r0, r6)     // Catch:{ all -> 0x0516 }
        L_0x048b:
            com.google.android.gms.measurement.internal.zzan r0 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_v"
            com.google.android.gms.measurement.internal.zzam r13 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x0516 }
            r13.<init>(r5)     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = "auto"
            r11 = r0
            r15 = r9
            r11.<init>(r12, r13, r14, r15)     // Catch:{ all -> 0x0516 }
            r1.zza(r0, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x04a0
        L_0x049f:
        L_0x04a0:
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r0 = r0.zzb()     // Catch:{ all -> 0x0516 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzap.zzbc     // Catch:{ all -> 0x0516 }
            boolean r0 = r0.zze(r3, r5)     // Catch:{ all -> 0x0516 }
            if (r0 != 0) goto L_0x0506
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0516 }
            r0.<init>()     // Catch:{ all -> 0x0516 }
            r5 = 1
            r0.putLong(r4, r5)     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzgq r3 = r1.zzj     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzx r3 = r3.zzb()     // Catch:{ all -> 0x0516 }
            java.lang.String r4 = r2.zza     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzfc<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzap.zzbb     // Catch:{ all -> 0x0516 }
            boolean r3 = r3.zze(r4, r5)     // Catch:{ all -> 0x0516 }
            if (r3 == 0) goto L_0x04d4
            java.lang.String r3 = "_fr"
            r4 = 1
            r0.putLong(r3, r4)     // Catch:{ all -> 0x0516 }
        L_0x04d4:
            com.google.android.gms.measurement.internal.zzan r3 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_e"
            com.google.android.gms.measurement.internal.zzam r13 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x0516 }
            r13.<init>(r0)     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = "auto"
            r11 = r3
            r15 = r9
            r11.<init>(r12, r13, r14, r15)     // Catch:{ all -> 0x0516 }
            r1.zza(r3, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x0506
        L_0x04e8:
            boolean r0 = r2.zzi     // Catch:{ all -> 0x0516 }
            if (r0 == 0) goto L_0x0506
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0516 }
            r0.<init>()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzan r3 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0516 }
            java.lang.String r12 = "_cd"
            com.google.android.gms.measurement.internal.zzam r13 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x0516 }
            r13.<init>(r0)     // Catch:{ all -> 0x0516 }
            java.lang.String r14 = "auto"
            r11 = r3
            r15 = r9
            r11.<init>(r12, r13, r14, r15)     // Catch:{ all -> 0x0516 }
            r1.zza(r3, r2)     // Catch:{ all -> 0x0516 }
            goto L_0x0507
        L_0x0506:
        L_0x0507:
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x0516 }
            r0.mo16206b_()     // Catch:{ all -> 0x0516 }
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()
            r0.zzh()
            return
        L_0x0516:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzac r2 = r21.zze()
            r2.zzh()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.zzb(com.google.android.gms.measurement.internal.zzm):void");
    }

    private final zzm zza(String str) {
        String str2;
        String str3 = str;
        zzg zzb2 = zze().zzb(str3);
        if (zzb2 == null || TextUtils.isEmpty(zzb2.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping", str3);
            return null;
        }
        Boolean zzb3 = zzb(zzb2);
        if (zzb3 == null || zzb3.booleanValue()) {
            String zze2 = zzb2.zze();
            String zzl2 = zzb2.zzl();
            long zzm2 = zzb2.zzm();
            String zzn2 = zzb2.zzn();
            long zzo2 = zzb2.zzo();
            long zzp2 = zzb2.zzp();
            boolean zzr2 = zzb2.zzr();
            String zzi2 = zzb2.zzi();
            long zzae = zzb2.zzae();
            boolean zzaf = zzb2.zzaf();
            boolean zzag = zzb2.zzag();
            String zzf2 = zzb2.zzf();
            Boolean zzah = zzb2.zzah();
            long zzq2 = zzb2.zzq();
            List zzai = zzb2.zzai();
            if (!zzll.zzb() || !this.zzj.zzb().zze(str3, zzap.zzch)) {
                str2 = null;
            } else {
                str2 = zzb2.zzg();
            }
            zzm zzm3 = new zzm(str, zze2, zzl2, zzm2, zzn2, zzo2, zzp2, (String) null, zzr2, false, zzi2, zzae, 0, 0, zzaf, zzag, false, zzf2, zzah, zzq2, zzai, str2);
            return zzm3;
        }
        this.zzj.zzr().zzf().zza("App version does not match; dropping. appId", zzfj.zza(str));
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzv zzv2) {
        zzm zza2 = zza(zzv2.zza);
        if (zza2 != null) {
            zza(zzv2, zza2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzv zzv2, zzm zzm2) {
        Preconditions.checkNotNull(zzv2);
        Preconditions.checkNotEmpty(zzv2.zza);
        Preconditions.checkNotNull(zzv2.zzb);
        Preconditions.checkNotNull(zzv2.zzc);
        Preconditions.checkNotEmpty(zzv2.zzc.zza);
        zzw();
        zzk();
        if (zze(zzm2)) {
            if (!zzm2.zzh) {
                zzc(zzm2);
                return;
            }
            zzv zzv3 = new zzv(zzv2);
            boolean z = false;
            zzv3.zze = false;
            zze().zzf();
            try {
                zzv zzd2 = zze().zzd(zzv3.zza, zzv3.zzc.zza);
                if (zzd2 != null && !zzd2.zzb.equals(zzv3.zzb)) {
                    this.zzj.zzr().zzi().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzj.zzj().zzc(zzv3.zzc.zza), zzv3.zzb, zzd2.zzb);
                }
                if (zzd2 != null && zzd2.zze) {
                    zzv3.zzb = zzd2.zzb;
                    zzv3.zzd = zzd2.zzd;
                    zzv3.zzh = zzd2.zzh;
                    zzv3.zzf = zzd2.zzf;
                    zzv3.zzi = zzd2.zzi;
                    zzv3.zze = zzd2.zze;
                    zzkz zzkz = new zzkz(zzv3.zzc.zza, zzd2.zzc.zzb, zzv3.zzc.zza(), zzd2.zzc.zze);
                    zzv3.zzc = zzkz;
                } else if (TextUtils.isEmpty(zzv3.zzf)) {
                    zzkz zzkz2 = new zzkz(zzv3.zzc.zza, zzv3.zzd, zzv3.zzc.zza(), zzv3.zzc.zze);
                    zzv3.zzc = zzkz2;
                    zzv3.zze = true;
                    z = true;
                }
                if (zzv3.zze) {
                    zzkz zzkz3 = zzv3.zzc;
                    zzlb zzlb = new zzlb(zzv3.zza, zzv3.zzb, zzkz3.zza, zzkz3.zzb, zzkz3.zza());
                    if (zze().zza(zzlb)) {
                        this.zzj.zzr().zzw().zza("User property updated immediately", zzv3.zza, this.zzj.zzj().zzc(zzlb.zzc), zzlb.zze);
                    } else {
                        this.zzj.zzr().zzf().zza("(2)Too many active user properties, ignoring", zzfj.zza(zzv3.zza), this.zzj.zzj().zzc(zzlb.zzc), zzlb.zze);
                    }
                    if (z && zzv3.zzi != null) {
                        zzb(new zzan(zzv3.zzi, zzv3.zzd), zzm2);
                    }
                }
                if (zze().zza(zzv3)) {
                    this.zzj.zzr().zzw().zza("Conditional property added", zzv3.zza, this.zzj.zzj().zzc(zzv3.zzc.zza), zzv3.zzc.zza());
                } else {
                    this.zzj.zzr().zzf().zza("Too many conditional properties, ignoring", zzfj.zza(zzv3.zza), this.zzj.zzj().zzc(zzv3.zzc.zza), zzv3.zzc.zza());
                }
                zze().mo16206b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzv zzv2) {
        zzm zza2 = zza(zzv2.zza);
        if (zza2 != null) {
            zzb(zzv2, zza2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzv zzv2, zzm zzm2) {
        Bundle bundle;
        Preconditions.checkNotNull(zzv2);
        Preconditions.checkNotEmpty(zzv2.zza);
        Preconditions.checkNotNull(zzv2.zzc);
        Preconditions.checkNotEmpty(zzv2.zzc.zza);
        zzw();
        zzk();
        if (zze(zzm2)) {
            if (!zzm2.zzh) {
                zzc(zzm2);
                return;
            }
            zze().zzf();
            try {
                zzc(zzm2);
                zzv zzd2 = zze().zzd(zzv2.zza, zzv2.zzc.zza);
                if (zzd2 != null) {
                    this.zzj.zzr().zzw().zza("Removing conditional user property", zzv2.zza, this.zzj.zzj().zzc(zzv2.zzc.zza));
                    zze().zze(zzv2.zza, zzv2.zzc.zza);
                    if (zzd2.zze) {
                        zze().zzb(zzv2.zza, zzv2.zzc.zza);
                    }
                    if (zzv2.zzk != null) {
                        if (zzv2.zzk.zzb != null) {
                            bundle = zzv2.zzk.zzb.zzb();
                        } else {
                            bundle = null;
                        }
                        zzb(this.zzj.zzi().zza(zzv2.zza, zzv2.zzk.zza, bundle, zzd2.zzb, zzv2.zzk.zzd, true, false), zzm2);
                    }
                } else {
                    this.zzj.zzr().zzi().zza("Conditional user property doesn't exist", zzfj.zza(zzv2.zza), this.zzj.zzj().zzc(zzv2.zzc.zza));
                }
                zze().mo16206b_();
            } finally {
                zze().zzh();
            }
        }
    }

    private final zzg zza(zzm zzm2, zzg zzg2, String str) {
        boolean z;
        if (zzg2 == null) {
            zzg2 = new zzg(this.zzj, zzm2.zza);
            zzg2.zza(this.zzj.zzi().zzk());
            zzg2.zze(str);
            z = true;
        } else if (!str.equals(zzg2.zzh())) {
            zzg2.zze(str);
            zzg2.zza(this.zzj.zzi().zzk());
            z = true;
        } else {
            z = false;
        }
        if (!TextUtils.equals(zzm2.zzb, zzg2.zze())) {
            zzg2.zzb(zzm2.zzb);
            z = true;
        }
        if (!TextUtils.equals(zzm2.zzr, zzg2.zzf())) {
            zzg2.zzc(zzm2.zzr);
            z = true;
        }
        if (zzll.zzb() && this.zzj.zzb().zze(zzg2.zzc(), zzap.zzch) && !TextUtils.equals(zzm2.zzv, zzg2.zzg())) {
            zzg2.zzd(zzm2.zzv);
            z = true;
        }
        if (!TextUtils.isEmpty(zzm2.zzk) && !zzm2.zzk.equals(zzg2.zzi())) {
            zzg2.zzf(zzm2.zzk);
            z = true;
        }
        if (!(zzm2.zze == 0 || zzm2.zze == zzg2.zzo())) {
            zzg2.zzd(zzm2.zze);
            z = true;
        }
        if (!TextUtils.isEmpty(zzm2.zzc) && !zzm2.zzc.equals(zzg2.zzl())) {
            zzg2.zzg(zzm2.zzc);
            z = true;
        }
        if (zzm2.zzj != zzg2.zzm()) {
            zzg2.zzc(zzm2.zzj);
            z = true;
        }
        if (zzm2.zzd != null && !zzm2.zzd.equals(zzg2.zzn())) {
            zzg2.zzh(zzm2.zzd);
            z = true;
        }
        if (zzm2.zzf != zzg2.zzp()) {
            zzg2.zze(zzm2.zzf);
            z = true;
        }
        if (zzm2.zzh != zzg2.zzr()) {
            zzg2.zza(zzm2.zzh);
            z = true;
        }
        if (!TextUtils.isEmpty(zzm2.zzg) && !zzm2.zzg.equals(zzg2.zzac())) {
            zzg2.zzi(zzm2.zzg);
            z = true;
        }
        if (!this.zzj.zzb().zza(zzap.zzdh) && zzm2.zzl != zzg2.zzae()) {
            zzg2.zzp(zzm2.zzl);
            z = true;
        }
        if (zzm2.zzo != zzg2.zzaf()) {
            zzg2.zzb(zzm2.zzo);
            z = true;
        }
        if (zzm2.zzp != zzg2.zzag()) {
            zzg2.zzc(zzm2.zzp);
            z = true;
        }
        if (this.zzj.zzb().zze(zzm2.zza, zzap.zzbd) && zzm2.zzs != zzg2.zzah()) {
            zzg2.zza(zzm2.zzs);
            z = true;
        }
        if (!(zzm2.zzt == 0 || zzm2.zzt == zzg2.zzq())) {
            zzg2.zzf(zzm2.zzt);
            z = true;
        }
        if (z) {
            zze().zza(zzg2);
        }
        return zzg2;
    }

    /* access modifiers changed from: 0000 */
    public final zzg zzc(zzm zzm2) {
        zzw();
        zzk();
        Preconditions.checkNotNull(zzm2);
        Preconditions.checkNotEmpty(zzm2.zza);
        zzg zzb2 = zze().zzb(zzm2.zza);
        String zzb3 = this.zzj.zzc().zzb(zzm2.zza);
        if (!zzkt.zzb() || !this.zzj.zzb().zza(zzap.zzcp)) {
            return zza(zzm2, zzb2, zzb3);
        }
        if (zzb2 == null) {
            zzb2 = new zzg(this.zzj, zzm2.zza);
            zzb2.zza(this.zzj.zzi().zzk());
            zzb2.zze(zzb3);
        } else if (!zzb3.equals(zzb2.zzh())) {
            zzb2.zze(zzb3);
            zzb2.zza(this.zzj.zzi().zzk());
        }
        zzb2.zzb(zzm2.zzb);
        zzb2.zzc(zzm2.zzr);
        if (zzll.zzb() && this.zzj.zzb().zze(zzb2.zzc(), zzap.zzch)) {
            zzb2.zzd(zzm2.zzv);
        }
        if (!TextUtils.isEmpty(zzm2.zzk)) {
            zzb2.zzf(zzm2.zzk);
        }
        if (zzm2.zze != 0) {
            zzb2.zzd(zzm2.zze);
        }
        if (!TextUtils.isEmpty(zzm2.zzc)) {
            zzb2.zzg(zzm2.zzc);
        }
        zzb2.zzc(zzm2.zzj);
        if (zzm2.zzd != null) {
            zzb2.zzh(zzm2.zzd);
        }
        zzb2.zze(zzm2.zzf);
        zzb2.zza(zzm2.zzh);
        if (!TextUtils.isEmpty(zzm2.zzg)) {
            zzb2.zzi(zzm2.zzg);
        }
        if (!this.zzj.zzb().zza(zzap.zzdh)) {
            zzb2.zzp(zzm2.zzl);
        }
        zzb2.zzb(zzm2.zzo);
        zzb2.zzc(zzm2.zzp);
        if (this.zzj.zzb().zze(zzm2.zza, zzap.zzbd)) {
            zzb2.zza(zzm2.zzs);
        }
        zzb2.zzf(zzm2.zzt);
        if (zzb2.zza()) {
            zze().zza(zzb2);
        }
        return zzb2;
    }

    /* access modifiers changed from: 0000 */
    public final String zzd(zzm zzm2) {
        try {
            return (String) this.zzj.zzq().zza((Callable<V>) new zzkv<V>(this, zzm2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzj.zzr().zzf().zza("Failed to get app instance id. appId", zzfj.zza(zzm2.zza), e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(boolean z) {
        zzz();
    }

    private final boolean zze(zzm zzm2) {
        return (!zzll.zzb() || !this.zzj.zzb().zze(zzm2.zza, zzap.zzch)) ? !TextUtils.isEmpty(zzm2.zzb) || !TextUtils.isEmpty(zzm2.zzr) : !TextUtils.isEmpty(zzm2.zzb) || !TextUtils.isEmpty(zzm2.zzv) || !TextUtils.isEmpty(zzm2.zzr);
    }
}
