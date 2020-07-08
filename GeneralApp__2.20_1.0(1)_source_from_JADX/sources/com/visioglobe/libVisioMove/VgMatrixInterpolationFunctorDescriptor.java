package com.visioglobe.libVisioMove;

public class VgMatrixInterpolationFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgMatrixInterpolationFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMatrixInterpolationFunctorDescriptor obj) {
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

    public static VgMatrixInterpolationFunctorDescriptorRefPtr create() {
        return new VgMatrixInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_create(), true);
    }

    public void setMStartMatrix(float[] value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_mStartMatrix_set(this.swigCPtr, this, value);
    }

    public float[] getMStartMatrix() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_mStartMatrix_get(this.swigCPtr, this);
    }

    public void setMEndMatrix(float[] value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_mEndMatrix_set(this.swigCPtr, this, value);
    }

    public float[] getMEndMatrix() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_mEndMatrix_get(this.swigCPtr, this);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgMatrixInterpolationFunctorDescriptor_mCubic_get(this.swigCPtr, this);
    }
}
