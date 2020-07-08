package com.visioglobe.libVisioMove;

public class VgMarkerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMarkerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarkerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMarkerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMarkerRefPtr() {
        this(libVisioMoveJNI.new_VgMarkerRefPtr__SWIG_0(), true);
    }

    public VgMarkerRefPtr(VgMarker pPointer) {
        this(libVisioMoveJNI.new_VgMarkerRefPtr__SWIG_1(VgMarker.getCPtr(pPointer), pPointer), true);
    }

    public VgMarkerRefPtr(VgMarkerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgMarkerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgMarkerRefPtr getNull() {
        return new VgMarkerRefPtr(libVisioMoveJNI.VgMarkerRefPtr_getNull(), true);
    }

    public VgMarkerRefPtr set(VgMarker pPointer) {
        return new VgMarkerRefPtr(libVisioMoveJNI.VgMarkerRefPtr_set(this.swigCPtr, this, VgMarker.getCPtr(pPointer), pPointer), false);
    }

    public VgMarker __ref__() {
        return new VgMarker(libVisioMoveJNI.VgMarkerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMarker __deref__() {
        long cPtr = libVisioMoveJNI.VgMarkerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarker(cPtr, false);
    }

    public VgMarker get() {
        long cPtr = libVisioMoveJNI.VgMarkerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarker(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMarkerRefPtr_isValid(this.swigCPtr, this);
    }

    public VgMarkerRefPtr(VgIconMarkerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_61__(VgIconMarkerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgMarkerRefPtr(VgTextMarkerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_62__(VgTextMarkerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgIconMarker asIconMarker() {
        long cPtr = libVisioMoveJNI.VgMarkerRefPtr_asIconMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public VgTextMarker asTextMarker() {
        long cPtr = libVisioMoveJNI.VgMarkerRefPtr_asTextMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgMarkerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMarkerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMarkerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
