package com.visioglobe.libVisioMove;

public final class VgAltitudeMode {
    public static final VgAltitudeMode eAbsolute = new VgAltitudeMode("eAbsolute");
    public static final VgAltitudeMode eRelative = new VgAltitudeMode("eRelative");
    private static int swigNext = 0;
    private static VgAltitudeMode[] swigValues = {eRelative, eAbsolute};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgAltitudeMode swigToEnum(int swigValue2) {
        VgAltitudeMode[] vgAltitudeModeArr = swigValues;
        if (swigValue2 < vgAltitudeModeArr.length && swigValue2 >= 0 && vgAltitudeModeArr[swigValue2].swigValue == swigValue2) {
            return vgAltitudeModeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgAltitudeMode[] vgAltitudeModeArr2 = swigValues;
            if (i >= vgAltitudeModeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgAltitudeMode.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgAltitudeModeArr2[i].swigValue == swigValue2) {
                return vgAltitudeModeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgAltitudeMode(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgAltitudeMode(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgAltitudeMode(String swigName2, VgAltitudeMode swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
