package com.navibees.core.model.postioning.p026i;

import java.lang.reflect.Array;
import org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.Pair;

/* renamed from: com.navibees.core.model.postioning.i.c */
/* compiled from: TrilaterationFunction */
public class C1717c implements MultivariateJacobianFunction {

    /* renamed from: c */
    protected static final double f1560c = 1.0E-7d;

    /* renamed from: a */
    protected final double[][] f1561a;

    /* renamed from: b */
    protected final double[] f1562b;

    public C1717c(double[][] dArr, double[] dArr2) {
        if (dArr.length < 2) {
            throw new IllegalArgumentException("Need at least two positions.");
        } else if (dArr.length == dArr2.length) {
            for (int i = 0; i < dArr2.length; i++) {
                dArr2[i] = Math.max(dArr2[i], f1560c);
            }
            int length = dArr[0].length;
            int i2 = 1;
            while (i2 < dArr.length) {
                if (length == dArr[i2].length) {
                    i2++;
                } else {
                    throw new IllegalArgumentException("The dimension of all positions should be the same.");
                }
            }
            this.f1561a = dArr;
            this.f1562b = dArr2;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("The number of positions you provided, ");
            sb.append(dArr.length);
            sb.append(", does not match the number of distances, ");
            sb.append(dArr2.length);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: a */
    public final double[] mo29398a() {
        return this.f1562b;
    }

    /* renamed from: b */
    public final double[][] mo29399b() {
        return this.f1561a;
    }

    public Pair<RealVector, RealMatrix> value(RealVector realVector) {
        double[] array = realVector.toArray();
        double[] dArr = new double[this.f1562b.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = 0.0d;
            for (int i2 = 0; i2 < array.length; i2++) {
                dArr[i] = dArr[i] + ((array[i2] - mo29399b()[i][i2]) * (array[i2] - mo29399b()[i][i2]));
            }
            dArr[i] = dArr[i] - (mo29398a()[i] * mo29398a()[i]);
        }
        return new Pair<>(new ArrayRealVector(dArr), mo29397a(realVector));
    }

    /* renamed from: a */
    public RealMatrix mo29397a(RealVector realVector) {
        double[] array = realVector.toArray();
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{this.f1562b.length, array.length});
        for (int i = 0; i < dArr.length; i++) {
            for (int i2 = 0; i2 < array.length; i2++) {
                dArr[i][i2] = (array[i2] * 2.0d) - (this.f1561a[i][i2] * 2.0d);
            }
        }
        return new Array2DRowRealMatrix(dArr);
    }
}
