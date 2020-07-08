package com.visioglobe.libVisioMove;

public class VgINavigationRequest {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationRequest(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationRequest obj) {
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
                libVisioMoveJNI.delete_VgINavigationRequest(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void cancel() {
        libVisioMoveJNI.VgINavigationRequest_cancel(this.swigCPtr, this);
    }
}
