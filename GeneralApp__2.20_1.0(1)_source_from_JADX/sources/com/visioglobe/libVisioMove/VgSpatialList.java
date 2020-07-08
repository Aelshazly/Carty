package com.visioglobe.libVisioMove;

public class VgSpatialList {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSpatialList(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSpatialList obj) {
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
                libVisioMoveJNI.delete_VgSpatialList(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSpatialList() {
        this(libVisioMoveJNI.new_VgSpatialList(), true);
    }

    public long size() {
        return libVisioMoveJNI.VgSpatialList_size(this.swigCPtr, this);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgSpatialList_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgSpatialList_clear(this.swigCPtr, this);
    }

    public void add(VgSpatialRefPtr x) {
        libVisioMoveJNI.VgSpatialList_add(this.swigCPtr, this, VgSpatialRefPtr.getCPtr(x), x);
    }

    public VgSpatialRefPtr get(int i) {
        return new VgSpatialRefPtr(libVisioMoveJNI.VgSpatialList_get(this.swigCPtr, this, i), false);
    }
}
