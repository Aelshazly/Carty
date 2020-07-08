package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Map;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray row) throws NotFoundException {
        BitArray bitArray = row;
        int width = row.getSize();
        int rowOffset = bitArray.getNextSet(0);
        int counterPosition = 0;
        int[] counters = new int[6];
        int patternStart = rowOffset;
        boolean isWhite = false;
        for (int i = rowOffset; i < width; i++) {
            boolean z = true;
            if (bitArray.get(i) ^ isWhite) {
                counters[counterPosition] = counters[counterPosition] + 1;
            } else {
                if (counterPosition == 5) {
                    float bestVariance = MAX_AVG_VARIANCE;
                    int bestMatch = -1;
                    for (int startCode = 103; startCode <= 105; startCode++) {
                        float patternMatchVariance = patternMatchVariance(counters, CODE_PATTERNS[startCode], MAX_INDIVIDUAL_VARIANCE);
                        float variance = patternMatchVariance;
                        if (patternMatchVariance < bestVariance) {
                            bestMatch = startCode;
                            bestVariance = variance;
                        }
                    }
                    if (bestMatch < 0 || !bitArray.isRange(Math.max(0, patternStart - ((i - patternStart) / 2)), patternStart, false)) {
                        patternStart += counters[0] + counters[1];
                        System.arraycopy(counters, 2, counters, 0, 4);
                        counters[4] = 0;
                        counters[5] = 0;
                        counterPosition--;
                    } else {
                        return new int[]{patternStart, i, bestMatch};
                    }
                } else {
                    counterPosition++;
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

    private static int decodeCode(BitArray row, int[] counters, int rowOffset) throws NotFoundException {
        recordPattern(row, rowOffset, counters);
        float bestVariance = MAX_AVG_VARIANCE;
        int bestMatch = -1;
        int d = 0;
        while (true) {
            int[][] iArr = CODE_PATTERNS;
            if (d >= iArr.length) {
                break;
            }
            float patternMatchVariance = patternMatchVariance(counters, iArr[d], MAX_INDIVIDUAL_VARIANCE);
            float variance = patternMatchVariance;
            if (patternMatchVariance < bestVariance) {
                bestVariance = variance;
                bestMatch = d;
            }
            d++;
        }
        if (bestMatch >= 0) {
            return bestMatch;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> hints) throws NotFoundException, FormatException, ChecksumException {
        int codeSet;
        int startCode;
        int[] counters;
        boolean upperMode;
        boolean shiftUpperMode;
        int i = rowNumber;
        BitArray bitArray = row;
        Map<DecodeHintType, ?> map = hints;
        boolean convertFNC1 = map != null && map.containsKey(DecodeHintType.ASSUME_GS1);
        int[] findStartPattern = findStartPattern(row);
        int[] startPatternInfo = findStartPattern;
        int startCode2 = findStartPattern[2];
        ArrayList arrayList = new ArrayList(20);
        ArrayList arrayList2 = arrayList;
        arrayList.add(Byte.valueOf((byte) startCode2));
        switch (startCode2) {
            case 103:
                codeSet = 101;
                break;
            case 104:
                codeSet = 100;
                break;
            case 105:
                codeSet = 99;
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        boolean done = false;
        boolean isNextShifted = false;
        StringBuilder result = new StringBuilder(20);
        int lastStart = startPatternInfo[0];
        int nextStart = startPatternInfo[1];
        int[] counters2 = new int[6];
        int nextStart2 = 0;
        int checksumTotal = startCode2;
        int multiplier = 0;
        boolean lastCharacterWasPrintable = true;
        int codeSet2 = codeSet;
        int lastCode = 0;
        boolean upperMode2 = false;
        boolean shiftUpperMode2 = false;
        while (!done) {
            boolean unshift = isNextShifted;
            isNextShifted = false;
            lastCode = nextStart2;
            int code = decodeCode(bitArray, counters2, nextStart);
            arrayList2.add(Byte.valueOf((byte) code));
            if (code != 106) {
                lastCharacterWasPrintable = true;
            }
            if (code != 106) {
                multiplier++;
                checksumTotal += multiplier * code;
            }
            lastStart = nextStart;
            int nextStart3 = nextStart;
            for (int nextStart4 = 0; nextStart4 < 6; nextStart4++) {
                nextStart3 += counters2[nextStart4];
            }
            switch (code) {
                case 103:
                case 104:
                case 105:
                    throw FormatException.getFormatInstance();
                default:
                    String str = "]C1";
                    switch (codeSet2) {
                        case 99:
                            upperMode = upperMode2;
                            counters = counters2;
                            shiftUpperMode = shiftUpperMode2;
                            startCode = startCode2;
                            if (code >= 100) {
                                if (code != 106) {
                                    lastCharacterWasPrintable = false;
                                }
                                if (code == 106) {
                                    done = true;
                                    break;
                                } else {
                                    switch (code) {
                                        case 100:
                                            codeSet2 = 100;
                                            break;
                                        case 101:
                                            codeSet2 = 101;
                                            break;
                                        case 102:
                                            if (convertFNC1) {
                                                if (result.length() != 0) {
                                                    result.append(29);
                                                    break;
                                                } else {
                                                    result.append(str);
                                                    break;
                                                }
                                            }
                                            break;
                                    }
                                }
                            } else {
                                if (code < 10) {
                                    result.append('0');
                                }
                                result.append(code);
                                break;
                            }
                            break;
                        case 100:
                            upperMode = upperMode2;
                            counters = counters2;
                            shiftUpperMode = shiftUpperMode2;
                            startCode = startCode2;
                            if (code >= 96) {
                                if (code != 106) {
                                    lastCharacterWasPrintable = false;
                                }
                                if (code == 106) {
                                    done = true;
                                    break;
                                } else {
                                    switch (code) {
                                        case 98:
                                            codeSet2 = 101;
                                            isNextShifted = true;
                                            break;
                                        case 99:
                                            codeSet2 = 99;
                                            break;
                                        case 100:
                                            if (upperMode || !shiftUpperMode) {
                                                if (upperMode && shiftUpperMode) {
                                                    shiftUpperMode = false;
                                                    upperMode = false;
                                                    break;
                                                } else {
                                                    shiftUpperMode = true;
                                                    break;
                                                }
                                            } else {
                                                shiftUpperMode = false;
                                                upperMode = true;
                                                break;
                                            }
                                        case 101:
                                            codeSet2 = 101;
                                            break;
                                        case 102:
                                            if (convertFNC1) {
                                                if (result.length() != 0) {
                                                    result.append(29);
                                                    break;
                                                } else {
                                                    result.append(str);
                                                    break;
                                                }
                                            }
                                            break;
                                    }
                                }
                            } else {
                                if (shiftUpperMode == upperMode) {
                                    result.append((char) (code + 32));
                                } else {
                                    result.append((char) (code + 32 + 128));
                                }
                                shiftUpperMode = false;
                                break;
                            }
                        case 101:
                            if (code >= 64) {
                                upperMode = upperMode2;
                                counters = counters2;
                                shiftUpperMode = shiftUpperMode2;
                                startCode = startCode2;
                                if (code >= 96) {
                                    if (code != 106) {
                                        lastCharacterWasPrintable = false;
                                    }
                                    if (code == 106) {
                                        done = true;
                                        break;
                                    } else {
                                        switch (code) {
                                            case 98:
                                                codeSet2 = 100;
                                                isNextShifted = true;
                                                break;
                                            case 99:
                                                codeSet2 = 99;
                                                break;
                                            case 100:
                                                codeSet2 = 100;
                                                break;
                                            case 101:
                                                if (upperMode || !shiftUpperMode) {
                                                    if (upperMode && shiftUpperMode) {
                                                        shiftUpperMode = false;
                                                        upperMode = false;
                                                        break;
                                                    } else {
                                                        shiftUpperMode = true;
                                                        break;
                                                    }
                                                } else {
                                                    shiftUpperMode = false;
                                                    upperMode = true;
                                                    break;
                                                }
                                            case 102:
                                                if (convertFNC1) {
                                                    if (result.length() != 0) {
                                                        result.append(29);
                                                        break;
                                                    } else {
                                                        result.append(str);
                                                        break;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    if (shiftUpperMode == upperMode) {
                                        result.append((char) (code - 64));
                                    } else {
                                        result.append((char) (code + 64));
                                    }
                                    shiftUpperMode = false;
                                    break;
                                }
                            } else {
                                upperMode = upperMode2;
                                boolean shiftUpperMode3 = shiftUpperMode2;
                                if (shiftUpperMode3 == upperMode) {
                                    boolean z = shiftUpperMode3;
                                    result.append((char) (code + 32));
                                } else {
                                    result.append((char) (code + 32 + 128));
                                }
                                counters = counters2;
                                startCode = startCode2;
                                shiftUpperMode = false;
                                break;
                            }
                        default:
                            upperMode = upperMode2;
                            counters = counters2;
                            shiftUpperMode = shiftUpperMode2;
                            startCode = startCode2;
                            break;
                    }
                    if (unshift) {
                        codeSet2 = codeSet2 == 101 ? 'd' : 'e';
                    }
                    Map<DecodeHintType, ?> map2 = hints;
                    startCode2 = startCode;
                    shiftUpperMode2 = shiftUpperMode;
                    counters2 = counters;
                    upperMode2 = upperMode;
                    nextStart = nextStart3;
                    nextStart2 = code;
            }
        }
        boolean upperMode3 = upperMode2;
        int[] iArr = counters2;
        boolean shiftUpperMode4 = shiftUpperMode2;
        int i2 = startCode2;
        int lastPatternSize = nextStart - lastStart;
        int nextStart5 = bitArray.getNextUnset(nextStart);
        boolean z2 = upperMode3;
        if (!bitArray.isRange(nextStart5, Math.min(row.getSize(), nextStart5 + ((nextStart5 - lastStart) / 2)), false)) {
            throw NotFoundException.getNotFoundInstance();
        } else if ((checksumTotal - (multiplier * lastCode)) % 103 == lastCode) {
            int length = result.length();
            int resultLength = length;
            if (length != 0) {
                if (resultLength > 0 && lastCharacterWasPrintable) {
                    if (codeSet2 == 99) {
                        result.delete(resultLength - 2, resultLength);
                    } else {
                        result.delete(resultLength - 1, resultLength);
                    }
                }
                float left = ((float) (startPatternInfo[1] + startPatternInfo[0])) / 2.0f;
                boolean z3 = shiftUpperMode4;
                float right = ((float) lastStart) + (((float) lastPatternSize) / 2.0f);
                int size = arrayList2.size();
                int rawCodesSize = size;
                byte[] rawBytes = new byte[size];
                int i3 = lastPatternSize;
                int i4 = 0;
                while (true) {
                    boolean convertFNC12 = convertFNC1;
                    int rawCodesSize2 = rawCodesSize;
                    if (i4 < rawCodesSize2) {
                        rawBytes[i4] = ((Byte) arrayList2.get(i4)).byteValue();
                        i4++;
                        rawCodesSize = rawCodesSize2;
                        convertFNC1 = convertFNC12;
                    } else {
                        int i5 = rawCodesSize2;
                        int i6 = nextStart5;
                        ArrayList arrayList3 = arrayList2;
                        int[] iArr2 = startPatternInfo;
                        return new Result(result.toString(), rawBytes, new ResultPoint[]{new ResultPoint(left, (float) i), new ResultPoint(right, (float) i)}, BarcodeFormat.CODE_128);
                    }
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
