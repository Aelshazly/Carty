package com.visioglobe.libVisioMove;

public class VgDiscreteQuaternionFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgDiscreteQuaternionFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDiscreteQuaternionFunctorDescriptor obj) {
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

    public static VgDiscreteQuaternionFunctorDescriptorRefPtr create() {
        return new VgDiscreteQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_create(), true);
    }

    public void setMOrientationValues(VgOrientationValuePairVector value) {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_mOrientationValues_set(this.swigCPtr, this, VgOrientationValuePairVector.getCPtr(value), value);
    }

    public VgOrientationValuePairVector getMOrientationValues() {
        long cPtr = libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_mOrientationValues_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientationValuePairVector(cPtr, false);
    }

    public void setMInitialTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_mInitialTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMInitialTimestamp() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_mInitialTimestamp_get(this.swigCPtr, this);
    }

    public void setMFinalTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_mFinalTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMFinalTimestamp() {
        return libVisioMoveJNI.VgDiscreteQuaternionFunctorDescriptor_mFinalTimestamp_get(this.swigCPtr, this);
    }
}
