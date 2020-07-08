package com.visioglobe.libVisioMove;

public class VgIApplication {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIApplication(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIApplication obj) {
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
                libVisioMoveJNI.delete_VgIApplication(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgIModuleManager editModuleManager() {
        long cPtr = libVisioMoveJNI.VgIApplication_editModuleManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIModuleManager(cPtr, false);
    }

    public VgIEngine editEngine() {
        long cPtr = libVisioMoveJNI.VgIApplication_editEngine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIEngine(cPtr, false);
    }

    public VgIManipulatorManager editManipulatorManager() {
        long cPtr = libVisioMoveJNI.VgIApplication_editManipulatorManager(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIManipulatorManager(cPtr, false);
    }

    public static VgIApplication createApplication() {
        long cPtr = libVisioMoveJNI.VgIApplication_createApplication();
        if (cPtr == 0) {
            return null;
        }
        return new VgIApplication(cPtr, true);
    }

    public static VgIApplication createApplicationOGL() {
        long cPtr = libVisioMoveJNI.VgIApplication_createApplicationOGL();
        if (cPtr == 0) {
            return null;
        }
        return new VgIApplication(cPtr, true);
    }

    public static VgIApplication createApplicationOGLES1() {
        long cPtr = libVisioMoveJNI.VgIApplication_createApplicationOGLES1();
        if (cPtr == 0) {
            return null;
        }
        return new VgIApplication(cPtr, true);
    }

    public static VgIApplication createApplicationOGLES2() {
        long cPtr = libVisioMoveJNI.VgIApplication_createApplicationOGLES2();
        if (cPtr == 0) {
            return null;
        }
        return new VgIApplication(cPtr, true);
    }

    public static VgIApplication createApplicationNOHEAD() {
        long cPtr = libVisioMoveJNI.VgIApplication_createApplicationNOHEAD();
        if (cPtr == 0) {
            return null;
        }
        return new VgIApplication(cPtr, true);
    }

    public VgIMapModule editMapModule() {
        long cPtr = libVisioMoveJNI.VgIApplication_editMapModule(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIMapModule(cPtr, false);
    }

    public VgI3DModule edit3dModule() {
        long cPtr = libVisioMoveJNI.VgIApplication_edit3dModule(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgI3DModule(cPtr, false);
    }

    public VgIRoutingModule editRoutingModule() {
        long cPtr = libVisioMoveJNI.VgIApplication_editRoutingModule(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingModule(cPtr, false);
    }

    public VgINavigationModule editNavigationModule() {
        long cPtr = libVisioMoveJNI.VgIApplication_editNavigationModule(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationModule(cPtr, false);
    }
}
