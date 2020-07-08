package com.visioglobe.libVisioMove;

public class VgLayerConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayerConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLayerConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLayerConstRefPtr() {
        this(libVisioMoveJNI.new_VgLayerConstRefPtr__SWIG_0(), true);
    }

    public VgLayerConstRefPtr(VgLayer pPointer) {
        this(libVisioMoveJNI.new_VgLayerConstRefPtr__SWIG_1(VgLayer.getCPtr(pPointer), pPointer), true);
    }

    public VgLayerConstRefPtr(VgLayerConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLayerConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLayerConstRefPtr getNull() {
        return new VgLayerConstRefPtr(libVisioMoveJNI.VgLayerConstRefPtr_getNull(), true);
    }

    public VgLayerConstRefPtr set(VgLayer pPointer) {
        return new VgLayerConstRefPtr(libVisioMoveJNI.VgLayerConstRefPtr_set(this.swigCPtr, this, VgLayer.getCPtr(pPointer), pPointer), false);
    }

    public VgLayer __ref__() {
        return new VgLayer(libVisioMoveJNI.VgLayerConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLayer __deref__() {
        long cPtr = libVisioMoveJNI.VgLayerConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayer(cPtr, false);
    }

    public VgLayer get() {
        long cPtr = libVisioMoveJNI.VgLayerConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayer(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLayerConstRefPtr_isValid(this.swigCPtr, this);
    }

    public String getName() {
        return libVisioMoveJNI.VgLayerConstRefPtr_getName(this.swigCPtr, this);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgLayerConstRefPtr_isVisible(this.swigCPtr, this);
    }

    public float getLODFactor() {
        return libVisioMoveJNI.VgLayerConstRefPtr_getLODFactor(this.swigCPtr, this);
    }

    public float getLoadFactor() {
        return libVisioMoveJNI.VgLayerConstRefPtr_getLoadFactor(this.swigCPtr, this);
    }

    public VgSRSConstRefPtr getSRS() {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgLayerConstRefPtr_getSRS(this.swigCPtr, this), true);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLayerConstRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLayerConstRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgLayerConstRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLayerConstRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLayerConstRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public float getScale() {
        return libVisioMoveJNI.VgLayerConstRefPtr_getScale(this.swigCPtr, this);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgLayerConstRefPtr_getZIndex(this.swigCPtr, this);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgLayerConstRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLayerConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLayerConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLayerConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
