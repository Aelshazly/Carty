package com.navibees.core.model.postioning.compass;

public class VgMyMatrix3 {
    public float[] mVals = new float[9];

    public float get(int i, int i2) {
        return this.mVals[(i * 3) + i2];
    }

    public void set(int i, int i2, float f) {
        this.mVals[(i * 3) + i2] = f;
    }

    public VgMyMatrix3 set(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float[] fArr = this.mVals;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        fArr[6] = f7;
        fArr[7] = f8;
        fArr[8] = f9;
        return this;
    }
}
