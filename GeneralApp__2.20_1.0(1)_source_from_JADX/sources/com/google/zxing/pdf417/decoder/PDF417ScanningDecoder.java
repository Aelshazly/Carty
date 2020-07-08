package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.p007ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;

public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    public static DecoderResult decode(BitMatrix image, ResultPoint imageTopLeft, ResultPoint imageBottomLeft, ResultPoint imageTopRight, ResultPoint imageBottomRight, int minCodewordWidth, int maxCodewordWidth) throws NotFoundException, FormatException, ChecksumException {
        int barcodeColumnCount;
        DetectionResultColumn detectionResultColumn;
        int barcodeColumnCount2;
        int barcodeColumn;
        int maxCodewordWidth2;
        int maxCodewordWidth3;
        DetectionResultColumn detectionResultColumn2;
        int startColumn;
        BoundingBox boundingBox = new BoundingBox(image, imageTopLeft, imageBottomLeft, imageTopRight, imageBottomRight);
        DetectionResultRowIndicatorColumn leftRowIndicatorColumn = null;
        DetectionResultRowIndicatorColumn rightRowIndicatorColumn = null;
        DetectionResult detectionResult = null;
        int i = 0;
        while (true) {
            if (i >= 2) {
                break;
            }
            if (imageTopLeft != null) {
                leftRowIndicatorColumn = getRowIndicatorColumn(image, boundingBox, imageTopLeft, true, minCodewordWidth, maxCodewordWidth);
            }
            if (imageTopRight != null) {
                rightRowIndicatorColumn = getRowIndicatorColumn(image, boundingBox, imageTopRight, false, minCodewordWidth, maxCodewordWidth);
            }
            DetectionResult merge = merge(leftRowIndicatorColumn, rightRowIndicatorColumn);
            detectionResult = merge;
            if (merge == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i != 0 || detectionResult.getBoundingBox() == null || (detectionResult.getBoundingBox().getMinY() >= boundingBox.getMinY() && detectionResult.getBoundingBox().getMaxY() <= boundingBox.getMaxY())) {
                detectionResult.setBoundingBox(boundingBox);
            } else {
                boundingBox = detectionResult.getBoundingBox();
                i++;
            }
        }
        detectionResult.setBoundingBox(boundingBox);
        int maxBarcodeColumn = detectionResult.getBarcodeColumnCount() + 1;
        detectionResult.setDetectionResultColumn(0, leftRowIndicatorColumn);
        detectionResult.setDetectionResultColumn(maxBarcodeColumn, rightRowIndicatorColumn);
        boolean leftToRight = leftRowIndicatorColumn != null;
        int barcodeColumnCount3 = 1;
        Codeword codeword = null;
        int imageRow = minCodewordWidth;
        int maxCodewordWidth4 = maxCodewordWidth;
        while (barcodeColumnCount3 <= maxBarcodeColumn) {
            int barcodeColumn2 = leftToRight ? barcodeColumnCount3 : maxBarcodeColumn - barcodeColumnCount3;
            if (detectionResult.getDetectionResultColumn(barcodeColumn2) == null) {
                if (barcodeColumn2 == 0 || barcodeColumn2 == maxBarcodeColumn) {
                    detectionResultColumn = new DetectionResultRowIndicatorColumn(boundingBox, barcodeColumn2 == 0);
                } else {
                    detectionResultColumn = new DetectionResultColumn(boundingBox);
                }
                detectionResult.setDetectionResultColumn(barcodeColumn2, detectionResultColumn);
                int i2 = maxCodewordWidth4;
                int maxCodewordWidth5 = imageRow;
                int imageRow2 = i2;
                Codeword codeword2 = codeword;
                int minCodewordWidth2 = -1;
                int imageRow3 = boundingBox.getMinY();
                while (imageRow3 <= boundingBox.getMaxY()) {
                    int startColumn2 = getStartColumn(detectionResult, barcodeColumn2, imageRow3, leftToRight);
                    int startColumn3 = startColumn2;
                    if (startColumn2 >= 0 && startColumn3 <= boundingBox.getMaxX()) {
                        startColumn = startColumn3;
                    } else if (minCodewordWidth2 != -1) {
                        startColumn = minCodewordWidth2;
                    } else {
                        int previousStartColumn = minCodewordWidth2;
                        detectionResultColumn2 = detectionResultColumn;
                        barcodeColumn = barcodeColumn2;
                        barcodeColumnCount2 = barcodeColumnCount3;
                        maxCodewordWidth2 = imageRow2;
                        maxCodewordWidth3 = imageRow3;
                        int imageRow4 = maxCodewordWidth5;
                        imageRow3 = maxCodewordWidth3 + 1;
                        imageRow2 = maxCodewordWidth2;
                        barcodeColumn2 = barcodeColumn;
                        barcodeColumnCount3 = barcodeColumnCount2;
                        detectionResultColumn = detectionResultColumn2;
                    }
                    int maxCodewordWidth6 = imageRow2;
                    int minCodewordWidth3 = maxCodewordWidth5;
                    int previousStartColumn2 = minCodewordWidth2;
                    int imageRow5 = imageRow3;
                    detectionResultColumn2 = detectionResultColumn;
                    barcodeColumn = barcodeColumn2;
                    barcodeColumnCount2 = barcodeColumnCount3;
                    Codeword detectCodeword = detectCodeword(image, boundingBox.getMinX(), boundingBox.getMaxX(), leftToRight, startColumn, imageRow5, minCodewordWidth3, maxCodewordWidth6);
                    Codeword codeword3 = codeword2;
                    Codeword codeword4 = detectCodeword;
                    if (detectCodeword != null) {
                        maxCodewordWidth3 = imageRow5;
                        detectionResultColumn2.setCodeword(maxCodewordWidth3, codeword4);
                        int previousStartColumn3 = startColumn;
                        codeword2 = codeword4;
                        maxCodewordWidth5 = Math.min(minCodewordWidth3, codeword4.getWidth());
                        maxCodewordWidth2 = Math.max(maxCodewordWidth6, codeword4.getWidth());
                        minCodewordWidth2 = previousStartColumn3;
                    } else {
                        maxCodewordWidth2 = maxCodewordWidth6;
                        maxCodewordWidth3 = imageRow5;
                        int i3 = startColumn;
                        codeword2 = codeword4;
                        maxCodewordWidth5 = minCodewordWidth3;
                        minCodewordWidth2 = previousStartColumn2;
                    }
                    imageRow3 = maxCodewordWidth3 + 1;
                    imageRow2 = maxCodewordWidth2;
                    barcodeColumn2 = barcodeColumn;
                    barcodeColumnCount3 = barcodeColumnCount2;
                    detectionResultColumn = detectionResultColumn2;
                }
                int i4 = minCodewordWidth2;
                DetectionResultColumn detectionResultColumn3 = detectionResultColumn;
                int i5 = barcodeColumn2;
                barcodeColumnCount = barcodeColumnCount3;
                int maxCodewordWidth7 = imageRow2;
                int maxCodewordWidth8 = imageRow3;
                imageRow = maxCodewordWidth5;
                maxCodewordWidth4 = maxCodewordWidth7;
                codeword = codeword2;
            } else {
                barcodeColumnCount = barcodeColumnCount3;
            }
            barcodeColumnCount3 = barcodeColumnCount + 1;
        }
        return createDecoderResult(detectionResult);
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn leftRowIndicatorColumn, DetectionResultRowIndicatorColumn rightRowIndicatorColumn) throws NotFoundException {
        if (leftRowIndicatorColumn == null && rightRowIndicatorColumn == null) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata(leftRowIndicatorColumn, rightRowIndicatorColumn);
        BarcodeMetadata barcodeMetadata2 = barcodeMetadata;
        if (barcodeMetadata == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata2, BoundingBox.merge(adjustBoundingBox(leftRowIndicatorColumn), adjustBoundingBox(rightRowIndicatorColumn)));
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn rowIndicatorColumn) throws NotFoundException {
        if (rowIndicatorColumn == null) {
            return null;
        }
        int[] rowHeights = rowIndicatorColumn.getRowHeights();
        int[] rowHeights2 = rowHeights;
        if (rowHeights == null) {
            return null;
        }
        int maxRowHeight = getMax(rowHeights2);
        int missingStartRows = 0;
        for (int rowHeight : rowHeights2) {
            missingStartRows += maxRowHeight - rowHeight;
            if (rowHeight > 0) {
                break;
            }
        }
        Codeword[] codewords = rowIndicatorColumn.getCodewords();
        int row = 0;
        while (missingStartRows > 0 && codewords[row] == null) {
            missingStartRows--;
            row++;
        }
        int missingEndRows = 0;
        for (int row2 = rowHeights2.length - 1; row2 >= 0; row2--) {
            missingEndRows += maxRowHeight - rowHeights2[row2];
            if (rowHeights2[row2] > 0) {
                break;
            }
        }
        int row3 = codewords.length - 1;
        while (missingEndRows > 0 && codewords[row3] == null) {
            missingEndRows--;
            row3--;
        }
        return rowIndicatorColumn.getBoundingBox().addMissingRows(missingStartRows, missingEndRows, rowIndicatorColumn.isLeft());
    }

    private static int getMax(int[] values) {
        int maxValue = -1;
        for (int value : values) {
            maxValue = Math.max(maxValue, value);
        }
        return maxValue;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn leftRowIndicatorColumn, DetectionResultRowIndicatorColumn rightRowIndicatorColumn) {
        if (leftRowIndicatorColumn != null) {
            BarcodeMetadata barcodeMetadata = leftRowIndicatorColumn.getBarcodeMetadata();
            BarcodeMetadata leftBarcodeMetadata = barcodeMetadata;
            if (barcodeMetadata != null) {
                if (rightRowIndicatorColumn != null) {
                    BarcodeMetadata barcodeMetadata2 = rightRowIndicatorColumn.getBarcodeMetadata();
                    BarcodeMetadata rightBarcodeMetadata = barcodeMetadata2;
                    if (barcodeMetadata2 == null || leftBarcodeMetadata.getColumnCount() == rightBarcodeMetadata.getColumnCount() || leftBarcodeMetadata.getErrorCorrectionLevel() == rightBarcodeMetadata.getErrorCorrectionLevel() || leftBarcodeMetadata.getRowCount() == rightBarcodeMetadata.getRowCount()) {
                        return leftBarcodeMetadata;
                    }
                    return null;
                }
                return leftBarcodeMetadata;
            }
        }
        if (rightRowIndicatorColumn == null) {
            return null;
        }
        return rightRowIndicatorColumn.getBarcodeMetadata();
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix image, BoundingBox boundingBox, ResultPoint startPoint, boolean leftToRight, int minCodewordWidth, int maxCodewordWidth) {
        boolean z = leftToRight;
        DetectionResultRowIndicatorColumn rowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        Codeword codeword = null;
        int i = 0;
        while (i < 2) {
            int increment = i == 0 ? 1 : -1;
            int startColumn = (int) startPoint.getX();
            Codeword codeword2 = codeword;
            int imageRow = (int) startPoint.getY();
            while (imageRow <= boundingBox.getMaxY() && imageRow >= boundingBox.getMinY()) {
                Codeword detectCodeword = detectCodeword(image, 0, image.getWidth(), leftToRight, startColumn, imageRow, minCodewordWidth, maxCodewordWidth);
                Codeword codeword3 = codeword2;
                codeword2 = detectCodeword;
                if (detectCodeword != null) {
                    rowIndicatorColumn.setCodeword(imageRow, codeword2);
                    if (z) {
                        startColumn = codeword2.getStartX();
                    } else {
                        startColumn = codeword2.getEndX();
                    }
                }
                imageRow += increment;
            }
            i++;
            codeword = codeword2;
        }
        return rowIndicatorColumn;
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeMatrix) throws NotFoundException {
        int[] numberOfCodewords = barcodeMatrix[0][1].getValue();
        int calculatedNumberOfCodewords = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (numberOfCodewords.length != 0) {
            if (numberOfCodewords[0] != calculatedNumberOfCodewords) {
                barcodeMatrix[0][1].setValue(calculatedNumberOfCodewords);
            }
        } else if (calculatedNumberOfCodewords <= 0 || calculatedNumberOfCodewords > 928) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            barcodeMatrix[0][1].setValue(calculatedNumberOfCodewords);
        }
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] barcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, barcodeMatrix);
        Collection<Integer> erasures = new ArrayList<>();
        int[] codewords = new int[(detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount())];
        List<int[]> ambiguousIndexValuesList = new ArrayList<>();
        List<Integer> ambiguousIndexesList = new ArrayList<>();
        for (int row = 0; row < detectionResult.getBarcodeRowCount(); row++) {
            for (int column = 0; column < detectionResult.getBarcodeColumnCount(); column++) {
                int[] values = barcodeMatrix[row][column + 1].getValue();
                int codewordIndex = (detectionResult.getBarcodeColumnCount() * row) + column;
                if (values.length == 0) {
                    erasures.add(Integer.valueOf(codewordIndex));
                } else if (values.length == 1) {
                    codewords[codewordIndex] = values[0];
                } else {
                    ambiguousIndexesList.add(Integer.valueOf(codewordIndex));
                    ambiguousIndexValuesList.add(values);
                }
            }
        }
        int[][] ambiguousIndexValues = new int[ambiguousIndexValuesList.size()][];
        for (int i = 0; i < ambiguousIndexValues.length; i++) {
            ambiguousIndexValues[i] = (int[]) ambiguousIndexValuesList.get(i);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), codewords, PDF417Common.toIntArray(erasures), PDF417Common.toIntArray(ambiguousIndexesList), ambiguousIndexValues);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int ecLevel, int[] codewords, int[] erasureArray, int[] ambiguousIndexes, int[][] ambiguousIndexValues) throws FormatException, ChecksumException {
        int[] ambiguousIndexCount = new int[ambiguousIndexes.length];
        int i = 100;
        while (true) {
            int tries = i - 1;
            if (i > 0) {
                for (int i2 = 0; i2 < ambiguousIndexCount.length; i2++) {
                    codewords[ambiguousIndexes[i2]] = ambiguousIndexValues[i2][ambiguousIndexCount[i2]];
                }
                try {
                    return decodeCodewords(codewords, ecLevel, erasureArray);
                } catch (ChecksumException e) {
                    if (ambiguousIndexCount.length != 0) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= ambiguousIndexCount.length) {
                                break;
                            } else if (ambiguousIndexCount[i3] < ambiguousIndexValues[i3].length - 1) {
                                ambiguousIndexCount[i3] = ambiguousIndexCount[i3] + 1;
                                break;
                            } else {
                                ambiguousIndexCount[i3] = 0;
                                if (i3 != ambiguousIndexCount.length - 1) {
                                    i3++;
                                } else {
                                    throw ChecksumException.getChecksumInstance();
                                }
                            }
                        }
                        i = tries;
                    } else {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        DetectionResultColumn[] detectionResultColumns;
        Codeword[] codewords;
        BarcodeValue[][] barcodeMatrix = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, new int[]{detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2});
        for (int row = 0; row < barcodeMatrix.length; row++) {
            for (int column = 0; column < barcodeMatrix[row].length; column++) {
                barcodeMatrix[row][column] = new BarcodeValue();
            }
        }
        Codeword codeword = null;
        int i = 0;
        int column2 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            DetectionResultColumn detectionResultColumn2 = detectionResultColumn;
            if (detectionResultColumn != null) {
                int rowNumber = i;
                Codeword codeword2 = codeword;
                for (Codeword codeword3 : detectionResultColumn2.getCodewords()) {
                    codeword2 = codeword3;
                    if (codeword3 != null) {
                        int rowNumber2 = codeword2.getRowNumber();
                        rowNumber = rowNumber2;
                        if (rowNumber2 >= 0 && rowNumber < barcodeMatrix.length) {
                            barcodeMatrix[rowNumber][column2].setValue(codeword2.getValue());
                        }
                    }
                }
                codeword = codeword2;
                i = rowNumber;
            }
            column2++;
        }
        return barcodeMatrix;
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int barcodeColumn) {
        return barcodeColumn >= 0 && barcodeColumn <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static int getStartColumn(DetectionResult detectionResult, int barcodeColumn, int imageRow, boolean leftToRight) {
        Codeword[] codewords;
        int offset = leftToRight ? 1 : -1;
        Codeword codeword = null;
        if (isValidBarcodeColumn(detectionResult, barcodeColumn - offset)) {
            codeword = detectionResult.getDetectionResultColumn(barcodeColumn - offset).getCodeword(imageRow);
        }
        if (codeword != null) {
            return leftToRight ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(barcodeColumn).getCodewordNearby(imageRow);
        Codeword codeword2 = codewordNearby;
        if (codewordNearby != null) {
            return leftToRight ? codeword2.getStartX() : codeword2.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, barcodeColumn - offset)) {
            codeword2 = detectionResult.getDetectionResultColumn(barcodeColumn - offset).getCodewordNearby(imageRow);
        }
        if (codeword2 != null) {
            return leftToRight ? codeword2.getEndX() : codeword2.getStartX();
        }
        int skippedColumns = 0;
        while (isValidBarcodeColumn(detectionResult, barcodeColumn - offset)) {
            barcodeColumn -= offset;
            for (Codeword codeword3 : detectionResult.getDetectionResultColumn(barcodeColumn).getCodewords()) {
                Codeword previousRowCodeword = codeword3;
                if (codeword3 != null) {
                    return (leftToRight ? previousRowCodeword.getEndX() : previousRowCodeword.getStartX()) + (offset * skippedColumns * (previousRowCodeword.getEndX() - previousRowCodeword.getStartX()));
                }
            }
            skippedColumns++;
        }
        BoundingBox boundingBox = detectionResult.getBoundingBox();
        return leftToRight ? boundingBox.getMinX() : boundingBox.getMaxX();
    }

    private static Codeword detectCodeword(BitMatrix image, int minColumn, int maxColumn, boolean leftToRight, int startColumn, int imageRow, int minCodewordWidth, int maxCodewordWidth) {
        int endColumn;
        int startColumn2 = adjustCodewordStartColumn(image, minColumn, maxColumn, leftToRight, startColumn, imageRow);
        int[] moduleBitCount = getModuleBitCount(image, minColumn, maxColumn, leftToRight, startColumn2, imageRow);
        int[] moduleBitCount2 = moduleBitCount;
        if (moduleBitCount == null) {
            return null;
        }
        int codewordBitCount = MathUtils.sum(moduleBitCount2);
        if (leftToRight) {
            endColumn = startColumn2 + codewordBitCount;
        } else {
            for (int i = 0; i < moduleBitCount2.length / 2; i++) {
                int tmpCount = moduleBitCount2[i];
                moduleBitCount2[i] = moduleBitCount2[(moduleBitCount2.length - 1) - i];
                moduleBitCount2[(moduleBitCount2.length - 1) - i] = tmpCount;
            }
            endColumn = startColumn2;
            startColumn2 -= codewordBitCount;
        }
        if (!checkCodewordSkew(codewordBitCount, minCodewordWidth, maxCodewordWidth)) {
            return null;
        }
        int decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount2);
        int decodedValue2 = decodedValue;
        int codeword = PDF417Common.getCodeword(decodedValue);
        int codeword2 = codeword;
        if (codeword == -1) {
            return null;
        }
        return new Codeword(startColumn2, endColumn, getCodewordBucketNumber(decodedValue2), codeword2);
    }

    private static int[] getModuleBitCount(BitMatrix image, int minColumn, int maxColumn, boolean leftToRight, int startColumn, int imageRow) {
        int imageColumn = startColumn;
        int[] moduleBitCount = new int[8];
        int moduleNumber = 0;
        int increment = leftToRight ? 1 : -1;
        boolean previousPixelValue = leftToRight;
        while (true) {
            if (!leftToRight) {
                if (imageColumn < minColumn) {
                    break;
                }
            } else if (imageColumn >= maxColumn) {
                break;
            }
            if (moduleNumber >= 8) {
                break;
            } else if (image.get(imageColumn, imageRow) == previousPixelValue) {
                moduleBitCount[moduleNumber] = moduleBitCount[moduleNumber] + 1;
                imageColumn += increment;
            } else {
                moduleNumber++;
                previousPixelValue = !previousPixelValue;
            }
        }
        if (moduleNumber != 8) {
            if (!(imageColumn == (leftToRight ? maxColumn : minColumn) && moduleNumber == 7)) {
                return null;
            }
        }
        return moduleBitCount;
    }

    private static int getNumberOfECCodeWords(int barcodeECLevel) {
        return 2 << barcodeECLevel;
    }

    private static int adjustCodewordStartColumn(BitMatrix image, int minColumn, int maxColumn, boolean leftToRight, int codewordStartColumn, int imageRow) {
        int correctedStartColumn = codewordStartColumn;
        int increment = leftToRight ? -1 : 1;
        for (int i = 0; i < 2; i++) {
            while (true) {
                if (!leftToRight) {
                    if (correctedStartColumn >= maxColumn) {
                        break;
                    }
                } else if (correctedStartColumn < minColumn) {
                    break;
                }
                if (leftToRight != image.get(correctedStartColumn, imageRow)) {
                    break;
                } else if (Math.abs(codewordStartColumn - correctedStartColumn) > 2) {
                    return codewordStartColumn;
                } else {
                    correctedStartColumn += increment;
                }
            }
            increment = -increment;
            leftToRight = !leftToRight;
        }
        return correctedStartColumn;
    }

    private static boolean checkCodewordSkew(int codewordSize, int minCodewordWidth, int maxCodewordWidth) {
        return minCodewordWidth + -2 <= codewordSize && codewordSize <= maxCodewordWidth + 2;
    }

    private static DecoderResult decodeCodewords(int[] codewords, int ecLevel, int[] erasures) throws FormatException, ChecksumException {
        if (codewords.length != 0) {
            int numECCodewords = 1 << (ecLevel + 1);
            int correctedErrorsCount = correctErrors(codewords, erasures, numECCodewords);
            verifyCodewordCount(codewords, numECCodewords);
            DecoderResult decode = DecodedBitStreamParser.decode(codewords, String.valueOf(ecLevel));
            DecoderResult decoderResult = decode;
            decode.setErrorsCorrected(Integer.valueOf(correctedErrorsCount));
            decoderResult.setErasures(Integer.valueOf(erasures.length));
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static int correctErrors(int[] codewords, int[] erasures, int numECCodewords) throws ChecksumException {
        if ((erasures == null || erasures.length <= (numECCodewords / 2) + 3) && numECCodewords >= 0 && numECCodewords <= 512) {
            return errorCorrection.decode(codewords, numECCodewords, erasures);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static void verifyCodewordCount(int[] codewords, int numECCodewords) throws FormatException {
        if (codewords.length >= 4) {
            int i = codewords[0];
            int numberOfCodewords = i;
            if (i > codewords.length) {
                throw FormatException.getFormatInstance();
            } else if (numberOfCodewords != 0) {
            } else {
                if (numECCodewords < codewords.length) {
                    codewords[0] = codewords.length - numECCodewords;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int[] getBitCountForCodeword(int codeword) {
        int[] result = new int[8];
        int previousValue = 0;
        int i = 7;
        while (true) {
            if ((codeword & 1) != previousValue) {
                previousValue = codeword & 1;
                i--;
                if (i < 0) {
                    return result;
                }
            }
            result[i] = result[i] + 1;
            codeword >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int codeword) {
        return getCodewordBucketNumber(getBitCountForCodeword(codeword));
    }

    private static int getCodewordBucketNumber(int[] moduleBitCount) {
        return ((((moduleBitCount[0] - moduleBitCount[2]) + moduleBitCount[4]) - moduleBitCount[6]) + 9) % 9;
    }

    public static String toString(BarcodeValue[][] barcodeMatrix) {
        Formatter formatter = new Formatter();
        for (int row = 0; row < barcodeMatrix.length; row++) {
            formatter.format("Row %2d: ", new Object[]{Integer.valueOf(row)});
            for (BarcodeValue barcodeValue : barcodeMatrix[row]) {
                BarcodeValue barcodeValue2 = barcodeValue;
                if (barcodeValue.getValue().length == 0) {
                    formatter.format("        ", null);
                } else {
                    formatter.format("%4d(%2d)", new Object[]{Integer.valueOf(barcodeValue2.getValue()[0]), barcodeValue2.getConfidence(barcodeValue2.getValue()[0])});
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
