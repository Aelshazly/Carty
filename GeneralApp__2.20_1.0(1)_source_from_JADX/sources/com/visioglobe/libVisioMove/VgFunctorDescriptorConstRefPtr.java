package com.visioglobe.libVisioMove;

public class VgFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgFunctorDescriptorConstRefPtr(VgFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgFunctorDescriptorConstRefPtr__SWIG_1(VgFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgFunctorDescriptorConstRefPtr(VgFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgFunctorDescriptorConstRefPtr getNull() {
        return new VgFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgFunctorDescriptorConstRefPtr set(VgFunctorDescriptor pPointer) {
        return new VgFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgFunctorDescriptor __ref__() {
        return new VgFunctorDescriptor(libVisioMoveJNI.VgFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptor(cPtr, false);
    }

    public VgFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_mStartTime_get(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_mEndTime_get(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgFunctorDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
