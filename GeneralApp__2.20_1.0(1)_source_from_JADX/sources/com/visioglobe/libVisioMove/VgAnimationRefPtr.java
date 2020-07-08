package com.visioglobe.libVisioMove;

public class VgAnimationRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAnimationRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimationRefPtr obj) {
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
                libVisioMoveJNI.delete_VgAnimationRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgAnimationRefPtr() {
        this(libVisioMoveJNI.new_VgAnimationRefPtr__SWIG_0(), true);
    }

    public VgAnimationRefPtr(VgAnimation pPointer) {
        this(libVisioMoveJNI.new_VgAnimationRefPtr__SWIG_1(VgAnimation.getCPtr(pPointer), pPointer), true);
    }

    public VgAnimationRefPtr(VgAnimationRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgAnimationRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgAnimationRefPtr getNull() {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgAnimationRefPtr_getNull(), true);
    }

    public VgAnimationRefPtr set(VgAnimation pPointer) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgAnimationRefPtr_set(this.swigCPtr, this, VgAnimation.getCPtr(pPointer), pPointer), false);
    }

    public VgAnimation __ref__() {
        return new VgAnimation(libVisioMoveJNI.VgAnimationRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgAnimation __deref__() {
        long cPtr = libVisioMoveJNI.VgAnimationRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimation(cPtr, false);
    }

    public VgAnimation get() {
        long cPtr = libVisioMoveJNI.VgAnimationRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgAnimation(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgAnimationRefPtr_isValid(this.swigCPtr, this);
    }

    public void start(float pDelay, float pStart, float pEnd, float pDuration) {
        libVisioMoveJNI.VgAnimationRefPtr_start__SWIG_0(this.swigCPtr, this, pDelay, pStart, pEnd, pDuration);
    }

    public void start(float pDelay, float pStart, float pEnd) {
        libVisioMoveJNI.VgAnimationRefPtr_start__SWIG_1(this.swigCPtr, this, pDelay, pStart, pEnd);
    }

    public void start(float pDelay, float pStart) {
        libVisioMoveJNI.VgAnimationRefPtr_start__SWIG_2(this.swigCPtr, this, pDelay, pStart);
    }

    public void start(float pDelay) {
        libVisioMoveJNI.VgAnimationRefPtr_start__SWIG_3(this.swigCPtr, this, pDelay);
    }

    public void start() {
        libVisioMoveJNI.VgAnimationRefPtr_start__SWIG_4(this.swigCPtr, this);
    }

    public void stop() {
        libVisioMoveJNI.VgAnimationRefPtr_stop(this.swigCPtr, this);
    }

    public void pause() {
        libVisioMoveJNI.VgAnimationRefPtr_pause(this.swigCPtr, this);
    }

    public void seek(float pTime) {
        libVisioMoveJNI.VgAnimationRefPtr_seek(this.swigCPtr, this, pTime);
    }

    public boolean isPlaying() {
        return libVisioMoveJNI.VgAnimationRefPtr_isPlaying(this.swigCPtr, this);
    }

    public float getDuration() {
        return libVisioMoveJNI.VgAnimationRefPtr_getDuration(this.swigCPtr, this);
    }

    public float getCursor() {
        return libVisioMoveJNI.VgAnimationRefPtr_getCursor(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgAnimationRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgAnimationRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgAnimationRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
