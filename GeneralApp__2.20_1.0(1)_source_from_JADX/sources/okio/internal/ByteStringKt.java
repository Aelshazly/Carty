package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Base64;
import okio.ByteString;
import okio.Platform;
import okio.Util;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0017\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\tH\u0000\u001a\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\f\u0010\u0012\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\u0014\u0010\u0013\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0000\u001a\u000e\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\u0011H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\tH\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u001b\u001a\u00020\u0019*\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u001cH\u0000\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0007H\u0000\u001a\f\u0010 \u001a\u00020\u0007*\u00020\u0001H\u0000\u001a\f\u0010!\u001a\u00020\u0007*\u00020\u0001H\u0000\u001a\f\u0010\"\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\u001c\u0010#\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0000\u001a\f\u0010%\u001a\u00020\t*\u00020\u0001H\u0000\u001a\u001c\u0010&\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0000\u001a,\u0010'\u001a\u00020\u0019*\u00020\u00012\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0000\u001a,\u0010'\u001a\u00020\u0019*\u00020\u00012\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0000\u001a\u0014\u0010+\u001a\u00020\u0019*\u00020\u00012\u0006\u0010,\u001a\u00020\tH\u0000\u001a\u0014\u0010+\u001a\u00020\u0019*\u00020\u00012\u0006\u0010,\u001a\u00020\u0001H\u0000\u001a\u001c\u0010-\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0007H\u0000\u001a\f\u00100\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u00101\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u00102\u001a\u00020\t*\u00020\u0001H\u0000\u001a\f\u00103\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\f\u00104\u001a\u00020\u0011*\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00065"}, mo24952d2 = {"COMMON_EMPTY", "Lokio/ByteString;", "getCOMMON_EMPTY", "()Lokio/ByteString;", "HEX_DIGITS", "", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "offset", "otherOffset", "byteCount", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToString", "commonUtf8", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* compiled from: ByteString.kt */
public final class ByteStringKt {
    private static final ByteString COMMON_EMPTY = ByteString.Companion.mo27891of(new byte[0]);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final String commonUtf8(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String result = $receiver.getUtf8$jvm();
        if (result != null) {
            return result;
        }
        String result2 = Platform.toUtf8String($receiver.internalArray$jvm());
        $receiver.setUtf8$jvm(result2);
        return result2;
    }

    public static final String commonBase64(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return Base64.encodeBase64$default($receiver.getData$jvm(), null, 1, null);
    }

    public static final String commonBase64Url(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return Base64.encodeBase64($receiver.getData$jvm(), Base64.getBASE64_URL_SAFE());
    }

    public static final String commonHex(ByteString $receiver) {
        byte[] data$jvm;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        char[] result = new char[($receiver.getData$jvm().length * 2)];
        int c = 0;
        for (byte b : $receiver.getData$jvm()) {
            int c2 = c + 1;
            char[] cArr = HEX_DIGITS;
            int $receiver$iv = b;
            result[c] = cArr[($receiver$iv >> 4) & 15];
            c = c2 + 1;
            result[c2] = cArr[15 & $receiver$iv];
        }
        return new String(result);
    }

    public static final ByteString commonToAsciiLowercase(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        for (int i = 0; i < $receiver.getData$jvm().length; i++) {
            byte c = $receiver.getData$jvm()[i];
            byte b = (byte) 65;
            if (c >= b) {
                byte b2 = (byte) 90;
                if (c <= b2) {
                    byte[] data$jvm = $receiver.getData$jvm();
                    byte[] lowercase = Arrays.copyOf(data$jvm, data$jvm.length);
                    Intrinsics.checkExpressionValueIsNotNull(lowercase, "java.util.Arrays.copyOf(this, size)");
                    int i2 = i + 1;
                    lowercase[i] = (byte) (c + 32);
                    while (i2 < lowercase.length) {
                        byte c2 = lowercase[i2];
                        if (c2 < b || c2 > b2) {
                            i2++;
                        } else {
                            lowercase[i2] = (byte) (c2 + 32);
                            i2++;
                        }
                    }
                    return new ByteString(lowercase);
                }
            }
        }
        return $receiver;
    }

    public static final ByteString commonToAsciiUppercase(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        for (int i = 0; i < $receiver.getData$jvm().length; i++) {
            byte c = $receiver.getData$jvm()[i];
            byte b = (byte) 97;
            if (c >= b) {
                byte b2 = (byte) 122;
                if (c <= b2) {
                    byte[] data$jvm = $receiver.getData$jvm();
                    byte[] lowercase = Arrays.copyOf(data$jvm, data$jvm.length);
                    Intrinsics.checkExpressionValueIsNotNull(lowercase, "java.util.Arrays.copyOf(this, size)");
                    int i2 = i + 1;
                    lowercase[i] = (byte) (c - 32);
                    while (i2 < lowercase.length) {
                        byte c2 = lowercase[i2];
                        if (c2 < b || c2 > b2) {
                            i2++;
                        } else {
                            lowercase[i2] = (byte) (c2 - 32);
                            i2++;
                        }
                    }
                    return new ByteString(lowercase);
                }
            }
        }
        return $receiver;
    }

    public static final ByteString commonSubstring(ByteString $receiver, int beginIndex, int endIndex) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        boolean z = true;
        if (beginIndex >= 0) {
            if (endIndex <= $receiver.getData$jvm().length) {
                int subLen = endIndex - beginIndex;
                if (subLen < 0) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException("endIndex < beginIndex".toString());
                } else if (beginIndex == 0 && endIndex == $receiver.getData$jvm().length) {
                    return $receiver;
                } else {
                    byte[] copy = new byte[subLen];
                    Platform.arraycopy($receiver.getData$jvm(), beginIndex, copy, 0, subLen);
                    return new ByteString(copy);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("endIndex > length(");
                sb.append($receiver.getData$jvm().length);
                sb.append(')');
                throw new IllegalArgumentException(sb.toString().toString());
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
    }

    public static final byte commonGetByte(ByteString $receiver, int pos) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.getData$jvm()[pos];
    }

    public static final int commonGetSize(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.getData$jvm().length;
    }

    public static final byte[] commonToByteArray(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        byte[] data$jvm = $receiver.getData$jvm();
        byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    public static final byte[] commonInternalArray(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.getData$jvm();
    }

    public static final boolean commonRangeEquals(ByteString $receiver, int offset, ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return other.rangeEquals(otherOffset, $receiver.getData$jvm(), offset, byteCount);
    }

    public static final boolean commonRangeEquals(ByteString $receiver, int offset, byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return offset >= 0 && offset <= $receiver.getData$jvm().length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && Util.arrayRangeEquals($receiver.getData$jvm(), offset, other, otherOffset, byteCount);
    }

    public static final boolean commonStartsWith(ByteString $receiver, ByteString prefix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        return $receiver.rangeEquals(0, prefix, 0, prefix.size());
    }

    public static final boolean commonStartsWith(ByteString $receiver, byte[] prefix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        return $receiver.rangeEquals(0, prefix, 0, prefix.length);
    }

    public static final boolean commonEndsWith(ByteString $receiver, ByteString suffix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return $receiver.rangeEquals($receiver.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public static final boolean commonEndsWith(ByteString $receiver, byte[] suffix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return $receiver.rangeEquals($receiver.size() - suffix.length, suffix, 0, suffix.length);
    }

    public static final int commonIndexOf(ByteString $receiver, byte[] other, int fromIndex) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int limit = $receiver.getData$jvm().length - other.length;
        int i = Math.max(fromIndex, 0);
        if (i <= limit) {
            while (!Util.arrayRangeEquals($receiver.getData$jvm(), i, other, 0, other.length)) {
                if (i != limit) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    public static final int commonLastIndexOf(ByteString $receiver, byte[] other, int fromIndex) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        for (int i = Math.min(fromIndex, $receiver.getData$jvm().length - other.length); i >= 0; i--) {
            if (Util.arrayRangeEquals($receiver.getData$jvm(), i, other, 0, other.length)) {
                return i;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(ByteString $receiver, Object other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (other == $receiver) {
            return true;
        }
        if (!(other instanceof ByteString)) {
            return false;
        }
        if (((ByteString) other).size() != $receiver.getData$jvm().length || !((ByteString) other).rangeEquals(0, $receiver.getData$jvm(), 0, $receiver.getData$jvm().length)) {
            return false;
        }
        return true;
    }

    public static final int commonHashCode(ByteString $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        int result = $receiver.getHashCode$jvm();
        if (result != 0) {
            return result;
        }
        $receiver.setHashCode$jvm(Arrays.hashCode($receiver.getData$jvm()));
        return $receiver.getHashCode$jvm();
    }

    public static final int commonCompareTo(ByteString $receiver, ByteString other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int sizeA = $receiver.size();
        int sizeB = other.size();
        int i = 0;
        int size = Math.min(sizeA, sizeB);
        while (true) {
            int i2 = -1;
            if (i < size) {
                int byteA = $receiver.getByte(i) & 255;
                int byteB = other.getByte(i) & 255;
                if (byteA == byteB) {
                    i++;
                } else {
                    if (byteA >= byteB) {
                        i2 = 1;
                    }
                    return i2;
                }
            } else if (sizeA == sizeB) {
                return 0;
            } else {
                if (sizeA >= sizeB) {
                    i2 = 1;
                }
                return i2;
            }
        }
    }

    public static final ByteString getCOMMON_EMPTY() {
        return COMMON_EMPTY;
    }

    public static final ByteString commonOf(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new ByteString(copyOf);
    }

    public static final ByteString commonEncodeUtf8(String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        ByteString byteString = new ByteString(Platform.asUtf8ToByteArray($receiver));
        byteString.setUtf8$jvm($receiver);
        return byteString;
    }

    public static final ByteString commonDecodeBase64(String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        byte[] decoded = Base64.decodeBase64ToArray($receiver);
        if (decoded != null) {
            return new ByteString(decoded);
        }
        return null;
    }

    public static final ByteString commonDecodeHex(String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ($receiver.length() % 2 == 0) {
            byte[] result = new byte[($receiver.length() / 2)];
            int length = result.length;
            for (int i = 0; i < length; i++) {
                result[i] = (byte) ((decodeHexDigit($receiver.charAt(i * 2)) << 4) + decodeHexDigit($receiver.charAt((i * 2) + 1)));
            }
            return new ByteString(result);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected hex string: ");
        sb.append($receiver);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    private static final int decodeHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        if ('a' <= c && 'f' >= c) {
            return (c - 'a') + 10;
        }
        if ('A' <= c && 'F' >= c) {
            return (c - 'A') + 10;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected hex digit: ");
        sb.append(c);
        throw new IllegalArgumentException(sb.toString());
    }

    public static final String commonToString(ByteString $receiver) {
        String str;
        String str2;
        ByteString byteString = $receiver;
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        if ($receiver.getData$jvm().length == 0) {
            return "[size=0]";
        }
        int i = codePointIndexToCharIndex($receiver.getData$jvm(), 64);
        String str3 = "…]";
        String str4 = "[size=";
        if (i == -1) {
            if ($receiver.getData$jvm().length <= 64) {
                StringBuilder sb = new StringBuilder();
                sb.append("[hex=");
                sb.append($receiver.hex());
                sb.append(']');
                str2 = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str4);
                sb2.append($receiver.getData$jvm().length);
                sb2.append(" hex=");
                sb2.append(commonSubstring(byteString, 0, 64).hex());
                sb2.append(str3);
                str2 = sb2.toString();
            }
            return str2;
        }
        String text = $receiver.utf8();
        if (text != null) {
            String substring = text.substring(0, i);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String safeText = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
            if (i < text.length()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str4);
                sb3.append($receiver.getData$jvm().length);
                sb3.append(" text=");
                sb3.append(safeText);
                sb3.append(str3);
                str = sb3.toString();
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("[text=");
                sb4.append(safeText);
                sb4.append(']');
                str = sb4.toString();
            }
            return str;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:314:0x037a, code lost:
        if (65533(0xfffd) < 65536(0x10000)) goto L_0x03ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x03aa, code lost:
        if (r15 < 65536) goto L_0x03ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0082, code lost:
        if (31 < r9) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:522:0x05dc, code lost:
        if (65533(0xfffd) < 65536(0x10000)) goto L_0x05de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:548:0x061c, code lost:
        if (65533(0xfffd) < 65536(0x10000)) goto L_0x05de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:570:0x064d, code lost:
        if (r10 < 65536) goto L_0x05de;
     */
    /* JADX WARNING: Removed duplicated region for block: B:606:0x009a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int codePointIndexToCharIndex(byte[] r31, int r32) {
        /*
            r0 = r32
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = r31
            int r5 = r4.length
            r6 = r31
            r7 = 0
            r8 = r7
            r9 = r3
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
        L_0x0019:
            if (r9 >= r5) goto L_0x06a6
            byte r19 = r6[r9]
            r20 = -1
            r21 = 2
            r22 = 1
            if (r19 < 0) goto L_0x00b7
            r23 = r19
            int r24 = r2 + 1
            if (r2 != r0) goto L_0x002d
            return r1
        L_0x002d:
            r2 = r23
            r7 = 10
            if (r2 == r7) goto L_0x004d
            r7 = 13
            if (r2 == r7) goto L_0x004d
            if (r2 < 0) goto L_0x003f
            r7 = 31
            if (r7 >= r2) goto L_0x0048
        L_0x003f:
            r7 = 127(0x7f, float:1.78E-43)
            if (r7 <= r2) goto L_0x0044
            goto L_0x004a
        L_0x0044:
            r7 = 159(0x9f, float:2.23E-43)
            if (r7 < r2) goto L_0x004a
        L_0x0048:
            r7 = 1
            goto L_0x004b
        L_0x004a:
            r7 = 0
        L_0x004b:
            if (r7 != 0) goto L_0x0052
        L_0x004d:
            r7 = 65533(0xfffd, float:9.1831E-41)
            if (r2 != r7) goto L_0x0053
        L_0x0052:
            return r20
        L_0x0053:
            r7 = 65536(0x10000, float:9.18355E-41)
            if (r2 >= r7) goto L_0x0059
            r7 = 1
            goto L_0x005a
        L_0x0059:
            r7 = 2
        L_0x005a:
            int r1 = r1 + r7
            int r9 = r9 + 1
            r2 = r1
            r1 = r24
        L_0x0061:
            if (r9 >= r5) goto L_0x00a8
            byte r7 = r6[r9]
            if (r7 < 0) goto L_0x00a8
            int r7 = r9 + 1
            byte r9 = r6[r9]
            int r24 = r1 + 1
            if (r1 != r0) goto L_0x0070
            return r2
        L_0x0070:
            r1 = 10
            if (r9 == r1) goto L_0x0095
            r1 = 13
            if (r9 == r1) goto L_0x0095
            r1 = r17
            if (r9 < 0) goto L_0x0085
            r17 = r1
            r1 = 31
            if (r1 >= r9) goto L_0x0090
            goto L_0x0087
        L_0x0085:
            r17 = r1
        L_0x0087:
            r1 = 127(0x7f, float:1.78E-43)
            if (r1 <= r9) goto L_0x008c
            goto L_0x0092
        L_0x008c:
            r1 = 159(0x9f, float:2.23E-43)
            if (r1 < r9) goto L_0x0092
        L_0x0090:
            r1 = 1
            goto L_0x0093
        L_0x0092:
            r1 = 0
        L_0x0093:
            if (r1 != 0) goto L_0x009a
        L_0x0095:
            r1 = 65533(0xfffd, float:9.1831E-41)
            if (r9 != r1) goto L_0x009b
        L_0x009a:
            return r20
        L_0x009b:
            r1 = 65536(0x10000, float:9.18355E-41)
            if (r9 >= r1) goto L_0x00a1
            r1 = 1
            goto L_0x00a2
        L_0x00a1:
            r1 = 2
        L_0x00a2:
            int r2 = r2 + r1
            r9 = r7
            r1 = r24
            goto L_0x0061
        L_0x00a8:
            r24 = r3
            r27 = r5
            r26 = r6
            r29 = r8
            r30 = r2
            r2 = r1
            r1 = r30
            goto L_0x0698
        L_0x00b7:
            r7 = 5
            r24 = r19
            int r7 = r24 >> r7
            r24 = r3
            r3 = -2
            if (r7 != r3) goto L_0x01e7
            r3 = r6
            r7 = r12
            int r12 = r9 + 1
            if (r5 > r12) goto L_0x0101
            r4 = 65533(0xfffd, float:9.1831E-41)
            r12 = r4
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x00d0
            return r1
        L_0x00d0:
            r2 = 10
            if (r12 == r2) goto L_0x00ee
            r2 = 13
            if (r12 == r2) goto L_0x00ee
            if (r12 < 0) goto L_0x00e0
            r2 = 31
            if (r2 >= r12) goto L_0x00e9
        L_0x00e0:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r12) goto L_0x00e5
            goto L_0x00eb
        L_0x00e5:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r12) goto L_0x00eb
        L_0x00e9:
            r2 = 1
            goto L_0x00ec
        L_0x00eb:
            r2 = 0
        L_0x00ec:
            if (r2 != 0) goto L_0x00f3
        L_0x00ee:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r12 != r2) goto L_0x00f4
        L_0x00f3:
            return r20
        L_0x00f4:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r12 >= r2) goto L_0x00fa
            r21 = 1
        L_0x00fa:
            int r1 = r1 + r21
            r26 = r7
            goto L_0x01d9
        L_0x0101:
            byte r12 = r3[r9]
            int r18 = r9 + 1
            byte r4 = r3[r18]
            r18 = 192(0xc0, float:2.69E-43)
            r26 = r4
            r27 = r3
            r3 = r26 & r18
            r26 = r7
            r7 = 128(0x80, float:1.794E-43)
            if (r3 != r7) goto L_0x0118
            r3 = 1
            goto L_0x0119
        L_0x0118:
            r3 = 0
        L_0x0119:
            if (r3 != 0) goto L_0x0153
            r3 = 65533(0xfffd, float:9.1831E-41)
            r7 = r3
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0124
            return r1
        L_0x0124:
            r2 = 10
            if (r7 == r2) goto L_0x0142
            r2 = 13
            if (r7 == r2) goto L_0x0142
            if (r7 < 0) goto L_0x0134
            r2 = 31
            if (r2 >= r7) goto L_0x013d
        L_0x0134:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r7) goto L_0x0139
            goto L_0x013f
        L_0x0139:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r7) goto L_0x013f
        L_0x013d:
            r2 = 1
            goto L_0x0140
        L_0x013f:
            r2 = 0
        L_0x0140:
            if (r2 != 0) goto L_0x0147
        L_0x0142:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r7 != r2) goto L_0x0148
        L_0x0147:
            return r20
        L_0x0148:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r7 >= r2) goto L_0x014e
            r21 = 1
        L_0x014e:
            int r1 = r1 + r21
            goto L_0x01d9
        L_0x0153:
            r3 = r4 ^ 3968(0xf80, float:5.56E-42)
            int r7 = r12 << 6
            r3 = r3 ^ r7
            r7 = 128(0x80, float:1.794E-43)
            if (r3 >= r7) goto L_0x019c
            r7 = 65533(0xfffd, float:9.1831E-41)
            r25 = r7
            int r28 = r2 + 1
            if (r2 != r0) goto L_0x016a
            return r1
        L_0x016a:
            r2 = r25
            r25 = r4
            r4 = 10
            if (r2 == r4) goto L_0x018c
            r4 = 13
            if (r2 == r4) goto L_0x018c
            if (r2 < 0) goto L_0x017e
            r4 = 31
            if (r4 >= r2) goto L_0x0187
        L_0x017e:
            r4 = 127(0x7f, float:1.78E-43)
            if (r4 <= r2) goto L_0x0183
            goto L_0x0189
        L_0x0183:
            r4 = 159(0x9f, float:2.23E-43)
            if (r4 < r2) goto L_0x0189
        L_0x0187:
            r4 = 1
            goto L_0x018a
        L_0x0189:
            r4 = 0
        L_0x018a:
            if (r4 != 0) goto L_0x0191
        L_0x018c:
            r4 = 65533(0xfffd, float:9.1831E-41)
            if (r2 != r4) goto L_0x0192
        L_0x0191:
            return r20
        L_0x0192:
            r4 = 65536(0x10000, float:9.18355E-41)
            if (r2 >= r4) goto L_0x0197
            goto L_0x0199
        L_0x0197:
            r22 = 2
        L_0x0199:
            int r1 = r1 + r22
            goto L_0x01d4
        L_0x019c:
            r25 = r4
            r4 = r3
            r7 = r10
            r10 = r4
            int r28 = r2 + 1
            if (r2 != r0) goto L_0x01a6
            return r1
        L_0x01a6:
            r2 = 10
            if (r10 == r2) goto L_0x01c4
            r2 = 13
            if (r10 == r2) goto L_0x01c4
            if (r10 < 0) goto L_0x01b6
            r2 = 31
            if (r2 >= r10) goto L_0x01bf
        L_0x01b6:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r10) goto L_0x01bb
            goto L_0x01c1
        L_0x01bb:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r10) goto L_0x01c1
        L_0x01bf:
            r2 = 1
            goto L_0x01c2
        L_0x01c1:
            r2 = 0
        L_0x01c2:
            if (r2 != 0) goto L_0x01c9
        L_0x01c4:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r10 != r2) goto L_0x01ca
        L_0x01c9:
            return r20
        L_0x01ca:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r10 >= r2) goto L_0x01cf
            goto L_0x01d1
        L_0x01cf:
            r22 = 2
        L_0x01d1:
            int r1 = r1 + r22
            r10 = r7
        L_0x01d4:
            r25 = r28
            r22 = 2
        L_0x01d9:
            int r9 = r9 + r22
            r27 = r5
            r29 = r8
            r2 = r25
            r12 = r26
            r26 = r6
            goto L_0x0698
        L_0x01e7:
            r4 = 4
            r7 = r19
            int r4 = r7 >> r4
            if (r4 != r3) goto L_0x03c4
            r3 = r6
            r4 = r13
            int r13 = r9 + 2
            if (r5 > r13) goto L_0x024e
            r7 = 65533(0xfffd, float:9.1831E-41)
            r13 = r7
            int r26 = r2 + 1
            if (r2 != r0) goto L_0x01fd
            return r1
        L_0x01fd:
            r2 = 10
            if (r13 == r2) goto L_0x021b
            r2 = 13
            if (r13 == r2) goto L_0x021b
            if (r13 < 0) goto L_0x020d
            r2 = 31
            if (r2 >= r13) goto L_0x0216
        L_0x020d:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r13) goto L_0x0212
            goto L_0x0218
        L_0x0212:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r13) goto L_0x0218
        L_0x0216:
            r2 = 1
            goto L_0x0219
        L_0x0218:
            r2 = 0
        L_0x0219:
            if (r2 != 0) goto L_0x0220
        L_0x021b:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r13 != r2) goto L_0x0221
        L_0x0220:
            return r20
        L_0x0221:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r13 >= r2) goto L_0x0227
            r2 = 1
            goto L_0x0228
        L_0x0227:
            r2 = 2
        L_0x0228:
            int r1 = r1 + r2
            int r2 = r9 + 1
            if (r5 <= r2) goto L_0x0246
            int r2 = r9 + 1
            byte r2 = r3[r2]
            r7 = 192(0xc0, float:2.69E-43)
            r13 = r2
            r7 = r7 & r13
            r13 = 128(0x80, float:1.794E-43)
            if (r7 != r13) goto L_0x023c
            r2 = 1
            goto L_0x023d
        L_0x023c:
            r2 = 0
        L_0x023d:
            if (r2 != 0) goto L_0x0240
            goto L_0x0246
        L_0x0240:
            r27 = r4
            r25 = r26
            goto L_0x03b6
        L_0x0246:
            r27 = r4
            r25 = r26
            r21 = 1
            goto L_0x03b6
        L_0x024e:
            byte r13 = r3[r9]
            int r18 = r9 + 1
            byte r18 = r3[r18]
            r26 = 192(0xc0, float:2.69E-43)
            r27 = r18
            r7 = r27 & r26
            r27 = r4
            r4 = 128(0x80, float:1.794E-43)
            if (r7 != r4) goto L_0x0263
            r4 = 1
            goto L_0x0264
        L_0x0263:
            r4 = 0
        L_0x0264:
            if (r4 != 0) goto L_0x02a2
            r4 = 65533(0xfffd, float:9.1831E-41)
            r7 = r4
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x026f
            return r1
        L_0x026f:
            r2 = 10
            if (r7 == r2) goto L_0x028d
            r2 = 13
            if (r7 == r2) goto L_0x028d
            if (r7 < 0) goto L_0x027f
            r2 = 31
            if (r2 >= r7) goto L_0x0288
        L_0x027f:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r7) goto L_0x0284
            goto L_0x028a
        L_0x0284:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r7) goto L_0x028a
        L_0x0288:
            r2 = 1
            goto L_0x028b
        L_0x028a:
            r2 = 0
        L_0x028b:
            if (r2 != 0) goto L_0x0292
        L_0x028d:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r7 != r2) goto L_0x0293
        L_0x0292:
            return r20
        L_0x0293:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r7 >= r2) goto L_0x0299
            r21 = 1
        L_0x0299:
            int r1 = r1 + r21
            r18 = r26
            r21 = 1
            goto L_0x03b6
        L_0x02a2:
            int r4 = r9 + 2
            byte r4 = r3[r4]
            r7 = 192(0xc0, float:2.69E-43)
            r15 = r4
            r26 = r3
            r3 = r15 & r7
            r29 = r7
            r7 = 128(0x80, float:1.794E-43)
            if (r3 != r7) goto L_0x02b6
            r3 = 1
            goto L_0x02b7
        L_0x02b6:
            r3 = 0
        L_0x02b7:
            if (r3 != 0) goto L_0x02f6
            r3 = 65533(0xfffd, float:9.1831E-41)
            r7 = r3
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x02c2
            return r1
        L_0x02c2:
            r2 = 10
            if (r7 == r2) goto L_0x02e0
            r2 = 13
            if (r7 == r2) goto L_0x02e0
            if (r7 < 0) goto L_0x02d2
            r2 = 31
            if (r2 >= r7) goto L_0x02db
        L_0x02d2:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r7) goto L_0x02d7
            goto L_0x02dd
        L_0x02d7:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r7) goto L_0x02dd
        L_0x02db:
            r2 = 1
            goto L_0x02de
        L_0x02dd:
            r2 = 0
        L_0x02de:
            if (r2 != 0) goto L_0x02e5
        L_0x02e0:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r7 != r2) goto L_0x02e6
        L_0x02e5:
            return r20
        L_0x02e6:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r7 >= r2) goto L_0x02eb
            goto L_0x02ed
        L_0x02eb:
            r22 = 2
        L_0x02ed:
            int r1 = r1 + r22
            r18 = r15
            r15 = r29
            goto L_0x03b6
        L_0x02f6:
            r3 = -123008(0xfffffffffffe1f80, float:NaN)
            r3 = r3 ^ r4
            int r7 = r18 << 6
            r3 = r3 ^ r7
            int r7 = r13 << 12
            r3 = r3 ^ r7
            r7 = 2048(0x800, float:2.87E-42)
            if (r3 >= r7) goto L_0x0340
            r7 = 65533(0xfffd, float:9.1831E-41)
            r15 = r7
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0310
            return r1
        L_0x0310:
            r2 = 10
            if (r15 == r2) goto L_0x032e
            r2 = 13
            if (r15 == r2) goto L_0x032e
            if (r15 < 0) goto L_0x0320
            r2 = 31
            if (r2 >= r15) goto L_0x0329
        L_0x0320:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r15) goto L_0x0325
            goto L_0x032b
        L_0x0325:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r15) goto L_0x032b
        L_0x0329:
            r2 = 1
            goto L_0x032c
        L_0x032b:
            r2 = 0
        L_0x032c:
            if (r2 != 0) goto L_0x0333
        L_0x032e:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r15 != r2) goto L_0x0334
        L_0x0333:
            return r20
        L_0x0334:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r15 >= r2) goto L_0x033a
            r21 = 1
        L_0x033a:
            int r1 = r1 + r21
            r15 = r29
            goto L_0x03b1
        L_0x0340:
            r7 = 57343(0xdfff, float:8.0355E-41)
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r15 <= r3) goto L_0x0349
            goto L_0x037d
        L_0x0349:
            if (r7 < r3) goto L_0x037d
            r7 = 65533(0xfffd, float:9.1831E-41)
            r15 = r7
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0354
            return r1
        L_0x0354:
            r2 = 10
            if (r15 == r2) goto L_0x0372
            r2 = 13
            if (r15 == r2) goto L_0x0372
            if (r15 < 0) goto L_0x0364
            r2 = 31
            if (r2 >= r15) goto L_0x036d
        L_0x0364:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r15) goto L_0x0369
            goto L_0x036f
        L_0x0369:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r15) goto L_0x036f
        L_0x036d:
            r2 = 1
            goto L_0x0370
        L_0x036f:
            r2 = 0
        L_0x0370:
            if (r2 != 0) goto L_0x0377
        L_0x0372:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r15 != r2) goto L_0x0378
        L_0x0377:
            return r20
        L_0x0378:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r15 >= r2) goto L_0x03ae
            goto L_0x03ac
        L_0x037d:
            r7 = r3
            r15 = r7
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0384
            return r1
        L_0x0384:
            r2 = 10
            if (r15 == r2) goto L_0x03a2
            r2 = 13
            if (r15 == r2) goto L_0x03a2
            if (r15 < 0) goto L_0x0394
            r2 = 31
            if (r2 >= r15) goto L_0x039d
        L_0x0394:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r15) goto L_0x0399
            goto L_0x039f
        L_0x0399:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r15) goto L_0x039f
        L_0x039d:
            r2 = 1
            goto L_0x03a0
        L_0x039f:
            r2 = 0
        L_0x03a0:
            if (r2 != 0) goto L_0x03a7
        L_0x03a2:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r15 != r2) goto L_0x03a8
        L_0x03a7:
            return r20
        L_0x03a8:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r15 >= r2) goto L_0x03ae
        L_0x03ac:
            r21 = 1
        L_0x03ae:
            int r1 = r1 + r21
            r15 = r3
        L_0x03b1:
            r18 = r3
            r21 = 3
        L_0x03b6:
            int r9 = r9 + r21
            r26 = r6
            r29 = r8
            r2 = r25
            r13 = r27
            r27 = r5
            goto L_0x0698
        L_0x03c4:
            r4 = 3
            r7 = r19
            int r4 = r7 >> r4
            if (r4 != r3) goto L_0x0658
            r3 = r6
            r4 = r16
            int r7 = r9 + 3
            if (r5 > r7) goto L_0x045b
            r7 = 65533(0xfffd, float:9.1831E-41)
            r16 = r7
            int r26 = r2 + 1
            if (r2 != r0) goto L_0x03dc
            return r1
        L_0x03dc:
            r2 = r16
            r16 = r4
            r4 = 10
            if (r2 == r4) goto L_0x03fe
            r4 = 13
            if (r2 == r4) goto L_0x03fe
            if (r2 < 0) goto L_0x03f0
            r4 = 31
            if (r4 >= r2) goto L_0x03f9
        L_0x03f0:
            r4 = 127(0x7f, float:1.78E-43)
            if (r4 <= r2) goto L_0x03f5
            goto L_0x03fb
        L_0x03f5:
            r4 = 159(0x9f, float:2.23E-43)
            if (r4 < r2) goto L_0x03fb
        L_0x03f9:
            r4 = 1
            goto L_0x03fc
        L_0x03fb:
            r4 = 0
        L_0x03fc:
            if (r4 != 0) goto L_0x0403
        L_0x03fe:
            r4 = 65533(0xfffd, float:9.1831E-41)
            if (r2 != r4) goto L_0x0404
        L_0x0403:
            return r20
        L_0x0404:
            r4 = 65536(0x10000, float:9.18355E-41)
            if (r2 >= r4) goto L_0x040a
            r4 = 1
            goto L_0x040b
        L_0x040a:
            r4 = 2
        L_0x040b:
            int r1 = r1 + r4
            int r2 = r9 + 1
            if (r5 <= r2) goto L_0x044f
            int r2 = r9 + 1
            byte r2 = r3[r2]
            r4 = 192(0xc0, float:2.69E-43)
            r7 = r2
            r4 = r4 & r7
            r7 = 128(0x80, float:1.794E-43)
            if (r4 != r7) goto L_0x041f
            r2 = 1
            goto L_0x0420
        L_0x041f:
            r2 = 0
        L_0x0420:
            if (r2 != 0) goto L_0x0423
            goto L_0x044f
        L_0x0423:
            int r2 = r9 + 2
            if (r5 <= r2) goto L_0x0445
            int r2 = r9 + 2
            byte r2 = r3[r2]
            r4 = 192(0xc0, float:2.69E-43)
            r7 = r2
            r4 = r4 & r7
            r7 = 128(0x80, float:1.794E-43)
            if (r4 != r7) goto L_0x0434
            goto L_0x0436
        L_0x0434:
            r22 = 0
        L_0x0436:
            if (r22 != 0) goto L_0x0439
            goto L_0x0445
        L_0x0439:
            r27 = r5
            r29 = r8
            r25 = r26
            r21 = 3
            r26 = r6
            goto L_0x0653
        L_0x0445:
            r27 = r5
            r29 = r8
            r25 = r26
            r26 = r6
            goto L_0x0653
        L_0x044f:
            r27 = r5
            r29 = r8
            r25 = r26
            r21 = 1
            r26 = r6
            goto L_0x0653
        L_0x045b:
            r16 = r4
            byte r4 = r3[r9]
            int r7 = r9 + 1
            byte r7 = r3[r7]
            r18 = 192(0xc0, float:2.69E-43)
            r26 = r7
            r27 = r5
            r5 = r26 & r18
            r26 = r6
            r6 = 128(0x80, float:1.794E-43)
            if (r5 != r6) goto L_0x0474
            r5 = 1
            goto L_0x0475
        L_0x0474:
            r5 = 0
        L_0x0475:
            if (r5 != 0) goto L_0x04b3
            r5 = 65533(0xfffd, float:9.1831E-41)
            r6 = r5
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0480
            return r1
        L_0x0480:
            r2 = 10
            if (r6 == r2) goto L_0x049e
            r2 = 13
            if (r6 == r2) goto L_0x049e
            if (r6 < 0) goto L_0x0490
            r2 = 31
            if (r2 >= r6) goto L_0x0499
        L_0x0490:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r6) goto L_0x0495
            goto L_0x049b
        L_0x0495:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r6) goto L_0x049b
        L_0x0499:
            r2 = 1
            goto L_0x049c
        L_0x049b:
            r2 = 0
        L_0x049c:
            if (r2 != 0) goto L_0x04a3
        L_0x049e:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r6 != r2) goto L_0x04a4
        L_0x04a3:
            return r20
        L_0x04a4:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r2) goto L_0x04aa
            r21 = 1
        L_0x04aa:
            int r1 = r1 + r21
            r29 = r8
            r21 = 1
            goto L_0x0653
        L_0x04b3:
            int r5 = r9 + 2
            byte r5 = r3[r5]
            r6 = 192(0xc0, float:2.69E-43)
            r18 = r5
            r15 = r6
            r6 = r18 & r15
            r29 = r8
            r8 = 128(0x80, float:1.794E-43)
            if (r6 != r8) goto L_0x04c7
            r6 = 1
            goto L_0x04c8
        L_0x04c7:
            r6 = 0
        L_0x04c8:
            if (r6 != 0) goto L_0x0503
            r6 = 65533(0xfffd, float:9.1831E-41)
            r8 = r6
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x04d3
            return r1
        L_0x04d3:
            r2 = 10
            if (r8 == r2) goto L_0x04f1
            r2 = 13
            if (r8 == r2) goto L_0x04f1
            if (r8 < 0) goto L_0x04e3
            r2 = 31
            if (r2 >= r8) goto L_0x04ec
        L_0x04e3:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r8) goto L_0x04e8
            goto L_0x04ee
        L_0x04e8:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r8) goto L_0x04ee
        L_0x04ec:
            r2 = 1
            goto L_0x04ef
        L_0x04ee:
            r2 = 0
        L_0x04ef:
            if (r2 != 0) goto L_0x04f6
        L_0x04f1:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r8 != r2) goto L_0x04f7
        L_0x04f6:
            return r20
        L_0x04f7:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r8 >= r2) goto L_0x04fc
            goto L_0x04fe
        L_0x04fc:
            r22 = 2
        L_0x04fe:
            int r1 = r1 + r22
            goto L_0x0653
        L_0x0503:
            int r6 = r9 + 3
            byte r18 = r3[r6]
            r6 = 192(0xc0, float:2.69E-43)
            r15 = r18
            r14 = r6
            r6 = r15 & r14
            r8 = 128(0x80, float:1.794E-43)
            if (r6 != r8) goto L_0x0515
            r6 = 1
            goto L_0x0516
        L_0x0515:
            r6 = 0
        L_0x0516:
            if (r6 != 0) goto L_0x0552
            r6 = 65533(0xfffd, float:9.1831E-41)
            r8 = r6
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0521
            return r1
        L_0x0521:
            r2 = 10
            if (r8 == r2) goto L_0x053f
            r2 = 13
            if (r8 == r2) goto L_0x053f
            if (r8 < 0) goto L_0x0531
            r2 = 31
            if (r2 >= r8) goto L_0x053a
        L_0x0531:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r8) goto L_0x0536
            goto L_0x053c
        L_0x0536:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r8) goto L_0x053c
        L_0x053a:
            r2 = 1
            goto L_0x053d
        L_0x053c:
            r2 = 0
        L_0x053d:
            if (r2 != 0) goto L_0x0544
        L_0x053f:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r8 != r2) goto L_0x0545
        L_0x0544:
            return r20
        L_0x0545:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r8 >= r2) goto L_0x054b
            r21 = 1
        L_0x054b:
            int r1 = r1 + r21
            r21 = 3
            goto L_0x0653
        L_0x0552:
            r6 = 3678080(0x381f80, float:5.154088E-39)
            r6 = r18 ^ r6
            int r8 = r5 << 6
            r6 = r6 ^ r8
            int r8 = r7 << 12
            r6 = r6 ^ r8
            int r8 = r4 << 18
            r6 = r6 ^ r8
            r15 = r6
            r6 = 1114111(0x10ffff, float:1.561202E-39)
            if (r15 <= r6) goto L_0x05a1
            r6 = 65533(0xfffd, float:9.1831E-41)
            r8 = r10
            r10 = r6
            int r25 = r2 + 1
            if (r2 != r0) goto L_0x0572
            return r1
        L_0x0572:
            r2 = 10
            if (r10 == r2) goto L_0x0590
            r2 = 13
            if (r10 == r2) goto L_0x0590
            if (r10 < 0) goto L_0x0582
            r2 = 31
            if (r2 >= r10) goto L_0x058b
        L_0x0582:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r10) goto L_0x0587
            goto L_0x058d
        L_0x0587:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r10) goto L_0x058d
        L_0x058b:
            r2 = 1
            goto L_0x058e
        L_0x058d:
            r2 = 0
        L_0x058e:
            if (r2 != 0) goto L_0x0595
        L_0x0590:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r10 != r2) goto L_0x0596
        L_0x0595:
            return r20
        L_0x0596:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r10 >= r2) goto L_0x059c
            r21 = 1
        L_0x059c:
            int r1 = r1 + r21
            r10 = r8
            goto L_0x0650
        L_0x05a1:
            r6 = 57343(0xdfff, float:8.0355E-41)
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r8 <= r15) goto L_0x05aa
            goto L_0x05e8
        L_0x05aa:
            if (r6 < r15) goto L_0x05e8
            r6 = 65533(0xfffd, float:9.1831E-41)
            r8 = r10
            r10 = r6
            int r14 = r2 + 1
            if (r2 != r0) goto L_0x05b6
            return r1
        L_0x05b6:
            r2 = 10
            if (r10 == r2) goto L_0x05d4
            r2 = 13
            if (r10 == r2) goto L_0x05d4
            if (r10 < 0) goto L_0x05c6
            r2 = 31
            if (r2 >= r10) goto L_0x05cf
        L_0x05c6:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r10) goto L_0x05cb
            goto L_0x05d1
        L_0x05cb:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r10) goto L_0x05d1
        L_0x05cf:
            r2 = 1
            goto L_0x05d2
        L_0x05d1:
            r2 = 0
        L_0x05d2:
            if (r2 != 0) goto L_0x05d9
        L_0x05d4:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r10 != r2) goto L_0x05da
        L_0x05d9:
            return r20
        L_0x05da:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r10 >= r2) goto L_0x05e0
        L_0x05de:
            r21 = 1
        L_0x05e0:
            int r1 = r1 + r21
            r10 = r8
            r25 = r14
            r14 = r15
            goto L_0x0650
        L_0x05e8:
            r6 = 65536(0x10000, float:9.18355E-41)
            if (r15 >= r6) goto L_0x061f
            r6 = 65533(0xfffd, float:9.1831E-41)
            r8 = r10
            r10 = r6
            int r14 = r2 + 1
            if (r2 != r0) goto L_0x05f6
            return r1
        L_0x05f6:
            r2 = 10
            if (r10 == r2) goto L_0x0614
            r2 = 13
            if (r10 == r2) goto L_0x0614
            if (r10 < 0) goto L_0x0606
            r2 = 31
            if (r2 >= r10) goto L_0x060f
        L_0x0606:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r10) goto L_0x060b
            goto L_0x0611
        L_0x060b:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r10) goto L_0x0611
        L_0x060f:
            r2 = 1
            goto L_0x0612
        L_0x0611:
            r2 = 0
        L_0x0612:
            if (r2 != 0) goto L_0x0619
        L_0x0614:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r10 != r2) goto L_0x061a
        L_0x0619:
            return r20
        L_0x061a:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r10 >= r2) goto L_0x05e0
            goto L_0x05de
        L_0x061f:
            r6 = r15
            r8 = r10
            r10 = r6
            int r14 = r2 + 1
            if (r2 != r0) goto L_0x0627
            return r1
        L_0x0627:
            r2 = 10
            if (r10 == r2) goto L_0x0645
            r2 = 13
            if (r10 == r2) goto L_0x0645
            if (r10 < 0) goto L_0x0637
            r2 = 31
            if (r2 >= r10) goto L_0x0640
        L_0x0637:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r10) goto L_0x063c
            goto L_0x0642
        L_0x063c:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r10) goto L_0x0642
        L_0x0640:
            r2 = 1
            goto L_0x0643
        L_0x0642:
            r2 = 0
        L_0x0643:
            if (r2 != 0) goto L_0x064a
        L_0x0645:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r10 != r2) goto L_0x064b
        L_0x064a:
            return r20
        L_0x064b:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r10 >= r2) goto L_0x05e0
            goto L_0x05de
        L_0x0650:
            r21 = 4
        L_0x0653:
            int r9 = r9 + r21
            r2 = r25
            goto L_0x0698
        L_0x0658:
            r27 = r5
            r26 = r6
            r29 = r8
            r3 = 65533(0xfffd, float:9.1831E-41)
            r4 = r11
            int r5 = r2 + 1
            if (r2 != r0) goto L_0x0667
            return r1
        L_0x0667:
            r2 = 10
            if (r3 == r2) goto L_0x0685
            r2 = 13
            if (r3 == r2) goto L_0x0685
            if (r3 < 0) goto L_0x0677
            r2 = 31
            if (r2 >= r3) goto L_0x0680
        L_0x0677:
            r2 = 127(0x7f, float:1.78E-43)
            if (r2 <= r3) goto L_0x067c
            goto L_0x0682
        L_0x067c:
            r2 = 159(0x9f, float:2.23E-43)
            if (r2 < r3) goto L_0x0682
        L_0x0680:
            r2 = 1
            goto L_0x0683
        L_0x0682:
            r2 = 0
        L_0x0683:
            if (r2 != 0) goto L_0x068a
        L_0x0685:
            r2 = 65533(0xfffd, float:9.1831E-41)
            if (r3 != r2) goto L_0x068b
        L_0x068a:
            return r20
        L_0x068b:
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r3 >= r2) goto L_0x0691
            r21 = 1
        L_0x0691:
            int r1 = r1 + r21
            int r9 = r9 + 1
            r11 = r4
            r2 = r5
        L_0x0698:
            r4 = r31
            r3 = r24
            r6 = r26
            r5 = r27
            r8 = r29
            r7 = 0
            goto L_0x0019
        L_0x06a6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ByteStringKt.codePointIndexToCharIndex(byte[], int):int");
    }
}
