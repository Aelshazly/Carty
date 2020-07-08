package com.navibees.core.model.postioning.compass;

public class VgMyVector3 {
    public static final VgMyVector3 UNITX = new VgMyVector3(1.0f, 0.0f, 0.0f);
    public static final VgMyVector3 UNITY = new VgMyVector3(0.0f, 1.0f, 0.0f);
    public static final VgMyVector3 UNITZ = new VgMyVector3(0.0f, 0.0f, 1.0f);
    public static final VgMyVector3 UNITx = new VgMyVector3(-1.0f, 0.0f, 0.0f);
    public static final VgMyVector3 UNITy = new VgMyVector3(0.0f, -1.0f, 0.0f);
    public static final VgMyVector3 UNITz = new VgMyVector3(0.0f, 0.0f, -1.0f);

    /* renamed from: a */
    static final VgMyVector3 f1468a = new VgMyVector3(0.0f, 0.0f, 0.0f);
    public float[] mVals = new float[3];

    public VgMyVector3() {
        float[] fArr = this.mVals;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
    }

    public static VgMyVector3 cross(VgMyVector3 vgMyVector3, VgMyVector3 vgMyVector32, VgMyVector3 vgMyVector33) {
        float[] fArr = vgMyVector32.mVals;
        float f = fArr[1];
        float[] fArr2 = vgMyVector33.mVals;
        vgMyVector3.set((f * fArr2[2]) - (fArr[2] * fArr2[1]), (fArr[2] * fArr2[0]) - (fArr[0] * fArr2[2]), (fArr[0] * fArr2[1]) - (fArr[1] * fArr2[0]));
        return vgMyVector3;
    }

    public VgMyVector3 add(VgMyVector3 vgMyVector3) {
        float[] fArr = this.mVals;
        float f = fArr[0];
        float[] fArr2 = vgMyVector3.mVals;
        fArr[0] = f + fArr2[0];
        fArr[1] = fArr[1] + fArr2[1];
        fArr[2] = fArr[2] + fArr2[2];
        return this;
    }

    public VgMyVector3 copy() {
        return new VgMyVector3(this);
    }

    public VgMyVector3 div(float f) {
        return mult(1.0f / f);
    }

    public boolean equals(VgMyVector3 vgMyVector3) {
        float[] fArr = this.mVals;
        float f = fArr[0];
        float[] fArr2 = vgMyVector3.mVals;
        return f == fArr2[0] && fArr[1] == fArr2[1] && fArr[2] == fArr2[2];
    }

    public VgMyVector3 fromVector3(VgMyVector3 vgMyVector3) {
        System.arraycopy(vgMyVector3.mVals, 0, this.mVals, 0, 3);
        return this;
    }

    public VgMyVector3 mult(float f) {
        float[] fArr = this.mVals;
        fArr[0] = fArr[0] * f;
        fArr[1] = fArr[1] * f;
        fArr[2] = fArr[2] * f;
        return this;
    }

    public float norm() {
        float[] fArr = this.mVals;
        return (float) Math.sqrt((double) ((fArr[0] * fArr[0]) + (fArr[1] * fArr[1]) + (fArr[2] * fArr[2])));
    }

    public VgMyVector3 normalize() {
        mult(1.0f / norm());
        return this;
    }

    public VgMyVector3 radiansToDegrees() {
        float[] fArr = this.mVals;
        fArr[0] = (float) Math.toDegrees((double) fArr[0]);
        float[] fArr2 = this.mVals;
        fArr2[1] = (float) Math.toDegrees((double) fArr2[1]);
        float[] fArr3 = this.mVals;
        fArr3[2] = (float) Math.toDegrees((double) fArr3[2]);
        return this;
    }

    public VgMyVector3 set(float[] fArr) {
        int i = 0;
        while (i < fArr.length && i < 3) {
            this.mVals[i] = fArr[i];
            i++;
        }
        return this;
    }

    public float sqNorm() {
        float[] fArr = this.mVals;
        return (fArr[0] * fArr[0]) + (fArr[1] * fArr[1]) + (fArr[2] * fArr[2]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(this.mVals[0]);
        String str = ", ";
        sb.append(str);
        sb.append(this.mVals[1]);
        sb.append(str);
        sb.append(this.mVals[2]);
        sb.append(")");
        return sb.toString();
    }

    public VgMyVector3 set(VgMyVector3 vgMyVector3) {
        System.arraycopy(vgMyVector3.mVals, 0, this.mVals, 0, 3);
        return this;
    }

    public VgMyVector3 mult(VgMyQuaternion vgMyQuaternion) {
        VgMyQuaternion conjugate = vgMyQuaternion.copy().conjugate();
        float[] fArr = this.mVals;
        VgMyQuaternion mult = vgMyQuaternion.copy().mult(new VgMyQuaternion(fArr[0], fArr[1], fArr[2], 0.0f)).mult(conjugate);
        float[] fArr2 = this.mVals;
        float[] fArr3 = mult.mVals;
        fArr2[0] = fArr3[0];
        fArr2[1] = fArr3[1];
        fArr2[2] = fArr3[2];
        return this;
    }

    public VgMyVector3 set(float f, float f2, float f3) {
        float[] fArr = this.mVals;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        return this;
    }

    public VgMyVector3(VgMyVector3 vgMyVector3) {
        fromVector3(vgMyVector3);
    }

    public VgMyVector3(float f, float f2, float f3) {
        float[] fArr = this.mVals;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
    }
}
