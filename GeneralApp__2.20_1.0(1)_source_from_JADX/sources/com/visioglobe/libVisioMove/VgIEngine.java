package com.visioglobe.libVisioMove;

public class VgIEngine {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIEngine(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIEngine obj) {
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
                libVisioMoveJNI.delete_VgIEngine(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void addPostDrawCallback(VgIEnginePostDrawCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgIEngine_addPostDrawCallback(this.swigCPtr, this, VgIEnginePostDrawCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public void removePostDrawCallback(VgIEnginePostDrawCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgIEngine_removePostDrawCallback(this.swigCPtr, this, VgIEnginePostDrawCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public VgPostDrawCallbacks getPostDrawCallbacks() {
        return new VgPostDrawCallbacks(libVisioMoveJNI.VgIEngine_getPostDrawCallbacks(this.swigCPtr, this), false);
    }

    public VgIDatabase editDatabase() {
        long cPtr = libVisioMoveJNI.VgIEngine_editDatabase(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIDatabase(cPtr, false);
    }

    public VgICamera editCamera() {
        long cPtr = libVisioMoveJNI.VgIEngine_editCamera(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgICamera(cPtr, false);
    }

    public VgILicenseManager editLicenseManager() {
        long cPtr = libVisioMoveJNI.VgIEngine_editLicenseManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgILicenseManager(cPtr, false);
    }

    public VgITextureManager editTextureManager() {
        long cPtr = libVisioMoveJNI.VgIEngine_editTextureManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureManager(cPtr, false);
    }

    public VgIAnimationManager editAnimationManager() {
        long cPtr = libVisioMoveJNI.VgIEngine_editAnimationManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationManager(cPtr, false);
    }

    public VgIResourceManager editResourceManager() {
        long cPtr = libVisioMoveJNI.VgIEngine_editResourceManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIResourceManager(cPtr, false);
    }

    public VgLayerManager editLayerManager() {
        long cPtr = libVisioMoveJNI.VgIEngine_editLayerManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayerManager(cPtr, false);
    }

    public VgInstanceFactory editInstanceFactory() {
        long cPtr = libVisioMoveJNI.VgIEngine_editInstanceFactory(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgInstanceFactory(cPtr, false);
    }

    public VgFontManager editFontManager() {
        long cPtr = libVisioMoveJNI.VgIEngine_editFontManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgFontManager(cPtr, false);
    }

    public VgPositionToolbox getPositionToolbox() {
        long cPtr = libVisioMoveJNI.VgIEngine_getPositionToolbox(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPositionToolbox(cPtr, false);
    }

    public VgErrorCode getLastError() {
        return VgErrorCode.swigToEnum(libVisioMoveJNI.VgIEngine_getLastError(this.swigCPtr, this));
    }

    public String getErrorString(VgErrorCode pErrorCode) {
        return libVisioMoveJNI.VgIEngine_getErrorString(this.swigCPtr, this, pErrorCode.swigValue());
    }

    public VgSpatialList execute(VgQuery pQuery) {
        return new VgSpatialList(libVisioMoveJNI.VgIEngine_execute(this.swigCPtr, this, VgQuery.getCPtr(pQuery), pQuery), true);
    }

    public void resetGraphicResources(boolean pIsContextLost) {
        libVisioMoveJNI.VgIEngine_resetGraphicResources(this.swigCPtr, this, pIsContextLost);
    }

    public void reloadShaders() {
        libVisioMoveJNI.VgIEngine_reloadShaders(this.swigCPtr, this);
    }

    public VgLightConstRefPtr getLight(long pLightNum) {
        return new VgLightConstRefPtr(libVisioMoveJNI.VgIEngine_getLight(this.swigCPtr, this, pLightNum), true);
    }

    public VgLightRefPtr editLight(long pLightNum) {
        return new VgLightRefPtr(libVisioMoveJNI.VgIEngine_editLight(this.swigCPtr, this, pLightNum), true);
    }

    public long getNumLights() {
        return libVisioMoveJNI.VgIEngine_getNumLights(this.swigCPtr, this);
    }

    public void replaceNamedTexture(String pResourceName, VgITextureRefPtr pTexture) {
        libVisioMoveJNI.VgIEngine_replaceNamedTexture(this.swigCPtr, this, pResourceName, VgITextureRefPtr.getCPtr(pTexture), pTexture);
    }

    public void setClearColor(VgColor pClearColor) {
        libVisioMoveJNI.VgIEngine_setClearColor(this.swigCPtr, this, VgColor.getCPtr(pClearColor), pClearColor);
    }

    public boolean isLoaded(int[] pGraphicsQueue, int[] pLoadQueueSize, int[] pLoadedQueueSize, int[] pActiveThreads) {
        return libVisioMoveJNI.VgIEngine_isLoaded(this.swigCPtr, this, pGraphicsQueue, pLoadQueueSize, pLoadedQueueSize, pActiveThreads);
    }
}
