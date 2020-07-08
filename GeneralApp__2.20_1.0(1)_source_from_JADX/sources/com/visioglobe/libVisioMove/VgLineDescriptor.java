package com.visioglobe.libVisioMove;

public class VgLineDescriptor extends VgGeometryDescriptor {
    private long swigCPtr;

    protected VgLineDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLineDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLineDescriptor obj) {
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

    public static VgLineDescriptorRefPtr create() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptor_create(), true);
    }

    public VgLineDescriptorRefPtr copy() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptor_copy(this.swigCPtr, this), true);
    }

    public void setMPositions(VgPositionVector value) {
        libVisioMoveJNI.VgLineDescriptor_mPositions_set(this.swigCPtr, this, VgPositionVector.getCPtr(value), value);
    }

    public VgPositionVector getMPositions() {
        long cPtr = libVisioMoveJNI.VgLineDescriptor_mPositions_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public void setMColors(VgColorVector value) {
        libVisioMoveJNI.VgLineDescriptor_mColors_set(this.swigCPtr, this, VgColorVector.getCPtr(value), value);
    }

    public VgColorVector getMColors() {
        long cPtr = libVisioMoveJNI.VgLineDescriptor_mColors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColorVector(cPtr, false);
    }

    public void setMWidths(VgDoubleVector value) {
        libVisioMoveJNI.VgLineDescriptor_mWidths_set(this.swigCPtr, this, VgDoubleVector.getCPtr(value), value);
    }

    public VgDoubleVector getMWidths() {
        long cPtr = libVisioMoveJNI.VgLineDescriptor_mWidths_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDoubleVector(cPtr, false);
    }

    public void setMTexture(VgITextureRefPtr value) {
        libVisioMoveJNI.VgLineDescriptor_mTexture_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMTexture() {
        long cPtr = libVisioMoveJNI.VgLineDescriptor_mTexture_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public void setMTextureSize(float value) {
        libVisioMoveJNI.VgLineDescriptor_mTextureSize_set(this.swigCPtr, this, value);
    }

    public float getMTextureSize() {
        return libVisioMoveJNI.VgLineDescriptor_mTextureSize_get(this.swigCPtr, this);
    }

    public void setMTextureAnimationSpeed(float value) {
        libVisioMoveJNI.VgLineDescriptor_mTextureAnimationSpeed_set(this.swigCPtr, this, value);
    }

    public float getMTextureAnimationSpeed() {
        return libVisioMoveJNI.VgLineDescriptor_mTextureAnimationSpeed_get(this.swigCPtr, this);
    }

    public void setMLineType(VgLineType value) {
        libVisioMoveJNI.VgLineDescriptor_mLineType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgLineType getMLineType() {
        return VgLineType.swigToEnum(libVisioMoveJNI.VgLineDescriptor_mLineType_get(this.swigCPtr, this));
    }

    public void setMAltitudeMode(VgAltitudeMode value) {
        libVisioMoveJNI.VgLineDescriptor_mAltitudeMode_set(this.swigCPtr, this, value.swigValue());
    }

    public VgAltitudeMode getMAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgLineDescriptor_mAltitudeMode_get(this.swigCPtr, this));
    }

    public void setMMaxCornerRadius(double value) {
        libVisioMoveJNI.VgLineDescriptor_mMaxCornerRadius_set(this.swigCPtr, this, value);
    }

    public double getMMaxCornerRadius() {
        return libVisioMoveJNI.VgLineDescriptor_mMaxCornerRadius_get(this.swigCPtr, this);
    }

    public void setMHaveCaps(boolean value) {
        libVisioMoveJNI.VgLineDescriptor_mHaveCaps_set(this.swigCPtr, this, value);
    }

    public boolean getMHaveCaps() {
        return libVisioMoveJNI.VgLineDescriptor_mHaveCaps_get(this.swigCPtr, this);
    }

    public void setMID(String value) {
        libVisioMoveJNI.VgLineDescriptor_mID_set(this.swigCPtr, this, value);
    }

    public String getMID() {
        return libVisioMoveJNI.VgLineDescriptor_mID_get(this.swigCPtr, this);
    }

    public void setMMinTesselationDist(float value) {
        libVisioMoveJNI.VgLineDescriptor_mMinTesselationDist_set(this.swigCPtr, this, value);
    }

    public float getMMinTesselationDist() {
        return libVisioMoveJNI.VgLineDescriptor_mMinTesselationDist_get(this.swigCPtr, this);
    }

    public VgLineDescriptorRefPtr clone() {
        return new VgLineDescriptorRefPtr(libVisioMoveJNI.VgLineDescriptor_clone(this.swigCPtr, this), true);
    }
}
