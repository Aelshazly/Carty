package com.visioglobe.libVisioMove;

public class VgPlaceColorDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPlaceColorDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPlaceColorDescriptor obj) {
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
                libVisioMoveJNI.delete_VgPlaceColorDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPlaceColorDescriptor() {
        this(libVisioMoveJNI.new_VgPlaceColorDescriptor__SWIG_0(), true);
    }

    public VgPlaceColorDescriptor(VgPlaceColorDescriptor pDescriptor) {
        this(libVisioMoveJNI.new_VgPlaceColorDescriptor__SWIG_1(getCPtr(pDescriptor), pDescriptor), true);
    }

    public void setMBottomColor(VgColor value) {
        libVisioMoveJNI.VgPlaceColorDescriptor_mBottomColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMBottomColor() {
        long cPtr = libVisioMoveJNI.VgPlaceColorDescriptor_mBottomColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMTopColor(VgColor value) {
        libVisioMoveJNI.VgPlaceColorDescriptor_mTopColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMTopColor() {
        long cPtr = libVisioMoveJNI.VgPlaceColorDescriptor_mTopColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }
}
