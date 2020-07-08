package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class HammerProjection extends PseudoCylindricalProjection {

    /* renamed from: m */
    private double f1691m = 1.0d;

    /* renamed from: rm */
    private double f1692rm;

    /* renamed from: w */
    private double f1693w = 0.5d;

    public Double project(double lplam, double lpphi, Double xy) {
        double cos = Math.cos(lpphi);
        double cosphi = cos;
        double d = this.f1693w * lplam;
        double lplam2 = d;
        double d2 = Math.sqrt(2.0d / ((cos * Math.cos(d)) + 1.0d));
        xy.f1626x = this.f1691m * d2 * cosphi * Math.sin(lplam2);
        xy.f1627y = this.f1692rm * d2 * Math.sin(lpphi);
        return xy;
    }

    public void initialize() {
        super.initialize();
        double abs = Math.abs(this.f1693w);
        this.f1693w = abs;
        String str = "-27";
        if (abs > 0.0d) {
            this.f1693w = 0.5d;
            double abs2 = Math.abs(this.f1691m);
            this.f1691m = abs2;
            if (abs2 > 0.0d) {
                this.f1691m = 1.0d;
                double d = this.f1691m;
                this.f1692rm = 1.0d / d;
                this.f1691m = d / this.f1693w;
                this.f1736es = 0.0d;
                return;
            }
            throw new ProjectionException(str);
        }
        throw new ProjectionException(str);
    }

    public boolean isEqualArea() {
        return true;
    }

    public void setW(double w) {
        this.f1693w = w;
    }

    public double getW() {
        return this.f1693w;
    }

    public void setM(double m) {
        this.f1691m = m;
    }

    public double getM() {
        return this.f1691m;
    }

    public String toString() {
        return "Hammer & Eckert-Greifendorff";
    }
}
