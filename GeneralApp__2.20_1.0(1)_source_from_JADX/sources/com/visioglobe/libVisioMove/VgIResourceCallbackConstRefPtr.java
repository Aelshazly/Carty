package com.visioglobe.libVisioMove;

public class VgIResourceCallbackConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIResourceCallbackConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceCallbackConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIResourceCallbackConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIResourceCallbackConstRefPtr() {
        this(libVisioMoveJNI.new_VgIResourceCallbackConstRefPtr__SWIG_0(), true);
    }

    public VgIResourceCallbackConstRefPtr(VgIResourceCallback pPointer) {
        this(libVisioMoveJNI.new_VgIResourceCallbackConstRefPtr__SWIG_1(VgIResourceCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIResourceCallbackConstRefPtr(VgIResourceCallbackConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIResourceCallbackConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIResourceCallbackConstRefPtr getNull() {
        return new VgIResourceCallbackConstRefPtr(libVisioMoveJNI.VgIResourceCallbackConstRefPtr_getNull(), true);
    }

    public VgIResourceCallbackConstRefPtr set(VgIResourceCallback pPointer) {
        return new VgIResourceCallbackConstRefPtr(libVisioMoveJNI.VgIResourceCallbackConstRefPtr_set(this.swigCPtr, this, VgIResourceCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIResourceCallback __ref__() {
        return new VgIResourceCallback(libVisioMoveJNI.VgIResourceCallbackConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIResourceCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIResourceCallbackConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceCallback(cPtr, false);
    }

    public VgIResourceCallback get() {
        long cPtr = libVisioMoveJNI.VgIResourceCallbackConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIResourceCallbackConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIResourceCallbackConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIResourceCallbackConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIResourceCallbackConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
