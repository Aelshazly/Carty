package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class MBTFPSProjection extends Projection {

    /* renamed from: C1 */
    private static final double f1717C1 = 0.45503d;
    private static final double C1_2 = 0.3333333333333333d;

    /* renamed from: C2 */
    private static final double f1718C2 = 1.36509d;

    /* renamed from: C3 */
    private static final double f1719C3 = 1.41546d;
    private static final double C_x = 0.22248d;
    private static final double C_y = 1.44492d;
    private static final double LOOP_TOL = 1.0E-7d;
    private static final int MAX_ITER = 10;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double k = Math.sin(lpphi) * f1719C3;
        for (int i = 10; i > 0; i--) {
            double t = lpphi / f1718C2;
            double sin = (((Math.sin(t) * f1717C1) + Math.sin(lpphi)) - k) / ((Math.cos(t) * C1_2) + Math.cos(lpphi));
            double V = sin;
            doubleR.f1627y -= sin;
            if (Math.abs(V) < LOOP_TOL) {
                break;
            }
        }
        double t2 = lpphi / f1718C2;
        doubleR.f1626x = C_x * lplam * (((Math.cos(lpphi) * 3.0d) / Math.cos(t2)) + 1.0d);
        doubleR.f1627y = Math.sin(t2) * C_y;
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double asin = MapMath.asin(xyy / C_y);
        double t = asin;
        out.f1627y = asin * f1718C2;
        out.f1626x = xyx / ((((Math.cos(out.f1627y) * 3.0d) / Math.cos(t)) + 1.0d) * C_x);
        out.f1627y = MapMath.asin(((Math.sin(t) * f1717C1) + Math.sin(out.f1627y)) / f1719C3);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "McBryde-Thomas Flat-Pole Sine (No. 2)";
    }
}
