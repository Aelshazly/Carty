package com.visioglobe.libVisioMove;

public class VgPointRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPointRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPointRefPtr obj) {
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
                libVisioMoveJNI.delete_VgPointRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPointRefPtr() {
        this(libVisioMoveJNI.new_VgPointRefPtr__SWIG_0(), true);
    }

    public VgPointRefPtr(VgPoint pPointer) {
        this(libVisioMoveJNI.new_VgPointRefPtr__SWIG_1(VgPoint.getCPtr(pPointer), pPointer), true);
    }

    public VgPointRefPtr(VgPointRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgPointRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgPointRefPtr getNull() {
        return new VgPointRefPtr(libVisioMoveJNI.VgPointRefPtr_getNull(), true);
    }

    public VgPointRefPtr set(VgPoint pPointer) {
        return new VgPointRefPtr(libVisioMoveJNI.VgPointRefPtr_set(this.swigCPtr, this, VgPoint.getCPtr(pPointer), pPointer), false);
    }

    public VgPoint __ref__() {
        return new VgPoint(libVisioMoveJNI.VgPointRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgPoint __deref__() {
        long cPtr = libVisioMoveJNI.VgPointRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgPoint get() {
        long cPtr = libVisioMoveJNI.VgPointRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgPointRefPtr_isValid(this.swigCPtr, this);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgPointRefPtr_getType(this.swigCPtr, this));
    }

    public VgAltitudeMode getAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgPointRefPtr_getAltitudeMode(this.swigCPtr, this));
    }

    public void setAltitudeMode(VgAltitudeMode pAltitudeMode) {
        libVisioMoveJNI.VgPointRefPtr_setAltitudeMode(this.swigCPtr, this, pAltitudeMode.swigValue());
    }

    public VgAnchorMode getAnchorPosition() {
        return VgAnchorMode.swigToEnum(libVisioMoveJNI.VgPointRefPtr_getAnchorPosition(this.swigCPtr, this));
    }

    public void setAnchorPosition(VgAnchorMode pAnchorMode) {
        libVisioMoveJNI.VgPointRefPtr_setAnchorPosition(this.swigCPtr, this, pAnchorMode.swigValue());
    }

    public float getGeometryConstantSizeDistance() {
        return libVisioMoveJNI.VgPointRefPtr_getGeometryConstantSizeDistance(this.swigCPtr, this);
    }

    public void setGeometryConstantSizeDistance(float pDistanceInMeters) {
        libVisioMoveJNI.VgPointRefPtr_setGeometryConstantSizeDistance(this.swigCPtr, this, pDistanceInMeters);
    }

    public boolean isForceFrontFaceEnabled() {
        return libVisioMoveJNI.VgPointRefPtr_isForceFrontFaceEnabled(this.swigCPtr, this);
    }

    public void setForceFrontFace(boolean pEnable) {
        libVisioMoveJNI.VgPointRefPtr_setForceFrontFace(this.swigCPtr, this, pEnable);
    }

    public VgOrientationConstraints getOrientationConstraints() {
        return new VgOrientationConstraints(libVisioMoveJNI.VgPointRefPtr_getOrientationConstraints(this.swigCPtr, this), true);
    }

    public void setOrientationConstraints(VgOrientationConstraints pConstraints) {
        libVisioMoveJNI.VgPointRefPtr_setOrientationConstraints(this.swigCPtr, this, VgOrientationConstraints.getCPtr(pConstraints), pConstraints);
    }

    public long getNbMarkers() {
        return libVisioMoveJNI.VgPointRefPtr_getNbMarkers(this.swigCPtr, this);
    }

    public VgMarkerRefPtr editMarker(long pIndex) {
        return new VgMarkerRefPtr(libVisioMoveJNI.VgPointRefPtr_editMarker(this.swigCPtr, this, pIndex), true);
    }

    public boolean insertMarker(VgMarkerDescriptor pMarkerDescriptor, int pMarkerPosition) {
        return libVisioMoveJNI.VgPointRefPtr_insertMarker(this.swigCPtr, this, VgMarkerDescriptor.getCPtr(pMarkerDescriptor), pMarkerDescriptor, pMarkerPosition);
    }

    public boolean removeMarker(int pMarkerPosition) {
        return libVisioMoveJNI.VgPointRefPtr_removeMarker(this.swigCPtr, this, pMarkerPosition);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgPointRefPtr_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgPointRefPtr_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public boolean setSizePolicy(VgSizePolicy pSizePolicy) {
        return libVisioMoveJNI.VgPointRefPtr_setSizePolicy(this.swigCPtr, this, pSizePolicy.swigValue());
    }

    public VgSizePolicy getSizePolicy() {
        return VgSizePolicy.swigToEnum(libVisioMoveJNI.VgPointRefPtr_getSizePolicy(this.swigCPtr, this));
    }

    public void getBoundingRect(float[] pRectangleWidth, float[] pRectangleHeight) {
        libVisioMoveJNI.VgPointRefPtr_getBoundingRect(this.swigCPtr, this, pRectangleWidth, pRectangleHeight);
    }

    public void setBoundingRect(float pRectangleWidth, float pRectangleHeight) {
        libVisioMoveJNI.VgPointRefPtr_setBoundingRect(this.swigCPtr, this, pRectangleWidth, pRectangleHeight);
    }

    public VgLayerRefPtr getLayer() {
        return new VgLayerRefPtr(libVisioMoveJNI.VgPointRefPtr_getLayer(this.swigCPtr, this), true);
    }

    public void setLayer(VgLayerRefPtr pLayer, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgPointRefPtr_setLayer__SWIG_0(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer, pHaveGeographicCoherence);
    }

    public void setLayer(VgLayerRefPtr pLayer) {
        libVisioMoveJNI.VgPointRefPtr_setLayer__SWIG_1(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer);
    }

    public void addListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgPointRefPtr_addListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public void removeListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgPointRefPtr_removeListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgPointRefPtr_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public void setVisibilityRamp(VgVisibilityRamp pRamp) {
        libVisioMoveJNI.VgPointRefPtr_setVisibilityRamp(this.swigCPtr, this, VgVisibilityRamp.getCPtr(pRamp), pRamp);
    }

    public String getID() {
        return libVisioMoveJNI.VgPointRefPtr_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgPointRefPtr_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public void setNotifyPOISelectedOnClick(boolean pValue) {
        libVisioMoveJNI.VgPointRefPtr_setNotifyPOISelectedOnClick(this.swigCPtr, this, pValue);
    }

    public boolean getBoundingPositions(VgPositionVector pResult) {
        return libVisioMoveJNI.VgPointRefPtr_getBoundingPositions(this.swigCPtr, this, VgPositionVector.getCPtr(pResult), pResult);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgPointRefPtr_getLocalPosition(this.swigCPtr, this), true);
    }

    public void setLocalPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgPointRefPtr_setLocalPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgPointRefPtr_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgPointRefPtr_setAnimation__SWIG_0(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgPointRefPtr_setAnimation__SWIG_1(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setLocalAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgPointRefPtr_setLocalAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgPointRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgPointRefPtr_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgPointRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgPointRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgPointRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgPointRefPtr_setPosition__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHaveGeographicCoherence);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgPointRefPtr_setPosition__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgPointRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgPointRefPtr_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public float getScale() {
        return libVisioMoveJNI.VgPointRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgPointRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgPointRefPtr_getZIndex(this.swigCPtr, this);
    }

    public void setZIndex(int pZIndex) {
        libVisioMoveJNI.VgPointRefPtr_setZIndex(this.swigCPtr, this, pZIndex);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgPointRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public void setDrawOnTop(boolean pEnable) {
        libVisioMoveJNI.VgPointRefPtr_setDrawOnTop(this.swigCPtr, this, pEnable);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgPointRefPtr_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgPointRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgPointRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgPointRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgPointRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
