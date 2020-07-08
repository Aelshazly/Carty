package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    private static final class ResultPointsAndTransitions {
        private final ResultPoint from;

        /* renamed from: to */
        private final ResultPoint f96to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint from2, ResultPoint to, int transitions2) {
            this.from = from2;
            this.f96to = to;
            this.transitions = transitions2;
        }

        /* access modifiers changed from: 0000 */
        public ResultPoint getFrom() {
            return this.from;
        }

        /* access modifiers changed from: 0000 */
        public ResultPoint getTo() {
            return this.f96to;
        }

        /* access modifiers changed from: 0000 */
        public int getTransitions() {
            return this.transitions;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.from);
            sb.append("/");
            sb.append(this.f96to);
            sb.append('/');
            sb.append(this.transitions);
            return sb.toString();
        }
    }

    private static final class ResultPointsAndTransitionsComparator implements Serializable, Comparator<ResultPointsAndTransitions> {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(ResultPointsAndTransitions o1, ResultPointsAndTransitions o2) {
            return o1.getTransitions() - o2.getTransitions();
        }
    }

    public Detector(BitMatrix image2) throws NotFoundException {
        this.image = image2;
        this.rectangleDetector = new WhiteRectangleDetector(image2);
    }

    public DetectorResult detect() throws NotFoundException {
        ResultPoint topRight;
        char c;
        ResultPoint bottomRight;
        BitMatrix bits;
        ResultPoint topLeft;
        ResultPoint correctedTopRight;
        ResultPoint topRight2;
        ResultPoint pointD;
        ResultPoint correctedTopRight2;
        ResultPoint[] detect = this.rectangleDetector.detect();
        ResultPoint[] cornerPoints = detect;
        ResultPoint pointA = detect[0];
        ResultPoint pointB = cornerPoints[1];
        ResultPoint pointC = cornerPoints[2];
        ResultPoint pointD2 = cornerPoints[3];
        ArrayList arrayList = new ArrayList(4);
        ArrayList arrayList2 = arrayList;
        arrayList.add(transitionsBetween(pointA, pointB));
        arrayList2.add(transitionsBetween(pointA, pointC));
        arrayList2.add(transitionsBetween(pointB, pointD2));
        arrayList2.add(transitionsBetween(pointC, pointD2));
        Collections.sort(arrayList2, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions lSideOne = (ResultPointsAndTransitions) arrayList2.get(0);
        ResultPointsAndTransitions lSideTwo = (ResultPointsAndTransitions) arrayList2.get(1);
        Map<ResultPoint, Integer> hashMap = new HashMap<>();
        Map<ResultPoint, Integer> map = hashMap;
        increment(hashMap, lSideOne.getFrom());
        increment(map, lSideOne.getTo());
        increment(map, lSideTwo.getFrom());
        increment(map, lSideTwo.getTo());
        Iterator it = map.entrySet().iterator();
        ResultPoint bottomLeft = null;
        ResultPoint maybeBottomRight = null;
        ResultPoint maybeTopLeft = null;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            Entry entry2 = entry;
            ResultPoint point = (ResultPoint) entry.getKey();
            if (((Integer) entry2.getValue()).intValue() == 2) {
                bottomLeft = point;
            } else if (maybeTopLeft == null) {
                maybeTopLeft = point;
            } else {
                maybeBottomRight = point;
            }
        }
        if (maybeTopLeft == null || bottomLeft == null || maybeBottomRight == null) {
            Map<ResultPoint, Integer> pointCount = map;
            ArrayList arrayList3 = arrayList2;
            ResultPoint resultPoint = pointD2;
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint[] resultPointArr = {maybeTopLeft, bottomLeft, maybeBottomRight};
        Iterator it2 = it;
        ResultPoint[] corners = resultPointArr;
        ResultPoint.orderBestPatterns(resultPointArr);
        ResultPoint bottomRight2 = corners[0];
        ResultPoint bottomLeft2 = corners[1];
        ResultPoint topLeft2 = corners[2];
        if (!map.containsKey(pointA)) {
            topRight = pointA;
        } else if (!map.containsKey(pointB)) {
            topRight = pointB;
        } else if (!map.containsKey(pointC)) {
            topRight = pointC;
        } else {
            topRight = pointD2;
        }
        int dimensionTop = transitionsBetween(topLeft2, topRight).getTransitions();
        int dimensionRight = transitionsBetween(bottomRight2, topRight).getTransitions();
        if ((dimensionTop & 1) == 1) {
            dimensionTop++;
        }
        int dimensionTop2 = dimensionTop + 2;
        if ((dimensionRight & 1) == 1) {
            dimensionRight++;
        }
        int dimensionRight2 = dimensionRight + 2;
        if (dimensionTop2 * 4 >= dimensionRight2 * 7) {
            topRight2 = topRight;
            HashMap hashMap2 = map;
            ArrayList arrayList4 = arrayList2;
            ResultPoint resultPoint2 = pointD2;
            pointD = topLeft2;
        } else if (dimensionRight2 * 4 >= dimensionTop2 * 7) {
            topRight2 = topRight;
            HashMap hashMap3 = map;
            ArrayList arrayList5 = arrayList2;
            ResultPoint resultPoint3 = pointD2;
            pointD = topLeft2;
        } else {
            ResultPoint topRight3 = topRight;
            ResultPoint topLeft3 = topLeft2;
            HashMap hashMap4 = map;
            ArrayList arrayList6 = arrayList2;
            ResultPoint bottomRight3 = bottomRight2;
            ResultPoint resultPoint4 = pointD2;
            ResultPoint correctTopRight = correctTopRight(bottomLeft2, bottomRight2, topLeft3, topRight3, Math.min(dimensionRight2, dimensionTop2));
            ResultPoint correctedTopRight3 = correctTopRight;
            if (correctTopRight == null) {
                correctedTopRight = topRight3;
            } else {
                correctedTopRight = correctedTopRight3;
            }
            ResultPoint topLeft4 = topLeft3;
            ResultPoint bottomRight4 = bottomRight3;
            int dimensionCorrected = Math.max(transitionsBetween(topLeft4, correctedTopRight).getTransitions(), transitionsBetween(bottomRight4, correctedTopRight).getTransitions()) + 1;
            if ((dimensionCorrected & 1) == 1) {
                dimensionCorrected++;
            }
            bottomRight = bottomRight4;
            int i = dimensionTop2;
            int i2 = dimensionRight2;
            c = 3;
            bits = sampleGrid(this.image, topLeft4, bottomLeft2, bottomRight4, correctedTopRight, dimensionCorrected, dimensionCorrected);
            topLeft = topLeft4;
            ResultPoint[] resultPointArr2 = new ResultPoint[4];
            resultPointArr2[0] = topLeft;
            resultPointArr2[1] = bottomLeft2;
            resultPointArr2[2] = bottomRight;
            resultPointArr2[c] = correctedTopRight;
            return new DetectorResult(bits, resultPointArr2);
        }
        bottomRight = bottomRight2;
        ResultPoint topLeft5 = pointD;
        int i3 = dimensionTop2;
        c = 3;
        ResultPoint correctTopRightRectangular = correctTopRightRectangular(bottomLeft2, bottomRight2, pointD, topRight2, dimensionTop2, dimensionRight2);
        ResultPoint correctedTopRight4 = correctTopRightRectangular;
        if (correctTopRightRectangular == null) {
            correctedTopRight2 = topRight2;
        } else {
            correctedTopRight2 = correctedTopRight4;
        }
        topLeft = topLeft5;
        int dimensionTop3 = transitionsBetween(topLeft, correctedTopRight).getTransitions();
        int dimensionRight3 = transitionsBetween(bottomRight, correctedTopRight).getTransitions();
        if ((dimensionTop3 & 1) == 1) {
            dimensionTop3++;
        }
        if ((dimensionRight3 & 1) == 1) {
            dimensionRight3++;
        }
        bits = sampleGrid(this.image, topLeft, bottomLeft2, bottomRight, correctedTopRight, dimensionTop3, dimensionRight3);
        ResultPoint[] resultPointArr22 = new ResultPoint[4];
        resultPointArr22[0] = topLeft;
        resultPointArr22[1] = bottomLeft2;
        resultPointArr22[2] = bottomRight;
        resultPointArr22[c] = correctedTopRight;
        return new DetectorResult(bits, resultPointArr22);
    }

    private ResultPoint correctTopRightRectangular(ResultPoint bottomLeft, ResultPoint bottomRight, ResultPoint topLeft, ResultPoint topRight, int dimensionTop, int dimensionRight) {
        float corr = ((float) distance(bottomLeft, bottomRight)) / ((float) dimensionTop);
        int norm = distance(topLeft, topRight);
        ResultPoint c1 = new ResultPoint(topRight.getX() + (corr * ((topRight.getX() - topLeft.getX()) / ((float) norm))), topRight.getY() + (corr * ((topRight.getY() - topLeft.getY()) / ((float) norm))));
        float corr2 = ((float) distance(bottomLeft, topLeft)) / ((float) dimensionRight);
        int norm2 = distance(bottomRight, topRight);
        ResultPoint c2 = new ResultPoint(topRight.getX() + (corr2 * ((topRight.getX() - bottomRight.getX()) / ((float) norm2))), topRight.getY() + (corr2 * ((topRight.getY() - bottomRight.getY()) / ((float) norm2))));
        if (!isValid(c1)) {
            if (isValid(c2)) {
                return c2;
            }
            return null;
        } else if (isValid(c2) && Math.abs(dimensionTop - transitionsBetween(topLeft, c1).getTransitions()) + Math.abs(dimensionRight - transitionsBetween(bottomRight, c1).getTransitions()) > Math.abs(dimensionTop - transitionsBetween(topLeft, c2).getTransitions()) + Math.abs(dimensionRight - transitionsBetween(bottomRight, c2).getTransitions())) {
            return c2;
        } else {
            return c1;
        }
    }

    private ResultPoint correctTopRight(ResultPoint bottomLeft, ResultPoint bottomRight, ResultPoint topLeft, ResultPoint topRight, int dimension) {
        float corr = ((float) distance(bottomLeft, bottomRight)) / ((float) dimension);
        int norm = distance(topLeft, topRight);
        ResultPoint c1 = new ResultPoint(topRight.getX() + (corr * ((topRight.getX() - topLeft.getX()) / ((float) norm))), topRight.getY() + (corr * ((topRight.getY() - topLeft.getY()) / ((float) norm))));
        float corr2 = ((float) distance(bottomLeft, topLeft)) / ((float) dimension);
        int norm2 = distance(bottomRight, topRight);
        ResultPoint c2 = new ResultPoint(topRight.getX() + (corr2 * ((topRight.getX() - bottomRight.getX()) / ((float) norm2))), topRight.getY() + (corr2 * ((topRight.getY() - bottomRight.getY()) / ((float) norm2))));
        if (isValid(c1)) {
            return (isValid(c2) && Math.abs(transitionsBetween(topLeft, c1).getTransitions() - transitionsBetween(bottomRight, c1).getTransitions()) > Math.abs(transitionsBetween(topLeft, c2).getTransitions() - transitionsBetween(bottomRight, c2).getTransitions())) ? c2 : c1;
        }
        if (isValid(c2)) {
            return c2;
        }
        return null;
    }

    private boolean isValid(ResultPoint p) {
        return p.getX() >= 0.0f && p.getX() < ((float) this.image.getWidth()) && p.getY() > 0.0f && p.getY() < ((float) this.image.getHeight());
    }

    private static int distance(ResultPoint a, ResultPoint b) {
        return MathUtils.round(ResultPoint.distance(a, b));
    }

    private static void increment(Map<ResultPoint, Integer> table, ResultPoint key) {
        Integer value = (Integer) table.get(key);
        int i = 1;
        if (value != null) {
            i = 1 + value.intValue();
        }
        table.put(key, Integer.valueOf(i));
    }

    private static BitMatrix sampleGrid(BitMatrix image2, ResultPoint topLeft, ResultPoint bottomLeft, ResultPoint bottomRight, ResultPoint topRight, int dimensionX, int dimensionY) throws NotFoundException {
        int i = dimensionX;
        int i2 = dimensionY;
        return GridSampler.getInstance().sampleGrid(image2, dimensionX, dimensionY, 0.5f, 0.5f, ((float) i) - 0.5f, 0.5f, ((float) i) - 0.5f, ((float) i2) - 0.5f, 0.5f, ((float) i2) - 0.5f, topLeft.getX(), topLeft.getY(), topRight.getX(), topRight.getY(), bottomRight.getX(), bottomRight.getY(), bottomLeft.getX(), bottomLeft.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint from, ResultPoint to) {
        Detector detector = this;
        int fromX = (int) from.getX();
        int fromY = (int) from.getY();
        int toX = (int) to.getX();
        int y = (int) to.getY();
        boolean isBlack = false;
        int toY = y;
        int xstep = 1;
        boolean z = Math.abs(y - fromY) > Math.abs(toX - fromX);
        boolean steep = z;
        if (z) {
            int temp = fromX;
            fromX = fromY;
            fromY = temp;
            int temp2 = toX;
            toX = toY;
            toY = temp2;
        }
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);
        int error = (-dx) / 2;
        int ystep = fromY < toY ? 1 : -1;
        if (fromX >= toX) {
            xstep = -1;
        }
        int transitions = 0;
        boolean inBlack = detector.image.get(steep ? fromY : fromX, steep ? fromX : fromY);
        int x = fromX;
        int y2 = fromY;
        while (true) {
            if (x == toX) {
                int i = fromY;
                break;
            }
            int fromX2 = fromX;
            int fromY2 = fromY;
            boolean z2 = detector.image.get(steep ? y2 : x, steep ? x : y2);
            boolean z3 = isBlack;
            isBlack = z2;
            if (z2 != inBlack) {
                transitions++;
                inBlack = isBlack;
            }
            int i2 = error + dy;
            error = i2;
            if (i2 > 0) {
                if (y2 == toY) {
                    break;
                }
                y2 += ystep;
                error -= dx;
            }
            x += xstep;
            detector = this;
            fromX = fromX2;
            fromY = fromY2;
        }
        return new ResultPointsAndTransitions(from, to, transitions);
    }
}
