package com.visioglobe.libVisioMove;

public class VgBinaryBufferRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgBinaryBufferRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgBinaryBufferRefPtr obj) {
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
                libVisioMoveJNI.delete_VgBinaryBufferRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgBinaryBufferRefPtr() {
        this(libVisioMoveJNI.new_VgBinaryBufferRefPtr__SWIG_0(), true);
    }

    public VgBinaryBufferRefPtr(VgBinaryBuffer pPointer) {
        this(libVisioMoveJNI.new_VgBinaryBufferRefPtr__SWIG_1(VgBinaryBuffer.getCPtr(pPointer), pPointer), true);
    }

    public VgBinaryBufferRefPtr(VgBinaryBufferRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgBinaryBufferRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgBinaryBufferRefPtr getNull() {
        return new VgBinaryBufferRefPtr(libVisioMoveJNI.VgBinaryBufferRefPtr_getNull(), true);
    }

    public VgBinaryBufferRefPtr set(VgBinaryBuffer pPointer) {
        return new VgBinaryBufferRefPtr(libVisioMoveJNI.VgBinaryBufferRefPtr_set(this.swigCPtr, this, VgBinaryBuffer.getCPtr(pPointer), pPointer), false);
    }

    public VgBinaryBuffer __ref__() {
        return new VgBinaryBuffer(libVisioMoveJNI.VgBinaryBufferRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgBinaryBuffer __deref__() {
        long cPtr = libVisioMoveJNI.VgBinaryBufferRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgBinaryBuffer(cPtr, false);
    }

    public VgBinaryBuffer get() {
        long cPtr = libVisioMoveJNI.VgBinaryBufferRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgBinaryBuffer(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgBinaryBufferRefPtr_isValid(this.swigCPtr, this);
    }

    public long getLength() {
        return libVisioMoveJNI.VgBinaryBufferRefPtr_getLength(this.swigCPtr, this);
    }

    public String getData() {
        return libVisioMoveJNI.VgBinaryBufferRefPtr_getData(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgBinaryBufferRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgBinaryBufferRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgBinaryBufferRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
