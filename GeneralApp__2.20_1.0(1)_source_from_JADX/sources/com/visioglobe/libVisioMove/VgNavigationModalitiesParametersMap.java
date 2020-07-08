package com.visioglobe.libVisioMove;

public class VgNavigationModalitiesParametersMap {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgNavigationModalitiesParametersMap(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgNavigationModalitiesParametersMap obj) {
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
                libVisioMoveJNI.delete_VgNavigationModalitiesParametersMap(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgNavigationModalitiesParametersMap() {
        this(libVisioMoveJNI.new_VgNavigationModalitiesParametersMap__SWIG_0(), true);
    }

    public VgNavigationModalitiesParametersMap(VgNavigationModalitiesParametersMap arg0) {
        this(libVisioMoveJNI.new_VgNavigationModalitiesParametersMap__SWIG_1(getCPtr(arg0), arg0), true);
    }

    public long size() {
        return libVisioMoveJNI.VgNavigationModalitiesParametersMap_size(this.swigCPtr, this);
    }

    public boolean empty() {
        return libVisioMoveJNI.VgNavigationModalitiesParametersMap_empty(this.swigCPtr, this);
    }

    public void clear() {
        libVisioMoveJNI.VgNavigationModalitiesParametersMap_clear(this.swigCPtr, this);
    }

    public VgNavigationModalityParametersMap get(String key) {
        return new VgNavigationModalityParametersMap(libVisioMoveJNI.VgNavigationModalitiesParametersMap_get(this.swigCPtr, this, key), false);
    }

    public void set(String key, VgNavigationModalityParametersMap x) {
        libVisioMoveJNI.VgNavigationModalitiesParametersMap_set(this.swigCPtr, this, key, VgNavigationModalityParametersMap.getCPtr(x), x);
    }

    public void del(String key) {
        libVisioMoveJNI.VgNavigationModalitiesParametersMap_del(this.swigCPtr, this, key);
    }

    public boolean has_key(String key) {
        return libVisioMoveJNI.VgNavigationModalitiesParametersMap_has_key(this.swigCPtr, this, key);
    }
}
