package com.visioglobe.libVisioMove;

public class VgGeoReferencedSRS extends VgSRS {
    private long swigCPtr;

    protected VgGeoReferencedSRS(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgGeoReferencedSRS_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgGeoReferencedSRS obj) {
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
}
