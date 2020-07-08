package com.visioglobe.libVisioMove;

public class VgIconMarkerDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIconMarkerDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIconMarkerDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIconMarkerDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIconMarkerDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgIconMarkerDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgIconMarkerDescriptorConstRefPtr(VgIconMarkerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgIconMarkerDescriptorConstRefPtr__SWIG_1(VgIconMarkerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgIconMarkerDescriptorConstRefPtr(VgIconMarkerDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgIconMarkerDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgIconMarkerDescriptorConstRefPtr getNull() {
        return new VgIconMarkerDescriptorConstRefPtr(libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_getNull(), true);
    }

    public VgIconMarkerDescriptorConstRefPtr set(VgIconMarkerDescriptor pPointer) {
        return new VgIconMarkerDescriptorConstRefPtr(libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_set(this.swigCPtr, this, VgIconMarkerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgIconMarkerDescriptor __ref__() {
        return new VgIconMarkerDescriptor(libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIconMarkerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarkerDescriptor(cPtr, false);
    }

    public VgIconMarkerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarkerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public VgITextureRefPtr getMIcon() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_mIcon_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_mScale_get(this.swigCPtr, this);
    }

    public VgColor getMColor() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_mColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_getType(this.swigCPtr, this));
    }

    public void ref() {
        libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIconMarkerDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
