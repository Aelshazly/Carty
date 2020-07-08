package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfp {
    private final byte[] buffer;
    private final zzga zzsj;

    private zzfp(int i) {
        this.buffer = new byte[i];
        this.zzsj = zzga.zze(this.buffer);
    }

    public final zzfh zzev() {
        this.zzsj.zzfh();
        return new zzfr(this.buffer);
    }

    public final zzga zzew() {
        return this.zzsj;
    }

    /* synthetic */ zzfp(int i, zzfk zzfk) {
        this(i);
    }
}
