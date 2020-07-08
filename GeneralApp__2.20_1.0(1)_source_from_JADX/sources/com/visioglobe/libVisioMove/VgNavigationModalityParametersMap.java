package com.visioglobe.libVisioMove;

public class VgNavigationModalityParametersMap {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgNavigationModalityParametersMap(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgNavigationModalityParametersMap obj) {
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
                libVisioMoveJNI.delete_VgNavigationModalityParametersMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgNavigationModalityParametersMap() {
        this(libVisioMoveJNI.new_VgNavigationModalityParametersMap__SWIG_0(), true);
    }

    public VgNavigationModalityParametersMap(VgNavigationModalityParametersMap arg0) {
        this(libVisioMoveJNI.new_VgNavigationModalityParametersMap__SWIG_1(getCPtr(arg0), arg0), true);
    }

    public long size() {
        return libVisioMoveJNI.VgNavigationModalityParametersMap_size(this.swigCPtr, this);
    }

    public boolean empty() {
        return libVisioMoveJNI.VgNavigationModalityParametersMap_empty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgNavigationModalityParametersMap_clear(this.swigCPtr, this);
    }

    public double get(VgModalityParameterType key) {
        return libVisioMoveJNI.VgNavigationModalityParametersMap_get(this.swigCPtr, this, key.swigValue());
    }

    public void set(VgModalityParameterType key, double x) {
        libVisioMoveJNI.VgNavigationModalityParametersMap_set(this.swigCPtr, this, key.swigValue(), x);
    }

    public void del(VgModalityParameterType key) {
        libVisioMoveJNI.VgNavigationModalityParametersMap_del(this.swigCPtr, this, key.swigValue());
    }

    public boolean has_key(VgModalityParameterType key) {
        return libVisioMoveJNI.VgNavigationModalityParametersMap_has_key(this.swigCPtr, this, key.swigValue());
    }
}
