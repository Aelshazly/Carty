package com.visioglobe.libVisioMove;

public class VgQuaternionInterpolationFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgQuaternionInterpolationFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgQuaternionInterpolationFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgQuaternionInterpolationFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgQuaternionInterpolationFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgQuaternionInterpolationFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgQuaternionInterpolationFunctorDescriptorRefPtr(VgQuaternionInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgQuaternionInterpolationFunctorDescriptorRefPtr__SWIG_1(VgQuaternionInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgQuaternionInterpolationFunctorDescriptorRefPtr(VgQuaternionInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgQuaternionInterpolationFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgQuaternionInterpolationFunctorDescriptorRefPtr getNull() {
        return new VgQuaternionInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgQuaternionInterpolationFunctorDescriptorRefPtr set(VgQuaternionInterpolationFunctorDescriptor pPointer) {
        return new VgQuaternionInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgQuaternionInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgQuaternionInterpolationFunctorDescriptor __ref__() {
        return new VgQuaternionInterpolationFunctorDescriptor(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgQuaternionInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgQuaternionInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgQuaternionInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgQuaternionInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgQuaternionInterpolationFunctorDescriptorRefPtr create() {
        return new VgQuaternionInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMStartOrientation(VgOrientation value) {
        libVisioMoveJNI.m1216x3bf4fec7(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getMStartOrientation() {
        long cPtr = libVisioMoveJNI.m1215x3bf4d1bb(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setMEndOrientation(VgOrientation value) {
        libVisioMoveJNI.m1214x79bd26ae(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getMEndOrientation() {
        long cPtr = libVisioMoveJNI.m1213x79bcf9a2(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setMExtraSpins(int value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mExtraSpins_set(this.swigCPtr, this, value);
    }

    public int getMExtraSpins() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mExtraSpins_get(this.swigCPtr, this);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
