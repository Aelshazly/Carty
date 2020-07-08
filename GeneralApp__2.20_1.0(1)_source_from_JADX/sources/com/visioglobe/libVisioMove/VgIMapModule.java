package com.visioglobe.libVisioMove;

public class VgIMapModule extends VgIModule {
    private long swigCPtr;

    protected VgIMapModule(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIMapModule_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIMapModule obj) {
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

    public void addListener(VgIPlaceListenerRefPtr pListener) {
        libVisioMoveJNI.VgIMapModule_addListener(this.swigCPtr, this, VgIPlaceListenerRefPtr.getCPtr(pListener), pListener);
    }

    public void removeListener(VgIPlaceListenerRefPtr pListener) {
        libVisioMoveJNI.VgIMapModule_removeListener(this.swigCPtr, this, VgIPlaceListenerRefPtr.getCPtr(pListener), pListener);
    }

    public void setPlaceName(String pID, String pName) {
        libVisioMoveJNI.VgIMapModule_setPlaceName(this.swigCPtr, this, pID, pName);
    }

    public void setPlaceIcon(String pID, VgPlaceIconDescriptor pPlaceIconDescriptor) {
        libVisioMoveJNI.VgIMapModule_setPlaceIcon(this.swigCPtr, this, pID, VgPlaceIconDescriptor.getCPtr(pPlaceIconDescriptor), pPlaceIconDescriptor);
    }

    public boolean getPlaceName(String pID, String[] pPlaceName) {
        return libVisioMoveJNI.VgIMapModule_getPlaceName(this.swigCPtr, this, pID, pPlaceName);
    }

    public boolean queryPlaceDescriptor(String pID, VgPlaceDescriptor pPlaceDescriptor) {
        return libVisioMoveJNI.VgIMapModule_queryPlaceDescriptor(this.swigCPtr, this, pID, VgPlaceDescriptor.getCPtr(pPlaceDescriptor), pPlaceDescriptor);
    }

    public boolean queryPOIDescriptor(String pID, VgPOIDescriptor pPOIDescriptor) {
        return libVisioMoveJNI.VgIMapModule_queryPOIDescriptor(this.swigCPtr, this, pID, VgPOIDescriptor.getCPtr(pPOIDescriptor), pPOIDescriptor);
    }

    public void queryAllPlaceIDs(VgStringVector pPlaceIDs) {
        libVisioMoveJNI.VgIMapModule_queryAllPlaceIDs(this.swigCPtr, this, VgStringVector.getCPtr(pPlaceIDs), pPlaceIDs);
    }

    public boolean getLayerForPosition(VgPosition pPos, String[] pLayerNameContainingPosition) {
        return libVisioMoveJNI.VgIMapModule_getLayerForPosition(this.swigCPtr, this, VgPosition.getCPtr(pPos), pPos, pLayerNameContainingPosition);
    }

    public boolean getHeightRangeForLayer(String pLayerName, float[] pHeightMin, float[] pHeightMax) {
        return libVisioMoveJNI.VgIMapModule_getHeightRangeForLayer(this.swigCPtr, this, pLayerName, pHeightMin, pHeightMax);
    }

    public void setPlaceColor(String pID, VgPlaceColorDescriptor pColorDescriptor) {
        libVisioMoveJNI.VgIMapModule_setPlaceColor__SWIG_0(this.swigCPtr, this, pID, VgPlaceColorDescriptor.getCPtr(pColorDescriptor), pColorDescriptor);
    }

    public void setPlaceColor(VgStringVector pIDs, VgPlaceColorDescriptor pColorDescriptor) {
        libVisioMoveJNI.VgIMapModule_setPlaceColor__SWIG_1(this.swigCPtr, this, VgStringVector.getCPtr(pIDs), pIDs, VgPlaceColorDescriptor.getCPtr(pColorDescriptor), pColorDescriptor);
    }

    public void resetPlaceColor(String pID) {
        libVisioMoveJNI.VgIMapModule_resetPlaceColor__SWIG_0(this.swigCPtr, this, pID);
    }

    public void resetPlaceColor(VgStringVector pIDs) {
        libVisioMoveJNI.VgIMapModule_resetPlaceColor__SWIG_1(this.swigCPtr, this, VgStringVector.getCPtr(pIDs), pIDs);
    }

    public boolean getGeofences(VgStringVector pPoiIds) {
        return libVisioMoveJNI.VgIMapModule_getGeofences__SWIG_0(this.swigCPtr, this, VgStringVector.getCPtr(pPoiIds), pPoiIds);
    }

    public boolean getGeofences(VgStringSet pPoiIds) {
        return libVisioMoveJNI.VgIMapModule_getGeofences__SWIG_1(this.swigCPtr, this, VgStringSet.getCPtr(pPoiIds), pPoiIds);
    }

    public boolean getGeofences(VgPosition pPosition, VgStringVector pGeofenceIDs) {
        return libVisioMoveJNI.VgIMapModule_getGeofences__SWIG_2(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, VgStringVector.getCPtr(pGeofenceIDs), pGeofenceIDs);
    }

    public boolean getGeofences(VgPosition pPosition, String pLayerName, VgStringVector pGeofenceIDs) {
        return libVisioMoveJNI.VgIMapModule_getGeofences__SWIG_3(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pLayerName, VgStringVector.getCPtr(pGeofenceIDs), pGeofenceIDs);
    }
}
