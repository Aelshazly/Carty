package com.visioglobe.libVisioMove;

public class VgSplineVectorFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSplineVectorFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSplineVectorFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSplineVectorFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSplineVectorFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgSplineVectorFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgSplineVectorFunctorDescriptorRefPtr(VgSplineVectorFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgSplineVectorFunctorDescriptorRefPtr__SWIG_1(VgSplineVectorFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgSplineVectorFunctorDescriptorRefPtr(VgSplineVectorFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgSplineVectorFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgSplineVectorFunctorDescriptorRefPtr getNull() {
        return new VgSplineVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgSplineVectorFunctorDescriptorRefPtr set(VgSplineVectorFunctorDescriptor pPointer) {
        return new VgSplineVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgSplineVectorFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgSplineVectorFunctorDescriptor __ref__() {
        return new VgSplineVectorFunctorDescriptor(libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSplineVectorFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptor(cPtr, false);
    }

    public VgSplineVectorFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgSplineVectorFunctorDescriptorRefPtr create() {
        return new VgSplineVectorFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMPoints(VgPositionVector value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mPoints_set(this.swigCPtr, this, VgPositionVector.getCPtr(value), value);
    }

    public VgPositionVector getMPoints() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mPoints_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public void setMSplineMetricRadius(float value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mSplineMetricRadius_set(this.swigCPtr, this, value);
    }

    public float getMSplineMetricRadius() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mSplineMetricRadius_get(this.swigCPtr, this);
    }

    public void setMDistanceFromSpline(float value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mDistanceFromSpline_set(this.swigCPtr, this, value);
    }

    public float getMDistanceFromSpline() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mDistanceFromSpline_get(this.swigCPtr, this);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
