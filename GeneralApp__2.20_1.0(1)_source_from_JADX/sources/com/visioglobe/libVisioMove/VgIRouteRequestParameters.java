package com.visioglobe.libVisioMove;

public class VgIRouteRequestParameters {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteRequestParameters(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteRequestParameters obj) {
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
                libVisioMoveJNI.delete_VgIRouteRequestParameters(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteRequestParameters() {
        this(libVisioMoveJNI.new_VgIRouteRequestParameters(), true);
    }

    public void setMDestinationsOrder(VgIRouteDestinationsOrder value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mDestinationsOrder_set(this.swigCPtr, this, value.swigValue());
    }

    public VgIRouteDestinationsOrder getMDestinationsOrder() {
        return VgIRouteDestinationsOrder.swigToEnum(libVisioMoveJNI.VgIRouteRequestParameters_mDestinationsOrder_get(this.swigCPtr, this));
    }

    public void setMRequestType(VgIRouteRequestType value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mRequestType_set(this.swigCPtr, this, value.swigValue());
    }

    public VgIRouteRequestType getMRequestType() {
        return VgIRouteRequestType.swigToEnum(libVisioMoveJNI.VgIRouteRequestParameters_mRequestType_get(this.swigCPtr, this));
    }

    public void setMOrigin(VgIRoutingNodeRefPtr value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mOrigin_set(this.swigCPtr, this, VgIRoutingNodeRefPtr.getCPtr(value), value);
    }

    public VgIRoutingNodeRefPtr getMOrigin() {
        long cPtr = libVisioMoveJNI.VgIRouteRequestParameters_mOrigin_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingNodeRefPtr(cPtr, false);
    }

    public void setMDestinations(VgIRoutingNodeRefPtrVector value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mDestinations_set(this.swigCPtr, this, VgIRoutingNodeRefPtrVector.getCPtr(value), value);
    }

    public VgIRoutingNodeRefPtrVector getMDestinations() {
        long cPtr = libVisioMoveJNI.VgIRouteRequestParameters_mDestinations_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingNodeRefPtrVector(cPtr, false);
    }

    public void setMExcludedAttributes(VgStringSet value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mExcludedAttributes_set(this.swigCPtr, this, VgStringSet.getCPtr(value), value);
    }

    public VgStringSet getMExcludedAttributes() {
        long cPtr = libVisioMoveJNI.VgIRouteRequestParameters_mExcludedAttributes_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgStringSet(cPtr, false);
    }

    public void setMExcludedModalities(VgStringSet value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mExcludedModalities_set(this.swigCPtr, this, VgStringSet.getCPtr(value), value);
    }

    public VgStringSet getMExcludedModalities() {
        long cPtr = libVisioMoveJNI.VgIRouteRequestParameters_mExcludedModalities_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgStringSet(cPtr, false);
    }

    public void setMRemapResultingModality(VgStringStringMap value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mRemapResultingModality_set(this.swigCPtr, this, VgStringStringMap.getCPtr(value), value);
    }

    public VgStringStringMap getMRemapResultingModality() {
        long cPtr = libVisioMoveJNI.VgIRouteRequestParameters_mRemapResultingModality_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgStringStringMap(cPtr, false);
    }

    public void setMCallback(VgIRouteCallbackRefPtr value) {
        libVisioMoveJNI.VgIRouteRequestParameters_mCallback_set(this.swigCPtr, this, VgIRouteCallbackRefPtr.getCPtr(value), value);
    }

    public VgIRouteCallbackRefPtr getMCallback() {
        long cPtr = libVisioMoveJNI.VgIRouteRequestParameters_mCallback_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteCallbackRefPtr(cPtr, false);
    }
}
