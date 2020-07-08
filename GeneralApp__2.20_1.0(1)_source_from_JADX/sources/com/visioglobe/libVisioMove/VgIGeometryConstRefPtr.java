package com.visioglobe.libVisioMove;

public class VgIGeometryConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIGeometryConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometryConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIGeometryConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIGeometryConstRefPtr() {
        this(libVisioMoveJNI.new_VgIGeometryConstRefPtr__SWIG_0(), true);
    }

    public VgIGeometryConstRefPtr(VgIGeometry pPointer) {
        this(libVisioMoveJNI.new_VgIGeometryConstRefPtr__SWIG_1(VgIGeometry.getCPtr(pPointer), pPointer), true);
    }

    public VgIGeometryConstRefPtr(VgIGeometryConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIGeometryConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIGeometryConstRefPtr getNull() {
        return new VgIGeometryConstRefPtr(libVisioMoveJNI.VgIGeometryConstRefPtr_getNull(), true);
    }

    public VgIGeometryConstRefPtr set(VgIGeometry pPointer) {
        return new VgIGeometryConstRefPtr(libVisioMoveJNI.VgIGeometryConstRefPtr_set(this.swigCPtr, this, VgIGeometry.getCPtr(pPointer), pPointer), false);
    }

    public VgIGeometry __ref__() {
        return new VgIGeometry(libVisioMoveJNI.VgIGeometryConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIGeometry __deref__() {
        long cPtr = libVisioMoveJNI.VgIGeometryConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public VgIGeometry get() {
        long cPtr = libVisioMoveJNI.VgIGeometryConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIGeometry(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgIGeometryConstRefPtr_getType(this.swigCPtr, this));
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgIGeometryConstRefPtr_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public String getID() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgIGeometryConstRefPtr_getLocalPosition(this.swigCPtr, this), true);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgIGeometryConstRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgIGeometryConstRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgIGeometryConstRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgIGeometryConstRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgIGeometryConstRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public float getScale() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_getScale(this.swigCPtr, this);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_getZIndex(this.swigCPtr, this);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgIGeometryConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIGeometryConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
