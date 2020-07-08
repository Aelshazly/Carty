package com.visioglobe.libVisioMove;

public class VgColorVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgColorVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgColorVector obj) {
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
                libVisioMoveJNI.delete_VgColorVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgColorVector() {
        this(libVisioMoveJNI.new_VgColorVector__SWIG_0(), true);
    }

    public VgColorVector(long n) {
        this(libVisioMoveJNI.new_VgColorVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgColorVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgColorVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgColorVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgColorVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgColorVector_clear(this.swigCPtr, this);
    }

    public void add(VgColor x) {
        libVisioMoveJNI.VgColorVector_add(this.swigCPtr, this, VgColor.getCPtr(x), x);
    }

    public VgColor get(int i) {
        return new VgColor(libVisioMoveJNI.VgColorVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgColor val) {
        libVisioMoveJNI.VgColorVector_set(this.swigCPtr, this, i, VgColor.getCPtr(val), val);
    }
}
