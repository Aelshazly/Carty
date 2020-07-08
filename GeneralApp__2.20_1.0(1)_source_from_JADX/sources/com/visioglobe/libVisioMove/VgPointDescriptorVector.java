package com.visioglobe.libVisioMove;

public class VgPointDescriptorVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPointDescriptorVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPointDescriptorVector obj) {
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
                libVisioMoveJNI.delete_VgPointDescriptorVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPointDescriptorVector() {
        this(libVisioMoveJNI.new_VgPointDescriptorVector__SWIG_0(), true);
    }

    public VgPointDescriptorVector(long n) {
        this(libVisioMoveJNI.new_VgPointDescriptorVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgPointDescriptorVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgPointDescriptorVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgPointDescriptorVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgPointDescriptorVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgPointDescriptorVector_clear(this.swigCPtr, this);
    }

    public void add(VgPointDescriptorRefPtr x) {
        libVisioMoveJNI.VgPointDescriptorVector_add(this.swigCPtr, this, VgPointDescriptorRefPtr.getCPtr(x), x);
    }

    public VgPointDescriptorRefPtr get(int i) {
        return new VgPointDescriptorRefPtr(libVisioMoveJNI.VgPointDescriptorVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgPointDescriptorRefPtr val) {
        libVisioMoveJNI.VgPointDescriptorVector_set(this.swigCPtr, this, i, VgPointDescriptorRefPtr.getCPtr(val), val);
    }
}
