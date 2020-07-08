package com.visioglobe.libVisioMove;

public class VgLightRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLightRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLightRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLightRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLightRefPtr() {
        this(libVisioMoveJNI.new_VgLightRefPtr__SWIG_0(), true);
    }

    public VgLightRefPtr(VgLight pPointer) {
        this(libVisioMoveJNI.new_VgLightRefPtr__SWIG_1(VgLight.getCPtr(pPointer), pPointer), true);
    }

    public VgLightRefPtr(VgLightRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLightRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLightRefPtr getNull() {
        return new VgLightRefPtr(libVisioMoveJNI.VgLightRefPtr_getNull(), true);
    }

    public VgLightRefPtr set(VgLight pPointer) {
        return new VgLightRefPtr(libVisioMoveJNI.VgLightRefPtr_set(this.swigCPtr, this, VgLight.getCPtr(pPointer), pPointer), false);
    }

    public VgLight __ref__() {
        return new VgLight(libVisioMoveJNI.VgLightRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLight __deref__() {
        long cPtr = libVisioMoveJNI.VgLightRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLight(cPtr, false);
    }

    public VgLight get() {
        long cPtr = libVisioMoveJNI.VgLightRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLight(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLightRefPtr_isValid(this.swigCPtr, this);
    }

    public void setAnimation(String pAnimationName, VgAnimationRefPtr pAnimation) {
        libVisioMoveJNI.VgLightRefPtr_setAnimation(this.swigCPtr, this, pAnimationName, VgAnimationRefPtr.getCPtr(pAnimation), pAnimation);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLightRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public VgAnimationRefPtr editAnimation(String pName) {
        return new VgAnimationRefPtr(libVisioMoveJNI.VgLightRefPtr_editAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLightRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLightRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgLightRefPtr_setPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLightRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public void setOrientation(VgOrientation pOrientation) {
        libVisioMoveJNI.VgLightRefPtr_setOrientation(this.swigCPtr, this, VgOrientation.getCPtr(pOrientation), pOrientation);
    }

    public VgColor getAmbient() {
        return new VgColor(libVisioMoveJNI.VgLightRefPtr_getAmbient(this.swigCPtr, this), true);
    }

    public void setAmbient(VgColor pColor) {
        libVisioMoveJNI.VgLightRefPtr_setAmbient(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgColor getDiffuse() {
        return new VgColor(libVisioMoveJNI.VgLightRefPtr_getDiffuse(this.swigCPtr, this), true);
    }

    public void setDiffuse(VgColor pColor) {
        libVisioMoveJNI.VgLightRefPtr_setDiffuse(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgColor getSpecular() {
        return new VgColor(libVisioMoveJNI.VgLightRefPtr_getSpecular(this.swigCPtr, this), true);
    }

    public void setSpecular(VgColor pColor) {
        libVisioMoveJNI.VgLightRefPtr_setSpecular(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgColor getEmission() {
        return new VgColor(libVisioMoveJNI.VgLightRefPtr_getEmission(this.swigCPtr, this), true);
    }

    public void setEmission(VgColor pColor) {
        libVisioMoveJNI.VgLightRefPtr_setEmission(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public double getSpotCutoff() {
        return libVisioMoveJNI.VgLightRefPtr_getSpotCutoff(this.swigCPtr, this);
    }

    public void setSpotCutoff(double pCutoff) {
        libVisioMoveJNI.VgLightRefPtr_setSpotCutoff(this.swigCPtr, this, pCutoff);
    }

    public double getSpotExponent() {
        return libVisioMoveJNI.VgLightRefPtr_getSpotExponent(this.swigCPtr, this);
    }

    public void setSpotExponent(double pExponent) {
        libVisioMoveJNI.VgLightRefPtr_setSpotExponent(this.swigCPtr, this, pExponent);
    }

    public boolean isDirectional() {
        return libVisioMoveJNI.VgLightRefPtr_isDirectional(this.swigCPtr, this);
    }

    public void setDirectional(boolean pDirectional) {
        libVisioMoveJNI.VgLightRefPtr_setDirectional(this.swigCPtr, this, pDirectional);
    }

    public void ref() {
        libVisioMoveJNI.VgLightRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLightRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLightRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
