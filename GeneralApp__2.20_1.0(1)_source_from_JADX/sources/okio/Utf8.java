package okio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\b\u001a1\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006 "}, mo24952d2 = {"HIGH_SURROGATE_HEADER", "", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", "", "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", "", "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", "", "processUtf8CodePoints", "utf8Size", "", "size", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* compiled from: Utf8.kt */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = '�';
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final long size(String str) {
        return size$default(str, 0, 0, 3, null);
    }

    public static final long size(String str, int i) {
        return size$default(str, i, 0, 2, null);
    }

    public static /* bridge */ /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    public static final long size(String $receiver, int beginIndex, int endIndex) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        boolean z = true;
        if (beginIndex >= 0) {
            if (endIndex >= beginIndex) {
                if (endIndex > $receiver.length()) {
                    z = false;
                }
                if (z) {
                    long result = 0;
                    int i = beginIndex;
                    while (i < endIndex) {
                        int c = $receiver.charAt(i);
                        if (c < 128) {
                            result++;
                            i++;
                        } else if (c < 2048) {
                            result += (long) 2;
                            i++;
                        } else if (c < 55296 || c > 57343) {
                            result += (long) 3;
                            i++;
                        } else {
                            int low = i + 1 < endIndex ? $receiver.charAt(i + 1) : 0;
                            if (c > 56319 || low < 56320 || low > 57343) {
                                result++;
                                i++;
                            } else {
                                result += (long) 4;
                                i += 2;
                            }
                        }
                    }
                    return result;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("endIndex > string.length: ");
                sb.append(endIndex);
                sb.append(" > ");
                sb.append($receiver.length());
                throw new IllegalArgumentException(sb.toString().toString());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("endIndex < beginIndex: ");
            sb2.append(endIndex);
            sb2.append(" < ");
            sb2.append(beginIndex);
            throw new IllegalArgumentException(sb2.toString().toString());
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("beginIndex < 0: ");
        sb3.append(beginIndex);
        throw new IllegalArgumentException(sb3.toString().toString());
    }

    public static final boolean isIsoControl(int codePoint) {
        return (codePoint >= 0 && 31 >= codePoint) || (127 <= codePoint && 159 >= codePoint);
    }

    public static final boolean isUtf8Continuation(byte b) {
        if ((192 & b) == 128) {
            return true;
        }
        return false;
    }

    public static final void processUtf8Bytes(String $receiver, int beginIndex, int endIndex, Function1<? super Byte, Unit> yield) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(yield, "yield");
        int index = beginIndex;
        while (index < endIndex) {
            char c = $receiver.charAt(index);
            if (c < 128) {
                yield.invoke(Byte.valueOf((byte) c));
                index++;
                while (index < endIndex && $receiver.charAt(index) < 128) {
                    int index2 = index + 1;
                    yield.invoke(Byte.valueOf((byte) $receiver.charAt(index)));
                    index = index2;
                }
            } else if (c < 2048) {
                yield.invoke(Byte.valueOf((byte) ((c >> 6) | 192)));
                yield.invoke(Byte.valueOf((byte) (128 | (c & '?'))));
                index++;
            } else if (55296 > c || 57343 < c) {
                yield.invoke(Byte.valueOf((byte) ((c >> 12) | 224)));
                yield.invoke(Byte.valueOf((byte) (((c >> 6) & 63) | 128)));
                yield.invoke(Byte.valueOf((byte) (128 | (c & '?'))));
                index++;
            } else {
                if (c <= 56319 && endIndex > index + 1) {
                    char charAt = $receiver.charAt(index + 1);
                    if (56320 <= charAt && 57343 >= charAt) {
                        int codePoint = ((c << 10) + $receiver.charAt(index + 1)) - 56613888;
                        yield.invoke(Byte.valueOf((byte) ((codePoint >> 18) | 240)));
                        yield.invoke(Byte.valueOf((byte) (((codePoint >> 12) & 63) | 128)));
                        yield.invoke(Byte.valueOf((byte) ((63 & (codePoint >> 6)) | 128)));
                        yield.invoke(Byte.valueOf((byte) (128 | (codePoint & 63))));
                        index += 2;
                    }
                }
                yield.invoke(Byte.valueOf(REPLACEMENT_BYTE));
                index++;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00dd, code lost:
        if (((192 & r0[r4 + 1]) == 128 ? (byte) 1 : 0) == 0) goto L_0x00e2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processUtf8CodePoints(byte[] r22, int r23, int r24, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r25) {
        /*
            r0 = r22
            r1 = r24
            r2 = r25
            r3 = 0
            java.lang.String r4 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r4)
            java.lang.String r4 = "yield"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r4)
            r4 = r23
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x001a:
            if (r4 >= r1) goto L_0x027c
            byte r13 = r0[r4]
            if (r13 < 0) goto L_0x0041
            java.lang.Integer r14 = java.lang.Integer.valueOf(r13)
            r2.invoke(r14)
            int r4 = r4 + 1
        L_0x002a:
            if (r4 >= r1) goto L_0x003d
            byte r14 = r0[r4]
            if (r14 < 0) goto L_0x003d
            int r14 = r4 + 1
            byte r4 = r0[r4]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.invoke(r4)
            r4 = r14
            goto L_0x002a
        L_0x003d:
            r19 = r3
            goto L_0x0273
        L_0x0041:
            r14 = 5
            r15 = r13
            int r14 = r15 >> r14
            r15 = -2
            r5 = 128(0x80, float:1.794E-43)
            r16 = 2
            r17 = 1
            if (r14 != r15) goto L_0x00b2
            r14 = r22
            int r15 = r4 + 1
            if (r1 > r15) goto L_0x0063
            r5 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r5)
            r2.invoke(r15)
            r19 = r3
            r18 = r12
            goto L_0x00ac
        L_0x0063:
            byte r12 = r14[r4]
            int r15 = r4 + 1
            byte r15 = r14[r15]
            r18 = 192(0xc0, float:2.69E-43)
            r19 = r15
            r0 = r19 & r18
            if (r0 != r5) goto L_0x0074
            r0 = 1
            goto L_0x0075
        L_0x0074:
            r0 = 0
        L_0x0075:
            if (r0 != 0) goto L_0x0084
            r0 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r2.invoke(r5)
            r19 = r3
            goto L_0x00ac
        L_0x0084:
            r0 = r15 ^ 3968(0xf80, float:5.56E-42)
            int r17 = r12 << 6
            r0 = r0 ^ r17
            if (r0 >= r5) goto L_0x009d
            r5 = 65533(0xfffd, float:9.1831E-41)
            r19 = r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r2.invoke(r3)
            goto L_0x00a9
        L_0x009d:
            r19 = r3
            r3 = r0
            r5 = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r2.invoke(r6)
            r6 = r5
        L_0x00a9:
            r17 = 2
        L_0x00ac:
            int r4 = r4 + r17
            r12 = r18
            goto L_0x0273
        L_0x00b2:
            r19 = r3
            r0 = 4
            r3 = r13
            int r0 = r3 >> r0
            r18 = 3
            if (r0 != r15) goto L_0x016e
            r0 = r22
            int r15 = r4 + 2
            if (r1 > r15) goto L_0x00e6
            r3 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r3)
            r2.invoke(r14)
            int r3 = r4 + 1
            if (r1 <= r3) goto L_0x00e2
            int r3 = r4 + 1
            byte r3 = r0[r3]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r3
            r14 = r14 & r15
            if (r14 != r5) goto L_0x00dc
            r3 = 1
            goto L_0x00dd
        L_0x00dc:
            r3 = 0
        L_0x00dd:
            if (r3 != 0) goto L_0x00e0
            goto L_0x00e2
        L_0x00e0:
            goto L_0x016a
        L_0x00e2:
            r16 = 1
            goto L_0x016a
        L_0x00e6:
            byte r12 = r0[r4]
            int r15 = r4 + 1
            byte r15 = r0[r15]
            r20 = 192(0xc0, float:2.69E-43)
            r21 = r15
            r14 = r21 & r20
            if (r14 != r5) goto L_0x00f7
            r14 = 1
            goto L_0x00f8
        L_0x00f7:
            r14 = 0
        L_0x00f8:
            if (r14 != 0) goto L_0x010a
            r3 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r2.invoke(r5)
            r12 = r20
            r16 = 1
            goto L_0x016a
        L_0x010a:
            int r10 = r4 + 2
            byte r10 = r0[r10]
            r14 = 192(0xc0, float:2.69E-43)
            r20 = r10
            r3 = r20 & r14
            if (r3 != r5) goto L_0x0118
            goto L_0x011a
        L_0x0118:
            r17 = 0
        L_0x011a:
            if (r17 != 0) goto L_0x012a
            r3 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r2.invoke(r5)
            r10 = r14
            r12 = r20
            goto L_0x016a
        L_0x012a:
            r3 = -123008(0xfffffffffffe1f80, float:NaN)
            r3 = r3 ^ r10
            int r5 = r15 << 6
            r3 = r3 ^ r5
            int r5 = r12 << 12
            r3 = r3 ^ r5
            r5 = 2048(0x800, float:2.87E-42)
            if (r3 >= r5) goto L_0x0148
            r5 = 65533(0xfffd, float:9.1831E-41)
            r20 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r2.invoke(r0)
            goto L_0x0165
        L_0x0148:
            r20 = r0
            r0 = 55296(0xd800, float:7.7486E-41)
            if (r0 <= r3) goto L_0x0150
            goto L_0x015a
        L_0x0150:
            r0 = 57343(0xdfff, float:8.0355E-41)
            if (r0 < r3) goto L_0x015a
            r0 = 65533(0xfffd, float:9.1831E-41)
            r5 = r6
            goto L_0x015c
        L_0x015a:
            r0 = r3
            r5 = r6
        L_0x015c:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r2.invoke(r6)
            r14 = r3
            r6 = r5
        L_0x0165:
            r12 = r3
            r10 = r14
            r16 = 3
        L_0x016a:
            int r4 = r4 + r16
            goto L_0x0273
        L_0x016e:
            r0 = 3
            r3 = r13
            int r0 = r3 >> r0
            if (r0 != r15) goto L_0x0267
            r0 = r22
            r3 = r11
            int r11 = r4 + 3
            if (r1 > r11) goto L_0x01b7
            r11 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r11)
            r2.invoke(r14)
            int r11 = r4 + 1
            if (r1 <= r11) goto L_0x01b3
            int r11 = r4 + 1
            byte r11 = r0[r11]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r11
            r14 = r14 & r15
            if (r14 != r5) goto L_0x0195
            r11 = 1
            goto L_0x0196
        L_0x0195:
            r11 = 0
        L_0x0196:
            if (r11 != 0) goto L_0x0199
            goto L_0x01b3
        L_0x0199:
            int r11 = r4 + 2
            if (r1 <= r11) goto L_0x01b1
            int r11 = r4 + 2
            byte r11 = r0[r11]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r11
            r14 = r14 & r15
            if (r14 != r5) goto L_0x01a8
            goto L_0x01aa
        L_0x01a8:
            r17 = 0
        L_0x01aa:
            if (r17 != 0) goto L_0x01ad
            goto L_0x01b1
        L_0x01ad:
            r16 = 3
            goto L_0x0263
        L_0x01b1:
            goto L_0x0263
        L_0x01b3:
            r16 = 1
            goto L_0x0263
        L_0x01b7:
            byte r11 = r0[r4]
            int r12 = r4 + 1
            byte r12 = r0[r12]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r12
            r15 = r15 & r14
            if (r15 != r5) goto L_0x01c6
            r15 = 1
            goto L_0x01c7
        L_0x01c6:
            r15 = 0
        L_0x01c7:
            if (r15 != 0) goto L_0x01d8
            r5 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r5)
            r2.invoke(r15)
            r12 = r14
            r16 = 1
            goto L_0x0263
        L_0x01d8:
            int r10 = r4 + 2
            byte r10 = r0[r10]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r10
            r1 = r15 & r14
            if (r1 != r5) goto L_0x01e6
            r1 = 1
            goto L_0x01e7
        L_0x01e6:
            r1 = 0
        L_0x01e7:
            if (r1 != 0) goto L_0x01f7
            r1 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r2.invoke(r5)
            r10 = r14
            r12 = r15
            goto L_0x0263
        L_0x01f7:
            int r1 = r4 + 3
            byte r1 = r0[r1]
            r9 = 192(0xc0, float:2.69E-43)
            r14 = r1
            r15 = r14 & r9
            if (r15 != r5) goto L_0x0204
            goto L_0x0206
        L_0x0204:
            r17 = 0
        L_0x0206:
            if (r17 != 0) goto L_0x0217
            r5 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r5)
            r2.invoke(r15)
            r12 = r1
            r10 = r14
            r16 = 3
            goto L_0x0263
        L_0x0217:
            r5 = 3678080(0x381f80, float:5.154088E-39)
            r5 = r5 ^ r1
            int r14 = r10 << 6
            r5 = r5 ^ r14
            int r14 = r12 << 12
            r5 = r5 ^ r14
            int r14 = r11 << 18
            r5 = r5 ^ r14
            r14 = 1114111(0x10ffff, float:1.561202E-39)
            if (r5 <= r14) goto L_0x0237
            r14 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r14)
            r2.invoke(r15)
            goto L_0x025e
        L_0x0237:
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r9 <= r5) goto L_0x023d
            goto L_0x024e
        L_0x023d:
            r9 = 57343(0xdfff, float:8.0355E-41)
            if (r9 < r5) goto L_0x024e
            r9 = 65533(0xfffd, float:9.1831E-41)
        L_0x0245:
            java.lang.Integer r14 = java.lang.Integer.valueOf(r9)
            r2.invoke(r14)
            r9 = r5
            goto L_0x025e
        L_0x024e:
            r9 = 65536(0x10000, float:9.18355E-41)
            if (r5 >= r9) goto L_0x0256
            r9 = 65533(0xfffd, float:9.1831E-41)
            goto L_0x0245
        L_0x0256:
            r9 = r5
            java.lang.Integer r14 = java.lang.Integer.valueOf(r9)
            r2.invoke(r14)
        L_0x025e:
            r16 = 4
            r12 = r1
            r10 = r5
        L_0x0263:
            int r4 = r4 + r16
            r11 = r3
            goto L_0x0273
        L_0x0267:
            r0 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.invoke(r0)
            int r4 = r4 + 1
        L_0x0273:
            r0 = r22
            r1 = r24
            r3 = r19
            goto L_0x001a
        L_0x027c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf8CodePoints(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:125:0x030f, code lost:
        if (65533(0xfffd) != 65533(0xfffd)) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x032c, code lost:
        r2.invoke(java.lang.Character.valueOf(REPLACEMENT_CHARACTER));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0343, code lost:
        if (65533(0xfffd) != 65533(0xfffd)) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x034a, code lost:
        if (r5 != 65533) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e4, code lost:
        if (((192 & r0[r4 + 1]) == 128 ? (byte) 1 : 0) == 0) goto L_0x00e9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processUtf16Chars(byte[] r24, int r25, int r26, kotlin.jvm.functions.Function1<? super java.lang.Character, kotlin.Unit> r27) {
        /*
            r0 = r24
            r1 = r26
            r2 = r27
            r3 = 0
            java.lang.String r4 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r4)
            java.lang.String r4 = "yield"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r4)
            r4 = r25
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x001a:
            if (r4 >= r1) goto L_0x0369
            byte r13 = r0[r4]
            if (r13 < 0) goto L_0x0043
            char r14 = (char) r13
            java.lang.Character r14 = java.lang.Character.valueOf(r14)
            r2.invoke(r14)
            int r4 = r4 + 1
        L_0x002b:
            if (r4 >= r1) goto L_0x003f
            byte r14 = r0[r4]
            if (r14 < 0) goto L_0x003f
            int r14 = r4 + 1
            byte r4 = r0[r4]
            char r4 = (char) r4
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            r4 = r14
            goto L_0x002b
        L_0x003f:
            r19 = r3
            goto L_0x0362
        L_0x0043:
            r14 = 5
            r15 = r13
            int r14 = r15 >> r14
            r15 = -2
            r5 = 128(0x80, float:1.794E-43)
            r16 = 2
            r17 = 1
            if (r14 != r15) goto L_0x00b8
            r14 = r24
            int r15 = r4 + 1
            if (r1 > r15) goto L_0x0066
            r5 = 65533(0xfffd, float:9.1831E-41)
            char r15 = (char) r5
            java.lang.Character r15 = java.lang.Character.valueOf(r15)
            r2.invoke(r15)
            r19 = r3
            r18 = r12
            goto L_0x00b2
        L_0x0066:
            byte r12 = r14[r4]
            int r15 = r4 + 1
            byte r15 = r14[r15]
            r18 = 192(0xc0, float:2.69E-43)
            r19 = r15
            r0 = r19 & r18
            if (r0 != r5) goto L_0x0077
            r0 = 1
            goto L_0x0078
        L_0x0077:
            r0 = 0
        L_0x0078:
            if (r0 != 0) goto L_0x0088
            r0 = 65533(0xfffd, float:9.1831E-41)
            char r5 = (char) r0
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            r19 = r3
            goto L_0x00b2
        L_0x0088:
            r0 = r15 ^ 3968(0xf80, float:5.56E-42)
            int r17 = r12 << 6
            r0 = r0 ^ r17
            if (r0 >= r5) goto L_0x00a2
            r5 = 65533(0xfffd, float:9.1831E-41)
            r19 = r3
            char r3 = (char) r5
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
            goto L_0x00af
        L_0x00a2:
            r19 = r3
            r3 = r0
            r5 = r6
            char r6 = (char) r3
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            r2.invoke(r6)
            r6 = r5
        L_0x00af:
            r17 = 2
        L_0x00b2:
            int r4 = r4 + r17
            r12 = r18
            goto L_0x0362
        L_0x00b8:
            r19 = r3
            r0 = 4
            r3 = r13
            int r0 = r3 >> r0
            r18 = 3
            if (r0 != r15) goto L_0x0179
            r0 = r24
            int r15 = r4 + 2
            if (r1 > r15) goto L_0x00ed
            r3 = 65533(0xfffd, float:9.1831E-41)
            char r14 = (char) r3
            java.lang.Character r14 = java.lang.Character.valueOf(r14)
            r2.invoke(r14)
            int r3 = r4 + 1
            if (r1 <= r3) goto L_0x00e9
            int r3 = r4 + 1
            byte r3 = r0[r3]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r3
            r14 = r14 & r15
            if (r14 != r5) goto L_0x00e3
            r3 = 1
            goto L_0x00e4
        L_0x00e3:
            r3 = 0
        L_0x00e4:
            if (r3 != 0) goto L_0x00e7
            goto L_0x00e9
        L_0x00e7:
            goto L_0x0175
        L_0x00e9:
            r16 = 1
            goto L_0x0175
        L_0x00ed:
            byte r12 = r0[r4]
            int r15 = r4 + 1
            byte r15 = r0[r15]
            r20 = 192(0xc0, float:2.69E-43)
            r21 = r15
            r14 = r21 & r20
            if (r14 != r5) goto L_0x00fe
            r14 = 1
            goto L_0x00ff
        L_0x00fe:
            r14 = 0
        L_0x00ff:
            if (r14 != 0) goto L_0x0112
            r3 = 65533(0xfffd, float:9.1831E-41)
            char r5 = (char) r3
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            r12 = r20
            r16 = 1
            goto L_0x0175
        L_0x0112:
            int r10 = r4 + 2
            byte r10 = r0[r10]
            r14 = 192(0xc0, float:2.69E-43)
            r20 = r10
            r3 = r20 & r14
            if (r3 != r5) goto L_0x0120
            goto L_0x0122
        L_0x0120:
            r17 = 0
        L_0x0122:
            if (r17 != 0) goto L_0x0133
            r3 = 65533(0xfffd, float:9.1831E-41)
            char r5 = (char) r3
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            r10 = r14
            r12 = r20
            goto L_0x0175
        L_0x0133:
            r3 = -123008(0xfffffffffffe1f80, float:NaN)
            r3 = r3 ^ r10
            int r5 = r15 << 6
            r3 = r3 ^ r5
            int r5 = r12 << 12
            r3 = r3 ^ r5
            r5 = 2048(0x800, float:2.87E-42)
            if (r3 >= r5) goto L_0x0152
            r5 = 65533(0xfffd, float:9.1831E-41)
            r20 = r0
            char r0 = (char) r5
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r2.invoke(r0)
            goto L_0x0170
        L_0x0152:
            r20 = r0
            r0 = 55296(0xd800, float:7.7486E-41)
            if (r0 <= r3) goto L_0x015a
            goto L_0x0164
        L_0x015a:
            r0 = 57343(0xdfff, float:8.0355E-41)
            if (r0 < r3) goto L_0x0164
            r0 = 65533(0xfffd, float:9.1831E-41)
            r5 = r6
            goto L_0x0166
        L_0x0164:
            r0 = r3
            r5 = r6
        L_0x0166:
            char r6 = (char) r0
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            r2.invoke(r6)
            r14 = r3
            r6 = r5
        L_0x0170:
            r12 = r3
            r10 = r14
            r16 = 3
        L_0x0175:
            int r4 = r4 + r16
            goto L_0x0362
        L_0x0179:
            r0 = 3
            r3 = r13
            int r0 = r3 >> r0
            r3 = 65533(0xfffd, float:9.1831E-41)
            if (r0 != r15) goto L_0x0356
            r0 = r24
            int r14 = r4 + 3
            r15 = 56320(0xdc00, float:7.8921E-41)
            r20 = 55232(0xd7c0, float:7.7397E-41)
            if (r1 > r14) goto L_0x01e6
            r14 = 65533(0xfffd, float:9.1831E-41)
            if (r14 == r3) goto L_0x01ab
            int r3 = r14 >>> 10
            int r3 = r3 + r20
            char r3 = (char) r3
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
            r3 = r14 & 1023(0x3ff, float:1.434E-42)
            int r3 = r3 + r15
            char r3 = (char) r3
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
            goto L_0x01b2
        L_0x01ab:
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
        L_0x01b2:
            int r3 = r4 + 1
            if (r1 <= r3) goto L_0x01e2
            int r3 = r4 + 1
            byte r3 = r0[r3]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r3
            r14 = r14 & r15
            if (r14 != r5) goto L_0x01c4
            r3 = 1
            goto L_0x01c5
        L_0x01c4:
            r3 = 0
        L_0x01c5:
            if (r3 != 0) goto L_0x01c8
            goto L_0x01e2
        L_0x01c8:
            int r3 = r4 + 2
            if (r1 <= r3) goto L_0x01e0
            int r3 = r4 + 2
            byte r3 = r0[r3]
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r3
            r14 = r14 & r15
            if (r14 != r5) goto L_0x01d7
            goto L_0x01d9
        L_0x01d7:
            r17 = 0
        L_0x01d9:
            if (r17 != 0) goto L_0x01dc
            goto L_0x01e0
        L_0x01dc:
            r16 = 3
            goto L_0x0353
        L_0x01e0:
            goto L_0x0353
        L_0x01e2:
            r16 = 1
            goto L_0x0353
        L_0x01e6:
            byte r12 = r0[r4]
            int r14 = r4 + 1
            byte r14 = r0[r14]
            r22 = 192(0xc0, float:2.69E-43)
            r23 = r14
            r15 = r23 & r22
            if (r15 != r5) goto L_0x01f7
            r15 = 1
            goto L_0x01f8
        L_0x01f7:
            r15 = 0
        L_0x01f8:
            if (r15 != 0) goto L_0x0229
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r5 == r3) goto L_0x021a
            int r3 = r5 >>> 10
            int r3 = r3 + r20
            char r3 = (char) r3
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
            r3 = r5 & 1023(0x3ff, float:1.434E-42)
            r15 = 56320(0xdc00, float:7.8921E-41)
            int r3 = r3 + r15
            char r3 = (char) r3
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
            goto L_0x0221
        L_0x021a:
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
        L_0x0221:
            r12 = r22
            r16 = 1
            goto L_0x0353
        L_0x0229:
            int r10 = r4 + 2
            byte r10 = r0[r10]
            r15 = 192(0xc0, float:2.69E-43)
            r22 = r10
            r3 = r22 & r15
            if (r3 != r5) goto L_0x0238
            r3 = 1
            goto L_0x0239
        L_0x0238:
            r3 = 0
        L_0x0239:
            if (r3 != 0) goto L_0x0270
            r3 = 65533(0xfffd, float:9.1831E-41)
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r3 == r5) goto L_0x025f
            int r5 = r3 >>> 10
            int r5 = r5 + r20
            char r5 = (char) r5
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            r5 = r3 & 1023(0x3ff, float:1.434E-42)
            r17 = 56320(0xdc00, float:7.8921E-41)
            int r5 = r5 + r17
            char r5 = (char) r5
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            goto L_0x0269
        L_0x025f:
            r5 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
        L_0x0269:
            r10 = r15
            r12 = r22
            goto L_0x0353
        L_0x0270:
            int r3 = r4 + 3
            byte r3 = r0[r3]
            r9 = 192(0xc0, float:2.69E-43)
            r15 = r3
            r16 = r0
            r0 = r15 & r9
            if (r0 != r5) goto L_0x027f
            goto L_0x0281
        L_0x027f:
            r17 = 0
        L_0x0281:
            if (r17 != 0) goto L_0x02b9
            r0 = 65533(0xfffd, float:9.1831E-41)
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r0 == r5) goto L_0x02a7
            int r5 = r0 >>> 10
            int r5 = r5 + r20
            char r5 = (char) r5
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            r5 = r0 & 1023(0x3ff, float:1.434E-42)
            r17 = 56320(0xdc00, float:7.8921E-41)
            int r5 = r5 + r17
            char r5 = (char) r5
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            goto L_0x02b1
        L_0x02a7:
            r5 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
        L_0x02b1:
            r12 = r3
            r10 = r15
            r16 = 3
            goto L_0x0353
        L_0x02b9:
            r0 = 3678080(0x381f80, float:5.154088E-39)
            r0 = r0 ^ r3
            int r5 = r10 << 6
            r0 = r0 ^ r5
            int r5 = r14 << 12
            r0 = r0 ^ r5
            int r5 = r12 << 18
            r0 = r0 ^ r5
            r5 = 1114111(0x10ffff, float:1.561202E-39)
            if (r0 <= r5) goto L_0x02fe
            r5 = 65533(0xfffd, float:9.1831E-41)
            r15 = 65533(0xfffd, float:9.1831E-41)
            if (r5 == r15) goto L_0x02f2
            int r15 = r5 >>> 10
            int r15 = r15 + r20
            char r15 = (char) r15
            java.lang.Character r15 = java.lang.Character.valueOf(r15)
            r2.invoke(r15)
            r15 = r5 & 1023(0x3ff, float:1.434E-42)
            r17 = 56320(0xdc00, float:7.8921E-41)
            int r15 = r15 + r17
            char r15 = (char) r15
            java.lang.Character r15 = java.lang.Character.valueOf(r15)
            r2.invoke(r15)
            goto L_0x02fc
        L_0x02f2:
            r15 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Character r15 = java.lang.Character.valueOf(r15)
            r2.invoke(r15)
        L_0x02fc:
            goto L_0x034d
        L_0x02fe:
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r5 <= r0) goto L_0x0304
            goto L_0x0339
        L_0x0304:
            r5 = 57343(0xdfff, float:8.0355E-41)
            if (r5 < r0) goto L_0x0339
            r5 = 65533(0xfffd, float:9.1831E-41)
            r9 = 65533(0xfffd, float:9.1831E-41)
            if (r5 == r9) goto L_0x032c
        L_0x0311:
            int r9 = r5 >>> 10
            int r9 = r9 + r20
            char r9 = (char) r9
            java.lang.Character r9 = java.lang.Character.valueOf(r9)
            r2.invoke(r9)
            r9 = r5 & 1023(0x3ff, float:1.434E-42)
            r15 = 56320(0xdc00, float:7.8921E-41)
            int r9 = r9 + r15
            char r9 = (char) r9
            java.lang.Character r9 = java.lang.Character.valueOf(r9)
            r2.invoke(r9)
            goto L_0x0336
        L_0x032c:
            r9 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Character r9 = java.lang.Character.valueOf(r9)
            r2.invoke(r9)
        L_0x0336:
            r9 = r0
            goto L_0x034d
        L_0x0339:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r0 >= r5) goto L_0x0346
            r5 = 65533(0xfffd, float:9.1831E-41)
            r9 = 65533(0xfffd, float:9.1831E-41)
            if (r5 == r9) goto L_0x032c
            goto L_0x0311
        L_0x0346:
            r9 = 65533(0xfffd, float:9.1831E-41)
            r5 = r0
            if (r5 == r9) goto L_0x032c
            goto L_0x0311
        L_0x034d:
            r5 = 4
            r10 = r0
            r12 = r3
            r16 = 4
        L_0x0353:
            int r4 = r4 + r16
            goto L_0x0362
        L_0x0356:
            r0 = 65533(0xfffd, float:9.1831E-41)
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r2.invoke(r0)
            int r4 = r4 + 1
        L_0x0362:
            r0 = r24
            r3 = r19
            goto L_0x001a
        L_0x0369:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf16Chars(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    public static final int process2Utf8Bytes(byte[] $receiver, int beginIndex, int endIndex, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(yield, "yield");
        int i = beginIndex + 1;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (endIndex <= i) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b0 = $receiver[beginIndex];
        byte b1 = $receiver[beginIndex + 1];
        boolean z = false;
        if ((192 & b1) == 128) {
            z = true;
        }
        if (!z) {
            yield.invoke(valueOf);
            return 1;
        }
        int codePoint = (b1 ^ MASK_2BYTES) ^ (b0 << 6);
        if (codePoint < 128) {
            yield.invoke(valueOf);
        } else {
            yield.invoke(Integer.valueOf(codePoint));
        }
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] $receiver, int beginIndex, int endIndex, Function1<? super Integer, Unit> yield) {
        byte[] bArr = $receiver;
        int i = endIndex;
        Function1<? super Integer, Unit> function1 = yield;
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "yield");
        int i2 = beginIndex + 2;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        boolean z = false;
        if (i <= i2) {
            function1.invoke(valueOf);
            if (i > beginIndex + 1) {
                if ((192 & bArr[beginIndex + 1]) == 128) {
                    z = true;
                }
                return !z ? 1 : 2;
            }
        }
        byte b0 = bArr[beginIndex];
        byte b1 = bArr[beginIndex + 1];
        if (!((192 & b1) == 128)) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b2 = bArr[beginIndex + 2];
        if ((b2 & 192) == 128) {
            z = true;
        }
        if (!z) {
            function1.invoke(valueOf);
            return 2;
        }
        int codePoint = ((-123008 ^ b2) ^ (b1 << 6)) ^ (b0 << 12);
        if (codePoint < 2048) {
            function1.invoke(valueOf);
        } else if (55296 <= codePoint && 57343 >= codePoint) {
            function1.invoke(valueOf);
        } else {
            function1.invoke(Integer.valueOf(codePoint));
        }
        return 3;
    }

    public static final int process4Utf8Bytes(byte[] $receiver, int beginIndex, int endIndex, Function1<? super Integer, Unit> yield) {
        byte[] bArr = $receiver;
        int i = endIndex;
        Function1<? super Integer, Unit> function1 = yield;
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "yield");
        int i2 = beginIndex + 3;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        boolean z = true;
        if (i <= i2) {
            function1.invoke(valueOf);
            if (i > beginIndex + 1) {
                if (((192 & bArr[beginIndex + 1]) == 128 ? (byte) 1 : 0) != 0) {
                    if (i > beginIndex + 2) {
                        if ((192 & bArr[beginIndex + 2]) != 128) {
                            z = false;
                        }
                        return !z ? 2 : 3;
                    }
                }
            }
            return 1;
        }
        byte b0 = bArr[beginIndex];
        byte b1 = bArr[beginIndex + 1];
        if (!((192 & b1) == 128)) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b2 = bArr[beginIndex + 2];
        if (!((b2 & 192) == 128)) {
            function1.invoke(valueOf);
            return 2;
        }
        byte b3 = bArr[beginIndex + 3];
        if ((b3 & 192) != 128) {
            z = false;
        }
        if (!z) {
            function1.invoke(valueOf);
            return 3;
        }
        int codePoint = (((3678080 ^ b3) ^ (b2 << 6)) ^ (b1 << 12)) ^ (b0 << 18);
        if (codePoint > 1114111) {
            function1.invoke(valueOf);
        } else if (55296 <= codePoint && 57343 >= codePoint) {
            function1.invoke(valueOf);
        } else if (codePoint < 65536) {
            function1.invoke(valueOf);
        } else {
            function1.invoke(Integer.valueOf(codePoint));
        }
        return 4;
    }
}
