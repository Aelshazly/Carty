package com.visioglobe.libVisioMove;

public class VgIEnginePostDrawCallback extends VgReferenced {
    private long swigCPtr;

    protected VgIEnginePostDrawCallback(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIEnginePostDrawCallback_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIEnginePostDrawCallback obj) {
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
        libVisioMoveJNI.VgIEnginePostDrawCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        libVisioMoveJNI.VgIEnginePostDrawCallback_change_ownership(this, this.swigCPtr, true);
    }

    public void postDraw(VgIEngineContext pContext) {
        libVisioMoveJNI.VgIEnginePostDrawCallback_postDraw(this.swigCPtr, this, VgIEngineContext.getCPtr(pContext), pContext);
    }

    public void setEngine(VgIEngine pEngine) {
        libVisioMoveJNI.VgIEnginePostDrawCallback_setEngine(this.swigCPtr, this, VgIEngine.getCPtr(pEngine), pEngine);
    }

    protected VgIEnginePostDrawCallback() {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallback(), false);
        libVisioMoveJNI.VgIEnginePostDrawCallback_director_connect(this, this.swigCPtr, false, false);
    }
}
