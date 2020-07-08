package com.visioglobe.libVisioMove;

public class VgLinkRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLinkRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLinkRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLinkRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLinkRefPtr() {
        this(libVisioMoveJNI.new_VgLinkRefPtr__SWIG_0(), true);
    }

    public VgLinkRefPtr(VgLink pPointer) {
        this(libVisioMoveJNI.new_VgLinkRefPtr__SWIG_1(VgLink.getCPtr(pPointer), pPointer), true);
    }

    public VgLinkRefPtr(VgLinkRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLinkRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLinkRefPtr getNull() {
        return new VgLinkRefPtr(libVisioMoveJNI.VgLinkRefPtr_getNull(), true);
    }

    public VgLinkRefPtr set(VgLink pPointer) {
        return new VgLinkRefPtr(libVisioMoveJNI.VgLinkRefPtr_set(this.swigCPtr, this, VgLink.getCPtr(pPointer), pPointer), false);
    }

    public VgLink __ref__() {
        return new VgLink(libVisioMoveJNI.VgLinkRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLink __deref__() {
        long cPtr = libVisioMoveJNI.VgLinkRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLink(cPtr, false);
    }

    public VgLink get() {
        long cPtr = libVisioMoveJNI.VgLinkRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLink(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLinkRefPtr_isValid(this.swigCPtr, this);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgLinkRefPtr_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public void ref() {
        libVisioMoveJNI.VgLinkRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLinkRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLinkRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
