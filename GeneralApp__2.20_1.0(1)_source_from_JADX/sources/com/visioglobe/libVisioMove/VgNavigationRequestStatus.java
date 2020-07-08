package com.visioglobe.libVisioMove;

public final class VgNavigationRequestStatus {
    public static final VgNavigationRequestStatus eError = new VgNavigationRequestStatus("eError");
    public static final VgNavigationRequestStatus eSuccess = new VgNavigationRequestStatus("eSuccess");
    private static int swigNext = 0;
    private static VgNavigationRequestStatus[] swigValues = {eSuccess, eError};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgNavigationRequestStatus swigToEnum(int swigValue2) {
        VgNavigationRequestStatus[] vgNavigationRequestStatusArr = swigValues;
        if (swigValue2 < vgNavigationRequestStatusArr.length && swigValue2 >= 0 && vgNavigationRequestStatusArr[swigValue2].swigValue == swigValue2) {
            return vgNavigationRequestStatusArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgNavigationRequestStatus[] vgNavigationRequestStatusArr2 = swigValues;
            if (i >= vgNavigationRequestStatusArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgNavigationRequestStatus.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgNavigationRequestStatusArr2[i].swigValue == swigValue2) {
                return vgNavigationRequestStatusArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgNavigationRequestStatus(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgNavigationRequestStatus(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgNavigationRequestStatus(String swigName2, VgNavigationRequestStatus swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
