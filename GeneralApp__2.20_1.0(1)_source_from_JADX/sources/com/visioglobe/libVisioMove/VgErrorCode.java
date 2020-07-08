package com.visioglobe.libVisioMove;

public final class VgErrorCode {
    public static final VgErrorCode eConfigAndLicenseErrorMax = new VgErrorCode("eConfigAndLicenseErrorMax", libVisioMoveJNI.eConfigAndLicenseErrorMax_get());
    public static final VgErrorCode eConfigFileMissingOrEmptyError = new VgErrorCode("eConfigFileMissingOrEmptyError", libVisioMoveJNI.eConfigFileMissingOrEmptyError_get());
    public static final VgErrorCode eDatabaseBadDatasetError = new VgErrorCode("eDatabaseBadDatasetError");
    public static final VgErrorCode eDatabaseConfigurationError = new VgErrorCode("eDatabaseConfigurationError");
    public static final VgErrorCode eInvalidConfigErrorBadLoad = new VgErrorCode("eInvalidConfigErrorBadLoad", libVisioMoveJNI.eInvalidConfigErrorBadLoad_get());
    public static final VgErrorCode eInvalidConfigErrorBadXML = new VgErrorCode("eInvalidConfigErrorBadXML", libVisioMoveJNI.eInvalidConfigErrorBadXML_get());
    public static final VgErrorCode eInvalidFile = new VgErrorCode("eInvalidFile");
    public static final VgErrorCode eInvalidLicenseError = new VgErrorCode("eInvalidLicenseError", libVisioMoveJNI.eInvalidLicenseError_get());
    public static final VgErrorCode eInvalidLicenseErrorBadXML = new VgErrorCode("eInvalidLicenseErrorBadXML", libVisioMoveJNI.eInvalidLicenseErrorBadXML_get());
    public static final VgErrorCode eInvalidLicenseErrorInvalidMachines = new VgErrorCode("eInvalidLicenseErrorInvalidMachines", libVisioMoveJNI.eInvalidLicenseErrorInvalidMachines_get());
    public static final VgErrorCode eInvalidLicenseErrorInvalidSDK = new VgErrorCode("eInvalidLicenseErrorInvalidSDK", libVisioMoveJNI.eInvalidLicenseErrorInvalidSDK_get());
    public static final VgErrorCode eInvalidLicenseErrorInvalidSDKorMachines = new VgErrorCode("eInvalidLicenseErrorInvalidSDKorMachines", libVisioMoveJNI.eInvalidLicenseErrorInvalidSDKorMachines_get());
    public static final VgErrorCode eInvalidLicenseErrorInvalidSecret = new VgErrorCode("eInvalidLicenseErrorInvalidSecret", libVisioMoveJNI.eInvalidLicenseErrorInvalidSecret_get());
    public static final VgErrorCode eInvalidLicenseRenewFailure = new VgErrorCode("eInvalidLicenseRenewFailure", libVisioMoveJNI.eInvalidLicenseRenewFailure_get());
    public static final VgErrorCode eInvalidOperation = new VgErrorCode("eInvalidOperation");
    public static final VgErrorCode eManipulatorError = new VgErrorCode("eManipulatorError", libVisioMoveJNI.eManipulatorError_get());
    public static final VgErrorCode eNbErrors = new VgErrorCode("eNbErrors");
    public static final VgErrorCode eNoError = new VgErrorCode("eNoError", libVisioMoveJNI.eNoError_get());
    public static final VgErrorCode eOtherErrors = new VgErrorCode("eOtherErrors", libVisioMoveJNI.eOtherErrors_get());
    public static final VgErrorCode eViewerArchiveParseError = new VgErrorCode("eViewerArchiveParseError", libVisioMoveJNI.eViewerArchiveParseError_get());
    public static final VgErrorCode eViewerDatasetIndexError = new VgErrorCode("eViewerDatasetIndexError", libVisioMoveJNI.eViewerDatasetIndexError_get());
    public static final VgErrorCode eViewerDatasetNoValidLayerFound = new VgErrorCode("eViewerDatasetNoValidLayerFound", libVisioMoveJNI.eViewerDatasetNoValidLayerFound_get());
    public static final VgErrorCode eViewerDatasetProjectionError = new VgErrorCode("eViewerDatasetProjectionError", libVisioMoveJNI.eViewerDatasetProjectionError_get());
    public static final VgErrorCode eViewerErrorMax = new VgErrorCode("eViewerErrorMax", libVisioMoveJNI.eViewerErrorMax_get());
    public static final VgErrorCode eViewerRenderingTechniqueError = new VgErrorCode("eViewerRenderingTechniqueError", libVisioMoveJNI.eViewerRenderingTechniqueError_get());
    private static int swigNext = 0;
    private static VgErrorCode[] swigValues = {eNoError, eInvalidLicenseError, eInvalidLicenseRenewFailure, eInvalidLicenseErrorInvalidSDK, eInvalidLicenseErrorInvalidMachines, eInvalidLicenseErrorInvalidSDKorMachines, eInvalidLicenseErrorBadXML, eInvalidLicenseErrorInvalidSecret, eConfigFileMissingOrEmptyError, eInvalidConfigErrorBadXML, eInvalidConfigErrorBadLoad, eConfigAndLicenseErrorMax, eViewerArchiveParseError, eViewerDatasetIndexError, eViewerDatasetProjectionError, eViewerDatasetNoValidLayerFound, eViewerRenderingTechniqueError, eViewerErrorMax, eOtherErrors, eManipulatorError, eDatabaseConfigurationError, eDatabaseBadDatasetError, eInvalidFile, eInvalidOperation, eNbErrors};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgErrorCode swigToEnum(int swigValue2) {
        VgErrorCode[] vgErrorCodeArr = swigValues;
        if (swigValue2 < vgErrorCodeArr.length && swigValue2 >= 0 && vgErrorCodeArr[swigValue2].swigValue == swigValue2) {
            return vgErrorCodeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgErrorCode[] vgErrorCodeArr2 = swigValues;
            if (i >= vgErrorCodeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgErrorCode.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgErrorCodeArr2[i].swigValue == swigValue2) {
                return vgErrorCodeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgErrorCode(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgErrorCode(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgErrorCode(String swigName2, VgErrorCode swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
