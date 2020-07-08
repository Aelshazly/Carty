package com.visioglobe.libVisioMove;

public class VgPoint extends VgIGeometry {
    private long swigCPtr;

    protected VgPoint(long cPtr, boolean cMemoryOwn) {
        super(libVisioMoveJNI.VgPoint_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPoint obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            super.delete();
            this.swigCPtr = 0;
        }
    }

    public VgIGeometryType getType() {
        return VgIGeometryType.swigToEnum(libVisioMoveJNI.VgPoint_getType(this.swigCPtr, this));
    }

    public VgAltitudeMode getAltitudeMode() {
        return VgAltitudeMode.swigToEnum(libVisioMoveJNI.VgPoint_getAltitudeMode(this.swigCPtr, this));
    }

    public void setAltitudeMode(VgAltitudeMode pAltitudeMode) {
        libVisioMoveJNI.VgPoint_setAltitudeMode(this.swigCPtr, this, pAltitudeMode.swigValue());
    }

    public VgAnchorMode getAnchorPosition() {
        return VgAnchorMode.swigToEnum(libVisioMoveJNI.VgPoint_getAnchorPosition(this.swigCPtr, this));
    }

    public void setAnchorPosition(VgAnchorMode pAnchorMode) {
        libVisioMoveJNI.VgPoint_setAnchorPosition(this.swigCPtr, this, pAnchorMode.swigValue());
    }

    public float getGeometryConstantSizeDistance() {
        return libVisioMoveJNI.VgPoint_getGeometryConstantSizeDistance(this.swigCPtr, this);
    }

    public void setGeometryConstantSizeDistance(float pDistanceInMeters) {
        libVisioMoveJNI.VgPoint_setGeometryConstantSizeDistance(this.swigCPtr, this, pDistanceInMeters);
    }

    public boolean isForceFrontFaceEnabled() {
        return libVisioMoveJNI.VgPoint_isForceFrontFaceEnabled(this.swigCPtr, this);
    }

    public void setForceFrontFace(boolean pEnable) {
        libVisioMoveJNI.VgPoint_setForceFrontFace(this.swigCPtr, this, pEnable);
    }

    public VgOrientationConstraints getOrientationConstraints() {
        return new VgOrientationConstraints(libVisioMoveJNI.VgPoint_getOrientationConstraints(this.swigCPtr, this), true);
    }

    public void setOrientationConstraints(VgOrientationConstraints pConstraints) {
        libVisioMoveJNI.VgPoint_setOrientationConstraints(this.swigCPtr, this, VgOrientationConstraints.getCPtr(pConstraints), pConstraints);
    }

    public long getNbMarkers() {
        return libVisioMoveJNI.VgPoint_getNbMarkers(this.swigCPtr, this);
    }

    public VgMarkerRefPtr editMarker(long pIndex) {
        return new VgMarkerRefPtr(libVisioMoveJNI.VgPoint_editMarker(this.swigCPtr, this, pIndex), true);
    }

    public boolean insertMarker(VgMarkerDescriptor pMarkerDescriptor, int pMarkerPosition) {
        return libVisioMoveJNI.VgPoint_insertMarker(this.swigCPtr, this, VgMarkerDescriptor.getCPtr(pMarkerDescriptor), pMarkerDescriptor, pMarkerPosition);
    }

    public boolean removeMarker(int pMarkerPosition) {
        return libVisioMoveJNI.VgPoint_removeMarker(this.swigCPtr, this, pMarkerPosition);
    }

    public VgPoint asPoint() {
        long cPtr = libVisioMoveJNI.VgPoint_asPoint(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgPoint(cPtr, false);
    }

    public VgLine asLine() {
        long cPtr = libVisioMoveJNI.VgPoint_asLine(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgLine(cPtr, false);
    }

    public boolean setSizePolicy(VgSizePolicy pSizePolicy) {
        return libVisioMoveJNI.VgPoint_setSizePolicy(this.swigCPtr, this, pSizePolicy.swigValue());
    }

    public VgSizePolicy getSizePolicy() {
        return VgSizePolicy.swigToEnum(libVisioMoveJNI.VgPoint_getSizePolicy(this.swigCPtr, this));
    }

    public void getBoundingRect(float[] pRectangleWidth, float[] pRectangleHeight) {
        libVisioMoveJNI.VgPoint_getBoundingRect(this.swigCPtr, this, pRectangleWidth, pRectangleHeight);
    }

    public void setBoundingRect(float pRectangleWidth, float pRectangleHeight) {
        libVisioMoveJNI.VgPoint_setBoundingRect(this.swigCPtr, this, pRectangleWidth, pRectangleHeight);
    }
}
