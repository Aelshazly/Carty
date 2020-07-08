package com.visioglobe.libVisioMove;

public class VgIRoutingNode extends VgReferenced {
    private long swigCPtr;

    protected VgIRoutingNode(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIRoutingNode_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingNode obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public boolean hasPosition() {
        return libVisioMoveJNI.VgIRoutingNode_hasPosition(this.swigCPtr, this);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgIRoutingNode_getPosition(this.swigCPtr, this), false);
    }

    public boolean hasRoutePosition() {
        return libVisioMoveJNI.VgIRoutingNode_hasRoutePosition(this.swigCPtr, this);
    }

    public VgPosition getRoutePosition() {
        return new VgPosition(libVisioMoveJNI.VgIRoutingNode_getRoutePosition(this.swigCPtr, this), false);
    }

    public boolean hasPoiID() {
        return libVisioMoveJNI.VgIRoutingNode_hasPoiID(this.swigCPtr, this);
    }

    public String getPoiID() {
        return libVisioMoveJNI.VgIRoutingNode_getPoiID(this.swigCPtr, this);
    }

    public String getLayerName() {
        return libVisioMoveJNI.VgIRoutingNode_getLayerName(this.swigCPtr, this);
    }
}
