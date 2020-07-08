package com.visioglobe.libVisioMove;

public class VgReferencedConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgReferencedConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgReferencedConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgReferencedConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgReferencedConstRefPtr() {
        this(libVisioMoveJNI.new_VgReferencedConstRefPtr__SWIG_0(), true);
    }

    public VgReferencedConstRefPtr(VgReferenced pPointer) {
        this(libVisioMoveJNI.new_VgReferencedConstRefPtr__SWIG_1(VgReferenced.getCPtr(pPointer), pPointer), true);
    }

    public VgReferencedConstRefPtr(VgReferencedConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgReferencedConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgReferencedConstRefPtr getNull() {
        return new VgReferencedConstRefPtr(libVisioMoveJNI.VgReferencedConstRefPtr_getNull(), true);
    }

    public VgReferencedConstRefPtr set(VgReferenced pPointer) {
        return new VgReferencedConstRefPtr(libVisioMoveJNI.VgReferencedConstRefPtr_set(this.swigCPtr, this, VgReferenced.getCPtr(pPointer), pPointer), false);
    }

    public VgReferenced __ref__() {
        return new VgReferenced(libVisioMoveJNI.VgReferencedConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgReferenced __deref__() {
        long cPtr = libVisioMoveJNI.VgReferencedConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgReferenced(cPtr, false);
    }

    public VgReferenced get() {
        long cPtr = libVisioMoveJNI.VgReferencedConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgReferenced(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgReferencedConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgReferencedConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgReferencedConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgReferencedConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
