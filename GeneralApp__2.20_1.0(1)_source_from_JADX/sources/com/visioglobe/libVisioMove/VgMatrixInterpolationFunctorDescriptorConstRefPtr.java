package com.visioglobe.libVisioMove;

public class VgMatrixInterpolationFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMatrixInterpolationFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMatrixInterpolationFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMatrixInterpolationFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMatrixInterpolationFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgMatrixInterpolationFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgMatrixInterpolationFunctorDescriptorConstRefPtr(VgMatrixInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgMatrixInterpolationFunctorDescriptorConstRefPtr__SWIG_1(VgMatrixInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgMatrixInterpolationFunctorDescriptorConstRefPtr(VgMatrixInterpolationFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgMatrixInterpolationFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgMatrixInterpolationFunctorDescriptorConstRefPtr getNull() {
        return new VgMatrixInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgMatrixInterpolationFunctorDescriptorConstRefPtr set(VgMatrixInterpolationFunctorDescriptor pPointer) {
        return new VgMatrixInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgMatrixInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgMatrixInterpolationFunctorDescriptor __ref__() {
        return new VgMatrixInterpolationFunctorDescriptor(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMatrixInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMatrixInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgMatrixInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMatrixInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public float[] getMStartMatrix() {
        return libVisioMoveJNI.m1206x4aaebc7e(this.swigCPtr, this);
    }

    public float[] getMEndMatrix() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_mEndMatrix_get(this.swigCPtr, this);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1205x3a32c6e1(this.swigCPtr, this);
    }
}
