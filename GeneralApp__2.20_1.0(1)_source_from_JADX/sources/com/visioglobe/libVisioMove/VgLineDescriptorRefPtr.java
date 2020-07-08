package com.visioglobe.libVisioMove;

public class VgLineDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLineDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLineDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLineDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLineDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgLineDescriptorRefPtr__SWIG_0(), true);
    }

    public VgLineDescriptorRefPtr(VgLineDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgLineDescriptorRefPtr__SWIG_1(VgLineDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgLineDescriptorRefPtr(VgLineDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLineDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLineDescriptorRefPtr getNull() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorRefPtr_getNull(), true);
    }

    public VgLineDescriptorRefPtr set(VgLineDescriptor pPointer) {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorRefPtr_set(this.swigCPtr, this, VgLineDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgLineDescriptor __ref__() {
        return new VgLineDescriptor(libVisioMoveJNI.VgLineDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLineDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLineDescriptor(cPtr, false);
    }

    public VgLineDescriptor get() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLineDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgLineDescriptorRefPtr create() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public VgLineDescriptorRefPtr copy() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorRefPtr_copy(this.swigCPtr, this), true);
    }

    public void setMPositions(VgPositionVector value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mPositions_set(this.swigCPtr, this, VgPositionVector.getCPtr(value), value);
    }

    public VgPositionVector getMPositions() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorRefPtr_mPositions_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public void setMColors(VgColorVector value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mColors_set(this.swigCPtr, this, VgColorVector.getCPtr(value), value);
    }

    public VgColorVector getMColors() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorRefPtr_mColors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColorVector(cPtr, false);
    }

    public void setMWidths(VgDoubleVector value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mWidths_set(this.swigCPtr, this, VgDoubleVector.getCPtr(value), value);
    }

    public VgDoubleVector getMWidths() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorRefPtr_mWidths_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDoubleVector(cPtr, false);
    }

    public void setMTexture(VgITextureRefPtr value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mTexture_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMTexture() {
        long cPtr = libVisioMoveJNI.VgLineDescriptorRefPtr_mTexture_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public void setMTextureSize(float value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mTextureSize_set(this.swigCPtr, this, value);
    }

    public float getMTextureSize() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mTextureSize_get(this.swigCPtr, this);
    }

    public void setMTextureAnimationSpeed(float value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mTextureAnimationSpeed_set(this.swigCPtr, this, value);
    }

    public float getMTextureAnimationSpeed() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mTextureAnimationSpeed_get(this.swigCPtr, this);
    }

    public void setMLineType(VgLineType value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mLineType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgLineType getMLineType() {
        return VgLineType.swigToEnum(libVisioMoveJNI.VgLineDescriptorRefPtr_mLineType_get(this.swigCPtr, this));
    }

    public void setMAltitudeMode(VgAltitudeMode value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mAltitudeMode_set(this.swigCPtr, this, value.swigValue());
    }

    public VgAltitudeMode getMAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgLineDescriptorRefPtr_mAltitudeMode_get(this.swigCPtr, this));
    }

    public void setMMaxCornerRadius(double value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mMaxCornerRadius_set(this.swigCPtr, this, value);
    }

    public double getMMaxCornerRadius() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mMaxCornerRadius_get(this.swigCPtr, this);
    }

    public void setMHaveCaps(boolean value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mHaveCaps_set(this.swigCPtr, this, value);
    }

    public boolean getMHaveCaps() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mHaveCaps_get(this.swigCPtr, this);
    }

    public void setMID(String value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mID_set(this.swigCPtr, this, value);
    }

    public String getMID() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mID_get(this.swigCPtr, this);
    }

    public void setMMinTesselationDist(float value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mMinTesselationDist_set(this.swigCPtr, this, value);
    }

    public float getMMinTesselationDist() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mMinTesselationDist_get(this.swigCPtr, this);
    }

    public VgLineDescriptorRefPtr clone() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptorRefPtr_clone(this.swigCPtr, this), true);
    }

    public void setMVisibilityRampStartVisible(double value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampStartVisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampStartVisible() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampStartVisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampFullyVisible(double value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampFullyVisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampFullyVisible() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampFullyVisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampStartInvisible(double value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampStartInvisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampStartInvisible() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampStartInvisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampFullyInvisible(double value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampFullyInvisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampFullyInvisible() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mVisibilityRampFullyInvisible_get(this.swigCPtr, this);
    }

    public void setMZIndex(int value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mZIndex_set(this.swigCPtr, this, value);
    }

    public int getMZIndex() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mZIndex_get(this.swigCPtr, this);
    }

    public void setMDrawOnTop(boolean value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mDrawOnTop_set(this.swigCPtr, this, value);
    }

    public boolean getMDrawOnTop() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mDrawOnTop_get(this.swigCPtr, this);
    }

    public void setMScale(float value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mScale_set(this.swigCPtr, this, value);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mScale_get(this.swigCPtr, this);
    }

    public void setMNotifyPOISelectedOnClick(boolean value) {
        libVisioMoveJNI.VgLineDescriptorRefPtr_mNotifyPOISelectedOnClick_set(this.swigCPtr, this, value);
    }

    public boolean getMNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_mNotifyPOISelectedOnClick_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLineDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLineDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
