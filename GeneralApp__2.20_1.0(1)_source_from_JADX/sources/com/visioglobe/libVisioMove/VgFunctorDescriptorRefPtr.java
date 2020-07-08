package com.visioglobe.libVisioMove;

public class VgFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgFunctorDescriptorRefPtr(VgFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgFunctorDescriptorRefPtr__SWIG_1(VgFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgFunctorDescriptorRefPtr(VgFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgFunctorDescriptorRefPtr getNull() {
        return new VgFunctorDescriptorRefPtr(libVisioMoveJNI.VgFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgFunctorDescriptorRefPtr set(VgFunctorDescriptor pPointer) {
        return new VgFunctorDescriptorRefPtr(libVisioMoveJNI.VgFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgFunctorDescriptor __ref__() {
        return new VgFunctorDescriptor(libVisioMoveJNI.VgFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptor(cPtr, false);
    }

    public VgFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgFunctorDescriptorRefPtr(VgAxialRotationQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_0__(VgAxialRotationQuaternionFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgDiscreteQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_1__(VgDiscreteQuaternionFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgDiscreteVectorFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_2__(VgDiscreteVectorFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgFloatInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_3__(VgFloatInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgMatrixInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_4__(VgMatrixInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgQuaternionInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_5__(VgQuaternionInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgSinusoidalVectorOffsetFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_6__(VgSinusoidalVectorOffsetFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgSplineOrientationQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_7__(VgSplineOrientationQuaternionFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgSplineVectorFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_8__(VgSplineVectorFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgFunctorDescriptorRefPtr(VgVectorInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_9__(VgVectorInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
