package com.visioglobe.libVisioMove;

public class VgReferencedRefPtr {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VgReferencedRefPtr(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgReferencedRefPtr obj) {
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
                libVisioMoveJNI.delete_VgReferencedRefPtr(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgReferencedRefPtr() {
        this(libVisioMoveJNI.new_VgReferencedRefPtr__SWIG_0(), true);
    }

    public VgReferencedRefPtr(VgReferenced pPointer) {
        this(libVisioMoveJNI.new_VgReferencedRefPtr__SWIG_1(VgReferenced.getCPtr(pPointer), pPointer), true);
    }

    public VgReferencedRefPtr(VgReferencedRefPtr pRefPtr) {
        this(libVisioMoveJNI.new_VgReferencedRefPtr__SWIG_2(getCPtr(pRefPtr), pRefPtr), true);
    }

    public static VgReferencedRefPtr getNull() {
        return new VgReferencedRefPtr(libVisioMoveJNI.VgReferencedRefPtr_getNull(), true);
    }

    public VgReferencedRefPtr set(VgReferenced pPointer) {
        return new VgReferencedRefPtr(libVisioMoveJNI.VgReferencedRefPtr_set(this.swigCPtr, this, VgReferenced.getCPtr(pPointer), pPointer), false);
    }

    public VgReferenced __ref__() {
        return new VgReferenced(libVisioMoveJNI.VgReferencedRefPtr___ref__(this.swigCPtr, this), false);
    }

    public VgReferenced __deref__() {
        long cPtr = libVisioMoveJNI.VgReferencedRefPtr___deref__(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgReferenced(cPtr, false);
    }

    public VgReferenced get() {
        long cPtr = libVisioMoveJNI.VgReferencedRefPtr_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new VgReferenced(cPtr, false);
    }

    public boolean isValid() {
        return libVisioMoveJNI.VgReferencedRefPtr_isValid(this.swigCPtr, this);
    }

    public VgReferencedRefPtr(VgAnimationRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_10__(VgAnimationRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgAnimationDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_11__(VgAnimationDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgAxialRotationQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_12__(VgAxialRotationQuaternionFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgBinaryBufferRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_13__(VgBinaryBufferRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgDiscreteQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_14__(VgDiscreteQuaternionFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgDiscreteVectorFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_15__(VgDiscreteVectorFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgFloatInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_16__(VgFloatInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_17__(VgFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgGeoReferencedSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_18__(VgGeoReferencedSRSRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIAnimationCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_19__(VgIAnimationCallbackRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIEnginePostDrawCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_20__(VgIEnginePostDrawCallbackRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIResourceCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_21__(VgIResourceCallbackRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIResourceRequestRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_22__(VgIResourceRequestRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgManipulatorListenerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_23__(VgManipulatorListenerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgITextureRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_24__(VgITextureRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLayerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_25__(VgLayerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLayerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_26__(VgLayerDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLightRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_27__(VgLightRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgMatrixInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_28__(VgMatrixInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgMetricSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_29__(VgMetricSRSRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgQuaternionInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_30__(VgQuaternionInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgSinusoidalVectorOffsetFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_31__(VgSinusoidalVectorOffsetFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgSpatialRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_32__(VgSpatialRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgSplineOrientationQuaternionFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_33__(VgSplineOrientationQuaternionFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgSplineVectorFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_34__(VgSplineVectorFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgSRSRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_35__(VgSRSRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgVectorInterpolationFunctorDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_36__(VgVectorInterpolationFunctorDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIconMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_37__(VgIconMarkerDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIGeometryRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_38__(VgIGeometryRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIGeometryCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_39__(VgIGeometryCallbackRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLineRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_40__(VgLineRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLineDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_41__(VgLineDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLinkRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_42__(VgLinkRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgLinkDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_43__(VgLinkDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgMarkerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_44__(VgMarkerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_45__(VgMarkerDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgPointRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_46__(VgPointRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgPointDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_47__(VgPointDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgTextMarkerDescriptorRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_48__(VgTextMarkerDescriptorRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIPlaceListenerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_49__(VgIPlaceListenerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIRoutingNodeRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_50__(VgIRoutingNodeRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIRouteRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_51__(VgIRouteRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgIRouteCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_52__(VgIRouteCallbackRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgINavigationRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_53__(VgINavigationRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgINavigationInstructionRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_54__(VgINavigationInstructionRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgINavigationCallbackRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_55__(VgINavigationCallbackRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public VgReferencedRefPtr(VgINavigationListenerRefPtr pRefPtr) {
        this(libVisioMoveJNI.new___dummy_56__(VgINavigationListenerRefPtr.getCPtr(pRefPtr), pRefPtr), true);
    }

    public void ref() {
        libVisioMoveJNI.VgReferencedRefPtr_ref(this.swigCPtr, this);
    }

    public int unref() {
        return libVisioMoveJNI.VgReferencedRefPtr_unref(this.swigCPtr, this);
    }

    public int getNbReferences() {
        return libVisioMoveJNI.VgReferencedRefPtr_getNbReferences(this.swigCPtr, this);
    }
}
