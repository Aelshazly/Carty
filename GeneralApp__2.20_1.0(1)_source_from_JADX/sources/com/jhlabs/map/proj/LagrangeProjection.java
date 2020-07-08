package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class LagrangeProjection extends Projection {
    private static final double TOL = 1.0E-10d;

    /* renamed from: a1 */
    private double f1696a1;
    private double hrw;
    private double phi1;

    /* renamed from: rw */
    private double f1697rw = 1.4d;

    public Double project(double lplam, double lpphi, Double xy) {
        Double doubleR = xy;
        double d = 2.0d;
        if (Math.abs(Math.abs(lpphi) - 1.5707963267948966d) < TOL) {
            doubleR.f1626x = 0.0d;
            if (lpphi < 0.0d) {
                d = -2.0d;
            }
            doubleR.f1627y = d;
            double d2 = lplam;
            double d3 = lpphi;
        } else {
            double lpphi2 = Math.sin(lpphi);
            double v = this.f1696a1 * Math.pow((lpphi2 + 1.0d) / (1.0d - lpphi2), this.hrw);
            double d4 = this.f1697rw * lplam;
            double lplam2 = d4;
            double cos = (((1.0d / v) + v) * 0.5d) + Math.cos(d4);
            double c = cos;
            if (cos >= TOL) {
                doubleR.f1626x = (Math.sin(lplam2) * 2.0d) / c;
                doubleR.f1627y = (v - (1.0d / v)) / c;
            } else {
                throw new ProjectionException();
            }
        }
        return doubleR;
    }

    public void setW(double w) {
        this.f1697rw = w;
    }

    public double getW() {
        return this.f1697rw;
    }

    public void initialize() {
        super.initialize();
        double d = this.f1697rw;
        if (d > 0.0d) {
            double d2 = 1.0d / d;
            this.f1697rw = d2;
            this.hrw = d2 * 0.5d;
            this.phi1 = this.projectionLatitude1;
            double sin = Math.sin(this.phi1);
            this.phi1 = sin;
            if (Math.abs(Math.abs(sin) - 1.0d) >= TOL) {
                double d3 = this.phi1;
                this.f1696a1 = Math.pow((1.0d - d3) / (d3 + 1.0d), this.hrw);
                return;
            }
            throw new ProjectionException("-22");
        }
        throw new ProjectionException("-27");
    }

    public boolean isConformal() {
        return true;
    }

    public boolean hasInverse() {
        return false;
    }

    public String toString() {
        return "Lagrange";
    }
}
