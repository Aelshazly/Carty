package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox2) {
        this.boundingBox = new BoundingBox(boundingBox2);
        this.codewords = new Codeword[((boundingBox2.getMaxY() - boundingBox2.getMinY()) + 1)];
    }

    /* access modifiers changed from: 0000 */
    public final Codeword getCodewordNearby(int imageRow) {
        Codeword codeword = getCodeword(imageRow);
        Codeword codeword2 = codeword;
        if (codeword != null) {
            return codeword2;
        }
        for (int i = 1; i < 5; i++) {
            int imageRowToCodewordIndex = imageRowToCodewordIndex(imageRow) - i;
            int nearImageRow = imageRowToCodewordIndex;
            if (imageRowToCodewordIndex >= 0) {
                Codeword codeword3 = this.codewords[nearImageRow];
                Codeword codeword4 = codeword3;
                if (codeword3 != null) {
                    return codeword4;
                }
            }
            int imageRowToCodewordIndex2 = imageRowToCodewordIndex(imageRow) + i;
            int nearImageRow2 = imageRowToCodewordIndex2;
            Codeword[] codewordArr = this.codewords;
            if (imageRowToCodewordIndex2 < codewordArr.length) {
                Codeword codeword5 = codewordArr[nearImageRow2];
                Codeword codeword6 = codeword5;
                if (codeword5 != null) {
                    return codeword6;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final int imageRowToCodewordIndex(int imageRow) {
        return imageRow - this.boundingBox.getMinY();
    }

    /* access modifiers changed from: 0000 */
    public final void setCodeword(int imageRow, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(imageRow)] = codeword;
    }

    /* access modifiers changed from: 0000 */
    public final Codeword getCodeword(int imageRow) {
        return this.codewords[imageRowToCodewordIndex(imageRow)];
    }

    /* access modifiers changed from: 0000 */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* access modifiers changed from: 0000 */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    public String toString() {
        Codeword[] codewordArr;
        Formatter formatter = new Formatter();
        int row = 0;
        for (Codeword codeword : this.codewords) {
            Codeword codeword2 = codeword;
            if (codeword == null) {
                int row2 = row + 1;
                formatter.format("%3d:    |   %n", new Object[]{Integer.valueOf(row)});
                row = row2;
            } else {
                int row3 = row + 1;
                formatter.format("%3d: %3d|%3d%n", new Object[]{Integer.valueOf(row), Integer.valueOf(codeword2.getRowNumber()), Integer.valueOf(codeword2.getValue())});
                row = row3;
            }
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
