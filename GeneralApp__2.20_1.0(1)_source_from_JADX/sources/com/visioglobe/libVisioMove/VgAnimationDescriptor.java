package com.visioglobe.libVisioMove;

public class VgAnimationDescriptor extends VgReferenced {
    private long swigCPtr;

    protected VgAnimationDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgAnimationDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimationDescriptor obj) {
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

    public static VgAnimationDescriptorRefPtr create() {
        return new VgAnimationDescriptorRefPtr(libVisioMoveJNI.VgAnimationDescriptor_create(), true);
    }

    public void setMFunctorDescriptors(VgFunctorDescriptorMap value) {
        libVisioMoveJNI.VgAnimationDescriptor_mFunctorDescriptors_set(this.swigCPtr, this, VgFunctorDescriptorMap.getCPtr(value), value);
    }

    public VgFunctorDescriptorMap getMFunctorDescriptors() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptor_mFunctorDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptorMap(cPtr, false);
    }

    public void setMDuration(float value) {
        libVisioMoveJNI.VgAnimationDescriptor_mDuration_set(this.swigCPtr, this, value);
    }

    public float getMDuration() {
        return libVisioMoveJNI.VgAnimationDescriptor_mDuration_get(this.swigCPtr, this);
    }

    public void setMLoopMode(long value) {
        libVisioMoveJNI.VgAnimationDescriptor_mLoopMode_set(this.swigCPtr, this, value);
    }

    public long getMLoopMode() {
        return libVisioMoveJNI.VgAnimationDescriptor_mLoopMode_get(this.swigCPtr, this);
    }

    public void setMCallback(VgIAnimationCallbackRefPtr value) {
        libVisioMoveJNI.VgAnimationDescriptor_mCallback_set(this.swigCPtr, this, VgIAnimationCallbackRefPtr.getCPtr(value), value);
    }

    public VgIAnimationCallbackRefPtr getMCallback() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptor_mCallback_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallbackRefPtr(cPtr, false);
    }
}
