package com.visioglobe.libVisioMove;

public class VgOrientationValuePairVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgOrientationValuePairVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgOrientationValuePairVector obj) {
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
                libVisioMoveJNI.delete_VgOrientationValuePairVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgOrientationValuePairVector() {
        this(libVisioMoveJNI.new_VgOrientationValuePairVector__SWIG_0(), true);
    }

    public VgOrientationValuePairVector(long n) {
        this(libVisioMoveJNI.new_VgOrientationValuePairVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgOrientationValuePairVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgOrientationValuePairVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgOrientationValuePairVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgOrientationValuePairVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgOrientationValuePairVector_clear(this.swigCPtr, this);
    }

    public void add(VgOrientationDoublePair x) {
        libVisioMoveJNI.VgOrientationValuePairVector_add(this.swigCPtr, this, VgOrientationDoublePair.getCPtr(x), x);
    }

    public VgOrientationDoublePair get(int i) {
        return new VgOrientationDoublePair(libVisioMoveJNI.VgOrientationValuePairVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgOrientationDoublePair val) {
        libVisioMoveJNI.VgOrientationValuePairVector_set(this.swigCPtr, this, i, VgOrientationDoublePair.getCPtr(val), val);
    }
}
