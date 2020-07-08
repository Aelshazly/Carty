package com.visioglobe.libVisioMove;

public class VgDiscreteVectorFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgDiscreteVectorFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDiscreteVectorFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgDiscreteVectorFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgDiscreteVectorFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgDiscreteVectorFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgDiscreteVectorFunctorDescriptorConstRefPtr(VgDiscreteVectorFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgDiscreteVectorFunctorDescriptorConstRefPtr__SWIG_1(VgDiscreteVectorFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgDiscreteVectorFunctorDescriptorConstRefPtr(VgDiscreteVectorFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgDiscreteVectorFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgDiscreteVectorFunctorDescriptorConstRefPtr getNull() {
        return new VgDiscreteVectorFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgDiscreteVectorFunctorDescriptorConstRefPtr set(VgDiscreteVectorFunctorDescriptor pPointer) {
        return new VgDiscreteVectorFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgDiscreteVectorFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgDiscreteVectorFunctorDescriptor __ref__() {
        return new VgDiscreteVectorFunctorDescriptor(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgDiscreteVectorFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteVectorFunctorDescriptor(cPtr, false);
    }

    public VgDiscreteVectorFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteVectorFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgLocationValuePairVector getMLocationValues() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_mLocationValues_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLocationValuePairVector(cPtr, false);
    }

    public double getMInitialTimestamp() {
        return libVisioMoveJNI.m1202x75ba2ae0(this.swigCPtr, this);
    }

    public double getMFinalTimestamp() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_mFinalTimestamp_get(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
