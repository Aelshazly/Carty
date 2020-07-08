package com.visioglobe.libVisioMove;

public class VgBinaryBufferConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgBinaryBufferConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgBinaryBufferConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgBinaryBufferConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgBinaryBufferConstRefPtr() {
        this(libVisioMoveJNI.new_VgBinaryBufferConstRefPtr__SWIG_0(), true);
    }

    public VgBinaryBufferConstRefPtr(VgBinaryBuffer pPointer) {
        this(libVisioMoveJNI.new_VgBinaryBufferConstRefPtr__SWIG_1(VgBinaryBuffer.getCPtr(pPointer), pPointer), true);
    }

    public VgBinaryBufferConstRefPtr(VgBinaryBufferConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgBinaryBufferConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgBinaryBufferConstRefPtr getNull() {
        return new VgBinaryBufferConstRefPtr(libVisioMoveJNI.VgBinaryBufferConstRefPtr_getNull(), true);
    }

    public VgBinaryBufferConstRefPtr set(VgBinaryBuffer pPointer) {
        return new VgBinaryBufferConstRefPtr(libVisioMoveJNI.VgBinaryBufferConstRefPtr_set(this.swigCPtr, this, VgBinaryBuffer.getCPtr(pPointer), pPointer), false);
    }

    public VgBinaryBuffer __ref__() {
        return new VgBinaryBuffer(libVisioMoveJNI.VgBinaryBufferConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgBinaryBuffer __deref__() {
        long cPtr = libVisioMoveJNI.VgBinaryBufferConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgBinaryBuffer(cPtr, false);
    }

    public VgBinaryBuffer get() {
        long cPtr = libVisioMoveJNI.VgBinaryBufferConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgBinaryBuffer(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgBinaryBufferConstRefPtr_isValid(this.swigCPtr, this);
    }

    public long getLength() {
        return libVisioMoveJNI.VgBinaryBufferConstRefPtr_getLength(this.swigCPtr, this);
    }

    public String getData() {
        return libVisioMoveJNI.VgBinaryBufferConstRefPtr_getData(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgBinaryBufferConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgBinaryBufferConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgBinaryBufferConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
