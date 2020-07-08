package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class MBTFPPProjection extends Projection {
    private static final double C13 = 0.3333333333333333d;
    private static final double C23 = 0.6666666666666666d;

    /* renamed from: CS */
    private static final double f1714CS = 0.9525793444156804d;
    private static final double FXC = 0.9258200997725514d;
    private static final double FYC = 3.401680257083045d;
    private static final double ONEEPS = 1.0000001d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1627y = Math.asin(Math.sin(lpphi) * f1714CS);
        out.f1626x = FXC * lplam * ((Math.cos(C23 * lpphi) * 2.0d) - 1.0d);
        out.f1627y = Math.sin(C13 * lpphi) * FYC;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        Double doubleR = out;
        doubleR.f1627y = xyy / FYC;
        double d = 1.5707963267948966d;
        String str = "I";
        if (Math.abs(doubleR.f1627y) < 1.0d) {
            doubleR.f1627y = Math.asin(doubleR.f1627y);
        } else if (Math.abs(doubleR.f1627y) <= ONEEPS) {
            doubleR.f1627y = doubleR.f1627y < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
        } else {
            throw new ProjectionException(str);
        }
        double d2 = doubleR.f1627y * 3.0d;
        doubleR.f1627y = d2;
        doubleR.f1626x = xyx / (((Math.cos(d2 * C23) * 2.0d) - 1.0d) * FXC);
        double sin = Math.sin(doubleR.f1627y) / f1714CS;
        doubleR.f1627y = sin;
        if (Math.abs(sin) < 1.0d) {
            doubleR.f1627y = Math.asin(doubleR.f1627y);
        } else if (Math.abs(doubleR.f1627y) <= ONEEPS) {
            if (doubleR.f1627y < 0.0d) {
                d = -1.5707963267948966d;
            }
            doubleR.f1627y = d;
        } else {
            throw new ProjectionException(str);
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "McBride-Thomas Flat-Polar Parabolic";
    }
}
