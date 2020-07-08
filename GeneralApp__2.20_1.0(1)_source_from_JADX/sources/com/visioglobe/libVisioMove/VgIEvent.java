package com.visioglobe.libVisioMove;

public class VgIEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIEvent(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIEvent obj) {
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
                libVisioMoveJNI.delete_VgIEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIEventType getType() {
        return VgIEventType.swigToEnum(libVisioMoveJNI.VgIEvent_getType(this.swigCPtr, this));
    }
}
