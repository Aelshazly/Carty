package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    public static AztecCode encode(byte[] data) {
        return encode(data, 33, 0);
    }

    public static AztecCode encode(byte[] data, int minECCPercent, int userSpecifiedLayers) {
        BitArray stuffedBits;
        int totalBitsInLayer;
        int layers;
        boolean compact;
        int wordSize;
        int eccBits;
        int totalSizeBits;
        int totalBitsInLayer2;
        int totalSizeBits2;
        BitArray bits;
        int eccBits2;
        BitArray encode = new HighLevelEncoder(data).encode();
        Object obj = null;
        BitArray bits2 = encode;
        int eccBits3 = ((encode.getSize() * minECCPercent) / 100) + 11;
        int totalSizeBits3 = bits2.getSize() + eccBits3;
        int i = 32;
        if (userSpecifiedLayers != 0) {
            compact = userSpecifiedLayers < 0;
            int abs = Math.abs(userSpecifiedLayers);
            layers = abs;
            if (compact) {
                i = 4;
            }
            if (abs <= i) {
                int totalBitsInLayer3 = totalBitsInLayer(layers, compact);
                int wordSize2 = WORD_SIZE[layers];
                int usableBitsInLayers = totalBitsInLayer3 - (totalBitsInLayer3 % wordSize2);
                BitArray stuffBits = stuffBits(bits2, wordSize2);
                stuffedBits = stuffBits;
                String str = "Data to large for user specified layer";
                if (stuffBits.getSize() + eccBits3 > usableBitsInLayers) {
                    throw new IllegalArgumentException(str);
                } else if (!compact || stuffedBits.getSize() <= (wordSize2 << 6)) {
                    totalBitsInLayer = totalBitsInLayer3;
                    wordSize = wordSize2;
                } else {
                    throw new IllegalArgumentException(str);
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(userSpecifiedLayers)}));
            }
        } else {
            wordSize = 0;
            BitArray stuffedBits2 = null;
            int i2 = 0;
            while (i2 <= i) {
                boolean z = i2 <= 3;
                boolean compact2 = z;
                int i3 = z ? i2 + 1 : i2;
                int layers2 = i3;
                totalBitsInLayer = totalBitsInLayer(i3, compact2);
                if (totalSizeBits3 <= totalBitsInLayer) {
                    int[] iArr = WORD_SIZE;
                    if (wordSize != iArr[layers2]) {
                        wordSize = iArr[layers2];
                        stuffedBits = stuffBits(bits2, wordSize);
                    } else {
                        stuffedBits = stuffedBits2;
                    }
                    int usableBitsInLayers2 = totalBitsInLayer - (totalBitsInLayer % wordSize);
                    if ((!compact2 || stuffedBits.getSize() <= (wordSize << 6)) && stuffedBits.getSize() + eccBits3 <= usableBitsInLayers2) {
                        compact = compact2;
                        layers = layers2;
                    } else {
                        eccBits2 = eccBits3;
                        bits = bits2;
                        totalSizeBits2 = totalSizeBits3;
                        stuffedBits2 = stuffedBits;
                    }
                } else {
                    eccBits2 = eccBits3;
                    bits = bits2;
                    totalSizeBits2 = totalSizeBits3;
                }
                i2++;
                i = 32;
                byte[] bArr = data;
                obj = obj;
                eccBits3 = eccBits2;
                bits2 = bits;
                totalSizeBits3 = totalSizeBits2;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray messageBits = generateCheckWords(stuffedBits, totalBitsInLayer, wordSize);
        int messageSizeInWords = stuffedBits.getSize() / wordSize;
        BitArray modeMessage = generateModeMessage(compact, layers, messageSizeInWords);
        int i4 = (compact ? 11 : 14) + (layers << 2);
        int baseMatrixSize = i4;
        int[] alignmentMap = new int[i4];
        if (compact) {
            int matrixSize = baseMatrixSize;
            for (int i5 = 0; i5 < alignmentMap.length; i5++) {
                alignmentMap[i5] = i5;
            }
            int i6 = eccBits3;
            eccBits = matrixSize;
        } else {
            int matrixSize2 = baseMatrixSize + 1 + ((((baseMatrixSize / 2) - 1) / 15) * 2);
            int origCenter = baseMatrixSize / 2;
            int center = matrixSize2 / 2;
            int i7 = eccBits3;
            for (int i8 = 0; i8 < origCenter; i8++) {
                int newOffset = i8 + (i8 / 15);
                alignmentMap[(origCenter - i8) - 1] = (center - newOffset) - 1;
                alignmentMap[origCenter + i8] = center + newOffset + 1;
            }
            eccBits = matrixSize2;
        }
        BitMatrix matrix = new BitMatrix(eccBits);
        int i9 = 0;
        int rowOffset = 0;
        while (i9 < layers) {
            int rowSize = ((layers - i9) << 2) + (compact ? 9 : 12);
            BitArray bits3 = bits2;
            int j = 0;
            while (j < rowSize) {
                int columnOffset = j << 1;
                int wordSize3 = wordSize;
                int k = 0;
                while (true) {
                    totalSizeBits = totalSizeBits3;
                    if (k >= 2) {
                        break;
                    }
                    if (messageBits.get(rowOffset + columnOffset + k)) {
                        totalBitsInLayer2 = totalBitsInLayer;
                        matrix.set(alignmentMap[(i9 << 1) + k], alignmentMap[(i9 << 1) + j]);
                    } else {
                        totalBitsInLayer2 = totalBitsInLayer;
                    }
                    if (messageBits.get(rowOffset + (rowSize << 1) + columnOffset + k)) {
                        matrix.set(alignmentMap[(i9 << 1) + j], alignmentMap[((baseMatrixSize - 1) - (i9 << 1)) - k]);
                    }
                    if (messageBits.get(rowOffset + (rowSize << 2) + columnOffset + k)) {
                        matrix.set(alignmentMap[((baseMatrixSize - 1) - (i9 << 1)) - k], alignmentMap[((baseMatrixSize - 1) - (i9 << 1)) - j]);
                    }
                    if (messageBits.get(rowOffset + (rowSize * 6) + columnOffset + k)) {
                        matrix.set(alignmentMap[((baseMatrixSize - 1) - (i9 << 1)) - j], alignmentMap[(i9 << 1) + k]);
                    }
                    k++;
                    totalSizeBits3 = totalSizeBits;
                    totalBitsInLayer = totalBitsInLayer2;
                }
                j++;
                totalSizeBits3 = totalSizeBits;
                wordSize = wordSize3;
            }
            int i10 = totalSizeBits3;
            int i11 = totalBitsInLayer;
            rowOffset += rowSize << 3;
            i9++;
            byte[] bArr2 = data;
            bits2 = bits3;
        }
        int i12 = wordSize;
        int i13 = totalSizeBits3;
        int i14 = totalBitsInLayer;
        drawModeMessage(matrix, compact, eccBits, modeMessage);
        if (compact) {
            drawBullsEye(matrix, eccBits / 2, 5);
        } else {
            drawBullsEye(matrix, eccBits / 2, 7);
            int i15 = 0;
            int j2 = 0;
            while (i15 < (baseMatrixSize / 2) - 1) {
                for (int k2 = (eccBits / 2) & 1; k2 < eccBits; k2 += 2) {
                    matrix.set((eccBits / 2) - j2, k2);
                    matrix.set((eccBits / 2) + j2, k2);
                    matrix.set(k2, (eccBits / 2) - j2);
                    matrix.set(k2, (eccBits / 2) + j2);
                }
                i15 += 15;
                j2 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        AztecCode aztec = aztecCode;
        aztecCode.setCompact(compact);
        aztec.setSize(eccBits);
        aztec.setLayers(layers);
        aztec.setCodeWords(messageSizeInWords);
        aztec.setMatrix(matrix);
        return aztec;
    }

    private static void drawBullsEye(BitMatrix matrix, int center, int size) {
        for (int i = 0; i < size; i += 2) {
            for (int j = center - i; j <= center + i; j++) {
                matrix.set(j, center - i);
                matrix.set(j, center + i);
                matrix.set(center - i, j);
                matrix.set(center + i, j);
            }
        }
        matrix.set(center - size, center - size);
        matrix.set((center - size) + 1, center - size);
        matrix.set(center - size, (center - size) + 1);
        matrix.set(center + size, center - size);
        matrix.set(center + size, (center - size) + 1);
        matrix.set(center + size, (center + size) - 1);
    }

    static BitArray generateModeMessage(boolean compact, int layers, int messageSizeInWords) {
        BitArray modeMessage = new BitArray();
        if (compact) {
            modeMessage.appendBits(layers - 1, 2);
            modeMessage.appendBits(messageSizeInWords - 1, 6);
            return generateCheckWords(modeMessage, 28, 4);
        }
        modeMessage.appendBits(layers - 1, 5);
        modeMessage.appendBits(messageSizeInWords - 1, 11);
        return generateCheckWords(modeMessage, 40, 4);
    }

    private static void drawModeMessage(BitMatrix matrix, boolean compact, int matrixSize, BitArray modeMessage) {
        int center = matrixSize / 2;
        if (compact) {
            for (int i = 0; i < 7; i++) {
                int offset = (center - 3) + i;
                if (modeMessage.get(i)) {
                    matrix.set(offset, center - 5);
                }
                if (modeMessage.get(i + 7)) {
                    matrix.set(center + 5, offset);
                }
                if (modeMessage.get(20 - i)) {
                    matrix.set(offset, center + 5);
                }
                if (modeMessage.get(27 - i)) {
                    matrix.set(center - 5, offset);
                }
            }
            return;
        }
        for (int i2 = 0; i2 < 10; i2++) {
            int offset2 = (center - 5) + i2 + (i2 / 5);
            if (modeMessage.get(i2)) {
                matrix.set(offset2, center - 7);
            }
            if (modeMessage.get(i2 + 10)) {
                matrix.set(center + 7, offset2);
            }
            if (modeMessage.get(29 - i2)) {
                matrix.set(offset2, center + 7);
            }
            if (modeMessage.get(39 - i2)) {
                matrix.set(center - 7, offset2);
            }
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int totalBits, int wordSize) {
        int messageSizeInWords = bitArray.getSize() / wordSize;
        ReedSolomonEncoder rs = new ReedSolomonEncoder(getGF(wordSize));
        int totalWords = totalBits / wordSize;
        int[] messageWords = bitsToWords(bitArray, wordSize, totalWords);
        rs.encode(messageWords, totalWords - messageSizeInWords);
        int startPad = totalBits % wordSize;
        BitArray bitArray2 = new BitArray();
        BitArray messageBits = bitArray2;
        bitArray2.appendBits(0, startPad);
        for (int messageWord : messageWords) {
            messageBits.appendBits(messageWord, wordSize);
        }
        return messageBits;
    }

    private static int[] bitsToWords(BitArray stuffedBits, int wordSize, int totalWords) {
        int[] message = new int[totalWords];
        int n = stuffedBits.getSize() / wordSize;
        for (int i = 0; i < n; i++) {
            int value = 0;
            for (int j = 0; j < wordSize; j++) {
                value |= stuffedBits.get((i * wordSize) + j) ? 1 << ((wordSize - j) - 1) : 0;
            }
            message[i] = value;
        }
        return message;
    }

    private static GenericGF getGF(int wordSize) {
        if (wordSize == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (wordSize == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (wordSize == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (wordSize == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (wordSize == 12) {
            return GenericGF.AZTEC_DATA_12;
        }
        StringBuilder sb = new StringBuilder("Unsupported word size ");
        sb.append(wordSize);
        throw new IllegalArgumentException(sb.toString());
    }

    static BitArray stuffBits(BitArray bits, int wordSize) {
        BitArray out = new BitArray();
        int n = bits.getSize();
        int mask = (1 << wordSize) - 2;
        int i = 0;
        while (i < n) {
            int word = 0;
            for (int j = 0; j < wordSize; j++) {
                if (i + j >= n || bits.get(i + j)) {
                    word |= 1 << ((wordSize - 1) - j);
                }
            }
            if ((word & mask) == mask) {
                out.appendBits(word & mask, wordSize);
                i--;
            } else if ((word & mask) == 0) {
                out.appendBits(word | 1, wordSize);
                i--;
            } else {
                out.appendBits(word, wordSize);
            }
            i += wordSize;
        }
        return out;
    }

    private static int totalBitsInLayer(int layers, boolean compact) {
        return ((compact ? 88 : 112) + (layers << 4)) * layers;
    }
}
