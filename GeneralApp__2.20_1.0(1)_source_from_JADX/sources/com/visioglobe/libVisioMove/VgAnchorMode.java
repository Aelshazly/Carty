package com.visioglobe.libVisioMove;

public final class VgAnchorMode {
    public static final VgAnchorMode eVgBottomCenter = new VgAnchorMode("eVgBottomCenter");
    public static final VgAnchorMode eVgBottomLeft = new VgAnchorMode("eVgBottomLeft");
    public static final VgAnchorMode eVgBottomRight = new VgAnchorMode("eVgBottomRight");
    public static final VgAnchorMode eVgCenter = new VgAnchorMode("eVgCenter");
    public static final VgAnchorMode eVgCenterLeft = new VgAnchorMode("eVgCenterLeft");
    public static final VgAnchorMode eVgCenterRight = new VgAnchorMode("eVgCenterRight");
    public static final VgAnchorMode eVgTopCenter = new VgAnchorMode("eVgTopCenter");
    public static final VgAnchorMode eVgTopLeft = new VgAnchorMode("eVgTopLeft");
    public static final VgAnchorMode eVgTopRight = new VgAnchorMode("eVgTopRight");
    private static int swigNext = 0;
    private static VgAnchorMode[] swigValues = {eVgTopLeft, eVgTopCenter, eVgTopRight, eVgCenterLeft, eVgCenter, eVgCenterRight, eVgBottomLeft, eVgBottomCenter, eVgBottomRight};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgAnchorMode swigToEnum(int swigValue2) {
        VgAnchorMode[] vgAnchorModeArr = swigValues;
        if (swigValue2 < vgAnchorModeArr.length && swigValue2 >= 0 && vgAnchorModeArr[swigValue2].swigValue == swigValue2) {
            return vgAnchorModeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgAnchorMode[] vgAnchorModeArr2 = swigValues;
            if (i >= vgAnchorModeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgAnchorMode.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgAnchorModeArr2[i].swigValue == swigValue2) {
                return vgAnchorModeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgAnchorMode(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgAnchorMode(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgAnchorMode(String swigName2, VgAnchorMode swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
