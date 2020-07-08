package com.visioglobe.libVisioMove;

public class VgIGeometryCallbackConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIGeometryCallbackConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometryCallbackConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIGeometryCallbackConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIGeometryCallbackConstRefPtr() {
        this(libVisioMoveJNI.new_VgIGeometryCallbackConstRefPtr__SWIG_0(), true);
    }

    public VgIGeometryCallbackConstRefPtr(VgIGeometryCallback pPointer) {
        this(libVisioMoveJNI.new_VgIGeometryCallbackConstRefPtr__SWIG_1(VgIGeometryCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIGeometryCallbackConstRefPtr(VgIGeometryCallbackConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIGeometryCallbackConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIGeometryCallbackConstRefPtr getNull() {
        return new VgIGeometryCallbackConstRefPtr(libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_getNull(), true);
    }

    public VgIGeometryCallbackConstRefPtr set(VgIGeometryCallback pPointer) {
        return new VgIGeometryCallbackConstRefPtr(libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_set(this.swigCPtr, this, VgIGeometryCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIGeometryCallback __ref__() {
        return new VgIGeometryCallback(libVisioMoveJNI.VgIGeometryCallbackConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIGeometryCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIGeometryCallbackConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometryCallback(cPtr, false);
    }

    public VgIGeometryCallback get() {
        long cPtr = libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometryCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIGeometryCallbackConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
