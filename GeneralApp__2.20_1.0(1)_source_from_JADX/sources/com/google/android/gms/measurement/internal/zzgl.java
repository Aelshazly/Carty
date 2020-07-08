package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzgl implements UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzgj zzb;

    public zzgl(zzgj zzgj, String str) {
        this.zzb = zzgj;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzr().zzf().zza(this.zza, th);
    }
}
