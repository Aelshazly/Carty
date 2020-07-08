package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class FoucautSinusoidalProjection extends Projection {
    private static final double LOOP_TOL = 1.0E-7d;
    private static final int MAX_ITER = 10;

    /* renamed from: n */
    private double f1685n;

    /* renamed from: n1 */
    private double f1686n1;

    public Double project(double lplam, double lpphi, Double out) {
        double t = Math.cos(lpphi);
        double d = lplam * t;
        double d2 = this.f1685n;
        double d3 = this.f1686n1;
        out.f1626x = d / ((d3 * t) + d2);
        out.f1627y = (d2 * lpphi) + (d3 * Math.sin(lpphi));
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double d = xyy;
        Double doubleR = out;
        if (this.f1685n != 0.0d) {
            doubleR.f1627y = d;
            int i = 10;
            while (i > 0) {
                double sin = (((this.f1685n * doubleR.f1627y) + (this.f1686n1 * Math.sin(doubleR.f1627y))) - d) / (this.f1685n + (this.f1686n1 * Math.cos(doubleR.f1627y)));
                double V = sin;
                doubleR.f1627y -= sin;
                if (Math.abs(V) < LOOP_TOL) {
                    break;
                }
                i--;
            }
            if (i == 0) {
                doubleR.f1627y = d < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
            }
        } else {
            doubleR.f1627y = MapMath.asin(xyy);
        }
        double V2 = Math.cos(doubleR.f1627y);
        doubleR.f1626x = ((this.f1685n + (this.f1686n1 * V2)) * xyx) / V2;
        return doubleR;
    }

    public void initialize() {
        super.initialize();
        double d = this.f1685n;
        if (d < 0.0d || d > 1.0d) {
            throw new ProjectionException("-99");
        }
        this.f1686n1 = 1.0d - d;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Foucaut Sinusoidal";
    }
}
