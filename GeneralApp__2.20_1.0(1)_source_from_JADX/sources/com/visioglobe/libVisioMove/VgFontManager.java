package com.visioglobe.libVisioMove;

public class VgFontManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgFontManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFontManager obj) {
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
                libVisioMoveJNI.delete_VgFontManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean setFont(String pFontFilename) {
        return libVisioMoveJNI.VgFontManager_setFont__SWIG_0(this.swigCPtr, this, pFontFilename);
    }

    public boolean setFont(String pFontFilename, long pDPIResolution) {
        return libVisioMoveJNI.VgFontManager_setFont__SWIG_1(this.swigCPtr, this, pFontFilename, pDPIResolution);
    }

    public boolean setFontDpi(long pDPIResolution) {
        return libVisioMoveJNI.VgFontManager_setFontDpi(this.swigCPtr, this, pDPIResolution);
    }
}
