package com.visioglobe.libVisioMove;

public class VgIPlaceListener extends VgReferenced {
    private long swigCPtr;

    protected VgIPlaceListener(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIPlaceListener_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIPlaceListener obj) {
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
        libVisioMoveJNI.VgIPlaceListener_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgIPlaceListener_change_ownership(this, this.swigCPtr, true);
    }

    protected VgIPlaceListener() {
        this(libVisioMoveJNI.new_VgIPlaceListener(), false);
        libVisioMoveJNI.VgIPlaceListener_director_connect(this, this.swigCPtr, false, false);
    }

    public void notifyPlaceSelected(VgIApplication pVgApplication, String pID, VgPosition pPosition) {
        libVisioMoveJNI.VgIPlaceListener_notifyPlaceSelected(this.swigCPtr, this, VgIApplication.getCPtr(pVgApplication), pVgApplication, pID, VgPosition.getCPtr(pPosition), pPosition);
    }

    public void notifyMapDatabaseLoaded(VgIApplication pVgApplication) {
        libVisioMoveJNI.VgIPlaceListener_notifyMapDatabaseLoaded(this.swigCPtr, this, VgIApplication.getCPtr(pVgApplication), pVgApplication);
    }
}
