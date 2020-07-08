package com.visioglobe.libVisioMove;

public class VgPOIDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPOIDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPOIDescriptor obj) {
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
                libVisioMoveJNI.delete_VgPOIDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMLayerName(String value) {
        libVisioMoveJNI.VgPOIDescriptor_mLayerName_set(this.swigCPtr, this, value);
    }

    public String getMLayerName() {
        return libVisioMoveJNI.VgPOIDescriptor_mLayerName_get(this.swigCPtr, this);
    }

    public void setMCenter(VgPosition value) {
        libVisioMoveJNI.VgPOIDescriptor_mCenter_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMCenter() {
        long cPtr = libVisioMoveJNI.VgPOIDescriptor_mCenter_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMHeight(double value) {
        libVisioMoveJNI.VgPOIDescriptor_mHeight_set(this.swigCPtr, this, value);
    }

    public double getMHeight() {
        return libVisioMoveJNI.VgPOIDescriptor_mHeight_get(this.swigCPtr, this);
    }

    public void setMHeading(double value) {
        libVisioMoveJNI.VgPOIDescriptor_mHeading_set(this.swigCPtr, this, value);
    }

    public double getMHeading() {
        return libVisioMoveJNI.VgPOIDescriptor_mHeading_get(this.swigCPtr, this);
    }

    public void setMBoundingPositions(VgPositionVector value) {
        libVisioMoveJNI.VgPOIDescriptor_mBoundingPositions_set(this.swigCPtr, this, VgPositionVector.getCPtr(value), value);
    }

    public VgPositionVector getMBoundingPositions() {
        long cPtr = libVisioMoveJNI.VgPOIDescriptor_mBoundingPositions_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public VgPOIDescriptor() {
        this(libVisioMoveJNI.new_VgPOIDescriptor(), true);
    }
}
