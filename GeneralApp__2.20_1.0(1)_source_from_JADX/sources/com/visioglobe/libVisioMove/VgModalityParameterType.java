package com.visioglobe.libVisioMove;

public final class VgModalityParameterType {
    public static final VgModalityParameterType eDistanceFromCouloirThreshold = new VgModalityParameterType("eDistanceFromCouloirThreshold");
    public static final VgModalityParameterType eMinimumInstructionLength = new VgModalityParameterType("eMinimumInstructionLength");
    public static final VgModalityParameterType eNearPlacesThreshold = new VgModalityParameterType("eNearPlacesThreshold");
    public static final VgModalityParameterType eNumParameters = new VgModalityParameterType("eNumParameters");
    public static final VgModalityParameterType eStraightAngleThreshold = new VgModalityParameterType("eStraightAngleThreshold");
    private static int swigNext = 0;
    private static VgModalityParameterType[] swigValues = {eStraightAngleThreshold, eDistanceFromCouloirThreshold, eNearPlacesThreshold, eMinimumInstructionLength, eNumParameters};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgModalityParameterType swigToEnum(int swigValue2) {
        VgModalityParameterType[] vgModalityParameterTypeArr = swigValues;
        if (swigValue2 < vgModalityParameterTypeArr.length && swigValue2 >= 0 && vgModalityParameterTypeArr[swigValue2].swigValue == swigValue2) {
            return vgModalityParameterTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgModalityParameterType[] vgModalityParameterTypeArr2 = swigValues;
            if (i >= vgModalityParameterTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgModalityParameterType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgModalityParameterTypeArr2[i].swigValue == swigValue2) {
                return vgModalityParameterTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgModalityParameterType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgModalityParameterType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgModalityParameterType(String swigName2, VgModalityParameterType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
