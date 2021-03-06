package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import kotlin.UByte;
import okhttp3.internal.cache.DiskLruCache;
import okio.Utf8;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED = new byte[128];
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, Utf8.REPLACEMENT_BYTE, 123, 125, 39, 0};

    static {
        Arrays.fill(MIXED, -1);
        int i = 0;
        while (true) {
            byte[] bArr = TEXT_MIXED_RAW;
            if (i >= bArr.length) {
                break;
            }
            byte b = bArr[i];
            byte b2 = b;
            if (b > 0) {
                MIXED[b2] = (byte) i;
            }
            i++;
        }
        Arrays.fill(PUNCTUATION, -1);
        int i2 = 0;
        while (true) {
            byte[] bArr2 = TEXT_PUNCTUATION_RAW;
            if (i2 < bArr2.length) {
                byte b3 = bArr2[i2];
                byte b4 = b3;
                if (b3 > 0) {
                    PUNCTUATION[b4] = (byte) i2;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String encodeHighLevel(String msg, Compaction compaction, Charset encoding) throws WriterException {
        String str = msg;
        Compaction compaction2 = compaction;
        Charset encoding2 = encoding;
        StringBuilder sb = new StringBuilder(msg.length());
        if (encoding2 == null) {
            encoding2 = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(encoding2)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(encoding.name());
            CharacterSetECI eci = characterSetECIByName;
            if (characterSetECIByName != null) {
                encodingECI(eci.getValue(), sb);
            }
        }
        int len = msg.length();
        int p = 0;
        int textSubMode = 0;
        if (compaction2 == Compaction.TEXT) {
            encodeText(str, 0, len, sb, 0);
        } else if (compaction2 == Compaction.BYTE) {
            byte[] bytes = str.getBytes(encoding2);
            encodeBinary(bytes, 0, bytes.length, 1, sb);
        } else if (compaction2 == Compaction.NUMERIC) {
            sb.append(902);
            encodeNumeric(str, 0, len, sb);
        } else {
            int encodingMode = 0;
            while (p < len) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, p);
                int n = determineConsecutiveDigitCount;
                if (determineConsecutiveDigitCount >= 13) {
                    sb.append(902);
                    encodingMode = 2;
                    textSubMode = 0;
                    encodeNumeric(str, p, n, sb);
                    p += n;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, p);
                    int t = determineConsecutiveTextCount;
                    if (determineConsecutiveTextCount < 5) {
                        if (n != len) {
                            int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, p, encoding2);
                            int b = determineConsecutiveBinaryCount;
                            if (determineConsecutiveBinaryCount == 0) {
                                b = 1;
                            }
                            byte[] bytes2 = str.substring(p, p + b).getBytes(encoding2);
                            byte[] bytes3 = bytes2;
                            if (bytes2.length == 1 && encodingMode == 0) {
                                encodeBinary(bytes3, 0, 1, 0, sb);
                            } else {
                                encodeBinary(bytes3, 0, bytes3.length, encodingMode, sb);
                                encodingMode = 1;
                                textSubMode = 0;
                            }
                            p += b;
                        }
                    }
                    if (encodingMode != 0) {
                        sb.append(900);
                        encodingMode = 0;
                        textSubMode = 0;
                    }
                    textSubMode = encodeText(str, p, t, sb, textSubMode);
                    p += t;
                }
            }
        }
        return sb.toString();
    }

    private static int encodeText(CharSequence msg, int startpos, int count, StringBuilder sb, int initialSubmode) {
        CharSequence charSequence = msg;
        int i = count;
        StringBuilder sb2 = sb;
        StringBuilder tmp = new StringBuilder(i);
        int submode = initialSubmode;
        int idx = 0;
        while (true) {
            char ch = charSequence.charAt(startpos + idx);
            if (submode != 0) {
                if (submode != 1) {
                    if (submode != 2) {
                        if (isPunctuation(ch)) {
                            tmp.append((char) PUNCTUATION[ch]);
                        } else {
                            submode = 0;
                            tmp.append(29);
                        }
                    } else if (isMixed(ch)) {
                        tmp.append((char) MIXED[ch]);
                    } else if (isAlphaUpper(ch)) {
                        submode = 0;
                        tmp.append(28);
                    } else if (isAlphaLower(ch)) {
                        submode = 1;
                        tmp.append(27);
                    } else if (startpos + idx + 1 >= i || !isPunctuation(charSequence.charAt(startpos + idx + 1))) {
                        tmp.append(29);
                        tmp.append((char) PUNCTUATION[ch]);
                    } else {
                        submode = 3;
                        tmp.append(25);
                    }
                } else if (isAlphaLower(ch)) {
                    if (ch == ' ') {
                        tmp.append(26);
                    } else {
                        tmp.append((char) (ch - 'a'));
                    }
                } else if (isAlphaUpper(ch)) {
                    tmp.append(27);
                    tmp.append((char) (ch - 'A'));
                } else if (isMixed(ch)) {
                    submode = 2;
                    tmp.append(28);
                } else {
                    tmp.append(29);
                    tmp.append((char) PUNCTUATION[ch]);
                }
            } else if (isAlphaUpper(ch)) {
                if (ch == ' ') {
                    tmp.append(26);
                } else {
                    tmp.append((char) (ch - 'A'));
                }
            } else if (isAlphaLower(ch)) {
                submode = 1;
                tmp.append(27);
            } else if (isMixed(ch)) {
                submode = 2;
                tmp.append(28);
            } else {
                tmp.append(29);
                tmp.append((char) PUNCTUATION[ch]);
            }
            idx++;
            if (idx >= i) {
                break;
            }
        }
        char h = 0;
        int len = tmp.length();
        for (int i2 = 0; i2 < len; i2++) {
            if (i2 % 2 != 0) {
                h = (char) ((h * 30) + tmp.charAt(i2));
                sb2.append(h);
            } else {
                h = tmp.charAt(i2);
            }
        }
        if (len % 2 != 0) {
            sb2.append((char) ((h * 30) + 29));
        }
        return submode;
    }

    private static void encodeBinary(byte[] bytes, int startpos, int count, int startmode, StringBuilder sb) {
        if (count == 1 && startmode == 0) {
            sb.append(913);
        } else if (count % 6 == 0) {
            sb.append(924);
        } else {
            sb.append(901);
        }
        int idx = startpos;
        if (count >= 6) {
            char[] chars = new char[5];
            while ((startpos + count) - idx >= 6) {
                long t = 0;
                for (int i = 0; i < 6; i++) {
                    t = (t << 8) + ((long) (bytes[idx + i] & UByte.MAX_VALUE));
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    chars[i2] = (char) ((int) (t % 900));
                    t /= 900;
                }
                for (int i3 = 4; i3 >= 0; i3--) {
                    sb.append(chars[i3]);
                }
                idx += 6;
            }
        }
        for (int i4 = idx; i4 < startpos + count; i4++) {
            sb.append((char) (bytes[i4] & 255));
        }
    }

    private static void encodeNumeric(String msg, int startpos, int count, StringBuilder sb) {
        BigInteger divide;
        int idx = 0;
        StringBuilder tmp = new StringBuilder((count / 3) + 1);
        BigInteger num900 = BigInteger.valueOf(900);
        BigInteger num0 = BigInteger.valueOf(0);
        while (idx < count) {
            tmp.setLength(0);
            int len = Math.min(44, count - idx);
            StringBuilder sb2 = new StringBuilder(DiskLruCache.VERSION_1);
            sb2.append(msg.substring(startpos + idx, startpos + idx + len));
            BigInteger bigint = new BigInteger(sb2.toString());
            do {
                tmp.append((char) bigint.mod(num900).intValue());
                divide = bigint.divide(num900);
                bigint = divide;
            } while (!divide.equals(num0));
            for (int i = tmp.length() - 1; i >= 0; i--) {
                sb.append(tmp.charAt(i));
            }
            idx += len;
        }
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean isAlphaUpper(char ch) {
        return ch == ' ' || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean isAlphaLower(char ch) {
        return ch == ' ' || (ch >= 'a' && ch <= 'z');
    }

    private static boolean isMixed(char ch) {
        return MIXED[ch] != -1;
    }

    private static boolean isPunctuation(char ch) {
        return PUNCTUATION[ch] != -1;
    }

    private static boolean isText(char ch) {
        return ch == 9 || ch == 10 || ch == 13 || (ch >= ' ' && ch <= '~');
    }

    private static int determineConsecutiveDigitCount(CharSequence msg, int startpos) {
        int count = 0;
        int len = msg.length();
        int idx = startpos;
        if (startpos < len) {
            char ch = msg.charAt(startpos);
            while (isDigit(ch) && idx < len) {
                count++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
        }
        return count;
    }

    private static int determineConsecutiveTextCount(CharSequence msg, int startpos) {
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && isDigit(ch) && idx < len) {
                numericCount++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
            if (numericCount < 13) {
                if (numericCount <= 0) {
                    if (!isText(msg.charAt(idx))) {
                        break;
                    }
                    idx++;
                }
            } else {
                return (idx - startpos) - numericCount;
            }
        }
        return idx - startpos;
    }

    private static int determineConsecutiveBinaryCount(String msg, int startpos, Charset encoding) throws WriterException {
        CharsetEncoder encoder = encoding.newEncoder();
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && isDigit(ch)) {
                numericCount++;
                int i = idx + numericCount;
                int i2 = i;
                if (i >= len) {
                    break;
                }
                ch = msg.charAt(i2);
            }
            if (numericCount >= 13) {
                return idx - startpos;
            }
            char ch2 = msg.charAt(idx);
            if (encoder.canEncode(ch2)) {
                idx++;
            } else {
                StringBuilder sb = new StringBuilder("Non-encodable character detected: ");
                sb.append(ch2);
                sb.append(" (Unicode: ");
                sb.append(ch2);
                sb.append(')');
                throw new WriterException(sb.toString());
            }
        }
        return idx - startpos;
    }

    private static void encodingECI(int eci, StringBuilder sb) throws WriterException {
        if (eci >= 0 && eci < LATCH_TO_TEXT) {
            sb.append(927);
            sb.append((char) eci);
        } else if (eci < 810900) {
            sb.append(926);
            sb.append((char) ((eci / LATCH_TO_TEXT) - 1));
            sb.append((char) (eci % LATCH_TO_TEXT));
        } else if (eci < 811800) {
            sb.append(925);
            sb.append((char) (810900 - eci));
        } else {
            StringBuilder sb2 = new StringBuilder("ECI number not in valid range from 0..811799, but was ");
            sb2.append(eci);
            throw new WriterException(sb2.toString());
        }
    }
}
