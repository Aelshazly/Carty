package com.visioglobe.libVisioMove;

public final class VgIEventType {
    public static final VgIEventType eClicked = new VgIEventType("eClicked");
    private static int swigNext = 0;
    private static VgIEventType[] swigValues = {eClicked};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgIEventType swigToEnum(int swigValue2) {
        VgIEventType[] vgIEventTypeArr = swigValues;
        if (swigValue2 < vgIEventTypeArr.length && swigValue2 >= 0 && vgIEventTypeArr[swigValue2].swigValue == swigValue2) {
            return vgIEventTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgIEventType[] vgIEventTypeArr2 = swigValues;
            if (i >= vgIEventTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgIEventType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgIEventTypeArr2[i].swigValue == swigValue2) {
                return vgIEventTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgIEventType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgIEventType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgIEventType(String swigName2, VgIEventType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
