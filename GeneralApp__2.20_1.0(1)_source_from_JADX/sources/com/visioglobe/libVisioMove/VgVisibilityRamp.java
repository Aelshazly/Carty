package com.visioglobe.libVisioMove;

public class VgVisibilityRamp {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgVisibilityRamp(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgVisibilityRamp obj) {
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
                libVisioMoveJNI.delete_VgVisibilityRamp(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMStartVisibleDistance(float value) {
        libVisioMoveJNI.VgVisibilityRamp_mStartVisibleDistance_set(this.swigCPtr, this, value);
    }

    public float getMStartVisibleDistance() {
        return libVisioMoveJNI.VgVisibilityRamp_mStartVisibleDistance_get(this.swigCPtr, this);
    }

    public void setMFullyVisibleDistance(float value) {
        libVisioMoveJNI.VgVisibilityRamp_mFullyVisibleDistance_set(this.swigCPtr, this, value);
    }

    public float getMFullyVisibleDistance() {
        return libVisioMoveJNI.VgVisibilityRamp_mFullyVisibleDistance_get(this.swigCPtr, this);
    }

    public void setMStartInvisibleDistance(float value) {
        libVisioMoveJNI.VgVisibilityRamp_mStartInvisibleDistance_set(this.swigCPtr, this, value);
    }

    public float getMStartInvisibleDistance() {
        return libVisioMoveJNI.VgVisibilityRamp_mStartInvisibleDistance_get(this.swigCPtr, this);
    }

    public void setMFullyInvisibleDistance(float value) {
        libVisioMoveJNI.VgVisibilityRamp_mFullyInvisibleDistance_set(this.swigCPtr, this, value);
    }

    public float getMFullyInvisibleDistance() {
        return libVisioMoveJNI.VgVisibilityRamp_mFullyInvisibleDistance_get(this.swigCPtr, this);
    }

    public VgVisibilityRamp() {
        this(libVisioMoveJNI.new_VgVisibilityRamp__SWIG_0(), true);
    }

    public VgVisibilityRamp(float pStartVisibleDistance, float pFullyVisibleDistance, float pStartInvisibleDistance, float pFullyInvisibleDistance) {
        this(libVisioMoveJNI.new_VgVisibilityRamp__SWIG_1(pStartVisibleDistance, pFullyVisibleDistance, pStartInvisibleDistance, pFullyInvisibleDistance), true);
    }
}
