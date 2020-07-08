package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzik extends zzif {
    zzik() {
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0063, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bf, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(int r16, byte[] r17, int r18, int r19) {
        /*
            r15 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r1 | r2
            int r4 = r0.length
            int r4 = r4 - r2
            r3 = r3 | r4
            r4 = 2
            r5 = 3
            r6 = 0
            if (r3 < 0) goto L_0x00c0
            long r7 = (long) r1
            long r1 = (long) r2
            long r1 = r1 - r7
            int r2 = (int) r1
            r1 = 16
            r9 = 1
            if (r2 >= r1) goto L_0x001d
            r1 = 0
            goto L_0x002f
        L_0x001d:
            r11 = r7
            r1 = 0
        L_0x001f:
            if (r1 >= r2) goto L_0x002e
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.measurement.zzib.zza(r0, r11)
            if (r3 >= 0) goto L_0x002a
            goto L_0x002f
        L_0x002a:
            int r1 = r1 + 1
            r11 = r13
            goto L_0x001f
        L_0x002e:
            r1 = r2
        L_0x002f:
            int r2 = r2 - r1
            long r11 = (long) r1
            long r7 = r7 + r11
        L_0x0033:
            r1 = 0
        L_0x0034:
            if (r2 <= 0) goto L_0x0042
            long r11 = r7 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzib.zza(r0, r7)
            if (r1 < 0) goto L_0x0043
            int r2 = r2 + -1
            r7 = r11
            goto L_0x0034
        L_0x0042:
            r11 = r7
        L_0x0043:
            if (r2 != 0) goto L_0x0046
            return r6
        L_0x0046:
            int r2 = r2 + -1
            r3 = -32
            r7 = -65
            r8 = -1
            if (r1 >= r3) goto L_0x0064
            if (r2 != 0) goto L_0x0052
            return r1
        L_0x0052:
            int r2 = r2 + -1
            r3 = -62
            if (r1 < r3) goto L_0x0063
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzib.zza(r0, r11)
            if (r1 <= r7) goto L_0x0061
            goto L_0x0063
        L_0x0061:
            r7 = r13
            goto L_0x00bd
        L_0x0063:
            return r8
        L_0x0064:
            r13 = -16
            if (r1 >= r13) goto L_0x0091
            if (r2 >= r4) goto L_0x006f
            int r0 = zza(r0, r1, r11, r2)
            return r0
        L_0x006f:
            int r2 = r2 + -2
            long r13 = r11 + r9
            byte r11 = com.google.android.gms.internal.measurement.zzib.zza(r0, r11)
            if (r11 > r7) goto L_0x0090
            r12 = -96
            if (r1 != r3) goto L_0x007f
            if (r11 < r12) goto L_0x0090
        L_0x007f:
            r3 = -19
            if (r1 != r3) goto L_0x0085
            if (r11 >= r12) goto L_0x0090
        L_0x0085:
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzib.zza(r0, r13)
            if (r1 <= r7) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            r7 = r11
            goto L_0x0033
        L_0x0090:
            return r8
        L_0x0091:
            if (r2 >= r5) goto L_0x0098
            int r0 = zza(r0, r1, r11, r2)
            return r0
        L_0x0098:
            int r2 = r2 + -3
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.measurement.zzib.zza(r0, r11)
            if (r3 > r7) goto L_0x00bf
            int r1 = r1 << 28
            int r3 = r3 + 112
            int r1 = r1 + r3
            int r1 = r1 >> 30
            if (r1 != 0) goto L_0x00bf
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzib.zza(r0, r13)
            if (r1 > r7) goto L_0x00bf
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzib.zza(r0, r11)
            if (r1 <= r7) goto L_0x00bc
            goto L_0x00bf
        L_0x00bc:
            r7 = r13
        L_0x00bd:
            goto L_0x0033
        L_0x00bf:
            return r8
        L_0x00c0:
            java.lang.ArrayIndexOutOfBoundsException r3 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            int r0 = r0.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r18)
            r1 = 1
            r5[r1] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r19)
            r5[r4] = r0
            java.lang.String r0 = "Array length=%d, index=%d, limit=%d"
            java.lang.String r0 = java.lang.String.format(r0, r5)
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzik.zza(int, byte[], int, int):int");
    }

    /* access modifiers changed from: 0000 */
    public final String zzb(byte[] bArr, int i, int i2) throws zzfo {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (r13 < i3) {
                byte zza = zzib.zza(bArr, (long) r13);
                if (!zzig.zzd(zza)) {
                    break;
                }
                i = r13 + 1;
                int i5 = i4 + 1;
                zzig.zzb(zza, cArr, i4);
                i4 = i5;
            }
            int i6 = i4;
            while (r13 < i3) {
                int i7 = r13 + 1;
                byte zza2 = zzib.zza(bArr, (long) r13);
                if (zzig.zzd(zza2)) {
                    int i8 = i6 + 1;
                    zzig.zzb(zza2, cArr, i6);
                    while (i7 < i3) {
                        byte zza3 = zzib.zza(bArr, (long) i7);
                        if (!zzig.zzd(zza3)) {
                            break;
                        }
                        i7++;
                        int i9 = i8 + 1;
                        zzig.zzb(zza3, cArr, i8);
                        i8 = i9;
                    }
                    r13 = i7;
                    i6 = i8;
                } else if (zzig.zze(zza2)) {
                    if (i7 < i3) {
                        int i10 = i7 + 1;
                        int i11 = i6 + 1;
                        zzig.zzb(zza2, zzib.zza(bArr, (long) i7), cArr, i6);
                        r13 = i10;
                        i6 = i11;
                    } else {
                        throw zzfo.zzh();
                    }
                } else if (zzig.zzf(zza2)) {
                    if (i7 < i3 - 1) {
                        int i12 = i7 + 1;
                        int i13 = i12 + 1;
                        int i14 = i6 + 1;
                        zzig.zzb(zza2, zzib.zza(bArr, (long) i7), zzib.zza(bArr, (long) i12), cArr, i6);
                        r13 = i13;
                        i6 = i14;
                    } else {
                        throw zzfo.zzh();
                    }
                } else if (i7 < i3 - 2) {
                    int i15 = i7 + 1;
                    byte zza4 = zzib.zza(bArr, (long) i7);
                    int i16 = i15 + 1;
                    int i17 = i16 + 1;
                    int i18 = i6 + 1;
                    zzig.zzb(zza2, zza4, zzib.zza(bArr, (long) i15), zzib.zza(bArr, (long) i16), cArr, i6);
                    r13 = i17;
                    i6 = i18 + 1;
                } else {
                    throw zzfo.zzh();
                }
            }
            return new String(cArr, 0, i6);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: 0000 */
    public final int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        long j3;
        CharSequence charSequence2 = charSequence;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        long j4 = (long) i3;
        long j5 = ((long) i4) + j4;
        int length = charSequence.length();
        String str = " at index ";
        String str2 = "Failed writing ";
        if (length > i4 || bArr2.length - i4 < i3) {
            char charAt = charSequence2.charAt(length - 1);
            int i5 = i3 + i4;
            StringBuilder sb = new StringBuilder(37);
            sb.append(str2);
            sb.append(charAt);
            sb.append(str);
            sb.append(i5);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i6 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i6 >= length) {
                break;
            }
            char charAt2 = charSequence2.charAt(i6);
            if (charAt2 >= 128) {
                break;
            }
            long j6 = 1 + j4;
            zzib.zza(bArr2, j4, (byte) charAt2);
            i6++;
            j4 = j6;
        }
        if (i6 == length) {
            return (int) j4;
        }
        while (i6 < length) {
            char charAt3 = charSequence2.charAt(i6);
            if (charAt3 < c && j4 < j5) {
                long j7 = j4 + j;
                zzib.zza(bArr2, j4, (byte) charAt3);
                j3 = j;
                j2 = j7;
            } else if (charAt3 < 2048 && j4 <= j5 - 2) {
                long j8 = j4 + j;
                zzib.zza(bArr2, j4, (byte) ((charAt3 >>> 6) | 960));
                long j9 = j8 + j;
                zzib.zza(bArr2, j8, (byte) ((charAt3 & '?') | 128));
                j2 = j9;
                j3 = j;
            } else if ((charAt3 < 55296 || 57343 < charAt3) && j4 <= j5 - 3) {
                long j10 = j4 + j;
                zzib.zza(bArr2, j4, (byte) ((charAt3 >>> 12) | 480));
                long j11 = j10 + j;
                zzib.zza(bArr2, j10, (byte) (((charAt3 >>> 6) & 63) | 128));
                long j12 = j11 + 1;
                zzib.zza(bArr2, j11, (byte) ((charAt3 & '?') | 128));
                j2 = j12;
                j3 = 1;
            } else if (j4 <= j5 - 4) {
                int i7 = i6 + 1;
                if (i7 != length) {
                    char charAt4 = charSequence2.charAt(i7);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j13 = j4 + 1;
                        zzib.zza(bArr2, j4, (byte) ((codePoint >>> 18) | 240));
                        long j14 = j13 + 1;
                        zzib.zza(bArr2, j13, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j15 = j14 + 1;
                        zzib.zza(bArr2, j14, (byte) (((codePoint >>> 6) & 63) | 128));
                        j3 = 1;
                        j2 = j15 + 1;
                        zzib.zza(bArr2, j15, (byte) ((codePoint & 63) | 128));
                        i6 = i7;
                    } else {
                        i6 = i7;
                    }
                }
                throw new zzih(i6 - 1, length);
            } else {
                if (55296 <= charAt3 && charAt3 <= 57343) {
                    int i8 = i6 + 1;
                    if (i8 == length || !Character.isSurrogatePair(charAt3, charSequence2.charAt(i8))) {
                        throw new zzih(i6, length);
                    }
                }
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append(str2);
                sb2.append(charAt3);
                sb2.append(str);
                sb2.append(j4);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            }
            i6++;
            c = 128;
            long j16 = j3;
            j4 = j2;
            j = j16;
        }
        return (int) j4;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return zzie.zzb(i);
        }
        if (i2 == 1) {
            return zzie.zzb(i, zzib.zza(bArr, j));
        }
        if (i2 == 2) {
            return zzie.zzb(i, (int) zzib.zza(bArr, j), (int) zzib.zza(bArr, j + 1));
        }
        throw new AssertionError();
    }
}
