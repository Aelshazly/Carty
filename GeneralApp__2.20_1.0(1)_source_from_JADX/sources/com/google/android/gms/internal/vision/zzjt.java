package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
abstract class zzjt {
    zzjt() {
    }

    /* access modifiers changed from: 0000 */
    public abstract int zzb(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: 0000 */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract String zzh(byte[] bArr, int i, int i2) throws zzhc;

    /* access modifiers changed from: 0000 */
    public final boolean zzf(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }
}
