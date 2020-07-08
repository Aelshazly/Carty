package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzly implements zzlv {
    private static final zzcl<Long> zza;
    private static final zzcl<Boolean> zzb;
    private static final zzcl<Boolean> zzc;
    private static final zzcl<Boolean> zzd;
    private static final zzcl<Long> zze;

    public final boolean zza() {
        return ((Boolean) zzb.zzc()).booleanValue();
    }

    public final boolean zzb() {
        return ((Boolean) zzc.zzc()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzd.zzc()).booleanValue();
    }

    static {
        zzcr zzcr = new zzcr(zzcm.zza("com.google.android.gms.measurement"));
        zza = zzcr.zza("measurement.id.lifecycle.app_in_background_parameter", 0);
        zzb = zzcr.zza("measurement.lifecycle.app_backgrounded_engagement", false);
        zzc = zzcr.zza("measurement.lifecycle.app_backgrounded_tracking", true);
        zzd = zzcr.zza("measurement.lifecycle.app_in_background_parameter", false);
        zze = zzcr.zza("measurement.id.lifecycle.app_backgrounded_tracking", 0);
    }
}
