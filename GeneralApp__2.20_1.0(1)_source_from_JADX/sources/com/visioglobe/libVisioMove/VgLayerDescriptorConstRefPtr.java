package com.visioglobe.libVisioMove;

public class VgLayerDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgLayerDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgLayerDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgLayerDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgLayerDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgLayerDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgLayerDescriptorConstRefPtr(VgLayerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgLayerDescriptorConstRefPtr__SWIG_1(VgLayerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgLayerDescriptorConstRefPtr(VgLayerDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgLayerDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgLayerDescriptorConstRefPtr getNull() {
        return new VgLayerDescriptorConstRefPtr(libVisioMoveJNI.VgLayerDescriptorConstRefPtr_getNull(), true);
    }

    public VgLayerDescriptorConstRefPtr set(VgLayerDescriptor pPointer) {
        return new VgLayerDescriptorConstRefPtr(libVisioMoveJNI.VgLayerDescriptorConstRefPtr_set(this.swigCPtr, this, VgLayerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgLayerDescriptor __ref__() {
        return new VgLayerDescriptor(libVisioMoveJNI.VgLayerDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgLayerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayerDescriptor(cPtr, false);
    }

    public VgLayerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgLayerDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public String getMName() {
        return libVisioMoveJNI.VgLayerDescriptorConstRefPtr_mName_get(this.swigCPtr, this);
    }

    public VgLayoutDescriptor getMLayoutDescriptor() {
        long cPtr = libVisioMoveJNI.VgLayerDescriptorConstRefPtr_mLayoutDescriptor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLayoutDescriptor(cPtr, false);
    }

    public void ref() {
        libVisioMoveJNI.VgLayerDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgLayerDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgLayerDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
