package com.visioglobe.libVisioMove;

public class VgLink extends VgReferenced {
    private long swigCPtr;

    protected VgLink(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLink_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLink obj) {
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

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgLink_setVisible(this.swigCPtr, this, pIsVisible);
    }
}
