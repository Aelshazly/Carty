package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, mo24952d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* compiled from: -Utf8.kt */
public final class _Utf8Kt {
    public static final String commonToUtf8String(byte[] $receiver) {
        byte[] $receiver$iv;
        int endIndex$iv;
        boolean z;
        boolean z2;
        boolean z3;
        int length;
        int length2;
        int length3;
        int length4;
        int length5;
        int length6;
        boolean z4;
        int length7;
        int length8;
        int length9;
        boolean z5;
        int length10;
        byte[] bArr = $receiver;
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        char[] chars = new char[bArr.length];
        int length11 = 0;
        boolean z6 = false;
        int endIndex$iv2 = bArr.length;
        byte[] $receiver$iv2 = $receiver;
        boolean z7 = false;
        int length12 = 0;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        while (length12 < endIndex$iv2) {
            byte b0$iv = $receiver$iv2[length12];
            if (b0$iv >= 0) {
                int length13 = length11 + 1;
                chars[length11] = (char) b0$iv;
                length12++;
                while (length12 < endIndex$iv2 && $receiver$iv2[length12] >= 0) {
                    int index$iv = length12 + 1;
                    int length14 = length13 + 1;
                    chars[length13] = (char) $receiver$iv2[length12];
                    length13 = length14;
                    length12 = index$iv;
                }
                endIndex$iv = endIndex$iv2;
                $receiver$iv = $receiver$iv2;
                z = z7;
                length11 = length13;
                z2 = z6;
            } else {
                z2 = z6;
                int length15 = 2;
                int length16 = 1;
                if ((b0$iv >> 5) == -2) {
                    byte[] $receiver$iv$iv = $receiver$iv2;
                    boolean z13 = z10;
                    if (endIndex$iv2 <= length12 + 1) {
                        z5 = z13;
                        length9 = length11 + 1;
                        chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                        z = z7;
                    } else {
                        z5 = z13;
                        byte b0$iv$iv = $receiver$iv$iv[length12];
                        byte b1$iv$iv = $receiver$iv$iv[length12 + 1];
                        byte[] bArr2 = $receiver$iv$iv;
                        z = z7;
                        if (!((b1$iv$iv & 192) == 128)) {
                            length9 = length11 + 1;
                            chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                        } else {
                            int i = (b1$iv$iv ^ Utf8.MASK_2BYTES) ^ (b0$iv$iv << 6);
                            if (i < 128) {
                                byte b = b0$iv$iv;
                                length10 = length11 + 1;
                                chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                            } else {
                                boolean z14 = z8;
                                length10 = length11 + 1;
                                chars[length11] = (char) i;
                                z8 = z14;
                            }
                            length9 = length10;
                            length16 = 2;
                        }
                    }
                    length12 += length16;
                    endIndex$iv = endIndex$iv2;
                    $receiver$iv = $receiver$iv2;
                    z10 = z5;
                    length11 = length9;
                } else {
                    z = z7;
                    boolean z15 = z8;
                    if ((b0$iv >> 4) == -2) {
                        byte[] $receiver$iv$iv2 = $receiver$iv2;
                        boolean z16 = z11;
                        if (endIndex$iv2 <= length12 + 2) {
                            length7 = length11 + 1;
                            chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                            if (endIndex$iv2 > length12 + 1) {
                                if (((192 & $receiver$iv$iv2[length12 + 1]) == 128 ? (byte) 1 : 0) != 0) {
                                    z4 = z16;
                                }
                            }
                            z4 = z16;
                            length15 = 1;
                        } else {
                            byte b0$iv$iv2 = $receiver$iv$iv2[length12];
                            byte b1$iv$iv2 = $receiver$iv$iv2[length12 + 1];
                            z4 = z16;
                            if (!((b1$iv$iv2 & 192) == 128)) {
                                int length17 = length11 + 1;
                                chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                length7 = length17;
                                length15 = 1;
                            } else {
                                byte b2$iv$iv = $receiver$iv$iv2[length12 + 2];
                                byte $receiver$iv$iv$iv$iv = b2$iv$iv;
                                byte[] bArr3 = $receiver$iv$iv2;
                                if (($receiver$iv$iv$iv$iv & 192) != 128) {
                                    length16 = 0;
                                }
                                if (length16 == 0) {
                                    int length18 = length11 + 1;
                                    chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                    byte b1$iv$iv3 = $receiver$iv$iv$iv$iv;
                                    length7 = length18;
                                } else {
                                    int i2 = ((-123008 ^ b2$iv$iv) ^ (b1$iv$iv2 << 6)) ^ (b0$iv$iv2 << 12);
                                    if (i2 < 2048) {
                                        length8 = length11 + 1;
                                        chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                    } else {
                                        if (55296 <= i2 && 57343 >= i2) {
                                            length8 = length11 + 1;
                                            chars[length11] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                        } else {
                                            length8 = length11 + 1;
                                            chars[length11] = (char) i2;
                                        }
                                        byte b2 = i2;
                                    }
                                    byte b1$iv$iv4 = i2;
                                    length7 = length8;
                                    length15 = 3;
                                }
                            }
                        }
                        length12 += length15;
                        endIndex$iv = endIndex$iv2;
                        $receiver$iv = $receiver$iv2;
                        z8 = z15;
                        length11 = length7;
                        z11 = z4;
                    } else if ((b0$iv >> 3) == -2) {
                        byte[] $receiver$iv$iv3 = $receiver$iv2;
                        boolean z17 = z12;
                        if (endIndex$iv2 <= length12 + 3) {
                            if (65533 != 65533) {
                                int length19 = length11 + 1;
                                chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                length = length19 + 1;
                                chars[length19] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                            } else {
                                int length20 = length11 + 1;
                                chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                length = length20;
                            }
                            if (endIndex$iv2 > length12 + 1) {
                                if (($receiver$iv$iv3[length12 + 1] & 192) == 128) {
                                    if (endIndex$iv2 > length12 + 2) {
                                        if (($receiver$iv$iv3[length12 + 2] & 192) != 128) {
                                            length16 = 0;
                                        }
                                        if (length16 != 0) {
                                            z3 = z17;
                                            endIndex$iv = endIndex$iv2;
                                            $receiver$iv = $receiver$iv2;
                                            length15 = 3;
                                        }
                                    }
                                    z3 = z17;
                                    endIndex$iv = endIndex$iv2;
                                    $receiver$iv = $receiver$iv2;
                                }
                            }
                            z3 = z17;
                            endIndex$iv = endIndex$iv2;
                            $receiver$iv = $receiver$iv2;
                            length15 = 1;
                        } else {
                            byte b0$iv$iv3 = $receiver$iv$iv3[length12];
                            byte b1$iv$iv5 = $receiver$iv$iv3[length12 + 1];
                            z3 = z17;
                            if (!((b1$iv$iv5 & 192) == 128)) {
                                if (65533 != 65533) {
                                    int length21 = length11 + 1;
                                    chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                    length6 = length21 + 1;
                                    chars[length21] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                } else {
                                    int length22 = length11 + 1;
                                    chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                    length6 = length22;
                                }
                                $receiver$iv = $receiver$iv2;
                                length15 = 1;
                                endIndex$iv = endIndex$iv2;
                            } else {
                                byte b2$iv$iv2 = $receiver$iv$iv3[length12 + 2];
                                int $receiver$iv$iv$iv$iv2 = b2$iv$iv2;
                                endIndex$iv = endIndex$iv2;
                                $receiver$iv = $receiver$iv2;
                                if (!(($receiver$iv$iv$iv$iv2 & 192) == 128)) {
                                    if (65533 != 65533) {
                                        int length23 = length11 + 1;
                                        chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                        boolean z18 = z9;
                                        int length24 = length23 + 1;
                                        chars[length23] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                        length5 = length24;
                                        z9 = z18;
                                    } else {
                                        length5 = length11 + 1;
                                        chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                    }
                                    byte b1$iv$iv6 = $receiver$iv$iv$iv$iv2;
                                    length = length5;
                                } else {
                                    byte b3$iv$iv = $receiver$iv$iv3[length12 + 3];
                                    if ((b3$iv$iv & 192) != 128) {
                                        length16 = 0;
                                    }
                                    if (length16 == 0) {
                                        if (65533 != 65533) {
                                            int length25 = length11 + 1;
                                            chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                            length4 = length25 + 1;
                                            chars[length25] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                        } else {
                                            int length26 = length11 + 1;
                                            chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                            length4 = length26;
                                        }
                                        byte b1$iv$iv7 = b3$iv$iv;
                                        length15 = 3;
                                    } else {
                                        int i3 = (((3678080 ^ b3$iv$iv) ^ (b2$iv$iv2 << 6)) ^ (b1$iv$iv5 << 12)) ^ (b0$iv$iv3 << 18);
                                        if (i3 <= 1114111) {
                                            if (55296 <= i3 && 57343 >= i3) {
                                                if (65533 != 65533) {
                                                    int length27 = length11 + 1;
                                                    chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                                    length3 = length27 + 1;
                                                    chars[length27] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                                } else {
                                                    length2 = length11 + 1;
                                                    chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                                    byte b3 = i3;
                                                }
                                            } else if (i3 >= 65536) {
                                                int codePoint$iv = i3;
                                                if (codePoint$iv != 65533) {
                                                    int length28 = length11 + 1;
                                                    chars[length11] = (char) ((codePoint$iv >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                                    length3 = length28 + 1;
                                                    chars[length28] = (char) ((codePoint$iv & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                                } else {
                                                    length2 = length11 + 1;
                                                    chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                                    byte b32 = i3;
                                                }
                                            } else if (65533 != 65533) {
                                                int length29 = length11 + 1;
                                                chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                                length3 = length29 + 1;
                                                chars[length29] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                            } else {
                                                length2 = length11 + 1;
                                                chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                                byte b322 = i3;
                                            }
                                            length2 = length3;
                                            byte b3222 = i3;
                                        } else if (65533 != 65533) {
                                            int length30 = length11 + 1;
                                            chars[length11] = (char) ((Utf8.REPLACEMENT_CODE_POINT >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                            length2 = length30 + 1;
                                            chars[length30] = (char) ((65533 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                        } else {
                                            int length31 = length11 + 1;
                                            chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                                            length2 = length31;
                                        }
                                        length15 = 4;
                                        byte b1$iv$iv8 = b3$iv$iv;
                                    }
                                }
                            }
                        }
                        length12 += length15;
                        z8 = z15;
                        length11 = length;
                        z12 = z3;
                    } else {
                        endIndex$iv = endIndex$iv2;
                        $receiver$iv = $receiver$iv2;
                        boolean z19 = z9;
                        int length32 = length11 + 1;
                        chars[length11] = Utf8.REPLACEMENT_CHARACTER;
                        length12++;
                        length11 = length32;
                        z8 = z15;
                    }
                }
            }
            byte[] bArr4 = $receiver;
            z6 = z2;
            z7 = z;
            endIndex$iv2 = endIndex$iv;
            $receiver$iv2 = $receiver$iv;
        }
        int beginIndex$iv = z6;
        return new String(chars, 0, length11);
    }

    public static final byte[] commonAsUtf8ToByteArray(String $receiver) {
        String str = $receiver;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        byte[] bytes = new byte[($receiver.length() * 4)];
        int length = $receiver.length();
        int index = 0;
        while (true) {
            String str2 = "java.util.Arrays.copyOf(this, newSize)";
            if (index < length) {
                char b0 = str.charAt(index);
                if (b0 >= 128) {
                    int index$iv = index;
                    int endIndex$iv = $receiver.length();
                    String $receiver$iv = $receiver;
                    int index$iv2 = index;
                    boolean z = false;
                    while (index$iv2 < endIndex$iv) {
                        char c$iv = $receiver$iv.charAt(index$iv2);
                        if (c$iv < 128) {
                            int size = index$iv + 1;
                            bytes[index$iv] = (byte) c$iv;
                            index$iv2++;
                            while (index$iv2 < endIndex$iv && $receiver$iv.charAt(index$iv2) < 128) {
                                int index$iv3 = index$iv2 + 1;
                                int size2 = size + 1;
                                bytes[size] = (byte) $receiver$iv.charAt(index$iv2);
                                index$iv2 = index$iv3;
                                size = size2;
                            }
                            index$iv = size;
                        } else if (c$iv < 2048) {
                            int size3 = index$iv + 1;
                            bytes[index$iv] = (byte) ((c$iv >> 6) | 192);
                            int size4 = size3 + 1;
                            bytes[size3] = (byte) ((c$iv & '?') | 128);
                            index$iv2++;
                            index$iv = size4;
                        } else if (55296 > c$iv || 57343 < c$iv) {
                            int size5 = index$iv + 1;
                            bytes[index$iv] = (byte) ((c$iv >> 12) | 224);
                            boolean z2 = z;
                            int size6 = size5 + 1;
                            bytes[size5] = (byte) (((c$iv >> 6) & 63) | 128);
                            int size7 = size6 + 1;
                            bytes[size6] = (byte) ((c$iv & '?') | 128);
                            index$iv2++;
                            index$iv = size7;
                            int i = size6;
                            z = z2;
                            int i2 = i;
                        } else {
                            if (c$iv <= 56319 && endIndex$iv > index$iv2 + 1) {
                                char charAt = $receiver$iv.charAt(index$iv2 + 1);
                                if (56320 <= charAt && 57343 >= charAt) {
                                    int codePoint$iv = ((c$iv << 10) + $receiver$iv.charAt(index$iv2 + 1)) - 56613888;
                                    int size8 = index$iv + 1;
                                    bytes[index$iv] = (byte) ((codePoint$iv >> 18) | 240);
                                    int size9 = size8 + 1;
                                    bytes[size8] = (byte) (((codePoint$iv >> 12) & 63) | 128);
                                    int size10 = size9 + 1;
                                    bytes[size9] = (byte) (((codePoint$iv >> 6) & 63) | 128);
                                    int size11 = size10 + 1;
                                    bytes[size10] = (byte) ((codePoint$iv & 63) | 128);
                                    index$iv2 += 2;
                                    index$iv = size11;
                                    int codePoint$iv2 = size10;
                                }
                            }
                            int size12 = index$iv + 1;
                            bytes[index$iv] = Utf8.REPLACEMENT_BYTE;
                            index$iv2++;
                            int i3 = index$iv;
                            index$iv = size12;
                        }
                    }
                    byte[] copyOf = Arrays.copyOf(bytes, index$iv);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, str2);
                    return copyOf;
                }
                bytes[index] = (byte) b0;
                index++;
            } else {
                byte[] copyOf2 = Arrays.copyOf(bytes, $receiver.length());
                Intrinsics.checkExpressionValueIsNotNull(copyOf2, str2);
                return copyOf2;
            }
        }
    }
}
