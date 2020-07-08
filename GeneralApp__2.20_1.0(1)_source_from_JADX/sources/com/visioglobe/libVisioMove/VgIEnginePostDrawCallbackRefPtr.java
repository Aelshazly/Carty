package com.visioglobe.libVisioMove;

public class VgIEnginePostDrawCallbackRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIEnginePostDrawCallbackRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIEnginePostDrawCallbackRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIEnginePostDrawCallbackRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIEnginePostDrawCallbackRefPtr() {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallbackRefPtr__SWIG_0(), true);
    }

    public VgIEnginePostDrawCallbackRefPtr(VgIEnginePostDrawCallback pPointer) {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallbackRefPtr__SWIG_1(VgIEnginePostDrawCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIEnginePostDrawCallbackRefPtr(VgIEnginePostDrawCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallbackRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIEnginePostDrawCallbackRefPtr getNull() {
        return new VgIEnginePostDrawCallbackRefPtr(libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_getNull(), true);
    }

    public VgIEnginePostDrawCallbackRefPtr set(VgIEnginePostDrawCallback pPointer) {
        return new VgIEnginePostDrawCallbackRefPtr(libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_set(this.swigCPtr, this, VgIEnginePostDrawCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIEnginePostDrawCallback __ref__() {
        return new VgIEnginePostDrawCallback(libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIEnginePostDrawCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIEnginePostDrawCallback(cPtr, false);
    }

    public VgIEnginePostDrawCallback get() {
        long cPtr = libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIEnginePostDrawCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_isValid(this.swigCPtr, this);
    }

    public void postDraw(VgIEngineContext pContext) {
        libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_postDraw(this.swigCPtr, this, VgIEngineContext.getCPtr(pContext), pContext);
    }

    public void setEngine(VgIEngine pEngine) {
        libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_setEngine(this.swigCPtr, this, VgIEngine.getCPtr(pEngine), pEngine);
    }

    public void ref() {
        libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIEnginePostDrawCallbackRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
