package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class AugustProjection extends Projection {

    /* renamed from: M */
    private static final double f1639M = 1.333333333333333d;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double t = Math.tan(lpphi * 0.5d);
        double c1 = Math.sqrt(1.0d - (t * t));
        double d = 0.5d * lplam;
        double lplam2 = d;
        double c = (Math.cos(d) * c1) + 1.0d;
        double x1 = (Math.sin(lplam2) * c1) / c;
        double y1 = t / c;
        double d2 = x1 * x1;
        double x12 = d2;
        double d3 = y1 * y1;
        double y12 = d3;
        doubleR.f1626x = x1 * f1639M * ((d2 + 3.0d) - (d3 * 3.0d));
        doubleR.f1627y = f1639M * y1 * (((x12 * 3.0d) + 3.0d) - y12);
        return doubleR;
    }

    public boolean isConformal() {
        return true;
    }

    public boolean hasInverse() {
        return false;
    }

    public String toString() {
        return "August Epicycloidal";
    }
}
