package com.visioglobe.libVisioMove;

public class VgINavigationCallback extends VgReferenced {
    private long swigCPtr;

    protected VgINavigationCallback(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgINavigationCallback_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationCallback obj) {
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

    /* access modifiers changed from: protected */
    public void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        libVisioMoveJNI.VgINavigationCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgINavigationCallback_change_ownership(this, this.swigCPtr, true);
    }

    protected VgINavigationCallback() {
        this(libVisioMoveJNI.new_VgINavigationCallback(), false);
        libVisioMoveJNI.VgINavigationCallback_director_connect(this, this.swigCPtr, false, false);
    }

    public boolean notifyNavigationComputed(VgNavigationRequestStatus pStatus, VgINavigationRefPtr pNavigation) {
        return libVisioMoveJNI.VgINavigationCallback_notifyNavigationComputed(this.swigCPtr, this, pStatus.swigValue(), VgINavigationRefPtr.getCPtr(pNavigation), pNavigation);
    }
}
