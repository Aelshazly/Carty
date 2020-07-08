package com.visioglobe.libVisioMove;

public class VgPointDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPointDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPointDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgPointDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPointDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgPointDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgPointDescriptorConstRefPtr(VgPointDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgPointDescriptorConstRefPtr__SWIG_1(VgPointDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgPointDescriptorConstRefPtr(VgPointDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgPointDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgPointDescriptorConstRefPtr getNull() {
        return new VgPointDescriptorConstRefPtr(libVisioMoveJNI.VgPointDescriptorConstRefPtr_getNull(), true);
    }

    public VgPointDescriptorConstRefPtr set(VgPointDescriptor pPointer) {
        return new VgPointDescriptorConstRefPtr(libVisioMoveJNI.VgPointDescriptorConstRefPtr_set(this.swigCPtr, this, VgPointDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgPointDescriptor __ref__() {
        return new VgPointDescriptor(libVisioMoveJNI.VgPointDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgPointDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPointDescriptor(cPtr, false);
    }

    public VgPointDescriptor get() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPointDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgPosition getMPosition() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorConstRefPtr_mPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public VgAltitudeMode getMAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgPointDescriptorConstRefPtr_mAltitudeMode_get(this.swigCPtr, this));
    }

    public VgAnchorMode getMAnchorPosition() {
        return VgAnchorMode.swigToEnum(libVisioMoveJNI.VgPointDescriptorConstRefPtr_mAnchorPosition_get(this.swigCPtr, this));
    }

    public float getMGeometryConstantSizeDistance() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mGeometryConstantSizeDistance_get(this.swigCPtr, this);
    }

    public float getMHeading() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mHeading_get(this.swigCPtr, this);
    }

    public float getMPitch() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mPitch_get(this.swigCPtr, this);
    }

    public float getMRoll() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mRoll_get(this.swigCPtr, this);
    }

    public boolean getMForceFrontFace() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mForceFrontFace_get(this.swigCPtr, this);
    }

    public VgOrientationType getMHeadingOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptorConstRefPtr_mHeadingOrientationType_get(this.swigCPtr, this));
    }

    public VgOrientationType getMPitchOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptorConstRefPtr_mPitchOrientationType_get(this.swigCPtr, this));
    }

    public VgOrientationType getMRollOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptorConstRefPtr_mRollOrientationType_get(this.swigCPtr, this));
    }

    public VgMarkerDescriptorVector getMMarkerDescriptors() {
        long cPtr = libVisioMoveJNI.VgPointDescriptorConstRefPtr_mMarkerDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptorVector(cPtr, false);
    }

    public String getMID() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mID_get(this.swigCPtr, this);
    }

    public VgSizePolicy getMSizePolicy() {
        return VgSizePolicy.swigToEnum(libVisioMoveJNI.VgPointDescriptorConstRefPtr_mSizePolicy_get(this.swigCPtr, this));
    }

    public float getMRectangleWidth() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mRectangleWidth_get(this.swigCPtr, this);
    }

    public float getMRectangleHeight() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mRectangleHeight_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampStartVisible() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mVisibilityRampStartVisible_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampFullyVisible() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mVisibilityRampFullyVisible_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampStartInvisible() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mVisibilityRampStartInvisible_get(this.swigCPtr, this);
    }

    public double getMVisibilityRampFullyInvisible() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mVisibilityRampFullyInvisible_get(this.swigCPtr, this);
    }

    public int getMZIndex() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mZIndex_get(this.swigCPtr, this);
    }

    public boolean getMDrawOnTop() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mDrawOnTop_get(this.swigCPtr, this);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mScale_get(this.swigCPtr, this);
    }

    public boolean getMNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_mNotifyPOISelectedOnClick_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgPointDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgPointDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
