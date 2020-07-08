package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class Wagner3Projection extends PseudoCylindricalProjection {
    private static final double TWOTHIRD = 0.6666666666666666d;
    private double C_x;

    public Double project(double lplam, double lpphi, Double xy) {
        xy.f1626x = this.C_x * lplam * Math.cos(TWOTHIRD * lpphi);
        xy.f1627y = lpphi;
        return xy;
    }

    public Double projectInverse(double x, double y, Double lp) {
        lp.f1627y = y;
        lp.f1626x = x / (this.C_x * Math.cos(lp.f1627y * TWOTHIRD));
        return lp;
    }

    public void initialize() {
        super.initialize();
        this.C_x = Math.cos(this.trueScaleLatitude) / Math.cos((this.trueScaleLatitude * 2.0d) / 3.0d);
        this.f1736es = 0.0d;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Wagner III";
    }
}
