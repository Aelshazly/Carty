package com.visioglobe.libVisioMove;

public class VgDiscreteVectorFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgDiscreteVectorFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDiscreteVectorFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgDiscreteVectorFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgDiscreteVectorFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgDiscreteVectorFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgDiscreteVectorFunctorDescriptorRefPtr(VgDiscreteVectorFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgDiscreteVectorFunctorDescriptorRefPtr__SWIG_1(VgDiscreteVectorFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgDiscreteVectorFunctorDescriptorRefPtr(VgDiscreteVectorFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgDiscreteVectorFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgDiscreteVectorFunctorDescriptorRefPtr getNull() {
        return new VgDiscreteVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgDiscreteVectorFunctorDescriptorRefPtr set(VgDiscreteVectorFunctorDescriptor pPointer) {
        return new VgDiscreteVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgDiscreteVectorFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgDiscreteVectorFunctorDescriptor __ref__() {
        return new VgDiscreteVectorFunctorDescriptor(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgDiscreteVectorFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteVectorFunctorDescriptor(cPtr, false);
    }

    public VgDiscreteVectorFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteVectorFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgDiscreteVectorFunctorDescriptorRefPtr create() {
        return new VgDiscreteVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMLocationValues(VgLocationValuePairVector value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mLocationValues_set(this.swigCPtr, this, VgLocationValuePairVector.getCPtr(value), value);
    }

    public VgLocationValuePairVector getMLocationValues() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mLocationValues_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLocationValuePairVector(cPtr, false);
    }

    public void setMInitialTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mInitialTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMInitialTimestamp() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mInitialTimestamp_get(this.swigCPtr, this);
    }

    public void setMFinalTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mFinalTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMFinalTimestamp() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mFinalTimestamp_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
