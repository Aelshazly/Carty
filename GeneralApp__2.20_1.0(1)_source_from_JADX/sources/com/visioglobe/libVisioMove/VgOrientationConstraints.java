package com.visioglobe.libVisioMove;

public class VgOrientationConstraints {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgOrientationConstraints(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgOrientationConstraints obj) {
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
                libVisioMoveJNI.delete_VgOrientationConstraints(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMHeading(VgOrientationType value) {
        libVisioMoveJNI.VgOrientationConstraints_mHeading_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMHeading() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgOrientationConstraints_mHeading_get(this.swigCPtr, this));
    }

    public void setMPitch(VgOrientationType value) {
        libVisioMoveJNI.VgOrientationConstraints_mPitch_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMPitch() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgOrientationConstraints_mPitch_get(this.swigCPtr, this));
    }

    public void setMRoll(VgOrientationType value) {
        libVisioMoveJNI.VgOrientationConstraints_mRoll_set(this.swigCPtr, this, value.swigValue());
    }

    public VgOrientationType getMRoll() {
        return VgOrientationType.swigToEnum(libVisioMoveJNI.VgOrientationConstraints_mRoll_get(this.swigCPtr, this));
    }

    public VgOrientationConstraints() {
        this(libVisioMoveJNI.new_VgOrientationConstraints__SWIG_0(), true);
    }

    public VgOrientationConstraints(VgOrientationType pHeading, VgOrientationType pPitch, VgOrientationType pRoll) {
        this(libVisioMoveJNI.new_VgOrientationConstraints__SWIG_1(pHeading.swigValue(), pPitch.swigValue(), pRoll.swigValue()), true);
    }
}
