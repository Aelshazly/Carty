package com.visioglobe.libVisioMove;

public class VgValue {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class Type {
        public static final Type eVT_Float1 = new Type("eVT_Float1");
        public static final Type eVT_Float2 = new Type("eVT_Float2");
        public static final Type eVT_Float3 = new Type("eVT_Float3");
        public static final Type eVT_Float3x3 = new Type("eVT_Float3x3");
        public static final Type eVT_Float4 = new Type("eVT_Float4");
        public static final Type eVT_Float4x4 = new Type("eVT_Float4x4");
        public static final Type eVT_Int1 = new Type("eVT_Int1");
        public static final Type eVT_None = new Type("eVT_None");
        private static int swigNext = 0;
        private static Type[] swigValues = {eVT_Int1, eVT_Float1, eVT_Float2, eVT_Float3, eVT_Float4, eVT_Float3x3, eVT_Float4x4, eVT_None};
        private final String swigName;
        private final int swigValue;

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        public static Type swigToEnum(int swigValue2) {
            Type[] typeArr = swigValues;
            if (swigValue2 < typeArr.length && swigValue2 >= 0 && typeArr[swigValue2].swigValue == swigValue2) {
                return typeArr[swigValue2];
            }
            int i = 0;
            while (true) {
                Type[] typeArr2 = swigValues;
                if (i >= typeArr2.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No enum ");
                    sb.append(Type.class);
                    sb.append(" with value ");
                    sb.append(swigValue2);
                    throw new IllegalArgumentException(sb.toString());
                } else if (typeArr2[i].swigValue == swigValue2) {
                    return typeArr2[i];
                } else {
                    i++;
                }
            }
        }

        private Type(String swigName2) {
            this.swigName = swigName2;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        private Type(String swigName2, int swigValue2) {
            this.swigName = swigName2;
            this.swigValue = swigValue2;
            swigNext = swigValue2 + 1;
        }

        private Type(String swigName2, Type swigEnum) {
            this.swigName = swigName2;
            this.swigValue = swigEnum.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    protected VgValue(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VgValue obj) {
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
                libVisioMoveJNI.delete_VgValue(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public VgValue() {
        this(libVisioMoveJNI.new_VgValue(), true);
    }

    public void setInt(int pValue) {
        libVisioMoveJNI.VgValue_setInt(this.swigCPtr, this, pValue);
    }

    public void getInt(int[] pResult) {
        libVisioMoveJNI.VgValue_getInt(this.swigCPtr, this, pResult);
    }

    public void setFloat(float pValue) {
        libVisioMoveJNI.VgValue_setFloat(this.swigCPtr, this, pValue);
    }

    public void getFloat(float[] pResult) {
        libVisioMoveJNI.VgValue_getFloat(this.swigCPtr, this, pResult);
    }

    public void setVector2(float[] pValue) {
        libVisioMoveJNI.VgValue_setVector2(this.swigCPtr, this, pValue);
    }

    public void getVector2(float[] pResult) {
        libVisioMoveJNI.VgValue_getVector2(this.swigCPtr, this, pResult);
    }

    public void setVector3(float[] pValue) {
        libVisioMoveJNI.VgValue_setVector3(this.swigCPtr, this, pValue);
    }

    public void getVector3(float[] pResult) {
        libVisioMoveJNI.VgValue_getVector3(this.swigCPtr, this, pResult);
    }

    public void setVector4(float[] pValue) {
        libVisioMoveJNI.VgValue_setVector4(this.swigCPtr, this, pValue);
    }

    public void getVector4(float[] pResult) {
        libVisioMoveJNI.VgValue_getVector4(this.swigCPtr, this, pResult);
    }

    public void setMatrix3(float[] pValue) {
        libVisioMoveJNI.VgValue_setMatrix3(this.swigCPtr, this, pValue);
    }

    public void getMatrix3(float[] pResult) {
        libVisioMoveJNI.VgValue_getMatrix3(this.swigCPtr, this, pResult);
    }

    public void setMatrix4(float[] pValue) {
        libVisioMoveJNI.VgValue_setMatrix4(this.swigCPtr, this, pValue);
    }

    public void getMatrix4(float[] pResult) {
        libVisioMoveJNI.VgValue_getMatrix4(this.swigCPtr, this, pResult);
    }
}
