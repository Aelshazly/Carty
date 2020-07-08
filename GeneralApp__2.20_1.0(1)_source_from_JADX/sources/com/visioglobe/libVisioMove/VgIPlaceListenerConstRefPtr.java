package com.visioglobe.libVisioMove;

public class VgIPlaceListenerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIPlaceListenerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIPlaceListenerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIPlaceListenerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIPlaceListenerConstRefPtr() {
        this(libVisioMoveJNI.new_VgIPlaceListenerConstRefPtr__SWIG_0(), true);
    }

    public VgIPlaceListenerConstRefPtr(VgIPlaceListener pPointer) {
        this(libVisioMoveJNI.new_VgIPlaceListenerConstRefPtr__SWIG_1(VgIPlaceListener.getCPtr(pPointer), pPointer), true);
    }

    public VgIPlaceListenerConstRefPtr(VgIPlaceListenerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIPlaceListenerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIPlaceListenerConstRefPtr getNull() {
        return new VgIPlaceListenerConstRefPtr(libVisioMoveJNI.VgIPlaceListenerConstRefPtr_getNull(), true);
    }

    public VgIPlaceListenerConstRefPtr set(VgIPlaceListener pPointer) {
        return new VgIPlaceListenerConstRefPtr(libVisioMoveJNI.VgIPlaceListenerConstRefPtr_set(this.swigCPtr, this, VgIPlaceListener.getCPtr(pPointer), pPointer), false);
    }

    public VgIPlaceListener __ref__() {
        return new VgIPlaceListener(libVisioMoveJNI.VgIPlaceListenerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIPlaceListener __deref__() {
        long cPtr = libVisioMoveJNI.VgIPlaceListenerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIPlaceListener(cPtr, false);
    }

    public VgIPlaceListener get() {
        long cPtr = libVisioMoveJNI.VgIPlaceListenerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIPlaceListener(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIPlaceListenerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIPlaceListenerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIPlaceListenerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIPlaceListenerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
