package com.visioglobe.libVisioMove;

public class VgIModuleManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIModuleManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIModuleManager obj) {
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
                libVisioMoveJNI.delete_VgIModuleManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIModule queryModule(String pName) {
        long cPtr = libVisioMoveJNI.VgIModuleManager_queryModule(this.swigCPtr, this, pName);
        if (cPtr == 0) {
            return null;
        }
        return new VgIModule(cPtr, false);
    }
}
