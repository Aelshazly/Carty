package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public class Code93Writer extends OneDimensionalCodeWriter {
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.CODE_93) {
            return super.encode(contents, format, width, height, hints);
        }
        StringBuilder sb = new StringBuilder("Can only encode CODE_93, but got ");
        sb.append(format);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] encode(String contents) {
        int length = contents.length();
        int length2 = length;
        if (length <= 80) {
            int[] widths = new int[9];
            boolean[] result = new boolean[(((contents.length() + 2 + 2) * 9) + 1)];
            toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], widths);
            int pos = appendPattern(result, 0, widths, true);
            int i = 0;
            while (true) {
                String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
                if (i < length2) {
                    toIntArray(Code93Reader.CHARACTER_ENCODINGS[str.indexOf(contents.charAt(i))], widths);
                    pos += appendPattern(result, pos, widths, true);
                    i++;
                } else {
                    int check1 = computeChecksumIndex(contents, 20);
                    toIntArray(Code93Reader.CHARACTER_ENCODINGS[check1], widths);
                    int pos2 = pos + appendPattern(result, pos, widths, true);
                    StringBuilder sb = new StringBuilder();
                    sb.append(contents);
                    sb.append(str.charAt(check1));
                    toIntArray(Code93Reader.CHARACTER_ENCODINGS[computeChecksumIndex(sb.toString(), 15)], widths);
                    int pos3 = pos2 + appendPattern(result, pos2, widths, true);
                    toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], widths);
                    result[pos3 + appendPattern(result, pos3, widths, true)] = true;
                    return result;
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder("Requested contents should be less than 80 digits long, but got ");
            sb2.append(length2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    private static void toIntArray(int a, int[] toReturn) {
        for (int i = 0; i < 9; i++) {
            int i2 = 1;
            if (((1 << (8 - i)) & a) == 0) {
                i2 = 0;
            }
            toReturn[i] = i2;
        }
    }

    protected static int appendPattern(boolean[] target, int pos, int[] pattern, boolean startColor) {
        int length = pattern.length;
        int pos2 = pos;
        int pos3 = 0;
        while (pos3 < length) {
            int pos4 = pos2 + 1;
            target[pos2] = pattern[pos3] != 0;
            pos3++;
            pos2 = pos4;
        }
        return 9;
    }

    private static int computeChecksumIndex(String contents, int maxWeight) {
        int weight = 1;
        int total = 0;
        for (int i = contents.length() - 1; i >= 0; i--) {
            total += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i)) * weight;
            weight++;
            if (weight > maxWeight) {
                weight = 1;
            }
        }
        return total % 47;
    }
}
