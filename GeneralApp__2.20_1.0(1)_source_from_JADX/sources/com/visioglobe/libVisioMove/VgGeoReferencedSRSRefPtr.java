package com.visioglobe.libVisioMove;

public class VgGeoReferencedSRSRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgGeoReferencedSRSRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgGeoReferencedSRSRefPtr obj) {
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
                libVisioMoveJNI.delete_VgGeoReferencedSRSRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgGeoReferencedSRSRefPtr() {
        this(libVisioMoveJNI.new_VgGeoReferencedSRSRefPtr__SWIG_0(), true);
    }

    public VgGeoReferencedSRSRefPtr(VgGeoReferencedSRS pPointer) {
        this(libVisioMoveJNI.new_VgGeoReferencedSRSRefPtr__SWIG_1(VgGeoReferencedSRS.getCPtr(pPointer), pPointer), true);
    }

    public VgGeoReferencedSRSRefPtr(VgGeoReferencedSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgGeoReferencedSRSRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgGeoReferencedSRSRefPtr getNull() {
        return new VgGeoReferencedSRSRefPtr(libVisioMoveJNI.VgGeoReferencedSRSRefPtr_getNull(), true);
    }

    public VgGeoReferencedSRSRefPtr set(VgGeoReferencedSRS pPointer) {
        return new VgGeoReferencedSRSRefPtr(libVisioMoveJNI.VgGeoReferencedSRSRefPtr_set(this.swigCPtr, this, VgGeoReferencedSRS.getCPtr(pPointer), pPointer), false);
    }

    public VgGeoReferencedSRS __ref__() {
        return new VgGeoReferencedSRS(libVisioMoveJNI.VgGeoReferencedSRSRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgGeoReferencedSRS __deref__() {
        long cPtr = libVisioMoveJNI.VgGeoReferencedSRSRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgGeoReferencedSRS(cPtr, false);
    }

    public VgGeoReferencedSRS get() {
        long cPtr = libVisioMoveJNI.VgGeoReferencedSRSRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgGeoReferencedSRS(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgGeoReferencedSRSRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgGeoReferencedSRSRefPtr_isGeoReferenced(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgGeoReferencedSRSRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgGeoReferencedSRSRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgGeoReferencedSRSRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
