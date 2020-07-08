package com.visioglobe.libVisioMove;

public class VgINavigationListenerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationListenerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationListenerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationListenerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationListenerConstRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationListenerConstRefPtr__SWIG_0(), true);
    }

    public VgINavigationListenerConstRefPtr(VgINavigationListener pPointer) {
        this(libVisioMoveJNI.new_VgINavigationListenerConstRefPtr__SWIG_1(VgINavigationListener.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationListenerConstRefPtr(VgINavigationListenerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationListenerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgINavigationListenerConstRefPtr getNull() {
        return new VgINavigationListenerConstRefPtr(libVisioMoveJNI.VgINavigationListenerConstRefPtr_getNull(), true);
    }

    public VgINavigationListenerConstRefPtr set(VgINavigationListener pPointer) {
        return new VgINavigationListenerConstRefPtr(libVisioMoveJNI.VgINavigationListenerConstRefPtr_set(this.swigCPtr, this, VgINavigationListener.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigationListener __ref__() {
        return new VgINavigationListener(libVisioMoveJNI.VgINavigationListenerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigationListener __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationListenerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationListener(cPtr, false);
    }

    public VgINavigationListener get() {
        long cPtr = libVisioMoveJNI.VgINavigationListenerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationListener(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationListenerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationListenerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationListenerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationListenerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
