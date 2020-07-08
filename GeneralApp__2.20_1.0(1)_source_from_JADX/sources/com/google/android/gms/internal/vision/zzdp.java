package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdp extends zzdf<Object> {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zzmb;

    zzdp(Object[] objArr, int i, int i2) {
        this.zzmb = objArr;
        this.offset = i;
        this.size = i2;
    }

    public final Object get(int i) {
        zzct.zzc(i, this.size);
        return this.zzmb[(i * 2) + this.offset];
    }

    public final int size() {
        return this.size;
    }
}
