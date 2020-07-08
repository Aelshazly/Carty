package com.visioglobe.libVisioMove;

public class VgSpatialRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSpatialRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSpatialRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSpatialRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSpatialRefPtr() {
        this(libVisioMoveJNI.new_VgSpatialRefPtr__SWIG_0(), true);
    }

    public VgSpatialRefPtr(VgSpatial pPointer) {
        this(libVisioMoveJNI.new_VgSpatialRefPtr__SWIG_1(VgSpatial.getCPtr(pPointer), pPointer), true);
    }

    public VgSpatialRefPtr(VgSpatialRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgSpatialRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgSpatialRefPtr getNull() {
        return new VgSpatialRefPtr(libVisioMoveJNI.VgSpatialRefPtr_getNull(), true);
    }

    public VgSpatialRefPtr set(VgSpatial pPointer) {
        return new VgSpatialRefPtr(libVisioMoveJNI.VgSpatialRefPtr_set(this.swigCPtr, this, VgSpatial.getCPtr(pPointer), pPointer), false);
    }

    public VgSpatial __ref__() {
        return new VgSpatial(libVisioMoveJNI.VgSpatialRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSpatial __deref__() {
        long cPtr = libVisioMoveJNI.VgSpatialRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSpatial(cPtr, false);
    }

    public VgSpatial get() {
        long cPtr = libVisioMoveJNI.VgSpatialRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSpatial(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSpatialRefPtr_isValid(this.swigCPtr, this);
    }

    public VgSpatialRefPtr(VgLayerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_63__(VgLayerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgSpatialRefPtr(VgIGeometryRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_64__(VgIGeometryRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgSpatialRefPtr(VgPointRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_65__(VgPointRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgSpatialRefPtr(VgLineRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_66__(VgLineRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgSpatialRefPtr_setAnimation__SWIG_0(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgSpatialRefPtr_setAnimation__SWIG_1(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public void setLocalAnimation(VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgSpatialRefPtr_setLocalAnimation(this.swigCPtr, this, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgSpatialRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgSpatialRefPtr_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgSpatialRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgSpatialRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgSpatialRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition, boolean pHaveGeographicCoherence) {
        libVisioMoveJNI.VgSpatialRefPtr_setPosition__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHaveGeographicCoherence);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgSpatialRefPtr_setPosition__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgSpatialRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgSpatialRefPtr_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public float getScale() {
        return libVisioMoveJNI.VgSpatialRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgSpatialRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgSpatialRefPtr_getZIndex(this.swigCPtr, this);
    }

    public void setZIndex(int pZIndex) {
        libVisioMoveJNI.VgSpatialRefPtr_setZIndex(this.swigCPtr, this, pZIndex);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgSpatialRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public void setDrawOnTop(boolean pEnable) {
        libVisioMoveJNI.VgSpatialRefPtr_setDrawOnTop(this.swigCPtr, this, pEnable);
    }

    public void setVisible(boolean pIsVisible) {
        libVisioMoveJNI.VgSpatialRefPtr_setVisible(this.swigCPtr, this, pIsVisible);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgSpatialRefPtr_isVisible(this.swigCPtr, this);
    }

    public VgIGeometry asGeometry() {
        long cPtr = libVisioMoveJNI.VgSpatialRefPtr_asGeometry(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgSpatialRefPtr_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgSpatialRefPtr_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgSpatialRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSpatialRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSpatialRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
