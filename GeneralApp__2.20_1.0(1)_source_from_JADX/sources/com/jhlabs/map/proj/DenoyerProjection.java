package com.jhlabs.map.proj;

import java.awt.geom.Point2D.Double;

public class DenoyerProjection extends Projection {

    /* renamed from: C0 */
    public static final double f1665C0 = 0.95d;

    /* renamed from: C1 */
    public static final double f1666C1 = -0.08333333333333333d;

    /* renamed from: C3 */
    public static final double f1667C3 = 0.0016666666666666666d;

    /* renamed from: D1 */
    public static final double f1668D1 = 0.9d;

    /* renamed from: D5 */
    public static final double f1669D5 = 0.03d;

    public Double project(double lplam, double lpphi, Double out) {
        out.y = lpphi;
        out.x = lplam;
        double aphi = Math.abs(lplam);
        out.x *= Math.cos((((((aphi * aphi) * 0.0016666666666666666d) - 53.333333333333336d) * aphi) + 0.95d) * ((0.03d * lpphi * lpphi * lpphi * lpphi) + 0.9d) * lpphi);
        return out;
    }

    public boolean parallelsAreParallel() {
        return true;
    }

    public boolean hasInverse() {
        return false;
    }

    public String toString() {
        return "Denoyer Semi-elliptical";
    }
}
