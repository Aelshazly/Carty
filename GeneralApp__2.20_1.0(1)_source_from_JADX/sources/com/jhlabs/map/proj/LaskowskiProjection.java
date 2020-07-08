package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class LaskowskiProjection extends Projection {
    private static final double a10 = 0.975534d;
    private static final double a12 = -0.119161d;
    private static final double a14 = -0.0547009d;
    private static final double a32 = -0.0143059d;
    private static final double b01 = 1.00384d;
    private static final double b03 = 0.0998909d;
    private static final double b05 = -0.0491032d;
    private static final double b21 = 0.0802894d;
    private static final double b23 = -0.02855d;
    private static final double b41 = 1.99025E-4d;

    public Double project(double lplam, double lpphi, Double out) {
        double l2 = lplam * lplam;
        double p2 = lpphi * lpphi;
        out.f1626x = ((((a32 * l2) + a12 + (a14 * p2)) * p2) + a10) * lplam;
        out.f1627y = ((((b23 * p2) + b21 + (b41 * l2)) * l2) + b01 + (((b05 * p2) + b03) * p2)) * lpphi;
        return out;
    }

    public String toString() {
        return "Laskowski";
    }
}
