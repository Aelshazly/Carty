package com.visioglobe.libVisioMove;

public class VgLinkConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLinkConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLinkConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLinkConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLinkConstRefPtr() {
        this(libVisioMoveJNI.new_VgLinkConstRefPtr__SWIG_0(), true);
    }

    public VgLinkConstRefPtr(VgLink pPointer) {
        this(libVisioMoveJNI.new_VgLinkConstRefPtr__SWIG_1(VgLink.getCPtr(pPointer), pPointer), true);
    }

    public VgLinkConstRefPtr(VgLinkConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLinkConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLinkConstRefPtr getNull() {
        return new VgLinkConstRefPtr(libVisioMoveJNI.VgLinkConstRefPtr_getNull(), true);
    }

    public VgLinkConstRefPtr set(VgLink pPointer) {
        return new VgLinkConstRefPtr(libVisioMoveJNI.VgLinkConstRefPtr_set(this.swigCPtr, this, VgLink.getCPtr(pPointer), pPointer), false);
    }

    public VgLink __ref__() {
        return new VgLink(libVisioMoveJNI.VgLinkConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLink __deref__() {
        long cPtr = libVisioMoveJNI.VgLinkConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLink(cPtr, false);
    }

    public VgLink get() {
        long cPtr = libVisioMoveJNI.VgLinkConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLink(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLinkConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLinkConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLinkConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLinkConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
