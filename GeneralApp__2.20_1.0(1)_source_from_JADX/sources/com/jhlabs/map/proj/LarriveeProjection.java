package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class LarriveeProjection extends Projection {
    private static final double SIXTH = 0.16666666666666666d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = lplam * 0.5d * (Math.sqrt(Math.cos(lpphi)) + 1.0d);
        out.f1627y = lpphi / (Math.cos(0.5d * lpphi) * Math.cos(SIXTH * lplam));
        return out;
    }

    public String toString() {
        return "Larrivee";
    }
}
