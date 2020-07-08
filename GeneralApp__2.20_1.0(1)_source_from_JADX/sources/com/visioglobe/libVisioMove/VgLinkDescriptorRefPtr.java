package com.visioglobe.libVisioMove;

public class VgLinkDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLinkDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLinkDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLinkDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLinkDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgLinkDescriptorRefPtr__SWIG_0(), true);
    }

    public VgLinkDescriptorRefPtr(VgLinkDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgLinkDescriptorRefPtr__SWIG_1(VgLinkDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgLinkDescriptorRefPtr(VgLinkDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLinkDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLinkDescriptorRefPtr getNull() {
        return new VgLinkDescriptorRefPtr(libVisioMoveJNI.VgLinkDescriptorRefPtr_getNull(), true);
    }

    public VgLinkDescriptorRefPtr set(VgLinkDescriptor pPointer) {
        return new VgLinkDescriptorRefPtr(libVisioMoveJNI.VgLinkDescriptorRefPtr_set(this.swigCPtr, this, VgLinkDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgLinkDescriptor __ref__() {
        return new VgLinkDescriptor(libVisioMoveJNI.VgLinkDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLinkDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLinkDescriptor(cPtr, false);
    }

    public VgLinkDescriptor get() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLinkDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLinkDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgLinkDescriptorRefPtr create() {
        return new VgLinkDescriptorRefPtr(libVisioMoveJNI.VgLinkDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMSourcePosition(VgPosition value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mSourcePosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMSourcePosition() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr_mSourcePosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMSourceColor(VgColor value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mSourceColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMSourceColor() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr_mSourceColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMTargetPosition(VgPosition value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mTargetPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMTargetPosition() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr_mTargetPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMTargetColor(VgColor value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mTargetColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMTargetColor() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr_mTargetColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMTexture(VgITextureRefPtr value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mTexture_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMTexture() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorRefPtr_mTexture_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public void setMWidth(float value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mWidth_set(this.swigCPtr, this, value);
    }

    public float getMWidth() {
        return libVisioMoveJNI.VgLinkDescriptorRefPtr_mWidth_get(this.swigCPtr, this);
    }

    public void setMTextureRatio(float value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mTextureRatio_set(this.swigCPtr, this, value);
    }

    public float getMTextureRatio() {
        return libVisioMoveJNI.VgLinkDescriptorRefPtr_mTextureRatio_get(this.swigCPtr, this);
    }

    public void setMAnimationSpeed(float value) {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_mAnimationSpeed_set(this.swigCPtr, this, value);
    }

    public float getMAnimationSpeed() {
        return libVisioMoveJNI.VgLinkDescriptorRefPtr_mAnimationSpeed_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLinkDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLinkDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLinkDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
