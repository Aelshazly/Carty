package com.visioglobe.libVisioMove;

public class VgManipulatorListenerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgManipulatorListenerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgManipulatorListenerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgManipulatorListenerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgManipulatorListenerRefPtr() {
        this(libVisioMoveJNI.new_VgManipulatorListenerRefPtr__SWIG_0(), true);
    }

    public VgManipulatorListenerRefPtr(VgManipulatorListener pPointer) {
        this(libVisioMoveJNI.new_VgManipulatorListenerRefPtr__SWIG_1(VgManipulatorListener.getCPtr(pPointer), pPointer), true);
    }

    public VgManipulatorListenerRefPtr(VgManipulatorListenerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgManipulatorListenerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgManipulatorListenerRefPtr getNull() {
        return new VgManipulatorListenerRefPtr(libVisioMoveJNI.VgManipulatorListenerRefPtr_getNull(), true);
    }

    public VgManipulatorListenerRefPtr set(VgManipulatorListener pPointer) {
        return new VgManipulatorListenerRefPtr(libVisioMoveJNI.VgManipulatorListenerRefPtr_set(this.swigCPtr, this, VgManipulatorListener.getCPtr(pPointer), pPointer), false);
    }

    public VgManipulatorListener __ref__() {
        return new VgManipulatorListener(libVisioMoveJNI.VgManipulatorListenerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgManipulatorListener __deref__() {
        long cPtr = libVisioMoveJNI.VgManipulatorListenerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgManipulatorListener(cPtr, false);
    }

    public VgManipulatorListener get() {
        long cPtr = libVisioMoveJNI.VgManipulatorListenerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgManipulatorListener(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgManipulatorListenerRefPtr_isValid(this.swigCPtr, this);
    }

    public void onSimplePinch(VgGestureState pState, float pSpan, float pVelocity) {
        libVisioMoveJNI.VgManipulatorListenerRefPtr_onSimplePinch(this.swigCPtr, this, pState.swigValue(), pSpan, pVelocity);
    }

    public void onSimpleDrag(VgGestureState pState, long pNbTouch, float pAverageXOffset, float pAverageYOffset) {
        libVisioMoveJNI.VgManipulatorListenerRefPtr_onSimpleDrag(this.swigCPtr, this, pState.swigValue(), pNbTouch, pAverageXOffset, pAverageYOffset);
    }

    public void onClick(float x, float y) {
        libVisioMoveJNI.VgManipulatorListenerRefPtr_onClick(this.swigCPtr, this, x, y);
    }

    public void onDoubleClick(float x, float y) {
        libVisioMoveJNI.VgManipulatorListenerRefPtr_onDoubleClick(this.swigCPtr, this, x, y);
    }

    public void ref() {
        libVisioMoveJNI.VgManipulatorListenerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgManipulatorListenerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgManipulatorListenerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
