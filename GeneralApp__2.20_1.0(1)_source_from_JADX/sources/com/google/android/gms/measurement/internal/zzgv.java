package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.3.0 */
final class zzgv implements Callable<List<zzlb>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzgr zzd;

    zzgv(zzgr zzgr, String str, String str2, String str3) {
        this.zzd = zzgr;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzd.zza.zzo();
        return this.zzd.zza.zze().zza(this.zza, this.zzb, this.zzc);
    }
}
