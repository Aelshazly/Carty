package com.visioglobe.libVisioMove;

public class VgStringStringMap {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgStringStringMap(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgStringStringMap obj) {
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
                libVisioMoveJNI.delete_VgStringStringMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgStringStringMap() {
        this(libVisioMoveJNI.new_VgStringStringMap__SWIG_0(), true);
    }

    public VgStringStringMap(VgStringStringMap arg0) {
        this(libVisioMoveJNI.new_VgStringStringMap__SWIG_1(getCPtr(arg0), arg0), true);
    }

    public long size() {
        return libVisioMoveJNI.VgStringStringMap_size(this.swigCPtr, this);
    }

    public boolean empty() {
        return libVisioMoveJNI.VgStringStringMap_empty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgStringStringMap_clear(this.swigCPtr, this);
    }

    public String get(String key) {
        return libVisioMoveJNI.VgStringStringMap_get(this.swigCPtr, this, key);
    }

    public void set(String key, String x) {
        libVisioMoveJNI.VgStringStringMap_set(this.swigCPtr, this, key, x);
    }

    public void del(String key) {
        libVisioMoveJNI.VgStringStringMap_del(this.swigCPtr, this, key);
    }

    public boolean has_key(String key) {
        return libVisioMoveJNI.VgStringStringMap_has_key(this.swigCPtr, this, key);
    }
}
