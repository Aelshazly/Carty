package com.visioglobe.libVisioMove;

public class VgIDatabaseDatasetDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIDatabaseDatasetDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIDatabaseDatasetDescriptor obj) {
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
                libVisioMoveJNI.delete_VgIDatabaseDatasetDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIDatabaseDatasetDescriptor() {
        this(libVisioMoveJNI.new_VgIDatabaseDatasetDescriptor(), true);
    }

    public void setMName(String value) {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptor_mName_set(this.swigCPtr, this, value);
    }

    public String getMName() {
        return libVisioMoveJNI.VgIDatabaseDatasetDescriptor_mName_get(this.swigCPtr, this);
    }

    public void setMMinimum(VgPosition value) {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptor_mMinimum_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMMinimum() {
        long cPtr = libVisioMoveJNI.VgIDatabaseDatasetDescriptor_mMinimum_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMMaximum(VgPosition value) {
        libVisioMoveJNI.VgIDatabaseDatasetDescriptor_mMaximum_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMMaximum() {
        long cPtr = libVisioMoveJNI.VgIDatabaseDatasetDescriptor_mMaximum_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }
}
