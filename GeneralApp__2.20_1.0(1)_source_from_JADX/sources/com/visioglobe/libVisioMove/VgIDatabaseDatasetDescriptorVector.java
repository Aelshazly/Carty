package com.visioglobe.libVisioMove;

public class VgIDatabaseDatasetDescriptorVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIDatabaseDatasetDescriptorVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIDatabaseDatasetDescriptorVector obj) {
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
                libVisioMoveJNI.delete_VgIDatabaseDatasetDescriptorVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIDatabaseDatasetDescriptorVector() {
        this(libVisioMoveJNI.new_VgIDatabaseDatasetDescriptorVector__SWIG_0(), true);
    }

    public VgIDatabaseDatasetDescriptorVector(long n) {
        this(libVisioMoveJNI.new_VgIDatabaseDatasetDescriptorVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_clear(this.swigCPtr, this);
    }

    public void add(VgIDatabaseDatasetDescriptor x) {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_add(this.swigCPtr, this, VgIDatabaseDatasetDescriptor.getCPtr(x), x);
    }

    public VgIDatabaseDatasetDescriptor get(int i) {
        return new VgIDatabaseDatasetDescriptor(libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgIDatabaseDatasetDescriptor val) {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptorVector_set(this.swigCPtr, this, i, VgIDatabaseDatasetDescriptor.getCPtr(val), val);
    }
}
