package com.visioglobe.libVisioMove;

public class VgLoopModes {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLoopModes(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLoopModes obj) {
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
                libVisioMoveJNI.delete_VgLoopModes(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static long getMscNoLoop() {
        return libVisioMoveJNI.VgLoopModes_mscNoLoop_get();
    }

    public static long getMscLoop() {
        return libVisioMoveJNI.VgLoopModes_mscLoop_get();
    }

    public static long getMscPingPong() {
        return libVisioMoveJNI.VgLoopModes_mscPingPong_get();
    }

    public VgLoopModes() {
        this(libVisioMoveJNI.new_VgLoopModes(), true);
    }
}
