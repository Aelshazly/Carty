package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code39Writer extends OneDimensionalCodeWriter {
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.CODE_39) {
            return super.encode(contents, format, width, height, hints);
        }
        StringBuilder sb = new StringBuilder("Can only encode CODE_39, but got ");
        sb.append(format);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] encode(String contents) {
        int length = contents.length();
        int length2 = length;
        if (length <= 80) {
            int[] widths = new int[9];
            int codeWidth = length2 + 25;
            int i = 0;
            while (true) {
                String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
                if (i < length2) {
                    int indexOf = str.indexOf(contents.charAt(i));
                    int indexInString = indexOf;
                    if (indexOf >= 0) {
                        toIntArray(Code39Reader.CHARACTER_ENCODINGS[indexInString], widths);
                        int codeWidth2 = codeWidth;
                        for (int codeWidth3 = 0; codeWidth3 < 9; codeWidth3++) {
                            codeWidth2 += widths[codeWidth3];
                        }
                        i++;
                        codeWidth = codeWidth2;
                    } else {
                        StringBuilder sb = new StringBuilder("Bad contents: ");
                        sb.append(contents);
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else {
                    boolean[] result = new boolean[codeWidth];
                    toIntArray(Code39Reader.ASTERISK_ENCODING, widths);
                    int pos = appendPattern(result, 0, widths, true);
                    int[] narrowWhite = {1};
                    int pos2 = pos + appendPattern(result, pos, narrowWhite, false);
                    for (int i2 = 0; i2 < length2; i2++) {
                        toIntArray(Code39Reader.CHARACTER_ENCODINGS[str.indexOf(contents.charAt(i2))], widths);
                        int appendPattern = appendPattern(result, pos2, widths, true) + pos2;
                        pos2 = appendPattern + appendPattern(result, appendPattern, narrowWhite, false);
                    }
                    toIntArray(Code39Reader.ASTERISK_ENCODING, widths);
                    appendPattern(result, pos2, widths, true);
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
            if (((1 << (8 - i)) & a) != 0) {
                i2 = 2;
            }
            toReturn[i] = i2;
        }
    }
}
