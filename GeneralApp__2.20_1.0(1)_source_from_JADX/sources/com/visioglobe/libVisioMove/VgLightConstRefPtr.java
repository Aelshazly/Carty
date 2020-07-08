package com.visioglobe.libVisioMove;

public class VgLightConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLightConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLightConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLightConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLightConstRefPtr() {
        this(libVisioMoveJNI.new_VgLightConstRefPtr__SWIG_0(), true);
    }

    public VgLightConstRefPtr(VgLight pPointer) {
        this(libVisioMoveJNI.new_VgLightConstRefPtr__SWIG_1(VgLight.getCPtr(pPointer), pPointer), true);
    }

    public VgLightConstRefPtr(VgLightConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLightConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLightConstRefPtr getNull() {
        return new VgLightConstRefPtr(libVisioMoveJNI.VgLightConstRefPtr_getNull(), true);
    }

    public VgLightConstRefPtr set(VgLight pPointer) {
        return new VgLightConstRefPtr(libVisioMoveJNI.VgLightConstRefPtr_set(this.swigCPtr, this, VgLight.getCPtr(pPointer), pPointer), false);
    }

    public VgLight __ref__() {
        return new VgLight(libVisioMoveJNI.VgLightConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLight __deref__() {
        long cPtr = libVisioMoveJNI.VgLightConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLight(cPtr, false);
    }

    public VgLight get() {
        long cPtr = libVisioMoveJNI.VgLightConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLight(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLightConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLightConstRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLightConstRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLightConstRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLightConstRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public VgColor getAmbient() {
        return new VgColor(libVisioMoveJNI.VgLightConstRefPtr_getAmbient(this.swigCPtr, this), true);
    }

    public VgColor getDiffuse() {
        return new VgColor(libVisioMoveJNI.VgLightConstRefPtr_getDiffuse(this.swigCPtr, this), true);
    }

    public VgColor getSpecular() {
        return new VgColor(libVisioMoveJNI.VgLightConstRefPtr_getSpecular(this.swigCPtr, this), true);
    }

    public VgColor getEmission() {
        return new VgColor(libVisioMoveJNI.VgLightConstRefPtr_getEmission(this.swigCPtr, this), true);
    }

    public double getSpotCutoff() {
        return libVisioMoveJNI.VgLightConstRefPtr_getSpotCutoff(this.swigCPtr, this);
    }

    public double getSpotExponent() {
        return libVisioMoveJNI.VgLightConstRefPtr_getSpotExponent(this.swigCPtr, this);
    }

    public boolean isDirectional() {
        return libVisioMoveJNI.VgLightConstRefPtr_isDirectional(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLightConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLightConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLightConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
