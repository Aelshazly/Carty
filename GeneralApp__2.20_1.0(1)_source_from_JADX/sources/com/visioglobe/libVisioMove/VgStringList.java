package com.visioglobe.libVisioMove;

public class VgStringList {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgStringList(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgStringList obj) {
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
                libVisioMoveJNI.delete_VgStringList(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgStringList() {
        this(libVisioMoveJNI.new_VgStringList(), true);
    }

    public long size() {
        return libVisioMoveJNI.VgStringList_size(this.swigCPtr, this);
    }

    public boolean isEmpty() {
        return libVisioMoveJNI.VgStringList_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgStringList_clear(this.swigCPtr, this);
    }

    public void add(String x) {
        libVisioMoveJNI.VgStringList_add(this.swigCPtr, this, x);
    }

    public String get(int i) {
        return libVisioMoveJNI.VgStringList_get(this.swigCPtr, this, i);
    }
}
