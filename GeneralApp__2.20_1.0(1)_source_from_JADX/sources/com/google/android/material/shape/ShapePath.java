package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.List;

public class ShapePath {
    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    @Deprecated
    public float currentShadowAngle;
    @Deprecated
    public float endShadowAngle;
    @Deprecated
    public float endX;
    @Deprecated
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    private final List<ShadowCompatOperation> shadowCompatOperations = new ArrayList();
    @Deprecated
    public float startX;
    @Deprecated
    public float startY;

    static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation operation2) {
            this.operation = operation2;
        }

        public void draw(Matrix transform, ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
            float startAngle = this.operation.getStartAngle();
            float sweepAngle = this.operation.getSweepAngle();
            shadowRenderer.drawCornerShadow(canvas, transform, new RectF(this.operation.getLeft(), this.operation.getTop(), this.operation.getRight(), this.operation.getBottom()), shadowElevation, startAngle, sweepAngle);
        }
    }

    static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation operation2, float startX2, float startY2) {
            this.operation = operation2;
            this.startX = startX2;
            this.startY = startY2;
        }

        public void draw(Matrix transform, ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
            RectF rect = new RectF(0.0f, 0.0f, (float) Math.hypot((double) (this.operation.f76y - this.startY), (double) (this.operation.f75x - this.startX)), 0.0f);
            Matrix edgeTransform = new Matrix(transform);
            edgeTransform.preTranslate(this.startX, this.startY);
            edgeTransform.preRotate(getAngle());
            shadowRenderer.drawEdgeShadow(canvas, edgeTransform, rect, shadowElevation);
        }

        /* access modifiers changed from: 0000 */
        public float getAngle() {
            return (float) Math.toDegrees(Math.atan((double) ((this.operation.f76y - this.startY) / (this.operation.f75x - this.startX))));
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();
        @Deprecated
        public float bottom;
        @Deprecated
        public float left;
        @Deprecated
        public float right;
        @Deprecated
        public float startAngle;
        @Deprecated
        public float sweepAngle;
        @Deprecated
        public float top;

        public PathArcOperation(float left2, float top2, float right2, float bottom2) {
            setLeft(left2);
            setTop(top2);
            setRight(right2);
            setBottom(bottom2);
        }

        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            rectF.set(getLeft(), getTop(), getRight(), getBottom());
            path.arcTo(rectF, getStartAngle(), getSweepAngle(), false);
            path.transform(transform);
        }

        /* access modifiers changed from: private */
        public float getLeft() {
            return this.left;
        }

        /* access modifiers changed from: private */
        public float getTop() {
            return this.top;
        }

        /* access modifiers changed from: private */
        public float getRight() {
            return this.right;
        }

        /* access modifiers changed from: private */
        public float getBottom() {
            return this.bottom;
        }

        private void setLeft(float left2) {
            this.left = left2;
        }

        private void setTop(float top2) {
            this.top = top2;
        }

        private void setRight(float right2) {
            this.right = right2;
        }

        private void setBottom(float bottom2) {
            this.bottom = bottom2;
        }

        /* access modifiers changed from: private */
        public float getStartAngle() {
            return this.startAngle;
        }

        /* access modifiers changed from: private */
        public float getSweepAngle() {
            return this.sweepAngle;
        }

        /* access modifiers changed from: private */
        public void setStartAngle(float startAngle2) {
            this.startAngle = startAngle2;
        }

        /* access modifiers changed from: private */
        public void setSweepAngle(float sweepAngle2) {
            this.sweepAngle = sweepAngle2;
        }
    }

    public static class PathLineOperation extends PathOperation {
        /* access modifiers changed from: private */

        /* renamed from: x */
        public float f75x;
        /* access modifiers changed from: private */

        /* renamed from: y */
        public float f76y;

        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            path.lineTo(this.f75x, this.f76y);
            path.transform(transform);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix2, Path path);
    }

    public static class PathQuadOperation extends PathOperation {
        @Deprecated
        public float controlX;
        @Deprecated
        public float controlY;
        @Deprecated
        public float endX;
        @Deprecated
        public float endY;

        public void applyToPath(Matrix transform, Path path) {
            Matrix inverse = this.matrix;
            transform.invert(inverse);
            path.transform(inverse);
            path.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            path.transform(transform);
        }

        private float getEndX() {
            return this.endX;
        }

        /* access modifiers changed from: private */
        public void setEndX(float endX2) {
            this.endX = endX2;
        }

        private float getControlY() {
            return this.controlY;
        }

        /* access modifiers changed from: private */
        public void setControlY(float controlY2) {
            this.controlY = controlY2;
        }

        private float getEndY() {
            return this.endY;
        }

        /* access modifiers changed from: private */
        public void setEndY(float endY2) {
            this.endY = endY2;
        }

        private float getControlX() {
            return this.controlX;
        }

        /* access modifiers changed from: private */
        public void setControlX(float controlX2) {
            this.controlX = controlX2;
        }
    }

    static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX = new Matrix();

        public abstract void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i, Canvas canvas);

        ShadowCompatOperation() {
        }

        public final void draw(ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
            draw(IDENTITY_MATRIX, shadowRenderer, shadowElevation, canvas);
        }
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    public ShapePath(float startX2, float startY2) {
        reset(startX2, startY2);
    }

    public void reset(float startX2, float startY2) {
        reset(startX2, startY2, 270.0f, 0.0f);
    }

    public void reset(float startX2, float startY2, float shadowStartAngle, float shadowSweepAngle) {
        setStartX(startX2);
        setStartY(startY2);
        setEndX(startX2);
        setEndY(startY2);
        setCurrentShadowAngle(shadowStartAngle);
        setEndShadowAngle((shadowStartAngle + shadowSweepAngle) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
    }

    public void lineTo(float x, float y) {
        PathLineOperation operation = new PathLineOperation();
        operation.f75x = x;
        operation.f76y = y;
        this.operations.add(operation);
        LineShadowOperation shadowOperation = new LineShadowOperation(operation, getEndX(), getEndY());
        addShadowCompatOperation(shadowOperation, shadowOperation.getAngle() + 270.0f, shadowOperation.getAngle() + 270.0f);
        setEndX(x);
        setEndY(y);
    }

    public void quadToPoint(float controlX, float controlY, float toX, float toY) {
        PathQuadOperation operation = new PathQuadOperation();
        operation.setControlX(controlX);
        operation.setControlY(controlY);
        operation.setEndX(toX);
        operation.setEndY(toY);
        this.operations.add(operation);
        setEndX(toX);
        setEndY(toY);
    }

    public void addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) {
        float f = left;
        float f2 = top;
        float f3 = right;
        float f4 = bottom;
        float f5 = startAngle;
        float f6 = sweepAngle;
        PathArcOperation operation = new PathArcOperation(f, f2, f3, f4);
        operation.setStartAngle(f5);
        operation.setSweepAngle(f6);
        this.operations.add(operation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(operation);
        float endAngle = f5 + f6;
        boolean drawShadowInsideBounds = f6 < 0.0f;
        addShadowCompatOperation(arcShadowOperation, drawShadowInsideBounds ? (f5 + 180.0f) % 360.0f : f5, drawShadowInsideBounds ? (180.0f + endAngle) % 360.0f : endAngle);
        setEndX(((f + f3) * 0.5f) + (((f3 - f) / 2.0f) * ((float) Math.cos(Math.toRadians((double) (f5 + f6))))));
        setEndY(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.sin(Math.toRadians((double) (f5 + f6))))));
    }

    public void applyToPath(Matrix transform, Path path) {
        int size = this.operations.size();
        for (int i = 0; i < size; i++) {
            ((PathOperation) this.operations.get(i)).applyToPath(transform, path);
        }
    }

    /* access modifiers changed from: 0000 */
    public ShadowCompatOperation createShadowCompatOperation(final Matrix transform) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        final List<ShadowCompatOperation> operations2 = new ArrayList<>(this.shadowCompatOperations);
        return new ShadowCompatOperation() {
            public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int shadowElevation, Canvas canvas) {
                for (ShadowCompatOperation op : operations2) {
                    op.draw(transform, shadowRenderer, shadowElevation, canvas);
                }
            }
        };
    }

    private void addShadowCompatOperation(ShadowCompatOperation shadowOperation, float startShadowAngle, float endShadowAngle2) {
        addConnectingShadowIfNecessary(startShadowAngle);
        this.shadowCompatOperations.add(shadowOperation);
        setCurrentShadowAngle(endShadowAngle2);
    }

    private void addConnectingShadowIfNecessary(float nextShadowAngle) {
        if (getCurrentShadowAngle() != nextShadowAngle) {
            float shadowSweep = ((nextShadowAngle - getCurrentShadowAngle()) + 360.0f) % 360.0f;
            if (shadowSweep <= 180.0f) {
                PathArcOperation pathArcOperation = new PathArcOperation(getEndX(), getEndY(), getEndX(), getEndY());
                pathArcOperation.setStartAngle(getCurrentShadowAngle());
                pathArcOperation.setSweepAngle(shadowSweep);
                this.shadowCompatOperations.add(new ArcShadowOperation(pathArcOperation));
                setCurrentShadowAngle(nextShadowAngle);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public float getStartX() {
        return this.startX;
    }

    /* access modifiers changed from: 0000 */
    public float getStartY() {
        return this.startY;
    }

    /* access modifiers changed from: 0000 */
    public float getEndX() {
        return this.endX;
    }

    /* access modifiers changed from: 0000 */
    public float getEndY() {
        return this.endY;
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setStartX(float startX2) {
        this.startX = startX2;
    }

    private void setStartY(float startY2) {
        this.startY = startY2;
    }

    private void setEndX(float endX2) {
        this.endX = endX2;
    }

    private void setEndY(float endY2) {
        this.endY = endY2;
    }

    private void setCurrentShadowAngle(float currentShadowAngle2) {
        this.currentShadowAngle = currentShadowAngle2;
    }

    private void setEndShadowAngle(float endShadowAngle2) {
        this.endShadowAngle = endShadowAngle2;
    }
}
