package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class BoggsProjection extends PseudoCylindricalProjection {
    private static final double EPS = 1.0E-7d;
    private static final double FXC = 2.00276d;
    private static final double FXC2 = 1.11072d;
    private static final double FYC = 0.49931d;
    private static final double FYC2 = 1.4142135623730951d;
    private static final int NITER = 20;
    private static final double ONETOL = 1.000001d;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double theta = lpphi;
        if (Math.abs(Math.abs(lpphi) - 1.5707963267948966d) < EPS) {
            doubleR.f1626x = 0.0d;
        } else {
            double c = Math.sin(theta) * 3.141592653589793d;
            for (int i = 20; i > 0; i--) {
                double sin = ((Math.sin(theta) + theta) - c) / (Math.cos(theta) + 1.0d);
                theta -= sin;
                if (Math.abs(sin) < EPS) {
                    break;
                }
            }
            theta *= 0.5d;
            doubleR.f1626x = (FXC * lplam) / ((1.0d / Math.cos(lpphi)) + (FXC2 / Math.cos(theta)));
        }
        doubleR.f1627y = (lpphi + (Math.sin(theta) * FYC2)) * FYC;
        return doubleR;
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return false;
    }

    public String toString() {
        return "Boggs Eumorphic";
    }
}
