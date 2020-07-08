package com.visioglobe.libVisioMove;

public class VgLayerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLayerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLayerRefPtr() {
        this(libVisioMoveJNI.new_VgLayerRefPtr__SWIG_0(), true);
    }

    public VgLayerRefPtr(VgLayer pPointer) {
        this(libVisioMoveJNI.new_VgLayerRefPtr__SWIG_1(VgLayer.getCPtr(pPointer), pPointer), true);
    }

    public VgLayerRefPtr(VgLayerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLayerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLayerRefPtr getNull() {
        return new VgLayerRefPtr(libVisioMoveJNI.VgLayerRefPtr_getNull(), true);
    }

    public VgLayerRefPtr set(VgLayer pPointer) {
        return new VgLayerRefPtr(libVisioMoveJNI.VgLayerRefPtr_set(this.swigCPtr, this, VgLayer.getCPtr(pPointer), pPointer), false);
    }

    public VgLayer __ref__() {
        return new VgLayer(libVisioMoveJNI.VgLayerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLayer __deref__() {
        long cPtr = libVisioMoveJNI.VgLayerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayer(cPtr, false);
    }

    public VgLayer get() {
        long cPtr = libVisioMoveJNI.VgLayerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayer(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLayerRefPtr_isValid(this.swigCPtr, this);
    }

    public String getName() {
        return libVisioMoveJNI.VgLayerRefPtr_getName(this.swigCPtr, this);
    }

    public void setHostedVisible(boolean pVisible) {
        libVisioMoveJNI.VgLayerRefPtr_setHostedVisible(this.swigCPtr, this, pVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgLayerRefPtr_isVisible(this.swigCPtr, this);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgLayerRefPtr_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public void setLODFactor(float pLODFactor) {
        libVisioMoveJNI.VgLayerRefPtr_setLODFactor(this.swigCPtr, this, pLODFactor);
    }

    public void setLoadFactor(float pLoadFactor) {
        libVisioMoveJNI.VgLayerRefPtr_setLoadFactor(this.swigCPtr, this, pLoadFactor);
    }

    public float getLODFactor() {
        return libVisioMoveJNI.VgLayerRefPtr_getLODFactor(this.swigCPtr, this);
    }

    public float getLoadFactor() {
        return libVisioMoveJNI.VgLayerRefPtr_getLoadFactor(this.swigCPtr, this);
    }

    public VgSRSConstRefPtr getSRS() {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgLayerRefPtr_getSRS(this.swigCPtr, this), true);
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLayerRefPtr_setAnimation__SWIG_0(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLayerRefPtr_setAnimation__SWIG_1(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setLocalAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLayerRefPtr_setLocalAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLayerRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgLayerRefPtr_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLayerRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgLayerRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLayerRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgLayerRefPtr_setPosition__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHaveGeographicCoherence);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgLayerRefPtr_setPosition__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLayerRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgLayerRefPtr_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public float getScale() {
        return libVisioMoveJNI.VgLayerRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgLayerRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgLayerRefPtr_getZIndex(this.swigCPtr, this);
    }

    public void setZIndex(int pZIndex) {
        libVisioMoveJNI.VgLayerRefPtr_setZIndex(this.swigCPtr, this, pZIndex);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgLayerRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public void setDrawOnTop(boolean pEnable) {
        libVisioMoveJNI.VgLayerRefPtr_setDrawOnTop(this.swigCPtr, this, pEnable);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgLayerRefPtr_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgLayerRefPtr_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgLayerRefPtr_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgLayerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLayerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLayerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
