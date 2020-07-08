package com.visioglobe.libVisioMove;

public class VgLineDescriptorVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLineDescriptorVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLineDescriptorVector obj) {
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
                libVisioMoveJNI.delete_VgLineDescriptorVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLineDescriptorVector() {
        this(libVisioMoveJNI.new_VgLineDescriptorVector__SWIG_0(), true);
    }

    public VgLineDescriptorVector(long n) {
        this(libVisioMoveJNI.new_VgLineDescriptorVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgLineDescriptorVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgLineDescriptorVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgLineDescriptorVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgLineDescriptorVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgLineDescriptorVector_clear(this.swigCPtr, this);
    }

    public void add(VgLineDescriptorRefPtr x) {
        libVisioMoveJNI.VgLineDescriptorVector_add(this.swigCPtr, this, VgLineDescriptorRefPtr.getCPtr(x), x);
    }

    public VgLineDescriptorRefPtr get(int i) {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgLineDescriptorRefPtr val) {
        libVisioMoveJNI.VgLineDescriptorVector_set(this.swigCPtr, this, i, VgLineDescriptorRefPtr.getCPtr(val), val);
    }
}
