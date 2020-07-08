package com.visioglobe.libVisioMove;

public class VgMarkerDescriptor extends VgReferenced {
    private long swigCPtr;

    protected VgMarkerDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgMarkerDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarkerDescriptor obj) {
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
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public VgMarkerType getType() {
        return VgMarkerType.swigToEnum(libVisioMoveJNI.VgMarkerDescriptor_getType(this.swigCPtr, this));
    }
}
