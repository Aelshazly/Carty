package com.visioglobe.libVisioMove;

public class VgAnimationDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAnimationDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimationDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgAnimationDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAnimationDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgAnimationDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgAnimationDescriptorConstRefPtr(VgAnimationDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgAnimationDescriptorConstRefPtr__SWIG_1(VgAnimationDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgAnimationDescriptorConstRefPtr(VgAnimationDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgAnimationDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgAnimationDescriptorConstRefPtr getNull() {
        return new VgAnimationDescriptorConstRefPtr(libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_getNull(), true);
    }

    public VgAnimationDescriptorConstRefPtr set(VgAnimationDescriptor pPointer) {
        return new VgAnimationDescriptorConstRefPtr(libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_set(this.swigCPtr, this, VgAnimationDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgAnimationDescriptor __ref__() {
        return new VgAnimationDescriptor(libVisioMoveJNI.VgAnimationDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgAnimationDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimationDescriptor(cPtr, false);
    }

    public VgAnimationDescriptor get() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimationDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgFunctorDescriptorMap getMFunctorDescriptors() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_mFunctorDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptorMap(cPtr, false);
    }

    public float getMDuration() {
        return libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_mDuration_get(this.swigCPtr, this);
    }

    public long getMLoopMode() {
        return libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_mLoopMode_get(this.swigCPtr, this);
    }

    public VgIAnimationCallbackRefPtr getMCallback() {
        long cPtr = libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_mCallback_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallbackRefPtr(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgAnimationDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
