package com.visioglobe.libVisioMove;

public class VgPointDescriptor extends VgGeometryDescriptor {
    private long swigCPtr;

    protected VgPointDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgPointDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPointDescriptor obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public static VgPointDescriptorRefPtr create() {
        return new VgPointDescriptorRefPtr(libVisioMoveJNI.VgPointDescriptor_create__SWIG_0(), true);
    }

    public static VgPointDescriptorRefPtr create(VgPointDescriptorRefPtr pOther) {
        return new VgPointDescriptorRefPtr(libVisioMoveJNI.VgPointDescriptor_create__SWIG_1(VgPointDescriptorRefPtr.getCPtr(pOther), pOther), true);
    }

    public void setMPosition(VgPosition value) {
        libVisioMoveJNI.VgPointDescriptor_mPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMPosition() {
        long cPtr = libVisioMoveJNI.VgPointDescriptor_mPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMAltitudeMode(VgAltitudeMode value) {
        libVisioMoveJNI.VgPointDescriptor_mAltitudeMode_set(this.swigCPtr, this, value.swigValue());
    }

    public VgAltitudeMode getMAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgPointDescriptor_mAltitudeMode_get(this.swigCPtr, this));
    }

    public void setMAnchorPosition(VgAnchorMode value) {
        libVisioMoveJNI.VgPointDescriptor_mAnchorPosition_set(this.swigCPtr, this, value.swigValue());
    }

    public VgAnchorMode getMAnchorPosition() {
        return VgAnchorMode.swigToEnum(libVisioMoveJNI.VgPointDescriptor_mAnchorPosition_get(this.swigCPtr, this));
    }

    public void setMGeometryConstantSizeDistance(float value) {
        libVisioMoveJNI.VgPointDescriptor_mGeometryConstantSizeDistance_set(this.swigCPtr, this, value);
    }

    public float getMGeometryConstantSizeDistance() {
        return libVisioMoveJNI.VgPointDescriptor_mGeometryConstantSizeDistance_get(this.swigCPtr, this);
    }

    public void setMHeading(float value) {
        libVisioMoveJNI.VgPointDescriptor_mHeading_set(this.swigCPtr, this, value);
    }

    public float getMHeading() {
        return libVisioMoveJNI.VgPointDescriptor_mHeading_get(this.swigCPtr, this);
    }

    public void setMPitch(float value) {
        libVisioMoveJNI.VgPointDescriptor_mPitch_set(this.swigCPtr, this, value);
    }

    public float getMPitch() {
        return libVisioMoveJNI.VgPointDescriptor_mPitch_get(this.swigCPtr, this);
    }

    public void setMRoll(float value) {
        libVisioMoveJNI.VgPointDescriptor_mRoll_set(this.swigCPtr, this, value);
    }

    public float getMRoll() {
        return libVisioMoveJNI.VgPointDescriptor_mRoll_get(this.swigCPtr, this);
    }

    public void setMForceFrontFace(boolean value) {
        libVisioMoveJNI.VgPointDescriptor_mForceFrontFace_set(this.swigCPtr, this, value);
    }

    public boolean getMForceFrontFace() {
        return libVisioMoveJNI.VgPointDescriptor_mForceFrontFace_get(this.swigCPtr, this);
    }

    public void setMHeadingOrientationType(VgOrientationType value) {
        libVisioMoveJNI.VgPointDescriptor_mHeadingOrientationType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMHeadingOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptor_mHeadingOrientationType_get(this.swigCPtr, this));
    }

    public void setMPitchOrientationType(VgOrientationType value) {
        libVisioMoveJNI.VgPointDescriptor_mPitchOrientationType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMPitchOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptor_mPitchOrientationType_get(this.swigCPtr, this));
    }

    public void setMRollOrientationType(VgOrientationType value) {
        libVisioMoveJNI.VgPointDescriptor_mRollOrientationType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMRollOrientationType() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgPointDescriptor_mRollOrientationType_get(this.swigCPtr, this));
    }

    public void setMMarkerDescriptors(VgMarkerDescriptorVector value) {
        libVisioMoveJNI.VgPointDescriptor_mMarkerDescriptors_set(this.swigCPtr, this, VgMarkerDescriptorVector.getCPtr(value), value);
    }

    public VgMarkerDescriptorVector getMMarkerDescriptors() {
        long cPtr = libVisioMoveJNI.VgPointDescriptor_mMarkerDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptorVector(cPtr, false);
    }

    public void setMID(String value) {
        libVisioMoveJNI.VgPointDescriptor_mID_set(this.swigCPtr, this, value);
    }

    public String getMID() {
        return libVisioMoveJNI.VgPointDescriptor_mID_get(this.swigCPtr, this);
    }

    public void setMSizePolicy(VgSizePolicy value) {
        libVisioMoveJNI.VgPointDescriptor_mSizePolicy_set(this.swigCPtr, this, value.swigValue());
    }

    public VgSizePolicy getMSizePolicy() {
        return VgSizePolicy.swigToEnum(libVisioMoveJNI.VgPointDescriptor_mSizePolicy_get(this.swigCPtr, this));
    }

    public void setMRectangleWidth(float value) {
        libVisioMoveJNI.VgPointDescriptor_mRectangleWidth_set(this.swigCPtr, this, value);
    }

    public float getMRectangleWidth() {
        return libVisioMoveJNI.VgPointDescriptor_mRectangleWidth_get(this.swigCPtr, this);
    }

    public void setMRectangleHeight(float value) {
        libVisioMoveJNI.VgPointDescriptor_mRectangleHeight_set(this.swigCPtr, this, value);
    }

    public float getMRectangleHeight() {
        return libVisioMoveJNI.VgPointDescriptor_mRectangleHeight_get(this.swigCPtr, this);
    }
}
