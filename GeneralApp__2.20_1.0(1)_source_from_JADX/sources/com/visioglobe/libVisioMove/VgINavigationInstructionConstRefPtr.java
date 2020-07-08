package com.visioglobe.libVisioMove;

public class VgINavigationInstructionConstRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgINavigationInstructionConstRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationInstructionConstRefPtr obj) {
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
                libVisioMoveJNI.delete_VgINavigationInstructionConstRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgINavigationInstructionConstRefPtr() {
        this(libVisioMoveJNI.new_VgINavigationInstructionConstRefPtr__SWIG_0(), true);
    }

    public VgINavigationInstructionConstRefPtr(VgINavigationInstruction pPointer) {
        this(libVisioMoveJNI.new_VgINavigationInstructionConstRefPtr__SWIG_1(VgINavigationInstruction.getCPtr(pPointer), pPointer), true);
    }

    public VgINavigationInstructionConstRefPtr(VgINavigationInstructionConstRefPtr pConstRefPtr) {
        this(libVisioMoveJNI.new_VgINavigationInstructionConstRefPtr__SWIG_2(getCPtr(pConstRefPtr), pConstRefPtr), true);
    }

    public static VgINavigationInstructionConstRefPtr getNull() {
        return new VgINavigationInstructionConstRefPtr(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getNull(), true);
    }

    public VgINavigationInstructionConstRefPtr set(VgINavigationInstruction pPointer) {
        return new VgINavigationInstructionConstRefPtr(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_set(this.swigCPtr, this, VgINavigationInstruction.getCPtr(pPointer), pPointer), false);
    }

    public VgINavigationInstruction __ref__() {
        return new VgINavigationInstruction(libVisioMoveJNI.VgINavigationInstructionConstRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgINavigationInstruction __deref__() {
        long cPtr = libVisioMoveJNI.VgINavigationInstructionConstRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationInstruction(cPtr, false);
    }

    public VgINavigationInstruction get() {
        long cPtr = libVisioMoveJNI.VgINavigationInstructionConstRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationInstruction(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_isValid(this.swigCPtr, this);
    }

    public double getLength() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getLength(this.swigCPtr, this);
    }

    public VgManeuverType getManeuverType() {
        return VgManeuverType.swigToEnum(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getManeuverType(this.swigCPtr, this));
    }

    public String getLayer() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getLayer(this.swigCPtr, this);
    }

    public float getHeight() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getHeight(this.swigCPtr, this);
    }

    public String getModality() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getModality(this.swigCPtr, this);
    }

    public float getTime() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getTime(this.swigCPtr, this);
    }

    public float getTotalTime() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getTotalTime(this.swigCPtr, this);
    }

    public float getETA() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getETA(this.swigCPtr, this);
    }

    public float getDuration() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getDuration(this.swigCPtr, this);
    }

    public long getIndex() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getIndex(this.swigCPtr, this);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getPosition(this.swigCPtr, this), false);
    }

    public VgNearPlaceVector getNearPlaces() {
        return new VgNearPlaceVector(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getNearPlaces(this.swigCPtr, this), true);
    }

    public boolean isEndOrTransitionPoint() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_isEndOrTransitionPoint(this.swigCPtr, this);
    }

    public VgPositionVector getInstructionPositions() {
        return new VgPositionVector(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getInstructionPositions(this.swigCPtr, this), false);
    }

    public VgStringSet getAttributes() {
        return new VgStringSet(libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getAttributes(this.swigCPtr, this), false);
    }

    public long getDestinationIndex() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getDestinationIndex(this.swigCPtr, this);
    }

    public void ref() {
        libVisioMoveJNI.VgINavigationInstructionConstRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgINavigationInstructionConstRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
