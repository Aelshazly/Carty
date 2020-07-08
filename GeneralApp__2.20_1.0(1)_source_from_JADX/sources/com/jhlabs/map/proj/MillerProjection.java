package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class MillerProjection extends CylindricalProjection {
    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = lplam;
        out.f1627y = Math.log(Math.tan((0.4d * lpphi) + 0.7853981633974483d)) * 1.25d;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1626x = xyx;
        out.f1627y = (Math.atan(Math.exp(0.8d * xyy)) - 0.7853981633974483d) * 2.5d;
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Miller Cylindrical";
    }
}
