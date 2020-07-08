package com.visioglobe.libVisioMove;

public class VgLayer extends VgSpatial {
    private long swigCPtr;

    protected VgLayer(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLayer_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayer obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public String getName() {
        return libVisioMoveJNI.VgLayer_getName(this.swigCPtr, this);
    }

    public void setHostedVisible(boolean pVisible) {
        libVisioMoveJNI.VgLayer_setHostedVisible(this.swigCPtr, this, pVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgLayer_isVisible(this.swigCPtr, this);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgLayer_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public void setLODFactor(float pLODFactor) {
        libVisioMoveJNI.VgLayer_setLODFactor(this.swigCPtr, this, pLODFactor);
    }

    public void setLoadFactor(float pLoadFactor) {
        libVisioMoveJNI.VgLayer_setLoadFactor(this.swigCPtr, this, pLoadFactor);
    }

    public float getLODFactor() {
        return libVisioMoveJNI.VgLayer_getLODFactor(this.swigCPtr, this);
    }

    public float getLoadFactor() {
        return libVisioMoveJNI.VgLayer_getLoadFactor(this.swigCPtr, this);
    }

    public VgSRSConstRefPtr getSRS() {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgLayer_getSRS(this.swigCPtr, this), true);
    }
}
