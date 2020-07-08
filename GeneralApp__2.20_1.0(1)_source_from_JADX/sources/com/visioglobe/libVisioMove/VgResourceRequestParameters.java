package com.visioglobe.libVisioMove;

public class VgResourceRequestParameters {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgResourceRequestParameters(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgResourceRequestParameters obj) {
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
                libVisioMoveJNI.delete_VgResourceRequestParameters(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgResourceRequestParameters() {
        this(libVisioMoveJNI.new_VgResourceRequestParameters(), true);
    }

    public void setMResourceURI(String value) {
        libVisioMoveJNI.VgResourceRequestParameters_mResourceURI_set(this.swigCPtr, this, value);
    }

    public String getMResourceURI() {
        return libVisioMoveJNI.VgResourceRequestParameters_mResourceURI_get(this.swigCPtr, this);
    }

    public void setMCallback(VgIResourceCallbackRefPtr value) {
        libVisioMoveJNI.VgResourceRequestParameters_mCallback_set(this.swigCPtr, this, VgIResourceCallbackRefPtr.getCPtr(value), value);
    }

    public VgIResourceCallbackRefPtr getMCallback() {
        long cPtr = libVisioMoveJNI.VgResourceRequestParameters_mCallback_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceCallbackRefPtr(cPtr, false);
    }
}
