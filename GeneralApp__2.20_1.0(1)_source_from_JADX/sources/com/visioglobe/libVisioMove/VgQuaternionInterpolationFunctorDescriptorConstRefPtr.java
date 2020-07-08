package com.visioglobe.libVisioMove;

public class VgQuaternionInterpolationFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgQuaternionInterpolationFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgQuaternionInterpolationFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgQuaternionInterpolationFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgQuaternionInterpolationFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.m1257xf884df45(), true);
    }

    public VgQuaternionInterpolationFunctorDescriptorConstRefPtr(VgQuaternionInterpolationFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.m1258xf884df46(VgQuaternionInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgQuaternionInterpolationFunctorDescriptorConstRefPtr(VgQuaternionInterpolationFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.m1259xf884df47(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgQuaternionInterpolationFunctorDescriptorConstRefPtr getNull() {
        return new VgQuaternionInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgQuaternionInterpolationFunctorDescriptorConstRefPtr set(VgQuaternionInterpolationFunctorDescriptor pPointer) {
        return new VgQuaternionInterpolationFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgQuaternionInterpolationFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgQuaternionInterpolationFunctorDescriptor __ref__() {
        return new VgQuaternionInterpolationFunctorDescriptor(libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgQuaternionInterpolationFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgQuaternionInterpolationFunctorDescriptor(cPtr, false);
    }

    public VgQuaternionInterpolationFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgQuaternionInterpolationFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgOrientation getMStartOrientation() {
        long cPtr = libVisioMoveJNI.m1211x701f7b04(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public VgOrientation getMEndOrientation() {
        long cPtr = libVisioMoveJNI.m1208x1de3612b(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public int getMExtraSpins() {
        return libVisioMoveJNI.m1210xd596caf7(this.swigCPtr, this);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.m1212x25543ae7(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.m1209x712fe2a0(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgQuaternionInterpolationFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1207x9a80aa4(this.swigCPtr, this);
    }
}
