package com.visioglobe.libVisioMove;

public final class VgLineType {
    public static final VgLineType eGeometryConstantSize = new VgLineType("eGeometryConstantSize");
    public static final VgLineType ePixelConstantSize = new VgLineType("ePixelConstantSize");
    private static int swigNext = 0;
    private static VgLineType[] swigValues = {ePixelConstantSize, eGeometryConstantSize};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgLineType swigToEnum(int swigValue2) {
        VgLineType[] vgLineTypeArr = swigValues;
        if (swigValue2 < vgLineTypeArr.length && swigValue2 >= 0 && vgLineTypeArr[swigValue2].swigValue == swigValue2) {
            return vgLineTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgLineType[] vgLineTypeArr2 = swigValues;
            if (i >= vgLineTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgLineType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgLineTypeArr2[i].swigValue == swigValue2) {
                return vgLineTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgLineType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgLineType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgLineType(String swigName2, VgLineType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
