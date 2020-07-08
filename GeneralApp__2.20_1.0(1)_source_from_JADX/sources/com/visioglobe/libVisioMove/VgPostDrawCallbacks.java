package com.visioglobe.libVisioMove;

public class VgPostDrawCallbacks {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPostDrawCallbacks(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPostDrawCallbacks obj) {
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
                libVisioMoveJNI.delete_VgPostDrawCallbacks(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPostDrawCallbacks() {
        this(libVisioMoveJNI.new_VgPostDrawCallbacks__SWIG_0(), true);
    }

    public VgPostDrawCallbacks(long n) {
        this(libVisioMoveJNI.new_VgPostDrawCallbacks__SWIG_1(n), true);
    }

    public long size() {
        return libVisioMoveJNI.VgPostDrawCallbacks_size(this.swigCPtr, this);
    }

    public long capacity() {
        return libVisioMoveJNI.VgPostDrawCallbacks_capacity(this.swigCPtr, this);
    }

    public void reserve(long n) {
        libVisioMoveJNI.VgPostDrawCallbacks_reserve(this.swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgPostDrawCallbacks_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgPostDrawCallbacks_clear(this.swigCPtr, this);
    }

    public void add(VgIEnginePostDrawCallbackRefPtr x) {
        libVisioMoveJNI.VgPostDrawCallbacks_add(this.swigCPtr, this, VgIEnginePostDrawCallbackRefPtr.getCPtr(x), x);
    }

    public VgIEnginePostDrawCallbackRefPtr get(int i) {
        return new VgIEnginePostDrawCallbackRefPtr(libVisioMoveJNI.VgPostDrawCallbacks_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, VgIEnginePostDrawCallbackRefPtr val) {
        libVisioMoveJNI.VgPostDrawCallbacks_set(this.swigCPtr, this, i, VgIEnginePostDrawCallbackRefPtr.getCPtr(val), val);
    }
}
