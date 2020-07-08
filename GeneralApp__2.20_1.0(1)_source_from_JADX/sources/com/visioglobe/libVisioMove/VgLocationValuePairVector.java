package com.visioglobe.libVisioMove;

public class VgLocationValuePairVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLocationValuePairVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLocationValuePairVector obj) {
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
                libVisioMoveJNI.delete_VgLocationValuePairVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLocationValuePairVector() {
        this(libVisioMoveJNI.new_VgLocationValuePairVector__SWIG_0(), true);
    }

    public VgLocationValuePairVector(long n) {
        this(libVisioMoveJNI.new_VgLocationValuePairVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgLocationValuePairVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgLocationValuePairVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgLocationValuePairVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgLocationValuePairVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgLocationValuePairVector_clear(this.swigCPtr, this);
    }

    public void add(VgLocationDoublePair x) {
        libVisioMoveJNI.VgLocationValuePairVector_add(this.swigCPtr, this, VgLocationDoublePair.getCPtr(x), x);
    }

    public VgLocationDoublePair get(int i) {
        return new VgLocationDoublePair(libVisioMoveJNI.VgLocationValuePairVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgLocationDoublePair val) {
        libVisioMoveJNI.VgLocationValuePairVector_set(this.swigCPtr, this, i, VgLocationDoublePair.getCPtr(val), val);
    }
}
