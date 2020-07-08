package com.visioglobe.libVisioMove;

public class VgMetricSRSConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMetricSRSConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMetricSRSConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMetricSRSConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMetricSRSConstRefPtr() {
        this(libVisioMoveJNI.new_VgMetricSRSConstRefPtr__SWIG_0(), true);
    }

    public VgMetricSRSConstRefPtr(VgMetricSRS pPointer) {
        this(libVisioMoveJNI.new_VgMetricSRSConstRefPtr__SWIG_1(VgMetricSRS.getCPtr(pPointer), pPointer), true);
    }

    public VgMetricSRSConstRefPtr(VgMetricSRSConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgMetricSRSConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgMetricSRSConstRefPtr getNull() {
        return new VgMetricSRSConstRefPtr(libVisioMoveJNI.VgMetricSRSConstRefPtr_getNull(), true);
    }

    public VgMetricSRSConstRefPtr set(VgMetricSRS pPointer) {
        return new VgMetricSRSConstRefPtr(libVisioMoveJNI.VgMetricSRSConstRefPtr_set(this.swigCPtr, this, VgMetricSRS.getCPtr(pPointer), pPointer), false);
    }

    public VgMetricSRS __ref__() {
        return new VgMetricSRS(libVisioMoveJNI.VgMetricSRSConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMetricSRS __deref__() {
        long cPtr = libVisioMoveJNI.VgMetricSRSConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMetricSRS(cPtr, false);
    }

    public VgMetricSRS get() {
        long cPtr = libVisioMoveJNI.VgMetricSRSConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMetricSRS(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMetricSRSConstRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgMetricSRSConstRefPtr_isGeoReferenced(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgMetricSRSConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMetricSRSConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMetricSRSConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
