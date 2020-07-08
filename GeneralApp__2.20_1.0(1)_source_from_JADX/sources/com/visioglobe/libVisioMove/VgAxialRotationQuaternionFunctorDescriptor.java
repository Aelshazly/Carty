package com.visioglobe.libVisioMove;

public class VgAxialRotationQuaternionFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgAxialRotationQuaternionFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAxialRotationQuaternionFunctorDescriptor obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public static VgAxialRotationQuaternionFunctorDescriptorRefPtr create() {
        return new VgAxialRotationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_create(), true);
    }

    public void setMPreOrientation(VgOrientation value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mPreOrientation_set(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getMPreOrientation() {
        long cPtr = libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mPreOrientation_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setMAxis(float[] value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mAxis_set(this.swigCPtr, this, value);
    }

    public float[] getMAxis() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mAxis_get(this.swigCPtr, this);
    }

    public void setMStartAngle(float value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mStartAngle_set(this.swigCPtr, this, value);
    }

    public float getMStartAngle() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mStartAngle_get(this.swigCPtr, this);
    }

    public void setMEndAngle(float value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mEndAngle_set(this.swigCPtr, this, value);
    }

    public float getMEndAngle() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mEndAngle_get(this.swigCPtr, this);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptor_mCubic_get(this.swigCPtr, this);
    }
}
