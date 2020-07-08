package com.visioglobe.libVisioMove;

public class VgBinaryBuffer extends VgReferenced {
    private long swigCPtr;

    protected VgBinaryBuffer(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgBinaryBuffer_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgBinaryBuffer obj) {
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

    public VgBinaryBuffer() {
        this(libVisioMoveJNI.new_VgBinaryBuffer__SWIG_0(), true);
    }

    public VgBinaryBuffer(byte[] pData, boolean pVgBinaryBufferDoesDelete) {
        this(libVisioMoveJNI.new_VgBinaryBuffer__SWIG_1(pData, pVgBinaryBufferDoesDelete), true);
    }

    public VgBinaryBuffer(byte[] pData) {
        this(libVisioMoveJNI.new_VgBinaryBuffer__SWIG_2(pData), true);
    }

    public long getLength() {
        return libVisioMoveJNI.VgBinaryBuffer_getLength(this.swigCPtr, this);
    }

    public String getData() {
        return libVisioMoveJNI.VgBinaryBuffer_getData(this.swigCPtr, this);
    }
}
