package com.visioglobe.libVisioMove;

public class VgVectorInterpolationFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgVectorInterpolationFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgVectorInterpolationFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgVectorInterpolationFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgVectorInterpolationFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgVectorInterpolationFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgVectorInterpolationFunctorDescriptorRefPtr(VgVectorInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgVectorInterpolationFunctorDescriptorRefPtr__SWIG_1(VgVectorInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgVectorInterpolationFunctorDescriptorRefPtr(VgVectorInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgVectorInterpolationFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgVectorInterpolationFunctorDescriptorRefPtr getNull() {
        return new VgVectorInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgVectorInterpolationFunctorDescriptorRefPtr set(VgVectorInterpolationFunctorDescriptor pPointer) {
        return new VgVectorInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgVectorInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgVectorInterpolationFunctorDescriptor __ref__() {
        return new VgVectorInterpolationFunctorDescriptor(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgVectorInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgVectorInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgVectorInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgVectorInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgVectorInterpolationFunctorDescriptorRefPtr create() {
        return new VgVectorInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMStartPosition(VgPosition value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mStartPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMStartPosition() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mStartPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMEndPosition(VgPosition value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mEndPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMEndPosition() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mEndPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
