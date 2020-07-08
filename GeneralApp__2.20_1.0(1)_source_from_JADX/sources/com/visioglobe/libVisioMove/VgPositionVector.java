package com.visioglobe.libVisioMove;

public class VgPositionVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPositionVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPositionVector obj) {
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
                libVisioMoveJNI.delete_VgPositionVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPositionVector() {
        this(libVisioMoveJNI.new_VgPositionVector__SWIG_0(), true);
    }

    public VgPositionVector(long n) {
        this(libVisioMoveJNI.new_VgPositionVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgPositionVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgPositionVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgPositionVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgPositionVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgPositionVector_clear(this.swigCPtr, this);
    }

    public void add(VgPosition x) {
        libVisioMoveJNI.VgPositionVector_add(this.swigCPtr, this, VgPosition.getCPtr(x), x);
    }

    public VgPosition get(int i) {
        return new VgPosition(libVisioMoveJNI.VgPositionVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgPosition val) {
        libVisioMoveJNI.VgPositionVector_set(this.swigCPtr, this, i, VgPosition.getCPtr(val), val);
    }
}
