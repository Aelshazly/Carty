package com.visioglobe.libVisioMove;

public class VgICamera {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgICamera(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgICamera obj) {
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
                libVisioMoveJNI.delete_VgICamera(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPosition(VgPosition pPosition) {
        libVisioMoveJNI.VgICamera_setPosition(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition);
    }

    public void setHeading(double pHeadingInDegrees) {
        libVisioMoveJNI.VgICamera_setHeading(this.swigCPtr, this, pHeadingInDegrees);
    }

    public void setPitch(double pPitchInDegrees) {
        libVisioMoveJNI.VgICamera_setPitch(this.swigCPtr, this, pPitchInDegrees);
    }

    public VgPosition getPosition() {
        return new VgPosition(libVisioMoveJNI.VgICamera_getPosition(this.swigCPtr, this), true);
    }

    public double getHeading() {
        return libVisioMoveJNI.VgICamera_getHeading(this.swigCPtr, this);
    }

    public double getPitch() {
        return libVisioMoveJNI.VgICamera_getPitch(this.swigCPtr, this);
    }

    public void setViewpoint(VgIViewPoint pViewpoint) {
        libVisioMoveJNI.VgICamera_setViewpoint(this.swigCPtr, this, VgIViewPoint.getCPtr(pViewpoint), pViewpoint);
    }

    public VgIViewPoint getViewpoint() {
        return new VgIViewPoint(libVisioMoveJNI.VgICamera_getViewpoint(this.swigCPtr, this), false);
    }

    public void projectOnScreen(VgPosition pPosition, double[] pXScreen, double[] pYScreen, double[] pZScreen) {
        libVisioMoveJNI.VgICamera_projectOnScreen__SWIG_0(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pXScreen, pYScreen, pZScreen);
    }

    public void projectOnScreen(VgPosition pPosition, double[] pXScreen, double[] pYScreen) {
        libVisioMoveJNI.VgICamera_projectOnScreen__SWIG_1(this.swigCPtr, this, VgPosition.getCPtr(pPosition), pPosition, pXScreen, pYScreen);
    }

    public boolean pickGeographicPoint(double pX, double pY, VgPosition pPosition) {
        return libVisioMoveJNI.VgICamera_pickGeographicPoint(this.swigCPtr, this, pX, pY, VgPosition.getCPtr(pPosition), pPosition);
    }

    public VgIViewPoint getViewpointFromPositions(VgPositionVector pPositions, long pTop, long pBottom, long pLeft, long pRight) {
        return new VgIViewPoint(libVisioMoveJNI.VgICamera_getViewpointFromPositions__SWIG_0(this.swigCPtr, this, VgPositionVector.getCPtr(pPositions), pPositions, pTop, pBottom, pLeft, pRight), true);
    }

    public VgIViewPoint getViewpointFromPositions(VgPositionVector pPositions, long pTop, long pBottom, long pLeft, long pRight, double pPitch, double pHeading) {
        VgIViewPoint vgIViewPoint = new VgIViewPoint(libVisioMoveJNI.VgICamera_getViewpointFromPositions__SWIG_1(this.swigCPtr, this, VgPositionVector.getCPtr(pPositions), pPositions, pTop, pBottom, pLeft, pRight, pPitch, pHeading), true);
        return vgIViewPoint;
    }

    public VgIViewPoint getViewpointFromPositions(VgPositionVector pPositions, long pTop, long pBottom, long pLeft, long pRight, double pPitch, double pHeading, double pMinAltitude, double pMaxAltitude) {
        VgIViewPoint vgIViewPoint = new VgIViewPoint(libVisioMoveJNI.VgICamera_getViewpointFromPositions__SWIG_2(this.swigCPtr, this, VgPositionVector.getCPtr(pPositions), pPositions, pTop, pBottom, pLeft, pRight, pPitch, pHeading, pMinAltitude, pMaxAltitude), true);
        return vgIViewPoint;
    }

    public double getFovX() {
        return libVisioMoveJNI.VgICamera_getFovX(this.swigCPtr, this);
    }

    public double getFovY() {
        return libVisioMoveJNI.VgICamera_getFovY(this.swigCPtr, this);
    }

    public void setFovX(double pFovX, double pRatio) {
        libVisioMoveJNI.VgICamera_setFovX__SWIG_0(this.swigCPtr, this, pFovX, pRatio);
    }

    public void setFovX(double pFovX) {
        libVisioMoveJNI.VgICamera_setFovX__SWIG_1(this.swigCPtr, this, pFovX);
    }
}
