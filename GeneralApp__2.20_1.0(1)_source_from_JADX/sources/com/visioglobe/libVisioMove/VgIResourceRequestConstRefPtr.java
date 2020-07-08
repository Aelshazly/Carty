package com.visioglobe.libVisioMove;

public class VgIResourceRequestConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIResourceRequestConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceRequestConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIResourceRequestConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIResourceRequestConstRefPtr() {
        this(libVisioMoveJNI.new_VgIResourceRequestConstRefPtr__SWIG_0(), true);
    }

    public VgIResourceRequestConstRefPtr(VgIResourceRequest pPointer) {
        this(libVisioMoveJNI.new_VgIResourceRequestConstRefPtr__SWIG_1(VgIResourceRequest.getCPtr(pPointer), pPointer), true);
    }

    public VgIResourceRequestConstRefPtr(VgIResourceRequestConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIResourceRequestConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIResourceRequestConstRefPtr getNull() {
        return new VgIResourceRequestConstRefPtr(libVisioMoveJNI.VgIResourceRequestConstRefPtr_getNull(), true);
    }

    public VgIResourceRequestConstRefPtr set(VgIResourceRequest pPointer) {
        return new VgIResourceRequestConstRefPtr(libVisioMoveJNI.VgIResourceRequestConstRefPtr_set(this.swigCPtr, this, VgIResourceRequest.getCPtr(pPointer), pPointer), false);
    }

    public VgIResourceRequest __ref__() {
        return new VgIResourceRequest(libVisioMoveJNI.VgIResourceRequestConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIResourceRequest __deref__() {
        long cPtr = libVisioMoveJNI.VgIResourceRequestConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceRequest(cPtr, false);
    }

    public VgIResourceRequest get() {
        long cPtr = libVisioMoveJNI.VgIResourceRequestConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceRequest(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIResourceRequestConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIResourceRequestConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIResourceRequestConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIResourceRequestConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
