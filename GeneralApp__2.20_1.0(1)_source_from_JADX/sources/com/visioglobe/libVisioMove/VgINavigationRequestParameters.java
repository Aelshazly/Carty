package com.visioglobe.libVisioMove;

public class VgINavigationRequestParameters {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationRequestParameters(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationRequestParameters obj) {
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
                libVisioMoveJNI.delete_VgINavigationRequestParameters(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationRequestParameters(long pAttributes, VgINavigationCallbackRefPtr pCallback, VgIRouteRefPtr pRoute) {
        this(libVisioMoveJNI.new_VgINavigationRequestParameters__SWIG_0(pAttributes, VgINavigationCallbackRefPtr.getCPtr(pCallback), pCallback, VgIRouteRefPtr.getCPtr(pRoute), pRoute), true);
    }

    public VgINavigationRequestParameters(VgIRouteRefPtr pRoute) {
        this(libVisioMoveJNI.new_VgINavigationRequestParameters__SWIG_1(VgIRouteRefPtr.getCPtr(pRoute), pRoute), true);
    }

    public void setMCallback(VgINavigationCallbackRefPtr value) {
        libVisioMoveJNI.VgINavigationRequestParameters_mCallback_set(this.swigCPtr, this, VgINavigationCallbackRefPtr.getCPtr(value), value);
    }

    public VgINavigationCallbackRefPtr getMCallback() {
        long cPtr = libVisioMoveJNI.VgINavigationRequestParameters_mCallback_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationCallbackRefPtr(cPtr, false);
    }

    public void setMRoute(VgIRouteRefPtr value) {
        libVisioMoveJNI.VgINavigationRequestParameters_mRoute_set(this.swigCPtr, this, VgIRouteRefPtr.getCPtr(value), value);
    }

    public VgIRouteRefPtr getMRoute() {
        long cPtr = libVisioMoveJNI.VgINavigationRequestParameters_mRoute_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteRefPtr(cPtr, false);
    }

    public void setMMergeFloorChangeInstructions(boolean value) {
        libVisioMoveJNI.VgINavigationRequestParameters_mMergeFloorChangeInstructions_set(this.swigCPtr, this, value);
    }

    public boolean getMMergeFloorChangeInstructions() {
        return libVisioMoveJNI.VgINavigationRequestParameters_mMergeFloorChangeInstructions_get(this.swigCPtr, this);
    }

    public void setMModalityParameters(VgNavigationModalitiesParametersMap value) {
        libVisioMoveJNI.VgINavigationRequestParameters_mModalityParameters_set(this.swigCPtr, this, VgNavigationModalitiesParametersMap.getCPtr(value), value);
    }

    public VgNavigationModalitiesParametersMap getMModalityParameters() {
        long cPtr = libVisioMoveJNI.VgINavigationRequestParameters_mModalityParameters_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgNavigationModalitiesParametersMap(cPtr, false);
    }

    public void setMAlgorithm(VgNavigationAlgorithm value) {
        libVisioMoveJNI.VgINavigationRequestParameters_mAlgorithm_set(this.swigCPtr, this, value.swigValue());
    }

    public VgNavigationAlgorithm getMAlgorithm() {
        return VgNavigationAlgorithm.swigToEnum(libVisioMoveJNI.VgINavigationRequestParameters_mAlgorithm_get(this.swigCPtr, this));
    }

    public void setMFirstNodeAsIntersection(boolean value) {
        libVisioMoveJNI.VgINavigationRequestParameters_mFirstNodeAsIntersection_set(this.swigCPtr, this, value);
    }

    public boolean getMFirstNodeAsIntersection() {
        return libVisioMoveJNI.VgINavigationRequestParameters_mFirstNodeAsIntersection_get(this.swigCPtr, this);
    }
}
