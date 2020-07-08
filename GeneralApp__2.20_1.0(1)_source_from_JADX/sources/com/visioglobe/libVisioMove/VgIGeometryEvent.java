package com.visioglobe.libVisioMove;

public class VgIGeometryEvent extends VgIEvent {
    private long swigCPtr;

    protected VgIGeometryEvent(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIGeometryEvent_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIGeometryEvent obj) {
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

    public VgIGeometryConstRefPtr getGeometry() {
        return new VgIGeometryConstRefPtr(libVisioMoveJNI.VgIGeometryEvent_getGeometry(this.swigCPtr, this), true);
    }
}
