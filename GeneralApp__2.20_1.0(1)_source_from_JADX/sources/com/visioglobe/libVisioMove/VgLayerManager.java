package com.visioglobe.libVisioMove;

public class VgLayerManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayerManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerManager obj) {
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
                libVisioMoveJNI.delete_VgLayerManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLayerManager(VgIEngine pEngine) {
        this(libVisioMoveJNI.new_VgLayerManager(VgIEngine.getCPtr(pEngine), pEngine), true);
    }

    public VgLayerRefPtr editLayer(String pName) {
        return new VgLayerRefPtr(libVisioMoveJNI.VgLayerManager_editLayer(this.swigCPtr, this, pName), true);
    }

    public VgLayerVector getLayers() {
        return new VgLayerVector(libVisioMoveJNI.VgLayerManager_getLayers(this.swigCPtr, this), false);
    }

    public VgLayerVector editLayers() {
        return new VgLayerVector(libVisioMoveJNI.VgLayerManager_editLayers(this.swigCPtr, this), false);
    }
}
