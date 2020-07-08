package com.visioglobe.libVisioMove;

public class VgMarker extends VgReferenced {
    private long swigCPtr;

    protected VgMarker(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgMarker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgMarker obj) {
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

    public VgIconMarker asIconMarker() {
        long cPtr = libVisioMoveJNI.VgMarker_asIconMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarker(cPtr, false);
    }

    public VgTextMarker asTextMarker() {
        long cPtr = libVisioMoveJNI.VgMarker_asTextMarker(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarker(cPtr, false);
    }
}
