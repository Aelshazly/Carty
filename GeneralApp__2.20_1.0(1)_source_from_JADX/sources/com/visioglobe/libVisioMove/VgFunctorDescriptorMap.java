package com.visioglobe.libVisioMove;

public class VgFunctorDescriptorMap {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFunctorDescriptorMap(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFunctorDescriptorMap obj) {
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
                libVisioMoveJNI.delete_VgFunctorDescriptorMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgFunctorDescriptorMap() {
        this(libVisioMoveJNI.new_VgFunctorDescriptorMap__SWIG_0(), true);
    }

    public VgFunctorDescriptorMap(VgFunctorDescriptorMap pOther) {
        this(libVisioMoveJNI.new_VgFunctorDescriptorMap__SWIG_1(getCPtr(pOther), pOther), true);
    }

    public VgFunctorDescriptorRefPtr get(VgStringPair pChannel) {
        return new VgFunctorDescriptorRefPtr(libVisioMoveJNI.VgFunctorDescriptorMap_get(this.swigCPtr, this, VgStringPair.getCPtr(pChannel), pChannel), false);
    }

    public void set(VgStringPair pChannel, VgFunctorDescriptorRefPtr pDescriptor) {
        libVisioMoveJNI.VgFunctorDescriptorMap_set(this.swigCPtr, this, VgStringPair.getCPtr(pChannel), pChannel, VgFunctorDescriptorRefPtr.getCPtr(pDescriptor), pDescriptor);
    }
}
