package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zznc implements zzmz {
    private static final zzcl<Boolean> zza;
    private static final zzcl<Boolean> zzb;
    private static final zzcl<Boolean> zzc;

    public final boolean zza() {
        return ((Boolean) zza.zzc()).booleanValue();
    }

    public final boolean zzb() {
        return ((Boolean) zzb.zzc()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzc.zzc()).booleanValue();
    }

    static {
        zzcr zzcr = new zzcr(zzcm.zza("com.google.android.gms.measurement"));
        zza = zzcr.zza("measurement.service.sessions.remove_disabled_session_number", true);
        zzb = zzcr.zza("measurement.service.sessions.session_number_enabled", true);
        zzc = zzcr.zza("measurement.service.sessions.session_number_backfill_enabled", true);
    }
}
