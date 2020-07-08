package com.visioglobe.libVisioMove;

public class VgIAnimationCallbackConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIAnimationCallbackConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIAnimationCallbackConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIAnimationCallbackConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIAnimationCallbackConstRefPtr() {
        this(libVisioMoveJNI.new_VgIAnimationCallbackConstRefPtr__SWIG_0(), true);
    }

    public VgIAnimationCallbackConstRefPtr(VgIAnimationCallback pPointer) {
        this(libVisioMoveJNI.new_VgIAnimationCallbackConstRefPtr__SWIG_1(VgIAnimationCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIAnimationCallbackConstRefPtr(VgIAnimationCallbackConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIAnimationCallbackConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIAnimationCallbackConstRefPtr getNull() {
        return new VgIAnimationCallbackConstRefPtr(libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_getNull(), true);
    }

    public VgIAnimationCallbackConstRefPtr set(VgIAnimationCallback pPointer) {
        return new VgIAnimationCallbackConstRefPtr(libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_set(this.swigCPtr, this, VgIAnimationCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIAnimationCallback __ref__() {
        return new VgIAnimationCallback(libVisioMoveJNI.VgIAnimationCallbackConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIAnimationCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIAnimationCallbackConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallback(cPtr, false);
    }

    public VgIAnimationCallback get() {
        long cPtr = libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIAnimationCallbackConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
