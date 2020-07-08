package com.visioglobe.libVisioMove;

public class VgIGeometryCallback extends VgReferenced {
    private long swigCPtr;

    protected VgIGeometryCallback(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIGeometryCallback_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometryCallback obj) {
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
        libVisioMoveJNI.VgIGeometryCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgIGeometryCallback_change_ownership(this, this.swigCPtr, true);
    }

    protected VgIGeometryCallback() {
        this(libVisioMoveJNI.new_VgIGeometryCallback(), false);
        libVisioMoveJNI.VgIGeometryCallback_director_connect(this, this.swigCPtr, false, false);
    }

    public void handleGeometryEvent(VgIGeometryEvent pEvent) {
        libVisioMoveJNI.VgIGeometryCallback_handleGeometryEvent(this.swigCPtr, this, VgIGeometryEvent.getCPtr(pEvent), pEvent);
    }
}
