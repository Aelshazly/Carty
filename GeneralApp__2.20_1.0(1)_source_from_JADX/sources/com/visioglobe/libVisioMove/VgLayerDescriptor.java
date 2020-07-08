package com.visioglobe.libVisioMove;

public class VgLayerDescriptor extends VgReferenced {
    private long swigCPtr;

    protected VgLayerDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgLayerDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerDescriptor obj) {
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

    public VgLayerDescriptor() {
        this(libVisioMoveJNI.new_VgLayerDescriptor(), true);
    }

    public void setMName(String value) {
        libVisioMoveJNI.VgLayerDescriptor_mName_set(this.swigCPtr, this, value);
    }

    public String getMName() {
        return libVisioMoveJNI.VgLayerDescriptor_mName_get(this.swigCPtr, this);
    }

    public void setMLayoutDescriptor(VgLayoutDescriptor value) {
        libVisioMoveJNI.VgLayerDescriptor_mLayoutDescriptor_set(this.swigCPtr, this, VgLayoutDescriptor.getCPtr(value), value);
    }

    public VgLayoutDescriptor getMLayoutDescriptor() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptor_mLayoutDescriptor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayoutDescriptor(cPtr, false);
    }
}
