package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class NellProjection extends Projection {
    private static final double LOOP_TOL = 1.0E-7d;
    private static final int MAX_ITER = 10;

    public Double project(double lplam, double lpphi, Double out) {
        double d = lpphi;
        Double doubleR = out;
        double k = Math.sin(lpphi) * 2.0d;
        double V = d * d;
        doubleR.f1627y *= (((-0.011412d * V) - 48.1084416d) * V) + 1.00371d;
        for (int i = 10; i > 0; i--) {
            double sin = ((Math.sin(lpphi) + d) - k) / (Math.cos(lpphi) + 1.0d);
            double V2 = sin;
            doubleR.f1627y -= sin;
            if (Math.abs(V2) < LOOP_TOL) {
                break;
            }
        }
        doubleR.f1626x = 0.5d * lplam * (Math.cos(lpphi) + 1.0d);
        doubleR.f1627y = d;
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1626x = (2.0d * xyx) / (Math.cos(xyy) + 1.0d);
        out.f1627y = MapMath.asin((Math.sin(xyy) + xyy) * 0.5d);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Nell";
    }
}
