package com.visioglobe.libVisioMove;

public class VgAnimationConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAnimationConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimationConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgAnimationConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAnimationConstRefPtr() {
        this(libVisioMoveJNI.new_VgAnimationConstRefPtr__SWIG_0(), true);
    }

    public VgAnimationConstRefPtr(VgAnimation pPointer) {
        this(libVisioMoveJNI.new_VgAnimationConstRefPtr__SWIG_1(VgAnimation.getCPtr(pPointer), pPointer), true);
    }

    public VgAnimationConstRefPtr(VgAnimationConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgAnimationConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgAnimationConstRefPtr getNull() {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgAnimationConstRefPtr_getNull(), true);
    }

    public VgAnimationConstRefPtr set(VgAnimation pPointer) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgAnimationConstRefPtr_set(this.swigCPtr, this, VgAnimation.getCPtr(pPointer), pPointer), false);
    }

    public VgAnimation __ref__() {
        return new VgAnimation(libVisioMoveJNI.VgAnimationConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgAnimation __deref__() {
        long cPtr = libVisioMoveJNI.VgAnimationConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimation(cPtr, false);
    }

    public VgAnimation get() {
        long cPtr = libVisioMoveJNI.VgAnimationConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimation(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgAnimationConstRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean isPlaying() {
        return libVisioMoveJNI.VgAnimationConstRefPtr_isPlaying(this.swigCPtr, this);
    }

    public float getDuration() {
        return libVisioMoveJNI.VgAnimationConstRefPtr_getDuration(this.swigCPtr, this);
    }

    public float getCursor() {
        return libVisioMoveJNI.VgAnimationConstRefPtr_getCursor(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgAnimationConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgAnimationConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgAnimationConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
