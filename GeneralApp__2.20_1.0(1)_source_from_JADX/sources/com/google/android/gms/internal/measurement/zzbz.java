package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
final class zzbz extends ContentObserver {
    private final /* synthetic */ zzbx zza;

    zzbz(zzbx zzbx, Handler handler) {
        this.zza = zzbx;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zza.zzb();
    }
}
