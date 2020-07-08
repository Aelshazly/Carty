package com.visioglobe.libVisioMove;

public class VgIntVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIntVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIntVector obj) {
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
                libVisioMoveJNI.delete_VgIntVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIntVector() {
        this(libVisioMoveJNI.new_VgIntVector__SWIG_0(), true);
    }

    public VgIntVector(long n) {
        this(libVisioMoveJNI.new_VgIntVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgIntVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgIntVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgIntVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgIntVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgIntVector_clear(this.swigCPtr, this);
    }

    public void add(int x) {
        libVisioMoveJNI.VgIntVector_add(this.swigCPtr, this, x);
    }

    public int get(int i) {
        return libVisioMoveJNI.VgIntVector_get(this.swigCPtr, this, i);
    }

    public void set(int i, int val) {
        libVisioMoveJNI.VgIntVector_set(this.swigCPtr, this, i, val);
    }
}
