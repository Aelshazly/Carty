package com.visioglobe.libVisioMove;

public class VgIResourceManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIResourceManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIResourceManager obj) {
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
                libVisioMoveJNI.delete_VgIResourceManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgBinaryBufferRefPtr readFromFileOrURL(String pFilenameOrURL) {
        return new VgBinaryBufferRefPtr(libVisioMoveJNI.VgIResourceManager_readFromFileOrURL__SWIG_0(this.swigCPtr, this, pFilenameOrURL), true);
    }

    public VgIResourceRequestRefPtr readFromFileOrURL(VgResourceRequestParameters pParameters) {
        return new VgIResourceRequestRefPtr(libVisioMoveJNI.VgIResourceManager_readFromFileOrURL__SWIG_1(this.swigCPtr, this, VgResourceRequestParameters.getCPtr(pParameters), pParameters), true);
    }
}
