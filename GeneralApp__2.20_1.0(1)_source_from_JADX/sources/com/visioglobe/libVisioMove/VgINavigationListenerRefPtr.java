package com.visioglobe.libVisioMove;

public class VgINavigationListenerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationListenerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationListenerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationListenerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationListenerRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationListenerRefPtr__SWIG_0(), true);
    }

    public VgINavigationListenerRefPtr(VgINavigationListener pPointer) {
        this(libVisioMoveJNI.new_VgINavigationListenerRefPtr__SWIG_1(VgINavigationListener.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationListenerRefPtr(VgINavigationListenerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationListenerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgINavigationListenerRefPtr getNull() {
        return new VgINavigationListenerRefPtr(libVisioMoveJNI.VgINavigationListenerRefPtr_getNull(), true);
    }

    public VgINavigationListenerRefPtr set(VgINavigationListener pPointer) {
        return new VgINavigationListenerRefPtr(libVisioMoveJNI.VgINavigationListenerRefPtr_set(this.swigCPtr, this, VgINavigationListener.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigationListener __ref__() {
        return new VgINavigationListener(libVisioMoveJNI.VgINavigationListenerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigationListener __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationListenerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationListener(cPtr, false);
    }

    public VgINavigationListener get() {
        long cPtr = libVisioMoveJNI.VgINavigationListenerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationListener(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationListenerRefPtr_isValid(this.swigCPtr, this);
    }

    public void notifyPositionUpdated(VgINavigationConstRefPtr pNavigation, VgPosition pPosition, double pTime) {
        libVisioMoveJNI.VgINavigationListenerRefPtr_notifyPositionUpdated(this.swigCPtr, this, VgINavigationConstRefPtr.getCPtr(pNavigation), pNavigation, VgPosition.getCPtr(pPosition), pPosition, pTime);
    }

    public void notifyNewInstruction(VgINavigationConstRefPtr pNavigation, long pIndex) {
        libVisioMoveJNI.VgINavigationListenerRefPtr_notifyNewInstruction(this.swigCPtr, this, VgINavigationConstRefPtr.getCPtr(pNavigation), pNavigation, pIndex);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationListenerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationListenerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationListenerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
