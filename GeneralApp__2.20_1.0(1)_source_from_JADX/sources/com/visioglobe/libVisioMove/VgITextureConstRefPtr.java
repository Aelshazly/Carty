package com.visioglobe.libVisioMove;

public class VgITextureConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgITextureConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgITextureConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgITextureConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgITextureConstRefPtr() {
        this(libVisioMoveJNI.new_VgITextureConstRefPtr__SWIG_0(), true);
    }

    public VgITextureConstRefPtr(VgITexture pPointer) {
        this(libVisioMoveJNI.new_VgITextureConstRefPtr__SWIG_1(VgITexture.getCPtr(pPointer), pPointer), true);
    }

    public VgITextureConstRefPtr(VgITextureConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgITextureConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgITextureConstRefPtr getNull() {
        return new VgITextureConstRefPtr(libVisioMoveJNI.VgITextureConstRefPtr_getNull(), true);
    }

    public VgITextureConstRefPtr set(VgITexture pPointer) {
        return new VgITextureConstRefPtr(libVisioMoveJNI.VgITextureConstRefPtr_set(this.swigCPtr, this, VgITexture.getCPtr(pPointer), pPointer), false);
    }

    public VgITexture __ref__() {
        return new VgITexture(libVisioMoveJNI.VgITextureConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgITexture __deref__() {
        long cPtr = libVisioMoveJNI.VgITextureConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITexture(cPtr, false);
    }

    public VgITexture get() {
        long cPtr = libVisioMoveJNI.VgITextureConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgITexture(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgITextureConstRefPtr_isValid(this.swigCPtr, this);
    }

    public int getWidth() {
        return libVisioMoveJNI.VgITextureConstRefPtr_getWidth(this.swigCPtr, this);
    }

    public int getHeight() {
        return libVisioMoveJNI.VgITextureConstRefPtr_getHeight(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgITextureConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgITextureConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgITextureConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
