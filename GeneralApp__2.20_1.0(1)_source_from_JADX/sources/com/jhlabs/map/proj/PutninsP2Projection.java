package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class PutninsP2Projection extends Projection {
    private static final double C_p = 0.6141848493043784d;
    private static final double C_x = 1.8949d;
    private static final double C_y = 1.71848d;
    private static final double EPS = 1.0E-10d;
    private static final int NITER = 10;
    private static final double PI_DIV_3 = 1.0471975511965976d;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double p = Math.sin(lpphi) * C_p;
        double s = lpphi * lpphi;
        doubleR.f1627y *= (((0.0046292d * s) + 0.00909953d) * s) + 0.615709d;
        int i = 10;
        while (i > 0) {
            double c = Math.cos(lpphi);
            double s2 = Math.sin(lpphi);
            double d = ((lpphi + ((c - 1.0d) * s2)) - p) / ((((c - 1.0d) * c) + 1.0d) - (s2 * s2));
            double V = d;
            doubleR.f1627y -= d;
            if (Math.abs(V) < EPS) {
                break;
            }
            i--;
        }
        if (i == 0) {
            doubleR.f1627y = lpphi < 0.0d ? -1.0471975511965976d : PI_DIV_3;
        }
        doubleR.f1626x = C_x * lplam * (Math.cos(lpphi) - 0.5d);
        doubleR.f1627y = Math.sin(lpphi) * C_y;
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = MapMath.asin(xyy / C_y);
        double cos = Math.cos(out.f1627y);
        double c = cos;
        out.f1626x = xyx / ((cos - 0.5d) * C_x);
        out.f1627y = MapMath.asin((out.f1627y + (Math.sin(out.f1627y) * (c - 1.0d))) / C_p);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Putnins P2";
    }
}
