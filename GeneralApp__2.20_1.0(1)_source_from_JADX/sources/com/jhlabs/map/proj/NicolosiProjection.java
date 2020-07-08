package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class NicolosiProjection extends Projection {
    private static final double EPS = 1.0E-10d;

    public Double project(double lplam, double lpphi, Double out) {
        double d = lplam;
        double d2 = lpphi;
        Double doubleR = out;
        if (Math.abs(lplam) < EPS) {
            doubleR.f1626x = 0.0d;
            doubleR.f1627y = d2;
        } else if (Math.abs(lpphi) < EPS) {
            doubleR.f1626x = d;
            doubleR.f1627y = 0.0d;
        } else if (Math.abs(Math.abs(lplam) - 1.5707963267948966d) < EPS) {
            doubleR.f1626x = Math.cos(lpphi) * d;
            doubleR.f1627y = Math.sin(lpphi) * 1.5707963267948966d;
        } else if (Math.abs(Math.abs(lpphi) - 1.5707963267948966d) < EPS) {
            doubleR.f1626x = 0.0d;
            doubleR.f1627y = d2;
        } else {
            double tb = (1.5707963267948966d / d) - (d / 1.5707963267948966d);
            double c = d2 / 1.5707963267948966d;
            double d3 = 1.0d - (c * c);
            double sin = Math.sin(lpphi);
            double sp = sin;
            double d4 = d3 / (sin - c);
            double r2 = tb / d4;
            double r22 = r2 * r2;
            double m = (((tb * sp) / d4) - (tb * 0.5d)) / (r22 + 1.0d);
            double n = ((sp / r22) + (0.5d * d4)) / ((1.0d / r22) + 1.0d);
            double x = Math.cos(lpphi);
            double x2 = Math.sqrt((m * m) + ((x * x) / (r22 + 1.0d)));
            doubleR.f1626x = (m + (d < 0.0d ? -x2 : x2)) * 1.5707963267948966d;
            double y = Math.sqrt((n * n) - (((((sp * sp) / r22) + (d4 * sp)) - 1.0d) / ((1.0d / r22) + 1.0d)));
            doubleR.f1627y = (n + (d2 < 0.0d ? y : -y)) * 1.5707963267948966d;
        }
        return doubleR;
    }

    public String toString() {
        return "Nicolosi Globular";
    }
}
