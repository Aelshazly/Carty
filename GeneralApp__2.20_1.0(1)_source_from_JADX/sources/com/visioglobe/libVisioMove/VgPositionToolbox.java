package com.visioglobe.libVisioMove;

public class VgPositionToolbox {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgPositionToolbox(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgPositionToolbox obj) {
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
                libVisioMoveJNI.delete_VgPositionToolbox(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public double computeDistance(VgPositionVector pPositions) {
        return libVisioMoveJNI.VgPositionToolbox_computeDistance__SWIG_0(this.swigCPtr, this, VgPositionVector.getCPtr(pPositions), pPositions);
    }

    public double computeDistance(VgPosition pPos1, VgPosition pPos2) {
        return libVisioMoveJNI.VgPositionToolbox_computeDistance__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPos1), pPos1, VgPosition.getCPtr(pPos2), pPos2);
    }

    public double computeHeadingAngle(VgPosition pPosPivot, VgPosition pPos1, VgPosition pPos2) {
        return libVisioMoveJNI.VgPositionToolbox_computeHeadingAngle(this.swigCPtr, this, VgPosition.getCPtr(pPosPivot), pPosPivot, VgPosition.getCPtr(pPos1), pPos1, VgPosition.getCPtr(pPos2), pPos2);
    }

    public double computePitchAngle(VgPosition pPosPivot, VgPosition pPos1, VgPosition pPos2) {
        return libVisioMoveJNI.VgPositionToolbox_computePitchAngle(this.swigCPtr, this, VgPosition.getCPtr(pPosPivot), pPosPivot, VgPosition.getCPtr(pPos1), pPos1, VgPosition.getCPtr(pPos2), pPos2);
    }

    public VgPosition computeMiddlePoint(VgPosition pPos1, VgPosition pPos2) {
        return new VgPosition(libVisioMoveJNI.VgPositionToolbox_computeMiddlePoint(this.swigCPtr, this, VgPosition.getCPtr(pPos1), pPos1, VgPosition.getCPtr(pPos2), pPos2), true);
    }

    public VgPosition offsetPosition(VgPosition pPosition, double pHeadingInDegrees, double pPitchInDegrees, double pDistanceInMeters) {
        return new VgPosition(libVisioMoveJNI.VgPositionToolbox_offsetPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pHeadingInDegrees, pPitchInDegrees, pDistanceInMeters), true);
    }

    public VgSRSRefPtr editGeoReferencedSRS() {
        return new VgSRSRefPtr(libVisioMoveJNI.VgPositionToolbox_editGeoReferencedSRS(this.swigCPtr, this), true);
    }

    public VgSRSConstRefPtr getGeoReferencedSRS() {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgPositionToolbox_getGeoReferencedSRS(this.swigCPtr, this), true);
    }

    public VgSRSRefPtr editSceneSRS() {
        return new VgSRSRefPtr(libVisioMoveJNI.VgPositionToolbox_editSceneSRS(this.swigCPtr, this), true);
    }

    public VgSRSConstRefPtr getSceneSRS() {
        return new VgSRSConstRefPtr(libVisioMoveJNI.VgPositionToolbox_getSceneSRS(this.swigCPtr, this), true);
    }

    public void convert(VgPosition pPos, VgSRSConstRefPtr pSRS) {
        libVisioMoveJNI.VgPositionToolbox_convert(this.swigCPtr, this, VgPosition.getCPtr(pPos), pPos, VgSRSConstRefPtr.getCPtr(pSRS), pSRS);
    }

    public void geoConvert(VgPosition pPos, VgSRSConstRefPtr pSRS) {
        libVisioMoveJNI.VgPositionToolbox_geoConvert(this.swigCPtr, this, VgPosition.getCPtr(pPos), pPos, VgSRSConstRefPtr.getCPtr(pSRS), pSRS);
    }

    public VgPositionVector simplifyLineForWidth(VgPositionVector pPositions, double pWidth) {
        return new VgPositionVector(libVisioMoveJNI.VgPositionToolbox_simplifyLineForWidth(this.swigCPtr, this, VgPositionVector.getCPtr(pPositions), pPositions, pWidth), true);
    }

    public boolean isInside2D(VgPosition pPosition, VgPositionVector pPolygon) {
        return libVisioMoveJNI.VgPositionToolbox_isInside2D(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, VgPositionVector.getCPtr(pPolygon), pPolygon);
    }
}
