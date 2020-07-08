package com.visioglobe.libVisioMove;

public class VgSplineVectorFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSplineVectorFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSplineVectorFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSplineVectorFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSplineVectorFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgSplineVectorFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgSplineVectorFunctorDescriptorConstRefPtr(VgSplineVectorFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgSplineVectorFunctorDescriptorConstRefPtr__SWIG_1(VgSplineVectorFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgSplineVectorFunctorDescriptorConstRefPtr(VgSplineVectorFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgSplineVectorFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgSplineVectorFunctorDescriptorConstRefPtr getNull() {
        return new VgSplineVectorFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgSplineVectorFunctorDescriptorConstRefPtr set(VgSplineVectorFunctorDescriptor pPointer) {
        return new VgSplineVectorFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgSplineVectorFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgSplineVectorFunctorDescriptor __ref__() {
        return new VgSplineVectorFunctorDescriptor(libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSplineVectorFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptor(cPtr, false);
    }

    public VgSplineVectorFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgPositionVector getMPoints() {
        long cPtr = libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_mPoints_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionVector(cPtr, false);
    }

    public float getMSplineMetricRadius() {
        return libVisioMoveJNI.m1250x768dca59(this.swigCPtr, this);
    }

    public float getMDistanceFromSpline() {
        return libVisioMoveJNI.m1249xe53e5cf6(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSplineVectorFunctorDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
