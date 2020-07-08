package com.visioglobe.libVisioMove;

public class VgGeometryDescriptor extends VgReferenced {
    private long swigCPtr;

    protected VgGeometryDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgGeometryDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgGeometryDescriptor obj) {
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

    public void setMVisibilityRampStartVisible(double value) {
        libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampStartVisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampStartVisible() {
        return libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampStartVisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampFullyVisible(double value) {
        libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampFullyVisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampFullyVisible() {
        return libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampFullyVisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampStartInvisible(double value) {
        libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampStartInvisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampStartInvisible() {
        return libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampStartInvisible_get(this.swigCPtr, this);
    }

    public void setMVisibilityRampFullyInvisible(double value) {
        libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampFullyInvisible_set(this.swigCPtr, this, value);
    }

    public double getMVisibilityRampFullyInvisible() {
        return libVisioMoveJNI.VgGeometryDescriptor_mVisibilityRampFullyInvisible_get(this.swigCPtr, this);
    }

    public void setMZIndex(int value) {
        libVisioMoveJNI.VgGeometryDescriptor_mZIndex_set(this.swigCPtr, this, value);
    }

    public int getMZIndex() {
        return libVisioMoveJNI.VgGeometryDescriptor_mZIndex_get(this.swigCPtr, this);
    }

    public void setMDrawOnTop(boolean value) {
        libVisioMoveJNI.VgGeometryDescriptor_mDrawOnTop_set(this.swigCPtr, this, value);
    }

    public boolean getMDrawOnTop() {
        return libVisioMoveJNI.VgGeometryDescriptor_mDrawOnTop_get(this.swigCPtr, this);
    }

    public void setMScale(float value) {
        libVisioMoveJNI.VgGeometryDescriptor_mScale_set(this.swigCPtr, this, value);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgGeometryDescriptor_mScale_get(this.swigCPtr, this);
    }

    public void setMNotifyPOISelectedOnClick(boolean value) {
        libVisioMoveJNI.VgGeometryDescriptor_mNotifyPOISelectedOnClick_set(this.swigCPtr, this, value);
    }

    public boolean getMNotifyPOISelectedOnClick() {
        return libVisioMoveJNI.VgGeometryDescriptor_mNotifyPOISelectedOnClick_get(this.swigCPtr, this);
    }
}
