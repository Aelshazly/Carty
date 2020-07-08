package com.visioglobe.libVisioMove;

public class VgStringPair {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgStringPair(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgStringPair obj) {
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
                libVisioMoveJNI.delete_VgStringPair(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgStringPair() {
        this(libVisioMoveJNI.new_VgStringPair__SWIG_0(), true);
    }

    public VgStringPair(String first, String second) {
        this(libVisioMoveJNI.new_VgStringPair__SWIG_1(first, second), true);
    }

    public VgStringPair(VgStringPair p) {
        this(libVisioMoveJNI.new_VgStringPair__SWIG_2(getCPtr(p), p), true);
    }

    public void setFirst(String value) {
        libVisioMoveJNI.VgStringPair_first_set(this.swigCPtr, this, value);
    }

    public String getFirst() {
        return libVisioMoveJNI.VgStringPair_first_get(this.swigCPtr, this);
    }

    public void setSecond(String value) {
        libVisioMoveJNI.VgStringPair_second_set(this.swigCPtr, this, value);
    }

    public String getSecond() {
        return libVisioMoveJNI.VgStringPair_second_get(this.swigCPtr, this);
    }
}
