package com.visioglobe.libVisioMove;

public class VgIconMarkerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIconMarkerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIconMarkerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIconMarkerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIconMarkerConstRefPtr() {
        this(libVisioMoveJNI.new_VgIconMarkerConstRefPtr__SWIG_0(), true);
    }

    public VgIconMarkerConstRefPtr(VgIconMarker pPointer) {
        this(libVisioMoveJNI.new_VgIconMarkerConstRefPtr__SWIG_1(VgIconMarker.getCPtr(pPointer), pPointer), true);
    }

    public VgIconMarkerConstRefPtr(VgIconMarkerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIconMarkerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIconMarkerConstRefPtr getNull() {
        return new VgIconMarkerConstRefPtr(libVisioMoveJNI.VgIconMarkerConstRefPtr_getNull(), true);
    }

    public VgIconMarkerConstRefPtr set(VgIconMarker pPointer) {
        return new VgIconMarkerConstRefPtr(libVisioMoveJNI.VgIconMarkerConstRefPtr_set(this.swigCPtr, this, VgIconMarker.getCPtr(pPointer), pPointer), false);
    }

    public VgIconMarker __ref__() {
        return new VgIconMarker(libVisioMoveJNI.VgIconMarkerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIconMarker __deref__() {
        long cPtr = libVisioMoveJNI.VgIconMarkerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public VgIconMarker get() {
        long cPtr = libVisioMoveJNI.VgIconMarkerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIconMarkerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public float getScale() {
        return libVisioMoveJNI.VgIconMarkerConstRefPtr_getScale(this.swigCPtr, this);
    }

    public VgColor getColor() {
        return new VgColor(libVisioMoveJNI.VgIconMarkerConstRefPtr_getColor(this.swigCPtr, this), true);
    }

    public void ref() {
        libVisioMoveJNI.VgIconMarkerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIconMarkerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIconMarkerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
