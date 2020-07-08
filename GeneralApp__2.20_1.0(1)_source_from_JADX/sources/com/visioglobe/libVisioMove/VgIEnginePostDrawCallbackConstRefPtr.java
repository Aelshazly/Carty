package com.visioglobe.libVisioMove;

public class VgIEnginePostDrawCallbackConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIEnginePostDrawCallbackConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIEnginePostDrawCallbackConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIEnginePostDrawCallbackConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIEnginePostDrawCallbackConstRefPtr() {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallbackConstRefPtr__SWIG_0(), true);
    }

    public VgIEnginePostDrawCallbackConstRefPtr(VgIEnginePostDrawCallback pPointer) {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallbackConstRefPtr__SWIG_1(VgIEnginePostDrawCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIEnginePostDrawCallbackConstRefPtr(VgIEnginePostDrawCallbackConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIEnginePostDrawCallbackConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIEnginePostDrawCallbackConstRefPtr getNull() {
        return new VgIEnginePostDrawCallbackConstRefPtr(libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_getNull(), true);
    }

    public VgIEnginePostDrawCallbackConstRefPtr set(VgIEnginePostDrawCallback pPointer) {
        return new VgIEnginePostDrawCallbackConstRefPtr(libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_set(this.swigCPtr, this, VgIEnginePostDrawCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIEnginePostDrawCallback __ref__() {
        return new VgIEnginePostDrawCallback(libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIEnginePostDrawCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIEnginePostDrawCallback(cPtr, false);
    }

    public VgIEnginePostDrawCallback get() {
        long cPtr = libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIEnginePostDrawCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIEnginePostDrawCallbackConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
