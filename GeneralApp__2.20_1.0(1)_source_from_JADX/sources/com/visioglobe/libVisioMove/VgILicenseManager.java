package com.visioglobe.libVisioMove;

public class VgILicenseManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgILicenseManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgILicenseManager obj) {
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
                libVisioMoveJNI.delete_VgILicenseManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public String getMachineCode() {
        return libVisioMoveJNI.VgILicenseManager_getMachineCode(this.swigCPtr, this);
    }

    public String getVersion() {
        return libVisioMoveJNI.VgILicenseManager_getVersion(this.swigCPtr, this);
    }

    public String getRevision() {
        return libVisioMoveJNI.VgILicenseManager_getRevision(this.swigCPtr, this);
    }

    public static String staticGetVersion() {
        return libVisioMoveJNI.VgILicenseManager_staticGetVersion();
    }

    public static String staticGetMinimumDataSDKVersion() {
        return libVisioMoveJNI.VgILicenseManager_staticGetMinimumDataSDKVersion();
    }

    public static String staticGetRevision() {
        return libVisioMoveJNI.VgILicenseManager_staticGetRevision();
    }
}
