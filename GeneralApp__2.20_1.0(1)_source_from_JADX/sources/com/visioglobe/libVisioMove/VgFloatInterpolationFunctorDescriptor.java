package com.visioglobe.libVisioMove;

public class VgFloatInterpolationFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgFloatInterpolationFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFloatInterpolationFunctorDescriptor obj) {
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

    public static VgFloatInterpolationFunctorDescriptorRefPtr create() {
        return new VgFloatInterpolationFunctorDescriptorRefPtr(libVisioMoveJNI.VgFloatInterpolationFunctorDescriptor_create(), true);
    }

    public void setMStartValue(float value) {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptor_mStartValue_set(this.swigCPtr, this, value);
    }

    public float getMStartValue() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptor_mStartValue_get(this.swigCPtr, this);
    }

    public void setMEndValue(float value) {
        libVisioMoveJNI.VgFloatInterpolationFunctorDescriptor_mEndValue_set(this.swigCPtr, this, value);
    }

    public float getMEndValue() {
        return libVisioMoveJNI.VgFloatInterpolationFunctorDescriptor_mEndValue_get(this.swigCPtr, this);
    }
}
