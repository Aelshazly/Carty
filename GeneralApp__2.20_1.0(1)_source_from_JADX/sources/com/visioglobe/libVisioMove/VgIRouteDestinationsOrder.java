package com.visioglobe.libVisioMove;

public final class VgIRouteDestinationsOrder {
    public static final VgIRouteDestinationsOrder eClosest = new VgIRouteDestinationsOrder("eClosest");
    public static final VgIRouteDestinationsOrder eDestinationOrderMax = new VgIRouteDestinationsOrder("eDestinationOrderMax");
    public static final VgIRouteDestinationsOrder eInOrder = new VgIRouteDestinationsOrder("eInOrder");
    public static final VgIRouteDestinationsOrder eOptimal = new VgIRouteDestinationsOrder("eOptimal");
    public static final VgIRouteDestinationsOrder eOptimalFinishOnLast = new VgIRouteDestinationsOrder("eOptimalFinishOnLast");
    private static int swigNext = 0;
    private static VgIRouteDestinationsOrder[] swigValues = {eInOrder, eOptimal, eOptimalFinishOnLast, eClosest, eDestinationOrderMax};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgIRouteDestinationsOrder swigToEnum(int swigValue2) {
        VgIRouteDestinationsOrder[] vgIRouteDestinationsOrderArr = swigValues;
        if (swigValue2 < vgIRouteDestinationsOrderArr.length && swigValue2 >= 0 && vgIRouteDestinationsOrderArr[swigValue2].swigValue == swigValue2) {
            return vgIRouteDestinationsOrderArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgIRouteDestinationsOrder[] vgIRouteDestinationsOrderArr2 = swigValues;
            if (i >= vgIRouteDestinationsOrderArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgIRouteDestinationsOrder.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgIRouteDestinationsOrderArr2[i].swigValue == swigValue2) {
                return vgIRouteDestinationsOrderArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgIRouteDestinationsOrder(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgIRouteDestinationsOrder(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgIRouteDestinationsOrder(String swigName2, VgIRouteDestinationsOrder swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
