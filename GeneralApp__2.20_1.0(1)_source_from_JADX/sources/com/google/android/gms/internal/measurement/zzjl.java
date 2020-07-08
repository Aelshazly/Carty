package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzjl implements zzjm {
    private static final zzcl<Boolean> zza;
    private static final zzcl<Boolean> zzb;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return ((Boolean) zza.zzc()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzb.zzc()).booleanValue();
    }

    static {
        zzcr zzcr = new zzcr(zzcm.zza("com.google.android.gms.measurement"));
        zza = zzcr.zza("measurement.service.configurable_service_limits", false);
        zzb = zzcr.zza("measurement.client.configurable_service_limits", false);
    }
}
