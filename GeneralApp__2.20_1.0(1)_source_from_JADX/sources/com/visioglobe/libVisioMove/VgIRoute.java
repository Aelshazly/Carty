package com.visioglobe.libVisioMove;

public class VgIRoute extends VgReferenced {
    private long swigCPtr;

    protected VgIRoute(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIRoute_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoute obj) {
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

    public double getLength() {
        return libVisioMoveJNI.VgIRoute_getLength(this.swigCPtr, this);
    }

    public double getDuration() {
        return libVisioMoveJNI.VgIRoute_getDuration(this.swigCPtr, this);
    }

    public VgIntVector getDestinationIndices() {
        return new VgIntVector(libVisioMoveJNI.VgIRoute_getDestinationIndices(this.swigCPtr, this), false);
    }

    public VgIRouteRequestParameters getRequestParameters() {
        return new VgIRouteRequestParameters(libVisioMoveJNI.VgIRoute_getRequestParameters(this.swigCPtr, this), false);
    }
}
