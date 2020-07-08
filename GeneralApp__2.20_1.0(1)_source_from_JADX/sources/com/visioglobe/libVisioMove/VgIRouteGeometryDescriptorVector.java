package com.visioglobe.libVisioMove;

public class VgIRouteGeometryDescriptorVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteGeometryDescriptorVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteGeometryDescriptorVector obj) {
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
                libVisioMoveJNI.delete_VgIRouteGeometryDescriptorVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteGeometryDescriptorVector() {
        this(libVisioMoveJNI.new_VgIRouteGeometryDescriptorVector__SWIG_0(), true);
    }

    public VgIRouteGeometryDescriptorVector(long n) {
        this(libVisioMoveJNI.new_VgIRouteGeometryDescriptorVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgIRouteGeometryDescriptorVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgIRouteGeometryDescriptorVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgIRouteGeometryDescriptorVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgIRouteGeometryDescriptorVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgIRouteGeometryDescriptorVector_clear(this.swigCPtr, this);
    }

    public void add(VgIRouteGeometryDescriptor x) {
        libVisioMoveJNI.VgIRouteGeometryDescriptorVector_add(this.swigCPtr, this, VgIRouteGeometryDescriptor.getCPtr(x), x);
    }

    public VgIRouteGeometryDescriptor get(int i) {
        return new VgIRouteGeometryDescriptor(libVisioMoveJNI.VgIRouteGeometryDescriptorVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgIRouteGeometryDescriptor val) {
        libVisioMoveJNI.VgIRouteGeometryDescriptorVector_set(this.swigCPtr, this, i, VgIRouteGeometryDescriptor.getCPtr(val), val);
    }
}
