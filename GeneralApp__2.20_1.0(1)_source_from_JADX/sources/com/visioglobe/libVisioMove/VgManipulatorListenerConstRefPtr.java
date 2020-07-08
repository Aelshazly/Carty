package com.visioglobe.libVisioMove;

public class VgManipulatorListenerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgManipulatorListenerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgManipulatorListenerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgManipulatorListenerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgManipulatorListenerConstRefPtr() {
        this(libVisioMoveJNI.new_VgManipulatorListenerConstRefPtr__SWIG_0(), true);
    }

    public VgManipulatorListenerConstRefPtr(VgManipulatorListener pPointer) {
        this(libVisioMoveJNI.new_VgManipulatorListenerConstRefPtr__SWIG_1(VgManipulatorListener.getCPtr(pPointer), pPointer), true);
    }

    public VgManipulatorListenerConstRefPtr(VgManipulatorListenerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgManipulatorListenerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgManipulatorListenerConstRefPtr getNull() {
        return new VgManipulatorListenerConstRefPtr(libVisioMoveJNI.VgManipulatorListenerConstRefPtr_getNull(), true);
    }

    public VgManipulatorListenerConstRefPtr set(VgManipulatorListener pPointer) {
        return new VgManipulatorListenerConstRefPtr(libVisioMoveJNI.VgManipulatorListenerConstRefPtr_set(this.swigCPtr, this, VgManipulatorListener.getCPtr(pPointer), pPointer), false);
    }

    public VgManipulatorListener __ref__() {
        return new VgManipulatorListener(libVisioMoveJNI.VgManipulatorListenerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgManipulatorListener __deref__() {
        long cPtr = libVisioMoveJNI.VgManipulatorListenerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgManipulatorListener(cPtr, false);
    }

    public VgManipulatorListener get() {
        long cPtr = libVisioMoveJNI.VgManipulatorListenerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgManipulatorListener(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgManipulatorListenerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgManipulatorListenerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgManipulatorListenerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgManipulatorListenerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
