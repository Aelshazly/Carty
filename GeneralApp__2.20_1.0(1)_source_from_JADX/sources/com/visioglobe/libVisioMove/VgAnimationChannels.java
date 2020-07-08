package com.visioglobe.libVisioMove;

public class VgAnimationChannels {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgAnimationChannels(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgAnimationChannels obj) {
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
                libVisioMoveJNI.delete_VgAnimationChannels(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static String getMscNode() {
        return libVisioMoveJNI.VgAnimationChannels_mscNode_get();
    }

    public static String getMscLocalNode() {
        return libVisioMoveJNI.VgAnimationChannels_mscLocalNode_get();
    }

    public static String getMscPosition() {
        return libVisioMoveJNI.VgAnimationChannels_mscPosition_get();
    }

    public static String getMscOrientation() {
        return libVisioMoveJNI.VgAnimationChannels_mscOrientation_get();
    }

    public static String getMscScale() {
        return libVisioMoveJNI.VgAnimationChannels_mscScale_get();
    }

    public static String getMscFieldOfView() {
        return libVisioMoveJNI.VgAnimationChannels_mscFieldOfView_get();
    }

    public static String getMscColorMatrix() {
        return libVisioMoveJNI.VgAnimationChannels_mscColorMatrix_get();
    }

    public static String getMscAlpha() {
        return libVisioMoveJNI.VgAnimationChannels_mscAlpha_get();
    }

    public static VgStringPair getMscPositionChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscPositionChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public static VgStringPair getMscOrientationChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscOrientationChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public static VgStringPair getMscScaleChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscScaleChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public static VgStringPair getMscAlphaChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscAlphaChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public static VgStringPair getMscLocalPositionChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscLocalPositionChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public static VgStringPair getMscLocalOrientationChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscLocalOrientationChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public static VgStringPair getMscLocalScaleChannel() {
        long cPtr = libVisioMoveJNI.VgAnimationChannels_mscLocalScaleChannel_get();
        if (cPtr == 0) {
            return null;
        }
        return new VgStringPair(cPtr, false);
    }

    public VgAnimationChannels() {
        this(libVisioMoveJNI.new_VgAnimationChannels(), true);
    }
}
