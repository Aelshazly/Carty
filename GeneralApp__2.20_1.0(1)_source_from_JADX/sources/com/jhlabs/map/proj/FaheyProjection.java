package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class FaheyProjection extends Projection {
    private static final double TOL = 1.0E-6d;

    public Double project(double lplam, double lpphi, Double out) {
        double tan = Math.tan(0.5d * lpphi);
        out.f1626x = tan;
        out.f1627y = tan * 1.819152d;
        out.f1626x = 0.819152d * lplam * asqrt(1.0d - (out.f1626x * out.f1626x));
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double d;
        double d2 = out.f1627y / 1.819152d;
        out.f1627y = d2;
        out.f1627y = Math.atan(d2) * 2.0d;
        double d3 = 1.0d - (xyy * xyy);
        out.f1627y = d3;
        if (Math.abs(d3) < TOL) {
            d = 0.0d;
        } else {
            d = xyx / (Math.sqrt(xyy) * 0.819152d);
        }
        out.f1626x = d;
        return out;
    }

    private double asqrt(double v) {
        if (v <= 0.0d) {
            return 0.0d;
        }
        return Math.sqrt(v);
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Fahey";
    }
}
