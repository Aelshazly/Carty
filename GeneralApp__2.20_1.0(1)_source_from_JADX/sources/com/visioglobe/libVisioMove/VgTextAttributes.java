package com.visioglobe.libVisioMove;

public final class VgTextAttributes {
    public static final VgTextAttributes eVgTextAttributeNone = new VgTextAttributes("eVgTextAttributeNone", libVisioMoveJNI.eVgTextAttributeNone_get());
    public static final VgTextAttributes eVgTextAttributeOutline = new VgTextAttributes("eVgTextAttributeOutline", libVisioMoveJNI.eVgTextAttributeOutline_get());
    private static int swigNext = 0;
    private static VgTextAttributes[] swigValues = {eVgTextAttributeNone, eVgTextAttributeOutline};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgTextAttributes swigToEnum(int swigValue2) {
        VgTextAttributes[] vgTextAttributesArr = swigValues;
        if (swigValue2 < vgTextAttributesArr.length && swigValue2 >= 0 && vgTextAttributesArr[swigValue2].swigValue == swigValue2) {
            return vgTextAttributesArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgTextAttributes[] vgTextAttributesArr2 = swigValues;
            if (i >= vgTextAttributesArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgTextAttributes.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgTextAttributesArr2[i].swigValue == swigValue2) {
                return vgTextAttributesArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgTextAttributes(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgTextAttributes(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgTextAttributes(String swigName2, VgTextAttributes swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
