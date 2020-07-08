package okio;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u0007H\u0000\u001a\u0016\u0010\b\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\n"}, mo24952d2 = {"BASE64", "", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "decodeBase64ToArray", "", "encodeBase64", "map", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* renamed from: okio.-Base64 reason: invalid class name */
/* compiled from: -Base64.kt */
public final class Base64 {
    private static final byte[] BASE64 = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$jvm();
    private static final byte[] BASE64_URL_SAFE = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$jvm();

    public static final byte[] getBASE64() {
        return BASE64;
    }

    public static final byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }

    public static final byte[] decodeBase64ToArray(String $receiver) {
        int bits;
        String str = $receiver;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        int limit = $receiver.length();
        while (limit > 0) {
            char c = str.charAt(limit - 1);
            if (c != '=' && c != 10 && c != 13 && c != ' ' && c != 9) {
                break;
            }
            limit--;
        }
        byte[] out = new byte[((int) ((((long) limit) * 6) / 8))];
        int inCount = 0;
        int outCount = 0;
        int outCount2 = 0;
        for (int pos = 0; pos < limit; pos++) {
            char c2 = str.charAt(pos);
            if ('A' <= c2 && 'Z' >= c2) {
                bits = c2 - 'A';
            } else if ('a' <= c2 && 'z' >= c2) {
                bits = c2 - 'G';
            } else if ('0' <= c2 && '9' >= c2) {
                bits = c2 + 4;
            } else if (c2 == '+' || c2 == '-') {
                bits = 62;
            } else if (c2 == '/' || c2 == '_') {
                bits = 63;
            } else {
                if (!(c2 == 10 || c2 == 13 || c2 == ' ' || c2 == 9)) {
                    return null;
                }
            }
            int word = (outCount2 << 6) | bits;
            inCount++;
            if (inCount % 4 == 0) {
                int outCount3 = outCount + 1;
                out[outCount] = (byte) (word >> 16);
                int outCount4 = outCount3 + 1;
                out[outCount3] = (byte) (word >> 8);
                int outCount5 = outCount4 + 1;
                out[outCount4] = (byte) word;
                outCount = outCount5;
                outCount2 = word;
            } else {
                outCount2 = word;
            }
        }
        int lastWordChars = inCount % 4;
        if (lastWordChars == 1) {
            return null;
        }
        if (lastWordChars == 2) {
            int outCount6 = outCount + 1;
            out[outCount] = (byte) ((outCount2 << 12) >> 16);
            outCount = outCount6;
        } else if (lastWordChars == 3) {
            int word2 = outCount2 << 6;
            int outCount7 = outCount + 1;
            out[outCount] = (byte) (word2 >> 16);
            outCount = outCount7 + 1;
            out[outCount7] = (byte) (word2 >> 8);
        }
        if (outCount == out.length) {
            return out;
        }
        byte[] prefix = new byte[outCount];
        Platform.arraycopy(out, 0, prefix, 0, outCount);
        return prefix;
    }

    public static /* bridge */ /* synthetic */ String encodeBase64$default(byte[] bArr, byte[] bArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr2 = BASE64;
        }
        return encodeBase64(bArr, bArr2);
    }

    public static final String encodeBase64(byte[] $receiver, byte[] map) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(map, "map");
        byte[] out = new byte[((($receiver.length + 2) / 3) * 4)];
        int index = 0;
        int end = $receiver.length - ($receiver.length % 3);
        int b0 = 0;
        while (b0 < end) {
            int i = b0 + 1;
            byte b02 = $receiver[b0];
            int i2 = i + 1;
            byte b1 = $receiver[i];
            int i3 = i2 + 1;
            byte i4 = $receiver[i2];
            int index2 = index + 1;
            out[index] = map[(b02 & UByte.MAX_VALUE) >> 2];
            int index3 = index2 + 1;
            out[index2] = map[((b02 & 3) << 4) | ((b1 & UByte.MAX_VALUE) >> 4)];
            int index4 = index3 + 1;
            out[index3] = map[((b1 & 15) << 2) | ((i4 & UByte.MAX_VALUE) >> 6)];
            index = index4 + 1;
            out[index4] = map[i4 & Utf8.REPLACEMENT_BYTE];
            b0 = i3;
        }
        int length = $receiver.length - end;
        if (length == 1) {
            byte b03 = $receiver[b0];
            int index5 = index + 1;
            out[index] = map[(b03 & UByte.MAX_VALUE) >> 2];
            int index6 = index5 + 1;
            out[index5] = map[(b03 & 3) << 4];
            int index7 = index6 + 1;
            byte b = (byte) 61;
            out[index6] = b;
            out[index7] = b;
        } else if (length == 2) {
            int i5 = b0 + 1;
            byte b04 = $receiver[b0];
            byte b12 = $receiver[i5];
            int index8 = index + 1;
            out[index] = map[(b04 & UByte.MAX_VALUE) >> 2];
            int index9 = index8 + 1;
            out[index8] = map[((b04 & 3) << 4) | ((b12 & UByte.MAX_VALUE) >> 4)];
            int index10 = index9 + 1;
            out[index9] = map[(b12 & 15) << 2];
            out[index10] = (byte) 61;
            int b05 = i5;
            int i6 = index10;
        }
        return Platform.toUtf8String(out);
    }
}
