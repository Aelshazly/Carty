package com.visioglobe.libVisioMove;

public class VgOrientation {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgOrientation(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgOrientation obj) {
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
                libVisioMoveJNI.delete_VgOrientation(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMAzimuth(float value) {
        libVisioMoveJNI.VgOrientation_mAzimuth_set(this.swigCPtr, this, value);
    }

    public float getMAzimuth() {
        return libVisioMoveJNI.VgOrientation_mAzimuth_get(this.swigCPtr, this);
    }

    public void setMPitch(float value) {
        libVisioMoveJNI.VgOrientation_mPitch_set(this.swigCPtr, this, value);
    }

    public float getMPitch() {
        return libVisioMoveJNI.VgOrientation_mPitch_get(this.swigCPtr, this);
    }

    public void setMRoll(float value) {
        libVisioMoveJNI.VgOrientation_mRoll_set(this.swigCPtr, this, value);
    }

    public float getMRoll() {
        return libVisioMoveJNI.VgOrientation_mRoll_get(this.swigCPtr, this);
    }

    public VgOrientation() {
        this(libVisioMoveJNI.new_VgOrientation__SWIG_0(), true);
    }

    public VgOrientation(VgOrientation pOther) {
        this(libVisioMoveJNI.new_VgOrientation__SWIG_1(getCPtr(pOther), pOther), true);
    }

    public VgOrientation(float pAzimuth, float pPitch, float pRoll) {
        this(libVisioMoveJNI.new_VgOrientation__SWIG_2(pAzimuth, pPitch, pRoll), true);
    }
}
