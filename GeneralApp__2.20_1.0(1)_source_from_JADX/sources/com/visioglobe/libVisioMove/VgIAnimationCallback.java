package com.visioglobe.libVisioMove;

public class VgIAnimationCallback extends VgReferenced {
    private long swigCPtr;

    protected VgIAnimationCallback(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIAnimationCallback_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIAnimationCallback obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        libVisioMoveJNI.VgIAnimationCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgIAnimationCallback_change_ownership(this, this.swigCPtr, true);
    }

    public void onFinish(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgIAnimationCallback_onFinish(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    protected VgIAnimationCallback() {
        this(libVisioMoveJNI.new_VgIAnimationCallback(), false);
        libVisioMoveJNI.VgIAnimationCallback_director_connect(this, this.swigCPtr, false, false);
    }
}
