package com.visioglobe.libVisioMove;

public class VgLinkDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLinkDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLinkDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLinkDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLinkDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgLinkDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgLinkDescriptorConstRefPtr(VgLinkDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgLinkDescriptorConstRefPtr__SWIG_1(VgLinkDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgLinkDescriptorConstRefPtr(VgLinkDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLinkDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLinkDescriptorConstRefPtr getNull() {
        return new VgLinkDescriptorConstRefPtr(libVisioMoveJNI.VgLinkDescriptorConstRefPtr_getNull(), true);
    }

    public VgLinkDescriptorConstRefPtr set(VgLinkDescriptor pPointer) {
        return new VgLinkDescriptorConstRefPtr(libVisioMoveJNI.VgLinkDescriptorConstRefPtr_set(this.swigCPtr, this, VgLinkDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgLinkDescriptor __ref__() {
        return new VgLinkDescriptor(libVisioMoveJNI.VgLinkDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLinkDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLinkDescriptor(cPtr, false);
    }

    public VgLinkDescriptor get() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLinkDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLinkDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgPosition getMSourcePosition() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mSourcePosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public VgColor getMSourceColor() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mSourceColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public VgPosition getMTargetPosition() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mTargetPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public VgColor getMTargetColor() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mTargetColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public VgITextureRefPtr getMTexture() {
        long cPtr = libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mTexture_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public float getMWidth() {
        return libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mWidth_get(this.swigCPtr, this);
    }

    public float getMTextureRatio() {
        return libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mTextureRatio_get(this.swigCPtr, this);
    }

    public float getMAnimationSpeed() {
        return libVisioMoveJNI.VgLinkDescriptorConstRefPtr_mAnimationSpeed_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLinkDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLinkDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLinkDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
