package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class NellHProjection extends Projection {
    private static final double EPS = 1.0E-7d;
    private static final int NITER = 9;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = lplam * 0.5d * (Math.cos(lpphi) + 1.0d);
        out.f1627y = (lpphi - Math.tan(0.5d * lpphi)) * 2.0d;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        Double doubleR = out;
        double p = xyy * 0.5d;
        int i = 9;
        while (i > 0) {
            double c = Math.cos(xyy * 0.5d);
            double tan = ((xyy - Math.tan(xyy / 2.0d)) - p) / (1.0d - (0.5d / (c * c)));
            double V = tan;
            doubleR.f1627y -= tan;
            if (Math.abs(V) < EPS) {
                break;
            }
            i--;
        }
        if (i == 0) {
            doubleR.f1627y = p < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
            doubleR.f1626x = xyx * 2.0d;
        } else {
            doubleR.f1626x = (xyx * 2.0d) / (Math.cos(xyy) + 1.0d);
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Nell-Hammer";
    }
}
