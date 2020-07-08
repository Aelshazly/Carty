package com.visioglobe.libVisioMove;

public class VgModelManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgModelManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgModelManager obj) {
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
                libVisioMoveJNI.delete_VgModelManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgModelManager() {
        this(libVisioMoveJNI.new_VgModelManager(), true);
    }

    public VgIGeometryRefPtr createModel(VgBinaryBufferConstRefPtr pBuffer) {
        return new VgIGeometryRefPtr(libVisioMoveJNI.VgModelManager_createModel__SWIG_0(this.swigCPtr, this, VgBinaryBufferConstRefPtr.getCPtr(pBuffer), pBuffer), true);
    }

    public VgIGeometryRefPtr createModel(VgBinaryBufferConstRefPtr pBuffer, String pID) {
        return new VgIGeometryRefPtr(libVisioMoveJNI.VgModelManager_createModel__SWIG_1(this.swigCPtr, this, VgBinaryBufferConstRefPtr.getCPtr(pBuffer), pBuffer, pID), true);
    }
}
