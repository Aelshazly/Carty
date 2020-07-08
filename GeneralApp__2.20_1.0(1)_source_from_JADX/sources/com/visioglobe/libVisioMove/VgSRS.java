package com.visioglobe.libVisioMove;

public class VgSRS extends VgReferenced {
    private long swigCPtr;

    protected VgSRS(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgSRS_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgSRS obj) {
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

    public boolean isGeoReferenced() {
        return libVisioMoveJNI.VgSRS_isGeoReferenced(this.swigCPtr, this);
    }
}
