package com.visioglobe.libVisioMove;

public class VgIViewPoint {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIViewPoint(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIViewPoint obj) {
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
                libVisioMoveJNI.delete_VgIViewPoint(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIViewPoint() {
        this(libVisioMoveJNI.new_VgIViewPoint__SWIG_0(), true);
    }

    public VgIViewPoint(VgIViewPoint pOther) {
        this(libVisioMoveJNI.new_VgIViewPoint__SWIG_1(getCPtr(pOther), pOther), true);
    }

    public void setMPosition(VgPosition value) {
        libVisioMoveJNI.VgIViewPoint_mPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMPosition() {
        long cPtr = libVisioMoveJNI.VgIViewPoint_mPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMHeading(double value) {
        libVisioMoveJNI.VgIViewPoint_mHeading_set(this.swigCPtr, this, value);
    }

    public double getMHeading() {
        return libVisioMoveJNI.VgIViewPoint_mHeading_get(this.swigCPtr, this);
    }

    public void setMPitch(double value) {
        libVisioMoveJNI.VgIViewPoint_mPitch_set(this.swigCPtr, this, value);
    }

    public double getMPitch() {
        return libVisioMoveJNI.VgIViewPoint_mPitch_get(this.swigCPtr, this);
    }
}
