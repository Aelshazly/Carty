package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class Wagner7Projection extends Projection {
    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double sin = Math.sin(lpphi) * 0.9063077870366499d;
        doubleR.f1627y = sin;
        double cos = Math.cos(Math.asin(sin));
        double ct = cos;
        double d = lplam / 3.0d;
        double lplam2 = d;
        doubleR.f1626x = cos * 2.66723d * Math.sin(d);
        double sqrt = 1.0d / Math.sqrt(((Math.cos(lplam2) * ct) + 1.0d) * 0.5d);
        double D = sqrt;
        doubleR.f1627y *= sqrt * 1.24104d;
        doubleR.f1626x *= D;
        return doubleR;
    }

    public boolean isEqualArea() {
        return true;
    }

    public String toString() {
        return "Wagner VII";
    }
}
