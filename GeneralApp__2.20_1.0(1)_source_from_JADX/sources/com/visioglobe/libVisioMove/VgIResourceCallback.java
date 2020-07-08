package com.visioglobe.libVisioMove;

public class VgIResourceCallback extends VgReferenced {
    private long swigCPtr;

    protected VgIResourceCallback(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIResourceCallback_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceCallback obj) {
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
        libVisioMoveJNI.VgIResourceCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgIResourceCallback_change_ownership(this, this.swigCPtr, true);
    }

    public void notifyResource(VgResourceRequestStatus pStatus, String pResourceURI, VgBinaryBuffer pBuffer) {
        libVisioMoveJNI.VgIResourceCallback_notifyResource(this.swigCPtr, this, pStatus.swigValue(), pResourceURI, VgBinaryBuffer.getCPtr(pBuffer), pBuffer);
    }

    public VgIResourceCallback() {
        this(libVisioMoveJNI.new_VgIResourceCallback(), false);
        libVisioMoveJNI.VgIResourceCallback_director_connect(this, this.swigCPtr, false, false);
    }
}
