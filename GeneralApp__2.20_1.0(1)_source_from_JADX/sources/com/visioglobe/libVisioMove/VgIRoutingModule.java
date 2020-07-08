package com.visioglobe.libVisioMove;

public class VgIRoutingModule extends VgIModule {
    private long swigCPtr;

    protected VgIRoutingModule(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgIRoutingModule_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIRoutingModule obj) {
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

    public VgIRoutingSolver getRoutingSolver() {
        long cPtr = libVisioMoveJNI.VgIRoutingModule_getRoutingSolver(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingSolver(cPtr, false);
    }

    public VgIRouteConverterFactory getRouteConverterFactory() {
        long cPtr = libVisioMoveJNI.VgIRoutingModule_getRouteConverterFactory(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRouteConverterFactory(cPtr, false);
    }

    public int setEdgeTimeByAttributeAndModality(String pEdgeAttribute, String pModality, float pTime) {
        return libVisioMoveJNI.VgIRoutingModule_setEdgeTimeByAttributeAndModality(this.swigCPtr, this, pEdgeAttribute, pModality, pTime);
    }

    public void getAllModalityNames(VgStringVector pNames) {
        libVisioMoveJNI.VgIRoutingModule_getAllModalityNames(this.swigCPtr, this, VgStringVector.getCPtr(pNames), pNames);
    }

    public void getAllAttributeNames(VgStringVector pNames) {
        libVisioMoveJNI.VgIRoutingModule_getAllAttributeNames(this.swigCPtr, this, VgStringVector.getCPtr(pNames), pNames);
    }
}
