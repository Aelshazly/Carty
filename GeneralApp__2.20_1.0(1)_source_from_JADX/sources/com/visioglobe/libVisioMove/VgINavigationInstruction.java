package com.visioglobe.libVisioMove;

public class VgINavigationInstruction extends VgReferenced {
    private long swigCPtr;

    protected VgINavigationInstruction(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgINavigationInstruction_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgINavigationInstruction obj) {
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

    public double getLength() {
        return libVisioMoveJNI.VgINavigationInstruction_getLength(this.swigCPtr, this);
    }

    public VgManeuverType getManeuverType() {
        return VgManeuverType.swigToEnum(libVisioMoveJNI.VgINavigationInstruction_getManeuverType(this.swigCPtr, this));
    }

    public String getLayer() {
        return libVisioMoveJNI.VgINavigationInstruction_getLayer(this.swigCPtr, this);
    }

    public float getHeight() {
        return libVisioMoveJNI.VgINavigationInstruction_getHeight(this.swigCPtr, this);
    }

    public String getModality() {
        return libVisioMoveJNI.VgINavigationInstruction_getModality(this.swigCPtr, this);
    }

    public float getTime() {
        return libVisioMoveJNI.VgINavigationInstruction_getTime(this.swigCPtr, this);
    }

    public float getTotalTime() {
        return libVisioMoveJNI.VgINavigationInstruction_getTotalTime(this.swigCPtr, this);
    }

    public float getETA() {
        return libVisioMoveJNI.VgINavigationInstruction_getETA(this.swigCPtr, this);
    }

    public float getDuration() {
        return libVisioMoveJNI.VgINavigationInstruction_getDuration(this.swigCPtr, this);
    }

    public long getIndex() {
        return libVisioMoveJNI.VgINavigationInstruction_getIndex(this.swigCPtr, this);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgINavigationInstruction_getPosition(this.swigCPtr, this), false);
    }

    public VgNearPlaceVector getNearPlaces() {
        return new VgNearPlaceVector(libVisioMoveJNI.VgINavigationInstruction_getNearPlaces(this.swigCPtr, this), true);
    }

    public boolean isEndOrTransitionPoint() {
        return libVisioMoveJNI.VgINavigationInstruction_isEndOrTransitionPoint(this.swigCPtr, this);
    }

    public VgPositionVector getInstructionPositions() {
        return new VgPositionVector(libVisioMoveJNI.VgINavigationInstruction_getInstructionPositions(this.swigCPtr, this), false);
    }

    public VgStringSet getAttributes() {
        return new VgStringSet(libVisioMoveJNI.VgINavigationInstruction_getAttributes(this.swigCPtr, this), false);
    }

    public long getDestinationIndex() {
        return libVisioMoveJNI.VgINavigationInstruction_getDestinationIndex(this.swigCPtr, this);
    }
}
