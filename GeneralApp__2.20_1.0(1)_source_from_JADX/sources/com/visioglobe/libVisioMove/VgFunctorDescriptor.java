package com.visioglobe.libVisioMove;

public class VgFunctorDescriptor extends VgReferenced {
    private long swigCPtr;

    protected VgFunctorDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgFunctorDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgFunctorDescriptor obj) {
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

    public void setMStartTime(float value) {
        libVisioMoveJNI.VgFunctorDescriptor_mStartTime_set(this.swigCPtr, this, value);
    }

    public float getMStartTime() {
        return libVisioMoveJNI.VgFunctorDescriptor_mStartTime_get(this.swigCPtr, this);
    }

    public void setMEndTime(float value) {
        libVisioMoveJNI.VgFunctorDescriptor_mEndTime_set(this.swigCPtr, this, value);
    }

    public float getMEndTime() {
        return libVisioMoveJNI.VgFunctorDescriptor_mEndTime_get(this.swigCPtr, this);
    }
}
