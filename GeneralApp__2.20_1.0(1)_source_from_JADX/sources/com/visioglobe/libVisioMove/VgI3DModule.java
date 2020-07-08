package com.visioglobe.libVisioMove;

public class VgI3DModule extends VgIModule {
    private long swigCPtr;

    protected VgI3DModule(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgI3DModule_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgI3DModule obj) {
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

    public VgModelManager editModelManager() {
        return new VgModelManager(libVisioMoveJNI.VgI3DModule_editModelManager(this.swigCPtr, this), false);
    }
}
