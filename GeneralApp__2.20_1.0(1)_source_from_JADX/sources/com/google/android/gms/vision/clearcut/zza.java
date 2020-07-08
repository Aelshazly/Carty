package com.google.android.gms.vision.clearcut;

import com.google.android.gms.internal.vision.zzea.zzo;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zza implements Runnable {
    private final /* synthetic */ int zzbr;
    private final /* synthetic */ zzo zzbs;
    private final /* synthetic */ DynamiteClearcutLogger zzbt;

    zza(DynamiteClearcutLogger dynamiteClearcutLogger, int i, zzo zzo) {
        this.zzbt = dynamiteClearcutLogger;
        this.zzbr = i;
        this.zzbs = zzo;
    }

    public final void run() {
        this.zzbt.zzbq.zzb(this.zzbr, this.zzbs);
    }
}
