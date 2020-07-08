package com.visioglobe.libVisioMove;

public class VgINavigation extends VgReferenced {
    private long swigCPtr;

    protected VgINavigation(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgINavigation_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigation obj) {
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
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public void addListener(VgINavigationListenerRefPtr pListener) {
        libVisioMoveJNI.VgINavigation_addListener(this.swigCPtr, this, VgINavigationListenerRefPtr.getCPtr(pListener), pListener);
    }

    public void removeListener(VgINavigationListenerRefPtr pListener) {
        libVisioMoveJNI.VgINavigation_removeListener(this.swigCPtr, this, VgINavigationListenerRefPtr.getCPtr(pListener), pListener);
    }

    public VgINavigationInstructionConstRefPtr getInstruction(long pIndex) {
        return new VgINavigationInstructionConstRefPtr(libVisioMoveJNI.VgINavigation_getInstruction(this.swigCPtr, this, pIndex), true);
    }

    public long getNumInstructions() {
        return libVisioMoveJNI.VgINavigation_getNumInstructions(this.swigCPtr, this);
    }

    public void updateCurrentPosition(VgPosition pPosition, double pTime) {
        libVisioMoveJNI.VgINavigation_updateCurrentPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pTime);
    }

    public VgPosition getCurrentPosition() {
        return new VgPosition(libVisioMoveJNI.VgINavigation_getCurrentPosition(this.swigCPtr, this), false);
    }

    public long getCurrentInstructionIndex() {
        return libVisioMoveJNI.VgINavigation_getCurrentInstructionIndex(this.swigCPtr, this);
    }

    public VgPosition getClosestPositionOnRoute() {
        return new VgPosition(libVisioMoveJNI.VgINavigation_getClosestPositionOnRoute(this.swigCPtr, this), false);
    }

    public double getDistanceFromRoute() {
        return libVisioMoveJNI.VgINavigation_getDistanceFromRoute(this.swigCPtr, this);
    }

    public long getCurrentInstructionClosestPositionNextSubIndex() {
        return libVisioMoveJNI.VgINavigation_getCurrentInstructionClosestPositionNextSubIndex(this.swigCPtr, this);
    }

    public void setInstructionGeofenceDistance(double pDistanceInMeters) {
        libVisioMoveJNI.VgINavigation_setInstructionGeofenceDistance(this.swigCPtr, this, pDistanceInMeters);
    }

    public double getInstructionGeofenceDistance() {
        return libVisioMoveJNI.VgINavigation_getInstructionGeofenceDistance(this.swigCPtr, this);
    }

    public VgINavigationRequestParameters getRequestParameters() {
        return new VgINavigationRequestParameters(libVisioMoveJNI.VgINavigation_getRequestParameters(this.swigCPtr, this), false);
    }
}
