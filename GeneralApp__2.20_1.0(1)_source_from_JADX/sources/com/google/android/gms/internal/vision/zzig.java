package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzig<T> implements zzir<T> {
    private static final int[] zzyy = new int[0];
    private static final Unsafe zzyz = zzjp.zzil();
    private final int[] zzza;
    private final Object[] zzzb;
    private final int zzzc;
    private final int zzzd;
    private final zzic zzze;
    private final boolean zzzf;
    private final boolean zzzg;
    private final boolean zzzh;
    private final boolean zzzi;
    private final int[] zzzj;
    private final int zzzk;
    private final int zzzl;
    private final zzik zzzm;
    private final zzhm zzzn;
    private final zzjj<?, ?> zzzo;
    private final zzgf<?> zzzp;
    private final zzhv zzzq;

    private zzig(int[] iArr, Object[] objArr, int i, int i2, zzic zzic, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzik zzik, zzhm zzhm, zzjj<?, ?> zzjj, zzgf<?> zzgf, zzhv zzhv) {
        this.zzza = iArr;
        this.zzzb = objArr;
        this.zzzc = i;
        this.zzzd = i2;
        this.zzzg = zzic instanceof zzgs;
        this.zzzh = z;
        this.zzzf = zzgf != null && zzgf.zze(zzic);
        this.zzzi = false;
        this.zzzj = iArr2;
        this.zzzk = i3;
        this.zzzl = i4;
        this.zzzm = zzik;
        this.zzzn = zzhm;
        this.zzzo = zzjj;
        this.zzzp = zzgf;
        this.zzze = zzic;
        this.zzzq = zzhv;
    }

    /* JADX WARNING: Removed duplicated region for block: B:163:0x0365  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x03b6  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.vision.zzig<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.vision.zzia r34, com.google.android.gms.internal.vision.zzik r35, com.google.android.gms.internal.vision.zzhm r36, com.google.android.gms.internal.vision.zzjj<?, ?> r37, com.google.android.gms.internal.vision.zzgf<?> r38, com.google.android.gms.internal.vision.zzhv r39) {
        /*
            r0 = r34
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzip
            if (r1 == 0) goto L_0x0430
            com.google.android.gms.internal.vision.zzip r0 = (com.google.android.gms.internal.vision.zzip) r0
            int r1 = r0.zzhi()
            int r2 = com.google.android.gms.internal.vision.zzgs.zzf.zzxa
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzhp()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r6) goto L_0x0034
            r5 = 1
        L_0x0029:
            int r7 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x0035
            r5 = r7
            goto L_0x0029
        L_0x0034:
            r7 = 1
        L_0x0035:
            int r5 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0054
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0041:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x0051
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r7 = r7 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x0041
        L_0x0051:
            int r5 = r5 << r9
            r7 = r7 | r5
            goto L_0x0055
        L_0x0054:
            r10 = r5
        L_0x0055:
            if (r7 != 0) goto L_0x006b
            int[] r5 = zzyy
            r15 = r5
            r5 = 0
            r7 = 0
            r9 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            goto L_0x019c
        L_0x006b:
            int r5 = r10 + 1
            char r7 = r1.charAt(r10)
            if (r7 < r6) goto L_0x008a
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0077:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x0087
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r7 = r7 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x0077
        L_0x0087:
            int r5 = r5 << r9
            r5 = r5 | r7
            goto L_0x008c
        L_0x008a:
            r10 = r5
            r5 = r7
        L_0x008c:
            int r7 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r6) goto L_0x00ac
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0099:
            int r12 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x00a9
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r10
            r9 = r9 | r7
            int r10 = r10 + 13
            r7 = r12
            goto L_0x0099
        L_0x00a9:
            int r7 = r7 << r10
            r9 = r9 | r7
            goto L_0x00ad
        L_0x00ac:
            r12 = r7
        L_0x00ad:
            int r7 = r12 + 1
            char r10 = r1.charAt(r12)
            if (r10 < r6) goto L_0x00ce
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00ba:
            int r13 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x00ca
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r12
            r10 = r10 | r7
            int r12 = r12 + 13
            r7 = r13
            goto L_0x00ba
        L_0x00ca:
            int r7 = r7 << r12
            r7 = r7 | r10
            r10 = r7
            goto L_0x00cf
        L_0x00ce:
            r13 = r7
        L_0x00cf:
            int r7 = r13 + 1
            char r12 = r1.charAt(r13)
            if (r12 < r6) goto L_0x00f0
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00dc:
            int r14 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x00ec
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r13
            r12 = r12 | r7
            int r13 = r13 + 13
            r7 = r14
            goto L_0x00dc
        L_0x00ec:
            int r7 = r7 << r13
            r7 = r7 | r12
            r12 = r7
            goto L_0x00f1
        L_0x00f0:
            r14 = r7
        L_0x00f1:
            int r7 = r14 + 1
            char r13 = r1.charAt(r14)
            if (r13 < r6) goto L_0x0112
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00fe:
            int r15 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x010e
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r14
            r13 = r13 | r7
            int r14 = r14 + 13
            r7 = r15
            goto L_0x00fe
        L_0x010e:
            int r7 = r7 << r14
            r7 = r7 | r13
            r13 = r7
            goto L_0x0113
        L_0x0112:
            r15 = r7
        L_0x0113:
            int r7 = r15 + 1
            char r14 = r1.charAt(r15)
            if (r14 < r6) goto L_0x0136
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0120:
            int r16 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0131
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r15
            r14 = r14 | r7
            int r15 = r15 + 13
            r7 = r16
            goto L_0x0120
        L_0x0131:
            int r7 = r7 << r15
            r7 = r7 | r14
            r14 = r7
            r7 = r16
        L_0x0136:
            int r15 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x015a
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0143:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0155
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r7 = r7 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0143
        L_0x0155:
            int r15 = r15 << r16
            r7 = r7 | r15
            r15 = r17
        L_0x015a:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0186
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r32 = r16
            r16 = r15
            r15 = r32
        L_0x016d:
            int r18 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0180
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r17
            r16 = r16 | r15
            int r17 = r17 + 13
            r15 = r18
            goto L_0x016d
        L_0x0180:
            int r15 = r15 << r17
            r15 = r16 | r15
            r16 = r18
        L_0x0186:
            int r17 = r15 + r14
            int r7 = r17 + r7
            int[] r7 = new int[r7]
            int r17 = r5 << 1
            int r9 = r17 + r9
            r32 = r16
            r16 = r5
            r5 = r14
            r14 = r15
            r15 = r7
            r7 = r9
            r9 = r10
            r10 = r32
        L_0x019c:
            sun.misc.Unsafe r3 = zzyz
            java.lang.Object[] r17 = r0.zzhq()
            com.google.android.gms.internal.vision.zzic r18 = r0.zzhk()
            java.lang.Class r8 = r18.getClass()
            int r6 = r13 * 3
            int[] r6 = new int[r6]
            int r13 = r13 << r4
            java.lang.Object[] r13 = new java.lang.Object[r13]
            int r19 = r14 + r5
            r21 = r14
            r22 = r19
            r5 = 0
            r20 = 0
        L_0x01bc:
            if (r10 >= r2) goto L_0x040d
            int r23 = r10 + 1
            char r10 = r1.charAt(r10)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r10 < r4) goto L_0x01f0
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r25 = 13
            r32 = r23
            r23 = r10
            r10 = r32
        L_0x01d3:
            int r26 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r4) goto L_0x01e9
            r4 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r25
            r23 = r23 | r4
            int r25 = r25 + 13
            r10 = r26
            r4 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01d3
        L_0x01e9:
            int r4 = r10 << r25
            r10 = r23 | r4
            r4 = r26
            goto L_0x01f2
        L_0x01f0:
            r4 = r23
        L_0x01f2:
            int r23 = r4 + 1
            char r4 = r1.charAt(r4)
            r25 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x0227
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
            r32 = r23
            r23 = r4
            r4 = r32
        L_0x020a:
            int r27 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r2) goto L_0x0220
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r26
            r23 = r23 | r2
            int r26 = r26 + 13
            r4 = r27
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x020a
        L_0x0220:
            int r2 = r4 << r26
            r4 = r23 | r2
            r2 = r27
            goto L_0x0229
        L_0x0227:
            r2 = r23
        L_0x0229:
            r23 = r14
            r14 = r4 & 255(0xff, float:3.57E-43)
            r26 = r12
            r12 = r4 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x0239
            int r12 = r5 + 1
            r15[r5] = r20
            r5 = r12
        L_0x0239:
            r12 = 51
            r29 = r5
            if (r14 < r12) goto L_0x02d6
            int r12 = r2 + 1
            char r2 = r1.charAt(r2)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r5) goto L_0x0268
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r30 = 13
        L_0x024e:
            int r31 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x0263
            r5 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r30
            r2 = r2 | r5
            int r30 = r30 + 13
            r12 = r31
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x024e
        L_0x0263:
            int r5 = r12 << r30
            r2 = r2 | r5
            r12 = r31
        L_0x0268:
            int r5 = r14 + -51
            r30 = r12
            r12 = 9
            if (r5 == r12) goto L_0x028c
            r12 = 17
            if (r5 != r12) goto L_0x0276
            goto L_0x028c
        L_0x0276:
            r12 = 12
            if (r5 != r12) goto L_0x028a
            if (r11 != 0) goto L_0x028a
            int r5 = r20 / 3
            r12 = 1
            int r5 = r5 << r12
            int r5 = r5 + r12
            int r12 = r7 + 1
            r7 = r17[r7]
            r13[r5] = r7
            r7 = r12
            r12 = 1
            goto L_0x0299
        L_0x028a:
            r12 = 1
            goto L_0x0299
        L_0x028c:
            int r5 = r20 / 3
            r12 = 1
            int r5 = r5 << r12
            int r5 = r5 + r12
            int r24 = r7 + 1
            r7 = r17[r7]
            r13[r5] = r7
            r7 = r24
        L_0x0299:
            int r2 = r2 << r12
            r5 = r17[r2]
            boolean r12 = r5 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x02a3
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x02ab
        L_0x02a3:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zza(r8, r5)
            r17[r2] = r5
        L_0x02ab:
            r12 = r6
            long r5 = r3.objectFieldOffset(r5)
            int r6 = (int) r5
            int r2 = r2 + 1
            r5 = r17[r2]
            r27 = r6
            boolean r6 = r5 instanceof java.lang.reflect.Field
            if (r6 == 0) goto L_0x02be
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x02c6
        L_0x02be:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zza(r8, r5)
            r17[r2] = r5
        L_0x02c6:
            long r5 = r3.objectFieldOffset(r5)
            int r2 = (int) r5
            r5 = r2
            r18 = r8
            r6 = r27
            r28 = r30
            r2 = 0
            goto L_0x03d1
        L_0x02d6:
            r12 = r6
            int r5 = r7 + 1
            r6 = r17[r7]
            java.lang.String r6 = (java.lang.String) r6
            java.lang.reflect.Field r6 = zza(r8, r6)
            r7 = 9
            if (r14 == r7) goto L_0x034a
            r7 = 17
            if (r14 != r7) goto L_0x02ec
            r24 = 1
            goto L_0x034c
        L_0x02ec:
            r7 = 27
            if (r14 == r7) goto L_0x033b
            r7 = 49
            if (r14 != r7) goto L_0x02f5
            goto L_0x033b
        L_0x02f5:
            r7 = 12
            if (r14 == r7) goto L_0x032a
            r7 = 30
            if (r14 == r7) goto L_0x032a
            r7 = 44
            if (r14 != r7) goto L_0x0302
            goto L_0x032a
        L_0x0302:
            r7 = 50
            if (r14 != r7) goto L_0x0358
            int r7 = r21 + 1
            r15[r21] = r20
            int r21 = r20 / 3
            r24 = 1
            int r21 = r21 << 1
            int r27 = r5 + 1
            r5 = r17[r5]
            r13[r21] = r5
            r5 = r4 & 2048(0x800, float:2.87E-42)
            if (r5 == 0) goto L_0x0327
            int r21 = r21 + 1
            int r5 = r27 + 1
            r27 = r17[r27]
            r13[r21] = r27
            r27 = r5
            r21 = r7
            goto L_0x035a
        L_0x0327:
            r21 = r7
            goto L_0x035a
        L_0x032a:
            if (r11 != 0) goto L_0x0358
            int r7 = r20 / 3
            r24 = 1
            int r7 = r7 << 1
            int r7 = r7 + 1
            int r27 = r5 + 1
            r5 = r17[r5]
            r13[r7] = r5
            goto L_0x035a
        L_0x033b:
            int r7 = r20 / 3
            r24 = 1
            int r7 = r7 << 1
            int r7 = r7 + 1
            int r27 = r5 + 1
            r5 = r17[r5]
            r13[r7] = r5
            goto L_0x035a
        L_0x034a:
            r24 = 1
        L_0x034c:
            int r7 = r20 / 3
            int r7 = r7 << 1
            int r7 = r7 + 1
            java.lang.Class r27 = r6.getType()
            r13[r7] = r27
        L_0x0358:
            r27 = r5
        L_0x035a:
            long r5 = r3.objectFieldOffset(r6)
            int r6 = (int) r5
            r5 = r4 & 4096(0x1000, float:5.74E-42)
            r7 = 4096(0x1000, float:5.74E-42)
            if (r5 != r7) goto L_0x03b6
            r5 = 17
            if (r14 > r5) goto L_0x03b3
            int r5 = r2 + 1
            char r2 = r1.charAt(r2)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r7) goto L_0x038e
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r18 = 13
        L_0x0378:
            int r28 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r7) goto L_0x038a
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r18
            r2 = r2 | r5
            int r18 = r18 + 13
            r5 = r28
            goto L_0x0378
        L_0x038a:
            int r5 = r5 << r18
            r2 = r2 | r5
            goto L_0x0390
        L_0x038e:
            r28 = r5
        L_0x0390:
            r5 = 1
            int r18 = r16 << 1
            int r24 = r2 / 32
            int r18 = r18 + r24
            r5 = r17[r18]
            boolean r7 = r5 instanceof java.lang.reflect.Field
            if (r7 == 0) goto L_0x03a1
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x03a9
        L_0x03a1:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zza(r8, r5)
            r17[r18] = r5
        L_0x03a9:
            r18 = r8
            long r7 = r3.objectFieldOffset(r5)
            int r5 = (int) r7
            int r2 = r2 % 32
            goto L_0x03be
        L_0x03b3:
            r18 = r8
            goto L_0x03b8
        L_0x03b6:
            r18 = r8
        L_0x03b8:
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r28 = r2
            r2 = 0
        L_0x03be:
            r7 = 18
            if (r14 < r7) goto L_0x03cf
            r7 = 49
            if (r14 > r7) goto L_0x03cf
            int r7 = r22 + 1
            r15[r22] = r6
            r22 = r7
            r7 = r27
            goto L_0x03d1
        L_0x03cf:
            r7 = r27
        L_0x03d1:
            int r8 = r20 + 1
            r12[r20] = r10
            int r10 = r8 + 1
            r20 = r1
            r1 = r4 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x03e0
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03e1
        L_0x03e0:
            r1 = 0
        L_0x03e1:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x03e8
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03e9
        L_0x03e8:
            r4 = 0
        L_0x03e9:
            r1 = r1 | r4
            int r4 = r14 << 20
            r1 = r1 | r4
            r1 = r1 | r6
            r12[r8] = r1
            int r1 = r10 + 1
            int r2 = r2 << 20
            r2 = r2 | r5
            r12[r10] = r2
            r6 = r12
            r8 = r18
            r14 = r23
            r2 = r25
            r12 = r26
            r10 = r28
            r5 = r29
            r4 = 1
            r32 = r20
            r20 = r1
            r1 = r32
            goto L_0x01bc
        L_0x040d:
            r26 = r12
            r23 = r14
            r12 = r6
            com.google.android.gms.internal.vision.zzig r1 = new com.google.android.gms.internal.vision.zzig
            com.google.android.gms.internal.vision.zzic r10 = r0.zzhk()
            r0 = 0
            r5 = r1
            r7 = r13
            r8 = r9
            r9 = r26
            r12 = r0
            r13 = r15
            r15 = r19
            r16 = r35
            r17 = r36
            r18 = r37
            r19 = r38
            r20 = r39
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x0430:
            com.google.android.gms.internal.vision.zzjg r0 = (com.google.android.gms.internal.vision.zzjg) r0
            int r0 = r0.zzhi()
            int r1 = com.google.android.gms.internal.vision.zzgs.zzf.zzxa
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Class, com.google.android.gms.internal.vision.zzia, com.google.android.gms.internal.vision.zzik, com.google.android.gms.internal.vision.zzhm, com.google.android.gms.internal.vision.zzjj, com.google.android.gms.internal.vision.zzgf, com.google.android.gms.internal.vision.zzhv):com.google.android.gms.internal.vision.zzig");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    public final T newInstance() {
        return this.zzzm.newInstance(this.zzze);
    }

    public final boolean equals(T t, T t2) {
        int length = this.zzza.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzbq = zzbq(i);
                long j = (long) (zzbq & 1048575);
                switch ((zzbq & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzjp.zzo(t, j)) != Double.doubleToLongBits(zzjp.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzjp.zzn(t, j)) != Float.floatToIntBits(zzjp.zzn(t2, j))) {
                            z = false;
                            break;
                        }
                    case 2:
                        if (!zzc(t, t2, i) || zzjp.zzl(t, j) != zzjp.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 3:
                        if (!zzc(t, t2, i) || zzjp.zzl(t, j) != zzjp.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 4:
                        if (!zzc(t, t2, i) || zzjp.zzk(t, j) != zzjp.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 5:
                        if (!zzc(t, t2, i) || zzjp.zzl(t, j) != zzjp.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 6:
                        if (!zzc(t, t2, i) || zzjp.zzk(t, j) != zzjp.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 7:
                        if (!zzc(t, t2, i) || zzjp.zzm(t, j) != zzjp.zzm(t2, j)) {
                            z = false;
                            break;
                        }
                    case 8:
                        if (!zzc(t, t2, i) || !zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 9:
                        if (!zzc(t, t2, i) || !zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 10:
                        if (!zzc(t, t2, i) || !zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 11:
                        if (!zzc(t, t2, i) || zzjp.zzk(t, j) != zzjp.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 12:
                        if (!zzc(t, t2, i) || zzjp.zzk(t, j) != zzjp.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 13:
                        if (!zzc(t, t2, i) || zzjp.zzk(t, j) != zzjp.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 14:
                        if (!zzc(t, t2, i) || zzjp.zzl(t, j) != zzjp.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 15:
                        if (!zzc(t, t2, i) || zzjp.zzk(t, j) != zzjp.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 16:
                        if (!zzc(t, t2, i) || zzjp.zzl(t, j) != zzjp.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 17:
                        if (!zzc(t, t2, i) || !zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        z = zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j));
                        break;
                    case 50:
                        z = zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long zzbr = (long) (zzbr(i) & 1048575);
                        if (zzjp.zzk(t, zzbr) != zzjp.zzk(t2, zzbr) || !zzit.zze(zzjp.zzp(t, j), zzjp.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzzo.zzw(t).equals(this.zzzo.zzw(t2))) {
                return false;
            } else {
                if (this.zzzf) {
                    return this.zzzp.zzf(t).equals(this.zzzp.zzf(t2));
                }
                return true;
            }
        }
    }

    public final int hashCode(T t) {
        int length = this.zzza.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzbq = zzbq(i2);
            int i3 = this.zzza[i2];
            long j = (long) (1048575 & zzbq);
            int i4 = 37;
            switch ((zzbq & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzgt.zzab(Double.doubleToLongBits(zzjp.zzo(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzjp.zzn(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzgt.zzab(zzjp.zzl(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzgt.zzab(zzjp.zzl(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzjp.zzk(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzgt.zzab(zzjp.zzl(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzjp.zzk(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzgt.zzm(zzjp.zzm(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzjp.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object zzp = zzjp.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzjp.zzp(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzjp.zzk(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzjp.zzk(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzjp.zzk(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzgt.zzab(zzjp.zzl(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzjp.zzk(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzgt.zzab(zzjp.zzl(t, j));
                    break;
                case 17:
                    Object zzp2 = zzjp.zzp(t, j);
                    if (zzp2 != null) {
                        i4 = zzp2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzjp.zzp(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzjp.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzab(Double.doubleToLongBits(zzf(t, j)));
                        break;
                    }
                case 52:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzg(t, j));
                        break;
                    }
                case 53:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzab(zzi(t, j));
                        break;
                    }
                case 54:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzab(zzi(t, j));
                        break;
                    }
                case 55:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 56:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzab(zzi(t, j));
                        break;
                    }
                case 57:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 58:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzm(zzj(t, j));
                        break;
                    }
                case 59:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzjp.zzp(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzjp.zzp(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzjp.zzp(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 63:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 64:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 65:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzab(zzi(t, j));
                        break;
                    }
                case 66:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 67:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgt.zzab(zzi(t, j));
                        break;
                    }
                case 68:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzjp.zzp(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzzo.zzw(t).hashCode();
        if (this.zzzf) {
            return (hashCode * 53) + this.zzzp.zzf(t).hashCode();
        }
        return hashCode;
    }

    public final void zzd(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzza.length; i += 3) {
                int zzbq = zzbq(i);
                long j = (long) (1048575 & zzbq);
                int i2 = this.zzza[i];
                switch ((zzbq & 267386880) >>> 20) {
                    case 0:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzn(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zzb((Object) t, j, zzjp.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zzb((Object) t, j, zzjp.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzm(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzp(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzp(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zzb((Object) t, j, zzjp.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zzb((Object) t, j, zzjp.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zzb((Object) t, j, zzjp.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zzb((Object) t, j, zzjp.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzzn.zza(t, t2, j);
                        break;
                    case 50:
                        zzit.zza(this.zzzq, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzjp.zza((Object) t, j, zzjp.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzit.zza(this.zzzo, t, t2);
            if (this.zzzf) {
                zzit.zza(this.zzzp, t, t2);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzbq = (long) (zzbq(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zzjp.zzp(t, zzbq);
            Object zzp2 = zzjp.zzp(t2, zzbq);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzjp.zza((Object) t, zzbq, zzp2);
                    zzb(t, i);
                }
                return;
            }
            zzjp.zza((Object) t, zzbq, zzgt.zzb(zzp, zzp2));
            zzb(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzbq = zzbq(i);
        int i2 = this.zzza[i];
        long j = (long) (zzbq & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzjp.zzp(t, j);
            Object zzp2 = zzjp.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzjp.zza((Object) t, j, zzp2);
                    zzb(t, i2, i);
                }
                return;
            }
            zzjp.zza((Object) t, j, zzgt.zzb(zzp, zzp2));
            zzb(t, i2, i);
        }
    }

    public final int zzs(T t) {
        int i;
        int i2;
        long j;
        int i3;
        T t2 = t;
        int i4 = 267386880;
        if (this.zzzh) {
            Unsafe unsafe = zzyz;
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.zzza.length) {
                int zzbq = zzbq(i5);
                int i7 = (zzbq & i4) >>> 20;
                int i8 = this.zzza[i5];
                long j2 = (long) (zzbq & 1048575);
                if (i7 < zzgn.DOUBLE_LIST_PACKED.mo14798id() || i7 > zzgn.SINT64_LIST_PACKED.mo14798id()) {
                    i3 = 0;
                } else {
                    i3 = this.zzza[i5 + 2] & 1048575;
                }
                switch (i7) {
                    case 0:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzb(i8, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzb(i8, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzd(i8, zzjp.zzl(t2, j2));
                            break;
                        }
                    case 3:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zze(i8, zzjp.zzl(t2, j2));
                            break;
                        }
                    case 4:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzk(i8, zzjp.zzk(t2, j2));
                            break;
                        }
                    case 5:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzg(i8, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzn(i8, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzb(i8, true);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            Object zzp = zzjp.zzp(t2, j2);
                            if (!(zzp instanceof zzfh)) {
                                i6 += zzga.zzb(i8, (String) zzp);
                                break;
                            } else {
                                i6 += zzga.zzc(i8, (zzfh) zzp);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzit.zzc(i8, zzjp.zzp(t2, j2), zzbn(i5));
                            break;
                        }
                    case 10:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzc(i8, (zzfh) zzjp.zzp(t2, j2));
                            break;
                        }
                    case 11:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzl(i8, zzjp.zzk(t2, j2));
                            break;
                        }
                    case 12:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzp(i8, zzjp.zzk(t2, j2));
                            break;
                        }
                    case 13:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzo(i8, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzh(i8, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzm(i8, zzjp.zzk(t2, j2));
                            break;
                        }
                    case 16:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzf(i8, zzjp.zzl(t2, j2));
                            break;
                        }
                    case 17:
                        if (!zza(t2, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzc(i8, (zzic) zzjp.zzp(t2, j2), zzbn(i5));
                            break;
                        }
                    case 18:
                        i6 += zzit.zzw(i8, zze(t2, j2), false);
                        break;
                    case 19:
                        i6 += zzit.zzv(i8, zze(t2, j2), false);
                        break;
                    case 20:
                        i6 += zzit.zzo(i8, zze(t2, j2), false);
                        break;
                    case 21:
                        i6 += zzit.zzp(i8, zze(t2, j2), false);
                        break;
                    case 22:
                        i6 += zzit.zzs(i8, zze(t2, j2), false);
                        break;
                    case 23:
                        i6 += zzit.zzw(i8, zze(t2, j2), false);
                        break;
                    case 24:
                        i6 += zzit.zzv(i8, zze(t2, j2), false);
                        break;
                    case 25:
                        i6 += zzit.zzx(i8, zze(t2, j2), false);
                        break;
                    case 26:
                        i6 += zzit.zzc(i8, zze(t2, j2));
                        break;
                    case 27:
                        i6 += zzit.zzc(i8, zze(t2, j2), zzbn(i5));
                        break;
                    case 28:
                        i6 += zzit.zzd(i8, zze(t2, j2));
                        break;
                    case 29:
                        i6 += zzit.zzt(i8, zze(t2, j2), false);
                        break;
                    case 30:
                        i6 += zzit.zzr(i8, zze(t2, j2), false);
                        break;
                    case 31:
                        i6 += zzit.zzv(i8, zze(t2, j2), false);
                        break;
                    case 32:
                        i6 += zzit.zzw(i8, zze(t2, j2), false);
                        break;
                    case 33:
                        i6 += zzit.zzu(i8, zze(t2, j2), false);
                        break;
                    case 34:
                        i6 += zzit.zzq(i8, zze(t2, j2), false);
                        break;
                    case 35:
                        int zzy = zzit.zzy((List) unsafe.getObject(t2, j2));
                        if (zzy > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzy);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzy) + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzx = zzit.zzx((List) unsafe.getObject(t2, j2));
                        if (zzx > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzx);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzx) + zzx;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zzq = zzit.zzq((List) unsafe.getObject(t2, j2));
                        if (zzq > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzq);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzq) + zzq;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzr = zzit.zzr((List) unsafe.getObject(t2, j2));
                        if (zzr > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzr);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzr) + zzr;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zzu = zzit.zzu((List) unsafe.getObject(t2, j2));
                        if (zzu > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzu);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzu) + zzu;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzy2 = zzit.zzy((List) unsafe.getObject(t2, j2));
                        if (zzy2 > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzy2);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzy2) + zzy2;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzx2 = zzit.zzx((List) unsafe.getObject(t2, j2));
                        if (zzx2 > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzx2);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzx2) + zzx2;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzz = zzit.zzz((List) unsafe.getObject(t2, j2));
                        if (zzz > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzz);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzz) + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzv = zzit.zzv((List) unsafe.getObject(t2, j2));
                        if (zzv > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzv);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzv) + zzv;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzt = zzit.zzt((List) unsafe.getObject(t2, j2));
                        if (zzt > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzt);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzt) + zzt;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzx3 = zzit.zzx((List) unsafe.getObject(t2, j2));
                        if (zzx3 > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzx3);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzx3) + zzx3;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzy3 = zzit.zzy((List) unsafe.getObject(t2, j2));
                        if (zzy3 > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzy3);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzy3) + zzy3;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzw = zzit.zzw((List) unsafe.getObject(t2, j2));
                        if (zzw > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzw);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzw) + zzw;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzs = zzit.zzs((List) unsafe.getObject(t2, j2));
                        if (zzs > 0) {
                            if (this.zzzi) {
                                unsafe.putInt(t2, (long) i3, zzs);
                            }
                            i6 += zzga.zzbb(i8) + zzga.zzbd(zzs) + zzs;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i6 += zzit.zzd(i8, zze(t2, j2), zzbn(i5));
                        break;
                    case 50:
                        i6 += this.zzzq.zzb(i8, zzjp.zzp(t2, j2), zzbo(i5));
                        break;
                    case 51:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzb(i8, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzb(i8, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzd(i8, zzi(t2, j2));
                            break;
                        }
                    case 54:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zze(i8, zzi(t2, j2));
                            break;
                        }
                    case 55:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzk(i8, zzh(t2, j2));
                            break;
                        }
                    case 56:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzg(i8, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzn(i8, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzb(i8, true);
                            break;
                        }
                    case 59:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            Object zzp2 = zzjp.zzp(t2, j2);
                            if (!(zzp2 instanceof zzfh)) {
                                i6 += zzga.zzb(i8, (String) zzp2);
                                break;
                            } else {
                                i6 += zzga.zzc(i8, (zzfh) zzp2);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzit.zzc(i8, zzjp.zzp(t2, j2), zzbn(i5));
                            break;
                        }
                    case 61:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzc(i8, (zzfh) zzjp.zzp(t2, j2));
                            break;
                        }
                    case 62:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzl(i8, zzh(t2, j2));
                            break;
                        }
                    case 63:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzp(i8, zzh(t2, j2));
                            break;
                        }
                    case 64:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzo(i8, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzh(i8, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzm(i8, zzh(t2, j2));
                            break;
                        }
                    case 67:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzf(i8, zzi(t2, j2));
                            break;
                        }
                    case 68:
                        if (!zza(t2, i8, i5)) {
                            break;
                        } else {
                            i6 += zzga.zzc(i8, (zzic) zzjp.zzp(t2, j2), zzbn(i5));
                            break;
                        }
                }
                i5 += 3;
                i4 = 267386880;
            }
            return i6 + zza(this.zzzo, t2);
        }
        Unsafe unsafe2 = zzyz;
        int i9 = 0;
        int i10 = 0;
        int i11 = 1048575;
        int i12 = 0;
        while (i9 < this.zzza.length) {
            int zzbq2 = zzbq(i9);
            int[] iArr = this.zzza;
            int i13 = iArr[i9];
            int i14 = (zzbq2 & 267386880) >>> 20;
            if (i14 <= 17) {
                i2 = iArr[i9 + 2];
                int i15 = i2 & 1048575;
                i = 1 << (i2 >>> 20);
                if (i15 != i11) {
                    i12 = unsafe2.getInt(t2, (long) i15);
                } else {
                    i15 = i11;
                }
                i11 = i15;
            } else if (!this.zzzi || i14 < zzgn.DOUBLE_LIST_PACKED.mo14798id() || i14 > zzgn.SINT64_LIST_PACKED.mo14798id()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzza[i9 + 2] & 1048575;
                i = 0;
            }
            long j3 = (long) (zzbq2 & 1048575);
            switch (i14) {
                case 0:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzga.zzb(i13, 0.0d);
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzga.zzb(i13, 0.0f);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzga.zzd(i13, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzga.zze(i13, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzga.zzk(i13, unsafe2.getInt(t2, j3));
                        break;
                    }
                case 5:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        i10 += zzga.zzg(i13, 0);
                        break;
                    }
                case 6:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzn(i13, 0);
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzb(i13, true);
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        Object object = unsafe2.getObject(t2, j3);
                        if (!(object instanceof zzfh)) {
                            i10 += zzga.zzb(i13, (String) object);
                            j = 0;
                            break;
                        } else {
                            i10 += zzga.zzc(i13, (zzfh) object);
                            j = 0;
                            break;
                        }
                    }
                case 9:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzit.zzc(i13, unsafe2.getObject(t2, j3), zzbn(i9));
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzc(i13, (zzfh) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzl(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzp(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzo(i13, 0);
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzh(i13, 0);
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzm(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzf(i13, unsafe2.getLong(t2, j3));
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzc(i13, (zzic) unsafe2.getObject(t2, j3), zzbn(i9));
                        j = 0;
                        break;
                    }
                case 18:
                    i10 += zzit.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 19:
                    i10 += zzit.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 20:
                    i10 += zzit.zzo(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 21:
                    i10 += zzit.zzp(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 22:
                    i10 += zzit.zzs(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 23:
                    i10 += zzit.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 24:
                    i10 += zzit.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 25:
                    i10 += zzit.zzx(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 26:
                    i10 += zzit.zzc(i13, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 27:
                    i10 += zzit.zzc(i13, (List) unsafe2.getObject(t2, j3), zzbn(i9));
                    j = 0;
                    break;
                case 28:
                    i10 += zzit.zzd(i13, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 29:
                    i10 += zzit.zzt(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 30:
                    i10 += zzit.zzr(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 31:
                    i10 += zzit.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 32:
                    i10 += zzit.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 33:
                    i10 += zzit.zzu(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 34:
                    i10 += zzit.zzq(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzy4 = zzit.zzy((List) unsafe2.getObject(t2, j3));
                    if (zzy4 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzy4);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzy4) + zzy4;
                        j = 0;
                        break;
                    }
                case 36:
                    int zzx4 = zzit.zzx((List) unsafe2.getObject(t2, j3));
                    if (zzx4 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzx4);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzx4) + zzx4;
                        j = 0;
                        break;
                    }
                case 37:
                    int zzq2 = zzit.zzq((List) unsafe2.getObject(t2, j3));
                    if (zzq2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzq2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzq2) + zzq2;
                        j = 0;
                        break;
                    }
                case 38:
                    int zzr2 = zzit.zzr((List) unsafe2.getObject(t2, j3));
                    if (zzr2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzr2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzr2) + zzr2;
                        j = 0;
                        break;
                    }
                case 39:
                    int zzu2 = zzit.zzu((List) unsafe2.getObject(t2, j3));
                    if (zzu2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzu2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzu2) + zzu2;
                        j = 0;
                        break;
                    }
                case 40:
                    int zzy5 = zzit.zzy((List) unsafe2.getObject(t2, j3));
                    if (zzy5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzy5);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzy5) + zzy5;
                        j = 0;
                        break;
                    }
                case 41:
                    int zzx5 = zzit.zzx((List) unsafe2.getObject(t2, j3));
                    if (zzx5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzx5);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzx5) + zzx5;
                        j = 0;
                        break;
                    }
                case 42:
                    int zzz2 = zzit.zzz((List) unsafe2.getObject(t2, j3));
                    if (zzz2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzz2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzz2) + zzz2;
                        j = 0;
                        break;
                    }
                case 43:
                    int zzv2 = zzit.zzv((List) unsafe2.getObject(t2, j3));
                    if (zzv2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzv2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzv2) + zzv2;
                        j = 0;
                        break;
                    }
                case 44:
                    int zzt2 = zzit.zzt((List) unsafe2.getObject(t2, j3));
                    if (zzt2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzt2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzt2) + zzt2;
                        j = 0;
                        break;
                    }
                case 45:
                    int zzx6 = zzit.zzx((List) unsafe2.getObject(t2, j3));
                    if (zzx6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzx6);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzx6) + zzx6;
                        j = 0;
                        break;
                    }
                case 46:
                    int zzy6 = zzit.zzy((List) unsafe2.getObject(t2, j3));
                    if (zzy6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzy6);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzy6) + zzy6;
                        j = 0;
                        break;
                    }
                case 47:
                    int zzw2 = zzit.zzw((List) unsafe2.getObject(t2, j3));
                    if (zzw2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzw2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzw2) + zzw2;
                        j = 0;
                        break;
                    }
                case 48:
                    int zzs2 = zzit.zzs((List) unsafe2.getObject(t2, j3));
                    if (zzs2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzzi) {
                            unsafe2.putInt(t2, (long) i2, zzs2);
                        }
                        i10 += zzga.zzbb(i13) + zzga.zzbd(zzs2) + zzs2;
                        j = 0;
                        break;
                    }
                case 49:
                    i10 += zzit.zzd(i13, (List) unsafe2.getObject(t2, j3), zzbn(i9));
                    j = 0;
                    break;
                case 50:
                    i10 += this.zzzq.zzb(i13, unsafe2.getObject(t2, j3), zzbo(i9));
                    j = 0;
                    break;
                case 51:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzb(i13, 0.0d);
                        j = 0;
                        break;
                    }
                case 52:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzb(i13, 0.0f);
                        j = 0;
                        break;
                    }
                case 53:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzd(i13, zzi(t2, j3));
                        j = 0;
                        break;
                    }
                case 54:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zze(i13, zzi(t2, j3));
                        j = 0;
                        break;
                    }
                case 55:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzk(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 56:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzg(i13, 0);
                        j = 0;
                        break;
                    }
                case 57:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzn(i13, 0);
                        j = 0;
                        break;
                    }
                case 58:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzb(i13, true);
                        j = 0;
                        break;
                    }
                case 59:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        Object object2 = unsafe2.getObject(t2, j3);
                        if (!(object2 instanceof zzfh)) {
                            i10 += zzga.zzb(i13, (String) object2);
                            j = 0;
                            break;
                        } else {
                            i10 += zzga.zzc(i13, (zzfh) object2);
                            j = 0;
                            break;
                        }
                    }
                case 60:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzit.zzc(i13, unsafe2.getObject(t2, j3), zzbn(i9));
                        j = 0;
                        break;
                    }
                case 61:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzc(i13, (zzfh) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 62:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzl(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 63:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzp(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 64:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzo(i13, 0);
                        j = 0;
                        break;
                    }
                case 65:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzh(i13, 0);
                        j = 0;
                        break;
                    }
                case 66:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzm(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 67:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzf(i13, zzi(t2, j3));
                        j = 0;
                        break;
                    }
                case 68:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzga.zzc(i13, (zzic) unsafe2.getObject(t2, j3), zzbn(i9));
                        j = 0;
                        break;
                    }
                default:
                    j = 0;
                    break;
            }
            i9 += 3;
            long j4 = j;
        }
        int zza = i10 + zza(this.zzzo, t2);
        if (this.zzzf) {
            zzgi zzf = this.zzzp.zzf(t2);
            int i16 = 0;
            for (int i17 = 0; i17 < zzf.zzth.zzhx(); i17++) {
                Entry zzbv = zzf.zzth.zzbv(i17);
                i16 += zzgi.zzc((zzgk) zzbv.getKey(), zzbv.getValue());
            }
            for (Entry entry : zzf.zzth.zzhy()) {
                i16 += zzgi.zzc((zzgk) entry.getKey(), entry.getValue());
            }
            zza += i16;
        }
        return zza;
    }

    private static <UT, UB> int zza(zzjj<UT, UB> zzjj, T t) {
        return zzjj.zzs(zzjj.zzw(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzjp.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x05ad  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x05f1  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0b5f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.vision.zzkg r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzfj()
            int r1 = com.google.android.gms.internal.vision.zzgs.zzf.zzxd
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x05c3
            com.google.android.gms.internal.vision.zzjj<?, ?> r0 = r13.zzzo
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzzf
            if (r0 == 0) goto L_0x0036
            com.google.android.gms.internal.vision.zzgf<?> r0 = r13.zzzp
            com.google.android.gms.internal.vision.zzgi r0 = r0.zzf(r14)
            com.google.android.gms.internal.vision.zziw<T, java.lang.Object> r1 = r0.zzth
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0036
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0038
        L_0x0036:
            r0 = r3
            r1 = r0
        L_0x0038:
            int[] r7 = r13.zzza
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x003d:
            if (r7 < 0) goto L_0x05ab
            int r8 = r13.zzbq(r7)
            int[] r9 = r13.zzza
            r9 = r9[r7]
        L_0x0049:
            if (r1 == 0) goto L_0x0067
            com.google.android.gms.internal.vision.zzgf<?> r10 = r13.zzzp
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x0067
            com.google.android.gms.internal.vision.zzgf<?> r10 = r13.zzzp
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0049
        L_0x0065:
            r1 = r3
            goto L_0x0049
        L_0x0067:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x0596;
                case 1: goto L_0x0584;
                case 2: goto L_0x0572;
                case 3: goto L_0x0560;
                case 4: goto L_0x054e;
                case 5: goto L_0x053c;
                case 6: goto L_0x052a;
                case 7: goto L_0x0517;
                case 8: goto L_0x0505;
                case 9: goto L_0x04ef;
                case 10: goto L_0x04db;
                case 11: goto L_0x04c8;
                case 12: goto L_0x04b5;
                case 13: goto L_0x04a2;
                case 14: goto L_0x048f;
                case 15: goto L_0x047c;
                case 16: goto L_0x0469;
                case 17: goto L_0x0453;
                case 18: goto L_0x043f;
                case 19: goto L_0x042b;
                case 20: goto L_0x0417;
                case 21: goto L_0x0403;
                case 22: goto L_0x03ef;
                case 23: goto L_0x03db;
                case 24: goto L_0x03c7;
                case 25: goto L_0x03b3;
                case 26: goto L_0x039f;
                case 27: goto L_0x0387;
                case 28: goto L_0x0373;
                case 29: goto L_0x035f;
                case 30: goto L_0x034b;
                case 31: goto L_0x0337;
                case 32: goto L_0x0323;
                case 33: goto L_0x030f;
                case 34: goto L_0x02fb;
                case 35: goto L_0x02e7;
                case 36: goto L_0x02d3;
                case 37: goto L_0x02bf;
                case 38: goto L_0x02ab;
                case 39: goto L_0x0297;
                case 40: goto L_0x0283;
                case 41: goto L_0x026f;
                case 42: goto L_0x025b;
                case 43: goto L_0x0247;
                case 44: goto L_0x0233;
                case 45: goto L_0x021f;
                case 46: goto L_0x020b;
                case 47: goto L_0x01f7;
                case 48: goto L_0x01e3;
                case 49: goto L_0x01cb;
                case 50: goto L_0x01bf;
                case 51: goto L_0x01ad;
                case 52: goto L_0x019b;
                case 53: goto L_0x0189;
                case 54: goto L_0x0177;
                case 55: goto L_0x0165;
                case 56: goto L_0x0153;
                case 57: goto L_0x0141;
                case 58: goto L_0x012f;
                case 59: goto L_0x011d;
                case 60: goto L_0x0107;
                case 61: goto L_0x00f3;
                case 62: goto L_0x00e1;
                case 63: goto L_0x00cf;
                case 64: goto L_0x00bd;
                case 65: goto L_0x00ab;
                case 66: goto L_0x0099;
                case 67: goto L_0x0087;
                case 68: goto L_0x0071;
                default: goto L_0x006f;
            }
        L_0x006f:
            goto L_0x05a7
        L_0x0071:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzir r10 = r13.zzbn(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x05a7
        L_0x0087:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x05a7
        L_0x0099:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzi(r9, r8)
            goto L_0x05a7
        L_0x00ab:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x05a7
        L_0x00bd:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzq(r9, r8)
            goto L_0x05a7
        L_0x00cf:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzr(r9, r8)
            goto L_0x05a7
        L_0x00e1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzh(r9, r8)
            goto L_0x05a7
        L_0x00f3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzfh r8 = (com.google.android.gms.internal.vision.zzfh) r8
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x0107:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzir r10 = r13.zzbn(r7)
            r15.zza(r9, r8, r10)
            goto L_0x05a7
        L_0x011d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x05a7
        L_0x012f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x0141:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzj(r9, r8)
            goto L_0x05a7
        L_0x0153:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x05a7
        L_0x0165:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x05a7
        L_0x0177:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zza(r9, r10)
            goto L_0x05a7
        L_0x0189:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x05a7
        L_0x019b:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x01ad:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zza(r9, r10)
            goto L_0x05a7
        L_0x01bf:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x05a7
        L_0x01cb:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzir r10 = r13.zzbn(r7)
            com.google.android.gms.internal.vision.zzit.zzb(r9, r8, r15, r10)
            goto L_0x05a7
        L_0x01e3:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zze(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x01f7:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzj(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x020b:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzg(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x021f:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzl(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0233:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzm(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0247:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzi(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x025b:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzn(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x026f:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzk(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0283:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzf(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0297:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzh(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02ab:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzd(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02bf:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzc(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02d3:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzb(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02e7:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zza(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02fb:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zze(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x030f:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzj(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0323:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzg(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0337:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzl(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x034b:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzm(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x035f:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzi(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0373:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzb(r9, r8, r15)
            goto L_0x05a7
        L_0x0387:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzir r10 = r13.zzbn(r7)
            com.google.android.gms.internal.vision.zzit.zza(r9, r8, r15, r10)
            goto L_0x05a7
        L_0x039f:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zza(r9, r8, r15)
            goto L_0x05a7
        L_0x03b3:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzn(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03c7:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzk(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03db:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzf(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03ef:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzh(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0403:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzd(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0417:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzc(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x042b:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zzb(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x043f:
            int[] r9 = r13.zzza
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzit.zza(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0453:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzir r10 = r13.zzbn(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x05a7
        L_0x0469:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x05a7
        L_0x047c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r10)
            r15.zzi(r9, r8)
            goto L_0x05a7
        L_0x048f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x05a7
        L_0x04a2:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r10)
            r15.zzq(r9, r8)
            goto L_0x05a7
        L_0x04b5:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r10)
            r15.zzr(r9, r8)
            goto L_0x05a7
        L_0x04c8:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r10)
            r15.zzh(r9, r8)
            goto L_0x05a7
        L_0x04db:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzfh r8 = (com.google.android.gms.internal.vision.zzfh) r8
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x04ef:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzir r10 = r13.zzbn(r7)
            r15.zza(r9, r8, r10)
            goto L_0x05a7
        L_0x0505:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x05a7
        L_0x0517:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.vision.zzjp.zzm(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x052a:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r10)
            r15.zzj(r9, r8)
            goto L_0x05a7
        L_0x053c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x05a7
        L_0x054e:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x05a7
        L_0x0560:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r10)
            r15.zza(r9, r10)
            goto L_0x05a7
        L_0x0572:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x05a7
        L_0x0584:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.vision.zzjp.zzn(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x0596:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.vision.zzjp.zzo(r14, r10)
            r15.zza(r9, r10)
        L_0x05a7:
            int r7 = r7 + -3
            goto L_0x003d
        L_0x05ab:
            if (r1 == 0) goto L_0x05c2
            com.google.android.gms.internal.vision.zzgf<?> r14 = r13.zzzp
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x05c0
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x05ab
        L_0x05c0:
            r1 = r3
            goto L_0x05ab
        L_0x05c2:
            return
        L_0x05c3:
            boolean r0 = r13.zzzh
            if (r0 == 0) goto L_0x0b7a
            boolean r0 = r13.zzzf
            if (r0 == 0) goto L_0x05e8
            com.google.android.gms.internal.vision.zzgf<?> r0 = r13.zzzp
            com.google.android.gms.internal.vision.zzgi r0 = r0.zzf(r14)
            com.google.android.gms.internal.vision.zziw<T, java.lang.Object> r1 = r0.zzth
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x05e8
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x05ea
        L_0x05e8:
            r0 = r3
            r1 = r0
        L_0x05ea:
            int[] r7 = r13.zzza
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x05ef:
            if (r1 >= r7) goto L_0x0b5d
            int r9 = r13.zzbq(r1)
            int[] r10 = r13.zzza
            r10 = r10[r1]
        L_0x05fb:
            if (r8 == 0) goto L_0x0619
            com.google.android.gms.internal.vision.zzgf<?> r11 = r13.zzzp
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0619
            com.google.android.gms.internal.vision.zzgf<?> r11 = r13.zzzp
            r11.zza(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x0617
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x05fb
        L_0x0617:
            r8 = r3
            goto L_0x05fb
        L_0x0619:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0b48;
                case 1: goto L_0x0b36;
                case 2: goto L_0x0b24;
                case 3: goto L_0x0b12;
                case 4: goto L_0x0b00;
                case 5: goto L_0x0aee;
                case 6: goto L_0x0adc;
                case 7: goto L_0x0ac9;
                case 8: goto L_0x0ab7;
                case 9: goto L_0x0aa1;
                case 10: goto L_0x0a8d;
                case 11: goto L_0x0a7a;
                case 12: goto L_0x0a67;
                case 13: goto L_0x0a54;
                case 14: goto L_0x0a41;
                case 15: goto L_0x0a2e;
                case 16: goto L_0x0a1b;
                case 17: goto L_0x0a05;
                case 18: goto L_0x09f1;
                case 19: goto L_0x09dd;
                case 20: goto L_0x09c9;
                case 21: goto L_0x09b5;
                case 22: goto L_0x09a1;
                case 23: goto L_0x098d;
                case 24: goto L_0x0979;
                case 25: goto L_0x0965;
                case 26: goto L_0x0951;
                case 27: goto L_0x0939;
                case 28: goto L_0x0925;
                case 29: goto L_0x0911;
                case 30: goto L_0x08fd;
                case 31: goto L_0x08e9;
                case 32: goto L_0x08d5;
                case 33: goto L_0x08c1;
                case 34: goto L_0x08ad;
                case 35: goto L_0x0899;
                case 36: goto L_0x0885;
                case 37: goto L_0x0871;
                case 38: goto L_0x085d;
                case 39: goto L_0x0849;
                case 40: goto L_0x0835;
                case 41: goto L_0x0821;
                case 42: goto L_0x080d;
                case 43: goto L_0x07f9;
                case 44: goto L_0x07e5;
                case 45: goto L_0x07d1;
                case 46: goto L_0x07bd;
                case 47: goto L_0x07a9;
                case 48: goto L_0x0795;
                case 49: goto L_0x077d;
                case 50: goto L_0x0771;
                case 51: goto L_0x075f;
                case 52: goto L_0x074d;
                case 53: goto L_0x073b;
                case 54: goto L_0x0729;
                case 55: goto L_0x0717;
                case 56: goto L_0x0705;
                case 57: goto L_0x06f3;
                case 58: goto L_0x06e1;
                case 59: goto L_0x06cf;
                case 60: goto L_0x06b9;
                case 61: goto L_0x06a5;
                case 62: goto L_0x0693;
                case 63: goto L_0x0681;
                case 64: goto L_0x066f;
                case 65: goto L_0x065d;
                case 66: goto L_0x064b;
                case 67: goto L_0x0639;
                case 68: goto L_0x0623;
                default: goto L_0x0621;
            }
        L_0x0621:
            goto L_0x0b59
        L_0x0623:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzir r11 = r13.zzbn(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0b59
        L_0x0639:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0b59
        L_0x064b:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzi(r10, r9)
            goto L_0x0b59
        L_0x065d:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0b59
        L_0x066f:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzq(r10, r9)
            goto L_0x0b59
        L_0x0681:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzr(r10, r9)
            goto L_0x0b59
        L_0x0693:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzh(r10, r9)
            goto L_0x0b59
        L_0x06a5:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzfh r9 = (com.google.android.gms.internal.vision.zzfh) r9
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x06b9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzir r11 = r13.zzbn(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0b59
        L_0x06cf:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0b59
        L_0x06e1:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x06f3:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzj(r10, r9)
            goto L_0x0b59
        L_0x0705:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0b59
        L_0x0717:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0b59
        L_0x0729:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0b59
        L_0x073b:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0b59
        L_0x074d:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x075f:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0b59
        L_0x0771:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0b59
        L_0x077d:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzir r11 = r13.zzbn(r1)
            com.google.android.gms.internal.vision.zzit.zzb(r10, r9, r15, r11)
            goto L_0x0b59
        L_0x0795:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zze(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07a9:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzj(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07bd:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzg(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07d1:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzl(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07e5:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzm(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07f9:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzi(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x080d:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzn(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0821:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzk(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0835:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzf(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0849:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzh(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x085d:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzd(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0871:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzc(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0885:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzb(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0899:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zza(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x08ad:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zze(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08c1:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzj(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08d5:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzg(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08e9:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzl(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08fd:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzm(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0911:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzi(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0925:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzb(r10, r9, r15)
            goto L_0x0b59
        L_0x0939:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzir r11 = r13.zzbn(r1)
            com.google.android.gms.internal.vision.zzit.zza(r10, r9, r15, r11)
            goto L_0x0b59
        L_0x0951:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zza(r10, r9, r15)
            goto L_0x0b59
        L_0x0965:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzn(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0979:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzk(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x098d:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzf(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09a1:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzh(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09b5:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzd(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09c9:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzc(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09dd:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzb(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09f1:
            int[] r10 = r13.zzza
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zza(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0a05:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzir r11 = r13.zzbn(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0b59
        L_0x0a1b:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0b59
        L_0x0a2e:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r11)
            r15.zzi(r10, r9)
            goto L_0x0b59
        L_0x0a41:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0b59
        L_0x0a54:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r11)
            r15.zzq(r10, r9)
            goto L_0x0b59
        L_0x0a67:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r11)
            r15.zzr(r10, r9)
            goto L_0x0b59
        L_0x0a7a:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r11)
            r15.zzh(r10, r9)
            goto L_0x0b59
        L_0x0a8d:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzfh r9 = (com.google.android.gms.internal.vision.zzfh) r9
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0aa1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzir r11 = r13.zzbn(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0b59
        L_0x0ab7:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzjp.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0b59
        L_0x0ac9:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.vision.zzjp.zzm(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0adc:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r11)
            r15.zzj(r10, r9)
            goto L_0x0b59
        L_0x0aee:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0b59
        L_0x0b00:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzjp.zzk(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0b59
        L_0x0b12:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0b59
        L_0x0b24:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzjp.zzl(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0b59
        L_0x0b36:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.vision.zzjp.zzn(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0b48:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.vision.zzjp.zzo(r14, r11)
            r15.zza(r10, r11)
        L_0x0b59:
            int r1 = r1 + 3
            goto L_0x05ef
        L_0x0b5d:
            if (r8 == 0) goto L_0x0b74
            com.google.android.gms.internal.vision.zzgf<?> r1 = r13.zzzp
            r1.zza(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0b72
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0b5d
        L_0x0b72:
            r8 = r3
            goto L_0x0b5d
        L_0x0b74:
            com.google.android.gms.internal.vision.zzjj<?, ?> r0 = r13.zzzo
            zza(r0, (T) r14, r15)
            return
        L_0x0b7a:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, com.google.android.gms.internal.vision.zzkg):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:190:0x0563  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r18, com.google.android.gms.internal.vision.zzkg r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r0.zzzf
            if (r3 == 0) goto L_0x0025
            com.google.android.gms.internal.vision.zzgf<?> r3 = r0.zzzp
            com.google.android.gms.internal.vision.zzgi r3 = r3.zzf(r1)
            com.google.android.gms.internal.vision.zziw<T, java.lang.Object> r5 = r3.zzth
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0025
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0027
        L_0x0025:
            r3 = 0
            r5 = 0
        L_0x0027:
            int[] r6 = r0.zzza
            int r6 = r6.length
            sun.misc.Unsafe r7 = zzyz
            r11 = r5
            r5 = 0
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r12 = 0
        L_0x0034:
            if (r5 >= r6) goto L_0x0560
            int r13 = r0.zzbq(r5)
            int[] r14 = r0.zzza
            r15 = r14[r5]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r16
            int r4 = r16 >>> 20
            boolean r9 = r0.zzzh
            if (r9 != 0) goto L_0x006e
            r9 = 17
            if (r4 > r9) goto L_0x006e
            int r9 = r5 + 2
            r9 = r14[r9]
            r14 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r9 & r14
            if (r8 == r10) goto L_0x0064
            r14 = r11
            long r10 = (long) r8
            int r12 = r7.getInt(r1, r10)
            goto L_0x0066
        L_0x0064:
            r14 = r11
            r8 = r10
        L_0x0066:
            int r9 = r9 >>> 20
            r10 = 1
            int r9 = r10 << r9
            r10 = r8
            r11 = r14
            goto L_0x0071
        L_0x006e:
            r14 = r11
            r11 = r14
            r9 = 0
        L_0x0071:
            if (r11 == 0) goto L_0x0090
            com.google.android.gms.internal.vision.zzgf<?> r8 = r0.zzzp
            int r8 = r8.zza(r11)
            if (r8 > r15) goto L_0x0090
            com.google.android.gms.internal.vision.zzgf<?> r8 = r0.zzzp
            r8.zza(r2, r11)
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x008e
            java.lang.Object r8 = r3.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            r11 = r8
            goto L_0x0071
        L_0x008e:
            r11 = 0
            goto L_0x0071
        L_0x0090:
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r13 & r8
            long r13 = (long) r13
            switch(r4) {
                case 0: goto L_0x0550;
                case 1: goto L_0x0543;
                case 2: goto L_0x0537;
                case 3: goto L_0x052b;
                case 4: goto L_0x051f;
                case 5: goto L_0x0513;
                case 6: goto L_0x0507;
                case 7: goto L_0x04fa;
                case 8: goto L_0x04ee;
                case 9: goto L_0x04dd;
                case 10: goto L_0x04ce;
                case 11: goto L_0x04c1;
                case 12: goto L_0x04b4;
                case 13: goto L_0x04a7;
                case 14: goto L_0x049a;
                case 15: goto L_0x048d;
                case 16: goto L_0x0480;
                case 17: goto L_0x046e;
                case 18: goto L_0x045b;
                case 19: goto L_0x0448;
                case 20: goto L_0x0435;
                case 21: goto L_0x0422;
                case 22: goto L_0x040f;
                case 23: goto L_0x03fc;
                case 24: goto L_0x03e9;
                case 25: goto L_0x03d6;
                case 26: goto L_0x03c4;
                case 27: goto L_0x03ad;
                case 28: goto L_0x039b;
                case 29: goto L_0x0388;
                case 30: goto L_0x0375;
                case 31: goto L_0x0362;
                case 32: goto L_0x034f;
                case 33: goto L_0x033c;
                case 34: goto L_0x0329;
                case 35: goto L_0x0316;
                case 36: goto L_0x0303;
                case 37: goto L_0x02f0;
                case 38: goto L_0x02dd;
                case 39: goto L_0x02ca;
                case 40: goto L_0x02b7;
                case 41: goto L_0x02a4;
                case 42: goto L_0x0291;
                case 43: goto L_0x027e;
                case 44: goto L_0x026b;
                case 45: goto L_0x0258;
                case 46: goto L_0x0245;
                case 47: goto L_0x0232;
                case 48: goto L_0x021f;
                case 49: goto L_0x0208;
                case 50: goto L_0x01fe;
                case 51: goto L_0x01eb;
                case 52: goto L_0x01d8;
                case 53: goto L_0x01c5;
                case 54: goto L_0x01b2;
                case 55: goto L_0x019f;
                case 56: goto L_0x018c;
                case 57: goto L_0x0179;
                case 58: goto L_0x0166;
                case 59: goto L_0x0153;
                case 60: goto L_0x013c;
                case 61: goto L_0x0127;
                case 62: goto L_0x0114;
                case 63: goto L_0x0101;
                case 64: goto L_0x00ee;
                case 65: goto L_0x00db;
                case 66: goto L_0x00c8;
                case 67: goto L_0x00b5;
                case 68: goto L_0x009d;
                default: goto L_0x009a;
            }
        L_0x009a:
            r4 = 0
            goto L_0x055c
        L_0x009d:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x00b2
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzir r9 = r0.zzbn(r5)
            r2.zzb(r15, r4, r9)
            r4 = 0
            goto L_0x055c
        L_0x00b2:
            r4 = 0
            goto L_0x055c
        L_0x00b5:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x00c5
            long r13 = zzi(r1, r13)
            r2.zzb(r15, r13)
            r4 = 0
            goto L_0x055c
        L_0x00c5:
            r4 = 0
            goto L_0x055c
        L_0x00c8:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x00d8
            int r4 = zzh(r1, r13)
            r2.zzi(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x00d8:
            r4 = 0
            goto L_0x055c
        L_0x00db:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x00eb
            long r13 = zzi(r1, r13)
            r2.zzj(r15, r13)
            r4 = 0
            goto L_0x055c
        L_0x00eb:
            r4 = 0
            goto L_0x055c
        L_0x00ee:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x00fe
            int r4 = zzh(r1, r13)
            r2.zzq(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x00fe:
            r4 = 0
            goto L_0x055c
        L_0x0101:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0111
            int r4 = zzh(r1, r13)
            r2.zzr(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x0111:
            r4 = 0
            goto L_0x055c
        L_0x0114:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0124
            int r4 = zzh(r1, r13)
            r2.zzh(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x0124:
            r4 = 0
            goto L_0x055c
        L_0x0127:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0139
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzfh r4 = (com.google.android.gms.internal.vision.zzfh) r4
            r2.zza(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x0139:
            r4 = 0
            goto L_0x055c
        L_0x013c:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0150
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzir r9 = r0.zzbn(r5)
            r2.zza(r15, r4, r9)
            r4 = 0
            goto L_0x055c
        L_0x0150:
            r4 = 0
            goto L_0x055c
        L_0x0153:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0163
            java.lang.Object r4 = r7.getObject(r1, r13)
            zza(r15, r4, r2)
            r4 = 0
            goto L_0x055c
        L_0x0163:
            r4 = 0
            goto L_0x055c
        L_0x0166:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0176
            boolean r4 = zzj(r1, r13)
            r2.zza(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x0176:
            r4 = 0
            goto L_0x055c
        L_0x0179:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x0189
            int r4 = zzh(r1, r13)
            r2.zzj(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x0189:
            r4 = 0
            goto L_0x055c
        L_0x018c:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x019c
            long r13 = zzi(r1, r13)
            r2.zzc(r15, r13)
            r4 = 0
            goto L_0x055c
        L_0x019c:
            r4 = 0
            goto L_0x055c
        L_0x019f:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x01af
            int r4 = zzh(r1, r13)
            r2.zzg(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x01af:
            r4 = 0
            goto L_0x055c
        L_0x01b2:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x01c2
            long r13 = zzi(r1, r13)
            r2.zza(r15, r13)
            r4 = 0
            goto L_0x055c
        L_0x01c2:
            r4 = 0
            goto L_0x055c
        L_0x01c5:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x01d5
            long r13 = zzi(r1, r13)
            r2.zzi(r15, r13)
            r4 = 0
            goto L_0x055c
        L_0x01d5:
            r4 = 0
            goto L_0x055c
        L_0x01d8:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x01e8
            float r4 = zzg(r1, r13)
            r2.zza(r15, r4)
            r4 = 0
            goto L_0x055c
        L_0x01e8:
            r4 = 0
            goto L_0x055c
        L_0x01eb:
            boolean r4 = r0.zza((T) r1, r15, r5)
            if (r4 == 0) goto L_0x01fb
            double r13 = zzf(r1, r13)
            r2.zza(r15, r13)
            r4 = 0
            goto L_0x055c
        L_0x01fb:
            r4 = 0
            goto L_0x055c
        L_0x01fe:
            java.lang.Object r4 = r7.getObject(r1, r13)
            r0.zza(r2, r15, r4, r5)
            r4 = 0
            goto L_0x055c
        L_0x0208:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzir r13 = r0.zzbn(r5)
            com.google.android.gms.internal.vision.zzit.zzb(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x021f:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zze(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0232:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzj(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0245:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzg(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0258:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzl(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x026b:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzm(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x027e:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzi(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0291:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzn(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x02a4:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzk(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x02b7:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzf(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x02ca:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzh(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x02dd:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzd(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x02f0:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzc(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0303:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zzb(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0316:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.vision.zzit.zza(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0329:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zze(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x033c:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzj(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x034f:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzg(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0362:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzl(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0375:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzm(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0388:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzi(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x039b:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zzb(r4, r9, r2)
            r4 = 0
            goto L_0x055c
        L_0x03ad:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzir r13 = r0.zzbn(r5)
            com.google.android.gms.internal.vision.zzit.zza(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x03c4:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzit.zza(r4, r9, r2)
            r4 = 0
            goto L_0x055c
        L_0x03d6:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzn(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x03e9:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzk(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x03fc:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzf(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x040f:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzh(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0422:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzd(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0435:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzc(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x0448:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zzb(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x045b:
            int[] r4 = r0.zzza
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.vision.zzit.zza(r4, r9, r2, r13)
            r4 = 0
            goto L_0x055c
        L_0x046e:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            java.lang.Object r9 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzir r13 = r0.zzbn(r5)
            r2.zzb(r15, r9, r13)
            goto L_0x055c
        L_0x0480:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            long r13 = r7.getLong(r1, r13)
            r2.zzb(r15, r13)
            goto L_0x055c
        L_0x048d:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            int r9 = r7.getInt(r1, r13)
            r2.zzi(r15, r9)
            goto L_0x055c
        L_0x049a:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            long r13 = r7.getLong(r1, r13)
            r2.zzj(r15, r13)
            goto L_0x055c
        L_0x04a7:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            int r9 = r7.getInt(r1, r13)
            r2.zzq(r15, r9)
            goto L_0x055c
        L_0x04b4:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            int r9 = r7.getInt(r1, r13)
            r2.zzr(r15, r9)
            goto L_0x055c
        L_0x04c1:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            int r9 = r7.getInt(r1, r13)
            r2.zzh(r15, r9)
            goto L_0x055c
        L_0x04ce:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            java.lang.Object r9 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzfh r9 = (com.google.android.gms.internal.vision.zzfh) r9
            r2.zza(r15, r9)
            goto L_0x055c
        L_0x04dd:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            java.lang.Object r9 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzir r13 = r0.zzbn(r5)
            r2.zza(r15, r9, r13)
            goto L_0x055c
        L_0x04ee:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            java.lang.Object r9 = r7.getObject(r1, r13)
            zza(r15, r9, r2)
            goto L_0x055c
        L_0x04fa:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            boolean r9 = com.google.android.gms.internal.vision.zzjp.zzm(r1, r13)
            r2.zza(r15, r9)
            goto L_0x055c
        L_0x0507:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            int r9 = r7.getInt(r1, r13)
            r2.zzj(r15, r9)
            goto L_0x055c
        L_0x0513:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            long r13 = r7.getLong(r1, r13)
            r2.zzc(r15, r13)
            goto L_0x055c
        L_0x051f:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            int r9 = r7.getInt(r1, r13)
            r2.zzg(r15, r9)
            goto L_0x055c
        L_0x052b:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            long r13 = r7.getLong(r1, r13)
            r2.zza(r15, r13)
            goto L_0x055c
        L_0x0537:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            long r13 = r7.getLong(r1, r13)
            r2.zzi(r15, r13)
            goto L_0x055c
        L_0x0543:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            float r9 = com.google.android.gms.internal.vision.zzjp.zzn(r1, r13)
            r2.zza(r15, r9)
            goto L_0x055c
        L_0x0550:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x055c
            double r13 = com.google.android.gms.internal.vision.zzjp.zzo(r1, r13)
            r2.zza(r15, r13)
        L_0x055c:
            int r5 = r5 + 3
            goto L_0x0034
        L_0x0560:
            r14 = r11
        L_0x0561:
            if (r14 == 0) goto L_0x0578
            com.google.android.gms.internal.vision.zzgf<?> r4 = r0.zzzp
            r4.zza(r2, r14)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0576
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r14 = r4
            goto L_0x0561
        L_0x0576:
            r14 = 0
            goto L_0x0561
        L_0x0578:
            com.google.android.gms.internal.vision.zzjj<?, ?> r3 = r0.zzzo
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zzb(java.lang.Object, com.google.android.gms.internal.vision.zzkg):void");
    }

    private final <K, V> void zza(zzkg zzkg, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzkg.zza(i, this.zzzq.zzq(zzbo(i2)), this.zzzq.zzm(obj));
        }
    }

    private static <UT, UB> void zza(zzjj<UT, UB> zzjj, T t, zzkg zzkg) throws IOException {
        zzjj.zza(zzjj.zzw(t), zzkg);
    }

    public final void zza(T t, zzis zzis, zzgd zzgd) throws IOException {
        Object obj;
        zzgi zzgi;
        if (zzgd != null) {
            zzjj<?, ?> zzjj = this.zzzo;
            zzgf<?> zzgf = this.zzzp;
            zzgi zzgi2 = null;
            Object obj2 = null;
            while (true) {
                try {
                    int zzdu = zzis.zzdu();
                    int zzbt = zzbt(zzdu);
                    if (zzbt >= 0) {
                        int zzbq = zzbq(zzbt);
                        switch ((267386880 & zzbq) >>> 20) {
                            case 0:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.readDouble());
                                zzb(t, zzbt);
                                break;
                            case 1:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.readFloat());
                                zzb(t, zzbt);
                                break;
                            case 2:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzdx());
                                zzb(t, zzbt);
                                break;
                            case 3:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzdw());
                                zzb(t, zzbt);
                                break;
                            case 4:
                                zzjp.zzb((Object) t, (long) (zzbq & 1048575), zzis.zzdy());
                                zzb(t, zzbt);
                                break;
                            case 5:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzdz());
                                zzb(t, zzbt);
                                break;
                            case 6:
                                zzjp.zzb((Object) t, (long) (zzbq & 1048575), zzis.zzea());
                                zzb(t, zzbt);
                                break;
                            case 7:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzeb());
                                zzb(t, zzbt);
                                break;
                            case 8:
                                zza((Object) t, zzbq, zzis);
                                zzb(t, zzbt);
                                break;
                            case 9:
                                if (!zza(t, zzbt)) {
                                    zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zza(zzbn(zzbt), zzgd));
                                    zzb(t, zzbt);
                                    break;
                                } else {
                                    long j = (long) (zzbq & 1048575);
                                    zzjp.zza((Object) t, j, zzgt.zzb(zzjp.zzp(t, j), zzis.zza(zzbn(zzbt), zzgd)));
                                    break;
                                }
                            case 10:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) zzis.zzed());
                                zzb(t, zzbt);
                                break;
                            case 11:
                                zzjp.zzb((Object) t, (long) (zzbq & 1048575), zzis.zzee());
                                zzb(t, zzbt);
                                break;
                            case 12:
                                int zzef = zzis.zzef();
                                zzgy zzbp = zzbp(zzbt);
                                if (zzbp != null) {
                                    if (!zzbp.zzg(zzef)) {
                                        obj2 = zzit.zza(zzdu, zzef, obj2, zzjj);
                                        break;
                                    }
                                }
                                zzjp.zzb((Object) t, (long) (zzbq & 1048575), zzef);
                                zzb(t, zzbt);
                                break;
                            case 13:
                                zzjp.zzb((Object) t, (long) (zzbq & 1048575), zzis.zzeg());
                                zzb(t, zzbt);
                                break;
                            case 14:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzeh());
                                zzb(t, zzbt);
                                break;
                            case 15:
                                zzjp.zzb((Object) t, (long) (zzbq & 1048575), zzis.zzei());
                                zzb(t, zzbt);
                                break;
                            case 16:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzej());
                                zzb(t, zzbt);
                                break;
                            case 17:
                                if (!zza(t, zzbt)) {
                                    zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzc(zzbn(zzbt), zzgd));
                                    zzb(t, zzbt);
                                    break;
                                } else {
                                    long j2 = (long) (zzbq & 1048575);
                                    zzjp.zza((Object) t, j2, zzgt.zzb(zzjp.zzp(t, j2), zzis.zzc(zzbn(zzbt), zzgd)));
                                    break;
                                }
                            case 18:
                                zzis.zza(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 19:
                                zzis.zzb(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 20:
                                zzis.zzd(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 21:
                                zzis.zzc(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 22:
                                zzis.zze(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 23:
                                zzis.zzf(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 24:
                                zzis.zzg(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 25:
                                zzis.zzh(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 26:
                                if (!zzbs(zzbq)) {
                                    zzis.readStringList(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                    break;
                                } else {
                                    zzis.zzi(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                    break;
                                }
                            case 27:
                                zzis.zza(this.zzzn.zza(t, (long) (zzbq & 1048575)), zzbn(zzbt), zzgd);
                                break;
                            case 28:
                                zzis.zzj(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 29:
                                zzis.zzk(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 30:
                                List zza = this.zzzn.zza(t, (long) (zzbq & 1048575));
                                zzis.zzl(zza);
                                obj2 = zzit.zza(zzdu, zza, zzbp(zzbt), obj2, zzjj);
                                break;
                            case 31:
                                zzis.zzm(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 32:
                                zzis.zzn(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 33:
                                zzis.zzo(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 34:
                                zzis.zzp(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 35:
                                zzis.zza(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 36:
                                zzis.zzb(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 37:
                                zzis.zzd(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 38:
                                zzis.zzc(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 39:
                                zzis.zze(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 40:
                                zzis.zzf(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 41:
                                zzis.zzg(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 42:
                                zzis.zzh(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 43:
                                zzis.zzk(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 44:
                                List zza2 = this.zzzn.zza(t, (long) (zzbq & 1048575));
                                zzis.zzl(zza2);
                                obj2 = zzit.zza(zzdu, zza2, zzbp(zzbt), obj2, zzjj);
                                break;
                            case 45:
                                zzis.zzm(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 46:
                                zzis.zzn(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 47:
                                zzis.zzo(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 48:
                                zzis.zzp(this.zzzn.zza(t, (long) (zzbq & 1048575)));
                                break;
                            case 49:
                                long j3 = (long) (zzbq & 1048575);
                                zzis.zzb(this.zzzn.zza(t, j3), zzbn(zzbt), zzgd);
                                break;
                            case 50:
                                Object zzbo = zzbo(zzbt);
                                long zzbq2 = (long) (zzbq(zzbt) & 1048575);
                                Object zzp = zzjp.zzp(t, zzbq2);
                                if (zzp == null) {
                                    zzp = this.zzzq.zzp(zzbo);
                                    zzjp.zza((Object) t, zzbq2, zzp);
                                } else if (this.zzzq.zzn(zzp)) {
                                    Object zzp2 = this.zzzq.zzp(zzbo);
                                    this.zzzq.zzc(zzp2, zzp);
                                    zzjp.zza((Object) t, zzbq2, zzp2);
                                    zzp = zzp2;
                                }
                                zzis.zza(this.zzzq.zzl(zzp), this.zzzq.zzq(zzbo), zzgd);
                                break;
                            case 51:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Double.valueOf(zzis.readDouble()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 52:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Float.valueOf(zzis.readFloat()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 53:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Long.valueOf(zzis.zzdx()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 54:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Long.valueOf(zzis.zzdw()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 55:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Integer.valueOf(zzis.zzdy()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 56:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Long.valueOf(zzis.zzdz()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 57:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Integer.valueOf(zzis.zzea()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 58:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Boolean.valueOf(zzis.zzeb()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 59:
                                zza((Object) t, zzbq, zzis);
                                zzb(t, zzdu, zzbt);
                                break;
                            case 60:
                                if (zza(t, zzdu, zzbt)) {
                                    long j4 = (long) (zzbq & 1048575);
                                    zzjp.zza((Object) t, j4, zzgt.zzb(zzjp.zzp(t, j4), zzis.zza(zzbn(zzbt), zzgd)));
                                } else {
                                    zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zza(zzbn(zzbt), zzgd));
                                    zzb(t, zzbt);
                                }
                                zzb(t, zzdu, zzbt);
                                break;
                            case 61:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) zzis.zzed());
                                zzb(t, zzdu, zzbt);
                                break;
                            case 62:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Integer.valueOf(zzis.zzee()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 63:
                                int zzef2 = zzis.zzef();
                                zzgy zzbp2 = zzbp(zzbt);
                                if (zzbp2 != null) {
                                    if (!zzbp2.zzg(zzef2)) {
                                        obj2 = zzit.zza(zzdu, zzef2, obj2, zzjj);
                                        break;
                                    }
                                }
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Integer.valueOf(zzef2));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 64:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Integer.valueOf(zzis.zzeg()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 65:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Long.valueOf(zzis.zzeh()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 66:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Integer.valueOf(zzis.zzei()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 67:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), (Object) Long.valueOf(zzis.zzej()));
                                zzb(t, zzdu, zzbt);
                                break;
                            case 68:
                                zzjp.zza((Object) t, (long) (zzbq & 1048575), zzis.zzc(zzbn(zzbt), zzgd));
                                zzb(t, zzdu, zzbt);
                                break;
                            default:
                                if (obj2 == null) {
                                    obj2 = zzjj.zzif();
                                }
                                if (zzjj.zza(obj2, zzis)) {
                                    break;
                                } else {
                                    for (int i = this.zzzk; i < this.zzzl; i++) {
                                        obj2 = zza((Object) t, this.zzzj[i], (UB) obj2, zzjj);
                                    }
                                    if (obj2 != null) {
                                        zzjj.zzg(t, obj2);
                                    }
                                    return;
                                }
                        }
                    } else if (zzdu == Integer.MAX_VALUE) {
                        for (int i2 = this.zzzk; i2 < this.zzzl; i2++) {
                            obj2 = zza((Object) t, this.zzzj[i2], (UB) obj2, zzjj);
                        }
                        if (obj2 != null) {
                            zzjj.zzg(t, obj2);
                        }
                        return;
                    } else {
                        if (!this.zzzf) {
                            obj = null;
                        } else {
                            obj = zzgf.zza(zzgd, this.zzze, zzdu);
                        }
                        if (obj != null) {
                            if (zzgi2 == null) {
                                zzgi = zzgf.zzg(t);
                            } else {
                                zzgi = zzgi2;
                            }
                            obj2 = zzgf.zza(zzis, obj, zzgd, zzgi, obj2, zzjj);
                            zzgi2 = zzgi;
                        } else {
                            zzjj.zza(zzis);
                            if (obj2 == null) {
                                obj2 = zzjj.zzx(t);
                            }
                            if (!zzjj.zza(obj2, zzis)) {
                                for (int i3 = this.zzzk; i3 < this.zzzl; i3++) {
                                    obj2 = zza((Object) t, this.zzzj[i3], (UB) obj2, zzjj);
                                }
                                if (obj2 != null) {
                                    zzjj.zzg(t, obj2);
                                }
                                return;
                            }
                        }
                    }
                } catch (zzhb e) {
                    zzjj.zza(zzis);
                    if (obj2 == null) {
                        obj2 = zzjj.zzx(t);
                    }
                    if (!zzjj.zza(obj2, zzis)) {
                        for (int i4 = this.zzzk; i4 < this.zzzl; i4++) {
                            obj2 = zza((Object) t, this.zzzj[i4], (UB) obj2, zzjj);
                        }
                        if (obj2 != null) {
                            zzjj.zzg(t, obj2);
                        }
                        return;
                    }
                } catch (Throwable th) {
                    for (int i5 = this.zzzk; i5 < this.zzzl; i5++) {
                        obj2 = zza((Object) t, this.zzzj[i5], (UB) obj2, zzjj);
                    }
                    if (obj2 != null) {
                        zzjj.zzg(t, obj2);
                    }
                    throw th;
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    private static zzjm zzt(Object obj) {
        zzgs zzgs = (zzgs) obj;
        zzjm zzjm = zzgs.zzwj;
        if (zzjm != zzjm.zzig()) {
            return zzjm;
        }
        zzjm zzih = zzjm.zzih();
        zzgs.zzwj = zzih;
        return zzih;
    }

    private static int zza(byte[] bArr, int i, int i2, zzka zzka, Class<?> cls, zzfb zzfb) throws IOException {
        switch (zzka) {
            case BOOL:
                int zzb = zzez.zzb(bArr, i, zzfb);
                zzfb.zzrw = Boolean.valueOf(zzfb.zzrv != 0);
                return zzb;
            case BYTES:
                return zzez.zze(bArr, i, zzfb);
            case DOUBLE:
                zzfb.zzrw = Double.valueOf(zzez.zzc(bArr, i));
                return i + 8;
            case FIXED32:
            case SFIXED32:
                zzfb.zzrw = Integer.valueOf(zzez.zza(bArr, i));
                return i + 4;
            case FIXED64:
            case SFIXED64:
                zzfb.zzrw = Long.valueOf(zzez.zzb(bArr, i));
                return i + 8;
            case FLOAT:
                zzfb.zzrw = Float.valueOf(zzez.zzd(bArr, i));
                return i + 4;
            case ENUM:
            case INT32:
            case UINT32:
                int zza = zzez.zza(bArr, i, zzfb);
                zzfb.zzrw = Integer.valueOf(zzfb.zzru);
                return zza;
            case INT64:
            case UINT64:
                int zzb2 = zzez.zzb(bArr, i, zzfb);
                zzfb.zzrw = Long.valueOf(zzfb.zzrv);
                return zzb2;
            case MESSAGE:
                return zzez.zza(zzin.zzho().zzf(cls), bArr, i, i2, zzfb);
            case SINT32:
                int zza2 = zzez.zza(bArr, i, zzfb);
                zzfb.zzrw = Integer.valueOf(zzft.zzav(zzfb.zzru));
                return zza2;
            case SINT64:
                int zzb3 = zzez.zzb(bArr, i, zzfb);
                zzfb.zzrw = Long.valueOf(zzft.zzr(zzfb.zzrv));
                return zzb3;
            case STRING:
                return zzez.zzd(bArr, i, zzfb);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzfb zzfb) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i12 = i;
        int i13 = i2;
        int i14 = i3;
        int i15 = i5;
        int i16 = i6;
        long j3 = j2;
        zzfb zzfb2 = zzfb;
        zzgz zzgz = (zzgz) zzyz.getObject(t2, j3);
        if (!zzgz.zzdo()) {
            int size = zzgz.size();
            zzgz = zzgz.zzah(size == 0 ? 10 : size << 1);
            zzyz.putObject(t2, j3, zzgz);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    return zzez.zzf(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 1) {
                    zzgb zzgb = (zzgb) zzgz;
                    zzgb.zzc(zzez.zzc(bArr, i));
                    int i17 = i12 + 8;
                    while (i17 < i13) {
                        int zza = zzez.zza(bArr2, i17, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return i17;
                        }
                        zzgb.zzc(zzez.zzc(bArr2, zza));
                        i17 = zza + 8;
                    }
                    return i17;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    return zzez.zze(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 5) {
                    zzgo zzgo = (zzgo) zzgz;
                    zzgo.zzu(zzez.zzd(bArr, i));
                    int i18 = i12 + 4;
                    while (i18 < i13) {
                        int zza2 = zzez.zza(bArr2, i18, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return i18;
                        }
                        zzgo.zzu(zzez.zzd(bArr2, zza2));
                        i18 = zza2 + 4;
                    }
                    return i18;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i15 == 2) {
                    return zzez.zzb(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 0) {
                    zzhq zzhq = (zzhq) zzgz;
                    int zzb = zzez.zzb(bArr2, i12, zzfb2);
                    zzhq.zzac(zzfb2.zzrv);
                    while (zzb < i13) {
                        int zza3 = zzez.zza(bArr2, zzb, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return zzb;
                        }
                        zzb = zzez.zzb(bArr2, zza3, zzfb2);
                        zzhq.zzac(zzfb2.zzrv);
                    }
                    return zzb;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzez.zza(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 0) {
                    return zzez.zza(i3, bArr, i, i2, zzgz, zzfb);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    return zzez.zzd(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 1) {
                    zzhq zzhq2 = (zzhq) zzgz;
                    zzhq2.zzac(zzez.zzb(bArr, i));
                    int i19 = i12 + 8;
                    while (i19 < i13) {
                        int zza4 = zzez.zza(bArr2, i19, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return i19;
                        }
                        zzhq2.zzac(zzez.zzb(bArr2, zza4));
                        i19 = zza4 + 8;
                    }
                    return i19;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i15 == 2) {
                    return zzez.zzc(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 5) {
                    zzgu zzgu = (zzgu) zzgz;
                    zzgu.zzbm(zzez.zza(bArr, i));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zza5 = zzez.zza(bArr2, i20, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return i20;
                        }
                        zzgu.zzbm(zzez.zza(bArr2, zza5));
                        i20 = zza5 + 4;
                    }
                    return i20;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    return zzez.zzg(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 0) {
                    zzff zzff = (zzff) zzgz;
                    int zzb2 = zzez.zzb(bArr2, i12, zzfb2);
                    zzff.addBoolean(zzfb2.zzrv != 0);
                    while (zzb2 < i13) {
                        int zza6 = zzez.zza(bArr2, zzb2, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return zzb2;
                        }
                        zzb2 = zzez.zzb(bArr2, zza6, zzfb2);
                        zzff.addBoolean(zzfb2.zzrv != 0);
                    }
                    return zzb2;
                }
                break;
            case 26:
                if (i15 == 2) {
                    String str = "";
                    if ((j & 536870912) == 0) {
                        int zza7 = zzez.zza(bArr2, i12, zzfb2);
                        int i21 = zzfb2.zzru;
                        if (i21 >= 0) {
                            if (i21 == 0) {
                                zzgz.add(str);
                            } else {
                                zzgz.add(new String(bArr2, zza7, i21, zzgt.UTF_8));
                                zza7 += i21;
                            }
                            while (i9 < i13) {
                                int zza8 = zzez.zza(bArr2, i9, zzfb2);
                                if (i14 != zzfb2.zzru) {
                                    return i9;
                                }
                                i9 = zzez.zza(bArr2, zza8, zzfb2);
                                int i22 = zzfb2.zzru;
                                if (i22 < 0) {
                                    throw zzhc.zzgn();
                                } else if (i22 == 0) {
                                    zzgz.add(str);
                                } else {
                                    zzgz.add(new String(bArr2, i9, i22, zzgt.UTF_8));
                                    i9 += i22;
                                }
                            }
                            return i9;
                        }
                        throw zzhc.zzgn();
                    }
                    int zza9 = zzez.zza(bArr2, i12, zzfb2);
                    int i23 = zzfb2.zzru;
                    if (i23 >= 0) {
                        if (i23 == 0) {
                            zzgz.add(str);
                        } else {
                            int i24 = zza9 + i23;
                            if (zzjs.zzf(bArr2, zza9, i24)) {
                                zzgz.add(new String(bArr2, zza9, i23, zzgt.UTF_8));
                                zza9 = i24;
                            } else {
                                throw zzhc.zzgt();
                            }
                        }
                        while (i8 < i13) {
                            int zza10 = zzez.zza(bArr2, i8, zzfb2);
                            if (i14 != zzfb2.zzru) {
                                return i8;
                            }
                            i8 = zzez.zza(bArr2, zza10, zzfb2);
                            int i25 = zzfb2.zzru;
                            if (i25 < 0) {
                                throw zzhc.zzgn();
                            } else if (i25 == 0) {
                                zzgz.add(str);
                            } else {
                                int i26 = i8 + i25;
                                if (zzjs.zzf(bArr2, i8, i26)) {
                                    zzgz.add(new String(bArr2, i8, i25, zzgt.UTF_8));
                                    i8 = i26;
                                } else {
                                    throw zzhc.zzgt();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzhc.zzgn();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zzez.zza(zzbn(i16), i3, bArr, i, i2, zzgz, zzfb);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zza11 = zzez.zza(bArr2, i12, zzfb2);
                    int i27 = zzfb2.zzru;
                    if (i27 < 0) {
                        throw zzhc.zzgn();
                    } else if (i27 <= bArr2.length - zza11) {
                        if (i27 == 0) {
                            zzgz.add(zzfh.zzsd);
                        } else {
                            zzgz.add(zzfh.zza(bArr2, zza11, i27));
                            zza11 += i27;
                        }
                        while (i10 < i13) {
                            int zza12 = zzez.zza(bArr2, i10, zzfb2);
                            if (i14 != zzfb2.zzru) {
                                return i10;
                            }
                            i10 = zzez.zza(bArr2, zza12, zzfb2);
                            int i28 = zzfb2.zzru;
                            if (i28 < 0) {
                                throw zzhc.zzgn();
                            } else if (i28 > bArr2.length - i10) {
                                throw zzhc.zzgm();
                            } else if (i28 == 0) {
                                zzgz.add(zzfh.zzsd);
                            } else {
                                zzgz.add(zzfh.zza(bArr2, i10, i28));
                                i10 += i28;
                            }
                        }
                        return i10;
                    } else {
                        throw zzhc.zzgm();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzez.zza(bArr2, i12, zzgz, zzfb2);
                } else if (i15 == 0) {
                    i11 = zzez.zza(i3, bArr, i, i2, zzgz, zzfb);
                }
                zzgs zzgs = (zzgs) t2;
                zzjm zzjm = zzgs.zzwj;
                if (zzjm == zzjm.zzig()) {
                    zzjm = null;
                }
                zzjm zzjm2 = (zzjm) zzit.zza(i4, (List<Integer>) zzgz, zzbp(i16), zzjm, this.zzzo);
                if (zzjm2 != null) {
                    zzgs.zzwj = zzjm2;
                }
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    return zzez.zzh(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 0) {
                    zzgu zzgu2 = (zzgu) zzgz;
                    int zza13 = zzez.zza(bArr2, i12, zzfb2);
                    zzgu2.zzbm(zzft.zzav(zzfb2.zzru));
                    while (zza13 < i13) {
                        int zza14 = zzez.zza(bArr2, zza13, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return zza13;
                        }
                        zza13 = zzez.zza(bArr2, zza14, zzfb2);
                        zzgu2.zzbm(zzft.zzav(zzfb2.zzru));
                    }
                    return zza13;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    return zzez.zzi(bArr2, i12, zzgz, zzfb2);
                }
                if (i15 == 0) {
                    zzhq zzhq3 = (zzhq) zzgz;
                    int zzb3 = zzez.zzb(bArr2, i12, zzfb2);
                    zzhq3.zzac(zzft.zzr(zzfb2.zzrv));
                    while (zzb3 < i13) {
                        int zza15 = zzez.zza(bArr2, zzb3, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return zzb3;
                        }
                        zzb3 = zzez.zzb(bArr2, zza15, zzfb2);
                        zzhq3.zzac(zzft.zzr(zzfb2.zzrv));
                    }
                    return zzb3;
                }
                break;
            case 49:
                if (i15 == 3) {
                    zzir zzbn = zzbn(i16);
                    int i29 = (i14 & -8) | 4;
                    int zza16 = zzez.zza(zzbn, bArr, i, i2, i29, zzfb);
                    zzgz.add(zzfb2.zzrw);
                    while (zza16 < i13) {
                        int zza17 = zzez.zza(bArr2, zza16, zzfb2);
                        if (i14 != zzfb2.zzru) {
                            return zza16;
                        }
                        zza16 = zzez.zza(zzbn, bArr, zza17, i2, i29, zzfb);
                        zzgz.add(zzfb2.zzrw);
                    }
                    return zza16;
                }
                break;
        }
        return i12;
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzfb zzfb) throws IOException {
        int i4;
        int i5;
        Unsafe unsafe = zzyz;
        Object zzbo = zzbo(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzzq.zzn(object)) {
            Object zzp = this.zzzq.zzp(zzbo);
            this.zzzq.zzc(zzp, object);
            unsafe.putObject(t, j, zzp);
            object = zzp;
        }
        zzht zzq = this.zzzq.zzq(zzbo);
        Map zzl = this.zzzq.zzl(object);
        int zza = zzez.zza(bArr, i, zzfb);
        int i6 = zzfb.zzru;
        if (i6 < 0 || i6 > i2 - zza) {
            throw zzhc.zzgm();
        }
        int i7 = i6 + zza;
        K k = zzq.zzyt;
        V v = zzq.zzgd;
        while (zza < i7) {
            int i8 = zza + 1;
            byte b = bArr[zza];
            if (b < 0) {
                i5 = zzez.zza((int) b, bArr, i8, zzfb);
                i4 = zzfb.zzru;
            } else {
                i5 = i8;
                i4 = b;
            }
            int i9 = i4 >>> 3;
            int i10 = i4 & 7;
            if (i9 != 1) {
                if (i9 == 2 && i10 == zzq.zzyu.zziq()) {
                    zza = zza(bArr, i5, i2, zzq.zzyu, zzq.zzgd.getClass(), zzfb);
                    v = zzfb.zzrw;
                }
            } else if (i10 == zzq.zzys.zziq()) {
                zza = zza(bArr, i5, i2, zzq.zzys, null, zzfb);
                k = zzfb.zzrw;
            }
            zza = zzez.zza(i4, bArr, i5, i2, zzfb);
        }
        if (zza == i7) {
            zzl.put(k, v);
            return i7;
        }
        throw zzhc.zzgs();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a4, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.vision.zzfb r29) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r2 = r21
            r8 = r22
            r5 = r23
            r9 = r26
            r6 = r28
            r11 = r29
            sun.misc.Unsafe r12 = zzyz
            int[] r7 = r0.zzza
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r25) {
                case 51: goto L_0x0193;
                case 52: goto L_0x0183;
                case 53: goto L_0x0173;
                case 54: goto L_0x0173;
                case 55: goto L_0x0163;
                case 56: goto L_0x0152;
                case 57: goto L_0x0142;
                case 58: goto L_0x0129;
                case 59: goto L_0x00f5;
                case 60: goto L_0x00c6;
                case 61: goto L_0x00b9;
                case 62: goto L_0x0163;
                case 63: goto L_0x008b;
                case 64: goto L_0x0142;
                case 65: goto L_0x0152;
                case 66: goto L_0x0076;
                case 67: goto L_0x0061;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x01a8
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x01a8
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.vision.zzir r2 = r0.zzbn(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004c
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004d
        L_0x004c:
            r15 = 0
        L_0x004d:
            if (r15 != 0) goto L_0x0056
            java.lang.Object r3 = r11.zzrw
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0056:
            java.lang.Object r3 = r11.zzrw
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzgt.zzb(r15, r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0061:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zzb(r3, r4, r11)
            long r3 = r11.zzrv
            long r3 = com.google.android.gms.internal.vision.zzft.zzr(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0076:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r3, r4, r11)
            int r3 = r11.zzru
            int r3 = com.google.android.gms.internal.vision.zzft.zzav(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x008b:
            if (r5 != 0) goto L_0x01a8
            int r3 = com.google.android.gms.internal.vision.zzez.zza(r3, r4, r11)
            int r4 = r11.zzru
            com.google.android.gms.internal.vision.zzgy r5 = r0.zzbp(r6)
            if (r5 == 0) goto L_0x00af
            boolean r5 = r5.zzg(r4)
            if (r5 == 0) goto L_0x00a0
            goto L_0x00af
        L_0x00a0:
            com.google.android.gms.internal.vision.zzjm r1 = zzt(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zzb(r2, r4)
            r2 = r3
            goto L_0x01a9
        L_0x00af:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x01a4
        L_0x00b9:
            if (r5 != r15) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zze(r3, r4, r11)
            java.lang.Object r3 = r11.zzrw
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x00c6:
            if (r5 != r15) goto L_0x01a8
            com.google.android.gms.internal.vision.zzir r2 = r0.zzbn(r6)
            r5 = r20
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00de
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00df
        L_0x00de:
            r15 = 0
        L_0x00df:
            if (r15 != 0) goto L_0x00e7
            java.lang.Object r3 = r11.zzrw
            r12.putObject(r1, r9, r3)
            goto L_0x00f0
        L_0x00e7:
            java.lang.Object r3 = r11.zzrw
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzgt.zzb(r15, r3)
            r12.putObject(r1, r9, r3)
        L_0x00f0:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x00f5:
            if (r5 != r15) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r3, r4, r11)
            int r4 = r11.zzru
            if (r4 != 0) goto L_0x0105
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0124
        L_0x0105:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0119
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.vision.zzjs.zzf(r3, r2, r5)
            if (r5 == 0) goto L_0x0114
            goto L_0x0119
        L_0x0114:
            com.google.android.gms.internal.vision.zzhc r1 = com.google.android.gms.internal.vision.zzhc.zzgt()
            throw r1
        L_0x0119:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.vision.zzgt.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0124:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x0129:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zzb(r3, r4, r11)
            long r3 = r11.zzrv
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0139
            r15 = 1
            goto L_0x013a
        L_0x0139:
            r15 = 0
        L_0x013a:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0142:
            if (r5 != r7) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a4
        L_0x0152:
            r2 = 1
            if (r5 != r2) goto L_0x01a8
            long r2 = com.google.android.gms.internal.vision.zzez.zzb(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
            goto L_0x01a4
        L_0x0163:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r3, r4, r11)
            int r3 = r11.zzru
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0173:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.vision.zzez.zzb(r3, r4, r11)
            long r3 = r11.zzrv
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0183:
            if (r5 != r7) goto L_0x01a8
            float r2 = com.google.android.gms.internal.vision.zzez.zzd(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a4
        L_0x0193:
            r2 = 1
            if (r5 != r2) goto L_0x01a8
            double r2 = com.google.android.gms.internal.vision.zzez.zzc(r18, r19)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
        L_0x01a4:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x01a8:
            r2 = r4
        L_0x01a9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.vision.zzfb):int");
    }

    private final zzir zzbn(int i) {
        int i2 = (i / 3) << 1;
        zzir zzir = (zzir) this.zzzb[i2];
        if (zzir != null) {
            return zzir;
        }
        zzir zzf = zzin.zzho().zzf((Class) this.zzzb[i2 + 1]);
        this.zzzb[i2] = zzf;
        return zzf;
    }

    private final Object zzbo(int i) {
        return this.zzzb[(i / 3) << 1];
    }

    private final zzgy zzbp(int i) {
        return (zzgy) this.zzzb[((i / 3) << 1) + 1];
    }

    /* JADX WARNING: type inference failed for: r33v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v11, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r4v5, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r18v0 */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r15v4 */
    /* JADX WARNING: type inference failed for: r0v15, types: [int] */
    /* JADX WARNING: type inference failed for: r1v9, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r15v6 */
    /* JADX WARNING: type inference failed for: r15v7 */
    /* JADX WARNING: type inference failed for: r15v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r15v10 */
    /* JADX WARNING: type inference failed for: r15v11 */
    /* JADX WARNING: type inference failed for: r0v23, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r15v12, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v24, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v25, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r17v1, types: [java.lang.Double] */
    /* JADX WARNING: type inference failed for: r0v30 */
    /* JADX WARNING: type inference failed for: r17v2, types: [java.lang.Float] */
    /* JADX WARNING: type inference failed for: r0v32 */
    /* JADX WARNING: type inference failed for: r17v3, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r0v34 */
    /* JADX WARNING: type inference failed for: r17v4, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r0v36 */
    /* JADX WARNING: type inference failed for: r17v5, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r0v38 */
    /* JADX WARNING: type inference failed for: r17v6, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r0v40 */
    /* JADX WARNING: type inference failed for: r17v7, types: [java.lang.Boolean] */
    /* JADX WARNING: type inference failed for: r0v42 */
    /* JADX WARNING: type inference failed for: r17v8, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r0v45 */
    /* JADX WARNING: type inference failed for: r17v9, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r0v48 */
    /* JADX WARNING: type inference failed for: r0v49, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v50, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v28, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v54, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v57, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v58 */
    /* JADX WARNING: type inference failed for: r17v10, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r0v60 */
    /* JADX WARNING: type inference failed for: r15v13 */
    /* JADX WARNING: type inference failed for: r15v14 */
    /* JADX WARNING: type inference failed for: r15v15, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v63, types: [com.google.android.gms.internal.vision.zzgb, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v64, types: [com.google.android.gms.internal.vision.zzgo, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v65, types: [com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v66, types: [com.google.android.gms.internal.vision.zzgu, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v67, types: [com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v68, types: [com.google.android.gms.internal.vision.zzgu, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v69, types: [com.google.android.gms.internal.vision.zzff, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v70, types: [com.google.android.gms.internal.vision.zzgu, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v23, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v71, types: [com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARNING: type inference failed for: r15v24, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v73, types: [int] */
    /* JADX WARNING: type inference failed for: r1v46, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r15v25 */
    /* JADX WARNING: type inference failed for: r3v34 */
    /* JADX WARNING: type inference failed for: r23v5 */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: type inference failed for: r2v40 */
    /* JADX WARNING: type inference failed for: r18v2 */
    /* JADX WARNING: type inference failed for: r18v3 */
    /* JADX WARNING: type inference failed for: r2v42, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v15, types: [int] */
    /* JADX WARNING: type inference failed for: r7v2 */
    /* JADX WARNING: type inference failed for: r15v28 */
    /* JADX WARNING: type inference failed for: r3v36 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r2v45, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r3v38 */
    /* JADX WARNING: type inference failed for: r2v48, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v19, types: [int] */
    /* JADX WARNING: type inference failed for: r18v4 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r3v39 */
    /* JADX WARNING: type inference failed for: r18v5 */
    /* JADX WARNING: type inference failed for: r1v57, types: [int] */
    /* JADX WARNING: type inference failed for: r2v51, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v40 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r11v13 */
    /* JADX WARNING: type inference failed for: r18v6 */
    /* JADX WARNING: type inference failed for: r11v14 */
    /* JADX WARNING: type inference failed for: r2v54, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v41 */
    /* JADX WARNING: type inference failed for: r12v17 */
    /* JADX WARNING: type inference failed for: r11v16 */
    /* JADX WARNING: type inference failed for: r2v56, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v42 */
    /* JADX WARNING: type inference failed for: r12v19 */
    /* JADX WARNING: type inference failed for: r11v18 */
    /* JADX WARNING: type inference failed for: r2v58, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r6v58 */
    /* JADX WARNING: type inference failed for: r4v24 */
    /* JADX WARNING: type inference failed for: r6v59 */
    /* JADX WARNING: type inference failed for: r3v44 */
    /* JADX WARNING: type inference failed for: r12v21 */
    /* JADX WARNING: type inference failed for: r11v20 */
    /* JADX WARNING: type inference failed for: r2v61, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v45 */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: type inference failed for: r11v22 */
    /* JADX WARNING: type inference failed for: r2v63, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r3v46 */
    /* JADX WARNING: type inference failed for: r12v25 */
    /* JADX WARNING: type inference failed for: r11v24 */
    /* JADX WARNING: type inference failed for: r2v66, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v47 */
    /* JADX WARNING: type inference failed for: r12v27 */
    /* JADX WARNING: type inference failed for: r11v26 */
    /* JADX WARNING: type inference failed for: r2v68, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v49 */
    /* JADX WARNING: type inference failed for: r12v29 */
    /* JADX WARNING: type inference failed for: r11v28 */
    /* JADX WARNING: type inference failed for: r2v70, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v50 */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r11v30 */
    /* JADX WARNING: type inference failed for: r2v72, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r3v52 */
    /* JADX WARNING: type inference failed for: r11v32 */
    /* JADX WARNING: type inference failed for: r2v74, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v54 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r11v34, types: [int] */
    /* JADX WARNING: type inference failed for: r2v76, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v56 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r3v57 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r11v37 */
    /* JADX WARNING: type inference failed for: r2v79, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v59 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r11v39 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r2v81 */
    /* JADX WARNING: type inference failed for: r7v8, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v60 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r11v41 */
    /* JADX WARNING: type inference failed for: r2v84 */
    /* JADX WARNING: type inference failed for: r1v90, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v42 */
    /* JADX WARNING: type inference failed for: r3v62 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: type inference failed for: r11v44 */
    /* JADX WARNING: type inference failed for: r2v89 */
    /* JADX WARNING: type inference failed for: r18v7 */
    /* JADX WARNING: type inference failed for: r4v42 */
    /* JADX WARNING: type inference failed for: r3v63, types: [int] */
    /* JADX WARNING: type inference failed for: r4v43 */
    /* JADX WARNING: type inference failed for: r12v48 */
    /* JADX WARNING: type inference failed for: r3v65 */
    /* JADX WARNING: type inference failed for: r15v36 */
    /* JADX WARNING: type inference failed for: r15v37 */
    /* JADX WARNING: type inference failed for: r0v148 */
    /* JADX WARNING: type inference failed for: r0v149 */
    /* JADX WARNING: type inference failed for: r0v150 */
    /* JADX WARNING: type inference failed for: r0v151 */
    /* JADX WARNING: type inference failed for: r0v152 */
    /* JADX WARNING: type inference failed for: r15v38 */
    /* JADX WARNING: type inference failed for: r0v153 */
    /* JADX WARNING: type inference failed for: r15v39 */
    /* JADX WARNING: type inference failed for: r0v154 */
    /* JADX WARNING: type inference failed for: r15v40 */
    /* JADX WARNING: type inference failed for: r0v155 */
    /* JADX WARNING: type inference failed for: r15v41 */
    /* JADX WARNING: type inference failed for: r0v156 */
    /* JADX WARNING: type inference failed for: r15v42 */
    /* JADX WARNING: type inference failed for: r0v157 */
    /* JADX WARNING: type inference failed for: r15v43 */
    /* JADX WARNING: type inference failed for: r0v158 */
    /* JADX WARNING: type inference failed for: r15v44 */
    /* JADX WARNING: type inference failed for: r0v159 */
    /* JADX WARNING: type inference failed for: r15v45 */
    /* JADX WARNING: type inference failed for: r0v160 */
    /* JADX WARNING: type inference failed for: r15v46 */
    /* JADX WARNING: type inference failed for: r0v161 */
    /* JADX WARNING: type inference failed for: r15v47 */
    /* JADX WARNING: type inference failed for: r18v8 */
    /* JADX WARNING: type inference failed for: r18v9 */
    /* JADX WARNING: type inference failed for: r18v10 */
    /* JADX WARNING: type inference failed for: r11v45 */
    /* JADX WARNING: type inference failed for: r11v46 */
    /* JADX WARNING: type inference failed for: r11v47 */
    /* JADX WARNING: type inference failed for: r11v48 */
    /* JADX WARNING: type inference failed for: r11v49 */
    /* JADX WARNING: type inference failed for: r11v50 */
    /* JADX WARNING: type inference failed for: r11v51 */
    /* JADX WARNING: type inference failed for: r11v52 */
    /* JADX WARNING: type inference failed for: r11v53 */
    /* JADX WARNING: type inference failed for: r11v54 */
    /* JADX WARNING: type inference failed for: r11v55 */
    /* JADX WARNING: type inference failed for: r11v56 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v11, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r33v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 964
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 102 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.vision.zzfb r37) throws java.io.IOException {
        /*
            r31 = this;
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r9 = r37
            sun.misc.Unsafe r10 = zzyz
            r16 = 0
            r0 = r34
            r1 = -1
            r2 = 0
            r3 = 0
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001d:
            r17 = 0
            if (r0 >= r13) goto L_0x080d
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0030
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r12, r3, r9)
            int r3 = r9.zzru
            r4 = r3
            r3 = r0
            goto L_0x0031
        L_0x0030:
            r4 = r0
        L_0x0031:
            int r0 = r4 >>> 3
            r7 = r4 & 7
            r8 = 3
            if (r0 <= r1) goto L_0x003f
            int r2 = r2 / r8
            int r1 = r15.zzs(r0, r2)
            r2 = r1
            goto L_0x0044
        L_0x003f:
            int r1 = r15.zzbt(r0)
            r2 = r1
        L_0x0044:
            r20 = 0
            r8 = -1
            if (r2 != r8) goto L_0x005c
            r26 = r0
            r2 = r3
            r18 = r4
            r23 = r5
            r30 = r10
            r15 = r11
            r19 = -1
            r24 = 1
            r27 = 0
            goto L_0x0497
        L_0x005c:
            int[] r8 = r15.zzza
            int r23 = r2 + 1
            r1 = r8[r23]
            r23 = 267386880(0xff00000, float:2.3665827E-29)
            r23 = r1 & r23
            int r11 = r23 >>> 20
            r23 = r4
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r1 & r18
            long r12 = (long) r4
            r4 = 17
            if (r11 > r4) goto L_0x0372
            int r24 = r2 + 2
            r8 = r8[r24]
            int r24 = r8 >>> 20
            r22 = 1
            int r24 = r22 << r24
            r26 = r12
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r8 & r12
            if (r8 == r6) goto L_0x0098
            if (r6 == r12) goto L_0x0090
            long r12 = (long) r6
            r10.putInt(r14, r12, r5)
        L_0x0090:
            long r5 = (long) r8
            int r5 = r10.getInt(r14, r5)
            r6 = r5
            goto L_0x009a
        L_0x0098:
            r8 = r6
            r6 = r5
        L_0x009a:
            r5 = 5
            switch(r11) {
                case 0: goto L_0x033b;
                case 1: goto L_0x0316;
                case 2: goto L_0x02e6;
                case 3: goto L_0x02e6;
                case 4: goto L_0x02c1;
                case 5: goto L_0x0290;
                case 6: goto L_0x026a;
                case 7: goto L_0x023e;
                case 8: goto L_0x020e;
                case 9: goto L_0x01cb;
                case 10: goto L_0x01a4;
                case 11: goto L_0x02c1;
                case 12: goto L_0x0158;
                case 13: goto L_0x026a;
                case 14: goto L_0x0290;
                case 15: goto L_0x012e;
                case 16: goto L_0x00f9;
                case 17: goto L_0x00a9;
                default: goto L_0x009e;
            }
        L_0x009e:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r0 = 1
            r2 = r33
            goto L_0x035e
        L_0x00a9:
            r1 = 3
            if (r7 != r1) goto L_0x00ee
            int r1 = r0 << 3
            r4 = r1 | 4
            com.google.android.gms.internal.vision.zzir r1 = r15.zzbn(r2)
            r12 = r0
            r0 = r1
            r1 = r33
            r13 = r2
            r2 = r3
            r3 = r35
            r11 = r23
            r5 = r37
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x00d1
            java.lang.Object r1 = r9.zzrw
            r2 = r26
            r10.putObject(r14, r2, r1)
            goto L_0x00e0
        L_0x00d1:
            r2 = r26
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r9.zzrw
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzgt.zzb(r1, r4)
            r10.putObject(r14, r2, r1)
        L_0x00e0:
            r5 = r6 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r2 = r13
            r12 = r33
            r13 = r35
            r11 = r36
            goto L_0x001d
        L_0x00ee:
            r12 = r0
            r13 = r2
            r11 = r23
            r2 = r33
            r34 = r6
            r0 = 1
            goto L_0x035e
        L_0x00f9:
            r12 = r0
            r13 = r2
            r11 = r23
            r0 = r26
            if (r7 != 0) goto L_0x0126
            r7 = r33
            r4 = r0
            int r17 = com.google.android.gms.internal.vision.zzez.zzb(r7, r3, r9)
            long r0 = r9.zzrv
            long r20 = com.google.android.gms.internal.vision.zzft.zzr(r0)
            r0 = r10
            r1 = r32
            r2 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            r5 = r6 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r2 = r13
            r0 = r17
            r13 = r35
            r11 = r36
            r12 = r7
            goto L_0x001d
        L_0x0126:
            r7 = r33
            r34 = r6
            r2 = r7
            r0 = 1
            goto L_0x035e
        L_0x012e:
            r12 = r0
            r13 = r2
            r11 = r23
            r0 = r26
            r2 = r33
            if (r7 != 0) goto L_0x0153
            int r3 = com.google.android.gms.internal.vision.zzez.zza(r2, r3, r9)
            int r4 = r9.zzru
            int r4 = com.google.android.gms.internal.vision.zzft.zzav(r4)
            r10.putInt(r14, r0, r4)
            r5 = r6 | r24
            r0 = r3
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x0153:
            r34 = r6
            r0 = 1
            goto L_0x035e
        L_0x0158:
            r12 = r0
            r13 = r2
            r11 = r23
            r0 = r26
            r2 = r33
            if (r7 != 0) goto L_0x019f
            int r3 = com.google.android.gms.internal.vision.zzez.zza(r2, r3, r9)
            int r4 = r9.zzru
            com.google.android.gms.internal.vision.zzgy r5 = r15.zzbp(r13)
            if (r5 == 0) goto L_0x018e
            boolean r5 = r5.zzg(r4)
            if (r5 == 0) goto L_0x0175
            goto L_0x018e
        L_0x0175:
            com.google.android.gms.internal.vision.zzjm r0 = zzt(r32)
            long r4 = (long) r4
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            r0.zzb(r11, r1)
            r0 = r3
            r5 = r6
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x018e:
            r10.putInt(r14, r0, r4)
            r5 = r6 | r24
            r0 = r3
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x019f:
            r34 = r6
            r0 = 1
            goto L_0x035e
        L_0x01a4:
            r12 = r0
            r13 = r2
            r11 = r23
            r0 = r26
            r2 = r33
            r5 = 2
            if (r7 != r5) goto L_0x01c6
            int r3 = com.google.android.gms.internal.vision.zzez.zze(r2, r3, r9)
            java.lang.Object r4 = r9.zzrw
            r10.putObject(r14, r0, r4)
            r5 = r6 | r24
            r0 = r3
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x01c6:
            r34 = r6
            r0 = 1
            goto L_0x035e
        L_0x01cb:
            r12 = r0
            r13 = r2
            r11 = r23
            r0 = r26
            r2 = r33
            r5 = 2
            if (r7 != r5) goto L_0x0207
            com.google.android.gms.internal.vision.zzir r4 = r15.zzbn(r13)
            r5 = r35
            int r3 = com.google.android.gms.internal.vision.zzez.zza(r4, r2, r3, r5, r9)
            r4 = r6 & r24
            if (r4 != 0) goto L_0x01eb
            java.lang.Object r4 = r9.zzrw
            r10.putObject(r14, r0, r4)
            goto L_0x01f9
        L_0x01eb:
            java.lang.Object r4 = r10.getObject(r14, r0)
            java.lang.Object r7 = r9.zzrw
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzgt.zzb(r4, r7)
            r10.putObject(r14, r0, r4)
        L_0x01f9:
            r0 = r6 | r24
            r6 = r8
            r1 = r12
            r12 = r2
            r2 = r13
            r13 = r5
            r5 = r0
            r0 = r3
            r3 = r11
            r11 = r36
            goto L_0x001d
        L_0x0207:
            r5 = r35
            r34 = r6
            r0 = 1
            goto L_0x035e
        L_0x020e:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r2 = r33
            r0 = 2
            if (r7 != r0) goto L_0x023b
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0225
            int r0 = com.google.android.gms.internal.vision.zzez.zzc(r2, r3, r9)
            goto L_0x0229
        L_0x0225:
            int r0 = com.google.android.gms.internal.vision.zzez.zzd(r2, r3, r9)
        L_0x0229:
            java.lang.Object r1 = r9.zzrw
            r10.putObject(r14, r5, r1)
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x023b:
            r0 = 1
            goto L_0x035e
        L_0x023e:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r2 = r33
            if (r7 != 0) goto L_0x0267
            int r0 = com.google.android.gms.internal.vision.zzez.zzb(r2, r3, r9)
            long r3 = r9.zzrv
            int r1 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r1 == 0) goto L_0x0256
            r1 = 1
            goto L_0x0257
        L_0x0256:
            r1 = 0
        L_0x0257:
            com.google.android.gms.internal.vision.zzjp.zza(r14, r5, r1)
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x0267:
            r0 = 1
            goto L_0x035e
        L_0x026a:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r0 = 5
            r2 = r33
            if (r7 != r0) goto L_0x028d
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r2, r3)
            r10.putInt(r14, r5, r0)
            int r0 = r3 + 4
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x028d:
            r0 = 1
            goto L_0x035e
        L_0x0290:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r2 = r33
            r0 = 1
            if (r7 != r0) goto L_0x02bc
            long r20 = com.google.android.gms.internal.vision.zzez.zzb(r2, r3)
            r0 = r10
            r1 = r32
            r7 = r2
            r4 = r3
            r2 = r5
            r6 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            int r0 = r6 + 8
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r2 = r13
            r13 = r35
            r11 = r36
            r12 = r7
            goto L_0x001d
        L_0x02bc:
            r7 = r2
            r6 = r3
            r0 = 1
            goto L_0x035e
        L_0x02c1:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r2 = r33
            if (r7 != 0) goto L_0x02e3
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r2, r3, r9)
            int r1 = r9.zzru
            r10.putInt(r14, r5, r1)
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x02e3:
            r0 = 1
            goto L_0x035e
        L_0x02e6:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r2 = r33
            if (r7 != 0) goto L_0x0313
            int r7 = com.google.android.gms.internal.vision.zzez.zzb(r2, r3, r9)
            long r3 = r9.zzrv
            r0 = r10
            r1 = r32
            r20 = r3
            r4 = r2
            r2 = r5
            r6 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            r5 = r34 | r24
            r0 = r7
            r3 = r11
            r1 = r12
            r2 = r13
            r13 = r35
            r11 = r36
            r12 = r6
            r6 = r8
            goto L_0x001d
        L_0x0313:
            r6 = r2
            r0 = 1
            goto L_0x035e
        L_0x0316:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r0 = 5
            r2 = r33
            if (r7 != r0) goto L_0x0339
            float r0 = com.google.android.gms.internal.vision.zzez.zzd(r2, r3)
            com.google.android.gms.internal.vision.zzjp.zza(r14, r5, r0)
            int r0 = r3 + 4
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x0339:
            r0 = 1
            goto L_0x035e
        L_0x033b:
            r12 = r0
            r13 = r2
            r34 = r6
            r11 = r23
            r5 = r26
            r2 = r33
            r0 = 1
            if (r7 != r0) goto L_0x035e
            double r0 = com.google.android.gms.internal.vision.zzez.zzc(r2, r3)
            com.google.android.gms.internal.vision.zzjp.zza(r14, r5, r0)
            int r0 = r3 + 8
            r5 = r34 | r24
            r6 = r8
            r3 = r11
            r1 = r12
            r11 = r36
            r12 = r2
            r2 = r13
            r13 = r35
            goto L_0x001d
        L_0x035e:
            r23 = r34
            r15 = r36
            r2 = r3
            r6 = r8
            r30 = r10
            r18 = r11
            r26 = r12
            r27 = r13
            r19 = -1
            r24 = 1
            goto L_0x0497
        L_0x0372:
            r22 = r6
            r8 = r23
            r23 = r5
            r5 = r12
            r12 = r0
            r13 = r2
            r0 = 1
            r2 = r33
            r0 = 27
            if (r11 != r0) goto L_0x03d7
            r0 = 2
            if (r7 != r0) goto L_0x03c6
            java.lang.Object r0 = r10.getObject(r14, r5)
            com.google.android.gms.internal.vision.zzgz r0 = (com.google.android.gms.internal.vision.zzgz) r0
            boolean r1 = r0.zzdo()
            if (r1 != 0) goto L_0x03a6
            int r1 = r0.size()
            if (r1 != 0) goto L_0x039b
            r1 = 10
            goto L_0x039d
        L_0x039b:
            int r1 = r1 << 1
        L_0x039d:
            com.google.android.gms.internal.vision.zzgz r0 = r0.zzah(r1)
            r10.putObject(r14, r5, r0)
            r5 = r0
            goto L_0x03a7
        L_0x03a6:
            r5 = r0
        L_0x03a7:
            com.google.android.gms.internal.vision.zzir r0 = r15.zzbn(r13)
            r1 = r8
            r2 = r33
            r4 = r35
            r6 = r37
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5, r6)
            r11 = r36
            r3 = r8
            r1 = r12
            r2 = r13
            r6 = r22
            r5 = r23
            r12 = r33
            r13 = r35
            goto L_0x001d
        L_0x03c6:
            r15 = r36
            r14 = r3
            r18 = r8
            r30 = r10
            r26 = r12
            r27 = r13
            r19 = -1
            r24 = 1
            goto L_0x0473
        L_0x03d7:
            r0 = 49
            if (r11 > r0) goto L_0x0428
            long r1 = (long) r1
            r24 = 1
            r0 = r31
            r25 = r1
            r1 = r32
            r2 = r33
            r34 = r3
            r4 = r35
            r28 = r5
            r5 = r8
            r6 = r12
            r18 = r8
            r19 = -1
            r8 = r13
            r30 = r10
            r9 = r25
            r15 = r36
            r26 = r12
            r27 = r13
            r12 = r28
            r14 = r37
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            r14 = r34
            if (r0 != r14) goto L_0x040f
            r2 = r0
            r6 = r22
            goto L_0x0497
        L_0x040f:
            r14 = r32
            r12 = r33
            r13 = r35
            r9 = r37
            r11 = r15
            r3 = r18
            r6 = r22
            r5 = r23
            r1 = r26
            r2 = r27
            r10 = r30
            r15 = r31
            goto L_0x001d
        L_0x0428:
            r15 = r36
            r14 = r3
            r28 = r5
            r18 = r8
            r30 = r10
            r26 = r12
            r27 = r13
            r19 = -1
            r24 = 1
            r0 = 50
            if (r11 != r0) goto L_0x0477
            r0 = 2
            if (r7 != r0) goto L_0x0473
            r0 = r31
            r1 = r32
            r2 = r33
            r3 = r14
            r4 = r35
            r5 = r27
            r6 = r28
            r8 = r37
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r14) goto L_0x045a
            r2 = r0
            r6 = r22
            goto L_0x0497
        L_0x045a:
            r14 = r32
            r12 = r33
            r13 = r35
            r9 = r37
            r11 = r15
            r3 = r18
            r6 = r22
            r5 = r23
            r1 = r26
            r2 = r27
            r10 = r30
            r15 = r31
            goto L_0x001d
        L_0x0473:
            r2 = r14
            r6 = r22
            goto L_0x0497
        L_0x0477:
            r0 = r31
            r8 = r1
            r1 = r32
            r2 = r33
            r3 = r14
            r4 = r35
            r5 = r18
            r6 = r26
            r9 = r11
            r10 = r28
            r12 = r27
            r13 = r37
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r14) goto L_0x07ec
            r2 = r0
            r6 = r22
        L_0x0497:
            r7 = r18
            if (r7 != r15) goto L_0x04ab
            if (r15 != 0) goto L_0x049e
            goto L_0x04ab
        L_0x049e:
            r8 = r31
            r13 = r32
            r0 = r6
            r3 = r7
            r9 = r15
            r1 = r23
            r6 = r35
            goto L_0x081c
        L_0x04ab:
            r8 = r31
            r9 = r15
            boolean r0 = r8.zzzf
            if (r0 == 0) goto L_0x07be
            r10 = r37
            com.google.android.gms.internal.vision.zzgd r0 = r10.zzcn
            com.google.android.gms.internal.vision.zzgd r1 = com.google.android.gms.internal.vision.zzgd.zzfl()
            if (r0 == r1) goto L_0x07b3
            com.google.android.gms.internal.vision.zzic r0 = r8.zzze
            com.google.android.gms.internal.vision.zzjj<?, ?> r1 = r8.zzzo
            com.google.android.gms.internal.vision.zzgd r3 = r10.zzcn
            r11 = r26
            com.google.android.gms.internal.vision.zzgs$zzg r12 = r3.zza(r0, r11)
            if (r12 != 0) goto L_0x04e5
            com.google.android.gms.internal.vision.zzjm r4 = zzt(r32)
            r0 = r7
            r1 = r33
            r3 = r35
            r5 = r37
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5)
            r13 = r32
            r15 = r33
            r34 = r6
            r6 = r35
            goto L_0x07a1
        L_0x04e5:
            r13 = r32
            r0 = r13
            com.google.android.gms.internal.vision.zzgs$zze r0 = (com.google.android.gms.internal.vision.zzgs.zze) r0
            r0.zzgk()
            com.google.android.gms.internal.vision.zzgi<com.google.android.gms.internal.vision.zzgs$zzd> r14 = r0.zzwq
            com.google.android.gms.internal.vision.zzgs$zzd r3 = r12.zzxh
            boolean r3 = r3.zzwo
            if (r3 == 0) goto L_0x0609
            com.google.android.gms.internal.vision.zzgs$zzd r3 = r12.zzxh
            boolean r3 = r3.zzwp
            if (r3 == 0) goto L_0x0606
            int[] r3 = com.google.android.gms.internal.vision.zzfc.zzrx
            com.google.android.gms.internal.vision.zzgs$zzd r4 = r12.zzxh
            com.google.android.gms.internal.vision.zzka r4 = r4.zzwn
            int r4 = r4.ordinal()
            r3 = r3[r4]
            switch(r3) {
                case 1: goto L_0x05f4;
                case 2: goto L_0x05e2;
                case 3: goto L_0x05d0;
                case 4: goto L_0x05d0;
                case 5: goto L_0x05be;
                case 6: goto L_0x05be;
                case 7: goto L_0x05ac;
                case 8: goto L_0x05ac;
                case 9: goto L_0x059a;
                case 10: goto L_0x059a;
                case 11: goto L_0x0588;
                case 12: goto L_0x0576;
                case 13: goto L_0x0564;
                case 14: goto L_0x0535;
                default: goto L_0x050c;
            }
        L_0x050c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            com.google.android.gms.internal.vision.zzgs$zzd r1 = r12.zzxh
            com.google.android.gms.internal.vision.zzka r1 = r1.zzwn
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r1)
            int r2 = r2.length()
            int r2 = r2 + 23
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Type cannot be packed: "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        L_0x0535:
            com.google.android.gms.internal.vision.zzgu r3 = new com.google.android.gms.internal.vision.zzgu
            r3.<init>()
            r15 = r33
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r15, r2, r3, r10)
            com.google.android.gms.internal.vision.zzjm r4 = r0.zzwj
            com.google.android.gms.internal.vision.zzjm r5 = com.google.android.gms.internal.vision.zzjm.zzig()
            if (r4 != r5) goto L_0x054a
            r4 = r17
        L_0x054a:
            com.google.android.gms.internal.vision.zzgs$zzd r5 = r12.zzxh
            com.google.android.gms.internal.vision.zzgv<?> r5 = r5.zzwm
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzit.zza(r11, r3, r5, r4, r1)
            com.google.android.gms.internal.vision.zzjm r1 = (com.google.android.gms.internal.vision.zzjm) r1
            if (r1 == 0) goto L_0x0559
            r0.zzwj = r1
        L_0x0559:
            com.google.android.gms.internal.vision.zzgs$zzd r0 = r12.zzxh
            r14.zza(r0, r3)
            r34 = r6
            r6 = r35
            goto L_0x07a0
        L_0x0564:
            r15 = r33
            com.google.android.gms.internal.vision.zzhq r0 = new com.google.android.gms.internal.vision.zzhq
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzi(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x0576:
            r15 = r33
            com.google.android.gms.internal.vision.zzgu r0 = new com.google.android.gms.internal.vision.zzgu
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzh(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x0588:
            r15 = r33
            com.google.android.gms.internal.vision.zzff r0 = new com.google.android.gms.internal.vision.zzff
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzg(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x059a:
            r15 = r33
            com.google.android.gms.internal.vision.zzgu r0 = new com.google.android.gms.internal.vision.zzgu
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzc(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x05ac:
            r15 = r33
            com.google.android.gms.internal.vision.zzhq r0 = new com.google.android.gms.internal.vision.zzhq
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzd(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x05be:
            r15 = r33
            com.google.android.gms.internal.vision.zzgu r0 = new com.google.android.gms.internal.vision.zzgu
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zza(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x05d0:
            r15 = r33
            com.google.android.gms.internal.vision.zzhq r0 = new com.google.android.gms.internal.vision.zzhq
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzb(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x05e2:
            r15 = r33
            com.google.android.gms.internal.vision.zzgo r0 = new com.google.android.gms.internal.vision.zzgo
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zze(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x05f4:
            r15 = r33
            com.google.android.gms.internal.vision.zzgb r0 = new com.google.android.gms.internal.vision.zzgb
            r0.<init>()
            int r1 = com.google.android.gms.internal.vision.zzez.zzf(r15, r2, r0, r10)
            r2 = r1
            r34 = r6
            r6 = r35
            goto L_0x079b
        L_0x0606:
            r15 = r33
            goto L_0x060b
        L_0x0609:
            r15 = r33
        L_0x060b:
            com.google.android.gms.internal.vision.zzgs$zzd r3 = r12.zzxh
            com.google.android.gms.internal.vision.zzka r3 = r3.zzwn
            com.google.android.gms.internal.vision.zzka r4 = com.google.android.gms.internal.vision.zzka.ENUM
            if (r3 != r4) goto L_0x064c
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r15, r2, r10)
            com.google.android.gms.internal.vision.zzgs$zzd r3 = r12.zzxh
            com.google.android.gms.internal.vision.zzgv<?> r3 = r3.zzwm
            int r4 = r10.zzru
            com.google.android.gms.internal.vision.zzgw r3 = r3.zzh(r4)
            if (r3 != 0) goto L_0x063e
            com.google.android.gms.internal.vision.zzjm r3 = r0.zzwj
            com.google.android.gms.internal.vision.zzjm r4 = com.google.android.gms.internal.vision.zzjm.zzig()
            if (r3 != r4) goto L_0x0633
            com.google.android.gms.internal.vision.zzjm r3 = com.google.android.gms.internal.vision.zzjm.zzih()
            r0.zzwj = r3
        L_0x0633:
            int r0 = r10.zzru
            com.google.android.gms.internal.vision.zzit.zza(r11, r0, r3, r1)
            r34 = r6
            r6 = r35
            goto L_0x07a0
        L_0x063e:
            int r0 = r10.zzru
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            r34 = r6
            r0 = r17
            r6 = r35
            goto L_0x076d
        L_0x064c:
            int[] r0 = com.google.android.gms.internal.vision.zzfc.zzrx
            com.google.android.gms.internal.vision.zzgs$zzd r1 = r12.zzxh
            com.google.android.gms.internal.vision.zzka r1 = r1.zzwn
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x075d;
                case 2: goto L_0x074c;
                case 3: goto L_0x073b;
                case 4: goto L_0x073b;
                case 5: goto L_0x072a;
                case 6: goto L_0x072a;
                case 7: goto L_0x0719;
                case 8: goto L_0x0719;
                case 9: goto L_0x0708;
                case 10: goto L_0x0708;
                case 11: goto L_0x06ef;
                case 12: goto L_0x06d9;
                case 13: goto L_0x06c3;
                case 14: goto L_0x06bb;
                case 15: goto L_0x06af;
                case 16: goto L_0x06a3;
                case 17: goto L_0x067e;
                case 18: goto L_0x0663;
                default: goto L_0x065b;
            }
        L_0x065b:
            r34 = r6
            r6 = r35
            r0 = r17
            goto L_0x076d
        L_0x0663:
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzho()
            com.google.android.gms.internal.vision.zzic r1 = r12.zzxg
            java.lang.Class r1 = r1.getClass()
            com.google.android.gms.internal.vision.zzir r0 = r0.zzf(r1)
            r5 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r0, r15, r2, r5, r10)
            java.lang.Object r0 = r10.zzrw
            r34 = r6
            r6 = r5
            goto L_0x076d
        L_0x067e:
            r5 = r35
            int r0 = r11 << 3
            r4 = r0 | 4
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzho()
            com.google.android.gms.internal.vision.zzic r1 = r12.zzxg
            java.lang.Class r1 = r1.getClass()
            com.google.android.gms.internal.vision.zzir r0 = r0.zzf(r1)
            r1 = r33
            r3 = r35
            r34 = r6
            r6 = r5
            r5 = r37
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5)
            java.lang.Object r0 = r10.zzrw
            goto L_0x076d
        L_0x06a3:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zzc(r15, r2, r10)
            java.lang.Object r0 = r10.zzrw
            goto L_0x076d
        L_0x06af:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zze(r15, r2, r10)
            java.lang.Object r0 = r10.zzrw
            goto L_0x076d
        L_0x06bb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Shouldn't reach here."
            r0.<init>(r1)
            throw r0
        L_0x06c3:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zzb(r15, r2, r10)
            long r0 = r10.zzrv
            long r0 = com.google.android.gms.internal.vision.zzft.zzr(r0)
            java.lang.Long r17 = java.lang.Long.valueOf(r0)
            r0 = r17
            goto L_0x076d
        L_0x06d9:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r15, r2, r10)
            int r0 = r10.zzru
            int r0 = com.google.android.gms.internal.vision.zzft.zzav(r0)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            r0 = r17
            goto L_0x076d
        L_0x06ef:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zzb(r15, r2, r10)
            long r0 = r10.zzrv
            int r3 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r3 == 0) goto L_0x06fe
            goto L_0x0700
        L_0x06fe:
            r24 = 0
        L_0x0700:
            java.lang.Boolean r17 = java.lang.Boolean.valueOf(r24)
            r0 = r17
            goto L_0x076d
        L_0x0708:
            r34 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r15, r2)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            int r2 = r2 + 4
            r0 = r17
            goto L_0x076d
        L_0x0719:
            r34 = r6
            r6 = r35
            long r0 = com.google.android.gms.internal.vision.zzez.zzb(r15, r2)
            java.lang.Long r17 = java.lang.Long.valueOf(r0)
            int r2 = r2 + 8
            r0 = r17
            goto L_0x076d
        L_0x072a:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zza(r15, r2, r10)
            int r0 = r10.zzru
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            r0 = r17
            goto L_0x076d
        L_0x073b:
            r34 = r6
            r6 = r35
            int r2 = com.google.android.gms.internal.vision.zzez.zzb(r15, r2, r10)
            long r0 = r10.zzrv
            java.lang.Long r17 = java.lang.Long.valueOf(r0)
            r0 = r17
            goto L_0x076d
        L_0x074c:
            r34 = r6
            r6 = r35
            float r0 = com.google.android.gms.internal.vision.zzez.zzd(r15, r2)
            java.lang.Float r17 = java.lang.Float.valueOf(r0)
            int r2 = r2 + 4
            r0 = r17
            goto L_0x076d
        L_0x075d:
            r34 = r6
            r6 = r35
            double r0 = com.google.android.gms.internal.vision.zzez.zzc(r15, r2)
            java.lang.Double r17 = java.lang.Double.valueOf(r0)
            int r2 = r2 + 8
            r0 = r17
        L_0x076d:
            com.google.android.gms.internal.vision.zzgs$zzd r1 = r12.zzxh
            boolean r1 = r1.zzwo
            if (r1 == 0) goto L_0x077a
            com.google.android.gms.internal.vision.zzgs$zzd r1 = r12.zzxh
            r14.zzb(r1, r0)
            goto L_0x07a0
        L_0x077a:
            int[] r1 = com.google.android.gms.internal.vision.zzfc.zzrx
            com.google.android.gms.internal.vision.zzgs$zzd r3 = r12.zzxh
            com.google.android.gms.internal.vision.zzka r3 = r3.zzwn
            int r3 = r3.ordinal()
            r1 = r1[r3]
            r3 = 17
            if (r1 == r3) goto L_0x078f
            r3 = 18
            if (r1 == r3) goto L_0x078f
            goto L_0x079b
        L_0x078f:
            com.google.android.gms.internal.vision.zzgs$zzd r1 = r12.zzxh
            java.lang.Object r1 = r14.zza(r1)
            if (r1 == 0) goto L_0x079b
            java.lang.Object r0 = com.google.android.gms.internal.vision.zzgt.zzb(r1, r0)
        L_0x079b:
            com.google.android.gms.internal.vision.zzgs$zzd r1 = r12.zzxh
            r14.zza(r1, r0)
        L_0x07a0:
            r0 = r2
        L_0x07a1:
            r3 = r7
            r1 = r11
            r14 = r13
            r12 = r15
            r5 = r23
            r2 = r27
            r13 = r6
            r15 = r8
            r11 = r9
            r9 = r10
            r10 = r30
            r6 = r34
            goto L_0x001d
        L_0x07b3:
            r13 = r32
            r15 = r33
            r34 = r6
            r11 = r26
            r6 = r35
            goto L_0x07ca
        L_0x07be:
            r13 = r32
            r15 = r33
            r10 = r37
            r34 = r6
            r11 = r26
            r6 = r35
        L_0x07ca:
            com.google.android.gms.internal.vision.zzjm r4 = zzt(r32)
            r0 = r7
            r1 = r33
            r3 = r35
            r5 = r37
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5)
            r3 = r7
            r1 = r11
            r14 = r13
            r12 = r15
            r5 = r23
            r2 = r27
            r13 = r6
            r15 = r8
            r11 = r9
            r9 = r10
            r10 = r30
            r6 = r34
            goto L_0x001d
        L_0x07ec:
            r8 = r31
            r13 = r32
            r6 = r35
            r10 = r37
            r9 = r15
            r7 = r18
            r11 = r26
            r15 = r33
            r3 = r7
            r1 = r11
            r14 = r13
            r12 = r15
            r5 = r23
            r2 = r27
            r13 = r6
            r15 = r8
            r11 = r9
            r9 = r10
            r6 = r22
            r10 = r30
            goto L_0x001d
        L_0x080d:
            r23 = r5
            r22 = r6
            r30 = r10
            r9 = r11
            r6 = r13
            r13 = r14
            r8 = r15
            r2 = r0
            r0 = r22
            r1 = r23
        L_0x081c:
            r4 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 == r4) goto L_0x0827
            long r4 = (long) r0
            r0 = r30
            r0.putInt(r13, r4, r1)
        L_0x0827:
            int r0 = r8.zzzk
            r1 = r17
        L_0x082c:
            int r4 = r8.zzzl
            if (r0 >= r4) goto L_0x083f
            int[] r4 = r8.zzzj
            r4 = r4[r0]
            com.google.android.gms.internal.vision.zzjj<?, ?> r5 = r8.zzzo
            java.lang.Object r1 = r8.zza(r13, r4, (UB) r1, r5)
            com.google.android.gms.internal.vision.zzjm r1 = (com.google.android.gms.internal.vision.zzjm) r1
            int r0 = r0 + 1
            goto L_0x082c
        L_0x083f:
            if (r1 == 0) goto L_0x0846
            com.google.android.gms.internal.vision.zzjj<?, ?> r0 = r8.zzzo
            r0.zzg(r13, r1)
        L_0x0846:
            if (r9 != 0) goto L_0x0850
            if (r2 != r6) goto L_0x084b
            goto L_0x0854
        L_0x084b:
            com.google.android.gms.internal.vision.zzhc r0 = com.google.android.gms.internal.vision.zzhc.zzgs()
            throw r0
        L_0x0850:
            if (r2 > r6) goto L_0x0855
            if (r3 != r9) goto L_0x0855
        L_0x0854:
            return r2
        L_0x0855:
            com.google.android.gms.internal.vision.zzhc r0 = com.google.android.gms.internal.vision.zzhc.zzgs()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.zzfb):int");
    }

    /* JADX WARNING: type inference failed for: r31v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r17v0, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r0v7, types: [int] */
    /* JADX WARNING: type inference failed for: r1v6, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r2v8, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v3, types: [int] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r2v11, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r2v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r1v20, types: [int] */
    /* JADX WARNING: type inference failed for: r2v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r17v2 */
    /* JADX WARNING: type inference failed for: r3v12, types: [int] */
    /* JADX WARNING: type inference failed for: r17v3 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: type inference failed for: r12v15 */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: type inference failed for: r12v17 */
    /* JADX WARNING: type inference failed for: r12v18 */
    /* JADX WARNING: type inference failed for: r12v19 */
    /* JADX WARNING: type inference failed for: r12v20 */
    /* JADX WARNING: type inference failed for: r12v21 */
    /* JADX WARNING: type inference failed for: r12v22 */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v5, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r31v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 473
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 16 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r30, byte[] r31, int r32, int r33, com.google.android.gms.internal.vision.zzfb r34) throws java.io.IOException {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            boolean r0 = r15.zzzh
            if (r0 == 0) goto L_0x042d
            sun.misc.Unsafe r9 = zzyz
            r10 = -1
            r16 = 0
            r0 = r32
            r1 = -1
            r2 = 0
            r6 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001f:
            if (r0 >= r13) goto L_0x040e
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0031
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r12, r3, r11)
            int r3 = r11.zzru
            r4 = r0
            r17 = r3
            goto L_0x0034
        L_0x0031:
            r17 = r0
            r4 = r3
        L_0x0034:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x0042
            int r2 = r2 / 3
            int r0 = r15.zzs(r5, r2)
            r2 = r0
            goto L_0x0047
        L_0x0042:
            int r0 = r15.zzbt(r5)
            r2 = r0
        L_0x0047:
            if (r2 != r10) goto L_0x0055
            r2 = r4
            r19 = r5
            r26 = r9
            r20 = 0
            r27 = -1
            goto L_0x03d3
        L_0x0055:
            int[] r0 = r15.zzza
            int r1 = r2 + 1
            r1 = r0[r1]
            r18 = 267386880(0xff00000, float:2.3665827E-29)
            r18 = r1 & r18
            int r8 = r18 >>> 20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r1 & r18
            r18 = r4
            r32 = r5
            long r4 = (long) r10
            r10 = 17
            r20 = r1
            if (r8 > r10) goto L_0x02a3
            int r10 = r2 + 2
            r0 = r0[r10]
            int r10 = r0 >>> 20
            r1 = 1
            int r10 = r1 << r10
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r13
            if (r0 == r7) goto L_0x0099
            if (r7 == r13) goto L_0x008d
            r19 = r2
            long r1 = (long) r7
            r9.putInt(r14, r1, r6)
            goto L_0x008f
        L_0x008d:
            r19 = r2
        L_0x008f:
            if (r0 == r13) goto L_0x0097
            long r1 = (long) r0
            int r1 = r9.getInt(r14, r1)
            r6 = r1
        L_0x0097:
            r7 = r0
            goto L_0x009b
        L_0x0099:
            r19 = r2
        L_0x009b:
            r0 = 5
            switch(r8) {
                case 0: goto L_0x027c;
                case 1: goto L_0x025f;
                case 2: goto L_0x0239;
                case 3: goto L_0x0239;
                case 4: goto L_0x021c;
                case 5: goto L_0x01f6;
                case 6: goto L_0x01d5;
                case 7: goto L_0x01a7;
                case 8: goto L_0x017a;
                case 9: goto L_0x013c;
                case 10: goto L_0x011c;
                case 11: goto L_0x021c;
                case 12: goto L_0x00fd;
                case 13: goto L_0x01d5;
                case 14: goto L_0x01f6;
                case 15: goto L_0x00da;
                case 16: goto L_0x00aa;
                default: goto L_0x009f;
            }
        L_0x009f:
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            goto L_0x029a
        L_0x00aa:
            if (r3 != 0) goto L_0x00cf
            r8 = r18
            int r8 = com.google.android.gms.internal.vision.zzez.zzb(r12, r8, r11)
            long r0 = r11.zzrv
            long r17 = com.google.android.gms.internal.vision.zzft.zzr(r0)
            r0 = r9
            r1 = r30
            r13 = r19
            r2 = r4
            r19 = r32
            r4 = r17
            r0.putLong(r1, r2, r4)
            r6 = r6 | r10
            r0 = r8
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x00cf:
            r8 = r18
            r13 = r19
            r19 = r32
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x029a
        L_0x00da:
            r8 = r18
            r13 = r19
            r19 = r32
            if (r3 != 0) goto L_0x00f8
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r12, r8, r11)
            int r1 = r11.zzru
            int r1 = com.google.android.gms.internal.vision.zzft.zzav(r1)
            r9.putInt(r14, r4, r1)
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x00f8:
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x029a
        L_0x00fd:
            r8 = r18
            r13 = r19
            r19 = r32
            if (r3 != 0) goto L_0x0117
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r12, r8, r11)
            int r1 = r11.zzru
            r9.putInt(r14, r4, r1)
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x0117:
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x029a
        L_0x011c:
            r8 = r18
            r13 = r19
            r19 = r32
            r0 = 2
            if (r3 != r0) goto L_0x0137
            int r0 = com.google.android.gms.internal.vision.zzez.zze(r12, r8, r11)
            java.lang.Object r1 = r11.zzrw
            r9.putObject(r14, r4, r1)
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x0137:
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x029a
        L_0x013c:
            r8 = r18
            r13 = r19
            r19 = r32
            r0 = 2
            if (r3 != r0) goto L_0x0173
            com.google.android.gms.internal.vision.zzir r0 = r15.zzbn(r13)
            r2 = r33
            r18 = 1048575(0xfffff, float:1.469367E-39)
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r12, r8, r2, r11)
            java.lang.Object r1 = r9.getObject(r14, r4)
            if (r1 != 0) goto L_0x015f
            java.lang.Object r1 = r11.zzrw
            r9.putObject(r14, r4, r1)
            goto L_0x0168
        L_0x015f:
            java.lang.Object r3 = r11.zzrw
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzgt.zzb(r1, r3)
            r9.putObject(r14, r4, r1)
        L_0x0168:
            r6 = r6 | r10
            r1 = r19
            r10 = -1
            r28 = r13
            r13 = r2
            r2 = r28
            goto L_0x001f
        L_0x0173:
            r2 = r33
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x029a
        L_0x017a:
            r2 = r33
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            r0 = 2
            if (r3 != r0) goto L_0x029a
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x0193
            int r0 = com.google.android.gms.internal.vision.zzez.zzc(r12, r8, r11)
            goto L_0x0197
        L_0x0193:
            int r0 = com.google.android.gms.internal.vision.zzez.zzd(r12, r8, r11)
        L_0x0197:
            java.lang.Object r1 = r11.zzrw
            r9.putObject(r14, r4, r1)
            r6 = r6 | r10
            r1 = r19
            r10 = -1
            r28 = r13
            r13 = r2
            r2 = r28
            goto L_0x001f
        L_0x01a7:
            r2 = r33
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            if (r3 != 0) goto L_0x029a
            int r0 = com.google.android.gms.internal.vision.zzez.zzb(r12, r8, r11)
            r32 = r0
            long r0 = r11.zzrv
            r20 = 0
            int r3 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r3 == 0) goto L_0x01c4
            r0 = 1
            goto L_0x01c5
        L_0x01c4:
            r0 = 0
        L_0x01c5:
            com.google.android.gms.internal.vision.zzjp.zza(r14, r4, r0)
            r6 = r6 | r10
            r0 = r32
            r1 = r19
            r10 = -1
            r28 = r13
            r13 = r2
            r2 = r28
            goto L_0x001f
        L_0x01d5:
            r2 = r33
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            if (r3 != r0) goto L_0x029a
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r12, r8)
            r9.putInt(r14, r4, r0)
            int r0 = r8 + 4
            r6 = r6 | r10
            r1 = r19
            r10 = -1
            r28 = r13
            r13 = r2
            r2 = r28
            goto L_0x001f
        L_0x01f6:
            r2 = r33
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            r0 = 1
            if (r3 != r0) goto L_0x029a
            long r20 = com.google.android.gms.internal.vision.zzez.zzb(r12, r8)
            r0 = r9
            r1 = r30
            r2 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x021c:
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            if (r3 != 0) goto L_0x029a
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r12, r8, r11)
            int r1 = r11.zzru
            r9.putInt(r14, r4, r1)
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x0239:
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            if (r3 != 0) goto L_0x029a
            int r8 = com.google.android.gms.internal.vision.zzez.zzb(r12, r8, r11)
            long r2 = r11.zzrv
            r0 = r9
            r1 = r30
            r20 = r2
            r2 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            r6 = r6 | r10
            r0 = r8
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x025f:
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            if (r3 != r0) goto L_0x029a
            float r0 = com.google.android.gms.internal.vision.zzez.zzd(r12, r8)
            com.google.android.gms.internal.vision.zzjp.zza(r14, r4, r0)
            int r0 = r8 + 4
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x027c:
            r8 = r18
            r13 = r19
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r32
            r0 = 1
            if (r3 != r0) goto L_0x029a
            double r0 = com.google.android.gms.internal.vision.zzez.zzc(r12, r8)
            com.google.android.gms.internal.vision.zzjp.zza(r14, r4, r0)
            int r0 = r8 + 8
            r6 = r6 | r10
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x029a:
            r2 = r8
            r26 = r9
            r20 = r13
            r27 = -1
            goto L_0x03d3
        L_0x02a3:
            r19 = r32
            r13 = r2
            r10 = r18
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 27
            if (r8 != r0) goto L_0x02fe
            r0 = 2
            if (r3 != r0) goto L_0x02f0
            java.lang.Object r0 = r9.getObject(r14, r4)
            com.google.android.gms.internal.vision.zzgz r0 = (com.google.android.gms.internal.vision.zzgz) r0
            boolean r1 = r0.zzdo()
            if (r1 != 0) goto L_0x02d3
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02c8
            r1 = 10
            goto L_0x02ca
        L_0x02c8:
            int r1 = r1 << 1
        L_0x02ca:
            com.google.android.gms.internal.vision.zzgz r0 = r0.zzah(r1)
            r9.putObject(r14, r4, r0)
            r5 = r0
            goto L_0x02d4
        L_0x02d3:
            r5 = r0
        L_0x02d4:
            com.google.android.gms.internal.vision.zzir r0 = r15.zzbn(r13)
            r1 = r17
            r2 = r31
            r3 = r10
            r4 = r33
            r8 = r6
            r6 = r34
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5, r6)
            r6 = r8
            r2 = r13
            r1 = r19
            r10 = -1
            r13 = r33
            goto L_0x001f
        L_0x02f0:
            r8 = r6
            r18 = r7
            r24 = r8
            r26 = r9
            r15 = r10
            r20 = r13
            r27 = -1
            goto L_0x03ab
        L_0x02fe:
            r0 = 49
            if (r8 > r0) goto L_0x035a
            r1 = r20
            long r1 = (long) r1
            r0 = r29
            r20 = r1
            r1 = r30
            r2 = r31
            r32 = r3
            r3 = r10
            r22 = r4
            r4 = r33
            r5 = r17
            r15 = r6
            r6 = r19
            r24 = r15
            r15 = r7
            r7 = r32
            r25 = r8
            r18 = r15
            r15 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r13
            r26 = r9
            r15 = r10
            r27 = -1
            r9 = r20
            r11 = r25
            r20 = r13
            r12 = r22
            r14 = r34
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x0343
            r2 = r0
            r7 = r18
            r6 = r24
            goto L_0x03d3
        L_0x0343:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r7 = r18
            r1 = r19
            r2 = r20
            r6 = r24
            r9 = r26
            r10 = -1
            goto L_0x001f
        L_0x035a:
            r32 = r3
            r22 = r4
            r24 = r6
            r18 = r7
            r25 = r8
            r26 = r9
            r15 = r10
            r1 = r20
            r27 = -1
            r20 = r13
            r0 = 50
            r9 = r25
            if (r9 != r0) goto L_0x03b1
            r7 = r32
            r0 = 2
            if (r7 != r0) goto L_0x03ab
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r5 = r20
            r6 = r22
            r8 = r34
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0394
            r2 = r0
            r7 = r18
            r6 = r24
            goto L_0x03d3
        L_0x0394:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r7 = r18
            r1 = r19
            r2 = r20
            r6 = r24
            r9 = r26
            r10 = -1
            goto L_0x001f
        L_0x03ab:
            r2 = r15
            r7 = r18
            r6 = r24
            goto L_0x03d3
        L_0x03b1:
            r7 = r32
            r0 = r29
            r8 = r1
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r5 = r17
            r6 = r19
            r10 = r22
            r12 = r20
            r13 = r34
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x03f7
            r2 = r0
            r7 = r18
            r6 = r24
        L_0x03d3:
            com.google.android.gms.internal.vision.zzjm r4 = zzt(r30)
            r0 = r17
            r1 = r31
            r3 = r33
            r5 = r34
            int r0 = com.google.android.gms.internal.vision.zzez.zza(r0, r1, r2, r3, r4, r5)
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r1 = r19
            r2 = r20
            r9 = r26
            r10 = -1
            goto L_0x001f
        L_0x03f7:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r7 = r18
            r1 = r19
            r2 = r20
            r6 = r24
            r9 = r26
            r10 = -1
            goto L_0x001f
        L_0x040e:
            r24 = r6
            r18 = r7
            r26 = r9
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 == r1) goto L_0x0423
            long r1 = (long) r7
            r3 = r30
            r6 = r24
            r4 = r26
            r4.putInt(r3, r1, r6)
        L_0x0423:
            r4 = r33
            if (r0 != r4) goto L_0x0428
            return
        L_0x0428:
            com.google.android.gms.internal.vision.zzhc r0 = com.google.android.gms.internal.vision.zzhc.zzgs()
            throw r0
        L_0x042d:
            r4 = r13
            r3 = r14
            r5 = 0
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r32
            r4 = r33
            r6 = r34
            r0.zza((T) r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzfb):void");
    }

    public final void zzh(T t) {
        int i;
        int i2 = this.zzzk;
        while (true) {
            i = this.zzzl;
            if (i2 >= i) {
                break;
            }
            long zzbq = (long) (zzbq(this.zzzj[i2]) & 1048575);
            Object zzp = zzjp.zzp(t, zzbq);
            if (zzp != null) {
                zzjp.zza((Object) t, zzbq, this.zzzq.zzo(zzp));
            }
            i2++;
        }
        int length = this.zzzj.length;
        while (i < length) {
            this.zzzn.zzb(t, (long) this.zzzj[i]);
            i++;
        }
        this.zzzo.zzh(t);
        if (this.zzzf) {
            this.zzzp.zzh(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzjj<UT, UB> zzjj) {
        int i2 = this.zzza[i];
        Object zzp = zzjp.zzp(obj, (long) (zzbq(i) & 1048575));
        if (zzp == null) {
            return ub;
        }
        zzgy zzbp = zzbp(i);
        if (zzbp == null) {
            return ub;
        }
        return zza(i, i2, this.zzzq.zzl(zzp), zzbp, ub, zzjj);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzgy zzgy, UB ub, zzjj<UT, UB> zzjj) {
        zzht zzq = this.zzzq.zzq(zzbo(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzgy.zzg(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzjj.zzif();
                }
                zzfp zzaq = zzfh.zzaq(zzhu.zza(zzq, entry.getKey(), entry.getValue()));
                try {
                    zzhu.zza(zzaq.zzew(), zzq, entry.getKey(), entry.getValue());
                    zzjj.zza(ub, i2, zzaq.zzev());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzu(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.zzzk) {
                return !this.zzzf || this.zzzp.zzf(t2).isInitialized();
            }
            int i6 = this.zzzj[i5];
            int i7 = this.zzza[i6];
            int zzbq = zzbq(i6);
            int i8 = this.zzza[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i3) {
                i2 = i3;
                i = i4;
            } else if (i9 != 1048575) {
                i = zzyz.getInt(t2, (long) i9);
                i2 = i9;
            } else {
                i = i4;
                i2 = i9;
            }
            if (((268435456 & zzbq) != 0) && !zza(t, i6, i2, i, i10)) {
                return false;
            }
            int i11 = (267386880 & zzbq) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (zza(t2, i7, i6) && !zza((Object) t2, zzbq, zzbn(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map zzm = this.zzzq.zzm(zzjp.zzp(t2, (long) (zzbq & 1048575)));
                            if (!zzm.isEmpty()) {
                                if (this.zzzq.zzq(zzbo(i6)).zzyu.zzip() == zzkd.MESSAGE) {
                                    zzir zzir = null;
                                    Iterator it = zzm.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzir == null) {
                                            zzir = zzin.zzho().zzf(next.getClass());
                                        }
                                        if (!zzir.zzu(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzjp.zzp(t2, (long) (zzbq & 1048575));
                if (!list.isEmpty()) {
                    zzir zzbn = zzbn(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!zzbn.zzu(list.get(i12))) {
                            z = false;
                            break;
                        } else {
                            i12++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i6, i2, i, i10) && !zza((Object) t2, zzbq, zzbn(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
    }

    private static boolean zza(Object obj, int i, zzir zzir) {
        return zzir.zzu(zzjp.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzkg zzkg) throws IOException {
        if (obj instanceof String) {
            zzkg.zza(i, (String) obj);
        } else {
            zzkg.zza(i, (zzfh) obj);
        }
    }

    private final void zza(Object obj, int i, zzis zzis) throws IOException {
        if (zzbs(i)) {
            zzjp.zza(obj, (long) (i & 1048575), (Object) zzis.zzec());
        } else if (this.zzzg) {
            zzjp.zza(obj, (long) (i & 1048575), (Object) zzis.readString());
        } else {
            zzjp.zza(obj, (long) (i & 1048575), (Object) zzis.zzed());
        }
    }

    private final int zzbq(int i) {
        return this.zzza[i + 1];
    }

    private final int zzbr(int i) {
        return this.zzza[i + 2];
    }

    private static boolean zzbs(int i) {
        return (i & 536870912) != 0;
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzjp.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzjp.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzjp.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzjp.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzjp.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zza(t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zza(T t, int i) {
        int zzbr = zzbr(i);
        long j = (long) (zzbr & 1048575);
        if (j == 1048575) {
            int zzbq = zzbq(i);
            long j2 = (long) (zzbq & 1048575);
            switch ((zzbq & 267386880) >>> 20) {
                case 0:
                    return zzjp.zzo(t, j2) != 0.0d;
                case 1:
                    return zzjp.zzn(t, j2) != 0.0f;
                case 2:
                    return zzjp.zzl(t, j2) != 0;
                case 3:
                    return zzjp.zzl(t, j2) != 0;
                case 4:
                    return zzjp.zzk(t, j2) != 0;
                case 5:
                    return zzjp.zzl(t, j2) != 0;
                case 6:
                    return zzjp.zzk(t, j2) != 0;
                case 7:
                    return zzjp.zzm(t, j2);
                case 8:
                    Object zzp = zzjp.zzp(t, j2);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzfh) {
                        return !zzfh.zzsd.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzjp.zzp(t, j2) != null;
                case 10:
                    return !zzfh.zzsd.equals(zzjp.zzp(t, j2));
                case 11:
                    return zzjp.zzk(t, j2) != 0;
                case 12:
                    return zzjp.zzk(t, j2) != 0;
                case 13:
                    return zzjp.zzk(t, j2) != 0;
                case 14:
                    return zzjp.zzl(t, j2) != 0;
                case 15:
                    return zzjp.zzk(t, j2) != 0;
                case 16:
                    return zzjp.zzl(t, j2) != 0;
                case 17:
                    return zzjp.zzp(t, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzjp.zzk(t, j) & (1 << (zzbr >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        int zzbr = zzbr(i);
        long j = (long) (1048575 & zzbr);
        if (j != 1048575) {
            zzjp.zzb((Object) t, j, (1 << (zzbr >>> 20)) | zzjp.zzk(t, j));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzjp.zzk(t, (long) (zzbr(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzjp.zzb((Object) t, (long) (zzbr(i2) & 1048575), i);
    }

    private final int zzbt(int i) {
        if (i < this.zzzc || i > this.zzzd) {
            return -1;
        }
        return zzt(i, 0);
    }

    private final int zzs(int i, int i2) {
        if (i < this.zzzc || i > this.zzzd) {
            return -1;
        }
        return zzt(i, i2);
    }

    private final int zzt(int i, int i2) {
        int length = (this.zzza.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzza[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
