package com.visioglobe.libVisioMove;

public class VgStringSet {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgStringSet(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgStringSet obj) {
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
                libVisioMoveJNI.delete_VgStringSet(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgStringSet() {
        this(libVisioMoveJNI.new_VgStringSet__SWIG_0(), true);
    }

    public VgStringSet(VgStringSet arg0) {
        this(libVisioMoveJNI.new_VgStringSet__SWIG_1(getCPtr(arg0), arg0), true);
    }

    public long size() {
        return libVisioMoveJNI.VgStringSet_size(this.swigCPtr, this);
    }

    public boolean empty() {
        return libVisioMoveJNI.VgStringSet_empty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgStringSet_clear(this.swigCPtr, this);
    }

    public String get(String key) {
        return libVisioMoveJNI.VgStringSet_get__SWIG_0(this.swigCPtr, this, key);
    }

    public void insert(String key) {
        libVisioMoveJNI.VgStringSet_insert(this.swigCPtr, this, key);
    }

    public void del(String key) {
        libVisioMoveJNI.VgStringSet_del(this.swigCPtr, this, key);
    }

    public boolean has_key(String key) {
        return libVisioMoveJNI.VgStringSet_has_key(this.swigCPtr, this, key);
    }

    public String get(int i) {
        return libVisioMoveJNI.VgStringSet_get__SWIG_1(this.swigCPtr, this, i);
    }
}
