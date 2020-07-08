package com.visioglobe.libVisioMove;

public class VgNearPlacesParameters {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgNearPlacesParameters(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgNearPlacesParameters obj) {
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
                libVisioMoveJNI.delete_VgNearPlacesParameters(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMDistanceThreshold(float value) {
        libVisioMoveJNI.VgNearPlacesParameters_mDistanceThreshold_set(this.swigCPtr, this, value);
    }

    public float getMDistanceThreshold() {
        return libVisioMoveJNI.VgNearPlacesParameters_mDistanceThreshold_get(this.swigCPtr, this);
    }

    public void setMHeading(double value) {
        libVisioMoveJNI.VgNearPlacesParameters_mHeading_set(this.swigCPtr, this, value);
    }

    public double getMHeading() {
        return libVisioMoveJNI.VgNearPlacesParameters_mHeading_get(this.swigCPtr, this);
    }

    public void setMLayerName(String value) {
        libVisioMoveJNI.VgNearPlacesParameters_mLayerName_set(this.swigCPtr, this, value);
    }

    public String getMLayerName() {
        return libVisioMoveJNI.VgNearPlacesParameters_mLayerName_get(this.swigCPtr, this);
    }

    public VgNearPlacesParameters() {
        this(libVisioMoveJNI.new_VgNearPlacesParameters(), true);
    }
}
