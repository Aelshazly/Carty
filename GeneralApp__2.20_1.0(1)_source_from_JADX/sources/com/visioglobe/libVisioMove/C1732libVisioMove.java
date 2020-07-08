package com.visioglobe.libVisioMove;

/* renamed from: com.visioglobe.libVisioMove.libVisioMove */
public class C1732libVisioMove implements libVisioMoveConstants {
    public static String getMsEmptyString() {
        return libVisioMoveJNI.msEmptyString_get();
    }

    public static VgI3DModule castToI3DModule(VgIModule pModule) {
        long cPtr = libVisioMoveJNI.castToI3DModule(VgIModule.getCPtr(pModule), pModule);
        if (cPtr == 0) {
            return null;
        }
        return new VgI3DModule(cPtr, false);
    }

    public static VgIconMarkerDescriptor castToIconMarkerDescriptor(VgMarkerDescriptor pMarkerDescriptor) {
        long cPtr = libVisioMoveJNI.castToIconMarkerDescriptor(VgMarkerDescriptor.getCPtr(pMarkerDescriptor), pMarkerDescriptor);
        if (cPtr == 0) {
            return null;
        }
        return new VgIconMarkerDescriptor(cPtr, false);
    }

    public static VgTextMarkerDescriptor castToTextMarkerDescriptor(VgMarkerDescriptor pMarkerDescriptor) {
        long cPtr = libVisioMoveJNI.castToTextMarkerDescriptor(VgMarkerDescriptor.getCPtr(pMarkerDescriptor), pMarkerDescriptor);
        if (cPtr == 0) {
            return null;
        }
        return new VgTextMarkerDescriptor(cPtr, false);
    }

    public static void setDrawOnTop(VgSpatialList pSpatials, boolean pDrawOnTop) {
        libVisioMoveJNI.setDrawOnTop(VgSpatialList.getCPtr(pSpatials), pSpatials, pDrawOnTop);
    }

    public static void setIdAsNameOnEmptyPlaces(VgIMapModule pMapModule) {
        libVisioMoveJNI.setIdAsNameOnEmptyPlaces(VgIMapModule.getCPtr(pMapModule), pMapModule);
    }

    public static VgIMapModule castToIMapModule(VgIModule pModule) {
        long cPtr = libVisioMoveJNI.castToIMapModule(VgIModule.getCPtr(pModule), pModule);
        if (cPtr == 0) {
            return null;
        }
        return new VgIMapModule(cPtr, false);
    }

    public static VgIRoutingModule castToIRoutingModule(VgIModule pModule) {
        long cPtr = libVisioMoveJNI.castToIRoutingModule(VgIModule.getCPtr(pModule), pModule);
        if (cPtr == 0) {
            return null;
        }
        return new VgIRoutingModule(cPtr, false);
    }

    public static VgINavigationModule castToINavigationModule(VgIModule pModule) {
        long cPtr = libVisioMoveJNI.castToINavigationModule(VgIModule.getCPtr(pModule), pModule);
        if (cPtr == 0) {
            return null;
        }
        return new VgINavigationModule(cPtr, false);
    }
}
