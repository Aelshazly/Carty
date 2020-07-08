package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdi<E> extends zzdf<E> {
    static final zzdf<Object> zzlx = new zzdi(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzly;

    zzdi(Object[] objArr, int i) {
        this.zzly = objArr;
        this.size = i;
    }

    public final int size() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzbz() {
        return this.zzly;
    }

    /* access modifiers changed from: 0000 */
    public final int zzca() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final int zzcb() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzly, 0, objArr, i, this.size);
        return i + this.size;
    }

    public final E get(int i) {
        zzct.zzc(i, this.size);
        return this.zzly[i];
    }
}
