package com.visioglobe.libVisioMove;

public class VgIRoutingNodeRefPtrVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRoutingNodeRefPtrVector(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingNodeRefPtrVector obj) {
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
                libVisioMoveJNI.delete_VgIRoutingNodeRefPtrVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRoutingNodeRefPtrVector() {
        this(libVisioMoveJNI.new_VgIRoutingNodeRefPtrVector__SWIG_0(), true);
    }

    public VgIRoutingNodeRefPtrVector(long n) {
        this(libVisioMoveJNI.new_VgIRoutingNodeRefPtrVector__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtrVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtrVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgIRoutingNodeRefPtrVector_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtrVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgIRoutingNodeRefPtrVector_clear(this.swigCPtr, this);
    }

    public void add(VgIRoutingNodeRefPtr x) {
        libVisioMoveJNI.VgIRoutingNodeRefPtrVector_add(this.swigCPtr, this, VgIRoutingNodeRefPtr.getCPtr(x), x);
    }

    public VgIRoutingNodeRefPtr get(int i) {
        return new VgIRoutingNodeRefPtr(libVisioMoveJNI.VgIRoutingNodeRefPtrVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgIRoutingNodeRefPtr val) {
        libVisioMoveJNI.VgIRoutingNodeRefPtrVector_set(this.swigCPtr, this, i, VgIRoutingNodeRefPtr.getCPtr(val), val);
    }
}
