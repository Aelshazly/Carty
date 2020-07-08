package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import okhttp3.internal.cache.DiskLruCache;

public final class Decoder {
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", DiskLruCache.VERSION_1, "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private AztecDetectorResult ddata;

    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1 */
    static /* synthetic */ class C09231 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = new int[Table.values().length];

        static {
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public DecoderResult decode(AztecDetectorResult detectorResult) throws FormatException {
        this.ddata = detectorResult;
        boolean[] correctBits = correctBits(extractBits(detectorResult.getBits()));
        boolean[] correctedBits = correctBits;
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctedBits), null, null);
        DecoderResult decoderResult2 = decoderResult;
        decoderResult.setNumBits(correctedBits.length);
        return decoderResult2;
    }

    public static String highLevelDecode(boolean[] correctedBits) {
        return getEncodedData(correctedBits);
    }

    private static String getEncodedData(boolean[] correctedBits) {
        int endIndex = correctedBits.length;
        Table latchTable = Table.UPPER;
        Table shiftTable = Table.UPPER;
        StringBuilder result = new StringBuilder(20);
        int index = 0;
        while (index < endIndex) {
            if (shiftTable == Table.BINARY) {
                if (endIndex - index < 5) {
                    break;
                }
                int length = readCode(correctedBits, index, 5);
                index += 5;
                if (length == 0) {
                    if (endIndex - index < 11) {
                        break;
                    }
                    length = readCode(correctedBits, index, 11) + 31;
                    index += 11;
                }
                int charCount = 0;
                while (true) {
                    if (charCount >= length) {
                        break;
                    } else if (endIndex - index < 8) {
                        index = endIndex;
                        break;
                    } else {
                        result.append((char) readCode(correctedBits, index, 8));
                        index += 8;
                        charCount++;
                    }
                }
                shiftTable = latchTable;
            } else {
                int size = shiftTable == Table.DIGIT ? 4 : 5;
                if (endIndex - index < size) {
                    break;
                }
                int code = readCode(correctedBits, index, size);
                index += size;
                String character = getCharacter(shiftTable, code);
                String str = character;
                if (character.startsWith("CTRL_")) {
                    latchTable = shiftTable;
                    shiftTable = getTable(str.charAt(5));
                    if (str.charAt(6) == 'L') {
                        latchTable = shiftTable;
                    }
                } else {
                    result.append(str);
                    shiftTable = latchTable;
                }
            }
        }
        return result.toString();
    }

    private static Table getTable(char t) {
        if (t == 'B') {
            return Table.BINARY;
        }
        if (t == 'D') {
            return Table.DIGIT;
        }
        if (t == 'P') {
            return Table.PUNCT;
        }
        if (t == 'L') {
            return Table.LOWER;
        }
        if (t != 'M') {
            return Table.UPPER;
        }
        return Table.MIXED;
    }

    private static String getCharacter(Table table, int code) {
        int i = C09231.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i == 1) {
            return UPPER_TABLE[code];
        }
        if (i == 2) {
            return LOWER_TABLE[code];
        }
        if (i == 3) {
            return MIXED_TABLE[code];
        }
        if (i == 4) {
            return PUNCT_TABLE[code];
        }
        if (i == 5) {
            return DIGIT_TABLE[code];
        }
        throw new IllegalStateException("Bad table");
    }

    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v12, types: [int] */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: type inference failed for: r0v14, types: [boolean] */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: type inference failed for: r0v23 */
    /* JADX WARNING: type inference failed for: r0v24 */
    /* JADX WARNING: type inference failed for: r0v25 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v12, types: [int]
      assigns: []
      uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], int, ?[int, byte, short, char], boolean]
      mth insns count: 99
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean[] correctBits(boolean[] r19) throws com.google.zxing.FormatException {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            com.google.zxing.aztec.AztecDetectorResult r0 = r1.ddata
            int r0 = r0.getNbLayers()
            r3 = 2
            if (r0 > r3) goto L_0x0013
            r0 = 6
            com.google.zxing.common.reedsolomon.GenericGF r3 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_6
            r4 = r3
            r3 = r0
            goto L_0x003b
        L_0x0013:
            com.google.zxing.aztec.AztecDetectorResult r0 = r1.ddata
            int r0 = r0.getNbLayers()
            r3 = 8
            if (r0 > r3) goto L_0x0024
            r0 = 8
            com.google.zxing.common.reedsolomon.GenericGF r3 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_8
            r4 = r3
            r3 = r0
            goto L_0x003b
        L_0x0024:
            com.google.zxing.aztec.AztecDetectorResult r0 = r1.ddata
            int r0 = r0.getNbLayers()
            r3 = 22
            if (r0 > r3) goto L_0x0035
            r0 = 10
            com.google.zxing.common.reedsolomon.GenericGF r3 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_10
            r4 = r3
            r3 = r0
            goto L_0x003b
        L_0x0035:
            r0 = 12
            com.google.zxing.common.reedsolomon.GenericGF r3 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_12
            r4 = r3
            r3 = r0
        L_0x003b:
            com.google.zxing.aztec.AztecDetectorResult r0 = r1.ddata
            int r5 = r0.getNbDatablocks()
            int r0 = r2.length
            int r0 = r0 / r3
            r6 = 0
            r7 = r6
            r7 = r0
            if (r0 < r5) goto L_0x00cc
            int r0 = r2.length
            int r0 = r0 % r3
            int[] r8 = new int[r7]
            r9 = 0
            r10 = r0
        L_0x004e:
            if (r9 >= r7) goto L_0x005a
            int r0 = readCode(r2, r10, r3)
            r8[r9] = r0
            int r9 = r9 + 1
            int r10 = r10 + r3
            goto L_0x004e
        L_0x005a:
            r9 = 0
            com.google.zxing.common.reedsolomon.ReedSolomonDecoder r0 = new com.google.zxing.common.reedsolomon.ReedSolomonDecoder     // Catch:{ ReedSolomonException -> 0x00c5 }
            r0.<init>(r4)     // Catch:{ ReedSolomonException -> 0x00c5 }
            int r11 = r7 - r5
            r0.decode(r8, r11)     // Catch:{ ReedSolomonException -> 0x00c5 }
            r0 = 1
            int r9 = r0 << r3
            int r9 = r9 - r0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x006d:
            if (r12 >= r5) goto L_0x0086
            r14 = r8[r12]
            r13 = r14
            if (r14 == 0) goto L_0x0081
            if (r13 == r9) goto L_0x0081
            if (r13 == r0) goto L_0x007c
            int r14 = r9 + -1
            if (r13 != r14) goto L_0x007e
        L_0x007c:
            int r11 = r11 + 1
        L_0x007e:
            int r12 = r12 + 1
            goto L_0x006d
        L_0x0081:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0086:
            int r12 = r5 * r3
            int r12 = r12 - r11
            boolean[] r12 = new boolean[r12]
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x008e:
            if (r14 >= r5) goto L_0x00c4
            r6 = r8[r14]
            r15 = r6
            if (r6 == r0) goto L_0x00b2
            int r6 = r9 + -1
            if (r15 != r6) goto L_0x009a
            goto L_0x00b2
        L_0x009a:
            int r6 = r3 + -1
        L_0x009c:
            if (r6 < 0) goto L_0x00bf
            int r16 = r13 + 1
            int r17 = r0 << r6
            r17 = r15 & r17
            if (r17 == 0) goto L_0x00a9
            r17 = 1
            goto L_0x00ab
        L_0x00a9:
            r17 = 0
        L_0x00ab:
            r12[r13] = r17
            int r6 = r6 + -1
            r13 = r16
            goto L_0x009c
        L_0x00b2:
            int r6 = r13 + r3
            int r6 = r6 - r0
            if (r15 <= r0) goto L_0x00b8
            goto L_0x00b9
        L_0x00b8:
            r0 = 0
        L_0x00b9:
            java.util.Arrays.fill(r12, r13, r6, r0)
            int r0 = r3 + -1
            int r13 = r13 + r0
        L_0x00bf:
            int r14 = r14 + 1
            r0 = 1
            r6 = 0
            goto L_0x008e
        L_0x00c4:
            return r12
        L_0x00c5:
            r0 = move-exception
            r6 = r9
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance(r0)
            throw r0
        L_0x00cc:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.correctBits(boolean[]):boolean[]");
    }

    private boolean[] extractBits(BitMatrix matrix) {
        BitMatrix bitMatrix = matrix;
        boolean compact = this.ddata.isCompact();
        int layers = this.ddata.getNbLayers();
        int i = (compact ? 11 : 14) + (layers << 2);
        int baseMatrixSize = i;
        int[] alignmentMap = new int[i];
        boolean[] rawbits = new boolean[totalBitsInLayer(layers, compact)];
        int i2 = 2;
        if (compact) {
            for (int i3 = 0; i3 < alignmentMap.length; i3++) {
                alignmentMap[i3] = i3;
            }
        } else {
            int origCenter = baseMatrixSize / 2;
            int center = ((baseMatrixSize + 1) + ((((baseMatrixSize / 2) - 1) / 15) * 2)) / 2;
            for (int i4 = 0; i4 < origCenter; i4++) {
                int newOffset = (i4 / 15) + i4;
                alignmentMap[(origCenter - i4) - 1] = (center - newOffset) - 1;
                alignmentMap[origCenter + i4] = center + newOffset + 1;
            }
        }
        int i5 = 0;
        int rowOffset = 0;
        while (i5 < layers) {
            int rowSize = ((layers - i5) << i2) + (compact ? 9 : 12);
            int low = i5 << 1;
            int high = (baseMatrixSize - 1) - low;
            int j = 0;
            while (j < rowSize) {
                int columnOffset = j << 1;
                int k = 0;
                while (k < i2) {
                    rawbits[rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[low + k], alignmentMap[low + j]);
                    boolean compact2 = compact;
                    rawbits[(rowSize * 2) + rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[low + j], alignmentMap[high - k]);
                    rawbits[(rowSize * 4) + rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[high - k], alignmentMap[high - j]);
                    rawbits[(rowSize * 6) + rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[high - j], alignmentMap[low + k]);
                    k++;
                    i2 = 2;
                    compact = compact2;
                }
                j++;
                i2 = 2;
            }
            rowOffset += rowSize << 3;
            i5++;
            i2 = 2;
        }
        return rawbits;
    }

    private static int readCode(boolean[] rawbits, int startIndex, int length) {
        int res = 0;
        for (int i = startIndex; i < startIndex + length; i++) {
            res <<= 1;
            if (rawbits[i]) {
                res |= 1;
            }
        }
        return res;
    }

    private static byte readByte(boolean[] rawbits, int startIndex) {
        int length = rawbits.length - startIndex;
        int n = length;
        if (length >= 8) {
            return (byte) readCode(rawbits, startIndex, 8);
        }
        return (byte) (readCode(rawbits, startIndex, n) << (8 - n));
    }

    static byte[] convertBoolArrayToByteArray(boolean[] boolArr) {
        byte[] byteArr = new byte[((boolArr.length + 7) / 8)];
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = readByte(boolArr, i << 3);
        }
        return byteArr;
    }

    private static int totalBitsInLayer(int layers, boolean compact) {
        return ((compact ? 88 : 112) + (layers << 4)) * layers;
    }
}
