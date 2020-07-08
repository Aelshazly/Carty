package com.visioglobe.libVisioMove;

public class VgTextMarkerDescriptorRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgTextMarkerDescriptorRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgTextMarkerDescriptorRefPtr obj) {
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
                libVisioMoveJNI.delete_VgTextMarkerDescriptorRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgTextMarkerDescriptorRefPtr() {
        this(libVisioMoveJNI.new_VgTextMarkerDescriptorRefPtr__SWIG_0(), true);
    }

    public VgTextMarkerDescriptorRefPtr(VgTextMarkerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgTextMarkerDescriptorRefPtr__SWIG_1(VgTextMarkerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgTextMarkerDescriptorRefPtr(VgTextMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgTextMarkerDescriptorRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgTextMarkerDescriptorRefPtr getNull() {
        return new VgTextMarkerDescriptorRefPtr(libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_getNull(), true);
    }

    public VgTextMarkerDescriptorRefPtr set(VgTextMarkerDescriptor pPointer) {
        return new VgTextMarkerDescriptorRefPtr(libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_set(this.swigCPtr, this, VgTextMarkerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgTextMarkerDescriptor __ref__() {
        return new VgTextMarkerDescriptor(libVisioMoveJNI.VgTextMarkerDescriptorRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgTextMarkerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptorRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarkerDescriptor(cPtr, false);
    }

    public VgTextMarkerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarkerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_isValid(this.swigCPtr, this);
    }

    public VgTextMarkerDescriptorRefPtr create() {
        return new VgTextMarkerDescriptorRefPtr(libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_create(this.swigCPtr, this), true);
    }

    public void setMText(String value) {
        libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mText_set(this.swigCPtr, this, value);
    }

    public String getMText() {
        return libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mText_get(this.swigCPtr, this);
    }

    public void setMTextAttributesMask(long value) {
        libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mTextAttributesMask_set(this.swigCPtr, this, value);
    }

    public long getMTextAttributesMask() {
        return libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mTextAttributesMask_get(this.swigCPtr, this);
    }

    public void setMColor(VgColor value) {
        libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMColor() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMScale(double value) {
        libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mScale_set(this.swigCPtr, this, value);
    }

    public double getMScale() {
        return libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_mScale_get(this.swigCPtr, this);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_getType(this.swigCPtr, this));
    }

    public void ref() {
        libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgTextMarkerDescriptorRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
