package com.visioglobe.libVisioMove;

public class VgLineConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLineConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLineConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLineConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLineConstRefPtr() {
        this(libVisioMoveJNI.new_VgLineConstRefPtr__SWIG_0(), true);
    }

    public VgLineConstRefPtr(VgLine pPointer) {
        this(libVisioMoveJNI.new_VgLineConstRefPtr__SWIG_1(VgLine.getCPtr(pPointer), pPointer), true);
    }

    public VgLineConstRefPtr(VgLineConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLineConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLineConstRefPtr getNull() {
        return new VgLineConstRefPtr(libVisioMoveJNI.VgLineConstRefPtr_getNull(), true);
    }

    public VgLineConstRefPtr set(VgLine pPointer) {
        return new VgLineConstRefPtr(libVisioMoveJNI.VgLineConstRefPtr_set(this.swigCPtr, this, VgLine.getCPtr(pPointer), pPointer), false);
    }

    public VgLine __ref__() {
        return new VgLine(libVisioMoveJNI.VgLineConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLine __deref__() {
        long cPtr = libVisioMoveJNI.VgLineConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public VgLine get() {
        long cPtr = libVisioMoveJNI.VgLineConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLineConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgLineDescriptorConstRefPtr getDescriptor() {
        return new VgLineDescriptorConstRefPtr(libVisioMoveJNI.VgLineConstRefPtr_getDescriptor(this.swigCPtr, this), true);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgLineConstRefPtr_getType(this.swigCPtr, this));
    }

    public VgITextureRefPtr getTexture() {
        return new VgITextureRefPtr(libVisioMoveJNI.VgLineConstRefPtr_getTexture(this.swigCPtr, this), false);
    }

    public float getTextureAnimationSpeed() {
        return libVisioMoveJNI.VgLineConstRefPtr_getTextureAnimationSpeed(this.swigCPtr, this);
    }

    public VgVisibilityRamp getVisibilityRamp() {
        return new VgVisibilityRamp(libVisioMoveJNI.VgLineConstRefPtr_getVisibilityRamp(this.swigCPtr, this), true);
    }

    public String getID() {
        return libVisioMoveJNI.VgLineConstRefPtr_getID(this.swigCPtr, this);
    }

    public boolean getNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgLineConstRefPtr_getNotifyPOISelectedOnClick(this.swigCPtr, this);
    }

    public VgPosition getLocalPosition() {
        return new VgPosition(libVisioMoveJNI.VgLineConstRefPtr_getLocalPosition(this.swigCPtr, this), true);
    }

    public VgAnimationConstRefPtr getAnimation(String pName) {
        return new VgAnimationConstRefPtr(libVisioMoveJNI.VgLineConstRefPtr_getAnimation(this.swigCPtr, this, pName), true);
    }

    public void getAnimationNames(VgStringList pNameList) {
        libVisioMoveJNI.VgLineConstRefPtr_getAnimationNames(this.swigCPtr, this, VgStringList.getCPtr(pNameList), pNameList);
    }

    public VgValue getAnimationChannelValue(String pChannelName) {
        return new VgValue(libVisioMoveJNI.VgLineConstRefPtr_getAnimationChannelValue(this.swigCPtr, this, pChannelName), true);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgLineConstRefPtr_getPosition(this.swigCPtr, this), true);
    }

    public VgOrientation getOrientation() {
        return new VgOrientation(libVisioMoveJNI.VgLineConstRefPtr_getOrientation(this.swigCPtr, this), true);
    }

    public float getScale() {
        return libVisioMoveJNI.VgLineConstRefPtr_getScale(this.swigCPtr, this);
    }

    public int getZIndex() {
        return libVisioMoveJNI.VgLineConstRefPtr_getZIndex(this.swigCPtr, this);
    }

    public boolean isDrawnOnTop() {
        return libVisioMoveJNI.VgLineConstRefPtr_isDrawnOnTop(this.swigCPtr, this);
    }

    public boolean isVisible() {
        return libVisioMoveJNI.VgLineConstRefPtr_isVisible(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgLineConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLineConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLineConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
