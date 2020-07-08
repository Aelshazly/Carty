package com.visioglobe.libVisioMove;

public class VgLinkDescriptor extends VgReferenced {
    private long swigCPtr;

    protected VgLinkDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLinkDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLinkDescriptor obj) {
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
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public static VgLinkDescriptorRefPtr create() {
        return new VgLinkDescriptorRefPtr(libVisioMoveJNI.VgLinkDescriptor_create(), true);
    }

    public VgLinkDescriptor() {
        this(libVisioMoveJNI.new_VgLinkDescriptor__SWIG_0(), true);
    }

    public VgLinkDescriptor(VgPosition pSourcePosition, VgColor pSourceColor, VgPosition pTargetPosition, VgColor pTargetColor, VgITextureRefPtr pTexture, float pWidth, float pTextureRatio, float pAnimationSpeed) {
        this(libVisioMoveJNI.new_VgLinkDescriptor__SWIG_1(VgPosition.getCPtr(pSourcePosition), pSourcePosition, VgColor.getCPtr(pSourceColor), pSourceColor, VgPosition.getCPtr(pTargetPosition), pTargetPosition, VgColor.getCPtr(pTargetColor), pTargetColor, VgITextureRefPtr.getCPtr(pTexture), pTexture, pWidth, pTextureRatio, pAnimationSpeed), true);
    }

    public VgLinkDescriptor(VgLinkDescriptor pOther) {
        this(libVisioMoveJNI.new_VgLinkDescriptor__SWIG_2(getCPtr(pOther), pOther), true);
    }

    public void setMSourcePosition(VgPosition value) {
        libVisioMoveJNI.VgLinkDescriptor_mSourcePosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMSourcePosition() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptor_mSourcePosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMSourceColor(VgColor value) {
        libVisioMoveJNI.VgLinkDescriptor_mSourceColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMSourceColor() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptor_mSourceColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMTargetPosition(VgPosition value) {
        libVisioMoveJNI.VgLinkDescriptor_mTargetPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMTargetPosition() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptor_mTargetPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMTargetColor(VgColor value) {
        libVisioMoveJNI.VgLinkDescriptor_mTargetColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMTargetColor() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptor_mTargetColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMTexture(VgITextureRefPtr value) {
        libVisioMoveJNI.VgLinkDescriptor_mTexture_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMTexture() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptor_mTexture_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public void setMWidth(float value) {
        libVisioMoveJNI.VgLinkDescriptor_mWidth_set(this.swigCPtr, this, value);
    }

    public float getMWidth() {
        return libVisioMoveJNI.VgLinkDescriptor_mWidth_get(this.swigCPtr, this);
    }

    public void setMTextureRatio(float value) {
        libVisioMoveJNI.VgLinkDescriptor_mTextureRatio_set(this.swigCPtr, this, value);
    }

    public float getMTextureRatio() {
        return libVisioMoveJNI.VgLinkDescriptor_mTextureRatio_get(this.swigCPtr, this);
    }

    public void setMAnimationSpeed(float value) {
        libVisioMoveJNI.VgLinkDescriptor_mAnimationSpeed_set(this.swigCPtr, this, value);
    }

    public float getMAnimationSpeed() {
        return libVisioMoveJNI.VgLinkDescriptor_mAnimationSpeed_get(this.swigCPtr, this);
    }
}
