package com.visioglobe.libVisioMove;

public class VgTextMarkerDescriptorConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgTextMarkerDescriptorConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgTextMarkerDescriptorConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgTextMarkerDescriptorConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgTextMarkerDescriptorConstRefPtr() {
        this(libVisioMoveJNI.new_VgTextMarkerDescriptorConstRefPtr__SWIG_0(), true);
    }

    public VgTextMarkerDescriptorConstRefPtr(VgTextMarkerDescriptor pPointer) {
        this(libVisioMoveJNI.new_VgTextMarkerDescriptorConstRefPtr__SWIG_1(VgTextMarkerDescriptor.getCPtr(pPointer), pPointer), true);
    }

    public VgTextMarkerDescriptorConstRefPtr(VgTextMarkerDescriptorConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgTextMarkerDescriptorConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgTextMarkerDescriptorConstRefPtr getNull() {
        return new VgTextMarkerDescriptorConstRefPtr(libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_getNull(), true);
    }

    public VgTextMarkerDescriptorConstRefPtr set(VgTextMarkerDescriptor pPointer) {
        return new VgTextMarkerDescriptorConstRefPtr(libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_set(this.swigCPtr, this, VgTextMarkerDescriptor.getCPtr(pPointer), pPointer), false);
    }

    public VgTextMarkerDescriptor __ref__() {
        return new VgTextMarkerDescriptor(libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgTextMarkerDescriptor __deref__() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarkerDescriptor(cPtr, false);
    }

    public VgTextMarkerDescriptor get() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarkerDescriptor(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_isValid(this.swigCPtr, this);
    }

    public String getMText() {
        return libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_mText_get(this.swigCPtr, this);
    }

    public long getMTextAttributesMask() {
        return libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_mTextAttributesMask_get(this.swigCPtr, this);
    }

    public VgColor getMColor() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_mColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public double getMScale() {
        return libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_mScale_get(this.swigCPtr, this);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_getType(this.swigCPtr, this));
    }

    public void ref() {
        libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgTextMarkerDescriptorConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
