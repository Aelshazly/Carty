package com.visioglobe.libVisioMove;

public class VgMarkerDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMarkerDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarkerDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMarkerDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMarkerDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgMarkerDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgMarkerDescriptorConstRefPtr(VgMarkerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgMarkerDescriptorConstRefPtr__SWIG_1(VgMarkerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgMarkerDescriptorConstRefPtr(VgMarkerDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgMarkerDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgMarkerDescriptorConstRefPtr getNull() {
        return new VgMarkerDescriptorConstRefPtr(libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_getNull(), true);
    }

    public VgMarkerDescriptorConstRefPtr set(VgMarkerDescriptor pPointer) {
        return new VgMarkerDescriptorConstRefPtr(libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_set(this.swigCPtr, this, VgMarkerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgMarkerDescriptor __ref__() {
        return new VgMarkerDescriptor(libVisioMoveJNI.VgMarkerDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMarkerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgMarkerDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptor(cPtr, false);
    }

    public VgMarkerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_getType(this.swigCPtr, this));
    }

    public void ref() {
        libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMarkerDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
