package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfo extends zzfr {
    private final int zzsh;
    private final int zzsi;

    zzfo(byte[] bArr, int i, int i2) {
        super(bArr);
        zzc(i, i + i2, bArr.length);
        this.zzsh = i;
        this.zzsi = i2;
    }

    public final byte zzao(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzsk[this.zzsh + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    /* access modifiers changed from: 0000 */
    public final byte zzap(int i) {
        return this.zzsk[this.zzsh + i];
    }

    public final int size() {
        return this.zzsi;
    }

    /* access modifiers changed from: protected */
    public final int zzeu() {
        return this.zzsh;
    }

    /* access modifiers changed from: protected */
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzsk, zzeu(), bArr, 0, i3);
    }
}
