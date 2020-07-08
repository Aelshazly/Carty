package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzkv implements zzcu<zzku> {
    private static zzkv zzagw = new zzkv();
    private final zzcu<zzku> zzagt;

    public static boolean zzjp() {
        return ((zzku) zzagw.get()).zzjp();
    }

    public static boolean zzjq() {
        return ((zzku) zzagw.get()).zzjq();
    }

    private zzkv(zzcu<zzku> zzcu) {
        this.zzagt = zzcx.zza(zzcu);
    }

    public zzkv() {
        this(zzcx.zze(new zzkw()));
    }

    public final /* synthetic */ Object get() {
        return (zzku) this.zzagt.get();
    }
}
