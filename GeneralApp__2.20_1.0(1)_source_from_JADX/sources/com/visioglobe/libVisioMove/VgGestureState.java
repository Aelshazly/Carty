package com.visioglobe.libVisioMove;

public final class VgGestureState {
    public static final VgGestureState eVgGestureBegin = new VgGestureState("eVgGestureBegin", libVisioMoveJNI.eVgGestureBegin_get());
    public static final VgGestureState eVgGestureEnd = new VgGestureState("eVgGestureEnd", libVisioMoveJNI.eVgGestureEnd_get());
    public static final VgGestureState eVgGestureUpdate = new VgGestureState("eVgGestureUpdate", libVisioMoveJNI.eVgGestureUpdate_get());
    private static int swigNext = 0;
    private static VgGestureState[] swigValues = {eVgGestureBegin, eVgGestureUpdate, eVgGestureEnd};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgGestureState swigToEnum(int swigValue2) {
        VgGestureState[] vgGestureStateArr = swigValues;
        if (swigValue2 < vgGestureStateArr.length && swigValue2 >= 0 && vgGestureStateArr[swigValue2].swigValue == swigValue2) {
            return vgGestureStateArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgGestureState[] vgGestureStateArr2 = swigValues;
            if (i >= vgGestureStateArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgGestureState.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgGestureStateArr2[i].swigValue == swigValue2) {
                return vgGestureStateArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgGestureState(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgGestureState(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgGestureState(String swigName2, VgGestureState swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
