package com.visioglobe.libVisioMove;

public class VgINavigationModule extends VgIModule {
    private long swigCPtr;

    protected VgINavigationModule(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgINavigationModule_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationModule obj) {
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
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public VgINavigationRequest computeNavigation(VgINavigationRequestParameters pParameters) {
        long cPtr = libVisioMoveJNI.VgINavigationModule_computeNavigation(this.swigCPtr, this, VgINavigationRequestParameters.getCPtr(pParameters), pParameters);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationRequest(cPtr, false);
    }

    public VgINavigationRefPtr computeNavigationDirect(VgINavigationRequestParameters pParameters) {
        return new VgINavigationRefPtr(libVisioMoveJNI.VgINavigationModule_computeNavigationDirect(this.swigCPtr, this, VgINavigationRequestParameters.getCPtr(pParameters), pParameters), true);
    }

    public VgNearPlaceVector queryNearPlaces(VgPosition pPosition, VgNearPlacesParameters pParameters) {
        return new VgNearPlaceVector(libVisioMoveJNI.VgINavigationModule_queryNearPlaces(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, VgNearPlacesParameters.getCPtr(pParameters), pParameters), true);
    }
}
