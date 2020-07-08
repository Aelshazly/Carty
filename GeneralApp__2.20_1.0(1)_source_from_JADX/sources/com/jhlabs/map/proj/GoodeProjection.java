package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class GoodeProjection extends Projection {
    private static final double PHI_LIM = 0.7109307819790236d;
    private static final double Y_COR = 0.0528d;
    private MolleweideProjection moll = new MolleweideProjection();
    private SinusoidalProjection sinu = new SinusoidalProjection();

    public Double project(double lplam, double lpphi, Double out) {
        if (Math.abs(lpphi) <= PHI_LIM) {
            return this.sinu.project(lplam, lpphi, out);
        }
        Double out2 = this.moll.project(lplam, lpphi, out);
        out2.f1627y -= lpphi >= 0.0d ? Y_COR : -0.0528d;
        return out2;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        if (Math.abs(xyy) <= PHI_LIM) {
            double d = xyy;
            return this.sinu.projectInverse(xyx, xyy, out);
        }
        double xyy2 = (xyy >= 0.0d ? Y_COR : -0.0528d) + xyy;
        double d2 = xyy2;
        return this.moll.projectInverse(xyx, xyy2, out);
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Goode Homolosine";
    }
}
