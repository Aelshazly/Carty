package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class PutninsP4Projection extends Projection {
    protected double C_x = 0.874038744d;
    protected double C_y = 3.883251825d;

    public Double project(double lplam, double lpphi, Double xy) {
        double lpphi2 = MapMath.asin(Math.sin(lpphi) * 0.883883476d);
        xy.f1626x = this.C_x * lplam * Math.cos(lpphi2);
        double d = 0.333333333333333d * lpphi2;
        double lpphi3 = d;
        xy.f1626x /= Math.cos(d);
        xy.f1627y = this.C_y * Math.sin(lpphi3);
        return xy;
    }

    public Double projectInverse(double xyx, double xyy, Double lp) {
        lp.f1627y = MapMath.asin(xyy / this.C_y);
        lp.f1626x = (Math.cos(lp.f1627y) * xyx) / this.C_x;
        lp.f1627y *= 3.0d;
        lp.f1626x /= Math.cos(lp.f1627y);
        lp.f1627y = MapMath.asin(Math.sin(lp.f1627y) * 1.13137085d);
        return lp;
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Putnins P4";
    }
}
