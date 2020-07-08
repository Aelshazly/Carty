package com.visioglobe.libVisioMove;

public class VgIconMarkerDescriptor extends VgMarkerDescriptor {
    private long swigCPtr;

    protected VgIconMarkerDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIconMarkerDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIconMarkerDescriptor obj) {
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

    public static VgIconMarkerDescriptorRefPtr create() {
        return new VgIconMarkerDescriptorRefPtr(libVisioMoveJNI.VgIconMarkerDescriptor_create(), true);
    }

    public void setMIcon(VgITextureRefPtr value) {
        libVisioMoveJNI.VgIconMarkerDescriptor_mIcon_set(this.swigCPtr, this, VgITextureRefPtr.getCPtr(value), value);
    }

    public VgITextureRefPtr getMIcon() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptor_mIcon_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITextureRefPtr(cPtr, false);
    }

    public void setMScale(float value) {
        libVisioMoveJNI.VgIconMarkerDescriptor_mScale_set(this.swigCPtr, this, value);
    }

    public float getMScale() {
        return libVisioMoveJNI.VgIconMarkerDescriptor_mScale_get(this.swigCPtr, this);
    }

    public void setMColor(VgColor value) {
        libVisioMoveJNI.VgIconMarkerDescriptor_mColor_set(this.swigCPtr, this, VgColor.getCPtr(value), value);
    }

    public VgColor getMColor() {
        long cPtr = libVisioMoveJNI.VgIconMarkerDescriptor_mColor_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgColor(cPtr, false);
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgIconMarkerDescriptor_getType(this.swigCPtr, this));
    }
}
