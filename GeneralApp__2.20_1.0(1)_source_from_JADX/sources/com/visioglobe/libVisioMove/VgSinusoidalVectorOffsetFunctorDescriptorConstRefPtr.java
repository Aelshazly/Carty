package com.visioglobe.libVisioMove;

public class VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(VgSinusoidalVectorOffsetFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr__SWIG_1(VgSinusoidalVectorOffsetFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr getNull() {
        return new VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr set(VgSinusoidalVectorOffsetFunctorDescriptor pPointer) {
        return new VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgSinusoidalVectorOffsetFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptor __ref__() {
        return new VgSinusoidalVectorOffsetFunctorDescriptor(libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSinusoidalVectorOffsetFunctorDescriptor(cPtr, false);
    }

    public VgSinusoidalVectorOffsetFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSinusoidalVectorOffsetFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public float[] getMBaseVector() {
        return libVisioMoveJNI.m1218x164f295b(this.swigCPtr, this);
    }

    public float[] getMVector() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mVector_get(this.swigCPtr, this);
    }

    public double getMStartPhase() {
        return libVisioMoveJNI.m1221x664c4da0(this.swigCPtr, this);
    }

    public double getMEndPhase() {
        return libVisioMoveJNI.m1219x5ffc9547(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.m1222x35bc8e16(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.m1220x98a1358f(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1217x64a1d55(this.swigCPtr, this);
    }
}
