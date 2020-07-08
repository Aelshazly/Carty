package com.visioglobe.libVisioMove;

public class VgDiscreteVectorFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgDiscreteVectorFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgDiscreteVectorFunctorDescriptor obj) {
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

    public static VgDiscreteVectorFunctorDescriptorRefPtr create() {
        return new VgDiscreteVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_create(), true);
    }

    public void setMLocationValues(VgLocationValuePairVector value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_mLocationValues_set(this.swigCPtr, this, VgLocationValuePairVector.getCPtr(value), value);
    }

    public VgLocationValuePairVector getMLocationValues() {
        long cPtr = libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_mLocationValues_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLocationValuePairVector(cPtr, false);
    }

    public void setMInitialTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_mInitialTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMInitialTimestamp() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_mInitialTimestamp_get(this.swigCPtr, this);
    }

    public void setMFinalTimestamp(double value) {
        libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_mFinalTimestamp_set(this.swigCPtr, this, value);
    }

    public double getMFinalTimestamp() {
        return libVisioMoveJNI.VgDiscreteVectorFunctorDescriptor_mFinalTimestamp_get(this.swigCPtr, this);
    }
}
