package com.visioglobe.libVisioMove;

public class VgPosition {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPosition(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPosition obj) {
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
                libVisioMoveJNI.delete_VgPosition(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPosition() {
        this(libVisioMoveJNI.new_VgPosition__SWIG_0(), true);
    }

    public VgPosition(VgSRSConstRefPtr pSRS) {
        this(libVisioMoveJNI.new_VgPosition__SWIG_1(VgSRSConstRefPtr.getCPtr(pSRS), pSRS), true);
    }

    public VgPosition(VgPosition pOther) {
        this(libVisioMoveJNI.new_VgPosition__SWIG_2(getCPtr(pOther), pOther), true);
    }

    public VgPosition(double pXOrLongitude, double pYOrLatitude, double pZOrAltitude) {
        this(libVisioMoveJNI.new_VgPosition__SWIG_3(pXOrLongitude, pYOrLatitude, pZOrAltitude), true);
    }

    public VgPosition(double pXOrLongitude, double pYOrLatitude, double pZOrAltitude, VgSRSConstRefPtr pSRS) {
        this(libVisioMoveJNI.new_VgPosition__SWIG_4(pXOrLongitude, pYOrLatitude, pZOrAltitude, VgSRSConstRefPtr.getCPtr(pSRS), pSRS), true);
    }

    public void setMSRS(VgSRSConstRefPtr value) {
        libVisioMoveJNI.VgPosition_mSRS_set(this.swigCPtr, this, VgSRSConstRefPtr.getCPtr(value), value);
    }

    public VgSRSConstRefPtr getMSRS() {
        long cPtr = libVisioMoveJNI.VgPosition_mSRS_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSRSConstRefPtr(cPtr, false);
    }

    public void setMXOrLongitude(double value) {
        libVisioMoveJNI.VgPosition_mXOrLongitude_set(this.swigCPtr, this, value);
    }

    public double getMXOrLongitude() {
        return libVisioMoveJNI.VgPosition_mXOrLongitude_get(this.swigCPtr, this);
    }

    public void setMYOrLatitude(double value) {
        libVisioMoveJNI.VgPosition_mYOrLatitude_set(this.swigCPtr, this, value);
    }

    public double getMYOrLatitude() {
        return libVisioMoveJNI.VgPosition_mYOrLatitude_get(this.swigCPtr, this);
    }

    public void setMZOrAltitude(double value) {
        libVisioMoveJNI.VgPosition_mZOrAltitude_set(this.swigCPtr, this, value);
    }

    public double getMZOrAltitude() {
        return libVisioMoveJNI.VgPosition_mZOrAltitude_get(this.swigCPtr, this);
    }
}
