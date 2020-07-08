package com.visioglobe.libVisioMove;

public class VgPointConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPointConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPointConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgPointConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgPointConstRefPtr() {
        this(libVisioMoveJNI.new_VgPointConstRefPtr__SWIG_0(), true);
    }

    public VgPointConstRefPtr(VgPoint pPointer) {
        this(libVisioMoveJNI.new_VgPointConstRefPtr__SWIG_1(VgPoint.getCPtr(pPointer), pPointer), true);
    }

    public VgPointConstRefPtr(VgPointConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgPointConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgPointConstRefPtr getNull() {
        return new VgPointConstRefPtr(libVisioMoveJNI.VgPointConstRefPtr_getNull(), true);
    }

    public VgPointConstRefPtr set(VgPoint pPointer) {
        return new VgPointConstRefPtr(libVisioMoveJNI.VgPointConstRefPtr_set(this.swigCPtr, this, VgPoint.getCPtr(pPointer), pPointer), false);
    }

    public VgPoint __ref__() {
        return new VgPoint(libVisioMoveJNI.VgPointConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgPoint __deref__() {
        long cPtr = libVisioMoveJNI.VgPointConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgPoint get() {
        long cPtr = libVisioMoveJNI.VgPointConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgPointConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgPointConstRefPtr_getType(this.swigCPtr, this));
    }

    public VgAltitudeMode getAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgPointConstRefPtr_getAltitudeMode(this.swigCPtr, this));
    }

    public VgAnchorMode getAnchorPosition() {
        return VgAnchorMode.swigToEnum(libVisioMoveJNI.VgPointConstRefPtr_getAnchorPosition(this.swigCPtr, this));
    }

    public float getGeometryConstantSizeDistance() {
        return libVisioMoveJNI.VgPointConstRefPtr_getGeometryConstantSizeDistance(this.swigCPtr, this);
    }

    public boolean isForceFrontFaceEnabled() {
        return libVisioMoveJNI.VgPointConstRefPtr_isForceFrontFaceEnabled(this.swigCPtr, this);
    }

    public VgOrientationConstraints getOrientationConstraints() {
        return new VgOrientationConstraints(libVisioMoveJNI.VgPointConstRefPtr_getOrientationConstraints(this.swigCPtr, this), true);
    }

    public long getNbMarkers() {
        return libVisioMoveJNI.VgPointConstRefPtr_getNbMarkers(this.swigCPtr, this);
    }

    public VgSizePolicy getSizePolicy() {
        return VgSizePolicy.swigToEnum(libVisioMoveJNI.VgPointConstRefPtr_getSizePolicy(this.swigCPtr, this));
    }

    public void getBoundingRect(float[] pRectangleWidth, float[] pRectangleHeight) {
        libVisioMoveJNI.VgPointConstRefPtr_getBoundingRect(this.swigCPtr, this, pRectangleWidth, pRectangleHeight);
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgPointConstRefPtr_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public String getID() {
        return libVisioMoveJNI.VgPointConstRefPtr_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgPointConstRefPtr_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgPointConstRefPtr_getLocalPosition(this.swigCPtr, this), true);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgPointConstRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgPointConstRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgPointConstRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgPointConstRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgPointConstRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public float getScale() {
        return libVisioMoveJNI.VgPointConstRefPtr_getScale(this.swigCPtr, this);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgPointConstRefPtr_getZIndex(this.swigCPtr, this);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgPointConstRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgPointConstRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgPointConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgPointConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgPointConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
