package com.visioglobe.libVisioMove;

public class VgINavigationListener extends VgReferenced {
    private long swigCPtr;

    protected VgINavigationListener(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgINavigationListener_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationListener obj) {
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
        libVisioMoveJNI.VgINavigationListener_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgINavigationListener_change_ownership(this, this.swigCPtr, true);
    }

    protected VgINavigationListener() {
        this(libVisioMoveJNI.new_VgINavigationListener(), false);
        libVisioMoveJNI.VgINavigationListener_director_connect(this, this.swigCPtr, false, false);
    }

    public void notifyPositionUpdated(VgINavigationConstRefPtr pNavigation, VgPosition pPosition, double pTime) {
        libVisioMoveJNI.VgINavigationListener_notifyPositionUpdated(this.swigCPtr, this, VgINavigationConstRefPtr.getCPtr(pNavigation), pNavigation, VgPosition.getCPtr(pPosition), pPosition, pTime);
    }

    public void notifyNewInstruction(VgINavigationConstRefPtr pNavigation, long pIndex) {
        libVisioMoveJNI.VgINavigationListener_notifyNewInstruction(this.swigCPtr, this, VgINavigationConstRefPtr.getCPtr(pNavigation), pNavigation, pIndex);
    }
}
