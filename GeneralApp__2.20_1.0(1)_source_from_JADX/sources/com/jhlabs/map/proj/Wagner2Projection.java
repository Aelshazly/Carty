package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class Wagner2Projection extends Projection {
    private static final double C_p1 = 0.88022d;
    private static final double C_p2 = 0.8855d;
    private static final double C_x = 0.92483d;
    private static final double C_y = 1.38725d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1627y = MapMath.asin(Math.sin(C_p2 * lpphi) * C_p1);
        out.f1626x = C_x * lplam * Math.cos(lpphi);
        out.f1627y = C_y * lpphi;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = xyy / C_y;
        out.f1626x = xyx / (Math.cos(out.f1627y) * C_x);
        out.f1627y = MapMath.asin(Math.sin(out.f1627y) / C_p1) / C_p2;
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Wagner II";
    }
}
