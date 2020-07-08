package com.visioglobe.libVisioMove;

public class VgLight extends VgReferenced {
    private long swigCPtr;

    protected VgLight(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLight_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLight obj) {
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
        libVisioMoveJNI.VgLight_setAnimation(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLight_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgLight_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLight_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLight_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgLight_setPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLight_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgLight_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public VgColor getAmbient() {
        return new VgColor(libVisioMoveJNI.VgLight_getAmbient(this.swigCPtr, this), true);
    }

    public void setAmbient(VgColor pColor) {
        libVisioMoveJNI.VgLight_setAmbient(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgColor getDiffuse() {
        return new VgColor(libVisioMoveJNI.VgLight_getDiffuse(this.swigCPtr, this), true);
    }

    public void setDiffuse(VgColor pColor) {
        libVisioMoveJNI.VgLight_setDiffuse(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgColor getSpecular() {
        return new VgColor(libVisioMoveJNI.VgLight_getSpecular(this.swigCPtr, this), true);
    }

    public void setSpecular(VgColor pColor) {
        libVisioMoveJNI.VgLight_setSpecular(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgColor getEmission() {
        return new VgColor(libVisioMoveJNI.VgLight_getEmission(this.swigCPtr, this), true);
    }

    public void setEmission(VgColor pColor) {
        libVisioMoveJNI.VgLight_setEmission(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public double getSpotCutoff() {
        return libVisioMoveJNI.VgLight_getSpotCutoff(this.swigCPtr, this);
    }

    public void setSpotCutoff(double pCutoff) {
        libVisioMoveJNI.VgLight_setSpotCutoff(this.swigCPtr, this, pCutoff);
    }

    public double getSpotExponent() {
        return libVisioMoveJNI.VgLight_getSpotExponent(this.swigCPtr, this);
    }

    public void setSpotExponent(double pExponent) {
        libVisioMoveJNI.VgLight_setSpotExponent(this.swigCPtr, this, pExponent);
    }

    public boolean isDirectional() {
        return libVisioMoveJNI.VgLight_isDirectional(this.swigCPtr, this);
    }

    public void setDirectional(boolean pDirectional) {
        libVisioMoveJNI.VgLight_setDirectional(this.swigCPtr, this, pDirectional);
    }
}
