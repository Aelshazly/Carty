package com.visioglobe.libVisioMove;

public class VgIDatabase {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgIDatabase(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgIDatabase obj) {
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
                libVisioMoveJNI.delete_VgIDatabase(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean loadConfiguration(String pConfigFilename, long pSecretKey, String pLicenseGeneratorURL) {
        return libVisioMoveJNI.VgIDatabase_loadConfiguration__SWIG_0(this.swigCPtr, this, pConfigFilename, pSecretKey, pLicenseGeneratorURL);
    }

    public boolean loadConfiguration(String pConfigFilename, long pSecretKey) {
        return libVisioMoveJNI.VgIDatabase_loadConfiguration__SWIG_1(this.swigCPtr, this, pConfigFilename, pSecretKey);
    }

    public void unloadConfiguration() {
        libVisioMoveJNI.VgIDatabase_unloadConfiguration(this.swigCPtr, this);
    }

    public boolean getCachedLicenseFilenameForConfiguration(String pConfigFilename, String[] pOutLicenseFilename) {
        return libVisioMoveJNI.VgIDatabase_getCachedLicenseFilenameForConfiguration(this.swigCPtr, this, pConfigFilename, pOutLicenseFilename);
    }

    public boolean selectDataset(String pDatasetName) {
        return libVisioMoveJNI.VgIDatabase_selectDataset__SWIG_0(this.swigCPtr, this, pDatasetName);
    }

    public boolean selectDataset(int pIndex) {
        return libVisioMoveJNI.VgIDatabase_selectDataset__SWIG_1(this.swigCPtr, this, pIndex);
    }

    public VgIDatabaseDatasetDescriptor getCurrentDatasetDescriptor() {
        long cPtr = libVisioMoveJNI.VgIDatabase_getCurrentDatasetDescriptor(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgIDatabaseDatasetDescriptor(cPtr, false);
    }

    public VgIDatabaseDatasetDescriptorVector getDatasetDescriptors() {
        return new VgIDatabaseDatasetDescriptorVector(libVisioMoveJNI.VgIDatabase_getDatasetDescriptors(this.swigCPtr, this), false);
    }
}
