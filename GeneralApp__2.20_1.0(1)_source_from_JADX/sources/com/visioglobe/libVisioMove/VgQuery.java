package com.visioglobe.libVisioMove;

public class VgQuery {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class Operator {
        public static final Operator eEquals = new Operator("eEquals", libVisioMoveJNI.VgQuery_eEquals_get());
        private static int swigNext = 0;
        private static Operator[] swigValues = {eEquals};
        private final String swigName;
        private final int swigValue;

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        public static Operator swigToEnum(int swigValue2) {
            Operator[] operatorArr = swigValues;
            if (swigValue2 < operatorArr.length && swigValue2 >= 0 && operatorArr[swigValue2].swigValue == swigValue2) {
                return operatorArr[swigValue2];
            }
            int i = 0;
            while (true) {
                Operator[] operatorArr2 = swigValues;
                if (i >= operatorArr2.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No enum ");
                    sb.append(Operator.class);
                    sb.append(" with value ");
                    sb.append(swigValue2);
                    throw new IllegalArgumentException(sb.toString());
                } else if (operatorArr2[i].swigValue == swigValue2) {
                    return operatorArr2[i];
                } else {
                    i++;
                }
            }
        }

        private Operator(String swigName2) {
            this.swigName = swigName2;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        private Operator(String swigName2, int swigValue2) {
            this.swigName = swigName2;
            this.swigValue = swigValue2;
            swigNext = swigValue2 + 1;
        }

        private Operator(String swigName2, Operator swigEnum) {
            this.swigName = swigName2;
            this.swigValue = swigEnum.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    protected VgQuery(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgQuery obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                libVisioMoveJNI.delete_VgQuery(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgQuery() {
        this(libVisioMoveJNI.new_VgQuery(), true);
    }

    public VgQuery where(String pKey, Operator pOp, String pValue) {
        return new VgQuery(libVisioMoveJNI.VgQuery_where(this.swigCPtr, this, pKey, pOp.swigValue(), pValue), false);
    }

    public VgQuery reset() {
        return new VgQuery(libVisioMoveJNI.VgQuery_reset(this.swigCPtr, this), false);
    }
}
