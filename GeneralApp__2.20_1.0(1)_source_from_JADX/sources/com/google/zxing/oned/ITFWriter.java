package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class ITFWriter extends OneDimensionalCodeWriter {
    private static final int[] END_PATTERN = {3, 1, 1};
    private static final int[] START_PATTERN = {1, 1, 1, 1};

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.ITF) {
            return super.encode(contents, format, width, height, hints);
        }
        StringBuilder sb = new StringBuilder("Can only encode ITF, but got ");
        sb.append(format);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] encode(String contents) {
        int length = contents.length();
        int length2 = length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length2 <= 80) {
            boolean[] zArr = new boolean[((length2 * 9) + 9)];
            boolean[] result = zArr;
            int pos = appendPattern(zArr, 0, START_PATTERN, true);
            for (int i = 0; i < length2; i += 2) {
                int one = Character.digit(contents.charAt(i), 10);
                int two = Character.digit(contents.charAt(i + 1), 10);
                int[] encoding = new int[18];
                for (int j = 0; j < 5; j++) {
                    encoding[j * 2] = ITFReader.PATTERNS[one][j];
                    encoding[(j * 2) + 1] = ITFReader.PATTERNS[two][j];
                }
                pos += appendPattern(result, pos, encoding, true);
            }
            appendPattern(result, pos, END_PATTERN, true);
            return result;
        } else {
            StringBuilder sb = new StringBuilder("Requested contents should be less than 80 digits long, but got ");
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
