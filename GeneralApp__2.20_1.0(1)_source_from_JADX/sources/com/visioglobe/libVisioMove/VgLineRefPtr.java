package com.visioglobe.libVisioMove;

public class VgLineRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLineRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLineRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLineRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLineRefPtr() {
        this(libVisioMoveJNI.new_VgLineRefPtr__SWIG_0(), true);
    }

    public VgLineRefPtr(VgLine pPointer) {
        this(libVisioMoveJNI.new_VgLineRefPtr__SWIG_1(VgLine.getCPtr(pPointer), pPointer), true);
    }

    public VgLineRefPtr(VgLineRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLineRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLineRefPtr getNull() {
        return new VgLineRefPtr(libVisioMoveJNI.VgLineRefPtr_getNull(), true);
    }

    public VgLineRefPtr set(VgLine pPointer) {
        return new VgLineRefPtr(libVisioMoveJNI.VgLineRefPtr_set(this.swigCPtr, this, VgLine.getCPtr(pPointer), pPointer), false);
    }

    public VgLine __ref__() {
        return new VgLine(libVisioMoveJNI.VgLineRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLine __deref__() {
        long cPtr = libVisioMoveJNI.VgLineRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public VgLine get() {
        long cPtr = libVisioMoveJNI.VgLineRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLineRefPtr_isValid(this.swigCPtr, this);
    }

    public VgLineDescriptorConstRefPtr getDescriptor() {
        return new VgLineDescriptorConstRefPtr(libVisioMoveJNI.VgLineRefPtr_getDescriptor(this.swigCPtr, this), true);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgLineRefPtr_getType(this.swigCPtr, this));
    }

    public VgITextureRefPtr getTexture() {
        return new VgITextureRefPtr(libVisioMoveJNI.VgLineRefPtr_getTexture(this.swigCPtr, this), false);
    }

    public boolean setTexture(VgITextureRefPtr pTexture) {
        return libVisioMoveJNI.VgLineRefPtr_setTexture(this.swigCPtr, this, VgITextureRefPtr.getCPtr(pTexture), pTexture);
    }

    public float getTextureAnimationSpeed() {
        return libVisioMoveJNI.VgLineRefPtr_getTextureAnimationSpeed(this.swigCPtr, this);
    }

    public void setTextureAnimationSpeed(float pSpeed) {
        libVisioMoveJNI.VgLineRefPtr_setTextureAnimationSpeed(this.swigCPtr, this, pSpeed);
    }

    public VgPosition getInterpolatedPosition(float pValue) {
        return new VgPosition(libVisioMoveJNI.VgLineRefPtr_getInterpolatedPosition(this.swigCPtr, this, pValue), true);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgLineRefPtr_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgLineRefPtr_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public VgLayerRefPtr getLayer() {
        return new VgLayerRefPtr(libVisioMoveJNI.VgLineRefPtr_getLayer(this.swigCPtr, this), true);
    }

    public void setLayer(VgLayerRefPtr pLayer, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgLineRefPtr_setLayer__SWIG_0(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer, pHaveGeographicCoherence);
    }

    public void setLayer(VgLayerRefPtr pLayer) {
        libVisioMoveJNI.VgLineRefPtr_setLayer__SWIG_1(this.swigCPtr, this, VgLayerRefPtr.getCPtr(pLayer), pLayer);
    }

    public void addListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgLineRefPtr_addListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public void removeListener(VgIGeometryCallbackRefPtr pCallback) {
        libVisioMoveJNI.VgLineRefPtr_removeListener(this.swigCPtr, this, VgIGeometryCallbackRefPtr.getCPtr(pCallback), pCallback);
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgLineRefPtr_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public void setVisibilityRamp(VgVisibilityRamp pRamp) {
        libVisioMoveJNI.VgLineRefPtr_setVisibilityRamp(this.swigCPtr, this, VgVisibilityRamp.getCPtr(pRamp), pRamp);
    }

    public String getID() {
        return libVisioMoveJNI.VgLineRefPtr_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgLineRefPtr_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public void setNotifyPOISelectedOnClick(boolean pValue) {
        libVisioMoveJNI.VgLineRefPtr_setNotifyPOISelectedOnClick(this.swigCPtr, this, pValue);
    }

    public boolean getBoundingPositions(VgPositionVector pResult) {
        return libVisioMoveJNI.VgLineRefPtr_getBoundingPositions(this.swigCPtr, this, VgPositionVector.getCPtr(pResult), pResult);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgLineRefPtr_getLocalPosition(this.swigCPtr, this), true);
    }

    public void setLocalPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgLineRefPtr_setLocalPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgLineRefPtr_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLineRefPtr_setAnimation__SWIG_0(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLineRefPtr_setAnimation__SWIG_1(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setLocalAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLineRefPtr_setLocalAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLineRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgLineRefPtr_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLineRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgLineRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLineRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgLineRefPtr_setPosition__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHaveGeographicCoherence);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgLineRefPtr_setPosition__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLineRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgLineRefPtr_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public float getScale() {
        return libVisioMoveJNI.VgLineRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgLineRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgLineRefPtr_getZIndex(this.swigCPtr, this);
    }

    public void setZIndex(int pZIndex) {
        libVisioMoveJNI.VgLineRefPtr_setZIndex(this.swigCPtr, this, pZIndex);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgLineRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public void setDrawOnTop(boolean pEnable) {
        libVisioMoveJNI.VgLineRefPtr_setDrawOnTop(this.swigCPtr, this, pEnable);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgLineRefPtr_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgLineRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLineRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLineRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLineRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
