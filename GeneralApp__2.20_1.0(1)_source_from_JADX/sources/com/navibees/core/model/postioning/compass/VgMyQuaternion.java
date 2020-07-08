package com.navibees.core.model.postioning.compass;

public class VgMyQuaternion {
    public float[] mVals = new float[4];

    public static final class Euler {

        /* renamed from: a */
        static final int[][] f1466a = {new int[]{0, 1, 2}, new int[]{0, 2, 1}, new int[]{1, 0, 2}, new int[]{1, 2, 0}, new int[]{2, 0, 1}, new int[]{2, 1, 0}};

        /* renamed from: b */
        static final float[] f1467b = {-1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f};

        public enum Order {
            cEO_XYZ,
            cEO_XZY,
            cEO_YXZ,
            cEO_YZX,
            cEO_ZXY,
            cEO_ZYX
        }
    }

    public VgMyQuaternion(float f, float f2, float f3, float f4) {
        float[] fArr = this.mVals;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
    }

    /* renamed from: a */
    static double m1075a(double d, double d2, double d3) {
        return d < d2 ? d2 : d > d3 ? d3 : d;
    }

    public VgMyQuaternion conjugate() {
        float[] fArr = this.mVals;
        fArr[0] = fArr[0] * -1.0f;
        fArr[1] = fArr[1] * -1.0f;
        fArr[2] = fArr[2] * -1.0f;
        return this;
    }

    public VgMyQuaternion copy() {
        return new VgMyQuaternion(this);
    }

    public VgMyQuaternion mult(VgMyQuaternion vgMyQuaternion) {
        VgMyQuaternion vgMyQuaternion2 = new VgMyQuaternion();
        float[] fArr = vgMyQuaternion2.mVals;
        float[] fArr2 = this.mVals;
        float f = fArr2[3];
        float[] fArr3 = vgMyQuaternion.mVals;
        fArr[0] = (((f * fArr3[0]) + (fArr2[0] * fArr3[3])) + (fArr2[1] * fArr3[2])) - (fArr2[2] * fArr3[1]);
        fArr[1] = (((fArr2[3] * fArr3[1]) + (fArr2[1] * fArr3[3])) + (fArr2[2] * fArr3[0])) - (fArr2[0] * fArr3[2]);
        fArr[2] = (((fArr2[3] * fArr3[2]) + (fArr2[2] * fArr3[3])) + (fArr2[0] * fArr3[1])) - (fArr2[1] * fArr3[0]);
        fArr[3] = (((fArr2[3] * fArr3[3]) - (fArr2[0] * fArr3[0])) - (fArr2[1] * fArr3[1])) - (fArr2[2] * fArr3[2]);
        return vgMyQuaternion2;
    }

    public VgMyQuaternion setFromAxisSinHalfTheta(VgMyVector3 vgMyVector3, float f) {
        float[] fArr = this.mVals;
        float[] fArr2 = vgMyVector3.mVals;
        fArr[0] = fArr2[0] * f;
        fArr[1] = fArr2[1] * f;
        fArr[2] = fArr2[2] * f;
        fArr[3] = (float) Math.cos(Math.asin((double) f));
        return this;
    }

    public VgMyQuaternion setFromMatrix3(VgMyMatrix3 vgMyMatrix3) {
        int i = 0;
        float f = vgMyMatrix3.get(0, 0) + vgMyMatrix3.get(1, 1) + vgMyMatrix3.get(2, 2);
        if (f > 0.0f) {
            float sqrt = (float) Math.sqrt(((double) f) + 1.0d);
            float[] fArr = this.mVals;
            fArr[3] = sqrt * 0.5f;
            float f2 = 0.5f / sqrt;
            fArr[0] = (-(vgMyMatrix3.get(2, 1) - vgMyMatrix3.get(1, 2))) * f2;
            this.mVals[1] = (-(vgMyMatrix3.get(0, 2) - vgMyMatrix3.get(2, 0))) * f2;
            this.mVals[2] = (-(vgMyMatrix3.get(1, 0) - vgMyMatrix3.get(0, 1))) * f2;
        } else {
            int[] iArr = {1, 2, 0};
            if (vgMyMatrix3.get(1, 1) > vgMyMatrix3.get(0, 0)) {
                i = 1;
            }
            if (vgMyMatrix3.get(2, 2) > vgMyMatrix3.get(i, i)) {
                i = 2;
            }
            int i2 = iArr[i];
            int i3 = iArr[i2];
            float sqrt2 = (float) Math.sqrt((double) (((vgMyMatrix3.get(i, i) - vgMyMatrix3.get(i2, i2)) - vgMyMatrix3.get(i3, i3)) + 1.0f));
            float[] fArr2 = this.mVals;
            fArr2[i] = sqrt2 * 0.5f;
            float f3 = 0.5f / sqrt2;
            fArr2[3] = (vgMyMatrix3.get(i2, i3) - vgMyMatrix3.get(i3, i2)) * f3;
            this.mVals[i2] = (vgMyMatrix3.get(i, i2) + vgMyMatrix3.get(i2, i)) * f3;
            this.mVals[i3] = (vgMyMatrix3.get(i, i3) + vgMyMatrix3.get(i3, i)) * f3;
        }
        return this;
    }

    public void toEuler(VgMyVector3 vgMyVector3, Order order) {
        VgMyVector3 vgMyVector32 = vgMyVector3;
        float[] fArr = this.mVals;
        float f = fArr[3];
        float f2 = fArr[Euler.f1466a[order.ordinal()][0]];
        float f3 = this.mVals[Euler.f1466a[order.ordinal()][1]];
        float f4 = this.mVals[Euler.f1466a[order.ordinal()][2]];
        float f5 = Euler.f1467b[order.ordinal()];
        float f6 = f5 * f2;
        float f7 = ((f * f4) - (f6 * f3)) * 2.0f;
        float f8 = f3 * f3;
        float f9 = 1.0f - (((f4 * f4) + f8) * 2.0f);
        if (Math.abs(f9) < 0.01f) {
            float[] fArr2 = vgMyVector32.mVals;
            fArr2[2] = 0.0f;
            fArr2[1] = (float) Math.asin(m1075a((double) (((f3 * f) + (f6 * f4)) * 2.0f), -1.0d, 1.0d));
            vgMyVector32.mVals[0] = ((float) Math.atan2((double) f2, (double) f)) * 2.0f;
            return;
        }
        float f10 = f5;
        double d = (double) f7;
        float f11 = f8;
        vgMyVector32.mVals[2] = (float) Math.atan2(d, (double) f9);
        vgMyVector32.mVals[1] = (float) Math.asin((double) (((f * f3) + (f6 * f4)) * 2.0f));
        vgMyVector32.mVals[0] = (float) Math.atan2((double) (((f * f2) - ((f3 * f10) * f4)) * 2.0f), (double) (1.0f - (((f2 * f2) + f11) * 2.0f)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VgMyQuaternion(x=");
        sb.append(this.mVals[0]);
        sb.append(", y=");
        sb.append(this.mVals[1]);
        sb.append(", z=");
        sb.append(this.mVals[2]);
        sb.append(", w=");
        sb.append(this.mVals[3]);
        sb.append(")");
        return sb.toString();
    }

    public VgMyQuaternion() {
    }

    public VgMyQuaternion(VgMyQuaternion vgMyQuaternion) {
        System.arraycopy(vgMyQuaternion.mVals, 0, this.mVals, 0, 4);
    }
}
