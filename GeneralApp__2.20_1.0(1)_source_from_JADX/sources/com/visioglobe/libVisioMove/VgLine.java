package com.visioglobe.libVisioMove;

public class VgLine extends VgIGeometry {
    private long swigCPtr;

    protected VgLine(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLine_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLine obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public VgLineDescriptorConstRefPtr getDescriptor() {
        return new VgLineDescriptorConstRefPtr(libVisioMoveJNI.VgLine_getDescriptor(this.swigCPtr, this), true);
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgLine_getType(this.swigCPtr, this));
    }

    public VgITextureRefPtr getTexture() {
        return new VgITextureRefPtr(libVisioMoveJNI.VgLine_getTexture(this.swigCPtr, this), false);
    }

    public boolean setTexture(VgITextureRefPtr pTexture) {
        return libVisioMoveJNI.VgLine_setTexture(this.swigCPtr, this, VgITextureRefPtr.getCPtr(pTexture), pTexture);
    }

    public float getTextureAnimationSpeed() {
        return libVisioMoveJNI.VgLine_getTextureAnimationSpeed(this.swigCPtr, this);
    }

    public void setTextureAnimationSpeed(float pSpeed) {
        libVisioMoveJNI.VgLine_setTextureAnimationSpeed(this.swigCPtr, this, pSpeed);
    }

    public VgPosition getInterpolatedPosition(float pValue) {
        return new VgPosition(libVisioMoveJNI.VgLine_getInterpolatedPosition(this.swigCPtr, this, pValue), true);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgLine_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgLine_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }
}
