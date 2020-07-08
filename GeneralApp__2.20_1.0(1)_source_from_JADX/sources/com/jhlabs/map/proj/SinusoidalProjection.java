package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class SinusoidalProjection extends PseudoCylindricalProjection {
    public Double project(double lam, double phi, Double xy) {
        xy.f1626x = Math.cos(phi) * lam;
        xy.f1627y = phi;
        return xy;
    }

    public Double projectInverse(double x, double y, Double lp) {
        lp.f1626x = x / Math.cos(y);
        lp.f1627y = y;
        return lp;
    }

    public double getWidth(double y) {
        return MapMath.normalizeLongitude(3.141592653589793d) * Math.cos(y);
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Sinusoidal";
    }
}
