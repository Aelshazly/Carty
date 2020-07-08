package com.visioglobe.libVisioMove;

public class VgSplineOrientationQuaternionFunctorDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSplineOrientationQuaternionFunctorDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSplineOrientationQuaternionFunctorDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSplineOrientationQuaternionFunctorDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSplineOrientationQuaternionFunctorDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgSplineOrientationQuaternionFunctorDescriptorRefPtr__SWIG_0(), true);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorRefPtr(VgSplineOrientationQuaternionFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgSplineOrientationQuaternionFunctorDescriptorRefPtr__SWIG_1(VgSplineOrientationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorRefPtr(VgSplineOrientationQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgSplineOrientationQuaternionFunctorDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgSplineOrientationQuaternionFunctorDescriptorRefPtr getNull() {
        return new VgSplineOrientationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_getNull(), true);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorRefPtr set(VgSplineOrientationQuaternionFunctorDescriptor pPointer) {
        return new VgSplineOrientationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_set(this.swigCPtr, this, VgSplineOrientationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgSplineOrientationQuaternionFunctorDescriptor __ref__() {
        return new VgSplineOrientationQuaternionFunctorDescriptor(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSplineOrientationQuaternionFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineOrientationQuaternionFunctorDescriptor(cPtr, false);
    }

    public VgSplineOrientationQuaternionFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineOrientationQuaternionFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgSplineOrientationQuaternionFunctorDescriptorRefPtr create(VgSplineVectorFunctorDescriptorRefPtr pSplineVectorFunctorDescriptor) {
        return new VgSplineOrientationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_create(this.swigCPtr, this, VgSplineVectorFunctorDescriptorRefPtr.getCPtr(pSplineVectorFunctorDescriptor), pSplineVectorFunctorDescriptor), true);
    }

    public void setMPostHeading(float value) {
        libVisioMoveJNI.m1240x163af826(this.swigCPtr, this, value);
    }

    public float getMPostHeading() {
        return libVisioMoveJNI.m1239x163acb1a(this.swigCPtr, this);
    }

    public void setMPostPitch(float value) {
        libVisioMoveJNI.m1242xd5125aa4(this.swigCPtr, this, value);
    }

    public float getMPostPitch() {
        return libVisioMoveJNI.m1241xd5122d98(this.swigCPtr, this);
    }

    public void setMPostBank(float value) {
        libVisioMoveJNI.m1238xafcf2bfe(this.swigCPtr, this, value);
    }

    public float getMPostBank() {
        return libVisioMoveJNI.m1237xafcefef2(this.swigCPtr, this);
    }

    public void setMSplineVectorFunctorDescriptor(VgSplineVectorFunctorDescriptorRefPtr value) {
        libVisioMoveJNI.m1244x41278872(this.swigCPtr, this, VgSplineVectorFunctorDescriptorRefPtr.getCPtr(value), value);
    }

    public VgSplineVectorFunctorDescriptorRefPtr getMSplineVectorFunctorDescriptor() {
        long cPtr = libVisioMoveJNI.m1243x41275b66(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptorRefPtr(cPtr, false);
    }

    public void setMStartTime(float value) {
        libVisioMoveJNI.m1246x8594c6f3(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.m1245x859499e7(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.m1236x53fc2eac(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.m1235x53fc01a0(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1234xb1738ba4(this.swigCPtr, this);
    }
}
