package com.visioglobe.libVisioMove;

public class VgIRouteConverterFactory {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIRouteConverterFactory(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRouteConverterFactory obj) {
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
                libVisioMoveJNI.delete_VgIRouteConverterFactory(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIRouteConverter createConverter(VgIRouteConverterType pType) {
        long cPtr = libVisioMoveJNI.VgIRouteConverterFactory_createConverter(this.swigCPtr, this, pType.swigValue());
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteConverter(cPtr, false);
    }
}
