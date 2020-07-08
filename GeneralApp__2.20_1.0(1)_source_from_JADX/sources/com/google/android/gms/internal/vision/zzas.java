package com.google.android.gms.internal.vision;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzas extends ContentObserver {
    private final /* synthetic */ zzaq zzfr;

    zzas(zzaq zzaq, Handler handler) {
        this.zzfr = zzaq;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zzfr.zzv();
    }
}
