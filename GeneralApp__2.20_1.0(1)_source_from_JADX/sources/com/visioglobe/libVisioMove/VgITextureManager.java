package com.visioglobe.libVisioMove;

public class VgITextureManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgITextureManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgITextureManager obj) {
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
                libVisioMoveJNI.delete_VgITextureManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgITextureRefPtr createTexture(VgBinaryBufferConstRefPtr pBuffer, boolean pGenerateMipmaps) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTexture__SWIG_0(this.swigCPtr, this, VgBinaryBufferConstRefPtr.getCPtr(pBuffer), pBuffer, pGenerateMipmaps), true);
    }

    public VgITextureRefPtr createTexture(VgBinaryBufferConstRefPtr pBuffer) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTexture__SWIG_1(this.swigCPtr, this, VgBinaryBufferConstRefPtr.getCPtr(pBuffer), pBuffer), true);
    }

    public VgITextureRefPtr createTexture(long pWidth, long pHeight, VgPixelFormat pPixelFormat, String pData, long pLength, boolean pGenerateMipmaps) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTexture__SWIG_2(this.swigCPtr, this, pWidth, pHeight, pPixelFormat.swigValue(), pData, pLength, pGenerateMipmaps), true);
    }

    public VgITextureRefPtr createTexture(long pWidth, long pHeight, VgPixelFormat pPixelFormat, String pData, long pLength) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTexture__SWIG_3(this.swigCPtr, this, pWidth, pHeight, pPixelFormat.swigValue(), pData, pLength), true);
    }

    public VgITextureRefPtr createTextureWithUniformColor(VgColor pColor, long pWidth, long pHeight) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTextureWithUniformColor__SWIG_0(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor, pWidth, pHeight), true);
    }

    public VgITextureRefPtr createTextureWithUniformColor(VgColor pColor, long pWidth) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTextureWithUniformColor__SWIG_1(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor, pWidth), true);
    }

    public VgITextureRefPtr createTextureWithUniformColor(VgColor pColor) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_createTextureWithUniformColor__SWIG_2(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor), true);
    }

    public VgITextureRefPtr getTexture(String pTextureName) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureManager_getTexture(this.swigCPtr, this, pTextureName), true);
    }
}
