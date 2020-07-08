package com.visioglobe.libVisioMove;

public class VgIRoutingNodeConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRoutingNodeConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingNodeConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIRoutingNodeConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRoutingNodeConstRefPtr() {
        this(libVisioMoveJNI.new_VgIRoutingNodeConstRefPtr__SWIG_0(), true);
    }

    public VgIRoutingNodeConstRefPtr(VgIRoutingNode pPointer) {
        this(libVisioMoveJNI.new_VgIRoutingNodeConstRefPtr__SWIG_1(VgIRoutingNode.getCPtr(pPointer), pPointer), true);
    }

    public VgIRoutingNodeConstRefPtr(VgIRoutingNodeConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIRoutingNodeConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIRoutingNodeConstRefPtr getNull() {
        return new VgIRoutingNodeConstRefPtr(libVisioMoveJNI.VgIRoutingNodeConstRefPtr_getNull(), true);
    }

    public VgIRoutingNodeConstRefPtr set(VgIRoutingNode pPointer) {
        return new VgIRoutingNodeConstRefPtr(libVisioMoveJNI.VgIRoutingNodeConstRefPtr_set(this.swigCPtr, this, VgIRoutingNode.getCPtr(pPointer), pPointer), false);
    }

    public VgIRoutingNode __ref__() {
        return new VgIRoutingNode(libVisioMoveJNI.VgIRoutingNodeConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIRoutingNode __deref__() {
        long cPtr = libVisioMoveJNI.VgIRoutingNodeConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingNode(cPtr, false);
    }

    public VgIRoutingNode get() {
        long cPtr = libVisioMoveJNI.VgIRoutingNodeConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingNode(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_isValid(this.swigCPtr, this);
    }

    public boolean hasPosition() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_hasPosition(this.swigCPtr, this);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgIRoutingNodeConstRefPtr_getPosition(this.swigCPtr, this), false);
    }

    public boolean hasRoutePosition() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_hasRoutePosition(this.swigCPtr, this);
    }

    public VgPosition getRoutePosition() {
        return new VgPosition(libVisioMoveJNI.VgIRoutingNodeConstRefPtr_getRoutePosition(this.swigCPtr, this), false);
    }

    public boolean hasPoiID() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_hasPoiID(this.swigCPtr, this);
    }

    public String getPoiID() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_getPoiID(this.swigCPtr, this);
    }

    public String getLayerName() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_getLayerName(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIRoutingNodeConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIRoutingNodeConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
