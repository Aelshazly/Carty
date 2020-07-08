package com.google.zxing.common;

public final class PerspectiveTransform {
    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;

    private PerspectiveTransform(float a112, float a212, float a312, float a122, float a222, float a322, float a132, float a232, float a332) {
        this.a11 = a112;
        this.a12 = a122;
        this.a13 = a132;
        this.a21 = a212;
        this.a22 = a222;
        this.a23 = a232;
        this.a31 = a312;
        this.a32 = a322;
        this.a33 = a332;
    }

    public static PerspectiveTransform quadrilateralToQuadrilateral(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3, float x0p, float y0p, float x1p, float y1p, float x2p, float y2p, float x3p, float y3p) {
        return squareToQuadrilateral(x0p, y0p, x1p, y1p, x2p, y2p, x3p, y3p).times(quadrilateralToSquare(x0, y0, x1, y1, x2, y2, x3, y3));
    }

    public void transformPoints(float[] points) {
        float[] fArr = points;
        int max = fArr.length;
        float a112 = this.a11;
        float a122 = this.a12;
        float a132 = this.a13;
        float a212 = this.a21;
        float a222 = this.a22;
        float a232 = this.a23;
        float a312 = this.a31;
        float a322 = this.a32;
        float a332 = this.a33;
        for (int i = 0; i < max; i += 2) {
            float x = fArr[i];
            float y = fArr[i + 1];
            float denominator = (a132 * x) + (a232 * y) + a332;
            fArr[i] = (((a112 * x) + (a212 * y)) + a312) / denominator;
            fArr[i + 1] = (((a122 * x) + (a222 * y)) + a322) / denominator;
        }
    }

    public void transformPoints(float[] xValues, float[] yValues) {
        int n = xValues.length;
        for (int i = 0; i < n; i++) {
            float x = xValues[i];
            float y = yValues[i];
            float denominator = (this.a13 * x) + (this.a23 * y) + this.a33;
            xValues[i] = (((this.a11 * x) + (this.a21 * y)) + this.a31) / denominator;
            yValues[i] = (((this.a12 * x) + (this.a22 * y)) + this.a32) / denominator;
        }
    }

    public static PerspectiveTransform squareToQuadrilateral(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        float dx3 = ((x0 - x1) + x2) - x3;
        float dy3 = ((y0 - y1) + y2) - y3;
        if (dx3 == 0.0f && dy3 == 0.0f) {
            PerspectiveTransform perspectiveTransform = new PerspectiveTransform(x1 - x0, x2 - x1, x0, y1 - y0, y2 - y1, y0, 0.0f, 0.0f, 1.0f);
            return perspectiveTransform;
        }
        float dx1 = x1 - x2;
        float dx2 = x3 - x2;
        float dy1 = y1 - y2;
        float dy2 = y3 - y2;
        float denominator = (dx1 * dy2) - (dx2 * dy1);
        float a132 = ((dx3 * dy2) - (dx2 * dy3)) / denominator;
        float a232 = ((dx1 * dy3) - (dx3 * dy1)) / denominator;
        PerspectiveTransform perspectiveTransform2 = new PerspectiveTransform((a132 * x1) + (x1 - x0), (a232 * x3) + (x3 - x0), x0, (y1 - y0) + (a132 * y1), (y3 - y0) + (a232 * y3), y0, a132, a232, 1.0f);
        return perspectiveTransform2;
    }

    public static PerspectiveTransform quadrilateralToSquare(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        return squareToQuadrilateral(x0, y0, x1, y1, x2, y2, x3, y3).buildAdjoint();
    }

    /* access modifiers changed from: 0000 */
    public PerspectiveTransform buildAdjoint() {
        float f = this.a22;
        float f2 = this.a33;
        float f3 = f * f2;
        float f4 = this.a23;
        float f5 = this.a32;
        float f6 = f3 - (f4 * f5);
        float f7 = this.a31;
        float f8 = f4 * f7;
        float f9 = this.a21;
        float f10 = f8 - (f9 * f2);
        float f11 = (f9 * f5) - (f * f7);
        float f12 = this.a13;
        float f13 = f12 * f5;
        float f14 = this.a12;
        float f15 = f13 - (f14 * f2);
        float f16 = this.a11;
        PerspectiveTransform perspectiveTransform = new PerspectiveTransform(f6, f10, f11, f15, (f2 * f16) - (f12 * f7), (f7 * f14) - (f5 * f16), (f14 * f4) - (f12 * f), (f12 * f9) - (f4 * f16), (f16 * f) - (f14 * f9));
        return perspectiveTransform;
    }

    /* access modifiers changed from: 0000 */
    public PerspectiveTransform times(PerspectiveTransform other) {
        PerspectiveTransform perspectiveTransform = other;
        float f = this.a11;
        float f2 = perspectiveTransform.a11;
        float f3 = f * f2;
        float f4 = this.a21;
        float f5 = perspectiveTransform.a12;
        float f6 = f3 + (f4 * f5);
        float f7 = this.a31;
        float f8 = perspectiveTransform.a13;
        float f9 = f6 + (f7 * f8);
        float f10 = perspectiveTransform.a21;
        float f11 = f * f10;
        float f12 = perspectiveTransform.a22;
        float f13 = f11 + (f4 * f12);
        float f14 = perspectiveTransform.a23;
        float f15 = f13 + (f7 * f14);
        float f16 = perspectiveTransform.a31;
        float f17 = f * f16;
        float f18 = perspectiveTransform.a32;
        float f19 = f17 + (f4 * f18);
        float f20 = perspectiveTransform.a33;
        float f21 = (f7 * f20) + f19;
        float f22 = this.a12;
        float f23 = f22 * f2;
        float f24 = this.a22;
        float f25 = f23 + (f24 * f5);
        float f26 = f21;
        float f27 = this.a32;
        float f28 = f25 + (f27 * f8);
        float f29 = (f22 * f10) + (f24 * f12) + (f27 * f14);
        float f30 = (f22 * f16) + (f24 * f18) + (f27 * f20);
        float f31 = this.a13;
        float f32 = f2 * f31;
        float f33 = this.a23;
        float f34 = f32 + (f5 * f33);
        float f35 = this.a33;
        PerspectiveTransform perspectiveTransform2 = new PerspectiveTransform(f9, f15, f26, f28, f29, f30, f34 + (f8 * f35), (f10 * f31) + (f12 * f33) + (f14 * f35), (f31 * f16) + (f33 * f18) + (f35 * f20));
        return perspectiveTransform2;
    }
}
