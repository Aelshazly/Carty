package com.visioglobe.libVisioMove;

public class VgTextMarkerDescriptor extends VgMarkerDescriptor {
    private long swigCPtr;

    protected VgTextMarkerDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgTextMarkerDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgTextMarkerDescriptor obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public static VgTextMarkerDescriptorRefPtr create() {
        return new VgTextMarkerDescriptorRefPtr(libVisioMoveJNI.VgTextMarkerDescriptor_create(), true);
    }

    public void setMText(String value) {
        libVisioMoveJNI.VgTextMarkerDescriptor_mText_set(this.swigCPtr, this, value);
    }

    public String getMText() {
        return libVisioMoveJNI.VgTextMarkerDescriptor_mText_get(this.swigCPtr, this);
    }

    public void setMTextAttributesMask(long value) {
        libVisioMoveJNI.VgTextMarkerDescriptor_mTextAttributesMask_set(this.swigCPtr, this, value);
    }

    public long getMTextAttributesMask() {
        return libVisioMoveJNI.VgTextMarkerDescriptor_mTextAttributesMask_get(this.swigCPtr, this);
    }

    public void setMColor(VgColor value) {
        libVisioMoveJNI.VgTextMarkerDescriptor_mColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMColor() {
        long cPtr = libVisioMoveJNI.VgTextMarkerDescriptor_mColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public void setMScale(double value) {
        libVisioMoveJNI.VgTextMarkerDescriptor_mScale_set(this.swigCPtr, this, value);
    }

    public double getMScale() {
        return libVisioMoveJNI.VgTextMarkerDescriptor_mScale_get(this.swigCPtr, this);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgTextMarkerDescriptor_getType(this.swigCPtr, this));
    }
}
