package com.visioglobe.libVisioMove;

public class VgDiscreteQuaternionFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgDiscreteQuaternionFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDiscreteQuaternionFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgDiscreteQuaternionFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgDiscreteQuaternionFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgDiscreteQuaternionFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgDiscreteQuaternionFunctorDescriptorRefPtr(VgDiscreteQuaternionFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgDiscreteQuaternionFunctorDescriptorRefPtr__SWIG_1(VgDiscreteQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgDiscreteQuaternionFunctorDescriptorRefPtr(VgDiscreteQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgDiscreteQuaternionFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgDiscreteQuaternionFunctorDescriptorRefPtr getNull() {
        return new VgDiscreteQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgDiscreteQuaternionFunctorDescriptorRefPtr set(VgDiscreteQuaternionFunctorDescriptor pPointer) {
        return new VgDiscreteQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgDiscreteQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgDiscreteQuaternionFunctorDescriptor __ref__() {
        return new VgDiscreteQuaternionFunctorDescriptor(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgDiscreteQuaternionFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteQuaternionFunctorDescriptor(cPtr, false);
    }

    public VgDiscreteQuaternionFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteQuaternionFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgDiscreteQuaternionFunctorDescriptorRefPtr create() {
        return new VgDiscreteQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMOrientationValues(VgOrientationValuePairVector value) {
        libVisioMoveJNI.m1201xf598ebb2(this.swigCPtr, this, VgOrientationValuePairVector.getCPtr(value), value);
    }

    public VgOrientationValuePairVector getMOrientationValues() {
        long cPtr = libVisioMoveJNI.m1200xf598bea6(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientationValuePairVector(cPtr, false);
    }

    public void setMInitialTimestamp(double value) {
        libVisioMoveJNI.m1199x22c13738(this.swigCPtr, this, value);
    }

    public double getMInitialTimestamp() {
        return libVisioMoveJNI.m1198x22c10a2c(this.swigCPtr, this);
    }

    public void setMFinalTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_mFinalTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMFinalTimestamp() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_mFinalTimestamp_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
