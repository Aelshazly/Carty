package com.visioglobe.libVisioMove;

public class VgInstanceFactory {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgInstanceFactory(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgInstanceFactory obj) {
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
                libVisioMoveJNI.delete_VgInstanceFactory(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAnimationRefPtr instantiate(VgAnimationDescriptorConstRefPtr pDescriptor) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_2(this.swigCPtr, this, VgAnimationDescriptorConstRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgAnimationRefPtr instantiate(VgAnimationDescriptorRefPtr pDescriptor) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_3(this.swigCPtr, this, VgAnimationDescriptorRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgLayerRefPtr instantiate(VgLayerDescriptorConstRefPtr pDescriptor) {
        return new VgLayerRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_4(this.swigCPtr, this, VgLayerDescriptorConstRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgLayerRefPtr instantiate(VgLayerDescriptorRefPtr pDescriptor) {
        return new VgLayerRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_5(this.swigCPtr, this, VgLayerDescriptorRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgLinkRefPtr instantiate(VgLinkDescriptorConstRefPtr pDescriptor) {
        return new VgLinkRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_6(this.swigCPtr, this, VgLinkDescriptorConstRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgLinkRefPtr instantiate(VgLinkDescriptorRefPtr pDescriptor) {
        return new VgLinkRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_7(this.swigCPtr, this, VgLinkDescriptorRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgLineRefPtr instantiate(VgLineDescriptorConstRefPtr pDescriptor) {
        return new VgLineRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_8(this.swigCPtr, this, VgLineDescriptorConstRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgLineRefPtr instantiate(VgLineDescriptorRefPtr pDescriptor) {
        return new VgLineRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_9(this.swigCPtr, this, VgLineDescriptorRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgPointRefPtr instantiate(VgPointDescriptorConstRefPtr pDescriptor) {
        return new VgPointRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_10(this.swigCPtr, this, VgPointDescriptorConstRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }

    public VgPointRefPtr instantiate(VgPointDescriptorRefPtr pDescriptor) {
        return new VgPointRefPtr(libVisioMoveJNI.VgInstanceFactory_instantiate__SWIG_11(this.swigCPtr, this, VgPointDescriptorRefPtr.getCPtr(pDescriptor), pDescriptor), true);
    }
}
