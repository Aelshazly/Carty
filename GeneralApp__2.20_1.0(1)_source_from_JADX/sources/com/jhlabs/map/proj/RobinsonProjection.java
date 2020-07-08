package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class RobinsonProjection extends PseudoCylindricalProjection {

    /* renamed from: C1 */
    private static final double f1741C1 = 11.459155902616464d;
    private static final double EPS = 1.0E-8d;
    private static final double FXC = 0.8487d;
    private static final double FYC = 1.3523d;
    private static final double ONEEPS = 1.000001d;
    private static final double RC1 = 0.08726646259971647d;

    /* renamed from: X */
    private static final double[] f1742X = {1.0d, -5.67239E-12d, -7.15511E-5d, 3.11028E-6d, 0.9986d, -4.82241E-4d, -2.4897E-5d, -1.33094E-6d, 0.9954d, -8.31031E-4d, -4.4861E-5d, -9.86588E-7d, 0.99d, -0.00135363d, -5.96598E-5d, 3.67749E-6d, 0.9822d, -0.00167442d, -4.4975E-6d, -5.72394E-6d, 0.973d, -0.00214869d, -9.03565E-5d, 1.88767E-8d, 0.96d, -0.00305084d, -9.00732E-5d, 1.64869E-6d, 0.9427d, -0.00382792d, -6.53428E-5d, -2.61493E-6d, 0.9216d, -0.00467747d, -1.04566E-4d, 4.8122E-6d, 0.8962d, -0.00536222d, -3.23834E-5d, -5.43445E-6d, 0.8679d, -0.00609364d, -1.139E-4d, 3.32521E-6d, 0.835d, -0.00698325d, -6.40219E-5d, 9.34582E-7d, 0.7986d, -0.00755337d, -5.00038E-5d, 9.35532E-7d, 0.7597d, -0.00798325d, -3.59716E-5d, -2.27604E-6d, 0.7186d, -0.00851366d, -7.0112E-5d, -8.63072E-6d, 0.6732d, -0.00986209d, -1.99572E-4d, 1.91978E-5d, 0.6213d, -0.010418d, 8.83948E-5d, 6.24031E-6d, 0.5722d, -0.00906601d, 1.81999E-4d, 6.24033E-6d, 0.5322d, 0.0d, 0.0d, 0.0d};

    /* renamed from: Y */
    private static final double[] f1743Y = {0.0d, 0.0124d, 3.72529E-10d, 1.15484E-9d, 0.062d, 0.0124001d, 1.76951E-8d, -5.92321E-9d, 0.124d, 0.0123998d, -7.09668E-8d, 2.25753E-8d, 0.186d, 0.0124008d, 2.66917E-7d, -8.44523E-8d, 0.248d, 0.0123971d, -9.99682E-7d, 3.15569E-7d, 0.31d, 0.0124108d, 3.73349E-6d, -1.1779E-6d, 0.372d, 0.0123598d, -1.3935E-5d, 4.39588E-6d, 0.434d, 0.0125501d, 5.20034E-5d, -1.00051E-5d, 0.4968d, 0.0123198d, -9.80735E-5d, 9.22397E-6d, 0.5571d, 0.0120308d, 4.02857E-5d, -5.2901E-6d, 0.6176d, 0.0120369d, -3.90662E-5d, 7.36117E-7d, 0.6769d, 0.0117015d, -2.80246E-5d, -8.54283E-7d, 0.7346d, 0.0113572d, -4.08389E-5d, -5.18524E-7d, 0.7903d, 0.0109099d, -4.86169E-5d, -1.0718E-6d, 0.8435d, 0.0103433d, -6.46934E-5d, 5.36384E-9d, 0.8936d, 0.00969679d, -6.46129E-5d, -8.54894E-6d, 0.9394d, 0.00840949d, -1.92847E-4d, -4.21023E-6d, 0.9761d, 0.00616525d, -2.56001E-4d, -4.21021E-6d, 1.0d, 0.0d, 0.0d, 0.0d};
    private final int NODES = 18;

    private double poly(double[] array, int offset, double z) {
        return array[offset] + ((array[offset + 1] + ((array[offset + 2] + (array[offset + 3] * z)) * z)) * z);
    }

    public Double project(double lplam, double lpphi, Double xy) {
        double phi = Math.abs(lpphi);
        int i = (int) Math.floor(f1741C1 * phi);
        if (i >= 18) {
            i = 17;
        }
        double phi2 = Math.toDegrees(phi - (((double) i) * RC1));
        int i2 = i * 4;
        xy.f1626x = poly(f1742X, i2, phi2) * FXC * lplam;
        xy.f1627y = poly(f1743Y, i2, phi2) * FYC;
        if (lpphi < 0.0d) {
            xy.f1627y = -xy.f1627y;
        }
        return xy;
    }

    public Double projectInverse(double x, double y, Double lp) {
        double t1;
        Double doubleR = lp;
        doubleR.f1626x = x / FXC;
        doubleR.f1627y = Math.abs(y / FYC);
        if (doubleR.f1627y < 1.0d) {
            int i = ((int) Math.floor(doubleR.f1627y * 18.0d)) * 4;
            while (true) {
                if (f1743Y[i] <= doubleR.f1627y) {
                    if (f1743Y[i + 4] > doubleR.f1627y) {
                        break;
                    }
                    i += 4;
                } else {
                    i -= 4;
                }
            }
            double d = doubleR.f1627y;
            double[] dArr = f1743Y;
            double d2 = ((d - dArr[i]) * 5.0d) / (dArr[i + 4] - dArr[i]);
            double Tc0 = dArr[i];
            double Tc1 = dArr[i + 1];
            double Tc2 = dArr[i + 2];
            double Tc3 = dArr[i + 3];
            double t = ((doubleR.f1627y - Tc0) * 5.0d) / (f1743Y[i + 1] - Tc0);
            double Tc02 = Tc0 - doubleR.f1627y;
            while (true) {
                double d3 = ((((((t * Tc3) + Tc2) * t) + Tc1) * t) + Tc02) / ((((Tc2 + Tc2) + ((3.0d * t) * Tc3)) * t) + Tc1);
                t1 = d3;
                t -= d3;
                if (Math.abs(t1) < EPS) {
                    break;
                }
                double d4 = t1;
            }
            doubleR.f1627y = Math.toRadians(((double) (i * 5)) + t);
            if (y < 0.0d) {
                doubleR.f1627y = -doubleR.f1627y;
            }
            double d5 = t1;
            doubleR.f1626x /= poly(f1742X, i, t);
        } else if (doubleR.f1627y <= ONEEPS) {
            doubleR.f1627y = y < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
            doubleR.f1626x /= f1742X[72];
        } else {
            throw new ProjectionException();
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Robinson";
    }
}
