package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class PutninsP5Projection extends Projection {

    /* renamed from: C */
    private static final double f1737C = 1.01346d;

    /* renamed from: D */
    private static final double f1738D = 1.2158542d;

    /* renamed from: A */
    protected double f1739A = 2.0d;

    /* renamed from: B */
    protected double f1740B = 1.0d;

    public Double project(double lplam, double lpphi, Double xy) {
        Double doubleR = xy;
        doubleR.f1626x = lplam * f1737C * (this.f1739A - (this.f1740B * Math.sqrt(((f1738D * lpphi) * lpphi) + 1.0d)));
        doubleR.f1627y = f1737C * lpphi;
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double lp) {
        lp.f1627y = xyy / f1737C;
        lp.f1626x = xyx / ((this.f1739A - (this.f1740B * Math.sqrt(((lp.f1627y * f1738D) * lp.f1627y) + 1.0d))) * f1737C);
        return lp;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Putnins P5";
    }
}
