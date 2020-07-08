package com.visioglobe.libVisioMove;

public class VgIResourceCallbackRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIResourceCallbackRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceCallbackRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIResourceCallbackRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIResourceCallbackRefPtr() {
        this(libVisioMoveJNI.new_VgIResourceCallbackRefPtr__SWIG_0(), true);
    }

    public VgIResourceCallbackRefPtr(VgIResourceCallback pPointer) {
        this(libVisioMoveJNI.new_VgIResourceCallbackRefPtr__SWIG_1(VgIResourceCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIResourceCallbackRefPtr(VgIResourceCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIResourceCallbackRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIResourceCallbackRefPtr getNull() {
        return new VgIResourceCallbackRefPtr(libVisioMoveJNI.VgIResourceCallbackRefPtr_getNull(), true);
    }

    public VgIResourceCallbackRefPtr set(VgIResourceCallback pPointer) {
        return new VgIResourceCallbackRefPtr(libVisioMoveJNI.VgIResourceCallbackRefPtr_set(this.swigCPtr, this, VgIResourceCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIResourceCallback __ref__() {
        return new VgIResourceCallback(libVisioMoveJNI.VgIResourceCallbackRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIResourceCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIResourceCallbackRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceCallback(cPtr, false);
    }

    public VgIResourceCallback get() {
        long cPtr = libVisioMoveJNI.VgIResourceCallbackRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIResourceCallbackRefPtr_isValid(this.swigCPtr, this);
    }

    public void notifyResource(VgResourceRequestStatus pStatus, String pResourceURI, VgBinaryBuffer pBuffer) {
        libVisioMoveJNI.VgIResourceCallbackRefPtr_notifyResource(this.swigCPtr, this, pStatus.swigValue(), pResourceURI, VgBinaryBuffer.getCPtr(pBuffer), pBuffer);
    }

    public void ref() {
        libVisioMoveJNI.VgIResourceCallbackRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIResourceCallbackRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIResourceCallbackRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
