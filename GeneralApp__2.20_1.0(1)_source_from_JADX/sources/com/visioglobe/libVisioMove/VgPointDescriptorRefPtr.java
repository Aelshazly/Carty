package com.visioglobe.libVisioMove;

public class VgPointDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPointDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPointDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgPointDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPointDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgPointDescriptorRefPtr__SWIG_0(), true);
    }

    public VgPointDescriptorRefPtr(VgPointDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgPointDescriptorRefPtr__SWIG_1(VgPointDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgPointDescriptorRefPtr(VgPointDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgPointDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgPointDescriptorRefPtr getNull() {
        return new VgPointDescriptorRefPtr(libVisioMoveJNI.VgPointDescriptorRefPtr_getNull(), true);
    }

    public VgPointDescriptorRefPtr set(VgPointDescriptor pPointer) {
        return new VgPointDescriptorRefPtr(libVisioMoveJNI.VgPointDescriptorRefPtr_set(this.swigCPtr, this, VgPointDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgPointDescriptor __ref__() {
        return new VgPointDescriptor(libVisioMoveJNI.VgPointDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgPointDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPointDescriptor(cPtr, false);
    }

    public VgPointDescriptor get() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPointDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgPointDescriptorRefPtr create() {
        return new VgPointDescriptorRefPtr(libVisioMoveJNI.VgPointDescriptorRefPtr_create__SWIG_0(this.swigCPtr, this), true);
    }

    public void setMPosition(VgPosition value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMPosition() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorRefPtr_mPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMAltitudeMode(VgAltitudeMode value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mAltitudeMode_set(this.swigCPtr, this, value.swigValue());
    }

    public VgAltitudeMode getMAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgPointDescriptorRefPtr_mAltitudeMode_get(this.swigCPtr, this));
    }

    public void setMAnchorPosition(VgAnchorMode value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mAnchorPosition_set(this.swigCPtr, this, value.swigValue());
    }

    public VgAnchorMode getMAnchorPosition() {
        return VgAnchorMode.swigToEnum(libVisioMoveJNI.VgPointDescriptorRefPtr_mAnchorPosition_get(this.swigCPtr, this));
    }

    public void setMGeometryConstantSizeDistance(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mGeometryConstantSizeDistance_set(this.swigCPtr, this, value);
    }

    public float getMGeometryConstantSizeDistance() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mGeometryConstantSizeDistance_get(this.swigCPtr, this);
    }

    public void setMHeading(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mHeading_set(this.swigCPtr, this, value);
    }

    public float getMHeading() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mHeading_get(this.swigCPtr, this);
    }

    public void setMPitch(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mPitch_set(this.swigCPtr, this, value);
    }

    public float getMPitch() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mPitch_get(this.swigCPtr, this);
    }

    public void setMRoll(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mRoll_set(this.swigCPtr, this, value);
    }

    public float getMRoll() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mRoll_get(this.swigCPtr, this);
    }

    public void setMForceFrontFace(boolean value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mForceFrontFace_set(this.swigCPtr, this, value);
    }

    public boolean getMForceFrontFace() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mForceFrontFace_get(this.swigCPtr, this);
    }

    public void setMHeadingOrientationType(VgOrientationType value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mHeadingOrientationType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMHeadingOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptorRefPtr_mHeadingOrientationType_get(this.swigCPtr, this));
    }

    public void setMPitchOrientationType(VgOrientationType value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mPitchOrientationType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMPitchOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptorRefPtr_mPitchOrientationType_get(this.swigCPtr, this));
    }

    public void setMRollOrientationType(VgOrientationType value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mRollOrientationType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMRollOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptorRefPtr_mRollOrientationType_get(this.swigCPtr, this));
    }

    public void setMMarkerDescriptors(VgMarkerDescriptorVector value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mMarkerDescriptors_set(this.swigCPtr, this, VgMarkerDescriptorVector.getCPtr(value), value);
    }

    public VgMarkerDescriptorVector getMMarkerDescriptors() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorRefPtr_mMarkerDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptorVector(cPtr, false);
    }

    public void setMID(String value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mID_set(this.swigCPtr, this, value);
    }

    public String getMID() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mID_get(this.swigCPtr, this);
    }

    public void setMSizePolicy(VgSizePolicy value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mSizePolicy_set(this.swigCPtr, this, value.swigValue());
    }

    public VgSizePolicy getMSizePolicy() {
        return VgSizePolicy.swigToEnum(libVisioMoveJNI.VgPointDescriptorRefPtr_mSizePolicy_get(this.swigCPtr, this));
    }

    public void setMRectangleWidth(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mRectangleWidth_set(this.swigCPtr, this, value);
    }

    public float getMRectangleWidth() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mRectangleWidth_get(this.swigCPtr, this);
    }

    public void setMRectangleHeight(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mRectangleHeight_set(this.swigCPtr, this, value);
    }

    public float getMRectangleHeight() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mRectangleHeight_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampStartVisible(double value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampStartVisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampStartVisible() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampStartVisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampFullyVisible(double value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampFullyVisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampFullyVisible() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampFullyVisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampStartInvisible(double value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampStartInvisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampStartInvisible() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampStartInvisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampFullyInvisible(double value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampFullyInvisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampFullyInvisible() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mVisibilityRampFullyInvisible_get(this.swigCPtr, this);
    }

    public void setMZIndex(int value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mZIndex_set(this.swigCPtr, this, value);
    }

    public int getMZIndex() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mZIndex_get(this.swigCPtr, this);
    }

    public void setMDrawOnTop(boolean value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mDrawOnTop_set(this.swigCPtr, this, value);
    }

    public boolean getMDrawOnTop() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mDrawOnTop_get(this.swigCPtr, this);
    }

    public void setMScale(float value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mScale_set(this.swigCPtr, this, value);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mScale_get(this.swigCPtr, this);
    }

    public void setMNotifyPOISelectedOnClick(boolean value) {
        libVisioMoveJNI.VgPointDescriptorRefPtr_mNotifyPOISelectedOnClick_set(this.swigCPtr, this, value);
    }

    public boolean getMNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_mNotifyPOISelectedOnClick_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgPointDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgPointDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
