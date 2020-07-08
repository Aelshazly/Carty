package com.visioglobe.libVisioMove;

public class VgIGeometryRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIGeometryRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometryRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIGeometryRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIGeometryRefPtr() {
        this(libVisioMoveJNI.new_VgIGeometryRefPtr__SWIG_0(), true);
    }

    public VgIGeometryRefPtr(VgIGeometry pPointer) {
        this(libVisioMoveJNI.new_VgIGeometryRefPtr__SWIG_1(VgIGeometry.getCPtr(pPointer), pPointer), true);
    }

    public VgIGeometryRefPtr(VgIGeometryRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIGeometryRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIGeometryRefPtr getNull() {
        return new VgIGeometryRefPtr(libVisioMoveJNI.VgIGeometryRefPtr_getNull(), true);
    }

    public VgIGeometryRefPtr set(VgIGeometry pPointer) {
        return new VgIGeometryRefPtr(libVisioMoveJNI.VgIGeometryRefPtr_set(this.swigCPtr, this, VgIGeometry.getCPtr(pPointer), pPointer), false);
    }

    public VgIGeometry __ref__() {
        return new VgIGeometry(libVisioMoveJNI.VgIGeometryRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIGeometry __deref__() {
        long cPtr = libVisioMoveJNI.VgIGeometryRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgIGeometry get() {
        long cPtr = libVisioMoveJNI.VgIGeometryRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIGeometryRefPtr_isValid(this.swigCPtr, this);
    }

    public VgIGeometryRefPtr(VgPointRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_67__(VgPointRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgIGeometryRefPtr(VgLineRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_68__(VgLineRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgIGeometryRefPtr_getType(this.swigCPtr, this));
    }

    public VgLayerRefPtr getLayer() {
        return new VgLayerRefPtr(libVisioMoveJNI.VgIGeometryRefPtr_getLayer(this.swigCPtr, this), true);
    }

    public void setLayer(VgLayerRefPtr pLayer, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgIGeometryRefPtr_setLayer__SWIG_0(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer, pHaveGeographicCoherence);
    }

    public void setLayer(VgLayerRefPtr pLayer) {
        libVisioMoveJNI.VgIGeometryRefPtr_setLayer__SWIG_1(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer);
    }

    public void addListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgIGeometryRefPtr_addListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public void removeListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgIGeometryRefPtr_removeListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgIGeometryRefPtr_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public void setVisibilityRamp(VgVisibilityRamp pRamp) {
        libVisioMoveJNI.VgIGeometryRefPtr_setVisibilityRamp(this.swigCPtr, this, VgVisibilityRamp.getCPtr(pRamp), pRamp);
    }

    public String getID() {
        return libVisioMoveJNI.VgIGeometryRefPtr_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgIGeometryRefPtr_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public void setNotifyPOISelectedOnClick(boolean pValue) {
        libVisioMoveJNI.VgIGeometryRefPtr_setNotifyPOISelectedOnClick(this.swigCPtr, this, pValue);
    }

    public boolean getBoundingPositions(VgPositionVector pResult) {
        return libVisioMoveJNI.VgIGeometryRefPtr_getBoundingPositions(this.swigCPtr, this, VgPositionVector.getCPtr(pResult), pResult);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgIGeometryRefPtr_getLocalPosition(this.swigCPtr, this), true);
    }

    public void setLocalPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgIGeometryRefPtr_setLocalPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgIGeometryRefPtr_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgIGeometryRefPtr_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgIGeometryRefPtr_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgIGeometryRefPtr_setAnimation__SWIG_0(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgIGeometryRefPtr_setAnimation__SWIG_1(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setLocalAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgIGeometryRefPtr_setLocalAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgIGeometryRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgIGeometryRefPtr_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgIGeometryRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgIGeometryRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgIGeometryRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgIGeometryRefPtr_setPosition__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHaveGeographicCoherence);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgIGeometryRefPtr_setPosition__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgIGeometryRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgIGeometryRefPtr_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public float getScale() {
        return libVisioMoveJNI.VgIGeometryRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgIGeometryRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgIGeometryRefPtr_getZIndex(this.swigCPtr, this);
    }

    public void setZIndex(int pZIndex) {
        libVisioMoveJNI.VgIGeometryRefPtr_setZIndex(this.swigCPtr, this, pZIndex);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgIGeometryRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public void setDrawOnTop(boolean pEnable) {
        libVisioMoveJNI.VgIGeometryRefPtr_setDrawOnTop(this.swigCPtr, this, pEnable);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgIGeometryRefPtr_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgIGeometryRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIGeometryRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIGeometryRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIGeometryRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
