package com.visioglobe.libVisioMove;

public class VgStringVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgStringVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgStringVector obj) {
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
                libVisioMoveJNI.delete_VgStringVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgStringVector() {
        this(libVisioMoveJNI.new_VgStringVector__SWIG_0(), true);
    }

    public VgStringVector(long n) {
        this(libVisioMoveJNI.new_VgStringVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgStringVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgStringVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgStringVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgStringVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgStringVector_clear(this.swigCPtr, this);
    }

    public void add(String x) {
        libVisioMoveJNI.VgStringVector_add(this.swigCPtr, this, x);
    }

    public String get(int i) {
        return libVisioMoveJNI.VgStringVector_get(this.swigCPtr, this, i);
    }

    public void set(int i, String val) {
        libVisioMoveJNI.VgStringVector_set(this.swigCPtr, this, i, val);
    }
}
