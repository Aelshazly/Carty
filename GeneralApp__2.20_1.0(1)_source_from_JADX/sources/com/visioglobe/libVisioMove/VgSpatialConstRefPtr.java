package com.visioglobe.libVisioMove;

public class VgSpatialConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgSpatialConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSpatialConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgSpatialConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgSpatialConstRefPtr() {
        this(libVisioMoveJNI.new_VgSpatialConstRefPtr__SWIG_0(), true);
    }

    public VgSpatialConstRefPtr(VgSpatial pPointer) {
        this(libVisioMoveJNI.new_VgSpatialConstRefPtr__SWIG_1(VgSpatial.getCPtr(pPointer), pPointer), true);
    }

    public VgSpatialConstRefPtr(VgSpatialConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgSpatialConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgSpatialConstRefPtr getNull() {
        return new VgSpatialConstRefPtr(libVisioMoveJNI.VgSpatialConstRefPtr_getNull(), true);
    }

    public VgSpatialConstRefPtr set(VgSpatial pPointer) {
        return new VgSpatialConstRefPtr(libVisioMoveJNI.VgSpatialConstRefPtr_set(this.swigCPtr, this, VgSpatial.getCPtr(pPointer), pPointer), false);
    }

    public VgSpatial __ref__() {
        return new VgSpatial(libVisioMoveJNI.VgSpatialConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgSpatial __deref__() {
        long cPtr = libVisioMoveJNI.VgSpatialConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSpatial(cPtr, false);
    }

    public VgSpatial get() {
        long cPtr = libVisioMoveJNI.VgSpatialConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgSpatial(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgSpatialConstRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgSpatialConstRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgSpatialConstRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgSpatialConstRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgSpatialConstRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public float getScale() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_getScale(this.swigCPtr, this);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_getZIndex(this.swigCPtr, this);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgSpatialConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgSpatialConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
