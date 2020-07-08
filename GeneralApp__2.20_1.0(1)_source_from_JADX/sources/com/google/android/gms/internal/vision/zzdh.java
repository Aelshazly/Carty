package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdh extends zzdf<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzdf zzlw;

    zzdh(zzdf zzdf, int i, int i2) {
        this.zzlw = zzdf;
        this.offset = i;
        this.length = i2;
    }

    public final int size() {
        return this.length;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzbz() {
        return this.zzlw.zzbz();
    }

    /* access modifiers changed from: 0000 */
    public final int zzca() {
        return this.zzlw.zzca() + this.offset;
    }

    /* access modifiers changed from: 0000 */
    public final int zzcb() {
        return this.zzlw.zzca() + this.offset + this.length;
    }

    public final E get(int i) {
        zzct.zzc(i, this.length);
        return this.zzlw.get(i + this.offset);
    }

    public final zzdf<E> zze(int i, int i2) {
        zzct.zza(i, i2, this.length);
        zzdf zzdf = this.zzlw;
        int i3 = this.offset;
        return (zzdf) zzdf.subList(i + i3, i2 + i3);
    }

    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
