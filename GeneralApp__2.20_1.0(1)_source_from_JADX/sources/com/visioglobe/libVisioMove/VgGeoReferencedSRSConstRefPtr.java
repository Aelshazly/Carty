package com.visioglobe.libVisioMove;

public class VgGeoReferencedSRSConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgGeoReferencedSRSConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgGeoReferencedSRSConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgGeoReferencedSRSConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgGeoReferencedSRSConstRefPtr() {
        this(libVisioMoveJNI.new_VgGeoReferencedSRSConstRefPtr__SWIG_0(), true);
    }

    public VgGeoReferencedSRSConstRefPtr(VgGeoReferencedSRS pPointer) {
        this(libVisioMoveJNI.new_VgGeoReferencedSRSConstRefPtr__SWIG_1(VgGeoReferencedSRS.getCPtr(pPointer), pPointer), true);
    }

    public VgGeoReferencedSRSConstRefPtr(VgGeoReferencedSRSConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgGeoReferencedSRSConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgGeoReferencedSRSConstRefPtr getNull() {
        return new VgGeoReferencedSRSConstRefPtr(libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_getNull(), true);
    }

    public VgGeoReferencedSRSConstRefPtr set(VgGeoReferencedSRS pPointer) {
        return new VgGeoReferencedSRSConstRefPtr(libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_set(this.swigCPtr, this, VgGeoReferencedSRS.getCPtr(pPointer), pPointer), false);
    }

    public VgGeoReferencedSRS __ref__() {
        return new VgGeoReferencedSRS(libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgGeoReferencedSRS __deref__() {
        long cPtr = libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgGeoReferencedSRS(cPtr, false);
    }

    public VgGeoReferencedSRS get() {
        long cPtr = libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgGeoReferencedSRS(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_isGeoReferenced(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgGeoReferencedSRSConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
