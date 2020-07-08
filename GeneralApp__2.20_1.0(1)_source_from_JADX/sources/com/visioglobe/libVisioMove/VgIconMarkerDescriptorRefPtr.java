package com.visioglobe.libVisioMove;

public class VgIconMarkerDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIconMarkerDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIconMarkerDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgIconMarkerDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIconMarkerDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgIconMarkerDescriptorRefPtr__SWIG_0(), true);
    }

    public VgIconMarkerDescriptorRefPtr(VgIconMarkerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgIconMarkerDescriptorRefPtr__SWIG_1(VgIconMarkerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgIconMarkerDescriptorRefPtr(VgIconMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgIconMarkerDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgIconMarkerDescriptorRefPtr getNull() {
        return new VgIconMarkerDescriptorRefPtr(libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_getNull(), true);
    }

    public VgIconMarkerDescriptorRefPtr set(VgIconMarkerDescriptor pPointer) {
        return new VgIconMarkerDescriptorRefPtr(libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_set(this.swigCPtr, this, VgIconMarkerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgIconMarkerDescriptor __ref__() {
        return new VgIconMarkerDescriptor(libVisioMoveJNI.VgIconMarkerDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgIconMarkerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarkerDescriptor(cPtr, false);
    }

    public VgIconMarkerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarkerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgIconMarkerDescriptorRefPtr create() {
        return new VgIconMarkerDescriptorRefPtr(libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMIcon(VgITextureRefPtr value) {
        libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_mIcon_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMIcon() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_mIcon_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public void setMScale(float value) {
        libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_mScale_set(this.swigCPtr, this, value);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_mScale_get(this.swigCPtr, this);
    }

    public void setMColor(VgColor value) {
        libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_mColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMColor() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_mColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_getType(this.swigCPtr, this));
    }

    public void ref() {
        libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgIconMarkerDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
