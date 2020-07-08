package com.visioglobe.libVisioMove;

public class VgTextMarkerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgTextMarkerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgTextMarkerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgTextMarkerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgTextMarkerRefPtr() {
        this(libVisioMoveJNI.new_VgTextMarkerRefPtr__SWIG_0(), true);
    }

    public VgTextMarkerRefPtr(VgTextMarker pPointer) {
        this(libVisioMoveJNI.new_VgTextMarkerRefPtr__SWIG_1(VgTextMarker.getCPtr(pPointer), pPointer), true);
    }

    public VgTextMarkerRefPtr(VgTextMarkerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgTextMarkerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgTextMarkerRefPtr getNull() {
        return new VgTextMarkerRefPtr(libVisioMoveJNI.VgTextMarkerRefPtr_getNull(), true);
    }

    public VgTextMarkerRefPtr set(VgTextMarker pPointer) {
        return new VgTextMarkerRefPtr(libVisioMoveJNI.VgTextMarkerRefPtr_set(this.swigCPtr, this, VgTextMarker.getCPtr(pPointer), pPointer), false);
    }

    public VgTextMarker __ref__() {
        return new VgTextMarker(libVisioMoveJNI.VgTextMarkerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgTextMarker __deref__() {
        long cPtr = libVisioMoveJNI.VgTextMarkerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public VgTextMarker get() {
        long cPtr = libVisioMoveJNI.VgTextMarkerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgTextMarkerRefPtr_isValid(this.swigCPtr, this);
    }

    public VgTextMarker asTextMarker() {
        long cPtr = libVisioMoveJNI.VgTextMarkerRefPtr_asTextMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public String getText() {
        return libVisioMoveJNI.VgTextMarkerRefPtr_getText(this.swigCPtr, this);
    }

    public boolean setText(String pText) {
        return libVisioMoveJNI.VgTextMarkerRefPtr_setText(this.swigCPtr, this, pText);
    }

    public long getTextAttributes() {
        return libVisioMoveJNI.VgTextMarkerRefPtr_getTextAttributes(this.swigCPtr, this);
    }

    public boolean setTextAttributes(long pAttributesMask) {
        return libVisioMoveJNI.VgTextMarkerRefPtr_setTextAttributes(this.swigCPtr, this, pAttributesMask);
    }

    public VgColor getColor() {
        return new VgColor(libVisioMoveJNI.VgTextMarkerRefPtr_getColor(this.swigCPtr, this), true);
    }

    public void setColor(VgColor pColor) {
        libVisioMoveJNI.VgTextMarkerRefPtr_setColor(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public float getScale() {
        return libVisioMoveJNI.VgTextMarkerRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgTextMarkerRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public VgIconMarker asIconMarker() {
        long cPtr = libVisioMoveJNI.VgTextMarkerRefPtr_asIconMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgTextMarkerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgTextMarkerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgTextMarkerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
