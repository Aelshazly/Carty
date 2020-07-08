package com.google.android.material.shadow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Shader.TileMode;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;

public class ShadowRenderer {
    private static final int COLOR_ALPHA_END = 0;
    private static final int COLOR_ALPHA_MIDDLE = 20;
    private static final int COLOR_ALPHA_START = 68;
    private static final int[] cornerColors = new int[4];
    private static final float[] cornerPositions = {0.0f, 0.0f, 0.5f, 1.0f};
    private static final int[] edgeColors = new int[3];
    private static final float[] edgePositions = {0.0f, 0.5f, 1.0f};
    private final Paint cornerShadowPaint;
    private final Paint edgeShadowPaint;
    private final Path scratch;
    private int shadowEndColor;
    private int shadowMiddleColor;
    private final Paint shadowPaint;
    private int shadowStartColor;

    public ShadowRenderer() {
        this(ViewCompat.MEASURED_STATE_MASK);
    }

    public ShadowRenderer(int color) {
        this.scratch = new Path();
        setShadowColor(color);
        this.cornerShadowPaint = new Paint(4);
        this.cornerShadowPaint.setStyle(Style.FILL);
        this.shadowPaint = new Paint();
        this.shadowPaint.setColor(this.shadowStartColor);
        this.edgeShadowPaint = new Paint(this.cornerShadowPaint);
    }

    public void setShadowColor(int color) {
        this.shadowStartColor = ColorUtils.setAlphaComponent(color, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(color, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(color, 0);
    }

    public void drawEdgeShadow(Canvas canvas, Matrix transform, RectF bounds, int elevation) {
        bounds.bottom += (float) elevation;
        bounds.offset(0.0f, (float) (-elevation));
        int[] iArr = edgeColors;
        iArr[0] = this.shadowEndColor;
        iArr[1] = this.shadowMiddleColor;
        iArr[2] = this.shadowStartColor;
        Paint paint = this.edgeShadowPaint;
        LinearGradient linearGradient = new LinearGradient(bounds.left, bounds.top, bounds.left, bounds.bottom, edgeColors, edgePositions, TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.save();
        canvas.concat(transform);
        canvas.drawRect(bounds, this.edgeShadowPaint);
        canvas.restore();
    }

    public void drawCornerShadow(Canvas canvas, Matrix matrix, RectF bounds, int elevation, float startAngle, float sweepAngle) {
        RectF rectF = bounds;
        int i = elevation;
        float f = sweepAngle;
        boolean drawShadowInsideBounds = f < 0.0f;
        Path arcBounds = this.scratch;
        if (drawShadowInsideBounds) {
            int[] iArr = cornerColors;
            iArr[0] = 0;
            iArr[1] = this.shadowEndColor;
            iArr[2] = this.shadowMiddleColor;
            iArr[3] = this.shadowStartColor;
            float f2 = startAngle;
        } else {
            arcBounds.rewind();
            arcBounds.moveTo(bounds.centerX(), bounds.centerY());
            arcBounds.arcTo(rectF, startAngle, f);
            arcBounds.close();
            rectF.inset((float) (-i), (float) (-i));
            int[] iArr2 = cornerColors;
            iArr2[0] = 0;
            iArr2[1] = this.shadowStartColor;
            iArr2[2] = this.shadowMiddleColor;
            iArr2[3] = this.shadowEndColor;
        }
        float startRatio = 1.0f - (((float) i) / (bounds.width() / 2.0f));
        float midRatio = startRatio + ((1.0f - startRatio) / 2.0f);
        float[] fArr = cornerPositions;
        fArr[1] = startRatio;
        fArr[2] = midRatio;
        Paint paint = this.cornerShadowPaint;
        RadialGradient radialGradient = new RadialGradient(bounds.centerX(), bounds.centerY(), bounds.width() / 2.0f, cornerColors, cornerPositions, TileMode.CLAMP);
        paint.setShader(radialGradient);
        canvas.save();
        canvas.concat(matrix);
        if (!drawShadowInsideBounds) {
            canvas.clipPath(arcBounds, Op.DIFFERENCE);
        } else {
            Canvas canvas2 = canvas;
        }
        canvas.drawArc(bounds, startAngle, sweepAngle, true, this.cornerShadowPaint);
        canvas.restore();
    }

    public Paint getShadowPaint() {
        return this.shadowPaint;
    }
}
