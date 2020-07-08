package com.visioglobe.libVisioMove;

public class VgVectorInterpolationFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgVectorInterpolationFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgVectorInterpolationFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgVectorInterpolationFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgVectorInterpolationFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgVectorInterpolationFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgVectorInterpolationFunctorDescriptorConstRefPtr(VgVectorInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgVectorInterpolationFunctorDescriptorConstRefPtr__SWIG_1(VgVectorInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgVectorInterpolationFunctorDescriptorConstRefPtr(VgVectorInterpolationFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgVectorInterpolationFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgVectorInterpolationFunctorDescriptorConstRefPtr getNull() {
        return new VgVectorInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgVectorInterpolationFunctorDescriptorConstRefPtr set(VgVectorInterpolationFunctorDescriptor pPointer) {
        return new VgVectorInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgVectorInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgVectorInterpolationFunctorDescriptor __ref__() {
        return new VgVectorInterpolationFunctorDescriptor(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgVectorInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgVectorInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgVectorInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgVectorInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgPosition getMStartPosition() {
        long cPtr = libVisioMoveJNI.m1253x73c002e8(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public VgPosition getMEndPosition() {
        long cPtr = libVisioMoveJNI.m1252xa1d92b61(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1251xdc245b9f(this.swigCPtr, this);
    }
}
