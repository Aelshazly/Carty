package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class Eckert5Projection extends Projection {
    private static final double RXF = 2.267508027238226d;
    private static final double RYF = 1.133754013619113d;

    /* renamed from: XF */
    private static final double f1672XF = 0.4410127717245515d;

    /* renamed from: YF */
    private static final double f1673YF = 0.882025543449103d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = (Math.cos(lpphi) + 1.0d) * f1672XF * lplam;
        out.f1627y = f1673YF * lpphi;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double d = RXF * xyx;
        double d2 = RYF * xyy;
        out.f1627y = d2;
        out.f1626x = d / (Math.cos(d2) + 1.0d);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Eckert V";
    }
}
