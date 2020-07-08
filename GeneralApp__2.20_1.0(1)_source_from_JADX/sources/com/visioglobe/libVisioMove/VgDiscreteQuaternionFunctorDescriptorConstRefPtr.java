package com.visioglobe.libVisioMove;

public class VgDiscreteQuaternionFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgDiscreteQuaternionFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDiscreteQuaternionFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgDiscreteQuaternionFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgDiscreteQuaternionFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgDiscreteQuaternionFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgDiscreteQuaternionFunctorDescriptorConstRefPtr(VgDiscreteQuaternionFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgDiscreteQuaternionFunctorDescriptorConstRefPtr__SWIG_1(VgDiscreteQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgDiscreteQuaternionFunctorDescriptorConstRefPtr(VgDiscreteQuaternionFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgDiscreteQuaternionFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgDiscreteQuaternionFunctorDescriptorConstRefPtr getNull() {
        return new VgDiscreteQuaternionFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgDiscreteQuaternionFunctorDescriptorConstRefPtr set(VgDiscreteQuaternionFunctorDescriptor pPointer) {
        return new VgDiscreteQuaternionFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgDiscreteQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgDiscreteQuaternionFunctorDescriptor __ref__() {
        return new VgDiscreteQuaternionFunctorDescriptor(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgDiscreteQuaternionFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteQuaternionFunctorDescriptor(cPtr, false);
    }

    public VgDiscreteQuaternionFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgDiscreteQuaternionFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgOrientationValuePairVector getMOrientationValues() {
        long cPtr = libVisioMoveJNI.m1197x5d92efb7(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientationValuePairVector(cPtr, false);
    }

    public double getMInitialTimestamp() {
        return libVisioMoveJNI.m1196x4723f2fb(this.swigCPtr, this);
    }

    public double getMFinalTimestamp() {
        return libVisioMoveJNI.m1195xe64e4e89(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
