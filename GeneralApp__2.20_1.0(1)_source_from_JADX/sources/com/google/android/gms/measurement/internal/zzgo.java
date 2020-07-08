package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzgo<V> extends FutureTask<V> implements Comparable<zzgo<V>> {
    final boolean zza;
    private final long zzb = zzgj.zzj.getAndIncrement();
    private final String zzc;
    private final /* synthetic */ zzgj zzd;

    zzgo(zzgj zzgj, Callable<V> callable, boolean z, String str) {
        this.zzd = zzgj;
        super(callable);
        Preconditions.checkNotNull(str);
        this.zzc = str;
        this.zza = z;
        if (this.zzb == LongCompanionObject.MAX_VALUE) {
            zzgj.zzr().zzf().zza("Tasks index overflow");
        }
    }

    zzgo(zzgj zzgj, Runnable runnable, boolean z, String str) {
        this.zzd = zzgj;
        super(runnable, null);
        Preconditions.checkNotNull(str);
        this.zzc = str;
        this.zza = false;
        if (this.zzb == LongCompanionObject.MAX_VALUE) {
            zzgj.zzr().zzf().zza("Tasks index overflow");
        }
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zzd.zzr().zzf().zza(this.zzc, th);
        if (th instanceof zzgm) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzgo zzgo = (zzgo) obj;
        boolean z = this.zza;
        if (z != zzgo.zza) {
            return z ? -1 : 1;
        }
        long j = this.zzb;
        long j2 = zzgo.zzb;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzd.zzr().zzg().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
        return 0;
    }
}
