package com.visioglobe.libVisioMove;

public class VgIRouteCallback extends VgReferenced {
    private long swigCPtr;

    protected VgIRouteCallback(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIRouteCallback_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteCallback obj) {
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
        libVisioMoveJNI.VgIRouteCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgIRouteCallback_change_ownership(this, this.swigCPtr, true);
    }

    protected VgIRouteCallback() {
        this(libVisioMoveJNI.new_VgIRouteCallback(), false);
        libVisioMoveJNI.VgIRouteCallback_director_connect(this, this.swigCPtr, false, false);
    }

    public void notifyRouteComputed(VgRouteRequestStatus pStatus, VgIRouteRefPtr pRoute) {
        libVisioMoveJNI.VgIRouteCallback_notifyRouteComputed(this.swigCPtr, this, pStatus.swigValue(), VgIRouteRefPtr.getCPtr(pRoute), pRoute);
    }
}
