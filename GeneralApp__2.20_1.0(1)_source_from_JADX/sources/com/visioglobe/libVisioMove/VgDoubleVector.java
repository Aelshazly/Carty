package com.visioglobe.libVisioMove;

public class VgDoubleVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgDoubleVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDoubleVector obj) {
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
                libVisioMoveJNI.delete_VgDoubleVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgDoubleVector() {
        this(libVisioMoveJNI.new_VgDoubleVector__SWIG_0(), true);
    }

    public VgDoubleVector(long n) {
        this(libVisioMoveJNI.new_VgDoubleVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgDoubleVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgDoubleVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgDoubleVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgDoubleVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgDoubleVector_clear(this.swigCPtr, this);
    }

    public void add(double x) {
        libVisioMoveJNI.VgDoubleVector_add(this.swigCPtr, this, x);
    }

    public double get(int i) {
        return libVisioMoveJNI.VgDoubleVector_get(this.swigCPtr, this, i);
    }

    public void set(int i, double val) {
        libVisioMoveJNI.VgDoubleVector_set(this.swigCPtr, this, i, val);
    }
}
