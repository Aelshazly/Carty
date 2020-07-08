package com.visioglobe.libVisioMove;

public final class VgResourceRequestStatus {
    public static final VgResourceRequestStatus eCancelled = new VgResourceRequestStatus("eCancelled");
    public static final VgResourceRequestStatus eError = new VgResourceRequestStatus("eError");
    public static final VgResourceRequestStatus eSuccess = new VgResourceRequestStatus("eSuccess");
    private static int swigNext = 0;
    private static VgResourceRequestStatus[] swigValues = {eSuccess, eCancelled, eError};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgResourceRequestStatus swigToEnum(int swigValue2) {
        VgResourceRequestStatus[] vgResourceRequestStatusArr = swigValues;
        if (swigValue2 < vgResourceRequestStatusArr.length && swigValue2 >= 0 && vgResourceRequestStatusArr[swigValue2].swigValue == swigValue2) {
            return vgResourceRequestStatusArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgResourceRequestStatus[] vgResourceRequestStatusArr2 = swigValues;
            if (i >= vgResourceRequestStatusArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgResourceRequestStatus.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgResourceRequestStatusArr2[i].swigValue == swigValue2) {
                return vgResourceRequestStatusArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgResourceRequestStatus(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgResourceRequestStatus(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgResourceRequestStatus(String swigName2, VgResourceRequestStatus swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
