package com.visioglobe.libVisioMove;

public class VgIRoutingNodeRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRoutingNodeRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingNodeRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIRoutingNodeRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRoutingNodeRefPtr() {
        this(libVisioMoveJNI.new_VgIRoutingNodeRefPtr__SWIG_0(), true);
    }

    public VgIRoutingNodeRefPtr(VgIRoutingNode pPointer) {
        this(libVisioMoveJNI.new_VgIRoutingNodeRefPtr__SWIG_1(VgIRoutingNode.getCPtr(pPointer), pPointer), true);
    }

    public VgIRoutingNodeRefPtr(VgIRoutingNodeRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIRoutingNodeRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIRoutingNodeRefPtr getNull() {
        return new VgIRoutingNodeRefPtr(libVisioMoveJNI.VgIRoutingNodeRefPtr_getNull(), true);
    }

    public VgIRoutingNodeRefPtr set(VgIRoutingNode pPointer) {
        return new VgIRoutingNodeRefPtr(libVisioMoveJNI.VgIRoutingNodeRefPtr_set(this.swigCPtr, this, VgIRoutingNode.getCPtr(pPointer), pPointer), false);
    }

    public VgIRoutingNode __ref__() {
        return new VgIRoutingNode(libVisioMoveJNI.VgIRoutingNodeRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIRoutingNode __deref__() {
        long cPtr = libVisioMoveJNI.VgIRoutingNodeRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingNode(cPtr, false);
    }

    public VgIRoutingNode get() {
        long cPtr = libVisioMoveJNI.VgIRoutingNodeRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingNode(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean hasPosition() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_hasPosition(this.swigCPtr, this);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgIRoutingNodeRefPtr_getPosition(this.swigCPtr, this), false);
    }

    public boolean hasRoutePosition() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_hasRoutePosition(this.swigCPtr, this);
    }

    public VgPosition getRoutePosition() {
        return new VgPosition(libVisioMoveJNI.VgIRoutingNodeRefPtr_getRoutePosition(this.swigCPtr, this), false);
    }

    public boolean hasPoiID() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_hasPoiID(this.swigCPtr, this);
    }

    public String getPoiID() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_getPoiID(this.swigCPtr, this);
    }

    public String getLayerName() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_getLayerName(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIRoutingNodeRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIRoutingNodeRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
