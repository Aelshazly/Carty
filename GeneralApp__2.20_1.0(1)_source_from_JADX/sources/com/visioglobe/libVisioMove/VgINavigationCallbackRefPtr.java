package com.visioglobe.libVisioMove;

public class VgINavigationCallbackRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationCallbackRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationCallbackRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationCallbackRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationCallbackRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationCallbackRefPtr__SWIG_0(), true);
    }

    public VgINavigationCallbackRefPtr(VgINavigationCallback pPointer) {
        this(libVisioMoveJNI.new_VgINavigationCallbackRefPtr__SWIG_1(VgINavigationCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationCallbackRefPtr(VgINavigationCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationCallbackRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgINavigationCallbackRefPtr getNull() {
        return new VgINavigationCallbackRefPtr(libVisioMoveJNI.VgINavigationCallbackRefPtr_getNull(), true);
    }

    public VgINavigationCallbackRefPtr set(VgINavigationCallback pPointer) {
        return new VgINavigationCallbackRefPtr(libVisioMoveJNI.VgINavigationCallbackRefPtr_set(this.swigCPtr, this, VgINavigationCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigationCallback __ref__() {
        return new VgINavigationCallback(libVisioMoveJNI.VgINavigationCallbackRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigationCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationCallbackRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationCallback(cPtr, false);
    }

    public VgINavigationCallback get() {
        long cPtr = libVisioMoveJNI.VgINavigationCallbackRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationCallbackRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean notifyNavigationComputed(VgNavigationRequestStatus pStatus, VgINavigationRefPtr pNavigation) {
        return libVisioMoveJNI.VgINavigationCallbackRefPtr_notifyNavigationComputed(this.swigCPtr, this, pStatus.swigValue(), VgINavigationRefPtr.getCPtr(pNavigation), pNavigation);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationCallbackRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationCallbackRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationCallbackRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
