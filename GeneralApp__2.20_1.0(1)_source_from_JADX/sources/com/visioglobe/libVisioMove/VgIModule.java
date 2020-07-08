package com.visioglobe.libVisioMove;

public class VgIModule {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIModule(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIModule obj) {
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
                libVisioMoveJNI.delete_VgIModule(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public String getName() {
        return libVisioMoveJNI.VgIModule_getName(this.swigCPtr, this);
    }
}
