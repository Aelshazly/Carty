package com.visioglobe.libVisioMove;

public class VgSinusoidalVectorOffsetFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSinusoidalVectorOffsetFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(VgSinusoidalVectorOffsetFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr__SWIG_1(VgSinusoidalVectorOffsetFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(VgSinusoidalVectorOffsetFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgSinusoidalVectorOffsetFunctorDescriptorRefPtr getNull() {
        return new VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorRefPtr set(VgSinusoidalVectorOffsetFunctorDescriptor pPointer) {
        return new VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgSinusoidalVectorOffsetFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptor __ref__() {
        return new VgSinusoidalVectorOffsetFunctorDescriptor(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSinusoidalVectorOffsetFunctorDescriptor(cPtr, false);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSinusoidalVectorOffsetFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorRefPtr create() {
        return new VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMBaseVector(float[] value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mBaseVector_set(this.swigCPtr, this, value);
    }

    public float[] getMBaseVector() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mBaseVector_get(this.swigCPtr, this);
    }

    public void setMVector(float[] value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mVector_set(this.swigCPtr, this, value);
    }

    public float[] getMVector() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mVector_get(this.swigCPtr, this);
    }

    public void setMStartPhase(double value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartPhase_set(this.swigCPtr, this, value);
    }

    public double getMStartPhase() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartPhase_get(this.swigCPtr, this);
    }

    public void setMEndPhase(double value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndPhase_set(this.swigCPtr, this, value);
    }

    public double getMEndPhase() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndPhase_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
