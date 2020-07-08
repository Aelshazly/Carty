package com.visioglobe.libVisioMove;

public class VgAnimationDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAnimationDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimationDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgAnimationDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAnimationDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgAnimationDescriptorRefPtr__SWIG_0(), true);
    }

    public VgAnimationDescriptorRefPtr(VgAnimationDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgAnimationDescriptorRefPtr__SWIG_1(VgAnimationDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgAnimationDescriptorRefPtr(VgAnimationDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgAnimationDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgAnimationDescriptorRefPtr getNull() {
        return new VgAnimationDescriptorRefPtr(libVisioMoveJNI.VgAnimationDescriptorRefPtr_getNull(), true);
    }

    public VgAnimationDescriptorRefPtr set(VgAnimationDescriptor pPointer) {
        return new VgAnimationDescriptorRefPtr(libVisioMoveJNI.VgAnimationDescriptorRefPtr_set(this.swigCPtr, this, VgAnimationDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgAnimationDescriptor __ref__() {
        return new VgAnimationDescriptor(libVisioMoveJNI.VgAnimationDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgAnimationDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimationDescriptor(cPtr, false);
    }

    public VgAnimationDescriptor get() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimationDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgAnimationDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgAnimationDescriptorRefPtr create() {
        return new VgAnimationDescriptorRefPtr(libVisioMoveJNI.VgAnimationDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMFunctorDescriptors(VgFunctorDescriptorMap value) {
        libVisioMoveJNI.VgAnimationDescriptorRefPtr_mFunctorDescriptors_set(this.swigCPtr, this, VgFunctorDescriptorMap.getCPtr(value), value);
    }

    public VgFunctorDescriptorMap getMFunctorDescriptors() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorRefPtr_mFunctorDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptorMap(cPtr, false);
    }

    public void setMDuration(float value) {
        libVisioMoveJNI.VgAnimationDescriptorRefPtr_mDuration_set(this.swigCPtr, this, value);
    }

    public float getMDuration() {
        return libVisioMoveJNI.VgAnimationDescriptorRefPtr_mDuration_get(this.swigCPtr, this);
    }

    public void setMLoopMode(long value) {
        libVisioMoveJNI.VgAnimationDescriptorRefPtr_mLoopMode_set(this.swigCPtr, this, value);
    }

    public long getMLoopMode() {
        return libVisioMoveJNI.VgAnimationDescriptorRefPtr_mLoopMode_get(this.swigCPtr, this);
    }

    public void setMCallback(VgIAnimationCallbackRefPtr value) {
        libVisioMoveJNI.VgAnimationDescriptorRefPtr_mCallback_set(this.swigCPtr, this, VgIAnimationCallbackRefPtr.getCPtr(value), value);
    }

    public VgIAnimationCallbackRefPtr getMCallback() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorRefPtr_mCallback_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallbackRefPtr(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgAnimationDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgAnimationDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgAnimationDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
