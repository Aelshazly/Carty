package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class RectangularPolyconicProjection extends Projection {
    private static final double EPS = 1.0E-9d;
    private double fxa;
    private double fxb;
    private boolean mode;
    private double phi0;
    private double phi1;

    public Double project(double lplam, double lpphi, Double out) {
        double fa;
        if (this.mode) {
            fa = Math.tan(this.fxb * lplam) * this.fxa;
        } else {
            fa = 0.5d * lplam;
        }
        if (Math.abs(lpphi) < EPS) {
            out.f1626x = fa + fa;
            out.f1627y = -this.phi0;
        } else {
            out.f1627y = 1.0d / Math.tan(lpphi);
            double atan = Math.atan(Math.sin(lpphi) * fa) * 2.0d;
            double fa2 = atan;
            out.f1626x = Math.sin(atan) * out.f1627y;
            out.f1627y = (lpphi - this.phi0) + ((1.0d - Math.cos(fa2)) * out.f1627y);
        }
        return out;
    }

    public void initialize() {
        super.initialize();
    }

    public String toString() {
        return "Rectangular Polyconic";
    }
}
