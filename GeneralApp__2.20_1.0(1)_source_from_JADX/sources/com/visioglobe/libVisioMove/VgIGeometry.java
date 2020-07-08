package com.visioglobe.libVisioMove;

public class VgIGeometry extends VgSpatial {
    private long swigCPtr;

    protected VgIGeometry(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIGeometry_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometry obj) {
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

    public VgIGeometry() {
        this(libVisioMoveJNI.new_VgIGeometry(), true);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgIGeometry_getType(this.swigCPtr, this));
    }

    public VgLayerRefPtr getLayer() {
        return new VgLayerRefPtr(libVisioMoveJNI.VgIGeometry_getLayer(this.swigCPtr, this), true);
    }

    public void setLayer(VgLayerRefPtr pLayer, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgIGeometry_setLayer__SWIG_0(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer, pHaveGeographicCoherence);
    }

    public void setLayer(VgLayerRefPtr pLayer) {
        libVisioMoveJNI.VgIGeometry_setLayer__SWIG_1(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer);
    }

    public void addListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgIGeometry_addListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public void removeListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgIGeometry_removeListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgIGeometry_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public void setVisibilityRamp(VgVisibilityRamp pRamp) {
        libVisioMoveJNI.VgIGeometry_setVisibilityRamp(this.swigCPtr, this, VgVisibilityRamp.getCPtr(pRamp), pRamp);
    }

    public String getID() {
        return libVisioMoveJNI.VgIGeometry_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgIGeometry_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public void setNotifyPOISelectedOnClick(boolean pValue) {
        libVisioMoveJNI.VgIGeometry_setNotifyPOISelectedOnClick(this.swigCPtr, this, pValue);
    }

    public boolean getBoundingPositions(VgPositionVector pResult) {
        return libVisioMoveJNI.VgIGeometry_getBoundingPositions(this.swigCPtr, this, VgPositionVector.getCPtr(pResult), pResult);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgIGeometry_getLocalPosition(this.swigCPtr, this), true);
    }

    public void setLocalPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgIGeometry_setLocalPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgIGeometry_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgIGeometry_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgIGeometry_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }
}
