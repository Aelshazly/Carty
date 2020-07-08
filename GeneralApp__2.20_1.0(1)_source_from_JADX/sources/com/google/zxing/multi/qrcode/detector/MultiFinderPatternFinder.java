package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    private static final class ModuleSizeComparator implements Serializable, Comparator<FinderPattern> {
        private ModuleSizeComparator() {
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            float estimatedModuleSize = center2.getEstimatedModuleSize() - center1.getEstimatedModuleSize();
            float value = estimatedModuleSize;
            if (((double) estimatedModuleSize) < 0.0d) {
                return -1;
            }
            return ((double) value) > 0.0d ? 1 : 0;
        }
    }

    MultiFinderPatternFinder(BitMatrix image) {
        super(image);
    }

    MultiFinderPatternFinder(BitMatrix image, ResultPointCallback resultPointCallback) {
        super(image, resultPointCallback);
    }

    private FinderPattern[][] selectMutipleBestPatterns() throws NotFoundException {
        FinderPattern p1;
        List list;
        List possibleCenters = getPossibleCenters();
        List list2 = possibleCenters;
        int size = possibleCenters.size();
        int size2 = size;
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        } else if (size2 == 3) {
            return new FinderPattern[][]{new FinderPattern[]{(FinderPattern) list2.get(0), (FinderPattern) list2.get(1), (FinderPattern) list2.get(2)}};
        } else {
            Collections.sort(list2, new ModuleSizeComparator());
            List<FinderPattern[]> results = new ArrayList<>();
            int i1 = 0;
            while (i1 < size2 - 2) {
                FinderPattern finderPattern = (FinderPattern) list2.get(i1);
                FinderPattern p12 = finderPattern;
                if (finderPattern != null) {
                    int i2 = i1 + 1;
                    while (i2 < size2 - 1) {
                        FinderPattern finderPattern2 = (FinderPattern) list2.get(i2);
                        FinderPattern p2 = finderPattern2;
                        if (finderPattern2 != null) {
                            float vModSize12 = (p12.getEstimatedModuleSize() - p2.getEstimatedModuleSize()) / Math.min(p12.getEstimatedModuleSize(), p2.getEstimatedModuleSize());
                            float abs = Math.abs(p12.getEstimatedModuleSize() - p2.getEstimatedModuleSize());
                            float f = DIFF_MODSIZE_CUTOFF_PERCENT;
                            if (abs > DIFF_MODSIZE_CUTOFF && vModSize12 >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                                break;
                            }
                            int i3 = i2 + 1;
                            while (i3 < size2) {
                                FinderPattern finderPattern3 = (FinderPattern) list2.get(i3);
                                FinderPattern p3 = finderPattern3;
                                if (finderPattern3 != null) {
                                    float vModSize23 = (p2.getEstimatedModuleSize() - p3.getEstimatedModuleSize()) / Math.min(p2.getEstimatedModuleSize(), p3.getEstimatedModuleSize());
                                    if (Math.abs(p2.getEstimatedModuleSize() - p3.getEstimatedModuleSize()) > DIFF_MODSIZE_CUTOFF && vModSize23 >= f) {
                                        break;
                                    }
                                    FinderPattern[] finderPatternArr = {p12, p2, p3};
                                    FinderPattern[] test = finderPatternArr;
                                    ResultPoint.orderBestPatterns(finderPatternArr);
                                    FinderPatternInfo finderPatternInfo = new FinderPatternInfo(test);
                                    FinderPatternInfo info = finderPatternInfo;
                                    float dA = ResultPoint.distance(finderPatternInfo.getTopLeft(), info.getBottomLeft());
                                    float dC = ResultPoint.distance(info.getTopRight(), info.getBottomLeft());
                                    list = list2;
                                    float dB = ResultPoint.distance(info.getTopLeft(), info.getTopRight());
                                    float estimatedModuleSize = (dA + dB) / (p12.getEstimatedModuleSize() * 2.0f);
                                    float estimatedModuleCount = estimatedModuleSize;
                                    if (estimatedModuleSize > 180.0f || estimatedModuleCount < MIN_MODULE_COUNT_PER_EDGE) {
                                        p1 = p12;
                                        float f2 = dB;
                                    } else if (Math.abs((dA - dB) / Math.min(dA, dB)) < 0.1f) {
                                        p1 = p12;
                                        float f3 = dB;
                                        float dCpy = (float) Math.sqrt((double) ((dA * dA) + (dB * dB)));
                                        if (Math.abs((dC - dCpy) / Math.min(dC, dCpy)) < 0.1f) {
                                            results.add(test);
                                        }
                                    } else {
                                        p1 = p12;
                                        float f4 = dB;
                                    }
                                } else {
                                    p1 = p12;
                                    list = list2;
                                }
                                i3++;
                                list2 = list;
                                p12 = p1;
                                f = DIFF_MODSIZE_CUTOFF_PERCENT;
                            }
                        }
                        i2++;
                        list2 = list2;
                        p12 = p12;
                    }
                }
                i1++;
                list2 = list2;
                FinderPattern finderPattern4 = p12;
            }
            List list3 = list2;
            if (!results.isEmpty()) {
                return (FinderPattern[][]) results.toArray(new FinderPattern[results.size()][]);
            }
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> hints) throws NotFoundException {
        Map<DecodeHintType, ?> map = hints;
        int i = 0;
        boolean tryHarder = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean pureBarcode = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        BitMatrix image = getImage();
        BitMatrix image2 = image;
        int maxI = image.getHeight();
        int maxJ = image2.getWidth();
        int i2 = (int) ((((float) maxI) / 228.0f) * 3.0f);
        int iSkip = i2;
        char c = 3;
        if (i2 < 3 || tryHarder) {
            iSkip = 3;
        }
        int[] stateCount = new int[5];
        int i3 = iSkip - 1;
        while (i3 < maxI) {
            stateCount[0] = 0;
            stateCount[1] = 0;
            stateCount[2] = 0;
            stateCount[c] = 0;
            stateCount[4] = 0;
            int currentState = 0;
            for (int j = 0; j < maxJ; j++) {
                if (image2.get(j, i3)) {
                    if ((currentState & 1) == 1) {
                        currentState++;
                    }
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if ((currentState & 1) != 0) {
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (currentState != 4) {
                    currentState++;
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (!foundPatternCross(stateCount) || !handlePossibleCenter(stateCount, i3, j, pureBarcode)) {
                    stateCount[0] = stateCount[2];
                    stateCount[1] = stateCount[3];
                    stateCount[2] = stateCount[4];
                    stateCount[3] = 1;
                    stateCount[4] = 0;
                    currentState = 3;
                } else {
                    currentState = 0;
                    stateCount[0] = 0;
                    stateCount[1] = 0;
                    stateCount[2] = 0;
                    stateCount[3] = 0;
                    stateCount[4] = 0;
                }
            }
            if (foundPatternCross(stateCount) != 0) {
                handlePossibleCenter(stateCount, i3, maxJ, pureBarcode);
            }
            i3 += iSkip;
            c = 3;
        }
        FinderPattern[][] patternInfo = selectMutipleBestPatterns();
        List<FinderPatternInfo> result = new ArrayList<>();
        int length = patternInfo.length;
        FinderPattern[] finderPatternArr = null;
        while (i < length) {
            FinderPattern[] finderPatternArr2 = patternInfo[i];
            FinderPattern[] finderPatternArr3 = finderPatternArr;
            FinderPattern[] pattern = finderPatternArr2;
            ResultPoint.orderBestPatterns(finderPatternArr2);
            result.add(new FinderPatternInfo(pattern));
            i++;
            finderPatternArr = pattern;
        }
        if (result.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        return (FinderPatternInfo[]) result.toArray(new FinderPatternInfo[result.size()]);
    }
}
