package com.visioglobe.libVisioMove;

public class VgFunctorDescriptorMapEntry {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFunctorDescriptorMapEntry(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFunctorDescriptorMapEntry obj) {
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
                libVisioMoveJNI.delete_VgFunctorDescriptorMapEntry(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgFunctorDescriptorMapEntry(VgStringPair pChannel) {
        this(libVisioMoveJNI.new_VgFunctorDescriptorMapEntry(VgStringPair.getCPtr(pChannel), pChannel), true);
    }

    public void setMChannel(VgStringPair value) {
        libVisioMoveJNI.VgFunctorDescriptorMapEntry_mChannel_set(this.swigCPtr, this, VgStringPair.getCPtr(value), value);
    }

    public VgStringPair getMChannel() {
        long cPtr = libVisioMoveJNI.VgFunctorDescriptorMapEntry_mChannel_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public void setMFunctorDescriptor(VgFunctorDescriptorRefPtr value) {
        libVisioMoveJNI.VgFunctorDescriptorMapEntry_mFunctorDescriptor_set(this.swigCPtr, this, VgFunctorDescriptorRefPtr.getCPtr(value), value);
    }

    public VgFunctorDescriptorRefPtr getMFunctorDescriptor() {
        long cPtr = libVisioMoveJNI.VgFunctorDescriptorMapEntry_mFunctorDescriptor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptorRefPtr(cPtr, false);
    }
}
