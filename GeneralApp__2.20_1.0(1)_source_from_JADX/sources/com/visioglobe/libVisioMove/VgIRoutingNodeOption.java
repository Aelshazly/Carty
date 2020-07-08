package com.visioglobe.libVisioMove;

public final class VgIRoutingNodeOption {
    public static final VgIRoutingNodeOption eRoutingNodeOptionsAccessOnly = new VgIRoutingNodeOption("eRoutingNodeOptionsAccessOnly");
    public static final VgIRoutingNodeOption eRoutingNodeOptionsAnyNode = new VgIRoutingNodeOption("eRoutingNodeOptionsAnyNode");
    public static final VgIRoutingNodeOption eRoutingNodeOptionsOnEdge = new VgIRoutingNodeOption("eRoutingNodeOptionsOnEdge");
    private static int swigNext = 0;
    private static VgIRoutingNodeOption[] swigValues = {eRoutingNodeOptionsAnyNode, eRoutingNodeOptionsOnEdge, eRoutingNodeOptionsAccessOnly};
    private final String swigName;
    private final int swigValue;

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static VgIRoutingNodeOption swigToEnum(int swigValue2) {
        VgIRoutingNodeOption[] vgIRoutingNodeOptionArr = swigValues;
        if (swigValue2 < vgIRoutingNodeOptionArr.length && swigValue2 >= 0 && vgIRoutingNodeOptionArr[swigValue2].swigValue == swigValue2) {
            return vgIRoutingNodeOptionArr[swigValue2];
        }
        int i = 0;
        while (true) {
            VgIRoutingNodeOption[] vgIRoutingNodeOptionArr2 = swigValues;
            if (i >= vgIRoutingNodeOptionArr2.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("No enum ");
                sb.append(VgIRoutingNodeOption.class);
                sb.append(" with value ");
                sb.append(swigValue2);
                throw new IllegalArgumentException(sb.toString());
            } else if (vgIRoutingNodeOptionArr2[i].swigValue == swigValue2) {
                return vgIRoutingNodeOptionArr2[i];
            } else {
                i++;
            }
        }
    }

    private VgIRoutingNodeOption(String swigName2) {
        this.swigName = swigName2;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private VgIRoutingNodeOption(String swigName2, int swigValue2) {
        this.swigName = swigName2;
        this.swigValue = swigValue2;
        swigNext = swigValue2 + 1;
    }

    private VgIRoutingNodeOption(String swigName2, VgIRoutingNodeOption swigEnum) {
        this.swigName = swigName2;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }
}
