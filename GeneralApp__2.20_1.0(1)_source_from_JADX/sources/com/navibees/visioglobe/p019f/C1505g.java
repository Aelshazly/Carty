package com.navibees.visioglobe.p019f;

import com.navibees.visioglobe.C1477c;
import com.visioglobe.libVisioMove.VgAltitudeMode;
import com.visioglobe.libVisioMove.VgAnchorMode;
import com.visioglobe.libVisioMove.VgAnimationChannels;
import com.visioglobe.libVisioMove.VgAnimationDescriptor;
import com.visioglobe.libVisioMove.VgAnimationDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgAnimationRefPtr;
import com.visioglobe.libVisioMove.VgColor;
import com.visioglobe.libVisioMove.VgFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgIGeometryCallbackRefPtr;
import com.visioglobe.libVisioMove.VgITextureRefPtr;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptor;
import com.visioglobe.libVisioMove.VgIconMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgLineDescriptor;
import com.visioglobe.libVisioMove.VgLineDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgLineType;
import com.visioglobe.libVisioMove.VgLoopModes;
import com.visioglobe.libVisioMove.VgMarkerDescriptor;
import com.visioglobe.libVisioMove.VgMarkerDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgOrientationType;
import com.visioglobe.libVisioMove.VgPointDescriptor;
import com.visioglobe.libVisioMove.VgPointDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgPointRefPtr;
import com.visioglobe.libVisioMove.VgPositionToolbox;
import com.visioglobe.libVisioMove.VgSplineOrientationQuaternionFunctorDescriptor;
import com.visioglobe.libVisioMove.VgSplineOrientationQuaternionFunctorDescriptorRefPtr;
import com.visioglobe.libVisioMove.VgSplineVectorFunctorDescriptor;
import com.visioglobe.libVisioMove.VgSplineVectorFunctorDescriptorRefPtr;

/* renamed from: com.navibees.visioglobe.f.g */
/* compiled from: VgMyRouteStyler */
public class C1505g {

    /* renamed from: a */
    private VgIApplication f831a;

    /* renamed from: com.navibees.visioglobe.f.g$a */
    /* compiled from: VgMyRouteStyler */
    static /* synthetic */ class C1506a {

        /* renamed from: a */
        static final /* synthetic */ int[] f832a = new int[C1507b.values().length];

        static {
            try {
                f832a[C1507b.eSimplifiedMultiLinePlusAgent.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f832a[C1507b.eMultiLinePlusAgent.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f832a[C1507b.eMultiLine.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f832a[C1507b.eMultiAgents.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f832a[C1507b.eClassic.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* renamed from: com.navibees.visioglobe.f.g$b */
    /* compiled from: VgMyRouteStyler */
    public enum C1507b {
        eClassic,
        eMultiLine,
        eMultiAgents,
        eMultiLinePlusAgent,
        eSimplifiedMultiLinePlusAgent
    }

    /* renamed from: com.navibees.visioglobe.f.g$c */
    /* compiled from: VgMyRouteStyler */
    public class C1508c {

        /* renamed from: a */
        VgITextureRefPtr f839a;

        /* renamed from: b */
        VgITextureRefPtr f840b;

        /* renamed from: c */
        VgColor f841c;

        /* renamed from: d */
        float f842d;

        public C1508c(C1505g gVar) {
        }
    }

    public C1505g(VgIApplication vgIApplication) {
        this.f831a = vgIApplication;
    }

    /* renamed from: a */
    public void mo28616a(C1507b bVar, C1508c cVar, VgLineDescriptorRefPtr vgLineDescriptorRefPtr, C1477c cVar2, String str) {
        VgPositionToolbox vgPositionToolbox;
        C1508c cVar3 = cVar;
        C1477c cVar4 = cVar2;
        String str2 = str;
        VgLineDescriptorRefPtr create = VgLineDescriptor.create();
        float f = cVar3.f842d;
        float f2 = 0.0f;
        if (0.0f > f) {
            f = 0.0f;
        }
        VgPositionToolbox positionToolbox = this.f831a.editEngine().getPositionToolbox();
        double computeDistance = positionToolbox.computeDistance(vgLineDescriptorRefPtr.getMPositions());
        int i = C1506a.f832a[bVar.ordinal()];
        String str3 = "";
        VgPositionToolbox vgPositionToolbox2 = positionToolbox;
        String str4 = "routingObjects";
        if (i == 1) {
            double d = computeDistance;
            int i2 = 0;
            while (i2 < 10) {
                VgLineDescriptorRefPtr clone = vgLineDescriptorRefPtr.clone();
                clone.setMDrawOnTop(false);
                clone.setMHaveCaps(true);
                String str5 = str3;
                double d2 = ((double) i2) * 100.0d;
                clone.setMVisibilityRampStartVisible(d2);
                int i3 = i2;
                clone.setMVisibilityRampFullyVisible(d2 + 0.0d);
                clone.setMLineType(VgLineType.eGeometryConstantSize);
                clone.setMTextureAnimationSpeed(f2);
                int i4 = i3;
                if (i4 < 9) {
                    double d3 = d2 + 100.0d;
                    clone.setMVisibilityRampStartInvisible(d3);
                    clone.setMVisibilityRampFullyInvisible(d3);
                } else {
                    clone.setMVisibilityRampStartInvisible(Double.POSITIVE_INFINITY);
                    clone.setMVisibilityRampFullyInvisible(Double.POSITIVE_INFINITY);
                }
                float f3 = (float) i4;
                clone.setMTextureSize(f + (2.5f * f3));
                clone.getMWidths().clear();
                clone.getMWidths().add((double) clone.getMTextureSize());
                clone.setMMinTesselationDist(clone.getMTextureSize() * 0.25f);
                clone.setMMaxCornerRadius(((double) clone.getMTextureSize()) * 1.0d);
                if (i4 > 0) {
                    vgPositionToolbox = vgPositionToolbox2;
                    clone.setMPositions(vgPositionToolbox.simplifyLineForWidth(clone.getMPositions(), (double) clone.getMTextureSize()));
                } else {
                    vgPositionToolbox = vgPositionToolbox2;
                }
                VgColor vgColor = new VgColor(cVar3.f841c);
                clone.getMColors().clear();
                clone.getMColors().add(cVar3.f841c);
                clone.setMTexture(cVar3.f839a);
                clone.setMZIndex(1);
                cVar4.mo28558a(str4, str2, clone);
                clone.setMTexture(cVar3.f839a);
                i2 = i4 + 1;
                vgColor.setMAlpha(vgColor.getMAlpha() * 0.1f * ((float) i2));
                clone.getMColors().clear();
                clone.getMColors().add(vgColor);
                clone.setMDrawOnTop(true);
                clone.setMZIndex(2);
                cVar4.mo28558a(str4, str2, clone);
                VgPointDescriptorRefPtr create2 = VgPointDescriptor.create();
                create2.setMPosition(clone.getMPositions().get(0));
                create2.setMAnchorPosition(VgAnchorMode.eVgCenter);
                create2.setMGeometryConstantSizeDistance((f3 * 100.0f) + 100.0f);
                create2.setMZIndex(0);
                create2.setMAltitudeMode(VgAltitudeMode.eAbsolute);
                create2.setMHeadingOrientationType(VgOrientationType.eVgOrientationFixed);
                create2.setMPitchOrientationType(VgOrientationType.eVgOrientationFixed);
                create2.setMRollOrientationType(VgOrientationType.eVgOrientationFixed);
                create2.setMForceFrontFace(false);
                create2.setMDrawOnTop(true);
                VgIconMarkerDescriptorRefPtr create3 = VgIconMarkerDescriptor.create();
                create3.setMIcon(cVar3.f840b);
                create3.setMScale((f3 * 20.0f) + 20.0f);
                create2.getMMarkerDescriptors().add(new VgMarkerDescriptorRefPtr((VgMarkerDescriptor) create3.get()));
                VgAnimationDescriptorRefPtr create4 = VgAnimationDescriptor.create();
                create4.setMDuration(((float) d) / 20.0f);
                float f4 = f;
                vgPositionToolbox2 = vgPositionToolbox;
                create4.setMLoopMode(VgLoopModes.getMscLoop());
                VgSplineVectorFunctorDescriptorRefPtr create5 = VgSplineVectorFunctorDescriptor.create();
                create5.setMPoints(clone.getMPositions());
                create5.setMSplineMetricRadius(clone.getMTextureSize() * 3.0f);
                create4.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create5));
                VgSplineOrientationQuaternionFunctorDescriptorRefPtr create6 = VgSplineOrientationQuaternionFunctorDescriptor.create(create5);
                create6.setMPostHeading(180.0f);
                create6.setMPostPitch(90.0f);
                create6.setMPostBank(0.0f);
                create4.getMFunctorDescriptors().set(VgAnimationChannels.getMscOrientationChannel(), new VgFunctorDescriptorRefPtr(create6));
                create2.setMVisibilityRampStartVisible((double) ((float) clone.getMVisibilityRampStartVisible()));
                create2.setMVisibilityRampFullyVisible((double) ((float) clone.getMVisibilityRampFullyVisible()));
                create2.setMVisibilityRampStartInvisible((double) ((float) clone.getMVisibilityRampStartInvisible()));
                create2.setMVisibilityRampFullyInvisible((double) ((float) clone.getMVisibilityRampFullyInvisible()));
                VgPointRefPtr vgPointRefPtr = cVar4.mo28557a(str4, str2, create2, VgIGeometryCallbackRefPtr.getNull()).f745a;
                VgAnimationRefPtr instantiate = this.f831a.editEngine().editInstanceFactory().instantiate(create4);
                vgPointRefPtr.setAnimation(str5, instantiate);
                instantiate.start();
                cVar3 = cVar;
                str3 = str5;
                f = f4;
                f2 = 0.0f;
            }
        } else if (i == 2) {
            double d4 = computeDistance;
            VgLineDescriptorRefPtr vgLineDescriptorRefPtr2 = create;
            for (int i5 = 0; i5 < 10; i5++) {
                vgLineDescriptorRefPtr2 = vgLineDescriptorRefPtr.clone();
                vgLineDescriptorRefPtr2.setMZIndex(1);
                vgLineDescriptorRefPtr2.setMDrawOnTop(true);
                vgLineDescriptorRefPtr2.setMHaveCaps(true);
                double d5 = ((double) i5) * 100.0d;
                vgLineDescriptorRefPtr2.setMVisibilityRampStartVisible(d5);
                vgLineDescriptorRefPtr2.setMVisibilityRampFullyVisible(d5 + 0.0d);
                if (i5 < 9) {
                    double d6 = d5 + 100.0d;
                    vgLineDescriptorRefPtr2.setMVisibilityRampStartInvisible(d6);
                    vgLineDescriptorRefPtr2.setMVisibilityRampFullyInvisible(d6);
                } else {
                    vgLineDescriptorRefPtr2.setMVisibilityRampStartInvisible(Double.POSITIVE_INFINITY);
                    vgLineDescriptorRefPtr2.setMVisibilityRampFullyInvisible(Double.POSITIVE_INFINITY);
                }
                float f5 = (float) i5;
                vgLineDescriptorRefPtr2.setMTextureSize((2.5f * f5) + f);
                vgLineDescriptorRefPtr2.setMLineType(VgLineType.eGeometryConstantSize);
                vgLineDescriptorRefPtr2.getMWidths().clear();
                vgLineDescriptorRefPtr2.getMWidths().add((double) vgLineDescriptorRefPtr2.getMTextureSize());
                vgLineDescriptorRefPtr2.setMMinTesselationDist(vgLineDescriptorRefPtr2.getMTextureSize() * 0.25f);
                vgLineDescriptorRefPtr2.getMColors().clear();
                vgLineDescriptorRefPtr2.setMTextureAnimationSpeed(f5 + 1.0f);
                vgLineDescriptorRefPtr2.getMColors().add(cVar3.f841c);
                vgLineDescriptorRefPtr2.setMTexture(cVar3.f839a);
                cVar4.mo28558a(str4, str2, vgLineDescriptorRefPtr2);
            }
            VgPointDescriptorRefPtr create7 = VgPointDescriptor.create();
            create7.getMMarkerDescriptors().clear();
            create7.setMPosition(vgLineDescriptorRefPtr2.getMPositions().get(0));
            create7.setMAnchorPosition(VgAnchorMode.eVgCenter);
            create7.setMGeometryConstantSizeDistance(10000.0f);
            create7.setMAltitudeMode(VgAltitudeMode.eAbsolute);
            create7.setMHeadingOrientationType(VgOrientationType.eVgOrientationFixed);
            create7.setMPitchOrientationType(VgOrientationType.eVgOrientationFixed);
            create7.setMRollOrientationType(VgOrientationType.eVgOrientationFixed);
            create7.setMForceFrontFace(false);
            create7.setMDrawOnTop(true);
            create7.setMZIndex(0);
            VgIconMarkerDescriptorRefPtr create8 = VgIconMarkerDescriptor.create();
            create8.setMIcon(cVar3.f840b);
            create8.setMScale(1000.0f);
            create7.getMMarkerDescriptors().add(new VgMarkerDescriptorRefPtr((VgMarkerDescriptor) create8.get()));
            VgAnimationDescriptorRefPtr create9 = VgAnimationDescriptor.create();
            create9.setMDuration(((float) d4) / 20.0f);
            create9.setMLoopMode(VgLoopModes.getMscLoop());
            VgSplineVectorFunctorDescriptorRefPtr create10 = VgSplineVectorFunctorDescriptor.create();
            create10.setMPoints(vgLineDescriptorRefPtr2.getMPositions());
            create10.setMDistanceFromSpline(0.0f);
            create9.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create10));
            VgSplineOrientationQuaternionFunctorDescriptorRefPtr create11 = VgSplineOrientationQuaternionFunctorDescriptor.create(create10);
            create11.setMPostHeading(180.0f);
            create11.setMPostPitch(90.0f);
            create11.setMPostBank(0.0f);
            create9.getMFunctorDescriptors().set(VgAnimationChannels.getMscOrientationChannel(), new VgFunctorDescriptorRefPtr(create11));
            create7.setMVisibilityRampStartVisible(0.0d);
            create7.setMVisibilityRampFullyVisible(0.0d);
            create7.setMVisibilityRampStartInvisible(Double.POSITIVE_INFINITY);
            create7.setMVisibilityRampFullyInvisible(Double.POSITIVE_INFINITY);
            VgPointRefPtr vgPointRefPtr2 = cVar4.mo28557a(str4, str2, create7, VgIGeometryCallbackRefPtr.getNull()).f745a;
            VgAnimationRefPtr instantiate2 = this.f831a.editEngine().editInstanceFactory().instantiate(create9);
            vgPointRefPtr2.setAnimation(str3, instantiate2);
            instantiate2.start();
        } else if (i == 3) {
            int i6 = 0;
            while (i6 < 10) {
                VgLineDescriptorRefPtr clone2 = vgLineDescriptorRefPtr.clone();
                clone2.setMDrawOnTop(true);
                clone2.setMZIndex(1);
                clone2.setMHaveCaps(true);
                double d7 = ((double) i6) * 100.0d;
                clone2.setMVisibilityRampStartVisible(d7);
                clone2.setMVisibilityRampFullyVisible(20.0d + d7);
                if (i6 < 9) {
                    clone2.setMVisibilityRampStartInvisible(d7 + 100.0d);
                    clone2.setMVisibilityRampFullyInvisible(d7 + 120.0d);
                } else {
                    clone2.setMVisibilityRampStartInvisible(Double.POSITIVE_INFINITY);
                    clone2.setMVisibilityRampFullyInvisible(Double.POSITIVE_INFINITY);
                }
                clone2.setMTextureSize(((float) (i6 * 4)) + f);
                clone2.setMLineType(VgLineType.eGeometryConstantSize);
                clone2.getMWidths().clear();
                clone2.getMWidths().add((double) clone2.getMTextureSize());
                i6++;
                clone2.setMTextureAnimationSpeed((float) i6);
                clone2.getMColors().clear();
                clone2.setMTexture(cVar3.f839a);
                cVar4.mo28558a(str4, str2, clone2);
            }
        } else if (i != 4) {
            VgLineDescriptorRefPtr clone3 = vgLineDescriptorRefPtr.clone();
            clone3.getMColors().add(cVar3.f841c);
            clone3.setMTexture(cVar3.f839a);
            clone3.setMTextureSize(f);
            clone3.setMHaveCaps(true);
            clone3.setMMaxCornerRadius(3.0d);
            clone3.setMLineType(VgLineType.eGeometryConstantSize);
            clone3.getMWidths().clear();
            clone3.getMWidths().add((double) clone3.getMTextureSize());
            clone3.getMColors().clear();
            clone3.setMZIndex(1);
            cVar4.mo28558a(str4, str2, clone3);
        } else {
            VgLineDescriptorRefPtr clone4 = vgLineDescriptorRefPtr.clone();
            VgPointDescriptorRefPtr create12 = VgPointDescriptor.create();
            create12.setMPosition(clone4.getMPositions().get(0));
            create12.setMAnchorPosition(VgAnchorMode.eVgCenter);
            create12.setMGeometryConstantSizeDistance(0.0f);
            create12.setMAltitudeMode(VgAltitudeMode.eAbsolute);
            create12.setMHeadingOrientationType(VgOrientationType.eVgOrientationFixed);
            create12.setMPitchOrientationType(VgOrientationType.eVgOrientationFixed);
            create12.setMRollOrientationType(VgOrientationType.eVgOrientationFixed);
            create12.setMForceFrontFace(false);
            create12.setMDrawOnTop(true);
            create12.setMZIndex(0);
            VgIconMarkerDescriptorRefPtr create13 = VgIconMarkerDescriptor.create();
            create13.setMIcon(cVar3.f840b);
            create12.getMMarkerDescriptors().add(new VgMarkerDescriptorRefPtr((VgMarkerDescriptor) create13.get()));
            VgAnimationDescriptorRefPtr create14 = VgAnimationDescriptor.create();
            create14.setMDuration(((float) computeDistance) / 20.0f);
            create14.setMLoopMode(VgLoopModes.getMscLoop());
            VgSplineVectorFunctorDescriptorRefPtr create15 = VgSplineVectorFunctorDescriptor.create();
            create15.setMPoints(clone4.getMPositions());
            create14.getMFunctorDescriptors().set(VgAnimationChannels.getMscPositionChannel(), new VgFunctorDescriptorRefPtr(create15));
            VgSplineOrientationQuaternionFunctorDescriptorRefPtr create16 = VgSplineOrientationQuaternionFunctorDescriptor.create(create15);
            create16.setMPostHeading(180.0f);
            create16.setMPostPitch(90.0f);
            create16.setMPostBank(0.0f);
            create14.getMFunctorDescriptors().set(VgAnimationChannels.getMscOrientationChannel(), new VgFunctorDescriptorRefPtr(create16));
            int i7 = 0;
            while (i7 < 10) {
                create13.setMScale(((float) (i7 * 4)) + f);
                int ceil = (int) Math.ceil(computeDistance / ((double) create13.getMScale()));
                float f6 = ((float) i7) * 100.0f;
                double d8 = computeDistance;
                create12.setMVisibilityRampStartVisible((double) f6);
                create12.setMVisibilityRampFullyVisible((double) (f6 + 20.0f));
                if (i7 < 9) {
                    create12.setMVisibilityRampStartInvisible((double) (f6 + 100.0f));
                    create12.setMVisibilityRampFullyInvisible((double) (f6 + 120.0f));
                } else {
                    create12.setMVisibilityRampStartInvisible(Double.POSITIVE_INFINITY);
                    create12.setMVisibilityRampFullyInvisible(Double.POSITIVE_INFINITY);
                }
                if (ceil < 1) {
                    ceil = 1;
                }
                for (int i8 = 0; i8 < ceil; i8++) {
                    VgPointRefPtr vgPointRefPtr3 = cVar4.mo28557a(str4, str2, create12, VgIGeometryCallbackRefPtr.getNull()).f745a;
                    VgAnimationRefPtr instantiate3 = this.f831a.editEngine().editInstanceFactory().instantiate(create14);
                    vgPointRefPtr3.setAnimation(str3, instantiate3);
                    instantiate3.start((((float) i8) * instantiate3.getDuration()) / ((float) ceil));
                }
                i7++;
                computeDistance = d8;
            }
        }
    }
}
