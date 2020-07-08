package com.visioglobe.libVisioMove;

public class VgColor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgColor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgColor obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                libVisioMoveJNI.delete_VgColor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgColor(float pRed, float pGreen, float pBlue, float pAlpha) {
        this(libVisioMoveJNI.new_VgColor__SWIG_0(pRed, pGreen, pBlue, pAlpha), true);
    }

    public VgColor(float pRed, float pGreen, float pBlue) {
        this(libVisioMoveJNI.new_VgColor__SWIG_1(pRed, pGreen, pBlue), true);
    }

    public VgColor(float pRed, float pGreen) {
        this(libVisioMoveJNI.new_VgColor__SWIG_2(pRed, pGreen), true);
    }

    public VgColor(float pRed) {
        this(libVisioMoveJNI.new_VgColor__SWIG_3(pRed), true);
    }

    public VgColor() {
        this(libVisioMoveJNI.new_VgColor__SWIG_4(), true);
    }

    public VgColor(VgColor pColor) {
        this(libVisioMoveJNI.new_VgColor__SWIG_5(getCPtr(pColor), pColor), true);
    }

    public void setMRed(float value) {
        libVisioMoveJNI.VgColor_mRed_set(this.swigCPtr, this, value);
    }

    public float getMRed() {
        return libVisioMoveJNI.VgColor_mRed_get(this.swigCPtr, this);
    }

    public void setMGreen(float value) {
        libVisioMoveJNI.VgColor_mGreen_set(this.swigCPtr, this, value);
    }

    public float getMGreen() {
        return libVisioMoveJNI.VgColor_mGreen_get(this.swigCPtr, this);
    }

    public void setMBlue(float value) {
        libVisioMoveJNI.VgColor_mBlue_set(this.swigCPtr, this, value);
    }

    public float getMBlue() {
        return libVisioMoveJNI.VgColor_mBlue_get(this.swigCPtr, this);
    }

    public void setMAlpha(float value) {
        libVisioMoveJNI.VgColor_mAlpha_set(this.swigCPtr, this, value);
    }

    public float getMAlpha() {
        return libVisioMoveJNI.VgColor_mAlpha_get(this.swigCPtr, this);
    }
}
