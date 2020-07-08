package com.visioglobe.libVisioMove;

public class VgTextMarker extends VgMarker {
    private long swigCPtr;

    protected VgTextMarker(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgTextMarker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgTextMarker obj) {
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
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public VgTextMarker() {
        this(libVisioMoveJNI.new_VgTextMarker(), true);
    }

    public VgTextMarker asTextMarker() {
        long cPtr = libVisioMoveJNI.VgTextMarker_asTextMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }

    public String getText() {
        return libVisioMoveJNI.VgTextMarker_getText(this.swigCPtr, this);
    }

    public boolean setText(String pText) {
        return libVisioMoveJNI.VgTextMarker_setText(this.swigCPtr, this, pText);
    }

    public long getTextAttributes() {
        return libVisioMoveJNI.VgTextMarker_getTextAttributes(this.swigCPtr, this);
    }

    public boolean setTextAttributes(long pAttributesMask) {
        return libVisioMoveJNI.VgTextMarker_setTextAttributes(this.swigCPtr, this, pAttributesMask);
    }

    public VgColor getColor() {
        return new VgColor(libVisioMoveJNI.VgTextMarker_getColor(this.swigCPtr, this), true);
    }

    public void setColor(VgColor pColor) {
        libVisioMoveJNI.VgTextMarker_setColor(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }

    public float getScale() {
        return libVisioMoveJNI.VgTextMarker_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgTextMarker_setScale(this.swigCPtr, this, pScale);
    }
}
