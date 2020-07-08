package com.visioglobe.libVisioMove;

public class VgINavigationInstructionRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationInstructionRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationInstructionRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationInstructionRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationInstructionRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationInstructionRefPtr__SWIG_0(), true);
    }

    public VgINavigationInstructionRefPtr(VgINavigationInstruction pPointer) {
        this(libVisioMoveJNI.new_VgINavigationInstructionRefPtr__SWIG_1(VgINavigationInstruction.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationInstructionRefPtr(VgINavigationInstructionRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationInstructionRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgINavigationInstructionRefPtr getNull() {
        return new VgINavigationInstructionRefPtr(libVisioMoveJNI.VgINavigationInstructionRefPtr_getNull(), true);
    }

    public VgINavigationInstructionRefPtr set(VgINavigationInstruction pPointer) {
        return new VgINavigationInstructionRefPtr(libVisioMoveJNI.VgINavigationInstructionRefPtr_set(this.swigCPtr, this, VgINavigationInstruction.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigationInstruction __ref__() {
        return new VgINavigationInstruction(libVisioMoveJNI.VgINavigationInstructionRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigationInstruction __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationInstructionRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationInstruction(cPtr, false);
    }

    public VgINavigationInstruction get() {
        long cPtr = libVisioMoveJNI.VgINavigationInstructionRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationInstruction(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_isValid(this.swigCPtr, this);
    }

    public double getLength() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getLength(this.swigCPtr, this);
    }

    public VgManeuverType getManeuverType() {
        return VgManeuverType.swigToEnum(libVisioMoveJNI.VgINavigationInstructionRefPtr_getManeuverType(this.swigCPtr, this));
    }

    public String getLayer() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getLayer(this.swigCPtr, this);
    }

    public float getHeight() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getHeight(this.swigCPtr, this);
    }

    public String getModality() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getModality(this.swigCPtr, this);
    }

    public float getTime() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getTime(this.swigCPtr, this);
    }

    public float getTotalTime() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getTotalTime(this.swigCPtr, this);
    }

    public float getETA() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getETA(this.swigCPtr, this);
    }

    public float getDuration() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getDuration(this.swigCPtr, this);
    }

    public long getIndex() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getIndex(this.swigCPtr, this);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgINavigationInstructionRefPtr_getPosition(this.swigCPtr, this), false);
    }

    public VgNearPlaceVector getNearPlaces() {
        return new VgNearPlaceVector(libVisioMoveJNI.VgINavigationInstructionRefPtr_getNearPlaces(this.swigCPtr, this), true);
    }

    public boolean isEndOrTransitionPoint() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_isEndOrTransitionPoint(this.swigCPtr, this);
    }

    public VgPositionVector getInstructionPositions() {
        return new VgPositionVector(libVisioMoveJNI.VgINavigationInstructionRefPtr_getInstructionPositions(this.swigCPtr, this), false);
    }

    public VgStringSet getAttributes() {
        return new VgStringSet(libVisioMoveJNI.VgINavigationInstructionRefPtr_getAttributes(this.swigCPtr, this), false);
    }

    public long getDestinationIndex() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getDestinationIndex(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationInstructionRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationInstructionRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
