package com.visioglobe.libVisioMove;

public class VgAxialRotationQuaternionFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAxialRotationQuaternionFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAxialRotationQuaternionFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgAxialRotationQuaternionFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAxialRotationQuaternionFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgAxialRotationQuaternionFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgAxialRotationQuaternionFunctorDescriptorRefPtr(VgAxialRotationQuaternionFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgAxialRotationQuaternionFunctorDescriptorRefPtr__SWIG_1(VgAxialRotationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgAxialRotationQuaternionFunctorDescriptorRefPtr(VgAxialRotationQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgAxialRotationQuaternionFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgAxialRotationQuaternionFunctorDescriptorRefPtr getNull() {
        return new VgAxialRotationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgAxialRotationQuaternionFunctorDescriptorRefPtr set(VgAxialRotationQuaternionFunctorDescriptor pPointer) {
        return new VgAxialRotationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgAxialRotationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgAxialRotationQuaternionFunctorDescriptor __ref__() {
        return new VgAxialRotationQuaternionFunctorDescriptor(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgAxialRotationQuaternionFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAxialRotationQuaternionFunctorDescriptor(cPtr, false);
    }

    public VgAxialRotationQuaternionFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAxialRotationQuaternionFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgAxialRotationQuaternionFunctorDescriptorRefPtr create() {
        return new VgAxialRotationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMPreOrientation(VgOrientation value) {
        libVisioMoveJNI.m1194xefa3be53(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getMPreOrientation() {
        long cPtr = libVisioMoveJNI.m1193xefa39147(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setMAxis(float[] value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mAxis_set(this.swigCPtr, this, value);
    }

    public float[] getMAxis() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mAxis_get(this.swigCPtr, this);
    }

    public void setMStartAngle(float value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartAngle_set(this.swigCPtr, this, value);
    }

    public float getMStartAngle() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartAngle_get(this.swigCPtr, this);
    }

    public void setMEndAngle(float value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndAngle_set(this.swigCPtr, this, value);
    }

    public float getMEndAngle() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndAngle_get(this.swigCPtr, this);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
