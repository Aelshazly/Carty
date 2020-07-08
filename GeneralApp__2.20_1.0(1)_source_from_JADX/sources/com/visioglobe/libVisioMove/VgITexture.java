package com.visioglobe.libVisioMove;

public class VgITexture extends VgReferenced {
    private long swigCPtr;

    protected VgITexture(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgITexture_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgITexture obj) {
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

    public int getWidth() {
        return libVisioMoveJNI.VgITexture_getWidth(this.swigCPtr, this);
    }

    public int getHeight() {
        return libVisioMoveJNI.VgITexture_getHeight(this.swigCPtr, this);
    }
}
