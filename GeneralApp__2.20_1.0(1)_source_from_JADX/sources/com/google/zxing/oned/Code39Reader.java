package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;
import p008cz.msebera.android.httpclient.HttpStatus;

public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private static final String CHECK_DIGIT_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static {
        int[] iArr = {52, 289, 97, 352, 49, HttpStatus.SC_NOT_MODIFIED, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, HttpStatus.SC_BAD_REQUEST, 208, 133, 388, 196, 148, 168, 162, 138, 42};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[39];
    }

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean usingCheckDigit2) {
        this(usingCheckDigit2, false);
    }

    public Code39Reader(boolean usingCheckDigit2, boolean extendedMode2) {
        this.usingCheckDigit = usingCheckDigit2;
        this.extendedMode = extendedMode2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        String resultString;
        String str;
        Code39Reader code39Reader = this;
        int i = rowNumber;
        BitArray bitArray = row;
        int[] iArr = code39Reader.counters;
        int[] theCounters = iArr;
        Arrays.fill(iArr, 0);
        StringBuilder sb = code39Reader.decodeRowResult;
        StringBuilder result = sb;
        sb.setLength(0);
        int[] start = findAsteriskPattern(bitArray, theCounters);
        int i2 = 1;
        int nextStart = bitArray.getNextSet(start[1]);
        int end = row.getSize();
        while (true) {
            recordPattern(bitArray, nextStart, theCounters);
            int narrowWidePattern = toNarrowWidePattern(theCounters);
            int pattern = narrowWidePattern;
            if (narrowWidePattern >= 0) {
                char decodedChar = patternToChar(pattern);
                result.append(decodedChar);
                int lastStart = nextStart;
                int nextStart2 = nextStart;
                for (int counter : theCounters) {
                    nextStart2 += counter;
                }
                nextStart = bitArray.getNextSet(nextStart2);
                if (decodedChar == '*') {
                    result.setLength(result.length() - i2);
                    int lastPatternSize = 0;
                    for (int counter2 : theCounters) {
                        lastPatternSize += counter2;
                    }
                    int whiteSpaceAfterEnd = (nextStart - lastStart) - lastPatternSize;
                    if (nextStart == end || (whiteSpaceAfterEnd << 1) >= lastPatternSize) {
                        if (code39Reader.usingCheckDigit) {
                            int max = result.length() - i2;
                            int total = 0;
                            int i3 = 0;
                            while (true) {
                                str = CHECK_DIGIT_STRING;
                                if (i3 >= max) {
                                    break;
                                }
                                total += str.indexOf(code39Reader.decodeRowResult.charAt(i3));
                                i3++;
                            }
                            if (result.charAt(max) == str.charAt(total % 43)) {
                                result.setLength(max);
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        if (result.length() != 0) {
                            if (code39Reader.extendedMode) {
                                resultString = decodeExtended(result);
                            } else {
                                resultString = result.toString();
                            }
                            int[] iArr2 = start;
                            return new Result(resultString, null, new ResultPoint[]{new ResultPoint(((float) (start[1] + start[0])) / 2.0f, (float) i), new ResultPoint(((float) lastStart) + (((float) lastPatternSize) / 2.0f), (float) i)}, BarcodeFormat.CODE_39);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                bitArray = row;
                start = start;
                i2 = 1;
                code39Reader = this;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static int[] findAsteriskPattern(BitArray row, int[] counters2) throws NotFoundException {
        int width = row.getSize();
        int rowOffset = row.getNextSet(0);
        int counterPosition = 0;
        int patternStart = rowOffset;
        boolean isWhite = false;
        int patternLength = counters2.length;
        for (int i = rowOffset; i < width; i++) {
            boolean z = true;
            if (row.get(i) ^ isWhite) {
                counters2[counterPosition] = counters2[counterPosition] + 1;
            } else {
                if (counterPosition != patternLength - 1) {
                    counterPosition++;
                } else if (toNarrowWidePattern(counters2) != ASTERISK_ENCODING || !row.isRange(Math.max(0, patternStart - ((i - patternStart) / 2)), patternStart, false)) {
                    patternStart += counters2[0] + counters2[1];
                    System.arraycopy(counters2, 2, counters2, 0, patternLength - 2);
                    counters2[patternLength - 2] = 0;
                    counters2[patternLength - 1] = 0;
                    counterPosition--;
                } else {
                    return new int[]{patternStart, i};
                }
                counters2[counterPosition] = 1;
                if (isWhite) {
                    z = false;
                }
                isWhite = z;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] counters2) {
        int numCounters = counters2.length;
        int maxNarrowCounter = 0;
        int wideCounters = 0;
        while (true) {
            int minCounter = Integer.MAX_VALUE;
            int counter = wideCounters;
            for (int i : counters2) {
                counter = i;
                if (i < minCounter && counter > maxNarrowCounter) {
                    minCounter = counter;
                }
            }
            maxNarrowCounter = minCounter;
            int wideCounters2 = 0;
            int totalWideCountersWidth = 0;
            int pattern = 0;
            for (int i2 = 0; i2 < numCounters; i2++) {
                int i3 = counters2[i2];
                counter = i3;
                if (i3 > maxNarrowCounter) {
                    pattern |= 1 << ((numCounters - 1) - i2);
                    wideCounters2++;
                    totalWideCountersWidth += counter;
                }
            }
            if (wideCounters2 == 3) {
                for (int i4 = 0; i4 < numCounters && wideCounters2 > 0; i4++) {
                    int i5 = counters2[i4];
                    int counter2 = i5;
                    if (i5 > maxNarrowCounter) {
                        wideCounters2--;
                        if ((counter2 << 1) >= totalWideCountersWidth) {
                            return -1;
                        }
                    }
                }
                return pattern;
            } else if (wideCounters2 <= 3) {
                return -1;
            } else {
                wideCounters = counter;
            }
        }
    }

    private static char patternToChar(int pattern) throws NotFoundException {
        int i = 0;
        while (true) {
            int[] iArr = CHARACTER_ENCODINGS;
            if (i >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            } else if (iArr[i] == pattern) {
                return ALPHABET_STRING.charAt(i);
            } else {
                i++;
            }
        }
    }

    private static String decodeExtended(CharSequence encoded) throws FormatException {
        int length = encoded.length();
        StringBuilder decoded = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = encoded.charAt(i);
            char c = charAt;
            if (charAt == '+' || c == '$' || c == '%' || c == '/') {
                char next = encoded.charAt(i + 1);
                char decodedChar = 0;
                if (c != '$') {
                    if (c != '%') {
                        if (c != '+') {
                            if (c == '/') {
                                if (next >= 'A' && next <= 'O') {
                                    decodedChar = (char) (next - ' ');
                                } else if (next == 'Z') {
                                    decodedChar = ':';
                                } else {
                                    throw FormatException.getFormatInstance();
                                }
                            }
                        } else if (next < 'A' || next > 'Z') {
                            throw FormatException.getFormatInstance();
                        } else {
                            decodedChar = (char) (next + ' ');
                        }
                    } else if (next >= 'A' && next <= 'E') {
                        decodedChar = (char) (next - '&');
                    } else if (next < 'F' || next > 'W') {
                        throw FormatException.getFormatInstance();
                    } else {
                        decodedChar = (char) (next - 11);
                    }
                } else if (next < 'A' || next > 'Z') {
                    throw FormatException.getFormatInstance();
                } else {
                    decodedChar = (char) (next - '@');
                }
                decoded.append(decodedChar);
                i++;
            } else {
                decoded.append(c);
            }
            i++;
        }
        return decoded.toString();
    }
}
