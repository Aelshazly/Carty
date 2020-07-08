package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class GallProjection extends Projection {
    private static final double RXF = 1.4142135623730951d;
    private static final double RYF = 0.585786437626905d;

    /* renamed from: XF */
    private static final double f1687XF = 0.7071067811865476d;

    /* renamed from: YF */
    private static final double f1688YF = 1.7071067811865475d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = f1687XF * lplam;
        out.f1627y = Math.tan(0.5d * lpphi) * f1688YF;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1626x = RXF * xyx;
        out.f1627y = Math.atan(RYF * xyy) * 2.0d;
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Gall (Gall Stereographic)";
    }
}
