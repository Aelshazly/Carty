package com.visioglobe.libVisioMove;

public class VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.m1260xe140565c(), true);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(VgSplineOrientationQuaternionFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.m1261xe140565d(VgSplineOrientationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.m1262xe140565e(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr getNull() {
        return new VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(libVisioMoveJNI.m1226xb1c0c466(), true);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr set(VgSplineOrientationQuaternionFunctorDescriptor pPointer) {
        return new VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgSplineOrientationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgSplineOrientationQuaternionFunctorDescriptor __ref__() {
        return new VgSplineOrientationQuaternionFunctorDescriptor(libVisioMoveJNI.m1224x3fe23c(this.swigCPtr, this), false);
    }

    public VgSplineOrientationQuaternionFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.m1223xbe5001b(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineOrientationQuaternionFunctorDescriptor(cPtr, false);
    }

    public VgSplineOrientationQuaternionFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineOrientationQuaternionFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.m1227x31d2d35b(this.swigCPtr, this);
    }

    public float getMPostHeading() {
        return libVisioMoveJNI.m1230x60279663(this.swigCPtr, this);
    }

    public float getMPostPitch() {
        return libVisioMoveJNI.m1231xfd1b3721(this.swigCPtr, this);
    }

    public float getMPostBank() {
        return libVisioMoveJNI.m1229xf32a2049(this.swigCPtr, this);
    }

    public VgSplineVectorFunctorDescriptorRefPtr getMSplineVectorFunctorDescriptor() {
        long cPtr = libVisioMoveJNI.m1232x292d466f(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptorRefPtr(cPtr, false);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.m1233xad9da370(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.m1228x249bd969(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1225x8a8bb33b(this.swigCPtr, this);
    }
}
