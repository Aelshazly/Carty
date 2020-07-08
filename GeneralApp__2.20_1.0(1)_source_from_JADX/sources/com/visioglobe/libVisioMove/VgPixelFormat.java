package com.visioglobe.libVisioMove;

public final class VgPixelFormat {
    public static final VgPixelFormat eRGB = new VgPixelFormat("eRGB");
    public static final VgPixelFormat eRGBA = new VgPixelFormat("eRGBA");
    private static int swigNext = 0;
    private static VgPixelFormat[] swigValues = {eRGB, eRGBA};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgPixelFormat swigToEnum(int swigValue2) {
        VgPixelFormat[] vgPixelFormatArr = swigValues;
        if (swigValue2 < vgPixelFormatArr.length && swigValue2 >= 0 && vgPixelFormatArr[swigValue2].swigValue == swigValue2) {
            return vgPixelFormatArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgPixelFormat[] vgPixelFormatArr2 = swigValues;
            if (i >= vgPixelFormatArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgPixelFormat.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgPixelFormatArr2[i].swigValue == swigValue2) {
                return vgPixelFormatArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgPixelFormat(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgPixelFormat(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgPixelFormat(String swigName2, VgPixelFormat swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
