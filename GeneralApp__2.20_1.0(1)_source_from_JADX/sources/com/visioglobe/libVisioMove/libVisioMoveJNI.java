package com.visioglobe.libVisioMove;

public class libVisioMoveJNI {
    public static final native int NULL_get();

    public static final native long VgAnimationChannels_mscAlphaChannel_get();

    public static final native String VgAnimationChannels_mscAlpha_get();

    public static final native String VgAnimationChannels_mscColorMatrix_get();

    public static final native String VgAnimationChannels_mscFieldOfView_get();

    public static final native String VgAnimationChannels_mscLocalNode_get();

    public static final native long VgAnimationChannels_mscLocalOrientationChannel_get();

    public static final native long VgAnimationChannels_mscLocalPositionChannel_get();

    public static final native long VgAnimationChannels_mscLocalScaleChannel_get();

    public static final native String VgAnimationChannels_mscNode_get();

    public static final native long VgAnimationChannels_mscOrientationChannel_get();

    public static final native String VgAnimationChannels_mscOrientation_get();

    public static final native long VgAnimationChannels_mscPositionChannel_get();

    public static final native String VgAnimationChannels_mscPosition_get();

    public static final native long VgAnimationChannels_mscScaleChannel_get();

    public static final native String VgAnimationChannels_mscScale_get();

    public static final native long VgAnimationConstRefPtr___deref__(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native long VgAnimationConstRefPtr___ref__(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native long VgAnimationConstRefPtr_get(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native float VgAnimationConstRefPtr_getCursor(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native float VgAnimationConstRefPtr_getDuration(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native int VgAnimationConstRefPtr_getNbReferences(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native long VgAnimationConstRefPtr_getNull();

    public static final native boolean VgAnimationConstRefPtr_isPlaying(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native boolean VgAnimationConstRefPtr_isValid(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native void VgAnimationConstRefPtr_ref(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native long VgAnimationConstRefPtr_set(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr, long j2, VgAnimation vgAnimation);

    public static final native int VgAnimationConstRefPtr_unref(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr___deref__(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr___ref__(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr_get(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native int VgAnimationDescriptorConstRefPtr_getNbReferences(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr_getNull();

    public static final native boolean VgAnimationDescriptorConstRefPtr_isValid(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr_mCallback_get(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native float VgAnimationDescriptorConstRefPtr_mDuration_get(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr_mFunctorDescriptors_get(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr_mLoopMode_get(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native void VgAnimationDescriptorConstRefPtr_ref(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorConstRefPtr_set(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr, long j2, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native int VgAnimationDescriptorConstRefPtr_unref(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgAnimationDescriptorRefPtr___deref__(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptorRefPtr___ref__(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptorRefPtr_create(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptorRefPtr_get(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native int VgAnimationDescriptorRefPtr_getNbReferences(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptorRefPtr_getNull();

    public static final native boolean VgAnimationDescriptorRefPtr_isValid(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptorRefPtr_mCallback_get(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native void VgAnimationDescriptorRefPtr_mCallback_set(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr, long j2, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native float VgAnimationDescriptorRefPtr_mDuration_get(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native void VgAnimationDescriptorRefPtr_mDuration_set(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr, float f);

    public static final native long VgAnimationDescriptorRefPtr_mFunctorDescriptors_get(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native void VgAnimationDescriptorRefPtr_mFunctorDescriptors_set(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr, long j2, VgFunctorDescriptorMap vgFunctorDescriptorMap);

    public static final native long VgAnimationDescriptorRefPtr_mLoopMode_get(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native void VgAnimationDescriptorRefPtr_mLoopMode_set(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr, long j2);

    public static final native void VgAnimationDescriptorRefPtr_ref(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptorRefPtr_set(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr, long j2, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native int VgAnimationDescriptorRefPtr_unref(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgAnimationDescriptor_SWIGUpcast(long j);

    public static final native long VgAnimationDescriptor_create();

    public static final native long VgAnimationDescriptor_mCallback_get(long j, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native void VgAnimationDescriptor_mCallback_set(long j, VgAnimationDescriptor vgAnimationDescriptor, long j2, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native float VgAnimationDescriptor_mDuration_get(long j, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native void VgAnimationDescriptor_mDuration_set(long j, VgAnimationDescriptor vgAnimationDescriptor, float f);

    public static final native long VgAnimationDescriptor_mFunctorDescriptors_get(long j, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native void VgAnimationDescriptor_mFunctorDescriptors_set(long j, VgAnimationDescriptor vgAnimationDescriptor, long j2, VgFunctorDescriptorMap vgFunctorDescriptorMap);

    public static final native long VgAnimationDescriptor_mLoopMode_get(long j, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native void VgAnimationDescriptor_mLoopMode_set(long j, VgAnimationDescriptor vgAnimationDescriptor, long j2);

    public static final native long VgAnimationRefPtr___deref__(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native long VgAnimationRefPtr___ref__(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native long VgAnimationRefPtr_get(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native float VgAnimationRefPtr_getCursor(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native float VgAnimationRefPtr_getDuration(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native int VgAnimationRefPtr_getNbReferences(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native long VgAnimationRefPtr_getNull();

    public static final native boolean VgAnimationRefPtr_isPlaying(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native boolean VgAnimationRefPtr_isValid(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgAnimationRefPtr_pause(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgAnimationRefPtr_ref(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgAnimationRefPtr_seek(long j, VgAnimationRefPtr vgAnimationRefPtr, float f);

    public static final native long VgAnimationRefPtr_set(long j, VgAnimationRefPtr vgAnimationRefPtr, long j2, VgAnimation vgAnimation);

    public static final native void VgAnimationRefPtr_start__SWIG_0(long j, VgAnimationRefPtr vgAnimationRefPtr, float f, float f2, float f3, float f4);

    public static final native void VgAnimationRefPtr_start__SWIG_1(long j, VgAnimationRefPtr vgAnimationRefPtr, float f, float f2, float f3);

    public static final native void VgAnimationRefPtr_start__SWIG_2(long j, VgAnimationRefPtr vgAnimationRefPtr, float f, float f2);

    public static final native void VgAnimationRefPtr_start__SWIG_3(long j, VgAnimationRefPtr vgAnimationRefPtr, float f);

    public static final native void VgAnimationRefPtr_start__SWIG_4(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgAnimationRefPtr_stop(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native int VgAnimationRefPtr_unref(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native long VgAnimation_SWIGUpcast(long j);

    public static final native float VgAnimation_getCursor(long j, VgAnimation vgAnimation);

    public static final native float VgAnimation_getDuration(long j, VgAnimation vgAnimation);

    public static final native boolean VgAnimation_isPlaying(long j, VgAnimation vgAnimation);

    public static final native void VgAnimation_pause(long j, VgAnimation vgAnimation);

    public static final native void VgAnimation_seek(long j, VgAnimation vgAnimation, float f);

    public static final native void VgAnimation_start__SWIG_0(long j, VgAnimation vgAnimation, float f, float f2, float f3, float f4);

    public static final native void VgAnimation_start__SWIG_1(long j, VgAnimation vgAnimation, float f, float f2, float f3);

    public static final native void VgAnimation_start__SWIG_2(long j, VgAnimation vgAnimation, float f, float f2);

    public static final native void VgAnimation_start__SWIG_3(long j, VgAnimation vgAnimation, float f);

    public static final native void VgAnimation_start__SWIG_4(long j, VgAnimation vgAnimation);

    public static final native void VgAnimation_stop(long j, VgAnimation vgAnimation);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorConstRefPtr___deref__(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorConstRefPtr___ref__(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_get(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_getNbReferences */
    public static final native int m1187x1750cdd7(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_isValid(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native float[] VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mAxis_get(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native boolean VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mCubic_get(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mEndAngle_get */
    public static final native float m1188x155181a1(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mEndTime_get */
    public static final native float m1189x4422244d(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mPreOrientation_get */
    public static final native long m1190x6cbf6016(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mStartAngle_get */
    public static final native float m1191x680b017a(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_mStartTime_get */
    public static final native float m1192x4bcc554(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_ref(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_set(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr, long j2, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native int VgAxialRotationQuaternionFunctorDescriptorConstRefPtr_unref(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorRefPtr___deref__(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorRefPtr___ref__(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorRefPtr_create(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorRefPtr_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native int VgAxialRotationQuaternionFunctorDescriptorRefPtr_getNbReferences(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgAxialRotationQuaternionFunctorDescriptorRefPtr_isValid(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native float[] VgAxialRotationQuaternionFunctorDescriptorRefPtr_mAxis_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_mAxis_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, float[] fArr);

    public static final native boolean VgAxialRotationQuaternionFunctorDescriptorRefPtr_mCubic_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_mCubic_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, boolean z);

    public static final native float VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndAngle_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndAngle_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, float f);

    public static final native float VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndTime_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_mEndTime_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, float f);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorRefPtr_mPreOrientation_get */
    public static final native long m1193xefa39147(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgAxialRotationQuaternionFunctorDescriptorRefPtr_mPreOrientation_set */
    public static final native void m1194xefa3be53(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, long j2, VgOrientation vgOrientation);

    public static final native float VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartAngle_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartAngle_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, float f);

    public static final native float VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartTime_get(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_mStartTime_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, float f);

    public static final native void VgAxialRotationQuaternionFunctorDescriptorRefPtr_ref(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptorRefPtr_set(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr, long j2, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native int VgAxialRotationQuaternionFunctorDescriptorRefPtr_unref(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgAxialRotationQuaternionFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgAxialRotationQuaternionFunctorDescriptor_create();

    public static final native float[] VgAxialRotationQuaternionFunctorDescriptor_mAxis_get(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native void VgAxialRotationQuaternionFunctorDescriptor_mAxis_set(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor, float[] fArr);

    public static final native boolean VgAxialRotationQuaternionFunctorDescriptor_mCubic_get(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native void VgAxialRotationQuaternionFunctorDescriptor_mCubic_set(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor, boolean z);

    public static final native float VgAxialRotationQuaternionFunctorDescriptor_mEndAngle_get(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native void VgAxialRotationQuaternionFunctorDescriptor_mEndAngle_set(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor, float f);

    public static final native long VgAxialRotationQuaternionFunctorDescriptor_mPreOrientation_get(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native void VgAxialRotationQuaternionFunctorDescriptor_mPreOrientation_set(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor, long j2, VgOrientation vgOrientation);

    public static final native float VgAxialRotationQuaternionFunctorDescriptor_mStartAngle_get(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native void VgAxialRotationQuaternionFunctorDescriptor_mStartAngle_set(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor, float f);

    public static final native long VgBinaryBufferConstRefPtr___deref__(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgBinaryBufferConstRefPtr___ref__(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgBinaryBufferConstRefPtr_get(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native String VgBinaryBufferConstRefPtr_getData(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgBinaryBufferConstRefPtr_getLength(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native int VgBinaryBufferConstRefPtr_getNbReferences(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgBinaryBufferConstRefPtr_getNull();

    public static final native boolean VgBinaryBufferConstRefPtr_isValid(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native void VgBinaryBufferConstRefPtr_ref(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgBinaryBufferConstRefPtr_set(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr, long j2, VgBinaryBuffer vgBinaryBuffer);

    public static final native int VgBinaryBufferConstRefPtr_unref(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgBinaryBufferRefPtr___deref__(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long VgBinaryBufferRefPtr___ref__(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long VgBinaryBufferRefPtr_get(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native String VgBinaryBufferRefPtr_getData(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long VgBinaryBufferRefPtr_getLength(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native int VgBinaryBufferRefPtr_getNbReferences(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long VgBinaryBufferRefPtr_getNull();

    public static final native boolean VgBinaryBufferRefPtr_isValid(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native void VgBinaryBufferRefPtr_ref(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long VgBinaryBufferRefPtr_set(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr, long j2, VgBinaryBuffer vgBinaryBuffer);

    public static final native int VgBinaryBufferRefPtr_unref(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long VgBinaryBuffer_SWIGUpcast(long j);

    public static final native String VgBinaryBuffer_getData(long j, VgBinaryBuffer vgBinaryBuffer);

    public static final native long VgBinaryBuffer_getLength(long j, VgBinaryBuffer vgBinaryBuffer);

    public static final native void VgColorVector_add(long j, VgColorVector vgColorVector, long j2, VgColor vgColor);

    public static final native long VgColorVector_capacity(long j, VgColorVector vgColorVector);

    public static final native void VgColorVector_clear(long j, VgColorVector vgColorVector);

    public static final native long VgColorVector_get(long j, VgColorVector vgColorVector, int i);

    public static final native boolean VgColorVector_isEmpty(long j, VgColorVector vgColorVector);

    public static final native void VgColorVector_reserve(long j, VgColorVector vgColorVector, long j2);

    public static final native void VgColorVector_set(long j, VgColorVector vgColorVector, int i, long j2, VgColor vgColor);

    public static final native long VgColorVector_size(long j, VgColorVector vgColorVector);

    public static final native float VgColor_mAlpha_get(long j, VgColor vgColor);

    public static final native void VgColor_mAlpha_set(long j, VgColor vgColor, float f);

    public static final native float VgColor_mBlue_get(long j, VgColor vgColor);

    public static final native void VgColor_mBlue_set(long j, VgColor vgColor, float f);

    public static final native float VgColor_mGreen_get(long j, VgColor vgColor);

    public static final native void VgColor_mGreen_set(long j, VgColor vgColor, float f);

    public static final native float VgColor_mRed_get(long j, VgColor vgColor);

    public static final native void VgColor_mRed_set(long j, VgColor vgColor, float f);

    public static final native long VgDiscreteQuaternionFunctorDescriptorConstRefPtr___deref__(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorConstRefPtr___ref__(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorConstRefPtr_get(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native int VgDiscreteQuaternionFunctorDescriptorConstRefPtr_getNbReferences(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgDiscreteQuaternionFunctorDescriptorConstRefPtr_isValid(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native float VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mFinalTimestamp_get */
    public static final native double m1195xe64e4e89(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mInitialTimestamp_get */
    public static final native double m1196x4723f2fb(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mOrientationValues_get */
    public static final native long m1197x5d92efb7(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native float VgDiscreteQuaternionFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native void VgDiscreteQuaternionFunctorDescriptorConstRefPtr_ref(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorConstRefPtr_set(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr, long j2, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native int VgDiscreteQuaternionFunctorDescriptorConstRefPtr_unref(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorRefPtr___deref__(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorRefPtr___ref__(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorRefPtr_create(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorRefPtr_get(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native int VgDiscreteQuaternionFunctorDescriptorRefPtr_getNbReferences(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgDiscreteQuaternionFunctorDescriptorRefPtr_isValid(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native float VgDiscreteQuaternionFunctorDescriptorRefPtr_mEndTime_get(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native void VgDiscreteQuaternionFunctorDescriptorRefPtr_mEndTime_set(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr, float f);

    public static final native double VgDiscreteQuaternionFunctorDescriptorRefPtr_mFinalTimestamp_get(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native void VgDiscreteQuaternionFunctorDescriptorRefPtr_mFinalTimestamp_set(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr, double d);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorRefPtr_mInitialTimestamp_get */
    public static final native double m1198x22c10a2c(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorRefPtr_mInitialTimestamp_set */
    public static final native void m1199x22c13738(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr, double d);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorRefPtr_mOrientationValues_get */
    public static final native long m1200xf598bea6(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgDiscreteQuaternionFunctorDescriptorRefPtr_mOrientationValues_set */
    public static final native void m1201xf598ebb2(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr, long j2, VgOrientationValuePairVector vgOrientationValuePairVector);

    public static final native float VgDiscreteQuaternionFunctorDescriptorRefPtr_mStartTime_get(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native void VgDiscreteQuaternionFunctorDescriptorRefPtr_mStartTime_set(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr, float f);

    public static final native void VgDiscreteQuaternionFunctorDescriptorRefPtr_ref(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptorRefPtr_set(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr, long j2, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native int VgDiscreteQuaternionFunctorDescriptorRefPtr_unref(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long VgDiscreteQuaternionFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgDiscreteQuaternionFunctorDescriptor_create();

    public static final native double VgDiscreteQuaternionFunctorDescriptor_mFinalTimestamp_get(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native void VgDiscreteQuaternionFunctorDescriptor_mFinalTimestamp_set(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor, double d);

    public static final native double VgDiscreteQuaternionFunctorDescriptor_mInitialTimestamp_get(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native void VgDiscreteQuaternionFunctorDescriptor_mInitialTimestamp_set(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor, double d);

    public static final native long VgDiscreteQuaternionFunctorDescriptor_mOrientationValues_get(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native void VgDiscreteQuaternionFunctorDescriptor_mOrientationValues_set(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor, long j2, VgOrientationValuePairVector vgOrientationValuePairVector);

    public static final native long VgDiscreteVectorFunctorDescriptorConstRefPtr___deref__(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorConstRefPtr___ref__(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorConstRefPtr_get(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native int VgDiscreteVectorFunctorDescriptorConstRefPtr_getNbReferences(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgDiscreteVectorFunctorDescriptorConstRefPtr_isValid(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native float VgDiscreteVectorFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native double VgDiscreteVectorFunctorDescriptorConstRefPtr_mFinalTimestamp_get(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    /* renamed from: VgDiscreteVectorFunctorDescriptorConstRefPtr_mInitialTimestamp_get */
    public static final native double m1202x75ba2ae0(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorConstRefPtr_mLocationValues_get(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native float VgDiscreteVectorFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native void VgDiscreteVectorFunctorDescriptorConstRefPtr_ref(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorConstRefPtr_set(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr, long j2, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native int VgDiscreteVectorFunctorDescriptorConstRefPtr_unref(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr___deref__(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr___ref__(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr_create(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr_get(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native int VgDiscreteVectorFunctorDescriptorRefPtr_getNbReferences(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgDiscreteVectorFunctorDescriptorRefPtr_isValid(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native float VgDiscreteVectorFunctorDescriptorRefPtr_mEndTime_get(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native void VgDiscreteVectorFunctorDescriptorRefPtr_mEndTime_set(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr, float f);

    public static final native double VgDiscreteVectorFunctorDescriptorRefPtr_mFinalTimestamp_get(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native void VgDiscreteVectorFunctorDescriptorRefPtr_mFinalTimestamp_set(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr, double d);

    public static final native double VgDiscreteVectorFunctorDescriptorRefPtr_mInitialTimestamp_get(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native void VgDiscreteVectorFunctorDescriptorRefPtr_mInitialTimestamp_set(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr, double d);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr_mLocationValues_get(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native void VgDiscreteVectorFunctorDescriptorRefPtr_mLocationValues_set(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr, long j2, VgLocationValuePairVector vgLocationValuePairVector);

    public static final native float VgDiscreteVectorFunctorDescriptorRefPtr_mStartTime_get(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native void VgDiscreteVectorFunctorDescriptorRefPtr_mStartTime_set(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr, float f);

    public static final native void VgDiscreteVectorFunctorDescriptorRefPtr_ref(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptorRefPtr_set(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr, long j2, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native int VgDiscreteVectorFunctorDescriptorRefPtr_unref(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long VgDiscreteVectorFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgDiscreteVectorFunctorDescriptor_create();

    public static final native double VgDiscreteVectorFunctorDescriptor_mFinalTimestamp_get(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native void VgDiscreteVectorFunctorDescriptor_mFinalTimestamp_set(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor, double d);

    public static final native double VgDiscreteVectorFunctorDescriptor_mInitialTimestamp_get(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native void VgDiscreteVectorFunctorDescriptor_mInitialTimestamp_set(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor, double d);

    public static final native long VgDiscreteVectorFunctorDescriptor_mLocationValues_get(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native void VgDiscreteVectorFunctorDescriptor_mLocationValues_set(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor, long j2, VgLocationValuePairVector vgLocationValuePairVector);

    public static final native void VgDoubleVector_add(long j, VgDoubleVector vgDoubleVector, double d);

    public static final native long VgDoubleVector_capacity(long j, VgDoubleVector vgDoubleVector);

    public static final native void VgDoubleVector_clear(long j, VgDoubleVector vgDoubleVector);

    public static final native double VgDoubleVector_get(long j, VgDoubleVector vgDoubleVector, int i);

    public static final native boolean VgDoubleVector_isEmpty(long j, VgDoubleVector vgDoubleVector);

    public static final native void VgDoubleVector_reserve(long j, VgDoubleVector vgDoubleVector, long j2);

    public static final native void VgDoubleVector_set(long j, VgDoubleVector vgDoubleVector, int i, double d);

    public static final native long VgDoubleVector_size(long j, VgDoubleVector vgDoubleVector);

    public static final native long VgFloatInterpolationFunctorDescriptorConstRefPtr___deref__(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorConstRefPtr___ref__(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorConstRefPtr_get(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native int VgFloatInterpolationFunctorDescriptorConstRefPtr_getNbReferences(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgFloatInterpolationFunctorDescriptorConstRefPtr_isValid(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgFloatInterpolationFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgFloatInterpolationFunctorDescriptorConstRefPtr_mEndValue_get(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgFloatInterpolationFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgFloatInterpolationFunctorDescriptorConstRefPtr_mStartValue_get(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native void VgFloatInterpolationFunctorDescriptorConstRefPtr_ref(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorConstRefPtr_set(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr, long j2, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor);

    public static final native int VgFloatInterpolationFunctorDescriptorConstRefPtr_unref(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorRefPtr___deref__(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorRefPtr___ref__(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorRefPtr_create(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorRefPtr_get(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native int VgFloatInterpolationFunctorDescriptorRefPtr_getNbReferences(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgFloatInterpolationFunctorDescriptorRefPtr_isValid(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native float VgFloatInterpolationFunctorDescriptorRefPtr_mEndTime_get(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native void VgFloatInterpolationFunctorDescriptorRefPtr_mEndTime_set(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr, float f);

    public static final native float VgFloatInterpolationFunctorDescriptorRefPtr_mEndValue_get(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native void VgFloatInterpolationFunctorDescriptorRefPtr_mEndValue_set(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr, float f);

    public static final native float VgFloatInterpolationFunctorDescriptorRefPtr_mStartTime_get(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native void VgFloatInterpolationFunctorDescriptorRefPtr_mStartTime_set(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr, float f);

    public static final native float VgFloatInterpolationFunctorDescriptorRefPtr_mStartValue_get(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native void VgFloatInterpolationFunctorDescriptorRefPtr_mStartValue_set(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr, float f);

    public static final native void VgFloatInterpolationFunctorDescriptorRefPtr_ref(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptorRefPtr_set(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr, long j2, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor);

    public static final native int VgFloatInterpolationFunctorDescriptorRefPtr_unref(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long VgFloatInterpolationFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgFloatInterpolationFunctorDescriptor_create();

    public static final native float VgFloatInterpolationFunctorDescriptor_mEndValue_get(long j, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor);

    public static final native void VgFloatInterpolationFunctorDescriptor_mEndValue_set(long j, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor, float f);

    public static final native float VgFloatInterpolationFunctorDescriptor_mStartValue_get(long j, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor);

    public static final native void VgFloatInterpolationFunctorDescriptor_mStartValue_set(long j, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor, float f);

    public static final native boolean VgFontManager_setFontDpi(long j, VgFontManager vgFontManager, long j2);

    public static final native boolean VgFontManager_setFont__SWIG_0(long j, VgFontManager vgFontManager, String str);

    public static final native boolean VgFontManager_setFont__SWIG_1(long j, VgFontManager vgFontManager, String str, long j2);

    public static final native long VgFunctorDescriptorConstRefPtr___deref__(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native long VgFunctorDescriptorConstRefPtr___ref__(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native long VgFunctorDescriptorConstRefPtr_get(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native int VgFunctorDescriptorConstRefPtr_getNbReferences(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native long VgFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgFunctorDescriptorConstRefPtr_isValid(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native float VgFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native float VgFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native void VgFunctorDescriptorConstRefPtr_ref(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native long VgFunctorDescriptorConstRefPtr_set(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr, long j2, VgFunctorDescriptor vgFunctorDescriptor);

    public static final native int VgFunctorDescriptorConstRefPtr_unref(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native long VgFunctorDescriptorMapEntry_mChannel_get(long j, VgFunctorDescriptorMapEntry vgFunctorDescriptorMapEntry);

    public static final native void VgFunctorDescriptorMapEntry_mChannel_set(long j, VgFunctorDescriptorMapEntry vgFunctorDescriptorMapEntry, long j2, VgStringPair vgStringPair);

    public static final native long VgFunctorDescriptorMapEntry_mFunctorDescriptor_get(long j, VgFunctorDescriptorMapEntry vgFunctorDescriptorMapEntry);

    public static final native void VgFunctorDescriptorMapEntry_mFunctorDescriptor_set(long j, VgFunctorDescriptorMapEntry vgFunctorDescriptorMapEntry, long j2, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptorMap_get(long j, VgFunctorDescriptorMap vgFunctorDescriptorMap, long j2, VgStringPair vgStringPair);

    public static final native void VgFunctorDescriptorMap_set(long j, VgFunctorDescriptorMap vgFunctorDescriptorMap, long j2, VgStringPair vgStringPair, long j3, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptorRefPtr___deref__(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptorRefPtr___ref__(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptorRefPtr_get(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native int VgFunctorDescriptorRefPtr_getNbReferences(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgFunctorDescriptorRefPtr_isValid(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native float VgFunctorDescriptorRefPtr_mEndTime_get(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native void VgFunctorDescriptorRefPtr_mEndTime_set(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr, float f);

    public static final native float VgFunctorDescriptorRefPtr_mStartTime_get(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native void VgFunctorDescriptorRefPtr_mStartTime_set(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr, float f);

    public static final native void VgFunctorDescriptorRefPtr_ref(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptorRefPtr_set(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr, long j2, VgFunctorDescriptor vgFunctorDescriptor);

    public static final native int VgFunctorDescriptorRefPtr_unref(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long VgFunctorDescriptor_SWIGUpcast(long j);

    public static final native float VgFunctorDescriptor_mEndTime_get(long j, VgFunctorDescriptor vgFunctorDescriptor);

    public static final native void VgFunctorDescriptor_mEndTime_set(long j, VgFunctorDescriptor vgFunctorDescriptor, float f);

    public static final native float VgFunctorDescriptor_mStartTime_get(long j, VgFunctorDescriptor vgFunctorDescriptor);

    public static final native void VgFunctorDescriptor_mStartTime_set(long j, VgFunctorDescriptor vgFunctorDescriptor, float f);

    public static final native long VgGeoReferencedSRSConstRefPtr___deref__(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native long VgGeoReferencedSRSConstRefPtr___ref__(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native long VgGeoReferencedSRSConstRefPtr_get(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native int VgGeoReferencedSRSConstRefPtr_getNbReferences(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native long VgGeoReferencedSRSConstRefPtr_getNull();

    public static final native boolean VgGeoReferencedSRSConstRefPtr_isGeoReferenced(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native boolean VgGeoReferencedSRSConstRefPtr_isValid(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native void VgGeoReferencedSRSConstRefPtr_ref(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native long VgGeoReferencedSRSConstRefPtr_set(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr, long j2, VgGeoReferencedSRS vgGeoReferencedSRS);

    public static final native int VgGeoReferencedSRSConstRefPtr_unref(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native long VgGeoReferencedSRSRefPtr___deref__(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long VgGeoReferencedSRSRefPtr___ref__(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long VgGeoReferencedSRSRefPtr_get(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native int VgGeoReferencedSRSRefPtr_getNbReferences(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long VgGeoReferencedSRSRefPtr_getNull();

    public static final native boolean VgGeoReferencedSRSRefPtr_isGeoReferenced(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native boolean VgGeoReferencedSRSRefPtr_isValid(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native void VgGeoReferencedSRSRefPtr_ref(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long VgGeoReferencedSRSRefPtr_set(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr, long j2, VgGeoReferencedSRS vgGeoReferencedSRS);

    public static final native int VgGeoReferencedSRSRefPtr_unref(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long VgGeoReferencedSRS_SWIGUpcast(long j);

    public static final native long VgGeometryDescriptor_SWIGUpcast(long j);

    public static final native boolean VgGeometryDescriptor_mDrawOnTop_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mDrawOnTop_set(long j, VgGeometryDescriptor vgGeometryDescriptor, boolean z);

    public static final native boolean VgGeometryDescriptor_mNotifyPOISelectedOnClick_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mNotifyPOISelectedOnClick_set(long j, VgGeometryDescriptor vgGeometryDescriptor, boolean z);

    public static final native float VgGeometryDescriptor_mScale_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mScale_set(long j, VgGeometryDescriptor vgGeometryDescriptor, float f);

    public static final native double VgGeometryDescriptor_mVisibilityRampFullyInvisible_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mVisibilityRampFullyInvisible_set(long j, VgGeometryDescriptor vgGeometryDescriptor, double d);

    public static final native double VgGeometryDescriptor_mVisibilityRampFullyVisible_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mVisibilityRampFullyVisible_set(long j, VgGeometryDescriptor vgGeometryDescriptor, double d);

    public static final native double VgGeometryDescriptor_mVisibilityRampStartInvisible_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mVisibilityRampStartInvisible_set(long j, VgGeometryDescriptor vgGeometryDescriptor, double d);

    public static final native double VgGeometryDescriptor_mVisibilityRampStartVisible_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mVisibilityRampStartVisible_set(long j, VgGeometryDescriptor vgGeometryDescriptor, double d);

    public static final native int VgGeometryDescriptor_mZIndex_get(long j, VgGeometryDescriptor vgGeometryDescriptor);

    public static final native void VgGeometryDescriptor_mZIndex_set(long j, VgGeometryDescriptor vgGeometryDescriptor, int i);

    public static final native long VgI3DModule_SWIGUpcast(long j);

    public static final native long VgI3DModule_editModelManager(long j, VgI3DModule vgI3DModule);

    public static final native long VgIAnimationCallbackConstRefPtr___deref__(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native long VgIAnimationCallbackConstRefPtr___ref__(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native long VgIAnimationCallbackConstRefPtr_get(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native int VgIAnimationCallbackConstRefPtr_getNbReferences(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native long VgIAnimationCallbackConstRefPtr_getNull();

    public static final native boolean VgIAnimationCallbackConstRefPtr_isValid(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native void VgIAnimationCallbackConstRefPtr_ref(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native long VgIAnimationCallbackConstRefPtr_set(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr, long j2, VgIAnimationCallback vgIAnimationCallback);

    public static final native int VgIAnimationCallbackConstRefPtr_unref(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native long VgIAnimationCallbackRefPtr___deref__(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long VgIAnimationCallbackRefPtr___ref__(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long VgIAnimationCallbackRefPtr_get(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native int VgIAnimationCallbackRefPtr_getNbReferences(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long VgIAnimationCallbackRefPtr_getNull();

    public static final native boolean VgIAnimationCallbackRefPtr_isValid(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native void VgIAnimationCallbackRefPtr_onFinish(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgIAnimationCallbackRefPtr_ref(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long VgIAnimationCallbackRefPtr_set(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr, long j2, VgIAnimationCallback vgIAnimationCallback);

    public static final native int VgIAnimationCallbackRefPtr_unref(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long VgIAnimationCallback_SWIGUpcast(long j);

    public static final native void VgIAnimationCallback_change_ownership(VgIAnimationCallback vgIAnimationCallback, long j, boolean z);

    public static final native void VgIAnimationCallback_director_connect(VgIAnimationCallback vgIAnimationCallback, long j, boolean z, boolean z2);

    public static final native void VgIAnimationCallback_onFinish(long j, VgIAnimationCallback vgIAnimationCallback, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgIAnimationManager_setCameraAnimation(long j, VgIAnimationManager vgIAnimationManager, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native long VgIApplication_createApplication();

    public static final native long VgIApplication_createApplicationNOHEAD();

    public static final native long VgIApplication_createApplicationOGL();

    public static final native long VgIApplication_createApplicationOGLES1();

    public static final native long VgIApplication_createApplicationOGLES2();

    public static final native long VgIApplication_edit3dModule(long j, VgIApplication vgIApplication);

    public static final native long VgIApplication_editEngine(long j, VgIApplication vgIApplication);

    public static final native long VgIApplication_editManipulatorManager(long j, VgIApplication vgIApplication);

    public static final native long VgIApplication_editMapModule(long j, VgIApplication vgIApplication);

    public static final native long VgIApplication_editModuleManager(long j, VgIApplication vgIApplication);

    public static final native long VgIApplication_editNavigationModule(long j, VgIApplication vgIApplication);

    public static final native long VgIApplication_editRoutingModule(long j, VgIApplication vgIApplication);

    public static final native double VgICamera_getFovX(long j, VgICamera vgICamera);

    public static final native double VgICamera_getFovY(long j, VgICamera vgICamera);

    public static final native double VgICamera_getHeading(long j, VgICamera vgICamera);

    public static final native double VgICamera_getPitch(long j, VgICamera vgICamera);

    public static final native long VgICamera_getPosition(long j, VgICamera vgICamera);

    public static final native long VgICamera_getViewpoint(long j, VgICamera vgICamera);

    public static final native long VgICamera_getViewpointFromPositions__SWIG_0(long j, VgICamera vgICamera, long j2, VgPositionVector vgPositionVector, long j3, long j4, long j5, long j6);

    public static final native long VgICamera_getViewpointFromPositions__SWIG_1(long j, VgICamera vgICamera, long j2, VgPositionVector vgPositionVector, long j3, long j4, long j5, long j6, double d, double d2);

    public static final native long VgICamera_getViewpointFromPositions__SWIG_2(long j, VgICamera vgICamera, long j2, VgPositionVector vgPositionVector, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4);

    public static final native boolean VgICamera_pickGeographicPoint(long j, VgICamera vgICamera, double d, double d2, long j2, VgPosition vgPosition);

    public static final native void VgICamera_projectOnScreen__SWIG_0(long j, VgICamera vgICamera, long j2, VgPosition vgPosition, double[] dArr, double[] dArr2, double[] dArr3);

    public static final native void VgICamera_projectOnScreen__SWIG_1(long j, VgICamera vgICamera, long j2, VgPosition vgPosition, double[] dArr, double[] dArr2);

    public static final native void VgICamera_setFovX__SWIG_0(long j, VgICamera vgICamera, double d, double d2);

    public static final native void VgICamera_setFovX__SWIG_1(long j, VgICamera vgICamera, double d);

    public static final native void VgICamera_setHeading(long j, VgICamera vgICamera, double d);

    public static final native void VgICamera_setPitch(long j, VgICamera vgICamera, double d);

    public static final native void VgICamera_setPosition(long j, VgICamera vgICamera, long j2, VgPosition vgPosition);

    public static final native void VgICamera_setViewpoint(long j, VgICamera vgICamera, long j2, VgIViewPoint vgIViewPoint);

    public static final native void VgIDatabaseDatasetDescriptorVector_add(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector, long j2, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor);

    public static final native long VgIDatabaseDatasetDescriptorVector_capacity(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector);

    public static final native void VgIDatabaseDatasetDescriptorVector_clear(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector);

    public static final native long VgIDatabaseDatasetDescriptorVector_get(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector, int i);

    public static final native boolean VgIDatabaseDatasetDescriptorVector_isEmpty(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector);

    public static final native void VgIDatabaseDatasetDescriptorVector_reserve(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector, long j2);

    public static final native void VgIDatabaseDatasetDescriptorVector_set(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector, int i, long j2, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor);

    public static final native long VgIDatabaseDatasetDescriptorVector_size(long j, VgIDatabaseDatasetDescriptorVector vgIDatabaseDatasetDescriptorVector);

    public static final native long VgIDatabaseDatasetDescriptor_mMaximum_get(long j, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor);

    public static final native void VgIDatabaseDatasetDescriptor_mMaximum_set(long j, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor, long j2, VgPosition vgPosition);

    public static final native long VgIDatabaseDatasetDescriptor_mMinimum_get(long j, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor);

    public static final native void VgIDatabaseDatasetDescriptor_mMinimum_set(long j, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor, long j2, VgPosition vgPosition);

    public static final native String VgIDatabaseDatasetDescriptor_mName_get(long j, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor);

    public static final native void VgIDatabaseDatasetDescriptor_mName_set(long j, VgIDatabaseDatasetDescriptor vgIDatabaseDatasetDescriptor, String str);

    public static final native boolean VgIDatabase_getCachedLicenseFilenameForConfiguration(long j, VgIDatabase vgIDatabase, String str, String[] strArr);

    public static final native long VgIDatabase_getCurrentDatasetDescriptor(long j, VgIDatabase vgIDatabase);

    public static final native long VgIDatabase_getDatasetDescriptors(long j, VgIDatabase vgIDatabase);

    public static final native boolean VgIDatabase_loadConfiguration__SWIG_0(long j, VgIDatabase vgIDatabase, String str, long j2, String str2);

    public static final native boolean VgIDatabase_loadConfiguration__SWIG_1(long j, VgIDatabase vgIDatabase, String str, long j2);

    public static final native boolean VgIDatabase_selectDataset__SWIG_0(long j, VgIDatabase vgIDatabase, String str);

    public static final native boolean VgIDatabase_selectDataset__SWIG_1(long j, VgIDatabase vgIDatabase, int i);

    public static final native void VgIDatabase_unloadConfiguration(long j, VgIDatabase vgIDatabase);

    public static final native long VgIEnginePostDrawCallbackConstRefPtr___deref__(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native long VgIEnginePostDrawCallbackConstRefPtr___ref__(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native long VgIEnginePostDrawCallbackConstRefPtr_get(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native int VgIEnginePostDrawCallbackConstRefPtr_getNbReferences(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native long VgIEnginePostDrawCallbackConstRefPtr_getNull();

    public static final native boolean VgIEnginePostDrawCallbackConstRefPtr_isValid(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native void VgIEnginePostDrawCallbackConstRefPtr_ref(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native long VgIEnginePostDrawCallbackConstRefPtr_set(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr, long j2, VgIEnginePostDrawCallback vgIEnginePostDrawCallback);

    public static final native int VgIEnginePostDrawCallbackConstRefPtr_unref(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native long VgIEnginePostDrawCallbackRefPtr___deref__(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgIEnginePostDrawCallbackRefPtr___ref__(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgIEnginePostDrawCallbackRefPtr_get(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native int VgIEnginePostDrawCallbackRefPtr_getNbReferences(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgIEnginePostDrawCallbackRefPtr_getNull();

    public static final native boolean VgIEnginePostDrawCallbackRefPtr_isValid(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native void VgIEnginePostDrawCallbackRefPtr_postDraw(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr, long j2, VgIEngineContext vgIEngineContext);

    public static final native void VgIEnginePostDrawCallbackRefPtr_ref(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgIEnginePostDrawCallbackRefPtr_set(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr, long j2, VgIEnginePostDrawCallback vgIEnginePostDrawCallback);

    public static final native void VgIEnginePostDrawCallbackRefPtr_setEngine(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr, long j2, VgIEngine vgIEngine);

    public static final native int VgIEnginePostDrawCallbackRefPtr_unref(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgIEnginePostDrawCallback_SWIGUpcast(long j);

    public static final native void VgIEnginePostDrawCallback_change_ownership(VgIEnginePostDrawCallback vgIEnginePostDrawCallback, long j, boolean z);

    public static final native void VgIEnginePostDrawCallback_director_connect(VgIEnginePostDrawCallback vgIEnginePostDrawCallback, long j, boolean z, boolean z2);

    public static final native void VgIEnginePostDrawCallback_postDraw(long j, VgIEnginePostDrawCallback vgIEnginePostDrawCallback, long j2, VgIEngineContext vgIEngineContext);

    public static final native void VgIEnginePostDrawCallback_setEngine(long j, VgIEnginePostDrawCallback vgIEnginePostDrawCallback, long j2, VgIEngine vgIEngine);

    public static final native void VgIEngine_addPostDrawCallback(long j, VgIEngine vgIEngine, long j2, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgIEngine_editAnimationManager(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editCamera(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editDatabase(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editFontManager(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editInstanceFactory(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editLayerManager(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editLicenseManager(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editLight(long j, VgIEngine vgIEngine, long j2);

    public static final native long VgIEngine_editResourceManager(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_editTextureManager(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_execute(long j, VgIEngine vgIEngine, long j2, VgQuery vgQuery);

    public static final native String VgIEngine_getErrorString(long j, VgIEngine vgIEngine, int i);

    public static final native int VgIEngine_getLastError(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_getLight(long j, VgIEngine vgIEngine, long j2);

    public static final native long VgIEngine_getNumLights(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_getPositionToolbox(long j, VgIEngine vgIEngine);

    public static final native long VgIEngine_getPostDrawCallbacks(long j, VgIEngine vgIEngine);

    public static final native boolean VgIEngine_isLoaded(long j, VgIEngine vgIEngine, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static final native void VgIEngine_reloadShaders(long j, VgIEngine vgIEngine);

    public static final native void VgIEngine_removePostDrawCallback(long j, VgIEngine vgIEngine, long j2, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native void VgIEngine_replaceNamedTexture(long j, VgIEngine vgIEngine, String str, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native void VgIEngine_resetGraphicResources(long j, VgIEngine vgIEngine, boolean z);

    public static final native void VgIEngine_setClearColor(long j, VgIEngine vgIEngine, long j2, VgColor vgColor);

    public static final native int VgIEvent_getType(long j, VgIEvent vgIEvent);

    public static final native long VgIGeometryCallbackConstRefPtr___deref__(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native long VgIGeometryCallbackConstRefPtr___ref__(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native long VgIGeometryCallbackConstRefPtr_get(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native int VgIGeometryCallbackConstRefPtr_getNbReferences(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native long VgIGeometryCallbackConstRefPtr_getNull();

    public static final native boolean VgIGeometryCallbackConstRefPtr_isValid(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native void VgIGeometryCallbackConstRefPtr_ref(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native long VgIGeometryCallbackConstRefPtr_set(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr, long j2, VgIGeometryCallback vgIGeometryCallback);

    public static final native int VgIGeometryCallbackConstRefPtr_unref(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native long VgIGeometryCallbackRefPtr___deref__(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryCallbackRefPtr___ref__(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryCallbackRefPtr_get(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native int VgIGeometryCallbackRefPtr_getNbReferences(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryCallbackRefPtr_getNull();

    public static final native void VgIGeometryCallbackRefPtr_handleGeometryEvent(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr, long j2, VgIGeometryEvent vgIGeometryEvent);

    public static final native boolean VgIGeometryCallbackRefPtr_isValid(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native void VgIGeometryCallbackRefPtr_ref(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryCallbackRefPtr_set(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr, long j2, VgIGeometryCallback vgIGeometryCallback);

    public static final native int VgIGeometryCallbackRefPtr_unref(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryCallback_SWIGUpcast(long j);

    public static final native void VgIGeometryCallback_change_ownership(VgIGeometryCallback vgIGeometryCallback, long j, boolean z);

    public static final native void VgIGeometryCallback_director_connect(VgIGeometryCallback vgIGeometryCallback, long j, boolean z, boolean z2);

    public static final native void VgIGeometryCallback_handleGeometryEvent(long j, VgIGeometryCallback vgIGeometryCallback, long j2, VgIGeometryEvent vgIGeometryEvent);

    public static final native long VgIGeometryConstRefPtr___deref__(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr___ref__(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_get(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_getAnimation(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr, String str);

    public static final native long VgIGeometryConstRefPtr_getAnimationChannelValue(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr, String str);

    public static final native void VgIGeometryConstRefPtr_getAnimationNames(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr, long j2, VgStringList vgStringList);

    public static final native String VgIGeometryConstRefPtr_getID(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_getLocalPosition(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native int VgIGeometryConstRefPtr_getNbReferences(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native boolean VgIGeometryConstRefPtr_getNotifyPOISelectedOnClick(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_getNull();

    public static final native long VgIGeometryConstRefPtr_getOrientation(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_getPosition(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native float VgIGeometryConstRefPtr_getScale(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native int VgIGeometryConstRefPtr_getType(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_getVisibilityRamp(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native int VgIGeometryConstRefPtr_getZIndex(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native boolean VgIGeometryConstRefPtr_isDrawnOnTop(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native boolean VgIGeometryConstRefPtr_isValid(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native boolean VgIGeometryConstRefPtr_isVisible(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native void VgIGeometryConstRefPtr_ref(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryConstRefPtr_set(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr, long j2, VgIGeometry vgIGeometry);

    public static final native int VgIGeometryConstRefPtr_unref(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long VgIGeometryEvent_SWIGUpcast(long j);

    public static final native long VgIGeometryEvent_getGeometry(long j, VgIGeometryEvent vgIGeometryEvent);

    public static final native long VgIGeometryRefPtr___deref__(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr___ref__(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native void VgIGeometryRefPtr_addListener(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryRefPtr_asGeometry(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_asLine(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_asPoint(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_editAnimation(long j, VgIGeometryRefPtr vgIGeometryRefPtr, String str);

    public static final native long VgIGeometryRefPtr_get(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_getAnimation(long j, VgIGeometryRefPtr vgIGeometryRefPtr, String str);

    public static final native long VgIGeometryRefPtr_getAnimationChannelValue(long j, VgIGeometryRefPtr vgIGeometryRefPtr, String str);

    public static final native void VgIGeometryRefPtr_getAnimationNames(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgStringList vgStringList);

    public static final native boolean VgIGeometryRefPtr_getBoundingPositions(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgPositionVector vgPositionVector);

    public static final native String VgIGeometryRefPtr_getID(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_getLayer(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_getLocalPosition(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native int VgIGeometryRefPtr_getNbReferences(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native boolean VgIGeometryRefPtr_getNotifyPOISelectedOnClick(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_getNull();

    public static final native long VgIGeometryRefPtr_getOrientation(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_getPosition(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native float VgIGeometryRefPtr_getScale(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native int VgIGeometryRefPtr_getType(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometryRefPtr_getVisibilityRamp(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native int VgIGeometryRefPtr_getZIndex(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native boolean VgIGeometryRefPtr_isDrawnOnTop(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native boolean VgIGeometryRefPtr_isValid(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native boolean VgIGeometryRefPtr_isVisible(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native void VgIGeometryRefPtr_ref(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native void VgIGeometryRefPtr_removeListener(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometryRefPtr_set(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgIGeometry vgIGeometry);

    public static final native void VgIGeometryRefPtr_setAnimation__SWIG_0(long j, VgIGeometryRefPtr vgIGeometryRefPtr, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgIGeometryRefPtr_setAnimation__SWIG_1(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgIGeometryRefPtr_setDrawOnTop(long j, VgIGeometryRefPtr vgIGeometryRefPtr, boolean z);

    public static final native void VgIGeometryRefPtr_setLayer__SWIG_0(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgIGeometryRefPtr_setLayer__SWIG_1(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgLayerRefPtr vgLayerRefPtr);

    public static final native void VgIGeometryRefPtr_setLocalAnimation(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgIGeometryRefPtr_setLocalPosition(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgIGeometryRefPtr_setNotifyPOISelectedOnClick(long j, VgIGeometryRefPtr vgIGeometryRefPtr, boolean z);

    public static final native void VgIGeometryRefPtr_setOrientation(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgOrientation vgOrientation);

    public static final native void VgIGeometryRefPtr_setPosition__SWIG_0(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgPosition vgPosition, boolean z);

    public static final native void VgIGeometryRefPtr_setPosition__SWIG_1(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgIGeometryRefPtr_setScale(long j, VgIGeometryRefPtr vgIGeometryRefPtr, float f);

    public static final native void VgIGeometryRefPtr_setVisibilityRamp(long j, VgIGeometryRefPtr vgIGeometryRefPtr, long j2, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgIGeometryRefPtr_setVisible(long j, VgIGeometryRefPtr vgIGeometryRefPtr, boolean z);

    public static final native void VgIGeometryRefPtr_setZIndex(long j, VgIGeometryRefPtr vgIGeometryRefPtr, int i);

    public static final native int VgIGeometryRefPtr_unref(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long VgIGeometry_SWIGUpcast(long j);

    public static final native void VgIGeometry_addListener(long j, VgIGeometry vgIGeometry, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgIGeometry_asGeometry(long j, VgIGeometry vgIGeometry);

    public static final native long VgIGeometry_asLine(long j, VgIGeometry vgIGeometry);

    public static final native long VgIGeometry_asPoint(long j, VgIGeometry vgIGeometry);

    public static final native boolean VgIGeometry_getBoundingPositions(long j, VgIGeometry vgIGeometry, long j2, VgPositionVector vgPositionVector);

    public static final native String VgIGeometry_getID(long j, VgIGeometry vgIGeometry);

    public static final native long VgIGeometry_getLayer(long j, VgIGeometry vgIGeometry);

    public static final native long VgIGeometry_getLocalPosition(long j, VgIGeometry vgIGeometry);

    public static final native boolean VgIGeometry_getNotifyPOISelectedOnClick(long j, VgIGeometry vgIGeometry);

    public static final native int VgIGeometry_getType(long j, VgIGeometry vgIGeometry);

    public static final native long VgIGeometry_getVisibilityRamp(long j, VgIGeometry vgIGeometry);

    public static final native void VgIGeometry_removeListener(long j, VgIGeometry vgIGeometry, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native void VgIGeometry_setLayer__SWIG_0(long j, VgIGeometry vgIGeometry, long j2, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgIGeometry_setLayer__SWIG_1(long j, VgIGeometry vgIGeometry, long j2, VgLayerRefPtr vgLayerRefPtr);

    public static final native void VgIGeometry_setLocalPosition(long j, VgIGeometry vgIGeometry, long j2, VgPosition vgPosition);

    public static final native void VgIGeometry_setNotifyPOISelectedOnClick(long j, VgIGeometry vgIGeometry, boolean z);

    public static final native void VgIGeometry_setVisibilityRamp(long j, VgIGeometry vgIGeometry, long j2, VgVisibilityRamp vgVisibilityRamp);

    public static final native String VgILicenseManager_getMachineCode(long j, VgILicenseManager vgILicenseManager);

    public static final native String VgILicenseManager_getRevision(long j, VgILicenseManager vgILicenseManager);

    public static final native String VgILicenseManager_getVersion(long j, VgILicenseManager vgILicenseManager);

    public static final native String VgILicenseManager_staticGetMinimumDataSDKVersion();

    public static final native String VgILicenseManager_staticGetRevision();

    public static final native String VgILicenseManager_staticGetVersion();

    public static final native long VgIManipulatorManager_editManipulatorObject(long j, VgIManipulatorManager vgIManipulatorManager, String str);

    public static final native String VgIManipulatorManager_getCurrentManipulator(long j, VgIManipulatorManager vgIManipulatorManager);

    public static final native boolean VgIManipulatorManager_selectManipulator(long j, VgIManipulatorManager vgIManipulatorManager, String str);

    public static final native long VgIMapModule_SWIGUpcast(long j);

    public static final native void VgIMapModule_addListener(long j, VgIMapModule vgIMapModule, long j2, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native boolean VgIMapModule_getGeofences__SWIG_0(long j, VgIMapModule vgIMapModule, long j2, VgStringVector vgStringVector);

    public static final native boolean VgIMapModule_getGeofences__SWIG_1(long j, VgIMapModule vgIMapModule, long j2, VgStringSet vgStringSet);

    public static final native boolean VgIMapModule_getGeofences__SWIG_2(long j, VgIMapModule vgIMapModule, long j2, VgPosition vgPosition, long j3, VgStringVector vgStringVector);

    public static final native boolean VgIMapModule_getGeofences__SWIG_3(long j, VgIMapModule vgIMapModule, long j2, VgPosition vgPosition, String str, long j3, VgStringVector vgStringVector);

    public static final native boolean VgIMapModule_getHeightRangeForLayer(long j, VgIMapModule vgIMapModule, String str, float[] fArr, float[] fArr2);

    public static final native boolean VgIMapModule_getLayerForPosition(long j, VgIMapModule vgIMapModule, long j2, VgPosition vgPosition, String[] strArr);

    public static final native boolean VgIMapModule_getPlaceName(long j, VgIMapModule vgIMapModule, String str, String[] strArr);

    public static final native void VgIMapModule_queryAllPlaceIDs(long j, VgIMapModule vgIMapModule, long j2, VgStringVector vgStringVector);

    public static final native boolean VgIMapModule_queryPOIDescriptor(long j, VgIMapModule vgIMapModule, String str, long j2, VgPOIDescriptor vgPOIDescriptor);

    public static final native boolean VgIMapModule_queryPlaceDescriptor(long j, VgIMapModule vgIMapModule, String str, long j2, VgPlaceDescriptor vgPlaceDescriptor);

    public static final native void VgIMapModule_removeListener(long j, VgIMapModule vgIMapModule, long j2, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native void VgIMapModule_resetPlaceColor__SWIG_0(long j, VgIMapModule vgIMapModule, String str);

    public static final native void VgIMapModule_resetPlaceColor__SWIG_1(long j, VgIMapModule vgIMapModule, long j2, VgStringVector vgStringVector);

    public static final native void VgIMapModule_setPlaceColor__SWIG_0(long j, VgIMapModule vgIMapModule, String str, long j2, VgPlaceColorDescriptor vgPlaceColorDescriptor);

    public static final native void VgIMapModule_setPlaceColor__SWIG_1(long j, VgIMapModule vgIMapModule, long j2, VgStringVector vgStringVector, long j3, VgPlaceColorDescriptor vgPlaceColorDescriptor);

    public static final native void VgIMapModule_setPlaceIcon(long j, VgIMapModule vgIMapModule, String str, long j2, VgPlaceIconDescriptor vgPlaceIconDescriptor);

    public static final native void VgIMapModule_setPlaceName(long j, VgIMapModule vgIMapModule, String str, String str2);

    public static final native long VgIModuleManager_queryModule(long j, VgIModuleManager vgIModuleManager, String str);

    public static final native String VgIModule_getName(long j, VgIModule vgIModule);

    public static final native long VgINavigationCallbackConstRefPtr___deref__(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native long VgINavigationCallbackConstRefPtr___ref__(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native long VgINavigationCallbackConstRefPtr_get(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native int VgINavigationCallbackConstRefPtr_getNbReferences(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native long VgINavigationCallbackConstRefPtr_getNull();

    public static final native boolean VgINavigationCallbackConstRefPtr_isValid(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native void VgINavigationCallbackConstRefPtr_ref(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native long VgINavigationCallbackConstRefPtr_set(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr, long j2, VgINavigationCallback vgINavigationCallback);

    public static final native int VgINavigationCallbackConstRefPtr_unref(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native long VgINavigationCallbackRefPtr___deref__(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long VgINavigationCallbackRefPtr___ref__(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long VgINavigationCallbackRefPtr_get(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native int VgINavigationCallbackRefPtr_getNbReferences(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long VgINavigationCallbackRefPtr_getNull();

    public static final native boolean VgINavigationCallbackRefPtr_isValid(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native boolean VgINavigationCallbackRefPtr_notifyNavigationComputed(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr, int i, long j2, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native void VgINavigationCallbackRefPtr_ref(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long VgINavigationCallbackRefPtr_set(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr, long j2, VgINavigationCallback vgINavigationCallback);

    public static final native int VgINavigationCallbackRefPtr_unref(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long VgINavigationCallback_SWIGUpcast(long j);

    public static final native void VgINavigationCallback_change_ownership(VgINavigationCallback vgINavigationCallback, long j, boolean z);

    public static final native void VgINavigationCallback_director_connect(VgINavigationCallback vgINavigationCallback, long j, boolean z, boolean z2);

    public static final native boolean VgINavigationCallback_notifyNavigationComputed(long j, VgINavigationCallback vgINavigationCallback, int i, long j2, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationConstRefPtr___deref__(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr___ref__(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_get(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_getClosestPositionOnRoute(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    /* renamed from: VgINavigationConstRefPtr_getCurrentInstructionClosestPositionNextSubIndex */
    public static final native long m1203x64ae4d69(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_getCurrentInstructionIndex(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_getCurrentPosition(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native double VgINavigationConstRefPtr_getDistanceFromRoute(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_getInstruction(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr, long j2);

    public static final native double VgINavigationConstRefPtr_getInstructionGeofenceDistance(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native int VgINavigationConstRefPtr_getNbReferences(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_getNull();

    public static final native long VgINavigationConstRefPtr_getNumInstructions(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_getRequestParameters(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native boolean VgINavigationConstRefPtr_isValid(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native void VgINavigationConstRefPtr_ref(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationConstRefPtr_set(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr, long j2, VgINavigation vgINavigation);

    public static final native int VgINavigationConstRefPtr_unref(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr___deref__(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr___ref__(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_get(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_getAttributes(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_getDestinationIndex(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native float VgINavigationInstructionConstRefPtr_getDuration(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native float VgINavigationInstructionConstRefPtr_getETA(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native float VgINavigationInstructionConstRefPtr_getHeight(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_getIndex(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_getInstructionPositions(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native String VgINavigationInstructionConstRefPtr_getLayer(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native double VgINavigationInstructionConstRefPtr_getLength(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native int VgINavigationInstructionConstRefPtr_getManeuverType(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native String VgINavigationInstructionConstRefPtr_getModality(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native int VgINavigationInstructionConstRefPtr_getNbReferences(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_getNearPlaces(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_getNull();

    public static final native long VgINavigationInstructionConstRefPtr_getPosition(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native float VgINavigationInstructionConstRefPtr_getTime(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native float VgINavigationInstructionConstRefPtr_getTotalTime(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native boolean VgINavigationInstructionConstRefPtr_isEndOrTransitionPoint(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native boolean VgINavigationInstructionConstRefPtr_isValid(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native void VgINavigationInstructionConstRefPtr_ref(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionConstRefPtr_set(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr, long j2, VgINavigationInstruction vgINavigationInstruction);

    public static final native int VgINavigationInstructionConstRefPtr_unref(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long VgINavigationInstructionRefPtr___deref__(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr___ref__(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_get(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_getAttributes(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_getDestinationIndex(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native float VgINavigationInstructionRefPtr_getDuration(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native float VgINavigationInstructionRefPtr_getETA(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native float VgINavigationInstructionRefPtr_getHeight(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_getIndex(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_getInstructionPositions(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native String VgINavigationInstructionRefPtr_getLayer(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native double VgINavigationInstructionRefPtr_getLength(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native int VgINavigationInstructionRefPtr_getManeuverType(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native String VgINavigationInstructionRefPtr_getModality(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native int VgINavigationInstructionRefPtr_getNbReferences(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_getNearPlaces(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_getNull();

    public static final native long VgINavigationInstructionRefPtr_getPosition(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native float VgINavigationInstructionRefPtr_getTime(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native float VgINavigationInstructionRefPtr_getTotalTime(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native boolean VgINavigationInstructionRefPtr_isEndOrTransitionPoint(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native boolean VgINavigationInstructionRefPtr_isValid(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native void VgINavigationInstructionRefPtr_ref(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstructionRefPtr_set(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr, long j2, VgINavigationInstruction vgINavigationInstruction);

    public static final native int VgINavigationInstructionRefPtr_unref(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long VgINavigationInstruction_SWIGUpcast(long j);

    public static final native long VgINavigationInstruction_getAttributes(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long VgINavigationInstruction_getDestinationIndex(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native float VgINavigationInstruction_getDuration(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native float VgINavigationInstruction_getETA(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native float VgINavigationInstruction_getHeight(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long VgINavigationInstruction_getIndex(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long VgINavigationInstruction_getInstructionPositions(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native String VgINavigationInstruction_getLayer(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native double VgINavigationInstruction_getLength(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native int VgINavigationInstruction_getManeuverType(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native String VgINavigationInstruction_getModality(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long VgINavigationInstruction_getNearPlaces(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long VgINavigationInstruction_getPosition(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native float VgINavigationInstruction_getTime(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native float VgINavigationInstruction_getTotalTime(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native boolean VgINavigationInstruction_isEndOrTransitionPoint(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long VgINavigationListenerConstRefPtr___deref__(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native long VgINavigationListenerConstRefPtr___ref__(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native long VgINavigationListenerConstRefPtr_get(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native int VgINavigationListenerConstRefPtr_getNbReferences(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native long VgINavigationListenerConstRefPtr_getNull();

    public static final native boolean VgINavigationListenerConstRefPtr_isValid(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native void VgINavigationListenerConstRefPtr_ref(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native long VgINavigationListenerConstRefPtr_set(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr, long j2, VgINavigationListener vgINavigationListener);

    public static final native int VgINavigationListenerConstRefPtr_unref(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native long VgINavigationListenerRefPtr___deref__(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationListenerRefPtr___ref__(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationListenerRefPtr_get(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native int VgINavigationListenerRefPtr_getNbReferences(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationListenerRefPtr_getNull();

    public static final native boolean VgINavigationListenerRefPtr_isValid(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native void VgINavigationListenerRefPtr_notifyNewInstruction(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr, long j2, VgINavigationConstRefPtr vgINavigationConstRefPtr, long j3);

    public static final native void VgINavigationListenerRefPtr_notifyPositionUpdated(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr, long j2, VgINavigationConstRefPtr vgINavigationConstRefPtr, long j3, VgPosition vgPosition, double d);

    public static final native void VgINavigationListenerRefPtr_ref(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationListenerRefPtr_set(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr, long j2, VgINavigationListener vgINavigationListener);

    public static final native int VgINavigationListenerRefPtr_unref(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationListener_SWIGUpcast(long j);

    public static final native void VgINavigationListener_change_ownership(VgINavigationListener vgINavigationListener, long j, boolean z);

    public static final native void VgINavigationListener_director_connect(VgINavigationListener vgINavigationListener, long j, boolean z, boolean z2);

    public static final native void VgINavigationListener_notifyNewInstruction(long j, VgINavigationListener vgINavigationListener, long j2, VgINavigationConstRefPtr vgINavigationConstRefPtr, long j3);

    public static final native void VgINavigationListener_notifyPositionUpdated(long j, VgINavigationListener vgINavigationListener, long j2, VgINavigationConstRefPtr vgINavigationConstRefPtr, long j3, VgPosition vgPosition, double d);

    public static final native long VgINavigationModule_SWIGUpcast(long j);

    public static final native long VgINavigationModule_computeNavigation(long j, VgINavigationModule vgINavigationModule, long j2, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native long VgINavigationModule_computeNavigationDirect(long j, VgINavigationModule vgINavigationModule, long j2, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native long VgINavigationModule_queryNearPlaces(long j, VgINavigationModule vgINavigationModule, long j2, VgPosition vgPosition, long j3, VgNearPlacesParameters vgNearPlacesParameters);

    public static final native long VgINavigationRefPtr___deref__(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr___ref__(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native void VgINavigationRefPtr_addListener(long j, VgINavigationRefPtr vgINavigationRefPtr, long j2, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationRefPtr_get(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr_getClosestPositionOnRoute(long j, VgINavigationRefPtr vgINavigationRefPtr);

    /* renamed from: VgINavigationRefPtr_getCurrentInstructionClosestPositionNextSubIndex */
    public static final native long m1204x270a58f4(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr_getCurrentInstructionIndex(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr_getCurrentPosition(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native double VgINavigationRefPtr_getDistanceFromRoute(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr_getInstruction(long j, VgINavigationRefPtr vgINavigationRefPtr, long j2);

    public static final native double VgINavigationRefPtr_getInstructionGeofenceDistance(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native int VgINavigationRefPtr_getNbReferences(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr_getNull();

    public static final native long VgINavigationRefPtr_getNumInstructions(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long VgINavigationRefPtr_getRequestParameters(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native boolean VgINavigationRefPtr_isValid(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native void VgINavigationRefPtr_ref(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native void VgINavigationRefPtr_removeListener(long j, VgINavigationRefPtr vgINavigationRefPtr, long j2, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigationRefPtr_set(long j, VgINavigationRefPtr vgINavigationRefPtr, long j2, VgINavigation vgINavigation);

    public static final native void VgINavigationRefPtr_setInstructionGeofenceDistance(long j, VgINavigationRefPtr vgINavigationRefPtr, double d);

    public static final native int VgINavigationRefPtr_unref(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native void VgINavigationRefPtr_updateCurrentPosition(long j, VgINavigationRefPtr vgINavigationRefPtr, long j2, VgPosition vgPosition, double d);

    public static final native int VgINavigationRequestParameters_mAlgorithm_get(long j, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native void VgINavigationRequestParameters_mAlgorithm_set(long j, VgINavigationRequestParameters vgINavigationRequestParameters, int i);

    public static final native long VgINavigationRequestParameters_mCallback_get(long j, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native void VgINavigationRequestParameters_mCallback_set(long j, VgINavigationRequestParameters vgINavigationRequestParameters, long j2, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native boolean VgINavigationRequestParameters_mFirstNodeAsIntersection_get(long j, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native void VgINavigationRequestParameters_mFirstNodeAsIntersection_set(long j, VgINavigationRequestParameters vgINavigationRequestParameters, boolean z);

    public static final native boolean VgINavigationRequestParameters_mMergeFloorChangeInstructions_get(long j, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native void VgINavigationRequestParameters_mMergeFloorChangeInstructions_set(long j, VgINavigationRequestParameters vgINavigationRequestParameters, boolean z);

    public static final native long VgINavigationRequestParameters_mModalityParameters_get(long j, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native void VgINavigationRequestParameters_mModalityParameters_set(long j, VgINavigationRequestParameters vgINavigationRequestParameters, long j2, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap);

    public static final native long VgINavigationRequestParameters_mRoute_get(long j, VgINavigationRequestParameters vgINavigationRequestParameters);

    public static final native void VgINavigationRequestParameters_mRoute_set(long j, VgINavigationRequestParameters vgINavigationRequestParameters, long j2, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native void VgINavigationRequest_cancel(long j, VgINavigationRequest vgINavigationRequest);

    public static final native long VgINavigation_SWIGUpcast(long j);

    public static final native void VgINavigation_addListener(long j, VgINavigation vgINavigation, long j2, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long VgINavigation_getClosestPositionOnRoute(long j, VgINavigation vgINavigation);

    public static final native long VgINavigation_getCurrentInstructionClosestPositionNextSubIndex(long j, VgINavigation vgINavigation);

    public static final native long VgINavigation_getCurrentInstructionIndex(long j, VgINavigation vgINavigation);

    public static final native long VgINavigation_getCurrentPosition(long j, VgINavigation vgINavigation);

    public static final native double VgINavigation_getDistanceFromRoute(long j, VgINavigation vgINavigation);

    public static final native long VgINavigation_getInstruction(long j, VgINavigation vgINavigation, long j2);

    public static final native double VgINavigation_getInstructionGeofenceDistance(long j, VgINavigation vgINavigation);

    public static final native long VgINavigation_getNumInstructions(long j, VgINavigation vgINavigation);

    public static final native long VgINavigation_getRequestParameters(long j, VgINavigation vgINavigation);

    public static final native void VgINavigation_removeListener(long j, VgINavigation vgINavigation, long j2, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native void VgINavigation_setInstructionGeofenceDistance(long j, VgINavigation vgINavigation, double d);

    public static final native void VgINavigation_updateCurrentPosition(long j, VgINavigation vgINavigation, long j2, VgPosition vgPosition, double d);

    public static final native long VgIPlaceListenerConstRefPtr___deref__(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native long VgIPlaceListenerConstRefPtr___ref__(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native long VgIPlaceListenerConstRefPtr_get(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native int VgIPlaceListenerConstRefPtr_getNbReferences(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native long VgIPlaceListenerConstRefPtr_getNull();

    public static final native boolean VgIPlaceListenerConstRefPtr_isValid(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native void VgIPlaceListenerConstRefPtr_ref(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native long VgIPlaceListenerConstRefPtr_set(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr, long j2, VgIPlaceListener vgIPlaceListener);

    public static final native int VgIPlaceListenerConstRefPtr_unref(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native long VgIPlaceListenerRefPtr___deref__(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long VgIPlaceListenerRefPtr___ref__(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long VgIPlaceListenerRefPtr_get(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native int VgIPlaceListenerRefPtr_getNbReferences(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long VgIPlaceListenerRefPtr_getNull();

    public static final native boolean VgIPlaceListenerRefPtr_isValid(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native void VgIPlaceListenerRefPtr_notifyMapDatabaseLoaded(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr, long j2, VgIApplication vgIApplication);

    public static final native void VgIPlaceListenerRefPtr_notifyPlaceSelected(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr, long j2, VgIApplication vgIApplication, String str, long j3, VgPosition vgPosition);

    public static final native void VgIPlaceListenerRefPtr_ref(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long VgIPlaceListenerRefPtr_set(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr, long j2, VgIPlaceListener vgIPlaceListener);

    public static final native int VgIPlaceListenerRefPtr_unref(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long VgIPlaceListener_SWIGUpcast(long j);

    public static final native void VgIPlaceListener_change_ownership(VgIPlaceListener vgIPlaceListener, long j, boolean z);

    public static final native void VgIPlaceListener_director_connect(VgIPlaceListener vgIPlaceListener, long j, boolean z, boolean z2);

    public static final native void VgIPlaceListener_notifyMapDatabaseLoaded(long j, VgIPlaceListener vgIPlaceListener, long j2, VgIApplication vgIApplication);

    public static final native void VgIPlaceListener_notifyPlaceSelected(long j, VgIPlaceListener vgIPlaceListener, long j2, VgIApplication vgIApplication, String str, long j3, VgPosition vgPosition);

    public static final native long VgIResourceCallbackConstRefPtr___deref__(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native long VgIResourceCallbackConstRefPtr___ref__(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native long VgIResourceCallbackConstRefPtr_get(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native int VgIResourceCallbackConstRefPtr_getNbReferences(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native long VgIResourceCallbackConstRefPtr_getNull();

    public static final native boolean VgIResourceCallbackConstRefPtr_isValid(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native void VgIResourceCallbackConstRefPtr_ref(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native long VgIResourceCallbackConstRefPtr_set(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr, long j2, VgIResourceCallback vgIResourceCallback);

    public static final native int VgIResourceCallbackConstRefPtr_unref(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native long VgIResourceCallbackRefPtr___deref__(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long VgIResourceCallbackRefPtr___ref__(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long VgIResourceCallbackRefPtr_get(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native int VgIResourceCallbackRefPtr_getNbReferences(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long VgIResourceCallbackRefPtr_getNull();

    public static final native boolean VgIResourceCallbackRefPtr_isValid(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native void VgIResourceCallbackRefPtr_notifyResource(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr, int i, String str, long j2, VgBinaryBuffer vgBinaryBuffer);

    public static final native void VgIResourceCallbackRefPtr_ref(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long VgIResourceCallbackRefPtr_set(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr, long j2, VgIResourceCallback vgIResourceCallback);

    public static final native int VgIResourceCallbackRefPtr_unref(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long VgIResourceCallback_SWIGUpcast(long j);

    public static final native void VgIResourceCallback_change_ownership(VgIResourceCallback vgIResourceCallback, long j, boolean z);

    public static final native void VgIResourceCallback_director_connect(VgIResourceCallback vgIResourceCallback, long j, boolean z, boolean z2);

    public static final native void VgIResourceCallback_notifyResource(long j, VgIResourceCallback vgIResourceCallback, int i, String str, long j2, VgBinaryBuffer vgBinaryBuffer);

    public static final native long VgIResourceManager_readFromFileOrURL__SWIG_0(long j, VgIResourceManager vgIResourceManager, String str);

    public static final native long VgIResourceManager_readFromFileOrURL__SWIG_1(long j, VgIResourceManager vgIResourceManager, long j2, VgResourceRequestParameters vgResourceRequestParameters);

    public static final native long VgIResourceRequestConstRefPtr___deref__(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native long VgIResourceRequestConstRefPtr___ref__(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native long VgIResourceRequestConstRefPtr_get(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native int VgIResourceRequestConstRefPtr_getNbReferences(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native long VgIResourceRequestConstRefPtr_getNull();

    public static final native boolean VgIResourceRequestConstRefPtr_isValid(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native void VgIResourceRequestConstRefPtr_ref(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native long VgIResourceRequestConstRefPtr_set(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr, long j2, VgIResourceRequest vgIResourceRequest);

    public static final native int VgIResourceRequestConstRefPtr_unref(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native long VgIResourceRequestRefPtr___deref__(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long VgIResourceRequestRefPtr___ref__(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native void VgIResourceRequestRefPtr_cancel(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long VgIResourceRequestRefPtr_get(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native int VgIResourceRequestRefPtr_getNbReferences(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long VgIResourceRequestRefPtr_getNull();

    public static final native boolean VgIResourceRequestRefPtr_isValid(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native void VgIResourceRequestRefPtr_ref(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long VgIResourceRequestRefPtr_set(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr, long j2, VgIResourceRequest vgIResourceRequest);

    public static final native int VgIResourceRequestRefPtr_unref(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long VgIResourceRequest_SWIGUpcast(long j);

    public static final native void VgIResourceRequest_cancel(long j, VgIResourceRequest vgIResourceRequest);

    public static final native long VgIRouteCallbackConstRefPtr___deref__(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native long VgIRouteCallbackConstRefPtr___ref__(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native long VgIRouteCallbackConstRefPtr_get(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native int VgIRouteCallbackConstRefPtr_getNbReferences(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native long VgIRouteCallbackConstRefPtr_getNull();

    public static final native boolean VgIRouteCallbackConstRefPtr_isValid(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native void VgIRouteCallbackConstRefPtr_ref(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native long VgIRouteCallbackConstRefPtr_set(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr, long j2, VgIRouteCallback vgIRouteCallback);

    public static final native int VgIRouteCallbackConstRefPtr_unref(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native long VgIRouteCallbackRefPtr___deref__(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long VgIRouteCallbackRefPtr___ref__(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long VgIRouteCallbackRefPtr_get(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native int VgIRouteCallbackRefPtr_getNbReferences(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long VgIRouteCallbackRefPtr_getNull();

    public static final native boolean VgIRouteCallbackRefPtr_isValid(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native void VgIRouteCallbackRefPtr_notifyRouteComputed(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr, int i, long j2, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native void VgIRouteCallbackRefPtr_ref(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long VgIRouteCallbackRefPtr_set(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr, long j2, VgIRouteCallback vgIRouteCallback);

    public static final native int VgIRouteCallbackRefPtr_unref(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long VgIRouteCallback_SWIGUpcast(long j);

    public static final native void VgIRouteCallback_change_ownership(VgIRouteCallback vgIRouteCallback, long j, boolean z);

    public static final native void VgIRouteCallback_director_connect(VgIRouteCallback vgIRouteCallback, long j, boolean z, boolean z2);

    public static final native void VgIRouteCallback_notifyRouteComputed(long j, VgIRouteCallback vgIRouteCallback, int i, long j2, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteConstRefPtr___deref__(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native long VgIRouteConstRefPtr___ref__(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native long VgIRouteConstRefPtr_get(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native double VgIRouteConstRefPtr_getDuration(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native double VgIRouteConstRefPtr_getLength(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native int VgIRouteConstRefPtr_getNbReferences(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native long VgIRouteConstRefPtr_getNull();

    public static final native boolean VgIRouteConstRefPtr_isValid(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native void VgIRouteConstRefPtr_ref(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native long VgIRouteConstRefPtr_set(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr, long j2, VgIRoute vgIRoute);

    public static final native int VgIRouteConstRefPtr_unref(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native long VgIRouteConverterFactory_createConverter(long j, VgIRouteConverterFactory vgIRouteConverterFactory, int i);

    public static final native void VgIRouteConverter_convertRoute(long j, VgIRouteConverter vgIRouteConverter, long j2, VgIRouteConstRefPtr vgIRouteConstRefPtr, long j3, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector);

    public static final native void VgIRouteGeometryDescriptorVector_add(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector, long j2, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native long VgIRouteGeometryDescriptorVector_capacity(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector);

    public static final native void VgIRouteGeometryDescriptorVector_clear(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector);

    public static final native long VgIRouteGeometryDescriptorVector_get(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector, int i);

    public static final native boolean VgIRouteGeometryDescriptorVector_isEmpty(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector);

    public static final native void VgIRouteGeometryDescriptorVector_reserve(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector, long j2);

    public static final native void VgIRouteGeometryDescriptorVector_set(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector, int i, long j2, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native long VgIRouteGeometryDescriptorVector_size(long j, VgIRouteGeometryDescriptorVector vgIRouteGeometryDescriptorVector);

    public static final native int VgIRouteGeometryDescriptor_mDestinationIndex_get(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native void VgIRouteGeometryDescriptor_mDestinationIndex_set(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor, int i);

    public static final native String VgIRouteGeometryDescriptor_mLayerName_get(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native void VgIRouteGeometryDescriptor_mLayerName_set(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor, String str);

    public static final native long VgIRouteGeometryDescriptor_mLineDescriptors_get(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native void VgIRouteGeometryDescriptor_mLineDescriptors_set(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor, long j2, VgLineDescriptorVector vgLineDescriptorVector);

    public static final native String VgIRouteGeometryDescriptor_mModality_get(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native void VgIRouteGeometryDescriptor_mModality_set(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor, String str);

    public static final native long VgIRouteGeometryDescriptor_mPointDescriptors_get(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor);

    public static final native void VgIRouteGeometryDescriptor_mPointDescriptors_set(long j, VgIRouteGeometryDescriptor vgIRouteGeometryDescriptor, long j2, VgPointDescriptorVector vgPointDescriptorVector);

    public static final native long VgIRouteRefPtr___deref__(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteRefPtr___ref__(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteRefPtr_get(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteRefPtr_getDestinationIndices(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native double VgIRouteRefPtr_getDuration(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native double VgIRouteRefPtr_getLength(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native int VgIRouteRefPtr_getNbReferences(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteRefPtr_getNull();

    public static final native long VgIRouteRefPtr_getRequestParameters(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native boolean VgIRouteRefPtr_isValid(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native void VgIRouteRefPtr_ref(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteRefPtr_set(long j, VgIRouteRefPtr vgIRouteRefPtr, long j2, VgIRoute vgIRoute);

    public static final native int VgIRouteRefPtr_unref(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long VgIRouteRequestParameters_mCallback_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mCallback_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, long j2, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native int VgIRouteRequestParameters_mDestinationsOrder_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mDestinationsOrder_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, int i);

    public static final native long VgIRouteRequestParameters_mDestinations_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mDestinations_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, long j2, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector);

    public static final native long VgIRouteRequestParameters_mExcludedAttributes_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mExcludedAttributes_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, long j2, VgStringSet vgStringSet);

    public static final native long VgIRouteRequestParameters_mExcludedModalities_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mExcludedModalities_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, long j2, VgStringSet vgStringSet);

    public static final native long VgIRouteRequestParameters_mOrigin_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mOrigin_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, long j2, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRouteRequestParameters_mRemapResultingModality_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mRemapResultingModality_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, long j2, VgStringStringMap vgStringStringMap);

    public static final native int VgIRouteRequestParameters_mRequestType_get(long j, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native void VgIRouteRequestParameters_mRequestType_set(long j, VgIRouteRequestParameters vgIRouteRequestParameters, int i);

    public static final native void VgIRouteRequest_cancel(long j, VgIRouteRequest vgIRouteRequest);

    public static final native long VgIRoute_SWIGUpcast(long j);

    public static final native long VgIRoute_getDestinationIndices(long j, VgIRoute vgIRoute);

    public static final native double VgIRoute_getDuration(long j, VgIRoute vgIRoute);

    public static final native double VgIRoute_getLength(long j, VgIRoute vgIRoute);

    public static final native long VgIRoute_getRequestParameters(long j, VgIRoute vgIRoute);

    public static final native long VgIRoutingModule_SWIGUpcast(long j);

    public static final native void VgIRoutingModule_getAllAttributeNames(long j, VgIRoutingModule vgIRoutingModule, long j2, VgStringVector vgStringVector);

    public static final native void VgIRoutingModule_getAllModalityNames(long j, VgIRoutingModule vgIRoutingModule, long j2, VgStringVector vgStringVector);

    public static final native long VgIRoutingModule_getRouteConverterFactory(long j, VgIRoutingModule vgIRoutingModule);

    public static final native long VgIRoutingModule_getRoutingSolver(long j, VgIRoutingModule vgIRoutingModule);

    public static final native int VgIRoutingModule_setEdgeTimeByAttributeAndModality(long j, VgIRoutingModule vgIRoutingModule, String str, String str2, float f);

    public static final native long VgIRoutingNodeConstRefPtr___deref__(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long VgIRoutingNodeConstRefPtr___ref__(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long VgIRoutingNodeConstRefPtr_get(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native String VgIRoutingNodeConstRefPtr_getLayerName(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native int VgIRoutingNodeConstRefPtr_getNbReferences(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long VgIRoutingNodeConstRefPtr_getNull();

    public static final native String VgIRoutingNodeConstRefPtr_getPoiID(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long VgIRoutingNodeConstRefPtr_getPosition(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long VgIRoutingNodeConstRefPtr_getRoutePosition(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native boolean VgIRoutingNodeConstRefPtr_hasPoiID(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native boolean VgIRoutingNodeConstRefPtr_hasPosition(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native boolean VgIRoutingNodeConstRefPtr_hasRoutePosition(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native boolean VgIRoutingNodeConstRefPtr_isValid(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native void VgIRoutingNodeConstRefPtr_ref(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long VgIRoutingNodeConstRefPtr_set(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr, long j2, VgIRoutingNode vgIRoutingNode);

    public static final native int VgIRoutingNodeConstRefPtr_unref(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native float VgIRoutingNodeParameters_mDistanceThreshold_get(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters);

    public static final native void VgIRoutingNodeParameters_mDistanceThreshold_set(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters, float f);

    public static final native long VgIRoutingNodeParameters_mExcludedEdgeModalities_get(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters);

    public static final native void VgIRoutingNodeParameters_mExcludedEdgeModalities_set(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters, long j2, VgStringSet vgStringSet);

    public static final native String VgIRoutingNodeParameters_mLayerName_get(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters);

    public static final native void VgIRoutingNodeParameters_mLayerName_set(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters, String str);

    public static final native int VgIRoutingNodeParameters_mOption_get(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters);

    public static final native void VgIRoutingNodeParameters_mOption_set(long j, VgIRoutingNodeParameters vgIRoutingNodeParameters, int i);

    public static final native void VgIRoutingNodeRefPtrVector_add(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector, long j2, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtrVector_capacity(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector);

    public static final native void VgIRoutingNodeRefPtrVector_clear(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector);

    public static final native long VgIRoutingNodeRefPtrVector_get(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector, int i);

    public static final native boolean VgIRoutingNodeRefPtrVector_isEmpty(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector);

    public static final native void VgIRoutingNodeRefPtrVector_reserve(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector, long j2);

    public static final native void VgIRoutingNodeRefPtrVector_set(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector, int i, long j2, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtrVector_size(long j, VgIRoutingNodeRefPtrVector vgIRoutingNodeRefPtrVector);

    public static final native long VgIRoutingNodeRefPtr___deref__(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtr___ref__(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtr_get(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native String VgIRoutingNodeRefPtr_getLayerName(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native int VgIRoutingNodeRefPtr_getNbReferences(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtr_getNull();

    public static final native String VgIRoutingNodeRefPtr_getPoiID(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtr_getPosition(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtr_getRoutePosition(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native boolean VgIRoutingNodeRefPtr_hasPoiID(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native boolean VgIRoutingNodeRefPtr_hasPosition(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native boolean VgIRoutingNodeRefPtr_hasRoutePosition(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native boolean VgIRoutingNodeRefPtr_isValid(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native void VgIRoutingNodeRefPtr_ref(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNodeRefPtr_set(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr, long j2, VgIRoutingNode vgIRoutingNode);

    public static final native int VgIRoutingNodeRefPtr_unref(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long VgIRoutingNode_SWIGUpcast(long j);

    public static final native String VgIRoutingNode_getLayerName(long j, VgIRoutingNode vgIRoutingNode);

    public static final native String VgIRoutingNode_getPoiID(long j, VgIRoutingNode vgIRoutingNode);

    public static final native long VgIRoutingNode_getPosition(long j, VgIRoutingNode vgIRoutingNode);

    public static final native long VgIRoutingNode_getRoutePosition(long j, VgIRoutingNode vgIRoutingNode);

    public static final native boolean VgIRoutingNode_hasPoiID(long j, VgIRoutingNode vgIRoutingNode);

    public static final native boolean VgIRoutingNode_hasPosition(long j, VgIRoutingNode vgIRoutingNode);

    public static final native boolean VgIRoutingNode_hasRoutePosition(long j, VgIRoutingNode vgIRoutingNode);

    public static final native long VgIRoutingSolver_computeRoute(long j, VgIRoutingSolver vgIRoutingSolver, long j2, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native long VgIRoutingSolver_computeRouteDirect(long j, VgIRoutingSolver vgIRoutingSolver, long j2, VgIRouteRequestParameters vgIRouteRequestParameters);

    public static final native long VgIRoutingSolver_getRoutingNode__SWIG_0(long j, VgIRoutingSolver vgIRoutingSolver, long j2, VgPosition vgPosition);

    public static final native long VgIRoutingSolver_getRoutingNode__SWIG_1(long j, VgIRoutingSolver vgIRoutingSolver, long j2, VgPosition vgPosition, long j3, VgIRoutingNodeParameters vgIRoutingNodeParameters);

    public static final native long VgIRoutingSolver_getRoutingNode__SWIG_2(long j, VgIRoutingSolver vgIRoutingSolver, String str);

    public static final native long VgITextureConstRefPtr___deref__(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native long VgITextureConstRefPtr___ref__(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native long VgITextureConstRefPtr_get(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native int VgITextureConstRefPtr_getHeight(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native int VgITextureConstRefPtr_getNbReferences(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native long VgITextureConstRefPtr_getNull();

    public static final native int VgITextureConstRefPtr_getWidth(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native boolean VgITextureConstRefPtr_isValid(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native void VgITextureConstRefPtr_ref(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native long VgITextureConstRefPtr_set(long j, VgITextureConstRefPtr vgITextureConstRefPtr, long j2, VgITexture vgITexture);

    public static final native int VgITextureConstRefPtr_unref(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native long VgITextureManager_createTextureWithUniformColor__SWIG_0(long j, VgITextureManager vgITextureManager, long j2, VgColor vgColor, long j3, long j4);

    public static final native long VgITextureManager_createTextureWithUniformColor__SWIG_1(long j, VgITextureManager vgITextureManager, long j2, VgColor vgColor, long j3);

    public static final native long VgITextureManager_createTextureWithUniformColor__SWIG_2(long j, VgITextureManager vgITextureManager, long j2, VgColor vgColor);

    public static final native long VgITextureManager_createTexture__SWIG_0(long j, VgITextureManager vgITextureManager, long j2, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr, boolean z);

    public static final native long VgITextureManager_createTexture__SWIG_1(long j, VgITextureManager vgITextureManager, long j2, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgITextureManager_createTexture__SWIG_2(long j, VgITextureManager vgITextureManager, long j2, long j3, int i, String str, long j4, boolean z);

    public static final native long VgITextureManager_createTexture__SWIG_3(long j, VgITextureManager vgITextureManager, long j2, long j3, int i, String str, long j4);

    public static final native long VgITextureManager_getTexture(long j, VgITextureManager vgITextureManager, String str);

    public static final native long VgITextureRefPtr___deref__(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long VgITextureRefPtr___ref__(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long VgITextureRefPtr_get(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native int VgITextureRefPtr_getHeight(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native int VgITextureRefPtr_getNbReferences(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long VgITextureRefPtr_getNull();

    public static final native int VgITextureRefPtr_getWidth(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native boolean VgITextureRefPtr_isValid(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native void VgITextureRefPtr_ref(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long VgITextureRefPtr_set(long j, VgITextureRefPtr vgITextureRefPtr, long j2, VgITexture vgITexture);

    public static final native int VgITextureRefPtr_unref(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long VgITexture_SWIGUpcast(long j);

    public static final native int VgITexture_getHeight(long j, VgITexture vgITexture);

    public static final native int VgITexture_getWidth(long j, VgITexture vgITexture);

    public static final native double VgIViewPoint_mHeading_get(long j, VgIViewPoint vgIViewPoint);

    public static final native void VgIViewPoint_mHeading_set(long j, VgIViewPoint vgIViewPoint, double d);

    public static final native double VgIViewPoint_mPitch_get(long j, VgIViewPoint vgIViewPoint);

    public static final native void VgIViewPoint_mPitch_set(long j, VgIViewPoint vgIViewPoint, double d);

    public static final native long VgIViewPoint_mPosition_get(long j, VgIViewPoint vgIViewPoint);

    public static final native void VgIViewPoint_mPosition_set(long j, VgIViewPoint vgIViewPoint, long j2, VgPosition vgPosition);

    public static final native long VgIconMarkerConstRefPtr___deref__(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long VgIconMarkerConstRefPtr___ref__(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long VgIconMarkerConstRefPtr_get(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long VgIconMarkerConstRefPtr_getColor(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native int VgIconMarkerConstRefPtr_getNbReferences(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long VgIconMarkerConstRefPtr_getNull();

    public static final native float VgIconMarkerConstRefPtr_getScale(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native boolean VgIconMarkerConstRefPtr_isValid(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native void VgIconMarkerConstRefPtr_ref(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long VgIconMarkerConstRefPtr_set(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr, long j2, VgIconMarker vgIconMarker);

    public static final native int VgIconMarkerConstRefPtr_unref(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr___deref__(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr___ref__(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr_get(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native int VgIconMarkerDescriptorConstRefPtr_getNbReferences(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr_getNull();

    public static final native int VgIconMarkerDescriptorConstRefPtr_getType(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native boolean VgIconMarkerDescriptorConstRefPtr_isValid(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr_mColor_get(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr_mIcon_get(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native float VgIconMarkerDescriptorConstRefPtr_mScale_get(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native void VgIconMarkerDescriptorConstRefPtr_ref(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorConstRefPtr_set(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr, long j2, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native int VgIconMarkerDescriptorConstRefPtr_unref(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr___deref__(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr___ref__(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr_create(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr_get(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native int VgIconMarkerDescriptorRefPtr_getNbReferences(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr_getNull();

    public static final native int VgIconMarkerDescriptorRefPtr_getType(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native boolean VgIconMarkerDescriptorRefPtr_isValid(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr_mColor_get(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native void VgIconMarkerDescriptorRefPtr_mColor_set(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr, long j2, VgColor vgColor);

    public static final native long VgIconMarkerDescriptorRefPtr_mIcon_get(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native void VgIconMarkerDescriptorRefPtr_mIcon_set(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native float VgIconMarkerDescriptorRefPtr_mScale_get(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native void VgIconMarkerDescriptorRefPtr_mScale_set(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr, float f);

    public static final native void VgIconMarkerDescriptorRefPtr_ref(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptorRefPtr_set(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr, long j2, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native int VgIconMarkerDescriptorRefPtr_unref(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long VgIconMarkerDescriptor_SWIGUpcast(long j);

    public static final native long VgIconMarkerDescriptor_create();

    public static final native int VgIconMarkerDescriptor_getType(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native long VgIconMarkerDescriptor_mColor_get(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native void VgIconMarkerDescriptor_mColor_set(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor, long j2, VgColor vgColor);

    public static final native long VgIconMarkerDescriptor_mIcon_get(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native void VgIconMarkerDescriptor_mIcon_set(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native float VgIconMarkerDescriptor_mScale_get(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native void VgIconMarkerDescriptor_mScale_set(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor, float f);

    public static final native long VgIconMarkerRefPtr___deref__(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr___ref__(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_asIconMarker(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_asTextMarker(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_get(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_getColor(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_getIcon(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native int VgIconMarkerRefPtr_getNbReferences(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_getNull();

    public static final native float VgIconMarkerRefPtr_getScale(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native boolean VgIconMarkerRefPtr_isValid(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native void VgIconMarkerRefPtr_ref(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarkerRefPtr_set(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr, long j2, VgIconMarker vgIconMarker);

    public static final native void VgIconMarkerRefPtr_setColor(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr, long j2, VgColor vgColor);

    public static final native boolean VgIconMarkerRefPtr_setIcon(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native void VgIconMarkerRefPtr_setScale(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr, float f);

    public static final native int VgIconMarkerRefPtr_unref(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long VgIconMarker_SWIGUpcast(long j);

    public static final native long VgIconMarker_asIconMarker(long j, VgIconMarker vgIconMarker);

    public static final native long VgIconMarker_getColor(long j, VgIconMarker vgIconMarker);

    public static final native long VgIconMarker_getIcon(long j, VgIconMarker vgIconMarker);

    public static final native float VgIconMarker_getScale(long j, VgIconMarker vgIconMarker);

    public static final native void VgIconMarker_setColor(long j, VgIconMarker vgIconMarker, long j2, VgColor vgColor);

    public static final native boolean VgIconMarker_setIcon(long j, VgIconMarker vgIconMarker, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native void VgIconMarker_setScale(long j, VgIconMarker vgIconMarker, float f);

    public static final native long VgInstanceFactory_instantiate__SWIG_10(long j, VgInstanceFactory vgInstanceFactory, long j2, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_11(long j, VgInstanceFactory vgInstanceFactory, long j2, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_2(long j, VgInstanceFactory vgInstanceFactory, long j2, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_3(long j, VgInstanceFactory vgInstanceFactory, long j2, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_4(long j, VgInstanceFactory vgInstanceFactory, long j2, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_5(long j, VgInstanceFactory vgInstanceFactory, long j2, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_6(long j, VgInstanceFactory vgInstanceFactory, long j2, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_7(long j, VgInstanceFactory vgInstanceFactory, long j2, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_8(long j, VgInstanceFactory vgInstanceFactory, long j2, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgInstanceFactory_instantiate__SWIG_9(long j, VgInstanceFactory vgInstanceFactory, long j2, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgIntVector_add(long j, VgIntVector vgIntVector, int i);

    public static final native long VgIntVector_capacity(long j, VgIntVector vgIntVector);

    public static final native void VgIntVector_clear(long j, VgIntVector vgIntVector);

    public static final native int VgIntVector_get(long j, VgIntVector vgIntVector, int i);

    public static final native boolean VgIntVector_isEmpty(long j, VgIntVector vgIntVector);

    public static final native void VgIntVector_reserve(long j, VgIntVector vgIntVector, long j2);

    public static final native void VgIntVector_set(long j, VgIntVector vgIntVector, int i, int i2);

    public static final native long VgIntVector_size(long j, VgIntVector vgIntVector);

    public static final native long VgLayerConstRefPtr___deref__(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr___ref__(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr_get(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr_getAnimation(long j, VgLayerConstRefPtr vgLayerConstRefPtr, String str);

    public static final native long VgLayerConstRefPtr_getAnimationChannelValue(long j, VgLayerConstRefPtr vgLayerConstRefPtr, String str);

    public static final native void VgLayerConstRefPtr_getAnimationNames(long j, VgLayerConstRefPtr vgLayerConstRefPtr, long j2, VgStringList vgStringList);

    public static final native float VgLayerConstRefPtr_getLODFactor(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native float VgLayerConstRefPtr_getLoadFactor(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native String VgLayerConstRefPtr_getName(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native int VgLayerConstRefPtr_getNbReferences(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr_getNull();

    public static final native long VgLayerConstRefPtr_getOrientation(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr_getPosition(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr_getSRS(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native float VgLayerConstRefPtr_getScale(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native int VgLayerConstRefPtr_getZIndex(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native boolean VgLayerConstRefPtr_isDrawnOnTop(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native boolean VgLayerConstRefPtr_isValid(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native boolean VgLayerConstRefPtr_isVisible(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native void VgLayerConstRefPtr_ref(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerConstRefPtr_set(long j, VgLayerConstRefPtr vgLayerConstRefPtr, long j2, VgLayer vgLayer);

    public static final native int VgLayerConstRefPtr_unref(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long VgLayerDescriptorConstRefPtr___deref__(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgLayerDescriptorConstRefPtr___ref__(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgLayerDescriptorConstRefPtr_get(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native int VgLayerDescriptorConstRefPtr_getNbReferences(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgLayerDescriptorConstRefPtr_getNull();

    public static final native boolean VgLayerDescriptorConstRefPtr_isValid(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgLayerDescriptorConstRefPtr_mLayoutDescriptor_get(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native String VgLayerDescriptorConstRefPtr_mName_get(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native void VgLayerDescriptorConstRefPtr_ref(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgLayerDescriptorConstRefPtr_set(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr, long j2, VgLayerDescriptor vgLayerDescriptor);

    public static final native int VgLayerDescriptorConstRefPtr_unref(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long VgLayerDescriptorRefPtr___deref__(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgLayerDescriptorRefPtr___ref__(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgLayerDescriptorRefPtr_get(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native int VgLayerDescriptorRefPtr_getNbReferences(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgLayerDescriptorRefPtr_getNull();

    public static final native boolean VgLayerDescriptorRefPtr_isValid(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgLayerDescriptorRefPtr_mLayoutDescriptor_get(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native void VgLayerDescriptorRefPtr_mLayoutDescriptor_set(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr, long j2, VgLayoutDescriptor vgLayoutDescriptor);

    public static final native String VgLayerDescriptorRefPtr_mName_get(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native void VgLayerDescriptorRefPtr_mName_set(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr, String str);

    public static final native void VgLayerDescriptorRefPtr_ref(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgLayerDescriptorRefPtr_set(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr, long j2, VgLayerDescriptor vgLayerDescriptor);

    public static final native int VgLayerDescriptorRefPtr_unref(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long VgLayerDescriptor_SWIGUpcast(long j);

    public static final native long VgLayerDescriptor_mLayoutDescriptor_get(long j, VgLayerDescriptor vgLayerDescriptor);

    public static final native void VgLayerDescriptor_mLayoutDescriptor_set(long j, VgLayerDescriptor vgLayerDescriptor, long j2, VgLayoutDescriptor vgLayoutDescriptor);

    public static final native String VgLayerDescriptor_mName_get(long j, VgLayerDescriptor vgLayerDescriptor);

    public static final native void VgLayerDescriptor_mName_set(long j, VgLayerDescriptor vgLayerDescriptor, String str);

    public static final native long VgLayerManager_editLayer(long j, VgLayerManager vgLayerManager, String str);

    public static final native long VgLayerManager_editLayers(long j, VgLayerManager vgLayerManager);

    public static final native long VgLayerManager_getLayers(long j, VgLayerManager vgLayerManager);

    public static final native long VgLayerRefPtr___deref__(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr___ref__(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_asGeometry(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_asLine(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_asPoint(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_editAnimation(long j, VgLayerRefPtr vgLayerRefPtr, String str);

    public static final native long VgLayerRefPtr_get(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_getAnimation(long j, VgLayerRefPtr vgLayerRefPtr, String str);

    public static final native long VgLayerRefPtr_getAnimationChannelValue(long j, VgLayerRefPtr vgLayerRefPtr, String str);

    public static final native void VgLayerRefPtr_getAnimationNames(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgStringList vgStringList);

    public static final native float VgLayerRefPtr_getLODFactor(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native float VgLayerRefPtr_getLoadFactor(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native String VgLayerRefPtr_getName(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native int VgLayerRefPtr_getNbReferences(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_getNull();

    public static final native long VgLayerRefPtr_getOrientation(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_getPosition(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_getSRS(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native float VgLayerRefPtr_getScale(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native int VgLayerRefPtr_getZIndex(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native boolean VgLayerRefPtr_isDrawnOnTop(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native boolean VgLayerRefPtr_isValid(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native boolean VgLayerRefPtr_isVisible(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native void VgLayerRefPtr_ref(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerRefPtr_set(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgLayer vgLayer);

    public static final native void VgLayerRefPtr_setAnimation__SWIG_0(long j, VgLayerRefPtr vgLayerRefPtr, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLayerRefPtr_setAnimation__SWIG_1(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLayerRefPtr_setDrawOnTop(long j, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgLayerRefPtr_setHostedVisible(long j, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgLayerRefPtr_setLODFactor(long j, VgLayerRefPtr vgLayerRefPtr, float f);

    public static final native void VgLayerRefPtr_setLoadFactor(long j, VgLayerRefPtr vgLayerRefPtr, float f);

    public static final native void VgLayerRefPtr_setLocalAnimation(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLayerRefPtr_setOrientation(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgOrientation vgOrientation);

    public static final native void VgLayerRefPtr_setPosition__SWIG_0(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgPosition vgPosition, boolean z);

    public static final native void VgLayerRefPtr_setPosition__SWIG_1(long j, VgLayerRefPtr vgLayerRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgLayerRefPtr_setScale(long j, VgLayerRefPtr vgLayerRefPtr, float f);

    public static final native void VgLayerRefPtr_setVisible(long j, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgLayerRefPtr_setZIndex(long j, VgLayerRefPtr vgLayerRefPtr, int i);

    public static final native int VgLayerRefPtr_unref(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native void VgLayerVector_add(long j, VgLayerVector vgLayerVector, long j2, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerVector_capacity(long j, VgLayerVector vgLayerVector);

    public static final native void VgLayerVector_clear(long j, VgLayerVector vgLayerVector);

    public static final native long VgLayerVector_get(long j, VgLayerVector vgLayerVector, int i);

    public static final native boolean VgLayerVector_isEmpty(long j, VgLayerVector vgLayerVector);

    public static final native void VgLayerVector_reserve(long j, VgLayerVector vgLayerVector, long j2);

    public static final native void VgLayerVector_set(long j, VgLayerVector vgLayerVector, int i, long j2, VgLayerRefPtr vgLayerRefPtr);

    public static final native long VgLayerVector_size(long j, VgLayerVector vgLayerVector);

    public static final native long VgLayer_SWIGUpcast(long j);

    public static final native float VgLayer_getLODFactor(long j, VgLayer vgLayer);

    public static final native float VgLayer_getLoadFactor(long j, VgLayer vgLayer);

    public static final native String VgLayer_getName(long j, VgLayer vgLayer);

    public static final native long VgLayer_getSRS(long j, VgLayer vgLayer);

    public static final native boolean VgLayer_isVisible(long j, VgLayer vgLayer);

    public static final native void VgLayer_setHostedVisible(long j, VgLayer vgLayer, boolean z);

    public static final native void VgLayer_setLODFactor(long j, VgLayer vgLayer, float f);

    public static final native void VgLayer_setLoadFactor(long j, VgLayer vgLayer, float f);

    public static final native void VgLayer_setVisible(long j, VgLayer vgLayer, boolean z);

    public static final native long VgLightConstRefPtr___deref__(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr___ref__(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_get(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_getAmbient(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_getAnimation(long j, VgLightConstRefPtr vgLightConstRefPtr, String str);

    public static final native void VgLightConstRefPtr_getAnimationNames(long j, VgLightConstRefPtr vgLightConstRefPtr, long j2, VgStringList vgStringList);

    public static final native long VgLightConstRefPtr_getDiffuse(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_getEmission(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native int VgLightConstRefPtr_getNbReferences(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_getNull();

    public static final native long VgLightConstRefPtr_getOrientation(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_getPosition(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_getSpecular(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native double VgLightConstRefPtr_getSpotCutoff(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native double VgLightConstRefPtr_getSpotExponent(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native boolean VgLightConstRefPtr_isDirectional(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native boolean VgLightConstRefPtr_isValid(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native void VgLightConstRefPtr_ref(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightConstRefPtr_set(long j, VgLightConstRefPtr vgLightConstRefPtr, long j2, VgLight vgLight);

    public static final native int VgLightConstRefPtr_unref(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long VgLightRefPtr___deref__(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr___ref__(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_editAnimation(long j, VgLightRefPtr vgLightRefPtr, String str);

    public static final native long VgLightRefPtr_get(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_getAmbient(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_getAnimation(long j, VgLightRefPtr vgLightRefPtr, String str);

    public static final native void VgLightRefPtr_getAnimationNames(long j, VgLightRefPtr vgLightRefPtr, long j2, VgStringList vgStringList);

    public static final native long VgLightRefPtr_getDiffuse(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_getEmission(long j, VgLightRefPtr vgLightRefPtr);

    public static final native int VgLightRefPtr_getNbReferences(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_getNull();

    public static final native long VgLightRefPtr_getOrientation(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_getPosition(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_getSpecular(long j, VgLightRefPtr vgLightRefPtr);

    public static final native double VgLightRefPtr_getSpotCutoff(long j, VgLightRefPtr vgLightRefPtr);

    public static final native double VgLightRefPtr_getSpotExponent(long j, VgLightRefPtr vgLightRefPtr);

    public static final native boolean VgLightRefPtr_isDirectional(long j, VgLightRefPtr vgLightRefPtr);

    public static final native boolean VgLightRefPtr_isValid(long j, VgLightRefPtr vgLightRefPtr);

    public static final native void VgLightRefPtr_ref(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLightRefPtr_set(long j, VgLightRefPtr vgLightRefPtr, long j2, VgLight vgLight);

    public static final native void VgLightRefPtr_setAmbient(long j, VgLightRefPtr vgLightRefPtr, long j2, VgColor vgColor);

    public static final native void VgLightRefPtr_setAnimation(long j, VgLightRefPtr vgLightRefPtr, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLightRefPtr_setDiffuse(long j, VgLightRefPtr vgLightRefPtr, long j2, VgColor vgColor);

    public static final native void VgLightRefPtr_setDirectional(long j, VgLightRefPtr vgLightRefPtr, boolean z);

    public static final native void VgLightRefPtr_setEmission(long j, VgLightRefPtr vgLightRefPtr, long j2, VgColor vgColor);

    public static final native void VgLightRefPtr_setOrientation(long j, VgLightRefPtr vgLightRefPtr, long j2, VgOrientation vgOrientation);

    public static final native void VgLightRefPtr_setPosition(long j, VgLightRefPtr vgLightRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgLightRefPtr_setSpecular(long j, VgLightRefPtr vgLightRefPtr, long j2, VgColor vgColor);

    public static final native void VgLightRefPtr_setSpotCutoff(long j, VgLightRefPtr vgLightRefPtr, double d);

    public static final native void VgLightRefPtr_setSpotExponent(long j, VgLightRefPtr vgLightRefPtr, double d);

    public static final native int VgLightRefPtr_unref(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long VgLight_SWIGUpcast(long j);

    public static final native long VgLight_editAnimation(long j, VgLight vgLight, String str);

    public static final native long VgLight_getAmbient(long j, VgLight vgLight);

    public static final native long VgLight_getAnimation(long j, VgLight vgLight, String str);

    public static final native void VgLight_getAnimationNames(long j, VgLight vgLight, long j2, VgStringList vgStringList);

    public static final native long VgLight_getDiffuse(long j, VgLight vgLight);

    public static final native long VgLight_getEmission(long j, VgLight vgLight);

    public static final native long VgLight_getOrientation(long j, VgLight vgLight);

    public static final native long VgLight_getPosition(long j, VgLight vgLight);

    public static final native long VgLight_getSpecular(long j, VgLight vgLight);

    public static final native double VgLight_getSpotCutoff(long j, VgLight vgLight);

    public static final native double VgLight_getSpotExponent(long j, VgLight vgLight);

    public static final native boolean VgLight_isDirectional(long j, VgLight vgLight);

    public static final native void VgLight_setAmbient(long j, VgLight vgLight, long j2, VgColor vgColor);

    public static final native void VgLight_setAnimation(long j, VgLight vgLight, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLight_setDiffuse(long j, VgLight vgLight, long j2, VgColor vgColor);

    public static final native void VgLight_setDirectional(long j, VgLight vgLight, boolean z);

    public static final native void VgLight_setEmission(long j, VgLight vgLight, long j2, VgColor vgColor);

    public static final native void VgLight_setOrientation(long j, VgLight vgLight, long j2, VgOrientation vgOrientation);

    public static final native void VgLight_setPosition(long j, VgLight vgLight, long j2, VgPosition vgPosition);

    public static final native void VgLight_setSpecular(long j, VgLight vgLight, long j2, VgColor vgColor);

    public static final native void VgLight_setSpotCutoff(long j, VgLight vgLight, double d);

    public static final native void VgLight_setSpotExponent(long j, VgLight vgLight, double d);

    public static final native long VgLineConstRefPtr___deref__(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr___ref__(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_get(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_getAnimation(long j, VgLineConstRefPtr vgLineConstRefPtr, String str);

    public static final native long VgLineConstRefPtr_getAnimationChannelValue(long j, VgLineConstRefPtr vgLineConstRefPtr, String str);

    public static final native void VgLineConstRefPtr_getAnimationNames(long j, VgLineConstRefPtr vgLineConstRefPtr, long j2, VgStringList vgStringList);

    public static final native long VgLineConstRefPtr_getDescriptor(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native String VgLineConstRefPtr_getID(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_getLocalPosition(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native int VgLineConstRefPtr_getNbReferences(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native boolean VgLineConstRefPtr_getNotifyPOISelectedOnClick(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_getNull();

    public static final native long VgLineConstRefPtr_getOrientation(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_getPosition(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native float VgLineConstRefPtr_getScale(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_getTexture(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native float VgLineConstRefPtr_getTextureAnimationSpeed(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native int VgLineConstRefPtr_getType(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_getVisibilityRamp(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native int VgLineConstRefPtr_getZIndex(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native boolean VgLineConstRefPtr_isDrawnOnTop(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native boolean VgLineConstRefPtr_isValid(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native boolean VgLineConstRefPtr_isVisible(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native void VgLineConstRefPtr_ref(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineConstRefPtr_set(long j, VgLineConstRefPtr vgLineConstRefPtr, long j2, VgLine vgLine);

    public static final native int VgLineConstRefPtr_unref(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr___deref__(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr___ref__(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_copy(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native int VgLineDescriptorConstRefPtr_getNbReferences(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_getNull();

    public static final native boolean VgLineDescriptorConstRefPtr_isValid(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native int VgLineDescriptorConstRefPtr_mAltitudeMode_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_mColors_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native boolean VgLineDescriptorConstRefPtr_mDrawOnTop_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native boolean VgLineDescriptorConstRefPtr_mHaveCaps_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native String VgLineDescriptorConstRefPtr_mID_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native int VgLineDescriptorConstRefPtr_mLineType_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native double VgLineDescriptorConstRefPtr_mMaxCornerRadius_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native float VgLineDescriptorConstRefPtr_mMinTesselationDist_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native boolean VgLineDescriptorConstRefPtr_mNotifyPOISelectedOnClick_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_mPositions_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native float VgLineDescriptorConstRefPtr_mScale_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native float VgLineDescriptorConstRefPtr_mTextureAnimationSpeed_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native float VgLineDescriptorConstRefPtr_mTextureSize_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_mTexture_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native double VgLineDescriptorConstRefPtr_mVisibilityRampFullyInvisible_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native double VgLineDescriptorConstRefPtr_mVisibilityRampFullyVisible_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native double VgLineDescriptorConstRefPtr_mVisibilityRampStartInvisible_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native double VgLineDescriptorConstRefPtr_mVisibilityRampStartVisible_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_mWidths_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native int VgLineDescriptorConstRefPtr_mZIndex_get(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native void VgLineDescriptorConstRefPtr_ref(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorConstRefPtr_set(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr, long j2, VgLineDescriptor vgLineDescriptor);

    public static final native int VgLineDescriptorConstRefPtr_unref(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long VgLineDescriptorRefPtr___deref__(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr___ref__(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr_clone(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr_copy(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr_create(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native int VgLineDescriptorRefPtr_getNbReferences(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr_getNull();

    public static final native boolean VgLineDescriptorRefPtr_isValid(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native int VgLineDescriptorRefPtr_mAltitudeMode_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mAltitudeMode_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, int i);

    public static final native long VgLineDescriptorRefPtr_mColors_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mColors_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, long j2, VgColorVector vgColorVector);

    public static final native boolean VgLineDescriptorRefPtr_mDrawOnTop_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mDrawOnTop_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, boolean z);

    public static final native boolean VgLineDescriptorRefPtr_mHaveCaps_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mHaveCaps_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, boolean z);

    public static final native String VgLineDescriptorRefPtr_mID_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mID_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, String str);

    public static final native int VgLineDescriptorRefPtr_mLineType_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mLineType_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, int i);

    public static final native double VgLineDescriptorRefPtr_mMaxCornerRadius_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mMaxCornerRadius_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, double d);

    public static final native float VgLineDescriptorRefPtr_mMinTesselationDist_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mMinTesselationDist_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, float f);

    public static final native boolean VgLineDescriptorRefPtr_mNotifyPOISelectedOnClick_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mNotifyPOISelectedOnClick_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, boolean z);

    public static final native long VgLineDescriptorRefPtr_mPositions_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mPositions_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, long j2, VgPositionVector vgPositionVector);

    public static final native float VgLineDescriptorRefPtr_mScale_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mScale_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, float f);

    public static final native float VgLineDescriptorRefPtr_mTextureAnimationSpeed_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mTextureAnimationSpeed_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, float f);

    public static final native float VgLineDescriptorRefPtr_mTextureSize_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mTextureSize_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, float f);

    public static final native long VgLineDescriptorRefPtr_mTexture_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mTexture_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native double VgLineDescriptorRefPtr_mVisibilityRampFullyInvisible_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mVisibilityRampFullyInvisible_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, double d);

    public static final native double VgLineDescriptorRefPtr_mVisibilityRampFullyVisible_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mVisibilityRampFullyVisible_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, double d);

    public static final native double VgLineDescriptorRefPtr_mVisibilityRampStartInvisible_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mVisibilityRampStartInvisible_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, double d);

    public static final native double VgLineDescriptorRefPtr_mVisibilityRampStartVisible_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mVisibilityRampStartVisible_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, double d);

    public static final native long VgLineDescriptorRefPtr_mWidths_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mWidths_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, long j2, VgDoubleVector vgDoubleVector);

    public static final native int VgLineDescriptorRefPtr_mZIndex_get(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorRefPtr_mZIndex_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, int i);

    public static final native void VgLineDescriptorRefPtr_ref(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorRefPtr_set(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, long j2, VgLineDescriptor vgLineDescriptor);

    public static final native int VgLineDescriptorRefPtr_unref(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native void VgLineDescriptorVector_add(long j, VgLineDescriptorVector vgLineDescriptorVector, long j2, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorVector_capacity(long j, VgLineDescriptorVector vgLineDescriptorVector);

    public static final native void VgLineDescriptorVector_clear(long j, VgLineDescriptorVector vgLineDescriptorVector);

    public static final native long VgLineDescriptorVector_get(long j, VgLineDescriptorVector vgLineDescriptorVector, int i);

    public static final native boolean VgLineDescriptorVector_isEmpty(long j, VgLineDescriptorVector vgLineDescriptorVector);

    public static final native void VgLineDescriptorVector_reserve(long j, VgLineDescriptorVector vgLineDescriptorVector, long j2);

    public static final native void VgLineDescriptorVector_set(long j, VgLineDescriptorVector vgLineDescriptorVector, int i, long j2, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long VgLineDescriptorVector_size(long j, VgLineDescriptorVector vgLineDescriptorVector);

    public static final native long VgLineDescriptor_SWIGUpcast(long j);

    public static final native long VgLineDescriptor_clone(long j, VgLineDescriptor vgLineDescriptor);

    public static final native long VgLineDescriptor_copy(long j, VgLineDescriptor vgLineDescriptor);

    public static final native long VgLineDescriptor_create();

    public static final native int VgLineDescriptor_mAltitudeMode_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mAltitudeMode_set(long j, VgLineDescriptor vgLineDescriptor, int i);

    public static final native long VgLineDescriptor_mColors_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mColors_set(long j, VgLineDescriptor vgLineDescriptor, long j2, VgColorVector vgColorVector);

    public static final native boolean VgLineDescriptor_mHaveCaps_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mHaveCaps_set(long j, VgLineDescriptor vgLineDescriptor, boolean z);

    public static final native String VgLineDescriptor_mID_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mID_set(long j, VgLineDescriptor vgLineDescriptor, String str);

    public static final native int VgLineDescriptor_mLineType_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mLineType_set(long j, VgLineDescriptor vgLineDescriptor, int i);

    public static final native double VgLineDescriptor_mMaxCornerRadius_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mMaxCornerRadius_set(long j, VgLineDescriptor vgLineDescriptor, double d);

    public static final native float VgLineDescriptor_mMinTesselationDist_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mMinTesselationDist_set(long j, VgLineDescriptor vgLineDescriptor, float f);

    public static final native long VgLineDescriptor_mPositions_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mPositions_set(long j, VgLineDescriptor vgLineDescriptor, long j2, VgPositionVector vgPositionVector);

    public static final native float VgLineDescriptor_mTextureAnimationSpeed_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mTextureAnimationSpeed_set(long j, VgLineDescriptor vgLineDescriptor, float f);

    public static final native float VgLineDescriptor_mTextureSize_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mTextureSize_set(long j, VgLineDescriptor vgLineDescriptor, float f);

    public static final native long VgLineDescriptor_mTexture_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mTexture_set(long j, VgLineDescriptor vgLineDescriptor, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native long VgLineDescriptor_mWidths_get(long j, VgLineDescriptor vgLineDescriptor);

    public static final native void VgLineDescriptor_mWidths_set(long j, VgLineDescriptor vgLineDescriptor, long j2, VgDoubleVector vgDoubleVector);

    public static final native long VgLineRefPtr___deref__(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr___ref__(long j, VgLineRefPtr vgLineRefPtr);

    public static final native void VgLineRefPtr_addListener(long j, VgLineRefPtr vgLineRefPtr, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgLineRefPtr_asGeometry(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_asLine(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_asPoint(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_editAnimation(long j, VgLineRefPtr vgLineRefPtr, String str);

    public static final native long VgLineRefPtr_get(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getAnimation(long j, VgLineRefPtr vgLineRefPtr, String str);

    public static final native long VgLineRefPtr_getAnimationChannelValue(long j, VgLineRefPtr vgLineRefPtr, String str);

    public static final native void VgLineRefPtr_getAnimationNames(long j, VgLineRefPtr vgLineRefPtr, long j2, VgStringList vgStringList);

    public static final native boolean VgLineRefPtr_getBoundingPositions(long j, VgLineRefPtr vgLineRefPtr, long j2, VgPositionVector vgPositionVector);

    public static final native long VgLineRefPtr_getDescriptor(long j, VgLineRefPtr vgLineRefPtr);

    public static final native String VgLineRefPtr_getID(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getInterpolatedPosition(long j, VgLineRefPtr vgLineRefPtr, float f);

    public static final native long VgLineRefPtr_getLayer(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getLocalPosition(long j, VgLineRefPtr vgLineRefPtr);

    public static final native int VgLineRefPtr_getNbReferences(long j, VgLineRefPtr vgLineRefPtr);

    public static final native boolean VgLineRefPtr_getNotifyPOISelectedOnClick(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getNull();

    public static final native long VgLineRefPtr_getOrientation(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getPosition(long j, VgLineRefPtr vgLineRefPtr);

    public static final native float VgLineRefPtr_getScale(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getTexture(long j, VgLineRefPtr vgLineRefPtr);

    public static final native float VgLineRefPtr_getTextureAnimationSpeed(long j, VgLineRefPtr vgLineRefPtr);

    public static final native int VgLineRefPtr_getType(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLineRefPtr_getVisibilityRamp(long j, VgLineRefPtr vgLineRefPtr);

    public static final native int VgLineRefPtr_getZIndex(long j, VgLineRefPtr vgLineRefPtr);

    public static final native boolean VgLineRefPtr_isDrawnOnTop(long j, VgLineRefPtr vgLineRefPtr);

    public static final native boolean VgLineRefPtr_isValid(long j, VgLineRefPtr vgLineRefPtr);

    public static final native boolean VgLineRefPtr_isVisible(long j, VgLineRefPtr vgLineRefPtr);

    public static final native void VgLineRefPtr_ref(long j, VgLineRefPtr vgLineRefPtr);

    public static final native void VgLineRefPtr_removeListener(long j, VgLineRefPtr vgLineRefPtr, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgLineRefPtr_set(long j, VgLineRefPtr vgLineRefPtr, long j2, VgLine vgLine);

    public static final native void VgLineRefPtr_setAnimation__SWIG_0(long j, VgLineRefPtr vgLineRefPtr, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLineRefPtr_setAnimation__SWIG_1(long j, VgLineRefPtr vgLineRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLineRefPtr_setDrawOnTop(long j, VgLineRefPtr vgLineRefPtr, boolean z);

    public static final native void VgLineRefPtr_setLayer__SWIG_0(long j, VgLineRefPtr vgLineRefPtr, long j2, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgLineRefPtr_setLayer__SWIG_1(long j, VgLineRefPtr vgLineRefPtr, long j2, VgLayerRefPtr vgLayerRefPtr);

    public static final native void VgLineRefPtr_setLocalAnimation(long j, VgLineRefPtr vgLineRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgLineRefPtr_setLocalPosition(long j, VgLineRefPtr vgLineRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgLineRefPtr_setNotifyPOISelectedOnClick(long j, VgLineRefPtr vgLineRefPtr, boolean z);

    public static final native void VgLineRefPtr_setOrientation(long j, VgLineRefPtr vgLineRefPtr, long j2, VgOrientation vgOrientation);

    public static final native void VgLineRefPtr_setPosition__SWIG_0(long j, VgLineRefPtr vgLineRefPtr, long j2, VgPosition vgPosition, boolean z);

    public static final native void VgLineRefPtr_setPosition__SWIG_1(long j, VgLineRefPtr vgLineRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgLineRefPtr_setScale(long j, VgLineRefPtr vgLineRefPtr, float f);

    public static final native boolean VgLineRefPtr_setTexture(long j, VgLineRefPtr vgLineRefPtr, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native void VgLineRefPtr_setTextureAnimationSpeed(long j, VgLineRefPtr vgLineRefPtr, float f);

    public static final native void VgLineRefPtr_setVisibilityRamp(long j, VgLineRefPtr vgLineRefPtr, long j2, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgLineRefPtr_setVisible(long j, VgLineRefPtr vgLineRefPtr, boolean z);

    public static final native void VgLineRefPtr_setZIndex(long j, VgLineRefPtr vgLineRefPtr, int i);

    public static final native int VgLineRefPtr_unref(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long VgLine_SWIGUpcast(long j);

    public static final native long VgLine_asLine(long j, VgLine vgLine);

    public static final native long VgLine_asPoint(long j, VgLine vgLine);

    public static final native long VgLine_getDescriptor(long j, VgLine vgLine);

    public static final native long VgLine_getInterpolatedPosition(long j, VgLine vgLine, float f);

    public static final native long VgLine_getTexture(long j, VgLine vgLine);

    public static final native float VgLine_getTextureAnimationSpeed(long j, VgLine vgLine);

    public static final native int VgLine_getType(long j, VgLine vgLine);

    public static final native boolean VgLine_setTexture(long j, VgLine vgLine, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native void VgLine_setTextureAnimationSpeed(long j, VgLine vgLine, float f);

    public static final native long VgLinkConstRefPtr___deref__(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native long VgLinkConstRefPtr___ref__(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native long VgLinkConstRefPtr_get(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native int VgLinkConstRefPtr_getNbReferences(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native long VgLinkConstRefPtr_getNull();

    public static final native boolean VgLinkConstRefPtr_isValid(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native void VgLinkConstRefPtr_ref(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native long VgLinkConstRefPtr_set(long j, VgLinkConstRefPtr vgLinkConstRefPtr, long j2, VgLink vgLink);

    public static final native int VgLinkConstRefPtr_unref(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr___deref__(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr___ref__(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native int VgLinkDescriptorConstRefPtr_getNbReferences(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_getNull();

    public static final native boolean VgLinkDescriptorConstRefPtr_isValid(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native float VgLinkDescriptorConstRefPtr_mAnimationSpeed_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_mSourceColor_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_mSourcePosition_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_mTargetColor_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_mTargetPosition_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native float VgLinkDescriptorConstRefPtr_mTextureRatio_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_mTexture_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native float VgLinkDescriptorConstRefPtr_mWidth_get(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native void VgLinkDescriptorConstRefPtr_ref(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorConstRefPtr_set(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr, long j2, VgLinkDescriptor vgLinkDescriptor);

    public static final native int VgLinkDescriptorConstRefPtr_unref(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long VgLinkDescriptorRefPtr___deref__(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgLinkDescriptorRefPtr___ref__(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgLinkDescriptorRefPtr_create(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgLinkDescriptorRefPtr_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native int VgLinkDescriptorRefPtr_getNbReferences(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgLinkDescriptorRefPtr_getNull();

    public static final native boolean VgLinkDescriptorRefPtr_isValid(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native float VgLinkDescriptorRefPtr_mAnimationSpeed_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mAnimationSpeed_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, float f);

    public static final native long VgLinkDescriptorRefPtr_mSourceColor_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mSourceColor_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, long j2, VgColor vgColor);

    public static final native long VgLinkDescriptorRefPtr_mSourcePosition_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mSourcePosition_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, long j2, VgPosition vgPosition);

    public static final native long VgLinkDescriptorRefPtr_mTargetColor_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mTargetColor_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, long j2, VgColor vgColor);

    public static final native long VgLinkDescriptorRefPtr_mTargetPosition_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mTargetPosition_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, long j2, VgPosition vgPosition);

    public static final native float VgLinkDescriptorRefPtr_mTextureRatio_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mTextureRatio_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, float f);

    public static final native long VgLinkDescriptorRefPtr_mTexture_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mTexture_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native float VgLinkDescriptorRefPtr_mWidth_get(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native void VgLinkDescriptorRefPtr_mWidth_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, float f);

    public static final native void VgLinkDescriptorRefPtr_ref(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgLinkDescriptorRefPtr_set(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr, long j2, VgLinkDescriptor vgLinkDescriptor);

    public static final native int VgLinkDescriptorRefPtr_unref(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long VgLinkDescriptor_SWIGUpcast(long j);

    public static final native long VgLinkDescriptor_create();

    public static final native float VgLinkDescriptor_mAnimationSpeed_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mAnimationSpeed_set(long j, VgLinkDescriptor vgLinkDescriptor, float f);

    public static final native long VgLinkDescriptor_mSourceColor_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mSourceColor_set(long j, VgLinkDescriptor vgLinkDescriptor, long j2, VgColor vgColor);

    public static final native long VgLinkDescriptor_mSourcePosition_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mSourcePosition_set(long j, VgLinkDescriptor vgLinkDescriptor, long j2, VgPosition vgPosition);

    public static final native long VgLinkDescriptor_mTargetColor_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mTargetColor_set(long j, VgLinkDescriptor vgLinkDescriptor, long j2, VgColor vgColor);

    public static final native long VgLinkDescriptor_mTargetPosition_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mTargetPosition_set(long j, VgLinkDescriptor vgLinkDescriptor, long j2, VgPosition vgPosition);

    public static final native float VgLinkDescriptor_mTextureRatio_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mTextureRatio_set(long j, VgLinkDescriptor vgLinkDescriptor, float f);

    public static final native long VgLinkDescriptor_mTexture_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mTexture_set(long j, VgLinkDescriptor vgLinkDescriptor, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native float VgLinkDescriptor_mWidth_get(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native void VgLinkDescriptor_mWidth_set(long j, VgLinkDescriptor vgLinkDescriptor, float f);

    public static final native long VgLinkRefPtr___deref__(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long VgLinkRefPtr___ref__(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long VgLinkRefPtr_get(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native int VgLinkRefPtr_getNbReferences(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long VgLinkRefPtr_getNull();

    public static final native boolean VgLinkRefPtr_isValid(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native void VgLinkRefPtr_ref(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long VgLinkRefPtr_set(long j, VgLinkRefPtr vgLinkRefPtr, long j2, VgLink vgLink);

    public static final native void VgLinkRefPtr_setVisible(long j, VgLinkRefPtr vgLinkRefPtr, boolean z);

    public static final native int VgLinkRefPtr_unref(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long VgLink_SWIGUpcast(long j);

    public static final native void VgLink_setVisible(long j, VgLink vgLink, boolean z);

    public static final native long VgLocationDoublePair_first_get(long j, VgLocationDoublePair vgLocationDoublePair);

    public static final native void VgLocationDoublePair_first_set(long j, VgLocationDoublePair vgLocationDoublePair, long j2, VgPosition vgPosition);

    public static final native double VgLocationDoublePair_second_get(long j, VgLocationDoublePair vgLocationDoublePair);

    public static final native void VgLocationDoublePair_second_set(long j, VgLocationDoublePair vgLocationDoublePair, double d);

    public static final native void VgLocationValuePairVector_add(long j, VgLocationValuePairVector vgLocationValuePairVector, long j2, VgLocationDoublePair vgLocationDoublePair);

    public static final native long VgLocationValuePairVector_capacity(long j, VgLocationValuePairVector vgLocationValuePairVector);

    public static final native void VgLocationValuePairVector_clear(long j, VgLocationValuePairVector vgLocationValuePairVector);

    public static final native long VgLocationValuePairVector_get(long j, VgLocationValuePairVector vgLocationValuePairVector, int i);

    public static final native boolean VgLocationValuePairVector_isEmpty(long j, VgLocationValuePairVector vgLocationValuePairVector);

    public static final native void VgLocationValuePairVector_reserve(long j, VgLocationValuePairVector vgLocationValuePairVector, long j2);

    public static final native void VgLocationValuePairVector_set(long j, VgLocationValuePairVector vgLocationValuePairVector, int i, long j2, VgLocationDoublePair vgLocationDoublePair);

    public static final native long VgLocationValuePairVector_size(long j, VgLocationValuePairVector vgLocationValuePairVector);

    public static final native long VgLoopModes_mscLoop_get();

    public static final native long VgLoopModes_mscNoLoop_get();

    public static final native long VgLoopModes_mscPingPong_get();

    public static final native long VgManipulatorListenerConstRefPtr___deref__(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native long VgManipulatorListenerConstRefPtr___ref__(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native long VgManipulatorListenerConstRefPtr_get(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native int VgManipulatorListenerConstRefPtr_getNbReferences(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native long VgManipulatorListenerConstRefPtr_getNull();

    public static final native boolean VgManipulatorListenerConstRefPtr_isValid(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native void VgManipulatorListenerConstRefPtr_ref(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native long VgManipulatorListenerConstRefPtr_set(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr, long j2, VgManipulatorListener vgManipulatorListener);

    public static final native int VgManipulatorListenerConstRefPtr_unref(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native long VgManipulatorListenerRefPtr___deref__(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long VgManipulatorListenerRefPtr___ref__(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long VgManipulatorListenerRefPtr_get(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native int VgManipulatorListenerRefPtr_getNbReferences(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long VgManipulatorListenerRefPtr_getNull();

    public static final native boolean VgManipulatorListenerRefPtr_isValid(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native void VgManipulatorListenerRefPtr_onClick(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr, float f, float f2);

    public static final native void VgManipulatorListenerRefPtr_onDoubleClick(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr, float f, float f2);

    public static final native void VgManipulatorListenerRefPtr_onSimpleDrag(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr, int i, long j2, float f, float f2);

    public static final native void VgManipulatorListenerRefPtr_onSimplePinch(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr, int i, float f, float f2);

    public static final native void VgManipulatorListenerRefPtr_ref(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long VgManipulatorListenerRefPtr_set(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr, long j2, VgManipulatorListener vgManipulatorListener);

    public static final native int VgManipulatorListenerRefPtr_unref(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long VgManipulatorListener_SWIGUpcast(long j);

    public static final native void VgManipulatorListener_change_ownership(VgManipulatorListener vgManipulatorListener, long j, boolean z);

    public static final native void VgManipulatorListener_director_connect(VgManipulatorListener vgManipulatorListener, long j, boolean z, boolean z2);

    public static final native void VgManipulatorListener_onClick(long j, VgManipulatorListener vgManipulatorListener, float f, float f2);

    public static final native void VgManipulatorListener_onDoubleClick(long j, VgManipulatorListener vgManipulatorListener, float f, float f2);

    public static final native void VgManipulatorListener_onSimpleDrag(long j, VgManipulatorListener vgManipulatorListener, int i, long j2, float f, float f2);

    public static final native void VgManipulatorListener_onSimplePinch(long j, VgManipulatorListener vgManipulatorListener, int i, float f, float f2);

    public static final native boolean VgManipulator_getBoundaries(long j, VgManipulator vgManipulator, long j2, VgPosition vgPosition, long j3, VgPosition vgPosition2);

    public static final native String VgManipulator_getType(long j, VgManipulator vgManipulator);

    public static final native void VgManipulator_setAnchor(long j, VgManipulator vgManipulator, long j2, VgPosition vgPosition);

    public static final native boolean VgManipulator_setBoundaries__SWIG_0(long j, VgManipulator vgManipulator, long j2, VgPosition vgPosition, long j3, VgPosition vgPosition2);

    public static final native boolean VgManipulator_setBoundaries__SWIG_1(long j, VgManipulator vgManipulator, long j2, VgPositionVector vgPositionVector, boolean z);

    public static final native void VgManipulator_setListener(long j, VgManipulator vgManipulator, long j2, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native void VgManipulator_setMaxRadius(long j, VgManipulator vgManipulator, double d);

    public static final native void VgManipulator_setMinRadius(long j, VgManipulator vgManipulator, double d);

    public static final native long VgMarkerConstRefPtr___deref__(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native long VgMarkerConstRefPtr___ref__(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native long VgMarkerConstRefPtr_get(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native int VgMarkerConstRefPtr_getNbReferences(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native long VgMarkerConstRefPtr_getNull();

    public static final native boolean VgMarkerConstRefPtr_isValid(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native void VgMarkerConstRefPtr_ref(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native long VgMarkerConstRefPtr_set(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr, long j2, VgMarker vgMarker);

    public static final native int VgMarkerConstRefPtr_unref(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native long VgMarkerDescriptorConstRefPtr___deref__(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native long VgMarkerDescriptorConstRefPtr___ref__(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native long VgMarkerDescriptorConstRefPtr_get(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native int VgMarkerDescriptorConstRefPtr_getNbReferences(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native long VgMarkerDescriptorConstRefPtr_getNull();

    public static final native int VgMarkerDescriptorConstRefPtr_getType(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native boolean VgMarkerDescriptorConstRefPtr_isValid(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native void VgMarkerDescriptorConstRefPtr_ref(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native long VgMarkerDescriptorConstRefPtr_set(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr, long j2, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native int VgMarkerDescriptorConstRefPtr_unref(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native long VgMarkerDescriptorRefPtr___deref__(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long VgMarkerDescriptorRefPtr___ref__(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long VgMarkerDescriptorRefPtr_get(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native int VgMarkerDescriptorRefPtr_getNbReferences(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long VgMarkerDescriptorRefPtr_getNull();

    public static final native int VgMarkerDescriptorRefPtr_getType(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native boolean VgMarkerDescriptorRefPtr_isValid(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native void VgMarkerDescriptorRefPtr_ref(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long VgMarkerDescriptorRefPtr_set(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr, long j2, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native int VgMarkerDescriptorRefPtr_unref(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native void VgMarkerDescriptorVector_add(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector, long j2, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long VgMarkerDescriptorVector_capacity(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector);

    public static final native void VgMarkerDescriptorVector_clear(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector);

    public static final native long VgMarkerDescriptorVector_get(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector, int i);

    public static final native boolean VgMarkerDescriptorVector_isEmpty(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector);

    public static final native void VgMarkerDescriptorVector_reserve(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector, long j2);

    public static final native void VgMarkerDescriptorVector_set(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector, int i, long j2, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long VgMarkerDescriptorVector_size(long j, VgMarkerDescriptorVector vgMarkerDescriptorVector);

    public static final native long VgMarkerDescriptor_SWIGUpcast(long j);

    public static final native int VgMarkerDescriptor_getType(long j, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native long VgMarkerRefPtr___deref__(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarkerRefPtr___ref__(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarkerRefPtr_asIconMarker(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarkerRefPtr_asTextMarker(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarkerRefPtr_get(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native int VgMarkerRefPtr_getNbReferences(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarkerRefPtr_getNull();

    public static final native boolean VgMarkerRefPtr_isValid(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native void VgMarkerRefPtr_ref(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarkerRefPtr_set(long j, VgMarkerRefPtr vgMarkerRefPtr, long j2, VgMarker vgMarker);

    public static final native int VgMarkerRefPtr_unref(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long VgMarker_SWIGUpcast(long j);

    public static final native long VgMarker_asIconMarker(long j, VgMarker vgMarker);

    public static final native long VgMarker_asTextMarker(long j, VgMarker vgMarker);

    public static final native long VgMatrixInterpolationFunctorDescriptorConstRefPtr___deref__(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorConstRefPtr___ref__(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorConstRefPtr_get(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgMatrixInterpolationFunctorDescriptorConstRefPtr_getNbReferences */
    public static final native int m1205x3a32c6e1(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgMatrixInterpolationFunctorDescriptorConstRefPtr_isValid(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native boolean VgMatrixInterpolationFunctorDescriptorConstRefPtr_mCubic_get(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native float[] VgMatrixInterpolationFunctorDescriptorConstRefPtr_mEndMatrix_get(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgMatrixInterpolationFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgMatrixInterpolationFunctorDescriptorConstRefPtr_mStartMatrix_get */
    public static final native float[] m1206x4aaebc7e(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgMatrixInterpolationFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native void VgMatrixInterpolationFunctorDescriptorConstRefPtr_ref(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorConstRefPtr_set(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr, long j2, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native int VgMatrixInterpolationFunctorDescriptorConstRefPtr_unref(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorRefPtr___deref__(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorRefPtr___ref__(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorRefPtr_create(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorRefPtr_get(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native int VgMatrixInterpolationFunctorDescriptorRefPtr_getNbReferences(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgMatrixInterpolationFunctorDescriptorRefPtr_isValid(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native boolean VgMatrixInterpolationFunctorDescriptorRefPtr_mCubic_get(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native void VgMatrixInterpolationFunctorDescriptorRefPtr_mCubic_set(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr, boolean z);

    public static final native float[] VgMatrixInterpolationFunctorDescriptorRefPtr_mEndMatrix_get(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native void VgMatrixInterpolationFunctorDescriptorRefPtr_mEndMatrix_set(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr, float[] fArr);

    public static final native float VgMatrixInterpolationFunctorDescriptorRefPtr_mEndTime_get(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native void VgMatrixInterpolationFunctorDescriptorRefPtr_mEndTime_set(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr, float f);

    public static final native float[] VgMatrixInterpolationFunctorDescriptorRefPtr_mStartMatrix_get(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native void VgMatrixInterpolationFunctorDescriptorRefPtr_mStartMatrix_set(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr, float[] fArr);

    public static final native float VgMatrixInterpolationFunctorDescriptorRefPtr_mStartTime_get(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native void VgMatrixInterpolationFunctorDescriptorRefPtr_mStartTime_set(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr, float f);

    public static final native void VgMatrixInterpolationFunctorDescriptorRefPtr_ref(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptorRefPtr_set(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr, long j2, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native int VgMatrixInterpolationFunctorDescriptorRefPtr_unref(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long VgMatrixInterpolationFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgMatrixInterpolationFunctorDescriptor_create();

    public static final native boolean VgMatrixInterpolationFunctorDescriptor_mCubic_get(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native void VgMatrixInterpolationFunctorDescriptor_mCubic_set(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor, boolean z);

    public static final native float[] VgMatrixInterpolationFunctorDescriptor_mEndMatrix_get(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native void VgMatrixInterpolationFunctorDescriptor_mEndMatrix_set(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor, float[] fArr);

    public static final native float[] VgMatrixInterpolationFunctorDescriptor_mStartMatrix_get(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native void VgMatrixInterpolationFunctorDescriptor_mStartMatrix_set(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor, float[] fArr);

    public static final native long VgMetricSRSConstRefPtr___deref__(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native long VgMetricSRSConstRefPtr___ref__(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native long VgMetricSRSConstRefPtr_get(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native int VgMetricSRSConstRefPtr_getNbReferences(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native long VgMetricSRSConstRefPtr_getNull();

    public static final native boolean VgMetricSRSConstRefPtr_isGeoReferenced(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native boolean VgMetricSRSConstRefPtr_isValid(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native void VgMetricSRSConstRefPtr_ref(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native long VgMetricSRSConstRefPtr_set(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr, long j2, VgMetricSRS vgMetricSRS);

    public static final native int VgMetricSRSConstRefPtr_unref(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native long VgMetricSRSRefPtr___deref__(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long VgMetricSRSRefPtr___ref__(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long VgMetricSRSRefPtr_get(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native int VgMetricSRSRefPtr_getNbReferences(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long VgMetricSRSRefPtr_getNull();

    public static final native boolean VgMetricSRSRefPtr_isGeoReferenced(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native boolean VgMetricSRSRefPtr_isValid(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native void VgMetricSRSRefPtr_ref(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long VgMetricSRSRefPtr_set(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr, long j2, VgMetricSRS vgMetricSRS);

    public static final native int VgMetricSRSRefPtr_unref(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long VgMetricSRS_SWIGUpcast(long j);

    public static final native long VgModelManager_createModel__SWIG_0(long j, VgModelManager vgModelManager, long j2, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long VgModelManager_createModel__SWIG_1(long j, VgModelManager vgModelManager, long j2, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr, String str);

    public static final native void VgNavigationModalitiesParametersMap_clear(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap);

    public static final native void VgNavigationModalitiesParametersMap_del(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap, String str);

    public static final native boolean VgNavigationModalitiesParametersMap_empty(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap);

    public static final native long VgNavigationModalitiesParametersMap_get(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap, String str);

    public static final native boolean VgNavigationModalitiesParametersMap_has_key(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap, String str);

    public static final native void VgNavigationModalitiesParametersMap_set(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap, String str, long j2, VgNavigationModalityParametersMap vgNavigationModalityParametersMap);

    public static final native long VgNavigationModalitiesParametersMap_size(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap);

    public static final native void VgNavigationModalityParametersMap_clear(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap);

    public static final native void VgNavigationModalityParametersMap_del(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap, int i);

    public static final native boolean VgNavigationModalityParametersMap_empty(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap);

    public static final native double VgNavigationModalityParametersMap_get(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap, int i);

    public static final native boolean VgNavigationModalityParametersMap_has_key(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap, int i);

    public static final native void VgNavigationModalityParametersMap_set(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap, int i, double d);

    public static final native long VgNavigationModalityParametersMap_size(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap);

    public static final native void VgNearPlaceVector_add(long j, VgNearPlaceVector vgNearPlaceVector, long j2, VgNearPlace vgNearPlace);

    public static final native long VgNearPlaceVector_capacity(long j, VgNearPlaceVector vgNearPlaceVector);

    public static final native void VgNearPlaceVector_clear(long j, VgNearPlaceVector vgNearPlaceVector);

    public static final native long VgNearPlaceVector_get(long j, VgNearPlaceVector vgNearPlaceVector, int i);

    public static final native boolean VgNearPlaceVector_isEmpty(long j, VgNearPlaceVector vgNearPlaceVector);

    public static final native void VgNearPlaceVector_reserve(long j, VgNearPlaceVector vgNearPlaceVector, long j2);

    public static final native void VgNearPlaceVector_set(long j, VgNearPlaceVector vgNearPlaceVector, int i, long j2, VgNearPlace vgNearPlace);

    public static final native long VgNearPlaceVector_size(long j, VgNearPlaceVector vgNearPlaceVector);

    public static final native double VgNearPlace_mAngle_get(long j, VgNearPlace vgNearPlace);

    public static final native void VgNearPlace_mAngle_set(long j, VgNearPlace vgNearPlace, double d);

    public static final native double VgNearPlace_mDistance_get(long j, VgNearPlace vgNearPlace);

    public static final native void VgNearPlace_mDistance_set(long j, VgNearPlace vgNearPlace, double d);

    public static final native String VgNearPlace_mID_get(long j, VgNearPlace vgNearPlace);

    public static final native void VgNearPlace_mID_set(long j, VgNearPlace vgNearPlace, String str);

    public static final native float VgNearPlacesParameters_mDistanceThreshold_get(long j, VgNearPlacesParameters vgNearPlacesParameters);

    public static final native void VgNearPlacesParameters_mDistanceThreshold_set(long j, VgNearPlacesParameters vgNearPlacesParameters, float f);

    public static final native double VgNearPlacesParameters_mHeading_get(long j, VgNearPlacesParameters vgNearPlacesParameters);

    public static final native void VgNearPlacesParameters_mHeading_set(long j, VgNearPlacesParameters vgNearPlacesParameters, double d);

    public static final native String VgNearPlacesParameters_mLayerName_get(long j, VgNearPlacesParameters vgNearPlacesParameters);

    public static final native void VgNearPlacesParameters_mLayerName_set(long j, VgNearPlacesParameters vgNearPlacesParameters, String str);

    public static final native int VgOrientationConstraints_mHeading_get(long j, VgOrientationConstraints vgOrientationConstraints);

    public static final native void VgOrientationConstraints_mHeading_set(long j, VgOrientationConstraints vgOrientationConstraints, int i);

    public static final native int VgOrientationConstraints_mPitch_get(long j, VgOrientationConstraints vgOrientationConstraints);

    public static final native void VgOrientationConstraints_mPitch_set(long j, VgOrientationConstraints vgOrientationConstraints, int i);

    public static final native int VgOrientationConstraints_mRoll_get(long j, VgOrientationConstraints vgOrientationConstraints);

    public static final native void VgOrientationConstraints_mRoll_set(long j, VgOrientationConstraints vgOrientationConstraints, int i);

    public static final native long VgOrientationDoublePair_first_get(long j, VgOrientationDoublePair vgOrientationDoublePair);

    public static final native void VgOrientationDoublePair_first_set(long j, VgOrientationDoublePair vgOrientationDoublePair, long j2, VgOrientation vgOrientation);

    public static final native double VgOrientationDoublePair_second_get(long j, VgOrientationDoublePair vgOrientationDoublePair);

    public static final native void VgOrientationDoublePair_second_set(long j, VgOrientationDoublePair vgOrientationDoublePair, double d);

    public static final native void VgOrientationValuePairVector_add(long j, VgOrientationValuePairVector vgOrientationValuePairVector, long j2, VgOrientationDoublePair vgOrientationDoublePair);

    public static final native long VgOrientationValuePairVector_capacity(long j, VgOrientationValuePairVector vgOrientationValuePairVector);

    public static final native void VgOrientationValuePairVector_clear(long j, VgOrientationValuePairVector vgOrientationValuePairVector);

    public static final native long VgOrientationValuePairVector_get(long j, VgOrientationValuePairVector vgOrientationValuePairVector, int i);

    public static final native boolean VgOrientationValuePairVector_isEmpty(long j, VgOrientationValuePairVector vgOrientationValuePairVector);

    public static final native void VgOrientationValuePairVector_reserve(long j, VgOrientationValuePairVector vgOrientationValuePairVector, long j2);

    public static final native void VgOrientationValuePairVector_set(long j, VgOrientationValuePairVector vgOrientationValuePairVector, int i, long j2, VgOrientationDoublePair vgOrientationDoublePair);

    public static final native long VgOrientationValuePairVector_size(long j, VgOrientationValuePairVector vgOrientationValuePairVector);

    public static final native float VgOrientation_mAzimuth_get(long j, VgOrientation vgOrientation);

    public static final native void VgOrientation_mAzimuth_set(long j, VgOrientation vgOrientation, float f);

    public static final native float VgOrientation_mPitch_get(long j, VgOrientation vgOrientation);

    public static final native void VgOrientation_mPitch_set(long j, VgOrientation vgOrientation, float f);

    public static final native float VgOrientation_mRoll_get(long j, VgOrientation vgOrientation);

    public static final native void VgOrientation_mRoll_set(long j, VgOrientation vgOrientation, float f);

    public static final native long VgPOIDescriptor_mBoundingPositions_get(long j, VgPOIDescriptor vgPOIDescriptor);

    public static final native void VgPOIDescriptor_mBoundingPositions_set(long j, VgPOIDescriptor vgPOIDescriptor, long j2, VgPositionVector vgPositionVector);

    public static final native long VgPOIDescriptor_mCenter_get(long j, VgPOIDescriptor vgPOIDescriptor);

    public static final native void VgPOIDescriptor_mCenter_set(long j, VgPOIDescriptor vgPOIDescriptor, long j2, VgPosition vgPosition);

    public static final native double VgPOIDescriptor_mHeading_get(long j, VgPOIDescriptor vgPOIDescriptor);

    public static final native void VgPOIDescriptor_mHeading_set(long j, VgPOIDescriptor vgPOIDescriptor, double d);

    public static final native double VgPOIDescriptor_mHeight_get(long j, VgPOIDescriptor vgPOIDescriptor);

    public static final native void VgPOIDescriptor_mHeight_set(long j, VgPOIDescriptor vgPOIDescriptor, double d);

    public static final native String VgPOIDescriptor_mLayerName_get(long j, VgPOIDescriptor vgPOIDescriptor);

    public static final native void VgPOIDescriptor_mLayerName_set(long j, VgPOIDescriptor vgPOIDescriptor, String str);

    public static final native long VgPlaceColorDescriptor_mBottomColor_get(long j, VgPlaceColorDescriptor vgPlaceColorDescriptor);

    public static final native void VgPlaceColorDescriptor_mBottomColor_set(long j, VgPlaceColorDescriptor vgPlaceColorDescriptor, long j2, VgColor vgColor);

    public static final native long VgPlaceColorDescriptor_mTopColor_get(long j, VgPlaceColorDescriptor vgPlaceColorDescriptor);

    public static final native void VgPlaceColorDescriptor_mTopColor_set(long j, VgPlaceColorDescriptor vgPlaceColorDescriptor, long j2, VgColor vgColor);

    public static final native String VgPlaceDescriptor_mDataset_get(long j, VgPlaceDescriptor vgPlaceDescriptor);

    public static final native void VgPlaceDescriptor_mDataset_set(long j, VgPlaceDescriptor vgPlaceDescriptor, String str);

    public static final native String VgPlaceDescriptor_mLayerName_get(long j, VgPlaceDescriptor vgPlaceDescriptor);

    public static final native void VgPlaceDescriptor_mLayerName_set(long j, VgPlaceDescriptor vgPlaceDescriptor, String str);

    public static final native long VgPlaceDescriptor_mViewpoint_get(long j, VgPlaceDescriptor vgPlaceDescriptor);

    public static final native void VgPlaceDescriptor_mViewpoint_set(long j, VgPlaceDescriptor vgPlaceDescriptor, long j2, VgIViewPoint vgIViewPoint);

    public static final native long VgPlaceIconDescriptor_mIcon_get(long j, VgPlaceIconDescriptor vgPlaceIconDescriptor);

    public static final native void VgPlaceIconDescriptor_mIcon_set(long j, VgPlaceIconDescriptor vgPlaceIconDescriptor, long j2, VgITextureRefPtr vgITextureRefPtr);

    public static final native double VgPlaceIconDescriptor_mScale_get(long j, VgPlaceIconDescriptor vgPlaceIconDescriptor);

    public static final native void VgPlaceIconDescriptor_mScale_set(long j, VgPlaceIconDescriptor vgPlaceIconDescriptor, double d);

    public static final native long VgPointConstRefPtr___deref__(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr___ref__(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_get(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native int VgPointConstRefPtr_getAltitudeMode(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native int VgPointConstRefPtr_getAnchorPosition(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getAnimation(long j, VgPointConstRefPtr vgPointConstRefPtr, String str);

    public static final native long VgPointConstRefPtr_getAnimationChannelValue(long j, VgPointConstRefPtr vgPointConstRefPtr, String str);

    public static final native void VgPointConstRefPtr_getAnimationNames(long j, VgPointConstRefPtr vgPointConstRefPtr, long j2, VgStringList vgStringList);

    public static final native void VgPointConstRefPtr_getBoundingRect(long j, VgPointConstRefPtr vgPointConstRefPtr, float[] fArr, float[] fArr2);

    public static final native float VgPointConstRefPtr_getGeometryConstantSizeDistance(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native String VgPointConstRefPtr_getID(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getLocalPosition(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getNbMarkers(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native int VgPointConstRefPtr_getNbReferences(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native boolean VgPointConstRefPtr_getNotifyPOISelectedOnClick(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getNull();

    public static final native long VgPointConstRefPtr_getOrientation(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getOrientationConstraints(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getPosition(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native float VgPointConstRefPtr_getScale(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native int VgPointConstRefPtr_getSizePolicy(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native int VgPointConstRefPtr_getType(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_getVisibilityRamp(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native int VgPointConstRefPtr_getZIndex(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native boolean VgPointConstRefPtr_isDrawnOnTop(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native boolean VgPointConstRefPtr_isForceFrontFaceEnabled(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native boolean VgPointConstRefPtr_isValid(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native boolean VgPointConstRefPtr_isVisible(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native void VgPointConstRefPtr_ref(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointConstRefPtr_set(long j, VgPointConstRefPtr vgPointConstRefPtr, long j2, VgPoint vgPoint);

    public static final native int VgPointConstRefPtr_unref(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr___deref__(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr___ref__(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_getNbReferences(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr_getNull();

    public static final native boolean VgPointDescriptorConstRefPtr_isValid(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mAltitudeMode_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mAnchorPosition_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native boolean VgPointDescriptorConstRefPtr_mDrawOnTop_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native boolean VgPointDescriptorConstRefPtr_mForceFrontFace_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mGeometryConstantSizeDistance_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mHeadingOrientationType_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mHeading_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native String VgPointDescriptorConstRefPtr_mID_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr_mMarkerDescriptors_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native boolean VgPointDescriptorConstRefPtr_mNotifyPOISelectedOnClick_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mPitchOrientationType_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mPitch_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr_mPosition_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mRectangleHeight_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mRectangleWidth_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mRollOrientationType_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mRoll_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native float VgPointDescriptorConstRefPtr_mScale_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mSizePolicy_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native double VgPointDescriptorConstRefPtr_mVisibilityRampFullyInvisible_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native double VgPointDescriptorConstRefPtr_mVisibilityRampFullyVisible_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native double VgPointDescriptorConstRefPtr_mVisibilityRampStartInvisible_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native double VgPointDescriptorConstRefPtr_mVisibilityRampStartVisible_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native int VgPointDescriptorConstRefPtr_mZIndex_get(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native void VgPointDescriptorConstRefPtr_ref(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorConstRefPtr_set(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr, long j2, VgPointDescriptor vgPointDescriptor);

    public static final native int VgPointDescriptorConstRefPtr_unref(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long VgPointDescriptorRefPtr___deref__(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorRefPtr___ref__(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorRefPtr_create__SWIG_0(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorRefPtr_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native int VgPointDescriptorRefPtr_getNbReferences(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorRefPtr_getNull();

    public static final native boolean VgPointDescriptorRefPtr_isValid(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native int VgPointDescriptorRefPtr_mAltitudeMode_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mAltitudeMode_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native int VgPointDescriptorRefPtr_mAnchorPosition_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mAnchorPosition_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native boolean VgPointDescriptorRefPtr_mDrawOnTop_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mDrawOnTop_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, boolean z);

    public static final native boolean VgPointDescriptorRefPtr_mForceFrontFace_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mForceFrontFace_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, boolean z);

    public static final native float VgPointDescriptorRefPtr_mGeometryConstantSizeDistance_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mGeometryConstantSizeDistance_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native int VgPointDescriptorRefPtr_mHeadingOrientationType_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mHeadingOrientationType_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native float VgPointDescriptorRefPtr_mHeading_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mHeading_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native String VgPointDescriptorRefPtr_mID_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mID_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, String str);

    public static final native long VgPointDescriptorRefPtr_mMarkerDescriptors_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mMarkerDescriptors_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, long j2, VgMarkerDescriptorVector vgMarkerDescriptorVector);

    public static final native boolean VgPointDescriptorRefPtr_mNotifyPOISelectedOnClick_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mNotifyPOISelectedOnClick_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, boolean z);

    public static final native int VgPointDescriptorRefPtr_mPitchOrientationType_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mPitchOrientationType_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native float VgPointDescriptorRefPtr_mPitch_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mPitch_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native long VgPointDescriptorRefPtr_mPosition_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mPosition_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, long j2, VgPosition vgPosition);

    public static final native float VgPointDescriptorRefPtr_mRectangleHeight_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mRectangleHeight_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native float VgPointDescriptorRefPtr_mRectangleWidth_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mRectangleWidth_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native int VgPointDescriptorRefPtr_mRollOrientationType_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mRollOrientationType_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native float VgPointDescriptorRefPtr_mRoll_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mRoll_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native float VgPointDescriptorRefPtr_mScale_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mScale_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, float f);

    public static final native int VgPointDescriptorRefPtr_mSizePolicy_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mSizePolicy_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native double VgPointDescriptorRefPtr_mVisibilityRampFullyInvisible_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mVisibilityRampFullyInvisible_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, double d);

    public static final native double VgPointDescriptorRefPtr_mVisibilityRampFullyVisible_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mVisibilityRampFullyVisible_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, double d);

    public static final native double VgPointDescriptorRefPtr_mVisibilityRampStartInvisible_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mVisibilityRampStartInvisible_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, double d);

    public static final native double VgPointDescriptorRefPtr_mVisibilityRampStartVisible_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mVisibilityRampStartVisible_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, double d);

    public static final native int VgPointDescriptorRefPtr_mZIndex_get(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorRefPtr_mZIndex_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, int i);

    public static final native void VgPointDescriptorRefPtr_ref(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorRefPtr_set(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr, long j2, VgPointDescriptor vgPointDescriptor);

    public static final native int VgPointDescriptorRefPtr_unref(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native void VgPointDescriptorVector_add(long j, VgPointDescriptorVector vgPointDescriptorVector, long j2, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorVector_capacity(long j, VgPointDescriptorVector vgPointDescriptorVector);

    public static final native void VgPointDescriptorVector_clear(long j, VgPointDescriptorVector vgPointDescriptorVector);

    public static final native long VgPointDescriptorVector_get(long j, VgPointDescriptorVector vgPointDescriptorVector, int i);

    public static final native boolean VgPointDescriptorVector_isEmpty(long j, VgPointDescriptorVector vgPointDescriptorVector);

    public static final native void VgPointDescriptorVector_reserve(long j, VgPointDescriptorVector vgPointDescriptorVector, long j2);

    public static final native void VgPointDescriptorVector_set(long j, VgPointDescriptorVector vgPointDescriptorVector, int i, long j2, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long VgPointDescriptorVector_size(long j, VgPointDescriptorVector vgPointDescriptorVector);

    public static final native long VgPointDescriptor_SWIGUpcast(long j);

    public static final native long VgPointDescriptor_create__SWIG_0();

    public static final native long VgPointDescriptor_create__SWIG_1(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native int VgPointDescriptor_mAltitudeMode_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mAltitudeMode_set(long j, VgPointDescriptor vgPointDescriptor, int i);

    public static final native int VgPointDescriptor_mAnchorPosition_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mAnchorPosition_set(long j, VgPointDescriptor vgPointDescriptor, int i);

    public static final native boolean VgPointDescriptor_mForceFrontFace_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mForceFrontFace_set(long j, VgPointDescriptor vgPointDescriptor, boolean z);

    public static final native float VgPointDescriptor_mGeometryConstantSizeDistance_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mGeometryConstantSizeDistance_set(long j, VgPointDescriptor vgPointDescriptor, float f);

    public static final native int VgPointDescriptor_mHeadingOrientationType_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mHeadingOrientationType_set(long j, VgPointDescriptor vgPointDescriptor, int i);

    public static final native float VgPointDescriptor_mHeading_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mHeading_set(long j, VgPointDescriptor vgPointDescriptor, float f);

    public static final native String VgPointDescriptor_mID_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mID_set(long j, VgPointDescriptor vgPointDescriptor, String str);

    public static final native long VgPointDescriptor_mMarkerDescriptors_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mMarkerDescriptors_set(long j, VgPointDescriptor vgPointDescriptor, long j2, VgMarkerDescriptorVector vgMarkerDescriptorVector);

    public static final native int VgPointDescriptor_mPitchOrientationType_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mPitchOrientationType_set(long j, VgPointDescriptor vgPointDescriptor, int i);

    public static final native float VgPointDescriptor_mPitch_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mPitch_set(long j, VgPointDescriptor vgPointDescriptor, float f);

    public static final native long VgPointDescriptor_mPosition_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mPosition_set(long j, VgPointDescriptor vgPointDescriptor, long j2, VgPosition vgPosition);

    public static final native float VgPointDescriptor_mRectangleHeight_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mRectangleHeight_set(long j, VgPointDescriptor vgPointDescriptor, float f);

    public static final native float VgPointDescriptor_mRectangleWidth_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mRectangleWidth_set(long j, VgPointDescriptor vgPointDescriptor, float f);

    public static final native int VgPointDescriptor_mRollOrientationType_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mRollOrientationType_set(long j, VgPointDescriptor vgPointDescriptor, int i);

    public static final native float VgPointDescriptor_mRoll_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mRoll_set(long j, VgPointDescriptor vgPointDescriptor, float f);

    public static final native int VgPointDescriptor_mSizePolicy_get(long j, VgPointDescriptor vgPointDescriptor);

    public static final native void VgPointDescriptor_mSizePolicy_set(long j, VgPointDescriptor vgPointDescriptor, int i);

    public static final native long VgPointRefPtr___deref__(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr___ref__(long j, VgPointRefPtr vgPointRefPtr);

    public static final native void VgPointRefPtr_addListener(long j, VgPointRefPtr vgPointRefPtr, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long VgPointRefPtr_asGeometry(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_asLine(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_asPoint(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_editAnimation(long j, VgPointRefPtr vgPointRefPtr, String str);

    public static final native long VgPointRefPtr_editMarker(long j, VgPointRefPtr vgPointRefPtr, long j2);

    public static final native long VgPointRefPtr_get(long j, VgPointRefPtr vgPointRefPtr);

    public static final native int VgPointRefPtr_getAltitudeMode(long j, VgPointRefPtr vgPointRefPtr);

    public static final native int VgPointRefPtr_getAnchorPosition(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getAnimation(long j, VgPointRefPtr vgPointRefPtr, String str);

    public static final native long VgPointRefPtr_getAnimationChannelValue(long j, VgPointRefPtr vgPointRefPtr, String str);

    public static final native void VgPointRefPtr_getAnimationNames(long j, VgPointRefPtr vgPointRefPtr, long j2, VgStringList vgStringList);

    public static final native boolean VgPointRefPtr_getBoundingPositions(long j, VgPointRefPtr vgPointRefPtr, long j2, VgPositionVector vgPositionVector);

    public static final native void VgPointRefPtr_getBoundingRect(long j, VgPointRefPtr vgPointRefPtr, float[] fArr, float[] fArr2);

    public static final native float VgPointRefPtr_getGeometryConstantSizeDistance(long j, VgPointRefPtr vgPointRefPtr);

    public static final native String VgPointRefPtr_getID(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getLayer(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getLocalPosition(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getNbMarkers(long j, VgPointRefPtr vgPointRefPtr);

    public static final native int VgPointRefPtr_getNbReferences(long j, VgPointRefPtr vgPointRefPtr);

    public static final native boolean VgPointRefPtr_getNotifyPOISelectedOnClick(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getNull();

    public static final native long VgPointRefPtr_getOrientation(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getOrientationConstraints(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getPosition(long j, VgPointRefPtr vgPointRefPtr);

    public static final native float VgPointRefPtr_getScale(long j, VgPointRefPtr vgPointRefPtr);

    public static final native int VgPointRefPtr_getSizePolicy(long j, VgPointRefPtr vgPointRefPtr);

    public static final native int VgPointRefPtr_getType(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPointRefPtr_getVisibilityRamp(long j, VgPointRefPtr vgPointRefPtr);

    public static final native int VgPointRefPtr_getZIndex(long j, VgPointRefPtr vgPointRefPtr);

    public static final native boolean VgPointRefPtr_insertMarker(long j, VgPointRefPtr vgPointRefPtr, long j2, VgMarkerDescriptor vgMarkerDescriptor, int i);

    public static final native boolean VgPointRefPtr_isDrawnOnTop(long j, VgPointRefPtr vgPointRefPtr);

    public static final native boolean VgPointRefPtr_isForceFrontFaceEnabled(long j, VgPointRefPtr vgPointRefPtr);

    public static final native boolean VgPointRefPtr_isValid(long j, VgPointRefPtr vgPointRefPtr);

    public static final native boolean VgPointRefPtr_isVisible(long j, VgPointRefPtr vgPointRefPtr);

    public static final native void VgPointRefPtr_ref(long j, VgPointRefPtr vgPointRefPtr);

    public static final native void VgPointRefPtr_removeListener(long j, VgPointRefPtr vgPointRefPtr, long j2, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native boolean VgPointRefPtr_removeMarker(long j, VgPointRefPtr vgPointRefPtr, int i);

    public static final native long VgPointRefPtr_set(long j, VgPointRefPtr vgPointRefPtr, long j2, VgPoint vgPoint);

    public static final native void VgPointRefPtr_setAltitudeMode(long j, VgPointRefPtr vgPointRefPtr, int i);

    public static final native void VgPointRefPtr_setAnchorPosition(long j, VgPointRefPtr vgPointRefPtr, int i);

    public static final native void VgPointRefPtr_setAnimation__SWIG_0(long j, VgPointRefPtr vgPointRefPtr, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgPointRefPtr_setAnimation__SWIG_1(long j, VgPointRefPtr vgPointRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgPointRefPtr_setBoundingRect(long j, VgPointRefPtr vgPointRefPtr, float f, float f2);

    public static final native void VgPointRefPtr_setDrawOnTop(long j, VgPointRefPtr vgPointRefPtr, boolean z);

    public static final native void VgPointRefPtr_setForceFrontFace(long j, VgPointRefPtr vgPointRefPtr, boolean z);

    public static final native void VgPointRefPtr_setGeometryConstantSizeDistance(long j, VgPointRefPtr vgPointRefPtr, float f);

    public static final native void VgPointRefPtr_setLayer__SWIG_0(long j, VgPointRefPtr vgPointRefPtr, long j2, VgLayerRefPtr vgLayerRefPtr, boolean z);

    public static final native void VgPointRefPtr_setLayer__SWIG_1(long j, VgPointRefPtr vgPointRefPtr, long j2, VgLayerRefPtr vgLayerRefPtr);

    public static final native void VgPointRefPtr_setLocalAnimation(long j, VgPointRefPtr vgPointRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgPointRefPtr_setLocalPosition(long j, VgPointRefPtr vgPointRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgPointRefPtr_setNotifyPOISelectedOnClick(long j, VgPointRefPtr vgPointRefPtr, boolean z);

    public static final native void VgPointRefPtr_setOrientation(long j, VgPointRefPtr vgPointRefPtr, long j2, VgOrientation vgOrientation);

    public static final native void VgPointRefPtr_setOrientationConstraints(long j, VgPointRefPtr vgPointRefPtr, long j2, VgOrientationConstraints vgOrientationConstraints);

    public static final native void VgPointRefPtr_setPosition__SWIG_0(long j, VgPointRefPtr vgPointRefPtr, long j2, VgPosition vgPosition, boolean z);

    public static final native void VgPointRefPtr_setPosition__SWIG_1(long j, VgPointRefPtr vgPointRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgPointRefPtr_setScale(long j, VgPointRefPtr vgPointRefPtr, float f);

    public static final native boolean VgPointRefPtr_setSizePolicy(long j, VgPointRefPtr vgPointRefPtr, int i);

    public static final native void VgPointRefPtr_setVisibilityRamp(long j, VgPointRefPtr vgPointRefPtr, long j2, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgPointRefPtr_setVisible(long j, VgPointRefPtr vgPointRefPtr, boolean z);

    public static final native void VgPointRefPtr_setZIndex(long j, VgPointRefPtr vgPointRefPtr, int i);

    public static final native int VgPointRefPtr_unref(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long VgPoint_SWIGUpcast(long j);

    public static final native long VgPoint_asLine(long j, VgPoint vgPoint);

    public static final native long VgPoint_asPoint(long j, VgPoint vgPoint);

    public static final native long VgPoint_editMarker(long j, VgPoint vgPoint, long j2);

    public static final native int VgPoint_getAltitudeMode(long j, VgPoint vgPoint);

    public static final native int VgPoint_getAnchorPosition(long j, VgPoint vgPoint);

    public static final native void VgPoint_getBoundingRect(long j, VgPoint vgPoint, float[] fArr, float[] fArr2);

    public static final native float VgPoint_getGeometryConstantSizeDistance(long j, VgPoint vgPoint);

    public static final native long VgPoint_getNbMarkers(long j, VgPoint vgPoint);

    public static final native long VgPoint_getOrientationConstraints(long j, VgPoint vgPoint);

    public static final native int VgPoint_getSizePolicy(long j, VgPoint vgPoint);

    public static final native int VgPoint_getType(long j, VgPoint vgPoint);

    public static final native boolean VgPoint_insertMarker(long j, VgPoint vgPoint, long j2, VgMarkerDescriptor vgMarkerDescriptor, int i);

    public static final native boolean VgPoint_isForceFrontFaceEnabled(long j, VgPoint vgPoint);

    public static final native boolean VgPoint_removeMarker(long j, VgPoint vgPoint, int i);

    public static final native void VgPoint_setAltitudeMode(long j, VgPoint vgPoint, int i);

    public static final native void VgPoint_setAnchorPosition(long j, VgPoint vgPoint, int i);

    public static final native void VgPoint_setBoundingRect(long j, VgPoint vgPoint, float f, float f2);

    public static final native void VgPoint_setForceFrontFace(long j, VgPoint vgPoint, boolean z);

    public static final native void VgPoint_setGeometryConstantSizeDistance(long j, VgPoint vgPoint, float f);

    public static final native void VgPoint_setOrientationConstraints(long j, VgPoint vgPoint, long j2, VgOrientationConstraints vgOrientationConstraints);

    public static final native boolean VgPoint_setSizePolicy(long j, VgPoint vgPoint, int i);

    public static final native double VgPositionToolbox_computeDistance__SWIG_0(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPositionVector vgPositionVector);

    public static final native double VgPositionToolbox_computeDistance__SWIG_1(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgPosition vgPosition2);

    public static final native double VgPositionToolbox_computeHeadingAngle(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgPosition vgPosition2, long j4, VgPosition vgPosition3);

    public static final native long VgPositionToolbox_computeMiddlePoint(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgPosition vgPosition2);

    public static final native double VgPositionToolbox_computePitchAngle(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgPosition vgPosition2, long j4, VgPosition vgPosition3);

    public static final native void VgPositionToolbox_convert(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgPositionToolbox_editGeoReferencedSRS(long j, VgPositionToolbox vgPositionToolbox);

    public static final native long VgPositionToolbox_editSceneSRS(long j, VgPositionToolbox vgPositionToolbox);

    public static final native void VgPositionToolbox_geoConvert(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgPositionToolbox_getGeoReferencedSRS(long j, VgPositionToolbox vgPositionToolbox);

    public static final native long VgPositionToolbox_getSceneSRS(long j, VgPositionToolbox vgPositionToolbox);

    public static final native boolean VgPositionToolbox_isInside2D(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, long j3, VgPositionVector vgPositionVector);

    public static final native long VgPositionToolbox_offsetPosition(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPosition vgPosition, double d, double d2, double d3);

    public static final native long VgPositionToolbox_simplifyLineForWidth(long j, VgPositionToolbox vgPositionToolbox, long j2, VgPositionVector vgPositionVector, double d);

    public static final native void VgPositionVector_add(long j, VgPositionVector vgPositionVector, long j2, VgPosition vgPosition);

    public static final native long VgPositionVector_capacity(long j, VgPositionVector vgPositionVector);

    public static final native void VgPositionVector_clear(long j, VgPositionVector vgPositionVector);

    public static final native long VgPositionVector_get(long j, VgPositionVector vgPositionVector, int i);

    public static final native boolean VgPositionVector_isEmpty(long j, VgPositionVector vgPositionVector);

    public static final native void VgPositionVector_reserve(long j, VgPositionVector vgPositionVector, long j2);

    public static final native void VgPositionVector_set(long j, VgPositionVector vgPositionVector, int i, long j2, VgPosition vgPosition);

    public static final native long VgPositionVector_size(long j, VgPositionVector vgPositionVector);

    public static final native long VgPosition_mSRS_get(long j, VgPosition vgPosition);

    public static final native void VgPosition_mSRS_set(long j, VgPosition vgPosition, long j2, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native double VgPosition_mXOrLongitude_get(long j, VgPosition vgPosition);

    public static final native void VgPosition_mXOrLongitude_set(long j, VgPosition vgPosition, double d);

    public static final native double VgPosition_mYOrLatitude_get(long j, VgPosition vgPosition);

    public static final native void VgPosition_mYOrLatitude_set(long j, VgPosition vgPosition, double d);

    public static final native double VgPosition_mZOrAltitude_get(long j, VgPosition vgPosition);

    public static final native void VgPosition_mZOrAltitude_set(long j, VgPosition vgPosition, double d);

    public static final native void VgPostDrawCallbacks_add(long j, VgPostDrawCallbacks vgPostDrawCallbacks, long j2, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgPostDrawCallbacks_capacity(long j, VgPostDrawCallbacks vgPostDrawCallbacks);

    public static final native void VgPostDrawCallbacks_clear(long j, VgPostDrawCallbacks vgPostDrawCallbacks);

    public static final native long VgPostDrawCallbacks_get(long j, VgPostDrawCallbacks vgPostDrawCallbacks, int i);

    public static final native boolean VgPostDrawCallbacks_isEmpty(long j, VgPostDrawCallbacks vgPostDrawCallbacks);

    public static final native void VgPostDrawCallbacks_reserve(long j, VgPostDrawCallbacks vgPostDrawCallbacks, long j2);

    public static final native void VgPostDrawCallbacks_set(long j, VgPostDrawCallbacks vgPostDrawCallbacks, int i, long j2, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long VgPostDrawCallbacks_size(long j, VgPostDrawCallbacks vgPostDrawCallbacks);

    public static final native long VgQuadtreeLayoutDescriptor_SWIGUpcast(long j);

    public static final native long VgQuadtreeLayoutDescriptor_mNumLevels_get(long j, VgQuadtreeLayoutDescriptor vgQuadtreeLayoutDescriptor);

    public static final native void VgQuadtreeLayoutDescriptor_mNumLevels_set(long j, VgQuadtreeLayoutDescriptor vgQuadtreeLayoutDescriptor, long j2);

    public static final native float VgQuadtreeLayoutDescriptor_mRootSize_get(long j, VgQuadtreeLayoutDescriptor vgQuadtreeLayoutDescriptor);

    public static final native void VgQuadtreeLayoutDescriptor_mRootSize_set(long j, VgQuadtreeLayoutDescriptor vgQuadtreeLayoutDescriptor, float f);

    public static final native long VgQuaternionInterpolationFunctorDescriptorConstRefPtr___deref__(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorConstRefPtr___ref__(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorConstRefPtr_get(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorConstRefPtr_getNbReferences */
    public static final native int m1207x9a80aa4(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgQuaternionInterpolationFunctorDescriptorConstRefPtr_isValid(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native boolean VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mCubic_get(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mEndOrientation_get */
    public static final native long m1208x1de3612b(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mEndTime_get */
    public static final native float m1209x712fe2a0(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mExtraSpins_get */
    public static final native int m1210xd596caf7(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mStartOrientation_get */
    public static final native long m1211x701f7b04(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorConstRefPtr_mStartTime_get */
    public static final native float m1212x25543ae7(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native void VgQuaternionInterpolationFunctorDescriptorConstRefPtr_ref(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorConstRefPtr_set(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr, long j2, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native int VgQuaternionInterpolationFunctorDescriptorConstRefPtr_unref(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorRefPtr___deref__(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorRefPtr___ref__(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorRefPtr_create(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorRefPtr_get(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native int VgQuaternionInterpolationFunctorDescriptorRefPtr_getNbReferences(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgQuaternionInterpolationFunctorDescriptorRefPtr_isValid(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native boolean VgQuaternionInterpolationFunctorDescriptorRefPtr_mCubic_get(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native void VgQuaternionInterpolationFunctorDescriptorRefPtr_mCubic_set(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, boolean z);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorRefPtr_mEndOrientation_get */
    public static final native long m1213x79bcf9a2(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorRefPtr_mEndOrientation_set */
    public static final native void m1214x79bd26ae(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, long j2, VgOrientation vgOrientation);

    public static final native float VgQuaternionInterpolationFunctorDescriptorRefPtr_mEndTime_get(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native void VgQuaternionInterpolationFunctorDescriptorRefPtr_mEndTime_set(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, float f);

    public static final native int VgQuaternionInterpolationFunctorDescriptorRefPtr_mExtraSpins_get(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native void VgQuaternionInterpolationFunctorDescriptorRefPtr_mExtraSpins_set(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, int i);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorRefPtr_mStartOrientation_get */
    public static final native long m1215x3bf4d1bb(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    /* renamed from: VgQuaternionInterpolationFunctorDescriptorRefPtr_mStartOrientation_set */
    public static final native void m1216x3bf4fec7(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, long j2, VgOrientation vgOrientation);

    public static final native float VgQuaternionInterpolationFunctorDescriptorRefPtr_mStartTime_get(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native void VgQuaternionInterpolationFunctorDescriptorRefPtr_mStartTime_set(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, float f);

    public static final native void VgQuaternionInterpolationFunctorDescriptorRefPtr_ref(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptorRefPtr_set(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr, long j2, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native int VgQuaternionInterpolationFunctorDescriptorRefPtr_unref(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long VgQuaternionInterpolationFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgQuaternionInterpolationFunctorDescriptor_create();

    public static final native boolean VgQuaternionInterpolationFunctorDescriptor_mCubic_get(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native void VgQuaternionInterpolationFunctorDescriptor_mCubic_set(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor, boolean z);

    public static final native long VgQuaternionInterpolationFunctorDescriptor_mEndOrientation_get(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native void VgQuaternionInterpolationFunctorDescriptor_mEndOrientation_set(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor, long j2, VgOrientation vgOrientation);

    public static final native int VgQuaternionInterpolationFunctorDescriptor_mExtraSpins_get(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native void VgQuaternionInterpolationFunctorDescriptor_mExtraSpins_set(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor, int i);

    public static final native long VgQuaternionInterpolationFunctorDescriptor_mStartOrientation_get(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native void VgQuaternionInterpolationFunctorDescriptor_mStartOrientation_set(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor, long j2, VgOrientation vgOrientation);

    public static final native int VgQuery_eEquals_get();

    public static final native long VgQuery_reset(long j, VgQuery vgQuery);

    public static final native long VgQuery_where(long j, VgQuery vgQuery, String str, int i, String str2);

    public static final native long VgReferencedConstRefPtr___deref__(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native long VgReferencedConstRefPtr___ref__(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native long VgReferencedConstRefPtr_get(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native int VgReferencedConstRefPtr_getNbReferences(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native long VgReferencedConstRefPtr_getNull();

    public static final native boolean VgReferencedConstRefPtr_isValid(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native void VgReferencedConstRefPtr_ref(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native long VgReferencedConstRefPtr_set(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr, long j2, VgReferenced vgReferenced);

    public static final native int VgReferencedConstRefPtr_unref(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native long VgReferencedRefPtr___deref__(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native long VgReferencedRefPtr___ref__(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native long VgReferencedRefPtr_get(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native int VgReferencedRefPtr_getNbReferences(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native long VgReferencedRefPtr_getNull();

    public static final native boolean VgReferencedRefPtr_isValid(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native void VgReferencedRefPtr_ref(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native long VgReferencedRefPtr_set(long j, VgReferencedRefPtr vgReferencedRefPtr, long j2, VgReferenced vgReferenced);

    public static final native int VgReferencedRefPtr_unref(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native int VgReferenced_getNbReferences(long j, VgReferenced vgReferenced);

    public static final native void VgReferenced_ref(long j, VgReferenced vgReferenced);

    public static final native int VgReferenced_unref(long j, VgReferenced vgReferenced);

    public static final native long VgResourceRequestParameters_mCallback_get(long j, VgResourceRequestParameters vgResourceRequestParameters);

    public static final native void VgResourceRequestParameters_mCallback_set(long j, VgResourceRequestParameters vgResourceRequestParameters, long j2, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native String VgResourceRequestParameters_mResourceURI_get(long j, VgResourceRequestParameters vgResourceRequestParameters);

    public static final native void VgResourceRequestParameters_mResourceURI_set(long j, VgResourceRequestParameters vgResourceRequestParameters, String str);

    public static final native long VgSRSConstRefPtr___deref__(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgSRSConstRefPtr___ref__(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgSRSConstRefPtr_get(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native int VgSRSConstRefPtr_getNbReferences(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgSRSConstRefPtr_getNull();

    public static final native boolean VgSRSConstRefPtr_isGeoReferenced(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native boolean VgSRSConstRefPtr_isValid(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native void VgSRSConstRefPtr_ref(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgSRSConstRefPtr_set(long j, VgSRSConstRefPtr vgSRSConstRefPtr, long j2, VgSRS vgSRS);

    public static final native int VgSRSConstRefPtr_unref(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long VgSRSRefPtr___deref__(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long VgSRSRefPtr___ref__(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long VgSRSRefPtr_get(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native int VgSRSRefPtr_getNbReferences(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long VgSRSRefPtr_getNull();

    public static final native boolean VgSRSRefPtr_isGeoReferenced(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native boolean VgSRSRefPtr_isValid(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native void VgSRSRefPtr_ref(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long VgSRSRefPtr_set(long j, VgSRSRefPtr vgSRSRefPtr, long j2, VgSRS vgSRS);

    public static final native int VgSRSRefPtr_unref(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long VgSRS_SWIGUpcast(long j);

    public static final native boolean VgSRS_isGeoReferenced(long j, VgSRS vgSRS);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr___deref__(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr___ref__(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    /* renamed from: VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_getNbReferences */
    public static final native int m1217x64a1d55(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_isValid(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    /* renamed from: VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mBaseVector_get */
    public static final native float[] m1218x164f295b(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    /* renamed from: VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mEndPhase_get */
    public static final native double m1219x5ffc9547(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    /* renamed from: VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mEndTime_get */
    public static final native float m1220x98a1358f(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    /* renamed from: VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mStartPhase_get */
    public static final native double m1221x664c4da0(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    /* renamed from: VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mStartTime_get */
    public static final native float m1222x35bc8e16(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native float[] VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_mVector_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_ref(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr, long j2, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native int VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr_unref(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorRefPtr___deref__(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorRefPtr___ref__(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_create(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native int VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_getNbReferences(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_isValid(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native float[] VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mBaseVector_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mBaseVector_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, float[] fArr);

    public static final native double VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndPhase_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndPhase_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, double d);

    public static final native float VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndTime_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mEndTime_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, float f);

    public static final native double VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartPhase_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartPhase_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, double d);

    public static final native float VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartTime_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mStartTime_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, float f);

    public static final native float[] VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mVector_get(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_mVector_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, float[] fArr);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_ref(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_set(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr, long j2, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native int VgSinusoidalVectorOffsetFunctorDescriptorRefPtr_unref(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgSinusoidalVectorOffsetFunctorDescriptor_create();

    public static final native float[] VgSinusoidalVectorOffsetFunctorDescriptor_mBaseVector_get(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptor_mBaseVector_set(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor, float[] fArr);

    public static final native double VgSinusoidalVectorOffsetFunctorDescriptor_mEndPhase_get(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptor_mEndPhase_set(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor, double d);

    public static final native double VgSinusoidalVectorOffsetFunctorDescriptor_mStartPhase_get(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptor_mStartPhase_set(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor, double d);

    public static final native float[] VgSinusoidalVectorOffsetFunctorDescriptor_mVector_get(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native void VgSinusoidalVectorOffsetFunctorDescriptor_mVector_set(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor, float[] fArr);

    public static final native long VgSpatialConstRefPtr___deref__(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long VgSpatialConstRefPtr___ref__(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long VgSpatialConstRefPtr_get(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long VgSpatialConstRefPtr_getAnimation(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr, String str);

    public static final native long VgSpatialConstRefPtr_getAnimationChannelValue(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr, String str);

    public static final native void VgSpatialConstRefPtr_getAnimationNames(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr, long j2, VgStringList vgStringList);

    public static final native int VgSpatialConstRefPtr_getNbReferences(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long VgSpatialConstRefPtr_getNull();

    public static final native long VgSpatialConstRefPtr_getOrientation(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long VgSpatialConstRefPtr_getPosition(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native float VgSpatialConstRefPtr_getScale(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native int VgSpatialConstRefPtr_getZIndex(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native boolean VgSpatialConstRefPtr_isDrawnOnTop(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native boolean VgSpatialConstRefPtr_isValid(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native boolean VgSpatialConstRefPtr_isVisible(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native void VgSpatialConstRefPtr_ref(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long VgSpatialConstRefPtr_set(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr, long j2, VgSpatial vgSpatial);

    public static final native int VgSpatialConstRefPtr_unref(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native void VgSpatialList_add(long j, VgSpatialList vgSpatialList, long j2, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native void VgSpatialList_clear(long j, VgSpatialList vgSpatialList);

    public static final native long VgSpatialList_get(long j, VgSpatialList vgSpatialList, int i);

    public static final native boolean VgSpatialList_isEmpty(long j, VgSpatialList vgSpatialList);

    public static final native long VgSpatialList_size(long j, VgSpatialList vgSpatialList);

    public static final native long VgSpatialRefPtr___deref__(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr___ref__(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_asGeometry(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_asLine(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_asPoint(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_editAnimation(long j, VgSpatialRefPtr vgSpatialRefPtr, String str);

    public static final native long VgSpatialRefPtr_get(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_getAnimation(long j, VgSpatialRefPtr vgSpatialRefPtr, String str);

    public static final native long VgSpatialRefPtr_getAnimationChannelValue(long j, VgSpatialRefPtr vgSpatialRefPtr, String str);

    public static final native void VgSpatialRefPtr_getAnimationNames(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgStringList vgStringList);

    public static final native int VgSpatialRefPtr_getNbReferences(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_getNull();

    public static final native long VgSpatialRefPtr_getOrientation(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_getPosition(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native float VgSpatialRefPtr_getScale(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native int VgSpatialRefPtr_getZIndex(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native boolean VgSpatialRefPtr_isDrawnOnTop(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native boolean VgSpatialRefPtr_isValid(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native boolean VgSpatialRefPtr_isVisible(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native void VgSpatialRefPtr_ref(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatialRefPtr_set(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgSpatial vgSpatial);

    public static final native void VgSpatialRefPtr_setAnimation__SWIG_0(long j, VgSpatialRefPtr vgSpatialRefPtr, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgSpatialRefPtr_setAnimation__SWIG_1(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgSpatialRefPtr_setDrawOnTop(long j, VgSpatialRefPtr vgSpatialRefPtr, boolean z);

    public static final native void VgSpatialRefPtr_setLocalAnimation(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgSpatialRefPtr_setOrientation(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgOrientation vgOrientation);

    public static final native void VgSpatialRefPtr_setPosition__SWIG_0(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgPosition vgPosition, boolean z);

    public static final native void VgSpatialRefPtr_setPosition__SWIG_1(long j, VgSpatialRefPtr vgSpatialRefPtr, long j2, VgPosition vgPosition);

    public static final native void VgSpatialRefPtr_setScale(long j, VgSpatialRefPtr vgSpatialRefPtr, float f);

    public static final native void VgSpatialRefPtr_setVisible(long j, VgSpatialRefPtr vgSpatialRefPtr, boolean z);

    public static final native void VgSpatialRefPtr_setZIndex(long j, VgSpatialRefPtr vgSpatialRefPtr, int i);

    public static final native int VgSpatialRefPtr_unref(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long VgSpatial_SWIGUpcast(long j);

    public static final native long VgSpatial_asGeometry(long j, VgSpatial vgSpatial);

    public static final native long VgSpatial_asLine(long j, VgSpatial vgSpatial);

    public static final native long VgSpatial_asPoint(long j, VgSpatial vgSpatial);

    public static final native long VgSpatial_editAnimation(long j, VgSpatial vgSpatial, String str);

    public static final native long VgSpatial_getAnimation(long j, VgSpatial vgSpatial, String str);

    public static final native long VgSpatial_getAnimationChannelValue(long j, VgSpatial vgSpatial, String str);

    public static final native void VgSpatial_getAnimationNames(long j, VgSpatial vgSpatial, long j2, VgStringList vgStringList);

    public static final native long VgSpatial_getOrientation(long j, VgSpatial vgSpatial);

    public static final native long VgSpatial_getPosition(long j, VgSpatial vgSpatial);

    public static final native float VgSpatial_getScale(long j, VgSpatial vgSpatial);

    public static final native int VgSpatial_getZIndex(long j, VgSpatial vgSpatial);

    public static final native boolean VgSpatial_isDrawnOnTop(long j, VgSpatial vgSpatial);

    public static final native boolean VgSpatial_isVisible(long j, VgSpatial vgSpatial);

    public static final native void VgSpatial_setAnimation__SWIG_0(long j, VgSpatial vgSpatial, String str, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgSpatial_setAnimation__SWIG_1(long j, VgSpatial vgSpatial, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgSpatial_setDrawOnTop(long j, VgSpatial vgSpatial, boolean z);

    public static final native void VgSpatial_setLocalAnimation(long j, VgSpatial vgSpatial, long j2, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native void VgSpatial_setOrientation(long j, VgSpatial vgSpatial, long j2, VgOrientation vgOrientation);

    public static final native void VgSpatial_setPosition__SWIG_0(long j, VgSpatial vgSpatial, long j2, VgPosition vgPosition, boolean z);

    public static final native void VgSpatial_setPosition__SWIG_1(long j, VgSpatial vgSpatial, long j2, VgPosition vgPosition);

    public static final native void VgSpatial_setScale(long j, VgSpatial vgSpatial, float f);

    public static final native void VgSpatial_setVisible(long j, VgSpatial vgSpatial, boolean z);

    public static final native void VgSpatial_setZIndex(long j, VgSpatial vgSpatial, int i);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr___deref__ */
    public static final native long m1223xbe5001b(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr___ref__ */
    public static final native long m1224x3fe23c(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_get(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_getNbReferences */
    public static final native int m1225x8a8bb33b(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_getNull */
    public static final native long m1226xb1c0c466();

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_isValid */
    public static final native boolean m1227x31d2d35b(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_mEndTime_get */
    public static final native float m1228x249bd969(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_mPostBank_get */
    public static final native float m1229xf32a2049(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_mPostHeading_get */
    public static final native float m1230x60279663(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_mPostPitch_get */
    public static final native float m1231xfd1b3721(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_mSplineVectorFunctorDescriptor_get */
    public static final native long m1232x292d466f(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_mStartTime_get */
    public static final native float m1233xad9da370(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    public static final native void VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_ref(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_set(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr, long j2, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    public static final native int VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr_unref(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorRefPtr___deref__(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorRefPtr___ref__(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorRefPtr_create(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, long j2, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorRefPtr_get(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_getNbReferences */
    public static final native int m1234xb1738ba4(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgSplineOrientationQuaternionFunctorDescriptorRefPtr_isValid(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mEndTime_get */
    public static final native float m1235x53fc01a0(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mEndTime_set */
    public static final native void m1236x53fc2eac(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, float f);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mPostBank_get */
    public static final native float m1237xafcefef2(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mPostBank_set */
    public static final native void m1238xafcf2bfe(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, float f);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mPostHeading_get */
    public static final native float m1239x163acb1a(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mPostHeading_set */
    public static final native void m1240x163af826(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, float f);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mPostPitch_get */
    public static final native float m1241xd5122d98(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mPostPitch_set */
    public static final native void m1242xd5125aa4(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, float f);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mSplineVectorFunctorDescriptor_get */
    public static final native long m1243x41275b66(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mSplineVectorFunctorDescriptor_set */
    public static final native void m1244x41278872(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, long j2, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mStartTime_get */
    public static final native float m1245x859499e7(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptorRefPtr_mStartTime_set */
    public static final native void m1246x8594c6f3(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, float f);

    public static final native void VgSplineOrientationQuaternionFunctorDescriptorRefPtr_ref(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptorRefPtr_set(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr, long j2, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    public static final native int VgSplineOrientationQuaternionFunctorDescriptorRefPtr_unref(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgSplineOrientationQuaternionFunctorDescriptor_create(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native float VgSplineOrientationQuaternionFunctorDescriptor_mPostBank_get(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    public static final native void VgSplineOrientationQuaternionFunctorDescriptor_mPostBank_set(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor, float f);

    public static final native float VgSplineOrientationQuaternionFunctorDescriptor_mPostHeading_get(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    public static final native void VgSplineOrientationQuaternionFunctorDescriptor_mPostHeading_set(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor, float f);

    public static final native float VgSplineOrientationQuaternionFunctorDescriptor_mPostPitch_get(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    public static final native void VgSplineOrientationQuaternionFunctorDescriptor_mPostPitch_set(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor, float f);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptor_mSplineVectorFunctorDescriptor_get */
    public static final native long m1247x438cae41(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    /* renamed from: VgSplineOrientationQuaternionFunctorDescriptor_mSplineVectorFunctorDescriptor_set */
    public static final native void m1248x438cdb4d(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor, long j2, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorConstRefPtr___deref__(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorConstRefPtr___ref__(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorConstRefPtr_get(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native int VgSplineVectorFunctorDescriptorConstRefPtr_getNbReferences(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgSplineVectorFunctorDescriptorConstRefPtr_isValid(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineVectorFunctorDescriptorConstRefPtr_mDistanceFromSpline_get */
    public static final native float m1249xe53e5cf6(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native float VgSplineVectorFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorConstRefPtr_mPoints_get(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    /* renamed from: VgSplineVectorFunctorDescriptorConstRefPtr_mSplineMetricRadius_get */
    public static final native float m1250x768dca59(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native float VgSplineVectorFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native void VgSplineVectorFunctorDescriptorConstRefPtr_ref(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorConstRefPtr_set(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr, long j2, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native int VgSplineVectorFunctorDescriptorConstRefPtr_unref(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr___deref__(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr___ref__(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr_create(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr_get(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native int VgSplineVectorFunctorDescriptorRefPtr_getNbReferences(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgSplineVectorFunctorDescriptorRefPtr_isValid(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native float VgSplineVectorFunctorDescriptorRefPtr_mDistanceFromSpline_get(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native void VgSplineVectorFunctorDescriptorRefPtr_mDistanceFromSpline_set(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr, float f);

    public static final native float VgSplineVectorFunctorDescriptorRefPtr_mEndTime_get(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native void VgSplineVectorFunctorDescriptorRefPtr_mEndTime_set(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr, float f);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr_mPoints_get(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native void VgSplineVectorFunctorDescriptorRefPtr_mPoints_set(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr, long j2, VgPositionVector vgPositionVector);

    public static final native float VgSplineVectorFunctorDescriptorRefPtr_mSplineMetricRadius_get(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native void VgSplineVectorFunctorDescriptorRefPtr_mSplineMetricRadius_set(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr, float f);

    public static final native float VgSplineVectorFunctorDescriptorRefPtr_mStartTime_get(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native void VgSplineVectorFunctorDescriptorRefPtr_mStartTime_set(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr, float f);

    public static final native void VgSplineVectorFunctorDescriptorRefPtr_ref(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptorRefPtr_set(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr, long j2, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native int VgSplineVectorFunctorDescriptorRefPtr_unref(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long VgSplineVectorFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgSplineVectorFunctorDescriptor_create();

    public static final native float VgSplineVectorFunctorDescriptor_mDistanceFromSpline_get(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native void VgSplineVectorFunctorDescriptor_mDistanceFromSpline_set(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor, float f);

    public static final native long VgSplineVectorFunctorDescriptor_mPoints_get(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native void VgSplineVectorFunctorDescriptor_mPoints_set(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor, long j2, VgPositionVector vgPositionVector);

    public static final native float VgSplineVectorFunctorDescriptor_mSplineMetricRadius_get(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native void VgSplineVectorFunctorDescriptor_mSplineMetricRadius_set(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor, float f);

    public static final native void VgStringList_add(long j, VgStringList vgStringList, String str);

    public static final native void VgStringList_clear(long j, VgStringList vgStringList);

    public static final native String VgStringList_get(long j, VgStringList vgStringList, int i);

    public static final native boolean VgStringList_isEmpty(long j, VgStringList vgStringList);

    public static final native long VgStringList_size(long j, VgStringList vgStringList);

    public static final native String VgStringPair_first_get(long j, VgStringPair vgStringPair);

    public static final native void VgStringPair_first_set(long j, VgStringPair vgStringPair, String str);

    public static final native String VgStringPair_second_get(long j, VgStringPair vgStringPair);

    public static final native void VgStringPair_second_set(long j, VgStringPair vgStringPair, String str);

    public static final native void VgStringSet_clear(long j, VgStringSet vgStringSet);

    public static final native void VgStringSet_del(long j, VgStringSet vgStringSet, String str);

    public static final native boolean VgStringSet_empty(long j, VgStringSet vgStringSet);

    public static final native String VgStringSet_get__SWIG_0(long j, VgStringSet vgStringSet, String str);

    public static final native String VgStringSet_get__SWIG_1(long j, VgStringSet vgStringSet, int i);

    public static final native boolean VgStringSet_has_key(long j, VgStringSet vgStringSet, String str);

    public static final native void VgStringSet_insert(long j, VgStringSet vgStringSet, String str);

    public static final native long VgStringSet_size(long j, VgStringSet vgStringSet);

    public static final native void VgStringStringMap_clear(long j, VgStringStringMap vgStringStringMap);

    public static final native void VgStringStringMap_del(long j, VgStringStringMap vgStringStringMap, String str);

    public static final native boolean VgStringStringMap_empty(long j, VgStringStringMap vgStringStringMap);

    public static final native String VgStringStringMap_get(long j, VgStringStringMap vgStringStringMap, String str);

    public static final native boolean VgStringStringMap_has_key(long j, VgStringStringMap vgStringStringMap, String str);

    public static final native void VgStringStringMap_set(long j, VgStringStringMap vgStringStringMap, String str, String str2);

    public static final native long VgStringStringMap_size(long j, VgStringStringMap vgStringStringMap);

    public static final native void VgStringVector_add(long j, VgStringVector vgStringVector, String str);

    public static final native long VgStringVector_capacity(long j, VgStringVector vgStringVector);

    public static final native void VgStringVector_clear(long j, VgStringVector vgStringVector);

    public static final native String VgStringVector_get(long j, VgStringVector vgStringVector, int i);

    public static final native boolean VgStringVector_isEmpty(long j, VgStringVector vgStringVector);

    public static final native void VgStringVector_reserve(long j, VgStringVector vgStringVector, long j2);

    public static final native void VgStringVector_set(long j, VgStringVector vgStringVector, int i, String str);

    public static final native long VgStringVector_size(long j, VgStringVector vgStringVector);

    public static final native long VgTextMarkerConstRefPtr___deref__(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerConstRefPtr___ref__(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerConstRefPtr_get(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerConstRefPtr_getColor(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native int VgTextMarkerConstRefPtr_getNbReferences(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerConstRefPtr_getNull();

    public static final native float VgTextMarkerConstRefPtr_getScale(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native String VgTextMarkerConstRefPtr_getText(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerConstRefPtr_getTextAttributes(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native boolean VgTextMarkerConstRefPtr_isValid(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native void VgTextMarkerConstRefPtr_ref(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerConstRefPtr_set(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr, long j2, VgTextMarker vgTextMarker);

    public static final native int VgTextMarkerConstRefPtr_unref(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr___deref__(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr___ref__(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr_get(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native int VgTextMarkerDescriptorConstRefPtr_getNbReferences(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr_getNull();

    public static final native int VgTextMarkerDescriptorConstRefPtr_getType(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native boolean VgTextMarkerDescriptorConstRefPtr_isValid(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr_mColor_get(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native double VgTextMarkerDescriptorConstRefPtr_mScale_get(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr_mTextAttributesMask_get(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native String VgTextMarkerDescriptorConstRefPtr_mText_get(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native void VgTextMarkerDescriptorConstRefPtr_ref(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorConstRefPtr_set(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr, long j2, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native int VgTextMarkerDescriptorConstRefPtr_unref(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr___deref__(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr___ref__(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr_create(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr_get(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native int VgTextMarkerDescriptorRefPtr_getNbReferences(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr_getNull();

    public static final native int VgTextMarkerDescriptorRefPtr_getType(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native boolean VgTextMarkerDescriptorRefPtr_isValid(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr_mColor_get(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native void VgTextMarkerDescriptorRefPtr_mColor_set(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr, long j2, VgColor vgColor);

    public static final native double VgTextMarkerDescriptorRefPtr_mScale_get(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native void VgTextMarkerDescriptorRefPtr_mScale_set(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr, double d);

    public static final native long VgTextMarkerDescriptorRefPtr_mTextAttributesMask_get(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native void VgTextMarkerDescriptorRefPtr_mTextAttributesMask_set(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr, long j2);

    public static final native String VgTextMarkerDescriptorRefPtr_mText_get(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native void VgTextMarkerDescriptorRefPtr_mText_set(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr, String str);

    public static final native void VgTextMarkerDescriptorRefPtr_ref(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptorRefPtr_set(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr, long j2, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native int VgTextMarkerDescriptorRefPtr_unref(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long VgTextMarkerDescriptor_SWIGUpcast(long j);

    public static final native long VgTextMarkerDescriptor_create();

    public static final native int VgTextMarkerDescriptor_getType(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native long VgTextMarkerDescriptor_mColor_get(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native void VgTextMarkerDescriptor_mColor_set(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor, long j2, VgColor vgColor);

    public static final native double VgTextMarkerDescriptor_mScale_get(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native void VgTextMarkerDescriptor_mScale_set(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor, double d);

    public static final native long VgTextMarkerDescriptor_mTextAttributesMask_get(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native void VgTextMarkerDescriptor_mTextAttributesMask_set(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor, long j2);

    public static final native String VgTextMarkerDescriptor_mText_get(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native void VgTextMarkerDescriptor_mText_set(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor, String str);

    public static final native long VgTextMarkerRefPtr___deref__(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr___ref__(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_asIconMarker(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_asTextMarker(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_get(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_getColor(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native int VgTextMarkerRefPtr_getNbReferences(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_getNull();

    public static final native float VgTextMarkerRefPtr_getScale(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native String VgTextMarkerRefPtr_getText(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_getTextAttributes(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native boolean VgTextMarkerRefPtr_isValid(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native void VgTextMarkerRefPtr_ref(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarkerRefPtr_set(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr, long j2, VgTextMarker vgTextMarker);

    public static final native void VgTextMarkerRefPtr_setColor(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr, long j2, VgColor vgColor);

    public static final native void VgTextMarkerRefPtr_setScale(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr, float f);

    public static final native boolean VgTextMarkerRefPtr_setText(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr, String str);

    public static final native boolean VgTextMarkerRefPtr_setTextAttributes(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr, long j2);

    public static final native int VgTextMarkerRefPtr_unref(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long VgTextMarker_SWIGUpcast(long j);

    public static final native long VgTextMarker_asTextMarker(long j, VgTextMarker vgTextMarker);

    public static final native long VgTextMarker_getColor(long j, VgTextMarker vgTextMarker);

    public static final native float VgTextMarker_getScale(long j, VgTextMarker vgTextMarker);

    public static final native String VgTextMarker_getText(long j, VgTextMarker vgTextMarker);

    public static final native long VgTextMarker_getTextAttributes(long j, VgTextMarker vgTextMarker);

    public static final native void VgTextMarker_setColor(long j, VgTextMarker vgTextMarker, long j2, VgColor vgColor);

    public static final native void VgTextMarker_setScale(long j, VgTextMarker vgTextMarker, float f);

    public static final native boolean VgTextMarker_setText(long j, VgTextMarker vgTextMarker, String str);

    public static final native boolean VgTextMarker_setTextAttributes(long j, VgTextMarker vgTextMarker, long j2);

    public static final native void VgValue_getFloat(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_getInt(long j, VgValue vgValue, int[] iArr);

    public static final native void VgValue_getMatrix3(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_getMatrix4(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_getVector2(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_getVector3(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_getVector4(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_setFloat(long j, VgValue vgValue, float f);

    public static final native void VgValue_setInt(long j, VgValue vgValue, int i);

    public static final native void VgValue_setMatrix3(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_setMatrix4(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_setVector2(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_setVector3(long j, VgValue vgValue, float[] fArr);

    public static final native void VgValue_setVector4(long j, VgValue vgValue, float[] fArr);

    public static final native long VgVectorInterpolationFunctorDescriptorConstRefPtr___deref__(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorConstRefPtr___ref__(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorConstRefPtr_get(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgVectorInterpolationFunctorDescriptorConstRefPtr_getNbReferences */
    public static final native int m1251xdc245b9f(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorConstRefPtr_getNull();

    public static final native boolean VgVectorInterpolationFunctorDescriptorConstRefPtr_isValid(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native boolean VgVectorInterpolationFunctorDescriptorConstRefPtr_mCubic_get(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgVectorInterpolationFunctorDescriptorConstRefPtr_mEndPosition_get */
    public static final native long m1252xa1d92b61(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgVectorInterpolationFunctorDescriptorConstRefPtr_mEndTime_get(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    /* renamed from: VgVectorInterpolationFunctorDescriptorConstRefPtr_mStartPosition_get */
    public static final native long m1253x73c002e8(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native float VgVectorInterpolationFunctorDescriptorConstRefPtr_mStartTime_get(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native void VgVectorInterpolationFunctorDescriptorConstRefPtr_ref(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorConstRefPtr_set(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr, long j2, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native int VgVectorInterpolationFunctorDescriptorConstRefPtr_unref(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr___deref__(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr___ref__(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr_create(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr_get(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native int VgVectorInterpolationFunctorDescriptorRefPtr_getNbReferences(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr_getNull();

    public static final native boolean VgVectorInterpolationFunctorDescriptorRefPtr_isValid(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native boolean VgVectorInterpolationFunctorDescriptorRefPtr_mCubic_get(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native void VgVectorInterpolationFunctorDescriptorRefPtr_mCubic_set(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr, boolean z);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr_mEndPosition_get(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native void VgVectorInterpolationFunctorDescriptorRefPtr_mEndPosition_set(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr, long j2, VgPosition vgPosition);

    public static final native float VgVectorInterpolationFunctorDescriptorRefPtr_mEndTime_get(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native void VgVectorInterpolationFunctorDescriptorRefPtr_mEndTime_set(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr, float f);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr_mStartPosition_get(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native void VgVectorInterpolationFunctorDescriptorRefPtr_mStartPosition_set(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr, long j2, VgPosition vgPosition);

    public static final native float VgVectorInterpolationFunctorDescriptorRefPtr_mStartTime_get(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native void VgVectorInterpolationFunctorDescriptorRefPtr_mStartTime_set(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr, float f);

    public static final native void VgVectorInterpolationFunctorDescriptorRefPtr_ref(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptorRefPtr_set(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr, long j2, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native int VgVectorInterpolationFunctorDescriptorRefPtr_unref(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long VgVectorInterpolationFunctorDescriptor_SWIGUpcast(long j);

    public static final native long VgVectorInterpolationFunctorDescriptor_create();

    public static final native boolean VgVectorInterpolationFunctorDescriptor_mCubic_get(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native void VgVectorInterpolationFunctorDescriptor_mCubic_set(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor, boolean z);

    public static final native long VgVectorInterpolationFunctorDescriptor_mEndPosition_get(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native void VgVectorInterpolationFunctorDescriptor_mEndPosition_set(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor, long j2, VgPosition vgPosition);

    public static final native long VgVectorInterpolationFunctorDescriptor_mStartPosition_get(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native void VgVectorInterpolationFunctorDescriptor_mStartPosition_set(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor, long j2, VgPosition vgPosition);

    public static final native float VgVisibilityRamp_mFullyInvisibleDistance_get(long j, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgVisibilityRamp_mFullyInvisibleDistance_set(long j, VgVisibilityRamp vgVisibilityRamp, float f);

    public static final native float VgVisibilityRamp_mFullyVisibleDistance_get(long j, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgVisibilityRamp_mFullyVisibleDistance_set(long j, VgVisibilityRamp vgVisibilityRamp, float f);

    public static final native float VgVisibilityRamp_mStartInvisibleDistance_get(long j, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgVisibilityRamp_mStartInvisibleDistance_set(long j, VgVisibilityRamp vgVisibilityRamp, float f);

    public static final native float VgVisibilityRamp_mStartVisibleDistance_get(long j, VgVisibilityRamp vgVisibilityRamp);

    public static final native void VgVisibilityRamp_mStartVisibleDistance_set(long j, VgVisibilityRamp vgVisibilityRamp, float f);

    public static final native long castToI3DModule(long j, VgIModule vgIModule);

    public static final native long castToIMapModule(long j, VgIModule vgIModule);

    public static final native long castToINavigationModule(long j, VgIModule vgIModule);

    public static final native long castToIRoutingModule(long j, VgIModule vgIModule);

    public static final native long castToIconMarkerDescriptor(long j, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native long castToTextMarkerDescriptor(long j, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native void delete_VgAnimation(long j);

    public static final native void delete_VgAnimationChannels(long j);

    public static final native void delete_VgAnimationConstRefPtr(long j);

    public static final native void delete_VgAnimationDescriptorConstRefPtr(long j);

    public static final native void delete_VgAnimationDescriptorRefPtr(long j);

    public static final native void delete_VgAnimationRefPtr(long j);

    public static final native void delete_VgAxialRotationQuaternionFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgAxialRotationQuaternionFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgBinaryBuffer(long j);

    public static final native void delete_VgBinaryBufferConstRefPtr(long j);

    public static final native void delete_VgBinaryBufferRefPtr(long j);

    public static final native void delete_VgColor(long j);

    public static final native void delete_VgColorVector(long j);

    public static final native void delete_VgDiscreteQuaternionFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgDiscreteQuaternionFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgDiscreteVectorFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgDiscreteVectorFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgDoubleVector(long j);

    public static final native void delete_VgFloatInterpolationFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgFloatInterpolationFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgFontManager(long j);

    public static final native void delete_VgFunctorDescriptor(long j);

    public static final native void delete_VgFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgFunctorDescriptorMap(long j);

    public static final native void delete_VgFunctorDescriptorMapEntry(long j);

    public static final native void delete_VgFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgGeoReferencedSRSConstRefPtr(long j);

    public static final native void delete_VgGeoReferencedSRSRefPtr(long j);

    public static final native void delete_VgI3DModule(long j);

    public static final native void delete_VgIAnimationCallbackConstRefPtr(long j);

    public static final native void delete_VgIAnimationCallbackRefPtr(long j);

    public static final native void delete_VgIAnimationManager(long j);

    public static final native void delete_VgIApplication(long j);

    public static final native void delete_VgICamera(long j);

    public static final native void delete_VgIDatabase(long j);

    public static final native void delete_VgIDatabaseDatasetDescriptor(long j);

    public static final native void delete_VgIDatabaseDatasetDescriptorVector(long j);

    public static final native void delete_VgIEngine(long j);

    public static final native void delete_VgIEngineContext(long j);

    public static final native void delete_VgIEnginePostDrawCallback(long j);

    public static final native void delete_VgIEnginePostDrawCallbackConstRefPtr(long j);

    public static final native void delete_VgIEnginePostDrawCallbackRefPtr(long j);

    public static final native void delete_VgIEvent(long j);

    public static final native void delete_VgIGeometry(long j);

    public static final native void delete_VgIGeometryCallback(long j);

    public static final native void delete_VgIGeometryCallbackConstRefPtr(long j);

    public static final native void delete_VgIGeometryCallbackRefPtr(long j);

    public static final native void delete_VgIGeometryConstRefPtr(long j);

    public static final native void delete_VgIGeometryEvent(long j);

    public static final native void delete_VgIGeometryRefPtr(long j);

    public static final native void delete_VgILicenseManager(long j);

    public static final native void delete_VgIManipulatorManager(long j);

    public static final native void delete_VgIMapModule(long j);

    public static final native void delete_VgIModule(long j);

    public static final native void delete_VgIModuleManager(long j);

    public static final native void delete_VgINavigation(long j);

    public static final native void delete_VgINavigationCallback(long j);

    public static final native void delete_VgINavigationCallbackConstRefPtr(long j);

    public static final native void delete_VgINavigationCallbackRefPtr(long j);

    public static final native void delete_VgINavigationConstRefPtr(long j);

    public static final native void delete_VgINavigationInstruction(long j);

    public static final native void delete_VgINavigationInstructionConstRefPtr(long j);

    public static final native void delete_VgINavigationInstructionRefPtr(long j);

    public static final native void delete_VgINavigationListener(long j);

    public static final native void delete_VgINavigationListenerConstRefPtr(long j);

    public static final native void delete_VgINavigationListenerRefPtr(long j);

    public static final native void delete_VgINavigationModule(long j);

    public static final native void delete_VgINavigationRefPtr(long j);

    public static final native void delete_VgINavigationRequest(long j);

    public static final native void delete_VgINavigationRequestParameters(long j);

    public static final native void delete_VgIPlaceListener(long j);

    public static final native void delete_VgIPlaceListenerConstRefPtr(long j);

    public static final native void delete_VgIPlaceListenerRefPtr(long j);

    public static final native void delete_VgIResourceCallback(long j);

    public static final native void delete_VgIResourceCallbackConstRefPtr(long j);

    public static final native void delete_VgIResourceCallbackRefPtr(long j);

    public static final native void delete_VgIResourceManager(long j);

    public static final native void delete_VgIResourceRequest(long j);

    public static final native void delete_VgIResourceRequestConstRefPtr(long j);

    public static final native void delete_VgIResourceRequestRefPtr(long j);

    public static final native void delete_VgIRoute(long j);

    public static final native void delete_VgIRouteCallback(long j);

    public static final native void delete_VgIRouteCallbackConstRefPtr(long j);

    public static final native void delete_VgIRouteCallbackRefPtr(long j);

    public static final native void delete_VgIRouteConstRefPtr(long j);

    public static final native void delete_VgIRouteConverter(long j);

    public static final native void delete_VgIRouteConverterFactory(long j);

    public static final native void delete_VgIRouteGeometryDescriptor(long j);

    public static final native void delete_VgIRouteGeometryDescriptorVector(long j);

    public static final native void delete_VgIRouteRefPtr(long j);

    public static final native void delete_VgIRouteRequest(long j);

    public static final native void delete_VgIRouteRequestParameters(long j);

    public static final native void delete_VgIRoutingModule(long j);

    public static final native void delete_VgIRoutingNodeConstRefPtr(long j);

    public static final native void delete_VgIRoutingNodeParameters(long j);

    public static final native void delete_VgIRoutingNodeRefPtr(long j);

    public static final native void delete_VgIRoutingNodeRefPtrVector(long j);

    public static final native void delete_VgIRoutingSolver(long j);

    public static final native void delete_VgITexture(long j);

    public static final native void delete_VgITextureConstRefPtr(long j);

    public static final native void delete_VgITextureManager(long j);

    public static final native void delete_VgITextureRefPtr(long j);

    public static final native void delete_VgIViewPoint(long j);

    public static final native void delete_VgIconMarker(long j);

    public static final native void delete_VgIconMarkerConstRefPtr(long j);

    public static final native void delete_VgIconMarkerDescriptorConstRefPtr(long j);

    public static final native void delete_VgIconMarkerDescriptorRefPtr(long j);

    public static final native void delete_VgIconMarkerRefPtr(long j);

    public static final native void delete_VgInstanceFactory(long j);

    public static final native void delete_VgIntVector(long j);

    public static final native void delete_VgLayerConstRefPtr(long j);

    public static final native void delete_VgLayerDescriptor(long j);

    public static final native void delete_VgLayerDescriptorConstRefPtr(long j);

    public static final native void delete_VgLayerDescriptorRefPtr(long j);

    public static final native void delete_VgLayerManager(long j);

    public static final native void delete_VgLayerRefPtr(long j);

    public static final native void delete_VgLayerVector(long j);

    public static final native void delete_VgLayoutDescriptor(long j);

    public static final native void delete_VgLightConstRefPtr(long j);

    public static final native void delete_VgLightRefPtr(long j);

    public static final native void delete_VgLineConstRefPtr(long j);

    public static final native void delete_VgLineDescriptorConstRefPtr(long j);

    public static final native void delete_VgLineDescriptorRefPtr(long j);

    public static final native void delete_VgLineDescriptorVector(long j);

    public static final native void delete_VgLineRefPtr(long j);

    public static final native void delete_VgLink(long j);

    public static final native void delete_VgLinkConstRefPtr(long j);

    public static final native void delete_VgLinkDescriptor(long j);

    public static final native void delete_VgLinkDescriptorConstRefPtr(long j);

    public static final native void delete_VgLinkDescriptorRefPtr(long j);

    public static final native void delete_VgLinkRefPtr(long j);

    public static final native void delete_VgLocationDoublePair(long j);

    public static final native void delete_VgLocationValuePairVector(long j);

    public static final native void delete_VgLoopModes(long j);

    public static final native void delete_VgManipulator(long j);

    public static final native void delete_VgManipulatorListener(long j);

    public static final native void delete_VgManipulatorListenerConstRefPtr(long j);

    public static final native void delete_VgManipulatorListenerRefPtr(long j);

    public static final native void delete_VgMarker(long j);

    public static final native void delete_VgMarkerConstRefPtr(long j);

    public static final native void delete_VgMarkerDescriptor(long j);

    public static final native void delete_VgMarkerDescriptorConstRefPtr(long j);

    public static final native void delete_VgMarkerDescriptorRefPtr(long j);

    public static final native void delete_VgMarkerDescriptorVector(long j);

    public static final native void delete_VgMarkerRefPtr(long j);

    public static final native void delete_VgMatrixInterpolationFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgMatrixInterpolationFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgMetricSRSConstRefPtr(long j);

    public static final native void delete_VgMetricSRSRefPtr(long j);

    public static final native void delete_VgModelManager(long j);

    public static final native void delete_VgNavigationModalitiesParametersMap(long j);

    public static final native void delete_VgNavigationModalityParametersMap(long j);

    public static final native void delete_VgNearPlace(long j);

    public static final native void delete_VgNearPlaceVector(long j);

    public static final native void delete_VgNearPlacesParameters(long j);

    public static final native void delete_VgOrientation(long j);

    public static final native void delete_VgOrientationConstraints(long j);

    public static final native void delete_VgOrientationDoublePair(long j);

    public static final native void delete_VgOrientationValuePairVector(long j);

    public static final native void delete_VgPOIDescriptor(long j);

    public static final native void delete_VgPlaceColorDescriptor(long j);

    public static final native void delete_VgPlaceDescriptor(long j);

    public static final native void delete_VgPlaceIconDescriptor(long j);

    public static final native void delete_VgPointConstRefPtr(long j);

    public static final native void delete_VgPointDescriptorConstRefPtr(long j);

    public static final native void delete_VgPointDescriptorRefPtr(long j);

    public static final native void delete_VgPointDescriptorVector(long j);

    public static final native void delete_VgPointRefPtr(long j);

    public static final native void delete_VgPosition(long j);

    public static final native void delete_VgPositionToolbox(long j);

    public static final native void delete_VgPositionVector(long j);

    public static final native void delete_VgPostDrawCallbacks(long j);

    public static final native void delete_VgQuadtreeLayoutDescriptor(long j);

    public static final native void delete_VgQuaternionInterpolationFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgQuaternionInterpolationFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgQuery(long j);

    public static final native void delete_VgReferenced(long j);

    public static final native void delete_VgReferencedConstRefPtr(long j);

    public static final native void delete_VgReferencedRefPtr(long j);

    public static final native void delete_VgResourceRequestParameters(long j);

    public static final native void delete_VgSRSConstRefPtr(long j);

    public static final native void delete_VgSRSRefPtr(long j);

    public static final native void delete_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgSpatialConstRefPtr(long j);

    public static final native void delete_VgSpatialList(long j);

    public static final native void delete_VgSpatialRefPtr(long j);

    public static final native void delete_VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgSplineOrientationQuaternionFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgSplineVectorFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgSplineVectorFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgStringList(long j);

    public static final native void delete_VgStringPair(long j);

    public static final native void delete_VgStringSet(long j);

    public static final native void delete_VgStringStringMap(long j);

    public static final native void delete_VgStringVector(long j);

    public static final native void delete_VgTextMarker(long j);

    public static final native void delete_VgTextMarkerConstRefPtr(long j);

    public static final native void delete_VgTextMarkerDescriptorConstRefPtr(long j);

    public static final native void delete_VgTextMarkerDescriptorRefPtr(long j);

    public static final native void delete_VgTextMarkerRefPtr(long j);

    public static final native void delete_VgValue(long j);

    public static final native void delete_VgVectorInterpolationFunctorDescriptorConstRefPtr(long j);

    public static final native void delete_VgVectorInterpolationFunctorDescriptorRefPtr(long j);

    public static final native void delete_VgVisibilityRamp(long j);

    public static final native int eConfigAndLicenseErrorMax_get();

    public static final native int eConfigFileMissingOrEmptyError_get();

    public static final native int eInvalidConfigErrorBadLoad_get();

    public static final native int eInvalidConfigErrorBadXML_get();

    public static final native int eInvalidLicenseErrorBadXML_get();

    public static final native int eInvalidLicenseErrorInvalidMachines_get();

    public static final native int eInvalidLicenseErrorInvalidSDK_get();

    public static final native int eInvalidLicenseErrorInvalidSDKorMachines_get();

    public static final native int eInvalidLicenseErrorInvalidSecret_get();

    public static final native int eInvalidLicenseError_get();

    public static final native int eInvalidLicenseRenewFailure_get();

    public static final native int eManipulatorError_get();

    public static final native int eNoError_get();

    public static final native int eOtherErrors_get();

    public static final native int eVgGestureBegin_get();

    public static final native int eVgGestureEnd_get();

    public static final native int eVgGestureUpdate_get();

    public static final native int eVgTextAttributeNone_get();

    public static final native int eVgTextAttributeOutline_get();

    public static final native int eViewerArchiveParseError_get();

    public static final native int eViewerDatasetIndexError_get();

    public static final native int eViewerDatasetNoValidLayerFound_get();

    public static final native int eViewerDatasetProjectionError_get();

    public static final native int eViewerErrorMax_get();

    public static final native int eViewerRenderingTechniqueError_get();

    public static final native String msEmptyString_get();

    public static final native long new_VgAnimation();

    public static final native long new_VgAnimationChannels();

    public static final native long new_VgAnimationConstRefPtr__SWIG_0();

    public static final native long new_VgAnimationConstRefPtr__SWIG_1(long j, VgAnimation vgAnimation);

    public static final native long new_VgAnimationConstRefPtr__SWIG_2(long j, VgAnimationConstRefPtr vgAnimationConstRefPtr);

    public static final native long new_VgAnimationDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgAnimationDescriptorConstRefPtr__SWIG_1(long j, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native long new_VgAnimationDescriptorConstRefPtr__SWIG_2(long j, VgAnimationDescriptorConstRefPtr vgAnimationDescriptorConstRefPtr);

    public static final native long new_VgAnimationDescriptorRefPtr__SWIG_0();

    public static final native long new_VgAnimationDescriptorRefPtr__SWIG_1(long j, VgAnimationDescriptor vgAnimationDescriptor);

    public static final native long new_VgAnimationDescriptorRefPtr__SWIG_2(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long new_VgAnimationRefPtr__SWIG_0();

    public static final native long new_VgAnimationRefPtr__SWIG_1(long j, VgAnimation vgAnimation);

    public static final native long new_VgAnimationRefPtr__SWIG_2(long j, VgAnimationRefPtr vgAnimationRefPtr);

    /* renamed from: new_VgAxialRotationQuaternionFunctorDescriptorConstRefPtr__SWIG_0 */
    public static final native long m1254xe8918578();

    /* renamed from: new_VgAxialRotationQuaternionFunctorDescriptorConstRefPtr__SWIG_1 */
    public static final native long m1255xe8918579(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    /* renamed from: new_VgAxialRotationQuaternionFunctorDescriptorConstRefPtr__SWIG_2 */
    public static final native long m1256xe891857a(long j, VgAxialRotationQuaternionFunctorDescriptorConstRefPtr vgAxialRotationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long new_VgAxialRotationQuaternionFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgAxialRotationQuaternionFunctorDescriptorRefPtr__SWIG_1(long j, VgAxialRotationQuaternionFunctorDescriptor vgAxialRotationQuaternionFunctorDescriptor);

    public static final native long new_VgAxialRotationQuaternionFunctorDescriptorRefPtr__SWIG_2(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long new_VgBinaryBufferConstRefPtr__SWIG_0();

    public static final native long new_VgBinaryBufferConstRefPtr__SWIG_1(long j, VgBinaryBuffer vgBinaryBuffer);

    public static final native long new_VgBinaryBufferConstRefPtr__SWIG_2(long j, VgBinaryBufferConstRefPtr vgBinaryBufferConstRefPtr);

    public static final native long new_VgBinaryBufferRefPtr__SWIG_0();

    public static final native long new_VgBinaryBufferRefPtr__SWIG_1(long j, VgBinaryBuffer vgBinaryBuffer);

    public static final native long new_VgBinaryBufferRefPtr__SWIG_2(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long new_VgBinaryBuffer__SWIG_0();

    public static final native long new_VgBinaryBuffer__SWIG_1(byte[] bArr, boolean z);

    public static final native long new_VgBinaryBuffer__SWIG_2(byte[] bArr);

    public static final native long new_VgColorVector__SWIG_0();

    public static final native long new_VgColorVector__SWIG_1(long j);

    public static final native long new_VgColor__SWIG_0(float f, float f2, float f3, float f4);

    public static final native long new_VgColor__SWIG_1(float f, float f2, float f3);

    public static final native long new_VgColor__SWIG_2(float f, float f2);

    public static final native long new_VgColor__SWIG_3(float f);

    public static final native long new_VgColor__SWIG_4();

    public static final native long new_VgColor__SWIG_5(long j, VgColor vgColor);

    public static final native long new_VgDiscreteQuaternionFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgDiscreteQuaternionFunctorDescriptorConstRefPtr__SWIG_1(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native long new_VgDiscreteQuaternionFunctorDescriptorConstRefPtr__SWIG_2(long j, VgDiscreteQuaternionFunctorDescriptorConstRefPtr vgDiscreteQuaternionFunctorDescriptorConstRefPtr);

    public static final native long new_VgDiscreteQuaternionFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgDiscreteQuaternionFunctorDescriptorRefPtr__SWIG_1(long j, VgDiscreteQuaternionFunctorDescriptor vgDiscreteQuaternionFunctorDescriptor);

    public static final native long new_VgDiscreteQuaternionFunctorDescriptorRefPtr__SWIG_2(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long new_VgDiscreteVectorFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgDiscreteVectorFunctorDescriptorConstRefPtr__SWIG_1(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native long new_VgDiscreteVectorFunctorDescriptorConstRefPtr__SWIG_2(long j, VgDiscreteVectorFunctorDescriptorConstRefPtr vgDiscreteVectorFunctorDescriptorConstRefPtr);

    public static final native long new_VgDiscreteVectorFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgDiscreteVectorFunctorDescriptorRefPtr__SWIG_1(long j, VgDiscreteVectorFunctorDescriptor vgDiscreteVectorFunctorDescriptor);

    public static final native long new_VgDiscreteVectorFunctorDescriptorRefPtr__SWIG_2(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long new_VgDoubleVector__SWIG_0();

    public static final native long new_VgDoubleVector__SWIG_1(long j);

    public static final native long new_VgFloatInterpolationFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgFloatInterpolationFunctorDescriptorConstRefPtr__SWIG_1(long j, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor);

    public static final native long new_VgFloatInterpolationFunctorDescriptorConstRefPtr__SWIG_2(long j, VgFloatInterpolationFunctorDescriptorConstRefPtr vgFloatInterpolationFunctorDescriptorConstRefPtr);

    public static final native long new_VgFloatInterpolationFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgFloatInterpolationFunctorDescriptorRefPtr__SWIG_1(long j, VgFloatInterpolationFunctorDescriptor vgFloatInterpolationFunctorDescriptor);

    public static final native long new_VgFloatInterpolationFunctorDescriptorRefPtr__SWIG_2(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long new_VgFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgFunctorDescriptorConstRefPtr__SWIG_1(long j, VgFunctorDescriptor vgFunctorDescriptor);

    public static final native long new_VgFunctorDescriptorConstRefPtr__SWIG_2(long j, VgFunctorDescriptorConstRefPtr vgFunctorDescriptorConstRefPtr);

    public static final native long new_VgFunctorDescriptorMapEntry(long j, VgStringPair vgStringPair);

    public static final native long new_VgFunctorDescriptorMap__SWIG_0();

    public static final native long new_VgFunctorDescriptorMap__SWIG_1(long j, VgFunctorDescriptorMap vgFunctorDescriptorMap);

    public static final native long new_VgFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgFunctorDescriptorRefPtr__SWIG_1(long j, VgFunctorDescriptor vgFunctorDescriptor);

    public static final native long new_VgFunctorDescriptorRefPtr__SWIG_2(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long new_VgGeoReferencedSRSConstRefPtr__SWIG_0();

    public static final native long new_VgGeoReferencedSRSConstRefPtr__SWIG_1(long j, VgGeoReferencedSRS vgGeoReferencedSRS);

    public static final native long new_VgGeoReferencedSRSConstRefPtr__SWIG_2(long j, VgGeoReferencedSRSConstRefPtr vgGeoReferencedSRSConstRefPtr);

    public static final native long new_VgGeoReferencedSRSRefPtr__SWIG_0();

    public static final native long new_VgGeoReferencedSRSRefPtr__SWIG_1(long j, VgGeoReferencedSRS vgGeoReferencedSRS);

    public static final native long new_VgGeoReferencedSRSRefPtr__SWIG_2(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long new_VgIAnimationCallback();

    public static final native long new_VgIAnimationCallbackConstRefPtr__SWIG_0();

    public static final native long new_VgIAnimationCallbackConstRefPtr__SWIG_1(long j, VgIAnimationCallback vgIAnimationCallback);

    public static final native long new_VgIAnimationCallbackConstRefPtr__SWIG_2(long j, VgIAnimationCallbackConstRefPtr vgIAnimationCallbackConstRefPtr);

    public static final native long new_VgIAnimationCallbackRefPtr__SWIG_0();

    public static final native long new_VgIAnimationCallbackRefPtr__SWIG_1(long j, VgIAnimationCallback vgIAnimationCallback);

    public static final native long new_VgIAnimationCallbackRefPtr__SWIG_2(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long new_VgIDatabaseDatasetDescriptor();

    public static final native long new_VgIDatabaseDatasetDescriptorVector__SWIG_0();

    public static final native long new_VgIDatabaseDatasetDescriptorVector__SWIG_1(long j);

    public static final native long new_VgIEngineContext();

    public static final native long new_VgIEnginePostDrawCallback();

    public static final native long new_VgIEnginePostDrawCallbackConstRefPtr__SWIG_0();

    public static final native long new_VgIEnginePostDrawCallbackConstRefPtr__SWIG_1(long j, VgIEnginePostDrawCallback vgIEnginePostDrawCallback);

    public static final native long new_VgIEnginePostDrawCallbackConstRefPtr__SWIG_2(long j, VgIEnginePostDrawCallbackConstRefPtr vgIEnginePostDrawCallbackConstRefPtr);

    public static final native long new_VgIEnginePostDrawCallbackRefPtr__SWIG_0();

    public static final native long new_VgIEnginePostDrawCallbackRefPtr__SWIG_1(long j, VgIEnginePostDrawCallback vgIEnginePostDrawCallback);

    public static final native long new_VgIEnginePostDrawCallbackRefPtr__SWIG_2(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long new_VgIGeometry();

    public static final native long new_VgIGeometryCallback();

    public static final native long new_VgIGeometryCallbackConstRefPtr__SWIG_0();

    public static final native long new_VgIGeometryCallbackConstRefPtr__SWIG_1(long j, VgIGeometryCallback vgIGeometryCallback);

    public static final native long new_VgIGeometryCallbackConstRefPtr__SWIG_2(long j, VgIGeometryCallbackConstRefPtr vgIGeometryCallbackConstRefPtr);

    public static final native long new_VgIGeometryCallbackRefPtr__SWIG_0();

    public static final native long new_VgIGeometryCallbackRefPtr__SWIG_1(long j, VgIGeometryCallback vgIGeometryCallback);

    public static final native long new_VgIGeometryCallbackRefPtr__SWIG_2(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long new_VgIGeometryConstRefPtr__SWIG_0();

    public static final native long new_VgIGeometryConstRefPtr__SWIG_1(long j, VgIGeometry vgIGeometry);

    public static final native long new_VgIGeometryConstRefPtr__SWIG_2(long j, VgIGeometryConstRefPtr vgIGeometryConstRefPtr);

    public static final native long new_VgIGeometryRefPtr__SWIG_0();

    public static final native long new_VgIGeometryRefPtr__SWIG_1(long j, VgIGeometry vgIGeometry);

    public static final native long new_VgIGeometryRefPtr__SWIG_2(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long new_VgINavigationCallback();

    public static final native long new_VgINavigationCallbackConstRefPtr__SWIG_0();

    public static final native long new_VgINavigationCallbackConstRefPtr__SWIG_1(long j, VgINavigationCallback vgINavigationCallback);

    public static final native long new_VgINavigationCallbackConstRefPtr__SWIG_2(long j, VgINavigationCallbackConstRefPtr vgINavigationCallbackConstRefPtr);

    public static final native long new_VgINavigationCallbackRefPtr__SWIG_0();

    public static final native long new_VgINavigationCallbackRefPtr__SWIG_1(long j, VgINavigationCallback vgINavigationCallback);

    public static final native long new_VgINavigationCallbackRefPtr__SWIG_2(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long new_VgINavigationConstRefPtr__SWIG_0();

    public static final native long new_VgINavigationConstRefPtr__SWIG_1(long j, VgINavigation vgINavigation);

    public static final native long new_VgINavigationConstRefPtr__SWIG_2(long j, VgINavigationConstRefPtr vgINavigationConstRefPtr);

    public static final native long new_VgINavigationInstructionConstRefPtr__SWIG_0();

    public static final native long new_VgINavigationInstructionConstRefPtr__SWIG_1(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long new_VgINavigationInstructionConstRefPtr__SWIG_2(long j, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr);

    public static final native long new_VgINavigationInstructionRefPtr__SWIG_0();

    public static final native long new_VgINavigationInstructionRefPtr__SWIG_1(long j, VgINavigationInstruction vgINavigationInstruction);

    public static final native long new_VgINavigationInstructionRefPtr__SWIG_2(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long new_VgINavigationListener();

    public static final native long new_VgINavigationListenerConstRefPtr__SWIG_0();

    public static final native long new_VgINavigationListenerConstRefPtr__SWIG_1(long j, VgINavigationListener vgINavigationListener);

    public static final native long new_VgINavigationListenerConstRefPtr__SWIG_2(long j, VgINavigationListenerConstRefPtr vgINavigationListenerConstRefPtr);

    public static final native long new_VgINavigationListenerRefPtr__SWIG_0();

    public static final native long new_VgINavigationListenerRefPtr__SWIG_1(long j, VgINavigationListener vgINavigationListener);

    public static final native long new_VgINavigationListenerRefPtr__SWIG_2(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long new_VgINavigationRefPtr__SWIG_0();

    public static final native long new_VgINavigationRefPtr__SWIG_1(long j, VgINavigation vgINavigation);

    public static final native long new_VgINavigationRefPtr__SWIG_2(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long new_VgINavigationRequestParameters__SWIG_0(long j, long j2, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr, long j3, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long new_VgINavigationRequestParameters__SWIG_1(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long new_VgIPlaceListener();

    public static final native long new_VgIPlaceListenerConstRefPtr__SWIG_0();

    public static final native long new_VgIPlaceListenerConstRefPtr__SWIG_1(long j, VgIPlaceListener vgIPlaceListener);

    public static final native long new_VgIPlaceListenerConstRefPtr__SWIG_2(long j, VgIPlaceListenerConstRefPtr vgIPlaceListenerConstRefPtr);

    public static final native long new_VgIPlaceListenerRefPtr__SWIG_0();

    public static final native long new_VgIPlaceListenerRefPtr__SWIG_1(long j, VgIPlaceListener vgIPlaceListener);

    public static final native long new_VgIPlaceListenerRefPtr__SWIG_2(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long new_VgIResourceCallback();

    public static final native long new_VgIResourceCallbackConstRefPtr__SWIG_0();

    public static final native long new_VgIResourceCallbackConstRefPtr__SWIG_1(long j, VgIResourceCallback vgIResourceCallback);

    public static final native long new_VgIResourceCallbackConstRefPtr__SWIG_2(long j, VgIResourceCallbackConstRefPtr vgIResourceCallbackConstRefPtr);

    public static final native long new_VgIResourceCallbackRefPtr__SWIG_0();

    public static final native long new_VgIResourceCallbackRefPtr__SWIG_1(long j, VgIResourceCallback vgIResourceCallback);

    public static final native long new_VgIResourceCallbackRefPtr__SWIG_2(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long new_VgIResourceRequestConstRefPtr__SWIG_0();

    public static final native long new_VgIResourceRequestConstRefPtr__SWIG_1(long j, VgIResourceRequest vgIResourceRequest);

    public static final native long new_VgIResourceRequestConstRefPtr__SWIG_2(long j, VgIResourceRequestConstRefPtr vgIResourceRequestConstRefPtr);

    public static final native long new_VgIResourceRequestRefPtr__SWIG_0();

    public static final native long new_VgIResourceRequestRefPtr__SWIG_1(long j, VgIResourceRequest vgIResourceRequest);

    public static final native long new_VgIResourceRequestRefPtr__SWIG_2(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long new_VgIRouteCallback();

    public static final native long new_VgIRouteCallbackConstRefPtr__SWIG_0();

    public static final native long new_VgIRouteCallbackConstRefPtr__SWIG_1(long j, VgIRouteCallback vgIRouteCallback);

    public static final native long new_VgIRouteCallbackConstRefPtr__SWIG_2(long j, VgIRouteCallbackConstRefPtr vgIRouteCallbackConstRefPtr);

    public static final native long new_VgIRouteCallbackRefPtr__SWIG_0();

    public static final native long new_VgIRouteCallbackRefPtr__SWIG_1(long j, VgIRouteCallback vgIRouteCallback);

    public static final native long new_VgIRouteCallbackRefPtr__SWIG_2(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long new_VgIRouteConstRefPtr__SWIG_0();

    public static final native long new_VgIRouteConstRefPtr__SWIG_1(long j, VgIRoute vgIRoute);

    public static final native long new_VgIRouteConstRefPtr__SWIG_2(long j, VgIRouteConstRefPtr vgIRouteConstRefPtr);

    public static final native long new_VgIRouteGeometryDescriptor();

    public static final native long new_VgIRouteGeometryDescriptorVector__SWIG_0();

    public static final native long new_VgIRouteGeometryDescriptorVector__SWIG_1(long j);

    public static final native long new_VgIRouteRefPtr__SWIG_0();

    public static final native long new_VgIRouteRefPtr__SWIG_1(long j, VgIRoute vgIRoute);

    public static final native long new_VgIRouteRefPtr__SWIG_2(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long new_VgIRouteRequestParameters();

    public static final native long new_VgIRoutingNodeConstRefPtr__SWIG_0();

    public static final native long new_VgIRoutingNodeConstRefPtr__SWIG_1(long j, VgIRoutingNode vgIRoutingNode);

    public static final native long new_VgIRoutingNodeConstRefPtr__SWIG_2(long j, VgIRoutingNodeConstRefPtr vgIRoutingNodeConstRefPtr);

    public static final native long new_VgIRoutingNodeParameters();

    public static final native long new_VgIRoutingNodeRefPtrVector__SWIG_0();

    public static final native long new_VgIRoutingNodeRefPtrVector__SWIG_1(long j);

    public static final native long new_VgIRoutingNodeRefPtr__SWIG_0();

    public static final native long new_VgIRoutingNodeRefPtr__SWIG_1(long j, VgIRoutingNode vgIRoutingNode);

    public static final native long new_VgIRoutingNodeRefPtr__SWIG_2(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long new_VgITextureConstRefPtr__SWIG_0();

    public static final native long new_VgITextureConstRefPtr__SWIG_1(long j, VgITexture vgITexture);

    public static final native long new_VgITextureConstRefPtr__SWIG_2(long j, VgITextureConstRefPtr vgITextureConstRefPtr);

    public static final native long new_VgITextureRefPtr__SWIG_0();

    public static final native long new_VgITextureRefPtr__SWIG_1(long j, VgITexture vgITexture);

    public static final native long new_VgITextureRefPtr__SWIG_2(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long new_VgIViewPoint__SWIG_0();

    public static final native long new_VgIViewPoint__SWIG_1(long j, VgIViewPoint vgIViewPoint);

    public static final native long new_VgIconMarker();

    public static final native long new_VgIconMarkerConstRefPtr__SWIG_0();

    public static final native long new_VgIconMarkerConstRefPtr__SWIG_1(long j, VgIconMarker vgIconMarker);

    public static final native long new_VgIconMarkerConstRefPtr__SWIG_2(long j, VgIconMarkerConstRefPtr vgIconMarkerConstRefPtr);

    public static final native long new_VgIconMarkerDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgIconMarkerDescriptorConstRefPtr__SWIG_1(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native long new_VgIconMarkerDescriptorConstRefPtr__SWIG_2(long j, VgIconMarkerDescriptorConstRefPtr vgIconMarkerDescriptorConstRefPtr);

    public static final native long new_VgIconMarkerDescriptorRefPtr__SWIG_0();

    public static final native long new_VgIconMarkerDescriptorRefPtr__SWIG_1(long j, VgIconMarkerDescriptor vgIconMarkerDescriptor);

    public static final native long new_VgIconMarkerDescriptorRefPtr__SWIG_2(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long new_VgIconMarkerRefPtr__SWIG_0();

    public static final native long new_VgIconMarkerRefPtr__SWIG_1(long j, VgIconMarker vgIconMarker);

    public static final native long new_VgIconMarkerRefPtr__SWIG_2(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long new_VgIntVector__SWIG_0();

    public static final native long new_VgIntVector__SWIG_1(long j);

    public static final native long new_VgLayerConstRefPtr__SWIG_0();

    public static final native long new_VgLayerConstRefPtr__SWIG_1(long j, VgLayer vgLayer);

    public static final native long new_VgLayerConstRefPtr__SWIG_2(long j, VgLayerConstRefPtr vgLayerConstRefPtr);

    public static final native long new_VgLayerDescriptor();

    public static final native long new_VgLayerDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgLayerDescriptorConstRefPtr__SWIG_1(long j, VgLayerDescriptor vgLayerDescriptor);

    public static final native long new_VgLayerDescriptorConstRefPtr__SWIG_2(long j, VgLayerDescriptorConstRefPtr vgLayerDescriptorConstRefPtr);

    public static final native long new_VgLayerDescriptorRefPtr__SWIG_0();

    public static final native long new_VgLayerDescriptorRefPtr__SWIG_1(long j, VgLayerDescriptor vgLayerDescriptor);

    public static final native long new_VgLayerDescriptorRefPtr__SWIG_2(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long new_VgLayerManager(long j, VgIEngine vgIEngine);

    public static final native long new_VgLayerRefPtr__SWIG_0();

    public static final native long new_VgLayerRefPtr__SWIG_1(long j, VgLayer vgLayer);

    public static final native long new_VgLayerRefPtr__SWIG_2(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long new_VgLayerVector__SWIG_0();

    public static final native long new_VgLayerVector__SWIG_1(long j);

    public static final native long new_VgLightConstRefPtr__SWIG_0();

    public static final native long new_VgLightConstRefPtr__SWIG_1(long j, VgLight vgLight);

    public static final native long new_VgLightConstRefPtr__SWIG_2(long j, VgLightConstRefPtr vgLightConstRefPtr);

    public static final native long new_VgLightRefPtr__SWIG_0();

    public static final native long new_VgLightRefPtr__SWIG_1(long j, VgLight vgLight);

    public static final native long new_VgLightRefPtr__SWIG_2(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long new_VgLineConstRefPtr__SWIG_0();

    public static final native long new_VgLineConstRefPtr__SWIG_1(long j, VgLine vgLine);

    public static final native long new_VgLineConstRefPtr__SWIG_2(long j, VgLineConstRefPtr vgLineConstRefPtr);

    public static final native long new_VgLineDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgLineDescriptorConstRefPtr__SWIG_1(long j, VgLineDescriptor vgLineDescriptor);

    public static final native long new_VgLineDescriptorConstRefPtr__SWIG_2(long j, VgLineDescriptorConstRefPtr vgLineDescriptorConstRefPtr);

    public static final native long new_VgLineDescriptorRefPtr__SWIG_0();

    public static final native long new_VgLineDescriptorRefPtr__SWIG_1(long j, VgLineDescriptor vgLineDescriptor);

    public static final native long new_VgLineDescriptorRefPtr__SWIG_2(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long new_VgLineDescriptorVector__SWIG_0();

    public static final native long new_VgLineDescriptorVector__SWIG_1(long j);

    public static final native long new_VgLineRefPtr__SWIG_0();

    public static final native long new_VgLineRefPtr__SWIG_1(long j, VgLine vgLine);

    public static final native long new_VgLineRefPtr__SWIG_2(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long new_VgLinkConstRefPtr__SWIG_0();

    public static final native long new_VgLinkConstRefPtr__SWIG_1(long j, VgLink vgLink);

    public static final native long new_VgLinkConstRefPtr__SWIG_2(long j, VgLinkConstRefPtr vgLinkConstRefPtr);

    public static final native long new_VgLinkDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgLinkDescriptorConstRefPtr__SWIG_1(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native long new_VgLinkDescriptorConstRefPtr__SWIG_2(long j, VgLinkDescriptorConstRefPtr vgLinkDescriptorConstRefPtr);

    public static final native long new_VgLinkDescriptorRefPtr__SWIG_0();

    public static final native long new_VgLinkDescriptorRefPtr__SWIG_1(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native long new_VgLinkDescriptorRefPtr__SWIG_2(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long new_VgLinkDescriptor__SWIG_0();

    public static final native long new_VgLinkDescriptor__SWIG_1(long j, VgPosition vgPosition, long j2, VgColor vgColor, long j3, VgPosition vgPosition2, long j4, VgColor vgColor2, long j5, VgITextureRefPtr vgITextureRefPtr, float f, float f2, float f3);

    public static final native long new_VgLinkDescriptor__SWIG_2(long j, VgLinkDescriptor vgLinkDescriptor);

    public static final native long new_VgLinkRefPtr__SWIG_0();

    public static final native long new_VgLinkRefPtr__SWIG_1(long j, VgLink vgLink);

    public static final native long new_VgLinkRefPtr__SWIG_2(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long new_VgLocationDoublePair__SWIG_0();

    public static final native long new_VgLocationDoublePair__SWIG_1(long j, VgPosition vgPosition, double d);

    public static final native long new_VgLocationDoublePair__SWIG_2(long j, VgLocationDoublePair vgLocationDoublePair);

    public static final native long new_VgLocationValuePairVector__SWIG_0();

    public static final native long new_VgLocationValuePairVector__SWIG_1(long j);

    public static final native long new_VgLoopModes();

    public static final native long new_VgManipulatorListener();

    public static final native long new_VgManipulatorListenerConstRefPtr__SWIG_0();

    public static final native long new_VgManipulatorListenerConstRefPtr__SWIG_1(long j, VgManipulatorListener vgManipulatorListener);

    public static final native long new_VgManipulatorListenerConstRefPtr__SWIG_2(long j, VgManipulatorListenerConstRefPtr vgManipulatorListenerConstRefPtr);

    public static final native long new_VgManipulatorListenerRefPtr__SWIG_0();

    public static final native long new_VgManipulatorListenerRefPtr__SWIG_1(long j, VgManipulatorListener vgManipulatorListener);

    public static final native long new_VgManipulatorListenerRefPtr__SWIG_2(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long new_VgMarkerConstRefPtr__SWIG_0();

    public static final native long new_VgMarkerConstRefPtr__SWIG_1(long j, VgMarker vgMarker);

    public static final native long new_VgMarkerConstRefPtr__SWIG_2(long j, VgMarkerConstRefPtr vgMarkerConstRefPtr);

    public static final native long new_VgMarkerDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgMarkerDescriptorConstRefPtr__SWIG_1(long j, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native long new_VgMarkerDescriptorConstRefPtr__SWIG_2(long j, VgMarkerDescriptorConstRefPtr vgMarkerDescriptorConstRefPtr);

    public static final native long new_VgMarkerDescriptorRefPtr__SWIG_0();

    public static final native long new_VgMarkerDescriptorRefPtr__SWIG_1(long j, VgMarkerDescriptor vgMarkerDescriptor);

    public static final native long new_VgMarkerDescriptorRefPtr__SWIG_2(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long new_VgMarkerDescriptorVector__SWIG_0();

    public static final native long new_VgMarkerDescriptorVector__SWIG_1(long j);

    public static final native long new_VgMarkerRefPtr__SWIG_0();

    public static final native long new_VgMarkerRefPtr__SWIG_1(long j, VgMarker vgMarker);

    public static final native long new_VgMarkerRefPtr__SWIG_2(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long new_VgMatrixInterpolationFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgMatrixInterpolationFunctorDescriptorConstRefPtr__SWIG_1(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native long new_VgMatrixInterpolationFunctorDescriptorConstRefPtr__SWIG_2(long j, VgMatrixInterpolationFunctorDescriptorConstRefPtr vgMatrixInterpolationFunctorDescriptorConstRefPtr);

    public static final native long new_VgMatrixInterpolationFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgMatrixInterpolationFunctorDescriptorRefPtr__SWIG_1(long j, VgMatrixInterpolationFunctorDescriptor vgMatrixInterpolationFunctorDescriptor);

    public static final native long new_VgMatrixInterpolationFunctorDescriptorRefPtr__SWIG_2(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long new_VgMetricSRSConstRefPtr__SWIG_0();

    public static final native long new_VgMetricSRSConstRefPtr__SWIG_1(long j, VgMetricSRS vgMetricSRS);

    public static final native long new_VgMetricSRSConstRefPtr__SWIG_2(long j, VgMetricSRSConstRefPtr vgMetricSRSConstRefPtr);

    public static final native long new_VgMetricSRSRefPtr__SWIG_0();

    public static final native long new_VgMetricSRSRefPtr__SWIG_1(long j, VgMetricSRS vgMetricSRS);

    public static final native long new_VgMetricSRSRefPtr__SWIG_2(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long new_VgModelManager();

    public static final native long new_VgNavigationModalitiesParametersMap__SWIG_0();

    public static final native long new_VgNavigationModalitiesParametersMap__SWIG_1(long j, VgNavigationModalitiesParametersMap vgNavigationModalitiesParametersMap);

    public static final native long new_VgNavigationModalityParametersMap__SWIG_0();

    public static final native long new_VgNavigationModalityParametersMap__SWIG_1(long j, VgNavigationModalityParametersMap vgNavigationModalityParametersMap);

    public static final native long new_VgNearPlace();

    public static final native long new_VgNearPlaceVector__SWIG_0();

    public static final native long new_VgNearPlaceVector__SWIG_1(long j);

    public static final native long new_VgNearPlacesParameters();

    public static final native long new_VgOrientationConstraints__SWIG_0();

    public static final native long new_VgOrientationConstraints__SWIG_1(int i, int i2, int i3);

    public static final native long new_VgOrientationDoublePair__SWIG_0();

    public static final native long new_VgOrientationDoublePair__SWIG_1(long j, VgOrientation vgOrientation, double d);

    public static final native long new_VgOrientationDoublePair__SWIG_2(long j, VgOrientationDoublePair vgOrientationDoublePair);

    public static final native long new_VgOrientationValuePairVector__SWIG_0();

    public static final native long new_VgOrientationValuePairVector__SWIG_1(long j);

    public static final native long new_VgOrientation__SWIG_0();

    public static final native long new_VgOrientation__SWIG_1(long j, VgOrientation vgOrientation);

    public static final native long new_VgOrientation__SWIG_2(float f, float f2, float f3);

    public static final native long new_VgPOIDescriptor();

    public static final native long new_VgPlaceColorDescriptor__SWIG_0();

    public static final native long new_VgPlaceColorDescriptor__SWIG_1(long j, VgPlaceColorDescriptor vgPlaceColorDescriptor);

    public static final native long new_VgPlaceDescriptor();

    public static final native long new_VgPlaceIconDescriptor__SWIG_0();

    public static final native long new_VgPlaceIconDescriptor__SWIG_1(long j, VgPlaceIconDescriptor vgPlaceIconDescriptor);

    public static final native long new_VgPointConstRefPtr__SWIG_0();

    public static final native long new_VgPointConstRefPtr__SWIG_1(long j, VgPoint vgPoint);

    public static final native long new_VgPointConstRefPtr__SWIG_2(long j, VgPointConstRefPtr vgPointConstRefPtr);

    public static final native long new_VgPointDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgPointDescriptorConstRefPtr__SWIG_1(long j, VgPointDescriptor vgPointDescriptor);

    public static final native long new_VgPointDescriptorConstRefPtr__SWIG_2(long j, VgPointDescriptorConstRefPtr vgPointDescriptorConstRefPtr);

    public static final native long new_VgPointDescriptorRefPtr__SWIG_0();

    public static final native long new_VgPointDescriptorRefPtr__SWIG_1(long j, VgPointDescriptor vgPointDescriptor);

    public static final native long new_VgPointDescriptorRefPtr__SWIG_2(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long new_VgPointDescriptorVector__SWIG_0();

    public static final native long new_VgPointDescriptorVector__SWIG_1(long j);

    public static final native long new_VgPointRefPtr__SWIG_0();

    public static final native long new_VgPointRefPtr__SWIG_1(long j, VgPoint vgPoint);

    public static final native long new_VgPointRefPtr__SWIG_2(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long new_VgPositionVector__SWIG_0();

    public static final native long new_VgPositionVector__SWIG_1(long j);

    public static final native long new_VgPosition__SWIG_0();

    public static final native long new_VgPosition__SWIG_1(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long new_VgPosition__SWIG_2(long j, VgPosition vgPosition);

    public static final native long new_VgPosition__SWIG_3(double d, double d2, double d3);

    public static final native long new_VgPosition__SWIG_4(double d, double d2, double d3, long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long new_VgPostDrawCallbacks__SWIG_0();

    public static final native long new_VgPostDrawCallbacks__SWIG_1(long j);

    public static final native long new_VgQuadtreeLayoutDescriptor__SWIG_0(float f, long j);

    public static final native long new_VgQuadtreeLayoutDescriptor__SWIG_1(float f);

    public static final native long new_VgQuadtreeLayoutDescriptor__SWIG_2();

    /* renamed from: new_VgQuaternionInterpolationFunctorDescriptorConstRefPtr__SWIG_0 */
    public static final native long m1257xf884df45();

    /* renamed from: new_VgQuaternionInterpolationFunctorDescriptorConstRefPtr__SWIG_1 */
    public static final native long m1258xf884df46(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    /* renamed from: new_VgQuaternionInterpolationFunctorDescriptorConstRefPtr__SWIG_2 */
    public static final native long m1259xf884df47(long j, VgQuaternionInterpolationFunctorDescriptorConstRefPtr vgQuaternionInterpolationFunctorDescriptorConstRefPtr);

    public static final native long new_VgQuaternionInterpolationFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgQuaternionInterpolationFunctorDescriptorRefPtr__SWIG_1(long j, VgQuaternionInterpolationFunctorDescriptor vgQuaternionInterpolationFunctorDescriptor);

    public static final native long new_VgQuaternionInterpolationFunctorDescriptorRefPtr__SWIG_2(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long new_VgQuery();

    public static final native long new_VgReferencedConstRefPtr__SWIG_0();

    public static final native long new_VgReferencedConstRefPtr__SWIG_1(long j, VgReferenced vgReferenced);

    public static final native long new_VgReferencedConstRefPtr__SWIG_2(long j, VgReferencedConstRefPtr vgReferencedConstRefPtr);

    public static final native long new_VgReferencedRefPtr__SWIG_0();

    public static final native long new_VgReferencedRefPtr__SWIG_1(long j, VgReferenced vgReferenced);

    public static final native long new_VgReferencedRefPtr__SWIG_2(long j, VgReferencedRefPtr vgReferencedRefPtr);

    public static final native long new_VgReferenced__SWIG_0();

    public static final native long new_VgReferenced__SWIG_1(long j, VgReferenced vgReferenced);

    public static final native long new_VgResourceRequestParameters();

    public static final native long new_VgSRSConstRefPtr__SWIG_0();

    public static final native long new_VgSRSConstRefPtr__SWIG_1(long j, VgSRS vgSRS);

    public static final native long new_VgSRSConstRefPtr__SWIG_2(long j, VgSRSConstRefPtr vgSRSConstRefPtr);

    public static final native long new_VgSRSRefPtr__SWIG_0();

    public static final native long new_VgSRSRefPtr__SWIG_1(long j, VgSRS vgSRS);

    public static final native long new_VgSRSRefPtr__SWIG_2(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long new_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr__SWIG_1(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native long new_VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr__SWIG_2(long j, VgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr vgSinusoidalVectorOffsetFunctorDescriptorConstRefPtr);

    public static final native long new_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr__SWIG_1(long j, VgSinusoidalVectorOffsetFunctorDescriptor vgSinusoidalVectorOffsetFunctorDescriptor);

    public static final native long new_VgSinusoidalVectorOffsetFunctorDescriptorRefPtr__SWIG_2(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long new_VgSpatialConstRefPtr__SWIG_0();

    public static final native long new_VgSpatialConstRefPtr__SWIG_1(long j, VgSpatial vgSpatial);

    public static final native long new_VgSpatialConstRefPtr__SWIG_2(long j, VgSpatialConstRefPtr vgSpatialConstRefPtr);

    public static final native long new_VgSpatialList();

    public static final native long new_VgSpatialRefPtr__SWIG_0();

    public static final native long new_VgSpatialRefPtr__SWIG_1(long j, VgSpatial vgSpatial);

    public static final native long new_VgSpatialRefPtr__SWIG_2(long j, VgSpatialRefPtr vgSpatialRefPtr);

    /* renamed from: new_VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr__SWIG_0 */
    public static final native long m1260xe140565c();

    /* renamed from: new_VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr__SWIG_1 */
    public static final native long m1261xe140565d(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    /* renamed from: new_VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr__SWIG_2 */
    public static final native long m1262xe140565e(long j, VgSplineOrientationQuaternionFunctorDescriptorConstRefPtr vgSplineOrientationQuaternionFunctorDescriptorConstRefPtr);

    public static final native long new_VgSplineOrientationQuaternionFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgSplineOrientationQuaternionFunctorDescriptorRefPtr__SWIG_1(long j, VgSplineOrientationQuaternionFunctorDescriptor vgSplineOrientationQuaternionFunctorDescriptor);

    public static final native long new_VgSplineOrientationQuaternionFunctorDescriptorRefPtr__SWIG_2(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long new_VgSplineVectorFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgSplineVectorFunctorDescriptorConstRefPtr__SWIG_1(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native long new_VgSplineVectorFunctorDescriptorConstRefPtr__SWIG_2(long j, VgSplineVectorFunctorDescriptorConstRefPtr vgSplineVectorFunctorDescriptorConstRefPtr);

    public static final native long new_VgSplineVectorFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgSplineVectorFunctorDescriptorRefPtr__SWIG_1(long j, VgSplineVectorFunctorDescriptor vgSplineVectorFunctorDescriptor);

    public static final native long new_VgSplineVectorFunctorDescriptorRefPtr__SWIG_2(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long new_VgStringList();

    public static final native long new_VgStringPair__SWIG_0();

    public static final native long new_VgStringPair__SWIG_1(String str, String str2);

    public static final native long new_VgStringPair__SWIG_2(long j, VgStringPair vgStringPair);

    public static final native long new_VgStringSet__SWIG_0();

    public static final native long new_VgStringSet__SWIG_1(long j, VgStringSet vgStringSet);

    public static final native long new_VgStringStringMap__SWIG_0();

    public static final native long new_VgStringStringMap__SWIG_1(long j, VgStringStringMap vgStringStringMap);

    public static final native long new_VgStringVector__SWIG_0();

    public static final native long new_VgStringVector__SWIG_1(long j);

    public static final native long new_VgTextMarker();

    public static final native long new_VgTextMarkerConstRefPtr__SWIG_0();

    public static final native long new_VgTextMarkerConstRefPtr__SWIG_1(long j, VgTextMarker vgTextMarker);

    public static final native long new_VgTextMarkerConstRefPtr__SWIG_2(long j, VgTextMarkerConstRefPtr vgTextMarkerConstRefPtr);

    public static final native long new_VgTextMarkerDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgTextMarkerDescriptorConstRefPtr__SWIG_1(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native long new_VgTextMarkerDescriptorConstRefPtr__SWIG_2(long j, VgTextMarkerDescriptorConstRefPtr vgTextMarkerDescriptorConstRefPtr);

    public static final native long new_VgTextMarkerDescriptorRefPtr__SWIG_0();

    public static final native long new_VgTextMarkerDescriptorRefPtr__SWIG_1(long j, VgTextMarkerDescriptor vgTextMarkerDescriptor);

    public static final native long new_VgTextMarkerDescriptorRefPtr__SWIG_2(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long new_VgTextMarkerRefPtr__SWIG_0();

    public static final native long new_VgTextMarkerRefPtr__SWIG_1(long j, VgTextMarker vgTextMarker);

    public static final native long new_VgTextMarkerRefPtr__SWIG_2(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long new_VgValue();

    public static final native long new_VgVectorInterpolationFunctorDescriptorConstRefPtr__SWIG_0();

    public static final native long new_VgVectorInterpolationFunctorDescriptorConstRefPtr__SWIG_1(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native long new_VgVectorInterpolationFunctorDescriptorConstRefPtr__SWIG_2(long j, VgVectorInterpolationFunctorDescriptorConstRefPtr vgVectorInterpolationFunctorDescriptorConstRefPtr);

    public static final native long new_VgVectorInterpolationFunctorDescriptorRefPtr__SWIG_0();

    public static final native long new_VgVectorInterpolationFunctorDescriptorRefPtr__SWIG_1(long j, VgVectorInterpolationFunctorDescriptor vgVectorInterpolationFunctorDescriptor);

    public static final native long new_VgVectorInterpolationFunctorDescriptorRefPtr__SWIG_2(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long new_VgVisibilityRamp__SWIG_0();

    public static final native long new_VgVisibilityRamp__SWIG_1(float f, float f2, float f3, float f4);

    public static final native long new___dummy_0__(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long new___dummy_10__(long j, VgAnimationRefPtr vgAnimationRefPtr);

    public static final native long new___dummy_11__(long j, VgAnimationDescriptorRefPtr vgAnimationDescriptorRefPtr);

    public static final native long new___dummy_12__(long j, VgAxialRotationQuaternionFunctorDescriptorRefPtr vgAxialRotationQuaternionFunctorDescriptorRefPtr);

    public static final native long new___dummy_13__(long j, VgBinaryBufferRefPtr vgBinaryBufferRefPtr);

    public static final native long new___dummy_14__(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long new___dummy_15__(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long new___dummy_16__(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_17__(long j, VgFunctorDescriptorRefPtr vgFunctorDescriptorRefPtr);

    public static final native long new___dummy_18__(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long new___dummy_19__(long j, VgIAnimationCallbackRefPtr vgIAnimationCallbackRefPtr);

    public static final native long new___dummy_1__(long j, VgDiscreteQuaternionFunctorDescriptorRefPtr vgDiscreteQuaternionFunctorDescriptorRefPtr);

    public static final native long new___dummy_20__(long j, VgIEnginePostDrawCallbackRefPtr vgIEnginePostDrawCallbackRefPtr);

    public static final native long new___dummy_21__(long j, VgIResourceCallbackRefPtr vgIResourceCallbackRefPtr);

    public static final native long new___dummy_22__(long j, VgIResourceRequestRefPtr vgIResourceRequestRefPtr);

    public static final native long new___dummy_23__(long j, VgManipulatorListenerRefPtr vgManipulatorListenerRefPtr);

    public static final native long new___dummy_24__(long j, VgITextureRefPtr vgITextureRefPtr);

    public static final native long new___dummy_25__(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long new___dummy_26__(long j, VgLayerDescriptorRefPtr vgLayerDescriptorRefPtr);

    public static final native long new___dummy_27__(long j, VgLightRefPtr vgLightRefPtr);

    public static final native long new___dummy_28__(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_29__(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long new___dummy_2__(long j, VgDiscreteVectorFunctorDescriptorRefPtr vgDiscreteVectorFunctorDescriptorRefPtr);

    public static final native long new___dummy_30__(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_31__(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long new___dummy_32__(long j, VgSpatialRefPtr vgSpatialRefPtr);

    public static final native long new___dummy_33__(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long new___dummy_34__(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long new___dummy_35__(long j, VgSRSRefPtr vgSRSRefPtr);

    public static final native long new___dummy_36__(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_37__(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long new___dummy_38__(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long new___dummy_39__(long j, VgIGeometryCallbackRefPtr vgIGeometryCallbackRefPtr);

    public static final native long new___dummy_3__(long j, VgFloatInterpolationFunctorDescriptorRefPtr vgFloatInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_40__(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long new___dummy_41__(long j, VgLineDescriptorRefPtr vgLineDescriptorRefPtr);

    public static final native long new___dummy_42__(long j, VgLinkRefPtr vgLinkRefPtr);

    public static final native long new___dummy_43__(long j, VgLinkDescriptorRefPtr vgLinkDescriptorRefPtr);

    public static final native long new___dummy_44__(long j, VgMarkerRefPtr vgMarkerRefPtr);

    public static final native long new___dummy_45__(long j, VgMarkerDescriptorRefPtr vgMarkerDescriptorRefPtr);

    public static final native long new___dummy_46__(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long new___dummy_47__(long j, VgPointDescriptorRefPtr vgPointDescriptorRefPtr);

    public static final native long new___dummy_48__(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long new___dummy_49__(long j, VgIPlaceListenerRefPtr vgIPlaceListenerRefPtr);

    public static final native long new___dummy_4__(long j, VgMatrixInterpolationFunctorDescriptorRefPtr vgMatrixInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_50__(long j, VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr);

    public static final native long new___dummy_51__(long j, VgIRouteRefPtr vgIRouteRefPtr);

    public static final native long new___dummy_52__(long j, VgIRouteCallbackRefPtr vgIRouteCallbackRefPtr);

    public static final native long new___dummy_53__(long j, VgINavigationRefPtr vgINavigationRefPtr);

    public static final native long new___dummy_54__(long j, VgINavigationInstructionRefPtr vgINavigationInstructionRefPtr);

    public static final native long new___dummy_55__(long j, VgINavigationCallbackRefPtr vgINavigationCallbackRefPtr);

    public static final native long new___dummy_56__(long j, VgINavigationListenerRefPtr vgINavigationListenerRefPtr);

    public static final native long new___dummy_57__(long j, VgGeoReferencedSRSRefPtr vgGeoReferencedSRSRefPtr);

    public static final native long new___dummy_58__(long j, VgMetricSRSRefPtr vgMetricSRSRefPtr);

    public static final native long new___dummy_59__(long j, VgIconMarkerDescriptorRefPtr vgIconMarkerDescriptorRefPtr);

    public static final native long new___dummy_5__(long j, VgQuaternionInterpolationFunctorDescriptorRefPtr vgQuaternionInterpolationFunctorDescriptorRefPtr);

    public static final native long new___dummy_60__(long j, VgTextMarkerDescriptorRefPtr vgTextMarkerDescriptorRefPtr);

    public static final native long new___dummy_61__(long j, VgIconMarkerRefPtr vgIconMarkerRefPtr);

    public static final native long new___dummy_62__(long j, VgTextMarkerRefPtr vgTextMarkerRefPtr);

    public static final native long new___dummy_63__(long j, VgLayerRefPtr vgLayerRefPtr);

    public static final native long new___dummy_64__(long j, VgIGeometryRefPtr vgIGeometryRefPtr);

    public static final native long new___dummy_65__(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long new___dummy_66__(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long new___dummy_67__(long j, VgPointRefPtr vgPointRefPtr);

    public static final native long new___dummy_68__(long j, VgLineRefPtr vgLineRefPtr);

    public static final native long new___dummy_6__(long j, VgSinusoidalVectorOffsetFunctorDescriptorRefPtr vgSinusoidalVectorOffsetFunctorDescriptorRefPtr);

    public static final native long new___dummy_7__(long j, VgSplineOrientationQuaternionFunctorDescriptorRefPtr vgSplineOrientationQuaternionFunctorDescriptorRefPtr);

    public static final native long new___dummy_8__(long j, VgSplineVectorFunctorDescriptorRefPtr vgSplineVectorFunctorDescriptorRefPtr);

    public static final native long new___dummy_9__(long j, VgVectorInterpolationFunctorDescriptorRefPtr vgVectorInterpolationFunctorDescriptorRefPtr);

    public static final native void setDrawOnTop(long j, VgSpatialList vgSpatialList, boolean z);

    public static final native void setIdAsNameOnEmptyPlaces(long j, VgIMapModule vgIMapModule);

    private static final native void swig_module_init();

    public static void SwigDirector_VgIAnimationCallback_onFinish(VgIAnimationCallback self, long pAnimation) {
        self.onFinish(new VgAnimationRefPtr(pAnimation, false));
    }

    public static void SwigDirector_VgIEnginePostDrawCallback_postDraw(VgIEnginePostDrawCallback self, long pContext) {
        self.postDraw(pContext == 0 ? null : new VgIEngineContext(pContext, false));
    }

    public static void SwigDirector_VgIResourceCallback_notifyResource(VgIResourceCallback self, int pStatus, String pResourceURI, long pBuffer) {
        self.notifyResource(VgResourceRequestStatus.swigToEnum(pStatus), pResourceURI, new VgBinaryBuffer(pBuffer, false));
    }

    public static void SwigDirector_VgManipulatorListener_onSimplePinch(VgManipulatorListener self, int pState, float pSpan, float pVelocity) {
        self.onSimplePinch(VgGestureState.swigToEnum(pState), pSpan, pVelocity);
    }

    public static void SwigDirector_VgManipulatorListener_onSimpleDrag(VgManipulatorListener self, int pState, long pNbTouch, float pAverageXOffset, float pAverageYOffset) {
        self.onSimpleDrag(VgGestureState.swigToEnum(pState), pNbTouch, pAverageXOffset, pAverageYOffset);
    }

    public static void SwigDirector_VgManipulatorListener_onClick(VgManipulatorListener self, float x, float y) {
        self.onClick(x, y);
    }

    public static void SwigDirector_VgManipulatorListener_onDoubleClick(VgManipulatorListener self, float x, float y) {
        self.onDoubleClick(x, y);
    }

    public static void SwigDirector_VgIGeometryCallback_handleGeometryEvent(VgIGeometryCallback self, long pEvent) {
        self.handleGeometryEvent(new VgIGeometryEvent(pEvent, false));
    }

    public static void SwigDirector_VgIPlaceListener_notifyPlaceSelected(VgIPlaceListener self, long pVgApplication, String pID, long pPosition) {
        self.notifyPlaceSelected(new VgIApplication(pVgApplication, false), pID, new VgPosition(pPosition, false));
    }

    public static void SwigDirector_VgIPlaceListener_notifyMapDatabaseLoaded(VgIPlaceListener self, long pVgApplication) {
        self.notifyMapDatabaseLoaded(new VgIApplication(pVgApplication, false));
    }

    public static void SwigDirector_VgIRouteCallback_notifyRouteComputed(VgIRouteCallback self, int pStatus, long pRoute) {
        self.notifyRouteComputed(VgRouteRequestStatus.swigToEnum(pStatus), new VgIRouteRefPtr(pRoute, false));
    }

    public static boolean SwigDirector_VgINavigationCallback_notifyNavigationComputed(VgINavigationCallback self, int pStatus, long pNavigation) {
        return self.notifyNavigationComputed(VgNavigationRequestStatus.swigToEnum(pStatus), new VgINavigationRefPtr(pNavigation, false));
    }

    public static void SwigDirector_VgINavigationListener_notifyPositionUpdated(VgINavigationListener self, long pNavigation, long pPosition, double pTime) {
        self.notifyPositionUpdated(new VgINavigationConstRefPtr(pNavigation, false), new VgPosition(pPosition, false), pTime);
    }

    public static void SwigDirector_VgINavigationListener_notifyNewInstruction(VgINavigationListener self, long pNavigation, long pIndex) {
        self.notifyNewInstruction(new VgINavigationConstRefPtr(pNavigation, false), pIndex);
    }

    static {
        swig_module_init();
    }
}
