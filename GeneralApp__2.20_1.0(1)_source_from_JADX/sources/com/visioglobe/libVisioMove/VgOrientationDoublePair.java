package com.visioglobe.libVisioMove;

public class VgOrientationDoublePair {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgOrientationDoublePair(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgOrientationDoublePair obj) {
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
                libVisioMoveJNI.delete_VgOrientationDoublePair(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgOrientationDoublePair() {
        this(libVisioMoveJNI.new_VgOrientationDoublePair__SWIG_0(), true);
    }

    public VgOrientationDoublePair(VgOrientation first, double second) {
        this(libVisioMoveJNI.new_VgOrientationDoublePair__SWIG_1(VgOrientation.getCPtr(first), first, second), true);
    }

    public VgOrientationDoublePair(VgOrientationDoublePair p) {
        this(libVisioMoveJNI.new_VgOrientationDoublePair__SWIG_2(getCPtr(p), p), true);
    }

    public void setFirst(VgOrientation value) {
        libVisioMoveJNI.VgOrientationDoublePair_first_set(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getFirst() {
        long cPtr = libVisioMoveJNI.VgOrientationDoublePair_first_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setSecond(double value) {
        libVisioMoveJNI.VgOrientationDoublePair_second_set(this.swigCPtr, this, value);
    }

    public double getSecond() {
        return libVisioMoveJNI.VgOrientationDoublePair_second_get(this.swigCPtr, this);
    }
}
