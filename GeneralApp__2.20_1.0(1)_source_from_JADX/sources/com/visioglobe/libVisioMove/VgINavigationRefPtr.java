package com.visioglobe.libVisioMove;

public class VgINavigationRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationRefPtr__SWIG_0(), true);
    }

    public VgINavigationRefPtr(VgINavigation pPointer) {
        this(libVisioMoveJNI.new_VgINavigationRefPtr__SWIG_1(VgINavigation.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationRefPtr(VgINavigationRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgINavigationRefPtr getNull() {
        return new VgINavigationRefPtr(libVisioMoveJNI.VgINavigationRefPtr_getNull(), true);
    }

    public VgINavigationRefPtr set(VgINavigation pPointer) {
        return new VgINavigationRefPtr(libVisioMoveJNI.VgINavigationRefPtr_set(this.swigCPtr, this, VgINavigation.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigation __ref__() {
        return new VgINavigation(libVisioMoveJNI.VgINavigationRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigation __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigation(cPtr, false);
    }

    public VgINavigation get() {
        long cPtr = libVisioMoveJNI.VgINavigationRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigation(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationRefPtr_isValid(this.swigCPtr, this);
    }

    public void addListener(VgINavigationListenerRefPtr pListener) {
        libVisioMoveJNI.VgINavigationRefPtr_addListener(this.swigCPtr, this, VgINavigationListenerRefPtr.getCPtr(pListener), pListener);
    }

    public void removeListener(VgINavigationListenerRefPtr pListener) {
        libVisioMoveJNI.VgINavigationRefPtr_removeListener(this.swigCPtr, this, VgINavigationListenerRefPtr.getCPtr(pListener), pListener);
    }

    public VgINavigationInstructionConstRefPtr getInstruction(long pIndex) {
        return new VgINavigationInstructionConstRefPtr(libVisioMoveJNI.VgINavigationRefPtr_getInstruction(this.swigCPtr, this, pIndex), true);
    }

    public long getNumInstructions() {
        return libVisioMoveJNI.VgINavigationRefPtr_getNumInstructions(this.swigCPtr, this);
    }

    public void updateCurrentPosition(VgPosition pPosition, double pTime) {
        libVisioMoveJNI.VgINavigationRefPtr_updateCurrentPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pTime);
    }

    public VgPosition getCurrentPosition() {
        return new VgPosition(libVisioMoveJNI.VgINavigationRefPtr_getCurrentPosition(this.swigCPtr, this), false);
    }

    public long getCurrentInstructionIndex() {
        return libVisioMoveJNI.VgINavigationRefPtr_getCurrentInstructionIndex(this.swigCPtr, this);
    }

    public VgPosition getClosestPositionOnRoute() {
        return new VgPosition(libVisioMoveJNI.VgINavigationRefPtr_getClosestPositionOnRoute(this.swigCPtr, this), false);
    }

    public double getDistanceFromRoute() {
        return libVisioMoveJNI.VgINavigationRefPtr_getDistanceFromRoute(this.swigCPtr, this);
    }

    public long getCurrentInstructionClosestPositionNextSubIndex() {
        return libVisioMoveJNI.m1204x270a58f4(this.swigCPtr, this);
    }

    public void setInstructionGeofenceDistance(double pDistanceInMeters) {
        libVisioMoveJNI.VgINavigationRefPtr_setInstructionGeofenceDistance(this.swigCPtr, this, pDistanceInMeters);
    }

    public double getInstructionGeofenceDistance() {
        return libVisioMoveJNI.VgINavigationRefPtr_getInstructionGeofenceDistance(this.swigCPtr, this);
    }

    public VgINavigationRequestParameters getRequestParameters() {
        return new VgINavigationRequestParameters(libVisioMoveJNI.VgINavigationRefPtr_getRequestParameters(this.swigCPtr, this), false);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
