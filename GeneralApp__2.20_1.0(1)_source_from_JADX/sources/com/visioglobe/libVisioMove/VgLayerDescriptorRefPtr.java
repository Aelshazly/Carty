package com.visioglobe.libVisioMove;

public class VgLayerDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayerDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLayerDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLayerDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgLayerDescriptorRefPtr__SWIG_0(), true);
    }

    public VgLayerDescriptorRefPtr(VgLayerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgLayerDescriptorRefPtr__SWIG_1(VgLayerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgLayerDescriptorRefPtr(VgLayerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgLayerDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgLayerDescriptorRefPtr getNull() {
        return new VgLayerDescriptorRefPtr(libVisioMoveJNI.VgLayerDescriptorRefPtr_getNull(), true);
    }

    public VgLayerDescriptorRefPtr set(VgLayerDescriptor pPointer) {
        return new VgLayerDescriptorRefPtr(libVisioMoveJNI.VgLayerDescriptorRefPtr_set(this.swigCPtr, this, VgLayerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgLayerDescriptor __ref__() {
        return new VgLayerDescriptor(libVisioMoveJNI.VgLayerDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLayerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayerDescriptor(cPtr, false);
    }

    public VgLayerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLayerDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public void setMName(String value) {
        libVisioMoveJNI.VgLayerDescriptorRefPtr_mName_set(this.swigCPtr, this, value);
    }

    public String getMName() {
        return libVisioMoveJNI.VgLayerDescriptorRefPtr_mName_get(this.swigCPtr, this);
    }

    public void setMLayoutDescriptor(VgLayoutDescriptor value) {
        libVisioMoveJNI.VgLayerDescriptorRefPtr_mLayoutDescriptor_set(this.swigCPtr, this, VgLayoutDescriptor.getCPtr(value), value);
    }

    public VgLayoutDescriptor getMLayoutDescriptor() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptorRefPtr_mLayoutDescriptor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayoutDescriptor(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgLayerDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLayerDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLayerDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
