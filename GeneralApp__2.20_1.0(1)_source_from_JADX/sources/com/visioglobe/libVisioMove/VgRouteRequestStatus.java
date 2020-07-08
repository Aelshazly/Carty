package com.visioglobe.libVisioMove;

public final class VgRouteRequestStatus {
    public static final VgRouteRequestStatus eError = new VgRouteRequestStatus("eError");
    public static final VgRouteRequestStatus eSuccess = new VgRouteRequestStatus("eSuccess");
    private static int swigNext = 0;
    private static VgRouteRequestStatus[] swigValues = {eSuccess, eError};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgRouteRequestStatus swigToEnum(int swigValue2) {
        VgRouteRequestStatus[] vgRouteRequestStatusArr = swigValues;
        if (swigValue2 < vgRouteRequestStatusArr.length && swigValue2 >= 0 && vgRouteRequestStatusArr[swigValue2].swigValue == swigValue2) {
            return vgRouteRequestStatusArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgRouteRequestStatus[] vgRouteRequestStatusArr2 = swigValues;
            if (i >= vgRouteRequestStatusArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgRouteRequestStatus.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgRouteRequestStatusArr2[i].swigValue == swigValue2) {
                return vgRouteRequestStatusArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgRouteRequestStatus(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgRouteRequestStatus(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgRouteRequestStatus(String swigName2, VgRouteRequestStatus swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
