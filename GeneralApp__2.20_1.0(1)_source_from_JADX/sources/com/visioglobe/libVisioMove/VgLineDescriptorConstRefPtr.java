package com.visioglobe.libVisioMove;

public class VgLineDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLineDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLineDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLineDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLineDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgLineDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgLineDescriptorConstRefPtr(VgLineDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgLineDescriptorConstRefPtr__SWIG_1(VgLineDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgLineDescriptorConstRefPtr(VgLineDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLineDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLineDescriptorConstRefPtr getNull() {
        return new VgLineDescriptorConstRefPtr(libVisioMoveJNI.VgLineDescriptorConstRefPtr_getNull(), true);
    }

    public VgLineDescriptorConstRefPtr set(VgLineDescriptor pPointer) {
        return new VgLineDescriptorConstRefPtr(libVisioMoveJNI.VgLineDescriptorConstRefPtr_set(this.swigCPtr, this, VgLineDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgLineDescriptor __ref__() {
        return new VgLineDescriptor(libVisioMoveJNI.VgLineDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLineDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLineDescriptor(cPtr, false);
    }

    public VgLineDescriptor get() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLineDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgLineDescriptorRefPtr copy() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorConstRefPtr_copy(this.swigCPtr, this), true);
    }

    public VgPositionVector getMPositions() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorConstRefPtr_mPositions_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public VgColorVector getMColors() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorConstRefPtr_mColors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColorVector(cPtr, false);
    }

    public VgDoubleVector getMWidths() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorConstRefPtr_mWidths_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDoubleVector(cPtr, false);
    }

    public VgITextureRefPtr getMTexture() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorConstRefPtr_mTexture_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public float getMTextureSize() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mTextureSize_get(this.swigCPtr, this);
    }

    public float getMTextureAnimationSpeed() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mTextureAnimationSpeed_get(this.swigCPtr, this);
    }

    public VgLineType getMLineType() {
        return VgLineType.swigToEnum(libVisioMoveJNI.VgLineDescriptorConstRefPtr_mLineType_get(this.swigCPtr, this));
    }

    public VgAltitudeMode getMAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgLineDescriptorConstRefPtr_mAltitudeMode_get(this.swigCPtr, this));
    }

    public double getMMaxCornerRadius() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mMaxCornerRadius_get(this.swigCPtr, this);
    }

    public boolean getMHaveCaps() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mHaveCaps_get(this.swigCPtr, this);
    }

    public String getMID() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mID_get(this.swigCPtr, this);
    }

    public float getMMinTesselationDist() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mMinTesselationDist_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampStartVisible() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mVisibilityRampStartVisible_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampFullyVisible() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mVisibilityRampFullyVisible_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampStartInvisible() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mVisibilityRampStartInvisible_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampFullyInvisible() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mVisibilityRampFullyInvisible_get(this.swigCPtr, this);
    }

    public int getMZIndex() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mZIndex_get(this.swigCPtr, this);
    }

    public boolean getMDrawOnTop() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mDrawOnTop_get(this.swigCPtr, this);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mScale_get(this.swigCPtr, this);
    }

    public boolean getMNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_mNotifyPOISelectedOnClick_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLineDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLineDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
