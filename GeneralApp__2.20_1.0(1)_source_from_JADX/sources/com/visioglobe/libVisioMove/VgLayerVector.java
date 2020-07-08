package com.visioglobe.libVisioMove;

public class VgLayerVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayerVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerVector obj) {
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
                libVisioMoveJNI.delete_VgLayerVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLayerVector() {
        this(libVisioMoveJNI.new_VgLayerVector__SWIG_0(), true);
    }

    public VgLayerVector(long n) {
        this(libVisioMoveJNI.new_VgLayerVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgLayerVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgLayerVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgLayerVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgLayerVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgLayerVector_clear(this.swigCPtr, this);
    }

    public void add(VgLayerRefPtr x) {
        libVisioMoveJNI.VgLayerVector_add(this.swigCPtr, this, VgLayerRefPtr.getCPtr(x), x);
    }

    public VgLayerRefPtr get(int i) {
        return new VgLayerRefPtr(libVisioMoveJNI.VgLayerVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgLayerRefPtr val) {
        libVisioMoveJNI.VgLayerVector_set(this.swigCPtr, this, i, VgLayerRefPtr.getCPtr(val), val);
    }
}
