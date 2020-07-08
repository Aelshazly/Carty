package com.visioglobe.libVisioMove;

public final class VgIRouteRequestType {
    public static final VgIRouteRequestType eFastest = new VgIRouteRequestType("eFastest");
    public static final VgIRouteRequestType eShortest = new VgIRouteRequestType("eShortest");
    private static int swigNext = 0;
    private static VgIRouteRequestType[] swigValues = {eShortest, eFastest};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgIRouteRequestType swigToEnum(int swigValue2) {
        VgIRouteRequestType[] vgIRouteRequestTypeArr = swigValues;
        if (swigValue2 < vgIRouteRequestTypeArr.length && swigValue2 >= 0 && vgIRouteRequestTypeArr[swigValue2].swigValue == swigValue2) {
            return vgIRouteRequestTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgIRouteRequestType[] vgIRouteRequestTypeArr2 = swigValues;
            if (i >= vgIRouteRequestTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgIRouteRequestType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgIRouteRequestTypeArr2[i].swigValue == swigValue2) {
                return vgIRouteRequestTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgIRouteRequestType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgIRouteRequestType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgIRouteRequestType(String swigName2, VgIRouteRequestType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
