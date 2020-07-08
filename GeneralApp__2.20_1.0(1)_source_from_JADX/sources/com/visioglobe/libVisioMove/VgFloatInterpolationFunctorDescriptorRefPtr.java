package com.visioglobe.libVisioMove;

public class VgFloatInterpolationFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFloatInterpolationFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFloatInterpolationFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgFloatInterpolationFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgFloatInterpolationFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgFloatInterpolationFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgFloatInterpolationFunctorDescriptorRefPtr(VgFloatInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgFloatInterpolationFunctorDescriptorRefPtr__SWIG_1(VgFloatInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgFloatInterpolationFunctorDescriptorRefPtr(VgFloatInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgFloatInterpolationFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgFloatInterpolationFunctorDescriptorRefPtr getNull() {
        return new VgFloatInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgFloatInterpolationFunctorDescriptorRefPtr set(VgFloatInterpolationFunctorDescriptor pPointer) {
        return new VgFloatInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgFloatInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgFloatInterpolationFunctorDescriptor __ref__() {
        return new VgFloatInterpolationFunctorDescriptor(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgFloatInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFloatInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgFloatInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFloatInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgFloatInterpolationFunctorDescriptorRefPtr create() {
        return new VgFloatInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMStartValue(float value) {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mStartValue_set(this.swigCPtr, this, value);
    }

    public float getMStartValue() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mStartValue_get(this.swigCPtr, this);
    }

    public void setMEndValue(float value) {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mEndValue_set(this.swigCPtr, this, value);
    }

    public float getMEndValue() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mEndValue_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
