package com.visioglobe.libVisioMove;

public class VgAxialRotationQuaternionFunctorDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAxialRotationQuaternionFunctorDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAxialRotationQuaternionFunctorDescriptorConstRefPtr() {
        this(libVisioMoveJNI.m1254xe8918578(), true);
    }

    public VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(VgAxialRotationQuaternionFunctorDescriptor pPointer) {
        this(libVisioMoveJNI.m1255xe8918579(VgAxialRotationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(VgAxialRotationQuaternionFunctorDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.m1256xe891857a(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgAxialRotationQuaternionFunctorDescriptorConstRefPtr getNull() {
        return new VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_getNull(), true);
    }

    public VgAxialRotationQuaternionFunctorDescriptorConstRefPtr set(VgAxialRotationQuaternionFunctorDescriptor pPointer) {
        return new VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_set(this.swigCPtr, this, VgAxialRotationQuaternionFunctorDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgAxialRotationQuaternionFunctorDescriptor __ref__() {
        return new VgAxialRotationQuaternionFunctorDescriptor(libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgAxialRotationQuaternionFunctorDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAxialRotationQuaternionFunctorDescriptor(cPtr, false);
    }

    public VgAxialRotationQuaternionFunctorDescriptor get() {
        long cPtr = libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAxialRotationQuaternionFunctorDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgOrientation getMPreOrientation() {
        long cPtr = libVisioMoveJNI.m1190x6cbf6016(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgOrientation(cPtr, false);
    }

    public float[] getMAxis() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mAxis_get(this.swigCPtr, this);
    }

    public float getMStartAngle() {
        return libVisioMoveJNI.m1191x680b017a(this.swigCPtr, this);
    }

    public float getMEndAngle() {
        return libVisioMoveJNI.m1188x155181a1(this.swigCPtr, this);
    }

    public boolean getMCubic() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mCubic_get(this.swigCPtr, this);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.m1192x4bcc554(this.swigCPtr, this);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.m1189x4422244d(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.m1187x1750cdd7(this.swigCPtr, this);
    }
}
