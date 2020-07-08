package com.visioglobe.libVisioMove;

public class VgMetricSRSRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMetricSRSRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMetricSRSRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMetricSRSRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMetricSRSRefPtr() {
        this(libVisioMoveJNI.new_VgMetricSRSRefPtr__SWIG_0(), true);
    }

    public VgMetricSRSRefPtr(VgMetricSRS pPointer) {
        this(libVisioMoveJNI.new_VgMetricSRSRefPtr__SWIG_1(VgMetricSRS.getCPtr(pPointer), pPointer), true);
    }

    public VgMetricSRSRefPtr(VgMetricSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgMetricSRSRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgMetricSRSRefPtr getNull() {
        return new VgMetricSRSRefPtr(libVisioMoveJNI.VgMetricSRSRefPtr_getNull(), true);
    }

    public VgMetricSRSRefPtr set(VgMetricSRS pPointer) {
        return new VgMetricSRSRefPtr(libVisioMoveJNI.VgMetricSRSRefPtr_set(this.swigCPtr, this, VgMetricSRS.getCPtr(pPointer), pPointer), false);
    }

    public VgMetricSRS __ref__() {
        return new VgMetricSRS(libVisioMoveJNI.VgMetricSRSRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMetricSRS __deref__() {
        long cPtr = libVisioMoveJNI.VgMetricSRSRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMetricSRS(cPtr, false);
    }

    public VgMetricSRS get() {
        long cPtr = libVisioMoveJNI.VgMetricSRSRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMetricSRS(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMetricSRSRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgMetricSRSRefPtr_isGeoReferenced(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgMetricSRSRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMetricSRSRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMetricSRSRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
