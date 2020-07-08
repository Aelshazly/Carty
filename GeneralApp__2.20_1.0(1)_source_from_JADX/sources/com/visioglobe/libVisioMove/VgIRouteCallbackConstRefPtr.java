package com.visioglobe.libVisioMove;

public class VgIRouteCallbackConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteCallbackConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteCallbackConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIRouteCallbackConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteCallbackConstRefPtr() {
        this(libVisioMoveJNI.new_VgIRouteCallbackConstRefPtr__SWIG_0(), true);
    }

    public VgIRouteCallbackConstRefPtr(VgIRouteCallback pPointer) {
        this(libVisioMoveJNI.new_VgIRouteCallbackConstRefPtr__SWIG_1(VgIRouteCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIRouteCallbackConstRefPtr(VgIRouteCallbackConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIRouteCallbackConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIRouteCallbackConstRefPtr getNull() {
        return new VgIRouteCallbackConstRefPtr(libVisioMoveJNI.VgIRouteCallbackConstRefPtr_getNull(), true);
    }

    public VgIRouteCallbackConstRefPtr set(VgIRouteCallback pPointer) {
        return new VgIRouteCallbackConstRefPtr(libVisioMoveJNI.VgIRouteCallbackConstRefPtr_set(this.swigCPtr, this, VgIRouteCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIRouteCallback __ref__() {
        return new VgIRouteCallback(libVisioMoveJNI.VgIRouteCallbackConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIRouteCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIRouteCallbackConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteCallback(cPtr, false);
    }

    public VgIRouteCallback get() {
        long cPtr = libVisioMoveJNI.VgIRouteCallbackConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIRouteCallbackConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIRouteCallbackConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIRouteCallbackConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIRouteCallbackConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
