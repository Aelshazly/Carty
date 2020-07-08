package com.visioglobe.libVisioMove;

public class VgIResourceRequestRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIResourceRequestRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceRequestRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIResourceRequestRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIResourceRequestRefPtr() {
        this(libVisioMoveJNI.new_VgIResourceRequestRefPtr__SWIG_0(), true);
    }

    public VgIResourceRequestRefPtr(VgIResourceRequest pPointer) {
        this(libVisioMoveJNI.new_VgIResourceRequestRefPtr__SWIG_1(VgIResourceRequest.getCPtr(pPointer), pPointer), true);
    }

    public VgIResourceRequestRefPtr(VgIResourceRequestRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIResourceRequestRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIResourceRequestRefPtr getNull() {
        return new VgIResourceRequestRefPtr(libVisioMoveJNI.VgIResourceRequestRefPtr_getNull(), true);
    }

    public VgIResourceRequestRefPtr set(VgIResourceRequest pPointer) {
        return new VgIResourceRequestRefPtr(libVisioMoveJNI.VgIResourceRequestRefPtr_set(this.swigCPtr, this, VgIResourceRequest.getCPtr(pPointer), pPointer), false);
    }

    public VgIResourceRequest __ref__() {
        return new VgIResourceRequest(libVisioMoveJNI.VgIResourceRequestRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIResourceRequest __deref__() {
        long cPtr = libVisioMoveJNI.VgIResourceRequestRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceRequest(cPtr, false);
    }

    public VgIResourceRequest get() {
        long cPtr = libVisioMoveJNI.VgIResourceRequestRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceRequest(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIResourceRequestRefPtr_isValid(this.swigCPtr, this);
    }

    public void cancel() {
        libVisioMoveJNI.VgIResourceRequestRefPtr_cancel(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIResourceRequestRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIResourceRequestRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIResourceRequestRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
