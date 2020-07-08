package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

final class BoundingBox {
    private ResultPoint bottomLeft;
    private ResultPoint bottomRight;
    private BitMatrix image;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private ResultPoint topLeft;
    private ResultPoint topRight;

    BoundingBox(BitMatrix image2, ResultPoint topLeft2, ResultPoint bottomLeft2, ResultPoint topRight2, ResultPoint bottomRight2) throws NotFoundException {
        if (!(topLeft2 == null && topRight2 == null) && (!(bottomLeft2 == null && bottomRight2 == null) && ((topLeft2 == null || bottomLeft2 != null) && (topRight2 == null || bottomRight2 != null)))) {
            init(image2, topLeft2, bottomLeft2, topRight2, bottomRight2);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    BoundingBox(BoundingBox boundingBox) {
        init(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox.topRight, boundingBox.bottomRight);
    }

    private void init(BitMatrix image2, ResultPoint topLeft2, ResultPoint bottomLeft2, ResultPoint topRight2, ResultPoint bottomRight2) {
        this.image = image2;
        this.topLeft = topLeft2;
        this.bottomLeft = bottomLeft2;
        this.topRight = topRight2;
        this.bottomRight = bottomRight2;
        calculateMinMaxValues();
    }

    static BoundingBox merge(BoundingBox leftBox, BoundingBox rightBox) throws NotFoundException {
        if (leftBox == null) {
            return rightBox;
        }
        if (rightBox == null) {
            return leftBox;
        }
        BoundingBox boundingBox = new BoundingBox(leftBox.image, leftBox.topLeft, leftBox.bottomLeft, rightBox.topRight, rightBox.bottomRight);
        return boundingBox;
    }

    /* access modifiers changed from: 0000 */
    public BoundingBox addMissingRows(int missingStartRows, int missingEndRows, boolean isLeft) throws NotFoundException {
        ResultPoint newTopLeft = this.topLeft;
        ResultPoint newBottomLeft = this.bottomLeft;
        ResultPoint newTopRight = this.topRight;
        ResultPoint newBottomRight = this.bottomRight;
        if (missingStartRows > 0) {
            ResultPoint resultPoint = isLeft ? this.topLeft : this.topRight;
            ResultPoint top = resultPoint;
            int y = ((int) resultPoint.getY()) - missingStartRows;
            int newMinY = y;
            if (y < 0) {
                newMinY = 0;
            }
            ResultPoint newTop = new ResultPoint(top.getX(), (float) newMinY);
            if (isLeft) {
                newTopLeft = newTop;
            } else {
                newTopRight = newTop;
            }
        }
        if (missingEndRows > 0) {
            ResultPoint resultPoint2 = isLeft ? this.bottomLeft : this.bottomRight;
            ResultPoint bottom = resultPoint2;
            int y2 = ((int) resultPoint2.getY()) + missingEndRows;
            int newMaxY = y2;
            if (y2 >= this.image.getHeight()) {
                newMaxY = this.image.getHeight() - 1;
            }
            ResultPoint newBottom = new ResultPoint(bottom.getX(), (float) newMaxY);
            if (isLeft) {
                newBottomLeft = newBottom;
            } else {
                newBottomRight = newBottom;
            }
        }
        calculateMinMaxValues();
        BoundingBox boundingBox = new BoundingBox(this.image, newTopLeft, newBottomLeft, newTopRight, newBottomRight);
        return boundingBox;
    }

    private void calculateMinMaxValues() {
        if (this.topLeft == null) {
            this.topLeft = new ResultPoint(0.0f, this.topRight.getY());
            this.bottomLeft = new ResultPoint(0.0f, this.bottomRight.getY());
        } else if (this.topRight == null) {
            this.topRight = new ResultPoint((float) (this.image.getWidth() - 1), this.topLeft.getY());
            this.bottomRight = new ResultPoint((float) (this.image.getWidth() - 1), this.bottomLeft.getY());
        }
        this.minX = (int) Math.min(this.topLeft.getX(), this.bottomLeft.getX());
        this.maxX = (int) Math.max(this.topRight.getX(), this.bottomRight.getX());
        this.minY = (int) Math.min(this.topLeft.getY(), this.topRight.getY());
        this.maxY = (int) Math.max(this.bottomLeft.getY(), this.bottomRight.getY());
    }

    /* access modifiers changed from: 0000 */
    public int getMinX() {
        return this.minX;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxX() {
        return this.maxX;
    }

    /* access modifiers changed from: 0000 */
    public int getMinY() {
        return this.minY;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxY() {
        return this.maxY;
    }

    /* access modifiers changed from: 0000 */
    public ResultPoint getTopLeft() {
        return this.topLeft;
    }

    /* access modifiers changed from: 0000 */
    public ResultPoint getTopRight() {
        return this.topRight;
    }

    /* access modifiers changed from: 0000 */
    public ResultPoint getBottomLeft() {
        return this.bottomLeft;
    }

    /* access modifiers changed from: 0000 */
    public ResultPoint getBottomRight() {
        return this.bottomRight;
    }
}
