package com.visioglobe.libVisioMove;

public class VgMarkerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMarkerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarkerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMarkerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMarkerConstRefPtr() {
        this(libVisioMoveJNI.new_VgMarkerConstRefPtr__SWIG_0(), true);
    }

    public VgMarkerConstRefPtr(VgMarker pPointer) {
        this(libVisioMoveJNI.new_VgMarkerConstRefPtr__SWIG_1(VgMarker.getCPtr(pPointer), pPointer), true);
    }

    public VgMarkerConstRefPtr(VgMarkerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgMarkerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgMarkerConstRefPtr getNull() {
        return new VgMarkerConstRefPtr(libVisioMoveJNI.VgMarkerConstRefPtr_getNull(), true);
    }

    public VgMarkerConstRefPtr set(VgMarker pPointer) {
        return new VgMarkerConstRefPtr(libVisioMoveJNI.VgMarkerConstRefPtr_set(this.swigCPtr, this, VgMarker.getCPtr(pPointer), pPointer), false);
    }

    public VgMarker __ref__() {
        return new VgMarker(libVisioMoveJNI.VgMarkerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMarker __deref__() {
        long cPtr = libVisioMoveJNI.VgMarkerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarker(cPtr, false);
    }

    public VgMarker get() {
        long cPtr = libVisioMoveJNI.VgMarkerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarker(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMarkerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgMarkerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMarkerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMarkerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
