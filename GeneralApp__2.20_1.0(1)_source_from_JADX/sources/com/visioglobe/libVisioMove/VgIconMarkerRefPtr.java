package com.visioglobe.libVisioMove;

public class VgIconMarkerRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIconMarkerRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIconMarkerRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIconMarkerRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIconMarkerRefPtr() {
        this(libVisioMoveJNI.new_VgIconMarkerRefPtr__SWIG_0(), true);
    }

    public VgIconMarkerRefPtr(VgIconMarker pPointer) {
        this(libVisioMoveJNI.new_VgIconMarkerRefPtr__SWIG_1(VgIconMarker.getCPtr(pPointer), pPointer), true);
    }

    public VgIconMarkerRefPtr(VgIconMarkerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIconMarkerRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIconMarkerRefPtr getNull() {
        return new VgIconMarkerRefPtr(libVisioMoveJNI.VgIconMarkerRefPtr_getNull(), true);
    }

    public VgIconMarkerRefPtr set(VgIconMarker pPointer) {
        return new VgIconMarkerRefPtr(libVisioMoveJNI.VgIconMarkerRefPtr_set(this.swigCPtr, this, VgIconMarker.getCPtr(pPointer), pPointer), false);
    }

    public VgIconMarker __ref__() {
        return new VgIconMarker(libVisioMoveJNI.VgIconMarkerRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIconMarker __deref__() {
        long cPtr = libVisioMoveJNI.VgIconMarkerRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public VgIconMarker get() {
        long cPtr = libVisioMoveJNI.VgIconMarkerRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIconMarkerRefPtr_isValid(this.swigCPtr, this);
    }

    public VgIconMarker asIconMarker() {
        long cPtr = libVisioMoveJNI.VgIconMarkerRefPtr_asIconMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public VgITextureRefPtr getIcon() {
        return new VgITextureRefPtr(libVisioMoveJNI.VgIconMarkerRefPtr_getIcon(this.swigCPtr, this), false);
    }

    public boolean setIcon(VgITextureRefPtr pTexture) {
        return libVisioMoveJNI.VgIconMarkerRefPtr_setIcon(this.swigCPtr, this, VgITextureRefPtr.getCPtr(pTexture), pTexture);
    }

    public float getScale() {
        return libVisioMoveJNI.VgIconMarkerRefPtr_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgIconMarkerRefPtr_setScale(this.swigCPtr, this, pScale);
    }

    public VgColor getColor() {
        return new VgColor(libVisioMoveJNI.VgIconMarkerRefPtr_getColor(this.swigCPtr, this), true);
    }

    public void setColor(VgColor pColor) {
        libVisioMoveJNI.VgIconMarkerRefPtr_setColor(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public VgTextMarker asTextMarker() {
        long cPtr = libVisioMoveJNI.VgIconMarkerRefPtr_asTextMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgIconMarkerRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIconMarkerRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIconMarkerRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
