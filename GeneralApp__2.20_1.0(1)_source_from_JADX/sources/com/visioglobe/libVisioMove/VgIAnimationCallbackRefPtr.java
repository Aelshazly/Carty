package com.visioglobe.libVisioMove;

public class VgIAnimationCallbackRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIAnimationCallbackRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIAnimationCallbackRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIAnimationCallbackRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIAnimationCallbackRefPtr() {
        this(libVisioMoveJNI.new_VgIAnimationCallbackRefPtr__SWIG_0(), true);
    }

    public VgIAnimationCallbackRefPtr(VgIAnimationCallback pPointer) {
        this(libVisioMoveJNI.new_VgIAnimationCallbackRefPtr__SWIG_1(VgIAnimationCallback.getCPtr(pPointer), pPointer), true);
    }

    public VgIAnimationCallbackRefPtr(VgIAnimationCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIAnimationCallbackRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIAnimationCallbackRefPtr getNull() {
        return new VgIAnimationCallbackRefPtr(libVisioMoveJNI.VgIAnimationCallbackRefPtr_getNull(), true);
    }

    public VgIAnimationCallbackRefPtr set(VgIAnimationCallback pPointer) {
        return new VgIAnimationCallbackRefPtr(libVisioMoveJNI.VgIAnimationCallbackRefPtr_set(this.swigCPtr, this, VgIAnimationCallback.getCPtr(pPointer), pPointer), false);
    }

    public VgIAnimationCallback __ref__() {
        return new VgIAnimationCallback(libVisioMoveJNI.VgIAnimationCallbackRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIAnimationCallback __deref__() {
        long cPtr = libVisioMoveJNI.VgIAnimationCallbackRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallback(cPtr, false);
    }

    public VgIAnimationCallback get() {
        long cPtr = libVisioMoveJNI.VgIAnimationCallbackRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIAnimationCallback(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIAnimationCallbackRefPtr_isValid(this.swigCPtr, this);
    }

    public void onFinish(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgIAnimationCallbackRefPtr_onFinish(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void ref() {
        libVisioMoveJNI.VgIAnimationCallbackRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIAnimationCallbackRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIAnimationCallbackRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
