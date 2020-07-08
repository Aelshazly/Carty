package com.visioglobe.libVisioMove;

public class VgIGeometryCallbackRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIGeometryCallbackRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometryCallbackRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIGeometryCallbackRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIGeometryCallbackRefPtr() {
        this(libVisioMoveJNI.new_VgIGeometryCallbackRefPtr__SWIG_0(), true);
    }

    public VgIGeometryCallbackRefPtr(VgIGeometryCallback pPointer) {
        this(libVisioMoveJNI.new_VgIGeometryCallbackRefPtr__SWIG_1(VgIGeometryCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIGeometryCallbackRefPtr(VgIGeometryCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIGeometryCallbackRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIGeometryCallbackRefPtr getNull() {
        return new VgIGeometryCallbackRefPtr(libVisioMoveJNI.VgIGeometryCallbackRefPtr_getNull(), true);
    }

    public VgIGeometryCallbackRefPtr set(VgIGeometryCallback pPointer) {
        return new VgIGeometryCallbackRefPtr(libVisioMoveJNI.VgIGeometryCallbackRefPtr_set(this.swigCPtr, this, VgIGeometryCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIGeometryCallback __ref__() {
        return new VgIGeometryCallback(libVisioMoveJNI.VgIGeometryCallbackRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIGeometryCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIGeometryCallbackRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometryCallback(cPtr, false);
    }

    public VgIGeometryCallback get() {
        long cPtr = libVisioMoveJNI.VgIGeometryCallbackRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometryCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIGeometryCallbackRefPtr_isValid(this.swigCPtr, this);
    }

    public void handleGeometryEvent(VgIGeometryEvent pEvent) {
        libVisioMoveJNI.VgIGeometryCallbackRefPtr_handleGeometryEvent(this.swigCPtr, this, VgIGeometryEvent.getCPtr(pEvent), pEvent);
    }

    public void ref() {
        libVisioMoveJNI.VgIGeometryCallbackRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIGeometryCallbackRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIGeometryCallbackRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
