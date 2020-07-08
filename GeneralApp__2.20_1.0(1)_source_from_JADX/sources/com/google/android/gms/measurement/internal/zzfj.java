package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzfj extends zzhk {
    /* access modifiers changed from: private */
    public char zza = 0;
    /* access modifiers changed from: private */
    public long zzb = -1;
    private String zzc;
    private final zzfl zzd = new zzfl(this, 6, false, false);
    private final zzfl zze = new zzfl(this, 6, true, false);
    private final zzfl zzf = new zzfl(this, 6, false, true);
    private final zzfl zzg = new zzfl(this, 5, false, false);
    private final zzfl zzh = new zzfl(this, 5, true, false);
    private final zzfl zzi = new zzfl(this, 5, false, true);
    private final zzfl zzj = new zzfl(this, 4, false, false);
    private final zzfl zzk = new zzfl(this, 3, false, false);
    private final zzfl zzl = new zzfl(this, 2, false, false);

    zzfj(zzgq zzgq) {
        super(zzgq);
    }

    public final zzfl zzf() {
        return this.zzd;
    }

    public final zzfl zzg() {
        return this.zze;
    }

    public final zzfl zzh() {
        return this.zzf;
    }

    public final zzfl zzi() {
        return this.zzg;
    }

    public final zzfl zzj() {
        return this.zzh;
    }

    public final zzfl zzk() {
        return this.zzi;
    }

    public final zzfl zzv() {
        return this.zzj;
    }

    public final zzfl zzw() {
        return this.zzk;
    }

    public final zzfl zzx() {
        return this.zzl;
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    protected static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzfo(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        int i2;
        if (!z && zza(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzgj zzg2 = this.zzx.zzg();
            if (zzg2 == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzg2.zzz()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i < 0) {
                    i = 0;
                }
                if (i >= 9) {
                    i2 = 8;
                } else {
                    i2 = i;
                }
                zzfm zzfm = new zzfm(this, i2, str, obj, obj2, obj3);
                zzg2.zza((Runnable) zzfm);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i) {
        return Log.isLoggable(zzad(), i);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, String str) {
        Log.println(i, zzad(), str);
    }

    private final String zzad() {
        String str;
        String str2;
        synchronized (this) {
            if (this.zzc == null) {
                if (this.zzx.zzs() != null) {
                    str2 = this.zzx.zzs();
                } else {
                    zzt().zzu();
                    str2 = "FA";
                }
                this.zzc = str2;
            }
            str = this.zzc;
        }
        return str;
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        String zza2 = zza(z, obj);
        String zza3 = zza(z, obj2);
        String zza4 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zza4)) {
            sb.append(str2);
            sb.append(zza4);
        }
        return sb.toString();
    }

    private static String zza(boolean z, Object obj) {
        String str = "";
        if (obj == null) {
            return str;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        String str2 = "-";
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            if (String.valueOf(obj).charAt(0) == '-') {
                str = str2;
            }
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length());
            sb.append(str);
            sb.append(round);
            sb.append("...");
            sb.append(str);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzb2 = zzb(zzgq.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null && zzb(className).equals(zzb2)) {
                            sb2.append(": ");
                            sb2.append(stackTraceElement);
                            break;
                        }
                    }
                    i++;
                }
                return sb2.toString();
            } else if (obj instanceof zzfo) {
                return ((zzfo) obj).zza;
            } else {
                if (z) {
                    return str2;
                }
                return String.valueOf(obj);
            }
        }
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    public final String zzy() {
        Pair<String, Long> zza2 = zzs().zzb.zza();
        if (zza2 == null || zza2 == zzfv.zza) {
            return null;
        }
        String valueOf = String.valueOf(zza2.second);
        String str = (String) zza2.first;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
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
