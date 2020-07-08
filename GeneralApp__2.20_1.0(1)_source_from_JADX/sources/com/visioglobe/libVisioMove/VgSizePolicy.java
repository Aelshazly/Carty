package com.visioglobe.libVisioMove;

public final class VgSizePolicy {
    public static final VgSizePolicy eVgSizePolicyConstantScale = new VgSizePolicy("eVgSizePolicyConstantScale");
    public static final VgSizePolicy eVgSizePolicyFitRectangle = new VgSizePolicy("eVgSizePolicyFitRectangle");
    private static int swigNext = 0;
    private static VgSizePolicy[] swigValues = {eVgSizePolicyConstantScale, eVgSizePolicyFitRectangle};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgSizePolicy swigToEnum(int swigValue2) {
        VgSizePolicy[] vgSizePolicyArr = swigValues;
        if (swigValue2 < vgSizePolicyArr.length && swigValue2 >= 0 && vgSizePolicyArr[swigValue2].swigValue == swigValue2) {
            return vgSizePolicyArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgSizePolicy[] vgSizePolicyArr2 = swigValues;
            if (i >= vgSizePolicyArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgSizePolicy.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgSizePolicyArr2[i].swigValue == swigValue2) {
                return vgSizePolicyArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgSizePolicy(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgSizePolicy(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgSizePolicy(String swigName2, VgSizePolicy swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
