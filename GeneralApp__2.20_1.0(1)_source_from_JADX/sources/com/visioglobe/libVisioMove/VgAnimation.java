package com.visioglobe.libVisioMove;

public class VgAnimation extends VgReferenced {
    private long swigCPtr;

    protected VgAnimation(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgAnimation_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimation obj) {
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

    public VgAnimation() {
        this(libVisioMoveJNI.new_VgAnimation(), true);
    }

    public void start(float pDelay, float pStart, float pEnd, float pDuration) {
        libVisioMoveJNI.VgAnimation_start__SWIG_0(this.swigCPtr, this, pDelay, pStart, pEnd, pDuration);
    }

    public void start(float pDelay, float pStart, float pEnd) {
        libVisioMoveJNI.VgAnimation_start__SWIG_1(this.swigCPtr, this, pDelay, pStart, pEnd);
    }

    public void start(float pDelay, float pStart) {
        libVisioMoveJNI.VgAnimation_start__SWIG_2(this.swigCPtr, this, pDelay, pStart);
    }

    public void start(float pDelay) {
        libVisioMoveJNI.VgAnimation_start__SWIG_3(this.swigCPtr, this, pDelay);
    }

    public void start() {
        libVisioMoveJNI.VgAnimation_start__SWIG_4(this.swigCPtr, this);
    }

    public void stop() {
        libVisioMoveJNI.VgAnimation_stop(this.swigCPtr, this);
    }

    public void pause() {
        libVisioMoveJNI.VgAnimation_pause(this.swigCPtr, this);
    }

    public void seek(float pTime) {
        libVisioMoveJNI.VgAnimation_seek(this.swigCPtr, this, pTime);
    }

    public boolean isPlaying() {
        return libVisioMoveJNI.VgAnimation_isPlaying(this.swigCPtr, this);
    }

    public float getDuration() {
        return libVisioMoveJNI.VgAnimation_getDuration(this.swigCPtr, this);
    }

    public float getCursor() {
        return libVisioMoveJNI.VgAnimation_getCursor(this.swigCPtr, this);
    }
}
