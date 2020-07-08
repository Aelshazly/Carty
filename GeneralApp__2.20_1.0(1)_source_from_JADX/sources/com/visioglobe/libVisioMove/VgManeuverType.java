package com.visioglobe.libVisioMove;

public final class VgManeuverType {
    public static final VgManeuverType eVgManeuverTypeChangeLayer = new VgManeuverType("eVgManeuverTypeChangeLayer");
    public static final VgManeuverType eVgManeuverTypeChangeModality = new VgManeuverType("eVgManeuverTypeChangeModality");
    public static final VgManeuverType eVgManeuverTypeEnd = new VgManeuverType("eVgManeuverTypeEnd");
    public static final VgManeuverType eVgManeuverTypeGoDown = new VgManeuverType("eVgManeuverTypeGoDown");
    public static final VgManeuverType eVgManeuverTypeGoStraight = new VgManeuverType("eVgManeuverTypeGoStraight");
    public static final VgManeuverType eVgManeuverTypeGoUp = new VgManeuverType("eVgManeuverTypeGoUp");
    public static final VgManeuverType eVgManeuverTypeMax = new VgManeuverType("eVgManeuverTypeMax");
    public static final VgManeuverType eVgManeuverTypeStart = new VgManeuverType("eVgManeuverTypeStart");
    public static final VgManeuverType eVgManeuverTypeTurnGentleLeft = new VgManeuverType("eVgManeuverTypeTurnGentleLeft");
    public static final VgManeuverType eVgManeuverTypeTurnGentleRight = new VgManeuverType("eVgManeuverTypeTurnGentleRight");
    public static final VgManeuverType eVgManeuverTypeTurnLeft = new VgManeuverType("eVgManeuverTypeTurnLeft");
    public static final VgManeuverType eVgManeuverTypeTurnRight = new VgManeuverType("eVgManeuverTypeTurnRight");
    public static final VgManeuverType eVgManeuverTypeTurnSharpLeft = new VgManeuverType("eVgManeuverTypeTurnSharpLeft");
    public static final VgManeuverType eVgManeuverTypeTurnSharpRight = new VgManeuverType("eVgManeuverTypeTurnSharpRight");
    public static final VgManeuverType eVgManeuverTypeUTurnLeft = new VgManeuverType("eVgManeuverTypeUTurnLeft");
    public static final VgManeuverType eVgManeuverTypeUTurnRight = new VgManeuverType("eVgManeuverTypeUTurnRight");
    public static final VgManeuverType eVgManeuverTypeUnknown = new VgManeuverType("eVgManeuverTypeUnknown");
    public static final VgManeuverType eVgManeuverTypeWaypoint = new VgManeuverType("eVgManeuverTypeWaypoint");
    private static int swigNext = 0;
    private static VgManeuverType[] swigValues = {eVgManeuverTypeUnknown, eVgManeuverTypeGoStraight, eVgManeuverTypeTurnGentleRight, eVgManeuverTypeTurnGentleLeft, eVgManeuverTypeTurnRight, eVgManeuverTypeTurnLeft, eVgManeuverTypeTurnSharpRight, eVgManeuverTypeTurnSharpLeft, eVgManeuverTypeUTurnRight, eVgManeuverTypeUTurnLeft, eVgManeuverTypeStart, eVgManeuverTypeEnd, eVgManeuverTypeGoUp, eVgManeuverTypeGoDown, eVgManeuverTypeChangeModality, eVgManeuverTypeChangeLayer, eVgManeuverTypeWaypoint, eVgManeuverTypeMax};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgManeuverType swigToEnum(int swigValue2) {
        VgManeuverType[] vgManeuverTypeArr = swigValues;
        if (swigValue2 < vgManeuverTypeArr.length && swigValue2 >= 0 && vgManeuverTypeArr[swigValue2].swigValue == swigValue2) {
            return vgManeuverTypeArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgManeuverType[] vgManeuverTypeArr2 = swigValues;
            if (i >= vgManeuverTypeArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgManeuverType.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgManeuverTypeArr2[i].swigValue == swigValue2) {
                return vgManeuverTypeArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgManeuverType(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgManeuverType(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgManeuverType(String swigName2, VgManeuverType swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
