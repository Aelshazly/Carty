package com.google.android.gms.internal.vision;

import java.util.Map.Entry;
import kotlin.UByte;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdl<K, V> extends zzdg<K, V> {
    private static final zzdg<Object, Object> zzmd = new zzdl(null, new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzmb;
    private final transient Object zzme;

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0065, code lost:
        r0[r6] = (byte) r2;
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a7, code lost:
        r0[r6] = (short) r2;
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00df, code lost:
        r0[r7] = r2;
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <K, V> com.google.android.gms.internal.vision.zzdl<K, V> zza(int r10, java.lang.Object[] r11) {
        /*
            int r10 = r11.length
            r0 = 1
            int r10 = r10 >> r0
            r1 = 4
            com.google.android.gms.internal.vision.zzct.zzd(r1, r10)
            r10 = 2
            int r10 = java.lang.Math.max(r1, r10)
            r2 = 1073741824(0x40000000, float:2.0)
            r3 = 0
            r4 = 751619276(0x2ccccccc, float:5.8207657E-12)
            if (r10 >= r4) goto L_0x002f
            int r2 = r10 + -1
            int r2 = java.lang.Integer.highestOneBit(r2)
            int r0 = r2 << 1
            r2 = r0
        L_0x001e:
            double r4 = (double) r2
            r6 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r4 = r4 * r6
            double r6 = (double) r10
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x002e
            int r2 = r2 << 1
            goto L_0x001e
        L_0x002e:
            goto L_0x0039
        L_0x002f:
            if (r10 >= r2) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            r0 = 0
        L_0x0033:
            java.lang.String r10 = "collection too large"
            com.google.android.gms.internal.vision.zzct.checkArgument(r0, r10)
        L_0x0039:
            int r10 = r2 + -1
            r0 = 128(0x80, float:1.794E-43)
            r4 = -1
            if (r2 > r0) goto L_0x007e
            byte[] r0 = new byte[r2]
            java.util.Arrays.fill(r0, r4)
        L_0x0048:
            if (r3 >= r1) goto L_0x007c
            int r2 = r3 * 2
            r4 = r11[r2]
            r5 = r2 ^ 1
            r5 = r11[r5]
            com.google.android.gms.internal.vision.zzda.zza(r4, r5)
            int r6 = r4.hashCode()
            int r6 = com.google.android.gms.internal.vision.zzdd.zzs(r6)
        L_0x005d:
            r6 = r6 & r10
            byte r7 = r0[r6]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & r8
            if (r7 != r8) goto L_0x006c
            byte r2 = (byte) r2
            r0[r6] = r2
            int r3 = r3 + 1
            goto L_0x0048
        L_0x006c:
            r8 = r11[r7]
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x0077
            int r6 = r6 + 1
            goto L_0x005d
        L_0x0077:
            java.lang.IllegalArgumentException r10 = zza(r4, r5, r11, r7)
            throw r10
        L_0x007c:
            goto L_0x00f6
        L_0x007e:
            r0 = 32768(0x8000, float:4.5918E-41)
            if (r2 > r0) goto L_0x00bf
            short[] r0 = new short[r2]
            java.util.Arrays.fill(r0, r4)
        L_0x0089:
            if (r3 >= r1) goto L_0x00be
            int r2 = r3 * 2
            r4 = r11[r2]
            r5 = r2 ^ 1
            r5 = r11[r5]
            com.google.android.gms.internal.vision.zzda.zza(r4, r5)
            int r6 = r4.hashCode()
            int r6 = com.google.android.gms.internal.vision.zzdd.zzs(r6)
        L_0x009e:
            r6 = r6 & r10
            short r7 = r0[r6]
            r8 = 65535(0xffff, float:9.1834E-41)
            r7 = r7 & r8
            if (r7 != r8) goto L_0x00ae
            short r2 = (short) r2
            r0[r6] = r2
            int r3 = r3 + 1
            goto L_0x0089
        L_0x00ae:
            r8 = r11[r7]
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x00b9
            int r6 = r6 + 1
            goto L_0x009e
        L_0x00b9:
            java.lang.IllegalArgumentException r10 = zza(r4, r5, r11, r7)
            throw r10
        L_0x00be:
            goto L_0x00f6
        L_0x00bf:
            int[] r0 = new int[r2]
            java.util.Arrays.fill(r0, r4)
        L_0x00c5:
            if (r3 >= r1) goto L_0x00f5
            int r2 = r3 * 2
            r5 = r11[r2]
            r6 = r2 ^ 1
            r6 = r11[r6]
            com.google.android.gms.internal.vision.zzda.zza(r5, r6)
            int r7 = r5.hashCode()
            int r7 = com.google.android.gms.internal.vision.zzdd.zzs(r7)
        L_0x00da:
            r7 = r7 & r10
            r8 = r0[r7]
            if (r8 != r4) goto L_0x00e5
            r0[r7] = r2
            int r3 = r3 + 1
            goto L_0x00c5
        L_0x00e5:
            r9 = r11[r8]
            boolean r9 = r9.equals(r5)
            if (r9 != 0) goto L_0x00f0
            int r7 = r7 + 1
            goto L_0x00da
        L_0x00f0:
            java.lang.IllegalArgumentException r10 = zza(r5, r6, r11, r8)
            throw r10
        L_0x00f5:
        L_0x00f6:
            com.google.android.gms.internal.vision.zzdl r10 = new com.google.android.gms.internal.vision.zzdl
            r10.<init>(r0, r11, r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzdl.zza(int, java.lang.Object[]):com.google.android.gms.internal.vision.zzdl");
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        String str = "=";
        sb.append(str);
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append(str);
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    private zzdl(Object obj, Object[] objArr, int i) {
        this.zzme = obj;
        this.zzmb = objArr;
        this.size = i;
    }

    public final int size() {
        return this.size;
    }

    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzme;
        V[] vArr = this.zzmb;
        int i = this.size;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (vArr[0].equals(obj)) {
                return vArr[1];
            }
            return null;
        } else if (obj2 == null) {
            return null;
        } else {
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                int length = bArr.length - 1;
                int zzs = zzdd.zzs(obj.hashCode());
                while (true) {
                    int i2 = zzs & length;
                    byte b = bArr[i2] & UByte.MAX_VALUE;
                    if (b == 255) {
                        return null;
                    }
                    if (vArr[b].equals(obj)) {
                        return vArr[b ^ 1];
                    }
                    zzs = i2 + 1;
                }
            } else if (obj2 instanceof short[]) {
                short[] sArr = (short[]) obj2;
                int length2 = sArr.length - 1;
                int zzs2 = zzdd.zzs(obj.hashCode());
                while (true) {
                    int i3 = zzs2 & length2;
                    short s = sArr[i3] & UShort.MAX_VALUE;
                    if (s == 65535) {
                        return null;
                    }
                    if (vArr[s].equals(obj)) {
                        return vArr[s ^ 1];
                    }
                    zzs2 = i3 + 1;
                }
            } else {
                int[] iArr = (int[]) obj2;
                int length3 = iArr.length - 1;
                int zzs3 = zzdd.zzs(obj.hashCode());
                while (true) {
                    int i4 = zzs3 & length3;
                    int i5 = iArr[i4];
                    if (i5 == -1) {
                        return null;
                    }
                    if (vArr[i5].equals(obj)) {
                        return vArr[i5 ^ 1];
                    }
                    zzs3 = i4 + 1;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final zzdj<Entry<K, V>> zzce() {
        return new zzdk(this, this.zzmb, 0, this.size);
    }

    /* access modifiers changed from: 0000 */
    public final zzdj<K> zzcf() {
        return new zzdm(this, new zzdp(this.zzmb, 0, this.size));
    }

    /* access modifiers changed from: 0000 */
    public final zzdc<V> zzcg() {
        return new zzdp(this.zzmb, 1, this.size);
    }
}
