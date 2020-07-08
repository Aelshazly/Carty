package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class CrasterProjection extends Projection {
    private static final double RXM = 1.0233267079464885d;
    private static final double RYM = 0.32573500793527993d;
    private static final double THIRD = 0.3333333333333333d;

    /* renamed from: XM */
    private static final double f1662XM = 0.9772050238058398d;

    /* renamed from: YM */
    private static final double f1663YM = 3.0699801238394655d;

    public Double project(double lplam, double lpphi, Double out) {
        double lpphi2 = lpphi * THIRD;
        out.f1626x = f1662XM * lplam * ((Math.cos(lpphi2 + lpphi2) * 2.0d) - 1.0d);
        out.f1627y = Math.sin(lpphi2) * f1663YM;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = Math.asin(RYM * xyy) * 3.0d;
        out.f1626x = (RXM * xyx) / ((Math.cos((out.f1627y + out.f1627y) * THIRD) * 2.0d) - 1.0d);
        return out;
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Craster Parabolic (Putnins P4)";
    }
}
