package com.visioglobe.libVisioMove;

public class VgMarkerDescriptorVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMarkerDescriptorVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarkerDescriptorVector obj) {
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
                libVisioMoveJNI.delete_VgMarkerDescriptorVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMarkerDescriptorVector() {
        this(libVisioMoveJNI.new_VgMarkerDescriptorVector__SWIG_0(), true);
    }

    public VgMarkerDescriptorVector(long n) {
        this(libVisioMoveJNI.new_VgMarkerDescriptorVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgMarkerDescriptorVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgMarkerDescriptorVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgMarkerDescriptorVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgMarkerDescriptorVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgMarkerDescriptorVector_clear(this.swigCPtr, this);
    }

    public void add(VgMarkerDescriptorRefPtr x) {
        libVisioMoveJNI.VgMarkerDescriptorVector_add(this.swigCPtr, this, VgMarkerDescriptorRefPtr.getCPtr(x), x);
    }

    public VgMarkerDescriptorRefPtr get(int i) {
        return new VgMarkerDescriptorRefPtr(libVisioMoveJNI.VgMarkerDescriptorVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgMarkerDescriptorRefPtr val) {
        libVisioMoveJNI.VgMarkerDescriptorVector_set(this.swigCPtr, this, i, VgMarkerDescriptorRefPtr.getCPtr(val), val);
    }
}
