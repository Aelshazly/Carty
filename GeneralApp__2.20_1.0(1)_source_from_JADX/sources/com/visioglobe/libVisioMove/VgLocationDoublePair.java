package com.visioglobe.libVisioMove;

public class VgLocationDoublePair {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLocationDoublePair(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLocationDoublePair obj) {
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
                libVisioMoveJNI.delete_VgLocationDoublePair(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLocationDoublePair() {
        this(libVisioMoveJNI.new_VgLocationDoublePair__SWIG_0(), true);
    }

    public VgLocationDoublePair(VgPosition first, double second) {
        this(libVisioMoveJNI.new_VgLocationDoublePair__SWIG_1(VgPosition.getCPtr(first), first, second), true);
    }

    public VgLocationDoublePair(VgLocationDoublePair p) {
        this(libVisioMoveJNI.new_VgLocationDoublePair__SWIG_2(getCPtr(p), p), true);
    }

    public void setFirst(VgPosition value) {
        libVisioMoveJNI.VgLocationDoublePair_first_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getFirst() {
        long cPtr = libVisioMoveJNI.VgLocationDoublePair_first_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setSecond(double value) {
        libVisioMoveJNI.VgLocationDoublePair_second_set(this.swigCPtr, this, value);
    }

    public double getSecond() {
        return libVisioMoveJNI.VgLocationDoublePair_second_get(this.swigCPtr, this);
    }
}
