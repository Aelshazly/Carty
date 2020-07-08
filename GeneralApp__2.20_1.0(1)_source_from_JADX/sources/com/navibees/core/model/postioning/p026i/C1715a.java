package com.navibees.core.model.postioning.p026i;

import java.lang.reflect.Array;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealVector;

/* renamed from: com.navibees.core.model.postioning.i.a */
/* compiled from: LinearLeastSquaresSolver */
public class C1715a {

    /* renamed from: a */
    protected final C1717c f1556a;

    public C1715a(C1717c cVar) {
        this.f1556a = cVar;
    }

    /* renamed from: a */
    public RealVector mo29392a(boolean z) {
        ArrayRealVector arrayRealVector;
        int length = this.f1556a.mo29399b().length;
        int length2 = this.f1556a.mo29399b()[0].length;
        int i = length - 1;
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i, length2});
        for (int i2 = 1; i2 < length; i2++) {
            double[] dArr2 = new double[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                dArr2[i3] = this.f1556a.mo29399b()[i2][i3] - this.f1556a.mo29399b()[0][i3];
            }
            dArr[i2 - 1] = dArr2;
        }
        if (z) {
            System.out.println(new Array2DRowRealMatrix(dArr));
        }
        double d = this.f1556a.mo29398a()[0];
        double d2 = d * d;
        double[] dArr3 = new double[i];
        for (int i4 = 1; i4 < length; i4++) {
            double d3 = this.f1556a.mo29398a()[i4];
            double d4 = d3 * d3;
            double d5 = 0.0d;
            for (int i5 = 0; i5 < length2; i5++) {
                double d6 = this.f1556a.mo29399b()[i4][i5] - this.f1556a.mo29399b()[0][i5];
                d5 += d6 * d6;
            }
            dArr3[i4 - 1] = ((d2 - d4) + d5) * 0.5d;
        }
        if (z) {
            System.out.println(new ArrayRealVector(dArr3));
        }
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr, false);
        ArrayRealVector arrayRealVector2 = new ArrayRealVector(dArr3, false);
        DecompositionSolver solver = new QRDecomposition(array2DRowRealMatrix).getSolver();
        if (!solver.isNonSingular()) {
            arrayRealVector = new ArrayRealVector(new double[length2]);
        } else {
            arrayRealVector = solver.solve(arrayRealVector2);
        }
        return arrayRealVector.add(new ArrayRealVector(this.f1556a.mo29399b()[0]));
    }

    /* renamed from: a */
    public RealVector mo29391a() {
        return mo29392a(false);
    }
}
