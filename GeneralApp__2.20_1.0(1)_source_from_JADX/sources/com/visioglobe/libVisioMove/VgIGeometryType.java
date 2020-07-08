package com.visioglobe.libVisioMove;

public final class VgIGeometryType {
    public static final VgIGeometryType eGeometry = new VgIGeometryType("eGeometry");
    public static final VgIGeometryType eLine = new VgIGeometryType("eLine");
    public static final VgIGeometryType ePoint = new VgIGeometryType("ePoint");
    private static int swigNext = 0;
    private static VgIGeometryType[] swigValues = {eGeometry, ePoint, eLine};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgIGeometryType swigToEnum(int swigValue2) {
        VgIGeometryType[] vgIGeometryTypeArr = swigValues;
        if (swigValue2 < vgIGeometryTypeArr.length && swigValue2 >= 0 && vgIGeometryTypeArr[swigValue2].swigValue == swigValue2) {
            return vgIGeometryTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgIGeometryType[] vgIGeometryTypeArr2 = swigValues;
            if (i >= vgIGeometryTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgIGeometryType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgIGeometryTypeArr2[i].swigValue == swigValue2) {
                return vgIGeometryTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgIGeometryType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgIGeometryType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgIGeometryType(String swigName2, VgIGeometryType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
