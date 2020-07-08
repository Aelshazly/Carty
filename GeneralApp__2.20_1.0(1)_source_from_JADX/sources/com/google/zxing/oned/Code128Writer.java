package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 'ñ';
    private static final char ESCAPE_FNC_2 = 'ò';
    private static final char ESCAPE_FNC_3 = 'ó';
    private static final char ESCAPE_FNC_4 = 'ô';

    private enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.CODE_128) {
            return super.encode(contents, format, width, height, hints);
        }
        StringBuilder sb = new StringBuilder("Can only encode CODE_128, but got ");
        sb.append(format);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] encode(String contents) {
        int patternIndex;
        int patternIndex2;
        String str = contents;
        int length = contents.length();
        int length2 = length;
        if (length <= 0 || length2 > 80) {
            StringBuilder sb = new StringBuilder("Contents length should be between 1 and 80 characters, but got ");
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString());
        }
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            char c = charAt;
            if (charAt < ' ' || c > '~') {
                switch (c) {
                    case 241:
                    case 242:
                    case 243:
                    case 244:
                        break;
                    default:
                        StringBuilder sb2 = new StringBuilder("Bad character in input: ");
                        sb2.append(c);
                        throw new IllegalArgumentException(sb2.toString());
                }
            }
        }
        Collection<int[]> patterns = new ArrayList<>();
        int checkSum = 0;
        int codeSet = 0;
        int position = 0;
        int checkWeight = 1;
        while (position < length2) {
            int chooseCode = chooseCode(str, position, codeSet);
            int newCodeSet = chooseCode;
            if (chooseCode == codeSet) {
                switch (str.charAt(position)) {
                    case 241:
                        patternIndex = 102;
                        break;
                    case 242:
                        patternIndex = 97;
                        break;
                    case 243:
                        patternIndex = 96;
                        break;
                    case 244:
                        patternIndex = 100;
                        break;
                    default:
                        if (codeSet != 100) {
                            patternIndex = Integer.parseInt(str.substring(position, position + 2));
                            position++;
                            break;
                        } else {
                            patternIndex = str.charAt(position) - ' ';
                            break;
                        }
                }
                position++;
            } else {
                if (codeSet != 0) {
                    patternIndex2 = newCodeSet;
                } else if (newCodeSet == 100) {
                    patternIndex2 = 104;
                } else {
                    patternIndex2 = 105;
                }
                codeSet = newCodeSet;
            }
            patterns.add(Code128Reader.CODE_PATTERNS[patternIndex]);
            checkSum += patternIndex * checkWeight;
            if (position != 0) {
                checkWeight++;
            }
        }
        patterns.add(Code128Reader.CODE_PATTERNS[checkSum % 103]);
        patterns.add(Code128Reader.CODE_PATTERNS[106]);
        int codeWidth = 0;
        for (int[] iArr : patterns) {
            int codeWidth2 = codeWidth;
            for (int width : (int[]) r6.next()) {
                codeWidth2 += width;
            }
            codeWidth = codeWidth2;
        }
        boolean[] result = new boolean[codeWidth];
        int pos = 0;
        for (int[] pattern : patterns) {
            pos += appendPattern(result, pos, pattern, true);
        }
        return result;
    }

    private static CType findCType(CharSequence value, int start) {
        int last = value.length();
        if (start >= last) {
            return CType.UNCODABLE;
        }
        char charAt = value.charAt(start);
        char c = charAt;
        if (charAt == 241) {
            return CType.FNC_1;
        }
        if (c < '0' || c > '9') {
            return CType.UNCODABLE;
        }
        if (start + 1 >= last) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = value.charAt(start + 1);
        char c2 = charAt2;
        if (charAt2 < '0' || c2 > '9') {
            return CType.ONE_DIGIT;
        }
        return CType.TWO_DIGITS;
    }

    private static int chooseCode(CharSequence value, int start, int oldCode) {
        CType lookahead;
        CType findCType = findCType(value, start);
        CType lookahead2 = findCType;
        if (findCType == CType.UNCODABLE || lookahead2 == CType.ONE_DIGIT) {
            return 100;
        }
        if (oldCode == 99) {
            return oldCode;
        }
        if (oldCode != 100) {
            if (lookahead2 == CType.FNC_1) {
                lookahead2 = findCType(value, start + 1);
            }
            if (lookahead2 == CType.TWO_DIGITS) {
                return 99;
            }
            return 100;
        } else if (lookahead2 == CType.FNC_1) {
            return oldCode;
        } else {
            CType findCType2 = findCType(value, start + 2);
            CType lookahead3 = findCType2;
            if (findCType2 == CType.UNCODABLE || lookahead3 == CType.ONE_DIGIT) {
                return oldCode;
            }
            if (lookahead3 != CType.FNC_1) {
                int index = start + 4;
                while (true) {
                    CType findCType3 = findCType(value, index);
                    lookahead = findCType3;
                    if (findCType3 != CType.TWO_DIGITS) {
                        break;
                    }
                    index += 2;
                }
                if (lookahead == CType.ONE_DIGIT) {
                    return 100;
                }
                return 99;
            } else if (findCType(value, start + 3) == CType.TWO_DIGITS) {
                return 99;
            } else {
                return 100;
            }
        }
    }
}
