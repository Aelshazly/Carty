package com.visioglobe.libVisioMove;

public class VgIPlaceListenerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIPlaceListenerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIPlaceListenerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIPlaceListenerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIPlaceListenerRefPtr() {
        this(libVisioMoveJNI.new_VgIPlaceListenerRefPtr__SWIG_0(), true);
    }

    public VgIPlaceListenerRefPtr(VgIPlaceListener pPointer) {
        this(libVisioMoveJNI.new_VgIPlaceListenerRefPtr__SWIG_1(VgIPlaceListener.getCPtr(pPointer), pPointer), true);
    }

    public VgIPlaceListenerRefPtr(VgIPlaceListenerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIPlaceListenerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIPlaceListenerRefPtr getNull() {
        return new VgIPlaceListenerRefPtr(libVisioMoveJNI.VgIPlaceListenerRefPtr_getNull(), true);
    }

    public VgIPlaceListenerRefPtr set(VgIPlaceListener pPointer) {
        return new VgIPlaceListenerRefPtr(libVisioMoveJNI.VgIPlaceListenerRefPtr_set(this.swigCPtr, this, VgIPlaceListener.getCPtr(pPointer), pPointer), false);
    }

    public VgIPlaceListener __ref__() {
        return new VgIPlaceListener(libVisioMoveJNI.VgIPlaceListenerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIPlaceListener __deref__() {
        long cPtr = libVisioMoveJNI.VgIPlaceListenerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIPlaceListener(cPtr, false);
    }

    public VgIPlaceListener get() {
        long cPtr = libVisioMoveJNI.VgIPlaceListenerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIPlaceListener(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIPlaceListenerRefPtr_isValid(this.swigCPtr, this);
    }

    public void notifyPlaceSelected(VgIApplication pVgApplication, String pID, VgPosition pPosition) {
        libVisioMoveJNI.VgIPlaceListenerRefPtr_notifyPlaceSelected(this.swigCPtr, this, VgIApplication.getCPtr(pVgApplication), pVgApplication, pID, VgPosition.getCPtr(pPosition), pPosition);
    }

    public void notifyMapDatabaseLoaded(VgIApplication pVgApplication) {
        libVisioMoveJNI.VgIPlaceListenerRefPtr_notifyMapDatabaseLoaded(this.swigCPtr, this, VgIApplication.getCPtr(pVgApplication), pVgApplication);
    }

    public void ref() {
        libVisioMoveJNI.VgIPlaceListenerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIPlaceListenerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIPlaceListenerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
