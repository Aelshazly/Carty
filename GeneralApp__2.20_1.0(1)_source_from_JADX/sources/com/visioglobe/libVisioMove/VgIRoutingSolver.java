package com.visioglobe.libVisioMove;

public class VgIRoutingSolver {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRoutingSolver(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingSolver obj) {
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
                libVisioMoveJNI.delete_VgIRoutingSolver(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteRequest computeRoute(VgIRouteRequestParameters pParameters) {
        long cPtr = libVisioMoveJNI.VgIRoutingSolver_computeRoute(this.swigCPtr, this, VgIRouteRequestParameters.getCPtr(pParameters), pParameters);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteRequest(cPtr, false);
    }

    public VgIRouteRefPtr computeRouteDirect(VgIRouteRequestParameters pParameters) {
        return new VgIRouteRefPtr(libVisioMoveJNI.VgIRoutingSolver_computeRouteDirect(this.swigCPtr, this, VgIRouteRequestParameters.getCPtr(pParameters), pParameters), true);
    }

    public VgIRoutingNodeRefPtr getRoutingNode(VgPosition pPosition) {
        return new VgIRoutingNodeRefPtr(libVisioMoveJNI.VgIRoutingSolver_getRoutingNode__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition), true);
    }

    public VgIRoutingNodeRefPtr getRoutingNode(VgPosition pPosition, VgIRoutingNodeParameters pParameters) {
        return new VgIRoutingNodeRefPtr(libVisioMoveJNI.VgIRoutingSolver_getRoutingNode__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, VgIRoutingNodeParameters.getCPtr(pParameters), pParameters), true);
    }

    public VgIRoutingNodeRefPtr getRoutingNode(String pPoiID) {
        return new VgIRoutingNodeRefPtr(libVisioMoveJNI.VgIRoutingSolver_getRoutingNode__SWIG_2(this.swigCPtr, this, pPoiID), true);
    }
}
