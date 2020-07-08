package com.visioglobe.libVisioMove;

public class VgQuaternionInterpolationFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgQuaternionInterpolationFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgQuaternionInterpolationFunctorDescriptor obj) {
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

    public static VgQuaternionInterpolationFunctorDescriptorRefPtr create() {
        return new VgQuaternionInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_create(), true);
    }

    public void setMStartOrientation(VgOrientation value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mStartOrientation_set(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getMStartOrientation() {
        long cPtr = libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mStartOrientation_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setMEndOrientation(VgOrientation value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mEndOrientation_set(this.swigCPtr, this, VgOrientation.getCPtr(value), value);
    }

    public VgOrientation getMEndOrientation() {
        long cPtr = libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mEndOrientation_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public void setMExtraSpins(int value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mExtraSpins_set(this.swigCPtr, this, value);
    }

    public int getMExtraSpins() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mExtraSpins_get(this.swigCPtr, this);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptor_mCubic_get(this.swigCPtr, this);
    }
}
