package com.visioglobe.libVisioMove;

public class VgMatrixInterpolationFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMatrixInterpolationFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMatrixInterpolationFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMatrixInterpolationFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMatrixInterpolationFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgMatrixInterpolationFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgMatrixInterpolationFunctorDescriptorRefPtr(VgMatrixInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgMatrixInterpolationFunctorDescriptorRefPtr__SWIG_1(VgMatrixInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgMatrixInterpolationFunctorDescriptorRefPtr(VgMatrixInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgMatrixInterpolationFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgMatrixInterpolationFunctorDescriptorRefPtr getNull() {
        return new VgMatrixInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgMatrixInterpolationFunctorDescriptorRefPtr set(VgMatrixInterpolationFunctorDescriptor pPointer) {
        return new VgMatrixInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgMatrixInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgMatrixInterpolationFunctorDescriptor __ref__() {
        return new VgMatrixInterpolationFunctorDescriptor(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMatrixInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMatrixInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgMatrixInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMatrixInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgMatrixInterpolationFunctorDescriptorRefPtr create() {
        return new VgMatrixInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMStartMatrix(float[] value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mStartMatrix_set(this.swigCPtr, this, value);
    }

    public float[] getMStartMatrix() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mStartMatrix_get(this.swigCPtr, this);
    }

    public void setMEndMatrix(float[] value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mEndMatrix_set(this.swigCPtr, this, value);
    }

    public float[] getMEndMatrix() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mEndMatrix_get(this.swigCPtr, this);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
