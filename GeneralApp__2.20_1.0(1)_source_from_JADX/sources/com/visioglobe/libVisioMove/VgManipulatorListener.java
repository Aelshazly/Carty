package com.visioglobe.libVisioMove;

public class VgManipulatorListener extends VgReferenced {
    private long swigCPtr;

    protected VgManipulatorListener(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgManipulatorListener_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgManipulatorListener obj) {
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

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        libVisioMoveJNI.VgManipulatorListener_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgManipulatorListener_change_ownership(this, this.swigCPtr, true);
    }

    public void onSimplePinch(VgGestureState pState, float pSpan, float pVelocity) {
        libVisioMoveJNI.VgManipulatorListener_onSimplePinch(this.swigCPtr, this, pState.swigValue(), pSpan, pVelocity);
    }

    public void onSimpleDrag(VgGestureState pState, long pNbTouch, float pAverageXOffset, float pAverageYOffset) {
        libVisioMoveJNI.VgManipulatorListener_onSimpleDrag(this.swigCPtr, this, pState.swigValue(), pNbTouch, pAverageXOffset, pAverageYOffset);
    }

    public void onClick(float x, float y) {
        libVisioMoveJNI.VgManipulatorListener_onClick(this.swigCPtr, this, x, y);
    }

    public void onDoubleClick(float x, float y) {
        libVisioMoveJNI.VgManipulatorListener_onDoubleClick(this.swigCPtr, this, x, y);
    }

    public VgManipulatorListener() {
        this(libVisioMoveJNI.new_VgManipulatorListener(), false);
        libVisioMoveJNI.VgManipulatorListener_director_connect(this, this.swigCPtr, false, false);
    }
}
