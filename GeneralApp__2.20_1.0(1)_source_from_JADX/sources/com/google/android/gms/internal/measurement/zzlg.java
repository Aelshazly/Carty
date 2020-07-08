package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzlg implements zzld {
    private static final zzcl<Boolean> zza;
    private static final zzcl<Boolean> zzb;
    private static final zzcl<Boolean> zzc;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return ((Boolean) zza.zzc()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzb.zzc()).booleanValue();
    }

    public final boolean zzd() {
        return ((Boolean) zzc.zzc()).booleanValue();
    }

    static {
        zzcr zzcr = new zzcr(zzcm.zza("com.google.android.gms.measurement"));
        zza = zzcr.zza("measurement.client.sessions.check_on_reset_and_enable", false);
        zzb = zzcr.zza("measurement.client.sessions.check_on_startup", true);
        zzc = zzcr.zza("measurement.client.sessions.start_session_before_view_screen", true);
    }
}
