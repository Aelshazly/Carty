package com.visioglobe.libVisioMove;

public class VgSRSConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSRSConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSRSConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSRSConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSRSConstRefPtr() {
        this(libVisioMoveJNI.new_VgSRSConstRefPtr__SWIG_0(), true);
    }

    public VgSRSConstRefPtr(VgSRS pPointer) {
        this(libVisioMoveJNI.new_VgSRSConstRefPtr__SWIG_1(VgSRS.getCPtr(pPointer), pPointer), true);
    }

    public VgSRSConstRefPtr(VgSRSConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgSRSConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgSRSConstRefPtr getNull() {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgSRSConstRefPtr_getNull(), true);
    }

    public VgSRSConstRefPtr set(VgSRS pPointer) {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgSRSConstRefPtr_set(this.swigCPtr, this, VgSRS.getCPtr(pPointer), pPointer), false);
    }

    public VgSRS __ref__() {
        return new VgSRS(libVisioMoveJNI.VgSRSConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSRS __deref__() {
        long cPtr = libVisioMoveJNI.VgSRSConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSRS(cPtr, false);
    }

    public VgSRS get() {
        long cPtr = libVisioMoveJNI.VgSRSConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSRS(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSRSConstRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgSRSConstRefPtr_isGeoReferenced(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSRSConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSRSConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSRSConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
