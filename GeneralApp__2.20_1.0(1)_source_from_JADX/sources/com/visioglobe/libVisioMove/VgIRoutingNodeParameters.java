package com.visioglobe.libVisioMove;

public class VgIRoutingNodeParameters {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRoutingNodeParameters(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingNodeParameters obj) {
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
                libVisioMoveJNI.delete_VgIRoutingNodeParameters(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMOption(VgIRoutingNodeOption value) {
        libVisioMoveJNI.VgIRoutingNodeParameters_mOption_set(this.swigCPtr, this, value.swigValue());
    }

    public VgIRoutingNodeOption getMOption() {
        return VgIRoutingNodeOption.swigToEnum(libVisioMoveJNI.VgIRoutingNodeParameters_mOption_get(this.swigCPtr, this));
    }

    public void setMDistanceThreshold(float value) {
        libVisioMoveJNI.VgIRoutingNodeParameters_mDistanceThreshold_set(this.swigCPtr, this, value);
    }

    public float getMDistanceThreshold() {
        return libVisioMoveJNI.VgIRoutingNodeParameters_mDistanceThreshold_get(this.swigCPtr, this);
    }

    public void setMLayerName(String value) {
        libVisioMoveJNI.VgIRoutingNodeParameters_mLayerName_set(this.swigCPtr, this, value);
    }

    public String getMLayerName() {
        return libVisioMoveJNI.VgIRoutingNodeParameters_mLayerName_get(this.swigCPtr, this);
    }

    public void setMExcludedEdgeModalities(VgStringSet value) {
        libVisioMoveJNI.VgIRoutingNodeParameters_mExcludedEdgeModalities_set(this.swigCPtr, this, VgStringSet.getCPtr(value), value);
    }

    public VgStringSet getMExcludedEdgeModalities() {
        long cPtr = libVisioMoveJNI.VgIRoutingNodeParameters_mExcludedEdgeModalities_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgStringSet(cPtr, false);
    }

    public VgIRoutingNodeParameters() {
        this(libVisioMoveJNI.new_VgIRoutingNodeParameters(), true);
    }
}
