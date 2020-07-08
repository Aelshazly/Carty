package com.visioglobe.libVisioMove;

public class VgReferenced {
    protected boolean swigCMemOwn;
    private volatile long swigCPtr;
    protected VgReferencedRefPtr swigRefPtr;

    protected VgReferenced(long cPtr, boolean cMemoryOwn) {
        this.swigRefPtr = VgReferencedRefPtr.getNull();
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
        if (this.swigCMemOwn) {
            this.swigRefPtr.set(this);
        }
    }

    protected static long getCPtr(VgReferenced obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    public synchronized void release() {
        delete();
        this.swigRefPtr.set(null);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            this.swigCPtr = 0;
        }
    }

    public VgReferenced() {
        this(libVisioMoveJNI.new_VgReferenced__SWIG_0(), true);
    }

    public VgReferenced(VgReferenced pThis) {
        this(libVisioMoveJNI.new_VgReferenced__SWIG_1(getCPtr(pThis), pThis), true);
    }

    public void ref() {
        libVisioMoveJNI.VgReferenced_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgReferenced_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgReferenced_getNbReferences(this.swigCPtr, this);
    }
}
