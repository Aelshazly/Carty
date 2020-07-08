package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzgs<T> implements zzhd<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzib.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzgo zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzgw zzo;
    private final zzfy zzp;
    private final zzhv<?, ?> zzq;
    private final zzes<?> zzr;
    private final zzgh zzs;

    private zzgs(int[] iArr, Object[] objArr, int i, int i2, zzgo zzgo, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzgw zzgw, zzfy zzfy, zzhv<?, ?> zzhv, zzes<?> zzes, zzgh zzgh) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzgo instanceof zzfd;
        this.zzj = z;
        this.zzh = zzes != null && zzes.zza(zzgo);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzgw;
        this.zzp = zzfy;
        this.zzq = zzhv;
        this.zzr = zzes;
        this.zzg = zzgo;
        this.zzs = zzgh;
    }

    /* JADX WARNING: Removed duplicated region for block: B:169:0x0396  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.measurement.zzgs<T> zza(java.lang.Class<T> r35, com.google.android.gms.internal.measurement.zzgm r36, com.google.android.gms.internal.measurement.zzgw r37, com.google.android.gms.internal.measurement.zzfy r38, com.google.android.gms.internal.measurement.zzhv<?, ?> r39, com.google.android.gms.internal.measurement.zzes<?> r40, com.google.android.gms.internal.measurement.zzgh r41) {
        /*
            r0 = r36
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzhb
            if (r1 == 0) goto L_0x0461
            com.google.android.gms.internal.measurement.zzhb r0 = (com.google.android.gms.internal.measurement.zzhb) r0
            int r1 = r0.zza()
            int r2 = com.google.android.gms.internal.measurement.zzfd.zze.zzi
            r3 = 0
            if (r1 != r2) goto L_0x0013
            r11 = 1
            goto L_0x0014
        L_0x0013:
            r11 = 0
        L_0x0014:
            java.lang.String r1 = r0.zzd()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r7) goto L_0x003f
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = r5
            r5 = 1
            r9 = 13
        L_0x002c:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r7) goto L_0x003c
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r8 = r8 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x002c
        L_0x003c:
            int r5 = r5 << r9
            r5 = r5 | r8
            goto L_0x0040
        L_0x003f:
            r10 = 1
        L_0x0040:
            int r8 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r7) goto L_0x0060
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x004d:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x005d
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x004d
        L_0x005d:
            int r8 = r8 << r10
            r9 = r9 | r8
            goto L_0x0061
        L_0x0060:
            r12 = r8
        L_0x0061:
            if (r9 != 0) goto L_0x0076
            int[] r8 = zza
            r15 = r8
            r8 = 0
            r9 = 0
            r10 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            goto L_0x01b0
        L_0x0076:
            int r8 = r12 + 1
            char r9 = r1.charAt(r12)
            if (r9 < r7) goto L_0x0096
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0082:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0092
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x0082
        L_0x0092:
            int r8 = r8 << r10
            r8 = r8 | r9
            r9 = r8
            goto L_0x0097
        L_0x0096:
            r12 = r8
        L_0x0097:
            int r8 = r12 + 1
            char r10 = r1.charAt(r12)
            if (r10 < r7) goto L_0x00b7
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00a4:
            int r13 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00b4
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r12
            r10 = r10 | r8
            int r12 = r12 + 13
            r8 = r13
            goto L_0x00a4
        L_0x00b4:
            int r8 = r8 << r12
            r10 = r10 | r8
            goto L_0x00b8
        L_0x00b7:
            r13 = r8
        L_0x00b8:
            int r8 = r13 + 1
            char r12 = r1.charAt(r13)
            if (r12 < r7) goto L_0x00d9
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00c5:
            int r14 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00d5
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r13
            r12 = r12 | r8
            int r13 = r13 + 13
            r8 = r14
            goto L_0x00c5
        L_0x00d5:
            int r8 = r8 << r13
            r8 = r8 | r12
            r12 = r8
            goto L_0x00da
        L_0x00d9:
            r14 = r8
        L_0x00da:
            int r8 = r14 + 1
            char r13 = r1.charAt(r14)
            if (r13 < r7) goto L_0x00fb
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00e7:
            int r15 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00f7
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r14
            r13 = r13 | r8
            int r14 = r14 + 13
            r8 = r15
            goto L_0x00e7
        L_0x00f7:
            int r8 = r8 << r14
            r8 = r8 | r13
            r13 = r8
            goto L_0x00fc
        L_0x00fb:
            r15 = r8
        L_0x00fc:
            int r8 = r15 + 1
            char r14 = r1.charAt(r15)
            if (r14 < r7) goto L_0x011f
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0109:
            int r16 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x011a
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r15
            r14 = r14 | r8
            int r15 = r15 + 13
            r8 = r16
            goto L_0x0109
        L_0x011a:
            int r8 = r8 << r15
            r8 = r8 | r14
            r14 = r8
            r8 = r16
        L_0x011f:
            int r15 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0143
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x012c:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x013e
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r8 = r8 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x012c
        L_0x013e:
            int r15 = r15 << r16
            r8 = r8 | r15
            r15 = r17
        L_0x0143:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0170
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r34 = r16
            r16 = r15
            r15 = r34
        L_0x0156:
            int r18 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0169
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r17
            r16 = r16 | r15
            int r17 = r17 + 13
            r15 = r18
            goto L_0x0156
        L_0x0169:
            int r15 = r15 << r17
            r15 = r16 | r15
            r3 = r18
            goto L_0x0172
        L_0x0170:
            r3 = r16
        L_0x0172:
            int r16 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x019e
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r34 = r16
            r16 = r3
            r3 = r34
        L_0x0185:
            int r18 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0198
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r17
            r16 = r16 | r3
            int r17 = r17 + 13
            r3 = r18
            goto L_0x0185
        L_0x0198:
            int r3 = r3 << r17
            r3 = r16 | r3
            r16 = r18
        L_0x019e:
            int r17 = r3 + r8
            int r15 = r17 + r15
            int[] r15 = new int[r15]
            int r17 = r9 << 1
            int r10 = r17 + r10
            r34 = r16
            r16 = r9
            r9 = r12
            r12 = r34
        L_0x01b0:
            sun.misc.Unsafe r6 = zzb
            java.lang.Object[] r17 = r0.zze()
            com.google.android.gms.internal.measurement.zzgo r18 = r0.zzc()
            java.lang.Class r7 = r18.getClass()
            int r4 = r14 * 3
            int[] r4 = new int[r4]
            r18 = 1
            int r14 = r14 << 1
            java.lang.Object[] r14 = new java.lang.Object[r14]
            int r20 = r3 + r8
            r22 = r3
            r23 = r20
            r8 = 0
            r21 = 0
        L_0x01d3:
            if (r12 >= r2) goto L_0x0438
            int r24 = r12 + 1
            char r12 = r1.charAt(r12)
            r25 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r12 < r2) goto L_0x0209
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
            r34 = r24
            r24 = r12
            r12 = r34
        L_0x01ec:
            int r27 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r2) goto L_0x0202
            r2 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r26
            r24 = r24 | r2
            int r26 = r26 + 13
            r12 = r27
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01ec
        L_0x0202:
            int r2 = r12 << r26
            r12 = r24 | r2
            r2 = r27
            goto L_0x020b
        L_0x0209:
            r2 = r24
        L_0x020b:
            int r24 = r2 + 1
            char r2 = r1.charAt(r2)
            r26 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r3) goto L_0x0240
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r27 = 13
            r34 = r24
            r24 = r2
            r2 = r34
        L_0x0223:
            int r28 = r2 + 1
            char r2 = r1.charAt(r2)
            if (r2 < r3) goto L_0x0239
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r27
            r24 = r24 | r2
            int r27 = r27 + 13
            r2 = r28
            r3 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0223
        L_0x0239:
            int r2 = r2 << r27
            r2 = r24 | r2
            r3 = r28
            goto L_0x0242
        L_0x0240:
            r3 = r24
        L_0x0242:
            r24 = r11
            r11 = r2 & 255(0xff, float:3.57E-43)
            r27 = r13
            r13 = r2 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto L_0x0252
            int r13 = r8 + 1
            r15[r8] = r21
            r8 = r13
        L_0x0252:
            r13 = 51
            r30 = r8
            if (r11 < r13) goto L_0x02f8
            int r13 = r3 + 1
            char r3 = r1.charAt(r3)
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r8) goto L_0x0281
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r32 = 13
        L_0x0267:
            int r33 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r8) goto L_0x027c
            r8 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r32
            r3 = r3 | r8
            int r32 = r32 + 13
            r13 = r33
            r8 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0267
        L_0x027c:
            int r8 = r13 << r32
            r3 = r3 | r8
            r13 = r33
        L_0x0281:
            int r8 = r11 + -51
            r32 = r13
            r13 = 9
            if (r8 == r13) goto L_0x02a7
            r13 = 17
            if (r8 != r13) goto L_0x028f
            goto L_0x02a7
        L_0x028f:
            r13 = 12
            if (r8 != r13) goto L_0x02a5
            r8 = r5 & 1
            r13 = 1
            if (r8 != r13) goto L_0x02a5
            int r8 = r21 / 3
            int r8 = r8 << r13
            int r8 = r8 + r13
            int r13 = r10 + 1
            r10 = r17[r10]
            r14[r8] = r10
            r10 = r13
            r13 = 1
            goto L_0x02b4
        L_0x02a5:
            r13 = 1
            goto L_0x02b4
        L_0x02a7:
            int r8 = r21 / 3
            r13 = 1
            int r8 = r8 << r13
            int r8 = r8 + r13
            int r18 = r10 + 1
            r10 = r17[r10]
            r14[r8] = r10
            r10 = r18
        L_0x02b4:
            int r3 = r3 << r13
            r8 = r17[r3]
            boolean r13 = r8 instanceof java.lang.reflect.Field
            if (r13 == 0) goto L_0x02be
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02c6
        L_0x02be:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza(r7, r8)
            r17[r3] = r8
        L_0x02c6:
            r13 = r9
            long r8 = r6.objectFieldOffset(r8)
            int r9 = (int) r8
            int r3 = r3 + 1
            r8 = r17[r3]
            r28 = r9
            boolean r9 = r8 instanceof java.lang.reflect.Field
            if (r9 == 0) goto L_0x02d9
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02e1
        L_0x02d9:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza(r7, r8)
            r17[r3] = r8
        L_0x02e1:
            long r8 = r6.objectFieldOffset(r8)
            int r3 = (int) r8
            r31 = r1
            r8 = r3
            r1 = r7
            r19 = r10
            r9 = r28
            r10 = r32
            r3 = 0
            r18 = 1
            r28 = r13
            goto L_0x0400
        L_0x02f8:
            r13 = r9
            int r8 = r10 + 1
            r9 = r17[r10]
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = zza(r7, r9)
            r10 = 9
            if (r11 == r10) goto L_0x0380
            r10 = 17
            if (r11 != r10) goto L_0x0310
            r28 = r13
            r13 = 1
            goto L_0x0383
        L_0x0310:
            r10 = 27
            if (r11 == r10) goto L_0x0370
            r10 = 49
            if (r11 != r10) goto L_0x031b
            r28 = r13
            goto L_0x0372
        L_0x031b:
            r10 = 12
            if (r11 == r10) goto L_0x035a
            r10 = 30
            if (r11 == r10) goto L_0x035a
            r10 = 44
            if (r11 != r10) goto L_0x0328
            goto L_0x035a
        L_0x0328:
            r10 = 50
            if (r11 != r10) goto L_0x0356
            int r10 = r22 + 1
            r15[r22] = r21
            int r22 = r21 / 3
            r18 = 1
            int r22 = r22 << 1
            int r28 = r8 + 1
            r8 = r17[r8]
            r14[r22] = r8
            r8 = r2 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L_0x034e
            int r22 = r22 + 1
            int r8 = r28 + 1
            r28 = r17[r28]
            r14[r22] = r28
            r22 = r10
            r28 = r13
            r13 = 1
            goto L_0x038d
        L_0x034e:
            r22 = r10
            r8 = r28
            r28 = r13
            r13 = 1
            goto L_0x038d
        L_0x0356:
            r28 = r13
            r13 = 1
            goto L_0x038d
        L_0x035a:
            r10 = r5 & 1
            r28 = r13
            r13 = 1
            if (r10 != r13) goto L_0x036e
            int r10 = r21 / 3
            int r10 = r10 << r13
            int r10 = r10 + r13
            int r13 = r8 + 1
            r8 = r17[r8]
            r14[r10] = r8
            r8 = r13
            r13 = 1
            goto L_0x038d
        L_0x036e:
            r13 = 1
            goto L_0x038d
        L_0x0370:
            r28 = r13
        L_0x0372:
            int r10 = r21 / 3
            r13 = 1
            int r10 = r10 << r13
            int r10 = r10 + r13
            int r18 = r8 + 1
            r8 = r17[r8]
            r14[r10] = r8
            r8 = r18
            goto L_0x038d
        L_0x0380:
            r28 = r13
            r13 = 1
        L_0x0383:
            int r10 = r21 / 3
            int r10 = r10 << r13
            int r10 = r10 + r13
            java.lang.Class r18 = r9.getType()
            r14[r10] = r18
        L_0x038d:
            long r9 = r6.objectFieldOffset(r9)
            int r9 = (int) r9
            r10 = r5 & 1
            if (r10 != r13) goto L_0x03e7
            r10 = 17
            if (r11 > r10) goto L_0x03e7
            int r10 = r3 + 1
            char r3 = r1.charAt(r3)
            r13 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r13) goto L_0x03c0
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r19 = 13
        L_0x03a9:
            int r29 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r13) goto L_0x03bb
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r19
            r3 = r3 | r10
            int r19 = r19 + 13
            r10 = r29
            goto L_0x03a9
        L_0x03bb:
            int r10 = r10 << r19
            r3 = r3 | r10
            r10 = r29
        L_0x03c0:
            r18 = 1
            int r19 = r16 << 1
            int r29 = r3 / 32
            int r19 = r19 + r29
            r13 = r17[r19]
            r31 = r1
            boolean r1 = r13 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x03d4
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
            goto L_0x03dc
        L_0x03d4:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zza(r7, r13)
            r17[r19] = r13
        L_0x03dc:
            r1 = r7
            r19 = r8
            long r7 = r6.objectFieldOffset(r13)
            int r8 = (int) r7
            int r3 = r3 % 32
            goto L_0x03f2
        L_0x03e7:
            r31 = r1
            r1 = r7
            r19 = r8
            r18 = 1
            r10 = r3
            r3 = 0
            r8 = 0
        L_0x03f2:
            r7 = 18
            if (r11 < r7) goto L_0x0400
            r7 = 49
            if (r11 > r7) goto L_0x0400
            int r7 = r23 + 1
            r15[r23] = r9
            r23 = r7
        L_0x0400:
            int r7 = r21 + 1
            r4[r21] = r12
            int r12 = r7 + 1
            r13 = r2 & 512(0x200, float:7.175E-43)
            if (r13 == 0) goto L_0x040d
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x040e
        L_0x040d:
            r13 = 0
        L_0x040e:
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x0415
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0416
        L_0x0415:
            r2 = 0
        L_0x0416:
            r2 = r2 | r13
            int r11 = r11 << 20
            r2 = r2 | r11
            r2 = r2 | r9
            r4[r7] = r2
            int r21 = r12 + 1
            int r2 = r3 << 20
            r2 = r2 | r8
            r4[r12] = r2
            r7 = r1
            r12 = r10
            r10 = r19
            r11 = r24
            r2 = r25
            r3 = r26
            r13 = r27
            r9 = r28
            r8 = r30
            r1 = r31
            goto L_0x01d3
        L_0x0438:
            r26 = r3
            r28 = r9
            r24 = r11
            r27 = r13
            com.google.android.gms.internal.measurement.zzgs r1 = new com.google.android.gms.internal.measurement.zzgs
            com.google.android.gms.internal.measurement.zzgo r10 = r0.zzc()
            r12 = 0
            r5 = r1
            r6 = r4
            r7 = r14
            r8 = r28
            r9 = r27
            r13 = r15
            r14 = r26
            r15 = r20
            r16 = r37
            r17 = r38
            r18 = r39
            r19 = r40
            r20 = r41
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x0461:
            com.google.android.gms.internal.measurement.zzhs r0 = (com.google.android.gms.internal.measurement.zzhs) r0
            int r0 = r0.zza()
            int r1 = com.google.android.gms.internal.measurement.zzfd.zze.zzi
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zza(java.lang.Class, com.google.android.gms.internal.measurement.zzgm, com.google.android.gms.internal.measurement.zzgw, com.google.android.gms.internal.measurement.zzfy, com.google.android.gms.internal.measurement.zzhv, com.google.android.gms.internal.measurement.zzes, com.google.android.gms.internal.measurement.zzgh):com.google.android.gms.internal.measurement.zzgs");
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

    public final T zza() {
        return this.zzo.zza(this.zzg);
    }

    public final boolean zza(T t, T t2) {
        int length = this.zzc.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzd2 = zzd(i);
                long j = (long) (zzd2 & 1048575);
                switch ((zzd2 & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzib.zze(t, j)) != Double.doubleToLongBits(zzib.zze(t2, j))) {
                            z = false;
                            break;
                        }
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzib.zzd(t, j)) != Float.floatToIntBits(zzib.zzd(t2, j))) {
                            z = false;
                            break;
                        }
                    case 2:
                        if (!zzc(t, t2, i) || zzib.zzb(t, j) != zzib.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                    case 3:
                        if (!zzc(t, t2, i) || zzib.zzb(t, j) != zzib.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                    case 4:
                        if (!zzc(t, t2, i) || zzib.zza((Object) t, j) != zzib.zza((Object) t2, j)) {
                            z = false;
                            break;
                        }
                    case 5:
                        if (!zzc(t, t2, i) || zzib.zzb(t, j) != zzib.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                    case 6:
                        if (!zzc(t, t2, i) || zzib.zza((Object) t, j) != zzib.zza((Object) t2, j)) {
                            z = false;
                            break;
                        }
                    case 7:
                        if (!zzc(t, t2, i) || zzib.zzc(t, j) != zzib.zzc(t2, j)) {
                            z = false;
                            break;
                        }
                    case 8:
                        if (!zzc(t, t2, i) || !zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                    case 9:
                        if (!zzc(t, t2, i) || !zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                    case 10:
                        if (!zzc(t, t2, i) || !zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                    case 11:
                        if (!zzc(t, t2, i) || zzib.zza((Object) t, j) != zzib.zza((Object) t2, j)) {
                            z = false;
                            break;
                        }
                    case 12:
                        if (!zzc(t, t2, i) || zzib.zza((Object) t, j) != zzib.zza((Object) t2, j)) {
                            z = false;
                            break;
                        }
                    case 13:
                        if (!zzc(t, t2, i) || zzib.zza((Object) t, j) != zzib.zza((Object) t2, j)) {
                            z = false;
                            break;
                        }
                    case 14:
                        if (!zzc(t, t2, i) || zzib.zzb(t, j) != zzib.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                    case 15:
                        if (!zzc(t, t2, i) || zzib.zza((Object) t, j) != zzib.zza((Object) t2, j)) {
                            z = false;
                            break;
                        }
                    case 16:
                        if (!zzc(t, t2, i) || zzib.zzb(t, j) != zzib.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                    case 17:
                        if (!zzc(t, t2, i) || !zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j))) {
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
                        z = zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j));
                        break;
                    case 50:
                        z = zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j));
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
                        long zze2 = (long) (zze(i) & 1048575);
                        if (zzib.zza((Object) t, zze2) != zzib.zza((Object) t2, zze2) || !zzhf.zza(zzib.zzf(t, j), zzib.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzq.zzb(t).equals(this.zzq.zzb(t2))) {
                return false;
            } else {
                if (this.zzh) {
                    return this.zzr.zza((Object) t).equals(this.zzr.zza((Object) t2));
                }
                return true;
            }
        }
    }

    public final int zza(T t) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzd2 = zzd(i2);
            int i3 = this.zzc[i2];
            long j = (long) (1048575 & zzd2);
            int i4 = 37;
            switch ((zzd2 & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzff.zza(Double.doubleToLongBits(zzib.zze(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzib.zzd(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzff.zza(zzib.zzb(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzff.zza(zzib.zzb(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzib.zza((Object) t, j);
                    break;
                case 5:
                    i = (i * 53) + zzff.zza(zzib.zzb(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzib.zza((Object) t, j);
                    break;
                case 7:
                    i = (i * 53) + zzff.zza(zzib.zzc(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzib.zzf(t, j)).hashCode();
                    break;
                case 9:
                    Object zzf2 = zzib.zzf(t, j);
                    if (zzf2 != null) {
                        i4 = zzf2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzib.zzf(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzib.zza((Object) t, j);
                    break;
                case 12:
                    i = (i * 53) + zzib.zza((Object) t, j);
                    break;
                case 13:
                    i = (i * 53) + zzib.zza((Object) t, j);
                    break;
                case 14:
                    i = (i * 53) + zzff.zza(zzib.zzb(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzib.zza((Object) t, j);
                    break;
                case 16:
                    i = (i * 53) + zzff.zza(zzib.zzb(t, j));
                    break;
                case 17:
                    Object zzf3 = zzib.zzf(t, j);
                    if (zzf3 != null) {
                        i4 = zzf3.hashCode();
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
                    i = (i * 53) + zzib.zzf(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzib.zzf(t, j).hashCode();
                    break;
                case 51:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(Double.doubleToLongBits(zzb(t, j)));
                        break;
                    }
                case 52:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzc(t, j));
                        break;
                    }
                case 53:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(zze(t, j));
                        break;
                    }
                case 54:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(zze(t, j));
                        break;
                    }
                case 55:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzd(t, j);
                        break;
                    }
                case 56:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(zze(t, j));
                        break;
                    }
                case 57:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzd(t, j);
                        break;
                    }
                case 58:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(zzf(t, j));
                        break;
                    }
                case 59:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzib.zzf(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzib.zzf(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzib.zzf(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzd(t, j);
                        break;
                    }
                case 63:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzd(t, j);
                        break;
                    }
                case 64:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzd(t, j);
                        break;
                    }
                case 65:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(zze(t, j));
                        break;
                    }
                case 66:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzd(t, j);
                        break;
                    }
                case 67:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzff.zza(zze(t, j));
                        break;
                    }
                case 68:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzib.zzf(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzq.zzb(t).hashCode();
        if (this.zzh) {
            return (hashCode * 53) + this.zzr.zza((Object) t).hashCode();
        }
        return hashCode;
    }

    public final void zzb(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzd2 = zzd(i);
                long j = (long) (1048575 & zzd2);
                int i2 = this.zzc[i];
                switch ((zzd2 & 267386880) >>> 20) {
                    case 0:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zze(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzd(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzb(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzb(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zza((Object) t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzb(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zza((Object) t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzc(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzf(t2, j));
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
                            zzib.zza((Object) t, j, zzib.zzf(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zza((Object) t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zza((Object) t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zza((Object) t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzb(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zza((Object) t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzib.zza((Object) t, j, zzib.zzb(t2, j));
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
                        this.zzp.zza(t, t2, j);
                        break;
                    case 50:
                        zzhf.zza(this.zzs, t, t2, j);
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
                            zzib.zza((Object) t, j, zzib.zzf(t2, j));
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
                            zzib.zza((Object) t, j, zzib.zzf(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzhf.zza(this.zzq, t, t2);
            if (this.zzh) {
                zzhf.zza(this.zzr, t, t2);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzd2 = (long) (zzd(i) & 1048575);
        if (zza(t2, i)) {
            Object zzf2 = zzib.zzf(t, zzd2);
            Object zzf3 = zzib.zzf(t2, zzd2);
            if (zzf2 == null || zzf3 == null) {
                if (zzf3 != null) {
                    zzib.zza((Object) t, zzd2, zzf3);
                    zzb(t, i);
                }
                return;
            }
            zzib.zza((Object) t, zzd2, zzff.zza(zzf2, zzf3));
            zzb(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzd2 = zzd(i);
        int i2 = this.zzc[i];
        long j = (long) (zzd2 & 1048575);
        if (zza(t2, i2, i)) {
            Object zzf2 = zzib.zzf(t, j);
            Object zzf3 = zzib.zzf(t2, j);
            if (zzf2 == null || zzf3 == null) {
                if (zzf3 != null) {
                    zzib.zza((Object) t, j, zzf3);
                    zzb(t, i2, i);
                }
                return;
            }
            zzib.zza((Object) t, j, zzff.zza(zzf2, zzf3));
            zzb(t, i2, i);
        }
    }

    public final int zzb(T t) {
        int i;
        int i2;
        long j;
        int i3;
        T t2 = t;
        int i4 = 267386880;
        int i5 = 1048575;
        int i6 = 1;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.zzc.length) {
                int zzd2 = zzd(i7);
                int i9 = (zzd2 & i4) >>> 20;
                int i10 = this.zzc[i7];
                long j2 = (long) (zzd2 & 1048575);
                if (i9 < zzex.DOUBLE_LIST_PACKED.zza() || i9 > zzex.SINT64_LIST_PACKED.zza()) {
                    i3 = 0;
                } else {
                    i3 = this.zzc[i7 + 2] & 1048575;
                }
                switch (i9) {
                    case 0:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzb(i10, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzb(i10, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzd(i10, zzib.zzb(t2, j2));
                            break;
                        }
                    case 3:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zze(i10, zzib.zzb(t2, j2));
                            break;
                        }
                    case 4:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzf(i10, zzib.zza((Object) t2, j2));
                            break;
                        }
                    case 5:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzg(i10, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzi(i10, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzb(i10, true);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            Object zzf2 = zzib.zzf(t2, j2);
                            if (!(zzf2 instanceof zzdu)) {
                                i8 += zzen.zzb(i10, (String) zzf2);
                                break;
                            } else {
                                i8 += zzen.zzc(i10, (zzdu) zzf2);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzhf.zza(i10, zzib.zzf(t2, j2), zza(i7));
                            break;
                        }
                    case 10:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzc(i10, (zzdu) zzib.zzf(t2, j2));
                            break;
                        }
                    case 11:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzg(i10, zzib.zza((Object) t2, j2));
                            break;
                        }
                    case 12:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzk(i10, zzib.zza((Object) t2, j2));
                            break;
                        }
                    case 13:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzj(i10, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzh(i10, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzh(i10, zzib.zza((Object) t2, j2));
                            break;
                        }
                    case 16:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzf(i10, zzib.zzb(t2, j2));
                            break;
                        }
                    case 17:
                        if (!zza(t2, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzc(i10, (zzgo) zzib.zzf(t2, j2), zza(i7));
                            break;
                        }
                    case 18:
                        i8 += zzhf.zzi(i10, zza((Object) t2, j2), false);
                        break;
                    case 19:
                        i8 += zzhf.zzh(i10, zza((Object) t2, j2), false);
                        break;
                    case 20:
                        i8 += zzhf.zza(i10, zza((Object) t2, j2), false);
                        break;
                    case 21:
                        i8 += zzhf.zzb(i10, zza((Object) t2, j2), false);
                        break;
                    case 22:
                        i8 += zzhf.zze(i10, zza((Object) t2, j2), false);
                        break;
                    case 23:
                        i8 += zzhf.zzi(i10, zza((Object) t2, j2), false);
                        break;
                    case 24:
                        i8 += zzhf.zzh(i10, zza((Object) t2, j2), false);
                        break;
                    case 25:
                        i8 += zzhf.zzj(i10, zza((Object) t2, j2), false);
                        break;
                    case 26:
                        i8 += zzhf.zza(i10, zza((Object) t2, j2));
                        break;
                    case 27:
                        i8 += zzhf.zza(i10, zza((Object) t2, j2), zza(i7));
                        break;
                    case 28:
                        i8 += zzhf.zzb(i10, zza((Object) t2, j2));
                        break;
                    case 29:
                        i8 += zzhf.zzf(i10, zza((Object) t2, j2), false);
                        break;
                    case 30:
                        i8 += zzhf.zzd(i10, zza((Object) t2, j2), false);
                        break;
                    case 31:
                        i8 += zzhf.zzh(i10, zza((Object) t2, j2), false);
                        break;
                    case 32:
                        i8 += zzhf.zzi(i10, zza((Object) t2, j2), false);
                        break;
                    case 33:
                        i8 += zzhf.zzg(i10, zza((Object) t2, j2), false);
                        break;
                    case 34:
                        i8 += zzhf.zzc(i10, zza((Object) t2, j2), false);
                        break;
                    case 35:
                        int zzi2 = zzhf.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzi2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzi2) + zzi2;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzh2 = zzhf.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzh2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzh2) + zzh2;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zza2 = zzhf.zza((List) unsafe.getObject(t2, j2));
                        if (zza2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zza2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zza2) + zza2;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzb2 = zzhf.zzb((List) unsafe.getObject(t2, j2));
                        if (zzb2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzb2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzb2) + zzb2;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zze2 = zzhf.zze((List) unsafe.getObject(t2, j2));
                        if (zze2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zze2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zze2) + zze2;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzi3 = zzhf.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzi3);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzi3) + zzi3;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzh3 = zzhf.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzh3);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzh3) + zzh3;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzj2 = zzhf.zzj((List) unsafe.getObject(t2, j2));
                        if (zzj2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzj2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzj2) + zzj2;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzf3 = zzhf.zzf((List) unsafe.getObject(t2, j2));
                        if (zzf3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzf3);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzf3) + zzf3;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzd3 = zzhf.zzd((List) unsafe.getObject(t2, j2));
                        if (zzd3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzd3);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzd3) + zzd3;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzh4 = zzhf.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh4 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzh4);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzh4) + zzh4;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzi4 = zzhf.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi4 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzi4);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzi4) + zzi4;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzg2 = zzhf.zzg((List) unsafe.getObject(t2, j2));
                        if (zzg2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzg2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzg2) + zzg2;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzc2 = zzhf.zzc((List) unsafe.getObject(t2, j2));
                        if (zzc2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t2, (long) i3, zzc2);
                            }
                            i8 += zzen.zze(i10) + zzen.zzg(zzc2) + zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i8 += zzhf.zzb(i10, zza((Object) t2, j2), zza(i7));
                        break;
                    case 50:
                        i8 += this.zzs.zza(i10, zzib.zzf(t2, j2), zzb(i7));
                        break;
                    case 51:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzb(i10, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzb(i10, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzd(i10, zze(t2, j2));
                            break;
                        }
                    case 54:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zze(i10, zze(t2, j2));
                            break;
                        }
                    case 55:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzf(i10, zzd(t2, j2));
                            break;
                        }
                    case 56:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzg(i10, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzi(i10, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzb(i10, true);
                            break;
                        }
                    case 59:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            Object zzf4 = zzib.zzf(t2, j2);
                            if (!(zzf4 instanceof zzdu)) {
                                i8 += zzen.zzb(i10, (String) zzf4);
                                break;
                            } else {
                                i8 += zzen.zzc(i10, (zzdu) zzf4);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzhf.zza(i10, zzib.zzf(t2, j2), zza(i7));
                            break;
                        }
                    case 61:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzc(i10, (zzdu) zzib.zzf(t2, j2));
                            break;
                        }
                    case 62:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzg(i10, zzd(t2, j2));
                            break;
                        }
                    case 63:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzk(i10, zzd(t2, j2));
                            break;
                        }
                    case 64:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzj(i10, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzh(i10, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzh(i10, zzd(t2, j2));
                            break;
                        }
                    case 67:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzf(i10, zze(t2, j2));
                            break;
                        }
                    case 68:
                        if (!zza(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzen.zzc(i10, (zzgo) zzib.zzf(t2, j2), zza(i7));
                            break;
                        }
                }
                i7 += 3;
                i4 = 267386880;
            }
            return i8 + zza(this.zzq, t2);
        }
        Unsafe unsafe2 = zzb;
        int i11 = 0;
        int i12 = 0;
        int i13 = -1;
        int i14 = 0;
        while (i11 < this.zzc.length) {
            int zzd4 = zzd(i11);
            int[] iArr = this.zzc;
            int i15 = iArr[i11];
            int i16 = (zzd4 & 267386880) >>> 20;
            if (i16 <= 17) {
                i2 = iArr[i11 + 2];
                int i17 = i2 & i5;
                i = i6 << (i2 >>> 20);
                if (i17 != i13) {
                    i14 = unsafe2.getInt(t2, (long) i17);
                } else {
                    i17 = i13;
                }
                i13 = i17;
            } else if (!this.zzk || i16 < zzex.DOUBLE_LIST_PACKED.zza() || i16 > zzex.SINT64_LIST_PACKED.zza()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzc[i11 + 2] & i5;
                i = 0;
            }
            long j3 = (long) (zzd4 & i5);
            switch (i16) {
                case 0:
                    j = 0;
                    if ((i14 & i) == 0) {
                        break;
                    } else {
                        i12 += zzen.zzb(i15, 0.0d);
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i14 & i) == 0) {
                        break;
                    } else {
                        i12 += zzen.zzb(i15, 0.0f);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i14 & i) == 0) {
                        break;
                    } else {
                        i12 += zzen.zzd(i15, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i14 & i) == 0) {
                        break;
                    } else {
                        i12 += zzen.zze(i15, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i14 & i) == 0) {
                        break;
                    } else {
                        i12 += zzen.zzf(i15, unsafe2.getInt(t2, j3));
                        break;
                    }
                case 5:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        i12 += zzen.zzg(i15, 0);
                        break;
                    }
                case 6:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzi(i15, 0);
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzb(i15, true);
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        Object object = unsafe2.getObject(t2, j3);
                        if (!(object instanceof zzdu)) {
                            i12 += zzen.zzb(i15, (String) object);
                            j = 0;
                            break;
                        } else {
                            i12 += zzen.zzc(i15, (zzdu) object);
                            j = 0;
                            break;
                        }
                    }
                case 9:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzhf.zza(i15, unsafe2.getObject(t2, j3), zza(i11));
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzc(i15, (zzdu) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzg(i15, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzk(i15, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzj(i15, 0);
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzh(i15, 0);
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzh(i15, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzf(i15, unsafe2.getLong(t2, j3));
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i14 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzc(i15, (zzgo) unsafe2.getObject(t2, j3), zza(i11));
                        j = 0;
                        break;
                    }
                case 18:
                    i12 += zzhf.zzi(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 19:
                    i12 += zzhf.zzh(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 20:
                    i12 += zzhf.zza(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 21:
                    i12 += zzhf.zzb(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 22:
                    i12 += zzhf.zze(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 23:
                    i12 += zzhf.zzi(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 24:
                    i12 += zzhf.zzh(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 25:
                    i12 += zzhf.zzj(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 26:
                    i12 += zzhf.zza(i15, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 27:
                    i12 += zzhf.zza(i15, (List) unsafe2.getObject(t2, j3), zza(i11));
                    j = 0;
                    break;
                case 28:
                    i12 += zzhf.zzb(i15, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 29:
                    i12 += zzhf.zzf(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 30:
                    i12 += zzhf.zzd(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 31:
                    i12 += zzhf.zzh(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 32:
                    i12 += zzhf.zzi(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 33:
                    i12 += zzhf.zzg(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 34:
                    i12 += zzhf.zzc(i15, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzi5 = zzhf.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzi5);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzi5) + zzi5;
                        j = 0;
                        break;
                    }
                case 36:
                    int zzh5 = zzhf.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzh5);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzh5) + zzh5;
                        j = 0;
                        break;
                    }
                case 37:
                    int zza3 = zzhf.zza((List) unsafe2.getObject(t2, j3));
                    if (zza3 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zza3);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zza3) + zza3;
                        j = 0;
                        break;
                    }
                case 38:
                    int zzb3 = zzhf.zzb((List) unsafe2.getObject(t2, j3));
                    if (zzb3 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzb3);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzb3) + zzb3;
                        j = 0;
                        break;
                    }
                case 39:
                    int zze3 = zzhf.zze((List) unsafe2.getObject(t2, j3));
                    if (zze3 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zze3);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zze3) + zze3;
                        j = 0;
                        break;
                    }
                case 40:
                    int zzi6 = zzhf.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzi6);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzi6) + zzi6;
                        j = 0;
                        break;
                    }
                case 41:
                    int zzh6 = zzhf.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzh6);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzh6) + zzh6;
                        j = 0;
                        break;
                    }
                case 42:
                    int zzj3 = zzhf.zzj((List) unsafe2.getObject(t2, j3));
                    if (zzj3 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzj3);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzj3) + zzj3;
                        j = 0;
                        break;
                    }
                case 43:
                    int zzf5 = zzhf.zzf((List) unsafe2.getObject(t2, j3));
                    if (zzf5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzf5);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzf5) + zzf5;
                        j = 0;
                        break;
                    }
                case 44:
                    int zzd5 = zzhf.zzd((List) unsafe2.getObject(t2, j3));
                    if (zzd5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzd5);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzd5) + zzd5;
                        j = 0;
                        break;
                    }
                case 45:
                    int zzh7 = zzhf.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh7 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzh7);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzh7) + zzh7;
                        j = 0;
                        break;
                    }
                case 46:
                    int zzi7 = zzhf.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi7 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzi7);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzi7) + zzi7;
                        j = 0;
                        break;
                    }
                case 47:
                    int zzg3 = zzhf.zzg((List) unsafe2.getObject(t2, j3));
                    if (zzg3 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzg3);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzg3) + zzg3;
                        j = 0;
                        break;
                    }
                case 48:
                    int zzc3 = zzhf.zzc((List) unsafe2.getObject(t2, j3));
                    if (zzc3 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzk) {
                            unsafe2.putInt(t2, (long) i2, zzc3);
                        }
                        i12 += zzen.zze(i15) + zzen.zzg(zzc3) + zzc3;
                        j = 0;
                        break;
                    }
                case 49:
                    i12 += zzhf.zzb(i15, (List) unsafe2.getObject(t2, j3), zza(i11));
                    j = 0;
                    break;
                case 50:
                    i12 += this.zzs.zza(i15, unsafe2.getObject(t2, j3), zzb(i11));
                    j = 0;
                    break;
                case 51:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzb(i15, 0.0d);
                        j = 0;
                        break;
                    }
                case 52:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzb(i15, 0.0f);
                        j = 0;
                        break;
                    }
                case 53:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzd(i15, zze(t2, j3));
                        j = 0;
                        break;
                    }
                case 54:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zze(i15, zze(t2, j3));
                        j = 0;
                        break;
                    }
                case 55:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzf(i15, zzd(t2, j3));
                        j = 0;
                        break;
                    }
                case 56:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzg(i15, 0);
                        j = 0;
                        break;
                    }
                case 57:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzi(i15, 0);
                        j = 0;
                        break;
                    }
                case 58:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzb(i15, true);
                        j = 0;
                        break;
                    }
                case 59:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        Object object2 = unsafe2.getObject(t2, j3);
                        if (!(object2 instanceof zzdu)) {
                            i12 += zzen.zzb(i15, (String) object2);
                            j = 0;
                            break;
                        } else {
                            i12 += zzen.zzc(i15, (zzdu) object2);
                            j = 0;
                            break;
                        }
                    }
                case 60:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzhf.zza(i15, unsafe2.getObject(t2, j3), zza(i11));
                        j = 0;
                        break;
                    }
                case 61:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzc(i15, (zzdu) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 62:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzg(i15, zzd(t2, j3));
                        j = 0;
                        break;
                    }
                case 63:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzk(i15, zzd(t2, j3));
                        j = 0;
                        break;
                    }
                case 64:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzj(i15, 0);
                        j = 0;
                        break;
                    }
                case 65:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzh(i15, 0);
                        j = 0;
                        break;
                    }
                case 66:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzh(i15, zzd(t2, j3));
                        j = 0;
                        break;
                    }
                case 67:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzf(i15, zze(t2, j3));
                        j = 0;
                        break;
                    }
                case 68:
                    if (!zza(t2, i15, i11)) {
                        j = 0;
                        break;
                    } else {
                        i12 += zzen.zzc(i15, (zzgo) unsafe2.getObject(t2, j3), zza(i11));
                        j = 0;
                        break;
                    }
                default:
                    j = 0;
                    break;
            }
            i11 += 3;
            long j4 = j;
            i5 = 1048575;
            i6 = 1;
        }
        int zza4 = i12 + zza(this.zzq, t2);
        if (this.zzh) {
            zzew zza5 = this.zzr.zza((Object) t2);
            int i18 = 0;
            for (int i19 = 0; i19 < zza5.zza.zzc(); i19++) {
                Entry zzb4 = zza5.zza.zzb(i19);
                i18 += zzew.zza((zzey) zzb4.getKey(), zzb4.getValue());
            }
            for (Entry entry : zza5.zza.zzd()) {
                i18 += zzew.zza((zzey) entry.getKey(), entry.getValue());
            }
            zza4 += i18;
        }
        return zza4;
    }

    private static <UT, UB> int zza(zzhv<UT, UB> zzhv, T t) {
        return zzhv.zzf(zzhv.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzib.zzf(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x05ad  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x05f1  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0b5f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.measurement.zzis r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zza()
            int r1 = com.google.android.gms.internal.measurement.zzfd.zze.zzk
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x05c3
            com.google.android.gms.internal.measurement.zzhv<?, ?> r0 = r13.zzq
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzh
            if (r0 == 0) goto L_0x0036
            com.google.android.gms.internal.measurement.zzes<?> r0 = r13.zzr
            com.google.android.gms.internal.measurement.zzew r0 = r0.zza(r14)
            com.google.android.gms.internal.measurement.zzhi<T, java.lang.Object> r1 = r0.zza
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0036
            java.util.Iterator r0 = r0.zze()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0038
        L_0x0036:
            r0 = r3
            r1 = r0
        L_0x0038:
            int[] r7 = r13.zzc
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x003d:
            if (r7 < 0) goto L_0x05ab
            int r8 = r13.zzd(r7)
            int[] r9 = r13.zzc
            r9 = r9[r7]
        L_0x0049:
            if (r1 == 0) goto L_0x0067
            com.google.android.gms.internal.measurement.zzes<?> r10 = r13.zzr
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x0067
            com.google.android.gms.internal.measurement.zzes<?> r10 = r13.zzr
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
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            com.google.android.gms.internal.measurement.zzhd r10 = r13.zza(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x05a7
        L_0x0087:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zze(r14, r10)
            r15.zze(r9, r10)
            goto L_0x05a7
        L_0x0099:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzd(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x05a7
        L_0x00ab:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zze(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x05a7
        L_0x00bd:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzd(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x00cf:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzd(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x05a7
        L_0x00e1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzd(r14, r10)
            r15.zze(r9, r8)
            goto L_0x05a7
        L_0x00f3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            com.google.android.gms.internal.measurement.zzdu r8 = (com.google.android.gms.internal.measurement.zzdu) r8
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x0107:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            com.google.android.gms.internal.measurement.zzhd r10 = r13.zza(r7)
            r15.zza(r9, r8, r10)
            goto L_0x05a7
        L_0x011d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            zza(r9, r8, r15)
            goto L_0x05a7
        L_0x012f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzf(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x0141:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzd(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x05a7
        L_0x0153:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zze(r14, r10)
            r15.zzd(r9, r10)
            goto L_0x05a7
        L_0x0165:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzd(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x05a7
        L_0x0177:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zze(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x05a7
        L_0x0189:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zze(r14, r10)
            r15.zza(r9, r10)
            goto L_0x05a7
        L_0x019b:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzc(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x01ad:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzb((T) r14, r10)
            r15.zza(r9, r10)
            goto L_0x05a7
        L_0x01bf:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x05a7
        L_0x01cb:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhd r10 = r13.zza(r7)
            com.google.android.gms.internal.measurement.zzhf.zzb(r9, r8, r15, r10)
            goto L_0x05a7
        L_0x01e3:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zze(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x01f7:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzj(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x020b:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzg(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x021f:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzl(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0233:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzm(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0247:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzi(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x025b:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzn(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x026f:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzk(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0283:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzf(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0297:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzh(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02ab:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzd(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02bf:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzc(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02d3:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzb(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02e7:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zza(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02fb:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zze(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x030f:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzj(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0323:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzg(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0337:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzl(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x034b:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzm(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x035f:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzi(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0373:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzb(r9, r8, r15)
            goto L_0x05a7
        L_0x0387:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhd r10 = r13.zza(r7)
            com.google.android.gms.internal.measurement.zzhf.zza(r9, r8, r15, r10)
            goto L_0x05a7
        L_0x039f:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zza(r9, r8, r15)
            goto L_0x05a7
        L_0x03b3:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzn(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03c7:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzk(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03db:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzf(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03ef:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzh(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0403:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzd(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0417:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzc(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x042b:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zzb(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x043f:
            int[] r9 = r13.zzc
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzhf.zza(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0453:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            com.google.android.gms.internal.measurement.zzhd r10 = r13.zza(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x05a7
        L_0x0469:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r10)
            r15.zze(r9, r10)
            goto L_0x05a7
        L_0x047c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzib.zza(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x05a7
        L_0x048f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x05a7
        L_0x04a2:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzib.zza(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x04b5:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzib.zza(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x05a7
        L_0x04c8:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzib.zza(r14, r10)
            r15.zze(r9, r8)
            goto L_0x05a7
        L_0x04db:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            com.google.android.gms.internal.measurement.zzdu r8 = (com.google.android.gms.internal.measurement.zzdu) r8
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x04ef:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            com.google.android.gms.internal.measurement.zzhd r10 = r13.zza(r7)
            r15.zza(r9, r8, r10)
            goto L_0x05a7
        L_0x0505:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r10)
            zza(r9, r8, r15)
            goto L_0x05a7
        L_0x0517:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.measurement.zzib.zzc(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x052a:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzib.zza(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x05a7
        L_0x053c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r10)
            r15.zzd(r9, r10)
            goto L_0x05a7
        L_0x054e:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzib.zza(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x05a7
        L_0x0560:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x05a7
        L_0x0572:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r10)
            r15.zza(r9, r10)
            goto L_0x05a7
        L_0x0584:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.measurement.zzib.zzd(r14, r10)
            r15.zza(r9, r8)
            goto L_0x05a7
        L_0x0596:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.measurement.zzib.zze(r14, r10)
            r15.zza(r9, r10)
        L_0x05a7:
            int r7 = r7 + -3
            goto L_0x003d
        L_0x05ab:
            if (r1 == 0) goto L_0x05c2
            com.google.android.gms.internal.measurement.zzes<?> r14 = r13.zzr
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
            boolean r0 = r13.zzj
            if (r0 == 0) goto L_0x0b7a
            boolean r0 = r13.zzh
            if (r0 == 0) goto L_0x05e8
            com.google.android.gms.internal.measurement.zzes<?> r0 = r13.zzr
            com.google.android.gms.internal.measurement.zzew r0 = r0.zza(r14)
            com.google.android.gms.internal.measurement.zzhi<T, java.lang.Object> r1 = r0.zza
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x05e8
            java.util.Iterator r0 = r0.zzd()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x05ea
        L_0x05e8:
            r0 = r3
            r1 = r0
        L_0x05ea:
            int[] r7 = r13.zzc
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x05ef:
            if (r1 >= r7) goto L_0x0b5d
            int r9 = r13.zzd(r1)
            int[] r10 = r13.zzc
            r10 = r10[r1]
        L_0x05fb:
            if (r8 == 0) goto L_0x0619
            com.google.android.gms.internal.measurement.zzes<?> r11 = r13.zzr
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0619
            com.google.android.gms.internal.measurement.zzes<?> r11 = r13.zzr
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
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            com.google.android.gms.internal.measurement.zzhd r11 = r13.zza(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0b59
        L_0x0639:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zze(r14, r11)
            r15.zze(r10, r11)
            goto L_0x0b59
        L_0x064b:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzd(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0b59
        L_0x065d:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zze(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0b59
        L_0x066f:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzd(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0681:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzd(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0b59
        L_0x0693:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzd(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0b59
        L_0x06a5:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            com.google.android.gms.internal.measurement.zzdu r9 = (com.google.android.gms.internal.measurement.zzdu) r9
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x06b9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            com.google.android.gms.internal.measurement.zzhd r11 = r13.zza(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0b59
        L_0x06cf:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0b59
        L_0x06e1:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzf(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x06f3:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzd(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0b59
        L_0x0705:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zze(r14, r11)
            r15.zzd(r10, r11)
            goto L_0x0b59
        L_0x0717:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzd(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0b59
        L_0x0729:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zze(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0b59
        L_0x073b:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zze(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0b59
        L_0x074d:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzc(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x075f:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzb((T) r14, r11)
            r15.zza(r10, r11)
            goto L_0x0b59
        L_0x0771:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0b59
        L_0x077d:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhd r11 = r13.zza(r1)
            com.google.android.gms.internal.measurement.zzhf.zzb(r10, r9, r15, r11)
            goto L_0x0b59
        L_0x0795:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zze(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07a9:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzj(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07bd:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzg(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07d1:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzl(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07e5:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzm(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x07f9:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzi(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x080d:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzn(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0821:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzk(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0835:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzf(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0849:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzh(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x085d:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzd(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0871:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzc(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0885:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzb(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x0899:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zza(r10, r9, r15, r4)
            goto L_0x0b59
        L_0x08ad:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zze(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08c1:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzj(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08d5:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzg(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08e9:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzl(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x08fd:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzm(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0911:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzi(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0925:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzb(r10, r9, r15)
            goto L_0x0b59
        L_0x0939:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhd r11 = r13.zza(r1)
            com.google.android.gms.internal.measurement.zzhf.zza(r10, r9, r15, r11)
            goto L_0x0b59
        L_0x0951:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zza(r10, r9, r15)
            goto L_0x0b59
        L_0x0965:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzn(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0979:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzk(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x098d:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzf(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09a1:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzh(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09b5:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzd(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09c9:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzc(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09dd:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzb(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x09f1:
            int[] r10 = r13.zzc
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zza(r10, r9, r15, r5)
            goto L_0x0b59
        L_0x0a05:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            com.google.android.gms.internal.measurement.zzhd r11 = r13.zza(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0b59
        L_0x0a1b:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r11)
            r15.zze(r10, r11)
            goto L_0x0b59
        L_0x0a2e:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzib.zza(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0b59
        L_0x0a41:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0b59
        L_0x0a54:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzib.zza(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0a67:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzib.zza(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0b59
        L_0x0a7a:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzib.zza(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0b59
        L_0x0a8d:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            com.google.android.gms.internal.measurement.zzdu r9 = (com.google.android.gms.internal.measurement.zzdu) r9
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0aa1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            com.google.android.gms.internal.measurement.zzhd r11 = r13.zza(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0b59
        L_0x0ab7:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzib.zzf(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0b59
        L_0x0ac9:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.measurement.zzib.zzc(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0adc:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzib.zza(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0b59
        L_0x0aee:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r11)
            r15.zzd(r10, r11)
            goto L_0x0b59
        L_0x0b00:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzib.zza(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0b59
        L_0x0b12:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0b59
        L_0x0b24:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzib.zzb(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0b59
        L_0x0b36:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.measurement.zzib.zzd(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0b59
        L_0x0b48:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0b59
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.measurement.zzib.zze(r14, r11)
            r15.zza(r10, r11)
        L_0x0b59:
            int r1 = r1 + 3
            goto L_0x05ef
        L_0x0b5d:
            if (r8 == 0) goto L_0x0b74
            com.google.android.gms.internal.measurement.zzes<?> r1 = r13.zzr
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
            com.google.android.gms.internal.measurement.zzhv<?, ?> r0 = r13.zzq
            zza(r0, (T) r14, r15)
            return
        L_0x0b7a:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzis):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:190:0x0577  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.measurement.zzis r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzh
            if (r3 == 0) goto L_0x0025
            com.google.android.gms.internal.measurement.zzes<?> r3 = r0.zzr
            com.google.android.gms.internal.measurement.zzew r3 = r3.zza(r1)
            com.google.android.gms.internal.measurement.zzhi<T, java.lang.Object> r5 = r3.zza
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0025
            java.util.Iterator r3 = r3.zzd()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0027
        L_0x0025:
            r3 = 0
            r5 = 0
        L_0x0027:
            r6 = -1
            int[] r7 = r0.zzc
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzb
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x0031:
            if (r5 >= r7) goto L_0x0571
            int r12 = r0.zzd(r5)
            int[] r13 = r0.zzc
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzj
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x006d
            r4 = 17
            if (r15 > r4) goto L_0x006d
            int r4 = r5 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0061
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            goto L_0x0064
        L_0x0061:
            r17 = r10
            r13 = r6
        L_0x0064:
            int r4 = r4 >>> 20
            r6 = 1
            int r9 = r6 << r4
            r6 = r13
            r10 = r17
            goto L_0x0072
        L_0x006d:
            r17 = r10
            r10 = r17
            r9 = 0
        L_0x0072:
            if (r10 == 0) goto L_0x0091
            com.google.android.gms.internal.measurement.zzes<?> r4 = r0.zzr
            int r4 = r4.zza(r10)
            if (r4 > r14) goto L_0x0091
            com.google.android.gms.internal.measurement.zzes<?> r4 = r0.zzr
            r4.zza(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x008f
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0072
        L_0x008f:
            r10 = 0
            goto L_0x0072
        L_0x0091:
            r4 = r12 & r16
            long r12 = (long) r4
            switch(r15) {
                case 0: goto L_0x0560;
                case 1: goto L_0x0552;
                case 2: goto L_0x0545;
                case 3: goto L_0x0538;
                case 4: goto L_0x052b;
                case 5: goto L_0x051e;
                case 6: goto L_0x0511;
                case 7: goto L_0x0503;
                case 8: goto L_0x04f5;
                case 9: goto L_0x04e3;
                case 10: goto L_0x04d3;
                case 11: goto L_0x04c5;
                case 12: goto L_0x04b7;
                case 13: goto L_0x04a9;
                case 14: goto L_0x049b;
                case 15: goto L_0x048d;
                case 16: goto L_0x047f;
                case 17: goto L_0x046c;
                case 18: goto L_0x045a;
                case 19: goto L_0x0447;
                case 20: goto L_0x0434;
                case 21: goto L_0x0421;
                case 22: goto L_0x040e;
                case 23: goto L_0x03fb;
                case 24: goto L_0x03e8;
                case 25: goto L_0x03d5;
                case 26: goto L_0x03c3;
                case 27: goto L_0x03ac;
                case 28: goto L_0x039a;
                case 29: goto L_0x0387;
                case 30: goto L_0x0374;
                case 31: goto L_0x0361;
                case 32: goto L_0x034e;
                case 33: goto L_0x033b;
                case 34: goto L_0x0328;
                case 35: goto L_0x0315;
                case 36: goto L_0x0302;
                case 37: goto L_0x02ef;
                case 38: goto L_0x02dc;
                case 39: goto L_0x02c9;
                case 40: goto L_0x02b6;
                case 41: goto L_0x02a3;
                case 42: goto L_0x0290;
                case 43: goto L_0x027d;
                case 44: goto L_0x026a;
                case 45: goto L_0x0257;
                case 46: goto L_0x0244;
                case 47: goto L_0x0231;
                case 48: goto L_0x021e;
                case 49: goto L_0x0207;
                case 50: goto L_0x01fd;
                case 51: goto L_0x01ea;
                case 52: goto L_0x01d7;
                case 53: goto L_0x01c4;
                case 54: goto L_0x01b1;
                case 55: goto L_0x019e;
                case 56: goto L_0x018b;
                case 57: goto L_0x0178;
                case 58: goto L_0x0165;
                case 59: goto L_0x0152;
                case 60: goto L_0x013b;
                case 61: goto L_0x0126;
                case 62: goto L_0x0113;
                case 63: goto L_0x0100;
                case 64: goto L_0x00ed;
                case 65: goto L_0x00da;
                case 66: goto L_0x00c7;
                case 67: goto L_0x00b4;
                case 68: goto L_0x009c;
                default: goto L_0x0099;
            }
        L_0x0099:
            r15 = 0
            goto L_0x056d
        L_0x009c:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00b1
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzhd r9 = r0.zza(r5)
            r2.zzb(r14, r4, r9)
            r15 = 0
            goto L_0x056d
        L_0x00b1:
            r15 = 0
            goto L_0x056d
        L_0x00b4:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00c4
            long r12 = zze(r1, r12)
            r2.zze(r14, r12)
            r15 = 0
            goto L_0x056d
        L_0x00c4:
            r15 = 0
            goto L_0x056d
        L_0x00c7:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00d7
            int r4 = zzd(r1, r12)
            r2.zzf(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x00d7:
            r15 = 0
            goto L_0x056d
        L_0x00da:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00ea
            long r12 = zze(r1, r12)
            r2.zzb(r14, r12)
            r15 = 0
            goto L_0x056d
        L_0x00ea:
            r15 = 0
            goto L_0x056d
        L_0x00ed:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00fd
            int r4 = zzd(r1, r12)
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x00fd:
            r15 = 0
            goto L_0x056d
        L_0x0100:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0110
            int r4 = zzd(r1, r12)
            r2.zzb(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x0110:
            r15 = 0
            goto L_0x056d
        L_0x0113:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0123
            int r4 = zzd(r1, r12)
            r2.zze(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x0123:
            r15 = 0
            goto L_0x056d
        L_0x0126:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0138
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzdu r4 = (com.google.android.gms.internal.measurement.zzdu) r4
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x0138:
            r15 = 0
            goto L_0x056d
        L_0x013b:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x014f
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzhd r9 = r0.zza(r5)
            r2.zza(r14, r4, r9)
            r15 = 0
            goto L_0x056d
        L_0x014f:
            r15 = 0
            goto L_0x056d
        L_0x0152:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0162
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            r15 = 0
            goto L_0x056d
        L_0x0162:
            r15 = 0
            goto L_0x056d
        L_0x0165:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0175
            boolean r4 = zzf(r1, r12)
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x0175:
            r15 = 0
            goto L_0x056d
        L_0x0178:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0188
            int r4 = zzd(r1, r12)
            r2.zzd(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x0188:
            r15 = 0
            goto L_0x056d
        L_0x018b:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x019b
            long r12 = zze(r1, r12)
            r2.zzd(r14, r12)
            r15 = 0
            goto L_0x056d
        L_0x019b:
            r15 = 0
            goto L_0x056d
        L_0x019e:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01ae
            int r4 = zzd(r1, r12)
            r2.zzc(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x01ae:
            r15 = 0
            goto L_0x056d
        L_0x01b1:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01c1
            long r12 = zze(r1, r12)
            r2.zzc(r14, r12)
            r15 = 0
            goto L_0x056d
        L_0x01c1:
            r15 = 0
            goto L_0x056d
        L_0x01c4:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01d4
            long r12 = zze(r1, r12)
            r2.zza(r14, r12)
            r15 = 0
            goto L_0x056d
        L_0x01d4:
            r15 = 0
            goto L_0x056d
        L_0x01d7:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01e7
            float r4 = zzc(r1, r12)
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x056d
        L_0x01e7:
            r15 = 0
            goto L_0x056d
        L_0x01ea:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01fa
            double r12 = zzb((T) r1, r12)
            r2.zza(r14, r12)
            r15 = 0
            goto L_0x056d
        L_0x01fa:
            r15 = 0
            goto L_0x056d
        L_0x01fd:
            java.lang.Object r4 = r8.getObject(r1, r12)
            r0.zza(r2, r14, r4, r5)
            r15 = 0
            goto L_0x056d
        L_0x0207:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhd r12 = r0.zza(r5)
            com.google.android.gms.internal.measurement.zzhf.zzb(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x021e:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zze(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0231:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzj(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0244:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzg(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0257:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzl(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x026a:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzm(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x027d:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzi(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0290:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzn(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x02a3:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzk(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x02b6:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzf(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x02c9:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzh(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x02dc:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzd(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x02ef:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzc(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0302:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zzb(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0315:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.gms.internal.measurement.zzhf.zza(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0328:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zze(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x033b:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzj(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x034e:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzg(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0361:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzl(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0374:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzm(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0387:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzi(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x039a:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zzb(r4, r9, r2)
            r15 = 0
            goto L_0x056d
        L_0x03ac:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhd r12 = r0.zza(r5)
            com.google.android.gms.internal.measurement.zzhf.zza(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x03c3:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzhf.zza(r4, r9, r2)
            r15 = 0
            goto L_0x056d
        L_0x03d5:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzn(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x03e8:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzk(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x03fb:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzf(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x040e:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzh(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0421:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzd(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0434:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzc(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x0447:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.gms.internal.measurement.zzhf.zzb(r4, r9, r2, r12)
            r15 = 0
            goto L_0x056d
        L_0x045a:
            int[] r4 = r0.zzc
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.measurement.zzhf.zza(r4, r9, r2, r15)
            goto L_0x056d
        L_0x046c:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzhd r9 = r0.zza(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x056d
        L_0x047f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            long r12 = r8.getLong(r1, r12)
            r2.zze(r14, r12)
            goto L_0x056d
        L_0x048d:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            int r4 = r8.getInt(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x056d
        L_0x049b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            long r12 = r8.getLong(r1, r12)
            r2.zzb(r14, r12)
            goto L_0x056d
        L_0x04a9:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            int r4 = r8.getInt(r1, r12)
            r2.zza(r14, r4)
            goto L_0x056d
        L_0x04b7:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            int r4 = r8.getInt(r1, r12)
            r2.zzb(r14, r4)
            goto L_0x056d
        L_0x04c5:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            int r4 = r8.getInt(r1, r12)
            r2.zze(r14, r4)
            goto L_0x056d
        L_0x04d3:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzdu r4 = (com.google.android.gms.internal.measurement.zzdu) r4
            r2.zza(r14, r4)
            goto L_0x056d
        L_0x04e3:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzhd r9 = r0.zza(r5)
            r2.zza(r14, r4, r9)
            goto L_0x056d
        L_0x04f5:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            goto L_0x056d
        L_0x0503:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            boolean r4 = com.google.android.gms.internal.measurement.zzib.zzc(r1, r12)
            r2.zza(r14, r4)
            goto L_0x056d
        L_0x0511:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            int r4 = r8.getInt(r1, r12)
            r2.zzd(r14, r4)
            goto L_0x056d
        L_0x051e:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            long r12 = r8.getLong(r1, r12)
            r2.zzd(r14, r12)
            goto L_0x056d
        L_0x052b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            int r4 = r8.getInt(r1, r12)
            r2.zzc(r14, r4)
            goto L_0x056d
        L_0x0538:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            long r12 = r8.getLong(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x056d
        L_0x0545:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            long r12 = r8.getLong(r1, r12)
            r2.zza(r14, r12)
            goto L_0x056d
        L_0x0552:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            float r4 = com.google.android.gms.internal.measurement.zzib.zzd(r1, r12)
            r2.zza(r14, r4)
            goto L_0x056d
        L_0x0560:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x056d
            double r12 = com.google.android.gms.internal.measurement.zzib.zze(r1, r12)
            r2.zza(r14, r12)
        L_0x056d:
            int r5 = r5 + 3
            goto L_0x0031
        L_0x0571:
            r17 = r10
            r4 = r17
        L_0x0575:
            if (r4 == 0) goto L_0x058b
            com.google.android.gms.internal.measurement.zzes<?> r5 = r0.zzr
            r5.zza(r2, r4)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0589
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x0575
        L_0x0589:
            r4 = 0
            goto L_0x0575
        L_0x058b:
            com.google.android.gms.internal.measurement.zzhv<?, ?> r3 = r0.zzq
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zzb(java.lang.Object, com.google.android.gms.internal.measurement.zzis):void");
    }

    private final <K, V> void zza(zzis zzis, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzis.zza(i, this.zzs.zzf(zzb(i2)), this.zzs.zzb(obj));
        }
    }

    private static <UT, UB> void zza(zzhv<UT, UB> zzhv, T t, zzis zzis) throws IOException {
        zzhv.zza(zzhv.zzb(t), zzis);
    }

    public final void zza(T t, zzhe zzhe, zzeq zzeq) throws IOException {
        Object obj;
        zzew zzew;
        if (zzeq != null) {
            zzhv<?, ?> zzhv = this.zzq;
            zzes<?> zzes = this.zzr;
            zzew zzew2 = null;
            Object obj2 = null;
            while (true) {
                try {
                    int zza2 = zzhe.zza();
                    int zzg2 = zzg(zza2);
                    if (zzg2 >= 0) {
                        int zzd2 = zzd(zzg2);
                        switch ((267386880 & zzd2) >>> 20) {
                            case 0:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzd());
                                zzb(t, zzg2);
                                break;
                            case 1:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zze());
                                zzb(t, zzg2);
                                break;
                            case 2:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzg());
                                zzb(t, zzg2);
                                break;
                            case 3:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzf());
                                zzb(t, zzg2);
                                break;
                            case 4:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzh());
                                zzb(t, zzg2);
                                break;
                            case 5:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzi());
                                zzb(t, zzg2);
                                break;
                            case 6:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzj());
                                zzb(t, zzg2);
                                break;
                            case 7:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzk());
                                zzb(t, zzg2);
                                break;
                            case 8:
                                zza((Object) t, zzd2, zzhe);
                                zzb(t, zzg2);
                                break;
                            case 9:
                                if (!zza(t, zzg2)) {
                                    zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zza(zza(zzg2), zzeq));
                                    zzb(t, zzg2);
                                    break;
                                } else {
                                    long j = (long) (zzd2 & 1048575);
                                    zzib.zza((Object) t, j, zzff.zza(zzib.zzf(t, j), zzhe.zza(zza(zzg2), zzeq)));
                                    break;
                                }
                            case 10:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) zzhe.zzn());
                                zzb(t, zzg2);
                                break;
                            case 11:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzo());
                                zzb(t, zzg2);
                                break;
                            case 12:
                                int zzp2 = zzhe.zzp();
                                zzfk zzc2 = zzc(zzg2);
                                if (zzc2 != null) {
                                    if (!zzc2.zza(zzp2)) {
                                        obj2 = zzhf.zza(zza2, zzp2, obj2, zzhv);
                                        break;
                                    }
                                }
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzp2);
                                zzb(t, zzg2);
                                break;
                            case 13:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzq());
                                zzb(t, zzg2);
                                break;
                            case 14:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzr());
                                zzb(t, zzg2);
                                break;
                            case 15:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzs());
                                zzb(t, zzg2);
                                break;
                            case 16:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzt());
                                zzb(t, zzg2);
                                break;
                            case 17:
                                if (!zza(t, zzg2)) {
                                    zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzb(zza(zzg2), zzeq));
                                    zzb(t, zzg2);
                                    break;
                                } else {
                                    long j2 = (long) (zzd2 & 1048575);
                                    zzib.zza((Object) t, j2, zzff.zza(zzib.zzf(t, j2), zzhe.zzb(zza(zzg2), zzeq)));
                                    break;
                                }
                            case 18:
                                zzhe.zza(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 19:
                                zzhe.zzb(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 20:
                                zzhe.zzd(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 21:
                                zzhe.zzc(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 22:
                                zzhe.zze(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 23:
                                zzhe.zzf(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 24:
                                zzhe.zzg(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 25:
                                zzhe.zzh(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 26:
                                if (!zzf(zzd2)) {
                                    zzhe.zzi(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                    break;
                                } else {
                                    zzhe.zzj(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                    break;
                                }
                            case 27:
                                zzhe.zza(this.zzp.zza(t, (long) (zzd2 & 1048575)), zza(zzg2), zzeq);
                                break;
                            case 28:
                                zzhe.zzk(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 29:
                                zzhe.zzl(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 30:
                                List zza3 = this.zzp.zza(t, (long) (zzd2 & 1048575));
                                zzhe.zzm(zza3);
                                obj2 = zzhf.zza(zza2, zza3, zzc(zzg2), obj2, zzhv);
                                break;
                            case 31:
                                zzhe.zzn(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 32:
                                zzhe.zzo(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 33:
                                zzhe.zzp(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 34:
                                zzhe.zzq(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 35:
                                zzhe.zza(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 36:
                                zzhe.zzb(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 37:
                                zzhe.zzd(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 38:
                                zzhe.zzc(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 39:
                                zzhe.zze(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 40:
                                zzhe.zzf(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 41:
                                zzhe.zzg(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 42:
                                zzhe.zzh(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 43:
                                zzhe.zzl(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 44:
                                List zza4 = this.zzp.zza(t, (long) (zzd2 & 1048575));
                                zzhe.zzm(zza4);
                                obj2 = zzhf.zza(zza2, zza4, zzc(zzg2), obj2, zzhv);
                                break;
                            case 45:
                                zzhe.zzn(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 46:
                                zzhe.zzo(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 47:
                                zzhe.zzp(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 48:
                                zzhe.zzq(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                                break;
                            case 49:
                                long j3 = (long) (zzd2 & 1048575);
                                zzhe.zzb(this.zzp.zza(t, j3), zza(zzg2), zzeq);
                                break;
                            case 50:
                                Object zzb2 = zzb(zzg2);
                                long zzd3 = (long) (zzd(zzg2) & 1048575);
                                Object zzf2 = zzib.zzf(t, zzd3);
                                if (zzf2 == null) {
                                    zzf2 = this.zzs.zze(zzb2);
                                    zzib.zza((Object) t, zzd3, zzf2);
                                } else if (this.zzs.zzc(zzf2)) {
                                    Object zze2 = this.zzs.zze(zzb2);
                                    this.zzs.zza(zze2, zzf2);
                                    zzib.zza((Object) t, zzd3, zze2);
                                    zzf2 = zze2;
                                }
                                zzhe.zza(this.zzs.zza(zzf2), this.zzs.zzf(zzb2), zzeq);
                                break;
                            case 51:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Double.valueOf(zzhe.zzd()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 52:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Float.valueOf(zzhe.zze()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 53:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhe.zzg()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 54:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhe.zzf()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 55:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhe.zzh()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 56:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhe.zzi()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 57:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhe.zzj()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 58:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Boolean.valueOf(zzhe.zzk()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 59:
                                zza((Object) t, zzd2, zzhe);
                                zzb(t, zza2, zzg2);
                                break;
                            case 60:
                                if (zza(t, zza2, zzg2)) {
                                    long j4 = (long) (zzd2 & 1048575);
                                    zzib.zza((Object) t, j4, zzff.zza(zzib.zzf(t, j4), zzhe.zza(zza(zzg2), zzeq)));
                                } else {
                                    zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zza(zza(zzg2), zzeq));
                                    zzb(t, zzg2);
                                }
                                zzb(t, zza2, zzg2);
                                break;
                            case 61:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) zzhe.zzn());
                                zzb(t, zza2, zzg2);
                                break;
                            case 62:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhe.zzo()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 63:
                                int zzp3 = zzhe.zzp();
                                zzfk zzc3 = zzc(zzg2);
                                if (zzc3 != null) {
                                    if (!zzc3.zza(zzp3)) {
                                        obj2 = zzhf.zza(zza2, zzp3, obj2, zzhv);
                                        break;
                                    }
                                }
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzp3));
                                zzb(t, zza2, zzg2);
                                break;
                            case 64:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhe.zzq()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 65:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhe.zzr()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 66:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhe.zzs()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 67:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhe.zzt()));
                                zzb(t, zza2, zzg2);
                                break;
                            case 68:
                                zzib.zza((Object) t, (long) (zzd2 & 1048575), zzhe.zzb(zza(zzg2), zzeq));
                                zzb(t, zza2, zzg2);
                                break;
                            default:
                                if (obj2 == null) {
                                    obj2 = zzhv.zza();
                                }
                                if (zzhv.zza(obj2, zzhe)) {
                                    break;
                                } else {
                                    for (int i = this.zzm; i < this.zzn; i++) {
                                        obj2 = zza((Object) t, this.zzl[i], (UB) obj2, zzhv);
                                    }
                                    if (obj2 != null) {
                                        zzhv.zzb((Object) t, obj2);
                                    }
                                    return;
                                }
                        }
                    } else if (zza2 == Integer.MAX_VALUE) {
                        for (int i2 = this.zzm; i2 < this.zzn; i2++) {
                            obj2 = zza((Object) t, this.zzl[i2], (UB) obj2, zzhv);
                        }
                        if (obj2 != null) {
                            zzhv.zzb((Object) t, obj2);
                        }
                        return;
                    } else {
                        if (!this.zzh) {
                            obj = null;
                        } else {
                            obj = zzes.zza(zzeq, this.zzg, zza2);
                        }
                        if (obj != null) {
                            if (zzew2 == null) {
                                zzew = zzes.zzb(t);
                            } else {
                                zzew = zzew2;
                            }
                            obj2 = zzes.zza(zzhe, obj, zzeq, zzew, obj2, zzhv);
                            zzew2 = zzew;
                        } else {
                            zzhv.zza(zzhe);
                            if (obj2 == null) {
                                obj2 = zzhv.zzc(t);
                            }
                            if (!zzhv.zza(obj2, zzhe)) {
                                for (int i3 = this.zzm; i3 < this.zzn; i3++) {
                                    obj2 = zza((Object) t, this.zzl[i3], (UB) obj2, zzhv);
                                }
                                if (obj2 != null) {
                                    zzhv.zzb((Object) t, obj2);
                                }
                                return;
                            }
                        }
                    }
                } catch (zzfn e) {
                    zzhv.zza(zzhe);
                    if (obj2 == null) {
                        obj2 = zzhv.zzc(t);
                    }
                    if (!zzhv.zza(obj2, zzhe)) {
                        for (int i4 = this.zzm; i4 < this.zzn; i4++) {
                            obj2 = zza((Object) t, this.zzl[i4], (UB) obj2, zzhv);
                        }
                        if (obj2 != null) {
                            zzhv.zzb((Object) t, obj2);
                        }
                        return;
                    }
                } catch (Throwable th) {
                    for (int i5 = this.zzm; i5 < this.zzn; i5++) {
                        obj2 = zza((Object) t, this.zzl[i5], (UB) obj2, zzhv);
                    }
                    if (obj2 != null) {
                        zzhv.zzb((Object) t, obj2);
                    }
                    throw th;
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    private static zzhy zze(Object obj) {
        zzfd zzfd = (zzfd) obj;
        zzhy zzhy = zzfd.zzb;
        if (zzhy != zzhy.zza()) {
            return zzhy;
        }
        zzhy zzb2 = zzhy.zzb();
        zzfd.zzb = zzb2;
        return zzb2;
    }

    private static int zza(byte[] bArr, int i, int i2, zzim zzim, Class<?> cls, zzdt zzdt) throws IOException {
        switch (zzim) {
            case BOOL:
                int zzb2 = zzdq.zzb(bArr, i, zzdt);
                zzdt.zzc = Boolean.valueOf(zzdt.zzb != 0);
                return zzb2;
            case BYTES:
                return zzdq.zze(bArr, i, zzdt);
            case DOUBLE:
                zzdt.zzc = Double.valueOf(zzdq.zzc(bArr, i));
                return i + 8;
            case FIXED32:
            case SFIXED32:
                zzdt.zzc = Integer.valueOf(zzdq.zza(bArr, i));
                return i + 4;
            case FIXED64:
            case SFIXED64:
                zzdt.zzc = Long.valueOf(zzdq.zzb(bArr, i));
                return i + 8;
            case FLOAT:
                zzdt.zzc = Float.valueOf(zzdq.zzd(bArr, i));
                return i + 4;
            case ENUM:
            case INT32:
            case UINT32:
                int zza2 = zzdq.zza(bArr, i, zzdt);
                zzdt.zzc = Integer.valueOf(zzdt.zza);
                return zza2;
            case INT64:
            case UINT64:
                int zzb3 = zzdq.zzb(bArr, i, zzdt);
                zzdt.zzc = Long.valueOf(zzdt.zzb);
                return zzb3;
            case MESSAGE:
                return zzdq.zza(zzgz.zza().zza(cls), bArr, i, i2, zzdt);
            case SINT32:
                int zza3 = zzdq.zza(bArr, i, zzdt);
                zzdt.zzc = Integer.valueOf(zzeg.zze(zzdt.zza));
                return zza3;
            case SINT64:
                int zzb4 = zzdq.zzb(bArr, i, zzdt);
                zzdt.zzc = Long.valueOf(zzeg.zza(zzdt.zzb));
                return zzb4;
            case STRING:
                return zzdq.zzd(bArr, i, zzdt);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzdt zzdt) throws IOException {
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
        zzdt zzdt2 = zzdt;
        zzfl zzfl = (zzfl) zzb.getObject(t2, j3);
        if (!zzfl.zza()) {
            int size = zzfl.size();
            zzfl = zzfl.zza(size == 0 ? 10 : size << 1);
            zzb.putObject(t2, j3, zzfl);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    zzeo zzeo = (zzeo) zzfl;
                    int zza2 = zzdq.zza(bArr2, i12, zzdt2);
                    int i17 = zzdt2.zza + zza2;
                    while (zza2 < i17) {
                        zzeo.zza(zzdq.zzc(bArr2, zza2));
                        zza2 += 8;
                    }
                    if (zza2 == i17) {
                        return zza2;
                    }
                    throw zzfo.zza();
                } else if (i15 == 1) {
                    zzeo zzeo2 = (zzeo) zzfl;
                    zzeo2.zza(zzdq.zzc(bArr, i));
                    int i18 = i12 + 8;
                    while (i18 < i13) {
                        int zza3 = zzdq.zza(bArr2, i18, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return i18;
                        }
                        zzeo2.zza(zzdq.zzc(bArr2, zza3));
                        i18 = zza3 + 8;
                    }
                    return i18;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    zzfc zzfc = (zzfc) zzfl;
                    int zza4 = zzdq.zza(bArr2, i12, zzdt2);
                    int i19 = zzdt2.zza + zza4;
                    while (zza4 < i19) {
                        zzfc.zza(zzdq.zzd(bArr2, zza4));
                        zza4 += 4;
                    }
                    if (zza4 == i19) {
                        return zza4;
                    }
                    throw zzfo.zza();
                } else if (i15 == 5) {
                    zzfc zzfc2 = (zzfc) zzfl;
                    zzfc2.zza(zzdq.zzd(bArr, i));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zza5 = zzdq.zza(bArr2, i20, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return i20;
                        }
                        zzfc2.zza(zzdq.zzd(bArr2, zza5));
                        i20 = zza5 + 4;
                    }
                    return i20;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i15 == 2) {
                    zzgc zzgc = (zzgc) zzfl;
                    int zza6 = zzdq.zza(bArr2, i12, zzdt2);
                    int i21 = zzdt2.zza + zza6;
                    while (zza6 < i21) {
                        zza6 = zzdq.zzb(bArr2, zza6, zzdt2);
                        zzgc.zza(zzdt2.zzb);
                    }
                    if (zza6 == i21) {
                        return zza6;
                    }
                    throw zzfo.zza();
                } else if (i15 == 0) {
                    zzgc zzgc2 = (zzgc) zzfl;
                    int zzb2 = zzdq.zzb(bArr2, i12, zzdt2);
                    zzgc2.zza(zzdt2.zzb);
                    while (zzb2 < i13) {
                        int zza7 = zzdq.zza(bArr2, zzb2, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return zzb2;
                        }
                        zzb2 = zzdq.zzb(bArr2, zza7, zzdt2);
                        zzgc2.zza(zzdt2.zzb);
                    }
                    return zzb2;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzdq.zza(bArr2, i12, zzfl, zzdt2);
                }
                if (i15 == 0) {
                    return zzdq.zza(i3, bArr, i, i2, zzfl, zzdt);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    zzgc zzgc3 = (zzgc) zzfl;
                    int zza8 = zzdq.zza(bArr2, i12, zzdt2);
                    int i22 = zzdt2.zza + zza8;
                    while (zza8 < i22) {
                        zzgc3.zza(zzdq.zzb(bArr2, zza8));
                        zza8 += 8;
                    }
                    if (zza8 == i22) {
                        return zza8;
                    }
                    throw zzfo.zza();
                } else if (i15 == 1) {
                    zzgc zzgc4 = (zzgc) zzfl;
                    zzgc4.zza(zzdq.zzb(bArr, i));
                    int i23 = i12 + 8;
                    while (i23 < i13) {
                        int zza9 = zzdq.zza(bArr2, i23, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return i23;
                        }
                        zzgc4.zza(zzdq.zzb(bArr2, zza9));
                        i23 = zza9 + 8;
                    }
                    return i23;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i15 == 2) {
                    zzfg zzfg = (zzfg) zzfl;
                    int zza10 = zzdq.zza(bArr2, i12, zzdt2);
                    int i24 = zzdt2.zza + zza10;
                    while (zza10 < i24) {
                        zzfg.zzd(zzdq.zza(bArr2, zza10));
                        zza10 += 4;
                    }
                    if (zza10 == i24) {
                        return zza10;
                    }
                    throw zzfo.zza();
                } else if (i15 == 5) {
                    zzfg zzfg2 = (zzfg) zzfl;
                    zzfg2.zzd(zzdq.zza(bArr, i));
                    int i25 = i12 + 4;
                    while (i25 < i13) {
                        int zza11 = zzdq.zza(bArr2, i25, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return i25;
                        }
                        zzfg2.zzd(zzdq.zza(bArr2, zza11));
                        i25 = zza11 + 4;
                    }
                    return i25;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    zzds zzds = (zzds) zzfl;
                    int zza12 = zzdq.zza(bArr2, i12, zzdt2);
                    int i26 = zzdt2.zza + zza12;
                    while (zza12 < i26) {
                        zza12 = zzdq.zzb(bArr2, zza12, zzdt2);
                        zzds.zza(zzdt2.zzb != 0);
                    }
                    if (zza12 == i26) {
                        return zza12;
                    }
                    throw zzfo.zza();
                } else if (i15 == 0) {
                    zzds zzds2 = (zzds) zzfl;
                    int zzb3 = zzdq.zzb(bArr2, i12, zzdt2);
                    zzds2.zza(zzdt2.zzb != 0);
                    while (zzb3 < i13) {
                        int zza13 = zzdq.zza(bArr2, zzb3, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return zzb3;
                        }
                        zzb3 = zzdq.zzb(bArr2, zza13, zzdt2);
                        zzds2.zza(zzdt2.zzb != 0);
                    }
                    return zzb3;
                }
                break;
            case 26:
                if (i15 == 2) {
                    String str = "";
                    if ((j & 536870912) == 0) {
                        int zza14 = zzdq.zza(bArr2, i12, zzdt2);
                        int i27 = zzdt2.zza;
                        if (i27 >= 0) {
                            if (i27 == 0) {
                                zzfl.add(str);
                            } else {
                                zzfl.add(new String(bArr2, zza14, i27, zzff.zza));
                                zza14 += i27;
                            }
                            while (i9 < i13) {
                                int zza15 = zzdq.zza(bArr2, i9, zzdt2);
                                if (i14 != zzdt2.zza) {
                                    return i9;
                                }
                                i9 = zzdq.zza(bArr2, zza15, zzdt2);
                                int i28 = zzdt2.zza;
                                if (i28 < 0) {
                                    throw zzfo.zzb();
                                } else if (i28 == 0) {
                                    zzfl.add(str);
                                } else {
                                    zzfl.add(new String(bArr2, i9, i28, zzff.zza));
                                    i9 += i28;
                                }
                            }
                            return i9;
                        }
                        throw zzfo.zzb();
                    }
                    int zza16 = zzdq.zza(bArr2, i12, zzdt2);
                    int i29 = zzdt2.zza;
                    if (i29 >= 0) {
                        if (i29 == 0) {
                            zzfl.add(str);
                        } else {
                            int i30 = zza16 + i29;
                            if (zzie.zza(bArr2, zza16, i30)) {
                                zzfl.add(new String(bArr2, zza16, i29, zzff.zza));
                                zza16 = i30;
                            } else {
                                throw zzfo.zzh();
                            }
                        }
                        while (i8 < i13) {
                            int zza17 = zzdq.zza(bArr2, i8, zzdt2);
                            if (i14 != zzdt2.zza) {
                                return i8;
                            }
                            i8 = zzdq.zza(bArr2, zza17, zzdt2);
                            int i31 = zzdt2.zza;
                            if (i31 < 0) {
                                throw zzfo.zzb();
                            } else if (i31 == 0) {
                                zzfl.add(str);
                            } else {
                                int i32 = i8 + i31;
                                if (zzie.zza(bArr2, i8, i32)) {
                                    zzfl.add(new String(bArr2, i8, i31, zzff.zza));
                                    i8 = i32;
                                } else {
                                    throw zzfo.zzh();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzfo.zzb();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zzdq.zza(zza(i16), i3, bArr, i, i2, zzfl, zzdt);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zza18 = zzdq.zza(bArr2, i12, zzdt2);
                    int i33 = zzdt2.zza;
                    if (i33 < 0) {
                        throw zzfo.zzb();
                    } else if (i33 <= bArr2.length - zza18) {
                        if (i33 == 0) {
                            zzfl.add(zzdu.zza);
                        } else {
                            zzfl.add(zzdu.zza(bArr2, zza18, i33));
                            zza18 += i33;
                        }
                        while (i10 < i13) {
                            int zza19 = zzdq.zza(bArr2, i10, zzdt2);
                            if (i14 != zzdt2.zza) {
                                return i10;
                            }
                            i10 = zzdq.zza(bArr2, zza19, zzdt2);
                            int i34 = zzdt2.zza;
                            if (i34 < 0) {
                                throw zzfo.zzb();
                            } else if (i34 > bArr2.length - i10) {
                                throw zzfo.zza();
                            } else if (i34 == 0) {
                                zzfl.add(zzdu.zza);
                            } else {
                                zzfl.add(zzdu.zza(bArr2, i10, i34));
                                i10 += i34;
                            }
                        }
                        return i10;
                    } else {
                        throw zzfo.zza();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzdq.zza(bArr2, i12, zzfl, zzdt2);
                } else if (i15 == 0) {
                    i11 = zzdq.zza(i3, bArr, i, i2, zzfl, zzdt);
                }
                zzfd zzfd = (zzfd) t2;
                zzhy zzhy = zzfd.zzb;
                if (zzhy == zzhy.zza()) {
                    zzhy = null;
                }
                zzhy zzhy2 = (zzhy) zzhf.zza(i4, zzfl, zzc(i16), zzhy, this.zzq);
                if (zzhy2 != null) {
                    zzfd.zzb = zzhy2;
                }
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    zzfg zzfg3 = (zzfg) zzfl;
                    int zza20 = zzdq.zza(bArr2, i12, zzdt2);
                    int i35 = zzdt2.zza + zza20;
                    while (zza20 < i35) {
                        zza20 = zzdq.zza(bArr2, zza20, zzdt2);
                        zzfg3.zzd(zzeg.zze(zzdt2.zza));
                    }
                    if (zza20 == i35) {
                        return zza20;
                    }
                    throw zzfo.zza();
                } else if (i15 == 0) {
                    zzfg zzfg4 = (zzfg) zzfl;
                    int zza21 = zzdq.zza(bArr2, i12, zzdt2);
                    zzfg4.zzd(zzeg.zze(zzdt2.zza));
                    while (zza21 < i13) {
                        int zza22 = zzdq.zza(bArr2, zza21, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return zza21;
                        }
                        zza21 = zzdq.zza(bArr2, zza22, zzdt2);
                        zzfg4.zzd(zzeg.zze(zzdt2.zza));
                    }
                    return zza21;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    zzgc zzgc5 = (zzgc) zzfl;
                    int zza23 = zzdq.zza(bArr2, i12, zzdt2);
                    int i36 = zzdt2.zza + zza23;
                    while (zza23 < i36) {
                        zza23 = zzdq.zzb(bArr2, zza23, zzdt2);
                        zzgc5.zza(zzeg.zza(zzdt2.zzb));
                    }
                    if (zza23 == i36) {
                        return zza23;
                    }
                    throw zzfo.zza();
                } else if (i15 == 0) {
                    zzgc zzgc6 = (zzgc) zzfl;
                    int zzb4 = zzdq.zzb(bArr2, i12, zzdt2);
                    zzgc6.zza(zzeg.zza(zzdt2.zzb));
                    while (zzb4 < i13) {
                        int zza24 = zzdq.zza(bArr2, zzb4, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return zzb4;
                        }
                        zzb4 = zzdq.zzb(bArr2, zza24, zzdt2);
                        zzgc6.zza(zzeg.zza(zzdt2.zzb));
                    }
                    return zzb4;
                }
                break;
            case 49:
                if (i15 == 3) {
                    zzhd zza25 = zza(i16);
                    int i37 = (i14 & -8) | 4;
                    int zza26 = zzdq.zza(zza25, bArr, i, i2, i37, zzdt);
                    zzfl.add(zzdt2.zzc);
                    while (zza26 < i13) {
                        int zza27 = zzdq.zza(bArr2, zza26, zzdt2);
                        if (i14 != zzdt2.zza) {
                            return zza26;
                        }
                        zza26 = zzdq.zza(zza25, bArr, zza27, i2, i37, zzdt);
                        zzfl.add(zzdt2.zzc);
                    }
                    return zza26;
                }
                break;
        }
        return i12;
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzdt zzdt) throws IOException {
        int i4;
        int i5;
        Unsafe unsafe = zzb;
        Object zzb2 = zzb(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzs.zzc(object)) {
            Object zze2 = this.zzs.zze(zzb2);
            this.zzs.zza(zze2, object);
            unsafe.putObject(t, j, zze2);
            object = zze2;
        }
        zzgf zzf2 = this.zzs.zzf(zzb2);
        Map zza2 = this.zzs.zza(object);
        int zza3 = zzdq.zza(bArr, i, zzdt);
        int i6 = zzdt.zza;
        if (i6 < 0 || i6 > i2 - zza3) {
            throw zzfo.zza();
        }
        int i7 = i6 + zza3;
        K k = zzf2.zzb;
        V v = zzf2.zzd;
        while (zza3 < i7) {
            int i8 = zza3 + 1;
            byte b = bArr[zza3];
            if (b < 0) {
                i5 = zzdq.zza((int) b, bArr, i8, zzdt);
                i4 = zzdt.zza;
            } else {
                i5 = i8;
                i4 = b;
            }
            int i9 = i4 >>> 3;
            int i10 = i4 & 7;
            if (i9 != 1) {
                if (i9 == 2 && i10 == zzf2.zzc.zzb()) {
                    zza3 = zza(bArr, i5, i2, zzf2.zzc, zzf2.zzd.getClass(), zzdt);
                    v = zzdt.zzc;
                }
            } else if (i10 == zzf2.zza.zzb()) {
                zza3 = zza(bArr, i5, i2, zzf2.zza, null, zzdt);
                k = zzdt.zzc;
            }
            zza3 = zzdq.zza(i4, bArr, i5, i2, zzdt);
        }
        if (zza3 == i7) {
            zza2.put(k, v);
            return i7;
        }
        throw zzfo.zzg();
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
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.measurement.zzdt r29) throws java.io.IOException {
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
            sun.misc.Unsafe r12 = zzb
            int[] r7 = r0.zzc
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
            com.google.android.gms.internal.measurement.zzhd r2 = r0.zza(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = com.google.android.gms.internal.measurement.zzdq.zza(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004c
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004d
        L_0x004c:
            r15 = 0
        L_0x004d:
            if (r15 != 0) goto L_0x0056
            java.lang.Object r3 = r11.zzc
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0056:
            java.lang.Object r3 = r11.zzc
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzff.zza(r15, r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0061:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zzb(r3, r4, r11)
            long r3 = r11.zzb
            long r3 = com.google.android.gms.internal.measurement.zzeg.zza(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0076:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zza(r3, r4, r11)
            int r3 = r11.zza
            int r3 = com.google.android.gms.internal.measurement.zzeg.zze(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x008b:
            if (r5 != 0) goto L_0x01a8
            int r3 = com.google.android.gms.internal.measurement.zzdq.zza(r3, r4, r11)
            int r4 = r11.zza
            com.google.android.gms.internal.measurement.zzfk r5 = r0.zzc(r6)
            if (r5 == 0) goto L_0x00af
            boolean r5 = r5.zza(r4)
            if (r5 == 0) goto L_0x00a0
            goto L_0x00af
        L_0x00a0:
            com.google.android.gms.internal.measurement.zzhy r1 = zze(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zza(r2, r4)
            r2 = r3
            goto L_0x01a9
        L_0x00af:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x01a4
        L_0x00b9:
            if (r5 != r15) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zze(r3, r4, r11)
            java.lang.Object r3 = r11.zzc
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x00c6:
            if (r5 != r15) goto L_0x01a8
            com.google.android.gms.internal.measurement.zzhd r2 = r0.zza(r6)
            r5 = r20
            int r2 = com.google.android.gms.internal.measurement.zzdq.zza(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00de
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00df
        L_0x00de:
            r15 = 0
        L_0x00df:
            if (r15 != 0) goto L_0x00e7
            java.lang.Object r3 = r11.zzc
            r12.putObject(r1, r9, r3)
            goto L_0x00f0
        L_0x00e7:
            java.lang.Object r3 = r11.zzc
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzff.zza(r15, r3)
            r12.putObject(r1, r9, r3)
        L_0x00f0:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x00f5:
            if (r5 != r15) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zza(r3, r4, r11)
            int r4 = r11.zza
            if (r4 != 0) goto L_0x0105
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0124
        L_0x0105:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0119
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.measurement.zzie.zza(r3, r2, r5)
            if (r5 == 0) goto L_0x0114
            goto L_0x0119
        L_0x0114:
            com.google.android.gms.internal.measurement.zzfo r1 = com.google.android.gms.internal.measurement.zzfo.zzh()
            throw r1
        L_0x0119:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.measurement.zzff.zza
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0124:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x0129:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zzb(r3, r4, r11)
            long r3 = r11.zzb
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
            int r2 = com.google.android.gms.internal.measurement.zzdq.zza(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a4
        L_0x0152:
            r2 = 1
            if (r5 != r2) goto L_0x01a8
            long r2 = com.google.android.gms.internal.measurement.zzdq.zzb(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
            goto L_0x01a4
        L_0x0163:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zza(r3, r4, r11)
            int r3 = r11.zza
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0173:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.measurement.zzdq.zzb(r3, r4, r11)
            long r3 = r11.zzb
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0183:
            if (r5 != r7) goto L_0x01a8
            float r2 = com.google.android.gms.internal.measurement.zzdq.zzd(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a4
        L_0x0193:
            r2 = 1
            if (r5 != r2) goto L_0x01a8
            double r2 = com.google.android.gms.internal.measurement.zzdq.zzc(r18, r19)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.measurement.zzdt):int");
    }

    private final zzhd zza(int i) {
        int i2 = (i / 3) << 1;
        zzhd zzhd = (zzhd) this.zzd[i2];
        if (zzhd != null) {
            return zzhd;
        }
        zzhd zza2 = zzgz.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zza2;
        return zza2;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzfk zzc(int i) {
        return (zzfk) this.zzd[((i / 3) << 1) + 1];
    }

    /* JADX WARNING: type inference failed for: r31v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r5v0, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r25v0 */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r0v9, types: [int] */
    /* JADX WARNING: type inference failed for: r1v12, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r0v18, types: [int] */
    /* JADX WARNING: type inference failed for: r1v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r19v1 */
    /* JADX WARNING: type inference failed for: r8v5 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r25v1 */
    /* JADX WARNING: type inference failed for: r25v2 */
    /* JADX WARNING: type inference failed for: r2v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v7, types: [int] */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r2v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r2v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v10, types: [int] */
    /* JADX WARNING: type inference failed for: r25v3 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r25v4 */
    /* JADX WARNING: type inference failed for: r1v30, types: [int] */
    /* JADX WARNING: type inference failed for: r2v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r25v5 */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r12v15, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r12v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: type inference failed for: r12v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r8v15 */
    /* JADX WARNING: type inference failed for: r12v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* JADX WARNING: type inference failed for: r8v16 */
    /* JADX WARNING: type inference failed for: r12v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v25 */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: type inference failed for: r12v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r8v18 */
    /* JADX WARNING: type inference failed for: r12v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v27 */
    /* JADX WARNING: type inference failed for: r8v19 */
    /* JADX WARNING: type inference failed for: r12v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v28 */
    /* JADX WARNING: type inference failed for: r8v20 */
    /* JADX WARNING: type inference failed for: r12v23, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v29 */
    /* JADX WARNING: type inference failed for: r8v21 */
    /* JADX WARNING: type inference failed for: r12v24, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v30 */
    /* JADX WARNING: type inference failed for: r8v22, types: [int] */
    /* JADX WARNING: type inference failed for: r12v25, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v31 */
    /* JADX WARNING: type inference failed for: r3v33 */
    /* JADX WARNING: type inference failed for: r8v23 */
    /* JADX WARNING: type inference failed for: r12v26, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v34 */
    /* JADX WARNING: type inference failed for: r8v24 */
    /* JADX WARNING: type inference failed for: r12v27 */
    /* JADX WARNING: type inference failed for: r12v28, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v35 */
    /* JADX WARNING: type inference failed for: r8v25 */
    /* JADX WARNING: type inference failed for: r12v29 */
    /* JADX WARNING: type inference failed for: r1v73, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v27 */
    /* JADX WARNING: type inference failed for: r12v30 */
    /* JADX WARNING: type inference failed for: r3v37 */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r8v28 */
    /* JADX WARNING: type inference failed for: r25v6 */
    /* JADX WARNING: type inference failed for: r5v28 */
    /* JADX WARNING: type inference failed for: r3v38, types: [int] */
    /* JADX WARNING: type inference failed for: r5v29 */
    /* JADX WARNING: type inference failed for: r12v32 */
    /* JADX WARNING: type inference failed for: r3v39 */
    /* JADX WARNING: type inference failed for: r9v48 */
    /* JADX WARNING: type inference failed for: r25v7 */
    /* JADX WARNING: type inference failed for: r25v8 */
    /* JADX WARNING: type inference failed for: r25v9 */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r8v30 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r8v31 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r8v32 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r8v33 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r8v34 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r8v35 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r8v36 */
    /* JADX WARNING: type inference failed for: r12v43 */
    /* JADX WARNING: type inference failed for: r12v44 */
    /* JADX WARNING: type inference failed for: r8v37 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: type inference failed for: r8v38 */
    /* JADX WARNING: type inference failed for: r12v46 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v5, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r31v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 708
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
    /* JADX WARNING: Unknown variable types count: 52 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.measurement.zzdt r35) throws java.io.IOException {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r0 = r32
            r1 = -1
            r2 = 0
            r3 = 0
            r6 = 0
            r7 = -1
        L_0x001b:
            if (r0 >= r13) goto L_0x0560
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002c
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r12, r3, r9)
            int r3 = r9.zza
            r4 = r0
            r5 = r3
            goto L_0x002e
        L_0x002c:
            r5 = r0
            r4 = r3
        L_0x002e:
            int r3 = r5 >>> 3
            r0 = r5 & 7
            r8 = 3
            if (r3 <= r1) goto L_0x003c
            int r2 = r2 / r8
            int r1 = r15.zza(r3, r2)
            r2 = r1
            goto L_0x0041
        L_0x003c:
            int r1 = r15.zzg(r3)
            r2 = r1
        L_0x0041:
            r1 = -1
            if (r2 != r1) goto L_0x0050
            r24 = r3
            r2 = r4
            r25 = r5
            r26 = r10
            r18 = 0
            goto L_0x04b6
        L_0x0050:
            int[] r1 = r15.zzc
            int r18 = r2 + 1
            r8 = r1[r18]
            r18 = 267386880(0xff00000, float:2.3665827E-29)
            r18 = r8 & r18
            int r11 = r18 >>> 20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r5
            r5 = r8 & r18
            long r12 = (long) r5
            r5 = 17
            r20 = r8
            if (r11 > r5) goto L_0x0380
            int r5 = r2 + 2
            r1 = r1[r5]
            int r5 = r1 >>> 20
            r8 = 1
            int r22 = r8 << r5
            r1 = r1 & r18
            if (r1 == r7) goto L_0x008a
            r5 = -1
            if (r7 == r5) goto L_0x0082
            long r8 = (long) r7
            r10.putInt(r14, r8, r6)
        L_0x0082:
            long r6 = (long) r1
            int r6 = r10.getInt(r14, r6)
            r7 = r1
            goto L_0x008b
        L_0x008a:
            r5 = -1
        L_0x008b:
            r1 = 5
            switch(r11) {
                case 0: goto L_0x034b;
                case 1: goto L_0x0324;
                case 2: goto L_0x02f9;
                case 3: goto L_0x02f9;
                case 4: goto L_0x02d2;
                case 5: goto L_0x029e;
                case 6: goto L_0x0276;
                case 7: goto L_0x0241;
                case 8: goto L_0x020d;
                case 9: goto L_0x01ca;
                case 10: goto L_0x01a2;
                case 11: goto L_0x02d2;
                case 12: goto L_0x0158;
                case 13: goto L_0x0276;
                case 14: goto L_0x029e;
                case 15: goto L_0x012d;
                case 16: goto L_0x00f5;
                case 17: goto L_0x009e;
                default: goto L_0x008f;
            }
        L_0x008f:
            r12 = r31
            r13 = r35
            r9 = r2
            r11 = r3
            r32 = r7
            r8 = r19
            r18 = -1
            r7 = r4
            goto L_0x0373
        L_0x009e:
            r1 = 3
            if (r0 != r1) goto L_0x00e5
            int r0 = r3 << 3
            r8 = r0 | 4
            com.google.android.gms.internal.measurement.zzhd r0 = r15.zza(r2)
            r1 = r31
            r9 = r2
            r2 = r4
            r11 = r3
            r3 = r33
            r4 = r8
            r8 = r19
            r18 = -1
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00c8
            r5 = r35
            java.lang.Object r1 = r5.zzc
            r10.putObject(r14, r12, r1)
            goto L_0x00d7
        L_0x00c8:
            r5 = r35
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r5.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzff.zza(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x00d7:
            r6 = r6 | r22
            r12 = r31
            r13 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r11 = r34
            r9 = r5
            goto L_0x001b
        L_0x00e5:
            r5 = r35
            r9 = r2
            r11 = r3
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r5
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x00f5:
            r5 = r35
            r9 = r2
            r11 = r3
            r8 = r19
            r18 = -1
            if (r0 != 0) goto L_0x0125
            r2 = r12
            r12 = r31
            int r13 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r4, r5)
            long r0 = r5.zzb
            long r19 = com.google.android.gms.internal.measurement.zzeg.zza(r0)
            r0 = r10
            r1 = r30
            r32 = r13
            r13 = r5
            r4 = r19
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x0125:
            r12 = r31
            r13 = r5
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x012d:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != 0) goto L_0x0153
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r4, r13)
            int r1 = r13.zza
            int r1 = com.google.android.gms.internal.measurement.zzeg.zze(r1)
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x0153:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x0158:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != 0) goto L_0x019d
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r4, r13)
            int r1 = r13.zza
            com.google.android.gms.internal.measurement.zzfk r4 = r15.zzc(r9)
            if (r4 == 0) goto L_0x018e
            boolean r4 = r4.zza(r1)
            if (r4 == 0) goto L_0x0178
            goto L_0x018e
        L_0x0178:
            com.google.android.gms.internal.measurement.zzhy r2 = zze(r30)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zza(r8, r1)
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x018e:
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x019d:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x01a2:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r1 = 2
            if (r0 != r1) goto L_0x01c5
            int r0 = com.google.android.gms.internal.measurement.zzdq.zze(r12, r4, r13)
            java.lang.Object r1 = r13.zzc
            r10.putObject(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x01c5:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x01ca:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r1 = 2
            if (r0 != r1) goto L_0x0206
            com.google.android.gms.internal.measurement.zzhd r0 = r15.zza(r9)
            r5 = r33
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r12, r4, r5, r13)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x01ed
            java.lang.Object r1 = r13.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x01fb
        L_0x01ed:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r13.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzff.zza(r1, r4)
            r10.putObject(r14, r2, r1)
        L_0x01fb:
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r5
            goto L_0x001b
        L_0x0206:
            r5 = r33
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x020d:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r1 = 2
            if (r0 != r1) goto L_0x023c
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x0228
            int r0 = com.google.android.gms.internal.measurement.zzdq.zzc(r12, r4, r13)
            goto L_0x022c
        L_0x0228:
            int r0 = com.google.android.gms.internal.measurement.zzdq.zzd(r12, r4, r13)
        L_0x022c:
            java.lang.Object r1 = r13.zzc
            r10.putObject(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r5
            goto L_0x001b
        L_0x023c:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x0241:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != 0) goto L_0x0271
            int r0 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r4, r13)
            r32 = r0
            long r0 = r13.zzb
            r19 = 0
            int r4 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x0260
            r0 = 1
            goto L_0x0261
        L_0x0260:
            r0 = 0
        L_0x0261:
            com.google.android.gms.internal.measurement.zzib.zza(r14, r2, r0)
            r6 = r6 | r22
            r0 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r5
            goto L_0x001b
        L_0x0271:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x0276:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != r1) goto L_0x0299
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r4)
            r10.putInt(r14, r2, r0)
            int r0 = r4 + 4
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r5
            goto L_0x001b
        L_0x0299:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x029e:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r1 = 1
            if (r0 != r1) goto L_0x02cd
            long r19 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r4)
            r0 = r10
            r1 = r30
            r32 = r7
            r7 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            int r0 = r7 + 8
            r6 = r6 | r22
            r7 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x02cd:
            r32 = r7
            r7 = r4
            goto L_0x0373
        L_0x02d2:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != 0) goto L_0x0373
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r7, r13)
            int r1 = r13.zza
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r7 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x02f9:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != 0) goto L_0x0373
            int r7 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r7, r13)
            long r4 = r13.zzb
            r0 = r10
            r1 = r30
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r7
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r7 = r32
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x0324:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != r1) goto L_0x0373
            float r0 = com.google.android.gms.internal.measurement.zzdq.zzd(r12, r7)
            com.google.android.gms.internal.measurement.zzib.zza(r14, r2, r0)
            int r0 = r7 + 4
            r6 = r6 | r22
            r7 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x034b:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            r1 = 1
            if (r0 != r1) goto L_0x0373
            double r0 = com.google.android.gms.internal.measurement.zzdq.zzc(r12, r7)
            com.google.android.gms.internal.measurement.zzib.zza(r14, r2, r0)
            int r0 = r7 + 8
            r6 = r6 | r22
            r7 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r33
            r11 = r34
            goto L_0x001b
        L_0x0373:
            r2 = r7
            r25 = r8
            r18 = r9
            r26 = r10
            r24 = r11
            r7 = r32
            goto L_0x04b6
        L_0x0380:
            r5 = r3
            r17 = r7
            r8 = r19
            r18 = -1
            r7 = r4
            r27 = r12
            r12 = r31
            r13 = r9
            r9 = r2
            r2 = r27
            r1 = 27
            if (r11 != r1) goto L_0x03ea
            r1 = 2
            if (r0 != r1) goto L_0x03dc
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.measurement.zzfl r0 = (com.google.android.gms.internal.measurement.zzfl) r0
            boolean r1 = r0.zza()
            if (r1 != 0) goto L_0x03b8
            int r1 = r0.size()
            if (r1 != 0) goto L_0x03ad
            r1 = 10
            goto L_0x03af
        L_0x03ad:
            int r1 = r1 << 1
        L_0x03af:
            com.google.android.gms.internal.measurement.zzfl r0 = r0.zza(r1)
            r10.putObject(r14, r2, r0)
            r11 = r0
            goto L_0x03b9
        L_0x03b8:
            r11 = r0
        L_0x03b9:
            com.google.android.gms.internal.measurement.zzhd r0 = r15.zza(r9)
            r1 = r8
            r2 = r31
            r3 = r7
            r4 = r33
            r7 = r5
            r5 = r11
            r19 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r1, r2, r3, r4, r5, r6)
            r11 = r34
            r1 = r7
            r3 = r8
            r2 = r9
            r9 = r13
            r7 = r17
            r6 = r19
            r13 = r33
            goto L_0x001b
        L_0x03dc:
            r19 = r6
            r6 = r5
            r24 = r6
            r15 = r7
            r25 = r8
            r18 = r9
            r26 = r10
            goto L_0x048d
        L_0x03ea:
            r19 = r6
            r6 = r5
            r1 = 49
            if (r11 > r1) goto L_0x043f
            r5 = r20
            long r4 = (long) r5
            r1 = r0
            r0 = r29
            r32 = r1
            r1 = r30
            r22 = r2
            r2 = r31
            r3 = r7
            r20 = r4
            r4 = r33
            r5 = r8
            r24 = r6
            r15 = r7
            r7 = r32
            r25 = r8
            r8 = r9
            r18 = r9
            r26 = r10
            r9 = r20
            r12 = r22
            r14 = r35
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x0425
            r2 = r0
            r7 = r17
            r6 = r19
            goto L_0x04b6
        L_0x0425:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            r7 = r17
            r2 = r18
            r6 = r19
            r1 = r24
            r3 = r25
            r10 = r26
            goto L_0x001b
        L_0x043f:
            r32 = r0
            r22 = r2
            r24 = r6
            r15 = r7
            r25 = r8
            r18 = r9
            r26 = r10
            r5 = r20
            r0 = 50
            if (r11 != r0) goto L_0x0493
            r7 = r32
            r0 = 2
            if (r7 != r0) goto L_0x048d
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r5 = r18
            r6 = r22
            r8 = r35
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0473
            r2 = r0
            r7 = r17
            r6 = r19
            goto L_0x04b6
        L_0x0473:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            r7 = r17
            r2 = r18
            r6 = r19
            r1 = r24
            r3 = r25
            r10 = r26
            goto L_0x001b
        L_0x048d:
            r2 = r15
            r7 = r17
            r6 = r19
            goto L_0x04b6
        L_0x0493:
            r7 = r32
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r8 = r5
            r5 = r25
            r6 = r24
            r9 = r11
            r10 = r22
            r12 = r18
            r13 = r35
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x0540
            r2 = r0
            r7 = r17
            r6 = r19
        L_0x04b6:
            r8 = r34
            r9 = r25
            if (r9 != r8) goto L_0x04c6
            if (r8 != 0) goto L_0x04bf
            goto L_0x04c6
        L_0x04bf:
            r10 = r29
            r13 = r30
            r0 = r2
            goto L_0x056a
        L_0x04c6:
            r10 = r29
            boolean r0 = r10.zzh
            if (r0 == 0) goto L_0x051a
            r11 = r35
            com.google.android.gms.internal.measurement.zzeq r0 = r11.zzd
            com.google.android.gms.internal.measurement.zzeq r1 = com.google.android.gms.internal.measurement.zzeq.zza()
            if (r0 == r1) goto L_0x0515
            com.google.android.gms.internal.measurement.zzgo r0 = r10.zzg
            com.google.android.gms.internal.measurement.zzeq r1 = r11.zzd
            r12 = r24
            com.google.android.gms.internal.measurement.zzfd$zzf r0 = r1.zza(r0, r12)
            if (r0 != 0) goto L_0x0504
            com.google.android.gms.internal.measurement.zzhy r4 = zze(r30)
            r0 = r9
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r1, r2, r3, r4, r5)
            r14 = r30
            r13 = r33
            r3 = r9
            r15 = r10
            r9 = r11
            r1 = r12
            r2 = r18
            r10 = r26
            r12 = r31
            r11 = r8
            goto L_0x001b
        L_0x0504:
            r13 = r30
            r0 = r13
            com.google.android.gms.internal.measurement.zzfd$zzd r0 = (com.google.android.gms.internal.measurement.zzfd.zzd) r0
            r0.zza()
            com.google.android.gms.internal.measurement.zzew<com.google.android.gms.internal.measurement.zzfd$zzc> r0 = r0.zzc
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0515:
            r13 = r30
            r12 = r24
            goto L_0x0520
        L_0x051a:
            r13 = r30
            r11 = r35
            r12 = r24
        L_0x0520:
            com.google.android.gms.internal.measurement.zzhy r4 = zze(r30)
            r0 = r9
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r1, r2, r3, r4, r5)
            r3 = r9
            r15 = r10
            r9 = r11
            r1 = r12
            r14 = r13
            r2 = r18
            r10 = r26
            r12 = r31
            r13 = r33
            r11 = r8
            goto L_0x001b
        L_0x0540:
            r10 = r29
            r13 = r30
            r8 = r34
            r11 = r35
            r12 = r24
            r9 = r25
            r3 = r9
            r15 = r10
            r9 = r11
            r1 = r12
            r14 = r13
            r7 = r17
            r2 = r18
            r6 = r19
            r10 = r26
            r12 = r31
            r13 = r33
            r11 = r8
            goto L_0x001b
        L_0x0560:
            r19 = r6
            r17 = r7
            r26 = r10
            r8 = r11
            r13 = r14
            r10 = r15
            r9 = r3
        L_0x056a:
            r1 = -1
            if (r7 == r1) goto L_0x0573
            long r1 = (long) r7
            r3 = r26
            r3.putInt(r13, r1, r6)
        L_0x0573:
            r1 = 0
            int r2 = r10.zzm
        L_0x0576:
            int r3 = r10.zzn
            if (r2 >= r3) goto L_0x0589
            int[] r3 = r10.zzl
            r3 = r3[r2]
            com.google.android.gms.internal.measurement.zzhv<?, ?> r4 = r10.zzq
            java.lang.Object r1 = r10.zza(r13, r3, (UB) r1, r4)
            com.google.android.gms.internal.measurement.zzhy r1 = (com.google.android.gms.internal.measurement.zzhy) r1
            int r2 = r2 + 1
            goto L_0x0576
        L_0x0589:
            if (r1 == 0) goto L_0x0590
            com.google.android.gms.internal.measurement.zzhv<?, ?> r2 = r10.zzq
            r2.zzb(r13, r1)
        L_0x0590:
            if (r8 != 0) goto L_0x059c
            r1 = r33
            if (r0 != r1) goto L_0x0597
            goto L_0x05a2
        L_0x0597:
            com.google.android.gms.internal.measurement.zzfo r0 = com.google.android.gms.internal.measurement.zzfo.zzg()
            throw r0
        L_0x059c:
            r1 = r33
            if (r0 > r1) goto L_0x05a3
            if (r9 != r8) goto L_0x05a3
        L_0x05a2:
            return r0
        L_0x05a3:
            com.google.android.gms.internal.measurement.zzfo r0 = com.google.android.gms.internal.measurement.zzfo.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzdt):int");
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r17v0, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r0v7, types: [int] */
    /* JADX WARNING: type inference failed for: r1v4, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r2v7, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v3, types: [int] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r2v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r1v14, types: [int] */
    /* JADX WARNING: type inference failed for: r2v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r17v1 */
    /* JADX WARNING: type inference failed for: r3v13, types: [int] */
    /* JADX WARNING: type inference failed for: r17v2 */
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
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r29v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 405
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
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.measurement.zzdt r32) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            boolean r0 = r15.zzj
            if (r0 == 0) goto L_0x036d
            sun.misc.Unsafe r9 = zzb
            r10 = -1
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
        L_0x0019:
            if (r0 >= r13) goto L_0x0363
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002b
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r12, r3, r11)
            int r3 = r11.zza
            r8 = r0
            r17 = r3
            goto L_0x002e
        L_0x002b:
            r17 = r0
            r8 = r3
        L_0x002e:
            int r7 = r17 >>> 3
            r6 = r17 & 7
            if (r7 <= r1) goto L_0x003c
            int r2 = r2 / 3
            int r0 = r15.zza(r7, r2)
            r4 = r0
            goto L_0x0041
        L_0x003c:
            int r0 = r15.zzg(r7)
            r4 = r0
        L_0x0041:
            if (r4 != r10) goto L_0x004f
            r24 = r7
            r2 = r8
            r18 = r9
            r19 = 0
            r26 = -1
            goto L_0x032c
        L_0x004f:
            int[] r0 = r15.zzc
            int r1 = r4 + 1
            r5 = r0[r1]
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r3 = r0 >>> 20
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r1 = (long) r0
            r0 = 17
            r10 = 2
            if (r3 > r0) goto L_0x0231
            r0 = 1
            switch(r3) {
                case 0: goto L_0x0214;
                case 1: goto L_0x01f6;
                case 2: goto L_0x01d5;
                case 3: goto L_0x01d5;
                case 4: goto L_0x01b8;
                case 5: goto L_0x0196;
                case 6: goto L_0x0179;
                case 7: goto L_0x0154;
                case 8: goto L_0x012e;
                case 9: goto L_0x00fc;
                case 10: goto L_0x00e0;
                case 11: goto L_0x01b8;
                case 12: goto L_0x00c3;
                case 13: goto L_0x0179;
                case 14: goto L_0x0196;
                case 15: goto L_0x00a2;
                case 16: goto L_0x0078;
                default: goto L_0x006c;
            }
        L_0x006c:
            r10 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x0078:
            if (r6 != 0) goto L_0x0096
            int r6 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r8, r11)
            r19 = r1
            long r0 = r11.zzb
            long r21 = com.google.android.gms.internal.measurement.zzeg.zza(r0)
            r0 = r9
            r2 = r19
            r1 = r28
            r10 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x0096:
            r10 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x00a2:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x00b8
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r8, r11)
            int r1 = r11.zza
            int r1 = com.google.android.gms.internal.measurement.zzeg.zze(r1)
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x00b8:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x00c3:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x00d5
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r8, r11)
            int r1 = r11.zza
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x00d5:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x00e0:
            r2 = r1
            if (r6 != r10) goto L_0x00f1
            int r0 = com.google.android.gms.internal.measurement.zzdq.zze(r12, r8, r11)
            java.lang.Object r1 = r11.zzc
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0019
        L_0x00f1:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x030c
        L_0x00fc:
            r2 = r1
            if (r6 != r10) goto L_0x0123
            com.google.android.gms.internal.measurement.zzhd r0 = r15.zza(r4)
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r12, r8, r13, r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x0114
            java.lang.Object r1 = r11.zzc
            r9.putObject(r14, r2, r1)
            goto L_0x011e
        L_0x0114:
            java.lang.Object r5 = r11.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzff.zza(r1, r5)
            r9.putObject(r14, r2, r1)
        L_0x011e:
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0019
        L_0x0123:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x030c
        L_0x012e:
            r2 = r1
            if (r6 != r10) goto L_0x0149
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x013b
            int r0 = com.google.android.gms.internal.measurement.zzdq.zzc(r12, r8, r11)
            goto L_0x013f
        L_0x013b:
            int r0 = com.google.android.gms.internal.measurement.zzdq.zzd(r12, r8, r11)
        L_0x013f:
            java.lang.Object r1 = r11.zzc
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0019
        L_0x0149:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x030c
        L_0x0154:
            r2 = r1
            if (r6 != 0) goto L_0x016e
            int r1 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r8, r11)
            long r5 = r11.zzb
            r19 = 0
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x0164
            goto L_0x0165
        L_0x0164:
            r0 = 0
        L_0x0165:
            com.google.android.gms.internal.measurement.zzib.zza(r14, r2, r0)
            r0 = r1
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0019
        L_0x016e:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x030c
        L_0x0179:
            r2 = r1
            r0 = 5
            if (r6 != r0) goto L_0x018b
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0019
        L_0x018b:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x030c
        L_0x0196:
            r2 = r1
            if (r6 != r0) goto L_0x01ac
            long r5 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r8)
            r0 = r9
            r1 = r28
            r10 = r4
            r4 = r5
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x01ac:
            r10 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x01b8:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x01ca
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r12, r8, r11)
            int r1 = r11.zza
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x01ca:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x01d5:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x01eb
            int r6 = com.google.android.gms.internal.measurement.zzdq.zzb(r12, r8, r11)
            long r4 = r11.zzb
            r0 = r9
            r1 = r28
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x01eb:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x01f6:
            r2 = r1
            r10 = r4
            r0 = 5
            if (r6 != r0) goto L_0x0209
            float r0 = com.google.android.gms.internal.measurement.zzdq.zzd(r12, r8)
            com.google.android.gms.internal.measurement.zzib.zza(r14, r2, r0)
            int r0 = r8 + 4
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x0209:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x0214:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x0226
            double r0 = com.google.android.gms.internal.measurement.zzdq.zzc(r12, r8)
            com.google.android.gms.internal.measurement.zzib.zza(r14, r2, r0)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0019
        L_0x0226:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x030c
        L_0x0231:
            r0 = 27
            if (r3 != r0) goto L_0x027e
            if (r6 != r10) goto L_0x0273
            java.lang.Object r0 = r9.getObject(r14, r1)
            com.google.android.gms.internal.measurement.zzfl r0 = (com.google.android.gms.internal.measurement.zzfl) r0
            boolean r3 = r0.zza()
            if (r3 != 0) goto L_0x0258
            int r3 = r0.size()
            if (r3 != 0) goto L_0x024d
            r3 = 10
            goto L_0x024f
        L_0x024d:
            int r3 = r3 << 1
        L_0x024f:
            com.google.android.gms.internal.measurement.zzfl r0 = r0.zza(r3)
            r9.putObject(r14, r1, r0)
            r5 = r0
            goto L_0x0259
        L_0x0258:
            r5 = r0
        L_0x0259:
            com.google.android.gms.internal.measurement.zzhd r0 = r15.zza(r4)
            r1 = r17
            r2 = r29
            r3 = r8
            r19 = r4
            r4 = r31
            r6 = r32
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r2 = r19
            r10 = -1
            goto L_0x0019
        L_0x0273:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x030c
        L_0x027e:
            r19 = r4
            r0 = 49
            if (r3 > r0) goto L_0x02ca
            long r4 = (long) r5
            r0 = r27
            r20 = r1
            r1 = r28
            r2 = r29
            r10 = r3
            r3 = r8
            r22 = r4
            r4 = r31
            r5 = r17
            r30 = r6
            r6 = r7
            r24 = r7
            r7 = r30
            r15 = r8
            r8 = r19
            r18 = r9
            r25 = r10
            r26 = -1
            r9 = r22
            r11 = r25
            r12 = r20
            r14 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x02b7
            r2 = r0
            goto L_0x032c
        L_0x02b7:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            goto L_0x0019
        L_0x02ca:
            r20 = r1
            r25 = r3
            r30 = r6
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            r0 = 50
            r9 = r25
            if (r9 != r0) goto L_0x030e
            r7 = r30
            if (r7 != r10) goto L_0x030c
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r20
            r8 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x02f9
            r2 = r0
            goto L_0x032c
        L_0x02f9:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            goto L_0x0019
        L_0x030c:
            r2 = r15
            goto L_0x032c
        L_0x030e:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r8 = r5
            r5 = r17
            r6 = r24
            r10 = r20
            r12 = r19
            r13 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x0350
            r2 = r0
        L_0x032c:
            com.google.android.gms.internal.measurement.zzhy r4 = zze(r28)
            r0 = r17
            r1 = r29
            r3 = r31
            r5 = r32
            int r0 = com.google.android.gms.internal.measurement.zzdq.zza(r0, r1, r2, r3, r4, r5)
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            goto L_0x0019
        L_0x0350:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            goto L_0x0019
        L_0x0363:
            r4 = r31
            if (r0 != r4) goto L_0x0368
            return
        L_0x0368:
            com.google.android.gms.internal.measurement.zzfo r0 = com.google.android.gms.internal.measurement.zzfo.zzg()
            throw r0
        L_0x036d:
            r4 = r13
            r5 = 0
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r6 = r32
            r0.zza((T) r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzdt):void");
    }

    public final void zzc(T t) {
        int i;
        int i2 = this.zzm;
        while (true) {
            i = this.zzn;
            if (i2 >= i) {
                break;
            }
            long zzd2 = (long) (zzd(this.zzl[i2]) & 1048575);
            Object zzf2 = zzib.zzf(t, zzd2);
            if (zzf2 != null) {
                zzib.zza((Object) t, zzd2, this.zzs.zzd(zzf2));
            }
            i2++;
        }
        int length = this.zzl.length;
        while (i < length) {
            this.zzp.zzb(t, (long) this.zzl[i]);
            i++;
        }
        this.zzq.zzd(t);
        if (this.zzh) {
            this.zzr.zzc(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzhv<UT, UB> zzhv) {
        int i2 = this.zzc[i];
        Object zzf2 = zzib.zzf(obj, (long) (zzd(i) & 1048575));
        if (zzf2 == null) {
            return ub;
        }
        zzfk zzc2 = zzc(i);
        if (zzc2 == null) {
            return ub;
        }
        return zza(i, i2, this.zzs.zza(zzf2), zzc2, ub, zzhv);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzfk zzfk, UB ub, zzhv<UT, UB> zzhv) {
        zzgf zzf2 = this.zzs.zzf(zzb(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzfk.zza(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzhv.zza();
                }
                zzec zzc2 = zzdu.zzc(zzgg.zza(zzf2, entry.getKey(), entry.getValue()));
                try {
                    zzgg.zza(zzc2.zzb(), zzf2, entry.getKey(), entry.getValue());
                    zzhv.zza(ub, i2, zzc2.zza());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzd(T t) {
        int i;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.zzm) {
                return !this.zzh || this.zzr.zza((Object) t).zzf();
            }
            int i5 = this.zzl[i2];
            int i6 = this.zzc[i5];
            int zzd2 = zzd(i5);
            if (!this.zzj) {
                int i7 = this.zzc[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i3) {
                    i4 = zzb.getInt(t, (long) i8);
                    i3 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzd2) != 0) && !zza(t, i5, i4, i)) {
                return false;
            }
            int i9 = (267386880 & zzd2) >>> 20;
            if (i9 != 9 && i9 != 17) {
                if (i9 != 27) {
                    if (i9 == 60 || i9 == 68) {
                        if (zza(t, i6, i5) && !zza((Object) t, zzd2, zza(i5))) {
                            return false;
                        }
                    } else if (i9 != 49) {
                        if (i9 != 50) {
                            continue;
                        } else {
                            Map zzb2 = this.zzs.zzb(zzib.zzf(t, (long) (zzd2 & 1048575)));
                            if (!zzb2.isEmpty()) {
                                if (this.zzs.zzf(zzb(i5)).zzc.zza() == zzip.MESSAGE) {
                                    zzhd zzhd = null;
                                    Iterator it = zzb2.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzhd == null) {
                                            zzhd = zzgz.zza().zza(next.getClass());
                                        }
                                        if (!zzhd.zzd(next)) {
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
                List list = (List) zzib.zzf(t, (long) (zzd2 & 1048575));
                if (!list.isEmpty()) {
                    zzhd zza2 = zza(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 >= list.size()) {
                            break;
                        } else if (!zza2.zzd(list.get(i10))) {
                            z = false;
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i5, i4, i) && !zza((Object) t, zzd2, zza(i5))) {
                return false;
            }
            i2++;
        }
    }

    private static boolean zza(Object obj, int i, zzhd zzhd) {
        return zzhd.zzd(zzib.zzf(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzis zzis) throws IOException {
        if (obj instanceof String) {
            zzis.zza(i, (String) obj);
        } else {
            zzis.zza(i, (zzdu) obj);
        }
    }

    private final void zza(Object obj, int i, zzhe zzhe) throws IOException {
        if (zzf(i)) {
            zzib.zza(obj, (long) (i & 1048575), (Object) zzhe.zzm());
        } else if (this.zzi) {
            zzib.zza(obj, (long) (i & 1048575), (Object) zzhe.zzl());
        } else {
            zzib.zza(obj, (long) (i & 1048575), (Object) zzhe.zzn());
        }
    }

    private final int zzd(int i) {
        return this.zzc[i + 1];
    }

    private final int zze(int i) {
        return this.zzc[i + 2];
    }

    private static boolean zzf(int i) {
        return (i & 536870912) != 0;
    }

    private static <T> double zzb(T t, long j) {
        return ((Double) zzib.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzib.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzib.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzib.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzib.zzf(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzj) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzj) {
            int zzd2 = zzd(i);
            long j = (long) (zzd2 & 1048575);
            switch ((zzd2 & 267386880) >>> 20) {
                case 0:
                    return zzib.zze(t, j) != 0.0d;
                case 1:
                    return zzib.zzd(t, j) != 0.0f;
                case 2:
                    return zzib.zzb(t, j) != 0;
                case 3:
                    return zzib.zzb(t, j) != 0;
                case 4:
                    return zzib.zza((Object) t, j) != 0;
                case 5:
                    return zzib.zzb(t, j) != 0;
                case 6:
                    return zzib.zza((Object) t, j) != 0;
                case 7:
                    return zzib.zzc(t, j);
                case 8:
                    Object zzf2 = zzib.zzf(t, j);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzdu) {
                        return !zzdu.zza.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzib.zzf(t, j) != null;
                case 10:
                    return !zzdu.zza.equals(zzib.zzf(t, j));
                case 11:
                    return zzib.zza((Object) t, j) != 0;
                case 12:
                    return zzib.zza((Object) t, j) != 0;
                case 13:
                    return zzib.zza((Object) t, j) != 0;
                case 14:
                    return zzib.zzb(t, j) != 0;
                case 15:
                    return zzib.zza((Object) t, j) != 0;
                case 16:
                    return zzib.zzb(t, j) != 0;
                case 17:
                    return zzib.zzf(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zze2 = zze(i);
            return (zzib.zza((Object) t, (long) (zze2 & 1048575)) & (1 << (zze2 >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzj) {
            int zze2 = zze(i);
            long j = (long) (zze2 & 1048575);
            zzib.zza((Object) t, j, zzib.zza((Object) t, j) | (1 << (zze2 >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzib.zza((Object) t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzib.zza((Object) t, (long) (zze(i2) & 1048575), i);
    }

    private final int zzg(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, 0);
    }

    private final int zza(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, i2);
    }

    private final int zzb(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
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
