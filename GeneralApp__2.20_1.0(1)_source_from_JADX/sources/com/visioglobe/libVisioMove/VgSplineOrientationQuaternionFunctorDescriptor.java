package com.visioglobe.libVisioMove;

public class VgSplineOrientationQuaternionFunctorDescriptor extends VgFunctorDescriptor {
    private long swigCPtr;

    protected VgSplineOrientationQuaternionFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSplineOrientationQuaternionFunctorDescriptor obj) {
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

    public static VgSplineOrientationQuaternionFunctorDescriptorRefPtr create(VgSplineVectorFunctorDescriptorRefPtr pSplineVectorFunctorDescriptor) {
        return new VgSplineOrientationQuaternionFunctorDescriptorRefPtr(libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_create(VgSplineVectorFunctorDescriptorRefPtr.getCPtr(pSplineVectorFunctorDescriptor), pSplineVectorFunctorDescriptor), true);
    }

    public void setMPostHeading(float value) {
        libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_mPostHeading_set(this.swigCPtr, this, value);
    }

    public float getMPostHeading() {
        return libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_mPostHeading_get(this.swigCPtr, this);
    }

    public void setMPostPitch(float value) {
        libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_mPostPitch_set(this.swigCPtr, this, value);
    }

    public float getMPostPitch() {
        return libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_mPostPitch_get(this.swigCPtr, this);
    }

    public void setMPostBank(float value) {
        libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_mPostBank_set(this.swigCPtr, this, value);
    }

    public float getMPostBank() {
        return libVisioMoveJNI.VgSplineOrientationQuaternionFunctorDescriptor_mPostBank_get(this.swigCPtr, this);
    }

    public void setMSplineVectorFunctorDescriptor(VgSplineVectorFunctorDescriptorRefPtr value) {
        libVisioMoveJNI.m1248x438cdb4d(this.swigCPtr, this, VgSplineVectorFunctorDescriptorRefPtr.getCPtr(value), value);
    }

    public VgSplineVectorFunctorDescriptorRefPtr getMSplineVectorFunctorDescriptor() {
        long cPtr = libVisioMoveJNI.m1247x438cae41(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSplineVectorFunctorDescriptorRefPtr(cPtr, false);
    }
}
