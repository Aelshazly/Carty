package com.visioglobe.libVisioMove;

public final class VgOrientationType {
    public static final VgOrientationType eVgOrientationCameraFacing = new VgOrientationType("eVgOrientationCameraFacing");
    public static final VgOrientationType eVgOrientationFixed = new VgOrientationType("eVgOrientationFixed");
    public static final VgOrientationType eVgOrientationFixedModulo180degrees = new VgOrientationType("eVgOrientationFixedModulo180degrees");
    private static int swigNext = 0;
    private static VgOrientationType[] swigValues = {eVgOrientationFixed, eVgOrientationFixedModulo180degrees, eVgOrientationCameraFacing};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgOrientationType swigToEnum(int swigValue2) {
        VgOrientationType[] vgOrientationTypeArr = swigValues;
        if (swigValue2 < vgOrientationTypeArr.length && swigValue2 >= 0 && vgOrientationTypeArr[swigValue2].swigValue == swigValue2) {
            return vgOrientationTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgOrientationType[] vgOrientationTypeArr2 = swigValues;
            if (i >= vgOrientationTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgOrientationType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgOrientationTypeArr2[i].swigValue == swigValue2) {
                return vgOrientationTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgOrientationType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgOrientationType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgOrientationType(String swigName2, VgOrientationType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
