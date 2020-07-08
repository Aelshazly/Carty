package com.visioglobe.libVisioMove;

public class VgIRouteConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIRouteConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteConstRefPtr() {
        this(libVisioMoveJNI.new_VgIRouteConstRefPtr__SWIG_0(), true);
    }

    public VgIRouteConstRefPtr(VgIRoute pPointer) {
        this(libVisioMoveJNI.new_VgIRouteConstRefPtr__SWIG_1(VgIRoute.getCPtr(pPointer), pPointer), true);
    }

    public VgIRouteConstRefPtr(VgIRouteConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIRouteConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIRouteConstRefPtr getNull() {
        return new VgIRouteConstRefPtr(libVisioMoveJNI.VgIRouteConstRefPtr_getNull(), true);
    }

    public VgIRouteConstRefPtr set(VgIRoute pPointer) {
        return new VgIRouteConstRefPtr(libVisioMoveJNI.VgIRouteConstRefPtr_set(this.swigCPtr, this, VgIRoute.getCPtr(pPointer), pPointer), false);
    }

    public VgIRoute __ref__() {
        return new VgIRoute(libVisioMoveJNI.VgIRouteConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIRoute __deref__() {
        long cPtr = libVisioMoveJNI.VgIRouteConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoute(cPtr, false);
    }

    public VgIRoute get() {
        long cPtr = libVisioMoveJNI.VgIRouteConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoute(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIRouteConstRefPtr_isValid(this.swigCPtr, this);
    }

    public double getLength() {
        return libVisioMoveJNI.VgIRouteConstRefPtr_getLength(this.swigCPtr, this);
    }

    public double getDuration() {
        return libVisioMoveJNI.VgIRouteConstRefPtr_getDuration(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIRouteConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIRouteConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIRouteConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
