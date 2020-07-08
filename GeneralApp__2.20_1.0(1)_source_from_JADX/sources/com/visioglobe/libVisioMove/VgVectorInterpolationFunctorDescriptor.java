package com.visioglobe.libVisioMove;

public class VgVectorInterpolationFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgVectorInterpolationFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgVectorInterpolationFunctorDescriptor obj) {
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

    public static VgVectorInterpolationFunctorDescriptorRefPtr create() {
        return new VgVectorInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_create(), true);
    }

    public void setMStartPosition(VgPosition value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_mStartPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMStartPosition() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_mStartPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMEndPosition(VgPosition value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_mEndPosition_set(this.swigCPtr, this, VgPosition.getCPtr(value), value);
    }

    public VgPosition getMEndPosition() {
        long cPtr = libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_mEndPosition_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPosition(cPtr, false);
    }

    public void setMCubic(boolean value) {
        libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_mCubic_set(this.swigCPtr, this, value);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgVectorInterpolationFunctorDescriptor_mCubic_get(this.swigCPtr, this);
    }
}
