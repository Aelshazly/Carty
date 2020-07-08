package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    private static final class CenterComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            if (center2.getCount() != center1.getCount()) {
                return center2.getCount() - center1.getCount();
            }
            float dA = Math.abs(center2.getEstimatedModuleSize() - this.average);
            float dB = Math.abs(center1.getEstimatedModuleSize() - this.average);
            if (dA < dB) {
                return 1;
            }
            return dA == dB ? 0 : -1;
        }
    }

    private static final class FurthestFromAverageComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            float dA = Math.abs(center2.getEstimatedModuleSize() - this.average);
            float dB = Math.abs(center1.getEstimatedModuleSize() - this.average);
            if (dA < dB) {
                return -1;
            }
            return dA == dB ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix image2) {
        this(image2, null);
    }

    public FinderPatternFinder(BitMatrix image2, ResultPointCallback resultPointCallback2) {
        this.image = image2;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback2;
    }

    /* access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* access modifiers changed from: 0000 */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> hints) throws NotFoundException {
        int currentState;
        Map<DecodeHintType, ?> map = hints;
        boolean tryHarder = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean pureBarcode = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int maxI = this.image.getHeight();
        int maxJ = this.image.getWidth();
        int i = (maxI * 3) / 228;
        int currentState2 = i;
        char c = 3;
        if (i < 3 || tryHarder) {
            currentState2 = 3;
        }
        boolean confirmed = false;
        int[] stateCount = new int[5];
        int i2 = currentState - 1;
        int i3 = 0;
        while (i2 < maxI && !confirmed) {
            stateCount[0] = 0;
            stateCount[1] = 0;
            stateCount[2] = 0;
            stateCount[c] = 0;
            int i4 = 4;
            stateCount[4] = 0;
            boolean done = confirmed;
            int j = 0;
            int rowSkip = i3;
            int iSkip = currentState;
            int currentState3 = 0;
            while (j < maxJ) {
                if (this.image.get(j, i2)) {
                    if ((currentState3 & 1) == 1) {
                        currentState3++;
                    }
                    stateCount[currentState3] = stateCount[currentState3] + 1;
                } else if ((currentState3 & 1) != 0) {
                    stateCount[currentState3] = stateCount[currentState3] + 1;
                } else if (currentState3 != i4) {
                    currentState3++;
                    stateCount[currentState3] = stateCount[currentState3] + 1;
                } else if (!foundPatternCross(stateCount)) {
                    stateCount[0] = stateCount[2];
                    stateCount[1] = stateCount[3];
                    stateCount[2] = stateCount[4];
                    stateCount[3] = 1;
                    stateCount[4] = 0;
                    currentState3 = 3;
                } else if (handlePossibleCenter(stateCount, i2, j, pureBarcode)) {
                    if (this.hasSkipped != 0) {
                        done = haveMultiplyConfirmedCenters();
                    } else {
                        int findRowSkip = findRowSkip();
                        rowSkip = findRowSkip;
                        if (findRowSkip > stateCount[2]) {
                            i2 += (rowSkip - stateCount[2]) - 2;
                            j = maxJ - 1;
                        }
                    }
                    currentState3 = 0;
                    stateCount[0] = 0;
                    stateCount[1] = 0;
                    stateCount[2] = 0;
                    stateCount[3] = 0;
                    stateCount[4] = 0;
                    iSkip = 2;
                } else {
                    stateCount[0] = stateCount[2];
                    stateCount[1] = stateCount[3];
                    stateCount[2] = stateCount[4];
                    stateCount[3] = 1;
                    stateCount[4] = 0;
                    currentState3 = 3;
                }
                j++;
                i4 = 4;
            }
            if (!foundPatternCross(stateCount) || !handlePossibleCenter(stateCount, i2, maxJ, pureBarcode)) {
                currentState = iSkip;
                confirmed = done;
            } else {
                int iSkip2 = stateCount[0];
                if (this.hasSkipped != 0) {
                    currentState = iSkip2;
                    confirmed = haveMultiplyConfirmedCenters();
                } else {
                    currentState = iSkip2;
                    confirmed = done;
                }
            }
            i2 += currentState;
            i3 = rowSkip;
            c = 3;
        }
        FinderPattern[] selectBestPatterns = selectBestPatterns();
        FinderPattern[] patternInfo = selectBestPatterns;
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(patternInfo);
    }

    private static float centerFromEnd(int[] stateCount, int end) {
        return ((float) ((end - stateCount[4]) - stateCount[3])) - (((float) stateCount[2]) / 2.0f);
    }

    protected static boolean foundPatternCross(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = ((float) i) / 7.0f;
        float f2 = f / 2.0f;
        if (Math.abs(f - ((float) iArr[0])) >= f2 || Math.abs(f - ((float) iArr[1])) >= f2 || Math.abs((f * 3.0f) - ((float) iArr[2])) >= 3.0f * f2 || Math.abs(f - ((float) iArr[3])) >= f2 || Math.abs(f - ((float) iArr[4])) >= f2) {
            return false;
        }
        return true;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean crossCheckDiagonal(int startI, int centerJ, int maxCount, int originalStateCountTotal) {
        int i = startI;
        int i2 = centerJ;
        int i3 = maxCount;
        int[] stateCount = getCrossCheckStateCount();
        int i4 = 0;
        while (i >= i4 && i2 >= i4 && this.image.get(i2 - i4, i - i4)) {
            stateCount[2] = stateCount[2] + 1;
            i4++;
        }
        if (i < i4 || i2 < i4) {
            return false;
        }
        while (i >= i4 && i2 >= i4 && !this.image.get(i2 - i4, i - i4) && stateCount[1] <= i3) {
            stateCount[1] = stateCount[1] + 1;
            i4++;
        }
        if (i < i4 || i2 < i4 || stateCount[1] > i3) {
            return false;
        }
        while (i >= i4 && i2 >= i4 && this.image.get(i2 - i4, i - i4) && stateCount[0] <= i3) {
            stateCount[0] = stateCount[0] + 1;
            i4++;
        }
        if (stateCount[0] > i3) {
            return false;
        }
        int maxI = this.image.getHeight();
        int maxJ = this.image.getWidth();
        int i5 = 1;
        while (i + i5 < maxI && i2 + i5 < maxJ && this.image.get(i2 + i5, i + i5)) {
            stateCount[2] = stateCount[2] + 1;
            i5++;
        }
        if (i + i5 >= maxI || i2 + i5 >= maxJ) {
            return false;
        }
        while (i + i5 < maxI && i2 + i5 < maxJ && !this.image.get(i2 + i5, i + i5) && stateCount[3] < i3) {
            stateCount[3] = stateCount[3] + 1;
            i5++;
        }
        if (i + i5 >= maxI || i2 + i5 >= maxJ || stateCount[3] >= i3) {
            return false;
        }
        while (i + i5 < maxI && i2 + i5 < maxJ && this.image.get(i2 + i5, i + i5) && stateCount[4] < i3) {
            stateCount[4] = stateCount[4] + 1;
            i5++;
        }
        if (stateCount[4] < i3 && Math.abs(((((stateCount[0] + stateCount[1]) + stateCount[2]) + stateCount[3]) + stateCount[4]) - originalStateCountTotal) < originalStateCountTotal * 2 && foundPatternCross(stateCount)) {
            return true;
        }
        return false;
    }

    private float crossCheckVertical(int startI, int centerJ, int maxCount, int originalStateCountTotal) {
        BitMatrix bitMatrix = this.image;
        BitMatrix image2 = bitMatrix;
        int maxI = bitMatrix.getHeight();
        int[] stateCount = getCrossCheckStateCount();
        int i = startI;
        while (i >= 0 && image2.get(centerJ, i)) {
            stateCount[2] = stateCount[2] + 1;
            i--;
        }
        if (i < 0) {
            return Float.NaN;
        }
        while (i >= 0 && !image2.get(centerJ, i) && stateCount[1] <= maxCount) {
            stateCount[1] = stateCount[1] + 1;
            i--;
        }
        if (i < 0 || stateCount[1] > maxCount) {
            return Float.NaN;
        }
        while (i >= 0 && image2.get(centerJ, i) && stateCount[0] <= maxCount) {
            stateCount[0] = stateCount[0] + 1;
            i--;
        }
        if (stateCount[0] > maxCount) {
            return Float.NaN;
        }
        int i2 = startI + 1;
        while (i2 < maxI && image2.get(centerJ, i2)) {
            stateCount[2] = stateCount[2] + 1;
            i2++;
        }
        if (i2 == maxI) {
            return Float.NaN;
        }
        while (i2 < maxI && !image2.get(centerJ, i2) && stateCount[3] < maxCount) {
            stateCount[3] = stateCount[3] + 1;
            i2++;
        }
        if (i2 == maxI || stateCount[3] >= maxCount) {
            return Float.NaN;
        }
        while (i2 < maxI && image2.get(centerJ, i2) && stateCount[4] < maxCount) {
            stateCount[4] = stateCount[4] + 1;
            i2++;
        }
        if (stateCount[4] < maxCount && Math.abs(((((stateCount[0] + stateCount[1]) + stateCount[2]) + stateCount[3]) + stateCount[4]) - originalStateCountTotal) * 5 < originalStateCountTotal * 2 && foundPatternCross(stateCount)) {
            return centerFromEnd(stateCount, i2);
        }
        return Float.NaN;
    }

    private float crossCheckHorizontal(int startJ, int centerI, int maxCount, int originalStateCountTotal) {
        BitMatrix bitMatrix = this.image;
        BitMatrix image2 = bitMatrix;
        int maxJ = bitMatrix.getWidth();
        int[] stateCount = getCrossCheckStateCount();
        int j = startJ;
        while (j >= 0 && image2.get(j, centerI)) {
            stateCount[2] = stateCount[2] + 1;
            j--;
        }
        if (j < 0) {
            return Float.NaN;
        }
        while (j >= 0 && !image2.get(j, centerI) && stateCount[1] <= maxCount) {
            stateCount[1] = stateCount[1] + 1;
            j--;
        }
        if (j < 0 || stateCount[1] > maxCount) {
            return Float.NaN;
        }
        while (j >= 0 && image2.get(j, centerI) && stateCount[0] <= maxCount) {
            stateCount[0] = stateCount[0] + 1;
            j--;
        }
        if (stateCount[0] > maxCount) {
            return Float.NaN;
        }
        int j2 = startJ + 1;
        while (j2 < maxJ && image2.get(j2, centerI)) {
            stateCount[2] = stateCount[2] + 1;
            j2++;
        }
        if (j2 == maxJ) {
            return Float.NaN;
        }
        while (j2 < maxJ && !image2.get(j2, centerI) && stateCount[3] < maxCount) {
            stateCount[3] = stateCount[3] + 1;
            j2++;
        }
        if (j2 == maxJ || stateCount[3] >= maxCount) {
            return Float.NaN;
        }
        while (j2 < maxJ && image2.get(j2, centerI) && stateCount[4] < maxCount) {
            stateCount[4] = stateCount[4] + 1;
            j2++;
        }
        if (stateCount[4] < maxCount && Math.abs(((((stateCount[0] + stateCount[1]) + stateCount[2]) + stateCount[3]) + stateCount[4]) - originalStateCountTotal) * 5 < originalStateCountTotal && foundPatternCross(stateCount)) {
            return centerFromEnd(stateCount, j2);
        }
        return Float.NaN;
    }

    /* access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] stateCount, int i, int j, boolean pureBarcode) {
        int stateCountTotal = stateCount[0] + stateCount[1] + stateCount[2] + stateCount[3] + stateCount[4];
        float centerJ = centerFromEnd(stateCount, j);
        float crossCheckVertical = crossCheckVertical(i, (int) centerJ, stateCount[2], stateCountTotal);
        float centerI = crossCheckVertical;
        if (!Float.isNaN(crossCheckVertical)) {
            float crossCheckHorizontal = crossCheckHorizontal((int) centerJ, (int) centerI, stateCount[2], stateCountTotal);
            float centerJ2 = crossCheckHorizontal;
            if (!Float.isNaN(crossCheckHorizontal) && (!pureBarcode || crossCheckDiagonal((int) centerI, (int) centerJ2, stateCount[2], stateCountTotal))) {
                float estimatedModuleSize = ((float) stateCountTotal) / 7.0f;
                boolean found = false;
                int index = 0;
                while (true) {
                    if (index >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern finderPattern = (FinderPattern) this.possibleCenters.get(index);
                    FinderPattern center = finderPattern;
                    if (finderPattern.aboutEquals(estimatedModuleSize, centerI, centerJ2)) {
                        this.possibleCenters.set(index, center.combineEstimate(centerI, centerJ2, estimatedModuleSize));
                        found = true;
                        break;
                    }
                    index++;
                }
                if (!found) {
                    FinderPattern point = new FinderPattern(centerJ2, centerI, estimatedModuleSize);
                    this.possibleCenters.add(point);
                    ResultPointCallback resultPointCallback2 = this.resultPointCallback;
                    if (resultPointCallback2 != null) {
                        resultPointCallback2.foundPossibleResultPoint(point);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            FinderPattern center = finderPattern2;
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern == null) {
                    finderPattern = center;
                } else {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - center.getX()) - Math.abs(finderPattern.getY() - center.getY()))) / 2;
                }
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int confirmedCount = 0;
        float totalModuleSize = 0.0f;
        int max = this.possibleCenters.size();
        for (FinderPattern finderPattern : this.possibleCenters) {
            FinderPattern pattern = finderPattern;
            if (finderPattern.getCount() >= 2) {
                confirmedCount++;
                totalModuleSize += pattern.getEstimatedModuleSize();
            }
        }
        boolean z = false;
        if (confirmedCount < 3) {
            return false;
        }
        float average = totalModuleSize / ((float) max);
        float totalDeviation = 0.0f;
        for (FinderPattern pattern2 : this.possibleCenters) {
            totalDeviation += Math.abs(pattern2.getEstimatedModuleSize() - average);
        }
        if (totalDeviation <= 0.05f * totalModuleSize) {
            z = true;
        }
        return z;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        int startSize = size;
        if (size >= 3) {
            if (startSize > 3) {
                float totalModuleSize = 0.0f;
                float square = 0.0f;
                for (FinderPattern estimatedModuleSize : this.possibleCenters) {
                    float size2 = estimatedModuleSize.getEstimatedModuleSize();
                    totalModuleSize += size2;
                    square += size2 * size2;
                }
                float average = totalModuleSize / ((float) startSize);
                float stdDev = (float) Math.sqrt((double) ((square / ((float) startSize)) - (average * average)));
                Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(average));
                float limit = Math.max(0.2f * average, stdDev);
                int i = 0;
                while (i < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                    if (Math.abs(((FinderPattern) this.possibleCenters.get(i)).getEstimatedModuleSize() - average) > limit) {
                        this.possibleCenters.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            if (this.possibleCenters.size() > 3) {
                float totalModuleSize2 = 0.0f;
                for (FinderPattern possibleCenter : this.possibleCenters) {
                    totalModuleSize2 += possibleCenter.getEstimatedModuleSize();
                }
                Collections.sort(this.possibleCenters, new CenterComparator(totalModuleSize2 / ((float) this.possibleCenters.size())));
                List<FinderPattern> list = this.possibleCenters;
                list.subList(3, list.size()).clear();
            }
            return new FinderPattern[]{(FinderPattern) this.possibleCenters.get(0), (FinderPattern) this.possibleCenters.get(1), (FinderPattern) this.possibleCenters.get(2)};
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
