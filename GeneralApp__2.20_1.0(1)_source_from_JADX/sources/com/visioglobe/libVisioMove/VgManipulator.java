package com.visioglobe.libVisioMove;

public class VgManipulator {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgManipulator(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgManipulator obj) {
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
                libVisioMoveJNI.delete_VgManipulator(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public String getType() {
        return libVisioMoveJNI.VgManipulator_getType(this.swigCPtr, this);
    }

    public boolean getBoundaries(VgPosition pMinimumPosition, VgPosition pMaximumPosition) {
        return libVisioMoveJNI.VgManipulator_getBoundaries(this.swigCPtr, this, VgPosition.getCPtr(pMinimumPosition), pMinimumPosition, VgPosition.getCPtr(pMaximumPosition), pMaximumPosition);
    }

    public boolean setBoundaries(VgPosition pMinimumPosition, VgPosition pMaximumPosition) {
        return libVisioMoveJNI.VgManipulator_setBoundaries__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pMinimumPosition), pMinimumPosition, VgPosition.getCPtr(pMaximumPosition), pMaximumPosition);
    }

    public boolean setBoundaries(VgPositionVector pPositions, boolean pAutoComputeMaxAltitude) {
        return libVisioMoveJNI.VgManipulator_setBoundaries__SWIG_1(this.swigCPtr, this, VgPositionVector.getCPtr(pPositions), pPositions, pAutoComputeMaxAltitude);
    }

    public void setListener(VgManipulatorListenerRefPtr pListener) {
        libVisioMoveJNI.VgManipulator_setListener(this.swigCPtr, this, VgManipulatorListenerRefPtr.getCPtr(pListener), pListener);
    }

    public void setAnchor(VgPosition pAnchor) {
        libVisioMoveJNI.VgManipulator_setAnchor(this.swigCPtr, this, VgPosition.getCPtr(pAnchor), pAnchor);
    }

    public void setMinRadius(double pMinRadius) {
        libVisioMoveJNI.VgManipulator_setMinRadius(this.swigCPtr, this, pMinRadius);
    }

    public void setMaxRadius(double pMaxRadius) {
        libVisioMoveJNI.VgManipulator_setMaxRadius(this.swigCPtr, this, pMaxRadius);
    }
}
