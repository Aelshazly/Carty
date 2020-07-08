package com.visioglobe.libVisioMove;

public class VgTextMarkerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgTextMarkerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgTextMarkerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgTextMarkerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgTextMarkerConstRefPtr() {
        this(libVisioMoveJNI.new_VgTextMarkerConstRefPtr__SWIG_0(), true);
    }

    public VgTextMarkerConstRefPtr(VgTextMarker pPointer) {
        this(libVisioMoveJNI.new_VgTextMarkerConstRefPtr__SWIG_1(VgTextMarker.getCPtr(pPointer), pPointer), true);
    }

    public VgTextMarkerConstRefPtr(VgTextMarkerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgTextMarkerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgTextMarkerConstRefPtr getNull() {
        return new VgTextMarkerConstRefPtr(libVisioMoveJNI.VgTextMarkerConstRefPtr_getNull(), true);
    }

    public VgTextMarkerConstRefPtr set(VgTextMarker pPointer) {
        return new VgTextMarkerConstRefPtr(libVisioMoveJNI.VgTextMarkerConstRefPtr_set(this.swigCPtr, this, VgTextMarker.getCPtr(pPointer), pPointer), false);
    }

    public VgTextMarker __ref__() {
        return new VgTextMarker(libVisioMoveJNI.VgTextMarkerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgTextMarker __deref__() {
        long cPtr = libVisioMoveJNI.VgTextMarkerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public VgTextMarker get() {
        long cPtr = libVisioMoveJNI.VgTextMarkerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgTextMarkerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public String getText() {
        return libVisioMoveJNI.VgTextMarkerConstRefPtr_getText(this.swigCPtr, this);
    }

    public long getTextAttributes() {
        return libVisioMoveJNI.VgTextMarkerConstRefPtr_getTextAttributes(this.swigCPtr, this);
    }

    public VgColor getColor() {
        return new VgColor(libVisioMoveJNI.VgTextMarkerConstRefPtr_getColor(this.swigCPtr, this), true);
    }

    public float getScale() {
        return libVisioMoveJNI.VgTextMarkerConstRefPtr_getScale(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgTextMarkerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgTextMarkerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgTextMarkerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
