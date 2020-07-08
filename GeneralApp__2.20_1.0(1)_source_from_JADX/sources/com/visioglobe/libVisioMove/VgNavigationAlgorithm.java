package com.visioglobe.libVisioMove;

public final class VgNavigationAlgorithm {
    public static final VgNavigationAlgorithm eNavigationAlgorithmAuto = new VgNavigationAlgorithm("eNavigationAlgorithmAuto");
    public static final VgNavigationAlgorithm eNavigationAlgorithmIntersection = new VgNavigationAlgorithm("eNavigationAlgorithmIntersection");
    public static final VgNavigationAlgorithm eNavigationAlgorithmMax = new VgNavigationAlgorithm("eNavigationAlgorithmMax");
    public static final VgNavigationAlgorithm eNavigationAlgorithmOriginal = new VgNavigationAlgorithm("eNavigationAlgorithmOriginal");
    private static int swigNext = 0;
    private static VgNavigationAlgorithm[] swigValues = {eNavigationAlgorithmAuto, eNavigationAlgorithmOriginal, eNavigationAlgorithmIntersection, eNavigationAlgorithmMax};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgNavigationAlgorithm swigToEnum(int swigValue2) {
        VgNavigationAlgorithm[] vgNavigationAlgorithmArr = swigValues;
        if (swigValue2 < vgNavigationAlgorithmArr.length && swigValue2 >= 0 && vgNavigationAlgorithmArr[swigValue2].swigValue == swigValue2) {
            return vgNavigationAlgorithmArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgNavigationAlgorithm[] vgNavigationAlgorithmArr2 = swigValues;
            if (i >= vgNavigationAlgorithmArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgNavigationAlgorithm.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgNavigationAlgorithmArr2[i].swigValue == swigValue2) {
                return vgNavigationAlgorithmArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgNavigationAlgorithm(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgNavigationAlgorithm(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgNavigationAlgorithm(String swigName2, VgNavigationAlgorithm swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
