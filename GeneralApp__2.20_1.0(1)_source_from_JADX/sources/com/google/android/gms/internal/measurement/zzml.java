package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzml implements zzmm {
    private static final zzcl<Boolean> zza;
    private static final zzcl<Double> zzb;
    private static final zzcl<Long> zzc;
    private static final zzcl<Long> zzd;
    private static final zzcl<String> zze;

    public final boolean zza() {
        return ((Boolean) zza.zzc()).booleanValue();
    }

    public final double zzb() {
        return ((Double) zzb.zzc()).doubleValue();
    }

    public final long zzc() {
        return ((Long) zzc.zzc()).longValue();
    }

    public final long zzd() {
        return ((Long) zzd.zzc()).longValue();
    }

    public final String zze() {
        return (String) zze.zzc();
    }

    static {
        zzcr zzcr = new zzcr(zzcm.zza("com.google.android.gms.measurement"));
        zza = zzcr.zza("measurement.test.boolean_flag", false);
        zzb = zzcr.zza("measurement.test.double_flag", -3.0d);
        zzc = zzcr.zza("measurement.test.int_flag", -2);
        zzd = zzcr.zza("measurement.test.long_flag", -1);
        zze = zzcr.zza("measurement.test.string_flag", "---");
    }
}
