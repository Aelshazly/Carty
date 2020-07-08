package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class AitoffProjection extends PseudoCylindricalProjection {
    protected static final int AITOFF = 0;
    protected static final int WINKEL = 1;
    private double cosphi1 = 0.0d;
    private boolean winkel = false;

    public AitoffProjection() {
    }

    public AitoffProjection(int type, double projectionLatitude) {
        boolean z = false;
        this.projectionLatitude = projectionLatitude;
        if (type == 1) {
            z = true;
        }
        this.winkel = z;
    }

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double c = lplam * 0.5d;
        double d = Math.acos(Math.cos(lpphi) * Math.cos(c));
        if (d != 0.0d) {
            double cos = 2.0d * d * Math.cos(lpphi) * Math.sin(c);
            double sin = 1.0d / Math.sin(d);
            doubleR.f1627y = sin;
            doubleR.f1626x = cos * sin;
            doubleR.f1627y *= Math.sin(lpphi) * d;
        } else {
            doubleR.f1627y = 0.0d;
            doubleR.f1626x = 0.0d;
        }
        if (this.winkel) {
            doubleR.f1626x = (doubleR.f1626x + (this.cosphi1 * lplam)) * 0.5d;
            doubleR.f1627y = (doubleR.f1627y + lpphi) * 0.5d;
        }
        return doubleR;
    }

    public void initialize() {
        super.initialize();
        if (this.winkel) {
            this.cosphi1 = 0.6366197723675814d;
        }
    }

    public boolean hasInverse() {
        return false;
    }

    public String toString() {
        return this.winkel ? "Winkel Tripel" : "Aitoff";
    }
}
