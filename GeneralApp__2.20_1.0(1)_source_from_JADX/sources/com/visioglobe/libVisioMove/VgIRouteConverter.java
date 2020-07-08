package com.visioglobe.libVisioMove;

public class VgIRouteConverter {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteConverter(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteConverter obj) {
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
                libVisioMoveJNI.delete_VgIRouteConverter(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void convertRoute(VgIRouteConstRefPtr pRoute, VgIRouteGeometryDescriptorVector pRouteGeometriesDescriptor) {
        libVisioMoveJNI.VgIRouteConverter_convertRoute(this.swigCPtr, this, VgIRouteConstRefPtr.getCPtr(pRoute), pRoute, VgIRouteGeometryDescriptorVector.getCPtr(pRouteGeometriesDescriptor), pRouteGeometriesDescriptor);
    }
}
