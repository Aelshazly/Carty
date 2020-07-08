package com.visioglobe.libVisioMove;

public class VgNearPlaceVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgNearPlaceVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgNearPlaceVector obj) {
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
                libVisioMoveJNI.delete_VgNearPlaceVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgNearPlaceVector() {
        this(libVisioMoveJNI.new_VgNearPlaceVector__SWIG_0(), true);
    }

    public VgNearPlaceVector(long n) {
        this(libVisioMoveJNI.new_VgNearPlaceVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgNearPlaceVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgNearPlaceVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgNearPlaceVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgNearPlaceVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgNearPlaceVector_clear(this.swigCPtr, this);
    }

    public void add(VgNearPlace x) {
        libVisioMoveJNI.VgNearPlaceVector_add(this.swigCPtr, this, VgNearPlace.getCPtr(x), x);
    }

    public VgNearPlace get(int i) {
        return new VgNearPlace(libVisioMoveJNI.VgNearPlaceVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgNearPlace val) {
        libVisioMoveJNI.VgNearPlaceVector_set(this.swigCPtr, this, i, VgNearPlace.getCPtr(val), val);
    }
}
