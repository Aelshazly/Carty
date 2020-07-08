package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns = new DetectionResultColumn[(this.barcodeColumnCount + 2)];

    DetectionResult(BarcodeMetadata barcodeMetadata2, BoundingBox boundingBox2) {
        this.barcodeMetadata = barcodeMetadata2;
        this.barcodeColumnCount = barcodeMetadata2.getColumnCount();
        this.boundingBox = boundingBox2;
    }

    /* access modifiers changed from: 0000 */
    public DetectionResultColumn[] getDetectionResultColumns() {
        int previousUnadjustedCount;
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int unadjustedCodewordCount = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        do {
            previousUnadjustedCount = unadjustedCodewordCount;
            int adjustRowNumbers = adjustRowNumbers();
            unadjustedCodewordCount = adjustRowNumbers;
            if (adjustRowNumbers <= 0) {
                break;
            }
        } while (unadjustedCodewordCount < previousUnadjustedCount);
        return this.detectionResultColumns;
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        int unadjustedCount = adjustRowNumbersByRow;
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int barcodeColumn = 1; barcodeColumn < this.barcodeColumnCount + 1; barcodeColumn++) {
            Codeword[] codewords = this.detectionResultColumns[barcodeColumn].getCodewords();
            for (int codewordsRow = 0; codewordsRow < codewords.length; codewordsRow++) {
                if (codewords[codewordsRow] != null && !codewords[codewordsRow].hasValidRowNumber()) {
                    adjustRowNumbers(barcodeColumn, codewordsRow, codewords);
                }
            }
        }
        return unadjustedCount;
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] != null && detectionResultColumnArr[this.barcodeColumnCount + 1] != null) {
            Codeword[] LRIcodewords = detectionResultColumnArr[0].getCodewords();
            Codeword[] RRIcodewords = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
            for (int codewordsRow = 0; codewordsRow < LRIcodewords.length; codewordsRow++) {
                if (!(LRIcodewords[codewordsRow] == null || RRIcodewords[codewordsRow] == null || LRIcodewords[codewordsRow].getRowNumber() != RRIcodewords[codewordsRow].getRowNumber())) {
                    for (int barcodeColumn = 1; barcodeColumn <= this.barcodeColumnCount; barcodeColumn++) {
                        Codeword codeword = this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow];
                        Codeword codeword2 = codeword;
                        if (codeword != null) {
                            codeword2.setRowNumber(LRIcodewords[codewordsRow].getRowNumber());
                            if (!codeword2.hasValidRowNumber()) {
                                this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i = this.barcodeColumnCount;
        if (detectionResultColumnArr[i + 1] == null) {
            return 0;
        }
        int unadjustedCount = 0;
        Codeword[] codewords = detectionResultColumnArr[i + 1].getCodewords();
        for (int codewordsRow = 0; codewordsRow < codewords.length; codewordsRow++) {
            if (codewords[codewordsRow] != null) {
                int rowIndicatorRowNumber = codewords[codewordsRow].getRowNumber();
                int invalidRowCounts = 0;
                for (int barcodeColumn = this.barcodeColumnCount + 1; barcodeColumn > 0 && invalidRowCounts < 2; barcodeColumn--) {
                    Codeword codeword = this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow];
                    Codeword codeword2 = codeword;
                    if (codeword != null) {
                        invalidRowCounts = adjustRowNumberIfValid(rowIndicatorRowNumber, invalidRowCounts, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            unadjustedCount++;
                        }
                    }
                }
            }
        }
        return unadjustedCount;
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null) {
            return 0;
        }
        int unadjustedCount = 0;
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        for (int codewordsRow = 0; codewordsRow < codewords.length; codewordsRow++) {
            if (codewords[codewordsRow] != null) {
                int rowIndicatorRowNumber = codewords[codewordsRow].getRowNumber();
                int invalidRowCounts = 0;
                for (int barcodeColumn = 1; barcodeColumn < this.barcodeColumnCount + 1 && invalidRowCounts < 2; barcodeColumn++) {
                    Codeword codeword = this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow];
                    Codeword codeword2 = codeword;
                    if (codeword != null) {
                        invalidRowCounts = adjustRowNumberIfValid(rowIndicatorRowNumber, invalidRowCounts, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            unadjustedCount++;
                        }
                    }
                }
            }
        }
        return unadjustedCount;
    }

    private static int adjustRowNumberIfValid(int rowIndicatorRowNumber, int invalidRowCounts, Codeword codeword) {
        if (codeword == null) {
            return invalidRowCounts;
        }
        if (!codeword.hasValidRowNumber()) {
            if (codeword.isValidRowNumber(rowIndicatorRowNumber)) {
                codeword.setRowNumber(rowIndicatorRowNumber);
                invalidRowCounts = 0;
            } else {
                invalidRowCounts++;
            }
        }
        return invalidRowCounts;
    }

    private void adjustRowNumbers(int barcodeColumn, int codewordsRow, Codeword[] codewords) {
        Codeword codeword = codewords[codewordsRow];
        Codeword[] nextColumnCodewords = this.detectionResultColumns[barcodeColumn - 1].getCodewords();
        Codeword[] previousColumnCodewords = nextColumnCodewords;
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[barcodeColumn + 1] != null) {
            nextColumnCodewords = detectionResultColumnArr[barcodeColumn + 1].getCodewords();
        }
        Codeword[] codewordArr = new Codeword[14];
        Codeword[] otherCodewords = codewordArr;
        codewordArr[2] = previousColumnCodewords[codewordsRow];
        otherCodewords[3] = nextColumnCodewords[codewordsRow];
        int i = 0;
        if (codewordsRow > 0) {
            otherCodewords[0] = codewords[codewordsRow - 1];
            otherCodewords[4] = previousColumnCodewords[codewordsRow - 1];
            otherCodewords[5] = nextColumnCodewords[codewordsRow - 1];
        }
        if (codewordsRow > 1) {
            otherCodewords[8] = codewords[codewordsRow - 2];
            otherCodewords[10] = previousColumnCodewords[codewordsRow - 2];
            otherCodewords[11] = nextColumnCodewords[codewordsRow - 2];
        }
        if (codewordsRow < codewords.length - 1) {
            otherCodewords[1] = codewords[codewordsRow + 1];
            otherCodewords[6] = previousColumnCodewords[codewordsRow + 1];
            otherCodewords[7] = nextColumnCodewords[codewordsRow + 1];
        }
        if (codewordsRow < codewords.length - 2) {
            otherCodewords[9] = codewords[codewordsRow + 2];
            otherCodewords[12] = previousColumnCodewords[codewordsRow + 2];
            otherCodewords[13] = nextColumnCodewords[codewordsRow + 2];
        }
        while (i < 14 && !adjustRowNumber(codeword, otherCodewords[i])) {
            i++;
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword otherCodeword) {
        if (otherCodeword == null || !otherCodeword.hasValidRowNumber() || otherCodeword.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(otherCodeword.getRowNumber());
        return true;
    }

    /* access modifiers changed from: 0000 */
    public int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    /* access modifiers changed from: 0000 */
    public int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    /* access modifiers changed from: 0000 */
    public int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    public void setBoundingBox(BoundingBox boundingBox2) {
        this.boundingBox = boundingBox2;
    }

    /* access modifiers changed from: 0000 */
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* access modifiers changed from: 0000 */
    public void setDetectionResultColumn(int barcodeColumn, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[barcodeColumn] = detectionResultColumn;
    }

    /* access modifiers changed from: 0000 */
    public DetectionResultColumn getDetectionResultColumn(int barcodeColumn) {
        return this.detectionResultColumns[barcodeColumn];
    }

    public String toString() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        DetectionResultColumn rowIndicatorColumn = detectionResultColumn;
        if (detectionResultColumn == null) {
            rowIndicatorColumn = detectionResultColumnArr[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        for (int codewordsRow = 0; codewordsRow < rowIndicatorColumn.getCodewords().length; codewordsRow++) {
            formatter.format("CW %3d:", new Object[]{Integer.valueOf(codewordsRow)});
            for (int barcodeColumn = 0; barcodeColumn < this.barcodeColumnCount + 2; barcodeColumn++) {
                DetectionResultColumn[] detectionResultColumnArr2 = this.detectionResultColumns;
                String str = "    |   ";
                if (detectionResultColumnArr2[barcodeColumn] == null) {
                    formatter.format(str, new Object[0]);
                } else {
                    Codeword codeword = detectionResultColumnArr2[barcodeColumn].getCodewords()[codewordsRow];
                    Codeword codeword2 = codeword;
                    if (codeword == null) {
                        formatter.format(str, new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(codeword2.getRowNumber()), Integer.valueOf(codeword2.getValue())});
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
