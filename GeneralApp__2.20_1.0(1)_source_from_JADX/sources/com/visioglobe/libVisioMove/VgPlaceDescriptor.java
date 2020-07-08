package com.visioglobe.libVisioMove;

public class VgPlaceDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPlaceDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPlaceDescriptor obj) {
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
                libVisioMoveJNI.delete_VgPlaceDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPlaceDescriptor() {
        this(libVisioMoveJNI.new_VgPlaceDescriptor(), true);
    }

    public void setMDataset(String value) {
        libVisioMoveJNI.VgPlaceDescriptor_mDataset_set(this.swigCPtr, this, value);
    }

    public String getMDataset() {
        return libVisioMoveJNI.VgPlaceDescriptor_mDataset_get(this.swigCPtr, this);
    }

    public void setMLayerName(String value) {
        libVisioMoveJNI.VgPlaceDescriptor_mLayerName_set(this.swigCPtr, this, value);
    }

    public String getMLayerName() {
        return libVisioMoveJNI.VgPlaceDescriptor_mLayerName_get(this.swigCPtr, this);
    }

    public void setMViewpoint(VgIViewPoint value) {
        libVisioMoveJNI.VgPlaceDescriptor_mViewpoint_set(this.swigCPtr, this, VgIViewPoint.getCPtr(value), value);
    }

    public VgIViewPoint getMViewpoint() {
        long cPtr = libVisioMoveJNI.VgPlaceDescriptor_mViewpoint_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIViewPoint(cPtr, false);
    }
}
