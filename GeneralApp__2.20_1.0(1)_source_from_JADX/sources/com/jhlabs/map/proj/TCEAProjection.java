package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class TCEAProjection extends Projection {
    private double rk0;

    public TCEAProjection() {
        initialize();
    }

    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = this.rk0 * Math.cos(lpphi) * Math.sin(lplam);
        out.f1627y = this.scaleFactor * (Math.atan2(Math.tan(lpphi), Math.cos(lplam)) - this.projectionLatitude);
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = (this.rk0 * xyy) + this.projectionLatitude;
        out.f1626x *= this.scaleFactor;
        double t = Math.sqrt(1.0d - (xyx * xyx));
        out.f1627y = Math.asin(Math.sin(xyy) * t);
        out.f1626x = Math.atan2(xyx, Math.cos(xyy) * t);
        return out;
    }

    public void initialize() {
        super.initialize();
        this.rk0 = 1.0d / this.scaleFactor;
    }

    public boolean isRectilinear() {
        return false;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Transverse Cylindrical Equal Area";
    }
}
