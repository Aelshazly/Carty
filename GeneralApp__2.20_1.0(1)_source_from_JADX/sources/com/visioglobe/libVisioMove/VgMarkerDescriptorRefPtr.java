package com.visioglobe.libVisioMove;

public class VgMarkerDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgMarkerDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarkerDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgMarkerDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgMarkerDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgMarkerDescriptorRefPtr__SWIG_0(), true);
    }

    public VgMarkerDescriptorRefPtr(VgMarkerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgMarkerDescriptorRefPtr__SWIG_1(VgMarkerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgMarkerDescriptorRefPtr(VgMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgMarkerDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgMarkerDescriptorRefPtr getNull() {
        return new VgMarkerDescriptorRefPtr(libVisioMoveJNI.VgMarkerDescriptorRefPtr_getNull(), true);
    }

    public VgMarkerDescriptorRefPtr set(VgMarkerDescriptor pPointer) {
        return new VgMarkerDescriptorRefPtr(libVisioMoveJNI.VgMarkerDescriptorRefPtr_set(this.swigCPtr, this, VgMarkerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgMarkerDescriptor __ref__() {
        return new VgMarkerDescriptor(libVisioMoveJNI.VgMarkerDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgMarkerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgMarkerDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptor(cPtr, false);
    }

    public VgMarkerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgMarkerDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgMarkerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgMarkerDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgMarkerDescriptorRefPtr(VgIconMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_59__(VgIconMarkerDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgMarkerDescriptorRefPtr(VgTextMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_60__(VgTextMarkerDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgMarkerDescriptorRefPtr_getType(this.swigCPtr, this));
    }

    public void ref() {
        libVisioMoveJNI.VgMarkerDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgMarkerDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgMarkerDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
