package com.visioglobe.libVisioMove;

public class VgIconMarker extends VgMarker {
    private long swigCPtr;

    protected VgIconMarker(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIconMarker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIconMarker obj) {
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

    public VgIconMarker() {
        this(libVisioMoveJNI.new_VgIconMarker(), true);
    }

    public VgIconMarker asIconMarker() {
        long cPtr = libVisioMoveJNI.VgIconMarker_asIconMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public VgITextureRefPtr getIcon() {
        return new VgITextureRefPtr(libVisioMoveJNI.VgIconMarker_getIcon(this.swigCPtr, this), false);
    }

    public boolean setIcon(VgITextureRefPtr pTexture) {
        return libVisioMoveJNI.VgIconMarker_setIcon(this.swigCPtr, this, VgITextureRefPtr.getCPtr(pTexture), pTexture);
    }

    public float getScale() {
        return libVisioMoveJNI.VgIconMarker_getScale(this.swigCPtr, this);
    }

    public void setScale(float pScale) {
        libVisioMoveJNI.VgIconMarker_setScale(this.swigCPtr, this, pScale);
    }

    public VgColor getColor() {
        return new VgColor(libVisioMoveJNI.VgIconMarker_getColor(this.swigCPtr, this), true);
    }

    public void setColor(VgColor pColor) {
        libVisioMoveJNI.VgIconMarker_setColor(this.swigCPtr, this, VgColor.getCPtr(pColor), pColor);
    }
}
