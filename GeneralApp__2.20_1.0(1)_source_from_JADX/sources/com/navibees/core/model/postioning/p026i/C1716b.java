package com.navibees.core.model.postioning.p026i;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DiagonalMatrix;

/* renamed from: com.navibees.core.model.postioning.i.b */
/* compiled from: NonLinearLeastSquaresSolver */
public class C1716b {

    /* renamed from: c */
    protected static final int f1557c = 1000;

    /* renamed from: a */
    protected final C1717c f1558a;

    /* renamed from: b */
    protected final LeastSquaresOptimizer f1559b;

    public C1716b(C1717c cVar, LeastSquaresOptimizer leastSquaresOptimizer) {
        this.f1558a = cVar;
        this.f1559b = leastSquaresOptimizer;
    }

    /* renamed from: a */
    private double m1137a(double d) {
        return 1.0d / (d * d);
    }

    /* renamed from: a */
    public Optimum mo29396a(double[] dArr, double[] dArr2, double[] dArr3, boolean z) {
        if (z) {
            System.out.println("Max Number of Iterations : 1000");
        }
        return this.f1559b.optimize(LeastSquaresFactory.create(this.f1558a, new ArrayRealVector(dArr, false), new ArrayRealVector(dArr3, false), new DiagonalMatrix(dArr2), null, 1000, 1000));
    }

    /* renamed from: a */
    public Optimum mo29395a(double[] dArr, double[] dArr2, double[] dArr3) {
        return mo29396a(dArr, dArr2, dArr3, false);
    }

    /* renamed from: a */
    public Optimum mo29394a(boolean z) {
        int length = this.f1558a.mo29399b().length;
        double[] dArr = new double[this.f1558a.mo29399b()[0].length];
        for (double[] dArr2 : this.f1558a.mo29399b()) {
            for (int i = 0; i < dArr2.length; i++) {
                dArr[i] = dArr[i] + dArr2[i];
            }
        }
        for (int i2 = 0; i2 < dArr.length; i2++) {
            dArr[i2] = dArr[i2] / ((double) length);
        }
        if (z) {
            StringBuilder sb = new StringBuilder("initialPoint: ");
            for (double append : dArr) {
                sb.append(append);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
        double[] dArr3 = new double[length];
        double[] a = this.f1558a.mo29398a();
        double[] dArr4 = new double[dArr3.length];
        for (int i3 = 0; i3 < dArr3.length; i3++) {
            dArr3[i3] = 0.0d;
            dArr4[i3] = m1137a(a[i3]);
        }
        return mo29396a(dArr3, dArr4, dArr, z);
    }

    /* renamed from: a */
    public Optimum mo29393a() {
        return mo29394a(false);
    }
}
