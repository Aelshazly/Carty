package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class DecodedBitStreamParser {

    /* renamed from: AL */
    private static final int f103AL = 28;

    /* renamed from: AS */
    private static final int f104AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;

    /* renamed from: LL */
    private static final int f105LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* renamed from: ML */
    private static final int f106ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* renamed from: PL */
    private static final int f107PL = 25;

    /* renamed from: PS */
    private static final int f108PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger nineHundred = BigInteger.valueOf(900);
        EXP900[1] = nineHundred;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i < bigIntegerArr2.length) {
                bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(nineHundred);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] codewords, String ecLevel) throws FormatException {
        int codeIndex;
        StringBuilder result = new StringBuilder(codewords.length << 1);
        Charset encoding = DEFAULT_ENCODING;
        int codeIndex2 = 1 + 1;
        int code = codewords[1];
        PDF417ResultMetadata resultMetadata = new PDF417ResultMetadata();
        Charset encoding2 = encoding;
        while (codeIndex2 < codewords[0]) {
            if (code != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (code) {
                    case TEXT_COMPACTION_MODE_LATCH /*900*/:
                        codeIndex = textCompaction(codewords, codeIndex2, result);
                        break;
                    case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        codeIndex = byteCompaction(code, codewords, encoding2, codeIndex2, result);
                        break;
                    case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                        codeIndex = numericCompaction(codewords, codeIndex2, result);
                        break;
                    default:
                        switch (code) {
                            case MACRO_PDF417_TERMINATOR /*922*/:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                break;
                            case ECI_USER_DEFINED /*925*/:
                                codeIndex = codeIndex2 + 1;
                                break;
                            case ECI_GENERAL_PURPOSE /*926*/:
                                codeIndex = codeIndex2 + 2;
                                break;
                            case ECI_CHARSET /*927*/:
                                int codeIndex3 = codeIndex2 + 1;
                                encoding2 = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(codewords[codeIndex2]).name());
                                codeIndex = codeIndex3;
                                break;
                            case 928:
                                codeIndex = decodeMacroBlock(codewords, codeIndex2, resultMetadata);
                                break;
                            default:
                                codeIndex = textCompaction(codewords, codeIndex2 - 1, result);
                                break;
                        }
                        codeIndex = byteCompaction(code, codewords, encoding2, codeIndex2, result);
                        break;
                }
            } else {
                int codeIndex4 = codeIndex2 + 1;
                result.append((char) codewords[codeIndex2]);
                codeIndex = codeIndex4;
            }
            if (codeIndex < codewords.length) {
                int codeIndex5 = codeIndex + 1;
                code = codewords[codeIndex];
                codeIndex2 = codeIndex5;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (result.length() != 0) {
            DecoderResult decoderResult = new DecoderResult(null, result.toString(), null, ecLevel);
            DecoderResult decoderResult2 = decoderResult;
            decoderResult.setOther(resultMetadata);
            return decoderResult2;
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] codewords, int codeIndex, PDF417ResultMetadata resultMetadata) throws FormatException {
        if (codeIndex + 2 <= codewords[0]) {
            int[] segmentIndexArray = new int[2];
            int i = 0;
            while (i < 2) {
                segmentIndexArray[i] = codewords[codeIndex];
                i++;
                codeIndex++;
            }
            resultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(segmentIndexArray, 2)));
            StringBuilder fileId = new StringBuilder();
            int codeIndex2 = textCompaction(codewords, codeIndex, fileId);
            resultMetadata.setFileId(fileId.toString());
            if (codewords[codeIndex2] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int additionalOptionCodeWordsIndex = codeIndex2 + 1;
                int[] additionalOptionCodeWords = new int[(codewords[0] - additionalOptionCodeWordsIndex)];
                int additionalOptionCodeWordsIndex2 = 0;
                boolean end = false;
                while (additionalOptionCodeWordsIndex < codewords[0] && !end) {
                    int codeIndex3 = additionalOptionCodeWordsIndex + 1;
                    int codeIndex4 = codewords[additionalOptionCodeWordsIndex];
                    int code = codeIndex4;
                    if (codeIndex4 < TEXT_COMPACTION_MODE_LATCH) {
                        int additionalOptionCodeWordsIndex3 = additionalOptionCodeWordsIndex2 + 1;
                        additionalOptionCodeWords[additionalOptionCodeWordsIndex2] = code;
                        additionalOptionCodeWordsIndex2 = additionalOptionCodeWordsIndex3;
                        additionalOptionCodeWordsIndex = codeIndex3;
                    } else if (code == MACRO_PDF417_TERMINATOR) {
                        resultMetadata.setLastSegment(true);
                        additionalOptionCodeWordsIndex = codeIndex3 + 1;
                        end = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                resultMetadata.setOptionalData(Arrays.copyOf(additionalOptionCodeWords, additionalOptionCodeWordsIndex2));
                return additionalOptionCodeWordsIndex;
            } else if (codewords[codeIndex2] != MACRO_PDF417_TERMINATOR) {
                return codeIndex2;
            } else {
                resultMetadata.setLastSegment(true);
                return codeIndex2 + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int textCompaction(int[] codewords, int codeIndex, StringBuilder result) {
        int[] textCompactionData = new int[((codewords[0] - codeIndex) << 1)];
        int[] byteCompactionData = new int[((codewords[0] - codeIndex) << 1)];
        int index = 0;
        boolean end = false;
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int codeIndex3 = codewords[codeIndex];
            int code = codeIndex3;
            if (codeIndex3 < TEXT_COMPACTION_MODE_LATCH) {
                textCompactionData[index] = code / 30;
                textCompactionData[index + 1] = code % 30;
                index += 2;
                codeIndex = codeIndex2;
            } else if (code != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (code != 928) {
                    switch (code) {
                        case TEXT_COMPACTION_MODE_LATCH /*900*/:
                            int index2 = index + 1;
                            textCompactionData[index] = TEXT_COMPACTION_MODE_LATCH;
                            index = index2;
                            codeIndex = codeIndex2;
                            continue;
                        case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                            break;
                        default:
                            switch (code) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                                default:
                                    codeIndex = codeIndex2;
                                    continue;
                                    continue;
                            }
                    }
                }
                codeIndex = codeIndex2 - 1;
                end = true;
            } else {
                textCompactionData[index] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                codeIndex = codeIndex2 + 1;
                byteCompactionData[index] = codewords[codeIndex2];
                index++;
            }
        }
        decodeTextCompaction(textCompactionData, byteCompactionData, index, result);
        return codeIndex;
    }

    private static void decodeTextCompaction(int[] textCompactionData, int[] byteCompactionData, int length, StringBuilder result) {
        Mode subMode = Mode.ALPHA;
        Mode priorToShiftMode = Mode.ALPHA;
        for (int i = 0; i < length; i++) {
            int subModeCh = textCompactionData[i];
            char ch = 0;
            switch (subMode) {
                case ALPHA:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        } else {
                                            result.append((char) byteCompactionData[i]);
                                            break;
                                        }
                                    } else {
                                        priorToShiftMode = subMode;
                                        subMode = Mode.PUNCT_SHIFT;
                                        break;
                                    }
                                } else {
                                    subMode = Mode.MIXED;
                                    break;
                                }
                            } else {
                                subMode = Mode.LOWER;
                                break;
                            }
                        } else {
                            ch = ' ';
                            break;
                        }
                    } else {
                        ch = (char) (subModeCh + 65);
                        break;
                    }
                    break;
                case LOWER:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        } else {
                                            result.append((char) byteCompactionData[i]);
                                            break;
                                        }
                                    } else {
                                        priorToShiftMode = subMode;
                                        subMode = Mode.PUNCT_SHIFT;
                                        break;
                                    }
                                } else {
                                    subMode = Mode.MIXED;
                                    break;
                                }
                            } else {
                                priorToShiftMode = subMode;
                                subMode = Mode.ALPHA_SHIFT;
                                break;
                            }
                        } else {
                            ch = ' ';
                            break;
                        }
                    } else {
                        ch = (char) (subModeCh + 97);
                        break;
                    }
                    break;
                case MIXED:
                    if (subModeCh >= 25) {
                        if (subModeCh != 25) {
                            if (subModeCh != 26) {
                                if (subModeCh != 27) {
                                    if (subModeCh != 28) {
                                        if (subModeCh != 29) {
                                            if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                                if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                                    subMode = Mode.ALPHA;
                                                    break;
                                                }
                                            } else {
                                                result.append((char) byteCompactionData[i]);
                                                break;
                                            }
                                        } else {
                                            priorToShiftMode = subMode;
                                            subMode = Mode.PUNCT_SHIFT;
                                            break;
                                        }
                                    } else {
                                        subMode = Mode.ALPHA;
                                        break;
                                    }
                                } else {
                                    subMode = Mode.LOWER;
                                    break;
                                }
                            } else {
                                ch = ' ';
                                break;
                            }
                        } else {
                            subMode = Mode.PUNCT;
                            break;
                        }
                    } else {
                        ch = MIXED_CHARS[subModeCh];
                        break;
                    }
                    break;
                case PUNCT:
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            } else {
                                result.append((char) byteCompactionData[i]);
                                break;
                            }
                        } else {
                            subMode = Mode.ALPHA;
                            break;
                        }
                    } else {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    }
                    break;
                case ALPHA_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                subMode = Mode.ALPHA;
                                break;
                            }
                        } else {
                            ch = ' ';
                            break;
                        }
                    } else {
                        ch = (char) (subModeCh + 65);
                        break;
                    }
                    break;
                case PUNCT_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            } else {
                                result.append((char) byteCompactionData[i]);
                                break;
                            }
                        } else {
                            subMode = Mode.ALPHA;
                            break;
                        }
                    } else {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    }
                    break;
            }
            if (ch != 0) {
                result.append(ch);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00eb, code lost:
        if (r8 == MACRO_PDF417_TERMINATOR) goto L_0x00fa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int byteCompaction(int r25, int[] r26, java.nio.charset.Charset r27, int r28, java.lang.StringBuilder r29) {
        /*
            r0 = r25
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r3 = 923(0x39b, float:1.293E-42)
            r4 = 928(0x3a0, float:1.3E-42)
            r5 = 902(0x386, float:1.264E-42)
            r6 = 900(0x384, double:4.447E-321)
            r8 = 6
            r9 = 924(0x39c, float:1.295E-42)
            r10 = 901(0x385, float:1.263E-42)
            r11 = 900(0x384, float:1.261E-42)
            r12 = 0
            if (r0 != r10) goto L_0x00aa
            r13 = 0
            r14 = 0
            int[] r2 = new int[r8]
            r17 = 0
            int r18 = r28 + 1
            r19 = r26[r28]
            r22 = r14
            r15 = r13
            r13 = r18
            r14 = r19
            r18 = r22
        L_0x002d:
            r8 = r26[r12]
            if (r13 >= r8) goto L_0x0091
            if (r17 != 0) goto L_0x0091
            int r8 = r15 + 1
            r2[r15] = r14
            long r20 = r18 * r6
            long r6 = (long) r14
            long r18 = r20 + r6
            int r6 = r13 + 1
            r7 = r26[r13]
            r14 = r7
            if (r7 == r11) goto L_0x0084
            if (r14 == r10) goto L_0x0084
            if (r14 == r5) goto L_0x0084
            if (r14 == r9) goto L_0x0084
            if (r14 == r4) goto L_0x0084
            if (r14 == r3) goto L_0x0084
            r7 = 922(0x39a, float:1.292E-42)
            if (r14 != r7) goto L_0x0052
            goto L_0x0084
        L_0x0052:
            int r7 = r8 % 5
            if (r7 != 0) goto L_0x007a
            if (r8 <= 0) goto L_0x007a
            r7 = 0
        L_0x0059:
            r13 = 6
            if (r7 >= r13) goto L_0x006e
            int r13 = 5 - r7
            int r13 = r13 * 8
            long r3 = r18 >> r13
            int r4 = (int) r3
            byte r3 = (byte) r4
            r1.write(r3)
            int r7 = r7 + 1
            r3 = 923(0x39b, float:1.293E-42)
            r4 = 928(0x3a0, float:1.3E-42)
            goto L_0x0059
        L_0x006e:
            r18 = 0
            r15 = 0
            r13 = r6
            r3 = 923(0x39b, float:1.293E-42)
            r4 = 928(0x3a0, float:1.3E-42)
            r6 = 900(0x384, double:4.447E-321)
            r8 = 6
            goto L_0x002d
        L_0x007a:
            r13 = r6
            r15 = r8
            r3 = 923(0x39b, float:1.293E-42)
            r4 = 928(0x3a0, float:1.3E-42)
            r6 = 900(0x384, double:4.447E-321)
            r8 = 6
            goto L_0x002d
        L_0x0084:
            int r13 = r6 + -1
            r17 = 1
            r15 = r8
            r3 = 923(0x39b, float:1.293E-42)
            r4 = 928(0x3a0, float:1.3E-42)
            r6 = 900(0x384, double:4.447E-321)
            r8 = 6
            goto L_0x002d
        L_0x0091:
            r3 = r26[r12]
            if (r13 != r3) goto L_0x009c
            if (r14 >= r11) goto L_0x009c
            int r3 = r15 + 1
            r2[r15] = r14
            r15 = r3
        L_0x009c:
            r3 = 0
        L_0x009d:
            if (r3 >= r15) goto L_0x00a8
            r4 = r2[r3]
            byte r4 = (byte) r4
            r1.write(r4)
            int r3 = r3 + 1
            goto L_0x009d
        L_0x00a8:
            goto L_0x0130
        L_0x00aa:
            if (r0 != r9) goto L_0x012e
            r2 = 0
            r3 = 0
            r6 = 0
            r8 = 0
            r22 = r2
            r2 = r28
            r23 = r3
            r3 = r22
            r4 = r6
            r6 = r23
        L_0x00bc:
            r13 = r26[r12]
            if (r2 >= r13) goto L_0x012c
            if (r4 != 0) goto L_0x012c
            int r13 = r2 + 1
            r2 = r26[r2]
            r8 = r2
            if (r2 >= r11) goto L_0x00d9
            int r3 = r3 + 1
            r14 = 900(0x384, double:4.447E-321)
            long r17 = r6 * r14
            long r14 = (long) r8
            long r6 = r17 + r14
            r2 = 922(0x39a, float:1.292E-42)
            r14 = 928(0x3a0, float:1.3E-42)
            r15 = 923(0x39b, float:1.293E-42)
            goto L_0x00fd
        L_0x00d9:
            if (r8 == r11) goto L_0x00f4
            if (r8 == r10) goto L_0x00f4
            if (r8 == r5) goto L_0x00f4
            if (r8 == r9) goto L_0x00f4
            r14 = 928(0x3a0, float:1.3E-42)
            if (r8 == r14) goto L_0x00f1
            r15 = 923(0x39b, float:1.293E-42)
            if (r8 == r15) goto L_0x00ee
            r2 = 922(0x39a, float:1.292E-42)
            if (r8 != r2) goto L_0x00fd
            goto L_0x00fa
        L_0x00ee:
            r2 = 922(0x39a, float:1.292E-42)
            goto L_0x00fa
        L_0x00f1:
            r2 = 922(0x39a, float:1.292E-42)
            goto L_0x00f8
        L_0x00f4:
            r2 = 922(0x39a, float:1.292E-42)
            r14 = 928(0x3a0, float:1.3E-42)
        L_0x00f8:
            r15 = 923(0x39b, float:1.293E-42)
        L_0x00fa:
            int r13 = r13 + -1
            r4 = 1
        L_0x00fd:
            int r16 = r3 % 5
            if (r16 != 0) goto L_0x0123
            if (r3 <= 0) goto L_0x0123
            r16 = 0
            r2 = r16
        L_0x0107:
            r5 = 6
            if (r2 >= r5) goto L_0x011e
            int r18 = 5 - r2
            int r18 = r18 * 8
            long r9 = r6 >> r18
            int r10 = (int) r9
            byte r9 = (byte) r10
            r1.write(r9)
            int r2 = r2 + 1
            r5 = 902(0x386, float:1.264E-42)
            r9 = 924(0x39c, float:1.295E-42)
            r10 = 901(0x385, float:1.263E-42)
            goto L_0x0107
        L_0x011e:
            r6 = 0
            r2 = 0
            r3 = r2
            goto L_0x0124
        L_0x0123:
            r5 = 6
        L_0x0124:
            r2 = r13
            r5 = 902(0x386, float:1.264E-42)
            r9 = 924(0x39c, float:1.295E-42)
            r10 = 901(0x385, float:1.263E-42)
            goto L_0x00bc
        L_0x012c:
            r13 = r2
            goto L_0x0130
        L_0x012e:
            r13 = r28
        L_0x0130:
            java.lang.String r2 = new java.lang.String
            byte[] r3 = r1.toByteArray()
            r4 = r27
            r2.<init>(r3, r4)
            r3 = r29
            r3.append(r2)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], java.nio.charset.Charset, int, java.lang.StringBuilder):int");
    }

    private static int numericCompaction(int[] codewords, int code, StringBuilder result) throws FormatException {
        int count = 0;
        boolean end = false;
        int[] numericCodewords = new int[15];
        while (code < codewords[0] && !end) {
            int codeIndex = code + 1;
            int code2 = codewords[code];
            if (codeIndex == codewords[0]) {
                end = true;
            }
            if (code2 < TEXT_COMPACTION_MODE_LATCH) {
                numericCodewords[count] = code2;
                count++;
            } else if (code2 == TEXT_COMPACTION_MODE_LATCH || code2 == BYTE_COMPACTION_MODE_LATCH || code2 == BYTE_COMPACTION_MODE_LATCH_6 || code2 == 928 || code2 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || code2 == MACRO_PDF417_TERMINATOR) {
                codeIndex--;
                end = true;
            }
            if ((count % 15 == 0 || code2 == NUMERIC_COMPACTION_MODE_LATCH || end) && count > 0) {
                result.append(decodeBase900toBase10(numericCodewords, count));
                count = 0;
            }
            code = codeIndex;
        }
        return code;
    }

    private static String decodeBase900toBase10(int[] codewords, int count) throws FormatException {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < count; i++) {
            result = result.add(EXP900[(count - i) - 1].multiply(BigInteger.valueOf((long) codewords[i])));
        }
        String bigInteger = result.toString();
        String resultString = bigInteger;
        if (bigInteger.charAt(0) == '1') {
            return resultString.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
