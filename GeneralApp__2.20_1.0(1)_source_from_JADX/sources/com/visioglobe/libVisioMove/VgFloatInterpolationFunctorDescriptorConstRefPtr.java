package com.visioglobe.libVisioMove;

public class VgFloatInterpolationFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFloatInterpolationFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFloatInterpolationFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgFloatInterpolationFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgFloatInterpolationFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgFloatInterpolationFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgFloatInterpolationFunctorDescriptorConstRefPtr(VgFloatInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgFloatInterpolationFunctorDescriptorConstRefPtr__SWIG_1(VgFloatInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgFloatInterpolationFunctorDescriptorConstRefPtr(VgFloatInterpolationFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgFloatInterpolationFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgFloatInterpolationFunctorDescriptorConstRefPtr getNull() {
        return new VgFloatInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgFloatInterpolationFunctorDescriptorConstRefPtr set(VgFloatInterpolationFunctorDescriptor pPointer) {
        return new VgFloatInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgFloatInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgFloatInterpolationFunctorDescriptor __ref__() {
        return new VgFloatInterpolationFunctorDescriptor(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgFloatInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFloatInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgFloatInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFloatInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public float getMStartValue() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_mStartValue_get(this.swigCPtr, this);
    }

    public float getMEndValue() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_mEndValue_get(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
