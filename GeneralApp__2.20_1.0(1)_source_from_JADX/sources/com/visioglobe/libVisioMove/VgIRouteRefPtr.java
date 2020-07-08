package com.visioglobe.libVisioMove;

public class VgIRouteRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIRouteRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteRefPtr() {
        this(libVisioMoveJNI.new_VgIRouteRefPtr__SWIG_0(), true);
    }

    public VgIRouteRefPtr(VgIRoute pPointer) {
        this(libVisioMoveJNI.new_VgIRouteRefPtr__SWIG_1(VgIRoute.getCPtr(pPointer), pPointer), true);
    }

    public VgIRouteRefPtr(VgIRouteRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIRouteRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIRouteRefPtr getNull() {
        return new VgIRouteRefPtr(libVisioMoveJNI.VgIRouteRefPtr_getNull(), true);
    }

    public VgIRouteRefPtr set(VgIRoute pPointer) {
        return new VgIRouteRefPtr(libVisioMoveJNI.VgIRouteRefPtr_set(this.swigCPtr, this, VgIRoute.getCPtr(pPointer), pPointer), false);
    }

    public VgIRoute __ref__() {
        return new VgIRoute(libVisioMoveJNI.VgIRouteRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIRoute __deref__() {
        long cPtr = libVisioMoveJNI.VgIRouteRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoute(cPtr, false);
    }

    public VgIRoute get() {
        long cPtr = libVisioMoveJNI.VgIRouteRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoute(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIRouteRefPtr_isValid(this.swigCPtr, this);
    }

    public double getLength() {
        return libVisioMoveJNI.VgIRouteRefPtr_getLength(this.swigCPtr, this);
    }

    public double getDuration() {
        return libVisioMoveJNI.VgIRouteRefPtr_getDuration(this.swigCPtr, this);
    }

    public VgIntVector getDestinationIndices() {
        return new VgIntVector(libVisioMoveJNI.VgIRouteRefPtr_getDestinationIndices(this.swigCPtr, this), false);
    }

    public VgIRouteRequestParameters getRequestParameters() {
        return new VgIRouteRequestParameters(libVisioMoveJNI.VgIRouteRefPtr_getRequestParameters(this.swigCPtr, this), false);
    }

    public void ref() {
        libVisioMoveJNI.VgIRouteRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIRouteRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIRouteRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
