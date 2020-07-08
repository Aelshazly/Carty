package com.visioglobe.libVisioMove;

public class VgSinusoidalVectorOffsetFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgSinusoidalVectorOffsetFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSinusoidalVectorOffsetFunctorDescriptor obj) {
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

    public static VgSinusoidalVectorOffsetFunctorDescriptorRefPtr create() {
        return new VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_create(), true);
    }

    public void setMBaseVector(float[] value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mBaseVector_set(this.swigCPtr, this, value);
    }

    public float[] getMBaseVector() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mBaseVector_get(this.swigCPtr, this);
    }

    public void setMVector(float[] value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mVector_set(this.swigCPtr, this, value);
    }

    public float[] getMVector() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mVector_get(this.swigCPtr, this);
    }

    public void setMStartPhase(double value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mStartPhase_set(this.swigCPtr, this, value);
    }

    public double getMStartPhase() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mStartPhase_get(this.swigCPtr, this);
    }

    public void setMEndPhase(double value) {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mEndPhase_set(this.swigCPtr, this, value);
    }

    public double getMEndPhase() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptor_mEndPhase_get(this.swigCPtr, this);
    }
}
