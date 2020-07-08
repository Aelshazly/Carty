package com.visioglobe.libVisioMove;

public class VgPlaceIconDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPlaceIconDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPlaceIconDescriptor obj) {
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
                libVisioMoveJNI.delete_VgPlaceIconDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPlaceIconDescriptor() {
        this(libVisioMoveJNI.new_VgPlaceIconDescriptor__SWIG_0(), true);
    }

    public VgPlaceIconDescriptor(VgPlaceIconDescriptor pDescriptor) {
        this(libVisioMoveJNI.new_VgPlaceIconDescriptor__SWIG_1(getCPtr(pDescriptor), pDescriptor), true);
    }

    public void setMScale(double value) {
        libVisioMoveJNI.VgPlaceIconDescriptor_mScale_set(this.swigCPtr, this, value);
    }

    public double getMScale() {
        return libVisioMoveJNI.VgPlaceIconDescriptor_mScale_get(this.swigCPtr, this);
    }

    public void setMIcon(VgITextureRefPtr value) {
        libVisioMoveJNI.VgPlaceIconDescriptor_mIcon_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMIcon() {
        long cPtr = libVisioMoveJNI.VgPlaceIconDescriptor_mIcon_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }
}
