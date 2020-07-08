package com.visioglobe.libVisioMove;

public class VgINavigationCallbackConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationCallbackConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationCallbackConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationCallbackConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationCallbackConstRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationCallbackConstRefPtr__SWIG_0(), true);
    }

    public VgINavigationCallbackConstRefPtr(VgINavigationCallback pPointer) {
        this(libVisioMoveJNI.new_VgINavigationCallbackConstRefPtr__SWIG_1(VgINavigationCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationCallbackConstRefPtr(VgINavigationCallbackConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationCallbackConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgINavigationCallbackConstRefPtr getNull() {
        return new VgINavigationCallbackConstRefPtr(libVisioMoveJNI.VgINavigationCallbackConstRefPtr_getNull(), true);
    }

    public VgINavigationCallbackConstRefPtr set(VgINavigationCallback pPointer) {
        return new VgINavigationCallbackConstRefPtr(libVisioMoveJNI.VgINavigationCallbackConstRefPtr_set(this.swigCPtr, this, VgINavigationCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigationCallback __ref__() {
        return new VgINavigationCallback(libVisioMoveJNI.VgINavigationCallbackConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigationCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationCallbackConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationCallback(cPtr, false);
    }

    public VgINavigationCallback get() {
        long cPtr = libVisioMoveJNI.VgINavigationCallbackConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationCallbackConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationCallbackConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationCallbackConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationCallbackConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
