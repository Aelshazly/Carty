package com.visioglobe.libVisioMove;

public class VgIRouteGeometryDescriptor {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteGeometryDescriptor(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteGeometryDescriptor obj) {
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
                libVisioMoveJNI.delete_VgIRouteGeometryDescriptor(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMLineDescriptors(VgLineDescriptorVector value) {
        libVisioMoveJNI.VgIRouteGeometryDescriptor_mLineDescriptors_set(this.swigCPtr, this, VgLineDescriptorVector.getCPtr(value), value);
    }

    public VgLineDescriptorVector getMLineDescriptors() {
        long cPtr = libVisioMoveJNI.VgIRouteGeometryDescriptor_mLineDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLineDescriptorVector(cPtr, false);
    }

    public void setMPointDescriptors(VgPointDescriptorVector value) {
        libVisioMoveJNI.VgIRouteGeometryDescriptor_mPointDescriptors_set(this.swigCPtr, this, VgPointDescriptorVector.getCPtr(value), value);
    }

    public VgPointDescriptorVector getMPointDescriptors() {
        long cPtr = libVisioMoveJNI.VgIRouteGeometryDescriptor_mPointDescriptors_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPointDescriptorVector(cPtr, false);
    }

    public void setMLayerName(String value) {
        libVisioMoveJNI.VgIRouteGeometryDescriptor_mLayerName_set(this.swigCPtr, this, value);
    }

    public String getMLayerName() {
        return libVisioMoveJNI.VgIRouteGeometryDescriptor_mLayerName_get(this.swigCPtr, this);
    }

    public void setMModality(String value) {
        libVisioMoveJNI.VgIRouteGeometryDescriptor_mModality_set(this.swigCPtr, this, value);
    }

    public String getMModality() {
        return libVisioMoveJNI.VgIRouteGeometryDescriptor_mModality_get(this.swigCPtr, this);
    }

    public void setMDestinationIndex(int value) {
        libVisioMoveJNI.VgIRouteGeometryDescriptor_mDestinationIndex_set(this.swigCPtr, this, value);
    }

    public int getMDestinationIndex() {
        return libVisioMoveJNI.VgIRouteGeometryDescriptor_mDestinationIndex_get(this.swigCPtr, this);
    }

    public VgIRouteGeometryDescriptor() {
        this(libVisioMoveJNI.new_VgIRouteGeometryDescriptor(), true);
    }
}
