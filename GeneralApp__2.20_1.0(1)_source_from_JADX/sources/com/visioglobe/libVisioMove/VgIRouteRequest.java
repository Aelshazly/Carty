package com.visioglobe.libVisioMove;

public class VgIRouteRequest {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteRequest(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteRequest obj) {
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
                libVisioMoveJNI.delete_VgIRouteRequest(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void cancel() {
        libVisioMoveJNI.VgIRouteRequest_cancel(this.swigCPtr, this);
    }
}
