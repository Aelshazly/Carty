package com.visioglobe.libVisioMove;

public class VgITextureRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgITextureRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgITextureRefPtr obj) {
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
                libVisioMoveJNI.delete_VgITextureRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgITextureRefPtr() {
        this(libVisioMoveJNI.new_VgITextureRefPtr__SWIG_0(), true);
    }

    public VgITextureRefPtr(VgITexture pPointer) {
        this(libVisioMoveJNI.new_VgITextureRefPtr__SWIG_1(VgITexture.getCPtr(pPointer), pPointer), true);
    }

    public VgITextureRefPtr(VgITextureRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgITextureRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgITextureRefPtr getNull() {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureRefPtr_getNull(), true);
    }

    public VgITextureRefPtr set(VgITexture pPointer) {
        return new VgITextureRefPtr(libVisioMoveJNI.VgITextureRefPtr_set(this.swigCPtr, this, VgITexture.getCPtr(pPointer), pPointer), false);
    }

    public VgITexture __ref__() {
        return new VgITexture(libVisioMoveJNI.VgITextureRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgITexture __deref__() {
        long cPtr = libVisioMoveJNI.VgITextureRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITexture(cPtr, false);
    }

    public VgITexture get() {
        long cPtr = libVisioMoveJNI.VgITextureRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITexture(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgITextureRefPtr_isValid(this.swigCPtr, this);
    }

    public int getWidth() {
        return libVisioMoveJNI.VgITextureRefPtr_getWidth(this.swigCPtr, this);
    }

    public int getHeight() {
        return libVisioMoveJNI.VgITextureRefPtr_getHeight(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgITextureRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgITextureRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgITextureRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
