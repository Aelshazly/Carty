package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {
    static final int[] END_PATTERN = {1, 1, 1, 1, 1, 1};
    static final int[][] L_AND_G_PATTERNS = new int[20][];
    static final int[][] L_PATTERNS = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] MIDDLE_PATTERN = {1, 1, 1, 1, 1};
    static final int[] START_END_PATTERN = {1, 1, 1};
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    /* access modifiers changed from: protected */
    public abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException;

    /* access modifiers changed from: 0000 */
    public abstract BarcodeFormat getBarcodeFormat();

    static {
        System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = L_PATTERNS[i - 10];
            int[] widths = iArr;
            int[] reversedWidths = new int[iArr.length];
            for (int j = 0; j < widths.length; j++) {
                reversedWidths[j] = widths[(widths.length - j) - 1];
            }
            L_AND_G_PATTERNS[i] = reversedWidths;
        }
    }

    protected UPCEANReader() {
    }

    static int[] findStartGuardPattern(BitArray row) throws NotFoundException {
        boolean foundStart = false;
        int[] startRange = null;
        int nextStart = 0;
        int[] counters = new int[START_END_PATTERN.length];
        while (!foundStart) {
            Arrays.fill(counters, 0, START_END_PATTERN.length, 0);
            int[] findGuardPattern = findGuardPattern(row, nextStart, false, START_END_PATTERN, counters);
            startRange = findGuardPattern;
            int start = findGuardPattern[0];
            nextStart = startRange[1];
            int i = start - (nextStart - start);
            int quietStart = i;
            if (i >= 0) {
                foundStart = row.isRange(quietStart, start, false);
            }
        }
        return startRange;
    }

    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> hints) throws NotFoundException, ChecksumException, FormatException {
        return decodeRow(rowNumber, row, findStartGuardPattern(row), hints);
    }

    public Result decodeRow(int rowNumber, BitArray row, int[] startGuardRange, Map<DecodeHintType, ?> hints) throws NotFoundException, ChecksumException, FormatException {
        ResultPointCallback resultPointCallback;
        Result extensionResult;
        int[] iArr;
        boolean valid;
        int i = rowNumber;
        BitArray bitArray = row;
        int[] iArr2 = startGuardRange;
        Map<DecodeHintType, ?> map = hints;
        if (map == null) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        ResultPointCallback resultPointCallback2 = resultPointCallback;
        if (resultPointCallback != null) {
            resultPointCallback2.foundPossibleResultPoint(new ResultPoint(((float) (iArr2[0] + iArr2[1])) / 2.0f, (float) i));
        }
        StringBuilder sb = this.decodeRowStringBuffer;
        StringBuilder result = sb;
        sb.setLength(0);
        int endStart = decodeMiddle(bitArray, iArr2, result);
        if (resultPointCallback2 != null) {
            resultPointCallback2.foundPossibleResultPoint(new ResultPoint((float) endStart, (float) i));
        }
        int[] endRange = decodeEnd(bitArray, endStart);
        if (resultPointCallback2 != null) {
            resultPointCallback2.foundPossibleResultPoint(new ResultPoint(((float) (endRange[0] + endRange[1])) / 2.0f, (float) i));
        }
        int i2 = endRange[1];
        int end = i2;
        int i3 = i2 + (i2 - endRange[0]);
        int quietEnd = i3;
        if (i3 >= row.getSize() || !bitArray.isRange(end, quietEnd, false)) {
            ResultPointCallback resultPointCallback3 = resultPointCallback2;
            StringBuilder sb2 = result;
            throw NotFoundException.getNotFoundInstance();
        }
        String sb3 = result.toString();
        String resultString = sb3;
        if (sb3.length() < 8) {
            throw FormatException.getFormatInstance();
        } else if (checkChecksum(resultString)) {
            float left = ((float) (iArr2[1] + iArr2[0])) / 2.0f;
            float right = ((float) (endRange[1] + endRange[0])) / 2.0f;
            BarcodeFormat format = getBarcodeFormat();
            ResultPointCallback resultPointCallback4 = resultPointCallback2;
            StringBuilder sb4 = result;
            Result decodeResult = new Result(resultString, null, new ResultPoint[]{new ResultPoint(left, (float) i), new ResultPoint(right, (float) i)}, format);
            int extensionLength = 0;
            try {
                extensionResult = this.extensionReader.decodeRow(i, bitArray, endRange[1]);
                try {
                    decodeResult.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, extensionResult.getText());
                    decodeResult.putAllMetadata(extensionResult.getResultMetadata());
                    decodeResult.addResultPoints(extensionResult.getResultPoints());
                    extensionLength = extensionResult.getText().length();
                } catch (ReaderException e) {
                }
            } catch (ReaderException e2) {
                extensionResult = null;
            }
            if (map == null) {
                iArr = null;
            } else {
                iArr = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            Result result2 = extensionResult;
            int[] allowedExtensions = iArr;
            if (iArr != null) {
                int length = allowedExtensions.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        valid = false;
                        break;
                    } else if (extensionLength == allowedExtensions[i4]) {
                        valid = true;
                        break;
                    } else {
                        i4++;
                        BitArray bitArray2 = row;
                    }
                }
                if (!valid) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if (format == BarcodeFormat.EAN_13 || format == BarcodeFormat.UPC_A) {
                String lookupCountryIdentifier = this.eanManSupport.lookupCountryIdentifier(resultString);
                String countryID = lookupCountryIdentifier;
                if (lookupCountryIdentifier != null) {
                    decodeResult.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, countryID);
                }
            }
            return decodeResult;
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean checkChecksum(String s) throws FormatException {
        return checkStandardUPCEANChecksum(s);
    }

    static boolean checkStandardUPCEANChecksum(CharSequence s) throws FormatException {
        int length = s.length();
        boolean z = false;
        int length2 = length;
        if (length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = length2 - 2; i >= 0; i -= 2) {
            int charAt = s.charAt(i) - '0';
            int digit = charAt;
            if (charAt < 0 || digit > 9) {
                throw FormatException.getFormatInstance();
            }
            sum += digit;
        }
        int sum2 = sum * 3;
        for (int i2 = length2 - 1; i2 >= 0; i2 -= 2) {
            int charAt2 = s.charAt(i2) - '0';
            int digit2 = charAt2;
            if (charAt2 < 0 || digit2 > 9) {
                throw FormatException.getFormatInstance();
            }
            sum2 += digit2;
        }
        if (sum2 % 10 == 0) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public int[] decodeEnd(BitArray row, int endStart) throws NotFoundException {
        return findGuardPattern(row, endStart, false, START_END_PATTERN);
    }

    static int[] findGuardPattern(BitArray row, int rowOffset, boolean whiteFirst, int[] pattern) throws NotFoundException {
        return findGuardPattern(row, rowOffset, whiteFirst, pattern, new int[pattern.length]);
    }

    private static int[] findGuardPattern(BitArray row, int rowOffset, boolean whiteFirst, int[] pattern, int[] counters) throws NotFoundException {
        int width = row.getSize();
        int rowOffset2 = whiteFirst ? row.getNextUnset(rowOffset) : row.getNextSet(rowOffset);
        int counterPosition = 0;
        int patternStart = rowOffset2;
        int patternLength = pattern.length;
        boolean isWhite = whiteFirst;
        for (int x = rowOffset2; x < width; x++) {
            boolean z = true;
            if (row.get(x) ^ isWhite) {
                counters[counterPosition] = counters[counterPosition] + 1;
            } else {
                if (counterPosition != patternLength - 1) {
                    counterPosition++;
                } else if (patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{patternStart, x};
                } else {
                    patternStart += counters[0] + counters[1];
                    System.arraycopy(counters, 2, counters, 0, patternLength - 2);
                    counters[patternLength - 2] = 0;
                    counters[patternLength - 1] = 0;
                    counterPosition--;
                }
                counters[counterPosition] = 1;
                if (isWhite) {
                    z = false;
                }
                isWhite = z;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int decodeDigit(BitArray row, int[] counters, int rowOffset, int[][] patterns) throws NotFoundException {
        recordPattern(row, rowOffset, counters);
        float bestVariance = MAX_AVG_VARIANCE;
        int bestMatch = -1;
        int max = patterns.length;
        for (int i = 0; i < max; i++) {
            float patternMatchVariance = patternMatchVariance(counters, patterns[i], MAX_INDIVIDUAL_VARIANCE);
            float variance = patternMatchVariance;
            if (patternMatchVariance < bestVariance) {
                bestVariance = variance;
                bestMatch = i;
            }
        }
        if (bestMatch >= 0) {
            return bestMatch;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
