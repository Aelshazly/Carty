package com.visioglobe.libVisioMove;

public class VgQuadtreeLayoutDescriptor extends VgLayoutDescriptor {
    private long swigCPtr;

    protected VgQuadtreeLayoutDescriptor(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgQuadtreeLayoutDescriptor_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgQuadtreeLayoutDescriptor obj) {
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

    public VgQuadtreeLayoutDescriptor(float pRootSize, long pNumLevels) {
        this(libVisioMoveJNI.new_VgQuadtreeLayoutDescriptor__SWIG_0(pRootSize, pNumLevels), true);
    }

    public VgQuadtreeLayoutDescriptor(float pRootSize) {
        this(libVisioMoveJNI.new_VgQuadtreeLayoutDescriptor__SWIG_1(pRootSize), true);
    }

    public VgQuadtreeLayoutDescriptor() {
        this(libVisioMoveJNI.new_VgQuadtreeLayoutDescriptor__SWIG_2(), true);
    }

    public void setMRootSize(float value) {
        libVisioMoveJNI.VgQuadtreeLayoutDescriptor_mRootSize_set(this.swigCPtr, this, value);
    }

    public float getMRootSize() {
        return libVisioMoveJNI.VgQuadtreeLayoutDescriptor_mRootSize_get(this.swigCPtr, this);
    }

    public void setMNumLevels(long value) {
        libVisioMoveJNI.VgQuadtreeLayoutDescriptor_mNumLevels_set(this.swigCPtr, this, value);
    }

    public long getMNumLevels() {
        return libVisioMoveJNI.VgQuadtreeLayoutDescriptor_mNumLevels_get(this.swigCPtr, this);
    }
}
