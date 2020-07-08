package com.visioglobe.libVisioMove;

public class VgIResourceRequest extends VgReferenced {
    private long swigCPtr;

    protected VgIResourceRequest(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIResourceRequest_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceRequest obj) {
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
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public void cancel() {
        libVisioMoveJNI.VgIResourceRequest_cancel(this.swigCPtr, this);
    }
}
