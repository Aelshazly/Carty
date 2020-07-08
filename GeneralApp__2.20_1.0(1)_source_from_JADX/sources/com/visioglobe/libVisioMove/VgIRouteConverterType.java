package com.visioglobe.libVisioMove;

public final class VgIRouteConverterType {
    public static final VgIRouteConverterType e2D = new VgIRouteConverterType("e2D");
    public static final VgIRouteConverterType e3D = new VgIRouteConverterType("e3D");
    private static int swigNext = 0;
    private static VgIRouteConverterType[] swigValues = {e2D, e3D};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgIRouteConverterType swigToEnum(int swigValue2) {
        VgIRouteConverterType[] vgIRouteConverterTypeArr = swigValues;
        if (swigValue2 < vgIRouteConverterTypeArr.length && swigValue2 >= 0 && vgIRouteConverterTypeArr[swigValue2].swigValue == swigValue2) {
            return vgIRouteConverterTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgIRouteConverterType[] vgIRouteConverterTypeArr2 = swigValues;
            if (i >= vgIRouteConverterTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgIRouteConverterType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgIRouteConverterTypeArr2[i].swigValue == swigValue2) {
                return vgIRouteConverterTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgIRouteConverterType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgIRouteConverterType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgIRouteConverterType(String swigName2, VgIRouteConverterType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
