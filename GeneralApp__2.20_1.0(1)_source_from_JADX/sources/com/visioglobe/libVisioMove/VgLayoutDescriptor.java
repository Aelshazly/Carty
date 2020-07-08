package com.visioglobe.libVisioMove;

public class VgLayoutDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayoutDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayoutDescriptor obj) {
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
                libVisioMoveJNI.delete_VgLayoutDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }
}
