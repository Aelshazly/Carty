package com.visioglobe.libVisioMove;

public class VgSpatial extends VgReferenced {
    private long swigCPtr;

    protected VgSpatial(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgSpatial_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSpatial obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgSpatial_setAnimation__SWIG_0(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgSpatial_setAnimation__SWIG_1(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setLocalAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgSpatial_setLocalAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgSpatial_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgSpatial_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgSpatial_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgSpatial_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgSpatial_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgSpatial_setPosition__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHaveGeographicCoherence);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgSpatial_setPosition__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgSpatial_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgSpatial_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public float getScale() {
        return libVisioMoveJNI.VgSpatial_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgSpatial_setScale(this.swigCPtr, this, pScale);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgSpatial_getZIndex(this.swigCPtr, this);
    }

    public void setZIndex(int pZIndex) {
        libVisioMoveJNI.VgSpatial_setZIndex(this.swigCPtr, this, pZIndex);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgSpatial_isDrawnOnTop(this.swigCPtr, this);
    }

    public void setDrawOnTop(boolean pEnable) {
        libVisioMoveJNI.VgSpatial_setDrawOnTop(this.swigCPtr, this, pEnable);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgSpatial_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgSpatial_isVisible(this.swigCPtr, this);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgSpatial_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgSpatial_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgSpatial_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }
}
