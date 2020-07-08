package com.google.zxing.datamatrix.decoder;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int numDataCodewords2, byte[] codewords2) {
        this.numDataCodewords = numDataCodewords2;
        this.codewords = codewords2;
    }

    static DataBlock[] getDataBlocks(byte[] rawCodewords, Version version) {
        ECBlocks ecBlocks;
        int iOffset;
        byte[] bArr = rawCodewords;
        ECBlocks ecBlocks2 = version.getECBlocks();
        ECB[] eCBlocks = ecBlocks2.getECBlocks();
        ECB[] ecBlockArray = eCBlocks;
        int totalBlocks = 0;
        int totalBlocks2 = 0;
        while (totalBlocks2 < eCBlocks.length) {
            totalBlocks += eCBlocks[totalBlocks2].getCount();
            totalBlocks2++;
        }
        DataBlock[] result = new DataBlock[totalBlocks];
        int numResultBlocks = 0;
        for (ECB ecBlock : ecBlockArray) {
            int i = 0;
            while (i < ecBlock.getCount()) {
                int numDataCodewords2 = ecBlock.getDataCodewords();
                int numResultBlocks2 = numResultBlocks + 1;
                result[numResultBlocks] = new DataBlock(numDataCodewords2, new byte[(ecBlocks2.getECCodewords() + numDataCodewords2)]);
                i++;
                numResultBlocks = numResultBlocks2;
            }
        }
        int length = result[0].codewords.length - ecBlocks2.getECCodewords();
        int i2 = r8;
        int longerBlocksNumDataCodewords = length;
        boolean z = true;
        int shorterBlocksNumDataCodewords = length - 1;
        int rawCodewordsOffset = 0;
        for (int i3 = 0; i3 < shorterBlocksNumDataCodewords; i3++) {
            int j = 0;
            while (j < numResultBlocks) {
                int rawCodewordsOffset2 = rawCodewordsOffset + 1;
                result[j].codewords[i3] = bArr[rawCodewordsOffset];
                j++;
                rawCodewordsOffset = rawCodewordsOffset2;
            }
        }
        if (version.getVersionNumber() != 24) {
            z = false;
        }
        boolean specialVersion = z;
        int numLongerBlocks = z ? 8 : numResultBlocks;
        int j2 = 0;
        while (j2 < numLongerBlocks) {
            int rawCodewordsOffset3 = rawCodewordsOffset + 1;
            result[j2].codewords[longerBlocksNumDataCodewords - 1] = bArr[rawCodewordsOffset];
            j2++;
            rawCodewordsOffset = rawCodewordsOffset3;
        }
        int max = result[0].codewords.length;
        for (int i4 = longerBlocksNumDataCodewords; i4 < max; i4++) {
            int j3 = 0;
            while (j3 < numResultBlocks) {
                int jOffset = specialVersion ? (j3 + 8) % numResultBlocks : j3;
                if (specialVersion) {
                    ecBlocks = ecBlocks2;
                    if (jOffset > 7) {
                        iOffset = i4 - 1;
                        int longerBlocksTotalCodewords = totalBlocks2;
                        int rawCodewordsOffset4 = rawCodewordsOffset + 1;
                        result[jOffset].codewords[iOffset] = bArr[rawCodewordsOffset];
                        j3++;
                        ecBlocks2 = ecBlocks;
                        totalBlocks2 = longerBlocksTotalCodewords;
                        rawCodewordsOffset = rawCodewordsOffset4;
                    }
                } else {
                    ecBlocks = ecBlocks2;
                }
                iOffset = i4;
                int longerBlocksTotalCodewords2 = totalBlocks2;
                int rawCodewordsOffset42 = rawCodewordsOffset + 1;
                result[jOffset].codewords[iOffset] = bArr[rawCodewordsOffset];
                j3++;
                ecBlocks2 = ecBlocks;
                totalBlocks2 = longerBlocksTotalCodewords2;
                rawCodewordsOffset = rawCodewordsOffset42;
            }
            int i5 = totalBlocks2;
        }
        int i6 = totalBlocks2;
        if (rawCodewordsOffset == bArr.length) {
            return result;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: 0000 */
    public int getNumDataCodewords() {
        return this.numDataCodewords;
    }

    /* access modifiers changed from: 0000 */
    public byte[] getCodewords() {
        return this.codewords;
    }
}
