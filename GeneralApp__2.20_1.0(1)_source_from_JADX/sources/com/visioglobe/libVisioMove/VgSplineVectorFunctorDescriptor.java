package com.visioglobe.libVisioMove;

public class VgSplineVectorFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgSplineVectorFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgSplineVectorFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSplineVectorFunctorDescriptor obj) {
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

    public static VgSplineVectorFunctorDescriptorRefPtr create() {
        return new VgSplineVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineVectorFunctorDescriptor_create(), true);
    }

    public void setMPoints(VgPositionVector value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptor_mPoints_set(this.swigCPtr, this, VgPositionVector.getCPtr(value), value);
    }

    public VgPositionVector getMPoints() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptor_mPoints_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public void setMSplineMetricRadius(float value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptor_mSplineMetricRadius_set(this.swigCPtr, this, value);
    }

    public float getMSplineMetricRadius() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptor_mSplineMetricRadius_get(this.swigCPtr, this);
    }

    public void setMDistanceFromSpline(float value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptor_mDistanceFromSpline_set(this.swigCPtr, this, value);
    }

    public float getMDistanceFromSpline() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptor_mDistanceFromSpline_get(this.swigCPtr, this);
    }
}
