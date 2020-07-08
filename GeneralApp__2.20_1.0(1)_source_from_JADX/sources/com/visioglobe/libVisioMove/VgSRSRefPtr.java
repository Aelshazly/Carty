package com.visioglobe.libVisioMove;

public class VgSRSRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSRSRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSRSRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSRSRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSRSRefPtr() {
        this(libVisioMoveJNI.new_VgSRSRefPtr__SWIG_0(), true);
    }

    public VgSRSRefPtr(VgSRS pPointer) {
        this(libVisioMoveJNI.new_VgSRSRefPtr__SWIG_1(VgSRS.getCPtr(pPointer), pPointer), true);
    }

    public VgSRSRefPtr(VgSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgSRSRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgSRSRefPtr getNull() {
        return new VgSRSRefPtr(libVisioMoveJNI.VgSRSRefPtr_getNull(), true);
    }

    public VgSRSRefPtr set(VgSRS pPointer) {
        return new VgSRSRefPtr(libVisioMoveJNI.VgSRSRefPtr_set(this.swigCPtr, this, VgSRS.getCPtr(pPointer), pPointer), false);
    }

    public VgSRS __ref__() {
        return new VgSRS(libVisioMoveJNI.VgSRSRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSRS __deref__() {
        long cPtr = libVisioMoveJNI.VgSRSRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSRS(cPtr, false);
    }

    public VgSRS get() {
        long cPtr = libVisioMoveJNI.VgSRSRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSRS(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSRSRefPtr_isValid(this.swigCPtr, this);
    }

    public VgSRSRefPtr(VgGeoReferencedSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_57__(VgGeoReferencedSRSRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgSRSRefPtr(VgMetricSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_58__(VgMetricSRSRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgSRSRefPtr_isGeoReferenced(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSRSRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSRSRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSRSRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
