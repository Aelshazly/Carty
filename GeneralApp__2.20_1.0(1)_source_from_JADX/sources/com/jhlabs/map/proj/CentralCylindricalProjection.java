package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class CentralCylindricalProjection extends CylindricalProjection {
    private static final double EPS10 = 1.0E-10d;

    /* renamed from: ap */
    private double f1661ap;

    public CentralCylindricalProjection() {
        this.minLatitude = Math.toRadians(-80.0d);
        this.maxLatitude = Math.toRadians(80.0d);
    }

    public Double project(double lplam, double lpphi, Double out) {
        if (Math.abs(Math.abs(lpphi) - 1.5707963267948966d) > EPS10) {
            out.f1626x = lplam;
            out.f1627y = Math.tan(lpphi);
            return out;
        }
        throw new ProjectionException("F");
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = Math.atan(xyy);
        out.f1626x = xyx;
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Central Cylindrical";
    }
}
