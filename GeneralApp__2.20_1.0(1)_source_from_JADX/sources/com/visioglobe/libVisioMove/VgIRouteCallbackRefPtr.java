package com.visioglobe.libVisioMove;

public class VgIRouteCallbackRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteCallbackRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteCallbackRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIRouteCallbackRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteCallbackRefPtr() {
        this(libVisioMoveJNI.new_VgIRouteCallbackRefPtr__SWIG_0(), true);
    }

    public VgIRouteCallbackRefPtr(VgIRouteCallback pPointer) {
        this(libVisioMoveJNI.new_VgIRouteCallbackRefPtr__SWIG_1(VgIRouteCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIRouteCallbackRefPtr(VgIRouteCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIRouteCallbackRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIRouteCallbackRefPtr getNull() {
        return new VgIRouteCallbackRefPtr(libVisioMoveJNI.VgIRouteCallbackRefPtr_getNull(), true);
    }

    public VgIRouteCallbackRefPtr set(VgIRouteCallback pPointer) {
        return new VgIRouteCallbackRefPtr(libVisioMoveJNI.VgIRouteCallbackRefPtr_set(this.swigCPtr, this, VgIRouteCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIRouteCallback __ref__() {
        return new VgIRouteCallback(libVisioMoveJNI.VgIRouteCallbackRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIRouteCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIRouteCallbackRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteCallback(cPtr, false);
    }

    public VgIRouteCallback get() {
        long cPtr = libVisioMoveJNI.VgIRouteCallbackRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIRouteCallbackRefPtr_isValid(this.swigCPtr, this);
    }

    public void notifyRouteComputed(VgRouteRequestStatus pStatus, VgIRouteRefPtr pRoute) {
        libVisioMoveJNI.VgIRouteCallbackRefPtr_notifyRouteComputed(this.swigCPtr, this, pStatus.swigValue(), VgIRouteRefPtr.getCPtr(pRoute), pRoute);
    }

    public void ref() {
        libVisioMoveJNI.VgIRouteCallbackRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIRouteCallbackRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIRouteCallbackRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
