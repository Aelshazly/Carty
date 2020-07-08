package com.visioglobe.libVisioMove;

public class VgIAnimationManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIAnimationManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIAnimationManager obj) {
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
                libVisioMoveJNI.delete_VgIAnimationManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setCameraAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgIAnimationManager_setCameraAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }
}
