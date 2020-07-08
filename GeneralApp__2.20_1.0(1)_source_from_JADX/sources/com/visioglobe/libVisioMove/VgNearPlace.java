package com.visioglobe.libVisioMove;

public class VgNearPlace {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgNearPlace(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgNearPlace obj) {
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
                libVisioMoveJNI.delete_VgNearPlace(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMID(String value) {
        libVisioMoveJNI.VgNearPlace_mID_set(this.swigCPtr, this, value);
    }

    public String getMID() {
        return libVisioMoveJNI.VgNearPlace_mID_get(this.swigCPtr, this);
    }

    public void setMDistance(double value) {
        libVisioMoveJNI.VgNearPlace_mDistance_set(this.swigCPtr, this, value);
    }

    public double getMDistance() {
        return libVisioMoveJNI.VgNearPlace_mDistance_get(this.swigCPtr, this);
    }

    public void setMAngle(double value) {
        libVisioMoveJNI.VgNearPlace_mAngle_set(this.swigCPtr, this, value);
    }

    public double getMAngle() {
        return libVisioMoveJNI.VgNearPlace_mAngle_get(this.swigCPtr, this);
    }

    public VgNearPlace() {
        this(libVisioMoveJNI.new_VgNearPlace(), true);
    }
}
