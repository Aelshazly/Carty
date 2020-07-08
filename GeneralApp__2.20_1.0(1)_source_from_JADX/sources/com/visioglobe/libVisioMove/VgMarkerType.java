package com.visioglobe.libVisioMove;

public final class VgMarkerType {
    public static final VgMarkerType eVgMarkerIcon = new VgMarkerType("eVgMarkerIcon");
    public static final VgMarkerType eVgMarkerText = new VgMarkerType("eVgMarkerText");
    private static int swigNext = 0;
    private static VgMarkerType[] swigValues = {eVgMarkerText, eVgMarkerIcon};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgMarkerType swigToEnum(int swigValue2) {
        VgMarkerType[] vgMarkerTypeArr = swigValues;
        if (swigValue2 < vgMarkerTypeArr.length && swigValue2 >= 0 && vgMarkerTypeArr[swigValue2].swigValue == swigValue2) {
            return vgMarkerTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgMarkerType[] vgMarkerTypeArr2 = swigValues;
            if (i >= vgMarkerTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgMarkerType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgMarkerTypeArr2[i].swigValue == swigValue2) {
                return vgMarkerTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgMarkerType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgMarkerType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgMarkerType(String swigName2, VgMarkerType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
