package com.visioglobe.libVisioMove;

public class VgINavigationConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationConstRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationConstRefPtr__SWIG_0(), true);
    }

    public VgINavigationConstRefPtr(VgINavigation pPointer) {
        this(libVisioMoveJNI.new_VgINavigationConstRefPtr__SWIG_1(VgINavigation.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationConstRefPtr(VgINavigationConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgINavigationConstRefPtr getNull() {
        return new VgINavigationConstRefPtr(libVisioMoveJNI.VgINavigationConstRefPtr_getNull(), true);
    }

    public VgINavigationConstRefPtr set(VgINavigation pPointer) {
        return new VgINavigationConstRefPtr(libVisioMoveJNI.VgINavigationConstRefPtr_set(this.swigCPtr, this, VgINavigation.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigation __ref__() {
        return new VgINavigation(libVisioMoveJNI.VgINavigationConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigation __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigation(cPtr, false);
    }

    public VgINavigation get() {
        long cPtr = libVisioMoveJNI.VgINavigationConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigation(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgINavigationInstructionConstRefPtr getInstruction(long pIndex) {
        return new VgINavigationInstructionConstRefPtr(libVisioMoveJNI.VgINavigationConstRefPtr_getInstruction(this.swigCPtr, this, pIndex), true);
    }

    public long getNumInstructions() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_getNumInstructions(this.swigCPtr, this);
    }

    public VgPosition getCurrentPosition() {
        return new VgPosition(libVisioMoveJNI.VgINavigationConstRefPtr_getCurrentPosition(this.swigCPtr, this), false);
    }

    public long getCurrentInstructionIndex() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_getCurrentInstructionIndex(this.swigCPtr, this);
    }

    public VgPosition getClosestPositionOnRoute() {
        return new VgPosition(libVisioMoveJNI.VgINavigationConstRefPtr_getClosestPositionOnRoute(this.swigCPtr, this), false);
    }

    public double getDistanceFromRoute() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_getDistanceFromRoute(this.swigCPtr, this);
    }

    public long getCurrentInstructionClosestPositionNextSubIndex() {
        return libVisioMoveJNI.m1203x64ae4d69(this.swigCPtr, this);
    }

    public double getInstructionGeofenceDistance() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_getInstructionGeofenceDistance(this.swigCPtr, this);
    }

    public VgINavigationRequestParameters getRequestParameters() {
        return new VgINavigationRequestParameters(libVisioMoveJNI.VgINavigationConstRefPtr_getRequestParameters(this.swigCPtr, this), false);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
