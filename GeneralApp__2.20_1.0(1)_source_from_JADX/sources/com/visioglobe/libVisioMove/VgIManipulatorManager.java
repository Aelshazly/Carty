package com.visioglobe.libVisioMove;

public class VgIManipulatorManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIManipulatorManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIManipulatorManager obj) {
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
                libVisioMoveJNI.delete_VgIManipulatorManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean selectManipulator(String pName) {
        return libVisioMoveJNI.VgIManipulatorManager_selectManipulator(this.swigCPtr, this, pName);
    }

    public String getCurrentManipulator() {
        return libVisioMoveJNI.VgIManipulatorManager_getCurrentManipulator(this.swigCPtr, this);
    }

    public VgManipulator editManipulatorObject(String pManipulatorName) {
        long cPtr = libVisioMoveJNI.VgIManipulatorManager_editManipulatorObject(this.swigCPtr, this, pManipulatorName);
        if (cPtr == 0) {
            return null;
        }
        return new VgManipulator(cPtr, false);
    }
}
